/**
 * 
 */
package com.admin.miscellaneous.dao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightReportPage;
import com.isl.admin.page.Page;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.common.model.FlightAndHotelBookApiResponse;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

/**
 * @author Manish samrat
 *
 */
public class MiscellaneousOrderReportDao {

	Logger logger=Logger.getLogger(MiscellaneousOrderReportDao.class);

	public  FlightReportPage getMiscellaneousReports(FlightReportPage flightReportPage,String showType){
		List<ReportData>  reportData_list=new ArrayList<ReportData>();
		int availablePages = 0;
		int availableItems = 0;
		String companyId=null;
		Session session = null;
		FlightOrderDao flightOrderDao = new FlightOrderDao();
		try{
			//2017-03-15
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(MiscellaneousOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Conjunction  conjunctionFlightOrderCustomer= Restrictions.conjunction();
			// To get total row count.
			if(flightReportPage!=null && flightReportPage.isFilterEnabled())
			{

				FlightReportFilter flightReportFilter = flightReportPage.getFlightReportFilter();
				List<String> companyIdList  = new ArrayList<String>();
				companyIdList = flightOrderDao.getCompanyIdList(flightReportFilter.getLoginCompany(), flightReportFilter.getReportType(), flightReportFilter.getCompanyName());
				logger.info("companyIdList--------------"+companyIdList.size());
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setItems(new ArrayList<ReportData>());
					return flightReportPage;
				}
				List<Integer> companyIdIntegerList=new ArrayList<Integer>();
				for(String s : companyIdList) 
					companyIdIntegerList.add(Integer.valueOf(s));
				reportConjunction.add(Restrictions.in("companyId",companyIdIntegerList));	



				if(flightReportFilter.getInvoiceNo()!= null && !flightReportFilter.getInvoiceNo().trim().equals(""))
					reportConjunction.add(Restrictions.like("invoiceNo", flightReportFilter.getInvoiceNo(), MatchMode.ANYWHERE));

				if(flightReportFilter.getFirstName()!= null && !flightReportFilter.getFirstName().trim().equals(""))
					conjunctionFlightOrderCustomer.add(Restrictions.like("firstName", flightReportFilter.getFirstName(), MatchMode.ANYWHERE));
				if(flightReportFilter.getLastName()!= null && !flightReportFilter.getLastName().trim().equals(""))
					conjunctionFlightOrderCustomer.add(Restrictions.like("lastName", flightReportFilter.getLastName(), MatchMode.ANYWHERE));
				if(flightReportFilter.getSupplierName()!= null && !flightReportFilter.getSupplierName().trim().equals("") && !flightReportFilter.getSupplierName().trim().equals("ALL"))
					reportConjunction.add(Restrictions.like("supplierName", flightReportFilter.getSupplierName(), MatchMode.ANYWHERE));
				if(flightReportFilter.getMobile()!= null && !flightReportFilter.getMobile().trim().equals(""))
					conjunctionFlightOrderCustomer.add(Restrictions.like("mobile", flightReportFilter.getMobile(), MatchMode.ANYWHERE));
				if(flightReportFilter.getLocation()!= null && !flightReportFilter.getLocation().trim().equals(""))
					conjunctionFlightOrderCustomer.add(Restrictions.like("city", flightReportFilter.getLocation(), MatchMode.ANYWHERE));
				if(flightReportFilter.getConfirmationNo() != null && !flightReportFilter.getConfirmationNo().trim().equals(""))
				{
					reportConjunction.add(Restrictions.like("confirmationNumber", flightReportFilter.getConfirmationNo(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getConfirmationNo());
				}
				if(flightReportFilter.getOrderId()!= null && !flightReportFilter.getOrderId().trim().equals(""))
				{
					reportConjunction.add(Restrictions.like("orderId", flightReportFilter.getOrderId(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getOrderId());
				}	
				/*if(flightReportFilter.getEmail() != null && !flightReportFilter.getEmail().equals(""))
				{		
					conjunctionFlightOrderCustomer.add(Restrictions.eq("email", flightReportFilter.getEmail()));
				}*/

				if(flightReportFilter.getCompanyName() != null && !flightReportFilter.getCompanyName().trim().equals(""))
				{
					reportConjunction.add(Restrictions.like("createdBy", flightReportFilter.getCompanyName(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getCompanyName());
				}	
				if(flightReportFilter.getBookingStatus() != null && !flightReportFilter.getBookingStatus().equalsIgnoreCase("ALL"))
				{
					Disjunction statusActionDisjunction = Restrictions.disjunction();
					statusActionDisjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus().toLowerCase()));
					statusActionDisjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus().toUpperCase()));
					statusActionDisjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus()));
					reportConjunction.add(statusActionDisjunction);
					//reportConjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus()));
					logger.info("##########booking status added----"+flightReportFilter.getBookingStatus());
				}
				if(flightReportFilter.getPaymentStatus() != null && !flightReportFilter.getPaymentStatus().equalsIgnoreCase("ALL"))
				{

					Disjunction statusActionDisjunction = Restrictions.disjunction();
					statusActionDisjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus().toLowerCase()));
					statusActionDisjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus().toUpperCase()));
					statusActionDisjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus()));
					reportConjunction.add(statusActionDisjunction); 
					//reportConjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus()));
					logger.info("########## Payment Status added----"+flightReportFilter.getPaymentStatus());
				}


				if(flightReportFilter.getUserId()>0)
					reportConjunction.add(Restrictions.like("userId",  String.valueOf(flightReportFilter.getUserId())));

				if(flightReportFilter.getCompanyId()>0)
					reportConjunction.add(Restrictions.like("companyId", String.valueOf(flightReportFilter.getCompanyId())));


				if(flightReportFilter.getDateFilterBooking().getDtStart() != null && flightReportFilter.getDateFilterBooking().getDtEnd() != null && !flightReportFilter.getDateFilterBooking().getDtStart().equals("") && !flightReportFilter.getDateFilterBooking().getDtEnd().equals("") )
				{
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterBooking().getDtStart());
						reportConjunction.add(Restrictions.ge("bookingDate", date));
					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterBooking().getDtEnd());
						reportConjunction.add(Restrictions.le("bookingDate", date));
					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());

					}

				}

				if(flightReportFilter.getDateFilterArrival().getDtStart()!= null && flightReportFilter.getDateFilterArrival().getDtEnd() != null && !flightReportFilter.getDateFilterArrival().getDtEnd().equals("") && !flightReportFilter.getDateFilterArrival().getDtStart().equals(""))
				{

					//2016-06-28
					//SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");


					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterArrival().getDtStart());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);

						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));

						reportConjunction.add(Restrictions.ge("travelDate", date));
						logger.info("##########getDateFilterCheckIn date min added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########getDateFilterCheckIn date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterArrival().getDtEnd());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);

						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));

						reportConjunction.add(Restrictions.lt("travelDate", date));
						logger.info("##########getDateFilterCheckIn date max added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########getDateFilterCheckIn date max format exception---"+ex.getMessage());

					}
				}

				if(flightReportFilter.getDateFilterInvoice().getDtStart()!= null && flightReportFilter.getDateFilterInvoice().getDtEnd() != null && !flightReportFilter.getDateFilterInvoice().getDtEnd().equals("") && !flightReportFilter.getDateFilterInvoice().getDtStart().equals(""))
				{
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterInvoice().getDtStart());
						reportConjunction.add(Restrictions.ge("bookingDate", date));
					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterInvoice().getDtEnd());
						reportConjunction.add(Restrictions.le("bookingDate", date));
					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());

					}
				}


				try{
					if(showType!=null && showType.equalsIgnoreCase("insuranceconfirm")){
						///reportConjunction.add(Restrictions.in("companyId",companyIdList));
						reportConjunction.add(Restrictions.eq("statusAction","Confirmed"));
					}
					else if(showType!=null && showType.equalsIgnoreCase("insurancepaymentfailed")){
						//reportConjunction.add(Restrictions.in("companyId",companyIdList));
						reportConjunction.add(Restrictions.eq("paymentStatus","Failed"));
					}	
					else if(showType!=null && showType.equalsIgnoreCase("insurancepayment")){
						//reportConjunction.add(Restrictions.in("companyId",companyIdList));
						reportConjunction.add(Restrictions.eq("paymentStatus","Success"));
					}
					else if(showType!=null && showType.equalsIgnoreCase("today")){
						Calendar cal = Calendar.getInstance();
						String formattedDate = targetFormat.format(cal.getTime()); 
						Date  today = targetFormat.parse(formattedDate) ;
						Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.ge("createdAt", today));
						reportConjunction.add(Restrictions.lt("createdAt", tomorrow));
						logger.info("##########today added to conjuction---"+today);
					}
					else if(showType!=null && showType.equalsIgnoreCase("week")){							

						Calendar cal = Calendar.getInstance();
						String formattedDate = targetFormat.format(cal.getTime()); 
						Date  today = targetFormat.parse(formattedDate) ;
						Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));

						// get start of this week 
						cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
						//logger.info("Start of this week:       " + cal.getTime());
						String formattedfirstdayoftheweek = targetFormat.format(cal.getTime()); 
						Date  firstdayoftheweek = targetFormat.parse(formattedfirstdayoftheweek) ;
						//logger.info("firstdayoftheweek:       " + firstdayoftheweek);

						reportConjunction.add(Restrictions.ge("createdAt", firstdayoftheweek));
						reportConjunction.add(Restrictions.lt("createdAt", tomorrow));
						logger.info("##########week added to conjuction---"+firstdayoftheweek  +  today);
					}
					else if(showType!=null && showType.equalsIgnoreCase("month")){						

						Calendar cal = Calendar.getInstance();
						String formattedDate = targetFormat.format(cal.getTime()); 
						Date  today = targetFormat.parse(formattedDate) ;
						Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));

						cal.set(Calendar.DAY_OF_MONTH, 1);
						String formattedmonth = targetFormat.format(cal.getTime()); 
						Date  firstdayofthemonth = targetFormat.parse(formattedmonth) ;							

						reportConjunction.add(Restrictions.ge("createdAt", firstdayofthemonth));
						reportConjunction.add(Restrictions.lt("createdAt", tomorrow));
						logger.info("##########month added to conjuction---"+firstdayofthemonth  +  today);
					}

					else if(showType!=null && showType.equalsIgnoreCase("flightconfirm")){	
						Calendar cal = Calendar.getInstance();
						String formattedDate = targetFormat.format(cal.getTime()); 
						Date  today = targetFormat.parse(formattedDate) ;
						Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.ge("createdAt", today));
						reportConjunction.add(Restrictions.lt("createdAt", tomorrow));
						reportConjunction.add(Restrictions.eq("statusAction", "Confirmed"));
					}
					else if(showType!=null && showType.equalsIgnoreCase("flightpayment")){	
						Calendar cal = Calendar.getInstance();
						String formattedDate = targetFormat.format(cal.getTime()); 
						Date  today = targetFormat.parse(formattedDate) ;
						Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.ge("createdAt", today));
						reportConjunction.add(Restrictions.lt("createdAt", tomorrow));
						reportConjunction.add(Restrictions.eq("paymentStatus", "Success"));
					}
					else if(showType!=null && showType.equalsIgnoreCase("flightpaymentfailed")){	
						Calendar cal = Calendar.getInstance();
						String formattedDate = targetFormat.format(cal.getTime()); 
						Date  today = targetFormat.parse(formattedDate) ;
						Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.ge("createdAt", today));
						reportConjunction.add(Restrictions.lt("createdAt", tomorrow));
						reportConjunction.add(Restrictions.ne("paymentStatus", "Success"));
					}


				}catch(Exception ex)
				{
					logger.info("##########today format exception---"+ex.getMessage());

				} 

				criteria.add(reportConjunction);
				criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);	
				criteria.addOrder(Order.desc("id"));

			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);

			List<MiscellaneousOrderRow> list =null;
			if(rowCount>0)
			{
				if(flightReportPage.getMaxItems()==Page.ALL_ITEMS){
					logger.info(":::: retriving all items for excel export------");

					criteria = session.createCriteria(MiscellaneousOrderRow.class);
					criteria.add(reportConjunction);
					criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					logger.info(":::: retriving all items for excel export-----list-"+list);
					logger.info("rowCountForExcel list.size ------"+((list != null)?0:list.size()));	
					flightReportPage.setAvailableItems(list.size());
					flightReportPage.setAvailablePages(1);

				}
				else{
					if(flightReportPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % flightReportPage.getMaxItems() == 0)?(availableItems / flightReportPage.getMaxItems()):((availableItems / flightReportPage.getMaxItems()) + 1);
						flightReportPage.setAvailableItems(availableItems);
						flightReportPage.setAvailablePages(availablePages);
					} 
					//Retrive report with pagination .......

					int pageIndexDb = (flightReportPage.getCurrentPageIndex() > 1)?flightReportPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * flightReportPage.getMaxItems();

					//int itemIndex = flightReportPage.getCurrentPageIndex() * flightReportPage.getMaxItems();

					logger.info("setFirstResult-------"+itemIndex);

					if(itemIndex <= rowCount)
					{
						logger.info("setFirstResult-------"+itemIndex);
						logger.info("MaxResults-------"+flightReportPage.getMaxItems());

						criteria = session.createCriteria(MiscellaneousOrderRow.class);
						criteria.add(reportConjunction);
						criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);	
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(flightReportPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
						logger.info("Reports size------"+list.size());	

					}

				}

				//logger.info("---------logged in company id-----------------------------"+companySessionObj.getCompanyid());
				if(list!=null && list.size()>0)
				{
					for (MiscellaneousOrderRow orderRow :list)
					{
						ReportData reportData=new ReportData();
						//reportData.setPnr(insuranceOrderRow.getPnr());
						reportData.setFirstName(orderRow.getOrderCustomer().getFirstName());
						reportData.setLastName(orderRow.getOrderCustomer().getLastName());
						reportData.setTitle(orderRow.getOrderCustomer().getTitle());
						reportData.setCreatedAt(orderRow.getCreatedAt());
						//						reportData.setInsuranceBookingDate(orderRow.getInsuranceBookingDate());
						reportData.setMarkUp(orderRow.getMarkUpamount().setScale(2, BigDecimal.ROUND_UP));
						reportData.setSupplierName(orderRow.getSupplierName());


						//							reportData.setVehicleCompanyName(orderRow.getInsuranceCompanyName());
						reportData.setFinalPrice(orderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
						reportData.setEmail(orderRow.getOrderCustomer().getEmail());
						reportData.setStatus(orderRow.getStatusAction());
						reportData.setPaymentStatus(orderRow.getPaymentStatus());
						reportData.setOrderId(orderRow.getOrderId());
						reportData.setCurCode(orderRow.getBookingCurrency());
						reportData.setBookingDate(orderRow.getBookingDate()!=null?DateConversion.convertDateToStringDateddMMyyyy(orderRow.getBookingDate()):"-");
						reportData.setId(orderRow.getId());
						reportData.setCompanyId(String.valueOf(orderRow.getCompanyId()));
						reportData.setInvoiceDate(DateConversion.convertDateToStringToDate(orderRow.getCreatedAt()));
						//						reportData.setBasePrice(orderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
						reportData.setConfirmationNumber(orderRow.getConfirmationNumber());
						//						reportData.setConvenienceFees(orderRow.getConvenienceFee().setScale(2, BigDecimal.ROUND_UP));
						reportData.setInvoiceNo(orderRow.getInvoiceNo());
						//						reportData.setManagementFee(orderRow.getManagementFee().setScale(2, BigDecimal.ROUND_UP));
						//						reportData.setOtherTaxes(orderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP));
						//						reportData.setProcessingFee(orderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
						//						reportData.setServiceTax(orderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP));
						//						reportData.setTax(orderRow.getTaxes().setScale(2, BigDecimal.ROUND_UP));
						//							reportData.setTollOrParkingCharges(orderRow.getTollOrParkingCharges());
						//						reportData.setTravelDate1(orderRow.getTravelDate());
						//						reportData.setTransactionKey(orderRow.getTransactionKey());
						reportData.setOrderCustomerId(orderRow.getOrderCustomer().getId());
						//						reportData.setSupplierPrice(orderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP));
						reportData.setUpdatedBy(orderRow.getUpdatedBy());
						reportData.setInvoiceAmount(orderRow.getTotalAmount().add(calculateTotalserviceTax(orderRow)).setScale(2, BigDecimal.ROUND_UP));


						if(orderRow.getCreatedBy()!=null){
							reportData.setCreatedBy(orderRow.getCreatedBy().replace("+", " "));	
						}
						else{
							reportData.setCreatedBy(orderRow.getCreatedBy());	
						}

						reportData.setAgencyUsername(orderRow.getCreatedBy());
						//						reportData.setDescription(orderRow.getRemarks());
						reportData.setUserId(String.valueOf(orderRow.getUserId()));
						User user=new User();//getSalesPersonName(flightOrderRow.getUserId());

						if(user!=null && user.getId()>0) 
							reportData.setSalesPersonName(user.getUsername());
						else 
							reportData.setSalesPersonName("-");

						FlightAndHotelBookApiResponse flightAndHotelBookApiResponse = new FlightAndHotelBookApiResponse();   // getApiStatusMessage(flightOrderRow.getId());
						if(flightAndHotelBookApiResponse!=null)
							reportData.setApiResponseMessage(flightAndHotelBookApiResponse.getApiStatusMessage());

						reportData_list.add(reportData);
					}					
					flightReportPage.setItems(reportData_list);

				}
				else
				{
					//current page is having empty items..
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setItems(new ArrayList<ReportData>());
				}

			}
			else
			{
				flightReportPage.setAvailableItems(0);
				flightReportPage.setAvailablePages(0);
				flightReportPage.setItems(new ArrayList<ReportData>());
			}


		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		//logger.info("--------reportData_list size-------"+reportData_list.size());
		return flightReportPage;
	}

	public  FlightReportPage getMiscellaneousOrders(FlightReportPage flightReportPage){
		List<ReportData>  reportData_list=new ArrayList<ReportData>();
		int availablePages = 0;
		int availableItems = 0;
		String companyId=null;
		Session session = null;
		FlightOrderDao flightOrderDao = new FlightOrderDao();
		try{
			//2017-03-15
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(MiscellaneousOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Conjunction  conjunctionFlightOrderCustomer= Restrictions.conjunction();
			// To get total row count.
			if(flightReportPage!=null && flightReportPage.isFilterEnabled())
			{

				FlightReportFilter flightReportFilter = flightReportPage.getFlightReportFilter();
				List<String> companyIdList  = new ArrayList<String>();
				companyIdList = flightOrderDao.getCompanyIdList(flightReportFilter.getLoginCompany(), flightReportFilter.getReportType(), flightReportFilter.getCompanyName());
				logger.info("companyIdList--------------"+companyIdList.size());
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setItems(new ArrayList<ReportData>());
					return flightReportPage;
				}
				List<Integer> companyIdIntegerList=new ArrayList<Integer>();
				for(String s : companyIdList) 
					companyIdIntegerList.add(Integer.valueOf(s));
				reportConjunction.add(Restrictions.in("companyId",companyIdIntegerList));	


				if(flightReportFilter.getOrderId()!= null && !flightReportFilter.getOrderId().trim().equals(""))
				{ 
					reportConjunction.add(Restrictions.like("orderId", flightReportFilter.getOrderId(), MatchMode.ANYWHERE));
					logger.info("##########OrderId added----"+flightReportFilter.getOrderId());
				}
				if(flightReportFilter.getInvoiceNo()!= null && !flightReportFilter.getInvoiceNo().trim().equals(""))
				{ 
					reportConjunction.add(Restrictions.like("invoiceNo", flightReportFilter.getInvoiceNo(), MatchMode.ANYWHERE));
					logger.info("##########OrderId added----"+flightReportFilter.getInvoiceNo());
				}

				if(flightReportFilter.getPaxName()!= null && !flightReportFilter.getPaxName().trim().equals(""))
				{ 
					reportConjunction.add(Restrictions.like("empNmae", flightReportFilter.getPaxName(), MatchMode.ANYWHERE));
					logger.info("##########PaxName added----"+flightReportFilter.getPaxName());
				}
				if(flightReportFilter.getConfirmationNo() != null && !flightReportFilter.getConfirmationNo().trim().equals(""))
				{
					reportConjunction.add(Restrictions.like("confirmationNumber", flightReportFilter.getConfirmationNo(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getConfirmationNo());
				}	
				if(flightReportFilter.getOrderId()!= null && !flightReportFilter.getOrderId().trim().equals(""))
				{
					reportConjunction.add(Restrictions.like("orderId", flightReportFilter.getOrderId(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getOrderId());
				}	
				if(flightReportFilter.getEmail() != null && !flightReportFilter.getEmail().equals(""))
				{		
					conjunctionFlightOrderCustomer.add(Restrictions.eq("email", flightReportFilter.getEmail()));
				}

				if(flightReportFilter.getInvoiceNo()!= null && !flightReportFilter.getInvoiceNo().trim().equals(""))
					reportConjunction.add(Restrictions.like("invoiceNo", flightReportFilter.getInvoiceNo(), MatchMode.ANYWHERE));

				if(flightReportFilter.getFirstName()!= null && !flightReportFilter.getFirstName().trim().equals(""))
					conjunctionFlightOrderCustomer.add(Restrictions.like("firstName", flightReportFilter.getFirstName(), MatchMode.ANYWHERE));
				if(flightReportFilter.getLastName()!= null && !flightReportFilter.getLastName().trim().equals(""))
					conjunctionFlightOrderCustomer.add(Restrictions.like("lastName", flightReportFilter.getLastName(), MatchMode.ANYWHERE));
				if(flightReportFilter.getSupplierName()!= null && !flightReportFilter.getSupplierName().trim().equals("") && !flightReportFilter.getSupplierName().trim().equals("ALL"))
					reportConjunction.add(Restrictions.like("supplierName", flightReportFilter.getSupplierName(), MatchMode.ANYWHERE));
				if(flightReportFilter.getMobile()!= null && !flightReportFilter.getMobile().trim().equals(""))
					conjunctionFlightOrderCustomer.add(Restrictions.like("mobile", flightReportFilter.getMobile(), MatchMode.ANYWHERE));
				if(flightReportFilter.getLocation()!= null && !flightReportFilter.getLocation().trim().equals(""))
					conjunctionFlightOrderCustomer.add(Restrictions.like("city", flightReportFilter.getLocation(), MatchMode.ANYWHERE));

				if(flightReportFilter.getCompanyName() != null && !flightReportFilter.getCompanyName().trim().equals(""))

				{
					reportConjunction.add(Restrictions.like("createdBy", flightReportFilter.getCompanyName(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getCompanyName());
				}	
				if(flightReportFilter.getBookingStatus() != null && !flightReportFilter.getBookingStatus().equalsIgnoreCase("ALL"))
				{
					Disjunction statusActionDisjunction = Restrictions.disjunction();
					statusActionDisjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus().toLowerCase()));
					statusActionDisjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus().toUpperCase()));
					statusActionDisjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus()));
					reportConjunction.add(statusActionDisjunction);
					//reportConjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus()));
					logger.info("##########booking status added----"+flightReportFilter.getBookingStatus());
				}
				if(flightReportFilter.getPaymentStatus() != null && !flightReportFilter.getPaymentStatus().equalsIgnoreCase("ALL"))
				{

					Disjunction statusActionDisjunction = Restrictions.disjunction();
					statusActionDisjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus().toLowerCase()));
					statusActionDisjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus().toUpperCase()));
					statusActionDisjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus()));
					reportConjunction.add(statusActionDisjunction); 
					//reportConjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus()));
					logger.info("########## Payment Status added----"+flightReportFilter.getPaymentStatus());
				}

				if(flightReportFilter.getDateFilterBooking().getDtStart() != null && flightReportFilter.getDateFilterBooking().getDtEnd() != null && !flightReportFilter.getDateFilterBooking().getDtStart().equals("") && !flightReportFilter.getDateFilterBooking().getDtEnd().equals("") )
				{
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterBooking().getDtStart());
						reportConjunction.add(Restrictions.ge("bookingDate", date));
					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterBooking().getDtEnd());
						reportConjunction.add(Restrictions.le("bookingDate", date));
					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());

					}

				}
				if(flightReportFilter.getDateFilterInvoice().getDtStart()!= null && flightReportFilter.getDateFilterInvoice().getDtEnd() != null && !flightReportFilter.getDateFilterInvoice().getDtEnd().equals("") && !flightReportFilter.getDateFilterInvoice().getDtStart().equals(""))
				{
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterInvoice().getDtStart());
						reportConjunction.add(Restrictions.ge("bookingDate", date));
					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterInvoice().getDtEnd());
						reportConjunction.add(Restrictions.le("bookingDate", date));
					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());

					}
				}


				criteria.add(reportConjunction);
				criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);	
				criteria.addOrder(Order.desc("id"));

			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);

			List<MiscellaneousOrderRow> list =null;
			if(rowCount>0)
			{
				if(flightReportPage.getMaxItems()==Page.ALL_ITEMS){
					logger.info(":::: retriving all items for excel export------");

					criteria = session.createCriteria(MiscellaneousOrderRow.class);
					criteria.add(reportConjunction);
					criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					logger.info(":::: retriving all items for excel export-----list-"+list);
					logger.info("rowCountForExcel list.size ------"+((list != null)?0:list.size()));	
					flightReportPage.setAvailableItems(list.size());
					flightReportPage.setAvailablePages(1);

				}
				else{
					if(flightReportPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % flightReportPage.getMaxItems() == 0)?(availableItems / flightReportPage.getMaxItems()):((availableItems / flightReportPage.getMaxItems()) + 1);
						flightReportPage.setAvailableItems(availableItems);
						flightReportPage.setAvailablePages(availablePages);
					} 
					//Retrive report with pagination .......

					int pageIndexDb = (flightReportPage.getCurrentPageIndex() > 1)?flightReportPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * flightReportPage.getMaxItems();

					//int itemIndex = flightReportPage.getCurrentPageIndex() * flightReportPage.getMaxItems();

					logger.info("setFirstResult-------"+itemIndex);

					if(itemIndex <= rowCount)
					{
						logger.info("setFirstResult-------"+itemIndex);
						logger.info("MaxResults-------"+flightReportPage.getMaxItems());

						criteria = session.createCriteria(MiscellaneousOrderRow.class);
						criteria.add(reportConjunction);
						criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);	
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(flightReportPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
						logger.info("Reports size------"+list.size());	

					}

				}

				//logger.info("---------logged in company id-----------------------------"+companySessionObj.getCompanyid());
				if(list!=null && list.size()>0)
				{
					for (MiscellaneousOrderRow OrderRow :list)
					{
						ReportData reportData=new ReportData();
						reportData=buildReportData(OrderRow, reportData);
						reportData_list.add(reportData);
					}					
					flightReportPage.setItems(reportData_list);

				}
				else
				{
					//current page is having empty items..
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setItems(new ArrayList<ReportData>());
				}

			}
			else
			{
				flightReportPage.setAvailableItems(0);
				flightReportPage.setAvailablePages(0);
				flightReportPage.setItems(new ArrayList<ReportData>());
			}


		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		//logger.info("--------reportData_list size-------"+reportData_list.size());
		return flightReportPage;
	}
	public ReportData buildReportData(MiscellaneousOrderRow miscellaneousOrderRow,ReportData reportData) {//reportData.setPnr(insuranceOrderRow.getPnr());
		reportData.setFirstName(miscellaneousOrderRow.getOrderCustomer().getFirstName());
		reportData.setLastName(miscellaneousOrderRow.getOrderCustomer().getLastName());
		reportData.setTitle(miscellaneousOrderRow.getOrderCustomer().getTitle());
		//		reportData.setVehicleCompanyName(miscellaneousOrderRow.getInsuranceCompanyName());
		reportData.setInvoiceAmount(miscellaneousOrderRow.getTotalAmount().add(calculateTotalserviceTax(miscellaneousOrderRow)).setScale(2, BigDecimal.ROUND_UP));

		reportData.setFinalPrice(miscellaneousOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
		reportData.setEmail(miscellaneousOrderRow.getOrderCustomer().getEmail());
		reportData.setStatus(miscellaneousOrderRow.getStatusAction());
		reportData.setPaymentStatus(miscellaneousOrderRow.getPaymentStatus());
		reportData.setOrderId(miscellaneousOrderRow.getOrderId());
		reportData.setCurCode(miscellaneousOrderRow.getBookingCurrency());
		reportData.setBookingDate(miscellaneousOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringDateddMMyyyy(miscellaneousOrderRow.getBookingDate()):"-");
		reportData.setCreatedAt(miscellaneousOrderRow.getCreatedAt());
		reportData.setSupplierName(miscellaneousOrderRow.getSupplierName());

		reportData.setId(miscellaneousOrderRow.getId());
		//		reportData.setCompanyId(miscellaneousOrderRow.getCompanyId());
		/*if(miscellaneousOrderRow.getProcessingFees()!=null)
			reportData.setConvenienceFees(miscellaneousOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
		else
			reportData.setConvenienceFees(new BigDecimal(0));*/
		//		reportData.setBasePrice(miscellaneousOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
		reportData.setConfirmationNumber(miscellaneousOrderRow.getConfirmationNumber());
		//		reportData.setConvenienceFees(miscellaneousOrderRow.getConvenienceFee().setScale(2, BigDecimal.ROUND_UP));

		reportData.setInvoiceNo(miscellaneousOrderRow.getInvoiceNo());
		reportData.setManagementFee(miscellaneousOrderRow.getManagementFee().setScale(2, BigDecimal.ROUND_UP));
		//		reportData.setOtherTaxes(miscellaneousOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP));
		//		reportData.setProcessingFee(miscellaneousOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
		//		reportData.setServiceTax(miscellaneousOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP));
		//		reportData.setTax(miscellaneousOrderRow.getTaxes().setScale(2, BigDecimal.ROUND_UP));
		//		reportData.setTravelDate1(miscellaneousOrderRow.getTravelDate());
		//		reportData.setTransactionKey(miscellaneousOrderRow.getTransactionKey());
		reportData.setOrderCustomerId(miscellaneousOrderRow.getOrderCustomer().getId());
		//		reportData.setSupplierPrice(miscellaneousOrderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP));
		reportData.setUpdatedBy(miscellaneousOrderRow.getUpdatedBy());

		if(miscellaneousOrderRow.getCreatedBy()!=null){
			reportData.setCreatedBy(miscellaneousOrderRow.getCreatedBy().replace("+", " "));	
		}
		else{
			reportData.setCreatedBy(miscellaneousOrderRow.getCreatedBy());	
		}
		/*if(miscellaneousOrderRow.getProcessingFees()!=null)
			reportData.setConvenienceFees(miscellaneousOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
		else
			reportData.setConvenienceFees(new BigDecimal(0));*/

		reportData.setAgencyUsername(miscellaneousOrderRow.getCreatedBy());
		//		reportData.setDescription(miscellaneousOrderRow.getRemarks());
		reportData.setUserId(String.valueOf(miscellaneousOrderRow.getUserId()));
		reportData.setStatus(miscellaneousOrderRow.getStatusAction());
		reportData.setPaymentStatus(miscellaneousOrderRow.getPaymentStatus());
		reportData.setOrderRequested(miscellaneousOrderRow.isOrderRequested());
		reportData.setBookingMode(miscellaneousOrderRow.getBookingMode());
		reportData.setCreditNoteIssued(miscellaneousOrderRow.isCreditNoteIssued());
		reportData.setOrderUpdated(miscellaneousOrderRow.isOrderUpdated());
		User user= new UserDAO().getSalesPersonName(String.valueOf(miscellaneousOrderRow.getUserId()));

		if(user!=null) 
			reportData.setSalesPersonName(user.getUsername());
		else 
			reportData.setSalesPersonName("-");
		return reportData;
	}
	public static BigDecimal  calculateTotalserviceTax(MiscellaneousOrderRow  insOrderRow){
		BigDecimal convenienceFeePerRoom = new BigDecimal("0");
		BigDecimal managementFeePerRoom = new BigDecimal("0");
		/*if(insOrderRow.getMiscellaneousOrderRowServiceTax() !=null && insOrderRow.getMiscellaneousOrderRowServiceTax().getManagementFee()!=null)
			managementFeePerRoom= insOrderRow.getMiscellaneousOrderRowServiceTax().getManagementFee();
		if(insOrderRow.getMiscellaneousOrderRowServiceTax() !=null && insOrderRow.getMiscellaneousOrderRowServiceTax().getConvenienceFee()!=null)
			convenienceFeePerRoom= insOrderRow.getMiscellaneousOrderRowServiceTax().getConvenienceFee();*/
		if(insOrderRow.getMiscellaneousOrderRowGstTax() !=null && insOrderRow.getMiscellaneousOrderRowGstTax().getManagementFee()!=null)
			managementFeePerRoom= insOrderRow.getMiscellaneousOrderRowGstTax().getManagementFee();
		if(insOrderRow.getMiscellaneousOrderRowGstTax() !=null && insOrderRow.getMiscellaneousOrderRowGstTax().getConvenienceFee()!=null)
			convenienceFeePerRoom= insOrderRow.getMiscellaneousOrderRowGstTax().getConvenienceFee();

		BigDecimal  serviceTax= new BigDecimal(0);
		BigDecimal  gstTax=insOrderRow.getTotalGstTax()!=null?insOrderRow.getTotalGstTax().setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);

		BigDecimal  totalAmount=serviceTax.add(managementFeePerRoom).add(convenienceFeePerRoom).add(gstTax);
		return totalAmount.setScale(2,BigDecimal.ROUND_UP);
	}
}
