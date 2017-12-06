package com.common.dsr;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

public class AdvancedFlightReportDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AdvancedFlightReportDao.class);
	CommonDsrReportDao commonDsrReportDao=new CommonDsrReportDao();
	public  CommonDsrUtility getCommonDsrAdvanceFlightReports(CommonDsrPage commonDsrPage){
		Session session = null;
		List<FlightOrderRow> list=null;
		CommonDsrUtility commonDsrUtility=new CommonDsrUtility();
		String taxType = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(FlightOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction reportDisjunction = Restrictions.disjunction();
			if(commonDsrPage!=null) 
			{
				CommonDsrFilters commonDsrFilters = commonDsrPage.getCommonDsrFilters();
				List<String>  companyIdList =null;

				if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") ||commonDsrFilters.getTravelType().equalsIgnoreCase("F") &&  commonDsrFilters.getTravelReportType().equalsIgnoreCase("plannedAirTripReport")  && commonDsrFilters.getCompanyUserId()!=null && !commonDsrFilters.getCompanyUserId().equals("")){
					Company selectedCompany=new Company();
					if(commonDsrFilters.getCompanyUserId().equals("")) 
						companyIdList = commonDsrReportDao.getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
					else{
						selectedCompany.setCompany_userid(commonDsrFilters.getCompanyUserId());
						companyIdList = commonDsrReportDao.getCompanyIdList(selectedCompany,1);
					}

				}
				else if(commonDsrFilters.getTravelType().equalsIgnoreCase("All")||commonDsrFilters.getTravelType().equalsIgnoreCase("F")&& ( commonDsrFilters.getTravelReportType().equalsIgnoreCase("plannedAirTripReport"))){
					companyIdList = commonDsrReportDao.getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
				}
				if(commonDsrFilters.getBookingStatus()!=null && !commonDsrFilters.getBookingStatus().equalsIgnoreCase("All")){
					if(commonDsrFilters.getBookingStatus().equalsIgnoreCase("Confirmed")) 
						reportDisjunction.add(Restrictions.eq("statusAction","Cancelled"));
					reportDisjunction.add(Restrictions.eq("statusAction",commonDsrFilters.getBookingStatus()));
				}
				if(commonDsrFilters.getBookingMode()!=null && !commonDsrFilters.getBookingMode().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("bookingMode",commonDsrFilters.getBookingMode()));
				}
				if(!commonDsrFilters.getBookingDateForAdvancePurchase().equals("") && !commonDsrFilters.getTravelDateForAdvancePurchase().equals("")){
					Criterion bookdate = Restrictions.ge("bookingDate", DateConversion.convertDDMMYYtoYYMMDD(commonDsrFilters.getBookingDateForAdvancePurchase()));
					Criterion departDate = Restrictions.le("departureDate",DateConversion.convertDDMMYYtoYYMMDD(commonDsrFilters.getTravelDateForAdvancePurchase()));
					LogicalExpression and = Restrictions.and(bookdate, departDate);
					reportConjunction.add(and);


				}

				if(!commonDsrFilters.getBookingDate().equals("")){
					reportConjunction.add(Restrictions.eq("bookingDate",DateConversion.convertDDMMYYtoYYMMDD(commonDsrFilters.getBookingDate())));
				}
				if(!commonDsrFilters.getTravelDate().equals("")){
					reportConjunction.add(Restrictions.eq("departureDate",DateConversion.convertDDMMYYtoYYMMDD(commonDsrFilters.getTravelDate())));
				}
				if(commonDsrFilters.getPnr()!=null &&  !commonDsrFilters.getPnr().equals("")){
					reportConjunction.add(Restrictions.eq("pnr",commonDsrFilters.getPnr()));
				}
				if(commonDsrFilters.getAirline()!=null &&  !commonDsrFilters.getAirline().equals("ALL")){
					reportConjunction.add(Restrictions.eq("airline",commonDsrFilters.getAirline()));
				}
				if((commonDsrFilters.getOrigin()!=null && !commonDsrFilters.getOrigin().equals(""))  &&  (commonDsrFilters.getDestination()!=null && !commonDsrFilters.getDestination().equals("")))
				{
					reportConjunction.add(Restrictions.eq("origin",commonDsrFilters.getOrigin()));
					reportConjunction.add(Restrictions.eq("destination",commonDsrFilters.getDestination()));

				} 
				if(!commonDsrFilters.getBookingDateForAdvancePurchase().equals("") && !commonDsrFilters.getTravelDateForAdvancePurchase().equals("")){
				}
				else{

					if(!commonDsrFilters.getFromDate().equals("") && !commonDsrFilters.getToDate().equals("")  )
					{
						String gstDateStart="2017-06-30";
						//2016-06-28
						SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
						DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
						Date gstStartDateObj = targetFormat.parse(gstDateStart);
						Date fromDate=null;
						Date toDate=null;
						try{
							fromDate = originalFormat.parse(commonDsrFilters.getFromDate());
							String formattedFromDate = targetFormat.format(fromDate); 
							fromDate = targetFormat.parse(formattedFromDate);
							//fromDate = new Date(fromDate.getTime());
							toDate = originalFormat.parse(commonDsrFilters.getToDate());
							String formattedToDate = targetFormat.format(toDate); 
							toDate = targetFormat.parse(formattedToDate);
							//toDate = new Date(toDate.getTime());
							if((fromDate.compareTo(gstStartDateObj)>0) && (toDate.compareTo(gstStartDateObj)>0))
							{
								fromDate = new Date(fromDate.getTime() + TimeUnit.SECONDS.toMillis(1));
								toDate = new Date(toDate.getTime() + TimeUnit.DAYS.toMillis(1));
								reportConjunction.add(Restrictions.ge("createdAt", fromDate));
								reportConjunction.add(Restrictions.lt("createdAt", toDate));
								taxType="GST";

							} 
							else if((fromDate.compareTo(gstStartDateObj)<=0) && (toDate.compareTo(gstStartDateObj)<=0)){
								fromDate = new Date(fromDate.getTime() + TimeUnit.SECONDS.toMillis(1));
								toDate = new Date(toDate.getTime() + TimeUnit.DAYS.toMillis(1));
								reportConjunction.add(Restrictions.ge("createdAt", fromDate));
								reportConjunction.add(Restrictions.lt("createdAt", toDate));
								taxType="Service";

							}

						}catch(Exception ex)
						{
							logger.info("##########date min format exception---"+ex.getMessage());

						}

					}else{
						String gstDateStart="2017-06-30";
						SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
						Date gstStartDateObj = targetFormat.parse(gstDateStart);
						Date todayDate=new Date();
						if(todayDate.compareTo(gstStartDateObj)>0){
							todayDate = new Date(todayDate.getTime() + TimeUnit.DAYS.toMillis(1));
							gstStartDateObj=new Date(gstStartDateObj.getTime() + TimeUnit.SECONDS.toMillis(1));
							reportConjunction.add(Restrictions.gt("createdAt", gstStartDateObj));
							reportConjunction.add(Restrictions.lt("createdAt",todayDate));
							taxType="GST";
						}
					}

				}

				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				criteria.add(reportConjunction);
				criteria.add(reportDisjunction);
				list=criteria.list();
				commonDsrUtility.setFlightOrderRowList(list);
				commonDsrUtility.setTaxType(taxType);
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
		return commonDsrUtility;
	}

}
