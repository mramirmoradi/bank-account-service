package com.mobilab.mobilab.Transferation.Service;

import com.mobilab.mobilab.Account.Entity.Account;
import com.mobilab.mobilab.Account.Service.AccountService;
import com.mobilab.mobilab.Base.Enum.Currency;
import com.mobilab.mobilab.Transaction.Enum.TransactionStatus;
import com.mobilab.mobilab.Transaction.Service.TransactionService;
import com.mobilab.mobilab.Transferation.Constants.TransferStatusMessages;
import com.mobilab.mobilab.Transferation.DTO.TransferRequestDTO;
import com.mobilab.mobilab.Transferation.DTO.TransferResponseDTO;
import com.mobilab.mobilab.Transferation.ECRServiceClient.DTO.ECRServiceResponse;
import com.mobilab.mobilab.Transferation.ECRServiceClient.ECRServiceClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author amirmoradi
 * @since 2022-12-03
 */
@Service
@RequiredArgsConstructor
public class TransferServiceImp implements TransferService {

    private final AccountService accountService;
    private final TransactionService transactionService;
    private final ECRServiceClient exchangeClient;

    @Getter
    @AllArgsConstructor
    private enum EXCEPTION_TYPE {
        NOT_ENOUGH_BALANCE (TransferStatusMessages.NOT_ENOUGH_BALANCE),
        ACCOUNT_NOT_FOUND(TransferStatusMessages.ACCOUNT_NOT_FOUND),
        EXCHANGE_SERVER_NOT_RESPOND(TransferStatusMessages.EXCHANGE_SERVER_NOT_RESPOND);
        private final String message;
    }


    /**
     * Transfer money between two Account
     * Transfer will happen by Account number
     * IF any account not found, the result will be as Failure (unsuccessfulTransfer)
     * IF the source account doesn't have enough balance, the result will be as failure (unsuccessfulTransfer)
     * IF any problem happened in fetching exchange rate from third party, the result will be as failure (unsuccessfulTransfer)
     * @param requestDTO as TransferRequestDTO.Class
     * @return TransferResponseDTO.Class, the response will be this in any Exception nether
     */
    @Transactional
    @Override
    public TransferResponseDTO transferRequest(TransferRequestDTO requestDTO) {
        double amount = requestDTO.getAmount();
        Account source;
        Account destination;
        String trackingCode;
        Double exchangeRate;

        // Fetching the account by account number
        try{
            source = accountService.findByNumber(requestDTO.getSourceAccountNumber());
            destination = accountService.findByNumber(requestDTO.getDestinationAccountNumber());
        } catch (Exception e) {
            return unsuccessfulTransfer(EXCEPTION_TYPE.ACCOUNT_NOT_FOUND);
        }

        // Check source balance is enough
        if (amountTransferValidation(source, amount))
            return unsuccessfulTransfer(EXCEPTION_TYPE.NOT_ENOUGH_BALANCE);

        // Fetch exchange rate
        exchangeRate = fetchExchangeRate(source, destination);

        // Any problem on fetching exchange rate
        if (exchangeRate == null)
            return unsuccessfulTransfer(EXCEPTION_TYPE.EXCHANGE_SERVER_NOT_RESPOND);

        // Transfer between accounts
        transfer(source, destination, amount, exchangeRate);

        // Submitting Transaction (Receiver, Send), fetching tracking code
        trackingCode = transactionService.submitTransaction(source, destination, amount, TransactionStatus.SUCCESS, exchangeRate);

        return successfulTransfer(source, destination, amount, trackingCode, exchangeRate);
    }

    /**
     * Decrease source account balance
     * Increase destination account balance
     * @param source as source Account.Class
     * @param destination as destination Account.Class
     * @param amount as amount money
     * @param exchangeRate as exchange rate between two accounts
     */
    private void transfer(Account source, Account destination, double amount, double exchangeRate) {
        accountService.decreaseBalance(source.getNumber(), amount);
        accountService.increaseBalance(destination.getNumber(), amount * exchangeRate);
    }

    /**
     * If both accounts currency is the same, the exchange rate will be 1.0
     * If source account currency is different from destination account, it will request to OpenExchangeRates third party api
     * Third party request will be sent by Feign
     * IMPORTANT: because OpenExchangeRates just allow for USD as Base currency, if our source account will be on EUR
     *              we will calculate the exchange rate by  { 1 / (Base USD) exchange Rate }
     * @param source as source Account.Class
     * @param destination as destination Account.Class
     * @return Double as exchangeRate, any exception -> return null
     */
    private Double fetchExchangeRate(Account source, Account destination) {
        Double exchangeRate;
        ECRServiceResponse response;
        try {
            response = exchangeClient.request(source.getCurrency().name(), destination.getCurrency().name());
        } catch (Exception e) {
            // Log microservice on message broker
            return null;
        }
        return response.getExchangeRate();
    }

    /**
     * Check source account has enough balance
     * @param source as source Account.Class
     * @param amount as amount of money
     * @return boolean as True: is valid transfer, False: is not valid for transfer
     */
    private boolean amountTransferValidation(Account source, double amount) {
        return source.getBalance() < amount;
    }

    /**
     * Return unsuccessful transfer response
     * @param exception_type as ACCOUNT_NOT_FOUND, NOT_ENOUGH_BALANCE, EXCHANGE_SERVER_NOT_RESPOND
     * @return TransferRespondDTO.Class
     */
    private TransferResponseDTO unsuccessfulTransfer(EXCEPTION_TYPE exception_type) {
        return TransferResponseDTO
                .builder()
                .trackingCode(null)
                .code(TransferStatusMessages.UNSUCCESSFUL_TRANSFER_CODE)
                .message(String.format(TransferStatusMessages.UNSUCCESSFUL_TRANSFER, exception_type.getMessage()))
                .build();
    }

    /**
     * Return successful transfer response
     * @param source as source account
     * @param destination as destination account
     * @param amount as amount of money
     * @param trackingCode as transaction tracking code
     * @param exchangeRate as exchange rate between two accounts
     * @return TransferRespondDTO.Class
     */
    private TransferResponseDTO successfulTransfer(Account source, Account destination, double amount, String trackingCode, Double exchangeRate) {
        return TransferResponseDTO
                .builder()
                .trackingCode(trackingCode)
                .code(TransferStatusMessages.SUCCESSFUL_TRANSFER_CODE)
                .message(successfulTransferMessage(source, destination, amount, exchangeRate))
                .build();
    }

    /**
     * Generate successful transfer message
     * amount x exchangeRate will be messaged
     * @param source as source account
     * @param destination as destination account
     * @param amount as amount of money
     * @param exchangeRate as exchange rate between two accounts
     * @return String as message
     */
    private String successfulTransferMessage(Account source, Account destination, double amount, Double exchangeRate) {
        return String.format(TransferStatusMessages.SUCCESSFUL_TRANSFER,
                amount*exchangeRate, destination.getCurrency(), source.getNumber(), destination.getNumber());
    }

}
