package com.isl.excel.flight.dao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightReportPage;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.FlightOrderTripDetail;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.model.Company;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;
public class FlightReportExcelDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(FlightReportExcelDao.class);
	public  List<String> getCompanyIdList(Company company, int reportType)
	{
		List<String> companyIdList = new ArrayList<String>();
		Session session = null;
		try{			
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Company.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			List<Company> list = null;
			logger.info("reportType--Excel--------------------"+reportType);
			switch (reportType){
			case FlightReportFilter.REPORTS_MINE:
				reportConjunction.add(Restrictions.eq("company_userid",company.getCompany_userid()));
				criteria.add(reportConjunction);
				list = criteria.list();
				break;			
		 case FlightReportFilter.REPORTS_ALL:
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				criteria.add(reportConjunction);
				list = criteria.list();
				break;	
			case FlightReportFilter.REPORTS_MY_AFFILIATES:
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				criteria.add(reportConjunction);
				list = criteria.list();
				break;
			case FlightReportFilter.REPORTS_MY_AGENCIES:
				logger.info("reportType---------"+reportType);
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isAgent",true));
				list = criteria.list();
				//reportConjunction.add(Restrictions.eq("companyRole.isAgent",true));
				break;
			case FlightReportFilter.REPORTS_MY_DISTRIBUTORS:
				//For direct distributors...
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isDistributor",true));
				list = criteria.list();
				//reportConjunction.add(Restrictions.eq("companyRole.isDistributor",true));
				break;
			case FlightReportFilter.REPORTS_MY_CORPORATES:
				//For direct distributors...
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isCorporate",true));
				list = criteria.list();
				//reportConjunction.add(Restrictions.eq("companyRole.isDistributor",true));
				break;	
			case FlightReportFilter.REPORTS_ALL_AFFILIATES:
				reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));
				criteria.add(reportConjunction);
				list = criteria.list();
				break;

			case FlightReportFilter.REPORTS_ALL_AGENCIES:
				logger.info("reportType---------"+reportType);
				reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isAgent",true));
				list = criteria.list();
				//reportConjunction.add(Restrictions.eq("companyRole.isAgent",true));
				break;
			case FlightReportFilter.REPORTS_ALL_DISTRIBUTORS:
				//For direct distributors...
				reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isDistributor",true));
				list = criteria.list();
				//reportConjunction.add(Restrictions.eq("companyRole.isDistributor",true));
				break;


			case FlightReportFilter.ORDERS_ALL:
				reportConjunction.add(Restrictions.eq("companyid",company.getCompanyid()));

				criteria.add(reportConjunction);
				list = criteria.list();
				break;		


			default:	
				companyIdList.add(String.valueOf(company.getCompanyid()));
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));

				criteria.add(reportConjunction);
				list = criteria.list();
				break; 
			}				
			criteria.add(reportConjunction);
			//List<Company> list = criteria.list();
			logger.error("--------------probable Company list -----------------"+list);
			if(list!=null && list.size()>0)
			{
				logger.error("--------------probable Company list size-----------------"+list);
				for (Company companyChild :list)
				{
					companyIdList.add(String.valueOf(companyChild.getCompanyid()));
					logger.info("-------------companyIdList-----------------"+companyIdList);
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

	public  FlightReportPage getCompanyFlightReports(FlightReportPage flightReportPage){
		List<ReportData>  reportData_list=new ArrayList<ReportData>();
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(FlightOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();

			if(flightReportPage!=null && flightReportPage.isFilterEnabled())
			{
				FlightReportFilter flightReportFilter = flightReportPage.getFlightReportFilter();
				List<String> companyIdList  =  null;
				companyIdList = getCompanyIdList(flightReportFilter.getLoginCompany(), flightReportFilter.getReportType());
				logger.info("companyIdList--------------"+companyIdList.size());
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
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
				criteria.add(reportConjunction);
				criteria.addOrder(Order.desc("id"));
			}
			 
			List<FlightOrderRow> list = criteria.list();
			
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
					reportData.setBookingDate(DateConversion.getBluestarDate(flightOrderRow.getBookingDate()));
					reportData.setDepartureDate(DateConversion.getBluestarDate(flightOrderRow.getDepartureDate()));
					reportData.setArrivalDate(DateConversion.getBluestarDate(flightOrderRow.getArrivalDate()));
					reportData.setId(flightOrderRow.getId());
					reportData.setCompanyId(flightOrderRow.getCompanyId());
					reportData.setPrice(basePrice.setScale(2,BigDecimal.ROUND_UP));
					reportData.setTax(taxes.setScale(2,BigDecimal.ROUND_UP));
					//reportData.setMarkUp(flightOrderRowMarkup.getMarkUp());
					reportData.setPassengers(flightOrderRow.getPassengerCount());
					reportData.setProcessingFee(flightOrderRow.getProcessingFees());
					reportData.setFinalPrice(totalPrice.setScale(2,BigDecimal.ROUND_UP));
					reportData.setEmail(flightOrderRow.getFlightCustomer().getEmail());
					reportData.setStatus(flightOrderRow.getStatusAction());
					reportData.setPaymentStatus(flightOrderRow.getPaymentStatus());
					reportData.setOrderId(flightOrderRow.getOrderId());
					reportData.setTransactionKey(flightOrderRow.getTransaction_key());
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
					reportData.setConfigId(flightOrderRow.getConfigId());
					reportData.setCreatedBy(flightOrderRow.getCreatedBy());
					reportData.setAgencyUsername(flightOrderRow.getCreatedBy());
					reportData.setApiComments(flightOrderRow.getApi_commit());
					reportData.setApiProvider(flightOrderRow.getProviderAPI());
					reportData.setUserId(flightOrderRow.getUserId());
					reportData.setFirstName(flightOrderRow.getCustomer().getFirstName());
					reportData.setLastName(flightOrderRow.getCustomer().getLastName());
					reportData.setOrderCustomerId(flightOrderRow.getCustomer().getId());
					reportData_list.add(reportData);
				 }	
				logger.info("+reportData_list.size------------------"+reportData_list.size());
				flightReportPage.setItems(reportData_list);
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












}
