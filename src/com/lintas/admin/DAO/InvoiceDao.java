package com.lintas.admin.DAO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.isl.admin.filter.FlightInvoiceFilter;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightInvoicePage;
import com.isl.admin.page.FlightReportPage;
import com.isl.admin.page.Page;
import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.flight.model.FlightCommissionReport;
import com.lintas.admin.flight.model.FlightOrderCustomer;
import com.lintas.admin.flight.model.FlightOrderCustomerPriceBreakup;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.FlightOrderTripDetail;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.FlightOrderRowCommission;
import com.lintas.admin.model.FlightOrderRowMarkup;
import com.lintas.admin.model.InvoiceData;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;
import com.lintas.utility.DateFilter;
import com.lintas.utility.GstPropertiesFile;
import com.lintas.utility.InvoiceFilter;

public class InvoiceDao {
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(InvoiceDao.class);
	/*load all FlightOrderRow data for Invoice*/
	public FlightInvoicePage  getFlightCustomerInvoiceList(FlightInvoicePage flightInvoicePage){
		Session session = null;
		List<FlightOrderRow> newFlightOrderRowList =  new ArrayList<>();
		int availablePages = 0;
		int availableItems = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(FlightOrderRow.class);
			Conjunction invoiceConjunction = Restrictions.conjunction();
			FlightInvoiceFilter flightInvoiceFilter =null;
			if(flightInvoicePage!=null && flightInvoicePage.isFilterEnabled())
			{
				flightInvoiceFilter = flightInvoicePage.getFlightInvoiceFilter();
				List<String> companyIdList = new FlightOrderDao().getCompanyIdList(flightInvoiceFilter.getLoginCompany(), flightInvoiceFilter.getReportType(),flightInvoiceFilter.getCompanyName());
				invoiceConjunction.add(Restrictions.in("companyId",companyIdList));
				invoiceConjunction.add(Restrictions.ne("statusAction", "failed"));
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
					invoiceConjunction.add(Restrictions.eq("orderId", flightInvoiceFilter.getConfirmNo()));
					logger.info("##########getOrderId  added----"+flightInvoiceFilter.getConfirmNo());
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

				/*if(flightInvoiceFilter.getAmountRangeBooking() != null && flightInvoiceFilter.getAmountRangeBooking().getAmountMin() != null && flightInvoiceFilter.getAmountRangeBooking().getAmountMax() != null )
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
				List<FlightOrderRow> list =  null;
				if(flightInvoicePage.getMaxItems()==Page.ALL_ITEMS){
					criteria = session.createCriteria(FlightOrderRow.class);
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
						criteria = session.createCriteria(FlightOrderRow.class);
						criteria.add(invoiceConjunction);
						criteria.addOrder(Order.desc("id"));
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(flightInvoicePage.getMaxItems());
						list = criteria.list();
						logger.info("Reports size------"+list.size());					
					}
				}
				if(list!=null && list.size()>0){
					for(FlightOrderRow invoiceData:list){
						/*BigDecimal basePrice= invoiceData.getPrice().multiply(invoiceData.getApiToBaseExchangeRate()) ;
						logger.info("basePrice----------"+basePrice);

						BigDecimal taxes= invoiceData.getTaxes().multiply(invoiceData.getApiToBaseExchangeRate()) ;
						BigDecimal totalBasePrice=basePrice.add(invoiceData.getMarkUp());
						BigDecimal basePriceInBooking=totalBasePrice.multiply(invoiceData.getBaseToBookingExchangeRate());
						BigDecimal taxesInBooking=taxes.multiply(invoiceData.getBaseToBookingExchangeRate());
						BigDecimal totalPrice = invoiceData.getProcessingFees().add(basePriceInBooking).add(taxesInBooking);
						invoiceData.setGst_on_markup(invoiceData.getGst_on_markup().multiply(invoiceData.getBaseToBookingExchangeRate()).setScale(2, BigDecimal.ROUND_UP));
						invoiceData.setBookingDate(DateConversion.getBluestarDate(invoiceData.getBookingDate()));
						invoiceData.setGstOnFlights(invoiceData.getGstOnFlights().multiply(invoiceData.getBaseToBookingExchangeRate()).setScale(2, BigDecimal.ROUND_UP));*/
						invoiceData.setCreatedBy(invoiceData.getCreatedBy().replace("+", " "));
						invoiceData.setFinalPrice(invoiceData.getFinalPrice().add(FlightOrderDao.calculateTotalserviceTax(invoiceData)).setScale(2, BigDecimal.ROUND_UP));
						Company companyNew = new  CompanyDAO().getCompanyProfile(Integer.parseInt(invoiceData.getCompanyId()));
						if(companyNew!=null){
							invoiceData.setUpdatedBy(companyNew.getCompanyname());
						}
						else{
							invoiceData.setUpdatedBy("----"); 
						}
						newFlightOrderRowList.add(invoiceData);
					}
					flightInvoicePage.setItems(newFlightOrderRowList);

					if((flightInvoiceFilter.getEmail() != null && !flightInvoiceFilter.getEmail().equals("")))
					{								
						newFlightOrderRowList = new ArrayList<FlightOrderRow>();
						for (FlightOrderRow hotelReport : flightInvoicePage.getItems()) {
							if(flightInvoiceFilter.getEmail().equalsIgnoreCase(hotelReport.getFlightCustomer().getEmail())){
								newFlightOrderRowList.add(hotelReport);
							}
						}	
						flightInvoicePage.setItems(newFlightOrderRowList);
						if(flightInvoicePage.isPagination())
						{
							availableItems = flightInvoicePage.getItems().size();
							availablePages = (availableItems % flightInvoicePage.getMaxItems() == 0)?(availableItems / flightInvoicePage.getMaxItems()):((availableItems / flightInvoicePage.getMaxItems()) + 1);
							flightInvoicePage.setAvailableItems(availableItems);
							flightInvoicePage.setAvailablePages(availablePages);
						} 
					} 
				}

