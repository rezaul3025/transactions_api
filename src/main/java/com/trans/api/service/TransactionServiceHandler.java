
package com.trans.api.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.trans.api.event.TransactionEvent;
import com.trans.api.event.TransactionStatistics;
import com.trans.api.utils.Constant;

/**
 * @author rkarim
 *
 */
@Service
public class TransactionServiceHandler implements TransactionService {

  /*
   * (non-Javadoc)
   * 
   * @see com.trans.api.service.TransactionService#createTransaction(com.trans.api.event.
   * TransactionEvent)
   */
  @Override
  public ResponseEntity<?> createTransaction(TransactionEvent data) {

    // String transactionID = UUID.randomUUID().toString();

    LocalDateTime transactionTimestampInUtc = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(data.getTimestamp()), ZoneId.of(Constant.UTC_TIME_ZONE_IND));

    LocalDateTime currentTimestampInUtc = LocalDateTime.now(ZoneId.of(Constant.UTC_TIME_ZONE_IND));

    long transactionTimeInseconds =
        transactionTimestampInUtc.until(currentTimestampInUtc, ChronoUnit.SECONDS);

    if (transactionTimeInseconds > 0
        && transactionTimeInseconds <= Constant.TRANSACTION_TIME_LIMIT) {
      Constant.TRANSACTION_TEMP_STORAGE.put(data.getTimestamp(), data);
      return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
  }

  /* (non-Javadoc)
   * @see com.trans.api.service.TransactionService#getTransactionStatistics()
   */
  @Override
  public TransactionStatistics getTransactionStatistics() {
    //Get TransactionStatistics with specified time frame. In this example its : 60 Seconds
    Long localTimeStart = LocalDateTime.now(ZoneId.of(Constant.UTC_TIME_ZONE_IND)).minusSeconds(Constant.TRANSACTION_TIME_LIMIT).toEpochSecond(ZoneOffset.UTC)*1000;
    Long localTimeEnd = LocalDateTime.now(ZoneId.of(Constant.UTC_TIME_ZONE_IND)).toEpochSecond(ZoneOffset.UTC)*1000;
    
    List<Double> tempList = new ArrayList<>();
    
    double sum = 0;
    
    for(long i = localTimeStart;i<localTimeEnd;i++){
      TransactionEvent transData = Constant.TRANSACTION_TEMP_STORAGE.get(i);
      
      if(transData != null){
        double ammount = transData.getAmount();
        sum += ammount;
        tempList.add(ammount);
      }
      
    }
    
    int count = tempList.size();
    
    double avg = sum/count;
    
    double max = Collections.max(tempList);
    
    double min = Collections.min(tempList);
    
    TransactionStatistics stat = new TransactionStatistics(sum, avg , max, min, count);
    
    return stat;
  }

}
