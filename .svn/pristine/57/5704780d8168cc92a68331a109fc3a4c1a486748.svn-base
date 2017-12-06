package com.lintas.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.admin.payment.recievable.PaymentRecievable;

public class RandomConfigurationNumber {
	public  static String generateRandomTxID(){
		// TODO Auto-generated method stub
		final SimpleDateFormat sdfDate = new SimpleDateFormat("yyMMddHHmmssS");//dd/MM/yyyy
		Date now = new Date();
		final String strDate = sdfDate.format(now);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1);
					//logger.info("------"+strDate);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		return "TX"+strDate;	
	}
	public  static String generateRandomPaxID(){
		// TODO Auto-generated method stub
		final SimpleDateFormat sdfDate = new SimpleDateFormat("yyMMddHHmmssS");//dd/MM/yyyy
		Date now = new Date();
		final String strDate = sdfDate.format(now);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1);
					//logger.info("------"+strDate);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		return "PX"+strDate;	
	}
	public  static String generateBugReferenceNumber(Long id){
		final SimpleDateFormat sdfDate = new SimpleDateFormat("yyMMddHHmmssS");//dd/MM/yyyy
		Date now = new Date();
		final String strDate = sdfDate.format(now);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		return "B-"+String.valueOf(id)+"-"+strDate;	
	} 

	public  static Long generateTripId(Long id){
		// TODO Auto-generated method stub
		long count=100;
		id=count+id;
		//logger.info(id);
		return id;
	} 

	public  static String generateHotelOfflineBookingNumber(){
		// TODO Auto-generated method stub
		final SimpleDateFormat sdfDate = new SimpleDateFormat("yyMMddHHmmssS");//dd/MM/yyyy
		Date now = new Date();
		final String strDate = sdfDate.format(now);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1);
					//logger.info("------"+strDate);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		return "HOF"+strDate;	
	} 
	public  static String generateHotelOfflineConfirmationNumber(){
		// TODO Auto-generated method stub
		final SimpleDateFormat sdfDate = new SimpleDateFormat("yyMMddHHmmssS");//dd/MM/yyyy
		Date now = new Date();
		final String strDate = sdfDate.format(now);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1);
					//logger.info("------"+strDate);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		return "TYH"+strDate;	
	} 

	public static String generateHotelandFlightPaymentTxKey(){
		// TODO Auto-generated method stub
		final SimpleDateFormat sdfDate = new SimpleDateFormat("yyMMddHHmmssS");//dd/MM/yyyy
		Date now = new Date();
		final String strDate = sdfDate.format(now);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1);
					//logger.info("------"+strDate);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		return "TK-"+strDate;	
	} 









	/* public  static String generateFlightOfflineInvoiceNumber(){
			// TODO Auto-generated method stub
			  	final SimpleDateFormat sdfDate = new SimpleDateFormat("yyMMddHHmmssS");//dd/MM/yyyy
					   Date now = new Date();
					    final String strDate = sdfDate.format(now);
					    new Thread(new Runnable() {
							 @Override
							public void run() {
								// TODO Auto-generated method stub
								    try {
										Thread.sleep(1);
										//logger.info("------"+strDate);
									 } catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
							 }
							 }).start();
						return "TYF"+strDate;	
		} 
	 */
	public  static String generateHotelOfflineInvoiceNumber(long id){
		return "TYH"+String.valueOf(id);	
	}

	public  static String generateFlightOfflineInvoiceNumber(long id){
		return "TYA"+String.valueOf(id);	
	}


	public  static String generateCarInvoiceNumber(long id){
		return "TYC"+String.valueOf(id);	
	}

	public  static String generateBusInvoiceNumber(long id){
		return "TYB"+String.valueOf(id);	
	}

	public  static String generateTrainInvoiceNumber(long id){
		return "TYT"+String.valueOf(id);	
	}

	public  static String generateInsurenceInvoiceNumber(long id){
		return "TYI"+String.valueOf(id);	
	}

	public  static String generateVisaInvoiceNumber(long id){
		return "TYV"+String.valueOf(id);	
	}
	public  static String generateMisslaniousInvoiceNumber(long id){
		return "TYM"+String.valueOf(id);	
	}
	public  static String generatePaymentRef(long id,PaymentRecievable paymentRecievable){
		String ref=null;
		if(paymentRecievable.getPaymentType().equals("Debit") && paymentRecievable.getPaymentMode().equals("Cash")) 
			ref="CRV";
		if(paymentRecievable.getPaymentType().equals("Debit") && !paymentRecievable.getPaymentMode().equals("Cash")) 
			ref="BRV";
		
		if(paymentRecievable.getPaymentType().equals("Credit") && paymentRecievable.getPaymentMode().equals("Cash")) 
			ref="CPV";
		if(paymentRecievable.getPaymentType().equals("Credit") && !paymentRecievable.getPaymentMode().equals("Cash")) 
			ref="BPV";
		if((paymentRecievable.getPaymentType().equals("Credit") || paymentRecievable.getPaymentType().equals("Debit")) && paymentRecievable.getPaymentMode().equals("JV")) 
			ref="JV";
		return ref+String.valueOf(id);	
	}

	/* public  static String generateHotelOfflineInvoiceNumber(long id){
			// TODO Auto-generated method stub
			  	final SimpleDateFormat sdfDate = new SimpleDateFormat("yyMM");//dd/MM/yyyy
					   Date now = new Date();
					    final String strDate = sdfDate.format(now);
					    new Thread(new Runnable() {
							 @Override
							public void run() {
								// TODO Auto-generated method stub
								    try {
										Thread.sleep(1);
										//logger.info("------"+strDate);
									 } catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
							 }
							 }).start();
						return "TYH"+strDate+String.valueOf(id);	
		} */


	public static String generateFlightOfflineBookingNumber() {
		// TODO Auto-generated method stub
		final SimpleDateFormat sdfDate = new SimpleDateFormat("yyMMddHHmmssS");//dd/MM/yyyy
		Date now = new Date();
		final String strDate = sdfDate.format(now);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1);
					//logger.info("------"+strDate);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		return "FOF"+strDate;	
	} 


	public static String generateConfigNumber(String configID){
		String randomString=null;


		switch(configID.length()){
		case 1: 
			randomString="GIFNOC";
			//configNumber=randomString.concat(configID);
			break;
		case 2: 
			randomString="GIFNO";
			//configNumber=randomString.concat(configID);
			break;
		case 3: 
			randomString="GIFN";
			//configNumber=randomString.concat(configID);
			break;
		case 4: 
			randomString="GIF";
			//configNumber=randomString.concat(configID);
			break;
		case 5: 
			randomString="GI";
			//configNumber=randomString.concat(configID);
			break;
		case 6: 
			randomString="G";
			//configNumber=randomString.concat(configID);
			break;
		default: 
			randomString="";
			//configNumber=randomString.concat(configID);
			break;

		}
		return randomString.concat(configID);
	} 


	public static String generateCreditNoteInvoiceNumber(String configID,String creditNoteType){
		String randomString=null;
		switch(configID.length()){
		case 1: 
			randomString="000000";
			//configNumber=randomString.concat(configID);
			break;
		case 2: 
			randomString="00000";
			//configNumber=randomString.concat(configID);
			break;
		case 3: 
			randomString="0000";
			//configNumber=randomString.concat(configID);
			break;
		case 4: 
			randomString="000";
			//configNumber=randomString.concat(configID);
			break;
		case 5: 
			randomString="00";
			//configNumber=randomString.concat(configID);
			break;
		case 6: 
			randomString="0";
			//configNumber=randomString.concat(configID);
			break;
		default: 
			randomString="";
			//configNumber=randomString.concat(configID);
			break;

		}
		/* if(creditNoteType.equals("F")){
				 CNType=creditNoteType;
			 }
			 if(creditNoteType.equals("H")){
				 CNType=creditNoteType;
			 }*/


		return creditNoteType+"CNI"+randomString.concat(configID);
	} 

	public static String getEncryptedEmailCode(String configID){
		return new encryptions().encrypt(configID);
	} 

	/*public static void main(String[] args) {
	  logger.info(generateHotelOfflineInvoiceNumber(100002l));

}*/

	public  static String generateRandomNmmber(){
		// TODO Auto-generated method stub
		final SimpleDateFormat sdfDate = new SimpleDateFormat("yyMMddHHmmssS");//dd/MM/yyyy
		Date now = new Date();
		final String strDate = sdfDate.format(now);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1);
					//logger.info("------"+strDate);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		return strDate;	
	}


}
