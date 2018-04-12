
package com.trans.api.service;

import org.springframework.http.ResponseEntity;

import com.trans.api.event.TransactionEvent;
import com.trans.api.event.TransactionStatistics;

/**
 * @author rkarim
 *
 */
public interface TransactionService {
  
  ResponseEntity<?> createTransaction(TransactionEvent data);
  
  TransactionStatistics getTransactionStatistics();

}
