package com.trans.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trans.api.event.TransactionEvent;
import com.trans.api.event.TransactionStatistics;
import com.trans.api.service.TransactionService;


/**
 * @author rkarim
 *
 */
@RestController
@RequestMapping(value="/api/v1/transaction")
public class TransactionApiController {
  
  @Autowired
  private TransactionService transactionService;
  
  @RequestMapping(value="/create", method=RequestMethod.POST)
  public ResponseEntity<?> create(@RequestBody TransactionEvent data){
    
   return transactionService.createTransaction(data);
  }
  
  @RequestMapping(value="/statistics", method=RequestMethod.GET)
  public TransactionStatistics statistics(){
    
   return transactionService.getTransactionStatistics();
  }
  
  

}
