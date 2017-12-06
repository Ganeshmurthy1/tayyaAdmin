package com.common.dsr;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.admin.miscellaneous.dao.MiscellaneousOrderDao;
import com.admin.miscellaneous.model.MiscellaneousCreditNote;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.common.dsr.helper.MergeOrderRowsUtility;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.Page;
import com.lintas.action.CreditNote.modal.BusCreditNote;
import com.lintas.action.CreditNote.modal.CarCreditNote;
import com.lintas.action.CreditNote.modal.InsuranceCreditNote;
import com.lintas.action.CreditNote.modal.TrainCreditNote;
import com.lintas.action.CreditNote.modal.VisaCreditNote;
import com.lintas.admin.DAO.BusOrderDao;
import com.lintas.admin.DAO.CarOrderDao;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.DAO.InsuranceOrderDao;
import com.lintas.admin.DAO.TrainOrderDao;
import com.lintas.admin.DAO.VisaOrderDao;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.common.model.CreditNote;
import com.lintas.admin.common.model.HotelCreditNote;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.FlightOrderTripDetail;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.FlightOrderRowMarkup;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

public class CommonDsrReportDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CommonDsrReportDao.class);
	MergeOrderRowsUtility  mergeOrderRowsUtility=new MergeOrderRowsUtility();
	public  CommonDsrPage getCompanyFlightReports(CommonDsrPage flightReportPage){
		List<ReportData>  reportData_list=new ArrayList<ReportData>();
		int availablePages = 0;
		int availableItems = 0;
		Session session = null;
		try{
			//2016-06-28
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(FlightOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			// To get total row count.
			if(flightReportPage!=null && flightReportPage.isFilterEnabled())
			{
				CommonDsrFilters flightReportFilter = flightReportPage.getCommonDsrFilters();
				logger.info("####ReportType-------------"+flightReportFilter.getReportType());
				logger.info("####getAirlineName-------------"+flightReportFilter.getTravelReportType());
				/* Add multiple condition separated by AND clause within brackets. */
				List<String> companyIdList  = new ArrayList<String>();
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
				logger.info("companyIdList--------------"+companyIdList);

				/*if(flightReportFilter.getTravelReportType().equals("Airline") )
				{
					 prjectionList.add(Projections.property("airline"));
				}
				 */
				//criteria.setProjection(prjectionList);
				criteria.add(reportConjunction);
				//criteria.createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);	

			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);

			List<FlightOrderRow> list =null;
			if(rowCount>0)
			{
				if(flightReportPage.getMaxItems()==Page.ALL_ITEMS){
					logger.info(":::: retriving all items for excel export------");

					criteria = session.createCriteria(FlightOrderRow.class);
					//criteria.setProjection(prjectionList);
					criteria.add(reportConjunction);
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
					logger.info("setFirstResult-------"+itemIndex);
					if(itemIndex <= rowCount)
					{
						logger.info("setFirstResult-------"+itemIndex);
						logger.info("MaxResults-------"+flightReportPage.getMaxItems());
						criteria = session.createCriteria(FlightOrderRow.class);
						//criteria.setProjection(prjectionList);
						criteria.add(reportConjunction);
						//criteria.createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);	
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(flightReportPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
						logger.info("Reports size------"+list.size());	

					}
				}
				if(list!=null && list.size()>0)
				{
					for (FlightOrderRow flightOrderRow :list)
					{
						ReportData reportData=new ReportData();

						if(flightReportPage.getHotelandFlightDisReportProperty().isGuestName()){
							reportData.setGuestName(flightOrderRow.getFlightCustomer().getFirstName()+" "+flightOrderRow.getFlightCustomer().getLastName());
						}
						if(flightReportPage.getHotelandFlightDisReportProperty().isPnr()){
							reportData.setPnr(flightOrderRow.getPnr());
						}
						if(flightReportPage.getHotelandFlightDisReportProperty().isOrderRef()){
							reportData.setOrderId(flightOrderRow.getOrderId());
						}
						if(flightReportPage.getHotelandFlightDisReportProperty().isAirline()){
							reportData.setAirline(flightOrderRow.getAirline());
						}
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
						reportData.setBookingDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getBookingDate()));
						if(flightReportPage.getHotelandFlightDisReportProperty().isFinalPrice()){
							reportData.setFinalPrice(flightOrderRow.getFinalPrice().setScale(2,BigDecimal.ROUND_UP));
							FlightOrderRowMarkup flightOrderRowMarkup=  getCompanyMarkup(flightOrderRow.getCompanyId(), flightOrderRow.getId());
							if(flightOrderRowMarkup!=null) 
								reportData.setMarkUp(flightOrderRowMarkup.getMarkUp().multiply(new BigDecimal(flightOrderRow.getPassengerCount())).setScale(0, BigDecimal.ROUND_HALF_UP));
							else 
								reportData.setMarkUp(new BigDecimal("0"));	
							BigDecimal netPaybleAmount=flightOrderRow.getFinalPrice().subtract(reportData.getMarkUp());
							reportData.setNetAmnt(netPaybleAmount.setScale(2,BigDecimal.ROUND_UP));

						}

						if(flightReportPage.getHotelandFlightDisReportProperty().isInvoiceNo())
							reportData.setInvoiceNo(flightOrderRow.getInvoiceNo());

						if(flightReportPage.getHotelandFlightDisReportProperty().isStatusAction())
							reportData.setStatusAction(flightOrderRow.getStatusAction());

						if(flightReportPage.getHotelandFlightDisReportProperty().isPaymentStatus())
							reportData.setPaymentStatus(flightOrderRow.getPaymentStatus());

						if(flightReportPage.getHotelandFlightDisReportProperty().isAgency()){
							if(flightOrderRow.getCreatedBy()!=null){
								reportData.setCreatedBy(flightOrderRow.getCreatedBy().replace("+", " "));	
							}
							else{
								reportData.setCreatedBy(flightOrderRow.getCreatedBy());	
							}

						} 
						reportData.setDepartureDate(DateConversion.getBluestarDate(flightOrderRow.getDepartureDate()));
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
		return flightReportPage;
	}

	public  CommonDsrUtility getCommonDsrFlightReports(CommonDsrPage commonDsrPage){
		Session session = null;
		List<FlightOrderRow> list=new ArrayList<>();
		List<FlightOrderRow> flightOrderListNew=new ArrayList<>();
		CommonDsrUtility commonDsrUtility=new CommonDsrUtility();
		FlightOrderDao FlightOrderDao=new FlightOrderDao();
		List<CreditNote> listForCreditNote=null;
		String taxType = null;

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(FlightOrderRow.class);
			Criteria criteriaForCreditNote = session.createCriteria(CreditNote.class);//added by  basha
			Conjunction reportConjunctionForCreditNote = Restrictions.conjunction();//added by  basha
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction reportDisjunction = Restrictions.disjunction();
			if(commonDsrPage!=null) 
			{
				CommonDsrFilters commonDsrFilters = commonDsrPage.getCommonDsrFilters();
				List<String>  companyIdList =null;

				if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") ||commonDsrFilters.getTravelType().equalsIgnoreCase("F") && (commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("CustomDsrReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("AirSalesReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("plannedAirTripReport") ||  commonDsrFilters.getTravelReportType().equalsIgnoreCase("AirMissedSavingReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("AirAdvencedPurchaseSalesReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("AirRouteWiseSalesReport")) && commonDsrFilters.getCompanyUserId()!=null && !commonDsrFilters.getCompanyUserId().equals("")){
					Company selectedCompany=new Company();
					if(commonDsrFilters.getCompanyUserId().equals("")) 
						companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
					else{
						selectedCompany.setCompany_userid(commonDsrFilters.getCompanyUserId());
						companyIdList = getCompanyIdList(selectedCompany,1);
					}

				}
				else if(commonDsrFilters.getTravelType().equalsIgnoreCase("All")||commonDsrFilters.getTravelType().equalsIgnoreCase("F")&& (commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("CustomDsrReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("AirSalesReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("plannedAirTripReport") ||  commonDsrFilters.getTravelReportType().equalsIgnoreCase("AirMissedSavingReport") ||  commonDsrFilters.getTravelReportType().equalsIgnoreCase("AirAdvencedPurchaseSalesReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("AirRouteWiseSalesReport"))){
					companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
				}
				if(commonDsrFilters.getBookingStatus()!=null && !commonDsrFilters.getBookingStatus().equalsIgnoreCase("All")){
					if(commonDsrFilters.getBookingStatus().equalsIgnoreCase("Confirmed")){
						if(commonDsrFilters.getTravelReportType().equals("plannedAirTripReport")){

						}
						else
							reportDisjunction.add(Restrictions.eq("statusAction","Cancelled"));
						reportDisjunction.add(Restrictions.eq("statusAction",commonDsrFilters.getBookingStatus()));
					}
					else {
						reportConjunction.add(Restrictions.eq("statusAction",commonDsrFilters.getBookingStatus()));
					}
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

				if(!commonDsrFilters.getAirPlannedTripFromTravelDate().equals("") && !commonDsrFilters.getAirPlannedTripToTravelDate().equals("")){
					Criterion cr=Restrictions.between("departureDate", DateConversion.convertDDMMYYtoYYMMDD(commonDsrFilters.getAirPlannedTripFromTravelDate()), 
							DateConversion.convertDDMMYYtoYYMMDD(commonDsrFilters.getAirPlannedTripToTravelDate()));
					criteria.add(cr);

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
				if((commonDsrFilters.getOrigin()!=null && !commonDsrFilters.getOrigin().equals(""))  &&  (commonDsrFilters.getDestination()==null || commonDsrFilters.getDestination().equals("")))
				{
					reportConjunction.add(Restrictions.eq("origin",commonDsrFilters.getOrigin()));

				} 
				if((commonDsrFilters.getDestination()!=null && !commonDsrFilters.getDestination().equals("") && (commonDsrFilters.getOrigin()==null || commonDsrFilters.getOrigin().equals(""))))
				{
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
							toDate = originalFormat.parse(commonDsrFilters.getToDate());
							String formattedToDate = targetFormat.format(toDate); 
							toDate = targetFormat.parse(formattedToDate);
							if((fromDate.compareTo(gstStartDateObj)>0) && (toDate.compareTo(gstStartDateObj)>0))
							{
								fromDate = new Date(fromDate.getTime() + TimeUnit.SECONDS.toMillis(1));
								toDate = new Date(toDate.getTime() + TimeUnit.DAYS.toMillis(1));
								reportConjunction.add(Restrictions.ge("createdAt", fromDate));
								reportConjunction.add(Restrictions.lt("createdAt", toDate));
								reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", fromDate));//added by  basha
								reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", toDate));//added by  basha
								taxType="GST";

							} 
							else if((fromDate.compareTo(gstStartDateObj)<=0) && (toDate.compareTo(gstStartDateObj)<=0)){
								fromDate = new Date(fromDate.getTime() + TimeUnit.SECONDS.toMillis(1));
								toDate = new Date(toDate.getTime() + TimeUnit.DAYS.toMillis(1));
								reportConjunction.add(Restrictions.ge("createdAt", fromDate));
								reportConjunction.add(Restrictions.lt("createdAt", toDate));
								reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", fromDate));//added by  basha
								reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", toDate));//added by  basha
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
							reportConjunctionForCreditNote.add(Restrictions.gt("issuedAt", gstStartDateObj));//added by  basha
							reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt",todayDate));//added by  basha
							taxType="GST";
						}
					}

				}


				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				criteria.add(reportConjunction);
				criteria.add(reportDisjunction);
				list=criteria.list();
				reportConjunctionForCreditNote.add(Restrictions.in("companyId",companyIdList));//added by  basha
				reportConjunctionForCreditNote.add(Restrictions.eq("isCreditnoteIssued",true));//added by  basha
				criteriaForCreditNote.add(reportConjunctionForCreditNote);//added by  basha
				listForCreditNote=criteriaForCreditNote.list();
				if(listForCreditNote!=null &&  listForCreditNote.size()>0){
					for(CreditNote creditNoteIterate:listForCreditNote){//added by  basha
						FlightOrderRow flightOrderRowdata =FlightOrderDao.getFlightOrderRowDataById(Long.parseLong(String.valueOf(creditNoteIterate.getRowId())));//added by  basha
						flightOrderListNew.add(flightOrderRowdata);
					}
				}
				commonDsrUtility.setFlightOrderRowList(list);
				commonDsrUtility.setFlightOrderRowCreditList(mergeOrderRowsUtility.groupFlightRecords(flightOrderListNew));
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

	public 	CommonDsrUtility  getCommonDsrHotelReports(CommonDsrPage commonDsrPage){
		Session session = null;
		List<HotelOrderRow> list=null;
		CommonDsrUtility commonDsrUtility=new CommonDsrUtility();
		List<HotelOrderRow> hotelOrderListNew=new ArrayList<>();
		HotelOrderDao hotelOrderDao=new HotelOrderDao();//added by  basha
		List<HotelCreditNote> listForCreditNote=null;//added by  basha
		String taxType=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(HotelOrderRow.class);
			Criteria criteriaForCreditNote = session.createCriteria(HotelCreditNote.class);//added by  basha
			Conjunction reportConjunctionForCreditNote = Restrictions.conjunction();//added by  basha
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction reportDisjunction = Restrictions.disjunction();
			if(commonDsrPage!=null) 
			{
				CommonDsrFilters commonDsrFilters = commonDsrPage.getCommonDsrFilters();
				List<String>  companyIdList =null;
				if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") || commonDsrFilters.getTravelType().equalsIgnoreCase("H") && (commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("CustomDsrReport") ||commonDsrFilters.getTravelReportType().equalsIgnoreCase("HotelSalesReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("Advancepurchasehotelreport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("HotelcitywiseSalesReport"))  && commonDsrFilters.getCompanyUserId()!=null && !commonDsrFilters.getCompanyUserId().equals("")){
					Company selectedCompany=new Company();
					if(commonDsrFilters.getCompanyUserId().equals("")) 
						companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
					else{
						selectedCompany.setCompany_userid(commonDsrFilters.getCompanyUserId());
						companyIdList = getCompanyIdList(selectedCompany,1);
					}

				}
				else if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") || commonDsrFilters.getTravelType().equalsIgnoreCase("H")  && (commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("CustomDsrReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("HotelSalesReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("Advancepurchasehotelreport")|| commonDsrFilters.getTravelReportType().equalsIgnoreCase("HotelcitywiseSalesReport"))){
					companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
				}
				if(commonDsrFilters.getBookingMode()!=null && !commonDsrFilters.getBookingMode().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("bookingMode",commonDsrFilters.getBookingMode()));
				}
				if(!commonDsrFilters.getBookingDate().equals("")){
					reportConjunction.add(Restrictions.eq("bookingDate",DateConversion.convertDDMMYYtoYYMMDD(commonDsrFilters.getBookingDate())));
				}
				if(!commonDsrFilters.getTravelDate().equals("")){
					reportConjunction.add(Restrictions.eq("checkInDate",DateConversion.StringToDate(commonDsrFilters.getTravelDate())));
				}
				if(commonDsrFilters.getBookingStatus()!=null && !commonDsrFilters.getBookingStatus().equalsIgnoreCase("All")){
					if(commonDsrFilters.getBookingStatus().equalsIgnoreCase("Confirmed")) 
						reportDisjunction.add(Restrictions.eq("statusAction","Cancelled"));
					if(commonDsrFilters.getBookingStatus().equalsIgnoreCase("Failed")){
						reportDisjunction.add(Restrictions.eq("statusAction","Booking Failed"));
						reportDisjunction.add(Restrictions.eq("statusAction","Failed"));
					}

					reportDisjunction.add(Restrictions.eq("statusAction",commonDsrFilters.getBookingStatus()));
				}
				if(!commonDsrFilters.getHotelbookingDateForAdvancePurchase().equals("") && !commonDsrFilters.getHoteltravelDateForAdvancePurchase().equals("")){
					Criterion bookdate = Restrictions.ge("bookingDate", DateConversion.convertDDMMYYtoYYMMDD(commonDsrFilters.getHotelbookingDateForAdvancePurchase()));
					Criterion checkInDate = Restrictions.le("checkInDate",DateConversion.StringToDate(commonDsrFilters.getHoteltravelDateForAdvancePurchase()));
					LogicalExpression and = Restrictions.and(bookdate, checkInDate);
					reportConjunction.add(and);
				}
				if(!commonDsrFilters.getOrderReference().equals("")){
					reportConjunction.add(Restrictions.eq("orderReference",commonDsrFilters.getOrderReference()));
				}
				if(commonDsrFilters.getCountry()!=null && !commonDsrFilters.getCountry().equalsIgnoreCase("ALL") ){
					criteria.createCriteria("hotelOrderHotelData").add(Restrictions.eq("country",commonDsrFilters.getCountry()));
				}
				if(!commonDsrFilters.getCity().equals("")){
					criteria.createCriteria("hotelOrderHotelData").add(Restrictions.eq("city",commonDsrFilters.getCity()));
				}
				if(!commonDsrFilters.getHotelbookingDateForAdvancePurchase().equals("") && !commonDsrFilters.getHoteltravelDateForAdvancePurchase().equals("")){

				}
				else{
					if(!commonDsrFilters.getFromDate().equals("") && !commonDsrFilters.getToDate().equals(""))
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
							toDate = originalFormat.parse(commonDsrFilters.getToDate());
							String formattedToDate = targetFormat.format(toDate); 
							toDate = targetFormat.parse(formattedToDate);
							if((fromDate.compareTo(gstStartDateObj)>0) && (toDate.compareTo(gstStartDateObj)>0))
							{
								fromDate = new Date(fromDate.getTime() + TimeUnit.SECONDS.toMillis(1));
								toDate = new Date(toDate.getTime() + TimeUnit.DAYS.toMillis(1));
								reportConjunction.add(Restrictions.ge("createdAt", fromDate));
								reportConjunction.add(Restrictions.lt("createdAt", toDate));
								reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", fromDate));//added by  basha
								reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", toDate));//added by  basha
								taxType="GST";
							} 
							else if((fromDate.compareTo(gstStartDateObj)<=0) && (toDate.compareTo(gstStartDateObj)<=0)){
								fromDate = new Date(fromDate.getTime() + TimeUnit.SECONDS.toMillis(1));
								toDate = new Date(toDate.getTime() + TimeUnit.DAYS.toMillis(1));
								reportConjunction.add(Restrictions.ge("createdAt", fromDate));
								reportConjunction.add(Restrictions.lt("createdAt", toDate));
								reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", fromDate));//added by  basha
								reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", toDate));//added by  basha
								taxType="Service";
							}

						}catch(Exception ex)
						{
							logger.info("##########date min format exception---"+ex.getMessage());

						}

					}
					else{
						String gstDateStart="2017-06-30";
						SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
						Date gstStartDateObj = targetFormat.parse(gstDateStart);
						Date todayDate=new Date();
						if(todayDate.compareTo(gstStartDateObj)>0){
							todayDate = new Date(todayDate.getTime()+ TimeUnit.DAYS.toMillis(1));
							gstStartDateObj=new Date(gstStartDateObj.getTime()+ TimeUnit.SECONDS.toMillis(1));
							reportConjunction.add(Restrictions.gt("createdAt", gstStartDateObj));
							reportConjunction.add(Restrictions.lt("createdAt",todayDate));
							reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", gstStartDateObj));//added by  basha
							reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", todayDate));//added by  basha
							taxType="GST";
						}

					}

				}		
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				criteria.add(reportConjunction);
				criteria.add(reportDisjunction);
				list=criteria.list();
				reportConjunctionForCreditNote.add(Restrictions.in("companyId",companyIdList));//added by  basha
				reportConjunctionForCreditNote.add(Restrictions.eq("isCreditnoteIssued",true));//added by  basha
				criteriaForCreditNote.add(reportConjunctionForCreditNote);//added by  basha
				listForCreditNote=criteriaForCreditNote.list();
				if(listForCreditNote!=null &&  listForCreditNote.size()>0){
					for(HotelCreditNote creditNoteIterate:listForCreditNote){//added by  basha
						HotelOrderRow hotelOrderRowdata =hotelOrderDao.getHotelOrderRowInfo(Long.parseLong(String.valueOf(creditNoteIterate.getRowId())));//added by  basha
						hotelOrderListNew.add(hotelOrderRowdata);
					}
				}
				commonDsrUtility.setHotelOrderRowList(list);
				commonDsrUtility.setHotelOrderRowCreditList(mergeOrderRowsUtility.groupRecordsForHotel(hotelOrderListNew));
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
	public CommonDsrUtility getCommonDsrCarReports(CommonDsrPage commonDsrPage){
		Session session = null;
		List<CarOrderRow> list=null;
		CommonDsrUtility commonDsrUtility=new CommonDsrUtility();
		CarOrderDao carOrderDao=new CarOrderDao();
		List<CarOrderRow> carOrderListNew=new ArrayList<>();
		List<CarCreditNote> listForCreditNote=null;
		String taxType = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(CarOrderRow.class);
			Criteria criteriaForCreditNote = session.createCriteria(CarCreditNote.class);//added by  basha
			Conjunction reportConjunctionForCreditNote = Restrictions.conjunction();//added by  basha
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction reportDisjunction = Restrictions.disjunction();
			if(commonDsrPage!=null) 
			{
				CommonDsrFilters commonDsrFilters = commonDsrPage.getCommonDsrFilters();
				List<String>  companyIdList =null;
				if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") || commonDsrFilters.getTravelType().equalsIgnoreCase("C")  && (commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("CustomDsrReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("CarSalesReport") )      && commonDsrFilters.getCompanyUserId()!=null && !commonDsrFilters.getCompanyUserId().equals("")){
					Company selectedCompany=new Company();
					if(commonDsrFilters.getCompanyUserId().equals("")) 
						companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
					else{
						selectedCompany.setCompany_userid(commonDsrFilters.getCompanyUserId());
						companyIdList = getCompanyIdList(selectedCompany,1);
					}
				}
				else if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") || commonDsrFilters.getTravelType().equalsIgnoreCase("C")  && commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("CustomDsrReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("CarSalesReport")){
					companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
				}
				if(commonDsrFilters.getBookingMode()!=null && !commonDsrFilters.getBookingMode().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("bookingMode",commonDsrFilters.getBookingMode()));
				}
				if(!commonDsrFilters.getBookingDate().equals("")){
					reportConjunction.add(Restrictions.eq("carBookingDate",commonDsrFilters.getBookingDate()));
				}
				if(!commonDsrFilters.getTravelDate().equals("")){
					reportConjunction.add(Restrictions.eq("travelDate",DateConversion.StringToDate(commonDsrFilters.getTravelDate())));
				}
				if(commonDsrFilters.getBookingStatus()!=null && !commonDsrFilters.getBookingStatus().equalsIgnoreCase("All")){
					if(commonDsrFilters.getBookingStatus().equalsIgnoreCase("Confirmed")) 
						reportDisjunction.add(Restrictions.eq("statusAction","Cancelled"));
					reportDisjunction.add(Restrictions.eq("statusAction",commonDsrFilters.getBookingStatus()));
				}

				if(!commonDsrFilters.getConfirmationNumber().equals("")){
					reportConjunction.add(Restrictions.eq("confirmationNumber",commonDsrFilters.getConfirmationNumber()));
				}

				if(!commonDsrFilters.getFromDate().equals("") && !commonDsrFilters.getToDate().equals(""))
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
						toDate = originalFormat.parse(commonDsrFilters.getToDate());
						String formattedToDate = targetFormat.format(toDate); 
						toDate = targetFormat.parse(formattedToDate);
						if((fromDate.compareTo(gstStartDateObj)>0) && (toDate.compareTo(gstStartDateObj)>0))
						{
							fromDate = new Date(fromDate.getTime() + TimeUnit.SECONDS.toMillis(1));
							toDate = new Date(toDate.getTime() + TimeUnit.DAYS.toMillis(1));
							reportConjunction.add(Restrictions.ge("createdAt", fromDate));
							reportConjunction.add(Restrictions.lt("createdAt", toDate));
							reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", fromDate));//added by  basha
							reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", toDate));//added by  basha
							taxType="GST";

						} 
						else if((fromDate.compareTo(gstStartDateObj)<=0) && (toDate.compareTo(gstStartDateObj)<=0)){
							fromDate = new Date(fromDate.getTime() + TimeUnit.SECONDS.toMillis(1));
							toDate = new Date(toDate.getTime() + TimeUnit.DAYS.toMillis(1));
							reportConjunction.add(Restrictions.ge("createdAt", fromDate));
							reportConjunction.add(Restrictions.lt("createdAt", toDate));
							reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", fromDate));//added by  basha
							reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", toDate));//added by  basha
							taxType="Service";

						}

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}

				}
				else{
					String gstDateStart="2017-06-30";
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					Date gstStartDateObj = targetFormat.parse(gstDateStart);
					Date todayDate=new Date();
					if(todayDate.compareTo(gstStartDateObj)>0){
						todayDate = new Date(todayDate.getTime() + TimeUnit.DAYS.toMillis(1));
						gstStartDateObj=new Date(gstStartDateObj.getTime() + TimeUnit.SECONDS.toMillis(1));
						reportConjunction.add(Restrictions.gt("createdAt", gstStartDateObj));
						reportConjunction.add(Restrictions.lt("createdAt",todayDate));
						reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", gstStartDateObj));//added by  basha
						reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", todayDate));//added by  basha
						taxType="GST";
					}

				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				criteria.add(reportConjunction);
				criteria.add(reportDisjunction);
				list=criteria.list();
				reportConjunctionForCreditNote.add(Restrictions.in("companyId",companyIdList));//added by  basha
				reportConjunctionForCreditNote.add(Restrictions.eq("isCreditnoteIssued",true));//added by  basha
				criteriaForCreditNote.add(reportConjunctionForCreditNote);//added by  basha
				listForCreditNote=criteriaForCreditNote.list();
				if(listForCreditNote!=null &&  listForCreditNote.size()>0){
					for(CarCreditNote creditNoteIterate:listForCreditNote){//added by  basha
						CarOrderRow carOrderRowdata =carOrderDao.getCarOrderRowDetail(Long.parseLong(String.valueOf(creditNoteIterate.getRowId())));//added by  basha
						carOrderListNew.add(carOrderRowdata);
					}
				}
				commonDsrUtility.setCarOrderRowList(list);
				commonDsrUtility.setCarOrderRowCreditList(mergeOrderRowsUtility.groupRecordsForCar(carOrderListNew));
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

	public CommonDsrUtility getCommonDsrTrainReports(CommonDsrPage commonDsrPage){
		Session session = null;
		List<TrainOrderRow> list=null;
		CommonDsrUtility commonDsrUtility=new CommonDsrUtility();
		TrainOrderDao trainOrderDao=new TrainOrderDao();
		List<TrainCreditNote> listForCreditNote=null;
		List<TrainOrderRow> trainOrderListNew=new ArrayList<>();
		String taxType = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(TrainOrderRow.class);
			Criteria criteriaForCreditNote = session.createCriteria(TrainCreditNote.class);//added by  basha
			Conjunction reportConjunctionForCreditNote = Restrictions.conjunction();//added by  basha
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction	reportDisjunction=Restrictions.disjunction();
			if(commonDsrPage!=null) 
			{
				CommonDsrFilters commonDsrFilters = commonDsrPage.getCommonDsrFilters();
				List<String>  companyIdList =null;
				if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") || commonDsrFilters.getTravelType().equalsIgnoreCase("T") && (commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("CustomDsrReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("TrainSalesReport")) && commonDsrFilters.getCompanyUserId()!=null && !commonDsrFilters.getCompanyUserId().equals("")){
					Company selectedCompany=new Company();
					if(commonDsrFilters.getCompanyUserId().equals("")) 
						companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
					else{
						selectedCompany.setCompany_userid(commonDsrFilters.getCompanyUserId());
						companyIdList = getCompanyIdList(selectedCompany,1);
					}

				}
				else if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") || commonDsrFilters.getTravelType().equalsIgnoreCase("T")  && (commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("CustomDsrReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("TrainSalesReport") )){
					companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
				}
				if(commonDsrFilters.getBookingMode()!=null && !commonDsrFilters.getBookingMode().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("bookingMode",commonDsrFilters.getBookingMode()));
				}
				if(!commonDsrFilters.getBookingDate().equals("")){
					reportConjunction.add(Restrictions.eq("trainBookingDate",commonDsrFilters.getBookingDate()));
				}
				if(!commonDsrFilters.getTravelDate().equals("")){
					reportConjunction.add(Restrictions.eq("travelDate",DateConversion.StringToDate(commonDsrFilters.getTravelDate())));
				}
				if(commonDsrFilters.getBookingStatus()!=null && !commonDsrFilters.getBookingStatus().equalsIgnoreCase("All")){
					if(commonDsrFilters.getBookingStatus().equalsIgnoreCase("Confirmed")) 
						reportDisjunction.add(Restrictions.eq("statusAction","Cancelled"));
					reportDisjunction.add(Restrictions.eq("statusAction",commonDsrFilters.getBookingStatus()));
				}
				if(!commonDsrFilters.getConfirmationNumber().equals("")){
					reportConjunction.add(Restrictions.eq("confirmationNumber",commonDsrFilters.getConfirmationNumber()));
				}


				if(!commonDsrFilters.getFromDate().equals("") && !commonDsrFilters.getToDate().equals(""))
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
							reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", fromDate));//added by  basha
							reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", toDate));//added by  basha
							taxType="GST";

						} 
						else if((fromDate.compareTo(gstStartDateObj)<=0) && (toDate.compareTo(gstStartDateObj)<=0)){
							fromDate = new Date(fromDate.getTime() + TimeUnit.SECONDS.toMillis(1));
							toDate = new Date(toDate.getTime() + TimeUnit.DAYS.toMillis(1));
							reportConjunction.add(Restrictions.ge("createdAt", fromDate));
							reportConjunction.add(Restrictions.lt("createdAt", toDate));
							reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", fromDate));//added by  basha
							reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", toDate));//added by  basha
							taxType="Service";

						}

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}

				}
				else{
					String gstDateStart="2017-06-30";
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					Date gstStartDateObj = targetFormat.parse(gstDateStart);
					Date todayDate=new Date();
					if(todayDate.compareTo(gstStartDateObj)>0){
						todayDate = new Date(todayDate.getTime()+TimeUnit.DAYS.toMillis(1));
						gstStartDateObj=new Date(gstStartDateObj.getTime()+TimeUnit.SECONDS.toMillis(1));
						reportConjunction.add(Restrictions.gt("createdAt", gstStartDateObj));
						reportConjunction.add(Restrictions.lt("createdAt",todayDate));
						reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", gstStartDateObj));//added by  basha
						reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", todayDate));//added by  basha
						taxType="GST";
					}

				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				criteria.add(reportConjunction);
				criteria.add(reportDisjunction);
				list=criteria.list();
				reportConjunctionForCreditNote.add(Restrictions.in("companyId",companyIdList));//added by  basha
				reportConjunctionForCreditNote.add(Restrictions.eq("isCreditnoteIssued",true));//added by  basha
				criteriaForCreditNote.add(reportConjunctionForCreditNote);//added by  basha
				listForCreditNote=criteriaForCreditNote.list();
				if(listForCreditNote!=null &&  listForCreditNote.size()>0){
					for(TrainCreditNote creditNoteIterate:listForCreditNote){//added by  basha
						TrainOrderRow trainOrderRowdata =trainOrderDao.getTrainOrderRowDetail(Long.parseLong(String.valueOf(creditNoteIterate.getRowId())));//added by  basha
						trainOrderListNew.add(trainOrderRowdata);
					}
				}
				commonDsrUtility.setTrainOrderRowList(list);
				commonDsrUtility.setTrainOrderRowCreditList(mergeOrderRowsUtility.groupRecordsForTrain(trainOrderListNew));
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

	public CommonDsrUtility getCommonDsrBusReports(CommonDsrPage commonDsrPage){
		Session session = null;
		List<BusOrderRow> list=null;
		CommonDsrUtility commonDsrUtility=new CommonDsrUtility();
		BusOrderDao busOrderDao=new BusOrderDao();
		List<BusCreditNote> listForCreditNote=null;
		List<BusOrderRow> busOrderListNew=new ArrayList<>();
		String taxType = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(BusOrderRow.class);
			Criteria criteriaForCreditNote = session.createCriteria(BusCreditNote.class);//added by  basha
			Conjunction reportConjunctionForCreditNote = Restrictions.conjunction();//added by  basha
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction reportDisjunction=Restrictions.disjunction();
			if(commonDsrPage!=null) 
			{
				CommonDsrFilters commonDsrFilters = commonDsrPage.getCommonDsrFilters();
				List<String>  companyIdList =null;
				if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") || commonDsrFilters.getTravelType().equalsIgnoreCase("B") && (commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("CustomDsrReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("BusSalesReport")) && commonDsrFilters.getCompanyUserId()!=null && !commonDsrFilters.getCompanyUserId().equals("")){
					Company selectedCompany=new Company();
					if(commonDsrFilters.getCompanyUserId().equals("")) 
						companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
					else{
						selectedCompany.setCompany_userid(commonDsrFilters.getCompanyUserId());
						companyIdList = getCompanyIdList(selectedCompany,1);
					}
				}
				else if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") || commonDsrFilters.getTravelType().equalsIgnoreCase("B")  && (commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("CustomDsrReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("BusSalesReport"))){
					companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
				}
				if(commonDsrFilters.getBookingMode()!=null && !commonDsrFilters.getBookingMode().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("bookingMode",commonDsrFilters.getBookingMode()));
				}
				if(!commonDsrFilters.getBookingDate().equals("")){
					reportConjunction.add(Restrictions.eq("busBookingDate",commonDsrFilters.getBookingDate()));
				}
				if(!commonDsrFilters.getTravelDate().equals("")){
					reportConjunction.add(Restrictions.eq("travelDate",DateConversion.StringToDate(commonDsrFilters.getTravelDate())));
				}
				if(commonDsrFilters.getBookingStatus()!=null && !commonDsrFilters.getBookingStatus().equalsIgnoreCase("All")){
					if(commonDsrFilters.getBookingStatus().equalsIgnoreCase("Confirmed")) 
						reportDisjunction.add(Restrictions.eq("statusAction","Cancelled"));
					reportDisjunction.add(Restrictions.eq("statusAction",commonDsrFilters.getBookingStatus()));
				}

				if(!commonDsrFilters.getConfirmationNumber().equals("")){
					reportConjunction.add(Restrictions.eq("confirmationNumber",commonDsrFilters.getConfirmationNumber()));
				}


				if(!commonDsrFilters.getFromDate().equals("") && !commonDsrFilters.getToDate().equals(""))
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
						toDate = originalFormat.parse(commonDsrFilters.getToDate());
						String formattedToDate = targetFormat.format(toDate); 
						toDate = targetFormat.parse(formattedToDate);
						if((fromDate.compareTo(gstStartDateObj)>0) && (toDate.compareTo(gstStartDateObj)>0))
						{
							fromDate = new Date(fromDate.getTime() + TimeUnit.SECONDS.toMillis(1));
							toDate = new Date(toDate.getTime() + TimeUnit.DAYS.toMillis(1));
							reportConjunction.add(Restrictions.ge("createdAt", fromDate));
							reportConjunction.add(Restrictions.lt("createdAt", toDate));
							reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", fromDate));//added by  basha
							reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", toDate));//added by  basha
							taxType="GST";

						} 
						else if((fromDate.compareTo(gstStartDateObj)<=0) && (toDate.compareTo(gstStartDateObj)<=0)){
							fromDate = new Date(fromDate.getTime() + TimeUnit.SECONDS.toMillis(1));
							toDate = new Date(toDate.getTime() + TimeUnit.DAYS.toMillis(1));
							reportConjunction.add(Restrictions.ge("createdAt", fromDate));
							reportConjunction.add(Restrictions.lt("createdAt", toDate));
							reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", fromDate));//added by  basha
							reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", toDate));//added by  basha
							taxType="Service";

						}

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}

				}
				else{
					String gstDateStart="2017-06-30";
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					Date gstStartDateObj = targetFormat.parse(gstDateStart);
					Date todayDate=new Date();
					if(todayDate.compareTo(gstStartDateObj)>0){
						todayDate = new Date(todayDate.getTime()+ TimeUnit.DAYS.toMillis(1));
						gstStartDateObj=new Date(gstStartDateObj.getTime()+ TimeUnit.SECONDS.toMillis(1));
						reportConjunction.add(Restrictions.gt("createdAt", gstStartDateObj));
						reportConjunction.add(Restrictions.lt("createdAt",todayDate));
						reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", gstStartDateObj));//added by  basha
						reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", todayDate));//added by  basha
						taxType="GST";
					}

				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				criteria.add(reportConjunction);
				criteria.add(reportDisjunction);
				list=criteria.list();
				reportConjunctionForCreditNote.add(Restrictions.in("companyId",companyIdList));//added by  basha
				reportConjunctionForCreditNote.add(Restrictions.eq("isCreditnoteIssued",true));//added by  basha
				criteriaForCreditNote.add(reportConjunctionForCreditNote);//added by  basha
				listForCreditNote=criteriaForCreditNote.list();
				if(listForCreditNote!=null &&  listForCreditNote.size()>0){
					for(BusCreditNote creditNoteIterate:listForCreditNote){//added by  basha
						BusOrderRow busOrderRowdata =busOrderDao.getBusOrderRowDetail(Long.parseLong(String.valueOf(creditNoteIterate.getRowId())));//added by  basha
						busOrderListNew.add(busOrderRowdata);
					}
				}
				commonDsrUtility.setBusOrderRowList(list);
				commonDsrUtility.setBusOrderRowCreditList(mergeOrderRowsUtility.groupRecordsForBus(busOrderListNew));
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

	public CommonDsrUtility getCommonDsrVisaReports(CommonDsrPage commonDsrPage){
		Session session = null;
		List<VisaOrderRow> list=null;
		CommonDsrUtility commonDsrUtility=new CommonDsrUtility();
		VisaOrderDao visaOrderDao=new VisaOrderDao();
		List<VisaCreditNote> listForCreditNote=null;
		List<VisaOrderRow> visaOrderListNew=new ArrayList<>();
		String taxType = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(VisaOrderRow.class);
			Criteria criteriaForCreditNote = session.createCriteria(VisaCreditNote.class);//added by  basha
			Conjunction reportConjunctionForCreditNote = Restrictions.conjunction();//added by  basha
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction reportDisjunction=Restrictions.disjunction();
			if(commonDsrPage!=null) 
			{
				CommonDsrFilters commonDsrFilters = commonDsrPage.getCommonDsrFilters();
				List<String>  companyIdList =null;
				if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") || commonDsrFilters.getTravelType().equalsIgnoreCase("V")  && (commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("CustomDsrReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("VisaSalesReport")) && commonDsrFilters.getCompanyUserId()!=null && !commonDsrFilters.getCompanyUserId().equals("")){
					Company selectedCompany=new Company();
					if(commonDsrFilters.getCompanyUserId().equals("")) 
						companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
					else{
						selectedCompany.setCompany_userid(commonDsrFilters.getCompanyUserId());
						companyIdList = getCompanyIdList(selectedCompany,1);
					}

				}
				else if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") || commonDsrFilters.getTravelType().equalsIgnoreCase("V")  && (commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("CustomDsrReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("VisaSalesReport") )){
					companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
				}
				if(commonDsrFilters.getBookingMode()!=null && !commonDsrFilters.getBookingMode().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("bookingMode",commonDsrFilters.getBookingMode()));
				}
				if(!commonDsrFilters.getBookingDate().equals("")){
					reportConjunction.add(Restrictions.eq("visaBookingDate",commonDsrFilters.getBookingDate()));
				}
				if(!commonDsrFilters.getTravelDate().equals("")){
					reportConjunction.add(Restrictions.eq("travelDate",DateConversion.StringToDate(commonDsrFilters.getTravelDate())));
				}
				if(commonDsrFilters.getBookingStatus()!=null && !commonDsrFilters.getBookingStatus().equalsIgnoreCase("All")){
					if(commonDsrFilters.getBookingStatus().equalsIgnoreCase("Confirmed")) 
						reportDisjunction.add(Restrictions.eq("statusAction","Cancelled"));
					reportDisjunction.add(Restrictions.eq("statusAction",commonDsrFilters.getBookingStatus()));
				}
				if(!commonDsrFilters.getConfirmationNumber().equals("")){
					reportConjunction.add(Restrictions.eq("confirmationNumber",commonDsrFilters.getConfirmationNumber()));
				}

				if(!commonDsrFilters.getFromDate().equals("") && !commonDsrFilters.getToDate().equals(""))
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
						toDate = originalFormat.parse(commonDsrFilters.getToDate());
						String formattedToDate = targetFormat.format(toDate); 
						toDate = targetFormat.parse(formattedToDate);
						if((fromDate.compareTo(gstStartDateObj)>0) && (toDate.compareTo(gstStartDateObj)>0))
						{
							fromDate = new Date(fromDate.getTime() + TimeUnit.SECONDS.toMillis(1));
							toDate = new Date(toDate.getTime() + TimeUnit.DAYS.toMillis(1));
							reportConjunction.add(Restrictions.ge("createdAt", fromDate));
							reportConjunction.add(Restrictions.lt("createdAt", toDate));
							reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", fromDate));//added by  basha
							reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", toDate));//added by  basha
							taxType="GST";

						} 
						else if((fromDate.compareTo(gstStartDateObj)<=0) && (toDate.compareTo(gstStartDateObj)<=0)){
							fromDate = new Date(fromDate.getTime() + TimeUnit.SECONDS.toMillis(1));
							toDate = new Date(toDate.getTime() + TimeUnit.DAYS.toMillis(1));
							reportConjunction.add(Restrictions.ge("createdAt", fromDate));
							reportConjunction.add(Restrictions.lt("createdAt", toDate));
							reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", fromDate));//added by  basha
							reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", toDate));//added by  basha
							taxType="Service";

						}

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}

				}
				else{
					String gstDateStart="2017-06-30";
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					Date gstStartDateObj = targetFormat.parse(gstDateStart);
					Date todayDate=new Date();
					if(todayDate.compareTo(gstStartDateObj)>0){
						todayDate = new Date(todayDate.getTime()+ TimeUnit.DAYS.toMillis(1));
						gstStartDateObj=new Date(gstStartDateObj.getTime()+ TimeUnit.SECONDS.toMillis(1));
						reportConjunction.add(Restrictions.gt("createdAt", gstStartDateObj));
						reportConjunction.add(Restrictions.lt("createdAt",todayDate));
						reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", gstStartDateObj));//added by  basha
						reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", todayDate));//added by  basha
						taxType="GST";
					}

				}


				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				criteria.add(reportConjunction);
				criteria.add(reportDisjunction);
				list=criteria.list();
				reportConjunctionForCreditNote.add(Restrictions.in("companyId",companyIdList));//added by  basha
				reportConjunctionForCreditNote.add(Restrictions.eq("isCreditnoteIssued",true));//added by  basha
				criteriaForCreditNote.add(reportConjunctionForCreditNote);//added by  basha
				listForCreditNote=criteriaForCreditNote.list();
				if(listForCreditNote!=null &&  listForCreditNote.size()>0){
					for(VisaCreditNote creditNoteIterate:listForCreditNote){//added by  basha
						VisaOrderRow visaOrderRowdata =visaOrderDao.getVisaOrderRowDetail(Long.parseLong(String.valueOf(creditNoteIterate.getRowId())));//added by  basha
						visaOrderListNew.add(visaOrderRowdata);
					}
				}
				commonDsrUtility.setVisaOrderRowList(list);
				commonDsrUtility.setVisaOrderRowCreditList(mergeOrderRowsUtility.groupRecordsForVisa(visaOrderListNew));
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



	public  CommonDsrUtility getCommonDsrInsuranceReports(CommonDsrPage commonDsrPage){
		Session session = null;
		List<InsuranceOrderRow> list=null;
		CommonDsrUtility commonDsrUtility=new CommonDsrUtility();
		InsuranceOrderDao insuranceOrderDao=new InsuranceOrderDao();
		List<InsuranceCreditNote> listForCreditNote=null;
		List<InsuranceOrderRow> insuranceOrderListNew=new ArrayList<>();
		String taxType = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(InsuranceOrderRow.class);
			Criteria criteriaForCreditNote = session.createCriteria(InsuranceCreditNote.class);//added by  basha
			Conjunction reportConjunctionForCreditNote = Restrictions.conjunction();//added by  basha
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction reportDisjunction= Restrictions.disjunction();
			if(commonDsrPage!=null) 
			{
				CommonDsrFilters commonDsrFilters = commonDsrPage.getCommonDsrFilters();
				List<String>  companyIdList =null;
				if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") || commonDsrFilters.getTravelType().equalsIgnoreCase("I")  && (commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("CustomDsrReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("InsuranceSalesReport")) && commonDsrFilters.getCompanyUserId()!=null && !commonDsrFilters.getCompanyUserId().equals("")){
					Company selectedCompany=new Company();
					if(commonDsrFilters.getCompanyUserId().equals("")) 
						companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
					else{
						selectedCompany.setCompany_userid(commonDsrFilters.getCompanyUserId());
						companyIdList = getCompanyIdList(selectedCompany,1);
					}

				}
				else if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") || commonDsrFilters.getTravelType().equalsIgnoreCase("I")  && (commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("CustomDsrReport") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("InsuranceSalesReport") )){
					companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
				}
				if(commonDsrFilters.getBookingMode()!=null && !commonDsrFilters.getBookingMode().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("bookingMode",commonDsrFilters.getBookingMode()));
				}
				if(!commonDsrFilters.getBookingDate().equals("")){
					reportConjunction.add(Restrictions.eq("insuranceBookingDate",commonDsrFilters.getBookingDate()));
				}
				if(!commonDsrFilters.getTravelDate().equals("")){
					reportConjunction.add(Restrictions.eq("travelDate",DateConversion.StringToDate(commonDsrFilters.getTravelDate())));
				}
				if(commonDsrFilters.getBookingStatus()!=null && !commonDsrFilters.getBookingStatus().equalsIgnoreCase("All")){
					if(commonDsrFilters.getBookingStatus().equalsIgnoreCase("Confirmed")) 
						reportDisjunction.add(Restrictions.eq("statusAction","Cancelled"));
					reportDisjunction.add(Restrictions.eq("statusAction",commonDsrFilters.getBookingStatus()));
				}

				if(!commonDsrFilters.getConfirmationNumber().equals("")){
					reportConjunction.add(Restrictions.eq("confirmationNumber",commonDsrFilters.getConfirmationNumber()));
				}

				if(!commonDsrFilters.getFromDate().equals("") && !commonDsrFilters.getToDate().equals(""))
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
						toDate = originalFormat.parse(commonDsrFilters.getToDate());
						String formattedToDate = targetFormat.format(toDate); 
						toDate = targetFormat.parse(formattedToDate);
						if((fromDate.compareTo(gstStartDateObj)>0) && (toDate.compareTo(gstStartDateObj)>0))
						{
							fromDate = new Date(fromDate.getTime() + TimeUnit.SECONDS.toMillis(1));
							toDate = new Date(toDate.getTime() + TimeUnit.DAYS.toMillis(1));
							reportConjunction.add(Restrictions.ge("createdAt", fromDate));
							reportConjunction.add(Restrictions.lt("createdAt", toDate));
							reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", fromDate));//added by  basha
							reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", toDate));//added by  basha
							taxType="GST";

						} 
						else if((fromDate.compareTo(gstStartDateObj)<=0) && (toDate.compareTo(gstStartDateObj)<=0)){
							fromDate = new Date(fromDate.getTime() + TimeUnit.SECONDS.toMillis(1));
							toDate = new Date(toDate.getTime() + TimeUnit.DAYS.toMillis(1));
							reportConjunction.add(Restrictions.ge("createdAt", fromDate));
							reportConjunction.add(Restrictions.lt("createdAt", toDate));
							reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", fromDate));//added by  basha
							reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", toDate));//added by  basha
							taxType="Service";

						}

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}

				}
				else{
					String gstDateStart="2017-06-30";
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					Date gstStartDateObj = targetFormat.parse(gstDateStart);
					Date todayDate=new Date();
					if(todayDate.compareTo(gstStartDateObj)>0){
						todayDate = new Date(todayDate.getTime() + TimeUnit.SECONDS.toMillis(1));
						gstStartDateObj=new Date(gstStartDateObj.getTime()+ TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.gt("createdAt", gstStartDateObj));
						reportConjunction.add(Restrictions.lt("createdAt",todayDate));
						reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", gstStartDateObj));//added by  basha
						reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", todayDate));//added by  basha
						taxType="GST";
					}

				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				criteria.add(reportConjunction);
				criteria.add(reportDisjunction);
				list=criteria.list();
				reportConjunctionForCreditNote.add(Restrictions.in("companyId",companyIdList));//added by  basha
				reportConjunctionForCreditNote.add(Restrictions.eq("isCreditnoteIssued",true));//added by  basha
				criteriaForCreditNote.add(reportConjunctionForCreditNote);//added by  basha
				listForCreditNote=criteriaForCreditNote.list();
				if(listForCreditNote!=null &&  listForCreditNote.size()>0){
					for(InsuranceCreditNote creditNoteIterate:listForCreditNote){//added by  basha
						InsuranceOrderRow insuranceOrderRowdata =insuranceOrderDao.getInsuranceOrderRowDetail(Long.parseLong(String.valueOf(creditNoteIterate.getRowId())));//added by  basha
						insuranceOrderListNew.add(insuranceOrderRowdata);
					}
				}
				commonDsrUtility.setInsuranceOrderRowList(list);
				commonDsrUtility.setInsuranceOrderRowCreditList(mergeOrderRowsUtility.groupRecordsForInsurance(insuranceOrderListNew));
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

	public  CommonDsrUtility getCommonDsrMiscellaneousReports(CommonDsrPage commonDsrPage){

		Session session = null;
		List<MiscellaneousOrderRow> list=null;
		CommonDsrUtility commonDsrUtility=new CommonDsrUtility();
		MiscellaneousOrderDao miscOrderDao=new MiscellaneousOrderDao();
		List<MiscellaneousCreditNote> listForCreditNote=null;
		List<MiscellaneousOrderRow> miscellaneousOrderListNew=new ArrayList<>();
		String taxType = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(MiscellaneousOrderRow.class);
			Criteria criteriaForCreditNote = session.createCriteria(MiscellaneousCreditNote.class);//added by  basha
			Conjunction reportConjunctionForCreditNote = Restrictions.conjunction();//added by  basha
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction reportDisjunction= Restrictions.disjunction();
			if(commonDsrPage!=null) 
			{
				CommonDsrFilters commonDsrFilters = commonDsrPage.getCommonDsrFilters();
				List<String>  companyIdList =null;
				List<Integer>  companyIdListNew = new  LinkedList<>();
				if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") || commonDsrFilters.getTravelType().equalsIgnoreCase("M")  && (commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("CustomDsrReport") ||commonDsrFilters.getTravelReportType().equalsIgnoreCase("MiscellaneousSalesReport")) && commonDsrFilters.getCompanyUserId()!=null && !commonDsrFilters.getCompanyUserId().equals("")){
					Company selectedCompany=new Company();
					if(commonDsrFilters.getCompanyUserId().equals("")) 
						companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
					else{
						selectedCompany.setCompany_userid(commonDsrFilters.getCompanyUserId());
						companyIdList = getCompanyIdList(selectedCompany,1);
					}

				}
				else if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") || commonDsrFilters.getTravelType().equalsIgnoreCase("M")  && (commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") || commonDsrFilters.getTravelReportType().equalsIgnoreCase("CustomDsrReport") ||commonDsrFilters.getTravelReportType().equalsIgnoreCase("MiscellaneousSalesReport"))){
					companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
				}
				if(companyIdList!=null && companyIdList.size()>0){
					for(String companyId:companyIdList){
						companyIdListNew.add(Integer.valueOf(companyId));
					}

				}

				if(commonDsrFilters.getBookingMode()!=null && !commonDsrFilters.getBookingMode().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("bookingMode",commonDsrFilters.getBookingMode()));
				}
				if(!commonDsrFilters.getBookingDate().equals("")){
					reportConjunction.add(Restrictions.eq("bookingDate",commonDsrFilters.getBookingDate()));
				}
				if(!commonDsrFilters.getTravelDate().equals("")){
					reportConjunction.add(Restrictions.eq("travelDate",DateConversion.StringToDate(commonDsrFilters.getTravelDate())));
				}
				if(commonDsrFilters.getBookingStatus()!=null && !commonDsrFilters.getBookingStatus().equalsIgnoreCase("All")){
					if(commonDsrFilters.getBookingStatus().equalsIgnoreCase("Confirmed")) 
						reportDisjunction.add(Restrictions.eq("statusAction","Cancelled"));
					reportDisjunction.add(Restrictions.eq("statusAction",commonDsrFilters.getBookingStatus()));
				}

				if(!commonDsrFilters.getConfirmationNumber().equals("")){
					reportConjunction.add(Restrictions.eq("confirmationNumber",commonDsrFilters.getConfirmationNumber()));
				}

				if(!commonDsrFilters.getFromDate().equals("") && !commonDsrFilters.getToDate().equals(""))
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
						toDate = originalFormat.parse(commonDsrFilters.getToDate());
						String formattedToDate = targetFormat.format(toDate); 
						toDate = targetFormat.parse(formattedToDate);
						if((fromDate.compareTo(gstStartDateObj)>0) && (toDate.compareTo(gstStartDateObj)>0))
						{
							fromDate = new Date(fromDate.getTime() + TimeUnit.SECONDS.toMillis(1));
							toDate = new Date(toDate.getTime() + TimeUnit.DAYS.toMillis(1));
							reportConjunction.add(Restrictions.ge("createdAt", fromDate));
							reportConjunction.add(Restrictions.lt("createdAt", toDate));
							reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", fromDate));//added by  basha
							reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", toDate));//added by  basha
							taxType="GST";
						} 
						else if((fromDate.compareTo(gstStartDateObj)<=0) && (toDate.compareTo(gstStartDateObj)<=0)){
							fromDate = new Date(fromDate.getTime() + TimeUnit.SECONDS.toMillis(1));
							toDate = new Date(toDate.getTime() + TimeUnit.DAYS.toMillis(1));
							reportConjunction.add(Restrictions.ge("createdAt", fromDate));
							reportConjunction.add(Restrictions.lt("createdAt", toDate));
							reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", fromDate));//added by  basha
							reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", toDate));//added by  basha
							taxType="Service";

						}

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}

				}
				else{
					String gstDateStart="2017-06-30";
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					Date gstStartDateObj = targetFormat.parse(gstDateStart);
					Date todayDate=new Date();
					if(todayDate.compareTo(gstStartDateObj)>0){
						todayDate = new Date(todayDate.getTime()+ TimeUnit.SECONDS.toMillis(1));
						gstStartDateObj=new Date(gstStartDateObj.getTime()+ TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.gt("createdAt", gstStartDateObj));
						reportConjunction.add(Restrictions.lt("createdAt",todayDate));
						reportConjunctionForCreditNote.add(Restrictions.ge("issuedAt", gstStartDateObj));//added by  basha
						reportConjunctionForCreditNote.add(Restrictions.lt("issuedAt", todayDate));//added by  basha
						taxType="GST";
					}

				}

				reportConjunction.add(Restrictions.in("companyId",companyIdListNew));
				criteria.add(reportConjunction);
				criteria.add(reportDisjunction);
				list=criteria.list();
				reportConjunctionForCreditNote.add(Restrictions.in("companyId",companyIdList));//added by  basha
				reportConjunctionForCreditNote.add(Restrictions.eq("isCreditnoteIssued",true));//added by  basha
				criteriaForCreditNote.add(reportConjunctionForCreditNote);//added by  basha
				listForCreditNote=criteriaForCreditNote.list();
				if(listForCreditNote!=null &&  listForCreditNote.size()>0){
					for(MiscellaneousCreditNote creditNoteIterate:listForCreditNote){//added by  basha
						MiscellaneousOrderRow miscOrderRowdata =miscOrderDao.getMiscellaneousOrderRowById(Long.parseLong(String.valueOf(creditNoteIterate.getRowId())));//added by  basha
						miscellaneousOrderListNew.add(miscOrderRowdata);
					}
				}
				commonDsrUtility.setMiscellaneousOrderRowList(list);
				commonDsrUtility.setMiscellaneousOrderRowCreditList(mergeOrderRowsUtility.groupRecordsForMisc(miscellaneousOrderListNew));
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
	public  List<String> getCompanyIdList(Company company, int reportType, String companyPreferable)
	{
		List<String> companyIdList = new ArrayList<String>();
		Session session = null;
		try{			
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Company.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			// To get total row count.
			List<Company> list = null;
			logger.info("##########company name preferable---"+companyPreferable);

			switch (reportType){
			case FlightReportFilter.REPORTS_MINE:
				reportConjunction.add(Restrictions.eq("company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					Disjunction reportDisjunction = Restrictions.disjunction();
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}

				criteria.add(reportConjunction);
				list = criteria.list();
				break;			
			case FlightReportFilter.REPORTS_ALL:
				if(!company.getCompanyRole().isAgent() && !company.getCompanyRole().isDistributor()){
					reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));	
				}
				else{
					reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				}
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					Disjunction reportDisjunction = Restrictions.disjunction();
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
					Disjunction reportDisjunction = Restrictions.disjunction();
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
					Disjunction reportDisjunction = Restrictions.disjunction();
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isAgent",true));
				list = criteria.list();

				break;
			case FlightReportFilter.REPORTS_MY_DISTRIBUTORS:
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					Disjunction reportDisjunction = Restrictions.disjunction();
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isDistributor",true));
				list = criteria.list();
				break;
			case FlightReportFilter.REPORTS_MY_CORPORATES:
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					Disjunction reportDisjunction = Restrictions.disjunction();
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isCorporate",true));
				list = criteria.list();
				break;
			case FlightReportFilter.REPORTS_ALL_AFFILIATES:
				reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					Disjunction reportDisjunction = Restrictions.disjunction();
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
					Disjunction reportDisjunction = Restrictions.disjunction();
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isAgent",true));
				list = criteria.list();
				break;
			case FlightReportFilter.REPORTS_ALL_DISTRIBUTORS:
				//For direct distributors...
				reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					Disjunction reportDisjunction = Restrictions.disjunction();
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
					Disjunction reportDisjunction = Restrictions.disjunction();
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
					Disjunction reportDisjunction = Restrictions.disjunction();
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
		return companyIdList;
	}
	public  List<String> getCompanyIdList(Company company, int reportType)
	{
		List<String> companyIdList = new ArrayList<String>();
		Session session = null;
		try{			
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Company.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			// To get total row count.
			List<Company> list = null;
			switch (reportType){
			case FlightReportFilter.REPORTS_MINE:
				reportConjunction.add(Restrictions.eq("company_userid",company.getCompany_userid()));
				criteria.add(reportConjunction);
				list = criteria.list();
				break;			
			case 0:	
				companyIdList.add(String.valueOf(company.getCompanyid()));
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				criteria.add(reportConjunction);
				list = criteria.list();
				break;
			}				
			criteria.add(reportConjunction);
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
		return companyIdList;
	}
	public   FlightOrderRowMarkup getCompanyMarkup (String companyId,Long orderRowId) {

		FlightOrderRowMarkup flightOrderRowMarkup= null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightOrderRowMarkup hor where hor.CompanyId=:companyid and hor.flightOrderRow.id=:orderRowId";
			Query query = session.createQuery(sql);
			query.setParameter("companyid", companyId);
			query.setParameter("orderRowId", orderRowId);
			flightOrderRowMarkup = (FlightOrderRowMarkup) query.uniqueResult();

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}

		return flightOrderRowMarkup;
	}
}
