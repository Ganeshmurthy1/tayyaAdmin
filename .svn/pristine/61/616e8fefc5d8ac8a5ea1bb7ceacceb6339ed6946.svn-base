package com.lintas.admin.DAO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SimpleTimeZone;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProvider;
import com.admin.api.provider.model.ApiProviderPaymentTransaction;
import com.admin.api.provider.model.ApiProviderPaymentTransactionDetail;
import com.admin.flight.fin.sheet.model.HotelandFlightDisReportProperty;
import com.isl.admin.filter.HotelReportFilter;
import com.isl.admin.page.HotelReportPage;
import com.isl.admin.page.Page;
import com.lintas.admin.common.model.FlightAndHotelBookApiResponse;
import com.lintas.admin.common.model.HotelCreditNote;
import com.lintas.admin.common.model.HotelOrderRowCancellation;
import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.common.model.PaymentTransactionDetail;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderCancellationPolicy;
import com.lintas.admin.hotel.model.HotelOrderGuest;
import com.lintas.admin.hotel.model.HotelOrderHotelData;
import com.lintas.admin.hotel.model.HotelOrderRoomInfo;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.hotel.model.HotelReport;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.HotelOrderRowCommission;
import com.lintas.admin.model.HotelOrderRowMarkup;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.CommonUtil;
import com.lintas.utility.DateConversion;

