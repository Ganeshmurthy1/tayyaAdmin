package com.lintas.admin.DAO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
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

import com.admin.flight.fin.sheet.model.HotelandFlightDisReportProperty;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightReportPage;
import com.isl.admin.page.Page;
import com.lintas.admin.common.model.CreditNote;
import com.lintas.admin.common.model.FlightAndHotelBookApiResponse;
import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.flight.model.FlightBookingKeysTemp;
import com.lintas.admin.flight.model.FlightOrderCustomer;
import com.lintas.admin.flight.model.FlightOrderCustomerPriceBreakup;
import com.lintas.admin.flight.model.FlightOrderCustomerSSR;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.FlightOrderTripDetail;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.FlightOrderRowCommission;
import com.lintas.admin.model.FlightOrderRowMarkup;
import com.lintas.admin.model.SalesLeadGeneration;
import com.lintas.admin.model.User;
import com.lintas.admin.model.UserWallet;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.AmountRoundingModeUtil;
import com.lintas.utility.DateConversion;
import com.lintas.utility.GstPropertiesFile;

/**
 * @author info raham
 * created date : 31st Aug 2015
 */
public class FlightOrderDao {
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(FlightOrderDao.class);

	/* load all  company bookings from database based on their user id */
	public  List<ReportData> getFlightOrdersByCompanyId(User userSessionObj,Company companySessionObj){
		String  ordersSql=null;
		List<ReportData> flightOrdersList=new ArrayList<ReportData>();
		if(userSessionObj!=null && companySessionObj!=null){
			ordersSql= "from FlightOrderRow for where for.companyId=:companyid order by for.id desc";
		}
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(ordersSql);
			query.setParameter("companyid", companySessionObj.getCompanyid());
			List<FlightOrderRow> flightOrderRows = query.list();
			if(flightOrderRows!=null && flightOrderRows.size()>0)
			{
				for (FlightOrderRow flightOrderRow:flightOrderRows){
					if(flightOrderRow!=null)
					{
						ReportData reportData=new ReportData();
						BigDecimal basePrice= flightOrderRow.getPrice().multiply(flightOrderRow.getApiToBaseExchangeRate()) ;
						BigDecimal taxes= flightOrderRow.getTaxes().multiply(flightOrderRow.getApiToBaseExchangeRate()) ;
						BigDecimal totalBasePrice=basePrice.add(flightOrderRow.getMarkUp());
						BigDecimal basePriceInBooking=totalBasePrice.multiply(flightOrderRow.getBaseToBookingExchangeRate());
						BigDecimal taxesInBooking=taxes.multiply(flightOrderRow.getBaseToBookingExchangeRate());
						BigDecimal totalPrice=flightOrderRow.getProcessingFees().add(basePriceInBooking).add(taxesInBooking);
						reportData.setAirline(flightOrderRow.getAirline());
						reportData.setPnr(flightOrderRow.getPnr());
						reportData.setCurCode(flightOrderRow.getBookingCurrency());
						reportData.setBookingDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getBookingDate()));
						reportData.setDepartureDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getDepartureDate()));
						if(flightOrderRow.getArrivalDate()!=null && flightOrderRow.getArrivalDate()!="")
							reportData.setArrivalDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getArrivalDate()));
						reportData.setId(flightOrderRow.getId());
						reportData.setCompanyId(flightOrderRow.getCompanyId());
						reportData.setPrice(basePrice.setScale(2,BigDecimal.ROUND_UP));
						reportData.setTax(taxes.setScale(2,BigDecimal.ROUND_UP));
						reportData.setPassengers(flightOrderRow.getPassengerCount());
						reportData.setProcessingFee(flightOrderRow.getProcessingFees());
						
						if(flightOrderRow.getFlightOrderRowServiceTax() !=null){
							reportData.setFinalPrice(totalPrice.add(calculateTotalserviceTax(flightOrderRow)).setScale(2,BigDecimal.ROUND_UP));
						}
						else if (flightOrderRow.getFlightOrderRowGstTax() !=null ) {
							
							if(companySessionObj.getCompanyRole().isAgent() || companySessionObj.getCompanyRole().isDistributor() || companySessionObj.getCompanyRole().isSuperUser() ){
								reportData.setTotal(flightOrderRow.getFinalPrice().setScale(2, BigDecimal.ROUND_UP));
							}
							else if (companySessionObj.getCompanyRole().isCorporate()) {
								reportData.setTotal(flightOrderRow.getFinalPrice().add(calculateTotalGSTTax(flightOrderRow)).setScale(2, BigDecimal.ROUND_UP));
							}
						}
						
						
						reportData.setEmail(flightOrderRow.getFlightCustomer().getEmail());
						reportData.setStatus(flightOrderRow.getStatusAction());
						reportData.setPaymentStatus(flightOrderRow.getPaymentStatus());
						reportData.setOrderId(flightOrderRow.getOrderId());
						reportData.setRoute(flightOrderRow.getOrigin()+"-"+flightOrderRow.getDestination());
						reportData.setConfigId(flightOrderRow.getConfigId());
						reportData.setCreatedBy(flightOrderRow.getCreatedBy());
						reportData.setAgencyUsername(flightOrderRow.getCreatedBy());
						reportData.setApiComments(flightOrderRow.getApi_commit());
						reportData.setUserId(flightOrderRow.getUserId());
						flightOrdersList.add(reportData);
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
			if(session != null && session.isOpen())
				session.close(); 
		}
		return flightOrdersList;
	}


	public ReportData getReportDetailsByRowId(Long id) {
		// TODO Auto-generated method stub
		ReportData reportData=new ReportData();

		try{
			String ordersSql= "from FlightOrderRow row where row.id=:id";
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(ordersSql);
			query.setParameter("id", id);
			FlightOrderRow flightOrderRow = (FlightOrderRow) query.uniqueResult();
			if(flightOrderRow!=null){
				reportData.setOrderId(flightOrderRow.getOrderId());
				reportData.setPnr(flightOrderRow.getPnr());
				reportData.setCurCode(flightOrderRow.getBookingCurrency());
				reportData.setStatus(flightOrderRow.getStatusAction());
				reportData.setPassengers(flightOrderRow.getPassengerCount());
				reportData.setRoute(flightOrderRow.getOrigin()+"-"+flightOrderRow.getDestination());
				reportData.setCreatedBy(flightOrderRow.getCreatedBy());
				reportData.setBookingDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getBookingDate()));
				reportData.setId(flightOrderRow.getId());
				reportData.setAirline(flightOrderRow.getAirline());
				BigDecimal markupInBooking=flightOrderRow.getMarkUp().multiply(flightOrderRow.getBaseToBookingExchangeRate()).setScale(2, BigDecimal.ROUND_UP);
				reportData.setTotAgentSComm(markupInBooking);
				markupInBooking=flightOrderRow.getGst_on_markup().multiply(flightOrderRow.getBaseToBookingExchangeRate().setScale(2, BigDecimal.ROUND_UP));
				reportData.setGstOnMarkup(markupInBooking.setScale(2, BigDecimal.ROUND_UP));
				markupInBooking=flightOrderRow.getGstOnFlights().multiply(flightOrderRow.getBaseToBookingExchangeRate());
				reportData.setGstOnFlights(markupInBooking.setScale(2, BigDecimal.ROUND_UP));
				reportData.setCreditNoteIssued(flightOrderRow.isCreditNoteIssued());
				reportData.setStaff(flightOrderRow.getUpdatedBy());
				reportData.setUserId(flightOrderRow.getUserId());
				reportData.setCompanyId(flightOrderRow.getCompanyId());
				reportData.setFinalPrice(flightOrderRow.getFinalPrice().add(flightOrderRow.getGst_on_markup().multiply(flightOrderRow.getBaseToBookingExchangeRate())).add(flightOrderRow.getGstOnFlights()).add(calculateTotalserviceTax(flightOrderRow)).setScale(2, BigDecimal.ROUND_UP));
				reportData.setApiProvider(flightOrderRow.getProviderAPI());
				reportData.setAgencyUsername(flightOrderRow.getCreatedBy());
				reportData.setDepartureDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getDepartureDate()));
				if(flightOrderRow.getArrivalDate()!=null && flightOrderRow.getArrivalDate()!="")
					reportData.setArrivalDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getArrivalDate()));
				reportData.setOrderRequested(flightOrderRow.isOrderRequested());
				reportData.setBookingMode(flightOrderRow.getBookingMode());
				reportData.setCancelationMode(flightOrderRow.getCancelationMode());
				reportData.setCreditNoteIssued(flightOrderRow.isCreditNoteIssued());
				reportData.setOrderUpdated(flightOrderRow.isOrderUpdated());
				reportData.setPaymentStatus(flightOrderRow.getPaymentStatus());
				reportData.setPaymentMethod(flightOrderRow.getPaidBy());
				GstPropertiesFile gstPropertiesFile =new GstPropertiesFile();
				reportData.setCompanysGstOn(gstPropertiesFile.getGstSwitchonValue());
				BigDecimal gstConvertToBookingCurrency=flightOrderRow.getGst_on_markup().multiply(flightOrderRow.getBaseToBookingExchangeRate());
				reportData.setGstOnMarkup(gstConvertToBookingCurrency.setScale(2, BigDecimal.ROUND_UP));
				reportData.setGSTOnTotMarkup(gstConvertToBookingCurrency.multiply(new BigDecimal(100)).divide(new BigDecimal(6)).setScale(2, BigDecimal.ROUND_UP));
				reportData.setLastticketdate(flightOrderRow.getLastTicketingDate());
				reportData.setTransactionKey(flightOrderRow.getTransaction_key());
				
				if(flightOrderRow.getTransaction_key()!=null)
					reportData.setPricekey(getPricekeyofCurrentFlightOrder(flightOrderRow.getTransaction_key()).getPrice_key());
				if(flightOrderRow.getProcessingFees()!=null)
					reportData.setConvenienceFees(flightOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
				else
					reportData.setConvenienceFees(new BigDecimal(0));
				
				if(flightOrderRow.getFlightOrderRowServiceTax()!=null && flightOrderRow.getFlightOrderRowServiceTax().getManagementFee()!=null)
					reportData.setManagementFee(flightOrderRow.getFlightOrderRowServiceTax().getManagementFee());
				else
					reportData.setManagementFee(new BigDecimal(0));
				if(flightOrderRow.getFlightOrderRowGstTax()!=null && flightOrderRow.getFlightOrderRowGstTax().getManagementFee()!=null)
					reportData.setManagementFee(flightOrderRow.getFlightOrderRowGstTax().getManagementFee());
				else
					reportData.setManagementFee(new BigDecimal(0));
				
					
					
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
	public FlightBookingKeysTemp getPricekeyofCurrentFlightOrder(String transaction_key){
		FlightBookingKeysTemp flightBookingKeysTemp = null;
		try{
			String ordersSql= "from FlightBookingKeysTemp row where row.transaction_key=:transaction_key";
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(ordersSql);
			query.setParameter("transaction_key", transaction_key);
			flightBookingKeysTemp = (FlightBookingKeysTemp) query.uniqueResult();
		}
		catch(Exception e){
			logger.info("-----Exception in getPricekeyofCurrentFlightOrder----------"+e);
		}
		return flightBookingKeysTemp;
	}

	public  List<ReportData> getCompanyFlightOrders(User userSessionObj,Company companySessionObj){
		List<ReportData>  commissionList=new ArrayList<>();

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
			if(parentandChildCommissionsList!=null && parentandChildCommissionsList.size()>0){
				for(FlightOrderRowCommission flightOrderRowCommissionParent:parentandChildCommissionsList){
					FlightOrderRow flightOrderRow =null;
					if(flightOrderRowCommissionParent.getFlightOrderRow()!=null){
						flightOrderRow =flightOrderRowCommissionParent.getFlightOrderRow();
						ReportData flightCommissionReport=new ReportData();
						flightCommissionReport.setOrderId(flightOrderRow.getOrderId());
						flightCommissionReport.setPnr(flightOrderRow.getPnr());
						BigDecimal bookingInBaseGstMarkups=flightOrderRow.getGst_on_markup().multiply(flightOrderRow.getBaseToBookingExchangeRate()).add(flightOrderRow.getGstOnFlights().multiply(flightOrderRow.getBaseToBookingExchangeRate()));
						flightCommissionReport.setFinalPrice(flightOrderRow.getFinalPrice().add(bookingInBaseGstMarkups).add(calculateTotalserviceTax(flightOrderRow)).setScale(2, BigDecimal.ROUND_UP));
						flightCommissionReport.setCurCode(flightOrderRow.getBookingCurrency());
						flightCommissionReport.setStatus(flightOrderRow.getStatusAction());
						flightCommissionReport.setPassengers(flightOrderRow.getPassengerCount());
						flightCommissionReport.setRoute(flightOrderRow.getOrigin()+"-"+flightOrderRow.getDestination());
						flightCommissionReport.setCreatedBy(flightOrderRow.getCreatedBy());
						flightCommissionReport.setBookingDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getBookingDate()));
						flightCommissionReport.setId(flightOrderRow.getId());
						flightCommissionReport.setAirline(flightOrderRow.getAirline());
						flightCommissionReport.setGstOnMarkup(flightOrderRow.getGst_on_markup());
						flightCommissionReport.setCreditNoteIssued(flightOrderRow.isCreditNoteIssued());
						flightCommissionReport.setStaff(flightOrderRow.getUpdatedBy());
						flightCommissionReport.setUserId(flightOrderRow.getUserId());
						flightCommissionReport.setCompanyId(flightOrderRow.getCompanyId());
						flightCommissionReport.setOrderUpdated(flightOrderRow.isOrderUpdated());
						flightCommissionReport.setOrderRequested(flightOrderRow.isOrderRequested());
						flightCommissionReport.setPaymentStatus(flightOrderRow.getPaymentStatus());
						flightCommissionReport.setApiProvider(flightOrderRow.getProviderAPI());
						commissionList.add(flightCommissionReport);
					}
					if(flightOrderRow==null){
						flightOrderRow=new FlightOrderRow();

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



	/* load all  company bookings from database based on company type while searching---------*/

	public  List<String> getCompanyIdList(Company company, int reportType, String companyPreferable)
	{
		List<String> companyIdList = new ArrayList<String>();
		Session session = null;
		try{			
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Company.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction reportDisjunction = Restrictions.disjunction();
			// To get total row count.
			List<Company> list = null;
			switch (reportType){
			case FlightReportFilter.REPORTS_MINE:
				reportConjunction.add(Restrictions.eq("company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}

				criteria.add(reportConjunction);
				list = criteria.list();
				break;			
			case FlightReportFilter.REPORTS_ALL:
				if(!company.getCompanyRole().isAgent() && !company.getCompanyRole().isDistributor()&& !company.getCompanyRole().isCorporate()){
					reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));	
				}
				else{
					reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				}
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{

					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				list = criteria.list();
				break;	

			case FlightReportFilter.REPORTS_MY_AFFILIATES:
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{

					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				list = criteria.list();
				break;

			case FlightReportFilter.REPORTS_MY_AGENCIES:
				logger.info("reportType---------"+reportType);


				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{

					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isAgent",true));
				list = criteria.list();
				//reportConjunction.add(Restrictions.eq("companyRole.isAgent",true));
				break;
			case FlightReportFilter.REPORTS_MY_DISTRIBUTORS:
				//For direct distributors...
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{

					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isDistributor",true));
				list = criteria.list();
				//reportConjunction.add(Restrictions.eq("companyRole.isDistributor",true));
				break;
			case FlightReportFilter.REPORTS_MY_CORPORATES:
				//For direct distributors...
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				//reportDisjunction.add(Restrictions.eq("company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isCorporate",true));
				list = criteria.list();
				//reportConjunction.add(Restrictions.eq("companyRole.isDistributor",true));
				break;

			case FlightReportFilter.REPORTS_ALL_AFFILIATES:
				reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{

					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				list = criteria.list();
				break;

			case FlightReportFilter.REPORTS_ALL_AGENCIES:
				logger.info("reportType---------"+reportType);

				reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{

					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isAgent",true));
				list = criteria.list();
				//reportConjunction.add(Restrictions.eq("companyRole.isAgent",true));
				break;
			case FlightReportFilter.REPORTS_ALL_DISTRIBUTORS:
				//For direct distributors...
				reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{

					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isDistributor",true));
				list = criteria.list();
				//reportConjunction.add(Restrictions.eq("companyRole.isDistributor",true));
				break;


			case FlightReportFilter.ORDERS_ALL:
				reportConjunction.add(Restrictions.eq("companyid",company.getCompanyid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{

					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				list = criteria.list();
				break;		


			default:	
				companyIdList.add(String.valueOf(company.getCompanyid()));
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{

					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				list = criteria.list();
				break;
			}				
			criteria.add(reportConjunction);
			//List<Company> list = criteria.list();
			logger.error("--------------probable Company list -----------------"+list);
			if(list!=null && list.size()>0)
			{
				logger.error("--------------probable Company list size-----------------"+list.size());
				for (Company companyChild :list)
				{
					companyIdList.add(String.valueOf(companyChild.getCompanyid()));
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
		//logger.info("--------reportData_list size-------"+reportData_list.size());
		return companyIdList;
	}
	
	public  List<Integer> getUserIdList(User company,List<Integer> companyIdListold, int reportType)
	{
		List<Integer> companyIdList = new ArrayList<Integer>();
		Session session = null;
		try{			
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(User.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction reportDisjunction = Restrictions.disjunction();
			// To get total row count.
			List<User> list = null;
			switch (reportType){
			case FlightReportFilter.REPORTS_MINE:
				reportConjunction.add(Restrictions.eq("id",company.getId()));
				criteria.add(reportConjunction);
				list = criteria.list();
				break;			
			case FlightReportFilter.REPORTS_ALL:
				reportDisjunction.add(Restrictions.eq("isSuperuser",true)).add(Restrictions.eq("isAdmin",true)).add(Restrictions.eq("isTechHead",true)).add(Restrictions.eq("isTechSupport",true));
				//reportConjunction.add(Restrictions.eq("Companyid",companyIdList));
				criteria.add(Restrictions.in("Companyid", companyIdListold));
				criteria.createCriteria("userrole_id").add(reportDisjunction);
				list = criteria.list();
				
				break;	

			}				
			criteria.add(reportConjunction);
			//List<Company> list = criteria.list();
			logger.error("--------------probable Company list -----------------"+list);
			if(list!=null && list.size()>0)
			{
				logger.error("--------------probable Company list size-----------------"+list.size());
				for (User companyChild :list)
				{
					companyIdList.add(companyChild.getId());
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
		//logger.info("--------reportData_list size-------"+reportData_list.size());
		return companyIdList;
	}
	
	
	/*public List<Integer> getUserIdList(List<Integer> companyIdList,int reportType) {
		List<Integer> userIdList = new ArrayList<>();
		Session session = null;
		try{			
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(User.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction reportDisjunction = Restrictions.disjunction();
			reportDisjunction.add(Restrictions.eq("isSuperuser",true)).add(Restrictions.eq("isAdmin",true)).add(Restrictions.eq("isTechHead",true)).add(Restrictions.eq("isTechSupport",true));
			criteria.add(Restrictions.in("Companyid", companyIdList));
			criteria.createCriteria("userrole_id").add(reportDisjunction);
			List<User> list = null;
			switch (reportType){
			case FlightReportFilter.REPORTS_MINE:
				reportConjunction.add(Restrictions.in("id",companyIdList));
				criteria.add(reportConjunction);
				list = criteria.list();
				break;			
			case FlightReportFilter.REPORTS_ALL:
				reportDisjunction.add(Restrictions.eq("isSuperuser",true)).add(Restrictions.eq("isAdmin",true)).add(Restrictions.eq("isTechHead",true)).add(Restrictions.eq("isTechSupport",true));
				//reportConjunction.add(Restrictions.eq("Companyid",companyIdList));
				criteria.add(Restrictions.in("Companyid", companyIdList));
				criteria.createCriteria("userrole_id").add(reportDisjunction);
				list = criteria.list();
				break;	
				
			}
			if(list!=null && list.size()>0)
			{
				for (User userChild :list)
				{
					userIdList.add(userChild.getId());
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
		//logger.info("--------reportData_list size-------"+reportData_list.size());
		return userIdList;
	}*/
	
	
	
	
	

	public  FlightReportPage getCompanyFlightOrders1(FlightReportPage flightReportPage,Company companySessionObj){
		List<ReportData>  orderList=new ArrayList<>();
		int availablePages = 0;
		int availableItems = 0;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteriaFlightOrderCommission = session.createCriteria(FlightOrderRowCommission.class);				
			Conjunction conjunctionFlightOrderRowCommission = Restrictions.conjunction();
			Conjunction conjunctionFlightOrderRow = Restrictions.conjunction();
			Conjunction conjunctionFlightOrderCustomer = Restrictions.conjunction();
			
			conjunctionFlightOrderRow.add(Restrictions.ne("statusAction", "Failed"));
			conjunctionFlightOrderRow.add(Restrictions.ne("statusAction", "Hold"));
			// To get total row count.
			if(flightReportPage!=null && flightReportPage.isFilterEnabled())
			{
				FlightReportFilter flightReportFilter = flightReportPage.getFlightReportFilter();
				Company company=null;
				if(flightReportFilter.getCompanyId()>0) 
					company=new CompanyDAO().getCompanyProfile(flightReportFilter.getCompanyId());

				else 
					company=flightReportFilter.getLoginCompany();


				List<String> companyIdList = getCompanyIdList(company, flightReportFilter.getReportType(), flightReportFilter.getCompanyName());
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setItems(new ArrayList<ReportData>());
					return flightReportPage;
				}
				conjunctionFlightOrderRowCommission.add(Restrictions.in("CompanyId",companyIdList));

				if(flightReportFilter.getPnr() != null )
					conjunctionFlightOrderRow.add(Restrictions.like("pnr", flightReportFilter.getPnr(), MatchMode.ANYWHERE));

				if(flightReportFilter.getAirlineName() != null && !flightReportFilter.getAirlineName().equalsIgnoreCase("ALL"))
					conjunctionFlightOrderRow.add(Restrictions.like("airline", flightReportFilter.getAirlineName(), MatchMode.ANYWHERE));
				if(flightReportFilter.getUserId()>0)
					conjunctionFlightOrderRow.add(Restrictions.like("userId",  String.valueOf(flightReportFilter.getUserId())));
				
				/*if(flightReportFilter.getEmail()!=null)
					conjunctionFlightOrderRow.add(Restrictions.like("email",  String.valueOf(flightReportFilter.getEmail())));*/

				
				if(flightReportFilter.getFirstName()!= null && !flightReportFilter.getFirstName().equals("")){
					conjunctionFlightOrderCustomer.add(Restrictions.like("firstName", flightReportFilter.getFirstName(), MatchMode.ANYWHERE));
				}
				if(flightReportFilter.getLastName()!= null && !flightReportFilter.getLastName().equals("")){
					conjunctionFlightOrderCustomer.add(Restrictions.like("lastName", flightReportFilter.getLastName(), MatchMode.ANYWHERE));
				}
				if(flightReportFilter.getOrigin() != null && !flightReportFilter.getOrigin().equals(""))
					conjunctionFlightOrderRow.add(Restrictions.like("origin", flightReportFilter.getOrigin(), MatchMode.ANYWHERE));

				if(flightReportFilter.getDestination() != null && !flightReportFilter.getDestination().equals(""))
					conjunctionFlightOrderRow.add(Restrictions.like("destination", flightReportFilter.getDestination(), MatchMode.ANYWHERE));
				
				if(flightReportFilter.getInvoiceNo() != null && !flightReportFilter.getInvoiceNo().equals(""))
					conjunctionFlightOrderRow.add(Restrictions.eq("invoiceNo", flightReportFilter.getInvoiceNo()));

				if(flightReportFilter.getOrderId() != null && !flightReportFilter.getOrderId().equals(""))
					conjunctionFlightOrderRow.add(Restrictions.like("orderId", flightReportFilter.getOrderId(), MatchMode.ANYWHERE));

				if(flightReportFilter.getEmail() != null && !flightReportFilter.getEmail().equals(""))
					conjunctionFlightOrderCustomer.add(Restrictions.like("email", flightReportFilter.getEmail(), MatchMode.ANYWHERE));

				if(flightReportFilter.getMobile() != null && !flightReportFilter.getMobile().equals(""))
					conjunctionFlightOrderCustomer.add(Restrictions.like("mobile", flightReportFilter.getMobile(), MatchMode.ANYWHERE));
				
				if(flightReportFilter.getSupplierName() != null && !flightReportFilter.getSupplierName().equalsIgnoreCase("ALL")){
					Disjunction statusActionDisjunction = Restrictions.disjunction();
					statusActionDisjunction.add(Restrictions.eq("providerAPI", flightReportFilter.getSupplierName().toLowerCase()));
					statusActionDisjunction.add(Restrictions.eq("providerAPI", flightReportFilter.getSupplierName().toUpperCase()));
					statusActionDisjunction.add(Restrictions.eq("providerAPI", flightReportFilter.getSupplierName()));
					conjunctionFlightOrderRow.add(statusActionDisjunction); 
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
						conjunctionFlightOrderRow.add(Restrictions.ge("createdAt", date));

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterBooking().getDtEnd());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						conjunctionFlightOrderRow.add(Restrictions.lt("createdAt", date));

					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());
					}
				}
				if(flightReportFilter.getDateFilterInvoice() != null && flightReportFilter.getDateFilterInvoice().getDtEnd() != null 
						&& !flightReportFilter.getDateFilterInvoice().getDtEnd().equals("") && flightReportFilter.getDateFilterInvoice().getDtStart() != null 
						&& !flightReportFilter.getDateFilterInvoice().getDtStart().equals(""))
				{
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterInvoice().getDtStart());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						conjunctionFlightOrderRow.add(Restrictions.ge("createdAt", date));

					}catch(Exception ex)
					{
						ex.printStackTrace();
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterInvoice().getDtEnd());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						conjunctionFlightOrderRow.add(Restrictions.lt("createdAt", date));

					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());
					}
				}
				
				if(flightReportFilter.getDateFilterTravel() != null && flightReportFilter.getDateFilterTravel().getDtEnd() != null 
						&& !flightReportFilter.getDateFilterTravel().getDtEnd().equals("") && flightReportFilter.getDateFilterTravel().getDtStart() != null 
						&& !flightReportFilter.getDateFilterTravel().getDtStart().equals(""))
				{
					try{
						String [] date = flightReportFilter.getDateFilterTravel().getDtStart().split("-");
						String convertedDate=date[2]+"-"+date[1]+"-"+date[0];
						
						String [] date1 = flightReportFilter.getDateFilterTravel().getDtEnd().split("-");
						String convertedEndDate=date1[2]+"-"+date1[1]+"-"+date1[0];
						conjunctionFlightOrderRow.add(Restrictions.between("departureDate", convertedDate, convertedEndDate));

					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());
					}
				}
				criteriaFlightOrderCommission.add(conjunctionFlightOrderRowCommission);
				criteriaFlightOrderCommission.createCriteria("flightOrderRow").add(conjunctionFlightOrderRow).createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);	
			 
				
				/*conjunctionFlightOrderCustomer*/
				criteriaFlightOrderCommission.addOrder(Order.desc("id"));
			}
			Long rowCount= (Long) criteriaFlightOrderCommission.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			if(rowCount>0)
			{
				List<FlightOrderRowCommission> list = null;
				if(flightReportPage.getMaxItems()==Page.ALL_ITEMS){

					logger.info("MaxResults-------"+flightReportPage.getMaxItems());
					criteriaFlightOrderCommission = session.createCriteria(FlightOrderRowCommission.class);						
					criteriaFlightOrderCommission.add(conjunctionFlightOrderRowCommission);
					criteriaFlightOrderCommission.createCriteria("flightOrderRow").add(conjunctionFlightOrderRow).createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);	
					criteriaFlightOrderCommission.addOrder(Order.desc("id"));
					list = criteriaFlightOrderCommission.list();
					logger.info("Reports size------"+list.size());			
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
						criteriaFlightOrderCommission = session.createCriteria(FlightOrderRowCommission.class);						
						criteriaFlightOrderCommission.add(conjunctionFlightOrderRowCommission);
						criteriaFlightOrderCommission.createCriteria("flightOrderRow").add(conjunctionFlightOrderRow).createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);	
						criteriaFlightOrderCommission.setFirstResult(itemIndex);
						criteriaFlightOrderCommission.setMaxResults(flightReportPage.getMaxItems());
						criteriaFlightOrderCommission.addOrder(Order.desc("id"));
						list = criteriaFlightOrderCommission.list();
						logger.info("Reports size------"+list.size());					
					}
				}
				if(list!=null && list.size()>0){
					for(FlightOrderRowCommission flightOrderRowCommissionParent:list){
						FlightOrderRow flightOrderRow =null;
						if(flightOrderRowCommissionParent.getFlightOrderRow()!=null){
							flightOrderRow =flightOrderRowCommissionParent.getFlightOrderRow();
							ReportData flightCommissionReport=new ReportData();
							flightCommissionReport.setOrderId(flightOrderRow.getOrderId());
							flightCommissionReport.setPnr(flightOrderRow.getPnr());
							BigDecimal bookingInBaseGstMarkups=flightOrderRow.getGst_on_markup().setScale(2, RoundingMode.UP).multiply(flightOrderRow.getBaseToBookingExchangeRate().setScale(2, BigDecimal.ROUND_UP)).add(flightOrderRow.getGstOnFlights().setScale(2, BigDecimal.ROUND_UP).multiply(flightOrderRow.getBaseToBookingExchangeRate().setScale(2, BigDecimal.ROUND_UP)));
							
							
							//flightCommissionReport.setFinalPrice(flightOrderRow.getFinalPrice().add(bookingInBaseGstMarkups).add(calculateTotalserviceTax(flightOrderRow)).setScale(2, BigDecimal.ROUND_UP));
							//flightCommissionReport.setBasePrice(flightOrderRow.getFinalPrice().setScale(2, BigDecimal.ROUND_UP));
							BigDecimal finalPrice = flightOrderRow.getFinalPrice() != null?flightOrderRow.getFinalPrice().setScale(2, RoundingMode.UP):new BigDecimal(0);
							if(flightOrderRow.getFlightOrderRowServiceTax()!=null){
								flightCommissionReport.setTotal(finalPrice.add(calculateTotalserviceTax(flightOrderRow)).setScale(2, BigDecimal.ROUND_UP));
								flightCommissionReport.setFinalPrice(finalPrice);
								}
								else if (flightOrderRow.getFlightOrderRowGstTax()!=null) {
									if(companySessionObj.getCompanyRole().isAgent() || companySessionObj.getCompanyRole().isDistributor() || companySessionObj.getCompanyRole().isSuperUser() ){
										flightCommissionReport.setFinalPrice(finalPrice.setScale(2, RoundingMode.UP));
									}
									else if (companySessionObj.getCompanyRole().isCorporate()) {
										flightCommissionReport.setFinalPrice(finalPrice.add(calculateTotalGSTTax(flightOrderRow)).setScale(2, BigDecimal.ROUND_UP));
									}
									
								}
							
							
							
							flightCommissionReport.setCurCode(flightOrderRow.getBookingCurrency());
							flightCommissionReport.setStatus(flightOrderRow.getStatusAction());
							flightCommissionReport.setPassengers(flightOrderRow.getPassengerCount());
							flightCommissionReport.setRoute(flightOrderRow.getOrigin()+"-"+flightOrderRow.getDestination());
							if(flightOrderRow.getCreatedBy()!=null){
								flightCommissionReport.setCreatedBy(flightOrderRow.getCreatedBy().replace("+", " "));	
							}
							else{
								flightCommissionReport.setCreatedBy(flightOrderRow.getCreatedBy());	
							}
							flightCommissionReport.setBookingDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getBookingDate()));
							flightCommissionReport.setId(flightOrderRow.getId());
							flightCommissionReport.setAirline(flightOrderRow.getAirline());
							flightCommissionReport.setGstOnMarkup(flightOrderRow.getGst_on_markup());
							flightCommissionReport.setCreditNoteIssued(flightOrderRow.isCreditNoteIssued());
							flightCommissionReport.setStaff(flightOrderRow.getUpdatedBy());
							flightCommissionReport.setUserId(flightOrderRow.getUserId());
							flightCommissionReport.setCompanyId(flightOrderRow.getCompanyId());
							flightCommissionReport.setDepartureDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getDepartureDate()));
							flightCommissionReport.setOrderUpdated(flightOrderRow.isOrderUpdated());
							flightCommissionReport.setOrderRequested(flightOrderRow.isOrderRequested());
							flightCommissionReport.setPaymentStatus(flightOrderRow.getPaymentStatus());
							flightCommissionReport.setApiProvider(flightOrderRow.getProviderAPI());
							flightCommissionReport.setCreatedAt(flightOrderRow.getCreatedAt());

							flightCommissionReport.setInvoiceNo(flightOrderRow.getInvoiceNo());
							flightCommissionReport.setInvoiceAmount(flightOrderRow.getTotInvoiceAmount()!=null?flightOrderRow.getTotInvoiceAmount().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0"));
							flightCommissionReport.setFirstName(flightOrderRow.getFlightCustomer().getFirstName());
							flightCommissionReport.setLastName(flightOrderRow.getFlightCustomer().getLastName());
							//ADDED BY BASHA
							if(flightOrderRow.getInsuranceOrderRowId()!=null)
								flightCommissionReport.setInsuranceOrderRowId(flightOrderRow.getInsuranceOrderRowId());
							if(flightOrderRow.getIsInsuranceAdded()!=null)
								flightCommissionReport.setIsInsuranceAdded(flightOrderRow.getIsInsuranceAdded());
							//ENDED BY BASHA
							
							orderList.add(flightCommissionReport);
						}
						flightReportPage.setItems(orderList);
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
			if(session != null && session.isOpen())
				session.close(); 
		}

		return flightReportPage;
	}

	public  FlightReportPage getCompanyFlightReports(FlightReportPage flightReportPage,String showType,Company companySessionObj){
		List<ReportData>  reportData_list=new ArrayList<ReportData>();
		int availablePages = 0;
		int availableItems = 0;
		String companyId=null;
		Session session = null;
		try{
			//2016-06-28
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(FlightOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Conjunction conjunctionFlightOrderCustomer = Restrictions.conjunction();
			Disjunction disjunctionFlightOrderCustomer = Restrictions.disjunction();
			// To get total row count.
			if(flightReportPage!=null && flightReportPage.isFilterEnabled())
			{
				FlightReportFilter flightReportFilter = flightReportPage.getFlightReportFilter();
				companyId=String.valueOf(flightReportFilter.getLoginCompany().getCompanyid());
				/* Add multiple condition separated by AND clause within brackets. */
				List<String> companyIdList  = new ArrayList<String>();
				companyIdList = getCompanyIdList(flightReportFilter.getLoginCompany(), flightReportFilter.getReportType(), flightReportFilter.getCompanyName());
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setItems(new ArrayList<ReportData>());
					return flightReportPage;
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				if(flightReportFilter.getPnr() != null && !flightReportFilter.getPnr().equals(""))
					reportConjunction.add(Restrictions.like("pnr", flightReportFilter.getPnr(), MatchMode.ANYWHERE));

				if(flightReportFilter.getPaxName()!= null && !flightReportFilter.getPaxName().equals("")){
					disjunctionFlightOrderCustomer.add(Restrictions.like("firstName", flightReportFilter.getPaxName(), MatchMode.ANYWHERE));
					disjunctionFlightOrderCustomer.add(Restrictions.like("lastName", flightReportFilter.getPaxName(), MatchMode.ANYWHERE));
				}
				
				if(flightReportFilter.getFirstName()!= null && !flightReportFilter.getFirstName().equals("")){
					conjunctionFlightOrderCustomer.add(Restrictions.like("firstName", flightReportFilter.getFirstName(), MatchMode.ANYWHERE));
				}
				if(flightReportFilter.getLastName()!= null && !flightReportFilter.getLastName().equals("")){
					conjunctionFlightOrderCustomer.add(Restrictions.like("lastName", flightReportFilter.getLastName(), MatchMode.ANYWHERE));
				}
				if(flightReportFilter.getOrigin() != null && !flightReportFilter.getOrigin().equals(""))
					reportConjunction.add(Restrictions.like("origin", flightReportFilter.getOrigin(), MatchMode.ANYWHERE));

				if(flightReportFilter.getDestination() != null && !flightReportFilter.getDestination().equals(""))
					reportConjunction.add(Restrictions.like("destination", flightReportFilter.getDestination(), MatchMode.ANYWHERE));
				
				if(flightReportFilter.getInvoiceNo() != null && !flightReportFilter.getInvoiceNo().equals(""))
					reportConjunction.add(Restrictions.eq("invoiceNo", flightReportFilter.getInvoiceNo()));

				if(flightReportFilter.getOrderId() != null && !flightReportFilter.getOrderId().equals(""))
					reportConjunction.add(Restrictions.like("orderId", flightReportFilter.getOrderId(), MatchMode.ANYWHERE));

				if(flightReportFilter.getEmail() != null && !flightReportFilter.getEmail().equals(""))
					conjunctionFlightOrderCustomer.add(Restrictions.like("email", flightReportFilter.getEmail(), MatchMode.ANYWHERE));

				if(flightReportFilter.getMobile() != null && !flightReportFilter.getMobile().equals(""))
					conjunctionFlightOrderCustomer.add(Restrictions.like("mobile", flightReportFilter.getMobile(), MatchMode.ANYWHERE));

				if(flightReportFilter.getSupplierName() != null && !flightReportFilter.getSupplierName().equalsIgnoreCase("ALL")){
					Disjunction statusActionDisjunction = Restrictions.disjunction();
					statusActionDisjunction.add(Restrictions.eq("providerAPI", flightReportFilter.getSupplierName().toLowerCase()));
					statusActionDisjunction.add(Restrictions.eq("providerAPI", flightReportFilter.getSupplierName().toUpperCase()));
					statusActionDisjunction.add(Restrictions.eq("providerAPI", flightReportFilter.getSupplierName()));
					reportConjunction.add(statusActionDisjunction); 
				}

				
				
				if(flightReportFilter.getBookingStatus() != null && !flightReportFilter.getBookingStatus().equalsIgnoreCase("ALL"))
				{
					Disjunction statusActionDisjunction = Restrictions.disjunction();
					statusActionDisjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus().toLowerCase()));
					statusActionDisjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus().toUpperCase()));
					statusActionDisjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus()));
					reportConjunction.add(statusActionDisjunction);
				}
				if(flightReportFilter.getPaymentStatus() != null && !flightReportFilter.getPaymentStatus().equalsIgnoreCase("ALL"))
				{
					Disjunction statusActionDisjunction = Restrictions.disjunction();
					statusActionDisjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus().toLowerCase()));
					statusActionDisjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus().toUpperCase()));
					statusActionDisjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus()));
					reportConjunction.add(statusActionDisjunction); 
				}
				if(flightReportFilter.getAirlineName() != null && !flightReportFilter.getAirlineName().equalsIgnoreCase("ALL"))
					reportConjunction.add(Restrictions.like("airline", flightReportFilter.getAirlineName(), MatchMode.ANYWHERE));

				if(flightReportFilter.getUserId()>0)
					reportConjunction.add(Restrictions.like("userId",  String.valueOf(flightReportFilter.getUserId())));

				if(flightReportFilter.getCompanyId()>0)
					reportConjunction.add(Restrictions.like("companyId", String.valueOf(flightReportFilter.getCompanyId())));


				if(flightReportFilter.getDateFilterBooking() != null && flightReportFilter.getDateFilterBooking().getDtEnd() != null
						&& !flightReportFilter.getDateFilterBooking().getDtEnd().equals("") && flightReportFilter.getDateFilterBooking().getDtStart() != null 
						&& !flightReportFilter.getDateFilterBooking().getDtStart().equals(""))
				{

					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterBooking().getDtStart());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						reportConjunction.add(Restrictions.ge("createdAt", date));

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

					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());
					}
				}
				if(flightReportFilter.getDateFilterInvoice() != null && flightReportFilter.getDateFilterInvoice().getDtEnd() != null 
						&& !flightReportFilter.getDateFilterInvoice().getDtEnd().equals("") && flightReportFilter.getDateFilterInvoice().getDtStart() != null 
						&& !flightReportFilter.getDateFilterInvoice().getDtStart().equals(""))
				{

					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterInvoice().getDtStart());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						reportConjunction.add(Restrictions.ge("createdAt", date));

					}catch(Exception ex)
					{
						ex.printStackTrace();
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterInvoice().getDtEnd());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.lt("createdAt", date));

					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());
					}
				}
				
				if(flightReportFilter.getDateFilterTravel() != null && flightReportFilter.getDateFilterTravel().getDtEnd() != null 
						&& !flightReportFilter.getDateFilterTravel().getDtEnd().equals("") && flightReportFilter.getDateFilterTravel().getDtStart() != null 
						&& !flightReportFilter.getDateFilterTravel().getDtStart().equals(""))
				{

					{
						try{
							String [] date = flightReportFilter.getDateFilterTravel().getDtStart().split("-");
							String convertedDate=date[2]+"-"+date[1]+"-"+date[0];
							
							String [] date1 = flightReportFilter.getDateFilterTravel().getDtEnd().split("-");
							String convertedEndDate=date1[2]+"-"+date1[1]+"-"+date1[0];
							reportConjunction.add(Restrictions.between("departureDate", convertedDate, convertedEndDate));

						}catch(Exception ex)
						{
							logger.info("##########date max format exception---"+ex.getMessage());
						}
					}
				}

				try{
					if(showType!=null && showType.equalsIgnoreCase("flightconfirm")){
						///reportConjunction.add(Restrictions.in("companyId",companyIdList));
						reportConjunction.add(Restrictions.eq("statusAction","Confirmed"));
					}
					else if(showType!=null && showType.equalsIgnoreCase("flightpaymentfailed")){
						//reportConjunction.add(Restrictions.in("companyId",companyIdList));
						reportConjunction.add(Restrictions.eq("paymentStatus","Failed"));
					}	
					else if(showType!=null && showType.equalsIgnoreCase("flightpayment")){
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
					}
					else if(showType!=null && showType.equalsIgnoreCase("week")){							

						Calendar cal = Calendar.getInstance();
						String formattedDate = targetFormat.format(cal.getTime()); 
						Date  today = targetFormat.parse(formattedDate) ;
						Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));
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
						reportConjunction.add(Restrictions.ne("paymentStatus", "Failed"));
					}

				}catch(Exception ex)
				{
					logger.info("##########today format exception---"+ex.getMessage());

				} 
				criteria.add(reportConjunction);
				criteria.createCriteria("flightCustomer").add(disjunctionFlightOrderCustomer).add(conjunctionFlightOrderCustomer);
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			List<FlightOrderRow> list =null;
			if(rowCount>0)
			{
				if(flightReportPage.getMaxItems()==Page.ALL_ITEMS){
					criteria = session.createCriteria(FlightOrderRow.class);
					criteria.add(reportConjunction);
					criteria.createCriteria("flightCustomer").add(disjunctionFlightOrderCustomer).add(conjunctionFlightOrderCustomer);
					criteria.addOrder(Order.desc("id"));
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
					int pageIndexDb = (flightReportPage.getCurrentPageIndex() > 1)?flightReportPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * flightReportPage.getMaxItems();
					if(itemIndex <= rowCount)
					{
						criteria = session.createCriteria(FlightOrderRow.class);
						criteria.add(reportConjunction);
						criteria.createCriteria("flightCustomer").add(disjunctionFlightOrderCustomer).add(conjunctionFlightOrderCustomer);	
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(flightReportPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
					}
				}
				if(list!=null && list.size()>0)
				{
					for (FlightOrderRow flightOrderRow :list)
					{
						ReportData reportData=new ReportData();
						reportData.setPnr(flightOrderRow.getPnr());
						reportData.setFirstName(flightOrderRow.getCustomer().getFirstName());
						reportData.setLastName(flightOrderRow.getCustomer().getLastName());
						reportData.setAirline(flightOrderRow.getAirline());
						
						BigDecimal finalPrice = flightOrderRow.getFinalPrice() != null?flightOrderRow.getFinalPrice().setScale(2, RoundingMode.UP):new BigDecimal(0);
						if(flightOrderRow.getFlightOrderRowServiceTax()!=null){
							reportData.setTotal(finalPrice.add(calculateTotalserviceTax(flightOrderRow)).setScale(2, BigDecimal.ROUND_UP));
							reportData.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));
							}
							else if (flightOrderRow.getFlightOrderRowGstTax()!=null) {
								if(companySessionObj.getCompanyRole().isAgent() || companySessionObj.getCompanyRole().isDistributor() || companySessionObj.getCompanyRole().isSuperUser() ){
									reportData.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));
								}
								else if (companySessionObj.getCompanyRole().isCorporate()) {
									reportData.setFinalPrice(finalPrice.add(calculateTotalGSTTax(flightOrderRow)).setScale(2, BigDecimal.ROUND_UP));
								}
								
							}
							else{
								reportData.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));
							}
						//reportData.setFinalPrice(flightOrderRow.getFinalPrice().add(calculateTotalserviceTax(flightOrderRow)).setScale(2,BigDecimal.ROUND_UP));
						reportData.setEmail(flightOrderRow.getFlightCustomer().getEmail());
						reportData.setStatus(flightOrderRow.getStatusAction());
						reportData.setPaymentStatus(flightOrderRow.getPaymentStatus());
						reportData.setOrderId(flightOrderRow.getOrderId());
						reportData.setCurCode(flightOrderRow.getBookingCurrency());
						reportData.setBookingDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getBookingDate()));
						reportData.setDepartureDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getDepartureDate()));
						if(flightOrderRow.getArrivalDate()!=null && !flightOrderRow.getArrivalDate().equals("")){
							reportData.setArrivalDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getArrivalDate()));
						}
						FlightOrderRowMarkup flightOrderRowMarkup= getCompanyMarkup(flightOrderRow.getCompanyId(), flightOrderRow.getId());
						if(flightOrderRowMarkup!=null) 
							reportData.setMarkUp(flightOrderRowMarkup.getMarkUp()!=null?flightOrderRowMarkup.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00").multiply(new BigDecimal(flightOrderRow.getPassengerCount())).setScale(2, BigDecimal.ROUND_UP));
						else 
							reportData.setMarkUp(new BigDecimal("0.00"));	
						BigDecimal netPaybleAmount=reportData.getFinalPrice().subtract(reportData.getMarkUp());
						reportData.setNetAmnt(netPaybleAmount.setScale(2,BigDecimal.ROUND_UP));
						StringBuilder descode = new StringBuilder();
						for(int i=0;i<flightOrderRow.getFlightOrderTripDetails().size();i++){
							FlightOrderTripDetail trips = flightOrderRow.getFlightOrderTripDetails().get(i);
							if(i == flightOrderRow.getFlightOrderTripDetails().size()-1)
								descode.append(trips.getDestinationCode());
							else
								descode.append(trips.getDestinationCode() + "/");
						}
						StringBuilder srcCode = new StringBuilder();
						for(int i=0;i<flightOrderRow.getFlightOrderTripDetails().size();i++){
							FlightOrderTripDetail trips = flightOrderRow.getFlightOrderTripDetails().get(i);
							if(i == flightOrderRow.getFlightOrderTripDetails().size()-1)
								srcCode.append(trips.getOriginCode());
							else
								srcCode.append(trips.getOriginCode() + "/");
						}
						reportData.setOrigin(srcCode.toString());
						reportData.setRoute(descode.toString());

						reportData.setCurCode(flightOrderRow.getBookingCurrency());
						reportData.setBookingDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getBookingDate()));
						reportData.setId(flightOrderRow.getId());
						reportData.setCompanyId(flightOrderRow.getCompanyId());
						reportData.setCreatedAt(flightOrderRow.getCreatedAt());
						reportData.setConfigId(flightOrderRow.getConfigId());
						
						//ADDED BY BASHA
						if(flightOrderRow.getInsuranceOrderRowId()!=null)
							reportData.setInsuranceOrderRowId(flightOrderRow.getInsuranceOrderRowId());
						if(flightOrderRow.getIsInsuranceAdded()!=null)
							reportData.setIsInsuranceAdded(flightOrderRow.getIsInsuranceAdded());
						//ENDED BY BASHA

						if(flightOrderRow.getCreatedBy()!=null){
							reportData.setCreatedBy(flightOrderRow.getCreatedBy().replace("+", " "));	
						}
						else{
							reportData.setCreatedBy(flightOrderRow.getCreatedBy());	
						}

						reportData.setAgencyUsername(flightOrderRow.getCreatedBy());
						reportData.setApiComments(flightOrderRow.getApi_commit());
						reportData.setApiProvider(flightOrderRow.getProviderAPI());
						reportData.setUserId(flightOrderRow.getUserId());


						reportData.setInvoiceNo(flightOrderRow.getInvoiceNo());
						reportData.setInvoiceAmount(flightOrderRow.getTotInvoiceAmount()!=null?flightOrderRow.getTotInvoiceAmount().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0"));
						
						reportData.setOrderCustomerId(flightOrderRow.getCustomer().getId());
						User user=getSalesPersonName(flightOrderRow.getUserId());

						if(user!=null) 
							reportData.setSalesPersonName(user.getUsername());
						else 
							reportData.setSalesPersonName("-");

						FlightAndHotelBookApiResponse flightAndHotelBookApiResponse = getApiStatusMessage(flightOrderRow.getId());
						if(flightAndHotelBookApiResponse!=null)
							reportData.setApiResponseMessage(flightAndHotelBookApiResponse.getApiStatusMessage());
						reportData_list.add(reportData);
					}					
					flightReportPage.setItems(reportData_list);
				}
				else
				{
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




	/* load all  company bookings from database based on their user id */
	public  List<ReportData> getCompanyFlightReports(User userSessionObj,Company companySessionObj,String type){
		List<ReportData>  reportData_list=new ArrayList<ReportData>();
		String sql=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(type!=null && !type.equalsIgnoreCase(""))
			{
				boolean sameCompanySearch = false;
				StringBuffer userIdBuffer = new StringBuffer();
				CompanyDAO companyDAO = new CompanyDAO();
				if(!companySessionObj.getCompanyRole().isDistributor() && !companySessionObj.getCompanyRole().isAgent()){
					userIdBuffer = companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
				}
				else if((userSessionObj.getUserrole_id().isAdmin() || userSessionObj.getUserrole_id().isUsermode()) && (companySessionObj.getCompanyRole().isDistributor() || companySessionObj.getCompanyRole().isAgent() ))
				{
					userIdBuffer = companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
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
						sql = "select * from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate between  DATE_ADD(CURDATE() ,INTERVAL -6 day) and CURDATE()";
					}
					else if (sameCompanySearch) 
					{
						sql = "select * from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate between  DATE_ADD(CURDATE() ,INTERVAL -6 day) and CURDATE()";
					}
					if(type!=null && type.equalsIgnoreCase("today")&& !sameCompanySearch)
					{
						sql = "select * from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE()";
					}
					else if (sameCompanySearch) 
					{
						sql = "select * from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE()";
					}
					if(type!=null && type.equalsIgnoreCase("month")&& !sameCompanySearch)
					{
						sql = "select * from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate between  DATE_ADD(CURDATE() ,INTERVAL -29 day) and CURDATE()";
					}
					else if (sameCompanySearch) 
					{
						sql = "select * from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate between  DATE_ADD(CURDATE() ,INTERVAL -29 day) and CURDATE()";
					}
					if(type!=null && type.equalsIgnoreCase("flightconfirm")&& !sameCompanySearch)
					{
						sql = "select * from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE() and status_action='Confirmed'";
					}
					else if (sameCompanySearch) 
					{
						sql = "select * from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE() and status_action='Confirmed'";
					}
					if(type!=null && type.equalsIgnoreCase("flightpayment")&& !sameCompanySearch)
					{
						sql = "select * from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE() and payment_status='Success'";
					}
					else if (sameCompanySearch) 
					{
						sql = "select * from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE() and payment_status='Success'";
					}
					if(type!=null && type.equalsIgnoreCase("flightpaymentfailed") && !sameCompanySearch)
					{
						sql = "select * from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE() and payment_status!='Success'";
					}
					else if (sameCompanySearch) 
					{
						sql = "select * from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE() and payment_status!='Success'";
					}
				}
			}
			else{
				sql = "select * from flight_order_row where  company_id="+companySessionObj.getCompanyid()+" order by id desc";
			}

			SQLQuery query = session.createSQLQuery(sql);
			//logger.info("------sql-----"+sql);
			query.addEntity(FlightOrderRow.class);
			List<FlightOrderRow> list = query.list();
			//logger.info("---------logged in company id-----------------------------"+companySessionObj.getCompanyid());
			if(list!=null && list.size()>0)
			{
				for (FlightOrderRow flightOrderRow :list)
				{
					ReportData reportData=new ReportData();
					BigDecimal basePrice= flightOrderRow.getPrice().multiply(flightOrderRow.getApiToBaseExchangeRate()) ;
					BigDecimal taxes= flightOrderRow.getTaxes().multiply(flightOrderRow.getApiToBaseExchangeRate()) ;
					BigDecimal totalBasePrice=basePrice.add(flightOrderRow.getMarkUp());
					BigDecimal basePriceInBooking=totalBasePrice.multiply(flightOrderRow.getBaseToBookingExchangeRate());
					BigDecimal taxesInBooking=taxes.multiply(flightOrderRow.getBaseToBookingExchangeRate());
					BigDecimal bookingInBaseGstMarkups=flightOrderRow.getGst_on_markup().multiply(flightOrderRow.getBaseToBookingExchangeRate()).add(flightOrderRow.getGstOnFlights().multiply(flightOrderRow.getBaseToBookingExchangeRate()));
					BigDecimal totalPrice=flightOrderRow.getProcessingFees().add(basePriceInBooking).add(taxesInBooking).add(bookingInBaseGstMarkups);
					//logger.info("totalPrice----in booking--------------------"+totalPrice);
					reportData.setAirline(flightOrderRow.getAirline());
					reportData.setPnr(flightOrderRow.getPnr());
					reportData.setCurCode(flightOrderRow.getBookingCurrency());
					reportData.setBookingDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getBookingDate()));
					reportData.setDepartureDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getDepartureDate()));
					if(flightOrderRow.getArrivalDate()!=null && flightOrderRow.getArrivalDate()!="")
						reportData.setArrivalDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getArrivalDate()));
					reportData.setId(flightOrderRow.getId());
					reportData.setCompanyId(flightOrderRow.getCompanyId());
					reportData.setPrice(basePrice.setScale(2,BigDecimal.ROUND_UP));
					reportData.setTax(taxes.setScale(2,BigDecimal.ROUND_UP));
					//reportData.setMarkUp(flightOrderRowMarkup.getMarkUp());

					reportData.setPassengers(flightOrderRow.getPassengerCount());
					reportData.setProcessingFee(flightOrderRow.getProcessingFees());
					reportData.setFinalPrice(totalPrice.add(calculateTotalserviceTax(flightOrderRow)).setScale(2,BigDecimal.ROUND_UP));
					reportData.setEmail(flightOrderRow.getFlightCustomer().getEmail());
					reportData.setStatus(flightOrderRow.getStatusAction());
					reportData.setPaymentStatus(flightOrderRow.getPaymentStatus());
					reportData.setOrderId(flightOrderRow.getOrderId());
					StringBuilder descode = new StringBuilder();
					for(int i=0;i<flightOrderRow.getFlightOrderTripDetails().size();i++){
						FlightOrderTripDetail trips = flightOrderRow.getFlightOrderTripDetails().get(i);
						if(i == flightOrderRow.getFlightOrderTripDetails().size()-1)
							descode.append(trips.getDestinationCode());
						else
							descode.append(trips.getDestinationCode() + "/");
					}

					reportData.setRoute(descode.toString());
					reportData.setConfigId(flightOrderRow.getConfigId());
					reportData.setCreatedBy(flightOrderRow.getCreatedBy());
					reportData.setAgencyUsername(flightOrderRow.getCreatedBy());
					reportData.setApiComments(flightOrderRow.getApi_commit());
					reportData.setApiProvider(flightOrderRow.getProviderAPI());
					reportData.setUserId(flightOrderRow.getUserId());
					reportData_list.add(reportData);
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
		//logger.info("--------reportData_list size-------"+reportData_list.size());
		return reportData_list;
	}





	/* this method for get userIDs by passing  company id*/
	public List<User> getUserIdsForCompanyReports(List<Company> companiesList){

		List<User> userIds =new ArrayList<User>();

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(companiesList!=null && companiesList.size()>0){
				logger.info("------companiesList---size--------"+companiesList.size());
				for(Company companyObj:companiesList){
					logger.info("------company Ids-----------"+companyObj.getCompanyid()+"-------company user id---------------"+companyObj.getCompany_userid());
					String useridsSql="from User u where u.Companyid=:companyid";
					Query userQuery = session.createQuery(useridsSql);
					userQuery.setParameter("companyid",companyObj.getCompanyid());
					List userList =userQuery.list();
					userIds.addAll(userList);

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
	/* this method for get CompanyBookings by passing userids*/
	public List<ReportData> getCompanyBookingsWithUserId(List<Company> directCompaniesLIst,List<User> userIds,ReportData filterObj,Company sessionObj){
		FlightOrderRow  customerBookings=null;
		String filterQuery=null;
		List<ReportData>  reportData_list=new ArrayList<ReportData>();
		logger.info("------fillter Company type------"+filterObj.getFilterCompanyType());
		logger.info("------fillter Airline------"+filterObj.getAirline());
		logger.info("------fillter Status------"+filterObj.getStatus());
		logger.info("------fillter fromdate ------"+filterObj.getYesterDayDate());
		logger.info("------fillter TodayDate------"+filterObj.getTodayDate());
		logger.info("------fillter orderId------"+filterObj.getOrderId());
		logger.info("------fillter getBookingDate------"+filterObj.getBookingDate());
		logger.info("------fillter company id-----"+filterObj.getCompanyName());
		logger.info("------fillter agent id-----"+filterObj.getAgentName());
		List<String> companyIds=new ArrayList<>();
		if(directCompaniesLIst!=null && directCompaniesLIst.size()>0){
			for (Company companyId:directCompaniesLIst) {
				companyIds.add(String.valueOf(companyId.getCompanyid()));
			}
		} 
		String loginId=String.valueOf(sessionObj.getCompanyid());
		Query query=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(filterObj.getFilterCompanyType().equals("mine") && (filterObj.getAirline().equalsIgnoreCase("all") && filterObj.getStatus().equalsIgnoreCase("all") &&(!filterObj.getOrderId().equals("") ||  !filterObj.getPnr().equals("") || !filterObj.getBookingDate().equals("") || !filterObj.getDepartureDate().equals("") || !filterObj.getCompanyName().equals("") || !filterObj.getAgentName().equals("") ))){
				filterQuery="from FlightOrderRow fr where fr.orderId=:orderid or fr.pnr=:pnr or fr.bookingDate=:bookingDate  or fr.departureDate=:departureDate or fr.companyId=:company_id or fr.userId=:user_id";
				query= session.createQuery(filterQuery);
				query.setParameter("orderid", filterObj.getOrderId());
				query.setParameter("pnr", filterObj.getPnr());
				query.setParameter("bookingDate", filterObj.getBookingDate());
				query.setParameter("departureDate", filterObj.getDepartureDate());
				query.setParameter("company_id", filterObj.getCompanyName());
				query.setParameter("user_id", filterObj.getAgentName());
			}
			else if(filterObj.getFilterCompanyType().equals("mine") && (filterObj.getAirline().equalsIgnoreCase("all") && filterObj.getStatus().equalsIgnoreCase("all") && !filterObj.getPaymentStatus().equals("0"))){

				filterQuery="from FlightOrderRow fr where fr.paymentStatus=:payment_status";
				query= session.createQuery(filterQuery);
				query.setParameter("payment_status", filterObj.getPaymentStatus());
			}

			else if(filterObj.getFilterCompanyType().equals("mine") && (filterObj.getAirline().equalsIgnoreCase("all") && filterObj.getStatus().equalsIgnoreCase("all") && (!filterObj.getEmail().equals("") || !filterObj.getMobile().equals("") || !filterObj.getFirstName().equals("") || !filterObj.getLastName().equals("") ))){

				//filterQuery="select * from flight_order_row  where customer_id in (select id from order_customer where email='"+filterObj.getEmail()+"' or mobile='"+filterObj.getMobile()+"' or firstName='"+filterObj.getFirstName()+"' or lastName='"+filterObj.getLastName()+"' )" ;
				String sql="from OrderCustomer orc where orc.email=:email or orc.mobile=:mobile  or orc.firstName=:firstName or orc.lastName=:lastName";
				query= session.createQuery(sql);
				query.setParameter("email",filterObj.getEmail());
				query.setParameter("mobile", filterObj.getMobile());
				query.setParameter("firstName", filterObj.getFirstName());
				query.setParameter("lastName", filterObj.getLastName());
				List<OrderCustomer> list = query.list();
				List<Long> customerids = new ArrayList<>();
				if(list!=null && list.size()>0){
					for(OrderCustomer oc:list){
						customerids.add(oc.getId());
					}
				}
				logger.info("customerids--"+customerids);
				if(customerids!=null && customerids.size()>0){
					filterQuery="from FlightOrderRow fr where fr.flightCustomer.id in (:customerIds)";
					query= session.createQuery(filterQuery);
					query.setParameterList("customerIds",customerids);
				}
			}

			else if(filterObj.getFilterCompanyType().equals("mine")){
				if(!filterObj.getYesterDayDate().equals("") && !filterObj.getTodayDate().equals("")){
					if(filterObj.getAirline().equalsIgnoreCase("all") &&filterObj.getStatus().equalsIgnoreCase("all")){
						filterQuery="from FlightOrderRow fr where fr.companyId=:company_id and date(fr.bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' order by fr.id desc"; 
						query= session.createQuery(filterQuery);
						query.setParameter("company_id",loginId);
					}
					else if(!filterObj.getAirline().equalsIgnoreCase("all") && !filterObj.getStatus().equalsIgnoreCase("all")){
						filterQuery="from FlightOrderRow fr where fr.companyId=:company_id and date(fr.bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' and fr.airline=:airline  and fr.statusAction=:status_action order by fr.id desc"; 
						query= session.createQuery(filterQuery);
						query.setParameter("company_id",loginId);
						query.setParameter("airline", filterObj.getAirline());
						query.setParameter("status_action", filterObj.getStatus());
					}
					else if(!filterObj.getAirline().equalsIgnoreCase("all") && filterObj.getStatus().equalsIgnoreCase("all")){
						filterQuery="from FlightOrderRow fr where fr.companyId=:company_id and date(fr.bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' and and fr.airline=:airline order by fr.id desc"; 
						query= session.createQuery(filterQuery);
						query.setParameter("company_id",loginId);
						query.setParameter("airline", filterObj.getAirline());
					}
					else if(filterObj.getAirline().equalsIgnoreCase("all") &&!filterObj.getStatus().equalsIgnoreCase("all")){
						filterQuery="from FlightOrderRow fr where fr.companyId=:company_id and date(fr.bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' and fr.statusAction=:status_action order by fr.id desc"; 
						query= session.createQuery(filterQuery);
						query.setParameter("company_id",loginId);
						query.setParameter("status_action", filterObj.getStatus());
					}
				}
				else{
					if(!filterObj.getAirline().equalsIgnoreCase("all") && !filterObj.getStatus().equalsIgnoreCase("all")){
						filterQuery="from FlightOrderRow fr where fr.companyId=:company_id and fr.airline=:airline  and fr.statusAction=:status_action order by fr.id desc"; 
						query= session.createQuery(filterQuery);
						query.setParameter("company_id",loginId);
						query.setParameter("airline", filterObj.getAirline());
						query.setParameter("status_action", filterObj.getStatus());
					}
					else if(!filterObj.getAirline().equalsIgnoreCase("all") && filterObj.getStatus().equalsIgnoreCase("all")){
						filterQuery="from FlightOrderRow fr where fr.companyId=:company_id and fr.airline=:airline order by fr.id desc"; 
						query= session.createQuery(filterQuery);
						query.setParameter("company_id",loginId);
						query.setParameter("airline", filterObj.getAirline());

					}
					else if(filterObj.getAirline().equalsIgnoreCase("all") && !filterObj.getStatus().equalsIgnoreCase("all")){
						filterQuery="from FlightOrderRow fr where fr.companyId=:company_id and fr.statusAction=:status_action order by fr.id desc"; 
						query= session.createQuery(filterQuery);
						query.setParameter("company_id",loginId);
						query.setParameter("status_action", filterObj.getStatus());
					}
					else if(filterObj.getFilterCompanyType().equalsIgnoreCase("mine")){
						filterQuery="from FlightOrderRow fr where fr.companyId=:company_id order by fr.id desc";
						query= session.createQuery(filterQuery);
						query.setParameter("company_id",loginId);
					} 
				}
			}

			else if(!filterObj.getYesterDayDate().equals("") && !filterObj.getTodayDate().equals("")){
				if(filterObj.getAirline().equalsIgnoreCase("all") &&filterObj.getStatus().equalsIgnoreCase("all")){
					filterQuery="from FlightOrderRow fr where fr.companyId in (:directCompanyIds) and date(fr.bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' order by fr.id desc"; 
					query= session.createQuery(filterQuery);
					query.setParameterList("directCompanyIds",companyIds);
				}
				else if(!filterObj.getAirline().equalsIgnoreCase("all") && !filterObj.getStatus().equalsIgnoreCase("all")){
					filterQuery="from FlightOrderRow fr where fr.companyId in (:directCompanyIds) and date(fr.bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' and and fr.airline=:airline  and fr.statusAction=:status_action order by fr.id desc"; 
					query= session.createQuery(filterQuery);
					query.setParameterList("directCompanyIds",companyIds);
					query.setParameter("airline", filterObj.getAirline());
					query.setParameter("status_action", filterObj.getStatus());
				}
				else if(!filterObj.getAirline().equalsIgnoreCase("all") && filterObj.getStatus().equalsIgnoreCase("all")){
					filterQuery="from FlightOrderRow fr where fr.companyId in (:directCompanyIds) and date(fr.bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' and fr.airline=:airline order by fr.id desc"; 
					query= session.createQuery(filterQuery);
					query.setParameterList("directCompanyIds",companyIds);
					query.setParameter("airline", filterObj.getAirline());

				}
				else if(filterObj.getAirline().equalsIgnoreCase("all") &&!filterObj.getStatus().equalsIgnoreCase("all")){
					filterQuery="from FlightOrderRow fr where fr.companyId in (:directCompanyIds) and date(fr.bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' and fr.statusAction=:status_action order by fr.id desc"; 
					query= session.createQuery(filterQuery);
					query.setParameterList("directCompanyIds",companyIds);
					query.setParameter("status_action", filterObj.getStatus());
				}

			}
			else{
				if(!filterObj.getAirline().equalsIgnoreCase("all") && !filterObj.getStatus().equalsIgnoreCase("all")){
					filterQuery="from FlightOrderRow fr where fr.companyId in (:directCompanyIds) and fr.airline=:airline  and fr.statusAction=:status_action order by fr.id desc"; 
					query= session.createQuery(filterQuery);
					query.setParameterList("directCompanyIds",companyIds);
					query.setParameter("airline", filterObj.getAirline());
					query.setParameter("status_action", filterObj.getStatus());
				}
				else if(!filterObj.getAirline().equalsIgnoreCase("all") && filterObj.getStatus().equalsIgnoreCase("all")){
					filterQuery="from FlightOrderRow fr where fr.companyId in (:directCompanyIds) and fr.airline=:airline order by fr.id desc"; 
					query= session.createQuery(filterQuery);
					query.setParameterList("directCompanyIds",companyIds);
					query.setParameter("airline", filterObj.getAirline());

				}
				else if(filterObj.getAirline().equalsIgnoreCase("all") && !filterObj.getStatus().equalsIgnoreCase("all")){
					filterQuery="from FlightOrderRow fr where fr.companyId in (:directCompanyIds)  and fr.statusAction=:status_action order by fr.id desc"; 
					query= session.createQuery(filterQuery);
					query.setParameterList("directCompanyIds",companyIds);
					query.setParameter("status_action", filterObj.getStatus());
				}

				else {
					filterQuery="from FlightOrderRow fr where fr.companyId in (:directCompanyIds) order by fr.id desc"; 
					query= session.createQuery(filterQuery);
					query.setParameterList("directCompanyIds",companyIds);
				}
			}
			logger.info("----filterQuery------"+filterQuery);
			List<FlightOrderRow> list = query.list(); 
			for (Iterator  iterator = list.iterator(); iterator.hasNext();){
				customerBookings= (FlightOrderRow)iterator.next();  
				ReportData reportData=new ReportData();
				reportData.setAirline(customerBookings.getAirline());
				reportData.setPnr(customerBookings.getPnr());
				reportData.setCurCode(customerBookings.getBookingCurrency());
				reportData.setBookingDate(DateConversion.getBluestarDateddMMyyyy(customerBookings.getBookingDate()));
				reportData.setDepartureDate(DateConversion.getBluestarDateddMMyyyy(customerBookings.getDepartureDate()));
				reportData.setArrivalDate(DateConversion.getBluestarDateddMMyyyy(customerBookings.getArrivalDate()));
				reportData.setId(customerBookings.getId());
				reportData.setCompanyId(customerBookings.getCompanyId());
				reportData.setPrice(customerBookings.getPrice());
				reportData.setPassengers(customerBookings.getPassengerCount());
				reportData.setProcessingFee(customerBookings.getProcessingFees());
				BigDecimal bookingInBaseGstMarkups=customerBookings.getGst_on_markup().multiply(customerBookings.getBaseToBookingExchangeRate());
				reportData.setFinalPrice(customerBookings.getFinalPrice().add(bookingInBaseGstMarkups).add(calculateTotalserviceTax(customerBookings)).setScale(2, BigDecimal.ROUND_UP));
				reportData.setEmail(customerBookings.getFlightCustomer().getEmail());
				reportData.setStatus(customerBookings.getStatusAction());
				reportData.setPaymentStatus(customerBookings.getPaymentStatus());
				reportData.setOrderId(customerBookings.getOrderId());
				reportData.setRoute(customerBookings.getOrigin()+"-"+customerBookings.getDestination());
				reportData.setConfigId(customerBookings.getConfigId());
				reportData.setCreatedBy(customerBookings.getCreatedBy());
				reportData.setAgencyUsername(customerBookings.getCreatedBy());
				reportData.setApiComments(customerBookings.getApi_commit());
				reportData.setUserId(customerBookings.getUserId());
				reportData_list.add(reportData);
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}

		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}

		return reportData_list;
	}
	/* load all  company bookings from database based on company type while searching---------*/
	public  List<ReportData> getCompanyFlightReportsByComapnyType1(User userSessionObj,Company companySessionObj, ReportData filterObj){

		List<Company> newCompaniesList= new ArrayList<Company>();
		String sql=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(userSessionObj!=null && filterObj!=null){
				if((userSessionObj.getUserrole_id().isUsermode() && companySessionObj.getCompanyRole().isDistributor()) || (companySessionObj.getCompanyRole().isDistributor() && userSessionObj.getCompanyid()==companySessionObj.getCompanyid())){
					sql="from Company com where com.parent_company_userid=:parentcompanyuserid or com.company_userid=:companyuserid";
					logger.info("---Filter distributor Query-----"+sql);
					Query  companyQuery = session.createQuery(sql);
					companyQuery.setParameter("parentcompanyuserid", companySessionObj.getCompany_userid());
					companyQuery.setParameter("companyuserid", companySessionObj.getCompany_userid());
					List<Company>  companiesList = companyQuery.list();
					if(filterObj.getFilterCompanyType().equalsIgnoreCase("all")){
						for (Company companyObj:companiesList){
							newCompaniesList.add(companyObj);
						}
					}

					else if(filterObj.getFilterCompanyType().equalsIgnoreCase("agency")){
						for (Company companyObj:companiesList){
							if(!companyObj.getCompany_userid().equals(companySessionObj.getCompany_userid())) 
								newCompaniesList.add(companyObj);
						}

					}

				}

				else if((userSessionObj.getUserrole_id().isUsermode() && companySessionObj.getCompanyRole().isAgent()) || (companySessionObj.getCompanyRole().isAgent() && userSessionObj.getCompanyid()==companySessionObj.getCompanyid())){
					sql="from Company com where com.company_userid=:companyuserid";
					logger.info("---Filter Agency Query-----"+sql);
					Query  companyQuery = session.createQuery(sql);
					companyQuery.setParameter("companyuserid", companySessionObj.getCompany_userid());
					List<Company> companiesList = companyQuery.list();
					if(filterObj.getFilterCompanyType().equalsIgnoreCase("all")){
						for(Company companyObj:companiesList){
							newCompaniesList.add(companyObj);
						}
					}
				}

				else if(userSessionObj.getUserrole_id().isSuperuser() || (userSessionObj.getCompanyid()==companySessionObj.getCompanyid())){
					//sql="select * from company where super_company_userid='"+companySessionObj.getCompany_userid()+"'";
					sql="from Company com where com.parent_company_userid=:parentcompanyuserid  or com.company_userid=:companyuserid";
					logger.info("---Filter super user  Query-----"+sql);
					Query  companyQuery = session.createQuery(sql);
					companyQuery.setParameter("parentcompanyuserid",companySessionObj.getCompany_userid());
					companyQuery.setParameter("companyuserid",companySessionObj.getCompany_userid());
					List<Company> companiesList = companyQuery.list();
					if(filterObj.getFilterCompanyType().equalsIgnoreCase("all")){
						for(Company companyObj:companiesList){
							newCompaniesList.add(companyObj);
						}
					}

					else if(filterObj.getFilterCompanyType().equalsIgnoreCase("dis")){
						for(Company companyObj:companiesList){
							if(companyObj.getCompanyRole().isDistributor()){
								newCompaniesList.add(companyObj);
								/*sql="select * from company where parent_company_userid='"+companyObj.getCompany_userid()+"' or company_userid='"+companyObj.getCompany_userid()+"'";
								logger.info("--------Super user distributor-query-------   "+sql);
								SQLQuery  disQuery = session.createSQLQuery(sql);
								disQuery.addEntity(Company.class);
								 newCompaniesList.addAll(disQuery.list()) ;*/
							}
						}
					}

					else if(filterObj.getFilterCompanyType().equalsIgnoreCase("agency")){
						for (Company companyObj:companiesList){
							if(companyObj.getCompanyRole().isAgent()){
								newCompaniesList.add(companyObj);
							}
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
		return getCompanyBookingsWithCompanyId(newCompaniesList, getUserIdsForCompanyReports(newCompaniesList),filterObj,companySessionObj);
	}

	/* this method for get CompanyBookings by passing userids*/
	public List<ReportData> getCompanyBookingsWithCompanyId(List<Company> directCompaniesLIst,List<User> userIds,ReportData filterObj,Company sessionObj){
		//FlightOrderRow  customerBookings=null;
		String filterQuery=null;
		List<ReportData>  reportData_list=new ArrayList<ReportData>();
		List<String>  companyIds=new ArrayList<>();

		logger.info("------fillter Company type------"+filterObj.getFilterCompanyType());
		logger.info("------fillter Airline------"+filterObj.getAirline());
		logger.info("------fillter Status------"+filterObj.getStatus());
		logger.info("------fillter fromdate ------"+filterObj.getYesterDayDate());
		logger.info("------fillter TodayDate------"+filterObj.getTodayDate());
		logger.info("------fillter orderId------"+filterObj.getOrderId());
		logger.info("------fillter getBookingDate------"+filterObj.getBookingDate());
		logger.info("------fillter company id-----"+filterObj.getCompanyName());
		logger.info("------fillter agent id-----"+filterObj.getAgentName());
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(directCompaniesLIst!=null && directCompaniesLIst.size()>0){
				for (Company companyId:directCompaniesLIst) {
					companyIds.add(String.valueOf(companyId.getCompanyid()));
				}
			} 
			String loginComId=String.valueOf(sessionObj.getCompanyid());
			Query query = null;

			if(filterObj.getFilterCompanyType().equals("mine")){
				if(!filterObj.getYesterDayDate().equals("") && !filterObj.getTodayDate().equals("")){
					if(filterObj.getAirline().equalsIgnoreCase("all") &&filterObj.getStatus().equalsIgnoreCase("all")){
						//filterQuery="select * from flight_order_row where company_id in ("+sessionObj.getCompanyid()+") and date(bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' order by id desc"; 
						filterQuery="from FlightOrderRow fr where fr.companyId=:company_id and date(fr.bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' order by fr.id desc"; 
						query= session.createQuery(filterQuery);
						query.setParameter("company_id",loginComId);
					}
					else if(!filterObj.getAirline().equalsIgnoreCase("all") && !filterObj.getStatus().equalsIgnoreCase("all")){
						//filterQuery="select * from flight_order_row where company_id in ("+sessionObj.getCompanyid()+") and date(bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' and airline='"+filterObj.getAirline()+"' and status_action='"+filterObj.getStatus()+"' order by id desc"; 
						filterQuery="from FlightOrderRow fr where fr.companyId=:company_id and date(fr.bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' and fr.airline=:airline  and fr.statusAction=:status_action order by fr.id desc"; 
						query= session.createQuery(filterQuery);
						query.setParameter("company_id",loginComId);
						query.setParameter("airline", filterObj.getAirline());
						query.setParameter("status_action", filterObj.getStatus());
					}
					else if(!filterObj.getAirline().equalsIgnoreCase("all") && filterObj.getStatus().equalsIgnoreCase("all")){
						//filterQuery="select * from flight_order_row where company_id in ("+sessionObj.getCompanyid()+") and date(bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' and airline='"+filterObj.getAirline()+"' order by id desc"; 
						filterQuery="from FlightOrderRow fr where fr.companyId=:company_id and date(fr.bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' and fr.airline=:airline order by fr.id desc"; 
						query= session.createQuery(filterQuery);
						query.setParameter("company_id",loginComId);
						query.setParameter("airline", filterObj.getAirline());
					}
					else if(filterObj.getAirline().equalsIgnoreCase("all") &&!filterObj.getStatus().equalsIgnoreCase("all")){
						//filterQuery="select * from flight_order_row where company_id in ("+sessionObj.getCompanyid()+") and date(bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' and status_action='"+filterObj.getStatus()+"' order by id desc"; 
						filterQuery="from FlightOrderRow fr where fr.companyId=:company_id and date(fr.bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' and fr.statusAction=:status_action order by fr.id desc";
						query= session.createQuery(filterQuery);
						query.setParameter("company_id",loginComId);
						query.setParameter("status_action", filterObj.getStatus());
					}
				}
				else{
					if(!filterObj.getAirline().equalsIgnoreCase("all") && !filterObj.getStatus().equalsIgnoreCase("all")){
						//filterQuery="SELECT  * FROM flight_order_row where company_id in ("+sessionObj.getCompanyid()+") and airline='"+filterObj.getAirline()+"' and status_action='"+filterObj.getStatus()+"' order by id desc";
						filterQuery="from FlightOrderRow fr where fr.companyId=:company_id and fr.airline=:airline  and fr.statusAction=:status_action order by fr.id desc"; 
						query= session.createQuery(filterQuery);
						query.setParameter("company_id",loginComId);
						query.setParameter("airline", filterObj.getAirline());
						query.setParameter("status_action", filterObj.getStatus());
					}
					else if(!filterObj.getAirline().equalsIgnoreCase("all") && filterObj.getStatus().equalsIgnoreCase("all")){
						//filterQuery="SELECT  * FROM flight_order_row where  company_id in ("+sessionObj.getCompanyid()+") and airline='"+filterObj.getAirline()+"' order by id desc";
						filterQuery="from FlightOrderRow fr where fr.companyId=:company_id and fr.airline=:airline order by fr.id desc"; 
						query= session.createQuery(filterQuery);
						query.setParameter("company_id",loginComId);
						query.setParameter("airline", filterObj.getAirline());
					}
					else if(filterObj.getAirline().equalsIgnoreCase("all") && !filterObj.getStatus().equalsIgnoreCase("all")){
						//filterQuery="SELECT  * FROM flight_order_row where  company_id in ("+sessionObj.getCompanyid()+") and status_action='"+filterObj.getStatus()+"' order by id desc";
						filterQuery="from FlightOrderRow fr where fr.companyId=:company_id and fr.statusAction=:status_action order by fr.id desc"; 
						query= session.createQuery(filterQuery);
						query.setParameter("company_id",loginComId);
						query.setParameter("status_action", filterObj.getStatus());
					}
					else if(filterObj.getFilterCompanyType().equalsIgnoreCase("mine")){
						//filterQuery="select * from flight_order_row where company_id in ("+sessionObj.getCompanyid()+") order by id desc";
						filterQuery="from FlightOrderRow fr where fr.companyId=:company_id order by fr.id desc"; 
						query= session.createQuery(filterQuery);
						query.setParameter("company_id",loginComId);
					} 
				}
			}

			else if(!filterObj.getYesterDayDate().equals("") && !filterObj.getTodayDate().equals("")){
				if(filterObj.getAirline().equalsIgnoreCase("all") &&filterObj.getStatus().equalsIgnoreCase("all")){
					//filterQuery="select * from flight_order_row where company_id in ("+directCompanyIdsBuffer+") and date(bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' order by id desc"; 
					filterQuery="from FlightOrderRow fr where fr.companyId in (:directCompanyIds) and date(fr.bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' order by fr.id desc"; 
					query= session.createQuery(filterQuery);
					query.setParameterList("directCompanyIds",companyIds);

				}
				else if(!filterObj.getAirline().equalsIgnoreCase("all") && !filterObj.getStatus().equalsIgnoreCase("all")){
					//filterQuery="select * from flight_order_row where company_id in ("+directCompanyIdsBuffer+") and date(bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' and airline='"+filterObj.getAirline()+"' and status_action='"+filterObj.getStatus()+"' order by id desc"; 

					filterQuery="from FlightOrderRow fr where fr.companyId in (:directCompanyIds) and date(fr.bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' and fr.airline=:airline  and fr.statusAction=:status_action order by fr.id desc"; 
					query= session.createQuery(filterQuery);
					query.setParameterList("directCompanyIds",companyIds);
					query.setParameter("airline", filterObj.getAirline());
					query.setParameter("status_action", filterObj.getStatus());
				}
				else if(!filterObj.getAirline().equalsIgnoreCase("all") && filterObj.getStatus().equalsIgnoreCase("all")){
					//filterQuery="select * from flight_order_row where company_id in ("+directCompanyIdsBuffer+") and date(bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' and airline='"+filterObj.getAirline()+"' order by id desc"; 
					filterQuery="from FlightOrderRow fr where fr.companyId in (:directCompanyIds) and date(fr.bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' and fr.airline=:airline order by fr.id desc"; 
					query= session.createQuery(filterQuery);
					query.setParameterList("directCompanyIds",companyIds);
					query.setParameter("airline", filterObj.getAirline());
				}
				else if(filterObj.getAirline().equalsIgnoreCase("all") &&!filterObj.getStatus().equalsIgnoreCase("all")){
					//filterQuery="select * from flight_order_row where company_id in ("+directCompanyIdsBuffer+") and date(bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' and status_action='"+filterObj.getStatus()+"' order by id desc"; 
					filterQuery="from FlightOrderRow fr where fr.companyId in (:directCompanyIds) and date(fr.bookingDate) between '"+filterObj.getYesterDayDate()+"' and '"+filterObj.getTodayDate()+"' and fr.statusAction=:status_action order by fr.id desc"; 
					query= session.createQuery(filterQuery);
					query.setParameterList("directCompanyIds",companyIds);
					query.setParameter("status_action", filterObj.getStatus());
				}

			}
			else{
				if(!filterObj.getAirline().equalsIgnoreCase("all") && !filterObj.getStatus().equalsIgnoreCase("all")){
					//filterQuery="SELECT  * FROM flight_order_row where company_id in ("+directCompanyIdsBuffer+") and airline='"+filterObj.getAirline()+"' and status_action='"+filterObj.getStatus()+"' order by id desc";
					filterQuery="from FlightOrderRow fr where fr.companyId in (:directCompanyIds) and fr.airline=:airline  and fr.statusAction=:status_action order by fr.id desc"; 
					query= session.createQuery(filterQuery);
					query.setParameterList("directCompanyIds",companyIds);
					query.setParameter("airline", filterObj.getAirline());
					query.setParameter("status_action", filterObj.getStatus());
				}
				else if(!filterObj.getAirline().equalsIgnoreCase("all") && filterObj.getStatus().equalsIgnoreCase("all")){
					//filterQuery="SELECT  * FROM flight_order_row where  company_id in ("+directCompanyIdsBuffer+") and airline='"+filterObj.getAirline()+"' order by id desc";
					filterQuery="from FlightOrderRow fr where fr.companyId in (:directCompanyIds) and fr.airline=:airline order by fr.id desc"; 
					query= session.createQuery(filterQuery);
					query.setParameterList("directCompanyIds",companyIds);
					query.setParameter("airline", filterObj.getAirline());
				}
				else if(filterObj.getAirline().equalsIgnoreCase("all") && !filterObj.getStatus().equalsIgnoreCase("all")){
					//filterQuery="SELECT  * FROM flight_order_row where  company_id in ("+directCompanyIdsBuffer+") and status_action='"+filterObj.getStatus()+"' order by id desc";
					filterQuery="from FlightOrderRow fr where fr.companyId in (:directCompanyIds) and fr.statusAction=:status_action order by fr.id desc"; 
					query= session.createQuery(filterQuery);
					query.setParameterList("directCompanyIds",companyIds);
					query.setParameter("status_action", filterObj.getStatus());
				}

				else {
					//filterQuery="select * from flight_order_row where company_id in ("+directCompanyIdsBuffer+") order by id desc";
					filterQuery="from FlightOrderRow fr where fr.companyId in (:directCompanyIds) order by fr.id desc"; 
					query= session.createQuery(filterQuery);
					query.setParameterList("directCompanyIds",companyIds);
				}
			}

			logger.info("----filterQuery------"+filterQuery);
			logger.info("----companyIds------"+companyIds);
			List<FlightOrderRow> list = query.list(); 
			for(FlightOrderRow customerBookings: list){
				ReportData reportData=new ReportData();
				BigDecimal basePrice= customerBookings.getPrice().multiply(customerBookings.getApiToBaseExchangeRate()) ;
				BigDecimal taxes= customerBookings.getTaxes().multiply(customerBookings.getApiToBaseExchangeRate()) ;
				BigDecimal totalBasePrice=basePrice.add(customerBookings.getMarkUp());
				BigDecimal basePriceInBooking=totalBasePrice.multiply(customerBookings.getBaseToBookingExchangeRate());
				BigDecimal taxesInBooking=taxes.multiply(customerBookings.getBaseToBookingExchangeRate());
				BigDecimal bookingInBaseGstMarkups=customerBookings.getGst_on_markup().multiply(customerBookings.getBaseToBookingExchangeRate()).add(customerBookings.getGstOnFlights().multiply(customerBookings.getBaseToBookingExchangeRate()));
				BigDecimal totalPrice=customerBookings.getProcessingFees().add(basePriceInBooking).add(taxesInBooking).add(bookingInBaseGstMarkups);
				//logger.info("totalPrice----in booking--------------------"+totalPrice);
				reportData.setAirline(customerBookings.getAirline());
				reportData.setPnr(customerBookings.getPnr());
				reportData.setCurCode(customerBookings.getBookingCurrency());
				reportData.setBookingDate(DateConversion.getBluestarDateddMMyyyy(customerBookings.getBookingDate()));
				reportData.setDepartureDate(customerBookings.getDepartureDate());
				reportData.setArrivalDate(customerBookings.getArrivalDate());
				reportData.setId(customerBookings.getId());
				reportData.setCompanyId(customerBookings.getCompanyId());
				reportData.setPrice(basePrice.setScale(2,BigDecimal.ROUND_UP));
				reportData.setTax(taxes.setScale(2,BigDecimal.ROUND_UP));
				//reportData.setMarkUp(flightOrderRowMarkup.getMarkUp());
				reportData.setPassengers(customerBookings.getPassengerCount());
				reportData.setProcessingFee(customerBookings.getProcessingFees());
				reportData.setFinalPrice(totalPrice.add(calculateTotalserviceTax(customerBookings)).setScale(2, BigDecimal.ROUND_UP));
				//reportData.setFinalPrice(totalPrice.setScale(2,BigDecimal.ROUND_UP));
				reportData.setEmail(customerBookings.getFlightCustomer().getEmail());
				reportData.setStatus(customerBookings.getStatusAction());
				reportData.setPaymentStatus(customerBookings.getPaymentStatus());
				reportData.setOrderId(customerBookings.getOrderId());
				reportData.setRoute(customerBookings.getOrigin()+"-"+customerBookings.getDestination());
				reportData.setConfigId(customerBookings.getConfigId());
				reportData.setCreatedBy(customerBookings.getCreatedBy());
				reportData.setAgencyUsername(customerBookings.getCreatedBy());
				reportData.setApiComments(customerBookings.getApi_commit());
				reportData.setUserId(customerBookings.getUserId());
				reportData_list.add(reportData);
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}

		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}

		return reportData_list;
	}


	/*this method for get all the  flight customer data based on flight order row id*/
	public  ReportData getFlightOrderCustomerDetail(Long id){
		ReportData reportData=new ReportData();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightOrderCustomer foc where foc.flightOrderRow.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			List<FlightOrderCustomer> list = query.list();
			if(list!=null && list.size()>0){
				reportData.setFlightOrderCustomerList(list);

			}
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		List<FlightOrderCustomerPriceBreakup> flightOrderCustomerPriceBreakup=flightOrderCustomerPriceBreakup(id);
		reportData.setFlightOrderCustomerPriceBreakup(flightOrderCustomerPriceBreakup);
		List<FlightOrderTripDetail> flightOrderTripDetail=getFlightOrderTripDetail(id);
		reportData.setFlightOrderTripDetail(flightOrderTripDetail);
		return reportData; 


	}
	public FlightOrderRow getFlightOrderRowDataById(Long id) {
		// TODO Auto-generated method stub
		FlightOrderRow  orderObj=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightOrderRow for where  for.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			orderObj = (FlightOrderRow) query.uniqueResult();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return orderObj;
	}
	/*this method for get all the  FlightOrderCustomerPriceBreakup data based on flight order row id*/
	public 	 List<FlightOrderCustomerPriceBreakup>  flightOrderCustomerPriceBreakup(Long id){
		List<FlightOrderCustomerPriceBreakup> list=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightOrderCustomerPriceBreakup  fopb where fopb.flightOrderRow.id=:order_row_id";
			Query query = session.createQuery(sql);
			query.setParameter("order_row_id", id);
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
	/*this method for get all the  FlightOrderTripDetail  data based on flight order row id*/
	public	 List<FlightOrderTripDetail>  getFlightOrderTripDetail(Long id){
		List<FlightOrderTripDetail> list=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();

			String sql = "from FlightOrderTripDetail fotd where fotd.flightOrderRow.id=:order_row_id";
			Query query = session.createQuery(sql);
			query.setParameter("order_row_id", id);
			list = query.list();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;

	}

	/*this method for get all the  PaymentTransactionDetail  data based on flight order id*/
	public  ReportData getPaymentTransactionDetail(String orderId){
		ReportData reportData=new ReportData();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from PaymentTransaction pt where pt.api_transaction_id=:apitxid";
			Query query = session.createQuery(sql);
			query.setParameter("apitxid", orderId);
			List<PaymentTransaction> list = query.list();
			reportData.setPaymentTransactionDetail(list);
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return reportData;
	} 

	/*this method for get all the  WalletAmountTxStatementHistory  data based on flight order id*/
	public  ReportData getWalletAmountTxStatementHistory(String orderId){
		ReportData reportData=new ReportData();
		List<WalletAmountTranferHistory>  walletHistoryList=new ArrayList<WalletAmountTranferHistory>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from WalletAmountTranferHistory wt where wt.actionId=:actionid";
			Query query = session.createQuery(sql);
			query.setParameter("actionid", orderId);
			List<WalletAmountTranferHistory> list = query.list();
			for(WalletAmountTranferHistory walletAmountTranferHistory:list){
				walletAmountTranferHistory.setConvertDate(DateConversion.convertDateToStringToDateWithTIME(walletAmountTranferHistory.getCreatedAt()));
				walletHistoryList.add(walletAmountTranferHistory);
			}
			reportData.setWalletAmountTranferHistory(walletHistoryList);
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return reportData;
	} 

	/*this method for get all the  WalletAmountTxStatementHistory  data based on flight order id*/
	public  ReportData getWalletAmountTxStatementHistoryByUserId(String userId,String orderId){
		ReportData reportData=new ReportData();
		List<WalletAmountTranferHistory>  walletHistoryList=new ArrayList<WalletAmountTranferHistory>();

		try{
			session = HibernateUtil.getSessionFactory().openSession();//wt.userId=:userid or 
			String sql = "from WalletAmountTranferHistory wt where wt.actionId=:actionid and wt.userId=:userid ";
			Query query = session.createQuery(sql);
			query.setParameter("userid", Integer.parseInt(userId));
			query.setParameter("actionid",orderId);
			
			List<WalletAmountTranferHistory> list = query.list();
			for(WalletAmountTranferHistory walletAmountTranferHistory:list){
				walletAmountTranferHistory.setConvertDate(DateConversion.convertDateToStringToDateWithTIME(walletAmountTranferHistory.getCreatedAt()));
				walletHistoryList.add(walletAmountTranferHistory);
			}
			reportData.setWalletAmountTranferHistory(walletHistoryList);
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return reportData;
	} 

	/*this method for get all the  FlightOrderRow Confirmed List */
	public  List<FlightOrderRow> getFlightOrderRowConfirmedList(){
		List<FlightOrderRow>  reportData_list=new ArrayList<FlightOrderRow>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();

			String sql = "from FlightOrderRow";
			Query query = session.createQuery(sql);
			List<FlightOrderRow> list = query.list();
			for(FlightOrderRow  customerPriceBreakup:list){
				if(customerPriceBreakup.getStatusAction().equalsIgnoreCase("Confirmed")){
					reportData_list.add(customerPriceBreakup);
				}
			}

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return reportData_list;
	}

	/*this method for get all the  EndUserPaymentTransactions() */
	public  List<PaymentTransaction> getEndUserPaymentTransactions(String orderId) {
		List<PaymentTransaction> list=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from PaymentTransaction pt where pt.api_transaction_id=:apitxid";
			Query query = session.createQuery(sql);
			query.setParameter("apitxid", orderId);
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

	public CreditNote flightCreditNoteData(Long id, int companyId) {
		String sql="from CreditNote cn where cn.rowId=:row_id and cn.companyId=:companyid";
		CreditNote creditNote=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("row_id",id.intValue());
			query.setParameter("companyid",String.valueOf(companyId)) ;
			creditNote=(CreditNote)query.uniqueResult();
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

	public List<CreditNote> loadCreditNoteListByFlightRowId(Long id) {
		// TODO Auto-generated method stub
		String sql="from CreditNote cn where cn.rowId=:row_id";
		List<CreditNote> list=new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("row_id",id.intValue());
			List<CreditNote> creditList=query.list();
			if(creditList!=null &&creditList.size()>0){
				for(CreditNote note:creditList){
					note.setConvertDate(DateConversion.convertTimestampToStringWithoutAM(note.getOrderedAt()));
					note.setManagementFees(note.getManagementFees().setScale(2, BigDecimal.ROUND_UP));
					note.setConvenienceFees(note.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP));
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

	/*	method for find the parent id for child*/
	public List<String>  searchForChildCompanyID(FlightOrderRowCommission order_row_id,Company sessionCompany,Session session){
		String childQuery=null;
		List<String> childlist=new ArrayList<String>();

		List<FlightOrderRowCommission> foclist=null;
		childQuery="from FlightOrderRowCommission forc where forc.CompanyId!=:compid and forc.flightOrderRow.id=:order_row_id";
		logger.info("------------------childQuery------------------------"+childQuery);
		Query query = session.createQuery(childQuery);
		query.setParameter("compid",String.valueOf(sessionCompany.getCompanyid()));
		query.setParameter("order_row_id",order_row_id.getFlightOrderRow().getId());
		foclist=query.list();
		if(foclist!=null && foclist.size()>0){
			for(FlightOrderRowCommission forc:foclist){
				childlist.add(forc.getCompanyId());
			}
		}

		return childlist;
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

	/*this method for get company ids from hotel_orderrow_markup or hotel_order_row__commission table*/
	public  List<FlightOrderCustomerSSR>  getFlightOrderSSR(Long rowId){
		Session session=null;
		String sql = "from FlightOrderCustomerSSR frm where frm.flightOrderRow.id in(:id)";
		List<FlightOrderCustomerSSR> newList= new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			logger.info("----------getFlightOrderSSR for markup----sql-----------------"+sql);
			Query query = session.createQuery(sql);
			query.setParameter("id",rowId);
			List<FlightOrderCustomerSSR>  list=query.list(); 
			if(list!=null && list.size()>0){
				for(int i=0;i<=list.size();i++){
					FlightOrderCustomerSSR flightOrderCustomerSSR =list.get(i);
					FlightOrderCustomer flightOrderCustomer=flightOrderCustomerSSR.getFlightOrderRow().getFlightOrderCustomers().get(i);
					flightOrderCustomerSSR.setPaxName(flightOrderCustomer.getFirstName()+" "+flightOrderCustomer.getLastName());
					newList.add(flightOrderCustomerSSR);
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
		return newList;

	}

	public User  getSalesPersonName(String userId){

		//User userNew =getParentUserId(userId);
		User user = null;
		Session session=null;
		String sql = "from SalesLeadGeneration frm where frm.childUserId in(:childUserId)";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("childUserId", Integer.parseInt(userId));
			SalesLeadGeneration salesLeadGeneration=(SalesLeadGeneration) query.uniqueResult();
			if(salesLeadGeneration!=null && salesLeadGeneration.getUser()!=null){
				user=salesLeadGeneration.getUser();
			}
			else{
				user=new User();
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
		return user;
	}




	public   FlightOrderRowMarkup getCompanyMarkup (String companyId,Long orderRowId) {

		FlightOrderRowMarkup flightOrderRowMarkup= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightOrderRowMarkup hor where hor.CompanyId=:companyid and hor.flightOrderRow.id=:orderRowId";
			Query query = session.createQuery(sql);
			query.setParameter("companyid", companyId);
			query.setParameter("orderRowId", orderRowId);
			flightOrderRowMarkup = (FlightOrderRowMarkup) query.uniqueResult();
			if(flightOrderRowMarkup==null) 
				flightOrderRowMarkup=new FlightOrderRowMarkup();
			else 
			flightOrderRowMarkup.setMarkUp(flightOrderRowMarkup.getMarkUp().setScale(2, BigDecimal.ROUND_UP));

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}

		return flightOrderRowMarkup;

	}
	public FlightOrderRow getFlightOrderRowInfo(long flightOrderRowId) {

		FlightOrderRow flightOrderRow= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightOrderRow hor where hor.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", flightOrderRowId);
			flightOrderRow = (FlightOrderRow) query.uniqueResult();
			flightOrderRow.setFinalPrice(flightOrderRow.getFinalPrice().add(calculateTotalserviceTax(flightOrderRow)).setScale(BigDecimal.ROUND_UP, 2));

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}

		return flightOrderRow;

	}
	/*this method for get  FlightOrderRow  using order id */
	public	FlightOrderRow getFlightOrderRowDetail(String orderid){
		FlightOrderRow flightOrderRow = null;
		Session session=null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(FlightOrderRow.class);
			criteria.add(Restrictions.eq("orderId", orderid));
			flightOrderRow = (FlightOrderRow) criteria.uniqueResult();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  flightOrderRow;
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


	public List<FlightOrderRow> getFlightReportsForGenerateExcel(HotelandFlightDisReportProperty hotelDisReportProperty) {
		//this method is testing  for download excel

		List<FlightOrderRow> flightOrderRowList= null;
		SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	

		try{
			Conjunction conjunction=Restrictions.conjunction();

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(FlightOrderRow.class);
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
			else if(hotelDisReportProperty.getReportType()!=null && hotelDisReportProperty.getReportType().equalsIgnoreCase("0")){
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
					if(hotelDisReportProperty.getReportType()!=null && hotelDisReportProperty.getReportType().equalsIgnoreCase("T")){
						Calendar cal = Calendar.getInstance();
						String formattedDate = targetFormat.format(cal.getTime()); 
						Date today = targetFormat.parse(formattedDate);
						Date tomorrow = new Date(today.getTime() + TimeUnit.DAYS.toMillis(1));
						conjunction.add(Restrictions.ge("createdAt", today));
						conjunction.add(Restrictions.lt("createdAt", tomorrow));
					} 
					if(hotelDisReportProperty.getReportType()!=null &&hotelDisReportProperty.getReportType().equalsIgnoreCase("W")){	
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
					if(hotelDisReportProperty.getReportType()!=null &&hotelDisReportProperty.getReportType().equalsIgnoreCase("M")){
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
			flightOrderRowList =  criteria.list();

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}

		return flightOrderRowList;


	}

	public	FlightOrderRow updateFlightOrderRowDetail(FlightOrderRow flightOrderRow){
		FlightOrderRow newFlightOrderRow = null;
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			newFlightOrderRow=(FlightOrderRow) session.get(FlightOrderRow.class,flightOrderRow.getId());
			newFlightOrderRow.setInvoiceNo(flightOrderRow.getInvoiceNo());
			newFlightOrderRow.setOrderId(flightOrderRow.getOrderId());
			newFlightOrderRow.getFlightCustomer().setOrderId(flightOrderRow.getOrderId());//added by basha
			session.update(newFlightOrderRow);
			tx.commit();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  newFlightOrderRow;
	}
	
	public	FlightOrderRow updateFlightOrderRowDetailPaymentStatus(FlightOrderRow flightOrderRow){
		FlightOrderRow newFlightOrderRow = null;
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			newFlightOrderRow=(FlightOrderRow) session.get(FlightOrderRow.class,flightOrderRow.getId());
			newFlightOrderRow.setPaymentStatus(flightOrderRow.getPaymentStatus());
			newFlightOrderRow.setStatusAction(flightOrderRow.getStatusAction());
			newFlightOrderRow.setPnr(flightOrderRow.getPnr());
			newFlightOrderRow.setInvoiceNo(flightOrderRow.getInvoiceNo());
			session.update(newFlightOrderRow);
			tx.commit();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  newFlightOrderRow;
	}
	

	public static List<FlightOrderRowMarkup> getMarkupDetail(Company company,BigDecimal markUp,FlightOrderRow flightOrderRow) throws Exception  
	{
		logger.info("insertMarkupDetail method called : ");
		markUp=markUp.divide(new BigDecimal(flightOrderRow.getPassengerCount()));
		List<FlightOrderRowMarkup> flightOrderRowMarkups=new ArrayList<FlightOrderRowMarkup>();
		CompanyDAO companyDAO = new CompanyDAO();
		FlightOrderDao flightOrderDao = new FlightOrderDao();
		Company companyParent= companyDAO.getParentCompany(company);
		Set<String> compnyIdset=new HashSet<String>();
		compnyIdset.add(String.valueOf(companyParent.getCompanyid()));
		compnyIdset.add(String.valueOf(company.getCompanyid()));
		for(String companyId:compnyIdset)
		{
			FlightOrderRowMarkup flightOrderRowMarkup=new FlightOrderRowMarkup();
			// setting parent markup
			if(!String.valueOf(company.getCompanyid()).equalsIgnoreCase(companyId))
			{
				flightOrderRowMarkup.setMarkUp(markUp);
			}
			else
			{
				flightOrderRowMarkup.setMarkUp(new BigDecimal(0));
			}
			flightOrderRowMarkup.setCompanyId(companyId);
			flightOrderRowMarkup.setFlightOrderRow(flightOrderRow);
			flightOrderDao.insertMarkupDetails(flightOrderRowMarkup);
			flightOrderRowMarkups.add(flightOrderRowMarkup);
		}
		return flightOrderRowMarkups;
	}

	public static List<FlightOrderRowCommission> getCommissionDetails(Company company,FlightOrderRow flightOrderRow) throws Exception
	{
		List<FlightOrderRowCommission> flightOrderRowCommissions=new ArrayList<FlightOrderRowCommission>();
		logger.info("insertCommission method called normal commission mode: ");
		FlightOrderDao flightOrderDao = new FlightOrderDao();
		CompanyDAO companyDAO = new CompanyDAO();
		Company companyParent= companyDAO.getParentCompany(company);

		Set<String> compnyIdset=new HashSet<String>();
		compnyIdset.add(String.valueOf(companyParent.getCompanyid()));
		compnyIdset.add(String.valueOf(company.getCompanyid()));
		for(String companyId:compnyIdset)
		{
			FlightOrderRowCommission flightOrderRowCommission=new FlightOrderRowCommission();
			flightOrderRowCommission.setCompanyId(companyId);
			flightOrderRowCommission.setFlightOrderRow(flightOrderRow);
			flightOrderRowCommission.setCommission(new BigDecimal(0));
			flightOrderRowCommission.setCommissionType("Fixed");
			flightOrderRowCommission.setRateType("Net");
			flightOrderRowCommission.setSheetMode(false);
			flightOrderRowCommission.setIataCommission(new BigDecimal(0));
			flightOrderRowCommission.setPlbCommission(new BigDecimal(0));
			flightOrderDao.insertCommission(flightOrderRowCommission);
			flightOrderRowCommissions.add(flightOrderRowCommission);
		}
		return flightOrderRowCommissions;
	}

	public void insertCommission(FlightOrderRowCommission flightOrderRowCommission) throws Exception {
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(flightOrderRowCommission);
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

	public void insertMarkupDetails(FlightOrderRowMarkup flightOrderRowMarkup)
			throws Exception {
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(flightOrderRowMarkup);
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
	public static  boolean deductUserWallet(BigDecimal finalPrice, User userNew, UserDAO userDao, String walletRemarks, String orderId,String Invoiceno) 
	{

		BigDecimal totWalletBalance=userNew.getAgentWallet().getWalletbalance().setScale(5, BigDecimal.ROUND_UP).subtract(userNew.getAgentWallet().getWalletBalanceRange().setScale(5, BigDecimal.ROUND_UP));
		if(userNew!=null && userNew.getAgentWallet().getDepositBalance()!=null && finalPrice.compareTo(userNew.getAgentWallet().getDepositBalance()) < 0){
			BigDecimal openingBalance = userNew.getAgentWallet().getDepositBalance();
			BigDecimal newDepositBalance= openingBalance.subtract(finalPrice);
			UserWallet newWalletObj= userDao.updateUserWalletByWalletId(userNew,newDepositBalance);
			if(newWalletObj!=null){
				WalletAmountTranferHistory  tranferHistory=new WalletAmountTranferHistory();
				tranferHistory.setCurrency(newWalletObj.getCurrencyCode());
				tranferHistory.setAmount(finalPrice);
				tranferHistory.setWalletId(newWalletObj.getWalletId());
				tranferHistory.setOpeningBalance(openingBalance);
				tranferHistory.setClosingBalance(newDepositBalance);
				tranferHistory.setUserId(userNew.getId());
				tranferHistory.setParentUserId(0);
				tranferHistory.setActionId(orderId);
				tranferHistory.setTransactionType("Withdrawal");
				tranferHistory.setCreatedAt(new Timestamp(new Date().getTime()));
				tranferHistory.setParentOpeningBalance(new BigDecimal("0"));
				tranferHistory.setRemarks(walletRemarks);
				tranferHistory.setParentClosingBalance(new BigDecimal("0"));
				tranferHistory.setAction(walletRemarks);
				tranferHistory.setInvoiceNo(Invoiceno);
				tranferHistory.setCompanyId(userNew.getCompanyid());
				tranferHistory =userDao.insertWalletAmountTransferHistory(tranferHistory);
				return true;
			}

		}
		else if(finalPrice.compareTo(totWalletBalance) < 0){
			BigDecimal openingBalance = userNew.getAgentWallet().getWalletbalance();
			BigDecimal newWalletBalance= openingBalance.subtract(finalPrice);
			UserWallet userWalletNew = userNew.getAgentWallet();
			userWalletNew.setWalletbalance(newWalletBalance);
			UserWallet newWalletObj= userDao.updateParentWallet(userWalletNew);
			if(newWalletObj!=null){
				WalletAmountTranferHistory  tranferHistory=new WalletAmountTranferHistory();
				tranferHistory.setCurrency(newWalletObj.getCurrencyCode());
				tranferHistory.setAmount(finalPrice);
				tranferHistory.setWalletId(newWalletObj.getWalletId());
				tranferHistory.setOpeningBalance(openingBalance);
				tranferHistory.setClosingBalance(newWalletBalance);
				tranferHistory.setUserId(userNew.getId());
				tranferHistory.setParentUserId(0);
				tranferHistory.setActionId(orderId);
				tranferHistory.setTransactionType("Debit");
				tranferHistory.setCreatedAt(new Timestamp(new Date().getTime()));
				tranferHistory.setParentOpeningBalance(new BigDecimal("0"));
				tranferHistory.setRemarks(walletRemarks);
				tranferHistory.setParentClosingBalance(new BigDecimal("0"));
				tranferHistory.setAction(walletRemarks);
				tranferHistory.setInvoiceNo(Invoiceno);
				tranferHistory.setCompanyId(userNew.getCompanyid());
				tranferHistory =userDao.insertWalletAmountTransferHistory(tranferHistory);
				return true;
			}
		} 
		else{
			return false;

		}
		return false;
	}
	
	public boolean creditUserWalletAmountForBookingFailed(BigDecimal finalPrice, User user, UserDAO userDao, String walletRemarks, String orderId,String Invoiceno) 
	{
		boolean result = false;
		try{
			BigDecimal totWalletBalance = user.getAgentWallet().getWalletbalance().setScale(5, BigDecimal.ROUND_UP).subtract(user.getAgentWallet().getWalletBalanceRange().setScale(5, BigDecimal.ROUND_UP));
			if(user!=null && user.getAgentWallet().getDepositBalance()!=null && AmountRoundingModeUtil.roundingMode(finalPrice).compareTo(user.getAgentWallet().getDepositBalance()) < 0 ){
				BigDecimal openingBalance = user.getAgentWallet().getDepositBalance();
				BigDecimal newDepositBalance= openingBalance.add(finalPrice);
				UserWallet newWalletObj= userDao.updateUserWalletByWalletId(user,newDepositBalance);
				if(newWalletObj!=null){
					WalletAmountTranferHistory  tranferHistory=new WalletAmountTranferHistory();
					tranferHistory.setCurrency(newWalletObj.getCurrencyCode());
					tranferHistory.setAmount(AmountRoundingModeUtil.roundingMode(finalPrice));
					tranferHistory.setWalletId(newWalletObj.getWalletId());
					tranferHistory.setOpeningBalance(openingBalance);
					tranferHistory.setClosingBalance(newDepositBalance);
					tranferHistory.setUserId(user.getId());
					tranferHistory.setParentUserId(0);
					tranferHistory.setActionId(orderId);
					tranferHistory.setTransactionType("Deposit");
					tranferHistory.setCreatedAt(new Timestamp(new Date().getTime()));
					tranferHistory.setParentOpeningBalance(new BigDecimal("0"));
					tranferHistory.setRemarks(walletRemarks);
					tranferHistory.setParentClosingBalance(new BigDecimal("0"));
					tranferHistory.setAction(walletRemarks);
					tranferHistory.setCompanyId(user.getCompanyid());
					tranferHistory =userDao.insertWalletAmountTransferHistory(tranferHistory);
					return true;
				}

			}
			else if(AmountRoundingModeUtil.roundingMode(finalPrice).compareTo(totWalletBalance) < 0){
				BigDecimal openingBalance = user.getAgentWallet().getWalletbalance();
				BigDecimal newWalletBalance= openingBalance.add(finalPrice);
				UserWallet userWalletNew = user.getAgentWallet();
				userWalletNew.setWalletbalance(newWalletBalance);
				UserWallet newWalletObj = userDao.updateParentWallet(userWalletNew);
				if(newWalletObj!=null){
					WalletAmountTranferHistory  tranferHistory=new WalletAmountTranferHistory();
					tranferHistory.setCurrency(newWalletObj.getCurrencyCode());
					tranferHistory.setAmount(AmountRoundingModeUtil.roundingMode(finalPrice));
					tranferHistory.setWalletId(newWalletObj.getWalletId());
					tranferHistory.setOpeningBalance(openingBalance);
					tranferHistory.setClosingBalance(newWalletBalance);
					tranferHistory.setUserId(user.getId());
					tranferHistory.setParentUserId(0);
					tranferHistory.setActionId(orderId);
					tranferHistory.setTransactionType("Credit");
					tranferHistory.setCreatedAt(new Timestamp(new Date().getTime()));
					tranferHistory.setParentOpeningBalance(new BigDecimal("0"));
					tranferHistory.setRemarks(walletRemarks);
					tranferHistory.setParentClosingBalance(new BigDecimal("0"));
					tranferHistory.setAction(walletRemarks);
					tranferHistory.setCompanyId(user.getCompanyid());
					tranferHistory = userDao.insertWalletAmountTransferHistory(tranferHistory);
					return true;
				}
			}
			else{
				return false;

			}


		}catch(Exception e){

		}


		return result;
	}
	
	public boolean checkWalletAmount(int userid, BigDecimal totalprice, BigDecimal Gstonmarkup, BigDecimal Gstonflights) throws Exception {
		Session session=null;
		boolean result = false;
		logger.info("checkWalletAmount method called :");
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("id", userid));
			User user = (User) criteria.uniqueResult();
			BigDecimal totalwithGST = totalprice.add(Gstonmarkup).add(Gstonflights);

			// If User is SuperUser/Agent/Distributor/Corporate Debit Money from Their Own wallet
			if(user != null){
				Criteria parentcriteria = session.createCriteria(Company.class);
				parentcriteria.add(Restrictions.eq("companyid", user.getCompanyid()));
				Company myCompany = (Company) parentcriteria.uniqueResult();
				if(myCompany.getCompanyRole()!=null && myCompany.getCompanyRole().isCorporate() || myCompany.getCompanyRole().isDistributor() || myCompany.getCompanyRole().isAgent())
				{
					Criteria userCriteria = session.createCriteria(User.class);
					userCriteria.add(Restrictions.eq("Email", myCompany.getEmail()));
					user = (User) userCriteria.uniqueResult();
				}
					result = checkUserWalletAmountForBooking(user,totalwithGST);
			}

		} catch (NumberFormatException ne) {
			result = false;
			logger.error("NumberFormatException", ne);

		} catch (Exception e) {
			logger.error("Exception", e);
			result = false;
		} finally {
			session.close();
		}
		return result;
	}
	
	private boolean checkUserWalletAmountForBooking(User user,BigDecimal totalprice){
		boolean result = false;
		try{
			BigDecimal totWalletBalance=user.getAgentWallet().getWalletbalance().setScale(5, BigDecimal.ROUND_UP).subtract(user.getAgentWallet().getWalletBalanceRange().setScale(5, BigDecimal.ROUND_UP));
			if(user!=null && user.getAgentWallet().getDepositBalance()!=null && AmountRoundingModeUtil.roundingMode(totalprice).compareTo(user.getAgentWallet().getDepositBalance()) < 0){
				return true;
			}
			else if(AmountRoundingModeUtil.roundingMode(totalprice).compareTo(totWalletBalance) < 0){
				return true;
			}
			else{
				return false;
			}
		}catch(Exception e){
		}
		return result;
	}
	
	
	public static BigDecimal  calculateTotalserviceTax(FlightOrderRow  flightOrderRow){
		BigDecimal convenienceFeePerRoom = new BigDecimal("0");
		BigDecimal managementFeePerRoom = new BigDecimal("0");
		if(flightOrderRow.getFlightOrderRowServiceTax() !=null && flightOrderRow.getFlightOrderRowServiceTax().getManagementFee()!=null)
			managementFeePerRoom= flightOrderRow.getFlightOrderRowServiceTax().getManagementFee();
		if(flightOrderRow.getFlightOrderRowServiceTax() !=null && flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee()!=null)
			convenienceFeePerRoom= flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee();
		if(flightOrderRow.getFlightOrderRowGstTax() !=null && flightOrderRow.getFlightOrderRowGstTax().getManagementFee()!=null)
			managementFeePerRoom= flightOrderRow.getFlightOrderRowGstTax().getManagementFee();
		if(flightOrderRow.getFlightOrderRowGstTax() !=null && flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee()!=null)
			convenienceFeePerRoom= flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee();
		BigDecimal  serviceTax= flightOrderRow.getServiceTax()!=null?flightOrderRow.getServiceTax().setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);
		BigDecimal  gstTax=flightOrderRow.getGstOnFlights()!=null?flightOrderRow.getGstOnFlights().setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);
		
		BigDecimal  totalAmount=serviceTax.add(managementFeePerRoom).add(convenienceFeePerRoom).add(gstTax);
		return totalAmount.setScale(2,BigDecimal.ROUND_UP);
	}
	public static BigDecimal  calculateTotalGSTTax(FlightOrderRow  flightOrderRow){
		BigDecimal convenienceFeePerRoom = new BigDecimal("0");
		BigDecimal managementFeePerRoom = new BigDecimal("0");
		if(flightOrderRow.getFlightOrderRowGstTax() !=null && flightOrderRow.getFlightOrderRowGstTax().getManagementFee()!=null)
			managementFeePerRoom= flightOrderRow.getFlightOrderRowGstTax().getManagementFee();
		if(flightOrderRow.getFlightOrderRowGstTax() !=null && flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee()!=null)
			convenienceFeePerRoom= flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee();
		BigDecimal  gstTax= flightOrderRow.getFlightOrderRowGstTax().getTotalGst()!=null?flightOrderRow.getFlightOrderRowGstTax().getTotalGst().setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);
		BigDecimal  totalAmount=gstTax.add(managementFeePerRoom).add(convenienceFeePerRoom);
		return totalAmount.setScale(2,BigDecimal.ROUND_UP);
	}

	public List<FlightOrderRow> getFlightBookingForIds(List<String> companyUserId ) {
		List<FlightOrderRow> flightOrderRows=null;
 		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr=session.createCriteria(FlightOrderRow.class);
			cr.add(Restrictions.in("companyId", companyUserId));
			flightOrderRows=cr.list();
			
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
 		return flightOrderRows;
	}
	 
	 
}