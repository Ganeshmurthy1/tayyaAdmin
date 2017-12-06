package com.lintas.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ArithmeticUti {

	public static BigDecimal divideTo2Decimal(BigDecimal value1, BigDecimal value2)
	{
		if(value1!=null && value2!=null && !(value2.compareTo(new BigDecimal(0))==0))
		try{
			value1 = value1.divide(value2);			
		}
		catch(ArithmeticException ex)
		{			
			value1 = value1.divide(value2, 7, RoundingMode.HALF_UP);
		}
		return value1;
		//return value1.setScale(2);
	}
	public static BigDecimal multiplyTo2Decimal(BigDecimal value1, BigDecimal value2)
	{
		try{
			value1 = value1.multiply(value2);			
		}
		catch(ArithmeticException ex)
		{			
			//value1 = value1.multiply(value2, RoundingMode.HALF_UP);
		}
		return value1.setScale(2);
	}

	public static void main(String s[])
	{
		System.out.println(divideTo2Decimal(new BigDecimal(1), new BigDecimal(2)));
	}

}


