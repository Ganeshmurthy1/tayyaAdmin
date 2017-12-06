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

import com.admin.common.quotation.model.CarTravelRequestQuotation;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightReportPage;
import com.isl.admin.page.Page;
import com.lintas.action.CreditNote.modal.CarCreditNote;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.common.model.FlightAndHotelBookApiResponse;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

/**
 * @author MANISH
 *
 */
public class CarOrderDao {

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CarOrderDao.class);
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
	List<CarOrderRow> carOrderRowList=new ArrayList<CarOrderRow>();
	public  FlightReportPage getCarReports(FlightReportPage flightReportPage,String showType){
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
			Criteria criteria = session.createCriteria(CarOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Conjunction  conjunctionFlightOrderCustomer= Restrictions.conjunction();
			// To get total row count.
			if(flightReportPage!=null && flightReportPage.isFilterEnabled())
			{
				/*FlightReportFilter flightReportFilter = flightReportPage.getFlightReportFilter();
				companyId=String.valueOf(flightReportFilter.getLoginCompany().getCompanyid());*/

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

				/* Add multiple condition separated by AND clause within brackets. */
				/*List<String> companyIdList  = new ArrayList<String>();
				companyIdList = getCompanyIdList(flightReportFilter.getLoginCompany(), flightReportFilter.getReportType(), flightReportFilter.getCompanyName());
				logger.info("companyIdList--------------"+companyIdList.size());
				//if(companyIdList != null && companyIdList.size() > 0)
				//{
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setItems(new ArrayList<ReportData>());
					return flightReportPage;
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				logger.info("companyIdList--------------"+companyIdList);*/

				if(flightReportFilter.getPaxName()!= null && !flightReportFilter.getPaxName().equalsIgnoreCase("") )
				{ 
					reportConjunction.add(Restrictions.like("empNmae", flightReportFilter.getPaxName(), MatchMode.ANYWHERE));
					logger.info("##########PaxName added----"+flightReportFilter.getPaxName());
				}
				if(flightReportFilter.getConfirmationNo() != null && !flightReportFilter.getConfirmationNo().equalsIgnoreCase(""))
				{
					reportConjunction.add(Restrictions.like("confirmationNumber", flightReportFilter.getConfirmationNo(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getConfirmationNo());
				}	

				if(flightReportFilter.getOrderId()!= null && !flightReportFilter.getOrderId().equalsIgnoreCase(""))
				{
					reportConjunction.add(Restrictions.like("orderId", flightReportFilter.getOrderId(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getOrderId());
				}	
				
			
				
				
				
				
				
			/////////////////////////////////////////////////////////////////////////////////////////////	
				if(flightReportFilter.getInvoiceNo() != null  && !flightReportFilter.getInvoiceNo().equalsIgnoreCase(""))
				{
					reportConjunction.add(Restrictions.like("invoiceNo", flightReportFilter.getInvoiceNo(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getInvoiceNo());
				}	
				if(flightReportFilter.getConfirmationNo() != null && !flightReportFilter.getConfirmationNo().equalsIgnoreCase(""))
				{
					reportConjunction.add(Restrictions.like("confirmationNumber", flightReportFilter.getConfirmationNo(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getConfirmationNo());
				}	
				if(flightReportFilter.getFirstName() != null && !flightReportFilter.getFirstName().equalsIgnoreCase(""))
				{
					conjunctionFlightOrderCustomer.add(Restrictions.like("firstName", flightReportFilter.getFirstName(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getFirstName());
				}	
				if(flightReportFilter.getLastName() != null && !flightReportFilter.getLastName().equalsIgnoreCase(""))
				{
					conjunctionFlightOrderCustomer.add(Restrictions.like("lastName", flightReportFilter.getLastName(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getLastName());
				}	
				if(flightReportFilter.getSupplierName() != null && !flightReportFilter.getSupplierName().equalsIgnoreCase(""))
				{
					reportConjunction.add(Restrictions.like("supplierName", flightReportFilter.getSupplierName(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getSupplierName());
				}	
				if(flightReportFilter.getCustomerMobileNo() != null && !flightReportFilter.getCustomerMobileNo().equalsIgnoreCase(""))
				{
					conjunctionFlightOrderCustomer.add(Restrictions.like("mobile", flightReportFilter.getCustomerMobileNo(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getCustomerMobileNo());
				}	
				if(flightReportFilter.getLocation() != null && !flightReportFilter.getLocation().equalsIgnoreCase(""))
				{
					reportConjunction.add(Restrictions.like("location", flightReportFilter.getLocation(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getLocation());
				}	
				
				if(flightReportFilter.getDateFilterInvoice().getDtStart() != null && flightReportFilter.getDateFilterInvoice().getDtEnd() != null && !flightReportFilter.getDateFilterInvoice().getDtStart().equals("") && !flightReportFilter.getDateFilterInvoice().getDtEnd().equals("") )
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
				
			///////////////////////////////////////////////////////////////////////////////////////////////	
				if(flightReportFilter.getEmail() != null && !flightReportFilter.getEmail().equals(""))
				{		
					conjunctionFlightOrderCustomer.add(Restrictions.eq("email", flightReportFilter.getEmail()));
				}
 
				if(flightReportFilter.getCompanyId()>0){
					reportConjunction.add(Restrictions.like("companyId", String.valueOf(flightReportFilter.getCompanyId())));
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
				if(!flightReportFilter.getFromDate().equals("")  && !flightReportFilter.getToDate().equals(""))
				{
					//2016-06-28
					//SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");


					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterArrival().getDtStart());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);

						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));

						reportConjunction.add(Restrictions.ge("createdAt", date));
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

						reportConjunction.add(Restrictions.lt("createdAt", date));
						logger.info("##########getDateFilterCheckIn date max added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########getDateFilterCheckIn date max format exception---"+ex.getMessage());

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

						date = new Date(date.getTime() + TimeUnit.HOURS.toMillis(1));

						reportConjunction.add(Restrictions.le("travelDate", date));
						logger.info("##########getDateFilterCheckIn date max added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########getDateFilterCheckIn date max format exception---"+ex.getMessage());

					}
				}





				try{
					if(showType!=null && showType.equalsIgnoreCase("carconfirm")){
						///reportConjunction.add(Restrictions.in("companyId",companyIdList));
						reportConjunction.add(Restrictions.eq("statusAction","Confirmed"));
					}
					else if(showType!=null && showType.equalsIgnoreCase("carpaymentfailed")){
						//reportConjunction.add(Restrictions.in("companyId",companyIdList));
						reportConjunction.add(Restrictions.eq("paymentStatus","Failed"));
					}	
					else if(showType!=null && showType.equalsIgnoreCase("carpayment")){
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

				List<CarTravelRequestQuotation>   trainTravelRequestQuotations=new ArrayList<>();
				List<Long>   orderRowIds=new ArrayList<>();
				Criteria criteria1 = session.createCriteria(CarTravelRequestQuotation.class);
				if(flightReportFilter.getFromLocation() != null && !flightReportFilter.getFromLocation().equalsIgnoreCase("") || flightReportFilter.getToLocation() != null && !flightReportFilter.getToLocation().equalsIgnoreCase("")){
				if(flightReportFilter.getFromLocation() != null && !flightReportFilter.getFromLocation().equalsIgnoreCase("")){
					criteria1.add(Restrictions.like("pickUp", flightReportFilter.getFromLocation(), MatchMode.ANYWHERE));
				}
				if(flightReportFilter.getToLocation() != null && !flightReportFilter.getToLocation().equalsIgnoreCase("")){
					criteria1.add(Restrictions.like("dropOff", flightReportFilter.getToLocation(), MatchMode.ANYWHERE));
				}	
					trainTravelRequestQuotations=criteria1.list();
					for (CarTravelRequestQuotation trainTravelRequestQuotation : trainTravelRequestQuotations) {
						orderRowIds.add(trainTravelRequestQuotation.getId());
				}
					reportConjunction.add(Restrictions.in("id", orderRowIds));
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);	
				criteria.addOrder(Order.desc("id"));


			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);

			List<CarOrderRow> list =null;
			if(rowCount>0)
			{
				if(flightReportPage.getMaxItems()==Page.ALL_ITEMS){
					logger.info(":::: retriving all items for excel export------");

					criteria = session.createCriteria(CarOrderRow.class);
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

						criteria = session.createCriteria(CarOrderRow.class);
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

				flightReportPage=new FlightReportPage();

				if(list!=null && list.size()>0)
				{
					for (CarOrderRow carOrderRow :list)
					{
						ReportData reportData=new ReportData();
						//reportData.setPnr(carOrderRow.getPnr());
						reportData.setFirstName(carOrderRow.getOrderCustomer().getFirstName());
						reportData.setLastName(carOrderRow.getOrderCustomer().getLastName());
						reportData.setTitle(carOrderRow.getOrderCustomer().getTitle());
						reportData.setVehicleCompanyName(carOrderRow.getCarCompanyName());
						reportData.setBookingPrice(carOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP).add(carOrderRow.getMarkUp()!=null?carOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00").add(carOrderRow.getTaxes())).setScale(2, BigDecimal.ROUND_UP));
						BigDecimal gstorServiceTax=new BigDecimal(0);
						if(carOrderRow.getCarOrderRowServiceTax()!=null) 
							gstorServiceTax=carOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP);
						 if (carOrderRow.getCarOrderRowGstTax()!=null)  
								gstorServiceTax=carOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP);
						BigDecimal finalPrice=carOrderRow.getTotalAmount().add(gstorServiceTax).setScale(2, BigDecimal.ROUND_UP);
						reportData.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));
						reportData.setEmail(carOrderRow.getOrderCustomer().getEmail());
						reportData.setStatus(carOrderRow.getStatusAction());
						reportData.setPaymentStatus(carOrderRow.getPaymentStatus());
						reportData.setOrderId(carOrderRow.getOrderId());
						reportData.setCurCode(carOrderRow.getBookingCurrency());
						reportData.setBookingDate(carOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringDateddMMyyyy(carOrderRow.getBookingDate()):"-");
						reportData.setTotal(carOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
						//reportData.setDepartureDate(DateConversion.getBluestarDate(carOrderRow.getDepartureDate()));
						reportData.setMarkUp(carOrderRow.getMarkUp()!=null?carOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
						BigDecimal netPaybleAmount=(carOrderRow.getTotalAmount().subtract(carOrderRow.getMarkUp()).setScale(2, BigDecimal.ROUND_UP));
						reportData.setNetAmnt(netPaybleAmount.setScale(2,BigDecimal.ROUND_UP));
						reportData.setOrigin(carOrderRow.getLocation());
						reportData.setId(carOrderRow.getId());
						reportData.setCompanyId(carOrderRow.getCompanyId());
						reportData.setCreatedAt(carOrderRow.getCreatedAt());
						reportData.setBasePrice(carOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
						reportData.setConfirmationNumber(carOrderRow.getConfirmationNumber());
						reportData.setConvenienceFees(carOrderRow.getConvenienceFee().setScale(2, BigDecimal.ROUND_UP));
						reportData.setDriverAllowanceDay(carOrderRow.getDriverAllowanceDay());
						reportData.setDriverAllowanceNight(carOrderRow.getDriverAllowanceNight());
						reportData.setInvoiceNo(carOrderRow.getInvoiceNo());
						reportData.setManagementFee(carOrderRow.getManagementFee().setScale(2, BigDecimal.ROUND_UP));
						reportData.setConvenienceFees(carOrderRow.getConvenienceFee().setScale(2, BigDecimal.ROUND_UP));
						reportData.setOtherTaxes(carOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP));
						reportData.setProcessingFee(carOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
						reportData.setServiceTax(carOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP));
						reportData.setTax(carOrderRow.getTaxes().setScale(2, BigDecimal.ROUND_UP));
						reportData.setTollOrParkingCharges(carOrderRow.getTollOrParkingCharges().setScale(2, BigDecimal.ROUND_UP));
						reportData.setDriverAllowanceNight(carOrderRow.getDriverAllowanceNight().setScale(2, BigDecimal.ROUND_UP));
						reportData.setDriverAllowanceDay(carOrderRow.getDriverAllowanceDay().setScale(2, BigDecimal.ROUND_UP));

						reportData.setExtraHours(carOrderRow.getExtraHours()!=null?new BigDecimal(carOrderRow.getExtraHours()).setScale(2, BigDecimal.ROUND_UP).toString():new BigDecimal("0.00").toString());
						reportData.setExtraKM(carOrderRow.getExtraKM()!=null?new BigDecimal(carOrderRow.getExtraKM()).setScale(2, BigDecimal.ROUND_UP).toString():new BigDecimal("0.00").toString());
						//reportData.setTravelDate(carOrderRow.getTravelDateTemp());
						reportData.setTravelDate(DateConversion.convertDateToStringToDate(carOrderRow.getTravelDate()));
						reportData.setInvoiceDate(DateConversion.convertDateToStringToDate(carOrderRow.getCreatedAt()));
						reportData.setTransactionKey(carOrderRow.getTransactionKey());
						reportData.setOrderCustomerId(carOrderRow.getOrderCustomer().getId());
						reportData.setSupplierPrice(carOrderRow.getSupplierPrice()!=null?carOrderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
						reportData.setUpdatedBy(carOrderRow.getUpdatedBy());
						reportData.setInvoiceAmount(carOrderRow.getTotalAmount().add(calculateTotalserviceTax(carOrderRow)).setScale(2, BigDecimal.ROUND_UP));
						if(carOrderRow.getCreatedBy()!=null){
							reportData.setCreatedBy(carOrderRow.getCreatedBy().replace("+", " "));	
						}
						else{
							reportData.setCreatedBy(carOrderRow.getCreatedBy());	
						}

						reportData.setAgencyUsername(carOrderRow.getCreatedBy());
						reportData.setDescription(carOrderRow.getRemarks());
						reportData.setUserId(carOrderRow.getUserId());
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
	public FlightReportPage getCarOrders(FlightReportPage commonReportPage) {
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
			Criteria criteria = session.createCriteria(CarOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Conjunction  conjunctionFlightOrderCustomer= Restrictions.conjunction();
			// To get total row count.
			FlightReportFilter flightReportFilter =  null;
			if(commonReportPage!=null && commonReportPage.isFilterEnabled())
			{
				flightReportFilter = commonReportPage.getFlightReportFilter();
				List<String> companyIdList  = new ArrayList<String>();
				companyIdList = flightOrderDao.getCompanyIdList(flightReportFilter.getLoginCompany(), flightReportFilter.getReportType(), flightReportFilter.getCompanyName());
				logger.info("companyIdList--------------"+companyIdList.size());
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					commonReportPage.setAvailableItems(0);					
					commonReportPage.setItems(new ArrayList<ReportData>());
					return commonReportPage;
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));	
				/* Add multiple condition separated by AND clause within brackets. */
				/*List<String> companyIdList  = new ArrayList<String>();
				companyIdList = getCompanyIdList(flightReportFilter.getLoginCompany(), flightReportFilter.getReportType(), flightReportFilter.getCompanyName());
				logger.info("companyIdList--------------"+companyIdList.size());
				//if(companyIdList != null && companyIdList.size() > 0)
				//{
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setItems(new ArrayList<ReportData>());
					return flightReportPage;
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				logger.info("companyIdList--------------"+companyIdList);*/

				if(flightReportFilter.getPaxName()!= null)
				{ 
					reportConjunction.add(Restrictions.like("empNmae", flightReportFilter.getPaxName(), MatchMode.ANYWHERE));
					logger.info("##########PaxName added----"+flightReportFilter.getPaxName());
				}
				if(flightReportFilter.getConfirmationNo() != null)
				{
					reportConjunction.add(Restrictions.like("confirmationNumber", flightReportFilter.getConfirmationNo(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getConfirmationNo());
				}	
				if(flightReportFilter.getOrderId()!= null)
				{
					reportConjunction.add(Restrictions.like("orderId", flightReportFilter.getOrderId(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getOrderId());
				}	
				
				
				/////////////////////////////////////////////////////////////////////////////////////////////	
				if(flightReportFilter.getInvoiceNo() != null  && !flightReportFilter.getInvoiceNo().equalsIgnoreCase(""))
				{
					reportConjunction.add(Restrictions.like("invoiceNo", flightReportFilter.getInvoiceNo(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getInvoiceNo());
				}	
				if(flightReportFilter.getConfirmationNo() != null && !flightReportFilter.getConfirmationNo().equalsIgnoreCase(""))
				{
					reportConjunction.add(Restrictions.like("confirmationNumber", flightReportFilter.getConfirmationNo(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getConfirmationNo());
				}	
				if(flightReportFilter.getFirstName() != null && !flightReportFilter.getFirstName().equalsIgnoreCase(""))
				{
					conjunctionFlightOrderCustomer.add(Restrictions.like("firstName", flightReportFilter.getFirstName(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getFirstName());
				}	
				if(flightReportFilter.getLastName() != null && !flightReportFilter.getLastName().equalsIgnoreCase(""))
				{
					conjunctionFlightOrderCustomer.add(Restrictions.like("lastName", flightReportFilter.getLastName(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getLastName());
				}	
				if(flightReportFilter.getSupplierName() != null && !flightReportFilter.getSupplierName().equalsIgnoreCase(""))
				{
					reportConjunction.add(Restrictions.like("supplierName", flightReportFilter.getSupplierName(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getSupplierName());
				}	
				if(flightReportFilter.getCustomerMobileNo() != null && !flightReportFilter.getCustomerMobileNo().equalsIgnoreCase(""))
				{
					conjunctionFlightOrderCustomer.add(Restrictions.like("mobile", flightReportFilter.getCustomerMobileNo(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getCustomerMobileNo());
				}	
				if(flightReportFilter.getLocation() != null && !flightReportFilter.getLocation().equalsIgnoreCase(""))
				{
					reportConjunction.add(Restrictions.like("location", flightReportFilter.getLocation(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getLocation());
				}	
				
				if(flightReportFilter.getDateFilterInvoice().getDtStart() != null && flightReportFilter.getDateFilterInvoice().getDtEnd() != null && !flightReportFilter.getDateFilterInvoice().getDtStart().equals("") && !flightReportFilter.getDateFilterInvoice().getDtEnd().equals("") )
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
				

				
			///////////////////////////////////////////////////////////////////////////////////////////////	
				if(flightReportFilter.getEmail() != null && !flightReportFilter.getEmail().equals(""))
				{		
					conjunctionFlightOrderCustomer.add(Restrictions.eq("email", flightReportFilter.getEmail()));
				}

				if(flightReportFilter.getUserId()>0)
					reportConjunction.add(Restrictions.like("userId",  String.valueOf(flightReportFilter.getUserId())));

				if(flightReportFilter.getCompanyId()>0)
					reportConjunction.add(Restrictions.like("companyId", String.valueOf(flightReportFilter.getCompanyId())));
	
				
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

						reportConjunction.add(Restrictions.le("travelDate", date));
						logger.info("##########getDateFilterCheckIn date max added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########getDateFilterCheckIn date max format exception---"+ex.getMessage());

					}
				}

				List<CarTravelRequestQuotation>   trainTravelRequestQuotations=new ArrayList<>();
				List<Long>   orderRowIds=new ArrayList<>();
				Criteria criteria1 = session.createCriteria(CarTravelRequestQuotation.class);
				if(flightReportFilter.getFromLocation() != null && !flightReportFilter.getFromLocation().equalsIgnoreCase("") || flightReportFilter.getToLocation() != null && !flightReportFilter.getToLocation().equalsIgnoreCase("")){
				if(flightReportFilter.getFromLocation() != null && !flightReportFilter.getFromLocation().equalsIgnoreCase("")){
					criteria1.add(Restrictions.like("pickUp", flightReportFilter.getFromLocation(), MatchMode.ANYWHERE));
				}
				if(flightReportFilter.getToLocation() != null && !flightReportFilter.getToLocation().equalsIgnoreCase("")){
					criteria1.add(Restrictions.like("dropOff", flightReportFilter.getToLocation(), MatchMode.ANYWHERE));
				}	
					trainTravelRequestQuotations=criteria1.list();
					for (CarTravelRequestQuotation trainTravelRequestQuotation : trainTravelRequestQuotations) {
						orderRowIds.add(trainTravelRequestQuotation.getId());
				}
					reportConjunction.add(Restrictions.in("id", orderRowIds));
				}


				criteria.add(reportConjunction);
				criteria.addOrder(Order.desc("id"));
				criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);	
				//criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);	

			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);

			List<CarOrderRow> list =null;
			if(rowCount>0)
			{
				if(commonReportPage.getMaxItems()==Page.ALL_ITEMS){
					logger.info(":::: retriving all items for excel export------");

					criteria = session.createCriteria(CarOrderRow.class);
					criteria.add(reportConjunction);
					criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					logger.info(":::: retriving all items for excel export-----list-"+list);
					logger.info("rowCountForExcel list.size ------"+((list != null)?0:list.size()));	
					commonReportPage.setAvailableItems(list.size());
					commonReportPage.setAvailablePages(1);

				}
				else{
					if(commonReportPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % commonReportPage.getMaxItems() == 0)?(availableItems / commonReportPage.getMaxItems()):((availableItems / commonReportPage.getMaxItems()) + 1);
						commonReportPage.setAvailableItems(availableItems);
						commonReportPage.setAvailablePages(availablePages);
					} 
					//Retrive report with pagination .......

					int pageIndexDb = (commonReportPage.getCurrentPageIndex() > 1)?commonReportPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * commonReportPage.getMaxItems();

					//int itemIndex = flightReportPage.getCurrentPageIndex() * flightReportPage.getMaxItems();

					logger.info("setFirstResult-------"+itemIndex);

					if(itemIndex <= rowCount)
					{
						logger.info("setFirstResult-------"+itemIndex);
						logger.info("MaxResults-------"+commonReportPage.getMaxItems());

						criteria = session.createCriteria(CarOrderRow.class);
						criteria.add(reportConjunction);
						criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);	
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(commonReportPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
						logger.info("Reports size------"+list.size());	

					}

				}

				//logger.info("---------logged in company id-----------------------------"+companySessionObj.getCompanyid());
				if(list!=null && list.size()>0)
				{
					for (CarOrderRow carOrderRow :list)
					{
						ReportData reportData=new ReportData();
						reportData=buildReportData(carOrderRow, reportData);
						reportData_list.add(reportData);
					}					
					commonReportPage.setItems(reportData_list);

				}
				else
				{
					//current page is having empty items..
					commonReportPage.setAvailableItems(0);					
					commonReportPage.setItems(new ArrayList<ReportData>());
				}

			}
			else
			{
				commonReportPage.setAvailableItems(0);
				commonReportPage.setAvailablePages(0);
				commonReportPage.setItems(new ArrayList<ReportData>());
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
		return commonReportPage;
	}






	public CarOrderRow updateCarOrderRowDetail(CarOrderRow carOrderRow){
		CarOrderRow newCarOrderRow = null;
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			newCarOrderRow=(CarOrderRow) session.get(CarOrderRow.class,carOrderRow.getId());
			newCarOrderRow.setInvoiceNo(carOrderRow.getInvoiceNo());
			newCarOrderRow.setOrderId(carOrderRow.getOrderId());
			newCarOrderRow.getOrderCustomer().setOrderId(carOrderRow.getOrderId());//added by basha
			session.update(newCarOrderRow);
			tx.commit();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  newCarOrderRow;
	}
	
	public CarOrderRow updateCarOrderRowDetailPaymentStatus(CarOrderRow carOrderRow){
		CarOrderRow newCarOrderRow = null;
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			newCarOrderRow=(CarOrderRow) session.get(CarOrderRow.class,carOrderRow.getId());
			newCarOrderRow.setPaymentStatus(carOrderRow.getPaymentStatus());
			session.update(newCarOrderRow);
			tx.commit();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  newCarOrderRow;
	}
	public ReportData getReportDetailsByRowId(Long id) {
		// TODO Auto-generated method stub
		ReportData reportData=new ReportData();

		try{
			String ordersSql= "from CarOrderRow row where row.id=:id";
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(ordersSql);
			query.setParameter("id", id);
			CarOrderRow carOrderRow = (CarOrderRow) query.uniqueResult();
			if(carOrderRow!=null){
				buildReportData(carOrderRow, reportData);
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
	public CarCreditNote carCreditNoteData(Long id, int companyId) {
		String sql="from CarCreditNote cn where cn.rowId=:row_id and cn.companyId=:companyid";
		CarCreditNote creditNote=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("row_id",id.intValue());
			query.setParameter("companyid",String.valueOf(companyId)) ;
			creditNote=(CarCreditNote)query.uniqueResult();
			if(creditNote!=null)
			{
				creditNote.setCancellationFees(creditNote.getCancellationFees().setScale(2, BigDecimal.ROUND_UP));
				creditNote.setConvenienceFees(creditNote.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP));
				creditNote.setManagementFees(creditNote.getManagementFees().setScale(2, BigDecimal.ROUND_UP));
			 
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

	public List<CarCreditNote> loadCreditNoteListById(Long id) {
		// TODO Auto-generated method stub
		String sql="from CarCreditNote cn where cn.rowId=:row_id";
		List<CarCreditNote> list=new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("row_id",id.intValue());
			List<CarCreditNote> creditList=query.list();
			if(creditList!=null &&creditList.size()>0){
				for(CarCreditNote note:creditList){
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
	private ReportData buildReportData(CarOrderRow carOrderRow,ReportData reportData) {

		//reportData.setPnr(carOrderRow.getPnr());
		reportData.setFirstName(carOrderRow.getOrderCustomer().getFirstName());
		reportData.setLastName(carOrderRow.getOrderCustomer().getLastName());
		reportData.setVehicleCompanyName(carOrderRow.getCarCompanyName());
		//reportData.setFinalPrice(carOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
		reportData.setTotal(carOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
		BigDecimal gstorServiceTax=new BigDecimal(0);
		if(carOrderRow.getCarOrderRowServiceTax()!=null) 
			gstorServiceTax=carOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP);
		 if (carOrderRow.getCarOrderRowGstTax()!=null)  
				gstorServiceTax=carOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP);
		BigDecimal finalPrice=carOrderRow.getTotalAmount().add(gstorServiceTax).setScale(2, BigDecimal.ROUND_UP);
		reportData.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));
		
		reportData.setEmail(carOrderRow.getOrderCustomer().getEmail());
		reportData.setOrderId(carOrderRow.getOrderId());
		reportData.setCurCode(carOrderRow.getBookingCurrency());
		reportData.setBookingDate(carOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringDateddMMyyyy(carOrderRow.getBookingDate()):"-");

		reportData.setTitle(carOrderRow.getOrderCustomer().getTitle());
		reportData.setMarkUp(carOrderRow.getMarkUp().setScale(2,BigDecimal.ROUND_UP));
		reportData.setBookingPrice(carOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP).add(carOrderRow.getMarkUp()!=null?carOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00")).add(carOrderRow.getTaxes()!=null?carOrderRow.getTaxes().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00")));
		reportData.setInsuranceBookingDate(carOrderRow.getCarBookingDate());
		reportData.setCreatedAt(carOrderRow.getCreatedAt());

		reportData.setSupplierName(carOrderRow.getSupplierName());
		reportData.setMarkUp(carOrderRow.getMarkUp()!=null?carOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
		BigDecimal netPaybleAmount=(carOrderRow.getTotalAmount().subtract(carOrderRow.getMarkUp()).setScale(2, BigDecimal.ROUND_UP));
		reportData.setNetAmnt(netPaybleAmount.setScale(2,BigDecimal.ROUND_UP));
		reportData.setOrigin(carOrderRow.getLocation());
		reportData.setId(carOrderRow.getId());
		reportData.setCompanyId(carOrderRow.getCompanyId());
		reportData.setBasePrice(carOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
		reportData.setConfirmationNumber(carOrderRow.getConfirmationNumber());
		reportData.setConvenienceFees(carOrderRow.getConvenienceFee().setScale(2, BigDecimal.ROUND_UP));
		reportData.setDriverAllowanceDay(carOrderRow.getDriverAllowanceDay());
		reportData.setDriverAllowanceNight(carOrderRow.getDriverAllowanceNight());
		reportData.setInvoiceNo(carOrderRow.getInvoiceNo());
		reportData.setManagementFee(carOrderRow.getManagementFee().setScale(2, BigDecimal.ROUND_UP));
		reportData.setOtherTaxes(carOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP));
		reportData.setProcessingFee(carOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
		if(carOrderRow.getProcessingFees()!=null)
			reportData.setConvenienceFees(carOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
		else
			reportData.setConvenienceFees(new BigDecimal(0));
		reportData.setServiceTax(carOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP));
		reportData.setTax(carOrderRow.getTaxes().setScale(2, BigDecimal.ROUND_UP));
		reportData.setTollOrParkingCharges(carOrderRow.getTollOrParkingCharges().setScale(2, BigDecimal.ROUND_UP));
		reportData.setDriverAllowanceNight(carOrderRow.getDriverAllowanceNight().setScale(2, BigDecimal.ROUND_UP));
		reportData.setDriverAllowanceDay(carOrderRow.getDriverAllowanceDay().setScale(2, BigDecimal.ROUND_UP));
		reportData.setExtraHours(new BigDecimal(carOrderRow.getExtraHours()).setScale(2, BigDecimal.ROUND_UP).toString());
		reportData.setExtraKM(new BigDecimal(carOrderRow.getExtraKM()).setScale(2, BigDecimal.ROUND_UP).toString());
		//reportData.setTravelDate(carOrderRow.getTravelDateTemp());
		reportData.setTravelDate(DateConversion.convertDateToStringDateddMMyyyy(carOrderRow.getTravelDate()));
		reportData.setInvoiceDate(DateConversion.convertDateToStringToDate(carOrderRow.getCreatedAt()));
		reportData.setTransactionKey(carOrderRow.getTransactionKey());
		reportData.setOrderCustomerId(carOrderRow.getOrderCustomer().getId());
		reportData.setSupplierPrice(carOrderRow.getSupplierPrice()!=null?carOrderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
		reportData.setUpdatedBy(carOrderRow.getUpdatedBy());
		reportData.setInvoiceAmount(carOrderRow.getTotalAmount().add(calculateTotalserviceTax(carOrderRow)).setScale(2, BigDecimal.ROUND_UP));
		
		if(carOrderRow.getCreatedBy()!=null){
			reportData.setCreatedBy(carOrderRow.getCreatedBy().replace("+", " "));	
		}
		else{
			reportData.setCreatedBy(carOrderRow.getCreatedBy());	
		}

		reportData.setAgencyUsername(carOrderRow.getCreatedBy());
		reportData.setDescription(carOrderRow.getRemarks());

		reportData.setUserId(carOrderRow.getUserId());
		reportData.setStatus(carOrderRow.getStatusAction());
		reportData.setPaymentStatus(carOrderRow.getPaymentStatus());
		reportData.setOrderRequested(carOrderRow.isOrderRequested());
		reportData.setBookingMode(carOrderRow.getBookingMode());
		reportData.setCreditNoteIssued(carOrderRow.isCreditNoteIssued());
		reportData.setOrderUpdated(carOrderRow.isOrderUpdated());
		User user= new UserDAO().getSalesPersonName(carOrderRow.getUserId());

		if(user!=null) 
			reportData.setSalesPersonName(user.getUsername());
		else 
			reportData.setSalesPersonName("-");
		return reportData;

	}
	public static BigDecimal  calculateTotalserviceTax(CarOrderRow  carOrderRow){
		BigDecimal convenienceFeePerRoom = new BigDecimal("0");
		BigDecimal managementFeePerRoom = new BigDecimal("0");
		if(carOrderRow.getCarOrderRowServiceTax() !=null && carOrderRow.getCarOrderRowServiceTax().getManagementFee()!=null)
			managementFeePerRoom= carOrderRow.getCarOrderRowServiceTax().getManagementFee();
		if(carOrderRow.getCarOrderRowServiceTax() !=null && carOrderRow.getCarOrderRowServiceTax().getConvenienceFee()!=null)
			convenienceFeePerRoom= carOrderRow.getCarOrderRowServiceTax().getConvenienceFee();
		if(carOrderRow.getCarOrderRowGstTax() !=null && carOrderRow.getCarOrderRowGstTax().getManagementFee()!=null)
			managementFeePerRoom= carOrderRow.getCarOrderRowGstTax().getManagementFee();
		if(carOrderRow.getCarOrderRowGstTax() !=null && carOrderRow.getCarOrderRowGstTax().getConvenienceFee()!=null)
			convenienceFeePerRoom= carOrderRow.getCarOrderRowGstTax().getConvenienceFee();
		
		BigDecimal  serviceTax= carOrderRow.getServiceTax()!=null?carOrderRow.getServiceTax().setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);
		BigDecimal  gstTax=carOrderRow.getTotalGstTax()!=null?carOrderRow.getTotalGstTax().setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);
		
		BigDecimal  totalAmount=serviceTax.add(managementFeePerRoom).add(convenienceFeePerRoom).add(gstTax);
		return totalAmount.setScale(2,BigDecimal.ROUND_UP);
	}
	public CarOrderRow getCarOrderRowDetail(String orderId) {
		CarOrderRow carOrderRow = null;
		Session session=null;
			try{
				session = HibernateUtil.getSessionFactory().openSession();
				Criteria criteria = session.createCriteria(CarOrderRow.class);
				criteria.add(Restrictions.eq("orderId", orderId));
				carOrderRow = (CarOrderRow) criteria.uniqueResult();
			}catch (HibernateException e) {
				logger.error("--------------HibernateException-----------------"+e.getMessage());
			}finally {
				if(session!=null && session.isOpen())
					session.close(); 
			}
			return carOrderRow;

		}
	public CarOrderRow getCarOrderRowDetail(Long id) {
		CarOrderRow carOrderRow = null;
			try{
				session = HibernateUtil.getSessionFactory().openSession();
				String sql = "from CarOrderRow fotd where fotd.id=:id";
				Query query = session.createQuery(sql);
				query.setParameter("id", id);
				carOrderRow = (CarOrderRow) query.uniqueResult();
			}catch (HibernateException e) {
				logger.error("--------------HibernateException-----------------"+e.getMessage());
			}finally {
				if(session!=null && session.isOpen())
					session.close(); 
			}
			return carOrderRow;

		}
	
	public List<CarOrderRow>  getCarBookingForIds(List<String> companyUserId ) {
		List<CarOrderRow> carOrderRows=null;
 		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr=session.createCriteria(CarOrderRow.class);
			cr.add(Restrictions.in("companyId", companyUserId));
			carOrderRows=cr.list();
			
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
 		return carOrderRows;
	}
	
	 
}
