package com.lintas.admin.DAO;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.isl.admin.filter.FlightInvoiceFilter;
import com.isl.admin.page.FlightInvoicePage;
import com.isl.admin.page.Page;
import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.hotel.model.HotelOrderGuest;
import com.lintas.admin.hotel.model.HotelOrderRoomInfo;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.HotelInvoiceData;
import com.lintas.admin.model.HotelOrderRowMarkup;
import com.lintas.admin.model.InvoiceData;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.CommonUtil;
import com.lintas.utility.DateConversion;
import com.lintas.utility.GstPropertiesFile;
import com.lintas.utility.InvoiceFilter;

public class HotelInvoiceDao {
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelInvoiceDao.class);
	List<Long> roomIds=new ArrayList<Long>();
	/*load all HotelOrderRow data for Invoice*/
	public  FlightInvoicePage getCompanyHotelCustomerOrderListTest(FlightInvoicePage flightInvoicePage,int invoiceType){
		List<HotelOrderRow> hotelOrderRowList=new ArrayList<HotelOrderRow>();
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(HotelOrderRow.class);
		Conjunction invoiceConjunction = Restrictions.conjunction();
		FlightInvoiceFilter flightInvoiceFilter =null;
		List<String> companyIdList  =null;
		try{	
			if(flightInvoicePage!=null && flightInvoicePage.isFilterEnabled())
			{
				flightInvoiceFilter = flightInvoicePage.getFlightInvoiceFilter();
				companyIdList = new FlightOrderDao().getCompanyIdList(flightInvoiceFilter.getLoginCompany(), flightInvoiceFilter.getReportType(), flightInvoiceFilter.getCompanyName());
				String companyId=String.valueOf(flightInvoiceFilter.getLoginCompany().getCompanyid());
				List<String> newCompanyIdList  = new ArrayList<String>();
				if(companyIdList!=null){
					for(String id:companyIdList){
						if(invoiceType == InvoiceData.AFFILIATE_INVOICE) 
						{
							if(!id.equals(companyId)){
								newCompanyIdList.add(id);
							}

						}

						else{
							newCompanyIdList.add(companyId) ;
						}

					} 
				}
				logger.info("companyIdList--------------"+companyIdList);
				/*if(invoiceType == InvoiceData.CUSTOMER_INVOICE || invoiceType == InvoiceData.MY_INVOICE)
				companyIdList.add(String.valueOf(flightInvoicePage.getFlightInvoiceFilter().getLoginCompany().getCompanyid()));
			else if(invoiceType == InvoiceData.AFFILIATE_INVOICE)
			{
			 companyIdList = new CompanyDAO().getChildrenCompanyIds(flightInvoicePage.getFlightInvoiceFilter().getLoginCompany().getCompanyid(), flightInvoicePage.getFlightInvoiceFilter().getLoginUser().getId());
				logger.error("--------------getCompanyHotelCustomerOrderList----------------companyIdList-"+companyIdList);		
			}		*/
				invoiceConjunction.add(Restrictions.in("companyId", companyIdList));
				invoiceConjunction.add(Restrictions.ne("statusAction", "Failed"));

				if(flightInvoiceFilter.getBookingStatus() != null && !flightInvoiceFilter.getBookingStatus().equalsIgnoreCase("ALL"))
				{
					Disjunction statusActionDisjunction = Restrictions.disjunction();
					statusActionDisjunction.add(Restrictions.eq("statusAction", flightInvoiceFilter.getBookingStatus().toLowerCase()));
					statusActionDisjunction.add(Restrictions.eq("statusAction", flightInvoiceFilter.getBookingStatus().toUpperCase()));
					statusActionDisjunction.add(Restrictions.eq("statusAction", flightInvoiceFilter.getBookingStatus()));
					invoiceConjunction.add(statusActionDisjunction);
					//invoiceConjunction.add(Restrictions.eq("statusAction", flightInvoiceFilter.getBookingStatus()));
					logger.info("##########booking status added----"+flightInvoiceFilter.getBookingStatus());
				}
				if(flightInvoiceFilter.getPaymentStatus() != null && !flightInvoiceFilter.getPaymentStatus().equalsIgnoreCase("ALL"))
				{
					Disjunction statusActionDisjunction = Restrictions.disjunction();
					statusActionDisjunction.add(Restrictions.eq("paymentStatus", flightInvoiceFilter.getPaymentStatus().toLowerCase()));
					statusActionDisjunction.add(Restrictions.eq("paymentStatus", flightInvoiceFilter.getPaymentStatus().toUpperCase()));
					statusActionDisjunction.add(Restrictions.eq("paymentStatus", flightInvoiceFilter.getPaymentStatus()));
					invoiceConjunction.add(statusActionDisjunction);
					//invoiceConjunction.add(Restrictions.eq("paymentStatus", flightInvoiceFilter.getPaymentStatus()));
					logger.info("########## Payment Status added----"+flightInvoiceFilter.getPaymentStatus());
				}
				if(flightInvoiceFilter.getConfirmNo() != null && !flightInvoiceFilter.getConfirmNo().equalsIgnoreCase(""))
				{
					invoiceConjunction.add(Restrictions.eq("orderReference", flightInvoiceFilter.getConfirmNo()));
					logger.info("##########getorderReference added----"+flightInvoiceFilter.getConfirmNo());
				} 
				if(flightInvoiceFilter.getInvoiceNo() != null && !flightInvoiceFilter.getInvoiceNo().equalsIgnoreCase(""))
				{
					invoiceConjunction.add(Restrictions.eq("invoiceNo", flightInvoiceFilter.getInvoiceNo()));
					logger.info("##########InvoiceNo----"+flightInvoiceFilter.getInvoiceNo());
				}
				if(flightInvoiceFilter.getDateFilterBooking() != null && flightInvoiceFilter.getDateFilterBooking().getDtEnd() != null && flightInvoiceFilter.getDateFilterBooking().getDtStart() != null )
				{

					//2016-06-28
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");


					try{
						Date date = originalFormat.parse(flightInvoiceFilter.getDateFilterBooking().getDtStart());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);

						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));

						invoiceConjunction.add(Restrictions.ge("createdAt", date));
						logger.info("##########date min added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(flightInvoiceFilter.getDateFilterBooking().getDtEnd());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);

						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));

						invoiceConjunction.add(Restrictions.lt("createdAt", date));
						logger.info("##########date max added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());

					}
				}

				/*	if(flightInvoiceFilter.getAmountRangeBooking() != null && flightInvoiceFilter.getAmountRangeBooking().getAmountMin() != null && flightInvoiceFilter.getAmountRangeBooking().getAmountMax() != null )
			{
				invoiceConjunction.add(Restrictions.ge("finalPrice", flightInvoiceFilter.getAmountRangeBooking().getAmountMin()));
				invoiceConjunction.add(Restrictions.lt("finalPrice", flightInvoiceFilter.getAmountRangeBooking().getAmountMax()));	
				logger.info("##########booking amount added to conjuction---"+flightInvoiceFilter.getAmountRangeBooking().getAmountMin()+"  to "+flightInvoiceFilter.getAmountRangeBooking().getAmountMax());
			}*/

				criteria.add(invoiceConjunction);
				criteria.addOrder(Order.desc("id"));
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			if(rowCount>0)
			{
				List<HotelOrderRow> list =null;
				if(flightInvoicePage.getMaxItems()==Page.ALL_ITEMS){
					criteria = session.createCriteria(HotelOrderRow.class);
					criteria.add(invoiceConjunction);
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					flightInvoicePage.setAvailableItems(list.size());
					flightInvoicePage.setAvailablePages(1);
				}
				else{	
					if(flightInvoicePage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % flightInvoicePage.getMaxItems() == 0)?(availableItems / flightInvoicePage.getMaxItems()):((availableItems / flightInvoicePage.getMaxItems()) + 1);
						flightInvoicePage.setAvailableItems(availableItems);
						flightInvoicePage.setAvailablePages(availablePages);
					} 
					int pageIndexDb = (flightInvoicePage.getCurrentPageIndex() > 1)?flightInvoicePage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * flightInvoicePage.getMaxItems();
					logger.info("setFirstResult-------"+itemIndex);

					if(itemIndex <= rowCount)
					{
						criteria = session.createCriteria(HotelOrderRow.class);
						criteria.add(invoiceConjunction);
						criteria.addOrder(Order.desc("id"));
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(flightInvoicePage.getMaxItems());
						list = criteria.list();
						logger.info("Reports size------"+list.size());					
					}
				}
				if(list!=null && list.size()>0){
					for (HotelOrderRow invoiceData:list){
						/*BigDecimal basePrice= invoiceData.getApiPrice().multiply(invoiceData.getApiToBaseExchangeRate()) ;
						BigDecimal taxes= invoiceData.getTaxes().multiply(invoiceData.getApiToBaseExchangeRate()) ;
						BigDecimal disCountInBooking= invoiceData.getDiscountAmount().multiply(invoiceData.getApiToBaseExchangeRate()).multiply(invoiceData.getBaseToBookingExchangeRate()) ;
						BigDecimal totalBasePrice=basePrice.add(invoiceData.getMarkupAmount());
						BigDecimal basePriceInBooking=totalBasePrice.multiply(invoiceData.getBaseToBookingExchangeRate());
						BigDecimal taxesInBooking=taxes.multiply(invoiceData.getBaseToBookingExchangeRate());//invoiceData.getFeeAmount().
						BigDecimal totalPrice=basePriceInBooking.add(taxesInBooking).subtract(disCountInBooking);
						invoiceData.setTaxes(taxes);
						invoiceData.setDiscountAmount(disCountInBooking);*/
						invoiceData.setFinalPrice(invoiceData.getFinalPrice().add(HotelOrderDao.calculateTotalserviceTax(invoiceData)).setScale(2, BigDecimal.ROUND_UP));
						
						invoiceData.setCheckIn(DateConversion.convertDateToStringToDateWithTIME(invoiceData.getCheckInDate()));
						invoiceData.setCheckOut(DateConversion.convertDateToStringToDateWithTIME(invoiceData.getCheckOutDate()));
						invoiceData.setBaseCurrency(invoiceData.getBookingCurrency());
						invoiceData.setCreatedDate(DateConversion.convertTimestampToString(invoiceData.getCreatedAt()));
						hotelOrderRowList.add(invoiceData);
					}
					flightInvoicePage.setHotelItems(hotelOrderRowList);
					if((flightInvoiceFilter.getEmail() != null && !flightInvoiceFilter.getEmail().equals("")))
					{								
						hotelOrderRowList = new ArrayList<HotelOrderRow>();
						for (HotelOrderRow hotelReport : flightInvoicePage.getHotelItems()) {
							if(flightInvoiceFilter.getEmail().equalsIgnoreCase(hotelReport.getOrderCustomer().getEmail())){
								hotelOrderRowList.add(hotelReport);
							}
						}	
						flightInvoicePage.setHotelItems(hotelOrderRowList);
						if(flightInvoicePage.isPagination())
						{
							availableItems = flightInvoicePage.getHotelItems().size();
							availablePages = (availableItems % flightInvoicePage.getMaxItems() == 0)?(availableItems / flightInvoicePage.getMaxItems()):((availableItems / flightInvoicePage.getMaxItems()) + 1);
							flightInvoicePage.setAvailableItems(availableItems);
							flightInvoicePage.setAvailablePages(availablePages);
						} 
					} 

				}
			}
			else
			{
				flightInvoicePage.setAvailableItems(0);
				flightInvoicePage.setAvailablePages(0);
				flightInvoicePage.setHotelItems(new ArrayList<HotelOrderRow>());
			}

		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally  {
			session.close(); 
		}

		return flightInvoicePage;
	}
	/*load all HotelOrderRow data for Invoice*/
	public  List<HotelOrderRow> getCompanyHotelCustomerOrderList(Company sessionCompany, User loginUser,  int reportType){

		List<HotelOrderRow> hotelOrderRowList=new ArrayList<HotelOrderRow>();
		List<String> companyIdList  = new ArrayList<String>();	
		logger.error("--------------getCompanyHotelCustomerOrderList----------------reportType-"+reportType);
		if(reportType == InvoiceData.CUSTOMER_INVOICE || reportType == InvoiceData.MY_INVOICE)
			companyIdList.add(String.valueOf(sessionCompany.getCompanyid()));
		else if(reportType == InvoiceData.AFFILIATE_INVOICE)
		{
			companyIdList = new CompanyDAO().getChildrenCompanyIds(sessionCompany.getCompanyid(), loginUser.getId());
			logger.error("--------------getCompanyHotelCustomerOrderList----------------companyIdList-"+companyIdList);		
		}		

		logger.error("--------------getCompanyHotelCustomerOrderList----------------companyIdList-"+companyIdList.size());

		Session sess = null;
		try{			
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = sess.createCriteria(HotelOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			reportConjunction.add(Restrictions.in("companyId",companyIdList));
			String bookStatus = "Confirmed";
			reportConjunction.add(Restrictions.eq("statusAction",bookStatus));//Confirmed
			criteria.add(reportConjunction);			
			List<HotelOrderRow> list = criteria.list();
			for (HotelOrderRow invoiceData:list){
				BigDecimal basePrice= invoiceData.getApiPrice().multiply(invoiceData.getApiToBaseExchangeRate()) ;
				BigDecimal taxes= invoiceData.getTaxes().multiply(invoiceData.getApiToBaseExchangeRate()) ;
				BigDecimal disCountInBooking= invoiceData.getDiscountAmount().multiply(invoiceData.getApiToBaseExchangeRate()).multiply(invoiceData.getBaseToBookingExchangeRate()) ;
				BigDecimal totalBasePrice=basePrice.add(invoiceData.getMarkupAmount());
				BigDecimal basePriceInBooking=totalBasePrice.multiply(invoiceData.getBaseToBookingExchangeRate());
				BigDecimal taxesInBooking=taxes.multiply(invoiceData.getBaseToBookingExchangeRate());//invoiceData.getFeeAmount().
				BigDecimal totalPrice=basePriceInBooking.add(taxesInBooking).subtract(disCountInBooking);
				invoiceData.setFinalPrice(invoiceData.getFinalPrice().add(HotelOrderDao.calculateTotalserviceTax(invoiceData)).setScale(2, BigDecimal.ROUND_UP));
				invoiceData.setTaxes(taxes);
				invoiceData.setDiscountAmount(disCountInBooking);
				invoiceData.setCheckIn(DateConversion.convertDateToStringToDateWithTIME(invoiceData.getCheckInDate()));
				invoiceData.setCheckOut(DateConversion.convertDateToStringToDateWithTIME(invoiceData.getCheckOutDate()));
				invoiceData.setBaseCurrency(invoiceData.getBookingCurrency());
				invoiceData.setCreatedDate(DateConversion.convertTimestampToString(invoiceData.getCreatedAt()));
				hotelOrderRowList.add(invoiceData);
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(sess!=null && sess.isOpen())
				sess.close(); 
		}
		return hotelOrderRowList;
	}




	public  HotelInvoiceData getHoteltOrderCustomerDetailsForInvoice(HotelOrderRow hotelOrderRow, int invoiceType, HashMap<String, BigDecimal> companyMarkUpCommissionsMap) {
		HotelOrderRow hotelOrderObj=null ;
		HotelInvoiceData hotelInvoiceObj=new HotelInvoiceData();
		try{
			String sql = "from HotelOrderRow hor where hor.orderReference=:orderReference";
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("orderReference",hotelOrderRow.getOrderReference());
			List<HotelOrderRow> list = query.list();			
			for (Iterator  iterator = list.iterator(); iterator.hasNext();){	
				hotelOrderObj= (HotelOrderRow)iterator.next(); 
				BigDecimal finalAmount = (companyMarkUpCommissionsMap.get("finalAmount") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("finalAmount");		
				BigDecimal payAmount = (companyMarkUpCommissionsMap.get("payAmount") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("payAmount");
				BigDecimal urProfit = (companyMarkUpCommissionsMap.get("urProfit") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("urProfit");		
				BigDecimal urMarkup = (companyMarkUpCommissionsMap.get("urMarkup") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("urMarkup");
				BigDecimal urCommission = (companyMarkUpCommissionsMap.get("urCommission") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("urCommission");		
				BigDecimal commissionShared = (companyMarkUpCommissionsMap.get("commissionShared") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("commissionShared");
				BigDecimal masterMarkup = (companyMarkUpCommissionsMap.get("masterMarkup") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("masterMarkup");		
				BigDecimal chlidMarkup = (companyMarkUpCommissionsMap.get("chlidMarkup") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("chlidMarkup");
				BigDecimal TDS = (companyMarkUpCommissionsMap.get("TDS") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("TDS");
				//booking price conversions...
				finalAmount = finalAmount.multiply(hotelOrderObj.getBaseToBookingExchangeRate());
				payAmount = payAmount.multiply(hotelOrderObj.getBaseToBookingExchangeRate());
				urProfit = urProfit.multiply(hotelOrderObj.getBaseToBookingExchangeRate());
				urMarkup = urMarkup.multiply(hotelOrderObj.getBaseToBookingExchangeRate());
				urCommission = urCommission.multiply(hotelOrderObj.getBaseToBookingExchangeRate());
				commissionShared = commissionShared.multiply(hotelOrderObj.getBaseToBookingExchangeRate());
				masterMarkup = masterMarkup.multiply(hotelOrderObj.getBaseToBookingExchangeRate());
				chlidMarkup = chlidMarkup.multiply(hotelOrderObj.getBaseToBookingExchangeRate());
				TDS = TDS.multiply(hotelOrderObj.getBaseToBookingExchangeRate());
				BigDecimal gstOnInvoiceMarkup = new BigDecimal("0.00");
				BigDecimal invoiceMarkup = hotelOrderObj.getMarkupAmount();
				BigDecimal basePrice= hotelOrderObj.getApiPrice().multiply(hotelOrderObj.getApiToBaseExchangeRate()) ;
				basePrice = basePrice.add(invoiceMarkup);
				basePrice= basePrice.multiply(hotelOrderObj.getBaseToBookingExchangeRate()) ;
				BigDecimal taxes= hotelOrderObj.getTaxes().multiply(hotelOrderObj.getApiToBaseExchangeRate()) ;
				BigDecimal disCountInBooking= hotelOrderObj.getDiscountAmount().multiply(hotelOrderObj.getApiToBaseExchangeRate()).multiply(hotelOrderObj.getBaseToBookingExchangeRate()) ;
				BigDecimal taxesInBooking=taxes.multiply(hotelOrderObj.getBaseToBookingExchangeRate());//hotelOrderObj.getFeeAmount().
				BigDecimal totalPrice=hotelOrderObj.getFinalPrice().add(HotelOrderDao.calculateTotalserviceTax(hotelOrderObj));
					if(invoiceType != InvoiceData.CUSTOMER_INVOICE)
				{
					invoiceMarkup = masterMarkup;
					HotelOrderRowMarkup hotelOrderRowMarkup=new HotelOrderDao().getCompanyMarkup(hotelOrderObj.getCompanyId(), hotelOrderObj.getId());
					BigDecimal totalMarkupWithGuestCount=new BigDecimal("0.0");
					String checkInDate=DateConversion.convertDateToStringToDate(hotelOrderObj.getCheckInDate());
					String checkOutDate=DateConversion.convertDateToStringToDate(hotelOrderObj.getCheckOutDate());
					int days=CommonUtil.getNoofStayDays(checkInDate, checkOutDate);
					int totalGuestMarkupCount=days*hotelOrderObj.getTotalGuest();
					if(hotelOrderRowMarkup!=null){
						totalMarkupWithGuestCount=hotelOrderRowMarkup.getMarkUp().multiply(new BigDecimal(totalGuestMarkupCount));
					}
					hotelInvoiceObj.setTotAmount(hotelOrderObj.getFinalPrice().add(HotelOrderDao.calculateTotalserviceTax(hotelOrderObj)).setScale(2, BigDecimal.ROUND_UP));		
					hotelInvoiceObj.setTotBeforeGst(hotelOrderObj.getFinalPrice().add(HotelOrderDao.calculateTotalserviceTax(hotelOrderObj)).subtract(totalMarkupWithGuestCount).setScale(2, BigDecimal.ROUND_UP));
					hotelInvoiceObj.setTotAgentComm(totalMarkupWithGuestCount.setScale(2, BigDecimal.ROUND_UP));
				 }		
				else{
					hotelInvoiceObj.setTotBeforeGst(basePrice.setScale(2, BigDecimal.ROUND_UP));//totalPrice.setScale(2, BigDecimal.ROUND_UP)
				}
			
				hotelInvoiceObj.setCheckInDate(DateConversion.convertDateToStringToDateWithTIME(hotelOrderObj.getCheckInDate()));
				hotelInvoiceObj.setCheckOutDate(DateConversion.convertDateToStringToDateWithTIME(hotelOrderObj.getCheckOutDate()));
				hotelInvoiceObj.setTax(taxesInBooking.setScale(2, BigDecimal.ROUND_UP));
				hotelInvoiceObj.setDiscountAmount(disCountInBooking.setScale(2, BigDecimal.ROUND_UP));
				hotelInvoiceObj.setPrice(totalPrice.setScale(2, BigDecimal.ROUND_UP));
				hotelInvoiceObj.setUrMarkup(urMarkup.setScale(2, BigDecimal.ROUND_UP));
				hotelInvoiceObj.setChlidMarkup(chlidMarkup.setScale(2, BigDecimal.ROUND_UP));
				hotelInvoiceObj.setCusAddress(hotelOrderObj.getOrderCustomer().getAddress());
				hotelInvoiceObj.setInvNo(hotelOrderObj.getInvoiceNo());
				hotelInvoiceObj.setConsultant(hotelOrderObj.getCreatedBy());
				hotelInvoiceObj.setActCode("3000/P001");
				hotelInvoiceObj.setBookNo("000020408");
				hotelInvoiceObj.setYourRef(hotelOrderObj.getOrderReference());
				hotelInvoiceObj.setDate(DateConversion.convertTimestampToString(hotelOrderObj.getCreatedAt()));
				hotelInvoiceObj.setMobile(hotelOrderObj.getOrderCustomer().getMobile());
				hotelInvoiceObj.setTel(hotelOrderObj.getOrderCustomer().getPhone());
				hotelInvoiceObj.setAttn(hotelOrderObj.getOrderCustomer().getFirstName()+hotelOrderObj.getOrderCustomer().getLastName());
				hotelInvoiceObj.setCurrency(hotelOrderObj.getBookingCurrency());
				hotelInvoiceObj.setAgentName(hotelOrderObj.getCreatedBy());
				GstPropertiesFile gstPropertiesFile =new GstPropertiesFile();
				hotelInvoiceObj.setCompanysGstOn(gstPropertiesFile.getGstSwitchonValue());
			}
			//logger.info("------------------------"+hotelOrderObj.getCompanyId());
			hotelInvoiceObj.setHotelOrderRoomInfo(getHotelOrderRoomInfoList(hotelOrderRow.getId(), session));
			hotelInvoiceObj.setTxDetails(getPaymentTxDetails(hotelOrderRow.getOrderReference(), session));
			hotelInvoiceObj.setAgentWalletTxDetails(getAgentPaymentTxWalletDetails(hotelOrderRow.getOrderReference(),hotelOrderObj.getUserId(), session));
			hotelInvoiceObj.setUserDetails(getAgentAddress(hotelOrderObj.getUserId(),session));
			hotelInvoiceObj.setCompanyAddress(getCompanyAddressByCompanyId(hotelOrderRow.getCompanyId(), session));

			if(hotelInvoiceObj.getAgentWalletTxDetails()!=null && hotelInvoiceObj.getAgentWalletTxDetails().size()>0){
				for(WalletAmountTranferHistory  tdsFromWalletHistory:hotelInvoiceObj.getAgentWalletTxDetails()){
					if(tdsFromWalletHistory.getAction()!=null)
					{
						if(tdsFromWalletHistory.getAction().equalsIgnoreCase("TDS on commission")){
							hotelInvoiceObj.setTotalTDS(tdsFromWalletHistory.getAmount());
							//logger.info("TOt TDS---"+tdsFromWalletHistory.getAmount());
						}
						if(tdsFromWalletHistory.getAction().equalsIgnoreCase("Hotel Commission Added")){
							hotelInvoiceObj.setTotAgentComm(tdsFromWalletHistory.getAmount());
							//logger.info("TOt comm---"+tdsFromWalletHistory.getAmount());
						}
					}
				}
				if(hotelInvoiceObj.getTotAgentComm()!=null && hotelInvoiceObj.getTotalTDS()!=null ){
					hotelInvoiceObj.setFinalCommWithTdsDeduct(hotelInvoiceObj.getTotAgentComm().subtract(hotelInvoiceObj.getTotalTDS()));
					//logger.info("final comm---"+hotelInvoiceObj.getFinalCommWithTdsDeduct());
				}
			} 
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return hotelInvoiceObj;

	}
 
	/* load all HotelOrderRow data for generate  Invoice */
	public  HotelInvoiceData getHoteltOrderCustomerDetailsForInvoice(FlightInvoicePage flightInvoicePage){
		HotelOrderRow hotelOrderObj=null ;
		HotelInvoiceData hotelInvoiceObj=new HotelInvoiceData();

		try{
			String sql = "from HotelOrderRow hor where hor.orderReference=:orderReference";
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("orderReference",flightInvoicePage.getOrderReference());
			hotelOrderObj = (HotelOrderRow) query.uniqueResult();
			if(hotelOrderObj!=null){
				BigDecimal basePrice= hotelOrderObj.getApiPrice().multiply(hotelOrderObj.getApiToBaseExchangeRate()) ;
				//logger.info("basePrice---------"+basePrice);
				BigDecimal taxes= hotelOrderObj.getTaxes().multiply(hotelOrderObj.getApiToBaseExchangeRate()) ;
				//logger.info("taxes---------"+taxes);
				BigDecimal totalBasePrice=basePrice.add(hotelOrderObj.getMarkupAmount());
				BigDecimal disCountInBooking= hotelOrderObj.getDiscountAmount().multiply(hotelOrderObj.getApiToBaseExchangeRate()).multiply(hotelOrderObj.getBaseToBookingExchangeRate()) ;
				//logger.info("totalBasePrice---------"+totalBasePrice);
				BigDecimal basePriceInBooking=totalBasePrice.multiply(hotelOrderObj.getBaseToBookingExchangeRate());
				//logger.info("basePriceInBooking---------"+basePriceInBooking);
				BigDecimal taxesInBooking=taxes.multiply(hotelOrderObj.getBaseToBookingExchangeRate());//hotelOrderObj.getFeeAmount().
				//logger.info("taxesInBooking---------"+taxesInBooking);
				BigDecimal totalPrice=basePriceInBooking.add(taxesInBooking).subtract(disCountInBooking);
				//logger.info("total booking price---------"+totalPrice);
				hotelInvoiceObj.setCusAddress(hotelOrderObj.getOrderCustomer().getAddress());
				hotelInvoiceObj.setInvNo(hotelOrderObj.getInvoiceNo());
				hotelInvoiceObj.setConsultant(hotelOrderObj.getCreatedBy());
				hotelInvoiceObj.setActCode("3000/P001");
				hotelInvoiceObj.setBookNo("000020408");
				hotelInvoiceObj.setYourRef(hotelOrderObj.getOrderReference());
				hotelInvoiceObj.setDate(DateConversion.convertTimestampToString(hotelOrderObj.getCreatedAt()));
				hotelInvoiceObj.setMobile(hotelOrderObj.getOrderCustomer().getMobile());
				hotelInvoiceObj.setTel(hotelOrderObj.getOrderCustomer().getPhone());
				//hotelInvoiceObj.setPrice(hotelOrderObj.getTotalPrice());
				hotelInvoiceObj.setAttn(hotelOrderObj.getOrderCustomer().getFirstName()+hotelOrderObj.getOrderCustomer().getLastName());
				hotelInvoiceObj.setTotBeforeGst(hotelOrderObj.getFinalPrice().add(HotelOrderDao.calculateTotalserviceTax(hotelOrderObj)).setScale(2, BigDecimal.ROUND_UP));//totalPrice.setScale(2, BigDecimal.ROUND_UP)
				hotelInvoiceObj.setCheckInDate(DateConversion.convertDateToStringToDateWithTIME(hotelOrderObj.getCheckInDate()));
				hotelInvoiceObj.setCheckOutDate(DateConversion.convertDateToStringToDateWithTIME(hotelOrderObj.getCheckOutDate()));
				hotelInvoiceObj.setCurrency(hotelOrderObj.getBookingCurrency());
				hotelInvoiceObj.setTax(taxesInBooking.setScale(2, BigDecimal.ROUND_UP));
				hotelInvoiceObj.setDiscountAmount(disCountInBooking.setScale(2, BigDecimal.ROUND_UP));
				hotelInvoiceObj.setPrice(basePriceInBooking.setScale(2, BigDecimal.ROUND_UP));
				/* GST Calculation.........START......
				BigDecimal hundred = new BigDecimal(100);
				BigDecimal multiplyValue = new BigDecimal(6);
				BigDecimal percentageFactor = totalPrice.divide(hundred);
				BigDecimal gstAmount=percentageFactor.multiply(multiplyValue);
				hotelInvoiceObj.setTotGst(gstAmount.setScale(2, BigDecimal.ROUND_UP));
				hotelInvoiceObj.setTotWithGst((totalPrice.add(gstAmount)).setScale(2, BigDecimal.ROUND_UP));
				GST Calculation.......END........*/
				hotelInvoiceObj.setAgentName(hotelOrderObj.getCreatedBy());
				GstPropertiesFile gstPropertiesFile =new GstPropertiesFile();
				hotelInvoiceObj.setCompanysGstOn(gstPropertiesFile.getGstSwitchonValue());

			}
			//logger.info("------------------------"+hotelOrderObj.getCompanyId());
			hotelInvoiceObj.setHotelOrderRoomInfo(getHotelOrderRoomInfoList(flightInvoicePage.getId(), session));
			hotelInvoiceObj.setTxDetails(getPaymentTxDetails(flightInvoicePage.getOrderReference(), session));
			hotelInvoiceObj.setAgentWalletTxDetails(getAgentPaymentTxWalletDetails(flightInvoicePage.getOrderReference(),hotelOrderObj.getUserId(), session));
			hotelInvoiceObj.setUserDetails(getAgentAddress(hotelOrderObj.getUserId(),session));
			hotelInvoiceObj.setCompanyAddress(getCompanyAddressByCompanyId(flightInvoicePage.getCompanyId(), session));
			if(hotelInvoiceObj.getAgentWalletTxDetails()!=null && hotelInvoiceObj.getAgentWalletTxDetails().size()>0){
				for(WalletAmountTranferHistory  tdsFromWalletHistory:hotelInvoiceObj.getAgentWalletTxDetails()){
					if(tdsFromWalletHistory.getAction()!=null)
					{
						if(tdsFromWalletHistory.getAction().equalsIgnoreCase("TDS on commission")){
							hotelInvoiceObj.setTotalTDS(tdsFromWalletHistory.getAmount());
							//logger.info("TOt TDS---"+tdsFromWalletHistory.getAmount());
						}
						if(tdsFromWalletHistory.getAction().equalsIgnoreCase("Hotel Commission Added")){
							hotelInvoiceObj.setTotAgentComm(tdsFromWalletHistory.getAmount());
							//logger.info("TOt comm---"+tdsFromWalletHistory.getAmount());
						}
					}

				}
				if(hotelInvoiceObj.getTotAgentComm()!=null && hotelInvoiceObj.getTotalTDS()!=null ){
					hotelInvoiceObj.setFinalCommWithTdsDeduct(hotelInvoiceObj.getTotAgentComm().subtract(hotelInvoiceObj.getTotalTDS()));
					//logger.info("final comm---"+hotelInvoiceObj.getFinalCommWithTdsDeduct());
				}
			} 
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return hotelInvoiceObj;

	}

	/* Hotel room info Particulars for Invoice based on hotel order id */
	public  List<HotelOrderRoomInfo> getHotelOrderRoomInfoList(Long id,Session session ){
		String sql = "from HotelOrderRoomInfo hori where hori.hotelOrderRow.id=:id";
		Query query = session.createQuery(sql);
		query.setParameter("id",id);
		List<HotelOrderRoomInfo> list = query.list();
		for(HotelOrderRoomInfo hotelOrderRoomInfo:list){
			hotelOrderRoomInfo.setConvertDate(DateConversion.convertTimestampToString(hotelOrderRoomInfo.getCreatedAt()));
			roomIds.add(hotelOrderRoomInfo.getId());
		}
		return list;

	}

	/* Hotel order customer Tx details for Invoice */
	public  List<PaymentTransaction> getPaymentTxDetails(String  orderId,Session session ){
		List<PaymentTransaction> txList=new ArrayList<PaymentTransaction>();
		String sql = "from PaymentTransaction pt where pt.api_transaction_id=:api_transaction_id";
		Query query = session.createQuery(sql);
		query.setParameter("api_transaction_id", orderId);
		List<PaymentTransaction> list = query.list();
		logger.info("------------TX Details-------------"+list.size());
		for(PaymentTransaction paymentTransaction:list){
			paymentTransaction.setConvertDate(DateConversion.convertTimestampToString(paymentTransaction.getCreatedAt()));
			paymentTransaction.setAmount(paymentTransaction.getAmount().setScale(2, BigDecimal.ROUND_UP));
			txList.add(paymentTransaction);
		}

		return txList;

	}

	/*HotelOrderGuest details for Invoice */
	public  List<HotelOrderGuest> getHotelGuestInfo(){
		List<HotelOrderGuest> hotelOrderGuestList=new ArrayList<HotelOrderGuest>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			for(Long room_id:roomIds){
				String sql = "from HotelOrderGuest hog where hog.roomInfo.id=:room_id";
				Query query = session.createQuery(sql);
				query.setParameter("room_id", room_id) ;
				List<HotelOrderGuest> list = query.list();
				for(HotelOrderGuest hotelOrderGuestObj:list){
					hotelOrderGuestObj.setConvertDate(DateConversion.convertTimestampToString(hotelOrderGuestObj.getCreatedAt()));
					hotelOrderGuestList.add(hotelOrderGuestObj);

				} 
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return hotelOrderGuestList;
	}


	/*  agent wallet Tx details for  generate Invoice */
	public  List<WalletAmountTranferHistory> getAgentPaymentTxWalletDetails (String orderId,String userId,Session session ){
		List<WalletAmountTranferHistory> walletTxList=new ArrayList<WalletAmountTranferHistory>();

		String sql = "from WalletAmountTranferHistory wt where wt.actionId=:action_id and wt.userId=:user_id"; 
		Query query = session.createQuery(sql);
		query.setParameter("action_id", orderId);
		query.setParameter("user_id",  Integer.parseInt(userId));
		List<WalletAmountTranferHistory> list = query.list();
		for(WalletAmountTranferHistory walletTxHistory:list){
			walletTxHistory.setConvertDate(DateConversion.convertTimestampToString(walletTxHistory.getCreatedAt()));
			walletTxHistory.setClosingBalance(walletTxHistory.getClosingBalance().setScale(2, BigDecimal.ROUND_UP));
			walletTxHistory.setOpeningBalance(walletTxHistory.getOpeningBalance().setScale(2, BigDecimal.ROUND_UP));
			walletTxHistory.setParentClosingBalance(walletTxHistory.getParentClosingBalance().setScale(2, BigDecimal.ROUND_UP));
			walletTxHistory.setParentOpeningBalance(walletTxHistory.getParentOpeningBalance().setScale(2, BigDecimal.ROUND_UP));
			walletTxHistory.setAmount(walletTxHistory.getAmount().setScale(2, BigDecimal.ROUND_UP));
			walletTxList.add(walletTxHistory);
		}
		return walletTxList;
	}

	/*  agent  details for   Invoice  Address*/
	public  List<User> getAgentAddress(String userId,Session session){
		//session = HibernateUtil.getSessionFactory().openSession();
		String sql = "from User u where u.id=:id";
		Query query = session.createQuery(sql);
		query.setParameter("id", Integer.parseInt(userId));
		List<User> list = query.list();
		return list;
	}
	/*  comapny  details for   Invoice  Address*/
	public   Company   getCompanyAddressByCompanyId(String companyId,Session session){
		logger.info("--------------companyId---for company Address---------"+companyId);
		Company address=null;
		if(companyId!=null){
			//session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from Company com  where com.companyid=:companyid";
			Query query = session.createQuery(sql);
			query.setParameter("companyid", Integer.parseInt(companyId));
			address = (Company) query.uniqueResult();
			logger.info("--------------company Address wiy company id---------------"+address.getCompanyname());
		}
		return address;
	}

	/* load all  company bookings from database based on company type while searching---------*/
	public  List<HotelOrderRow> getCompanyInvoiceByComapnyType(User userSessionObj,Company companySessionObj,InvoiceFilter invoiceFilter){

		List<Company> newCompaniesList= new ArrayList<Company>();
		String sql=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(userSessionObj!=null && invoiceFilter!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					sql="from Company com  where com.super_company_userid=:supercompanyuserid";
					Query  companyQuery = session.createQuery(sql);
					companyQuery.setParameter("supercompanyuserid", companySessionObj.getCompany_userid());
					List companiesList = companyQuery.list();
					if(invoiceFilter.getFilterCompanyType().equalsIgnoreCase("all")){
						for (Iterator  userIterator = companiesList.iterator(); userIterator.hasNext();){
							Company companyObj= (Company)userIterator.next(); 
							/*	if(!companyObj.getCompany_userid().equals(companySessionObj.getCompany_userid())) */
							newCompaniesList.add(companyObj);
						}
					}

					else if(invoiceFilter.getFilterCompanyType().equalsIgnoreCase("dis")){
						for (Iterator  userIterator = companiesList.iterator(); userIterator.hasNext();){
							Company companyObj= (Company)userIterator.next(); 
							if(companyObj.getCompanyRole().isDistributor()){
								sql="from Company com  where com.parent_company_userid=:parentcompanyuserid or com.company_userid=:companyuserid";
								Query  disQuery = session.createQuery(sql);
								disQuery.setParameter("parentcompanyuserid", companyObj.getCompany_userid());
								disQuery.setParameter("companyuserid", companyObj.getCompany_userid());
								newCompaniesList.addAll(disQuery.list());
							}
						}
					}

					else if(invoiceFilter.getFilterCompanyType().equalsIgnoreCase("agency")){
						for (Iterator  userIterator = companiesList.iterator(); userIterator.hasNext();){
							Company companyObj= (Company)userIterator.next(); 
							if(companyObj.getCompanyRole().isAgent()){
								newCompaniesList.add(companyObj);
							}
						}
					}
				}

				else if(userSessionObj.getUserrole_id().isUsermode() && companySessionObj.getCompanyRole().isDistributor()){
					sql="from Company com  where com.parent_company_userid=:parentcompanyuserid or com.company_userid=:companyuserid";
					Query  companyQuery = session.createQuery(sql);
					companyQuery.setParameter("parentcompanyuserid", companySessionObj.getCompany_userid());
					companyQuery.setParameter("companyuserid", companySessionObj.getCompany_userid());
					List companiesList = companyQuery.list();

					if(invoiceFilter.getFilterCompanyType().equalsIgnoreCase("all")){
						for (Iterator  userIterator = companiesList.iterator(); userIterator.hasNext();){
							Company companyObj= (Company)userIterator.next(); 
							/*	if(!companyObj.getCompany_userid().equals(companySessionObj.getCompany_userid())) */
							newCompaniesList.add(companyObj);
						}
					}

					else if(invoiceFilter.getFilterCompanyType().equalsIgnoreCase("agency")){
						for (Iterator  userIterator = companiesList.iterator(); userIterator.hasNext();){
							Company companyObj= (Company)userIterator.next(); 
							if(!companyObj.getCompany_userid().equals(companySessionObj.getCompany_userid())) 
								newCompaniesList.add(companyObj);
						}
					}
				}

				else if(userSessionObj.getUserrole_id().isUsermode() && companySessionObj.getCompanyRole().isAgent()){
					sql="from Company com  where com.company_userid=:companyuserid";
					Query  companyQuery = session.createQuery(sql);
					companyQuery.setParameter("companyuserid", companySessionObj.getCompany_userid());
					List companiesList = companyQuery.list();
					if(invoiceFilter.getFilterCompanyType().equalsIgnoreCase("all")){
						for (Iterator  userIterator = companiesList.iterator(); userIterator.hasNext();){
							Company companyObj= (Company)userIterator.next(); 
							/*	if(!companyObj.getCompany_userid().equals(companySessionObj.getCompany_userid())) */
							newCompaniesList.add(companyObj);
						}
					}
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
		return getCompanyInvoicesWithUserId(newCompaniesList,getUserIdsForCompanyInvoice(newCompaniesList),invoiceFilter,companySessionObj);
	}
	/* this method for get CompanyBookings by passing userids*/
	public List<HotelOrderRow> getCompanyInvoicesWithUserId(List<Company> directCompaniesList, List<User> userIds,InvoiceFilter invoiceFilter,Company sessionObj){
		String filterQuery=null;
		List<HotelOrderRow>  reportData_list=new ArrayList<HotelOrderRow>();
		Query query = null; 
		StringBuilder companyIdsBuilder = new StringBuilder();
		logger.info("------fillter Company type------"+invoiceFilter.getFilterCompanyType());
		List<String> companyIds=new ArrayList<>();
		if(directCompaniesList!=null && directCompaniesList.size()>0){
			for(Company c:directCompaniesList){
				companyIds.add(String.valueOf(c.getCompanyid()));
			}
		} 
		if(directCompaniesList!=null && directCompaniesList.size()>0){
			for(int i=0;i<directCompaniesList.size();i++){
				Company companyObj= (Company)directCompaniesList.get(i);
				if(i == directCompaniesList.size()-1)
					companyIdsBuilder.append("'"+companyObj.getCompanyid()+"'");
				else
					companyIdsBuilder.append("'"+companyObj.getCompanyid()+"',"); 
			}
		}
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(invoiceFilter.getFilterCompanyType().equals("mine")){
				if(!invoiceFilter.getFromDate().equals("") && !invoiceFilter.getToDate().equals("")){
					//filterQuery="from HotelOrderRow hor where company_id in ("+sessionObj.getCompanyid()+") and date(check_in_date) between '"+invoiceFilter.getFromDate()+"' and '"+invoiceFilter.getToDate()+"'";
					filterQuery="select * from hotel_order_row where company_id in ("+sessionObj.getCompanyid()+") and date(created_at) between '"+invoiceFilter.getFromDate()+"' and '"+invoiceFilter.getToDate()+"'"; 
					reportData_list=getWeekorMonthInvoiceReport(filterQuery, session,null);
				}
				else{
					if(invoiceFilter.getPeriod().equals("week")){
						filterQuery="select * from hotel_order_row where company_id in ("+sessionObj.getCompanyid()+") and date(created_at) between  DATE_ADD(CURDATE() ,INTERVAL -6 day) and CURDATE()";
					}
					else if(invoiceFilter.getPeriod().equals("month")){
						filterQuery="select * from hotel_order_row where company_id in ("+sessionObj.getCompanyid()+") and date(created_at) between  DATE_ADD(CURDATE() ,INTERVAL -29 day) and CURDATE()";
					}
					else{
						filterQuery="select * from hotel_order_row where company_id in ("+sessionObj.getCompanyid()+")";
					}
					reportData_list=getWeekorMonthInvoiceReport(filterQuery, session,null);
				}
			}
			else if(!invoiceFilter.getFromDate().equals("") && !invoiceFilter.getToDate().equals("")){
				filterQuery="select * from hotel_order_row where company_id in ("+companyIdsBuilder+") and date(created_at) between '"+invoiceFilter.getFromDate()+"' and '"+invoiceFilter.getToDate()+"'"; 
				//filterQuery="from HotelOrderRow hor where hor.companyId in (:companyIds) and date(hor.createdAt) between between :fromDate and :toDate"; 
				reportData_list=getWeekorMonthInvoiceReport(filterQuery, session,null);
			}
			else{
				if(invoiceFilter.getPeriod().equals("week")){
					filterQuery="select * from hotel_order_row where  company_id in ("+companyIdsBuilder+") and date(created_at) between  DATE_ADD(CURDATE() ,INTERVAL -6 day) and CURDATE()";
				}
				else if(invoiceFilter.getPeriod().equals("month")){
					filterQuery="select * from hotel_order_row where company_id in ("+companyIdsBuilder+") and date(created_at) between  DATE_ADD(CURDATE() ,INTERVAL -29 day) and CURDATE()";
				}
				else{
					filterQuery="select * from hotel_order_row where company_id in ("+companyIdsBuilder+")";
				}
				reportData_list=getWeekorMonthInvoiceReport(filterQuery, session,null);
			} 
		}catch(HibernateException e){
			logger.error("-------------------HibernateException--------------------"+e.getMessage());
		}
		catch(Exception e){
			logger.error("-------------------Exception--------------------"+e.getMessage());
		}	
		finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return reportData_list ;
	}
	public List<HotelOrderRow> getWeekorMonthInvoiceReport(String sql,Session session,Query query){
		List<HotelOrderRow>  reportData_list=new ArrayList<HotelOrderRow>();
		List<HotelOrderRow> list=null;
		if(sql!=null){
			SQLQuery sqlQuery =session.createSQLQuery(sql);
			sqlQuery.addEntity(HotelOrderRow.class);
			list = sqlQuery.list();
		}
		else{
			list = query.list();
		}
		if(list!=null && list.size()>0){
			for (HotelOrderRow invoiceData:list){
				BigDecimal basePrice= invoiceData.getApiPrice().multiply(invoiceData.getApiToBaseExchangeRate()) ;
				BigDecimal taxes= invoiceData.getTaxes().multiply(invoiceData.getApiToBaseExchangeRate()) ;
				BigDecimal totalBasePrice=basePrice.add(invoiceData.getMarkupAmount());
				BigDecimal basePriceInBooking=totalBasePrice.multiply(invoiceData.getBaseToBookingExchangeRate());
				BigDecimal taxesInBooking=taxes.multiply(invoiceData.getBaseToBookingExchangeRate());
				BigDecimal totalPrice=invoiceData.getFeeAmount().add(basePriceInBooking).add(taxesInBooking);
				invoiceData.setFinalPrice(invoiceData.getFinalPrice().add(HotelOrderDao.calculateTotalserviceTax(invoiceData)).setScale(2, BigDecimal.ROUND_UP));//totalPrice.setScale(2, BigDecimal.ROUND_UP)
				invoiceData.setCheckIn(DateConversion.convertDateToStringToDateWithTIME(invoiceData.getCheckInDate()));
				invoiceData.setCheckOut(DateConversion.convertDateToStringToDateWithTIME(invoiceData.getCheckOutDate()));
				invoiceData.setBaseCurrency(invoiceData.getBookingCurrency());
				reportData_list.add(invoiceData);
			} 
		}
		return reportData_list; 
	}

	/* this method for get userIDs by passing  company id*/
	public List<User> getUserIdsForCompanyInvoice(List<Company> companiesList){
		List<User> userIds =new ArrayList<User>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(companiesList!=null && companiesList.size()>0){
				logger.info("------companiesList---size--------"+companiesList.size());
				for(Company companyObj:companiesList){
					logger.info("------company Ids-----------"+companyObj.getCompanyid()+"-------company user id---------------"+companyObj.getCompany_userid());
					String useridsSql="from User u where u.Companyid=:companyid";
					Query userQuery = session.createQuery(useridsSql);
					userQuery.setParameter("companyid", companyObj.getCompanyid());
					List<User> userList =userQuery.list();
					if(userList!=null && userList.size()>0){
						userIds.addAll(userList);
					}
					/*for (Iterator  userIterator = userList.iterator(); userIterator.hasNext();){
						User userObj= (User)userIterator.next(); 
						userIds.add(userObj);

					}*/
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
		return userIds;
	}



	public HashMap<String, BigDecimal> getHotelOrderInvoiceDetails(String orderId, User invoiceUser, User bookingUser)
			throws HibernateException {		
		Session sess = null;
		HashMap<String, BigDecimal> companyMarkUpCommissionsMap = new HashMap<String, BigDecimal>();
		List<WalletAmountTranferHistory> historyList=null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			String sql = "select * from walletamount_tranfer_history where action_id='"+orderId+"' order by wallet_id";
			SQLQuery query = sess.createSQLQuery(sql);
			query.addEntity(WalletAmountTranferHistory.class);
			historyList = query.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			sess.close(); 
		}		
		LinkedHashSet<Integer> compIdList=new LinkedHashSet<Integer>();
		for(WalletAmountTranferHistory walletAmountTranferHistory:historyList){
			compIdList.add(walletAmountTranferHistory.getUserId());
		}
		ArrayList<Integer> userIdList=new ArrayList<Integer>();
		userIdList.addAll(compIdList);
		BigDecimal finalAmount = new BigDecimal("0.0");
		BigDecimal payAmount = new BigDecimal("0.0");
		BigDecimal urProfit = new BigDecimal("0.0");
		BigDecimal urMarkup = new BigDecimal("0.0");
		BigDecimal urCommission = new BigDecimal("0.0");
		BigDecimal commissionShared = new BigDecimal("0.0");
		BigDecimal masterMarkup = new BigDecimal("0.0");
		BigDecimal chlidMarkup = new BigDecimal("0.0");
		BigDecimal TDS = new BigDecimal("0.0");
		for(WalletAmountTranferHistory walletAmountTranferHistory:historyList){
			if(walletAmountTranferHistory.getAction().equalsIgnoreCase("HotelBooking Initiated")){
				finalAmount = walletAmountTranferHistory.getAmount();					  
			}
			if(walletAmountTranferHistory.getUserId() == invoiceUser.getId())
			{
				if(walletAmountTranferHistory.getAction().equalsIgnoreCase("Hotel Markup")){
					urMarkup=walletAmountTranferHistory.getAmount();
				}
				if(walletAmountTranferHistory.getAction().equalsIgnoreCase("Hotel Commission Added")){
					urCommission=walletAmountTranferHistory.getAmount();
				}
				if(walletAmountTranferHistory.getAction().equalsIgnoreCase("Hotel Commission Shared")){
					commissionShared=walletAmountTranferHistory.getAmount();
				}
				if(walletAmountTranferHistory.getAction().equalsIgnoreCase("TDS on commission")){
					TDS=walletAmountTranferHistory.getAmount();
				}
			}
			if(walletAmountTranferHistory.getAction().equalsIgnoreCase("Hotel Markup") && walletAmountTranferHistory.getUserId() < invoiceUser.getId())
			{
				masterMarkup = masterMarkup.add(walletAmountTranferHistory.getAmount());
			}
			if(walletAmountTranferHistory.getAction().equalsIgnoreCase("Hotel Markup") && walletAmountTranferHistory.getUserId() > invoiceUser.getId())
			{				
				chlidMarkup = chlidMarkup.add(walletAmountTranferHistory.getAmount());				
			}
		}
		payAmount=finalAmount.subtract(chlidMarkup.add(urMarkup)).add(TDS);
		urProfit= (urMarkup.add(urCommission)).subtract(commissionShared).subtract(TDS);
		try{
			finalAmount = finalAmount.setScale(2,
					BigDecimal.ROUND_HALF_UP);
			commissionShared = commissionShared.setScale(2,
					BigDecimal.ROUND_HALF_UP);
			urCommission = urCommission.setScale(2,
					BigDecimal.ROUND_HALF_UP);
			urMarkup = urMarkup.setScale(2,
					BigDecimal.ROUND_HALF_UP);
			masterMarkup = masterMarkup.setScale(2,
					BigDecimal.ROUND_HALF_UP);
			chlidMarkup = chlidMarkup.setScale(2,
					BigDecimal.ROUND_HALF_UP);
			payAmount = payAmount.setScale(2,
					BigDecimal.ROUND_HALF_UP);
			urProfit = urProfit.setScale(2,
					BigDecimal.ROUND_HALF_UP);
			TDS = TDS.setScale(2,
					BigDecimal.ROUND_HALF_UP);

		} catch (Exception e) {
			finalAmount = new BigDecimal(0).setScale(0);
			commissionShared = new BigDecimal(0).setScale(0);
			urCommission = new BigDecimal(0).setScale(0);
			urMarkup = new BigDecimal(0).setScale(0);
			masterMarkup = new BigDecimal(0).setScale(0);
			chlidMarkup = new BigDecimal(0).setScale(0);
			payAmount = new BigDecimal(0).setScale(0);
			urProfit = new BigDecimal(0).setScale(0);
			TDS = new BigDecimal(0).setScale(0);			
		}		
		companyMarkUpCommissionsMap.put("finalAmount", finalAmount);
		companyMarkUpCommissionsMap.put("payAmount", payAmount);
		companyMarkUpCommissionsMap.put("urProfit", urProfit);
		companyMarkUpCommissionsMap.put("urMarkup", urMarkup);
		companyMarkUpCommissionsMap.put("urCommission", urCommission);
		companyMarkUpCommissionsMap.put("commissionShared", commissionShared);
		companyMarkUpCommissionsMap.put("masterMarkup", masterMarkup);
		companyMarkUpCommissionsMap.put("chlidMarkup", chlidMarkup);
		companyMarkUpCommissionsMap.put("TDS", TDS);
		return companyMarkUpCommissionsMap;
	}
	public HotelOrderRow getHotelOrderRowDetails(Long id) {
		// TODO Auto-generated method stub
		Session sess = null;
		HotelOrderRow hotelOrderRow=null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=sess.createCriteria(HotelOrderRow.class);
			criteria.add(Restrictions.eq("id", id));
			hotelOrderRow = (HotelOrderRow) criteria.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			sess.close(); 
		}		
		return hotelOrderRow;
	}
}
