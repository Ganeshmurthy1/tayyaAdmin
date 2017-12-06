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

import com.admin.common.quotation.dao.TrainTravelRequestDao;
import com.admin.common.quotation.model.TrainTravelRequestQuotation;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightReportPage;
import com.isl.admin.page.Page;
import com.lintas.action.CreditNote.modal.TrainCreditNote;
import com.lintas.admin.common.model.FlightAndHotelBookApiResponse;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.model.User;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

/**
 * @author BASHA
 *
 */
public class TrainOrderDao {

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TrainOrderDao.class);
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
	List<TrainOrderRow> trainOrderRowList=new ArrayList<TrainOrderRow>();

	public  FlightReportPage getTrainReports(FlightReportPage flightReportPage,String showType){
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
			Criteria criteria = session.createCriteria(TrainOrderRow.class);
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


				if(flightReportFilter.getPaxName()!= null  && !flightReportFilter.getPaxName().equalsIgnoreCase(""))
				{ 
					reportConjunction.add(Restrictions.like("empNmae", flightReportFilter.getPaxName(), MatchMode.ANYWHERE));
					logger.info("##########PaxName added----"+flightReportFilter.getPaxName());
				}

				if(flightReportFilter.getConfirmationNo() != null  && !flightReportFilter.getConfirmationNo().equalsIgnoreCase(""))
				{
					reportConjunction.add(Restrictions.like("confirmationNumber", flightReportFilter.getConfirmationNo(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getConfirmationNo());
				}	
				if(flightReportFilter.getOrderId()!= null  && !flightReportFilter.getOrderId().equalsIgnoreCase(""))
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
				/*if(flightReportFilter.getLocation() != null && !flightReportFilter.getLocation().equalsIgnoreCase(""))
				{
					reportConjunction.add(Restrictions.like("location", flightReportFilter.getLocation(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getLocation());
				}	*/

				if(flightReportFilter.getDateFilterInvoice().getDtStart() != null && flightReportFilter.getDateFilterInvoice().getDtEnd() != null && !flightReportFilter.getDateFilterInvoice().getDtStart().equals("") && !flightReportFilter.getDateFilterInvoice().getDtEnd().equals("") )
				{
					//					reportConjunction.add(Restrictions.ge("carBookingDate", flightReportFilter.getDateFilterBooking().getDtStart()));
					//					reportConjunction.add(Restrictions.le("carBookingDate", flightReportFilter.getDateFilterBooking().getDtEnd()));

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
				/*if(flightReportFilter.getCustomerId() != null)

				{
					reportConjunction.add(Restrictions.eq("id", flightReportFilter.getCustomerId(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getCustomerId());
				}	*/


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

				if(flightReportFilter.getUserId()>0)
					reportConjunction.add(Restrictions.like("userId",  String.valueOf(flightReportFilter.getUserId())));

				if(flightReportFilter.getCompanyId()>0)
					reportConjunction.add(Restrictions.like("companyId", String.valueOf(flightReportFilter.getCompanyId())));

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
					if(showType!=null && showType.equalsIgnoreCase("trainconfirm")){
						///reportConjunction.add(Restrictions.in("companyId",companyIdList));
						reportConjunction.add(Restrictions.eq("statusAction","Confirmed"));
					}
					else if(showType!=null && showType.equalsIgnoreCase("trainpaymentfailed")){
						//reportConjunction.add(Restrictions.in("companyId",companyIdList));
						reportConjunction.add(Restrictions.eq("paymentStatus","Failed"));
					}	
					else if(showType!=null && showType.equalsIgnoreCase("trainpayment")){
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

				List<TrainTravelRequestQuotation>   trainTravelRequestQuotations=new ArrayList<>();
				List<Long>   orderRowIds=new ArrayList<>();
				Criteria criteria1 = session.createCriteria(TrainTravelRequestQuotation.class);
				if(flightReportFilter.getFromLocation() != null && !flightReportFilter.getFromLocation().equalsIgnoreCase("") || flightReportFilter.getToLocation() != null && !flightReportFilter.getToLocation().equalsIgnoreCase("")){
					if(flightReportFilter.getFromLocation() != null && !flightReportFilter.getFromLocation().equalsIgnoreCase("")){
						criteria1.add(Restrictions.like("Fromlocation", flightReportFilter.getFromLocation(), MatchMode.ANYWHERE));
					}
					if(flightReportFilter.getToLocation() != null && !flightReportFilter.getToLocation().equalsIgnoreCase("")){
						criteria1.add(Restrictions.like("Tolocation", flightReportFilter.getToLocation(), MatchMode.ANYWHERE));
					}	
					trainTravelRequestQuotations=criteria1.list();
					for (TrainTravelRequestQuotation trainTravelRequestQuotation : trainTravelRequestQuotations) {
						orderRowIds.add(trainTravelRequestQuotation.getId());
					}
					reportConjunction.add(Restrictions.in("id", orderRowIds));
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);	
				criteria.addOrder(Order.desc("id"));

				//criteria.createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);	

			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);

			List<TrainOrderRow> list =null;
			if(rowCount>0)
			{
				if(flightReportPage.getMaxItems()==Page.ALL_ITEMS){
					logger.info(":::: retriving all items for excel export------");

					criteria = session.createCriteria(TrainOrderRow.class);
					criteria.add(reportConjunction);
					criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);	
					//criteria.createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);
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

						criteria = session.createCriteria(TrainOrderRow.class);
						criteria.add(reportConjunction);
						criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);	
						//criteria.createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);	
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
					for (TrainOrderRow trainOrderRow :list)
					{
						ReportData reportData=new ReportData();
						//reportData.setPnr(trainOrderRow.getPnr());
						reportData.setFirstName(trainOrderRow.getOrderCustomer().getFirstName());
						reportData.setLastName(trainOrderRow.getOrderCustomer().getLastName());
						reportData.setTitle(trainOrderRow.getOrderCustomer().getTitle());
						reportData.setMarkUp(trainOrderRow.getMarkUp().setScale(2,BigDecimal.ROUND_UP));
						reportData.setInvoiceAmount(trainOrderRow.getTotalAmount().add(trainOrderRow.getServiceTax()).setScale(2,BigDecimal.ROUND_UP));
						//reportData.setFinalPrice(trainOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP).add(calculateTotalserviceTax(trainOrderRow).add(trainOrderRow.getMarkUp()!=null?trainOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00").add(trainOrderRow.getTaxes())).setScale(2, BigDecimal.ROUND_UP)));
						reportData.setBookingPrice(trainOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP).add(trainOrderRow.getMarkUp()!=null?trainOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00").add(trainOrderRow.getTaxes())).setScale(2, BigDecimal.ROUND_UP));
						BigDecimal gstorServiceTax=new BigDecimal(0);
						if(trainOrderRow.getTrainOrderRowServiceTax()!=null) 
							gstorServiceTax=trainOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP);
						if (trainOrderRow.getTrainOrderRowGstTax()!=null)  
							gstorServiceTax=trainOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP);
						BigDecimal finalPrice=trainOrderRow.getTotalAmount().add(gstorServiceTax).setScale(2, BigDecimal.ROUND_UP);
						reportData.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));
						reportData.setTotal(trainOrderRow.getTotalAmount().setScale(2, BigDecimal.ROUND_UP));
						reportData.setCreatedAt(trainOrderRow.getCreatedAt());
						reportData.setTicketType(trainOrderRow.getTicketType());
						reportData.setSupplierName(trainOrderRow.getSupplierName());
						//							reportData.setVehicleCompanyName(trainOrderRow.getTrainCompanyName());
						reportData.setFinalPrice(trainOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
						reportData.setEmail(trainOrderRow.getOrderCustomer().getEmail());
						reportData.setStatus(trainOrderRow.getStatusAction());
						reportData.setPaymentStatus(trainOrderRow.getPaymentStatus());
						reportData.setOrderId(trainOrderRow.getOrderId());
						reportData.setCurCode(trainOrderRow.getBookingCurrency());
						reportData.setBookingDate(trainOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringDateddMMyyyy(trainOrderRow.getBookingDate()):"-");
						//reportData.setDepartureDate(DateConversion.getBluestarDate(trainOrderRow.getDepartureDate()));
						reportData.setMarkUp(trainOrderRow.getMarkUp()!=null?trainOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
						BigDecimal netPaybleAmount=(trainOrderRow.getTotalAmount().subtract(trainOrderRow.getMarkUp()).setScale(2, BigDecimal.ROUND_UP));
						reportData.setNetAmnt(netPaybleAmount.setScale(2,BigDecimal.ROUND_UP));
						//							reportData.setOrigin(trainOrderRow.getLocation());
						reportData.setId(trainOrderRow.getId());
						reportData.setCompanyId(trainOrderRow.getCompanyId());
						reportData.setTicket(trainOrderRow.getTicketType());
						reportData.setBasePrice(trainOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
						reportData.setConfirmationNumber(trainOrderRow.getConfirmationNumber());
						reportData.setConvenienceFees(trainOrderRow.getConvenienceFee().setScale(2, BigDecimal.ROUND_UP));
						reportData.setInvoiceNo(trainOrderRow.getInvoiceNo());
						reportData.setManagementFee(trainOrderRow.getManagementFee().setScale(2, BigDecimal.ROUND_UP));
						reportData.setOtherTaxes(trainOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP));
						reportData.setProcessingFee(trainOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
						reportData.setServiceTax(trainOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP));
						reportData.setTax(trainOrderRow.getTaxes().setScale(2, BigDecimal.ROUND_UP));
						reportData.setTravelDate(trainOrderRow.getTravelDate()!=null?DateConversion.convertDateToStringDateddMMyyyy(trainOrderRow.getTravelDate()):"-");
						reportData.setInvoiceDate(DateConversion.convertDateToStringToDate(trainOrderRow.getCreatedAt()));
						reportData.setTransactionKey(trainOrderRow.getTransactionKey());
						reportData.setOrderCustomerId(trainOrderRow.getOrderCustomer().getId());
						reportData.setSupplierPrice(trainOrderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP));
						reportData.setUpdatedBy(trainOrderRow.getUpdatedBy());

						try{
							TrainTravelRequestQuotation quotation=new TrainTravelRequestDao().getTrainQuotationDetails(trainOrderRow.getId());
							reportData.setOrigin(quotation.getFromlocation());
							reportData.setDestination(quotation.getTolocation());
							reportData.setVehicleCompanyName(quotation.getTrainNumber());
						}catch (Exception e) {
							// TODO: handle exception
						}


						if(trainOrderRow.getCreatedBy()!=null){
							reportData.setCreatedBy(trainOrderRow.getCreatedBy().replace("+", " "));	
						}
						else{
							reportData.setCreatedBy(trainOrderRow.getCreatedBy());	
						}

						reportData.setAgencyUsername(trainOrderRow.getCreatedBy());
						reportData.setDescription(trainOrderRow.getRemarks());
						reportData.setUserId(trainOrderRow.getUserId());
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

	public TrainOrderRow updateTrainOrderRowDetail(TrainOrderRow trainOrderRow){
		TrainOrderRow newTrainOrderRow = null;
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			newTrainOrderRow=(TrainOrderRow) session.get(TrainOrderRow.class,trainOrderRow.getId());
			newTrainOrderRow.setInvoiceNo(trainOrderRow.getInvoiceNo());
			newTrainOrderRow.setOrderId(trainOrderRow.getOrderId());
			newTrainOrderRow.getOrderCustomer().setOrderId(trainOrderRow.getOrderId());//added by basha
			session.update(newTrainOrderRow);
			tx.commit();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  newTrainOrderRow;
	}

	public TrainOrderRow updateTrainOrderRowDetailPaymentStatus(TrainOrderRow trainOrderRow){
		TrainOrderRow newTrainOrderRow = null;
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			newTrainOrderRow=(TrainOrderRow) session.get(TrainOrderRow.class,trainOrderRow.getId());
			newTrainOrderRow.setPaymentStatus(trainOrderRow.getPaymentStatus());
			session.update(newTrainOrderRow);
			tx.commit();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  newTrainOrderRow;
	}

	public FlightReportPage getTrainOrders(FlightReportPage commonReportPage) {
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
			Criteria criteria = session.createCriteria(TrainOrderRow.class);

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
				/*if(flightReportFilter.getLocation() != null && !flightReportFilter.getLocation().equalsIgnoreCase(""))
				{
					Quotationcriteria.add(Restrictions.like("Fromlocation", flightReportFilter.getLocation(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getLocation());
				}	*/

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


				/*if(flightReportFilter.getCustomerId() != null)

				{
					reportConjunction.add(Restrictions.eq("id", flightReportFilter.getCustomerId(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getCustomerId());
				}	*/


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

					reportConjunction.add(Restrictions.ge("bookingDate", flightReportFilter.getDateFilterBooking().getDtStart()));
					reportConjunction.add(Restrictions.le("bookingDate", flightReportFilter.getDateFilterBooking().getDtEnd()));

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

				////////////////////////////////////////////////////////////////////////////////////////
				List<TrainTravelRequestQuotation>   trainTravelRequestQuotations=new ArrayList<>();
				List<Long>   orderRowIds=new ArrayList<>();
				Criteria criteria1 = session.createCriteria(TrainTravelRequestQuotation.class);
				if(flightReportFilter.getFromLocation() != null && !flightReportFilter.getFromLocation().equalsIgnoreCase("") || flightReportFilter.getToLocation() != null && !flightReportFilter.getToLocation().equalsIgnoreCase("")){
					if(flightReportFilter.getFromLocation() != null && !flightReportFilter.getFromLocation().equalsIgnoreCase("")){
						criteria1.add(Restrictions.like("Fromlocation", flightReportFilter.getFromLocation(), MatchMode.ANYWHERE));
					}
					if(flightReportFilter.getToLocation() != null && !flightReportFilter.getToLocation().equalsIgnoreCase("")){
						criteria1.add(Restrictions.like("Tolocation", flightReportFilter.getToLocation(), MatchMode.ANYWHERE));
					}	
					trainTravelRequestQuotations=criteria1.list();
					for (TrainTravelRequestQuotation trainTravelRequestQuotation : trainTravelRequestQuotations) {
						orderRowIds.add(trainTravelRequestQuotation.getId());
					}
					reportConjunction.add(Restrictions.in("id", orderRowIds));
				}
				//////////////////////////////////////////////////////////


				criteria.add(reportConjunction);
				criteria.addOrder(Order.desc("id"));
				criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);
				//criteria.createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);	

			}


			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);


			List<TrainOrderRow> list =null;
			if(rowCount>0)
			{
				if(commonReportPage.getMaxItems()==Page.ALL_ITEMS){
					logger.info(":::: retriving all items for excel export------");

					criteria = session.createCriteria(TrainOrderRow.class);
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

						criteria = session.createCriteria(TrainOrderRow.class);
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
					for (TrainOrderRow trainOrderRow :list)
					{
						ReportData reportData=new ReportData();
						reportData=buildReportData(trainOrderRow, reportData);
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

	public ReportData getReportDetailsByRowId(Long id) {
		// TODO Auto-generated method stub
		ReportData reportData=new ReportData();

		try{
			String ordersSql= "from TrainOrderRow row where row.id=:id";
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(ordersSql);
			query.setParameter("id", id);
			TrainOrderRow trainOrderRow = (TrainOrderRow) query.uniqueResult();
			if(trainOrderRow!=null){
				buildReportData(trainOrderRow, reportData);
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
	private ReportData buildReportData(TrainOrderRow trainOrderRow,ReportData reportData) {
		//reportData.setPnr(trainOrderRow.getPnr());
		reportData.setFirstName(trainOrderRow.getOrderCustomer().getFirstName());
		reportData.setLastName(trainOrderRow.getOrderCustomer().getLastName());
		reportData.setTitle(trainOrderRow.getOrderCustomer().getTitle());
		reportData.setMarkUp(trainOrderRow.getMarkUp().setScale(2,BigDecimal.ROUND_UP));
		reportData.setInvoiceAmount(trainOrderRow.getTotalAmount().add(trainOrderRow.getMarkUp()!=null?trainOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00")).add(calculateTotalserviceTax(trainOrderRow)).setScale(2, BigDecimal.ROUND_UP));
		reportData.setBookingPrice(trainOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP).add(trainOrderRow.getMarkUp()!=null?trainOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00")).add(trainOrderRow.getTaxes()!=null?trainOrderRow.getTaxes().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00")));
		reportData.setCreatedAt(trainOrderRow.getCreatedAt());
		reportData.setTicketType(trainOrderRow.getTicketType());
		reportData.setSupplierName(trainOrderRow.getSupplierName());
		//							reportData.setVehicleCompanyName(trainOrderRow.getTrainCompanyName());
		//reportData.setFinalPrice(trainOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));

		BigDecimal gstorServiceTax=new BigDecimal(0);
		if(trainOrderRow.getTrainOrderRowServiceTax()!=null) 
			gstorServiceTax=trainOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP);
		if (trainOrderRow.getTrainOrderRowGstTax()!=null)  
			gstorServiceTax=trainOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP);
		BigDecimal finalPrice=trainOrderRow.getTotalAmount().add(gstorServiceTax).setScale(2, BigDecimal.ROUND_UP);
		reportData.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));
		reportData.setTotal(trainOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
		reportData.setEmail(trainOrderRow.getOrderCustomer().getEmail());
		reportData.setStatus(trainOrderRow.getStatusAction());
		reportData.setPaymentStatus(trainOrderRow.getPaymentStatus());
		reportData.setOrderId(trainOrderRow.getOrderId());
		reportData.setCurCode(trainOrderRow.getBookingCurrency());
		reportData.setBookingDate(trainOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringDateddMMyyyy(trainOrderRow.getBookingDate()):"-");
		//reportData.setDepartureDate(DateConversion.getBluestarDate(trainOrderRow.getDepartureDate()));
		reportData.setMarkUp(trainOrderRow.getMarkUp()!=null?trainOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
		BigDecimal netPaybleAmount=(trainOrderRow.getTotalAmount().subtract(trainOrderRow.getMarkUp()).setScale(2, BigDecimal.ROUND_UP));
		reportData.setNetAmnt(netPaybleAmount.setScale(2,BigDecimal.ROUND_UP));
		//							reportData.setOrigin(trainOrderRow.getLocation());
		reportData.setId(trainOrderRow.getId());
		reportData.setCompanyId(trainOrderRow.getCompanyId());
		if(trainOrderRow.getProcessingFees()!=null)
			reportData.setConvenienceFees(trainOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
		else
			reportData.setConvenienceFees(new BigDecimal(0));
		reportData.setBasePrice(trainOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
		reportData.setConfirmationNumber(trainOrderRow.getConfirmationNumber());
		reportData.setConvenienceFees(trainOrderRow.getConvenienceFee().setScale(2, BigDecimal.ROUND_UP));
		reportData.setInvoiceNo(trainOrderRow.getInvoiceNo());
		if(trainOrderRow.getTicketType().equalsIgnoreCase("normal"))
			reportData.setManagementFee(trainOrderRow.getManagementFee().setScale(2, BigDecimal.ROUND_UP));
		else
			reportData.setManagementFee(trainOrderRow.getManagementFeeTatkal().setScale(2, BigDecimal.ROUND_UP));
		reportData.setOtherTaxes(trainOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP));
		reportData.setProcessingFee(trainOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
		reportData.setServiceTax(trainOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP));
		reportData.setTax(trainOrderRow.getTaxes().setScale(2, BigDecimal.ROUND_UP));
		reportData.setTravelDate(trainOrderRow.getTravelDate()!=null?DateConversion.convertDateToStringDateddMMyyyy(trainOrderRow.getTravelDate()):"-");
		reportData.setTransactionKey(trainOrderRow.getTransactionKey());
		reportData.setOrderCustomerId(trainOrderRow.getOrderCustomer().getId());
		reportData.setSupplierPrice(trainOrderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP));
		reportData.setUpdatedBy(trainOrderRow.getUpdatedBy());

		if(trainOrderRow.getCreatedBy()!=null){
			reportData.setCreatedBy(trainOrderRow.getCreatedBy().replace("+", " "));	
		}
		else{
			reportData.setCreatedBy(trainOrderRow.getCreatedBy());	
		}

		/*reportData.setAgencyUsername(trainOrderRow.getCreatedBy());*/
		reportData.setDescription(trainOrderRow.getRemarks());
		reportData.setUserId(trainOrderRow.getUserId());
		reportData.setStatus(trainOrderRow.getStatusAction());
		reportData.setPaymentStatus(trainOrderRow.getPaymentStatus());
		reportData.setOrderRequested(trainOrderRow.isOrderRequested());
		reportData.setBookingMode(trainOrderRow.getBookingMode());
		reportData.setCreditNoteIssued(trainOrderRow.isCreditNoteIssued());
		reportData.setOrderUpdated(trainOrderRow.isOrderUpdated());
		try{
			TrainTravelRequestQuotation quotation=new TrainTravelRequestDao().getTrainQuotationDetails(trainOrderRow.getId());
			reportData.setOrigin(quotation.getFromlocation());
			reportData.setDestination(quotation.getTolocation());
			reportData.setVehicleCompanyName(quotation.getTrainNumber());
		}catch (Exception e) {
			// TODO: handle exception
		}


		User user= new UserDAO().getSalesPersonName(trainOrderRow.getUserId());

		if(user!=null) 
			reportData.setSalesPersonName(user.getUsername());
		else 
			reportData.setSalesPersonName("-");

		return reportData;

	}

	public static BigDecimal  calculateTotalserviceTax(TrainOrderRow  trainOrderRow){
		BigDecimal convenienceFeePerRoom = new BigDecimal("0");
		BigDecimal managementFeePerRoom = new BigDecimal("0");
		if(trainOrderRow.getTrainOrderRowServiceTax() !=null && trainOrderRow.getTrainOrderRowServiceTax().getManagementFee()!=null)
			managementFeePerRoom= trainOrderRow.getTrainOrderRowServiceTax().getManagementFee();
		if(trainOrderRow.getTrainOrderRowServiceTax() !=null && trainOrderRow.getTrainOrderRowServiceTax().getConvenienceFee()!=null)
			convenienceFeePerRoom= trainOrderRow.getTrainOrderRowServiceTax().getConvenienceFee();
		if(trainOrderRow.getTrainOrderRowGstTax() !=null && trainOrderRow.getTrainOrderRowGstTax().getManagementFee()!=null)
			managementFeePerRoom= trainOrderRow.getTrainOrderRowGstTax().getManagementFee();
		if(trainOrderRow.getTrainOrderRowGstTax() !=null && trainOrderRow.getTrainOrderRowGstTax().getConvenienceFee()!=null)
			convenienceFeePerRoom= trainOrderRow.getTrainOrderRowGstTax().getConvenienceFee();

		BigDecimal  serviceTax= trainOrderRow.getServiceTax()!=null?trainOrderRow.getServiceTax().setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);
		BigDecimal  gstTax=trainOrderRow.getTotalGstTax()!=null?trainOrderRow.getTotalGstTax().setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);

		BigDecimal  totalAmount=serviceTax.add(managementFeePerRoom).add(convenienceFeePerRoom).add(gstTax);
		return totalAmount.setScale(2,BigDecimal.ROUND_UP);
	}
	public TrainCreditNote trainCreditNoteData(Long id, int companyId) {
		String sql="from TrainCreditNote cn where cn.rowId=:row_id and cn.companyId=:companyid";
		TrainCreditNote creditNote=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("row_id",id.intValue());
			query.setParameter("companyid",String.valueOf(companyId)) ;
			creditNote=(TrainCreditNote)query.uniqueResult();
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

	public List<TrainCreditNote> loadCreditNoteListById(Long id) {
		// TODO Auto-generated method stub
		String sql="from TrainCreditNote cn where cn.rowId=:row_id";
		List<TrainCreditNote> list=new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("row_id",id.intValue());
			List<TrainCreditNote> creditList=query.list();
			if(creditList!=null &&creditList.size()>0){
				for(TrainCreditNote note:creditList){
					note.setConvertDate(DateConversion.convertTimestampToStringWithoutAM(note.getOrderedAt()));
					note.setConvenienceFees(note.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP));
					note.setManagementFees(note.getManagementFees().setScale(2, BigDecimal.ROUND_UP));
					note.setCancellationFees(note.getCancellationFees().setScale(2, BigDecimal.ROUND_UP));
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

	public TrainOrderRow getTrainOrderRowDetail(String orderId) {
		/*this method for get  FlightOrderRow  using order id */
		TrainOrderRow trainOrderRow = null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(TrainOrderRow.class);
			criteria.add(Restrictions.eq("orderId", orderId));
			trainOrderRow = (TrainOrderRow) criteria.uniqueResult();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  trainOrderRow;
	}
	public TrainOrderRow getTrainOrderRowDetail(Long id) {
		/*this method for get  FlightOrderRow  using order id */
		TrainOrderRow trainOrderRow = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from TrainOrderRow fotd where fotd.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			trainOrderRow = (TrainOrderRow) query.uniqueResult();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  trainOrderRow;
	}
	
	public List<TrainOrderRow> getTrainBookingForIds(List<String> companyUserId ) {
		List<TrainOrderRow> trainOrderRows=null;
		BigDecimal finalPrice=new BigDecimal("0");
 		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr=session.createCriteria(TrainOrderRow.class);
			cr.add(Restrictions.in("companyId", companyUserId));
			trainOrderRows=cr.list();
			 
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
 		return trainOrderRows;
	}
}


