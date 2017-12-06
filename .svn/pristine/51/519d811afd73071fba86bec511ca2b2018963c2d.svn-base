package com.admin.dashboard.analysis.json.daoImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.admin.dashboard.Vo.DashBoardJsonVo;
import com.admin.dashboard.analysis.json.dao.AllServicesCommonPieChartDao;
import com.admin.dashboard.analysis.json.vo.CreditNotesCommonVo;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

public class AllServicesCommonPieChartDaoImpl implements AllServicesCommonPieChartDao {
	/**
	 * @author Basha
	 * 28 Aug 2017
	 */
	public static final Logger logger = Logger.getLogger(AllServicesCommonPieChartDaoImpl.class);
	Session session = null;
	CommonServiceFetchCreditNoteDao commonServiceFetchCreditNoteDao=new CommonServiceFetchCreditNoteDao();
	
	@Override
	public DashBoardJsonVo getAllServicesOrdersCount(User userSessionObj, Company companySessionObj, List<String> userIdList,String type,String serviceType) {
		Long count = new Long(0);
		Long countForCancellation = new Long(0);
		BigDecimal totalAmount=new BigDecimal(0);
		Criteria criteria = null;
		Criteria criteriaForCreditNote = null;
		DashBoardJsonVo  dashBoardJsonVo=new DashBoardJsonVo();
		List<FlightOrderRow> listOfFlightOrders=new ArrayList<>();
		List<HotelOrderRow> listOfHotelOrders=new ArrayList<>();
		List<CarOrderRow> listOfCarOrders=new ArrayList<>();
		List<BusOrderRow> listOfBusOrders=new ArrayList<>();
		List<TrainOrderRow> listOfTrainOrders=new ArrayList<>();
		List<VisaOrderRow> listOfVisaOrders=new ArrayList<>();
		List<InsuranceOrderRow> listOfInsuranceOrders=new ArrayList<>();
		List<MiscellaneousOrderRow> listOfMiscOrders=new ArrayList<>();
		List<FlightOrderRow> listOfFlights=new ArrayList<>();
		List<HotelOrderRow> listOfHotels=new ArrayList<>();
		List<CarOrderRow> listOfCars=new ArrayList<>();
		List<BusOrderRow> listOfBuss=new ArrayList<>();
		List<TrainOrderRow> listOfTrains=new ArrayList<>();
		List<VisaOrderRow> listOfVisas=new ArrayList<>();
		List<InsuranceOrderRow> listOfInsurances=new ArrayList<>();
		List<MiscellaneousOrderRow> listOfMiscs=new ArrayList<>();
		List<Long> listOfId=new ArrayList<>();
		List<CreditNotesCommonVo> creditNoteCommonList=new ArrayList<>();
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if(serviceType.equalsIgnoreCase("Flight")){
				criteria = session.createCriteria(FlightOrderRow.class);
				criteriaForCreditNote = session.createCriteria(FlightOrderRow.class);
				//criteriaForCreditNote = session.createCriteria(CreditNote.class);
			}
			else if(serviceType.equalsIgnoreCase("Hotel")){
				criteria = session.createCriteria(HotelOrderRow.class);
				criteriaForCreditNote = session.createCriteria(HotelOrderRow.class);
				//criteriaForCreditNote = session.createCriteria(HotelCreditNote.class);
			}
			else if(serviceType.equalsIgnoreCase("Car")){
				criteria = session.createCriteria(CarOrderRow.class);
				criteriaForCreditNote = session.createCriteria(CarOrderRow.class);
				//criteriaForCreditNote = session.createCriteria(CarCreditNote.class);
			}
			else if(serviceType.equalsIgnoreCase("Bus")){
				criteria = session.createCriteria(BusOrderRow.class);
				criteriaForCreditNote = session.createCriteria(BusOrderRow.class);
				//criteriaForCreditNote = session.createCriteria(BusCreditNote.class);
			}
			else if(serviceType.equalsIgnoreCase("Train")){
				criteria = session.createCriteria(TrainOrderRow.class);
				criteriaForCreditNote = session.createCriteria(TrainOrderRow.class);
				//criteriaForCreditNote = session.createCriteria(TrainCreditNote.class);
			}
			else if(serviceType.equalsIgnoreCase("Visa")){
				criteria = session.createCriteria(VisaOrderRow.class);
				criteriaForCreditNote = session.createCriteria(VisaOrderRow.class);
				//criteriaForCreditNote = session.createCriteria(VisaCreditNote.class);
			}
			else if(serviceType.equalsIgnoreCase("Insurance")){
				criteria = session.createCriteria(InsuranceOrderRow.class);
				criteriaForCreditNote = session.createCriteria(InsuranceOrderRow.class);
				//criteriaForCreditNote = session.createCriteria(InsuranceCreditNote.class);
			}
			else if(serviceType.equalsIgnoreCase("Misc")){
				criteria = session.createCriteria(MiscellaneousOrderRow.class);
				criteriaForCreditNote = session.createCriteria(MiscellaneousOrderRow.class);
				//criteriaForCreditNote = session.createCriteria(MiscellaneousCreditNote.class);
			}
			Conjunction conjunction = Restrictions.conjunction();
			Disjunction disjunction=Restrictions.disjunction();
			Conjunction conjunctionForCancellation = Restrictions.conjunction();
			if (userSessionObj != null && companySessionObj != null) {
				if(serviceType.equalsIgnoreCase("Misc")){
					List<Integer> userIdListNew= new ArrayList<Integer>(); 
					if(userIdList!=null && userIdList.size()>0)
					{
						for(String user : userIdList)
						{
							userIdListNew.add(Integer.parseInt(user));
						}
					}
					
					if (userSessionObj.getUserrole_id().isSuperuser()) {

						if (userIdList != null) {
							conjunction.add(Restrictions.in("userId", userIdListNew));
							conjunctionForCancellation.add(Restrictions.in("userId", userIdListNew));
						}
					}
					else if (userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()) {
						if (companySessionObj.getCompanyRole().isCorporate()) {
							conjunction.add(Restrictions.eq("companyId", companySessionObj.getCompanyid()));
							conjunctionForCancellation.add(Restrictions.eq("companyId", companySessionObj.getCompanyid()));
						} else {
							if (userIdList != null)
								conjunction.add(Restrictions.eq("companyId", companySessionObj.getCompanyid()));
							conjunctionForCancellation.add(Restrictions.eq("companyId", companySessionObj.getCompanyid()));
						}
					}

					else if ((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports())
							&& !userSessionObj.getUserrole_id().isAdmin()) {
						if (userSessionObj.getCompanyid() == companySessionObj.getCompanyid()) {
							conjunction.add(Restrictions.eq("companyId", companySessionObj.getCompanyid()));
							conjunctionForCancellation.add(Restrictions.eq("companyId", companySessionObj.getCompanyid()));
						}
					}

					else {
						conjunction.add(Restrictions.eq("userId", userSessionObj.getId()));
						conjunctionForCancellation.add(Restrictions.eq("userId", userSessionObj.getId()));
					}
					
				}else{
					if (userSessionObj.getUserrole_id().isSuperuser()) {

						if (userIdList != null) {
							conjunction.add(Restrictions.in("userId", userIdList));
							conjunctionForCancellation.add(Restrictions.in("userId", userIdList));
						}
					}
					else if (userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()) {
						if (companySessionObj.getCompanyRole().isCorporate()) {
							conjunction.add(Restrictions.eq("companyId", String.valueOf(companySessionObj.getCompanyid())));
							conjunctionForCancellation.add(Restrictions.eq("companyId", String.valueOf(companySessionObj.getCompanyid())));
						} else {
							if (userIdList != null)
								conjunction.add(Restrictions.eq("companyId", String.valueOf(companySessionObj.getCompanyid())));
							conjunctionForCancellation.add(Restrictions.eq("companyId", String.valueOf(companySessionObj.getCompanyid())));
						}
					}

					else if ((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports())
							&& !userSessionObj.getUserrole_id().isAdmin()) {
						if (userSessionObj.getCompanyid() == companySessionObj.getCompanyid()) {
							conjunction.add(Restrictions.eq("companyId", String.valueOf(companySessionObj.getCompanyid())));
							conjunctionForCancellation.add(Restrictions.eq("companyId", String.valueOf(companySessionObj.getCompanyid())));
						}
					}
					else {
						conjunction.add(Restrictions.eq("userId", userSessionObj.getId()));
						conjunctionForCancellation.add(Restrictions.eq("userId", userSessionObj.getId()));
					}
				}
						disjunction.add(Restrictions.eq("statusAction", "Confirmed"));
						disjunction.add(Restrictions.eq("statusAction", "Cancelled"));
						conjunction.add(disjunction);
						//conjunctionForCancellation.add(Restrictions.eq("statusAction", "Confirmed"));
						
				
					if(serviceType.equalsIgnoreCase("Flight") || serviceType.equalsIgnoreCase("Hotel"))
						conjunctionForCancellation.add(Restrictions.eq("isCreditNoteIssued", true));
					else
						conjunctionForCancellation.add(Restrictions.eq("creditNoteIssued", true));
						
						conjunctionForCancellation.add(Restrictions.eq("statusAction", "Cancelled"));
				
				 getDayConjunction(serviceType,conjunction);
				 getDayConjunctionForCredit(serviceType,conjunctionForCancellation);
			
			}
			criteria.add(conjunction);
			criteriaForCreditNote.add(conjunctionForCancellation);
			if(serviceType.equalsIgnoreCase("Flight")){
				listOfFlightOrders=criteriaForCreditNote.list();
				listOfFlights=criteria.list();
			for(FlightOrderRow OrdersRow:listOfFlightOrders){
				listOfId.add(OrdersRow.getId());
			}
			}
			if(serviceType.equalsIgnoreCase("Hotel")){
				listOfHotelOrders=criteriaForCreditNote.list();
				listOfHotels=criteria.list();
			for(HotelOrderRow OrdersRow:listOfHotelOrders){
				listOfId.add(OrdersRow.getId());
			}
			}
			if(serviceType.equalsIgnoreCase("Car")){
				listOfCarOrders=criteriaForCreditNote.list();
				listOfCars=criteria.list();
			for(CarOrderRow OrdersRow:listOfCarOrders){
				listOfId.add(OrdersRow.getId());
			}
			}
			
			if(serviceType.equalsIgnoreCase("Bus")){
				listOfBusOrders=criteriaForCreditNote.list();
				listOfBuss=criteria.list();
			for(BusOrderRow OrdersRow:listOfBusOrders){
				listOfId.add(OrdersRow.getId());
			}
			}
			if(serviceType.equalsIgnoreCase("Train")){
				listOfTrainOrders=criteriaForCreditNote.list();
				listOfTrains=criteria.list();
			for(TrainOrderRow OrdersRow:listOfTrainOrders){
				listOfId.add(OrdersRow.getId());
			}
			}
			if(serviceType.equalsIgnoreCase("Visa")){
				listOfVisaOrders=criteriaForCreditNote.list();
				listOfVisas=criteria.list();
			for(VisaOrderRow OrdersRow:listOfVisaOrders){
				listOfId.add(OrdersRow.getId());
			}
			}
			if(serviceType.equalsIgnoreCase("Insurance")){
				listOfInsuranceOrders=criteriaForCreditNote.list();
				listOfInsurances=criteria.list();
			for(InsuranceOrderRow OrdersRow:listOfInsuranceOrders){
				listOfId.add(OrdersRow.getId());
			}
			}
			if(serviceType.equalsIgnoreCase("Misc")){
				listOfMiscOrders=criteriaForCreditNote.list();
				listOfMiscs=criteria.list();
			for(MiscellaneousOrderRow OrdersRow:listOfMiscOrders){
				listOfId.add(OrdersRow.getId());
			}
			}
			count= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			countForCancellation= (Long) criteriaForCreditNote.setProjection(Projections.rowCount()).uniqueResult();
			if(serviceType.equalsIgnoreCase("Flight") || serviceType.equalsIgnoreCase("Hotel"))
				totalAmount=  (BigDecimal) criteria.setProjection(Projections.sum("finalPrice")).uniqueResult();//added by basha
			else
				totalAmount=  (BigDecimal) criteria.setProjection(Projections.sum("totalAmount")).uniqueResult();//added by basha
			
			 
				//System.out.println("count========================="+count);
				dashBoardJsonVo.setCount(count);
				dashBoardJsonVo.setCancelledCount(countForCancellation);//added by basha
				if(totalAmount!=null)
					dashBoardJsonVo.setTotalBookingAmount(totalAmount);//added by basha
				else 
					dashBoardJsonVo.setTotalBookingAmount(new BigDecimal(0));//added by basha
				
				
				
				BigDecimal totCancAmountOld=new BigDecimal(0);
				BigDecimal totLossAmount=new BigDecimal(0);
				
				if(listOfId!=null && listOfId.size()>0){
					if(userIdList!=null && userIdList.size()>0){
						for(Long ids:listOfId){
							CreditNotesCommonVo creditNoteCommon=new CreditNotesCommonVo();
							if(serviceType.equalsIgnoreCase("Flight"))
								creditNoteCommon=commonServiceFetchCreditNoteDao.getCreditNoteForFlight(userIdList, ids,companySessionObj.getCompanyid());
							
							if(serviceType.equalsIgnoreCase("Hotel"))
								creditNoteCommon=commonServiceFetchCreditNoteDao.getCreditNoteForHotel(userIdList, ids,companySessionObj.getCompanyid());
							
							if(serviceType.equalsIgnoreCase("Car"))
								creditNoteCommon=commonServiceFetchCreditNoteDao.getCreditNoteForCar(userIdList, ids,companySessionObj.getCompanyid());
							
							if(serviceType.equalsIgnoreCase("Bus"))
								creditNoteCommon=commonServiceFetchCreditNoteDao.getCreditNoteForBus(userIdList, ids,companySessionObj.getCompanyid());
							
							if(serviceType.equalsIgnoreCase("Train"))
								creditNoteCommon=commonServiceFetchCreditNoteDao.getCreditNoteForTrain(userIdList, ids,companySessionObj.getCompanyid());
							
							if(serviceType.equalsIgnoreCase("Visa"))
								creditNoteCommon=commonServiceFetchCreditNoteDao.getCreditNoteForVisa(userIdList, ids,companySessionObj.getCompanyid());
							
							if(serviceType.equalsIgnoreCase("Insurance"))
								creditNoteCommon=commonServiceFetchCreditNoteDao.getCreditNoteForInsurance(userIdList, ids,companySessionObj.getCompanyid());
							
							if(serviceType.equalsIgnoreCase("Misc"))
								creditNoteCommon=commonServiceFetchCreditNoteDao.getCreditNoteForMisc(userIdList, ids,companySessionObj.getCompanyid());
								//totCancAmountOld=totCancAmountOld.add(totCancAmount).setScale(0, RoundingMode.UP);
							creditNoteCommonList.add(creditNoteCommon);
						}
					}
				}
				
				for(CreditNotesCommonVo creditNotesCommonVo:creditNoteCommonList){
					totCancAmountOld=totCancAmountOld.add(creditNotesCommonVo.getRefundedAmount()).setScale(0, RoundingMode.UP);
				}
				if(totCancAmountOld!=null)
					dashBoardJsonVo.setTotalRefundedAmount(totCancAmountOld);//added by basha
				else
					dashBoardJsonVo.setTotalRefundedAmount(new BigDecimal(0));//added by basha 
				
				
				if(serviceType.equalsIgnoreCase("Flight")){
					if(listOfFlights!=null && listOfFlights.size()>0 && creditNoteCommonList!=null && creditNoteCommonList.size()>0){
						for(FlightOrderRow flightrow:listOfFlights){
							if(flightrow.isCreditNoteIssued())
							for(CreditNotesCommonVo creditNoteFlight:creditNoteCommonList){
								
								if(flightrow.getId().equals(new Long(creditNoteFlight.getRowId())))
									totLossAmount=totLossAmount.add(flightrow.getFinalPrice().subtract(creditNoteFlight.getRefundedAmount()).setScale(0, RoundingMode.UP));
							}
						}
					}
				}
				
				if(serviceType.equalsIgnoreCase("Hotel")){
					if(listOfHotels!=null && listOfHotels.size()>0 && creditNoteCommonList!=null && creditNoteCommonList.size()>0){
						for(HotelOrderRow hotelrow:listOfHotels){
							if(hotelrow.isCreditNoteIssued())
							for(CreditNotesCommonVo creditNoteHotel:creditNoteCommonList){
								
								if(hotelrow.getId().equals(new Long(creditNoteHotel.getRowId())))
									totLossAmount=totLossAmount.add(hotelrow.getFinalPrice().subtract(creditNoteHotel.getRefundedAmount()).setScale(0, RoundingMode.UP));
							}
						}
					}
				}
				
				if(serviceType.equalsIgnoreCase("Car")){
					if(listOfCars!=null && listOfCars.size()>0 && creditNoteCommonList!=null && creditNoteCommonList.size()>0){
						for(CarOrderRow carrow:listOfCars){
							if(carrow.isCreditNoteIssued())
							for(CreditNotesCommonVo creditNoteCar:creditNoteCommonList){
								
								if(carrow.getId().equals(new Long(creditNoteCar.getRowId())))
									totLossAmount=totLossAmount.add(carrow.getTotalAmount().subtract(creditNoteCar.getRefundedAmount()).setScale(0, RoundingMode.UP));
							}
						}
					}
					
				}
				
				if(serviceType.equalsIgnoreCase("Bus")){
					if(listOfBuss!=null && listOfBuss.size()>0 && creditNoteCommonList!=null && creditNoteCommonList.size()>0){
						for(BusOrderRow busrow:listOfBuss){
							if(busrow.isCreditNoteIssued())
							for(CreditNotesCommonVo creditNoteBus:creditNoteCommonList){
								
								if(busrow.getId().equals(new Long(creditNoteBus.getRowId())))
									totLossAmount=totLossAmount.add(busrow.getTotalAmount().subtract(creditNoteBus.getRefundedAmount()).setScale(0, RoundingMode.UP));
							}
						}
					}
					
				}
				
				if(serviceType.equalsIgnoreCase("Train")){
					if(listOfTrains!=null && listOfTrains.size()>0 && creditNoteCommonList!=null && creditNoteCommonList.size()>0){
						for(TrainOrderRow trainrow:listOfTrains){
							if(trainrow.isCreditNoteIssued())
							for(CreditNotesCommonVo creditNoteTrain:creditNoteCommonList){
								
								if(trainrow.getId().equals(new Long(creditNoteTrain.getRowId())))
									totLossAmount=totLossAmount.add(trainrow.getTotalAmount().subtract(creditNoteTrain.getRefundedAmount()).setScale(0, RoundingMode.UP));
							}
						}
					}
					
				}
				
				if(serviceType.equalsIgnoreCase("Visa")){
					if(listOfVisas!=null && listOfVisas.size()>0 && creditNoteCommonList!=null && creditNoteCommonList.size()>0){
						for(VisaOrderRow visarow:listOfVisas){
							if(visarow.isCreditNoteIssued())
							for(CreditNotesCommonVo creditNoteVisa:creditNoteCommonList){
								
								if(visarow.getId().equals(new Long(creditNoteVisa.getRowId())))
									totLossAmount=totLossAmount.add(visarow.getTotalAmount().subtract(creditNoteVisa.getRefundedAmount()).setScale(0, RoundingMode.UP));
							}
						}
					}
					
				}
				
				if(serviceType.equalsIgnoreCase("Insurance")){
					if(listOfInsurances!=null && listOfInsurances.size()>0 && creditNoteCommonList!=null && creditNoteCommonList.size()>0){
						for(InsuranceOrderRow insurancerow:listOfInsurances){
							if(insurancerow.isCreditNoteIssued())
							for(CreditNotesCommonVo creditNoteInsurance:creditNoteCommonList){
								if(insurancerow.getId().equals(new Long(creditNoteInsurance.getRowId())))
									totLossAmount=totLossAmount.add(insurancerow.getTotalAmount().subtract(creditNoteInsurance.getRefundedAmount()).setScale(0, RoundingMode.UP));
							}
						}
					}
				}
				
				if(serviceType.equalsIgnoreCase("Misc")){
					if(listOfMiscs!=null && listOfMiscs.size()>0 && creditNoteCommonList!=null && creditNoteCommonList.size()>0){
						for(MiscellaneousOrderRow miscrow:listOfMiscs){
							if(miscrow.isCreditNoteIssued())
							for(CreditNotesCommonVo creditNoteMisc:creditNoteCommonList){
								if(miscrow.getId().equals(new Long(creditNoteMisc.getRowId())))
									totLossAmount=totLossAmount.add(miscrow.getTotalAmount().subtract(creditNoteMisc.getRefundedAmount()).setScale(0, RoundingMode.UP));
							}
						}
					}
				}
					
				if(totLossAmount!=null){
					dashBoardJsonVo.setTotalLossAmount(totLossAmount.setScale(0, RoundingMode.UP));
				}else{
					dashBoardJsonVo.setTotalLossAmount(new BigDecimal(0));//added by basha
				}
				
				

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------" + e.getMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return dashBoardJsonVo;
	}
	
	
	
