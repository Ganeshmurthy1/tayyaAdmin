package com.lintas.admin.DAO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lintas.admin.flight.model.FlightCommissionReport;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.FlightOrderRowCommission;
import com.lintas.admin.model.FlightOrderRowMarkup;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;
import com.lintas.utility.DateFilter;
import com.lintas.utility.GstPropertiesFile;
import com.lintas.utility.InvoiceFilter;

public class FlightCommissionDao {
	Session session = null;
	Transaction transaction = null;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(FlightCommissionDao.class);
	public  List<FlightCommissionReport> getFlightCommissionReport(User userSessionObj,Company companySessionObj, InvoiceFilter filterObj){
		List<FlightCommissionReport>  commissionList=new ArrayList<FlightCommissionReport>();
		BigDecimal perVal = new BigDecimal(new GstPropertiesFile().getGstorTdsValue());
		String type=new GstPropertiesFile().getTDSorGSTType();
		BigDecimal hundred = new BigDecimal(100);
		BigDecimal totMymarkupWithPaxCount=null; 
		String commissionSql=null;
		Query query=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(userSessionObj!=null && companySessionObj!=null){
				commissionSql = "from FlightOrderRowCommission forc where  forc.CompanyId=:companyId order by forc.id desc";
				logger.info("-----super user commissionSql----------"+commissionSql);
				query=session.createQuery(commissionSql);
				query.setParameter("companyId", String.valueOf(companySessionObj.getCompanyid()));
			}
			List<FlightOrderRowCommission>  parentandChildCommissionsList=query.list(); 
			//if(userSessionObj.getUserrole_id().isUsermode() && (companySessionObj.getCompanyRole().isDistributor()||  companySessionObj.getCompanyRole().isAgent())){
			if(companySessionObj.getCompanyRole().isDistributor()||  companySessionObj.getCompanyRole().isAgent()){
				for(FlightOrderRowCommission flightOrderRowCommissionParent:parentandChildCommissionsList){
					logger.info("flightOrderRowCommission id:-"+flightOrderRowCommissionParent.getFlightOrderRow().getApiCurrency()+"....commission :"+flightOrderRowCommissionParent.getFlightOrderRow().getAirline()); 
					List<String> childCompanyList=searchForChildCompanyID(flightOrderRowCommissionParent, companySessionObj,session);
					int childcompanyId=getChildCompanyId(childCompanyList,companySessionObj.getCompany_userid(),session);

					FlightOrderRowCommission flightOrderRowCommissionForChild=null;
					if(childcompanyId!=0){
						flightOrderRowCommissionForChild=getFlightOrderRowCommission(childcompanyId,flightOrderRowCommissionParent.getFlightOrderRow().getId(),session);
						logger.info("childflightOrderRowCommission id:-"+flightOrderRowCommissionForChild.getFlightOrderRow().getId()+"...child.commission :"+flightOrderRowCommissionForChild.getCommission());  
					}
					//	FlightOrderRow flightOrderRow =getFlightOrderRowListByOrderId(flightOrderRowCommissionParent.getFlightOrderRow().getId());
						FlightOrderRow flightOrderRow =null;

					if(flightOrderRowCommissionParent.getFlightOrderRow().getStatusAction().equalsIgnoreCase("Confirmed")){
						flightOrderRow =flightOrderRowCommissionParent.getFlightOrderRow();

						if(filterObj!=null && filterObj.getFromDate()!=null && filterObj.getToDate()!=null){
							List<String> dateList=DateFilter.getDatelist(filterObj.getFromDate(), filterObj.getToDate());
							if(dateList!=null){
								for(String bookingDate:dateList){
									//logger.info("bookingDate----"+bookingDate+"flightOrderRow.getBookingDate----"+flightOrderRow.getBookingDate());
									if(bookingDate.equals(flightOrderRow.getBookingDate())){
										FlightCommissionReport flightCommissionReport=new FlightCommissionReport();
										BigDecimal parentTotCommission=new BigDecimal("0.00");
										BigDecimal childTotCommission=new BigDecimal("0.00");
										BigDecimal FinalPriceinBaseCurrency=flightOrderRow.getFinalPrice().divide(flightOrderRow.getBaseToBookingExchangeRate(),2,RoundingMode.UP);
										if(flightOrderRowCommissionForChild!=null){
											childTotCommission=flightOrderRowCommissionForChild.getCommissionAmountValue();
										}
										
										FlightOrderRowMarkup myMarkupObj=getMarkup(flightOrderRowCommissionParent.getCompanyId(),flightOrderRowCommissionParent.getFlightOrderRow().getId(),session);
										 ///BigDecimal Mymarkup=getMarkup(flightOrderRowCommissionParent.getCompanyId(),flightOrderRowCommissionParent.getFlightOrderRow().getId(),session);
										BigDecimal Mymarkup=myMarkupObj.getMarkUp();
										totMymarkupWithPaxCount=Mymarkup.multiply(new BigDecimal(myMarkupObj.getFlightOrderRow().getPassengerCount())).setScale(2,BigDecimal.ROUND_UP);
										
										BigDecimal distmarkup=new BigDecimal("0.00");
										if(childcompanyId!=0){
											FlightOrderRowMarkup disMarkupObj=getMarkup(flightOrderRowCommissionForChild.getCompanyId(),flightOrderRowCommissionForChild.getFlightOrderRow().getId(),session);
											distmarkup=disMarkupObj.getMarkUp();
											//distmarkup=getMarkup(flightOrderRowCommissionForChild.getCompanyId(),flightOrderRowCommissionForChild.getFlightOrderRow().getId(),session);
											logger.info("flightOrderRowCommissionForChild id:-"+flightOrderRowCommissionForChild.getFlightOrderRow().getId()+"...flightOrderRowCommissionParent.getFlightOrderRow().getId() :"+flightOrderRowCommissionParent.getFlightOrderRow().getId()); 
										}

										parentTotCommission=flightOrderRowCommissionParent.getCommissionAmountValue();
										BigDecimal totalOtherMarkup=Mymarkup.add(distmarkup);									 
										BigDecimal totalMarkup=flightOrderRow.getMarkUp();
										BigDecimal agencymarkup=totalMarkup.subtract(totalOtherMarkup);					
										BigDecimal myprofit=parentTotCommission.add(totMymarkupWithPaxCount).subtract(childTotCommission);
										BigDecimal ticketPrice=FinalPriceinBaseCurrency.subtract(totalMarkup);
										flightCommissionReport.setMarkup(totMymarkupWithPaxCount);
										flightCommissionReport.setDistributorMarkup(distmarkup.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setChildMarkup(agencymarkup.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setTicketPrice(ticketPrice.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setOrderId(flightOrderRow.getOrderId());
										flightCommissionReport.setFinalPrice(FinalPriceinBaseCurrency.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setCurCode(flightOrderRow.getBookingCurrency());
										flightCommissionReport.setFinalCommission(myprofit.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setMyProfit(myprofit.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setSharedCommission(childTotCommission.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setMyCommission(parentTotCommission.add(totMymarkupWithPaxCount).setScale(2,BigDecimal.ROUND_UP));//Totalcommission in design(commission +my markup)
										flightCommissionReport.setTDS(perVal);
										flightCommissionReport.setTdsType(type);
										//hotelCommissionReport.setAgentCommByRate(myprofit.setScale(2,BigDecimal.ROUND_UP));
										BigDecimal tdsOnCommission=null;
										if(type.equalsIgnoreCase("TDS")){
											if(companySessionObj.getCompanyRole().isAgent()){
												tdsOnCommission=parentTotCommission.divide(hundred).multiply(perVal);
												flightCommissionReport.setTdsOnCommission(tdsOnCommission.setScale(2,BigDecimal.ROUND_UP));
												flightCommissionReport.setMyProfitwithDeductTDS(myprofit.subtract(tdsOnCommission).setScale(2,BigDecimal.ROUND_UP));

											}
											if(companySessionObj.getCompanyRole().isDistributor()){
												BigDecimal parentComm=parentTotCommission.subtract(childTotCommission);
												tdsOnCommission=parentComm.divide(hundred).multiply(perVal);
												flightCommissionReport.setTdsOnCommission(tdsOnCommission.setScale(2,BigDecimal.ROUND_UP));
												flightCommissionReport.setMyProfitwithDeductTDS(myprofit.subtract(tdsOnCommission).setScale(2,BigDecimal.ROUND_UP));
											}
											flightCommissionReport.setAgentCommByRate(myprofit.subtract(tdsOnCommission).setScale(2,BigDecimal.ROUND_UP));
										}
										if(type.equalsIgnoreCase("GST")){
											flightCommissionReport.setAgentCommByRate(myprofit.setScale(2,BigDecimal.ROUND_UP));
										}


										//flightCommissionReport.setAgentCommByRate(myprofit.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setStatus(flightOrderRow.getStatusAction());
										flightCommissionReport.setCreatedBy(flightOrderRow.getCreatedBy());
										flightCommissionReport.setBookingDate(DateConversion.getBluestarDate(flightOrderRow.getBookingDate()));
										commissionList.add(flightCommissionReport);
									}
								}
							}
						}
						else{
							List<String> dateList=DateFilter.getPrevious30Days(-29, 0);
							if(dateList!=null){
								for(String bookingDate:dateList){
									//logger.info("bookingDate----"+bookingDate+"flightOrderRow.getBookingDate----"+flightOrderRow.getBookingDate());
									if(bookingDate.equals(flightOrderRow.getBookingDate())){
										FlightCommissionReport flightCommissionReport=new FlightCommissionReport();
										BigDecimal parentTotCommission=new BigDecimal("0.00");
										BigDecimal childTotCommission=new BigDecimal("0.00");
										BigDecimal FinalPriceinBaseCurrency=flightOrderRow.getFinalPrice().divide(flightOrderRow.getBaseToBookingExchangeRate(),2,RoundingMode.UP);
										if(flightOrderRowCommissionForChild!=null){
											childTotCommission=flightOrderRowCommissionForChild.getCommissionAmountValue();
										}
										
										FlightOrderRowMarkup myMarkupObj=getMarkup(flightOrderRowCommissionParent.getCompanyId(),flightOrderRowCommissionParent.getFlightOrderRow().getId(),session);
										BigDecimal Mymarkup=myMarkupObj.getMarkUp();
										totMymarkupWithPaxCount=Mymarkup.multiply(new BigDecimal(myMarkupObj.getFlightOrderRow().getPassengerCount())).setScale(2,BigDecimal.ROUND_UP);
										
										
										//BigDecimal Mymarkup=getMarkup(flightOrderRowCommissionParent.getCompanyId(),flightOrderRowCommissionParent.getFlightOrderRow().getId(),session);
										BigDecimal distmarkup=new BigDecimal("0.00");
										if(childcompanyId!=0){
											FlightOrderRowMarkup  distmarkupObj=getMarkup(flightOrderRowCommissionForChild.getCompanyId(),flightOrderRowCommissionForChild.getFlightOrderRow().getId(),session);
											distmarkup=distmarkupObj.getMarkUp();
											//distmarkup=getMarkup(flightOrderRowCommissionForChild.getCompanyId(),flightOrderRowCommissionForChild.getFlightOrderRow().getId(),session);
											logger.info("flightOrderRowCommissionForChild id:-"+flightOrderRowCommissionForChild.getFlightOrderRow().getId()+"...flightOrderRowCommissionParent.getFlightOrderRow().getId() :"+flightOrderRowCommissionParent.getFlightOrderRow().getId()); 
										}

										parentTotCommission=flightOrderRowCommissionParent.getCommissionAmountValue();
										BigDecimal totalOtherMarkup=Mymarkup.add(distmarkup);									 
										BigDecimal totalMarkup=flightOrderRow.getMarkUp();
										BigDecimal agencymarkup=totalMarkup.subtract(totalOtherMarkup);					
										BigDecimal myprofit=parentTotCommission.add(totMymarkupWithPaxCount).subtract(childTotCommission);
										logger.info("Mymarkup------"+Mymarkup+"childTotCommission-----"+childTotCommission);

										BigDecimal ticketPrice=FinalPriceinBaseCurrency.subtract(totalMarkup);
										flightCommissionReport.setMarkup(totMymarkupWithPaxCount);
										flightCommissionReport.setDistributorMarkup(distmarkup.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setChildMarkup(agencymarkup.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setTicketPrice(ticketPrice.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setOrderId(flightOrderRow.getOrderId());
										flightCommissionReport.setFinalPrice(FinalPriceinBaseCurrency.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setCurCode(flightOrderRow.getBookingCurrency());
										flightCommissionReport.setFinalCommission(myprofit.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setMyProfit(myprofit.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setSharedCommission(childTotCommission.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setMyCommission(parentTotCommission.add(totMymarkupWithPaxCount).setScale(2,BigDecimal.ROUND_UP));//Totalcommission in design(commission +my markup)
										flightCommissionReport.setTDS(perVal);
										flightCommissionReport.setTdsType(type);
										//hotelCommissionReport.setAgentCommByRate(myprofit.setScale(2,BigDecimal.ROUND_UP));
										BigDecimal tdsOnCommission=null;
										if(type.equalsIgnoreCase("TDS")){
											if(companySessionObj.getCompanyRole().isAgent()){
												tdsOnCommission=parentTotCommission.divide(hundred).multiply(perVal);
												flightCommissionReport.setTdsOnCommission(tdsOnCommission.setScale(2,BigDecimal.ROUND_UP));
												flightCommissionReport.setMyProfitwithDeductTDS(myprofit.subtract(tdsOnCommission).setScale(2,BigDecimal.ROUND_UP));

											}
											if(companySessionObj.getCompanyRole().isDistributor()){
												BigDecimal parentComm=parentTotCommission.subtract(childTotCommission);
												tdsOnCommission=parentComm.divide(hundred).multiply(perVal);
												flightCommissionReport.setTdsOnCommission(tdsOnCommission.setScale(2,BigDecimal.ROUND_UP));
												flightCommissionReport.setMyProfitwithDeductTDS(myprofit.subtract(tdsOnCommission).setScale(2,BigDecimal.ROUND_UP));
											}
											flightCommissionReport.setAgentCommByRate(myprofit.subtract(tdsOnCommission).setScale(2,BigDecimal.ROUND_UP));
										}
										if(type.equalsIgnoreCase("GST")){
											flightCommissionReport.setAgentCommByRate(myprofit.setScale(2,BigDecimal.ROUND_UP));
										}
										//flightCommissionReport.setAgentCommByRate(myprofit.subtract(tdsOnCommission).setScale(2,BigDecimal.ROUND_UP));

										//flightCommissionReport.setAgentCommByRate(myprofit.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setStatus(flightOrderRow.getStatusAction());
										flightCommissionReport.setCreatedBy(flightOrderRow.getCreatedBy());
										flightCommissionReport.setBookingDate(DateConversion.getBluestarDate(flightOrderRow.getBookingDate()));
										commissionList.add(flightCommissionReport);
									}
								}
							}
						}

						if(flightOrderRow==null){
							continue;
						}


					}
				}
			}
			/////////////Super user
			else if(userSessionObj.getUserrole_id().isSuperuser() || userSessionObj.getCompanyid() == companySessionObj.getCompanyid() ){

				//logger.info("dateList.size()---"+dateList.size());
				for(FlightOrderRowCommission flightOrderRowCommissionParent:parentandChildCommissionsList){
					logger.info("flightOrderRowCommission id:-"+flightOrderRowCommissionParent.getFlightOrderRow().getApiCurrency()+"....commission :"+flightOrderRowCommissionParent.getFlightOrderRow().getAirline()); 
					List<String> childCompanyList=searchForChildCompanyID(flightOrderRowCommissionParent, companySessionObj,session);
					int childcompanyId=getChildCompanyId(childCompanyList,companySessionObj.getCompany_userid(),session);

					logger.info("childcompanyId------"+childcompanyId);
					FlightOrderRowCommission flightOrderRowCommissionForChild=null;
					if(childcompanyId!=0){
						flightOrderRowCommissionForChild=getFlightOrderRowCommission(childcompanyId,flightOrderRowCommissionParent.getFlightOrderRow().getId(),session);
						logger.info("childflightOrderRowCommission id:-"+flightOrderRowCommissionForChild.getFlightOrderRow().getId()+"...child.commission :"+flightOrderRowCommissionForChild.getCommission());  
					}
					//	FlightOrderRow flightOrderRow =getFlightOrderRowListByOrderId(flightOrderRowCommissionParent.getFlightOrderRow().getId());
					FlightOrderRow flightOrderRow =null;

					if(flightOrderRowCommissionParent.getFlightOrderRow().getStatusAction().equalsIgnoreCase("Confirmed")){
						flightOrderRow =flightOrderRowCommissionParent.getFlightOrderRow();
						if(filterObj!=null && filterObj.getFromDate()!=null && filterObj.getToDate()!=null){
							List<String> dateList=DateFilter.getDatelist(filterObj.getFromDate(), filterObj.getToDate());
							if(dateList!=null){
								for(String bookingDate:dateList){
									//logger.info("bookingDate----"+bookingDate+"flightOrderRow.getBookingDate----"+flightOrderRow.getBookingDate());
									if(bookingDate.equals(DateConversion.convertDateToStringDate(flightOrderRow.getCreatedAt()))){
										FlightCommissionReport flightCommissionReport=new FlightCommissionReport();
										BigDecimal parentTotCommission=new BigDecimal("0.00");
										BigDecimal childTotCommission=new BigDecimal("0.00");
										BigDecimal FinalPriceinBaseCurrency=flightOrderRow.getFinalPrice().divide(flightOrderRow.getBaseToBookingExchangeRate(),2,RoundingMode.UP);
										if(flightOrderRowCommissionForChild!=null){
											childTotCommission=flightOrderRowCommissionForChild.getCommissionAmountValue();
										}
										FlightOrderRowMarkup  myMarkupObj=getMarkup(flightOrderRowCommissionParent.getCompanyId(),flightOrderRowCommissionParent.getFlightOrderRow().getId(),session);
										//BigDecimal Mymarkup=getMarkup(flightOrderRowCommissionParent.getCompanyId(),flightOrderRowCommissionParent.getFlightOrderRow().getId(),session);
										BigDecimal Mymarkup=myMarkupObj.getMarkUp();
										totMymarkupWithPaxCount=Mymarkup.multiply(new BigDecimal(myMarkupObj.getFlightOrderRow().getPassengerCount())).setScale(2,BigDecimal.ROUND_UP);
										
										BigDecimal distmarkup=new BigDecimal("0.00");
										if(childcompanyId!=0){
											FlightOrderRowMarkup  distmarkupObj=getMarkup(flightOrderRowCommissionForChild.getCompanyId(),flightOrderRowCommissionForChild.getFlightOrderRow().getId(),session);
											distmarkup=distmarkupObj.getMarkUp();
											//distmarkup=getMarkup(flightOrderRowCommissionForChild.getCompanyId(),flightOrderRowCommissionForChild.getFlightOrderRow().getId(),session);
											logger.info("flightOrderRowCommissionForChild id:-"+flightOrderRowCommissionForChild.getFlightOrderRow().getId()+"...flightOrderRowCommissionParent.getFlightOrderRow().getId() :"+flightOrderRowCommissionParent.getFlightOrderRow().getId()); 
										}

										parentTotCommission=flightOrderRowCommissionParent.getCommissionAmountValue();
										BigDecimal totalOtherMarkup=Mymarkup.add(distmarkup);									 
										BigDecimal totalMarkup=flightOrderRow.getMarkUp();
										BigDecimal agencymarkup=totalMarkup.subtract(totalOtherMarkup);



										BigDecimal myprofit=parentTotCommission.add(totMymarkupWithPaxCount).subtract(childTotCommission);
										BigDecimal ticketPrice=FinalPriceinBaseCurrency.subtract(totalMarkup);
										flightCommissionReport.setMarkup(totMymarkupWithPaxCount);
										flightCommissionReport.setDistributorMarkup(distmarkup.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setChildMarkup(agencymarkup.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setTicketPrice(ticketPrice.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setOrderId(flightOrderRow.getOrderId());
										flightCommissionReport.setFinalPrice(FinalPriceinBaseCurrency.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setCurCode(flightOrderRow.getBookingCurrency());
										flightCommissionReport.setFinalCommission(myprofit.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setMyProfit(myprofit.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setSharedCommission(childTotCommission.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setMyCommission(parentTotCommission.add(totMymarkupWithPaxCount).setScale(2,BigDecimal.ROUND_UP));//Totalcommission in design(commission +my markup)
										flightCommissionReport.setAgentCommByRate(myprofit.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setStatus(flightOrderRow.getStatusAction());
										flightCommissionReport.setCreatedBy(flightOrderRow.getCreatedBy());
										flightCommissionReport.setBookingDate(DateConversion.getBluestarDate(flightOrderRow.getBookingDate()));
										commissionList.add(flightCommissionReport);
									}
								}
							}
						}
						else{
							List<String> dateList=DateFilter.getPrevious30Days(-29, 0);
							if(dateList!=null){
								for(String bookingDate:dateList){
									//logger.info("bookingDate----"+bookingDate+"flightOrderRow.getBookingDate----"+flightOrderRow.getBookingDate());
									if(bookingDate.equals(DateConversion.convertDateToStringDate(flightOrderRow.getCreatedAt()))){
										FlightCommissionReport flightCommissionReport=new FlightCommissionReport();
										BigDecimal parentTotCommission=new BigDecimal("0.00");
										BigDecimal childTotCommission=new BigDecimal("0.00");
										BigDecimal FinalPriceinBaseCurrency=flightOrderRow.getFinalPrice().divide(flightOrderRow.getBaseToBookingExchangeRate(),2,RoundingMode.UP);
										if(flightOrderRowCommissionForChild!=null){
											childTotCommission=flightOrderRowCommissionForChild.getCommissionAmountValue();
										}
										
										FlightOrderRowMarkup myMarkupObj=getMarkup(flightOrderRowCommissionParent.getCompanyId(),flightOrderRowCommissionParent.getFlightOrderRow().getId(),session);
										//BigDecimal Mymarkup=getMarkup(flightOrderRowCommissionParent.getCompanyId(),flightOrderRowCommissionParent.getFlightOrderRow().getId(),session);
										BigDecimal Mymarkup=myMarkupObj.getMarkUp();
										totMymarkupWithPaxCount=Mymarkup.multiply(new BigDecimal(myMarkupObj.getFlightOrderRow().getPassengerCount())).setScale(2,BigDecimal.ROUND_UP);
										
										BigDecimal distmarkup=new BigDecimal("0.00");
										if(childcompanyId!=0){
											FlightOrderRowMarkup distmarkupObj=getMarkup(flightOrderRowCommissionForChild.getCompanyId(),flightOrderRowCommissionForChild.getFlightOrderRow().getId(),session);
											//distmarkup=getMarkup(flightOrderRowCommissionForChild.getCompanyId(),flightOrderRowCommissionForChild.getFlightOrderRow().getId(),session);
											distmarkup=distmarkupObj.getMarkUp();
											logger.info("flightOrderRowCommissionForChild id:-"+flightOrderRowCommissionForChild.getFlightOrderRow().getId()+"...flightOrderRowCommissionParent.getFlightOrderRow().getId() :"+flightOrderRowCommissionParent.getFlightOrderRow().getId()); 
										}
										if(flightOrderRowCommissionParent.getCommissionAmountValue()!=null)	
											parentTotCommission=flightOrderRowCommissionParent.getCommissionAmountValue();
										BigDecimal totalOtherMarkup= new BigDecimal("0.00");
										if(distmarkup!=null)
											totalOtherMarkup = Mymarkup.add(distmarkup);	
										else if(Mymarkup!=null)
											totalOtherMarkup = Mymarkup;	
										BigDecimal totalMarkup= new BigDecimal(0);
										if(flightOrderRow.getMarkUp()!=null)
											totalMarkup = flightOrderRow.getMarkUp();
										BigDecimal agencymarkup=totalMarkup.subtract(totalOtherMarkup);					
										BigDecimal myprofit=parentTotCommission.add(totMymarkupWithPaxCount).subtract(childTotCommission);
										BigDecimal ticketPrice=FinalPriceinBaseCurrency.subtract(totalMarkup);
										if(totMymarkupWithPaxCount ==null)
											totMymarkupWithPaxCount = new BigDecimal(0);
										flightCommissionReport.setMarkup(totMymarkupWithPaxCount);
										if(distmarkup==null)
											distmarkup = new BigDecimal(0);
										flightCommissionReport.setDistributorMarkup(distmarkup.setScale(2,BigDecimal.ROUND_UP));
										if(agencymarkup==null)
											agencymarkup = new BigDecimal(0);
										flightCommissionReport.setChildMarkup(agencymarkup.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setTicketPrice(ticketPrice.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setOrderId(flightOrderRow.getOrderId());
										flightCommissionReport.setFinalPrice(FinalPriceinBaseCurrency.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setCurCode(flightOrderRow.getBookingCurrency());
										flightCommissionReport.setFinalCommission(myprofit.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setMyProfit(myprofit.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setSharedCommission(childTotCommission.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setMyCommission(parentTotCommission.add(totMymarkupWithPaxCount).setScale(2,BigDecimal.ROUND_UP));//Totalcommission in design(commission +my markup)
										flightCommissionReport.setAgentCommByRate(myprofit.setScale(2,BigDecimal.ROUND_UP));
										flightCommissionReport.setStatus(flightOrderRow.getStatusAction());
										flightCommissionReport.setCreatedBy(flightOrderRow.getCreatedBy());
										flightCommissionReport.setBookingDate(DateConversion.getBluestarDate(flightOrderRow.getBookingDate()));
										commissionList.add(flightCommissionReport);
									}
								}
							}
						}
					}
					if(flightOrderRow==null){
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
			if(session != null && session.isOpen())
				session.close(); 
		}

		return commissionList;
	}
	/*	method for find the parent id for child*/
	public List<String>  searchForChildCompanyID(FlightOrderRowCommission order_row_id,Company sessionCompany,Session session){
		String childQuery=null;
		List<String> childlist=new ArrayList<String>();
		List<FlightOrderRowCommission> foclist=null;
		 session=HibernateUtil.getSessionFactory().openSession();
		 childQuery="from FlightOrderRowCommission forc where forc.CompanyId!=:compid and forc.flightOrderRow.id=:order_row_id";
		logger.info("------------------childQuery------------------------"+childQuery);
		 Query query = session.createQuery(childQuery);
		 query.setParameter("compid", String.valueOf(sessionCompany.getCompanyid()));
		 query.setParameter("order_row_id",order_row_id.getFlightOrderRow().getId());
		foclist=query.list();
		logger.info("foclist  ..."+foclist.size());
		if(foclist!=null && foclist.size()>0){
		for(FlightOrderRowCommission forc:foclist){
			childlist.add(forc.getCompanyId());
		}
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
			List results = query.list();
			if(results!=null && results.size()>0){
				childCompanyID=Integer.parseInt(companyId);
				break;
			}
		}
		return childCompanyID;
	}
	public  FlightOrderRowCommission getFlightOrderRowCommission(int childcompanyId,long orderId,Session session){
		FlightOrderRowCommission  flightOrderRowIdschild=null;
		String sql = "from FlightOrderRowCommission forc where forc.flightOrderRow.id=:rowId and forc.CompanyId=:companyId";
		logger.info("----------FlightOrderRowids for child----sql-----------------"+sql);
		 Query query = session.createQuery(sql);
		 query.setParameter("rowId",orderId);
		 query.setParameter("companyId", String.valueOf(childcompanyId));
		 flightOrderRowIdschild = (FlightOrderRowCommission) query.list().get(0);
		return flightOrderRowIdschild;

	}

	public FlightOrderRowMarkup getMarkup(String CompanyId,long Id,Session session){
		//BigDecimal markup=new BigDecimal("0.00");
		FlightOrderRowMarkup  flightOrderRowMarkup=null;
		String sql = "from FlightOrderRowMarkup frm where frm.flightOrderRow.id=:order_row_id and frm.CompanyId=:company_id";
		logger.info("----------FlightOrderRowids for markup----sql-----------------"+sql);
		  Query query = session.createQuery(sql);
		  query.setParameter("order_row_id",Id);
			 query.setParameter("company_id",CompanyId);
		if(query.list()!=null && query.list().size()>0){
			flightOrderRowMarkup = (FlightOrderRowMarkup) query.list().get(0) ;
			//markup=flightOrderRowMarkup.getMarkUp() ;
		}
		return flightOrderRowMarkup;
	}
 }

