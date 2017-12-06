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

import com.admin.common.quotation.model.BusTravelRequestQuotation;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightReportPage;
import com.isl.admin.page.Page;
import com.lintas.action.CreditNote.modal.BusCreditNote;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.common.model.FlightAndHotelBookApiResponse;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

/**
 * @author MANISH
 *
 */
public class BusOrderDao {

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(BusOrderDao.class);
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
	List<BusOrderRow> busOrderRowList=new ArrayList<BusOrderRow>();
	public  FlightReportPage getBusReports(FlightReportPage flightReportPage,String showType){
		List<ReportData>  reportData_list=new ArrayList<ReportData>();
		int availablePages = 0;
		int availableItems = 0;
		Session session = null;
		FlightOrderDao flightOrderDao = new FlightOrderDao();
		try{

			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(BusOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Conjunction  conjunctionBusOrderCustomer= Restrictions.conjunction();

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

				if(flightReportFilter.getPaxName()!= null && !flightReportFilter.getPaxName().equalsIgnoreCase(""))
				{ 
					reportConjunction.add(Restrictions.like("empName", flightReportFilter.getPaxName(), MatchMode.ANYWHERE));
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
					conjunctionBusOrderCustomer.add(Restrictions.like("firstName", flightReportFilter.getFirstName(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getFirstName());
				}	
				if(flightReportFilter.getLastName() != null && !flightReportFilter.getLastName().equalsIgnoreCase(""))
				{
					conjunctionBusOrderCustomer.add(Restrictions.like("lastName", flightReportFilter.getLastName(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getLastName());
				}	
				if(flightReportFilter.getSupplierName() != null && !flightReportFilter.getSupplierName().equalsIgnoreCase(""))
				{
					reportConjunction.add(Restrictions.like("supplierName", flightReportFilter.getSupplierName(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getSupplierName());
				}	
				if(flightReportFilter.getCustomerMobileNo() != null && !flightReportFilter.getCustomerMobileNo().equalsIgnoreCase(""))
				{
					conjunctionBusOrderCustomer.add(Restrictions.like("mobile", flightReportFilter.getCustomerMobileNo(), MatchMode.ANYWHERE));
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
					conjunctionBusOrderCustomer.add(Restrictions.eq("email", flightReportFilter.getEmail()));
				}
				if(flightReportFilter.getUserId()>0){
					reportConjunction.add(Restrictions.like("userId", String.valueOf(flightReportFilter.getUserId())));
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

				try{
					if(showType!=null && showType.equalsIgnoreCase("busconfirm")){
						///reportConjunction.add(Restrictions.in("companyId",companyIdList));
						reportConjunction.add(Restrictions.eq("statusAction","Confirmed"));
					}
					else if(showType!=null && showType.equalsIgnoreCase("buspaymentfailed")){
						//reportConjunction.add(Restrictions.in("companyId",companyIdList));
						reportConjunction.add(Restrictions.eq("paymentStatus","Failed"));
					}	
					else if(showType!=null && showType.equalsIgnoreCase("buspayment")){
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


				List<BusTravelRequestQuotation>   trainTravelRequestQuotations=new ArrayList<>();
				List<Long>   orderRowIds=new ArrayList<>();
				Criteria criteria1 = session.createCriteria(BusTravelRequestQuotation.class);
				if(flightReportFilter.getFromLocation() != null && !flightReportFilter.getFromLocation().equalsIgnoreCase("") || flightReportFilter.getToLocation() != null && !flightReportFilter.getToLocation().equalsIgnoreCase("")){
					if(flightReportFilter.getFromLocation() != null && !flightReportFilter.getFromLocation().equalsIgnoreCase("")){
						criteria1.add(Restrictions.like("pickUp", flightReportFilter.getFromLocation(), MatchMode.ANYWHERE));
					}
					if(flightReportFilter.getToLocation() != null && !flightReportFilter.getToLocation().equalsIgnoreCase("")){
						criteria1.add(Restrictions.like("dropOff", flightReportFilter.getToLocation(), MatchMode.ANYWHERE));
					}	
					trainTravelRequestQuotations=criteria1.list();
					for (BusTravelRequestQuotation trainTravelRequestQuotation : trainTravelRequestQuotations) {
						orderRowIds.add(trainTravelRequestQuotation.getId());
					}
					reportConjunction.add(Restrictions.in("id", orderRowIds));
				}

				criteria.add(reportConjunction);
				criteria.createCriteria("orderCustomer").add(conjunctionBusOrderCustomer);	
				criteria.addOrder(Order.desc("id"));

			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);

			List<BusOrderRow> list =null;
			if(rowCount>0)
			{
				if(flightReportPage.getMaxItems()==Page.ALL_ITEMS){
					logger.info(":::: retriving all items for excel export------");

					criteria = session.createCriteria(BusOrderRow.class);
					criteria.add(reportConjunction);
					criteria.createCriteria("orderCustomer").add(conjunctionBusOrderCustomer);

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

						criteria = session.createCriteria(BusOrderRow.class);
						criteria.add(reportConjunction);
						criteria.createCriteria("orderCustomer").add(conjunctionBusOrderCustomer);	
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
					for (BusOrderRow busOrderRow :list)
					{
						ReportData reportData=new ReportData();
						//reportData.setPnr(busOrderRow.getPnr());
						//reportData.setTravelDate(DateConversion.convertDateToStringDateddMMyyyy(date));
						reportData.setTitle(busOrderRow.getOrderCustomer().getTitle());
						reportData.setFirstName(busOrderRow.getOrderCustomer().getFirstName());
						reportData.setLastName(busOrderRow.getOrderCustomer().getLastName());
						reportData.setVehicleCompanyName(busOrderRow.getBusCompanyName());
						reportData.setBookingPrice(busOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP).add(busOrderRow.getMarkUp()!=null?busOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00").add(busOrderRow.getTaxes())).setScale(2, BigDecimal.ROUND_UP));

						reportData.setEmail(busOrderRow.getOrderCustomer().getEmail());
						reportData.setStatus(busOrderRow.getStatusAction());
						reportData.setPaymentStatus(busOrderRow.getPaymentStatus());
						reportData.setOrderId(busOrderRow.getOrderId());
						reportData.setCurCode(busOrderRow.getBookingCurrency());
						reportData.setBookingDate(busOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringDateddMMyyyy(busOrderRow.getBookingDate()):"-");
						reportData.setMarkUp(busOrderRow.getMarkUp()!=null?busOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
						BigDecimal netPaybleAmount=(busOrderRow.getTotalAmount().subtract(busOrderRow.getMarkUp()).setScale(2, BigDecimal.ROUND_UP));
						reportData.setNetAmnt(netPaybleAmount.setScale(2,BigDecimal.ROUND_UP));
						reportData.setOrigin(busOrderRow.getOrigin());
						reportData.setId(busOrderRow.getId());
						reportData.setCompanyId(busOrderRow.getCompanyId());

						reportData.setBasePrice(busOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
						reportData.setConfirmationNumber(busOrderRow.getConfirmationNumber());
						reportData.setConvenienceFees(busOrderRow.getConvenienceFee().setScale(2, BigDecimal.ROUND_UP));
						reportData.setInvoiceNo(busOrderRow.getInvoiceNo());
						reportData.setManagementFee(busOrderRow.getManagementFee().setScale(2, BigDecimal.ROUND_UP));
						reportData.setOtherTaxes(busOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP));
						reportData.setProcessingFee(busOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));

						reportData.setServiceTax(busOrderRow.getServiceTax()!=null?busOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP):new BigDecimal(0).add(calculateTotalserviceTax(busOrderRow)));
						//reportData.setServiceTax(serviceTax);
						BigDecimal gstorServiceTax=new BigDecimal(0);
						if(busOrderRow.getBusOrderRowServiceTax()!=null) 
							gstorServiceTax=busOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP);
						if (busOrderRow.getBusOrderRowGstTax()!=null)  
							gstorServiceTax=busOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP);

						BigDecimal finalPrice=busOrderRow.getTotalAmount().add(gstorServiceTax).setScale(2, BigDecimal.ROUND_UP);
						//reportData.setServiceTax(gstorServiceTax);
						reportData.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));
						reportData.setTax(busOrderRow.getTaxes().setScale(2, BigDecimal.ROUND_UP));
						reportData.setPickUp(busOrderRow.getPickUp());
						reportData.setDropOff(busOrderRow.getDropOff());
						reportData.setBookingDate(busOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringDateddMMyyyy(busOrderRow.getBookingDate()):"-");
						reportData.setInvoiceDate(DateConversion.convertDateToStringToDate(busOrderRow.getCreatedAt()));
						reportData.setTravelDate(DateConversion.convertDateToStringDateddMMyyyy(busOrderRow.getTravelDate()));
						reportData.setTransactionKey(busOrderRow.getTransactionKey());
						reportData.setOrderCustomerId(busOrderRow.getOrderCustomer().getId());
						reportData.setSupplierPrice(busOrderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP));
						reportData.setUpdatedBy(busOrderRow.getUpdatedBy());

						reportData.setCreatedAt(busOrderRow.getCreatedAt());
						reportData.setDestination(busOrderRow.getDestination());
						if(busOrderRow.getTotInvoiceAmount()!=null)
							reportData.setInvoiceAmount(busOrderRow.getTotInvoiceAmount().setScale(2, BigDecimal.ROUND_UP));
						else
						   reportData.setInvoiceAmount(busOrderRow.getTotalAmount().setScale(2, BigDecimal.ROUND_UP).add(calculateTotalserviceTax(busOrderRow)).setScale(2, BigDecimal.ROUND_UP));

						if(busOrderRow.getCreatedBy()!=null){
							reportData.setCreatedBy(busOrderRow.getCreatedBy().replace("+", " "));	
						}
						else{
							reportData.setCreatedBy(busOrderRow.getCreatedBy());	
						}

						reportData.setAgencyUsername(busOrderRow.getCreatedBy());
						reportData.setDescription(busOrderRow.getRemarks());
						reportData.setUserId(busOrderRow.getUserId());
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


	public BusOrderRow updateBusOrderRowDetail(BusOrderRow busOrderRow){
		BusOrderRow newBusOrderRow = null;
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			newBusOrderRow=(BusOrderRow) session.get(BusOrderRow.class,busOrderRow.getId());
			newBusOrderRow.setInvoiceNo(busOrderRow.getInvoiceNo());
			newBusOrderRow.setOrderId(busOrderRow.getOrderId());
			newBusOrderRow.getOrderCustomer().setOrderId(busOrderRow.getOrderId());//added by basha
			session.update(newBusOrderRow);
			tx.commit();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  newBusOrderRow;
	}

	public BusOrderRow updateBusOrderRowDetailPaymentStatus(BusOrderRow busOrderRow){
		BusOrderRow newBusOrderRow = null;
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			newBusOrderRow=(BusOrderRow) session.get(BusOrderRow.class,busOrderRow.getId());
			newBusOrderRow.setPaymentStatus(busOrderRow.getPaymentStatus());
			session.update(newBusOrderRow);
			tx.commit();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  newBusOrderRow;
	}
	public FlightReportPage getBusOrders(FlightReportPage commonReportPage) {
		List<ReportData>  reportData_list=new ArrayList<ReportData>();
		int availablePages = 0;
		int availableItems = 0;
		Session session = null;
		FlightOrderDao flightOrderDao = new FlightOrderDao();
		try{
			//2017-03-15
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(BusOrderRow.class);
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


				if(flightReportFilter.getPaxName()!= null  && !flightReportFilter.getPaxName().equalsIgnoreCase(""))
				{ 
					reportConjunction.add(Restrictions.like("empName", flightReportFilter.getPaxName(), MatchMode.ANYWHERE));
					logger.info("##########PaxName added----"+flightReportFilter.getPaxName());
				}

				if(flightReportFilter.getConfirmationNo() != null  && !flightReportFilter.getConfirmationNo().equalsIgnoreCase(""))
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

						reportConjunction.add(Restrictions.lt("travelDate", date));
						logger.info("##########getDateFilterCheckIn date max added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########getDateFilterCheckIn date max format exception---"+ex.getMessage());

					}
				}
				criteria.add(reportConjunction);
				criteria.addOrder(Order.desc("id"));
				criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);
				//criteria.createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);	

			}

			List<BusTravelRequestQuotation>   trainTravelRequestQuotations=new ArrayList<>();
			List<Long>   orderRowIds=new ArrayList<>();
			Criteria criteria1 = session.createCriteria(BusTravelRequestQuotation.class);
			if(flightReportFilter.getFromLocation() != null && !flightReportFilter.getFromLocation().equalsIgnoreCase("") || flightReportFilter.getToLocation() != null && !flightReportFilter.getToLocation().equalsIgnoreCase("")){
				if(flightReportFilter.getFromLocation() != null && !flightReportFilter.getFromLocation().equalsIgnoreCase("")){
					criteria1.add(Restrictions.like("pickUp", flightReportFilter.getFromLocation(), MatchMode.ANYWHERE));
				}
				if(flightReportFilter.getToLocation() != null && !flightReportFilter.getToLocation().equalsIgnoreCase("")){
					criteria1.add(Restrictions.like("dropOff", flightReportFilter.getToLocation(), MatchMode.ANYWHERE));
				}	
				trainTravelRequestQuotations=criteria1.list();
				for (BusTravelRequestQuotation trainTravelRequestQuotation : trainTravelRequestQuotations) {
					orderRowIds.add(trainTravelRequestQuotation.getId());
				}
				reportConjunction.add(Restrictions.in("id", orderRowIds));
			}




			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);

			List<BusOrderRow> list =null;
			if(rowCount>0)
			{
				if(commonReportPage.getMaxItems()==Page.ALL_ITEMS){
					logger.info(":::: retriving all items for excel export------");

					criteria = session.createCriteria(BusOrderRow.class);
					criteria.add(reportConjunction);
					criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);
					//criteria.createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);
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

						criteria = session.createCriteria(BusOrderRow.class);
						criteria.add(reportConjunction);
						criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);
						//criteria.createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);	
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
					for (BusOrderRow busOrderRow :list)
					{
						ReportData reportData=new ReportData();
						reportData=buildReportData(busOrderRow, reportData);

						reportData_list.add(reportData);
					}					
					commonReportPage.setItems(reportData_list);
					if((flightReportFilter.getEmail() != null && !flightReportFilter.getEmail().equals("")))
					{								
						reportData_list = new ArrayList<ReportData>();
						for (ReportData hotelReport : commonReportPage.getItems()) {
							if(flightReportFilter.getEmail().equalsIgnoreCase(hotelReport.getEmail())){
								reportData_list.add(hotelReport);
							}
						}	
						commonReportPage.setItems(reportData_list);
						if(commonReportPage.isPagination())
						{
							availableItems = commonReportPage.getItems().size();
							availablePages = (availableItems % commonReportPage.getMaxItems() == 0)?(availableItems / commonReportPage.getMaxItems()):((availableItems / commonReportPage.getMaxItems()) + 1);
							commonReportPage.setAvailableItems(availableItems);
							commonReportPage.setAvailablePages(availablePages);
						} 
					} 

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

	private ReportData buildReportData(
			com.lintas.admin.bus.model.BusOrderRow busOrderRow,
			ReportData reportData) {
		//reportData.setPnr(busOrderRow.getPnr());
		reportData.setFirstName(busOrderRow.getOrderCustomer().getFirstName());
		reportData.setLastName(busOrderRow.getOrderCustomer().getLastName());
		reportData.setTitle(busOrderRow.getOrderCustomer().getTitle());
		reportData.setMarkUp(busOrderRow.getMarkUp().setScale(2,BigDecimal.ROUND_UP));
		//		reportData.setInvoiceAmount(busOrderRow.getTotalAmount().add(busOrderRow.getMarkUp()!=null?busOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00")).add(calculateTotalserviceTax(busOrderRow)));
		reportData.setInvoiceAmount(busOrderRow.getTotalAmount().setScale(2, BigDecimal.ROUND_UP).add(calculateTotalserviceTax(busOrderRow)).setScale(2, BigDecimal.ROUND_UP));

		reportData.setBookingDate(busOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringDateddMMyyyy(busOrderRow.getBookingDate()):"-");
		reportData.setCreatedAt(busOrderRow.getCreatedAt());
		//reportData.setTicketType(busOrderRow.getTicketType());
		reportData.setSupplierName(busOrderRow.getSupplierName());
		reportData.setVehicleCompanyName(busOrderRow.getBusCompanyName());
		reportData.setOrigin(busOrderRow.getOrigin());
		reportData.setDestination(busOrderRow.getDestination());


		//		reportData.setVehicleCompanyName(busOrderRow.getBusCompanyName());

		//reportData.setFinalPrice(busOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));

		BigDecimal gstorServiceTax=new BigDecimal(0);
		if(busOrderRow.getBusOrderRowServiceTax()!=null) 
			gstorServiceTax=busOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP);
		if (busOrderRow.getBusOrderRowGstTax()!=null)  
			gstorServiceTax=busOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP);
		BigDecimal finalPrice=busOrderRow.getTotalAmount().add(gstorServiceTax).setScale(2, BigDecimal.ROUND_UP);
		reportData.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));

		reportData.setTotal(busOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
		reportData.setGstTax(busOrderRow.getTotalGstTax().setScale(2,BigDecimal.ROUND_UP));

		reportData.setBookingPrice(busOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP).add(busOrderRow.getMarkUp()!=null?busOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00")).add(busOrderRow.getTaxes()!=null?busOrderRow.getTaxes().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00")));
		reportData.setEmail(busOrderRow.getOrderCustomer().getEmail());
		reportData.setStatus(busOrderRow.getStatusAction());
		reportData.setPaymentStatus(busOrderRow.getPaymentStatus());
		reportData.setOrderId(busOrderRow.getOrderId());
		reportData.setCurCode(busOrderRow.getBookingCurrency());
		reportData.setBookingDate(busOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringDateddMMyyyy(busOrderRow.getBookingDate()):"-");
		reportData.setTravelDate(DateConversion.convertDateToStringDateddMMyyyy(busOrderRow.getTravelDate()));
		//reportData.setDepartureDate(DateConversion.getBluestarDate(busOrderRow.getDepartureDate()));
		reportData.setMarkUp(busOrderRow.getMarkUp()!=null?busOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
		BigDecimal netPaybleAmount=(busOrderRow.getTotalAmount().subtract(busOrderRow.getMarkUp()).setScale(2, BigDecimal.ROUND_UP));
		reportData.setNetAmnt(netPaybleAmount.setScale(2,BigDecimal.ROUND_UP));
		//		reportData.setOrigin(busOrderRow.getLocation());
		reportData.setId(busOrderRow.getId());
		reportData.setCompanyId(busOrderRow.getCompanyId());

		reportData.setBasePrice(busOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
		reportData.setConfirmationNumber(busOrderRow.getConfirmationNumber());
		reportData.setConvenienceFees(busOrderRow.getConvenienceFee().setScale(2, BigDecimal.ROUND_UP));
		reportData.setInvoiceNo(busOrderRow.getInvoiceNo());
		reportData.setManagementFee(busOrderRow.getManagementFee().setScale(2, BigDecimal.ROUND_UP));
		reportData.setOtherTaxes(busOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP));
		reportData.setProcessingFee(busOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
		reportData.setServiceTax(busOrderRow.getServiceTax()!=null?busOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP):new BigDecimal(0));
		reportData.setTax(busOrderRow.getTaxes().setScale(2, BigDecimal.ROUND_UP));
		reportData.setTravelDate1(busOrderRow.getTravelDate());
		reportData.setTransactionKey(busOrderRow.getTransactionKey());
		reportData.setOrderCustomerId(busOrderRow.getOrderCustomer().getId());
		reportData.setSupplierPrice(busOrderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP));
		reportData.setUpdatedBy(busOrderRow.getUpdatedBy());
		if(busOrderRow.getProcessingFees()!=null)
			reportData.setConvenienceFees(busOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
		else
			reportData.setConvenienceFees(new BigDecimal(0));

		reportData.setStatus(busOrderRow.getStatusAction());
		reportData.setPaymentStatus(busOrderRow.getPaymentStatus());
		reportData.setOrderRequested(busOrderRow.isOrderRequested());
		reportData.setBookingMode(busOrderRow.getBookingMode());
		reportData.setCreditNoteIssued(busOrderRow.isCreditNoteIssued());
		reportData.setOrderUpdated(busOrderRow.isOrderUpdated());

		if(busOrderRow.getCreatedBy()!=null){
			reportData.setCreatedBy(busOrderRow.getCreatedBy().replace("+", " "));	
		}
		else{
			reportData.setCreatedBy(busOrderRow.getCreatedBy());	
		}

		/*reportData.setAgencyUsername(busOrderRow.getCreatedBy());*/
		reportData.setDescription(busOrderRow.getRemarks());
		reportData.setUserId(busOrderRow.getUserId());
		reportData.setUserId(busOrderRow.getUserId());
		reportData.setStatus(busOrderRow.getStatusAction());
		reportData.setPaymentStatus(busOrderRow.getPaymentStatus());
		reportData.setOrderRequested(busOrderRow.isOrderRequested());
		reportData.setBookingMode(busOrderRow.getBookingMode());
		reportData.setCancelationMode(busOrderRow.getCancelMode());
		reportData.setCreditNoteIssued(busOrderRow.isCreditNoteIssued());
		reportData.setOrderUpdated(busOrderRow.isOrderUpdated());
		User user= new UserDAO().getSalesPersonName(busOrderRow.getUserId());

		if(user!=null) 
			reportData.setSalesPersonName(user.getUsername());
		else 
			reportData.setSalesPersonName("-");

		return reportData;

	}
	public static BigDecimal  calculateTotalserviceTax(BusOrderRow  flightOrderRow){
		BigDecimal convenienceFeePerRoom = new BigDecimal("0");
		BigDecimal managementFeePerRoom = new BigDecimal("0");
		if(flightOrderRow.getBusOrderRowServiceTax() !=null && flightOrderRow.getBusOrderRowServiceTax().getManagementFee()!=null)
			managementFeePerRoom= flightOrderRow.getBusOrderRowServiceTax().getManagementFee();
		if(flightOrderRow.getBusOrderRowServiceTax() !=null && flightOrderRow.getBusOrderRowServiceTax().getConvenienceFee()!=null)
			convenienceFeePerRoom= flightOrderRow.getBusOrderRowServiceTax().getConvenienceFee();
		if(flightOrderRow.getBusOrderRowGstTax() !=null && flightOrderRow.getBusOrderRowGstTax().getManagementFee()!=null)
			managementFeePerRoom= flightOrderRow.getBusOrderRowGstTax().getManagementFee();
		if(flightOrderRow.getBusOrderRowGstTax() !=null && flightOrderRow.getBusOrderRowGstTax().getConvenienceFee()!=null)
			convenienceFeePerRoom= flightOrderRow.getBusOrderRowGstTax().getConvenienceFee();

		BigDecimal  serviceTax= flightOrderRow.getServiceTax()!=null?flightOrderRow.getServiceTax().setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);
		BigDecimal  gstTax=flightOrderRow.getTotalGstTax()!=null?flightOrderRow.getTotalGstTax().setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);

		BigDecimal  totalAmount=serviceTax.add(managementFeePerRoom).add(convenienceFeePerRoom).add(gstTax);
		return totalAmount.setScale(2,BigDecimal.ROUND_UP);
	}

