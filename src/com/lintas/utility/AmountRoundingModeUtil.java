package com.lintas.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.log4j.Logger;

public class AmountRoundingModeUtil {
	public static final Logger logger = Logger.getLogger(AmountRoundingModeUtil.class);	
	public static BigDecimal roundingMode(BigDecimal amount){
		BigDecimal convertamount = new BigDecimal(0);		
		String mode = "UP";

		switch (mode) {
		case "UP":
			convertamount = amount.setScale(0, RoundingMode.UP);	
			break;
		case "DOWN":
			convertamount = amount.setScale(0, RoundingMode.DOWN);	
			break;
		case "FLOOR":
			convertamount = amount.setScale(0, RoundingMode.FLOOR);	
			break;
		case "CEILING":
			convertamount = amount.setScale(0, RoundingMode.CEILING);	
			break;
		case "HALF_UP":
			convertamount = amount.setScale(0, RoundingMode.HALF_UP);	
			break;
		case "HALF_DOWN":
			convertamount = amount.setScale(0, RoundingMode.HALF_DOWN);	
			break;
		case "HALF_EVEN":
			convertamount = amount.setScale(0, RoundingMode.HALF_EVEN);	
			break;
		case "ORIGINAL":
			convertamount = amount;
			break;
		default:
			convertamount = amount;
			break;
		}				

		return convertamount;
	}

	public static BigDecimal roundingModeForHotel(BigDecimal amount){
		BigDecimal convertamount = new BigDecimal(0);		
		String mode = "UP";

		switch (mode) {
		case "UP":
			convertamount = amount.setScale(0, RoundingMode.UP);	
			break;
		case "DOWN":
			convertamount = amount.setScale(0, RoundingMode.DOWN);	
			break;
		case "FLOOR":
			convertamount = amount.setScale(0, RoundingMode.FLOOR);	
			break;
		case "CEILING":
			convertamount = amount.setScale(0, RoundingMode.CEILING);	
			break;
		case "HALF_UP":
			convertamount = amount.setScale(0, RoundingMode.HALF_UP);	
			break;
		case "HALF_DOWN":
			convertamount = amount.setScale(0, RoundingMode.HALF_DOWN);	
			break;
		case "HALF_EVEN":
			convertamount = amount.setScale(0, RoundingMode.HALF_EVEN);	
			break;
		case "ORIGINAL":
			convertamount = amount;
			break;
		default:
			convertamount = amount;
			break;
		}			
		return convertamount;
	}

}
