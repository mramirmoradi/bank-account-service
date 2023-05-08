package com.mobilab.mobilab.Transaction.Mapper;

import com.mobilab.mobilab.Transaction.DTO.TransactionResponseDTO;
import com.mobilab.mobilab.Transaction.Entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapstruct {

    TransactionResponseDTO transactionToTransactionResponseDTO(Transaction transaction);

}