	public ReportData getReportDetailsByRowId(Long id) {
		// TODO Auto-generated method stub
		ReportData reportData=new ReportData();

		try{
			String ordersSql= "from BusOrderRow row where row.id=:id";
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(ordersSql);
			query.setParameter("id", id);
			BusOrderRow carOrderRow = (BusOrderRow) query.uniqueResult();
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
	public BusCreditNote busCreditNoteData(Long id, int companyId) {
		String sql="from BusCreditNote cn where cn.rowId=:row_id and cn.companyId=:companyid";
		BusCreditNote creditNote=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("row_id",id.intValue());
			query.setParameter("companyid",String.valueOf(companyId)) ;
			creditNote=(BusCreditNote)query.uniqueResult();
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

	public List<BusCreditNote> loadCreditNoteListById(Long id) {
		// TODO Auto-generated method stub
		String sql="from BusCreditNote cn where cn.rowId=:row_id";
		List<BusCreditNote> list=new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("row_id",id.intValue());
			List<BusCreditNote> creditList=query.list();
			if(creditList!=null &&creditList.size()>0){
				for(BusCreditNote note:creditList){
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

	public BusOrderRow getBusOrderRowDetail(String orderId) {
		BusOrderRow busOrderRow = null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(BusOrderRow.class);
			criteria.add(Restrictions.eq("orderId", orderId));
			busOrderRow = (BusOrderRow) criteria.uniqueResult();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return busOrderRow;

	}
	public BusOrderRow getBusOrderRowDetail(Long id) {
		BusOrderRow busOrderRow = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from BusOrderRow fotd where fotd.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			busOrderRow = (BusOrderRow) query.uniqueResult();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return busOrderRow;

	}
	public static BigDecimal  calculateTotalGSTTax(BusOrderRow  busOrderRow){
		//BigDecimal convenienceFeePerRoom = new BigDecimal("0");
		//BigDecimal managementFeePerRoom = new BigDecimal("0");
		BigDecimal totalGSTPerRoom = new BigDecimal("0");
		/*if(busOrderRow.getBusOrderRowGstTax() !=null && busOrderRow.getBusOrderRowGstTax().getManagementFee()!=null)
			managementFeePerRoom= busOrderRow.getBusOrderRowGstTax().getManagementFee();
		if(busOrderRow.getBusOrderRowGstTax() !=null && busOrderRow.getBusOrderRowGstTax().getConvenienceFee()!=null)
			convenienceFeePerRoom= busOrderRow.getBusOrderRowGstTax().getConvenienceFee();*/
		BigDecimal  totGst= busOrderRow.getBusOrderRowGstTax().getTotalGst()!=null?busOrderRow.getBusOrderRowGstTax().getTotalGst().setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);
		BigDecimal  totalAmount=totGst.add(totalGSTPerRoom);
		return totalAmount.setScale(2,BigDecimal.ROUND_UP);
	}
	public List<BusOrderRow>  getBusBookingForIds(List<String> companyUserId ) {
		List<BusOrderRow> busOrderRows=null;
		BigDecimal finalPrice=new BigDecimal("0");
 		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr=session.createCriteria(BusOrderRow.class);
			cr.add(Restrictions.in("companyId", companyUserId));
			busOrderRows=cr.list();
			 
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
 		return busOrderRows;
	}

}