	public Conjunction getDayConjunction(String serviceType,Conjunction conjunction){
			Date todayDate = new Date();
			String date=DateConversion.convertDateToStringToDate(todayDate);
			todayDate=DateConversion.StringToDate(date);
			conjunction.add(Restrictions.ge("createdAt", todayDate));
			
		return conjunction;
}
	
	
	public Conjunction getDayConjunctionForCredit(String serviceType,Conjunction conjunction){
		Date todayDate = new Date();
		String date=DateConversion.convertDateToStringToDate(todayDate);
		todayDate=DateConversion.StringToDate(date);
		conjunction.add(Restrictions.ge("createdAt", todayDate));
		conjunction.add(Restrictions.ge("updatedAt", todayDate));
		
	return conjunction;
}
	
	//this code for a pie chart based on requirement   and commented by basha
	
	
	/*@Override
	public Long getAllServicesOrdersCount(User userSessionObj, Company companySessionObj, List<String> userIdList,String type,String serviceType) {
		Long count = new Long(0);
		Criteria criteria = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if(serviceType.equalsIgnoreCase("Flight"))
				criteria = session.createCriteria(FlightOrderRow.class);
			else if(serviceType.equalsIgnoreCase("Hotel"))
				criteria = session.createCriteria(HotelOrderRow.class);
			else if(serviceType.equalsIgnoreCase("Car"))
				criteria = session.createCriteria(CarOrderRow.class);
			else if(serviceType.equalsIgnoreCase("Bus"))
				criteria = session.createCriteria(BusOrderRow.class);
			else if(serviceType.equalsIgnoreCase("Train"))
				criteria = session.createCriteria(TrainOrderRow.class);
			else if(serviceType.equalsIgnoreCase("Visa"))
				criteria = session.createCriteria(VisaOrderRow.class);
			else if(serviceType.equalsIgnoreCase("Insurance"))
				criteria = session.createCriteria(InsuranceOrderRow.class);
			else if(serviceType.equalsIgnoreCase("Misc"))
				criteria = session.createCriteria(MiscellaneousOrderRow.class);
			Conjunction conjunction = Restrictions.conjunction();
			if (userSessionObj != null && companySessionObj != null) {
				if(serviceType.equalsIgnoreCase("Misc")){
					List<Integer> userIdListNew= new ArrayList<Integer>(); 
					if(userIdList!=null && userIdList.size()>0)
					{
						for(String user : userIdList)
						{
							userIdListNew.add(Integer.parseInt(user));
						}
					}
					
					if (userSessionObj.getUserrole_id().isSuperuser()) {

						if (userIdList != null) {
							conjunction.add(Restrictions.in("userId", userIdListNew));
						}
					}
					else if (userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()) {
						if (companySessionObj.getCompanyRole().isCorporate()) {
							conjunction.add(Restrictions.eq("companyId", companySessionObj.getCompanyid()));
						} else {
							if (userIdList != null)
								conjunction.add(Restrictions.eq("companyId", companySessionObj.getCompanyid()));
						}
					}

					else if ((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports())
							&& !userSessionObj.getUserrole_id().isAdmin()) {
						if (userSessionObj.getCompanyid() == companySessionObj.getCompanyid()) {
							conjunction.add(Restrictions.eq("companyId", companySessionObj.getCompanyid()));
						}
					}

					else {
						conjunction.add(Restrictions.eq("userId", userSessionObj.getId()));
					}
					
				}else{
					if (userSessionObj.getUserrole_id().isSuperuser()) {

						if (userIdList != null) {
							conjunction.add(Restrictions.in("userId", userIdList));
						}
					}
					else if (userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()) {
						if (companySessionObj.getCompanyRole().isCorporate()) {
							conjunction.add(Restrictions.eq("companyId", String.valueOf(companySessionObj.getCompanyid())));
						} else {
							if (userIdList != null)
								conjunction.add(Restrictions.eq("companyId", String.valueOf(companySessionObj.getCompanyid())));
						}
					}

					else if ((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports())
							&& !userSessionObj.getUserrole_id().isAdmin()) {
						if (userSessionObj.getCompanyid() == companySessionObj.getCompanyid()) {
							conjunction.add(Restrictions.eq("companyId", String.valueOf(companySessionObj.getCompanyid())));
						}
					}
					else {
						conjunction.add(Restrictions.eq("userId", userSessionObj.getId()));
					}
				}
				if(type.equalsIgnoreCase("BK"))
				{
						conjunction.add(Restrictions.eq("statusAction", "Confirmed"));
						
				}else if(type.equalsIgnoreCase("BVC")){
					if(serviceType.equalsIgnoreCase("Flight") || serviceType.equalsIgnoreCase("Hotel"))
					conjunction.add(Restrictions.eq("isCreditNoteIssued", true));
					else
						conjunction.add(Restrictions.eq("creditNoteIssued", true));
						
					conjunction.add(Restrictions.eq("statusAction", "Cancelled"));
				}
				 getDayConjunction(type,serviceType,conjunction);
			
			}
			criteria.add(conjunction);
			count= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------" + e.getMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return count;
	}
	
	
	
	public Conjunction getDayConjunction(String type,String serviceType,Conjunction conjunction){
			Date todayDate = new Date();
			String date=DateConversion.convertDateToStringToDate(todayDate);
			todayDate=DateConversion.StringToDate(date);
			conjunction.add(Restrictions.ge("createdAt", todayDate));
			if(type.equalsIgnoreCase("BVC")){
				conjunction.add(Restrictions.ge("updatedAt", todayDate));
			}
		return conjunction;
}*/
	
	
}
