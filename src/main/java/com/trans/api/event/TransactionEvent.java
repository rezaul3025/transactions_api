package com.trans.api.event;

/**
 * Transaction input data
 * @author rkarim
 *
 */
public class TransactionEvent {

  private double amount;

  private long timestamp;
  
  public TransactionEvent(){
    
  }
  
  public TransactionEvent(double amount,long timestamp){
    this.amount = amount;
    this.timestamp = timestamp;
  }

  /**
   * @return the amount
   */
  public double getAmount() {
    return amount;
  }

  /**
   * @param amount the amount to set
   */
  public void setAmount(Double amount) {
    this.amount = amount;
  }

  /**
   * @return the timestamp
   */
  public long getTimestamp() {
    return timestamp;
  }

  /**
   * @param timestamp the timestamp to set
   */
  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }
  
}