				else
				{
					//current page is having empty items..
					flightInvoicePage.setAvailableItems(0);					
					flightInvoicePage.setItems(new ArrayList<FlightOrderRow>());
				}

			}
			else
			{
				flightInvoicePage.setAvailableItems(0);
				flightInvoicePage.setAvailablePages(0);
				flightInvoicePage.setItems(new ArrayList<FlightOrderRow>());
			}


		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return flightInvoicePage;
	}


	/* load all FlightOrder data fpr generate  Invoice */
	public  InvoiceData getFlightOrderCustomerDetailsForInvoice(FlightOrderRow flightOrderRow, HashMap<String, BigDecimal> companyMarkUpCommissionsMap, int reportType){
		FlightOrderRow   invoiceData=null ;
		InvoiceData reportData=new InvoiceData();
		logger.info("-------------------------flightOrderRow  Order Id--------------"+flightOrderRow.getOrderId());
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightOrderRow for where for.orderId=:orderid";
			Query query = session.createQuery(sql);
			query.setParameter("orderid",flightOrderRow.getOrderId());
			List<FlightOrderRow> list = query.list();
			for (Iterator  iterator = list.iterator(); iterator.hasNext();){
				invoiceData= (FlightOrderRow)iterator.next(); 
				BigDecimal basePrice= invoiceData.getPrice().multiply(invoiceData.getApiToBaseExchangeRate()) ;
				BigDecimal taxes= invoiceData.getTaxes().multiply(invoiceData.getApiToBaseExchangeRate()) ;
				BigDecimal totalBasePrice=basePrice.add(invoiceData.getMarkUp());
				BigDecimal basePriceInBooking=totalBasePrice.multiply(invoiceData.getBaseToBookingExchangeRate());
				BigDecimal taxesInBooking=taxes.multiply(invoiceData.getBaseToBookingExchangeRate());
				BigDecimal totalTicketPrice=invoiceData.getProcessingFees().add(basePriceInBooking).add(taxesInBooking);
				BigDecimal totalPayable =invoiceData.getProcessingFees().add(basePriceInBooking).add(taxesInBooking);
				BigDecimal finalAmount = (companyMarkUpCommissionsMap.get("finalAmount") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("finalAmount");		
				BigDecimal payAmount = (companyMarkUpCommissionsMap.get("payAmount") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("payAmount");
				BigDecimal urProfit = (companyMarkUpCommissionsMap.get("urProfit") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("urProfit");		
				BigDecimal urMarkup = (companyMarkUpCommissionsMap.get("urMarkup") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("urMarkup");
				BigDecimal urCommission = (companyMarkUpCommissionsMap.get("urCommission") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("urCommission");		
				BigDecimal commissionShared = (companyMarkUpCommissionsMap.get("commissionShared") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("commissionShared");
				BigDecimal masterMarkup = (companyMarkUpCommissionsMap.get("masterMarkup") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("masterMarkup");		
				BigDecimal chlidMarkup = (companyMarkUpCommissionsMap.get("chlidMarkup") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("chlidMarkup");
				BigDecimal TDS = (companyMarkUpCommissionsMap.get("TDS") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("TDS");
				logger.info("------------------------masterMarkup------------"+masterMarkup);
				BigDecimal masterMarkupBookingCurrency = masterMarkup.multiply(invoiceData.getBaseToBookingExchangeRate());
				logger.info("------------------------masterMarkup---Booking currency---------"+masterMarkup);
				BigDecimal gstOnMasterMarkupBookingCurrency = new BigDecimal("0.00");
				try{
					gstOnMasterMarkupBookingCurrency = masterMarkupBookingCurrency.multiply(new BigDecimal(6)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UP);
					logger.info("gstOnMasterMarkupBookingCurrency  "+gstOnMasterMarkupBookingCurrency);	
				}
				catch(ArithmeticException ex)
				{
					gstOnMasterMarkupBookingCurrency = masterMarkupBookingCurrency.multiply(new BigDecimal(6)).divide(new BigDecimal(100), 7, RoundingMode.HALF_UP).setScale(2, BigDecimal.ROUND_UP);
					logger.info("##### ArithmeticException gstOnMasterMarkupBookingCurrency  "+gstOnMasterMarkupBookingCurrency);	
				}
				BigDecimal urMarkupBookingCurrency = urMarkup.multiply(invoiceData.getBaseToBookingExchangeRate());
				BigDecimal gstOnUrMarkupBookingCurrency = new BigDecimal("0.00");
				try{
					gstOnUrMarkupBookingCurrency = urMarkupBookingCurrency.multiply(new BigDecimal(6)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UP);
					logger.info("gstOnUrMarkupBookingCurrency  "+gstOnUrMarkupBookingCurrency);	
				}
				catch(ArithmeticException ex)
				{
					gstOnUrMarkupBookingCurrency = urMarkupBookingCurrency.multiply(new BigDecimal(6)).divide(new BigDecimal(100), 7, RoundingMode.HALF_UP).setScale(2, BigDecimal.ROUND_UP);
					logger.info("##### ArithmeticException gstOnUrMarkupBookingCurrency  "+gstOnUrMarkupBookingCurrency);	
				}	
				if(reportType != InvoiceData.CUSTOMER_INVOICE)
				{

					reportData.setTotGst(gstOnUrMarkupBookingCurrency.setScale(2, BigDecimal.ROUND_UP));
					reportData.setGSTOnMArkup(gstOnUrMarkupBookingCurrency.setScale(2, BigDecimal.ROUND_UP));
					reportData.setGSTOnTotMarkup(urMarkupBookingCurrency.setScale(2, BigDecimal.ROUND_UP));				
					totalTicketPrice = totalTicketPrice.subtract(urMarkupBookingCurrency);
					totalTicketPrice = totalTicketPrice.add(gstOnMasterMarkupBookingCurrency);
					//totalPayable = totalPayable.add(augend)
					totalTicketPrice = totalTicketPrice.setScale(2, BigDecimal.ROUND_UP);				
					totalPayable = totalTicketPrice.add(urMarkupBookingCurrency);

					///reportData.setTotAmount(totalTicketPrice.setScale(2, BigDecimal.ROUND_UP));				
					reportData.setTotMarkup(invoiceData.getMarkUp().setScale(2, BigDecimal.ROUND_UP));				
					reportData.setPrice(basePriceInBooking.setScale(2, BigDecimal.ROUND_UP));
					reportData.setOrderRowId(flightOrderRow.getId());
					reportData.setCompanyId(flightOrderRow.getCompanyId());
					//reportData.setTotBeforeGst(totalTicketPrice.setScale(2, BigDecimal.ROUND_UP));
					FlightOrderRowMarkup flightOrderRowMarkup=new FlightOrderDao().getCompanyMarkup(reportData.getCompanyId(), reportData.getOrderRowId());
					BigDecimal totalMarkupWithPaxCount=new BigDecimal("0.0");

					if(flightOrderRowMarkup!=null){
						totalMarkupWithPaxCount=flightOrderRowMarkup.getMarkUp().multiply(new BigDecimal(flightOrderRow.getPassengerCount()));

					}
					reportData.setTotAmount(flightOrderRow.getFinalPrice().add(FlightOrderDao.calculateTotalserviceTax(invoiceData)).setScale(2, BigDecimal.ROUND_UP));		
					reportData.setTotBeforeGst(flightOrderRow.getFinalPrice().subtract(totalMarkupWithPaxCount).setScale(2, BigDecimal.ROUND_UP));
					reportData.setTotAgentComm(totalMarkupWithPaxCount.setScale(2, BigDecimal.ROUND_UP));
					BigDecimal gstonFlightsConvertToBookingCurrency=invoiceData.getGstOnFlights().multiply(invoiceData.getBaseToBookingExchangeRate());
					reportData.setGSTOnFlights(gstonFlightsConvertToBookingCurrency.setScale(2, BigDecimal.ROUND_UP));
					BigDecimal totGSTOnFlights=gstonFlightsConvertToBookingCurrency.multiply(new BigDecimal(100)).divide(new BigDecimal(6).setScale(2, BigDecimal.ROUND_UP));
					reportData.setGSTOnFlightsTotMarkup(totGSTOnFlights.setScale(2, BigDecimal.ROUND_UP));

					logger.info("##### totalTicketPrice-- "+totalTicketPrice);	
					logger.info("##### totalPayable-- "+totalPayable);	

					totalPayable = totalPayable.add(gstOnUrMarkupBookingCurrency).setScale(2, BigDecimal.ROUND_UP).add(gstonFlightsConvertToBookingCurrency.setScale(2, BigDecimal.ROUND_UP));
					logger.info("##### after gst on flight totalPayable-- "+totalPayable);	

					reportData.setTotWithGst(totalPayable);


				}
				else
				{
					/*reportData.setTotGst(gstOnMasterMarkupBookingCurrency.add(gstOnUrMarkupBookingCurrency).setScale(2, BigDecimal.ROUND_UP));
					reportData.setGSTOnMArkup(gstOnMasterMarkupBookingCurrency.add(gstOnUrMarkupBookingCurrency).setScale(2, BigDecimal.ROUND_UP));
					reportData.setGSTOnTotMarkup(masterMarkupBookingCurrency.add(urMarkupBookingCurrency).setScale(2, BigDecimal.ROUND_UP));	
					 */			
					//totalTicketPrice = totalTicketPrice.subtract(masterMarkupBookingCurrency.add(urMarkupBookingCurrency));
					//totalTicketPrice = totalTicketPrice.add(gstOnMasterMarkupBookingCurrency.add(gstOnUrMarkupBookingCurrency));
					//totalPayable = totalPayable.add(augend)
					totalTicketPrice = totalTicketPrice.setScale(2, BigDecimal.ROUND_UP);				
					totalPayable = totalTicketPrice.setScale(2, BigDecimal.ROUND_UP);
					reportData.setTotAmount(totalTicketPrice.add(FlightOrderDao.calculateTotalserviceTax(invoiceData)).setScale(2, BigDecimal.ROUND_UP));				
					reportData.setTotMarkup(invoiceData.getMarkUp().setScale(2, BigDecimal.ROUND_UP));				
					reportData.setPrice(basePriceInBooking.setScale(2, BigDecimal.ROUND_UP));
					reportData.setTotBeforeGst(flightOrderRow.getFinalPrice().add(FlightOrderDao.calculateTotalserviceTax(invoiceData)).setScale(2, BigDecimal.ROUND_UP));


					BigDecimal gstonFlightsConvertToBookingCurrency=invoiceData.getGstOnFlights().multiply(invoiceData.getBaseToBookingExchangeRate());
					reportData.setGSTOnFlights(gstonFlightsConvertToBookingCurrency.setScale(2, BigDecimal.ROUND_UP));
					BigDecimal totGSTOnFlights=gstonFlightsConvertToBookingCurrency.multiply(new BigDecimal(100)).divide(new BigDecimal(6).setScale(2, BigDecimal.ROUND_UP));
					reportData.setGSTOnFlightsTotMarkup(totGSTOnFlights.setScale(2, BigDecimal.ROUND_UP));
					reportData.setOrderRowId(flightOrderRow.getId());
					reportData.setCompanyId(flightOrderRow.getCompanyId());
					logger.info("##### totalTicketPrice-- "+totalTicketPrice);	
					logger.info("##### totalPayable-- "+totalPayable);	

					totalPayable = totalPayable.add(gstonFlightsConvertToBookingCurrency.setScale(2, BigDecimal.ROUND_UP));
					logger.info("##### after gst on flight totalPayable-- "+totalPayable);	
					reportData.setTotWithGst(totalPayable);
				}

				reportData.setCusAddress(invoiceData.getFlightCustomer().getAddress());
				reportData.setInvNo(invoiceData.getInvoiceNo());
				reportData.setConsultant(invoiceData.getCreatedBy());
				reportData.setActCode("3000/P001");
				reportData.setBookNo("000020408");	
				reportData.setCurrency(invoiceData.getBookingCurrency());
				reportData.setYourRef(invoiceData.getOrderId());
				reportData.setBookedDate(DateConversion.getBluestarDate(invoiceData.getBookingDate()));
				reportData.setMobile(invoiceData.getFlightCustomer().getMobile());
				reportData.setTel(invoiceData.getFlightCustomer().getPhone());
				String FirstNameUppercase = invoiceData.getFlightCustomer().getFirstName().substring(0, 1).toUpperCase() + invoiceData.getFlightCustomer().getFirstName().substring(1);
				reportData.setAttn(FirstNameUppercase+invoiceData.getFlightCustomer().getLastName());

				/*GST Calculation.......END........*/
				reportData.setAgentName(invoiceData.getCreatedBy());
				GstPropertiesFile gstPropertiesFile =new GstPropertiesFile();
				reportData.setCompanysGstOn(gstPropertiesFile.getGstSwitchonValue());

			}
			reportData.setTripParticulars(getFlightTripParticulars(flightOrderRow.getId(),session));
			reportData.setPriceBreakupParticulars(getFlightOrderCustomerPricebreakup(flightOrderRow.getId(),session));
			reportData.setOrderCustomerParticulars(getFlightOrderCustomerDetails(flightOrderRow.getId(),session));
			reportData.setTxDetails(getPaymentTxDetails(flightOrderRow.getOrderId(), session)) ;
			reportData.setAgentWalletTxDetails(getAgentPaymentTxWalletDetails(flightOrderRow.getOrderId(),invoiceData.getUserId(), session));
			reportData.setUserDetails(getAgentAddress(flightOrderRow.getUserId(),session)); 
			reportData.setCompanyAddress(getCompanyAddressByCompanyId(invoiceData.getCompanyId(), session));

		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return reportData;

	}



	public  InvoiceData getFlightOrderCustomerDetailsForAgentInvoice(FlightCommissionReport flightCommissionReport, InvoiceData invoiceData){		

		logger.info("-------------------------flightOrderRow  Order Id--------------"+flightCommissionReport.getOrderId());
		FlightOrderRow flightOrderRow = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightOrderRow for  where for.orderId=:orderid";
			Query query = session.createQuery(sql);
			query.setParameter("orderid", flightCommissionReport.getOrderId());
			List<FlightOrderRow> list = query.list();
			for (Iterator  iterator = list.iterator(); iterator.hasNext();){
				flightOrderRow= (FlightOrderRow)iterator.next(); 
				BigDecimal basePrice= flightOrderRow.getPrice().multiply(flightOrderRow.getApiToBaseExchangeRate()) ;
				BigDecimal taxes= flightOrderRow.getTaxes().multiply(flightOrderRow.getApiToBaseExchangeRate()) ;
				BigDecimal totalBasePrice=basePrice.add(flightOrderRow.getMarkUp());
				BigDecimal basePriceInBooking=totalBasePrice.multiply(flightOrderRow.getBaseToBookingExchangeRate());
				BigDecimal taxesInBooking=taxes.multiply(flightOrderRow.getBaseToBookingExchangeRate());
				BigDecimal totalPrice=flightOrderRow.getProcessingFees().add(basePriceInBooking).add(taxesInBooking);
				BigDecimal markupInBooking=flightOrderRow.getMarkUp().multiply(flightOrderRow.getBaseToBookingExchangeRate());
				invoiceData.setCusAddress(flightOrderRow.getFlightCustomer().getAddress());
				invoiceData.setInvNo(flightOrderRow.getInvoiceNo());
				invoiceData.setConsultant(flightOrderRow.getCreatedBy());
				invoiceData.setActCode("3000/P001");
				invoiceData.setBookNo("000020408");	
				invoiceData.setYourRef(flightOrderRow.getOrderId());
				invoiceData.setBookedDate(DateConversion.getBluestarDate(flightOrderRow.getBookingDate()));

				invoiceData.setTotMarkup(markupInBooking.setScale(2, BigDecimal.ROUND_UP));
				invoiceData.setMobile(flightOrderRow.getFlightCustomer().getMobile());
				invoiceData.setTel(flightOrderRow.getFlightCustomer().getPhone());
				invoiceData.setPrice(basePriceInBooking.setScale(2, BigDecimal.ROUND_UP));
				String FirstNameUppercase = flightOrderRow.getFlightCustomer().getFirstName().substring(0, 1).toUpperCase() + flightOrderRow.getFlightCustomer().getFirstName().substring(1);

				invoiceData.setAttn(FirstNameUppercase+flightOrderRow.getFlightCustomer().getLastName());
				invoiceData.setTotBeforeGst(totalPrice.add(FlightOrderDao.calculateTotalserviceTax(flightOrderRow)).setScale(2, BigDecimal.ROUND_UP));
				BigDecimal gstConvertToBookingCurrency=flightOrderRow.getGst_on_markup().multiply(flightOrderRow.getBaseToBookingExchangeRate());
				invoiceData.setTotGst(gstConvertToBookingCurrency.setScale(2, BigDecimal.ROUND_UP));
				invoiceData.setGSTOnMArkup(gstConvertToBookingCurrency);
				invoiceData.setGSTOnTotMarkup(gstConvertToBookingCurrency.multiply(new BigDecimal(100)).divide(new BigDecimal(6)).setScale(2, BigDecimal.ROUND_UP));
				BigDecimal gstonFlightsConvertToBookingCurrency=flightOrderRow.getGstOnFlights().multiply(flightOrderRow.getBaseToBookingExchangeRate());
				invoiceData.setGSTOnFlights(gstonFlightsConvertToBookingCurrency.setScale(2, BigDecimal.ROUND_UP));
				BigDecimal totGSTOnFlights=gstonFlightsConvertToBookingCurrency.multiply(new BigDecimal(100)).divide(new BigDecimal(6));
				invoiceData.setGSTOnFlightsTotMarkup(totGSTOnFlights.setScale(2, BigDecimal.ROUND_UP));
				invoiceData.setTotWithGst(totalPrice.add(FlightOrderDao.calculateTotalserviceTax(flightOrderRow)).add(gstConvertToBookingCurrency).setScale(2, BigDecimal.ROUND_UP).add(gstonFlightsConvertToBookingCurrency.setScale(2, BigDecimal.ROUND_UP)));
				/*GST Calculation.......END........*/

				GstPropertiesFile gstPropertiesFile =new GstPropertiesFile();
				invoiceData.setCompanysGstOn(gstPropertiesFile.getGstSwitchonValue());
				invoiceData.setAgentName(flightOrderRow.getCreatedBy());
			}
			if(flightOrderRow != null)
			{
				invoiceData.setTripParticulars(getFlightTripParticulars(flightOrderRow.getId(),session));
				invoiceData.setPriceBreakupParticulars(getFlightOrderCustomerPricebreakup(flightOrderRow.getId(),session));
				invoiceData.setOrderCustomerParticulars(getFlightOrderCustomerDetails(flightOrderRow.getId(),session));
				invoiceData.setTxDetails(getPaymentTxDetails(flightOrderRow.getOrderId(), session)) ;
				invoiceData.setAgentWalletTxDetails(getAgentPaymentTxWalletDetails(flightOrderRow.getOrderId(),flightOrderRow.getUserId(), session));
				invoiceData.setUserDetails(getAgentAddress(flightOrderRow.getUserId(),session));  
				invoiceData.setCompanyAddress(getCompanyAddressByCompanyId(flightOrderRow.getCompanyId(), session));
				if(invoiceData.getAgentWalletTxDetails()!=null){
					for(WalletAmountTranferHistory  tdsFromWalletHistory:invoiceData.getAgentWalletTxDetails()){
						if(tdsFromWalletHistory.getAction().equalsIgnoreCase("TDS on commission")){
							invoiceData.setTotalTDS(tdsFromWalletHistory.getAmount());
						}
						if(tdsFromWalletHistory.getAction().equalsIgnoreCase("Flight Commission Added")){
							invoiceData.setTotAgentComm(tdsFromWalletHistory.getAmount());
						}

					}
					if(invoiceData.getTotAgentComm()!=null && invoiceData.getTotalTDS()!=null ){
						invoiceData.setFinalCommWithTdsDeduct(invoiceData.getTotAgentComm().subtract(invoiceData.getTotalTDS()));
					}

				}   
			}

		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return invoiceData;

	}


	public  InvoiceData getFlightOrderCustomerDetailsForInvoice(FlightOrderRow flightOrderRow){
		FlightOrderRow   invoiceData=null ;
		InvoiceData reportData=new InvoiceData();
		logger.info("-------------------------flightOrderRow  Order Id--------------"+flightOrderRow.getOrderId());
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightOrderRow for where for.orderId=:orderid";
			Query query = session.createQuery(sql);
			query.setParameter("orderid",flightOrderRow.getOrderId());
			List<FlightOrderRow> list = query.list();
			for (Iterator  iterator = list.iterator(); iterator.hasNext();){
				invoiceData= (FlightOrderRow)iterator.next(); 

				BigDecimal basePrice= invoiceData.getPrice().multiply(invoiceData.getApiToBaseExchangeRate()) ;
				BigDecimal taxes= invoiceData.getTaxes().multiply(invoiceData.getApiToBaseExchangeRate()) ;
				BigDecimal totalBasePrice=basePrice.add(invoiceData.getMarkUp());
				BigDecimal basePriceInBooking=totalBasePrice.multiply(invoiceData.getBaseToBookingExchangeRate());
				BigDecimal taxesInBooking=taxes.multiply(invoiceData.getBaseToBookingExchangeRate());
				BigDecimal totalPrice=invoiceData.getProcessingFees().add(basePriceInBooking).add(taxesInBooking);
				reportData.setTotAmount(totalPrice.add(FlightOrderDao.calculateTotalserviceTax(invoiceData)).setScale(2, BigDecimal.ROUND_UP));
				reportData.setCusAddress(invoiceData.getFlightCustomer().getAddress());
				reportData.setInvNo(invoiceData.getInvoiceNo());
				reportData.setConsultant(invoiceData.getCreatedBy());
				reportData.setActCode("3000/P001");
				reportData.setBookNo("000020408");	
				reportData.setCurrency(invoiceData.getBookingCurrency());
				reportData.setYourRef(invoiceData.getOrderId());
				reportData.setBookedDate(DateConversion.getBluestarDate(invoiceData.getBookingDate()));
				reportData.setTotMarkup(invoiceData.getMarkUp().setScale(2, BigDecimal.ROUND_UP));
				reportData.setMobile(invoiceData.getFlightCustomer().getMobile());
				reportData.setTel(invoiceData.getFlightCustomer().getPhone());
				reportData.setPrice(basePriceInBooking.setScale(2, BigDecimal.ROUND_UP));
				String FirstNameUppercase = invoiceData.getFlightCustomer().getFirstName().substring(0, 1).toUpperCase() + invoiceData.getFlightCustomer().getFirstName().substring(1);
				reportData.setAttn(FirstNameUppercase+invoiceData.getFlightCustomer().getLastName());
				reportData.setTotBeforeGst(totalPrice.add(FlightOrderDao.calculateTotalserviceTax(invoiceData)).setScale(2, BigDecimal.ROUND_UP));
				BigDecimal gstConvertToBookingCurrency=invoiceData.getGst_on_markup().multiply(invoiceData.getBaseToBookingExchangeRate());
				reportData.setTotGst(gstConvertToBookingCurrency.setScale(2, BigDecimal.ROUND_UP));
				reportData.setGSTOnMArkup(gstConvertToBookingCurrency);
				reportData.setGSTOnTotMarkup(gstConvertToBookingCurrency.multiply(new BigDecimal(100)).divide(new BigDecimal(6)).setScale(2, BigDecimal.ROUND_UP));
				BigDecimal gstonFlightsConvertToBookingCurrency=invoiceData.getGstOnFlights().multiply(invoiceData.getBaseToBookingExchangeRate());
				reportData.setGSTOnFlights(gstonFlightsConvertToBookingCurrency.setScale(2, BigDecimal.ROUND_UP));
				BigDecimal totGSTOnFlights=gstonFlightsConvertToBookingCurrency.multiply(new BigDecimal(100)).divide(new BigDecimal(6).setScale(2, BigDecimal.ROUND_UP));
				reportData.setGSTOnFlightsTotMarkup(totGSTOnFlights.setScale(2, BigDecimal.ROUND_UP));
				reportData.setTotWithGst(totalPrice.add(FlightOrderDao.calculateTotalserviceTax(invoiceData)).add(gstConvertToBookingCurrency).setScale(2, BigDecimal.ROUND_UP).add(gstonFlightsConvertToBookingCurrency.setScale(2, BigDecimal.ROUND_UP)));
				/*GST Calculation.......END........*/
				reportData.setAgentName(invoiceData.getCreatedBy());
				GstPropertiesFile gstPropertiesFile =new GstPropertiesFile();
				reportData.setCompanysGstOn(gstPropertiesFile.getGstSwitchonValue());
			}
			reportData.setTripParticulars(getFlightTripParticulars(flightOrderRow.getId(),session));
			reportData.setPriceBreakupParticulars(getFlightOrderCustomerPricebreakup(flightOrderRow.getId(),session));
			reportData.setOrderCustomerParticulars(getFlightOrderCustomerDetails(flightOrderRow.getId(),session));
			reportData.setTxDetails(getPaymentTxDetails(flightOrderRow.getOrderId(), session)) ;
			reportData.setAgentWalletTxDetails(getAgentPaymentTxWalletDetails(flightOrderRow.getOrderId(),invoiceData.getUserId(), session));
			reportData.setUserDetails(getAgentAddress(flightOrderRow.getUserId(),session)); 
			reportData.setCompanyAddress(getCompanyAddressByCompanyId(invoiceData.getCompanyId(), session));

		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return reportData;

	}




	/* load all FlightOrder data fpr generate  Invoice */
	public  InvoiceData getFlightOrderCustomerDetailsForAgentInvoice(FlightCommissionReport flightOrderRow){
		FlightOrderRow   invoiceData=null ;
		InvoiceData reportData=new InvoiceData();
		logger.info("-------------------------flightOrderRow  Order Id--------------"+flightOrderRow.getOrderId());
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightOrderRow for  where for.orderId=:orderid";
			Query query = session.createQuery(sql);
			query.setParameter("orderid", flightOrderRow.getOrderId());
			List<FlightOrderRow> list = query.list();
			for (Iterator  iterator = list.iterator(); iterator.hasNext();){
				invoiceData= (FlightOrderRow)iterator.next(); 
				BigDecimal basePrice= invoiceData.getPrice().multiply(invoiceData.getApiToBaseExchangeRate()) ;
				BigDecimal taxes= invoiceData.getTaxes().multiply(invoiceData.getApiToBaseExchangeRate()) ;
				BigDecimal totalBasePrice=basePrice.add(invoiceData.getMarkUp());
				BigDecimal basePriceInBooking=totalBasePrice.multiply(invoiceData.getBaseToBookingExchangeRate());
				BigDecimal taxesInBooking=taxes.multiply(invoiceData.getBaseToBookingExchangeRate());
				BigDecimal totalPrice=invoiceData.getProcessingFees().add(basePriceInBooking).add(taxesInBooking).add(FlightOrderDao.calculateTotalserviceTax(invoiceData));
				BigDecimal markupInBooking=invoiceData.getMarkUp().multiply(invoiceData.getBaseToBookingExchangeRate());
				reportData.setCusAddress(invoiceData.getFlightCustomer().getAddress());
				reportData.setInvNo(invoiceData.getInvoiceNo());
				reportData.setConsultant(invoiceData.getCreatedBy());
				reportData.setActCode("3000/P001");
				reportData.setBookNo("000020408");	
				reportData.setYourRef(invoiceData.getOrderId());
				reportData.setBookedDate(DateConversion.getBluestarDate(invoiceData.getBookingDate()));

				reportData.setTotMarkup(markupInBooking.setScale(2, BigDecimal.ROUND_UP));
				reportData.setMobile(invoiceData.getFlightCustomer().getMobile());
				reportData.setTel(invoiceData.getFlightCustomer().getPhone());
				reportData.setPrice(basePriceInBooking.setScale(2, BigDecimal.ROUND_UP));
				String FirstNameUppercase = invoiceData.getFlightCustomer().getFirstName().substring(0, 1).toUpperCase() + invoiceData.getFlightCustomer().getFirstName().substring(1);

				reportData.setAttn(FirstNameUppercase+invoiceData.getFlightCustomer().getLastName());
				reportData.setTotBeforeGst(totalPrice.setScale(2, BigDecimal.ROUND_UP));
				BigDecimal gstConvertToBookingCurrency=invoiceData.getGst_on_markup().multiply(invoiceData.getBaseToBookingExchangeRate());
				reportData.setTotGst(gstConvertToBookingCurrency.setScale(2, BigDecimal.ROUND_UP));
				reportData.setGSTOnMArkup(gstConvertToBookingCurrency);
				reportData.setGSTOnTotMarkup(gstConvertToBookingCurrency.multiply(new BigDecimal(100)).divide(new BigDecimal(6)).setScale(2, BigDecimal.ROUND_UP));
				BigDecimal gstonFlightsConvertToBookingCurrency=invoiceData.getGstOnFlights().multiply(invoiceData.getBaseToBookingExchangeRate());
				reportData.setGSTOnFlights(gstonFlightsConvertToBookingCurrency.setScale(2, BigDecimal.ROUND_UP));
				BigDecimal totGSTOnFlights=gstonFlightsConvertToBookingCurrency.multiply(new BigDecimal(100)).divide(new BigDecimal(6));
				reportData.setGSTOnFlightsTotMarkup(totGSTOnFlights.setScale(2, BigDecimal.ROUND_UP));
				reportData.setTotWithGst(totalPrice.add(gstConvertToBookingCurrency).setScale(2, BigDecimal.ROUND_UP).add(gstonFlightsConvertToBookingCurrency.setScale(2, BigDecimal.ROUND_UP)));
				/*GST Calculation.......END........*/

				GstPropertiesFile gstPropertiesFile =new GstPropertiesFile();
				reportData.setCompanysGstOn(gstPropertiesFile.getGstSwitchonValue());
				reportData.setAgentName(invoiceData.getCreatedBy());
			}
			reportData.setTripParticulars(getFlightTripParticulars(flightOrderRow.getId(),session));
			reportData.setPriceBreakupParticulars(getFlightOrderCustomerPricebreakup(flightOrderRow.getId(),session));
			reportData.setOrderCustomerParticulars(getFlightOrderCustomerDetails(flightOrderRow.getId(),session));
			reportData.setTxDetails(getPaymentTxDetails(flightOrderRow.getOrderId(), session)) ;
			reportData.setAgentWalletTxDetails(getAgentPaymentTxWalletDetails(flightOrderRow.getOrderId(),flightOrderRow.getUserId(), session));
			reportData.setUserDetails(getAgentAddress(flightOrderRow.getUserId(),session));  
			reportData.setCompanyAddress(getCompanyAddressByCompanyId(invoiceData.getCompanyId(), session));
			if(reportData.getAgentWalletTxDetails()!=null){
				for(WalletAmountTranferHistory  tdsFromWalletHistory:reportData.getAgentWalletTxDetails()){
					if(tdsFromWalletHistory.getAction().equalsIgnoreCase("TDS on commission")){
						reportData.setTotalTDS(tdsFromWalletHistory.getAmount());
					}
					if(tdsFromWalletHistory.getAction().equalsIgnoreCase("Flight Commission Added")){
						reportData.setTotAgentComm(tdsFromWalletHistory.getAmount());
					}

				}
				if(reportData.getTotAgentComm()!=null && reportData.getTotalTDS()!=null ){
					reportData.setFinalCommWithTdsDeduct(reportData.getTotAgentComm().subtract(reportData.getTotalTDS()));
				}

			}   

		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return reportData;

	}

	/* FlightTrip Particulars for Invoice */
	public  List<FlightOrderTripDetail> getFlightTripParticulars(Long id,Session session ){
		String sql = "from FlightOrderTripDetail fotd where fotd.flightOrderRow.id=:id";
		List<FlightOrderTripDetail>  flightOrderTripDetailList=new ArrayList<FlightOrderTripDetail>();
		Query query = session.createQuery(sql);
		query.setParameter("id", id);
		List<FlightOrderTripDetail> list = query.list();
		for (FlightOrderTripDetail flightOrderTripDetail:list){
			flightOrderTripDetail.setConvertDate(DateConversion.convertDateToStringToDate(flightOrderTripDetail.getDepartureDate())+" "+DateConversion.convertTimestampToString(flightOrderTripDetail.getDepartureTime())+"/"+DateConversion.convertTimestampToString(flightOrderTripDetail.getArrivalTime()));
			flightOrderTripDetail.setDepDateTime(DateConversion.convertDateToStringToDateWithTIME(flightOrderTripDetail.getDepartureTimestamp()));
			flightOrderTripDetail.setArrDateTime(DateConversion.convertDateToStringToDateWithTIME(flightOrderTripDetail.getArrivalTimestamp()));
			flightOrderTripDetailList.add(flightOrderTripDetail);
		}
		return flightOrderTripDetailList;

	}

	/* Pricebreakup  Particulars for Invoice */
	public  List<FlightOrderCustomerPriceBreakup> getFlightOrderCustomerPricebreakup(Long id,Session session ){
		String sql = "from FlightOrderCustomerPriceBreakup focp where focp.flightOrderRow.id=:id";
		Query query = session.createQuery(sql);
		query.setParameter("id", id);
		List<FlightOrderCustomerPriceBreakup> list = query.list();
		return list;
	}

	/* flight order customer Particulars for Invoice */
	public  List<FlightOrderCustomer> getFlightOrderCustomerDetails(Long id,Session session ){
		String sql = "from FlightOrderCustomer  foc where foc.flightOrderRow.id=:id";
		Query query = session.createQuery(sql);
		query.setParameter("id", id);
		List<FlightOrderCustomer> list = query.list();
		return list;
	}

	/* flight order customer Tx details for Invoice */
	public  List<PaymentTransaction> getPaymentTxDetails(String  orderId,Session session ){
		String sql = "from PaymentTransaction pt where pt.api_transaction_id=:apitxid";
		List<PaymentTransaction> txList=new ArrayList<PaymentTransaction>();
		Query query = session.createQuery(sql);
		query.setParameter("apitxid", orderId);
		List<PaymentTransaction> list = query.list();
		logger.info("------------TX Details-------------"+list.size());
		for (PaymentTransaction paymentTransaction:list){
			paymentTransaction.setConvertDate(DateConversion.convertTimestampToString(paymentTransaction.getCreatedAt()));
			paymentTransaction.setAmount(paymentTransaction.getAmount().setScale(2, BigDecimal.ROUND_UP));
			txList.add(paymentTransaction);
		}

		return txList;
	}

	/*  agent wallet Tx details for  generate Invoice */
	public  List<WalletAmountTranferHistory> getAgentPaymentTxWalletDetails (String  orderId,String userID,Session session ){
		String sql = "from WalletAmountTranferHistory wt where wt.actionId=:action_id and wt.userId=:userid";
		List<WalletAmountTranferHistory> walletTxList=new ArrayList<WalletAmountTranferHistory>();
		Query query = session.createQuery(sql);
		query.setParameter("action_id", orderId);
		query.setParameter("userid",  Integer.parseInt(userID));
		List<WalletAmountTranferHistory> list = query.list();
		for (WalletAmountTranferHistory walletTxHistory:list){
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

	/* load all  company bookings from database based on company type while searching---------*/
	public  List<FlightCommissionReport> getAgentInvoiceByComapnyType(User userSessionObj,Company companySessionObj,InvoiceFilter invoiceFilter){

		List<Company> newCompaniesList= new ArrayList<Company>();
		String sql=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(userSessionObj!=null && invoiceFilter!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					sql= "from Company com where com.super_company_userid=:super_company_userid";
					Query  companyQuery = session.createQuery(sql);
					companyQuery.setParameter("super_company_userid", companySessionObj.getCompany_userid());
					List<Company> companiesList = companyQuery.list();
					if(invoiceFilter.getFilterCompanyType().equalsIgnoreCase("all")){
						for (Company companyObj:companiesList){
							newCompaniesList.add(companyObj);
						}
					}

					else if(invoiceFilter.getFilterCompanyType().equalsIgnoreCase("dis")){
						for (Company companyObj:companiesList){
							if(companyObj.getCompanyRole().isDistributor()){
								sql="from Company com where com.parent_company_userid=:parent_company_userid  or com.company_userid=:company_userid";
								Query  disQuery = session.createQuery(sql);
								disQuery.setParameter("parent_company_userid", companyObj.getCompany_userid());
								disQuery.setParameter("company_userid", companyObj.getCompany_userid());
								newCompaniesList.addAll(disQuery.list());
							}
						}
					}

					else if(invoiceFilter.getFilterCompanyType().equalsIgnoreCase("agency")){
						for (Company companyObj:companiesList){
							if(companyObj.getCompanyRole().isAgent()){
								newCompaniesList.add(companyObj);
							}
						}
					}
				}

				else if(userSessionObj.getUserrole_id().isUsermode() && companySessionObj.getCompanyRole().isDistributor()){
					sql="from Company com  where com.parent_company_userid=:parent_company_userid or com.company_userid=:company_userid";
					Query  disQuery = session.createQuery(sql);
					disQuery.setParameter("parent_company_userid", companySessionObj.getCompany_userid());
					disQuery.setParameter("company_userid", companySessionObj.getCompany_userid());
					List<Company> companiesList = disQuery.list();

					if(invoiceFilter.getFilterCompanyType().equalsIgnoreCase("all")){
						for (Company companyObj: companiesList){
							/*	if(!companyObj.getCompany_userid().equals(companySessionObj.getCompany_userid())) */
							newCompaniesList.add(companyObj);
						}
					}

					else if(invoiceFilter.getFilterCompanyType().equalsIgnoreCase("agency")){
						for (Company companyObj:companiesList){
							if(!companyObj.getCompany_userid().equals(companySessionObj.getCompany_userid())) 
								newCompaniesList.add(companyObj);
						}

					}

				}

				else if(userSessionObj.getUserrole_id().isUsermode() && companySessionObj.getCompanyRole().isAgent()){
					//sql="select * from company where company_userid='"+companySessionObj.getCompany_userid()+"'";
					sql="from Company com where com.company_userid=:company_userid";
					Query  companyQuery = session.createQuery(sql);
					companyQuery.setParameter("company_userid",companySessionObj.getCompany_userid());
					List<Company> companiesList = companyQuery.list();

					if(invoiceFilter.getFilterCompanyType().equalsIgnoreCase("all")){
						for (Company companyObj:companiesList){
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
		return filterAgentCommissionInvoiceListByCompanyId(newCompaniesList,invoiceFilter,companySessionObj);
	}




	public List<FlightCommissionReport> filterAgentCommissionInvoiceListByCompanyId(List<Company> companyList,InvoiceFilter invoiceFilter,Company companySessionObj){
		String filterQuery=null;
		List<FlightCommissionReport>  reportData_list=new ArrayList<FlightCommissionReport>();
		List<String>  companyIds=new ArrayList<>();
		logger.info("------fillter Company type------"+invoiceFilter.getFilterCompanyType());
		try{
			Query query=null;
			List<String> dateList=null;
			session = HibernateUtil.getSessionFactory().openSession();
			if(companyList!=null && companyList.size()>0){
				for(Company obj:companyList){
					companyIds.add(String.valueOf(obj.getCompanyid()));
				}
			}
			if(invoiceFilter.getFilterCompanyType().equals("mine")){
				if(!invoiceFilter.getFromDate().equals("") && !invoiceFilter.getToDate().equals("")){
					filterQuery="from FlightOrderRow for where for.companyId=:company_id and date(for.bookingDate) between '"+invoiceFilter.getFromDate()+"' and '"+invoiceFilter.getToDate()+"'"; 
				}
				else{
					if(invoiceFilter.getPeriod().equals("week")){
						dateList=DateFilter.getPrevious30Days(-6, 0);
						filterQuery="from FlightOrderRow for where for.companyId=:company_id and  for.bookingDate in (:dateList)"; 
					}
					else if(invoiceFilter.getPeriod().equals("month")){
						dateList=DateFilter.getPrevious30Days(-29, 0);
						filterQuery="from FlightOrderRow for where for.companyId=:company_id and  for.bookingDate in (:dateList)"; 
					}
					else{
						filterQuery="from FlightOrderRow for where for.companyId=:company_id";

					}
				}
				query = session.createQuery(filterQuery);
				query.setParameter("company_id", String.valueOf(companySessionObj.getCompanyid()));
				if(invoiceFilter.getPeriod().equals("week")|| invoiceFilter.getPeriod().equals("month"))
					query.setParameterList("dateList",dateList);



			}
			else if(!invoiceFilter.getFromDate().equals("") && !invoiceFilter.getToDate().equals("")){
				//filterQuery="select * from flight_order_row where company_id in ("+userIdBuffer+") and date(bookingDate) between '"+invoiceFilter.getFromDate()+"' and '"+invoiceFilter.getToDate()+"'"; 
				filterQuery="from FlightOrderRow for where for.companyId in (:companyIds) and date(for.bookingDate)  between '"+invoiceFilter.getFromDate()+"' and '"+invoiceFilter.getToDate()+"'"; 
				query = session.createQuery(filterQuery);
				query.setParameterList("companyIds",companyIds);
			}
			else{

				if(invoiceFilter.getPeriod().equals("week")){
					dateList=DateFilter.getPrevious30Days(-6, 0);
					filterQuery="from FlightOrderRow for where for.companyId in (:companyIds) and  for.bookingDate in (:dateList)"; 

				}
				else if(invoiceFilter.getPeriod().equals("month")){
					dateList=DateFilter.getPrevious30Days(-29, 0);
					filterQuery="from FlightOrderRow for where for.companyId in (:companyIds) and  for.bookingDate in (:dateList)"; 
				}
				else{
					//filterQuery="select * from flight_order_row where company_id in ("+userIdBuffer+")";
					filterQuery="from FlightOrderRow fr where fr.companyId in (:companyIds)";
				}

				query = session.createQuery(filterQuery);
				query.setParameterList("companyIds",companyIds);
				if(invoiceFilter.getPeriod().equals("week")|| invoiceFilter.getPeriod().equals("month"))
					query.setParameterList("dateList",dateList);

			} 
			logger.info("-----filterQuery------------------"+filterQuery);
			List<FlightOrderRow>  list = query.list();
			for(FlightOrderRow flightOrderRow:list){
				FlightCommissionReport flightCommissionReport=new FlightCommissionReport();
				BigDecimal FinalPriceinBaseCurrency=flightOrderRow.getFinalPrice().divide(flightOrderRow.getBaseToBookingExchangeRate(),2,RoundingMode.UP);
				flightCommissionReport.setOrderId(flightOrderRow.getOrderId());
				flightCommissionReport.setFinalPrice(flightOrderRow.getFinalPrice().add(FlightOrderDao.calculateTotalserviceTax(flightOrderRow)).add(flightOrderRow.getGst_on_markup().multiply(flightOrderRow.getBaseToBookingExchangeRate())).add(flightOrderRow.getGstOnFlights().multiply(flightOrderRow.getBaseToBookingExchangeRate())).setScale(2,BigDecimal.ROUND_UP));
				/*flightCommissionReport.setFinalPrice(flightOrderRow.getFinalPrice().setScale(2,BigDecimal.ROUND_UP));*/
				flightCommissionReport.setCurCode(flightOrderRow.getBaseCurrency());
				flightCommissionReport.setStatus(flightOrderRow.getStatusAction());
				flightCommissionReport.setPaymentStatus(flightOrderRow.getPaymentStatus());
				flightCommissionReport.setCreatedBy(flightOrderRow.getCreatedBy());
				flightCommissionReport.setBookingDate(DateConversion.getBluestarDate(flightOrderRow.getBookingDate()));
				flightCommissionReport.setUserId(flightOrderRow.getUserId());
				flightCommissionReport.setId(flightOrderRow.getId());
				reportData_list.add(flightCommissionReport);
			}

		}
		catch(HibernateException e){
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

	public  FlightReportPage getFlightAgentCommissionInvoiceList(FlightReportPage flightReportPage,int reportType){
		List<FlightCommissionReport>  agentInvoiceList=new ArrayList<FlightCommissionReport>();
		int availablePages = 0;
		int availableItems = 0;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(FlightOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			FlightReportFilter flightReportFilter=null;
			// To get total row count.
			if(flightReportPage!=null && flightReportPage.isFilterEnabled())
			{
				flightReportFilter = flightReportPage.getFlightReportFilter();

				logger.info("####ReportType-------------"+flightReportFilter.getReportType());
				logger.info("####getAirlineName-------------"+flightReportFilter.getAirlineName());
				/* Add multiple condition separated by AND clause within brackets. */
				List<String> newCompanyIdList  = new ArrayList<String>();
				List<String> companyIdList = new FlightOrderDao().getCompanyIdList(flightReportFilter.getLoginCompany(), flightReportFilter.getReportType(), flightReportFilter.getCompanyName());
				String companyId=String.valueOf(flightReportFilter.getLoginCompany().getCompanyid());
				if(companyIdList!=null){
					for(String id:companyIdList){
						if(reportType == InvoiceData.AFFILIATE_INVOICE) 
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

				if(newCompanyIdList == null || newCompanyIdList.size() <= 0)
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setInvoiceitems(new ArrayList<FlightCommissionReport>());
					return flightReportPage;
				}


				reportConjunction.add(Restrictions.in("companyId",newCompanyIdList));
				if(flightReportPage.getOrderId() != null)
				{
					reportConjunction.add(Restrictions.like("orderId",flightReportPage.getOrderId(), MatchMode.ANYWHERE));
					logger.info("##########Order Id----"+flightReportPage.getOrderId());
				}	
				if(flightReportPage.getInvoiceNo()!= null)
				{
					reportConjunction.add(Restrictions.like("invoiceNo",flightReportPage.getInvoiceNo(), MatchMode.ANYWHERE));
					logger.info("##########getInvoiceNo----"+flightReportPage.getInvoiceNo());
				}	
				if(flightReportFilter.getOrigin() != null)
				{
					reportConjunction.add(Restrictions.like("origin", flightReportFilter.getOrigin(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getOrigin());
				}	
				if(flightReportFilter.getDestination() != null)
				{
					reportConjunction.add(Restrictions.like("destination", flightReportFilter.getDestination(), MatchMode.ANYWHERE));
					logger.info("##########destination added----"+flightReportFilter.getDestination());
				}				
				if(flightReportFilter.getBookingStatus() != null && !flightReportFilter.getBookingStatus().equalsIgnoreCase("ALL"))
				{
					reportConjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus()));
					logger.info("##########booking status added----"+flightReportFilter.getBookingStatus());
				}
				if(flightReportFilter.getPaymentStatus() != null && !flightReportFilter.getPaymentStatus().equalsIgnoreCase("ALL"))
				{
					reportConjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus()));
					logger.info("########## Payment Status added----"+flightReportFilter.getPaymentStatus());
				}
				if(flightReportFilter.getAirlineName() != null && !flightReportFilter.getAirlineName().equalsIgnoreCase("ALL"))
				{
					reportConjunction.add(Restrictions.like("airline", flightReportFilter.getAirlineName(), MatchMode.ANYWHERE));
					logger.info("##########airline  added----"+flightReportFilter.getAirlineName());
				}

				if(flightReportFilter.getUserName() != null && !flightReportFilter.getUserName().equalsIgnoreCase(" "))
				{
					reportConjunction.add(Restrictions.like("createdBy", flightReportFilter.getUserName(), MatchMode.ANYWHERE));
					logger.info("##########airline  added----"+flightReportFilter.getUserName());
				}

				if(flightReportFilter.getUserId()>0)
				{
					reportConjunction.add(Restrictions.like("userId",  String.valueOf(flightReportFilter.getUserId())));
					logger.info("##########airline  added----"+flightReportFilter.getUserName());
				}
				if(flightReportFilter.getCompanyId()>0)
				{
					reportConjunction.add(Restrictions.like("companyId", String.valueOf(flightReportFilter.getCompanyId())));
					logger.info("##########airline  added----"+flightReportFilter.getUserName());
				}




				if(flightReportFilter.getDateFilterBooking() != null && flightReportFilter.getDateFilterBooking().getDtEnd() != null && flightReportFilter.getDateFilterBooking().getDtStart() != null )
				{

					//2016-06-28
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");


					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterBooking().getDtStart());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);

						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));

						reportConjunction.add(Restrictions.ge("createdAt", date));
						logger.info("##########date min added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterBooking().getDtEnd());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);

						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));

						reportConjunction.add(Restrictions.lt("createdAt", date));
						logger.info("##########date max added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());

					}
				}


				/*if(flightReportFilter.getAmountRangeBooking() != null && flightReportFilter.getAmountRangeBooking().getAmountMin() != null && flightReportFilter.getAmountRangeBooking().getAmountMax() != null )
				{
					reportConjunction.add(Restrictions.ge("finalPrice", flightReportFilter.getAmountRangeBooking().getAmountMin()));
					reportConjunction.add(Restrictions.lt("finalPrice", flightReportFilter.getAmountRangeBooking().getAmountMax()));	
					logger.info("##########booking amount added to conjuction---"+flightReportFilter.getAmountRangeBooking().getAmountMin()+"  to "+flightReportFilter.getAmountRangeBooking().getAmountMax());
				}
				 */
				if(flightReportFilter.getShowtype() != null && !flightReportFilter.getShowtype().equalsIgnoreCase(""))
				{
					//2016-06-28
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	

					try{
						if(flightReportFilter.getShowtype().equalsIgnoreCase("today")){
							Calendar cal = Calendar.getInstance();
							String formattedDate = targetFormat.format(cal.getTime()); 
							Date  today = targetFormat.parse(formattedDate) ;
							Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));
							reportConjunction.add(Restrictions.ge("createdAt", today));
							reportConjunction.add(Restrictions.lt("createdAt", tomorrow));
							logger.info("##########today added to conjuction---"+today);
						}
						if(flightReportFilter.getShowtype().equalsIgnoreCase("week")){							

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
						if(flightReportFilter.getShowtype().equalsIgnoreCase("month")){						

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

						if(flightReportFilter.getShowtype().equalsIgnoreCase("flightconfirm")){	
							Calendar cal = Calendar.getInstance();
							String formattedDate = targetFormat.format(cal.getTime()); 
							Date  today = targetFormat.parse(formattedDate) ;
							Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));
							reportConjunction.add(Restrictions.ge("createdAt", today));
							reportConjunction.add(Restrictions.lt("createdAt", tomorrow));
							reportConjunction.add(Restrictions.eq("statusAction", "Confirmed"));
						}
						if(flightReportFilter.getShowtype().equalsIgnoreCase("flightpayment")){	
							Calendar cal = Calendar.getInstance();
							String formattedDate = targetFormat.format(cal.getTime()); 
							Date  today = targetFormat.parse(formattedDate) ;
							Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));
							reportConjunction.add(Restrictions.ge("createdAt", today));
							reportConjunction.add(Restrictions.lt("createdAt", tomorrow));
							reportConjunction.add(Restrictions.eq("paymentStatus", "Success"));
						}
						if(flightReportFilter.getShowtype().equalsIgnoreCase("flightpaymentfailed")){	
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

				}
				logger.info("##########reportConjunction.toString()---"+reportConjunction.toString());
				reportConjunction.add(Restrictions.eq("statusAction", "Confirmed"));
				criteria.add(reportConjunction);
				criteria.addOrder(Order.desc("id"));

			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			if(rowCount>0)
			{
				List<FlightOrderRow> list = null;
				if(flightReportPage.getMaxItems()==Page.ALL_ITEMS){
					logger.info("MaxResults-------"+flightReportPage.getMaxItems());
					criteria = session.createCriteria(FlightOrderRow.class);
					criteria.addOrder(Order.desc("id"));
					criteria.add(reportConjunction);
					list = criteria.list();	
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
					logger.info("setFirstResult-------"+itemIndex);

					if(itemIndex <= rowCount)
					{
						logger.info("setFirstResult-------"+itemIndex);
						logger.info("MaxResults-------"+flightReportPage.getMaxItems());
						criteria = session.createCriteria(FlightOrderRow.class);
						criteria.addOrder(Order.desc("id"));
						criteria.add(reportConjunction);
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(flightReportPage.getMaxItems());
						list = criteria.list();
						logger.info("Reports size------"+list.size());					
					}
				}
				if(list!=null && list.size()>0){
					for(FlightOrderRow flightOrderRow :list){
						FlightCommissionReport flightAgentInvoiceReport=new FlightCommissionReport();

						if(flightOrderRow!=null){

							flightAgentInvoiceReport.setOrderId(flightOrderRow.getOrderId());
							flightAgentInvoiceReport.setFinalPrice(flightOrderRow.getFinalPrice().add(FlightOrderDao.calculateTotalserviceTax(flightOrderRow)).add(flightOrderRow.getGst_on_markup().multiply(flightOrderRow.getBaseToBookingExchangeRate())).add(flightOrderRow.getGstOnFlights().multiply(flightOrderRow.getBaseToBookingExchangeRate())).setScale(2,BigDecimal.ROUND_UP));
							flightAgentInvoiceReport.setCurCode(flightOrderRow.getBaseCurrency());
							flightAgentInvoiceReport.setStatus(flightOrderRow.getStatusAction());
							flightAgentInvoiceReport.setPaymentStatus(flightOrderRow.getPaymentStatus());
							flightAgentInvoiceReport.setCreatedBy(flightOrderRow.getCreatedBy());
							flightAgentInvoiceReport.setBookingDate(DateConversion.getBluestarDate(flightOrderRow.getBookingDate()));
							flightAgentInvoiceReport.setUserId(flightOrderRow.getUserId());
							flightAgentInvoiceReport.setId(flightOrderRow.getId());
							flightAgentInvoiceReport.setInvoiceNo(flightOrderRow.getInvoiceNo());
							agentInvoiceList.add(flightAgentInvoiceReport); 

						}
						flightReportPage.setInvoiceitems(agentInvoiceList); 
					}
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

		return flightReportPage;
	}

	public  List<FlightCommissionReport> getFlightAgentCommissionInvoiceList1(User userSessionObj,Company companySessionObj, int reportType){
		List<FlightCommissionReport>  commissionList=new ArrayList<FlightCommissionReport>();

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
			if(companySessionObj.getCompanyRole().isDistributor()||  companySessionObj.getCompanyRole().isAgent()){
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

					if(reportType == InvoiceData.AFFILIATE_INVOICE)
					{
						if(!flightOrderRowCommissionParent.getFlightOrderRow().getStatusAction().equalsIgnoreCase("failed") && !flightOrderRowCommissionParent.getFlightOrderRow().getCompanyId().equalsIgnoreCase(String.valueOf(companySessionObj.getCompanyid())))
						{							
							flightOrderRow =flightOrderRowCommissionParent.getFlightOrderRow();							
						}						
					}
					else
					{
						if(!flightOrderRowCommissionParent.getFlightOrderRow().getStatusAction().equalsIgnoreCase("failed") && flightOrderRowCommissionParent.getFlightOrderRow().getCompanyId().equalsIgnoreCase(String.valueOf(companySessionObj.getCompanyid())))
						{							
							flightOrderRow =flightOrderRowCommissionParent.getFlightOrderRow();							
						}	
					}

					if(flightOrderRow==null){
						continue;
					}

					FlightCommissionReport flightCommissionReport=new FlightCommissionReport();
					BigDecimal parentTotCommission=new BigDecimal("0.00");
					BigDecimal childTotCommission=new BigDecimal("0.00");
					BigDecimal FinalPriceinBaseCurrency=flightOrderRow.getFinalPrice().divide(flightOrderRow.getBaseToBookingExchangeRate(),2,RoundingMode.UP);

					if(flightOrderRowCommissionForChild!=null){
						childTotCommission=flightOrderRowCommissionForChild.getCommissionAmountValue();

					}

					parentTotCommission=flightOrderRowCommissionParent.getCommissionAmountValue();

					BigDecimal Mymarkup=getMarkup(flightOrderRowCommissionParent.getCompanyId(),flightOrderRowCommissionParent.getFlightOrderRow().getId(),session);

					BigDecimal totalmarkup=Mymarkup;
					BigDecimal chlidmarkup=new BigDecimal("0.00");
					if(childcompanyId!=0){
						chlidmarkup=getMarkup(flightOrderRowCommissionForChild.getCompanyId(),flightOrderRowCommissionForChild.getFlightOrderRow().getId(),session);
						totalmarkup=totalmarkup.add(chlidmarkup);
					}
					BigDecimal ticketPrice=FinalPriceinBaseCurrency.subtract(totalmarkup);
					BigDecimal myProfit=parentTotCommission.add(Mymarkup).subtract(childTotCommission);

					flightCommissionReport.setMarkup(Mymarkup.setScale(2,BigDecimal.ROUND_UP));
					flightCommissionReport.setChildMarkup(chlidmarkup.setScale(2,BigDecimal.ROUND_UP));
					flightCommissionReport.setTicketPrice(ticketPrice.setScale(2,BigDecimal.ROUND_UP));
					flightCommissionReport.setOrderId(flightOrderRow.getOrderId());

					flightCommissionReport.setFinalPrice(flightOrderRow.getFinalPrice().add(FlightOrderDao.calculateTotalserviceTax(flightOrderRow)).add(flightOrderRow.getGst_on_markup().multiply(flightOrderRow.getBaseToBookingExchangeRate())).add(flightOrderRow.getGstOnFlights().multiply(flightOrderRow.getBaseToBookingExchangeRate())).setScale(2,BigDecimal.ROUND_UP));
					flightCommissionReport.setCurCode(flightOrderRow.getBaseCurrency());
					flightCommissionReport.setFinalCommission(myProfit.setScale(2,BigDecimal.ROUND_UP));
					flightCommissionReport.setMyProfit(myProfit.setScale(2,BigDecimal.ROUND_UP));
					flightCommissionReport.setSharedCommission(childTotCommission.setScale(2,BigDecimal.ROUND_UP));
					flightCommissionReport.setMyCommission(parentTotCommission.add(Mymarkup).setScale(2,BigDecimal.ROUND_UP));
					flightCommissionReport.setAgentCommByRate(myProfit.setScale(2,BigDecimal.ROUND_UP));
					flightCommissionReport.setStatus(flightOrderRow.getStatusAction());
					flightCommissionReport.setPaymentStatus(flightOrderRow.getPaymentStatus());
					flightCommissionReport.setCreatedBy(flightOrderRow.getCreatedBy());
					flightCommissionReport.setBookingDate(DateConversion.getBluestarDate(flightOrderRow.getBookingDate()));
					flightCommissionReport.setUserId(flightOrderRow.getUserId());
					flightCommissionReport.setId(flightOrderRow.getId());
					commissionList.add(flightCommissionReport);

				}
			}

			/////////////Super user
			else if(userSessionObj.getUserrole_id().isSuperuser() || userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
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

					if(reportType == InvoiceData.AFFILIATE_INVOICE)
					{
						if(!flightOrderRowCommissionParent.getFlightOrderRow().getStatusAction().equalsIgnoreCase("failed") && !flightOrderRowCommissionParent.getFlightOrderRow().getCompanyId().equalsIgnoreCase(String.valueOf(companySessionObj.getCompanyid())))
						{							
							flightOrderRow =flightOrderRowCommissionParent.getFlightOrderRow();							
						}						
					}
					else
					{
						if(!flightOrderRowCommissionParent.getFlightOrderRow().getStatusAction().equalsIgnoreCase("failed") && flightOrderRowCommissionParent.getFlightOrderRow().getCompanyId().equalsIgnoreCase(String.valueOf(companySessionObj.getCompanyid())))
						{							
							flightOrderRow =flightOrderRowCommissionParent.getFlightOrderRow();							
						}	
					}



					if(flightOrderRow==null){
						continue;
					}

					FlightCommissionReport flightCommissionReport=new FlightCommissionReport();
					BigDecimal parentTotCommission=new BigDecimal("0.00");
					BigDecimal childTotCommission=new BigDecimal("0.00");
					BigDecimal FinalPriceinBaseCurrency=flightOrderRow.getFinalPrice().divide(flightOrderRow.getBaseToBookingExchangeRate(),2,RoundingMode.UP);

					if(flightOrderRowCommissionForChild!=null){
						childTotCommission=flightOrderRowCommissionForChild.getCommissionAmountValue();
					}
					BigDecimal Mymarkup=getMarkup(flightOrderRowCommissionParent.getCompanyId(),flightOrderRowCommissionParent.getFlightOrderRow().getId(),session);
					BigDecimal distmarkup=new BigDecimal("0.00");
					if(childcompanyId!=0){
						distmarkup=getMarkup(flightOrderRowCommissionForChild.getCompanyId(),flightOrderRowCommissionForChild.getFlightOrderRow().getId(),session);
						logger.info("flightOrderRowCommissionForChild id:-"+flightOrderRowCommissionForChild.getFlightOrderRow().getId()+"...flightOrderRowCommissionParent.getFlightOrderRow().getId() :"+flightOrderRowCommissionParent.getFlightOrderRow().getId()); 
					}

					parentTotCommission=flightOrderRowCommissionParent.getCommissionAmountValue();

					BigDecimal totalOtherMarkup=Mymarkup.add(distmarkup);									 
					BigDecimal totalMarkup=flightOrderRow.getMarkUp();
					BigDecimal agencymarkup=totalMarkup.subtract(totalOtherMarkup);					
					BigDecimal myprofit=parentTotCommission.add(Mymarkup).subtract(childTotCommission);
					BigDecimal ticketPrice=FinalPriceinBaseCurrency.subtract(totalMarkup);
					flightCommissionReport.setMarkup(Mymarkup.setScale(2,BigDecimal.ROUND_UP));
					flightCommissionReport.setDistributorMarkup(distmarkup.setScale(2,BigDecimal.ROUND_UP));
					flightCommissionReport.setChildMarkup(agencymarkup.setScale(2,BigDecimal.ROUND_UP));
					flightCommissionReport.setTicketPrice(ticketPrice.setScale(2,BigDecimal.ROUND_UP));
					flightCommissionReport.setOrderId(flightOrderRow.getOrderId());
					flightCommissionReport.setFinalPrice(flightOrderRow.getFinalPrice().add(FlightOrderDao.calculateTotalserviceTax(flightOrderRow)).add(flightOrderRow.getGst_on_markup().multiply(flightOrderRow.getBaseToBookingExchangeRate())).add(flightOrderRow.getGstOnFlights().multiply(flightOrderRow.getBaseToBookingExchangeRate())).setScale(2,BigDecimal.ROUND_UP));
					flightCommissionReport.setCurCode(flightOrderRow.getBookingCurrency());
					flightCommissionReport.setFinalCommission(myprofit.setScale(2,BigDecimal.ROUND_UP));
					flightCommissionReport.setMyProfit(myprofit.setScale(2,BigDecimal.ROUND_UP));
					flightCommissionReport.setSharedCommission(childTotCommission.setScale(2,BigDecimal.ROUND_UP));
					flightCommissionReport.setMyCommission(parentTotCommission.add(Mymarkup).setScale(2,BigDecimal.ROUND_UP));//Totalcommission in design(commission +my markup)
					flightCommissionReport.setAgentCommByRate(myprofit.setScale(2,BigDecimal.ROUND_UP));
					flightCommissionReport.setStatus(flightOrderRow.getStatusAction());
					flightCommissionReport.setCreatedBy(flightOrderRow.getCreatedBy());
					flightCommissionReport.setPaymentStatus(flightOrderRow.getPaymentStatus());
					flightCommissionReport.setBookingDate(DateConversion.getBluestarDate(flightOrderRow.getBookingDate()));
					flightCommissionReport.setUserId(flightOrderRow.getUserId());
					flightCommissionReport.setId(flightOrderRow.getId());
					commissionList.add(flightCommissionReport);
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
	public List<String>  searchForChildCompanyID(FlightOrderRowCommission order_row_id,Company sessionCompany,Session session){
		String childQuery=null;
		List<String> childlist=new ArrayList<String>();
		List<FlightOrderRowCommission> foclist=null;
		session=HibernateUtil.getSessionFactory().openSession();
		childQuery="from FlightOrderRowCommission forc where forc.CompanyId!=:compid and forc.flightOrderRow.id=:order_row_id";
		logger.info("------------------childQuery------------------------"+childQuery);
		Query query = session.createQuery(childQuery);
		query.setParameter("compid",String.valueOf(sessionCompany.getCompanyid()));
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
		query.setParameter("companyId",String.valueOf(childcompanyId));
		flightOrderRowIdschild = (FlightOrderRowCommission) query.list().get(0);
		return flightOrderRowIdschild;

	}

	public BigDecimal getMarkup(String CompanyId,long Id,Session session){
		BigDecimal markup=new BigDecimal("0.00");
		FlightOrderRowMarkup  flightOrderRowMarkup=null;
		String sql = "from FlightOrderRowMarkup frm where frm.flightOrderRow.id=:order_row_id and frm.CompanyId=:company_id";
		logger.info("----------FlightOrderRowids for markup----sql-----------------"+sql);
		Query query = session.createQuery(sql);
		query.setParameter("order_row_id",Id);
		query.setParameter("company_id",CompanyId);
		if(query.list()!=null && query.list().size()>0){
			flightOrderRowMarkup = (FlightOrderRowMarkup) query.list().get(0) ;
			markup=flightOrderRowMarkup.getMarkUp() ;
		}
		return markup;
	}

	/* load all  company bookings from database based on company type while searching---------*/
	public  List<FlightOrderRow> getCompanyInvoiceByComapnyTypexxxxxxx(User userSessionObj,Company companySessionObj,InvoiceFilter invoiceFilter){

		List<Company> newCompaniesList= new ArrayList<Company>();
		String sql=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(userSessionObj!=null && invoiceFilter!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					sql="from Company com where com.super_company_userid=:super_company_userid";
					Query  companyQuery = session.createQuery(sql);
					companyQuery.setParameter("super_company_userid", companySessionObj.getCompany_userid());
					List<Company> companiesList = companyQuery.list();
					if(invoiceFilter.getFilterCompanyType().equalsIgnoreCase("all")){
						for (Company companyObj:companiesList){
							/*	if(!companyObj.getCompany_userid().equals(companySessionObj.getCompany_userid())) */
							newCompaniesList.add(companyObj);
						}
					}

					else if(invoiceFilter.getFilterCompanyType().equalsIgnoreCase("dis")){
						for (Company companyObj: companiesList){
							if(companyObj.getCompanyRole().isDistributor()){
								sql="from Company com where com.parent_company_userid=:parent_company_userid or  com.company_userid=:company_userid";
								Query  disQuery = session.createQuery(sql);
								disQuery.setParameter("parent_company_userid", companySessionObj.getCompany_userid());
								disQuery.setParameter("company_userid", companySessionObj.getCompany_userid());
								newCompaniesList.addAll(disQuery.list());
							}
						}
					}

					else if(invoiceFilter.getFilterCompanyType().equalsIgnoreCase("agency")){
						for (Company companyObj:companiesList){
							if(companyObj.getCompanyRole().isAgent()){
								newCompaniesList.add(companyObj);
							}
						}
					}
				}

				else if(userSessionObj.getUserrole_id().isUsermode() && companySessionObj.getCompanyRole().isDistributor()){
					sql="from Company com where com.parent_company_userid=:parent_company_userid or  com.company_userid=:company_userid";
					Query  companyQuery = session.createQuery(sql);
					companyQuery.setParameter("parent_company_userid", companySessionObj.getCompany_userid());
					companyQuery.setParameter("company_userid", companySessionObj.getCompany_userid());
					List<Company> companiesList = companyQuery.list();
					if(invoiceFilter.getFilterCompanyType().equalsIgnoreCase("all")){
						for (Company companyObj:companiesList){
							/*	if(!companyObj.getCompany_userid().equals(companySessionObj.getCompany_userid())) */
							newCompaniesList.add(companyObj);
						}
					}

					else if(invoiceFilter.getFilterCompanyType().equalsIgnoreCase("agency")){
						for (Company companyObj:companiesList){
							if(!companyObj.getCompany_userid().equals(companySessionObj.getCompany_userid())) 
								newCompaniesList.add(companyObj);
						}
					}
				}

				else if(userSessionObj.getUserrole_id().isUsermode() && companySessionObj.getCompanyRole().isAgent()){
					sql="from Company com where com.company_userid=:company_userid";
					Query  companyQuery = session.createQuery(sql);
					companyQuery.setParameter("company_userid", companySessionObj.getCompany_userid());
					List<Company> companiesList = companyQuery.list();
					if(invoiceFilter.getFilterCompanyType().equalsIgnoreCase("all")){
						for (Company companyObj:companiesList){
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
		return getCompanyInvoicesWithUserId(getUserIdsForCompanyInvoice(newCompaniesList),invoiceFilter,companySessionObj);
	}
	/* this method for get CompanyBookings by passing userids*/
	public List<FlightOrderRow> getCompanyInvoicesWithUserId(List<User> userIds,InvoiceFilter invoiceFilter,Company sessionObj){

		String filterQuery=null;
		List<FlightOrderRow>  reportData_list=new ArrayList<FlightOrderRow>();
		logger.info("------fillter Company type------"+invoiceFilter.getFilterCompanyType());
		StringBuffer userIdBuffer=new StringBuffer();
		Query query =null;  

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			/*if(userIds.size()>0){
				for (int i = 0; i < userIds.size(); i++) {
					User userId= (User)userIds.get(i);
					if(i == userIds.size()-1)
						userIdBuffer.append("'"+userId.getId()+"'");
					else
						userIdBuffer.append("'"+userId.getId()+"',");
				}
			}*/
			if(invoiceFilter.getFilterCompanyType().equals("mine")){
				if(!invoiceFilter.getFromDate().equals("") && !invoiceFilter.getToDate().equals("")){
					filterQuery="from FlightOrderRow for where for.companyId=:company_id  and date(for.bookingDate) between '"+invoiceFilter.getFromDate()+"' and '"+invoiceFilter.getToDate()+"'"; 

				}
				else{
					if(invoiceFilter.getPeriod().equals("week")){
						//filterQuery="select * from flight_order_row where company_id in ("+sessionObj.getCompanyid()+") and   date(bookingDate) between  DATE_ADD(CURDATE() ,INTERVAL -6 day) and CURDATE()";
						filterQuery="from FlightOrderRow for where for.companyId=:company_id  and date(for.bookingDate) between  DATE_ADD(CURDATE() ,INTERVAL -6 day) and CURDATE()";
					}
					else if(invoiceFilter.getPeriod().equals("month")){
						//filterQuery="select * from flight_order_row where company_id in ("+sessionObj.getCompanyid()+") and   date(bookingDate) between  DATE_ADD(CURDATE() ,INTERVAL -29 day) and CURDATE()";
						filterQuery="from FlightOrderRow for where for.companyId=:company_id  and date(for.bookingDate) between  DATE_ADD(CURDATE() ,INTERVAL -29 day) and CURDATE()";
					}
					else{
						//filterQuery="select * from flight_order_row where company_id in ("+sessionObj.getCompanyid()+")";
						filterQuery="from FlightOrderRow for where for.companyId=:company_id";
					}
				}
				query =session.createQuery(filterQuery);
				query.setParameter("company_id", sessionObj.getCompanyid());

			}
			else if(!invoiceFilter.getFromDate().equals("") && !invoiceFilter.getToDate().equals("")){
				//filterQuery="select * from flight_order_row where user_id in ("+userIdBuffer+") and date(bookingDate) between '"+invoiceFilter.getFromDate()+"' and '"+invoiceFilter.getToDate()+"'"; 
				filterQuery="from FlightOrderRow for where for.userId in (:userIds) and date(for.bookingDate) between '"+invoiceFilter.getFromDate()+"' and '"+invoiceFilter.getToDate()+"'"; 
				query =session.createQuery(filterQuery);
				query.setParameter("userIds",userIds);
			}
			else{

				if(invoiceFilter.getPeriod().equals("week")){
					//filterQuery="select * from flight_order_row where  user_id in ("+userIdBuffer+") and date(bookingDate) between  DATE_ADD(CURDATE() ,INTERVAL -6 day) and CURDATE()";
					filterQuery="from FlightOrderRow for where for.userId in (:userIds) and date(for.bookingDate) between DATE_ADD(CURDATE() ,INTERVAL -6 day) and CURDATE()";

				}
				else if(invoiceFilter.getPeriod().equals("month")){
					//filterQuery="select * from flight_order_row where user_id in ("+userIdBuffer+") and  date(bookingDate) between  DATE_ADD(CURDATE() ,INTERVAL -29 day) and CURDATE()";
					filterQuery="from FlightOrderRow for where for.userId in (:userIds) and date(for.bookingDate) between DATE_ADD(CURDATE() ,INTERVAL -29 day) and CURDATE()";
				}
				else{
					//filterQuery="select * from flight_order_row where user_id in ("+userIdBuffer+")";
					filterQuery="from FlightOrderRow for where for.userId in (:userIds)";
				}

				query =session.createQuery(filterQuery);
				query.setParameter("userIds",userIds);
			} 
			logger.info("-----filterQuery------------------"+filterQuery);
			reportData_list = query.list();

		}
		catch(HibernateException e){
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
					userIds.addAll(userList);
					/*for (Iterator  userIterator = userList.iterator(); userIterator.hasNext();){
						User userObj= (User)userIterator.next(); 
						if(userObj.getUserrole_id().isUsermode() || userObj.getUserrole_id().isSuperuser()){
							userIds.add(userObj);
						}

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

	public  List<User>  getAgentAddress(String userId,Session session){
		logger.info("---------AhentAddress-----userId------------"+userId);
		List<User> list=null;
		if(userId!=null){
			//session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from User u where u.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", Integer.parseInt(userId));
			list =  query.list();
		}
		return list;
	}
	public Company getCompanyAddressByCompanyId(String companyId,Session session){
		logger.info("--------------companyId---for company Address---------"+companyId);
		Company address=null;
		if(companyId!=null){
			//session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from Company com  where com.companyid=:companyid";
			Query query = session.createQuery(sql);
			query.setParameter("companyid", Integer.parseInt(companyId));
			address = (Company) query.uniqueResult();
			String companynameUppercase = address.getCompanyname().substring(0, 1).toUpperCase() + address.getCompanyname().substring(1);
			address.setCompanyname(companynameUppercase);
		}

		return address;
	}



	public HashMap<String, BigDecimal> getFlightOrderInvoiceDetails(String orderId, User invoiceUser, User bookingUser)
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
			if(walletAmountTranferHistory.getAction().equalsIgnoreCase("FlightBooking Initiated")){
				finalAmount = walletAmountTranferHistory.getAmount();					  
			}
			if(walletAmountTranferHistory.getUserId() == invoiceUser.getId())
			{
				if(walletAmountTranferHistory.getAction().equalsIgnoreCase("Flight Markup")){
					urMarkup=walletAmountTranferHistory.getAmount();
				}
				if(walletAmountTranferHistory.getAction().equalsIgnoreCase("Flight Commission Added")){
					urCommission=walletAmountTranferHistory.getAmount();
				}
				if(walletAmountTranferHistory.getAction().equalsIgnoreCase("Flight Commission Shared")){
					commissionShared=walletAmountTranferHistory.getAmount();
				}
				if(walletAmountTranferHistory.getAction().equalsIgnoreCase("TDS on commission")){
					TDS=walletAmountTranferHistory.getAmount();
				}
			}
			if(walletAmountTranferHistory.getAction().equalsIgnoreCase("Flight Markup") && walletAmountTranferHistory.getUserId() < invoiceUser.getId())
			{
				masterMarkup = masterMarkup.add(walletAmountTranferHistory.getAmount());
			}
			if(walletAmountTranferHistory.getAction().equalsIgnoreCase("Flight Markup") && walletAmountTranferHistory.getUserId() > invoiceUser.getId())
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






	public  InvoiceData getFlightOfflineVoucher(long id){
		InvoiceData reportData=new InvoiceData();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightOrderRow for where for.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id",id);
			FlightOrderRow flightOrderRow = (FlightOrderRow) query.uniqueResult();
			if(flightOrderRow!=null){
				BigDecimal basePrice= flightOrderRow.getPrice().multiply(flightOrderRow.getApiToBaseExchangeRate()) ;
				BigDecimal taxes= flightOrderRow.getTaxes().multiply(flightOrderRow.getApiToBaseExchangeRate()) ;
				BigDecimal totalBasePrice=basePrice.add(flightOrderRow.getMarkUp());
				BigDecimal basePriceInBooking=totalBasePrice.multiply(flightOrderRow.getBaseToBookingExchangeRate());
				BigDecimal taxesInBooking=taxes.multiply(flightOrderRow.getBaseToBookingExchangeRate());
				BigDecimal totalPrice=flightOrderRow.getProcessingFees().add(basePriceInBooking).add(taxesInBooking);
				reportData.setTotAmount(totalPrice.setScale(2, BigDecimal.ROUND_UP));
				reportData.setCusAddress(flightOrderRow.getFlightCustomer().getAddress());
				reportData.setInvNo(flightOrderRow.getInvoiceNo());
				reportData.setConsultant(flightOrderRow.getCreatedBy());
				reportData.setActCode("3000/P001");
				reportData.setBookNo("000020408");	
				reportData.setCurrency(flightOrderRow.getBookingCurrency());
				reportData.setYourRef(flightOrderRow.getOrderId());
				reportData.setBookedDate(DateConversion.getBluestarDate(flightOrderRow.getBookingDate()));
				reportData.setTotMarkup(flightOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP));
				reportData.setMobile(flightOrderRow.getFlightCustomer().getMobile());
				reportData.setTel(flightOrderRow.getFlightCustomer().getPhone());
				reportData.setPrice(basePriceInBooking.setScale(2, BigDecimal.ROUND_UP));
				String FirstNameUppercase = flightOrderRow.getFlightCustomer().getFirstName().substring(0, 1).toUpperCase() + flightOrderRow.getFlightCustomer().getFirstName().substring(1);
				reportData.setAttn(FirstNameUppercase+flightOrderRow.getFlightCustomer().getLastName());
				reportData.setTotBeforeGst(totalPrice.setScale(2, BigDecimal.ROUND_UP));
				BigDecimal gstConvertToBookingCurrency=flightOrderRow.getGst_on_markup().multiply(flightOrderRow.getBaseToBookingExchangeRate());
				reportData.setTotGst(gstConvertToBookingCurrency.setScale(2, BigDecimal.ROUND_UP));
				reportData.setGSTOnMArkup(gstConvertToBookingCurrency);
				reportData.setGSTOnTotMarkup(gstConvertToBookingCurrency.multiply(new BigDecimal(100)).divide(new BigDecimal(6)).setScale(2, BigDecimal.ROUND_UP));
				BigDecimal gstonFlightsConvertToBookingCurrency=flightOrderRow.getGstOnFlights().multiply(flightOrderRow.getBaseToBookingExchangeRate());
				reportData.setGSTOnFlights(gstonFlightsConvertToBookingCurrency.setScale(2, BigDecimal.ROUND_UP));
				BigDecimal totGSTOnFlights=gstonFlightsConvertToBookingCurrency.multiply(new BigDecimal(100)).divide(new BigDecimal(6).setScale(2, BigDecimal.ROUND_UP));
				reportData.setGSTOnFlightsTotMarkup(totGSTOnFlights.setScale(2, BigDecimal.ROUND_UP));
				reportData.setTotWithGst(totalPrice.add(gstConvertToBookingCurrency).setScale(2, BigDecimal.ROUND_UP).add(gstonFlightsConvertToBookingCurrency.setScale(2, BigDecimal.ROUND_UP)));
				reportData.setAgentName(flightOrderRow.getCreatedBy());
				reportData.setTax(taxesInBooking.setScale(2, BigDecimal.ROUND_UP));
				reportData.setFlightOrderRow(flightOrderRow);

			}
			reportData.setTripParticulars(getFlightTripParticulars(flightOrderRow.getId(),session));
			reportData.setPriceBreakupParticulars(getFlightOrderCustomerPricebreakup(flightOrderRow.getId(),session));
			reportData.setOrderCustomerParticulars(getFlightOrderCustomerDetails(flightOrderRow.getId(),session));
			reportData.setTxDetails(getPaymentTxDetails(flightOrderRow.getOrderId(), session)) ;
			reportData.setUserDetails(getAgentAddress(flightOrderRow.getUserId(),session)); 
			reportData.setCompanyAddress(getCompanyAddressByCompanyId(flightOrderRow.getCompanyId(), session));

		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return reportData;

	}
























}
