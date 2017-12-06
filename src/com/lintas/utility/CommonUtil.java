package com.lintas.utility;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.lintas.admin.vo.CutandPayModel;
 
 

public class CommonUtil {
	public static final Logger logger = Logger.getLogger(CommonUtil.class);	
	public static int getNoofStayDays(String checkIn,String checkOut
			) throws java.text.ParseException
	{
		DateTimeFormatter dateStringFormat = DateTimeFormat
				.forPattern("dd-MM-yyyy");
		DateTime firstTime = dateStringFormat.parseDateTime(checkIn);
		DateTime secondTime = dateStringFormat.parseDateTime(checkOut);
		int days = Days.daysBetween(new LocalDate(firstTime), new LocalDate(secondTime)).getDays();
		 
		return days;
	}
 
	
	public static String getCancellaionDTFormat(String dtstr)
	{	
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");		
		Calendar cal = Calendar.getInstance();		
		if(dtstr != null)
		{
			try {
				DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");				
				Date date = originalFormat.parse(dtstr);
				String formattedDate = sdf.format(date); 
				return formattedDate;
			} catch (ParseException e) {
				e.printStackTrace();
				return sdf.format(cal.getTime());		
			}
		}
		else
		{			
			return sdf.format(cal.getTime());			
		}

	}

	public static HashMap getDateTimeMapFromTimestamp(Timestamp timestamp)
	{		
		Date date = new Date(timestamp.getTime());
		// S is the millisecond
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
		SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");

		HashMap<String, String> datetimemap = new HashMap<String, String>();
		datetimemap.put("date", simpleDateFormat.format(date));
		datetimemap.put("time", simpleTimeFormat.format(date));
		return datetimemap;
	}
 
	public static int getNoofStayDaysDBSQL(String startdate, String enddate) throws java.text.ParseException
	{
		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();

		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		Date date = sdf.parse(startdate);
		cal1.setTime(date);
		date = sdf.parse(enddate);
		cal2.setTime(date);

		int noofdays = daysBetween(cal1.getTime(),cal2.getTime());
		//logger.info("Days= "+noofdays);
		return noofdays;	

	}
	public static Date getFormattedDateFromStringSQL(String dateStr) throws java.text.ParseException
	{
		//22 Dec 2015 00:00:00		
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		Date date = sdf.parse(dateStr);	
		return date;	

	}

	public static int daysBetween(Date d1, Date d2){
		//return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));

		long diff = d1.getTime() - d2.getTime();
		//logger.info("Days:--- " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
		return (int) diff;
	 

	}
 
	/* public static void main(String[] args) throws ParseException {
		int days=getNoofStayDays("6/11/2016", "6/11/2016");
		System.out.println(days);
	}
	 */
	 
	 public static Map<Integer,CutandPayModel> getCutandPayModelUsersFlight(List<User> userList, Map<String, BigDecimal> flightMarkups, BigDecimal finalPriceAfterTax, User currentUser, FlightOrderRow  flightOrderRow){
			Map<Integer,CutandPayModel> cutandpayMap = new LinkedHashMap<>();
			 
			try{
				BigDecimal payableamt = finalPriceAfterTax;
				 
				for (User payableUser : userList) {
					BigDecimal flightMarksForAllPassengers = new BigDecimal(0);
					BigDecimal commisionAmount = new BigDecimal(0);
					flightMarksForAllPassengers = flightMarkups!=null && flightMarkups.size()>0 ?flightMarkups.get(String.valueOf(payableUser.getCompanyid())):new BigDecimal(0);
					CutandPayModel cutandpay = new CutandPayModel();
					if(payableUser.getId()==currentUser.getId()){
						BigDecimal payableamtInner = finalPriceAfterTax.subtract(commisionAmount);
						cutandpay.setUserId(String.valueOf(payableUser.getId()));  
						cutandpay.setPayableAmount(payableamtInner);
//						cutandpay.setBookingRemarks(CommonBookingStatusEnum.FLIHT_REMARKS.getMessage());
						cutandpay.setBookingStatus(true);
					}
					else{
						//payableamt = payableamt.subtract(flightMarksForAllPassengers);
						payableamt = payableamt.subtract(flightOrderRow.getMarkUp());
						payableamt = payableamt.subtract(commisionAmount);
						cutandpay.setUserId(String.valueOf(payableUser.getId()));  
						cutandpay.setPayableAmount(payableamt);
//						cutandpay.setBookingRemarks(CommonBookingStatusEnum.FLIHT_REMARKS.getMessage());
						cutandpay.setBookingStatus(true);
					}
					cutandpayMap.put(payableUser.getId(), cutandpay);
				}
			}catch(Exception e){

			}
			return cutandpayMap;
		}
	 
	 
	 
