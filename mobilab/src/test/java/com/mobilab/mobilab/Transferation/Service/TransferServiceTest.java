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
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @Mock private AccountService accountService;
    @Mock private TransactionService transactionService;
    private TransferService transferService;

    @Mock
    private ECRServiceClient exchangeClient;

    @BeforeEach
    void setUp() {
        transferService = new TransferServiceImp(accountService, transactionService, exchangeClient);
    }

    @Test
    void successfulTransferRequestWithoutThirdPartyExchange() {
        // Account config
        Account source =
                new Account("source", "source-description", "1234", 1L, Currency.USD);
        Account destination =
                new Account("destination", "destination-description", "4321", 2L, Currency.USD);
        source.setBalance(100.0);
        destination.setBalance(100.0);

        // Exchange config
        double exchangeRate = 1.0;

        // Transfer config
        double amount = 10.0;
        TransferRequestDTO requestDTO = new TransferRequestDTO();
        requestDTO.setSourceAccountNumber(source.getNumber());
        requestDTO.setDestinationAccountNumber(destination.getNumber());
        requestDTO.setAmount(amount);
        given(accountService.findByNumber(source.getNumber())).willReturn(source);
        given(accountService.findByNumber(destination.getNumber())).willReturn(destination);

        // Transaction config
        String trackingCode = "1212121212";
        given(transactionService.submitTransaction(source, destination, amount, TransactionStatus.SUCCESS, exchangeRate))
                .willReturn(trackingCode);

        // Exchange config
        ECRServiceResponse exchangeResponseDTO = new ECRServiceResponse();
        exchangeResponseDTO.setExchangeRate(exchangeRate);
        given(exchangeClient.request(source.getCurrency().name(), destination.getCurrency().name())).willReturn(exchangeResponseDTO);

        TransferResponseDTO responseDTO = transferService.transferRequest(requestDTO);

        assertThat(responseDTO.getCode()).isEqualTo(1);
        assertThat(responseDTO.getMessage())
                .isEqualTo(String.format(
                        TransferStatusMessages.SUCCESSFUL_TRANSFER,
                        requestDTO.getAmount()*exchangeRate,
                        destination.getCurrency(),
                        source.getNumber(),
                        destination.getNumber()));
        assertThat(responseDTO.getTrackingCode()).isEqualTo(trackingCode);
    }

    @Test
    void successfulTransferRequestByThirdPartyExchangeUSD_EUR() {
        // Account config
        Account source =
                new Account("source", "source-description", "1234", 1L, Currency.USD);
        Account destination =
                new Account("destination", "destination-description", "4321", 2L, Currency.EUR);

        source.setBalance(100.0);
        destination.setBalance(100.0);

        // Exchange config
        double exchangeRate = 0.9;
        ECRServiceResponse exchangeResponseDTO = new ECRServiceResponse();
        exchangeResponseDTO.setExchangeRate(exchangeRate);
        given(exchangeClient.request(source.getCurrency().name(), destination.getCurrency().name())).willReturn(exchangeResponseDTO);

        // Transfer request config
        double amount = 10.0;
        TransferRequestDTO requestDTO = new TransferRequestDTO();
        requestDTO.setSourceAccountNumber(source.getNumber());
        requestDTO.setDestinationAccountNumber(destination.getNumber());
        requestDTO.setAmount(amount);
        given(accountService.findByNumber(source.getNumber())).willReturn(source);
        given(accountService.findByNumber(destination.getNumber())).willReturn(destination);

        // Transaction config
        String trackingCode = "1212121212";
        given(transactionService.submitTransaction(source, destination, amount, TransactionStatus.SUCCESS, exchangeRate))
                .willReturn(trackingCode);

        TransferResponseDTO responseDTO = transferService.transferRequest(requestDTO);

        assertThat(responseDTO.getCode()).isEqualTo(1);
        assertThat(responseDTO.getMessage())
                .isEqualTo(String.format(
                        TransferStatusMessages.SUCCESSFUL_TRANSFER,
                        requestDTO.getAmount()*exchangeRate,
                        destination.getCurrency(),
                        source.getNumber(),
                        destination.getNumber()));
        assertThat(responseDTO.getTrackingCode()).isEqualTo(trackingCode);
    }

    @Test
    void successfulTransferRequestByThirdPartyExchangeEUR_USD() {
        // Account config
        Account source =
                new Account("source", "source-description", "1234", 1L, Currency.EUR);
        Account destination =
                new Account("destination", "destination-description", "4321", 2L, Currency.USD);

        source.setBalance(100.0);
        destination.setBalance(100.0);

        // Exchange config, our third party has limitation on base currency, it's always USD
        double exchangeRate = 0.9;
        ECRServiceResponse exchangeResponseDTO = new ECRServiceResponse();
        exchangeResponseDTO.setExchangeRate(exchangeRate);
        given(exchangeClient.request(source.getCurrency().name(), destination.getCurrency().name())).willReturn(exchangeResponseDTO);

        // Transfer request config
        double amount = 10.0;
        TransferRequestDTO requestDTO = new TransferRequestDTO();
        requestDTO.setSourceAccountNumber(source.getNumber());
        requestDTO.setDestinationAccountNumber(destination.getNumber());
        requestDTO.setAmount(amount);
        given(accountService.findByNumber(source.getNumber())).willReturn(source);
        given(accountService.findByNumber(destination.getNumber())).willReturn(destination);

        // Transaction config
        String trackingCode = "1212121212";
        given(transactionService.submitTransaction(source, destination, amount, TransactionStatus.SUCCESS, exchangeRate))
                .willReturn(trackingCode);

        TransferResponseDTO responseDTO = transferService.transferRequest(requestDTO);

        assertThat(responseDTO.getCode()).isEqualTo(1);
        assertThat(responseDTO.getMessage())
                .isEqualTo(String.format(
                        TransferStatusMessages.SUCCESSFUL_TRANSFER,
                        requestDTO.getAmount()*exchangeRate,
                        destination.getCurrency(),
                        source.getNumber(),
                        destination.getNumber()));
        assertThat(responseDTO.getTrackingCode()).isEqualTo(trackingCode);
    }

    @Test
    void unsuccessfulTransferRequest_NOT_ENOUGH_BALANCE() {
        // Account config
        Account source =
                new Account("source", "source-description", "1234", 1L, Currency.USD);
        Account destination =
                new Account("destination", "destination-description", "4321", 2L, Currency.USD);
        source.setBalance(100.0);
        destination.setBalance(100.0);

        // Transfer config
        double amount = 110.0;
        TransferRequestDTO requestDTO = new TransferRequestDTO();
        requestDTO.setSourceAccountNumber(source.getNumber());
        requestDTO.setDestinationAccountNumber(destination.getNumber());
        requestDTO.setAmount(amount);
        given(accountService.findByNumber(source.getNumber())).willReturn(source);
        given(accountService.findByNumber(destination.getNumber())).willReturn(destination);

        TransferResponseDTO responseDTO = transferService.transferRequest(requestDTO);

        assertThat(responseDTO.getCode()).isEqualTo(0);
        assertThat(responseDTO.getMessage())
                .isEqualTo(String.format(
                        TransferStatusMessages.UNSUCCESSFUL_TRANSFER,
                        TransferStatusMessages.NOT_ENOUGH_BALANCE));
        assertThat(responseDTO.getTrackingCode()).isEqualTo(null);
    }

    @Test
    void unsuccessfulTransferRequest_ACCOUNT_NOT_FOUND() {
        // Account config
        Account source =
                new Account("source", "source-description", "1234", 1L, Currency.USD);
        Account destination =
                new Account("destination", "destination-description", "4321", 2L, Currency.USD);
        source.setBalance(100.0);
        destination.setBalance(100.0);

        // Transfer config
        double amount = 10.0;
        TransferRequestDTO requestDTO = new TransferRequestDTO();
        requestDTO.setSourceAccountNumber(source.getNumber());
        requestDTO.setDestinationAccountNumber(destination.getNumber());
        requestDTO.setAmount(amount);
        given(accountService.findByNumber(source.getNumber())).willThrow(EntityNotFoundException.class);
//        given(accountService.findByNumber(destination.getNumber())).willThrow(EntityNotFoundException.class);

        TransferResponseDTO responseDTO = transferService.transferRequest(requestDTO);

        assertThat(responseDTO.getCode()).isEqualTo(0);
        assertThat(responseDTO.getMessage())
                .isEqualTo(String.format(
                        TransferStatusMessages.UNSUCCESSFUL_TRANSFER,
                        TransferStatusMessages.ACCOUNT_NOT_FOUND));
        assertThat(responseDTO.getTrackingCode()).isEqualTo(null);
    }

    @Test
    void unsuccessfulTransferRequestByThirdPartyExchange_EXCHANGE_SERVER_NOT_RESPOND() {
        // Account config
        Account source =
                new Account("source", "source-description", "1234", 1L, Currency.EUR);
        Account destination =
                new Account("destination", "destination-description", "4321", 2L, Currency.USD);

        source.setBalance(100.0);
        destination.setBalance(100.0);

        // Transfer request config
        double amount = 10.0;
        TransferRequestDTO requestDTO = new TransferRequestDTO();
        requestDTO.setSourceAccountNumber(source.getNumber());
        requestDTO.setDestinationAccountNumber(destination.getNumber());
        requestDTO.setAmount(amount);
        given(accountService.findByNumber(source.getNumber())).willReturn(source);
        given(accountService.findByNumber(destination.getNumber())).willReturn(destination);

        // Exchange config, our third party has limitation on base currency, it's always USD
        given(exchangeClient.request(source.getCurrency().name(), destination.getCurrency().name())).willAnswer( invocationOnMock -> { throw  new TimeoutException(); });

        TransferResponseDTO responseDTO = transferService.transferRequest(requestDTO);

        assertThat(responseDTO.getCode()).isEqualTo(0);
        assertThat(responseDTO.getMessage())
                .isEqualTo(String.format(
                        TransferStatusMessages.UNSUCCESSFUL_TRANSFER,
                        TransferStatusMessages.EXCHANGE_SERVER_NOT_RESPOND));
        assertThat(responseDTO.getTrackingCode()).isEqualTo(null);
    }
}