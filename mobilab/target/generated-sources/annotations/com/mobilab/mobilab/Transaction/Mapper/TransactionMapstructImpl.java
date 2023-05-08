package com.mobilab.mobilab.Transaction.Mapper;

import com.mobilab.mobilab.Transaction.DTO.TransactionResponseDTO;
import com.mobilab.mobilab.Transaction.Entity.Transaction;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-06T20:51:36+0430",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4 (Amazon.com Inc.)"
)
@Component
public class TransactionMapstructImpl implements TransactionMapstruct {

    @Override
    public TransactionResponseDTO transactionToTransactionResponseDTO(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();

        transactionResponseDTO.setId( transaction.getId() );
        transactionResponseDTO.setAccountNumber( transaction.getAccountNumber() );
        transactionResponseDTO.setSourceAccountNumber( transaction.getSourceAccountNumber() );
        transactionResponseDTO.setDestinationAccountNumber( transaction.getDestinationAccountNumber() );
        transactionResponseDTO.setAmount( transaction.getAmount() );
        if ( transaction.getExchangeRate() != null ) {
            transactionResponseDTO.setExchangeRate( transaction.getExchangeRate() );
        }
        transactionResponseDTO.setSourceCurrency( transaction.getSourceCurrency() );
        transactionResponseDTO.setDestinationCurrency( transaction.getDestinationCurrency() );
        transactionResponseDTO.setStatus( transaction.getStatus() );
        transactionResponseDTO.setType( transaction.getType() );
        transactionResponseDTO.setTrackingCode( transaction.getTrackingCode() );
        transactionResponseDTO.setCreatedDate( transaction.getCreatedDate() );

        return transactionResponseDTO;
    }
}
