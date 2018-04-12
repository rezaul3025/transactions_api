package com.trans.api.utils;

import java.util.concurrent.ConcurrentHashMap;

import com.trans.api.event.TransactionEvent;

/**
 * 
 * @author rkarim
 *
 */
public class Constant {
	
  public static final int TRANSACTION_TIME_LIMIT = 60;

  public static final String UTC_TIME_ZONE_IND = "UTC";

  public static final ConcurrentHashMap<Long, TransactionEvent> TRANSACTION_TEMP_STORAGE = new ConcurrentHashMap<>();
}
