package com.mobilab.mobilab.Transaction.Repository;

import com.mobilab.mobilab.Base.Repository.BaseRepository;
import com.mobilab.mobilab.Transaction.Entity.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends BaseRepository<Transaction> {
}