	 public static Map<Integer,CutandPayModel> getCutandPayModelUsersHotel(List<User> userList, Map<String, BigDecimal> flightMarkups, BigDecimal finalPriceAfterTax, User currentUser){
			Map<Integer,CutandPayModel> cutandpayMap = new LinkedHashMap<>();
			 
			try{
				BigDecimal payableamt = finalPriceAfterTax;
				 
				for (User payableUser : userList) {
					BigDecimal flightMarksForAllPassengers = new BigDecimal(0);
					BigDecimal commisionAmount = new BigDecimal(0);
					flightMarksForAllPassengers = flightMarkups!=null && flightMarkups.size()>0 ?flightMarkups.get(String.valueOf(payableUser.getCompanyid())):new BigDecimal(0);
					CutandPayModel cutandpay = new CutandPayModel();
					if(payableUser.getId()==currentUser.getId()){
						BigDecimal payableamtInner = finalPriceAfterTax.subtract(commisionAmount);
						cutandpay.setUserId(String.valueOf(payableUser.getId()));  
						cutandpay.setPayableAmount(payableamtInner);
//						cutandpay.setBookingRemarks(CommonBookingStatusEnum.FLIHT_REMARKS.getMessage());
						cutandpay.setBookingStatus(true);
					}
					else{
						//payableamt = payableamt.subtract(flightMarksForAllPassengers);
						payableamt = payableamt.subtract(flightMarksForAllPassengers);
						payableamt = payableamt.subtract(commisionAmount);
						cutandpay.setUserId(String.valueOf(payableUser.getId()));  
						cutandpay.setPayableAmount(payableamt);
//						cutandpay.setBookingRemarks(CommonBookingStatusEnum.FLIHT_REMARKS.getMessage());
						cutandpay.setBookingStatus(true);
					}
					cutandpayMap.put(payableUser.getId(), cutandpay);
				}
			}catch(Exception e){

			}
			return cutandpayMap;
		}
	  
	 public static LinkedList<Company> getParentCompanyBottomToTop(int companyId, CompanyDAO CDAO) {
			Company company= CDAO.getCompanyProfile(companyId);
			Company companyTemp=company;
			LinkedList<Company> companies= new LinkedList<>();
			companies.add(companyTemp);
			while(companyTemp!=null && companyTemp.getCompanyRole()!=null && !companyTemp.getCompanyRole().isSuperUser())
			{
				companyTemp =CDAO.getParentCompany(companyTemp);
				companies.add(companyTemp);
			}
			return companies;

		}
		
		public static List<User> getUsersAllWithUserModeBottomToTop(List<Company> companyListBottomToTop, CompanyDAO CDAO, User currentUser) throws Exception {
			List<User> userList= new LinkedList<>();
			UserDAO userDAO= new UserDAO();
			if(companyListBottomToTop!=null && companyListBottomToTop.size()>0)
			{
				User userTemp = new User();
				for(Company companyInner : companyListBottomToTop)
				{
					if(currentUser.getCompanyid()==companyInner.getCompanyid())
					{
						userTemp = currentUser;
					}
					else{
						userTemp =userDAO.getUserPasswordForLock(companyInner.getEmail());
					}
					userList.add(userTemp);

				}
			}
			return userList;
		}
		
		public static long findDigitsFromString(String invoiceNumber){
			return  Long.parseLong(invoiceNumber.replaceAll("[^0-9]", ""));
		}
		public static String findStringFromString(String invoiceNumber){
			return invoiceNumber.replaceAll("[^a-zA-Z]", "");
		}
		
		 
		 
}
