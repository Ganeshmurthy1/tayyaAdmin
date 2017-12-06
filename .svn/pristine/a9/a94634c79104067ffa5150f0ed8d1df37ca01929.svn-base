package com.lintas.admin.DAO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.HotelCommissionReport;
import com.lintas.admin.model.HotelOrderRowCommission;
import com.lintas.admin.model.HotelOrderRowMarkup;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.CommonUtil;
import com.lintas.utility.DateConversion;
import com.lintas.utility.DateFilter;
import com.lintas.utility.GstPropertiesFile;
import com.lintas.utility.InvoiceFilter;

public class HotelCommissionDao {
	Session session = null;
	Transaction transaction = null;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelCommissionDao.class);
	public  List<HotelCommissionReport> getHotelCommissionReport(User userSessionObj,Company companySessionObj, InvoiceFilter filterObj){
		List<HotelCommissionReport>  commissionList=new ArrayList<HotelCommissionReport>();
		String commissionSql=null;
		Query query=null;
		Criteria criteria=null;
		BigDecimal perVal = new BigDecimal(new GstPropertiesFile().getGstorTdsValue());
		String type=new GstPropertiesFile().getTDSorGSTType();
		BigDecimal hundred = new BigDecimal(100);
		BigDecimal totMymarkupWithGuestwithNightCount=null; 
		int totGuestwithNightCount; 
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(userSessionObj!=null && companySessionObj!=null){
				criteria=session.createCriteria(HotelOrderRowCommission.class);
				criteria.add(Restrictions.eq("CompanyId",String.valueOf(companySessionObj.getCompanyid())));
				criteria.addOrder(Order.desc("id"));
			  }
			List<HotelOrderRowCommission>  parentandChildCommissionsList=criteria.list();
			if(companySessionObj.getCompanyRole().isDistributor()||  companySessionObj.getCompanyRole().isAgent()){
				for(HotelOrderRowCommission hotelOrderRowCommissionParent:parentandChildCommissionsList){
					List<String> childCompanyList=searchForChildCompanyID(hotelOrderRowCommissionParent, companySessionObj,session);
					int childcompanyId=getChildCompanyId(childCompanyList,companySessionObj.getCompany_userid(),session);
					logger.info("childcompanyId------"+childcompanyId);
					HotelOrderRowCommission hotelOrderRowCommissionForChild=null;
					if(childcompanyId!=0){
						hotelOrderRowCommissionForChild=getHotelOrderRowCommission(childcompanyId,hotelOrderRowCommissionParent.getHotelOrderRow().getId(),session);
						logger.info("childflightOrderRowCommission id:-"+hotelOrderRowCommissionForChild.getHotelOrderRow().getId()+"...child.commission :"+hotelOrderRowCommissionForChild.getCommission());  
					}
					//	FlightOrderRow hotelOrderRow =getFlightOrderRowListByOrderId(hotelOrderRowCommissionParent.getHotelOrderRow().getId());
					HotelOrderRow hotelOrderRow =null;
					if(hotelOrderRowCommissionParent.getHotelOrderRow().getStatusAction().equalsIgnoreCase("Confirmed")){
						hotelOrderRow =hotelOrderRowCommissionParent.getHotelOrderRow();

						if(filterObj!=null && filterObj.getFromDate()!=null && filterObj.getToDate()!=null){
							List<String> dateList=DateFilter.getDatelist(filterObj.getFromDate(), filterObj.getToDate());
							if(dateList!=null){
								for(String bookingDate:dateList){
									if(bookingDate.equals(DateConversion.convertDateToStringDate(hotelOrderRow.getCreatedAt()))){
										HotelCommissionReport hotelCommissionReport=new HotelCommissionReport();
										BigDecimal parentTotCommission=new BigDecimal("0.00");
										BigDecimal childTotCommission=new BigDecimal("0.00");
										BigDecimal FinalPriceinBaseCurrency=hotelOrderRow.getFinalPrice().divide(hotelOrderRow.getBaseToBookingExchangeRate(),2,RoundingMode.UP);

										if(hotelOrderRowCommissionForChild!=null){
											childTotCommission=hotelOrderRowCommissionForChild.getCommissionAmountValue();
										}
										HotelOrderRowMarkup mymarkupObj=getMarkup(hotelOrderRowCommissionParent.getCompanyId(),hotelOrderRowCommissionParent.getHotelOrderRow().getId(),session);
										//BigDecimal Mymarkup=getMarkup(hotelOrderRowCommissionParent.getCompanyId(),hotelOrderRowCommissionParent.getHotelOrderRow().getId(),session);
										 BigDecimal Mymarkup=mymarkupObj.getMarkUp();
										 String checkInDate=DateConversion.convertDateToStringToDate(mymarkupObj.getHotelOrderRow().getCheckInDate());
											String checkOutDate=DateConversion.convertDateToStringToDate(mymarkupObj.getHotelOrderRow().getCheckOutDate());
											int days=CommonUtil.getNoofStayDays(checkInDate, checkOutDate);
											int totalGuestMarkupCount=days*mymarkupObj.getHotelOrderRow().getTotalGuest();
											totMymarkupWithGuestwithNightCount=Mymarkup.multiply(new BigDecimal(totalGuestMarkupCount)).setScale(2,BigDecimal.ROUND_UP);
										
										
										BigDecimal distmarkup=new BigDecimal("0.00");
										if(childcompanyId!=0){
											HotelOrderRowMarkup distmarkupObj =getMarkup(hotelOrderRowCommissionForChild.getCompanyId(),hotelOrderRowCommissionForChild.getHotelOrderRow().getId(),session);
											//distmarkup=getMarkup(hotelOrderRowCommissionForChild.getCompanyId(),hotelOrderRowCommissionForChild.getHotelOrderRow().getId(),session);
											distmarkup=distmarkupObj.getMarkUp();
											logger.info("hotelOrderRowCommissionForChild id:-"+hotelOrderRowCommissionForChild.getHotelOrderRow().getId()+"...hotelOrderRowCommissionParent.gethotelOrderRow().getId() :"+hotelOrderRowCommissionParent.getHotelOrderRow().getId()); 
										}

										parentTotCommission=hotelOrderRowCommissionParent.getCommissionAmountValue();
										BigDecimal totalOtherMarkup=Mymarkup.add(distmarkup);									 
										BigDecimal totalMarkup=hotelOrderRow.getMarkupAmount();
										BigDecimal agencymarkup=totalMarkup.subtract(totalOtherMarkup);					
										BigDecimal myprofit=parentTotCommission.add(totMymarkupWithGuestwithNightCount).subtract(childTotCommission);
										BigDecimal ticketPrice=FinalPriceinBaseCurrency.subtract(totalMarkup);
										hotelCommissionReport.setMarkup(totMymarkupWithGuestwithNightCount);
										hotelCommissionReport.setDistributorMarkup(distmarkup.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setChildMarkup(agencymarkup.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setTicketPrice(ticketPrice.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setOrderRef(hotelOrderRow.getOrderReference());
										hotelCommissionReport.setFinalPrice(FinalPriceinBaseCurrency.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setCurCode(hotelOrderRow.getBookingCurrency());
										hotelCommissionReport.setFinalCommission(myprofit.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setMyProfit(myprofit.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setSharedCommission(childTotCommission.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setMyCommission(parentTotCommission.add(totMymarkupWithGuestwithNightCount).setScale(2,BigDecimal.ROUND_UP));//Totalcommission in design(commission +my markup)
										hotelCommissionReport.setTDS(perVal);
										hotelCommissionReport.setTdsType(type);
										//hotelCommissionReport.setAgentCommByRate(myprofit.setScale(2,BigDecimal.ROUND_UP));
										BigDecimal tdsOnCommission=null;
										if(type.equalsIgnoreCase("TDS")){
											if(companySessionObj.getCompanyRole().isAgent()){
												tdsOnCommission=parentTotCommission.divide(hundred).multiply(perVal);
												hotelCommissionReport.setTdsOnCommission(tdsOnCommission.setScale(2,BigDecimal.ROUND_UP));
												hotelCommissionReport.setMyProfitwithDeductTDS(myprofit.subtract(tdsOnCommission).setScale(2,BigDecimal.ROUND_UP));

											}
											if(companySessionObj.getCompanyRole().isDistributor()){
												BigDecimal parentComm=parentTotCommission.subtract(childTotCommission);
												tdsOnCommission=parentComm.divide(hundred).multiply(perVal);
												hotelCommissionReport.setTdsOnCommission(tdsOnCommission.setScale(2,BigDecimal.ROUND_UP));
												hotelCommissionReport.setMyProfitwithDeductTDS(myprofit.subtract(tdsOnCommission).setScale(2,BigDecimal.ROUND_UP));
											}
											hotelCommissionReport.setAgentCommByRate(myprofit.subtract(tdsOnCommission).setScale(2,BigDecimal.ROUND_UP));
										}
										if(type.equalsIgnoreCase("GST")){
											hotelCommissionReport.setAgentCommByRate(myprofit.setScale(2,BigDecimal.ROUND_UP));
										}

										hotelCommissionReport.setStatus(hotelOrderRow.getStatusAction());
										hotelCommissionReport.setCreatedBy(hotelOrderRow.getCreatedBy());
										hotelCommissionReport.setCheckInDate(DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckInDate()));
										hotelCommissionReport.setCheckOutDate(DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckOutDate()));
										commissionList.add(hotelCommissionReport);
									}
								}
							}
						}
						else{
							List<String> dateList=DateFilter.getPrevious30Days(-29, 0);
							if(dateList!=null){
								for(String bookingDate:dateList){
									if(bookingDate.equals(DateConversion.convertDateToStringDate(hotelOrderRow.getCreatedAt()))){
										HotelCommissionReport hotelCommissionReport=new HotelCommissionReport();
										BigDecimal parentTotCommission=new BigDecimal("0.00");
										BigDecimal childTotCommission=new BigDecimal("0.00");
										BigDecimal FinalPriceinBaseCurrency=hotelOrderRow.getFinalPrice().divide(hotelOrderRow.getBaseToBookingExchangeRate(),2,RoundingMode.UP);

										if(hotelOrderRowCommissionForChild!=null){
											childTotCommission=hotelOrderRowCommissionForChild.getCommissionAmountValue();
										}
										HotelOrderRowMarkup   mymarkupObj= getMarkup(hotelOrderRowCommissionParent.getCompanyId(),hotelOrderRowCommissionParent.getHotelOrderRow().getId(),session);
										//BigDecimal Mymarkup=getMarkup(hotelOrderRowCommissionParent.getCompanyId(),hotelOrderRowCommissionParent.getHotelOrderRow().getId(),session);
										BigDecimal Mymarkup=mymarkupObj.getMarkUp();
										String checkInDate=DateConversion.convertDateToStringToDate(mymarkupObj.getHotelOrderRow().getCheckInDate());
										String checkOutDate=DateConversion.convertDateToStringToDate(mymarkupObj.getHotelOrderRow().getCheckOutDate());
										int days=CommonUtil.getNoofStayDays(checkInDate, checkOutDate);
										int totalGuestMarkupCount=days*mymarkupObj.getHotelOrderRow().getTotalGuest();
										totMymarkupWithGuestwithNightCount=Mymarkup.multiply(new BigDecimal(totalGuestMarkupCount)).setScale(2,BigDecimal.ROUND_UP);
										
										
										
										BigDecimal distmarkup=new BigDecimal("0.00");
										if(childcompanyId!=0){
											HotelOrderRowMarkup distmarkupObj=getMarkup(hotelOrderRowCommissionForChild.getCompanyId(),hotelOrderRowCommissionForChild.getHotelOrderRow().getId(),session);
											//distmarkup=getMarkup(hotelOrderRowCommissionForChild.getCompanyId(),hotelOrderRowCommissionForChild.getHotelOrderRow().getId(),session);
											distmarkup=distmarkupObj.getMarkUp();
											logger.info("hotelOrderRowCommissionForChild id:-"+hotelOrderRowCommissionForChild.getHotelOrderRow().getId()+"...hotelOrderRowCommissionParent.gethotelOrderRow().getId() :"+hotelOrderRowCommissionParent.getHotelOrderRow().getId()); 
										}

										parentTotCommission=hotelOrderRowCommissionParent.getCommissionAmountValue();
										BigDecimal totalOtherMarkup=Mymarkup.add(distmarkup);									 
										BigDecimal totalMarkup=hotelOrderRow.getMarkupAmount();
										BigDecimal agencymarkup=totalMarkup.subtract(totalOtherMarkup);					
										BigDecimal myprofit=parentTotCommission.add(totMymarkupWithGuestwithNightCount).subtract(childTotCommission);
										BigDecimal ticketPrice=FinalPriceinBaseCurrency.subtract(totalMarkup);

										
										hotelCommissionReport.setMarkup(totMymarkupWithGuestwithNightCount);
										hotelCommissionReport.setDistributorMarkup(distmarkup.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setChildMarkup(agencymarkup.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setTicketPrice(ticketPrice.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setOrderRef(hotelOrderRow.getOrderReference());
										hotelCommissionReport.setFinalPrice(FinalPriceinBaseCurrency.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setCurCode(hotelOrderRow.getBookingCurrency());
										hotelCommissionReport.setFinalCommission(myprofit.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setMyProfit(myprofit.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setSharedCommission(childTotCommission.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setMyCommission(parentTotCommission.add(totMymarkupWithGuestwithNightCount).setScale(2,BigDecimal.ROUND_UP));//Totalcommission in design(commission +my markup)
										hotelCommissionReport.setTDS(perVal);
										hotelCommissionReport.setTdsType(type);
										//hotelCommissionReport.setAgentCommByRate(myprofit.setScale(2,BigDecimal.ROUND_UP));
										BigDecimal tdsOnCommission=null;
										if(type.equalsIgnoreCase("TDS")){
											if(companySessionObj.getCompanyRole().isAgent()){
												tdsOnCommission=parentTotCommission.divide(hundred).multiply(perVal);
												hotelCommissionReport.setTdsOnCommission(tdsOnCommission.setScale(2,BigDecimal.ROUND_UP));
												hotelCommissionReport.setMyProfitwithDeductTDS(myprofit.subtract(tdsOnCommission).setScale(2,BigDecimal.ROUND_UP));

											}
											if(companySessionObj.getCompanyRole().isDistributor()){
												BigDecimal parentComm=parentTotCommission.subtract(childTotCommission);
												tdsOnCommission=parentComm.divide(hundred).multiply(perVal);
												hotelCommissionReport.setTdsOnCommission(tdsOnCommission.setScale(2,BigDecimal.ROUND_UP));
												hotelCommissionReport.setMyProfitwithDeductTDS(myprofit.subtract(tdsOnCommission).setScale(2,BigDecimal.ROUND_UP));
											}
											hotelCommissionReport.setAgentCommByRate(myprofit.subtract(tdsOnCommission).setScale(2,BigDecimal.ROUND_UP));
										}
										if(type.equalsIgnoreCase("GST")){
											hotelCommissionReport.setAgentCommByRate(myprofit.setScale(2,BigDecimal.ROUND_UP));
										}


										hotelCommissionReport.setStatus(hotelOrderRow.getStatusAction());
										hotelCommissionReport.setCreatedBy(hotelOrderRow.getCreatedBy());
										hotelCommissionReport.setCheckInDate(DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckInDate()));
										hotelCommissionReport.setCheckOutDate(DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckOutDate()));
										commissionList.add(hotelCommissionReport);
									}
								}
							}
						}

					}
					if(hotelOrderRow==null){
						continue;
					}

				}
			}

			/////////////Super user
			//else if(userSessionObj.getUserrole_id().isSuperuser() || userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
			else if(!companySessionObj.getCompanyRole().isAgent() && !companySessionObj.getCompanyRole().isDistributor()){
				for(HotelOrderRowCommission hotelOrderRowCommissionParent:parentandChildCommissionsList){
					List<String> childCompanyList=searchForChildCompanyID(hotelOrderRowCommissionParent, companySessionObj,session);
					int childcompanyId=getChildCompanyId(childCompanyList,companySessionObj.getCompany_userid(),session);
					logger.info("childcompanyId------"+childcompanyId);
					HotelOrderRowCommission hotelOrderRowCommissionForChild = null;
					if(childcompanyId!=0){
						hotelOrderRowCommissionForChild=getHotelOrderRowCommission(childcompanyId,hotelOrderRowCommissionParent.getHotelOrderRow().getId(),session);
						logger.info("childflightOrderRowCommission id:-"+hotelOrderRowCommissionForChild.getHotelOrderRow().getId()+"...child.commission :"+hotelOrderRowCommissionForChild.getCommission());  
					}
					//	FlightOrderRow flightOrderRow =getFlightOrderRowListByOrderId(flightOrderRowCommissionParent.getFlightOrderRow().getId());
					HotelOrderRow hotelOrderRow =null;

					if(hotelOrderRowCommissionParent.getHotelOrderRow().getStatusAction().equalsIgnoreCase("Confirmed")){
						hotelOrderRow =hotelOrderRowCommissionParent.getHotelOrderRow();

						if(filterObj!=null && filterObj.getFromDate()!=null && filterObj.getToDate()!=null){
							List<String> dateList=DateFilter.getDatelist(filterObj.getFromDate(), filterObj.getToDate());
							if(dateList!=null){
								for(String bookingDate:dateList){
									if(bookingDate.equals(DateConversion.convertDateToStringDate(hotelOrderRow.getCreatedAt()))){
										HotelCommissionReport hotelCommissionReport=new HotelCommissionReport();
										BigDecimal parentTotCommission=new BigDecimal("0.00");
										BigDecimal childTotCommission=new BigDecimal("0.00");
										BigDecimal FinalPriceinBaseCurrency=hotelOrderRow.getFinalPrice().divide(hotelOrderRow.getBaseToBookingExchangeRate(),2,RoundingMode.UP);

										if(hotelOrderRowCommissionForChild!=null){
											childTotCommission=hotelOrderRowCommissionForChild.getCommissionAmountValue();
										}
										HotelOrderRowMarkup  mymarkupObj=getMarkup(hotelOrderRowCommissionParent.getCompanyId(),hotelOrderRowCommissionParent.getHotelOrderRow().getId(),session);
										//BigDecimal Mymarkup=getMarkup(hotelOrderRowCommissionParent.getCompanyId(),hotelOrderRowCommissionParent.getHotelOrderRow().getId(),session);
										BigDecimal Mymarkup=mymarkupObj.getMarkUp();
										String checkInDate=DateConversion.convertDateToStringToDate(mymarkupObj.getHotelOrderRow().getCheckInDate());
										String checkOutDate=DateConversion.convertDateToStringToDate(mymarkupObj.getHotelOrderRow().getCheckOutDate());
										int days=CommonUtil.getNoofStayDays(checkInDate, checkOutDate);
										int totalGuestMarkupCount=days*mymarkupObj.getHotelOrderRow().getTotalGuest();
										totMymarkupWithGuestwithNightCount=Mymarkup.multiply(new BigDecimal(totalGuestMarkupCount)).setScale(2,BigDecimal.ROUND_UP);
										
										BigDecimal distmarkup=new BigDecimal("0.00");
										if(childcompanyId!=0){
											HotelOrderRowMarkup distmarkupObj=getMarkup(hotelOrderRowCommissionForChild.getCompanyId(),hotelOrderRowCommissionForChild.getHotelOrderRow().getId(),session);
											 //distmarkup=getMarkup(hotelOrderRowCommissionForChild.getCompanyId(),hotelOrderRowCommissionForChild.getHotelOrderRow().getId(),session);
											distmarkup=distmarkupObj.getMarkUp();
											logger.info("hotelOrderRowCommissionForChild id:-"+hotelOrderRowCommissionForChild.getHotelOrderRow().getId()+"...hotelOrderRowCommissionParent.gethotelOrderRow().getId() :"+hotelOrderRowCommissionParent.getHotelOrderRow().getId()); 
										}

										parentTotCommission=hotelOrderRowCommissionParent.getCommissionAmountValue();
										BigDecimal totalOtherMarkup=Mymarkup.add(distmarkup);									 
										BigDecimal totalMarkup=hotelOrderRow.getMarkupAmount();
										BigDecimal agencymarkup=totalMarkup.subtract(totalOtherMarkup);					
										BigDecimal myprofit=parentTotCommission.add(totMymarkupWithGuestwithNightCount).subtract(childTotCommission);
										BigDecimal ticketPrice=FinalPriceinBaseCurrency.subtract(totalMarkup);
										hotelCommissionReport.setMarkup(totMymarkupWithGuestwithNightCount);
										hotelCommissionReport.setDistributorMarkup(distmarkup.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setChildMarkup(agencymarkup.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setTicketPrice(ticketPrice.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setOrderRef(hotelOrderRow.getOrderReference());
										hotelCommissionReport.setFinalPrice(FinalPriceinBaseCurrency.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setCurCode(hotelOrderRow.getBookingCurrency());
										hotelCommissionReport.setFinalCommission(myprofit.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setMyProfit(myprofit.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setSharedCommission(childTotCommission.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setMyCommission(parentTotCommission.add(totMymarkupWithGuestwithNightCount).setScale(2,BigDecimal.ROUND_UP));//Totalcommission in design(commission +my markup)
										hotelCommissionReport.setAgentCommByRate(myprofit.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setStatus(hotelOrderRow.getStatusAction());
										hotelCommissionReport.setCreatedBy(hotelOrderRow.getCreatedBy());
										hotelCommissionReport.setCheckInDate(DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckInDate()));
										hotelCommissionReport.setCheckOutDate(DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckOutDate()));
										commissionList.add(hotelCommissionReport);
									}
								}
							}
						}
						else{
							List<String> dateList=DateFilter.getPrevious30Days(-29, 0);
							if(dateList!=null){
								for(String bookingDate:dateList){
									if(bookingDate.equals(DateConversion.convertDateToStringDate(hotelOrderRow.getCreatedAt()))){
										HotelCommissionReport hotelCommissionReport=new HotelCommissionReport();
										BigDecimal parentTotCommission=new BigDecimal("0.00");
										BigDecimal childTotCommission=new BigDecimal("0.00");
										BigDecimal FinalPriceinBaseCurrency=hotelOrderRow.getFinalPrice().divide(hotelOrderRow.getBaseToBookingExchangeRate(),2,RoundingMode.UP);

										if(hotelOrderRowCommissionForChild!=null){
											childTotCommission=hotelOrderRowCommissionForChild.getCommissionAmountValue();
										}
										
										 HotelOrderRowMarkup mymarkupObj=getMarkup(hotelOrderRowCommissionParent.getCompanyId(),hotelOrderRowCommissionParent.getHotelOrderRow().getId(),session);
										//BigDecimal Mymarkup=getMarkup(hotelOrderRowCommissionParent.getCompanyId(),hotelOrderRowCommissionParent.getHotelOrderRow().getId(),session);
										
										 BigDecimal Mymarkup=mymarkupObj.getMarkUp();
										 String checkInDate=DateConversion.convertDateToStringToDate(mymarkupObj.getHotelOrderRow().getCheckInDate());
											String checkOutDate=DateConversion.convertDateToStringToDate(mymarkupObj.getHotelOrderRow().getCheckOutDate());
											int days=CommonUtil.getNoofStayDays(checkInDate, checkOutDate);
											int totalGuestMarkupCount=days*mymarkupObj.getHotelOrderRow().getTotalGuest();
											totMymarkupWithGuestwithNightCount=Mymarkup.multiply(new BigDecimal(totalGuestMarkupCount)).setScale(2,BigDecimal.ROUND_UP);
											
										 BigDecimal distmarkup=new BigDecimal("0.00");
										if(childcompanyId!=0){
											 HotelOrderRowMarkup distmarkupObj=getMarkup(hotelOrderRowCommissionForChild.getCompanyId(),hotelOrderRowCommissionForChild.getHotelOrderRow().getId(),session);
											 //distmarkup=getMarkup(hotelOrderRowCommissionForChild.getCompanyId(),hotelOrderRowCommissionForChild.getHotelOrderRow().getId(),session);
											 distmarkup=distmarkupObj.getMarkUp();
											 logger.info("hotelOrderRowCommissionForChild id:-"+hotelOrderRowCommissionForChild.getHotelOrderRow().getId()+"...hotelOrderRowCommissionParent.gethotelOrderRow().getId() :"+hotelOrderRowCommissionParent.getHotelOrderRow().getId()); 
										}

										parentTotCommission=hotelOrderRowCommissionParent.getCommissionAmountValue();
										BigDecimal totalOtherMarkup=Mymarkup.add(distmarkup);									 
										BigDecimal totalMarkup=hotelOrderRow.getMarkupAmount();
										BigDecimal agencymarkup=totalMarkup.subtract(totalOtherMarkup);					
										BigDecimal myprofit=parentTotCommission.add(totMymarkupWithGuestwithNightCount).subtract(childTotCommission);
										BigDecimal ticketPrice=FinalPriceinBaseCurrency.subtract(totalMarkup);
										 
										hotelCommissionReport.setMarkup(totMymarkupWithGuestwithNightCount);
										hotelCommissionReport.setDistributorMarkup(distmarkup.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setChildMarkup(agencymarkup.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setTicketPrice(ticketPrice.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setOrderRef(hotelOrderRow.getOrderReference());
										hotelCommissionReport.setFinalPrice(FinalPriceinBaseCurrency.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setCurCode(hotelOrderRow.getBookingCurrency());
										hotelCommissionReport.setFinalCommission(myprofit.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setMyProfit(myprofit.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setSharedCommission(childTotCommission.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setMyCommission(parentTotCommission.add(totMymarkupWithGuestwithNightCount).setScale(2,BigDecimal.ROUND_UP));//Totalcommission in design(commission +my markup)
										hotelCommissionReport.setAgentCommByRate(myprofit.setScale(2,BigDecimal.ROUND_UP));
										hotelCommissionReport.setStatus(hotelOrderRow.getStatusAction());
										hotelCommissionReport.setCreatedBy(hotelOrderRow.getCreatedBy());
										hotelCommissionReport.setCheckInDate(DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckInDate()));
										hotelCommissionReport.setCheckOutDate(DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckOutDate()));
										commissionList.add(hotelCommissionReport);
									}
								}
							}
						}

					}
					if(hotelOrderRow==null){
						continue;
					}
				}
			}
		}
		catch (HibernateException e) {
			e.printStackTrace();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return commissionList;
	}
	/*	method for find the parent id for child*/
	public List<String>  searchForChildCompanyID(HotelOrderRowCommission order_row_id,Company sessionCompany,Session session){
		String childQuery=null;
		List<String> childlist=new ArrayList<String>();
		List<HotelOrderRowCommission> foclist=null;
		Criteria criteria= session.createCriteria(HotelOrderRowCommission.class);
		LogicalExpression expression=Restrictions.and(Restrictions.eq("CompanyId", String.valueOf(sessionCompany.getCompanyid())), Restrictions.eq("hotelOrderRow.id", order_row_id.getHotelOrderRow().getId()));
		criteria.add(expression);
		foclist=criteria.list();
		/* childQuery="from HotelOrderRowCommission horc  where horc.CompanyId!=:compid and horc.hotelOrderRow.id=:order_row_id";
		logger.info("------------------childQuery------------------------"+childQuery);
		Query query = session.createQuery(childQuery);
		query.setParameter("compid",String.valueOf(sessionCompany.getCompanyid()));
		query.setParameter("order_row_id",order_row_id.getHotelOrderRow().getId());
		foclist=query.list();*/
		logger.info("hoclist  ..."+foclist.size());
		for(HotelOrderRowCommission forc:foclist){
			childlist.add(forc.getCompanyId());
		}
		return childlist;
	}
	public int getChildCompanyId(List<String> childCompanyList ,String Company_userid,Session session){
		int childCompanyID=0;
		for(String companyId:childCompanyList){
			String sql = "from Company com  where  com.parent_company_userid=:parent_company_userid and com.companyid=:companyid";
			Query query = session.createQuery(sql);
			query.setParameter("parent_company_userid", Company_userid);
			query.setParameter("companyid", Integer.parseInt(companyId));
			List<Company> results = query.list();
			if(results!=null && results.size()>0){
				childCompanyID=Integer.parseInt(companyId);
				break;
			}
		}
		return childCompanyID;
	}

	public  HotelOrderRowCommission getHotelOrderRowCommission(int childcompanyId,long orderId,Session session){
		HotelOrderRowCommission  flightOrderRowIdschild=null;
		 
		Criteria criteria= session.createCriteria(HotelOrderRowCommission.class);
		LogicalExpression expression=Restrictions.and(Restrictions.eq("hotelOrderRow.id", orderId), Restrictions.eq("CompanyId",String.valueOf(childcompanyId)));
		criteria.add(expression);
		/* String sql = "from HotelOrderRowCommission forc where forc.hotelOrderRow.id=:rowId and forc.CompanyId=:companyId";
		logger.info("----------HotelOrderRowids for child----sql-----------------"+sql);
		Query query = session.createQuery(sql);
		query.setParameter("rowId",orderId);
		query.setParameter("companyId",String.valueOf(childcompanyId));*/
		flightOrderRowIdschild = (HotelOrderRowCommission) criteria.list().get(0);
		return flightOrderRowIdschild;

	}

	public HotelOrderRowMarkup getMarkup(String CompanyId,long Id,Session session){
		BigDecimal markup=new BigDecimal("0.00");
		HotelOrderRowMarkup  hotelOrderRowMarkup=null;
		Criteria criteria= session.createCriteria(HotelOrderRowMarkup.class);
		LogicalExpression expression=Restrictions.and(Restrictions.eq("hotelOrderRow.id", Id), Restrictions.eq("CompanyId",CompanyId));
		criteria.add(expression);
		List<HotelOrderRowMarkup> list=criteria.list();
		/*;
		String sql = "from HotelOrderRowMarkup frm where frm.hotelOrderRow.id=:order_row_id and frm.CompanyId=:company_id";
		logger.info("----------HotelOrderRowids for markup----sql-----------------"+sql);
		Query query = session.createQuery(sql);
		query.setParameter("order_row_id",Id);
		query.setParameter("company_id",CompanyId);*/
		if(list !=null &&  list.size()>0){
			hotelOrderRowMarkup = list.get(0);
			if(hotelOrderRowMarkup!=null) 
				markup=hotelOrderRowMarkup.getMarkUp();	
		}
		return hotelOrderRowMarkup;
	}
}