public class HotelOrderDao {
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	FlightOrderDao flightOrderDao = new FlightOrderDao();
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelOrderDao.class);
	/* load all  company bookings from database based on their user id */
	public  HotelReportPage getCompanyHotelReports(HotelReportPage hotelReportPage,String showType,Company companySessionObj){		
		List<HotelReport>  reportData_list = new ArrayList<HotelReport>();
		int availablePages = 0;
		int availableItems = 0;
		Session session = null;
		try{
			//2016-06-28
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(HotelOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Conjunction hotelOrderHotelDataConjunction = Restrictions.conjunction();
			Conjunction orderCustomerConjunction = Restrictions.conjunction();

			// To get total row count.
			if(hotelReportPage!=null && hotelReportPage.isFilterEnabled())
			{
				HotelReportFilter hotelReportFilter = hotelReportPage.getHotelReportFilter();
				List<String> companyIdList  = new ArrayList<String>();
				companyIdList = flightOrderDao.getCompanyIdList(hotelReportFilter.getLoginCompany(), hotelReportFilter.getReportType(), hotelReportFilter.getCompanyName());
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					hotelReportPage.setAvailableItems(0);					
					hotelReportPage.setItems(new ArrayList<HotelReport>());
					return hotelReportPage;
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));	
				if(hotelReportFilter.getBookingStatus() != null && !hotelReportFilter.getBookingStatus().equalsIgnoreCase("ALL"))
				{
					Disjunction statusActionDisjunction = Restrictions.disjunction();
					statusActionDisjunction.add(Restrictions.eq("statusAction", hotelReportFilter.getBookingStatus().toLowerCase()));
					statusActionDisjunction.add(Restrictions.eq("statusAction", hotelReportFilter.getBookingStatus().toUpperCase()));
					statusActionDisjunction.add(Restrictions.eq("statusAction", hotelReportFilter.getBookingStatus()));
					if(hotelReportFilter.getBookingStatus().equalsIgnoreCase("Failed"))
						statusActionDisjunction.add(Restrictions.eq("statusAction", "Booking Failed"));
					reportConjunction.add(statusActionDisjunction);

				}
				if(hotelReportFilter.getPaymentStatus() != null && !hotelReportFilter.getPaymentStatus().equalsIgnoreCase("ALL"))
				{
					Disjunction statusActionDisjunction = Restrictions.disjunction();
					statusActionDisjunction.add(Restrictions.eq("paymentStatus", hotelReportFilter.getPaymentStatus().toLowerCase()));
					statusActionDisjunction.add(Restrictions.eq("paymentStatus", hotelReportFilter.getPaymentStatus().toUpperCase()));
					statusActionDisjunction.add(Restrictions.eq("paymentStatus", hotelReportFilter.getPaymentStatus()));
					if(hotelReportFilter.getPaymentStatus().equalsIgnoreCase("Success"))
						statusActionDisjunction.add(Restrictions.eq("paymentStatus", "Paid"));
					reportConjunction.add(statusActionDisjunction);

				}				
				if(hotelReportFilter.getOrderId() != null && !hotelReportFilter.getOrderId().equalsIgnoreCase(""))
					reportConjunction.add(Restrictions.like("orderReference", hotelReportFilter.getOrderId(), MatchMode.ANYWHERE));
				if(hotelReportFilter.getUserId()>0)
					reportConjunction.add(Restrictions.like("userId",  String.valueOf(hotelReportFilter.getUserId())));
				if(hotelReportFilter.getCompanyId()>0)
					reportConjunction.add(Restrictions.like("companyId", String.valueOf(hotelReportFilter.getCompanyId())));
				if(hotelReportFilter.getHotelName()!=null && !hotelReportFilter.getHotelName().equals(""))
					hotelOrderHotelDataConjunction.add(Restrictions.like("name",hotelReportFilter.getHotelName()));
				if(hotelReportFilter.getEmail()!=null && !hotelReportFilter.getEmail().equals(""))
					orderCustomerConjunction.add(Restrictions.like("email",hotelReportFilter.getEmail()));
				
				if(hotelReportFilter.getInvoiceNo() != null && !hotelReportFilter.getInvoiceNo().equalsIgnoreCase(""))
					reportConjunction.add(Restrictions.eq("invoiceNo", hotelReportFilter.getInvoiceNo()));
				if(hotelReportFilter.getConfirmationNo() != null && !hotelReportFilter.getConfirmationNo().equalsIgnoreCase(""))
					reportConjunction.add(Restrictions.eq("confirmationNo", hotelReportFilter.getConfirmationNo()));
				
				if(hotelReportFilter.getSupplierName() != null && !hotelReportFilter.getSupplierName().equalsIgnoreCase("ALL")){
					List<Integer> apiIdList=null;
					List<String> stringIdList=new ArrayList<String>() ;
					try{
						apiIdList=new ApiProviderDao().getApiProviderDetailsBy(hotelReportFilter.getSupplierName());
							for(Integer idList:apiIdList)
								stringIdList.add(String.valueOf(idList));
							if( stringIdList.size()>0)
								reportConjunction.add(Restrictions.in("apiProvoder", stringIdList));
					}catch (Exception e) {
					}
				}
				
				if(hotelReportFilter.getFirstName() != null && !hotelReportFilter.getFirstName().equalsIgnoreCase(""))
					orderCustomerConjunction.add(Restrictions.eq("firstName", hotelReportFilter.getFirstName()));
				if(hotelReportFilter.getLastName() != null && !hotelReportFilter.getLastName().equalsIgnoreCase(""))
					orderCustomerConjunction.add(Restrictions.eq("lastName", hotelReportFilter.getLastName()));
				if(hotelReportFilter.getMobile() != null && !hotelReportFilter.getMobile().equalsIgnoreCase(""))
					orderCustomerConjunction.add(Restrictions.eq("mobile", hotelReportFilter.getMobile()));
				if(hotelReportFilter.getLocation() != null && !hotelReportFilter.getLocation().equalsIgnoreCase(""))
					orderCustomerConjunction.add(Restrictions.eq("city", hotelReportFilter.getLocation()));
				
				if((hotelReportFilter.getDateFilterBooking().getDtEnd()!=null && !hotelReportFilter.getDateFilterBooking().getDtEnd().equals("")) && (hotelReportFilter.getDateFilterBooking().getDtStart()!=null &&!hotelReportFilter.getDateFilterBooking().getDtStart().equals("")))
				{
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(hotelReportFilter.getDateFilterBooking().getDtStart());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						reportConjunction.add(Restrictions.ge("createdAt", date));
					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(hotelReportFilter.getDateFilterBooking().getDtEnd());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.lt("createdAt", date));
					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());

					}
				}

				if((hotelReportFilter.getCheckInFrom()!=null && !hotelReportFilter.getCheckInFrom().equals("")) && (hotelReportFilter.getCheckInTo()!=null &&!hotelReportFilter.getCheckInTo().equals(""))) 
				{
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(hotelReportFilter.getCheckInFrom());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						reportConjunction.add(Restrictions.ge("checkInDate", date));

					}catch(Exception ex)
					{
						logger.info("##########getDateFilterCheckIn date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(hotelReportFilter.getCheckInTo());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.lt("checkInDate", date));

					}catch(Exception ex)
					{
						logger.info("##########getDateFilterCheckIn date max format exception---"+ex.getMessage());
					}
				}

				if((hotelReportFilter.getCheckOutFrom()!=null && !hotelReportFilter.getCheckOutFrom().equals("")) && (hotelReportFilter.getCheckOutTo()!=null && !hotelReportFilter.getCheckOutTo().equals("")))
				{
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(hotelReportFilter.getCheckOutFrom());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						reportConjunction.add(Restrictions.ge("checkOutDate", date));

					}catch(Exception ex)
					{
						logger.info("##########getDateFilterCheckOut date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(hotelReportFilter.getCheckOutTo());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.lt("checkOutDate", date));

					}catch(Exception ex)
					{
						logger.info("##########getDateFilterCheckOut date max format exception---"+ex.getMessage());

					}
				}
				
				if((hotelReportFilter.getDateFilterInvoice().getDtEnd()!=null && !hotelReportFilter.getDateFilterInvoice().getDtEnd().equals("")) && (hotelReportFilter.getDateFilterInvoice().getDtStart()!=null &&!hotelReportFilter.getDateFilterInvoice().getDtStart().equals("")))
				{
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(hotelReportFilter.getDateFilterInvoice().getDtStart());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						reportConjunction.add(Restrictions.ge("createdAt", date));
					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(hotelReportFilter.getDateFilterInvoice().getDtEnd());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.lt("createdAt", date));
					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());

					}
				}
				try{
					if(showType!=null && showType.equalsIgnoreCase("hotelconfirm")){
						reportConjunction.add(Restrictions.eq("statusAction","Confirmed"));
					}
					else if(showType!=null && showType.equalsIgnoreCase("hotelpaymentfailed")){
						reportConjunction.add(Restrictions.eq("paymentStatus","Failed"));
					}	
					else if(showType!=null && showType.equalsIgnoreCase("hotelpayment")){
						reportConjunction.add(Restrictions.eq("paymentStatus","Paid"));
					}	

					else if(showType!=null && showType.equalsIgnoreCase("today")){
						Calendar cal = Calendar.getInstance();
						String formattedDate = targetFormat.format(cal.getTime()); 
						Date  today = targetFormat.parse(formattedDate) ;
						Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.ge("createdAt", today));
						reportConjunction.add(Restrictions.lt("createdAt", tomorrow));
					}
					else if(showType!=null && showType.equalsIgnoreCase("week")){							
						Calendar cal = Calendar.getInstance();
						String formattedDate = targetFormat.format(cal.getTime()); 
						Date  today = targetFormat.parse(formattedDate) ;
						Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));
						// get start of this week 
						cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
						String formattedfirstdayoftheweek = targetFormat.format(cal.getTime()); 
						Date  firstdayoftheweek = targetFormat.parse(formattedfirstdayoftheweek) ;
						reportConjunction.add(Restrictions.ge("createdAt", firstdayoftheweek));
						reportConjunction.add(Restrictions.lt("createdAt", tomorrow));
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
					}

					else if(showType!=null && showType.equalsIgnoreCase("hotelconfirm")){	
						Calendar cal = Calendar.getInstance();
						String formattedDate = targetFormat.format(cal.getTime()); 
						Date  today = targetFormat.parse(formattedDate) ;
						Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.ge("createdAt", today));
						reportConjunction.add(Restrictions.lt("createdAt", tomorrow));
						reportConjunction.add(Restrictions.eq("statusAction", "Confirmed"));
					}
					else if(showType!=null && showType.equalsIgnoreCase("hotelpayment")){	
						Calendar cal = Calendar.getInstance();
						String formattedDate = targetFormat.format(cal.getTime()); 
						Date  today = targetFormat.parse(formattedDate) ;
						Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.ge("createdAt", today));
						reportConjunction.add(Restrictions.lt("createdAt", tomorrow));
						reportConjunction.add(Restrictions.eq("paymentStatus", "Paid"));
					}
					else if(showType!=null && showType.equalsIgnoreCase("hotelpaymentfailed")){	
						Calendar cal = Calendar.getInstance();
						String formattedDate = targetFormat.format(cal.getTime()); 
						Date  today = targetFormat.parse(formattedDate) ;
						Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.ge("createdAt", today));
						reportConjunction.add(Restrictions.lt("createdAt", tomorrow));
						reportConjunction.add(Restrictions.ne("paymentStatus", "Paid"));
					}

				}catch(Exception ex)
				{
					logger.info("##########today format exception---"+ex.getMessage());
				}

				criteria.add(reportConjunction);
				criteria.createCriteria("hotelOrderHotelData").add(hotelOrderHotelDataConjunction);	
				criteria.createCriteria("orderCustomer").add(orderCustomerConjunction);
				criteria.addOrder(Order.desc("id"));
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount-------"+rowCount);
			if(rowCount>0)
			{
				List<HotelOrderRow> list = new ArrayList<HotelOrderRow>();
				if(hotelReportPage.getMaxItems()==Page.ALL_ITEMS){
					criteria = session.createCriteria(HotelOrderRow.class);
					criteria.add(reportConjunction);
					criteria.createCriteria("hotelOrderHotelData").add(hotelOrderHotelDataConjunction);	
					criteria.createCriteria("orderCustomer").add(orderCustomerConjunction);
					criteria.addOrder(Order.desc("id"));
					criteria.setMaxResults(hotelReportPage.getMaxItems());
					list = criteria.list();	
					hotelReportPage.setAvailableItems(list.size());
					hotelReportPage.setAvailablePages(1);
				}

				else{	 
					if(hotelReportPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % hotelReportPage.getMaxItems() == 0)?(availableItems / hotelReportPage.getMaxItems()):((availableItems / hotelReportPage.getMaxItems()) + 1);
						hotelReportPage.setAvailableItems(availableItems);
						hotelReportPage.setAvailablePages(availablePages);
					}
					int pageIndexDb = (hotelReportPage.getCurrentPageIndex() > 1)?hotelReportPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * hotelReportPage.getMaxItems();
					logger.info("setFirstResult-------"+itemIndex);
					if(itemIndex <= rowCount)
					{
						criteria = session.createCriteria(HotelOrderRow.class);
						criteria.add(reportConjunction);
						criteria.createCriteria("hotelOrderHotelData").add(hotelOrderHotelDataConjunction);	
						criteria.createCriteria("orderCustomer").add(orderCustomerConjunction);
						criteria.addOrder(Order.desc("id"));
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(hotelReportPage.getMaxItems());
						list = criteria.list();	
						logger.info("list" +list.size());
					}
				}
				if(list!=null && list.size()>0)
				{
					for(HotelOrderRow hor:list){
						HotelReport reportData=new HotelReport();
						reportData.setCheckInDate(DateConversion.convertDateToStringToDate(hor.getCheckInDate()));
						reportData.setCheckOutDate(DateConversion.convertDateToStringToDate(hor.getCheckOutDate()));
						reportData.setFirstname(hor.getOrderCustomer().getFirstName());
						reportData.setLastname(hor.getOrderCustomer().getLastName());
						reportData.setHotelName(hor.getHotelOrderHotelData().getName());

						reportData.setPaymentStatus(hor.getPaymentStatus());
						reportData.setStatusAction(hor.getStatusAction());
						reportData.setOrderRef(hor.getOrderReference());
						ApiProvider apiProvider= new ApiProvider();//getApiPrividerDetails(hor.getApiProvoder(), session);
						apiProvider.setApiProvider(hor.getApiProvoder());
						if(apiProvider!=null){
							reportData.setApiProvider(apiProvider);
						}
						if(hor.getCreatedBy()!=null){
							reportData.setCreatedBy(hor.getCreatedBy().replace("+", " "));	
						}
						else{
							reportData.setCreatedBy(hor.getCreatedBy());	
						}
						BigDecimal basePrice= hor.getApiPrice().multiply(hor.getApiToBaseExchangeRate()) ;
						BigDecimal taxes= hor.getTaxes().multiply(hor.getApiToBaseExchangeRate()) ;
						BigDecimal totalBasePrice=basePrice.add(hor.getMarkupAmount());
						BigDecimal basePriceInBooking=totalBasePrice.multiply(hor.getBaseToBookingExchangeRate());
						BigDecimal taxesInBooking=taxes.multiply(hor.getBaseToBookingExchangeRate());
						BigDecimal totalPrice = hor.getFeeAmount().add(basePriceInBooking).add(taxesInBooking);
						String checkInDate=DateConversion.convertDateToStringToDate(hor.getCheckInDate());
						String checkOutDate=DateConversion.convertDateToStringToDate(hor.getCheckOutDate());
						int days=CommonUtil.getNoofStayDays(checkInDate, checkOutDate);
						int totalGuestMarkupCount=days*hor.getTotalGuest();
						HotelOrderRowMarkup hotelOrderRowMarkup=  getCompanyMarkup(hor.getCompanyId(), hor.getId());
						if(hotelOrderRowMarkup!=null){
							reportData.setMarkUp(hotelOrderRowMarkup.getMarkUp().multiply(new BigDecimal(totalGuestMarkupCount)).setScale(2, BigDecimal.ROUND_UP));
						}
						else{
							reportData.setMarkUp(new BigDecimal("0.00"));	
						}
						
						BigDecimal finalPrice = hor.getFinalPrice()!= null?hor.getFinalPrice().setScale(2, RoundingMode.UP):new BigDecimal(0);
						if(hor.getHotelOrderRowServiceTax()!=null){
						reportData.setTotal(finalPrice.add(calculateTotalserviceTax(hor)).setScale(2, BigDecimal.ROUND_UP));
						}
						else if (hor.getHotelOrderRowGstTax()!=null) {
							if(companySessionObj.getCompanyRole().isAgent() || companySessionObj.getCompanyRole().isDistributor() || companySessionObj.getCompanyRole().isSuperUser() ){
								reportData.setTotal(finalPrice.setScale(2, BigDecimal.ROUND_UP));
							}
							else if (companySessionObj.getCompanyRole().isCorporate()) {
								reportData.setTotal(finalPrice.add(calculateTotalGSTTax(hor)).setScale(2, BigDecimal.ROUND_UP));
							}
							
						}
						else 
							reportData.setTotal(finalPrice.setScale(2, BigDecimal.ROUND_UP));
					 
						BigDecimal netPaybleAmount=reportData.getTotal().subtract(reportData.getMarkUp());
						reportData.setNetAmnt(netPaybleAmount.setScale(2,BigDecimal.ROUND_UP));
						reportData.setId(hor.getId());
						reportData.setCompanyId(hor.getCompanyId());
						reportData.setConfigId(hor.getConfigId());
						reportData.setCreatedAt(hor.getCreatedAt());
						reportData.setBooking_status(hor.getBookingStatus());
						reportData.setOrderRef(hor.getOrderReference());
						reportData.setGuests(hor.getTotalGuest());
						reportData.setTax(taxesInBooking);
						reportData.setFee_amount(hor.getFeeAmount());
						reportData.setDiscount(hor.getDiscountAmount());
						reportData.setAgentCom(hor.getMarkupAmount());
						reportData.setPaymentStatus(hor.getPaymentStatus());
						reportData.setStatusAction(hor.getStatusAction());
						reportData.setCountry(hor.getHotelOrderHotelData().getCountry());
						reportData.setCurCode(hor.getBookingCurrency());
						reportData.setHotel_loc(hor.getHotelOrderHotelData().getCity() !=null ? hor.getHotelOrderHotelData().getCity() :new String("--------"));
						reportData.setPhone(hor.getHotelOrderHotelData().getTelephone());
						reportData.setEmail(hor.getOrderCustomer().getEmail());
						reportData.setState(hor.getHotelOrderHotelData().getState());
						reportData.setHotel_cat(hor.getHotelOrderHotelData().getHotelCategory());
						reportData.setApiComments(hor.getApiComments());
						reportData.setUserCommits(hor.getUserComments());
						reportData.setUserId(hor.getUserId());
						reportData.setOrderRequested(hor.isOrderRequested());
						reportData.setCreditNoteIssued(hor.isCreditNoteIssued());
						reportData.setOrderUpdated(hor.isOrderUpdated());
						reportData.setConfirmNo(hor.getConfirmationNo());
						reportData.setBookingDate(hor.getBookingDate()!=null?DateConversion.getBluestarDateddMMyyyy(hor.getBookingDate()):"-");
						reportData.setCreatedAt(hor.getCreatedAt());
						reportData.setInvoiceNo(hor.getInvoiceNo());
						reportData.setOrderId(hor.getOrderReference());
						reportData.setInvoiceAmount(hor.getApiPrice().add(hor.getMarkupAmount()!=null?hor.getMarkupAmount().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00")).add(calculateTotalserviceTax(hor)).setScale(2, BigDecimal.ROUND_UP));
						

						User user=new FlightOrderDao().getSalesPersonName(hor.getUserId());
						if(user!=null) {
							if(user.getUsername()!=null){
								reportData.setSalesPersonName(user.getUsername());
							}
							else{
								reportData.setSalesPersonName("------");	
							}
						}

						else 
							reportData.setSalesPersonName("---------");	

						FlightAndHotelBookApiResponse flightAndHotelBookApiResponse = getApiStatusMessage(hor.getId());
						if(flightAndHotelBookApiResponse!=null)
							reportData.setApiResponseMessage(flightAndHotelBookApiResponse.getApiStatusMessage());

						reportData_list.add(reportData);

					}
					hotelReportPage.setItems(reportData_list);

				}
				else
				{
					//current page is having empty items..
					hotelReportPage.setAvailableItems(0);					
					hotelReportPage.setItems(new ArrayList<HotelReport>());
				}

			}

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return hotelReportPage;
	}

	public  HotelReportPage getCompanyHotelOrders(HotelReportPage hotelReportPage){
		List<HotelReport>  orderList=new ArrayList<>();
		int availablePages = 0;
		int availableItems = 0;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteriaHotelOrderCommission = session.createCriteria(HotelOrderRowCommission.class);				
			Conjunction conjunctionHotelOrderRowCommission = Restrictions.conjunction();
			Conjunction hotelOrderHotelDataConjunction = Restrictions.conjunction();
			Conjunction conjunctionHotelOrderRow = Restrictions.conjunction();
			Conjunction orderCustomerConjunction = Restrictions.conjunction();
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
			HotelReportFilter hotelReportFilter =  null;
			// To get total row count.
			if(hotelReportPage!=null && hotelReportPage.isFilterEnabled())
			{
				hotelReportFilter = hotelReportPage.getHotelReportFilter();
				/* Add multiple condition separated by AND clause within brackets. */
				List<String> companyIdList  = new ArrayList<String>();
				Company company=null;
				if(hotelReportFilter.getCompanyId()>0){
					company=new Company();
					company.setCompanyid(hotelReportFilter.getCompanyId());
				}
				else 
					company=hotelReportFilter.getLoginCompany();

				companyIdList = flightOrderDao.getCompanyIdList(company, hotelReportFilter.getReportType(), hotelReportFilter.getCompanyName());
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					hotelReportPage.setAvailableItems(0);					
					hotelReportPage.setItems(new ArrayList<HotelReport>());
					return hotelReportPage;
				}
				conjunctionHotelOrderRowCommission.add(Restrictions.in("CompanyId",companyIdList));
				if(hotelReportFilter.getOrderId() != null && !hotelReportFilter.getOrderId().equalsIgnoreCase(""))
					conjunctionHotelOrderRow.add(Restrictions.like("orderReference", hotelReportFilter.getOrderId(), MatchMode.ANYWHERE));
				if(hotelReportFilter.getUserId()>0)
					conjunctionHotelOrderRow.add(Restrictions.like("userId",  String.valueOf(hotelReportFilter.getUserId())));
				
				if(hotelReportFilter.getInvoiceNo() != null && !hotelReportFilter.getInvoiceNo().equalsIgnoreCase(""))
					conjunctionHotelOrderRow.add(Restrictions.eq("invoiceNo", hotelReportFilter.getInvoiceNo()));
				if(hotelReportFilter.getConfirmationNo() != null && !hotelReportFilter.getConfirmationNo().equalsIgnoreCase(""))
					conjunctionHotelOrderRow.add(Restrictions.eq("confirmationNo", hotelReportFilter.getConfirmationNo()));
				
				if(hotelReportFilter.getSupplierName() != null && !hotelReportFilter.getSupplierName().equalsIgnoreCase("ALL")){
					List<Integer> apiIdList=null;
					List<String> stringIdList=new ArrayList<String>() ;
					try{
						apiIdList=new ApiProviderDao().getApiProviderDetailsBy(hotelReportFilter.getSupplierName());
							for(Integer idList:apiIdList)
								stringIdList.add(String.valueOf(idList));
							if( stringIdList.size()>0)
								conjunctionHotelOrderRow.add(Restrictions.in("apiProvoder", stringIdList));
					}catch (Exception e) {
					}
				}
				
				if(hotelReportFilter.getFirstName() != null && !hotelReportFilter.getFirstName().equalsIgnoreCase(""))
					orderCustomerConjunction.add(Restrictions.eq("firstName", hotelReportFilter.getFirstName()));
				if(hotelReportFilter.getLastName() != null && !hotelReportFilter.getLastName().equalsIgnoreCase(""))
					orderCustomerConjunction.add(Restrictions.eq("lastName", hotelReportFilter.getLastName()));
				if(hotelReportFilter.getMobile() != null && !hotelReportFilter.getMobile().equalsIgnoreCase(""))
					orderCustomerConjunction.add(Restrictions.eq("mobile", hotelReportFilter.getMobile()));
				if(hotelReportFilter.getLocation() != null && !hotelReportFilter.getLocation().equalsIgnoreCase(""))
					orderCustomerConjunction.add(Restrictions.eq("city", hotelReportFilter.getLocation()));
				
				
				
				if((hotelReportFilter.getDateFilterBooking().getDtEnd()!=null && !hotelReportFilter.getDateFilterBooking().getDtEnd().equals("")) && (hotelReportFilter.getDateFilterBooking().getDtStart()!=null &&!hotelReportFilter.getDateFilterBooking().getDtStart().equals("")))
				{
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(hotelReportFilter.getDateFilterBooking().getDtStart());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						conjunctionHotelOrderRow.add(Restrictions.ge("createdAt", date));
					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(hotelReportFilter.getDateFilterBooking().getDtEnd());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						conjunctionHotelOrderRow.add(Restrictions.lt("createdAt", date));
					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());

					}
				}
				
				if((hotelReportFilter.getDateFilterInvoice().getDtEnd()!=null && !hotelReportFilter.getDateFilterInvoice().getDtEnd().equals("")) && (hotelReportFilter.getDateFilterInvoice().getDtStart()!=null &&!hotelReportFilter.getDateFilterInvoice().getDtStart().equals("")))
				{
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(hotelReportFilter.getDateFilterInvoice().getDtStart());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						conjunctionHotelOrderRow.add(Restrictions.ge("createdAt", date));
					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(hotelReportFilter.getDateFilterInvoice().getDtEnd());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						conjunctionHotelOrderRow.add(Restrictions.lt("createdAt", date));
					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());

					}
				}
				criteriaHotelOrderCommission.createCriteria("hotelOrderRow").add(conjunctionHotelOrderRow).createCriteria("orderCustomer").add(orderCustomerConjunction);		
				criteriaHotelOrderCommission.add(conjunctionHotelOrderRowCommission);
				criteriaHotelOrderCommission.addOrder(Order.desc("id"));
				
				/*conjunctionHotelOrderRow*/
			}
			Long rowCount= (Long) criteriaHotelOrderCommission.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			if(rowCount>0)
			{
				List<HotelOrderRowCommission> list = null;
				if(hotelReportPage.getMaxItems()==Page.ALL_ITEMS){
					criteriaHotelOrderCommission = session.createCriteria(HotelOrderRowCommission.class);						
					criteriaHotelOrderCommission.add(conjunctionHotelOrderRowCommission);
					criteriaHotelOrderCommission.addOrder(Order.desc("id"));
					criteriaHotelOrderCommission.createCriteria("hotelOrderRow").add(conjunctionHotelOrderRow).createCriteria("orderCustomer").add(orderCustomerConjunction);			
					list = criteriaHotelOrderCommission.list();
					hotelReportPage.setAvailableItems(list.size());
					hotelReportPage.setAvailablePages(1);
				}
				else{	
					if(hotelReportPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % hotelReportPage.getMaxItems() == 0)?(availableItems / hotelReportPage.getMaxItems()):((availableItems / hotelReportPage.getMaxItems()) + 1);
						hotelReportPage.setAvailableItems(availableItems);
						hotelReportPage.setAvailablePages(availablePages);
					} 
					//Retrive report with pagination ....... 
					int pageIndexDb = (hotelReportPage.getCurrentPageIndex() > 1)?hotelReportPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * hotelReportPage.getMaxItems();
					logger.info("setFirstResult-------"+itemIndex);

					if(itemIndex <= rowCount)
					{
						logger.info("setFirstResult-------"+itemIndex);
						logger.info("MaxResults-------"+hotelReportPage.getMaxItems());
						criteriaHotelOrderCommission = session.createCriteria(HotelOrderRowCommission.class);						
						criteriaHotelOrderCommission.add(conjunctionHotelOrderRowCommission);
						criteriaHotelOrderCommission.addOrder(Order.desc("id"));
						criteriaHotelOrderCommission.createCriteria("hotelOrderRow").add(conjunctionHotelOrderRow).createCriteria("orderCustomer").add(orderCustomerConjunction);				
						criteriaHotelOrderCommission.setFirstResult(itemIndex);
						criteriaHotelOrderCommission.setMaxResults(hotelReportPage.getMaxItems());
						list = criteriaHotelOrderCommission.list();
						logger.info("Hotel Orders size------"+list.size());					
					}
				}
				if(list!=null && list.size()>0){
					for(HotelOrderRowCommission flightOrderRowCommissionParent:list){
						HotelOrderRow hor =null;
						if(flightOrderRowCommissionParent.getHotelOrderRow()!=null){
							hor =flightOrderRowCommissionParent.getHotelOrderRow();
							HotelReport reportData=new HotelReport();
							SimpleDateFormat sdf = new SimpleDateFormat();
							sdf.setTimeZone(new SimpleTimeZone(0, "GMT"));
							sdf.applyPattern("dd-MM-yyyy");
							reportData.setCheckInDate(sdf.format(hor.getCheckInDate()));
							reportData.setCheckOutDate(sdf.format(hor.getCheckOutDate()));
							BigDecimal taxes= hor.getTaxes().multiply(hor.getApiToBaseExchangeRate()) ;
							BigDecimal taxesInBooking=taxes.multiply(hor.getBaseToBookingExchangeRate());
							reportData.setCompanyId(hor.getCompanyId());
							reportData.setConfigId(hor.getConfigId());

							if(hor.getCreatedBy()!=null){
								reportData.setCreatedBy(hor.getCreatedBy().replace("+", " "));	
							}
							else{
								reportData.setCreatedBy(hor.getCreatedBy());	
							}
							reportData.setBookingDate(hor.getBookingDate()!=null?DateConversion.getBluestarDateddMMyyyy(hor.getBookingDate()):"-");
							reportData.setCreatedDate(DateConversion.convertTimestampToString(hor.getCreatedAt()));
							reportData.setBooking_status(hor.getBookingStatus());
							reportData.setOrderRef(hor.getOrderReference());
							reportData.setGuests(hor.getTotalGuest());
							reportData.setTax(taxesInBooking);
							reportData.setGuests(hor.getTotalGuest());
							reportData.setTotal(hor.getFinalPrice().add(calculateTotalserviceTax(hor)).setScale(2, BigDecimal.ROUND_UP));
							reportData.setBase_price(hor.getFinalPrice().setScale(2, RoundingMode.UP));
							reportData.setFee_amount(hor.getFeeAmount());
							reportData.setDiscount(hor.getDiscountAmount());
							reportData.setAgentCom(hor.getMarkupAmount());
							reportData.setPaymentStatus(hor.getPaymentStatus());
							reportData.setStatusAction(hor.getStatusAction());
							reportData.setHotelName(hor.getHotelOrderHotelData().getName());
							reportData.setHotel_loc(hor.getHotelOrderHotelData().getCity() !=null ? hor.getHotelOrderHotelData().getCity() :new String("--"));
							reportData.setCountry(hor.getHotelOrderHotelData().getCountry());
							reportData.setCurCode(hor.getBookingCurrency());
							reportData.setId(hor.getId());
							reportData.setHotel_loc(hor.getHotelOrderHotelData().getCity());
							reportData.setPhone(hor.getHotelOrderHotelData().getTelephone());
							reportData.setFirstname(hor.getOrderCustomer().getFirstName());
							reportData.setLastname(hor.getOrderCustomer().getLastName());
							reportData.setEmail(hor.getOrderCustomer().getEmail());
							reportData.setState(hor.getHotelOrderHotelData().getState());
							reportData.setHotel_cat(hor.getHotelOrderHotelData().getHotelCategory());
							reportData.setApiComments(hor.getApiComments());
							reportData.setUserCommits(hor.getUserComments());
							reportData.setUserId(hor.getUserId());
							reportData.setOrderRequested(hor.isOrderRequested());
							reportData.setCreditNoteIssued(hor.isCreditNoteIssued());
							reportData.setOrderUpdated(hor.isOrderUpdated());
							reportData.setConfirmNo(hor.getConfirmationNo());
							ApiProvider apiProvider= new ApiProvider();//getApiPrividerDetails(hor.getApiProvoder(), session);
							apiProvider.setApiProvider(hor.getApiProvoder());
							reportData.setCreatedAt(hor.getCreatedAt());
							reportData.setApiProvider(apiProvider);		
							reportData.setInvoiceAmount(hor.getApiPrice().add(hor.getMarkupAmount()!=null?hor.getMarkupAmount().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00")).add(calculateTotalserviceTax(hor)).setScale(2, BigDecimal.ROUND_UP));
							
							FlightAndHotelBookApiResponse flightAndHotelBookApiResponse = getApiStatusMessage(hor.getId());
							if(flightAndHotelBookApiResponse!=null)
								reportData.setApiResponseMessage(flightAndHotelBookApiResponse.getApiStatusMessage());

							orderList.add(reportData);
						}
						hotelReportPage.setItems(orderList);
					}
					if((hotelReportFilter.getEmail() != null && !hotelReportFilter.getEmail().equals("")))
					{								
						orderList = new ArrayList<HotelReport>();
						for (HotelReport hotelReport : hotelReportPage.getItems()) {
							if(hotelReportFilter.getEmail().equalsIgnoreCase(hotelReport.getEmail())){
								orderList.add(hotelReport);
							}
						}	
						hotelReportPage.setItems(orderList);
						if(hotelReportPage.isPagination())
						{
							availableItems = hotelReportPage.getItems().size();
							availablePages = (availableItems % hotelReportPage.getMaxItems() == 0)?(availableItems / hotelReportPage.getMaxItems()):((availableItems / hotelReportPage.getMaxItems()) + 1);
							hotelReportPage.setAvailableItems(availableItems);
							hotelReportPage.setAvailablePages(availablePages);
						} 
					} 

					if((hotelReportFilter.getHotelName() != null && !hotelReportFilter.getHotelName().equals("")))
					{								
						orderList = new ArrayList<HotelReport>();
						for (HotelReport hotelReport : hotelReportPage.getItems()) {
							if(hotelReportFilter.getHotelName().equalsIgnoreCase(hotelReport.getHotelName())){
								orderList.add(hotelReport);
							}
						}	
						hotelReportPage.setItems(orderList);
						if(hotelReportPage.isPagination())
						{
							availableItems = hotelReportPage.getItems().size();
							availablePages = (availableItems % hotelReportPage.getMaxItems() == 0)?(availableItems / hotelReportPage.getMaxItems()):((availableItems / hotelReportPage.getMaxItems()) + 1);
							hotelReportPage.setAvailableItems(availableItems);
							hotelReportPage.setAvailablePages(availablePages);
						} 
					} 
				}
				else
				{
					//current page is having empty items..
					hotelReportPage.setAvailableItems(0);					
					hotelReportPage.setItems(new ArrayList<HotelReport>());
				}
			}
			else
			{
				hotelReportPage.setAvailableItems(0);
				hotelReportPage.setAvailablePages(0);
				hotelReportPage.setItems(new ArrayList<HotelReport>());
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
		return hotelReportPage;
	}

	public  List<HotelReport> getCompanyHotelReports(User userSessionObj,Company companySessionObj ,String type){
		List<HotelReport>  reportData_list=new ArrayList<HotelReport>();
		String sql=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(userSessionObj!=null && companySessionObj!=null){
				if(type!=null && !type.equalsIgnoreCase(""))
				{
					boolean sameCompanySearch = false;
					CompanyDAO companyDAO = new CompanyDAO();
					StringBuffer userIdBuffer = new StringBuffer();
					if(userSessionObj.getUserrole_id().isSuperuser()){
						userIdBuffer = companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					}
					else if((userSessionObj.getUserrole_id().isAdmin() || userSessionObj.getUserrole_id().isUsermode()))
					{
						if(companySessionObj.getCompanyRole().isCorporate())
						{
							userIdBuffer = null;
						}
						else
						{
							userIdBuffer = companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						}
					}
					else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin())
					{
						if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
							sameCompanySearch = true;
						}
					}
					else{
						userIdBuffer.append(userSessionObj.getId());
					}
					if(userIdBuffer!=null)
					{
						if(type!=null && type.equalsIgnoreCase("week")&& !sameCompanySearch)
						{
							sql = "select * from hotel_order_row where user_id in ("+userIdBuffer+") and date(created_at) between  DATE_ADD(CURDATE() ,INTERVAL -6 day) and CURDATE()";
						}
						else if (sameCompanySearch) 
						{
							sql = "select * from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and date(created_at) between  DATE_ADD(CURDATE() ,INTERVAL -6 day) and CURDATE()";
						}
						if(type!=null && type.equalsIgnoreCase("today")&& !sameCompanySearch)
						{
							sql = "select * from hotel_order_row where user_id in ("+userIdBuffer+") and date(created_at)=CURDATE()";
						}
						else if (sameCompanySearch) 
						{
							sql = "select * from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and date(created_at)=CURDATE()";
						}
						if(type!=null && type.equalsIgnoreCase("month")&& !sameCompanySearch)
						{
							sql = "select * from hotel_order_row where user_id in ("+userIdBuffer+") and date(created_at) between  DATE_ADD(CURDATE() ,INTERVAL -29 day) and CURDATE()";
						}
						else if (sameCompanySearch) 
						{
							sql = "select * from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and date(created_at) between  DATE_ADD(CURDATE() ,INTERVAL -29 day) and CURDATE()";
						}
						if(type!=null && type.equalsIgnoreCase("hotelconfirm")&& !sameCompanySearch)
						{
							sql = "select * from hotel_order_row where user_id in ("+userIdBuffer+") and date(created_at)=CURDATE() and status_action='Confirmed'";
						}
						else if (sameCompanySearch) 
						{
							sql = "select * from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and date(created_at)=CURDATE() and status_action='Confirmed'";
						}
						if(type!=null && type.equalsIgnoreCase("hotelpayment")&& !sameCompanySearch)
						{
							sql = "select * from hotel_order_row where user_id in ("+userIdBuffer+") and date(created_at)=CURDATE() and payment_status='Success'";
						}
						else if (sameCompanySearch) 
						{
							sql = "select * from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and date(created_at)=CURDATE() and payment_status='Success'";
						}
						if(type!=null && type.equalsIgnoreCase("hotelpaymentfailed") && !sameCompanySearch)
						{
							sql = "select * from hotel_order_row where user_id in ("+userIdBuffer+") and date(created_at)=CURDATE() and payment_status!='Success'";
						}
						else if (sameCompanySearch) 
						{
							sql = "select * from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and date(created_at)=CURDATE() and payment_status!='Success'";
						}
					}
				}
				else{
					sql = "select * from hotel_order_row where  company_id="+companySessionObj.getCompanyid()+" order by id desc";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(HotelOrderRow.class);
			List<HotelOrderRow> list = query.list();
			for (Iterator  iterator = list.iterator(); iterator.hasNext();){
				HotelOrderRow hor= (HotelOrderRow)iterator.next(); 
				HotelReport reportData=new HotelReport();
				SimpleDateFormat sdf = new SimpleDateFormat();
				sdf.setTimeZone(new SimpleTimeZone(0, "GMT"));
				sdf.applyPattern("dd-MM-yyyy");
				reportData.setCheckInDate(sdf.format(hor.getCheckInDate()));

				reportData.setCheckOutDate(sdf.format(hor.getCheckOutDate()));
				BigDecimal basePrice= hor.getApiPrice().multiply(hor.getApiToBaseExchangeRate()) ;
				BigDecimal taxes= hor.getTaxes().multiply(hor.getApiToBaseExchangeRate()) ;
				BigDecimal totalBasePrice=basePrice.add(hor.getMarkupAmount());
				BigDecimal basePriceInBooking=totalBasePrice.multiply(hor.getBaseToBookingExchangeRate());
				BigDecimal taxesInBooking=taxes.multiply(hor.getBaseToBookingExchangeRate());
				BigDecimal totalPrice=hor.getFeeAmount().add(basePriceInBooking).add(taxesInBooking);
				BigDecimal disCountInBooking=hor.getDiscountAmount().multiply(hor.getApiToBaseExchangeRate()).multiply(hor.getBaseToBookingExchangeRate());

				reportData.setCompanyId(hor.getCompanyId());
				reportData.setConfigId(hor.getConfigId());
				reportData.setCreatedBy(hor.getCreatedBy());
				reportData.setBooking_status(hor.getBookingStatus());
				reportData.setOrderRef(hor.getOrderReference());
				reportData.setGuests(hor.getTotalGuest());
				reportData.setTax(taxesInBooking.setScale(2, BigDecimal.ROUND_UP));
				reportData.setGuests(hor.getTotalGuest());
				reportData.setTotal(hor.getFinalPrice().add(calculateTotalserviceTax(hor)).setScale(2, BigDecimal.ROUND_UP));//totalPrice.setScale(2, BigDecimal.ROUND_UP)
				reportData.setFee_amount(hor.getFeeAmount());
				reportData.setDiscount(disCountInBooking.setScale(2, BigDecimal.ROUND_UP));
				reportData.setAgentCom(hor.getMarkupAmount().setScale(2, BigDecimal.ROUND_UP));
				reportData.setPaymentStatus(hor.getPaymentStatus());
				reportData.setStatusAction(hor.getStatusAction());
				reportData.setHotelName(hor.getHotelOrderHotelData().getName());
				reportData.setCountry(hor.getHotelOrderHotelData().getCountry());
				reportData.setCurCode(hor.getBookingCurrency());
				reportData.setId(hor.getId());
				reportData.setHotel_loc(hor.getHotelOrderHotelData().getHotelLocation());
				reportData.setPhone(hor.getHotelOrderHotelData().getTelephone());
				reportData.setFirstname(hor.getOrderCustomer().getFirstName());
				reportData.setLastname(hor.getOrderCustomer().getLastName());
				reportData.setEmail(hor.getOrderCustomer().getEmail());
				reportData.setState(hor.getHotelOrderHotelData().getState());
				reportData.setHotel_cat(hor.getHotelOrderHotelData().getHotelCategory());
				reportData.setApiComments(hor.getApiComments());
				reportData.setUserCommits(hor.getUserComments());
				reportData.setUserId(hor.getUserId());
				reportData.setOrderRequested(hor.isOrderRequested());
				reportData.setCreditNoteIssued(hor.isCreditNoteIssued());
				reportData.setOrderUpdated(hor.isOrderUpdated());
				reportData.setConfirmNo(hor.getConfirmationNo());
				reportData_list.add(reportData);
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
		return reportData_list;
	} 


	public HotelReport hotelRoomandGuestandHotelOrderRowInfo(long id){
		HotelReport reportData=new HotelReport();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from HotelOrderRow hor where hor.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			HotelOrderRow list = (HotelOrderRow) query.uniqueResult();
			reportData.setHotelOrderRow(list);
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		List<HotelOrderRoomInfo> hotelOrderRoomInfos=hotelOrderRoomInfo(id);
		reportData.setHotelOrderRoomInfo(hotelOrderRoomInfos);
		return reportData;

	}

	public  ApiProvider getApiPrividerDetails(String providerId,Session session){
		ApiProvider apiProvider=null;
		try{
			Criteria criteria=session.createCriteria(ApiProvider.class);
			criteria.add(Restrictions.eq("id", Integer.parseInt(providerId)));
			apiProvider = (ApiProvider) criteria.uniqueResult();

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}  
		return apiProvider;
	}

	public  List<HotelOrderRoomInfo> hotelOrderRoomInfo(Long id){
		List<HotelOrderRoomInfo> list=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from HotelOrderRoomInfo horf where horf.hotelOrderRow.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			list = query.list();


		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}	 
		return list;
	}

	public  List<HotelOrderGuest> hotelOrderGuestInfo(Long id){
		List<HotelOrderGuest> list = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();

			String sql = "from HotelOrderGuest hog where hog.roomInfo.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			list = query.list();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}	 
		return list;

	}

	public  List<HotelOrderCancellationPolicy>  hotelOrderCancellationPolicyDetails(Long id){
		List<HotelOrderCancellationPolicy> list = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();

			String sql = "from HotelOrderCancellationPolicy hcp where hcp.hotelOrderRow.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id",id);
			list = query.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}	 
		return list;
	}
	public  HotelCreditNote hotelCreditNoteData(Long id, String companyId) {

		HotelCreditNote creditNote=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelCreditNote.class);
			criteria.add(Restrictions.eq("rowId", id.intValue()));
			criteria.add(Restrictions.eq("companyId",companyId));
			creditNote=(HotelCreditNote) criteria.uniqueResult();
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
	public List<com.lintas.admin.common.model.HotelCreditNote> loadCreditNoteListByFlightRowId(Long id) {
		String sql="from HotelCreditNote hcn where hcn.rowId=:id";
		List<com.lintas.admin.common.model.HotelCreditNote> list=new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("id",id.intValue());
			List<com.lintas.admin.common.model.HotelCreditNote> creditList=query.list();
			if(creditList!=null &&creditList.size()>0){
				for(com.lintas.admin.common.model.HotelCreditNote note:creditList){
					note.setConvertDate(DateConversion.convertTimestampToString(note.getOrderedAt()));
					note.setCancellationFees(note.getCancellationFees().setScale(2, BigDecimal.ROUND_UP));
					note.setConvenienceFees(note.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP));
					note.setManagementFees(note.getManagementFees()!=null?note.getManagementFees().setScale(2, BigDecimal.ROUND_UP):new BigDecimal(0));
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

	public List<PaymentTransaction> getHotelOrderPaymentInfo(String orderRef) {
		//String sql="select * from payment_transaction_APG where api_transaction_id='"+orderRef+"'";

		List<PaymentTransaction> payTxList= new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(PaymentTransaction.class);
			cr.add(Restrictions.eq("api_transaction_id", orderRef));
			List<PaymentTransaction> pList= cr.list();
			for(PaymentTransaction p:pList){
				p.setAmount(p.getAmount().setScale(2, BigDecimal.ROUND_UP));
				payTxList.add(p);
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
		return payTxList;
	}

	public HotelOrderRowCancellation getHotelOrderRowCancellation(String orderReference) {
		// TODO Auto-generated method stub
		HotelOrderRowCancellation cancellation=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelOrderRowCancellation.class);
			criteria.add(Restrictions.eq("orderId", orderReference));
			cancellation=(HotelOrderRowCancellation) criteria.uniqueResult();
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
		return cancellation;
	}

	public HotelOrderRow getHotelOrderRowById(String orderReference) {
		// TODO Auto-generated method stub
		HotelOrderRow order = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelOrderRow.class);
			criteria.add(Restrictions.eq("orderReference", orderReference));
			order=(HotelOrderRow) criteria.uniqueResult();
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
		return order;
	}



	public boolean isApiproviderPaidFullAmount(String orderReference) {
		// TODO Auto-generated method stub
		boolean isCustomerPaid=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Conjunction con=Restrictions.conjunction();
			Criteria criteria=session.createCriteria(ApiProviderPaymentTransactionDetail.class);
			con.add(Restrictions.eq("apiTransactionId", orderReference));
			con.add(Restrictions.eq("isPaymentSuccess",true));
			criteria.add(con);
			ApiProviderPaymentTransactionDetail paymentTransactionDetail = (ApiProviderPaymentTransactionDetail) criteria.uniqueResult();
			if(paymentTransactionDetail!=null && paymentTransactionDetail.getIsPaymentSuccess()){
				isCustomerPaid=true;
			} 
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return isCustomerPaid;

	}

	public boolean isCustomerPaidFullAmount(String orderReference) {
		// TODO Auto-generated method stub
		boolean isCustomerPaid=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Conjunction con=Restrictions.conjunction();
			Criteria criteria=session.createCriteria(PaymentTransactionDetail.class);
			con.add(Restrictions.eq("apiTransactionId", orderReference));
			con.add(Restrictions.eq("isPaymentSuccess",true));
			criteria.add(con);
			PaymentTransactionDetail paymentTransactionDetail = (PaymentTransactionDetail) criteria.uniqueResult();
			if(paymentTransactionDetail!=null && paymentTransactionDetail.getIsPaymentSuccess()){
				isCustomerPaid=true;
			} 
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return isCustomerPaid;

	}
	public ApiProviderPaymentTransaction  getApiProviderPaymentTransactionInfo(String apiTransactionId){
		ApiProviderPaymentTransaction apiProviderPaymentTransaction= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(ApiProviderPaymentTransaction.class);
			criteria.add(Restrictions.eq("api_transaction_id", apiTransactionId));
			apiProviderPaymentTransaction = (ApiProviderPaymentTransaction) criteria.uniqueResult();
			apiProviderPaymentTransaction.setAmount(apiProviderPaymentTransaction.getAmount().setScale(2, BigDecimal.ROUND_UP));
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}

		return apiProviderPaymentTransaction;

	}
	public PaymentTransaction  getPaymentTransactionInfo(String apiTransactionId){
		PaymentTransaction paymentTransaction= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(PaymentTransaction.class);
			criteria.add(Restrictions.eq("api_transaction_id", apiTransactionId));
			paymentTransaction = (PaymentTransaction) criteria.uniqueResult();
			if(paymentTransaction!=null){
				paymentTransaction.setAmount(paymentTransaction.getAmount().setScale(2,BigDecimal.ROUND_UP));
			} 

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}

		return paymentTransaction;

	}
	public HotelOrderRow  getHotelOrderRowInfo(long id){
		HotelOrderRow hotelOrderRow= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from HotelOrderRow hor where hor.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			hotelOrderRow = (HotelOrderRow) query.uniqueResult();

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}

		return hotelOrderRow;

	}


	//this method is testing  for download excel
	public  List<HotelOrderRow>  getHotelReportsForGenerateExcel(HotelandFlightDisReportProperty hotelDisReportProperty){
		List<HotelOrderRow> hotelOrderRowList= null;
		SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	

		try{
			Conjunction conjunction=Restrictions.conjunction();

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelOrderRow.class);
			if(hotelDisReportProperty.getFromDate() != null && hotelDisReportProperty.getToDate()!= null&& !hotelDisReportProperty.getToDate().equals("") && !hotelDisReportProperty.getFromDate().equals("")  )
			{
				DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
				try{
					Date date = originalFormat.parse(hotelDisReportProperty.getFromDate());
					String formattedDate = targetFormat.format(date); 
					date = targetFormat.parse(formattedDate);
					date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
					conjunction.add(Restrictions.ge("createdAt", date));
				}catch(Exception ex)
				{
					logger.info("##########date min format exception---"+ex.getMessage());

				}
				try{
					Date date = originalFormat.parse(hotelDisReportProperty.getToDate());
					String formattedDate = targetFormat.format(date); 
					date = targetFormat.parse(formattedDate);
					date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
					conjunction.add(Restrictions.lt("createdAt", date));
				}catch(Exception ex)
				{
					logger.info("##########date max format exception---"+ex.getMessage());

				}
			}
			else if(hotelDisReportProperty.getReportType().equalsIgnoreCase("0")){
				Calendar cal = Calendar.getInstance();
				try {
					String formattedDate = targetFormat.format(cal.getTime()); 
					Date today;
					today = targetFormat.parse(formattedDate);
					Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));
					conjunction.add(Restrictions.ge("createdAt", today));
					conjunction.add(Restrictions.lt("createdAt", tomorrow));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				try {
					if(hotelDisReportProperty.getReportType().equalsIgnoreCase("T")){
						Calendar cal = Calendar.getInstance();
						String formattedDate = targetFormat.format(cal.getTime()); 
						Date today = targetFormat.parse(formattedDate);
						Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));
						conjunction.add(Restrictions.ge("createdAt", today));
						conjunction.add(Restrictions.lt("createdAt", tomorrow));
					} 
					if(hotelDisReportProperty.getReportType().equalsIgnoreCase("W")){	
						Calendar cal = Calendar.getInstance();
						String formattedDate = targetFormat.format(cal.getTime()); 
						Date  today = targetFormat.parse(formattedDate) ;
						Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));
						cal.add(Calendar.DATE, -6);
						String formattedfirstdayoftheweek = targetFormat.format(cal.getTime()); 
						Date  firstdayoftheweek = targetFormat.parse(formattedfirstdayoftheweek) ;
						conjunction.add(Restrictions.ge("createdAt", firstdayoftheweek));
						conjunction.add(Restrictions.lt("createdAt", tomorrow));
					}
					else if(hotelDisReportProperty.getReportType().equalsIgnoreCase("M")){
						Calendar cal = Calendar.getInstance();
						String formattedDate = targetFormat.format(cal.getTime()); 
						Date  today = targetFormat.parse(formattedDate) ;
						Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));
						cal.add(Calendar.DATE, -29);
						String formattedmonth = targetFormat.format(cal.getTime()); 
						Date  firstdayofthemonth = targetFormat.parse(formattedmonth) ;							
						conjunction.add(Restrictions.ge("createdAt", firstdayofthemonth));
						conjunction.add(Restrictions.lt("createdAt", tomorrow));

					} 
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			criteria.add(conjunction); 
			criteria.addOrder(Order.asc("createdAt"));
			hotelOrderRowList =  criteria.list();

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}

		return hotelOrderRowList;

	}

	//this method is testing  for download excel
	public   List<HotelOrderRow>  getHotelReportsForGenerateExcelTest(){
		List<HotelOrderRow> hotelOrderRowList= null;
		try{

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelOrderRow.class);
			criteria.addOrder(Order.asc("createdAt"));
			hotelOrderRowList =  criteria.list();

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}

		return hotelOrderRowList;

	}




	public HotelOrderRowMarkup getCompanyMarkup(String companyId,Long rowId){
		HotelOrderRowMarkup hotelOrderRowMarkup=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelOrderRowMarkup.class);
			criteria.createCriteria("hotelOrderRow").add(Restrictions.eq("id",rowId));
			criteria.add(Restrictions.eq("CompanyId", companyId));
			//njunction.add(criteria);
			hotelOrderRowMarkup=  (HotelOrderRowMarkup) criteria.uniqueResult();


		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return hotelOrderRowMarkup;

	}
	/*this method for get  FlightAndHotelBookApiResponse  using order id */
	public FlightAndHotelBookApiResponse getApiStatusMessage(long orderid){
		FlightAndHotelBookApiResponse flightAndHotelBookApiResponse = new FlightAndHotelBookApiResponse();
		try{
			session = HibernateUtil.getSessionFactory().openSession();			
			Criteria criteria = session.createCriteria(FlightAndHotelBookApiResponse.class);
			Conjunction conjunction = Restrictions.conjunction();
			conjunction.add(Restrictions.eq("orderRowId", orderid));
			criteria.add(conjunction);
			flightAndHotelBookApiResponse = (FlightAndHotelBookApiResponse) criteria.uniqueResult();

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  flightAndHotelBookApiResponse;
	}

	public	HotelOrderRow updateHotelOrderRowDetail(HotelOrderRow hotelOrderRow){
		HotelOrderRow newHotelOrderRow = null;
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			newHotelOrderRow=(HotelOrderRow) session.get(HotelOrderRow.class,hotelOrderRow.getId());
			newHotelOrderRow.setInvoiceNo(hotelOrderRow.getInvoiceNo());
			newHotelOrderRow.setOrderReference(hotelOrderRow.getOrderReference());
			newHotelOrderRow.getOrderCustomer().setOrderId(hotelOrderRow.getOrderReference());//added by basha
			if(hotelOrderRow.getTotalGuest()>0)
				newHotelOrderRow.setTotalGuest(hotelOrderRow.getTotalGuest());
			session.update(newHotelOrderRow);
			tx.commit();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  newHotelOrderRow;
	}


	public	HotelOrderRow updateHotelOrderRowDetailPaymentStatus(HotelOrderRow hotelOrderRow){
		HotelOrderRow newHotelOrderRow = null;
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			newHotelOrderRow=(HotelOrderRow) session.get(HotelOrderRow.class,hotelOrderRow.getId());
			newHotelOrderRow.setPaymentStatus(hotelOrderRow.getPaymentStatus());
			session.update(newHotelOrderRow);
			tx.commit();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  newHotelOrderRow;
	}

	public static List<HotelOrderRowCommission> getHotelOrderRowCommissions(Company company, HotelOrderRow hotelOrderRow) throws Exception  {
		List<HotelOrderRowCommission> hotelOrderRowCommissions = new ArrayList<HotelOrderRowCommission>();
		CompanyDAO companyDAO = new CompanyDAO();
		HotelOrderDao hotelOrderDao  = new HotelOrderDao();
		Company companyParent= companyDAO.getParentCompany(company);
		Set<String> compnyIdset=new HashSet<String>();
		compnyIdset.add(String.valueOf(companyParent.getCompanyid()));
		compnyIdset.add(String.valueOf(company.getCompanyid()));
		for(String companyId:compnyIdset)
		{			
			HotelOrderRowCommission hotelOrderRowCommission = new HotelOrderRowCommission();
			hotelOrderRowCommission.setCommission(new BigDecimal(0));
			hotelOrderRowCommission.setCommissionType("Fixed");
			hotelOrderRowCommission.setCompanyId(companyId);
			hotelOrderRowCommission.setRateType("Net");
			hotelOrderRowCommission.setHotelOrderRow(hotelOrderRow);
			hotelOrderRowCommission.setCommissionAmountValue(new BigDecimal(0));
			hotelOrderDao.insertCommission(hotelOrderRowCommission);
			hotelOrderRowCommissions.add(hotelOrderRowCommission);
		}
		return hotelOrderRowCommissions;
	}


	public static List<HotelOrderRowMarkup> getHotelOrderRowMarkups(Company company,BigDecimal markUp,  HotelOrderRow hotelOrderRow) throws Exception
	{
		List<HotelOrderRowMarkup> hotelOrderRowMarkups = new ArrayList<HotelOrderRowMarkup>();
		CompanyDAO companyDAO = new CompanyDAO();
		HotelOrderDao hotelOrderDao  = new HotelOrderDao();
		Company companyParent= companyDAO.getParentCompany(company);
		Set<String> compnyIdset=new HashSet<String>();
		compnyIdset.add(String.valueOf(companyParent.getCompanyid()));
		compnyIdset.add(String.valueOf(company.getCompanyid()));
		for(String companyId:compnyIdset)
		{
			HotelOrderRowMarkup hotelOrderRowMarkup = new HotelOrderRowMarkup();
			hotelOrderRowMarkup.setCompanyId(companyId);
			// setting parent markup
			if(!String.valueOf(company.getCompanyid()).equalsIgnoreCase(companyId))
			{
				hotelOrderRowMarkup.setMarkUp(markUp);
			}
			else{
				hotelOrderRowMarkup.setMarkUp(new BigDecimal(0));	
			}
			hotelOrderRowMarkup.setHotelOrderRow(hotelOrderRow);
			hotelOrderDao.insertMarkupDetails(hotelOrderRowMarkup);
			hotelOrderRowMarkups.add(hotelOrderRowMarkup);
		}
		return hotelOrderRowMarkups;
	}
	public void insertCommission(
			HotelOrderRowCommission hotelOrderRowCommission) throws Exception {
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(hotelOrderRowCommission);
			transaction.commit();
			session.close();
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

	}

	public void insertMarkupDetails(HotelOrderRowMarkup hotelOrderRowMarkup)
			throws Exception {
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(hotelOrderRowMarkup);
			//logger.info("sent successfully");
			transaction.commit();
			session.close();
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
	}

	public static BigDecimal  calculateTotalserviceTax(HotelOrderRow  hotelOrderRow){
		BigDecimal convenienceFeePerRoom = new BigDecimal("0");
		BigDecimal managementFeePerRoom = new BigDecimal("0");
		if(hotelOrderRow.getHotelOrderRowServiceTax() !=null && hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee()!=null)
			managementFeePerRoom= hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee();
		if(hotelOrderRow.getHotelOrderRowServiceTax() !=null && hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee()!=null)
			convenienceFeePerRoom= hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee();
		
		if(hotelOrderRow.getHotelOrderRowGstTax() !=null && hotelOrderRow.getHotelOrderRowGstTax().getManagementFee()!=null)
			managementFeePerRoom= hotelOrderRow.getHotelOrderRowGstTax().getManagementFee();
		if(hotelOrderRow.getHotelOrderRowGstTax() !=null && hotelOrderRow.getHotelOrderRowGstTax().getConvenienceFee()!=null)
			convenienceFeePerRoom= hotelOrderRow.getHotelOrderRowGstTax().getConvenienceFee();
		
		BigDecimal  gstTax= hotelOrderRow.getTotGst()!=null?hotelOrderRow.getTotGst().setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);
		BigDecimal  serviceTax= hotelOrderRow.getServiceTax()!=null?hotelOrderRow.getServiceTax().setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);
		BigDecimal  totalAmount=serviceTax.add(managementFeePerRoom).add(convenienceFeePerRoom).add(gstTax);
		return totalAmount.setScale(2,BigDecimal.ROUND_UP);
	}

	public static BigDecimal  calculateTotalGSTTax(HotelOrderRow  hotelOrderRow){
		BigDecimal convenienceFeePerRoom = new BigDecimal("0");
		BigDecimal managementFeePerRoom = new BigDecimal("0");
		BigDecimal totalGSTPerRoom = new BigDecimal("0");
		if(hotelOrderRow.getHotelOrderRowGstTax() !=null && hotelOrderRow.getHotelOrderRowGstTax().getManagementFee()!=null)
			managementFeePerRoom= hotelOrderRow.getHotelOrderRowGstTax().getManagementFee();
		if(hotelOrderRow.getHotelOrderRowGstTax() !=null && hotelOrderRow.getHotelOrderRowGstTax().getConvenienceFee()!=null)
			convenienceFeePerRoom= hotelOrderRow.getHotelOrderRowGstTax().getConvenienceFee();
		BigDecimal  totGst= hotelOrderRow.getTotGst()!=null?hotelOrderRow.getTotGst().setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);
		BigDecimal  totalAmount=totGst.add(managementFeePerRoom).add(convenienceFeePerRoom);
		return totalAmount.setScale(2,BigDecimal.ROUND_UP);
	}
	
	
	public HotelOrderRow getHotelOrderRowDetail(String actionId) {
		// TODO Auto-generated method stub
		HotelOrderRow hotelOrderRow = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from HotelOrderRow fotd where fotd.orderReference=:orderId";
			Query query = session.createQuery(sql);
			query.setParameter("orderId", actionId);
			hotelOrderRow = (HotelOrderRow) query.uniqueResult();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return hotelOrderRow;

	}
	
	public HotelOrderHotelData insertHotelData(HotelOrderHotelData hotelOrderHotelData){
		try{
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		session.save(hotelOrderHotelData);
		transaction.commit();
		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		
		return hotelOrderHotelData;
		
	}
	
	public OrderCustomer insertOrderCustomer(OrderCustomer orderCustomer){
		try{
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		session.save(orderCustomer);
		transaction.commit();
		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		
		return orderCustomer;
	}
	public List<HotelOrderRow> getHotelBookingForIds(List<String> companyUserId ) {
		List<HotelOrderRow> hotelOrderRows=null;
 		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr=session.createCriteria(HotelOrderRow.class);
			cr.add(Restrictions.in("companyId", companyUserId));
			hotelOrderRows=cr.list();
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
 		return hotelOrderRows;
	}
	
	 
 
}