
package com.trans.api.event;

/**
 * @author rkarim
 *
 */
public class TransactionStatistics {
  
  private double sum;
  
  private double avg;
  
  private double max;
  
  private double min;
  
  private int count;
  
  public TransactionStatistics(){
    
  }
  
  public TransactionStatistics(double sum, double avg, double max, double min, int count){
    this.sum = sum;
    this.avg = avg;
    this.max = max;
    this.min = min;
    this.count = count;
  }

  /**
   * @return the sum
   */
  public double getSum() {
    return sum;
  }

  /**
   * @param sum the sum to set
   */
  public void setSum(double sum) {
    this.sum = sum;
  }

  /**
   * @return the avg
   */
  public double getAvg() {
    return avg;
  }

  /**
   * @param avg the avg to set
   */
  public void setAvg(double avg) {
    this.avg = avg;
  }

  /**
   * @return the max
   */
  public double getMax() {
    return max;
  }

  /**
   * @param max the max to set
   */
  public void setMax(double max) {
    this.max = max;
  }

  /**
   * @return the min
   */
  public double getMin() {
    return min;
  }

  /**
   * @param min the min to set
   */
  public void setMin(double min) {
    this.min = min;
  }

  /**
   * @return the count
   */
  public int getCount() {
    return count;
  }

  /**
   * @param count the count to set
   */
  public void setCount(int count) {
    this.count = count;
  }
  
  @Override
  public String toString(){
    return this.sum+", "+this.avg+", "+this.max+", "+this.min+", "+this.count;
  }
  
}
