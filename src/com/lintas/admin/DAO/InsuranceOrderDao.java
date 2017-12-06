/**
 * 
 */
package com.lintas.admin.DAO;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightReportPage;
import com.isl.admin.page.Page;
import com.lintas.action.CreditNote.modal.InsuranceCreditNote;
import com.lintas.admin.common.model.FlightAndHotelBookApiResponse;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

/**
 * @author MANISH
 *
 */
public class InsuranceOrderDao {

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(InsuranceOrderDao.class);
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
	List<InsuranceOrderRow> insuranceOrderRowList=new ArrayList<InsuranceOrderRow>();
	FlightOrderDao flightOrderDao = new FlightOrderDao();

	public  FlightReportPage getInsuranceReports(FlightReportPage flightReportPage,String showType){
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
			Criteria criteria = session.createCriteria(InsuranceOrderRow.class);
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
				reportConjunction.add(Restrictions.in("companyId",companyIdList));	


				if(flightReportFilter.getPaxName()!= null && !flightReportFilter.getPaxName().equals(""))
				{ 
					reportConjunction.add(Restrictions.like("empNmae", flightReportFilter.getPaxName(), MatchMode.ANYWHERE));
					logger.info("##########PaxName added----"+flightReportFilter.getPaxName());
				}
				if(flightReportFilter.getConfirmationNo() != null && !flightReportFilter.getConfirmationNo().equals(""))
				{
					reportConjunction.add(Restrictions.like("confirmationNumber", flightReportFilter.getConfirmationNo(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getConfirmationNo());
				}
				if(flightReportFilter.getOrderId()!= null && !flightReportFilter.getOrderId().equals(""))
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
				
				if(flightReportFilter.getCompanyName() != null && !flightReportFilter.getCompanyName().equals(""))

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

			List<InsuranceOrderRow> list =null;
			if(rowCount>0)
			{
				if(flightReportPage.getMaxItems()==Page.ALL_ITEMS){
					logger.info(":::: retriving all items for excel export------");

					criteria = session.createCriteria(InsuranceOrderRow.class);
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

						criteria = session.createCriteria(InsuranceOrderRow.class);
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
					for (InsuranceOrderRow insuranceOrderRow :list)
					{
						ReportData reportData=new ReportData();
						FlightOrderDao FlightOrderDao=new FlightOrderDao();
						//ADDED BY BASHA
						if(insuranceOrderRow.getInsuredProductOrderRowId()!=null && insuranceOrderRow.getInsuredProductOrderRowId()>0 ){
							if(insuranceOrderRow.getInsuredProduct().equalsIgnoreCase("Flight")){
								reportData.setInsuredProductOrderRowId(insuranceOrderRow.getInsuredProductOrderRowId());
								FlightOrderRow flightOrderRow=FlightOrderDao.getFlightOrderRowDataById(insuranceOrderRow.getInsuredProductOrderRowId());
								if(flightOrderRow!=null && flightOrderRow.getOrderId()!=null)
									reportData.setInsuredProductRowOrderId(flightOrderRow.getOrderId());
								if(flightOrderRow!=null && flightOrderRow.getCompanyId()!=null)
									reportData.setInsuredProductRowCompanyId(flightOrderRow.getCompanyId());
							
							}
						}
						//ENDED BY BASHA

						//reportData.setPnr(insuranceOrderRow.getPnr());
						reportData.setFirstName(insuranceOrderRow.getOrderCustomer().getFirstName());
						reportData.setLastName(insuranceOrderRow.getOrderCustomer().getLastName());
						reportData.setTitle(insuranceOrderRow.getOrderCustomer().getTitle());
						reportData.setCreatedAt(insuranceOrderRow.getCreatedAt());
						reportData.setMarkUp(insuranceOrderRow.getMarkUpamount().setScale(2, BigDecimal.ROUND_UP));
						reportData.setSupplierName(insuranceOrderRow.getSupplierName());
						BigDecimal gstorServiceTax=new BigDecimal(0);
						if(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null) 
							gstorServiceTax=insuranceOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP);
						 if (insuranceOrderRow.getInsuranceOrderRowGstTax()!=null)  
								gstorServiceTax=insuranceOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP);
						BigDecimal finalPrice=insuranceOrderRow.getTotalAmount().add(gstorServiceTax).setScale(2, BigDecimal.ROUND_UP);
						reportData.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));
						reportData.setTotal(insuranceOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
						reportData.setBookingPrice(insuranceOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP).add(insuranceOrderRow.getMarkUpamount()!=null?insuranceOrderRow.getMarkUpamount().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00").add(insuranceOrderRow.getTaxes())).setScale(2, BigDecimal.ROUND_UP));
						reportData.setEmail(insuranceOrderRow.getOrderCustomer().getEmail());
						reportData.setStatus(insuranceOrderRow.getStatusAction());
						reportData.setPaymentStatus(insuranceOrderRow.getPaymentStatus());
						reportData.setOrderId(insuranceOrderRow.getOrderId());
						reportData.setCurCode(insuranceOrderRow.getBookingCurrency());
						reportData.setBookingDate(insuranceOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringDateddMMyyyy(insuranceOrderRow.getBookingDate()):"-");
						reportData.setId(insuranceOrderRow.getId());
						reportData.setCompanyId(insuranceOrderRow.getCompanyId());
						reportData.setInvoiceDate(DateConversion.convertDateToStringToDate(insuranceOrderRow.getCreatedAt()));
						reportData.setBasePrice(insuranceOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
						reportData.setConfirmationNumber(insuranceOrderRow.getConfirmationNumber());
						reportData.setConvenienceFees(insuranceOrderRow.getConvenienceFee().setScale(2, BigDecimal.ROUND_UP));
						reportData.setInvoiceNo(insuranceOrderRow.getInvoiceNo());
						reportData.setManagementFee(insuranceOrderRow.getManagementFee().setScale(2, BigDecimal.ROUND_UP));
						reportData.setOtherTaxes(insuranceOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP));
						reportData.setProcessingFee(insuranceOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
						reportData.setServiceTax(insuranceOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP));
						reportData.setTax(insuranceOrderRow.getTaxes().setScale(2, BigDecimal.ROUND_UP));
						reportData.setTravelDate1(insuranceOrderRow.getTravelDate());
						reportData.setTransactionKey(insuranceOrderRow.getTransactionKey());
						reportData.setOrderCustomerId(insuranceOrderRow.getOrderCustomer().getId());
						if(insuranceOrderRow.getSupplierPrice()!=null)
						reportData.setSupplierPrice(insuranceOrderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP));
						else
							reportData.setSupplierPrice(new BigDecimal(0).setScale(2, BigDecimal.ROUND_UP));
						reportData.setUpdatedBy(insuranceOrderRow.getUpdatedBy());
						reportData.setInvoiceAmount(insuranceOrderRow.getTotalAmount().add(calculateTotalserviceTax(insuranceOrderRow)).setScale(2, BigDecimal.ROUND_UP));

						if(insuranceOrderRow.getCreatedBy()!=null){
							reportData.setCreatedBy(insuranceOrderRow.getCreatedBy().replace("+", " "));	
						}
						else{
							reportData.setCreatedBy(insuranceOrderRow.getCreatedBy());	
						}

						reportData.setAgencyUsername(insuranceOrderRow.getCreatedBy());
						reportData.setDescription(insuranceOrderRow.getRemarks());
						reportData.setUserId(insuranceOrderRow.getUserId());
						User user=new User();//getSalesPersonName(flightOrderRow.getUserId());

						if(user!=null) 
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
	
	public  FlightReportPage getInsuranceOrders(FlightReportPage flightReportPage){
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
			Criteria criteria = session.createCriteria(InsuranceOrderRow.class);
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
				reportConjunction.add(Restrictions.in("companyId",companyIdList));	


				if(flightReportFilter.getOrderId()!= null && !flightReportFilter.getOrderId().equals(""))
				{ 
					reportConjunction.add(Restrictions.like("orderId", flightReportFilter.getOrderId(), MatchMode.ANYWHERE));
					logger.info("##########OrderId added----"+flightReportFilter.getOrderId());
				}
				if(flightReportFilter.getInvoiceNo()!= null && !flightReportFilter.getInvoiceNo().equals(""))
				{ 
					reportConjunction.add(Restrictions.like("invoiceNo", flightReportFilter.getInvoiceNo(), MatchMode.ANYWHERE));
					logger.info("##########OrderId added----"+flightReportFilter.getInvoiceNo());
				}

				if(flightReportFilter.getPaxName()!= null && !flightReportFilter.getPaxName().equals(""))
				{ 
					reportConjunction.add(Restrictions.like("empNmae", flightReportFilter.getPaxName(), MatchMode.ANYWHERE));
					logger.info("##########PaxName added----"+flightReportFilter.getPaxName());
				}
				if(flightReportFilter.getConfirmationNo() != null && !flightReportFilter.getConfirmationNo().equals(""))
				{
					reportConjunction.add(Restrictions.like("confirmationNumber", flightReportFilter.getConfirmationNo(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getConfirmationNo());
				}	
				/*if(flightReportFilter.getOrderId()!= null && !flightReportFilter.getOrderId().equals(""))
				{
					reportConjunction.add(Restrictions.like("orderId", flightReportFilter.getOrderId(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getOrderId());
				}	*/
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


				criteria.add(reportConjunction);
				criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);	
				criteria.addOrder(Order.desc("id"));

			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);

			List<InsuranceOrderRow> list =null;
			if(rowCount>0)
			{
				if(flightReportPage.getMaxItems()==Page.ALL_ITEMS){
					logger.info(":::: retriving all items for excel export------");

					criteria = session.createCriteria(InsuranceOrderRow.class);
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

						criteria = session.createCriteria(InsuranceOrderRow.class);
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
					for (InsuranceOrderRow insuranceOrderRow :list)
					{
						ReportData reportData=new ReportData();
						reportData=buildReportData(insuranceOrderRow, reportData);
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


	public InsuranceOrderRow updateInsuranceOrderRowDetail(InsuranceOrderRow insuranceOrderRow){
		InsuranceOrderRow newInsuranceOrderRow = null;
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			newInsuranceOrderRow=(InsuranceOrderRow) session.get(InsuranceOrderRow.class,insuranceOrderRow.getId());
			newInsuranceOrderRow.setInvoiceNo(insuranceOrderRow.getInvoiceNo());
			newInsuranceOrderRow.setOrderId(insuranceOrderRow.getOrderId());
			newInsuranceOrderRow.getOrderCustomer().setOrderId(insuranceOrderRow.getOrderId());//added by basha
			session.update(newInsuranceOrderRow);
			tx.commit();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  newInsuranceOrderRow;
	}

	public InsuranceOrderRow updateInsuranceOrderRowDetailPaymentStatus(InsuranceOrderRow insuranceOrderRow){
		InsuranceOrderRow newInsuranceOrderRow = null;
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			newInsuranceOrderRow=(InsuranceOrderRow) session.get(InsuranceOrderRow.class,insuranceOrderRow.getId());
			newInsuranceOrderRow.setPaymentStatus(insuranceOrderRow.getPaymentStatus());
			session.update(newInsuranceOrderRow);
			tx.commit();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  newInsuranceOrderRow;
	}

	public ReportData getReportDetailsByRowId(Long id) {
		// TODO Auto-generated method stub
		ReportData reportData=new ReportData();

		try{
			String ordersSql= "from InsuranceOrderRow row where row.id=:id";
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(ordersSql);
			query.setParameter("id", id);
			InsuranceOrderRow insuranceOrderRow = (InsuranceOrderRow) query.uniqueResult();
			if(insuranceOrderRow!=null){
				buildReportData(insuranceOrderRow, reportData);
			}
		}
		catch(Exception e)
		{
			logger.error(e);
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return reportData;
	}
	private ReportData buildReportData(InsuranceOrderRow insuranceOrderRow,ReportData reportData) {//reportData.setPnr(insuranceOrderRow.getPnr());
		FlightOrderDao FlightOrderDao=new FlightOrderDao();
		reportData.setFirstName(insuranceOrderRow.getOrderCustomer().getFirstName());
		reportData.setLastName(insuranceOrderRow.getOrderCustomer().getLastName());
		reportData.setTitle(insuranceOrderRow.getOrderCustomer().getTitle());
		//		reportData.setVehicleCompanyName(insuranceOrderRow.getInsuranceCompanyName());
		reportData.setBookingPrice(insuranceOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP).add(insuranceOrderRow.getMarkUpamount()!=null?insuranceOrderRow.getMarkUpamount().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00")).add(insuranceOrderRow.getTaxes()!=null?insuranceOrderRow.getTaxes().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00")));
		
		//ADDED BY BASHA
		if(insuranceOrderRow.getInsuredProductOrderRowId()!=null && insuranceOrderRow.getInsuredProductOrderRowId()>0 ){
			if(insuranceOrderRow.getInsuredProduct().equalsIgnoreCase("Flight")){
				reportData.setInsuredProductOrderRowId(insuranceOrderRow.getInsuredProductOrderRowId());
				FlightOrderRow flightOrderRow=FlightOrderDao.getFlightOrderRowDataById(insuranceOrderRow.getInsuredProductOrderRowId());
				if(flightOrderRow!=null && flightOrderRow.getOrderId()!=null)
					reportData.setInsuredProductRowOrderId(flightOrderRow.getOrderId());
				if(flightOrderRow!=null && flightOrderRow.getCompanyId()!=null)
					reportData.setInsuredProductRowCompanyId(flightOrderRow.getCompanyId());
			
			}
		}
		
		
		//ENDED BY BASHA

		
		reportData.setEmail(insuranceOrderRow.getOrderCustomer().getEmail());
		reportData.setStatus(insuranceOrderRow.getStatusAction());
		reportData.setPaymentStatus(insuranceOrderRow.getPaymentStatus());
		reportData.setOrderId(insuranceOrderRow.getOrderId());
		reportData.setCurCode(insuranceOrderRow.getBookingCurrency());
		reportData.setBookingDate(insuranceOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringDateddMMyyyy(insuranceOrderRow.getBookingDate()):"-");
		reportData.setCreatedAt(insuranceOrderRow.getCreatedAt());
		reportData.setSupplierName(insuranceOrderRow.getSupplierName());
		BigDecimal gstorServiceTax=new BigDecimal(0);
		if(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null) 
			gstorServiceTax=insuranceOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP);
		 if (insuranceOrderRow.getInsuranceOrderRowGstTax()!=null)  
				gstorServiceTax=insuranceOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP);
		BigDecimal finalPrice=insuranceOrderRow.getTotalAmount().add(gstorServiceTax).setScale(2, BigDecimal.ROUND_UP);
		reportData.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));
		reportData.setTotal(insuranceOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
		reportData.setId(insuranceOrderRow.getId());
		reportData.setCompanyId(insuranceOrderRow.getCompanyId());
		if(insuranceOrderRow.getProcessingFees()!=null)
			reportData.setConvenienceFees(insuranceOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
		else
			reportData.setConvenienceFees(new BigDecimal(0));
		reportData.setBasePrice(insuranceOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
		reportData.setConfirmationNumber(insuranceOrderRow.getConfirmationNumber());
		reportData.setConvenienceFees(insuranceOrderRow.getConvenienceFee().setScale(2, BigDecimal.ROUND_UP));
		//		reportData.setDriverAllowanceDay(insuranceOrderRow.getDriverAllowanceDay());
		//		reportData.setDriverAllowanceNight(insuranceOrderRow.getDriverAllowanceNight());
		//		reportData.setExtraHours(insuranceOrderRow.getExtraHours());
		//		reportData.setExtraKM(insuranceOrderRow.getExtraKM());
		reportData.setInvoiceNo(insuranceOrderRow.getInvoiceNo());
		reportData.setManagementFee(insuranceOrderRow.getManagementFee().setScale(2, BigDecimal.ROUND_UP));
		reportData.setOtherTaxes(insuranceOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP));
		reportData.setProcessingFee(insuranceOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
		reportData.setServiceTax(insuranceOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP));
		reportData.setTax(insuranceOrderRow.getTaxes().setScale(2, BigDecimal.ROUND_UP));
		//		reportData.setTollOrParkingCharges(insuranceOrderRow.getTollOrParkingCharges());
		reportData.setTravelDate1(insuranceOrderRow.getTravelDate());
		reportData.setTransactionKey(insuranceOrderRow.getTransactionKey());
		reportData.setOrderCustomerId(insuranceOrderRow.getOrderCustomer().getId());
		if(insuranceOrderRow.getSupplierPrice()!=null)
		reportData.setSupplierPrice(insuranceOrderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP));
		else
			reportData.setSupplierPrice(new BigDecimal(0).setScale(2, BigDecimal.ROUND_UP));
		reportData.setUpdatedBy(insuranceOrderRow.getUpdatedBy());
		reportData.setInvoiceAmount(insuranceOrderRow.getTotalAmount().add(calculateTotalserviceTax(insuranceOrderRow)).setScale(2, BigDecimal.ROUND_UP));

		if(insuranceOrderRow.getCreatedBy()!=null){
			reportData.setCreatedBy(insuranceOrderRow.getCreatedBy().replace("+", " "));	
		}
		else{
			reportData.setCreatedBy(insuranceOrderRow.getCreatedBy());	
		}
		if(insuranceOrderRow.getProcessingFees()!=null)
			reportData.setConvenienceFees(insuranceOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
		else
			reportData.setConvenienceFees(new BigDecimal(0));

		reportData.setAgencyUsername(insuranceOrderRow.getCreatedBy());
		reportData.setDescription(insuranceOrderRow.getRemarks());
		reportData.setUserId(insuranceOrderRow.getUserId());
		reportData.setStatus(insuranceOrderRow.getStatusAction());
		reportData.setPaymentStatus(insuranceOrderRow.getPaymentStatus());
		reportData.setOrderRequested(insuranceOrderRow.isOrderRequested());
		reportData.setBookingMode(insuranceOrderRow.getBookingMode());
		reportData.setCreditNoteIssued(insuranceOrderRow.isCreditNoteIssued());
		reportData.setOrderUpdated(insuranceOrderRow.isOrderUpdated());
		User user= new UserDAO().getSalesPersonName(insuranceOrderRow.getUserId());

		if(user!=null) 
			reportData.setSalesPersonName(user.getUsername());
		else 
			reportData.setSalesPersonName("-");
		return reportData;
	}
	public static BigDecimal  calculateTotalserviceTax(InsuranceOrderRow  insOrderRow){
		BigDecimal convenienceFeePerRoom = new BigDecimal("0");
		BigDecimal managementFeePerRoom = new BigDecimal("0");
		if(insOrderRow.getInsuranceOrderRowServiceTax() !=null && insOrderRow.getInsuranceOrderRowServiceTax().getManagementFee()!=null)
			managementFeePerRoom= insOrderRow.getInsuranceOrderRowServiceTax().getManagementFee();
		if(insOrderRow.getInsuranceOrderRowServiceTax() !=null && insOrderRow.getInsuranceOrderRowServiceTax().getConvenienceFee()!=null)
			convenienceFeePerRoom= insOrderRow.getInsuranceOrderRowServiceTax().getConvenienceFee();
		if(insOrderRow.getInsuranceOrderRowGstTax() !=null && insOrderRow.getInsuranceOrderRowGstTax().getManagementFee()!=null)
			managementFeePerRoom= insOrderRow.getInsuranceOrderRowGstTax().getManagementFee();
		if(insOrderRow.getInsuranceOrderRowGstTax() !=null && insOrderRow.getInsuranceOrderRowGstTax().getConvenienceFee()!=null)
			convenienceFeePerRoom= insOrderRow.getInsuranceOrderRowGstTax().getConvenienceFee();
		
		BigDecimal  serviceTax= insOrderRow.getServiceTax()!=null?insOrderRow.getServiceTax().setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);
		BigDecimal  gstTax=insOrderRow.getTotalGstTax()!=null?insOrderRow.getTotalGstTax().setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);
		
		BigDecimal  totalAmount=serviceTax.add(managementFeePerRoom).add(convenienceFeePerRoom).add(gstTax);
		return totalAmount.setScale(2,BigDecimal.ROUND_UP);
	}

	public InsuranceCreditNote insuranceCreditNoteData(Long id, int companyId) {
		String sql="from InsuranceCreditNote cn where cn.rowId=:row_id and cn.companyId=:companyid";
		InsuranceCreditNote creditNote=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("row_id",id.intValue());
			query.setParameter("companyid",String.valueOf(companyId)) ;
			creditNote=(InsuranceCreditNote)query.uniqueResult();
			if(creditNote!=null)
			{
				creditNote.setCancellationFees(creditNote.getCancellationFees().setScale(2, BigDecimal.ROUND_UP));
				creditNote.setManagementFees(creditNote.getManagementFees().setScale(2, BigDecimal.ROUND_UP));
				creditNote.setConvenienceFees(creditNote.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP));
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
		return creditNote;

	}

	public List<InsuranceCreditNote> loadCreditNoteListById(Long id) {
		// TODO Auto-generated method stub
		String sql="from InsuranceCreditNote cn where cn.rowId=:row_id";
		List<InsuranceCreditNote> list=new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("row_id",id.intValue());
			List<InsuranceCreditNote> creditList=query.list();
			if(creditList!=null &&creditList.size()>0){
				for(InsuranceCreditNote note:creditList){
					note.setConvertDate(DateConversion.convertTimestampToStringWithoutAM(note.getOrderedAt()));
					note.setConvenienceFees(note.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP));
					note.setCancellationFees(note.getCancellationFees().setScale(2, BigDecimal.ROUND_UP));
					note.setManagementFees(note.getManagementFees().setScale(2, BigDecimal.ROUND_UP));
					note.setGstAmount(note.getGstAmount().setScale(2, BigDecimal.ROUND_UP));
					list.add(note);
				}
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
		return list;

	}
	public InsuranceOrderRow getInsuranceOrderRowDetail(String orderId) {
		// TODO Auto-generated method stub
		InsuranceOrderRow insuranceOrderRow = null;
		Session session=null;
				try{
					session = HibernateUtil.getSessionFactory().openSession();
					Criteria criteria = session.createCriteria(InsuranceOrderRow.class);
					criteria.add(Restrictions.eq("orderId", orderId));
					insuranceOrderRow = (InsuranceOrderRow) criteria.uniqueResult();
				}catch (HibernateException e) {
					logger.error("--------------HibernateException-----------------"+e.getMessage());
				}finally {
					if(session!=null && session.isOpen())
						session.close(); 
				}
				return insuranceOrderRow;
		 
	}
	public InsuranceOrderRow getInsuranceOrderRowDetail(Long id) {
		// TODO Auto-generated method stub
		InsuranceOrderRow insuranceOrderRow = null;
				try{
					session = HibernateUtil.getSessionFactory().openSession();
					String sql = "from InsuranceOrderRow fotd where fotd.id=:id";
					Query query = session.createQuery(sql);
					query.setParameter("id", id);
					insuranceOrderRow = (InsuranceOrderRow) query.uniqueResult();
				}catch (HibernateException e) {
					logger.error("--------------HibernateException-----------------"+e.getMessage());
				}finally {
					if(session!=null && session.isOpen())
						session.close(); 
				}
				return insuranceOrderRow;
		 
	}
	public List<InsuranceOrderRow> getInsuranceBookingForIds(List<String> companyUserId ) {
		List<InsuranceOrderRow> insuranceOrderRows=null;
 		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr=session.createCriteria(InsuranceOrderRow.class);
			cr.add(Restrictions.in("companyId", companyUserId));
			insuranceOrderRows=cr.list();
		}catch (HibernateException e) {
			if (transaction!=null) transaction.rollback();
			logger.info("Exceeption----HibernateException--"+e.getMessage());
		}
		finally
		{
			if(session != null && session.isOpen())
			{				
				session.close(); 
			}
		}		
 		return insuranceOrderRows;
	}
}
