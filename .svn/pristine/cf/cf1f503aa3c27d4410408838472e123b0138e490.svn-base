package com.admin.dashboardsearch.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.admin.dashboardsearch.VO.DashBoardSearchCommonVirtualObject;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.common.dsr.CommonDsrDao;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.common.model.FlightAndHotelBookApiResponse;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.FlightOrderRowMarkup;
import com.lintas.admin.model.HotelOrderRowMarkup;
import com.lintas.admin.model.User;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

public class DashBoardSearchDao implements Serializable{

	/**
	 * @author Basha  created at 05-08-2017
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DashBoardSearchDao.class);


	public	List<FlightOrderRow> getFlightOrderRowDetailList(String searchParam,Company company){
		List<FlightOrderRow> list = null;
		Session session=null;
		List<String>  companyIdList = getCompanyIdListByParentCompany(company);
		 
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(FlightOrderRow.class);
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("orderId", searchParam,MatchMode.ANYWHERE));
			disjunction.add(Restrictions.like("invoiceNo", searchParam,MatchMode.ANYWHERE));
			disjunction.add(Restrictions.like("pnr", searchParam,MatchMode.ANYWHERE));
			criteria.add(Restrictions.in("companyId", companyIdList));
			criteria.add(disjunction);
			list = criteria.list();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  list;
	}



	public List<HotelOrderRow> getHotelOrderRowByIdList(String searchParam,Company company) {
		// TODO Auto-generated method stub
		List<HotelOrderRow> list = null;
		List<String>  companyIdList = getCompanyIdListByParentCompany(company);
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelOrderRow.class);
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("orderReference", searchParam,MatchMode.ANYWHERE));
			disjunction.add(Restrictions.like("invoiceNo", searchParam,MatchMode.ANYWHERE));
			disjunction.add(Restrictions.like("confirmationNo", searchParam,MatchMode.ANYWHERE));
			criteria.add(Restrictions.in("companyId", companyIdList));
			criteria.add(disjunction);
			list = criteria.list();
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

	public List<CarOrderRow> getCarOrderRowDetailList(String searchParam,Company company) {
		List<CarOrderRow> list = null;
		List<String>  companyIdList = getCompanyIdListByParentCompany(company);
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(CarOrderRow.class);
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("orderId", searchParam,MatchMode.ANYWHERE));
			disjunction.add(Restrictions.like("invoiceNo", searchParam,MatchMode.ANYWHERE));
			disjunction.add(Restrictions.like("confirmationNumber", searchParam,MatchMode.ANYWHERE));
			criteria.add(Restrictions.in("companyId", companyIdList));
			criteria.add(disjunction);
			list = criteria.list();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;

	}

	public List<BusOrderRow> getBusOrderRowDetailList(String searchParam,Company company) {
		List<BusOrderRow> list = null;
		List<String>  companyIdList = getCompanyIdListByParentCompany(company);
		Session session=null;
		try{

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(BusOrderRow.class);
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("orderId", searchParam,MatchMode.ANYWHERE));
			disjunction.add(Restrictions.like("invoiceNo", searchParam,MatchMode.ANYWHERE));
			disjunction.add(Restrictions.like("confirmationNumber", searchParam,MatchMode.ANYWHERE));
			criteria.add(Restrictions.in("companyId", companyIdList));
			criteria.add(disjunction);
			list = criteria.list();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;

	}
	public List<TrainOrderRow> getTrainOrderRowDetailList(String searchParam,Company company) {
		/*this method for get  FlightOrderRow  using order id */
		List<TrainOrderRow> list = null;
		List<String>  companyIdList = getCompanyIdListByParentCompany(company);
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(TrainOrderRow.class);
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("orderId", searchParam,MatchMode.ANYWHERE));
			disjunction.add(Restrictions.like("invoiceNo", searchParam,MatchMode.ANYWHERE));
			disjunction.add(Restrictions.like("confirmationNumber", searchParam,MatchMode.ANYWHERE));
			criteria.add(Restrictions.in("companyId", companyIdList));
			criteria.add(disjunction);
			list = criteria.list();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  list;
	}

	public List<VisaOrderRow> getVisaOrderRowDetailList(String searchParam,Company company) {
		/*this method for get  FlightOrderRow  using order id */
		List<VisaOrderRow> list = null;
		List<String>  companyIdList = getCompanyIdListByParentCompany(company);
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(VisaOrderRow.class);
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("orderId", searchParam,MatchMode.ANYWHERE));
			disjunction.add(Restrictions.like("invoiceNo", searchParam,MatchMode.ANYWHERE));
			disjunction.add(Restrictions.like("confirmationNumber", searchParam,MatchMode.ANYWHERE));
			criteria.add(Restrictions.in("companyId", companyIdList));
			criteria.add(disjunction);
			list = criteria.list();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;
	}
	public List<InsuranceOrderRow> getInsuranceOrderRowDetailList(String searchParam,Company company) {
		// TODO Auto-generated method stub
		List<InsuranceOrderRow> list = null;
		List<String>  companyIdList = getCompanyIdListByParentCompany(company);
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(InsuranceOrderRow.class);
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("orderId", searchParam,MatchMode.ANYWHERE));
			disjunction.add(Restrictions.like("invoiceNo", searchParam,MatchMode.ANYWHERE));
			disjunction.add(Restrictions.like("confirmationNumber", searchParam,MatchMode.ANYWHERE));
			criteria.add(Restrictions.in("companyId", companyIdList));
			criteria.add(disjunction);
			list = criteria.list();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;

	}

	public List<MiscellaneousOrderRow> getMiscellaneousOrderRowDetailList(String searchParam,Company company) {
		/*this method for get  FlightOrderRow  using order id */
		List<MiscellaneousOrderRow> list = null;
		List<Integer>  companyIdListInInteger =new ArrayList<>();
		List<String>  companyIdList = getCompanyIdListByParentCompany(company);
		if(companyIdList!=null){
			for(String companyId:companyIdList){
				companyIdListInInteger.add(Integer.parseInt(companyId));
			}
		}
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(MiscellaneousOrderRow.class);
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("orderId", searchParam,MatchMode.ANYWHERE));
			disjunction.add(Restrictions.like("invoiceNo", searchParam,MatchMode.ANYWHERE));
			disjunction.add(Restrictions.like("confirmationNumber", searchParam,MatchMode.ANYWHERE));
			criteria.add(Restrictions.in("companyId", companyIdListInInteger));
			criteria.add(disjunction);
			list = criteria.list();
		}catch (HibernateException e) {
			e.getMessage();
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;
	}



	public DashBoardSearchCommonVirtualObject generateFlightRequiredData(FlightOrderRow flightOrderRow,Company companySessionObj) {

		DashBoardSearchCommonVirtualObject dashBoardSearchCommonVirtualObject=new DashBoardSearchCommonVirtualObject();
		FlightOrderDao  flightOrderDao=new FlightOrderDao();
		dashBoardSearchCommonVirtualObject.setServicetype("Flight");
		dashBoardSearchCommonVirtualObject.setId(flightOrderRow.getId());
		dashBoardSearchCommonVirtualObject.setUserId(flightOrderRow.getUserId());
		dashBoardSearchCommonVirtualObject.setCheckInDate("-");
		dashBoardSearchCommonVirtualObject.setCheckOutDate("-");
		dashBoardSearchCommonVirtualObject.setConfirmationNo("-");
		dashBoardSearchCommonVirtualObject.setDescription("-");
		dashBoardSearchCommonVirtualObject.setFilterCompanyType("-");
		dashBoardSearchCommonVirtualObject.setHotelName("-");
		dashBoardSearchCommonVirtualObject.setStatusAction(flightOrderRow.getStatusAction());
		dashBoardSearchCommonVirtualObject.setInvoiceDate(flightOrderRow.getCreatedAt().toString());
		dashBoardSearchCommonVirtualObject.setInvoiceNo(flightOrderRow.getInvoiceNo());
		dashBoardSearchCommonVirtualObject.setOrderRef("-");
		dashBoardSearchCommonVirtualObject.setServiceTax(flightOrderRow.getServiceTax()!=null?flightOrderRow.getServiceTax():new BigDecimal(0));
		dashBoardSearchCommonVirtualObject.setSupplierPrice(flightOrderRow.getSupplierPrice()!=null?flightOrderRow.getSupplierPrice():new BigDecimal(0));
		dashBoardSearchCommonVirtualObject.setTicketType("-");
		dashBoardSearchCommonVirtualObject.setTravelDate("-");
		dashBoardSearchCommonVirtualObject.setCreatedAt(flightOrderRow.getCreatedAt());
		dashBoardSearchCommonVirtualObject.setOrderId(flightOrderRow.getOrderId());
		dashBoardSearchCommonVirtualObject.setPnr(flightOrderRow.getPnr());
		dashBoardSearchCommonVirtualObject.setBookingDate(DateConversion.getBluestarDate(flightOrderRow.getBookingDate()));
		dashBoardSearchCommonVirtualObject.setDepartureDate(DateConversion.getBluestarDate(flightOrderRow.getDepartureDate()));
		dashBoardSearchCommonVirtualObject.setAirline(flightOrderRow.getAirline());
		dashBoardSearchCommonVirtualObject.setStatus(flightOrderRow.getStatusAction());
		dashBoardSearchCommonVirtualObject.setRoute(flightOrderRow.getOrigin()+"-"+flightOrderRow.getDestination());
		dashBoardSearchCommonVirtualObject.setBasePrice(flightOrderRow.getPrice());
		dashBoardSearchCommonVirtualObject.setCurCode(flightOrderRow.getBookingCurrency());
		BigDecimal finalPrice = flightOrderRow.getFinalPrice() != null?flightOrderRow.getFinalPrice().setScale(2, RoundingMode.UP):new BigDecimal(0);
		if(flightOrderRow.getFlightOrderRowServiceTax()!=null){
			dashBoardSearchCommonVirtualObject.setTotal(finalPrice.add(flightOrderDao.calculateTotalserviceTax(flightOrderRow)).setScale(2, BigDecimal.ROUND_UP));
			dashBoardSearchCommonVirtualObject.setFinalPrice(finalPrice);
		}
		else if (flightOrderRow.getFlightOrderRowGstTax()!=null) {
			if(companySessionObj.getCompanyRole().isAgent() || companySessionObj.getCompanyRole().isDistributor() || companySessionObj.getCompanyRole().isSuperUser() ){
				dashBoardSearchCommonVirtualObject.setFinalPrice(finalPrice.setScale(2, RoundingMode.UP));
			}
			else if (companySessionObj.getCompanyRole().isCorporate()) {
				dashBoardSearchCommonVirtualObject.setFinalPrice(finalPrice.add(flightOrderDao.calculateTotalGSTTax(flightOrderRow)).setScale(2, BigDecimal.ROUND_UP));
			}

		}else
			dashBoardSearchCommonVirtualObject.setFinalPrice(finalPrice);

		dashBoardSearchCommonVirtualObject.setCreatedBy(flightOrderRow.getCreatedBy());
		dashBoardSearchCommonVirtualObject.setCompanyId(flightOrderRow.getCompanyId());
		dashBoardSearchCommonVirtualObject.setOrderRequested(flightOrderRow.isOrderRequested());
		dashBoardSearchCommonVirtualObject.setOrderUpdated(flightOrderRow.isOrderUpdated());
		dashBoardSearchCommonVirtualObject.setCreditNoteIssued(flightOrderRow.isCreditNoteIssued());
		dashBoardSearchCommonVirtualObject.setFirstName(flightOrderRow.getFlightCustomer().getFirstName());
		dashBoardSearchCommonVirtualObject.setLastName(flightOrderRow.getFlightCustomer().getLastName());
		dashBoardSearchCommonVirtualObject.setTitle(flightOrderRow.getFlightCustomer().getTitle());
		dashBoardSearchCommonVirtualObject.setOrigin(flightOrderRow.getOrigin());
		dashBoardSearchCommonVirtualObject.setDestination(flightOrderRow.getDestination());
		FlightOrderRowMarkup flightOrderRowMarkup= flightOrderDao.getCompanyMarkup(flightOrderRow.getCompanyId(), flightOrderRow.getId());
		if(flightOrderRowMarkup!=null) 
			dashBoardSearchCommonVirtualObject.setMarkUp(flightOrderRowMarkup.getMarkUp()!=null?flightOrderRowMarkup.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00").multiply(new BigDecimal(flightOrderRow.getPassengerCount())).setScale(2, BigDecimal.ROUND_UP));
		else 
			dashBoardSearchCommonVirtualObject.setMarkUp(new BigDecimal("0.00"));	
		BigDecimal netPaybleAmount=dashBoardSearchCommonVirtualObject.getFinalPrice().subtract(dashBoardSearchCommonVirtualObject.getMarkUp());
		dashBoardSearchCommonVirtualObject.setNetAmnt(netPaybleAmount.setScale(2,BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setPaymentStatus(flightOrderRow.getPaymentStatus());
		User user=new User();
		if(flightOrderRow.getUserId()!=null && !flightOrderRow.getUserId().trim().equalsIgnoreCase("")){
			user=flightOrderDao.getSalesPersonName(flightOrderRow.getUserId());
			if(user!=null && user.getUsername()!=null) 
				dashBoardSearchCommonVirtualObject.setSalesPersonName(user.getUsername());
			else 
				dashBoardSearchCommonVirtualObject.setSalesPersonName("NA");
		}else
			dashBoardSearchCommonVirtualObject.setSalesPersonName("NA");
		FlightAndHotelBookApiResponse flightAndHotelBookApiResponse = flightOrderDao.getApiStatusMessage(flightOrderRow.getId());
		if(flightAndHotelBookApiResponse!=null)
			dashBoardSearchCommonVirtualObject.setApiResponseMessage(flightAndHotelBookApiResponse.getApiStatusMessage());
		dashBoardSearchCommonVirtualObject.setApiProvider(flightOrderRow.getProviderAPI());

		return dashBoardSearchCommonVirtualObject;
	}



	public DashBoardSearchCommonVirtualObject generateHotelRequiredData(HotelOrderRow hotelOrderRow,Company companySessionObj) {
		DashBoardSearchCommonVirtualObject dashBoardSearchCommonVirtualObject=new DashBoardSearchCommonVirtualObject();
		HotelOrderDao  hotelOrderDao=new HotelOrderDao();
		FlightOrderDao  flightOrderDaonew=new FlightOrderDao();
		dashBoardSearchCommonVirtualObject.setServicetype("Hotel");
		dashBoardSearchCommonVirtualObject.setId(hotelOrderRow.getId());
		dashBoardSearchCommonVirtualObject.setUserId(hotelOrderRow.getUserId());
		dashBoardSearchCommonVirtualObject.setCheckInDate(DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckInDate()));
		dashBoardSearchCommonVirtualObject.setCheckOutDate(DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckOutDate()));
		dashBoardSearchCommonVirtualObject.setConfirmationNo(hotelOrderRow.getConfirmationNo());
		dashBoardSearchCommonVirtualObject.setDescription("-");
		dashBoardSearchCommonVirtualObject.setFilterCompanyType("-");
		dashBoardSearchCommonVirtualObject.setHotelName(hotelOrderRow.getHotelOrderHotelData().getName());
		dashBoardSearchCommonVirtualObject.setStatusAction(hotelOrderRow.getStatusAction());
		dashBoardSearchCommonVirtualObject.setInvoiceDate(DateConversion.convertTimestampToString(hotelOrderRow.getCreatedAt()));
		dashBoardSearchCommonVirtualObject.setInvoiceNo(hotelOrderRow.getInvoiceNo());
		dashBoardSearchCommonVirtualObject.setOrderRef(hotelOrderRow.getOrderReference());
		dashBoardSearchCommonVirtualObject.setServiceTax(hotelOrderRow.getServiceTax()!=null?hotelOrderRow.getServiceTax():new BigDecimal(0));
		dashBoardSearchCommonVirtualObject.setSupplierPrice(hotelOrderRow.getApiPrice()!=null?hotelOrderRow.getApiPrice():new BigDecimal(0));
		dashBoardSearchCommonVirtualObject.setTicketType("-");
		dashBoardSearchCommonVirtualObject.setTravelDate("-");
		dashBoardSearchCommonVirtualObject.setCreatedAt(hotelOrderRow.getCreatedAt());
		dashBoardSearchCommonVirtualObject.setPnr("");
		dashBoardSearchCommonVirtualObject.setBookingDate(DateConversion.getBluestarDate(hotelOrderRow.getBookingDate()));
		dashBoardSearchCommonVirtualObject.setDepartureDate("-");
		dashBoardSearchCommonVirtualObject.setAirline("-");
		dashBoardSearchCommonVirtualObject.setStatus(hotelOrderRow.getStatusAction());
		dashBoardSearchCommonVirtualObject.setRoute("-");
		dashBoardSearchCommonVirtualObject.setBasePrice(hotelOrderRow.getFinalPrice().setScale(2, BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setCurCode(hotelOrderRow.getBookingCurrency());
		BigDecimal finalPrice = hotelOrderRow.getFinalPrice()!= null?hotelOrderRow.getFinalPrice().setScale(2, RoundingMode.UP):new BigDecimal(0);
		if(hotelOrderRow.getHotelOrderRowServiceTax()!=null){
			dashBoardSearchCommonVirtualObject.setFinalPrice(finalPrice.add(hotelOrderDao.calculateTotalserviceTax(hotelOrderRow)).setScale(2, BigDecimal.ROUND_UP));
		}
		else if (hotelOrderRow.getHotelOrderRowGstTax()!=null) {
			if(companySessionObj.getCompanyRole().isAgent() || companySessionObj.getCompanyRole().isDistributor() || companySessionObj.getCompanyRole().isSuperUser() ){
				dashBoardSearchCommonVirtualObject.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));
			}
			else if (companySessionObj.getCompanyRole().isCorporate()) {
				dashBoardSearchCommonVirtualObject.setFinalPrice(finalPrice.add(hotelOrderDao.calculateTotalGSTTax(hotelOrderRow)).setScale(2, BigDecimal.ROUND_UP));
			}

		}
		else 
			dashBoardSearchCommonVirtualObject.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));

		if(hotelOrderRow.getCreatedBy()!=null){
			dashBoardSearchCommonVirtualObject.setCreatedBy(hotelOrderRow.getCreatedBy().replace("+", " "));	
		}
		else{
			dashBoardSearchCommonVirtualObject.setCreatedBy(hotelOrderRow.getCreatedBy());	
		}

		dashBoardSearchCommonVirtualObject.setOrderId(hotelOrderRow.getOrderReference());
		dashBoardSearchCommonVirtualObject.setCompanyId(hotelOrderRow.getCompanyId());
		dashBoardSearchCommonVirtualObject.setOrderRequested(hotelOrderRow.isOrderRequested());
		dashBoardSearchCommonVirtualObject.setOrderUpdated(hotelOrderRow.isOrderUpdated());
		dashBoardSearchCommonVirtualObject.setCreditNoteIssued(hotelOrderRow.isCreditNoteIssued());
		dashBoardSearchCommonVirtualObject.setFirstName(hotelOrderRow.getOrderCustomer().getFirstName());
		dashBoardSearchCommonVirtualObject.setLastName(hotelOrderRow.getOrderCustomer().getLastName());
		dashBoardSearchCommonVirtualObject.setTitle(hotelOrderRow.getOrderCustomer().getTitle());
		dashBoardSearchCommonVirtualObject.setOrigin("-");
		dashBoardSearchCommonVirtualObject.setDestination("-");
		BigDecimal netPaybleAmount=new BigDecimal(0);
		HotelOrderRowMarkup hotelOrderRowMarkup= hotelOrderDao.getCompanyMarkup(hotelOrderRow.getCompanyId(), hotelOrderRow.getId());
		if(hotelOrderRowMarkup!=null) 
			dashBoardSearchCommonVirtualObject.setMarkUp(hotelOrderRowMarkup.getMarkUp()!=null?hotelOrderRowMarkup.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00").multiply(new BigDecimal(hotelOrderRow.getNoOfRooms())).setScale(2, BigDecimal.ROUND_UP));
		else 
			dashBoardSearchCommonVirtualObject.setMarkUp(new BigDecimal("0.00"));	
		if(dashBoardSearchCommonVirtualObject.getMarkUp()!=null)
			netPaybleAmount=dashBoardSearchCommonVirtualObject.getFinalPrice().subtract(dashBoardSearchCommonVirtualObject.getMarkUp());
		else
			netPaybleAmount=dashBoardSearchCommonVirtualObject.getTotal().subtract(new BigDecimal(0));


		dashBoardSearchCommonVirtualObject.setNetAmnt(netPaybleAmount.setScale(2,BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setPaymentStatus(hotelOrderRow.getPaymentStatus());
		User user=new User();
		if(hotelOrderRow.getUserId()!=null && !hotelOrderRow.getUserId().trim().equalsIgnoreCase("")){
			user=flightOrderDaonew.getSalesPersonName(hotelOrderRow.getUserId());
			if(user!=null && user.getUsername()!=null) 
				dashBoardSearchCommonVirtualObject.setSalesPersonName(user.getUsername());
			else 
				dashBoardSearchCommonVirtualObject.setSalesPersonName("NA");
		}else
			dashBoardSearchCommonVirtualObject.setSalesPersonName("NA");
		FlightAndHotelBookApiResponse flightAndHotelBookApiResponse = hotelOrderDao.getApiStatusMessage(hotelOrderRow.getId());
		if(flightAndHotelBookApiResponse!=null)
			dashBoardSearchCommonVirtualObject.setApiResponseMessage(flightAndHotelBookApiResponse.getApiStatusMessage());

		dashBoardSearchCommonVirtualObject.setApiProvider(hotelOrderRow.getApiProvoder()!=null && !hotelOrderRow.getApiProvoder().equals("")?hotelOrderRow.getApiProvoder():"NA");

		return dashBoardSearchCommonVirtualObject;
	}



	public DashBoardSearchCommonVirtualObject generateCarRequiredData(CarOrderRow carOrderRow,Company sessionCompany) {
		DashBoardSearchCommonVirtualObject dashBoardSearchCommonVirtualObject=new DashBoardSearchCommonVirtualObject();
		FlightOrderDao  flightOrderDao=new FlightOrderDao();
		dashBoardSearchCommonVirtualObject.setServicetype("Car");
		dashBoardSearchCommonVirtualObject.setId(carOrderRow.getId());
		dashBoardSearchCommonVirtualObject.setUserId(carOrderRow.getUserId());
		dashBoardSearchCommonVirtualObject.setCheckInDate("NA");
		dashBoardSearchCommonVirtualObject.setCheckOutDate("NA");
		dashBoardSearchCommonVirtualObject.setConfirmationNo(carOrderRow.getConfirmationNumber());
		dashBoardSearchCommonVirtualObject.setDescription(carOrderRow.getRemarks());
		dashBoardSearchCommonVirtualObject.setFilterCompanyType("-");
		dashBoardSearchCommonVirtualObject.setHotelName("-");
		dashBoardSearchCommonVirtualObject.setStatusAction(carOrderRow.getStatusAction());
		dashBoardSearchCommonVirtualObject.setInvoiceDate(DateConversion.convertTimestampToString(carOrderRow.getCreatedAt()));
		dashBoardSearchCommonVirtualObject.setInvoiceNo(carOrderRow.getInvoiceNo());
		dashBoardSearchCommonVirtualObject.setOrderRef("-");
		dashBoardSearchCommonVirtualObject.setSupplierPrice(carOrderRow.getSupplierPrice()!=null?carOrderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
		dashBoardSearchCommonVirtualObject.setTicketType("-");
		dashBoardSearchCommonVirtualObject.setTravelDate(DateConversion.convertDateToStringToDate(carOrderRow.getTravelDate()));
		dashBoardSearchCommonVirtualObject.setCreatedAt(carOrderRow.getCreatedAt());
		dashBoardSearchCommonVirtualObject.setPnr("-");
		dashBoardSearchCommonVirtualObject.setBookingDate(DateConversion.convertDateToStringDate(carOrderRow.getBookingDate()));
		dashBoardSearchCommonVirtualObject.setDepartureDate(DateConversion.convertDateToStringToDate(carOrderRow.getTravelDate()));
		dashBoardSearchCommonVirtualObject.setAirline("-");
		dashBoardSearchCommonVirtualObject.setStatus(carOrderRow.getStatusAction());
		dashBoardSearchCommonVirtualObject.setRoute("-");
		dashBoardSearchCommonVirtualObject.setBasePrice(carOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setCurCode(carOrderRow.getBookingCurrency());
		dashBoardSearchCommonVirtualObject.setTotal(carOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
		BigDecimal gstorServiceTax=new BigDecimal(0);
		if(carOrderRow.getCarOrderRowServiceTax()!=null) 
			gstorServiceTax=carOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP);
		if (carOrderRow.getCarOrderRowGstTax()!=null)  
			gstorServiceTax=carOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP);
		BigDecimal finalPrice=carOrderRow.getTotalAmount().add(gstorServiceTax).setScale(2, BigDecimal.ROUND_UP);
		dashBoardSearchCommonVirtualObject.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));
		if(carOrderRow.getCreatedBy()!=null)
			dashBoardSearchCommonVirtualObject.setCreatedBy(carOrderRow.getCreatedBy().replace("+", " "));	
		else
			dashBoardSearchCommonVirtualObject.setCreatedBy(carOrderRow.getCreatedBy());	
		dashBoardSearchCommonVirtualObject.setOrderId(carOrderRow.getOrderId());
		dashBoardSearchCommonVirtualObject.setServiceTax(gstorServiceTax.setScale(2, BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setCompanyId(carOrderRow.getCompanyId());
		dashBoardSearchCommonVirtualObject.setOrderRequested(carOrderRow.isOrderRequested());
		dashBoardSearchCommonVirtualObject.setOrderUpdated(carOrderRow.isOrderUpdated());
		dashBoardSearchCommonVirtualObject.setCreditNoteIssued(carOrderRow.isCreditNoteIssued());
		dashBoardSearchCommonVirtualObject.setFirstName(carOrderRow.getOrderCustomer().getFirstName());
		dashBoardSearchCommonVirtualObject.setLastName(carOrderRow.getOrderCustomer().getLastName());
		dashBoardSearchCommonVirtualObject.setTitle(carOrderRow.getOrderCustomer().getTitle());
		dashBoardSearchCommonVirtualObject.setOrigin("-");
		dashBoardSearchCommonVirtualObject.setDestination("-");
		dashBoardSearchCommonVirtualObject.setMarkUp(carOrderRow.getMarkUp()!=null?carOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
		BigDecimal netPaybleAmount=(carOrderRow.getTotalAmount().subtract(carOrderRow.getMarkUp()).setScale(2, BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setNetAmnt(netPaybleAmount.setScale(2,BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setPaymentStatus(carOrderRow.getPaymentStatus());
		User user=new User();
		if(carOrderRow.getUserId()!=null && !carOrderRow.getUserId().trim().equalsIgnoreCase("")){
			user=flightOrderDao.getSalesPersonName(carOrderRow.getUserId());
			if(user!=null && user.getUsername()!=null) 
				dashBoardSearchCommonVirtualObject.setSalesPersonName(user.getUsername());
			else 
				dashBoardSearchCommonVirtualObject.setSalesPersonName("NA");
		}else
			dashBoardSearchCommonVirtualObject.setSalesPersonName("NA");
		FlightAndHotelBookApiResponse flightAndHotelBookApiResponse = new FlightAndHotelBookApiResponse();   // getApiStatusMessage(flightOrderRow.getId());
		if(flightAndHotelBookApiResponse!=null)
			dashBoardSearchCommonVirtualObject.setApiResponseMessage(flightAndHotelBookApiResponse.getApiStatusMessage());

		dashBoardSearchCommonVirtualObject.setApiProvider(carOrderRow.getSupplierName()!=null && !carOrderRow.getSupplierName().equals("")?carOrderRow.getSupplierName():"NA");

		return dashBoardSearchCommonVirtualObject;
	}



	public DashBoardSearchCommonVirtualObject generateBusRequiredData(BusOrderRow busOrderRow,Company sessionCompany) {
		DashBoardSearchCommonVirtualObject dashBoardSearchCommonVirtualObject=new DashBoardSearchCommonVirtualObject();
		FlightOrderDao  flightOrderDao=new FlightOrderDao();
		dashBoardSearchCommonVirtualObject.setServicetype("Bus");
		dashBoardSearchCommonVirtualObject.setId(busOrderRow.getId());
		dashBoardSearchCommonVirtualObject.setUserId(busOrderRow.getUserId());
		dashBoardSearchCommonVirtualObject.setCheckInDate("NA");
		dashBoardSearchCommonVirtualObject.setCheckOutDate("NA");
		dashBoardSearchCommonVirtualObject.setConfirmationNo(busOrderRow.getConfirmationNumber());
		dashBoardSearchCommonVirtualObject.setDescription(busOrderRow.getRemarks());
		dashBoardSearchCommonVirtualObject.setFilterCompanyType("-");
		dashBoardSearchCommonVirtualObject.setHotelName("-");
		dashBoardSearchCommonVirtualObject.setStatusAction(busOrderRow.getStatusAction());
		dashBoardSearchCommonVirtualObject.setInvoiceDate(DateConversion.convertTimestampToString(busOrderRow.getCreatedAt()));
		dashBoardSearchCommonVirtualObject.setInvoiceNo(busOrderRow.getInvoiceNo());
		dashBoardSearchCommonVirtualObject.setOrderRef("-");
		dashBoardSearchCommonVirtualObject.setSupplierPrice(busOrderRow.getSupplierPrice()!=null?busOrderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
		dashBoardSearchCommonVirtualObject.setTicketType("-");
		dashBoardSearchCommonVirtualObject.setTravelDate(DateConversion.convertDateToStringToDate(busOrderRow.getTravelDate()));
		dashBoardSearchCommonVirtualObject.setCreatedAt(busOrderRow.getCreatedAt());
		dashBoardSearchCommonVirtualObject.setPnr("-");
		dashBoardSearchCommonVirtualObject.setBookingDate(DateConversion.convertDateToStringDate(busOrderRow.getBookingDate()));
		dashBoardSearchCommonVirtualObject.setDepartureDate(DateConversion.convertDateToStringToDate(busOrderRow.getTravelDate()));
		dashBoardSearchCommonVirtualObject.setAirline("-");
		dashBoardSearchCommonVirtualObject.setStatus(busOrderRow.getStatusAction());
		dashBoardSearchCommonVirtualObject.setRoute("-");
		dashBoardSearchCommonVirtualObject.setBasePrice(busOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setCurCode(busOrderRow.getBookingCurrency());
		dashBoardSearchCommonVirtualObject.setTotal(busOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
		BigDecimal gstorServiceTax=new BigDecimal(0);
		if(busOrderRow.getBusOrderRowServiceTax()!=null) 
			gstorServiceTax=busOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP);
		if (busOrderRow.getBusOrderRowGstTax()!=null)  
			gstorServiceTax=busOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP);
		BigDecimal finalPrice=busOrderRow.getTotalAmount().add(gstorServiceTax).setScale(2, BigDecimal.ROUND_UP);
		dashBoardSearchCommonVirtualObject.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));
		if(busOrderRow.getCreatedBy()!=null)
			dashBoardSearchCommonVirtualObject.setCreatedBy(busOrderRow.getCreatedBy().replace("+", " "));	
		else
			dashBoardSearchCommonVirtualObject.setCreatedBy(busOrderRow.getCreatedBy());	
		dashBoardSearchCommonVirtualObject.setOrderId(busOrderRow.getOrderId());
		dashBoardSearchCommonVirtualObject.setServiceTax(gstorServiceTax.setScale(2, BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setCompanyId(busOrderRow.getCompanyId());
		dashBoardSearchCommonVirtualObject.setOrderRequested(busOrderRow.isOrderRequested());
		dashBoardSearchCommonVirtualObject.setOrderUpdated(busOrderRow.isOrderUpdated());
		dashBoardSearchCommonVirtualObject.setCreditNoteIssued(busOrderRow.isCreditNoteIssued());
		dashBoardSearchCommonVirtualObject.setFirstName(busOrderRow.getOrderCustomer().getFirstName());
		dashBoardSearchCommonVirtualObject.setLastName(busOrderRow.getOrderCustomer().getLastName());
		dashBoardSearchCommonVirtualObject.setTitle(busOrderRow.getOrderCustomer().getTitle());
		dashBoardSearchCommonVirtualObject.setOrigin("-");
		dashBoardSearchCommonVirtualObject.setDestination("-");
		dashBoardSearchCommonVirtualObject.setMarkUp(busOrderRow.getMarkUp()!=null?busOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
		BigDecimal netPaybleAmount=(busOrderRow.getTotalAmount().subtract(busOrderRow.getMarkUp()).setScale(2, BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setNetAmnt(netPaybleAmount.setScale(2,BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setPaymentStatus(busOrderRow.getPaymentStatus());
		User user=new User();
		if(busOrderRow.getUserId()!=null && !busOrderRow.getUserId().trim().equalsIgnoreCase("")){
			user=flightOrderDao.getSalesPersonName(busOrderRow.getUserId());
			if(user!=null && user.getUsername()!=null) 
				dashBoardSearchCommonVirtualObject.setSalesPersonName(user.getUsername());
			else 
				dashBoardSearchCommonVirtualObject.setSalesPersonName("NA");
		}else
			dashBoardSearchCommonVirtualObject.setSalesPersonName("NA");

		FlightAndHotelBookApiResponse flightAndHotelBookApiResponse = new FlightAndHotelBookApiResponse();   // getApiStatusMessage(flightOrderRow.getId());
		if(flightAndHotelBookApiResponse!=null)
			dashBoardSearchCommonVirtualObject.setApiResponseMessage(flightAndHotelBookApiResponse.getApiStatusMessage());

		dashBoardSearchCommonVirtualObject.setApiProvider(busOrderRow.getSupplierName()!=null && !busOrderRow.getSupplierName().equals("")?busOrderRow.getSupplierName():"NA");

		return dashBoardSearchCommonVirtualObject;
	}



	public DashBoardSearchCommonVirtualObject generateTrainRequiredData(TrainOrderRow trainOrderRow,Company sessionCompany) {
		DashBoardSearchCommonVirtualObject dashBoardSearchCommonVirtualObject=new DashBoardSearchCommonVirtualObject();
		FlightOrderDao  flightOrderDao=new FlightOrderDao();
		dashBoardSearchCommonVirtualObject.setServicetype("Train");
		dashBoardSearchCommonVirtualObject.setId(trainOrderRow.getId());
		dashBoardSearchCommonVirtualObject.setUserId(trainOrderRow.getUserId());
		dashBoardSearchCommonVirtualObject.setCheckInDate("NA");
		dashBoardSearchCommonVirtualObject.setCheckOutDate("NA");
		dashBoardSearchCommonVirtualObject.setConfirmationNo(trainOrderRow.getConfirmationNumber());
		dashBoardSearchCommonVirtualObject.setDescription(trainOrderRow.getRemarks());
		dashBoardSearchCommonVirtualObject.setFilterCompanyType("-");
		dashBoardSearchCommonVirtualObject.setHotelName("-");
		dashBoardSearchCommonVirtualObject.setStatusAction(trainOrderRow.getStatusAction());
		dashBoardSearchCommonVirtualObject.setInvoiceDate(DateConversion.convertTimestampToString(trainOrderRow.getCreatedAt()));
		dashBoardSearchCommonVirtualObject.setInvoiceNo(trainOrderRow.getInvoiceNo());
		dashBoardSearchCommonVirtualObject.setOrderRef("-");
		dashBoardSearchCommonVirtualObject.setSupplierPrice(trainOrderRow.getSupplierPrice()!=null?trainOrderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
		dashBoardSearchCommonVirtualObject.setTicketType(trainOrderRow.getTicketType());
		dashBoardSearchCommonVirtualObject.setTravelDate(DateConversion.convertDateToStringToDate(trainOrderRow.getTravelDate()));
		dashBoardSearchCommonVirtualObject.setCreatedAt(trainOrderRow.getCreatedAt());
		dashBoardSearchCommonVirtualObject.setPnr("-");
		dashBoardSearchCommonVirtualObject.setBookingDate(DateConversion.convertDateToStringDate(trainOrderRow.getBookingDate()));
		dashBoardSearchCommonVirtualObject.setDepartureDate(DateConversion.convertDateToStringToDate(trainOrderRow.getTravelDate()));
		dashBoardSearchCommonVirtualObject.setAirline("-");
		dashBoardSearchCommonVirtualObject.setStatus(trainOrderRow.getStatusAction());
		dashBoardSearchCommonVirtualObject.setRoute("-");
		dashBoardSearchCommonVirtualObject.setBasePrice(trainOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setCurCode(trainOrderRow.getBookingCurrency());
		dashBoardSearchCommonVirtualObject.setTotal(trainOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
		BigDecimal gstorServiceTax=new BigDecimal(0);
		if(trainOrderRow.getTrainOrderRowServiceTax()!=null) 
			gstorServiceTax=trainOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP);
		if (trainOrderRow.getTrainOrderRowGstTax()!=null)  
			gstorServiceTax=trainOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP);
		BigDecimal finalPrice=trainOrderRow.getTotalAmount().add(gstorServiceTax).setScale(2, BigDecimal.ROUND_UP);
		dashBoardSearchCommonVirtualObject.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));
		if(trainOrderRow.getCreatedBy()!=null)
			dashBoardSearchCommonVirtualObject.setCreatedBy(trainOrderRow.getCreatedBy().replace("+", " "));	
		else
			dashBoardSearchCommonVirtualObject.setCreatedBy(trainOrderRow.getCreatedBy());	
		dashBoardSearchCommonVirtualObject.setOrderId(trainOrderRow.getOrderId());
		dashBoardSearchCommonVirtualObject.setServiceTax(gstorServiceTax.setScale(2, BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setCompanyId(trainOrderRow.getCompanyId());
		dashBoardSearchCommonVirtualObject.setOrderRequested(trainOrderRow.isOrderRequested());
		dashBoardSearchCommonVirtualObject.setOrderUpdated(trainOrderRow.isOrderUpdated());
		dashBoardSearchCommonVirtualObject.setCreditNoteIssued(trainOrderRow.isCreditNoteIssued());
		dashBoardSearchCommonVirtualObject.setFirstName(trainOrderRow.getOrderCustomer().getFirstName());
		dashBoardSearchCommonVirtualObject.setLastName(trainOrderRow.getOrderCustomer().getLastName());
		dashBoardSearchCommonVirtualObject.setTitle(trainOrderRow.getOrderCustomer().getTitle());
		dashBoardSearchCommonVirtualObject.setOrigin("-");
		dashBoardSearchCommonVirtualObject.setDestination("-");
		dashBoardSearchCommonVirtualObject.setMarkUp(trainOrderRow.getMarkUp()!=null?trainOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
		BigDecimal netPaybleAmount=(trainOrderRow.getTotalAmount().subtract(trainOrderRow.getMarkUp()).setScale(2, BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setNetAmnt(netPaybleAmount.setScale(2,BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setPaymentStatus(trainOrderRow.getPaymentStatus());
		User user=new User();
		if(trainOrderRow.getUserId()!=null && !trainOrderRow.getUserId().trim().equalsIgnoreCase("")){
			user=flightOrderDao.getSalesPersonName(trainOrderRow.getUserId());
			if(user!=null && user.getUsername()!=null) 
				dashBoardSearchCommonVirtualObject.setSalesPersonName(user.getUsername());
			else 
				dashBoardSearchCommonVirtualObject.setSalesPersonName("NA");
		}else
			dashBoardSearchCommonVirtualObject.setSalesPersonName("NA");

		FlightAndHotelBookApiResponse flightAndHotelBookApiResponse = new FlightAndHotelBookApiResponse();   // getApiStatusMessage(flightOrderRow.getId());
		if(flightAndHotelBookApiResponse!=null)
			dashBoardSearchCommonVirtualObject.setApiResponseMessage(flightAndHotelBookApiResponse.getApiStatusMessage());
		dashBoardSearchCommonVirtualObject.setApiProvider(trainOrderRow.getSupplierName()!=null && !trainOrderRow.getSupplierName().equals("")?trainOrderRow.getSupplierName():"NA");

		return dashBoardSearchCommonVirtualObject;
	}



	public DashBoardSearchCommonVirtualObject generateVisaRequiredData(VisaOrderRow visaOrderRow,Company sessionCompany) {
		DashBoardSearchCommonVirtualObject dashBoardSearchCommonVirtualObject=new DashBoardSearchCommonVirtualObject();
		FlightOrderDao  flightOrderDao=new FlightOrderDao();
		dashBoardSearchCommonVirtualObject.setServicetype("Visa");
		dashBoardSearchCommonVirtualObject.setId(visaOrderRow.getId());
		dashBoardSearchCommonVirtualObject.setUserId(visaOrderRow.getUserId());
		dashBoardSearchCommonVirtualObject.setCheckInDate("NA");
		dashBoardSearchCommonVirtualObject.setCheckOutDate("NA");
		dashBoardSearchCommonVirtualObject.setConfirmationNo(visaOrderRow.getConfirmationNumber());
		dashBoardSearchCommonVirtualObject.setDescription(visaOrderRow.getRemarks());
		dashBoardSearchCommonVirtualObject.setFilterCompanyType("-");
		dashBoardSearchCommonVirtualObject.setHotelName("-");
		dashBoardSearchCommonVirtualObject.setStatusAction(visaOrderRow.getStatusAction());
		dashBoardSearchCommonVirtualObject.setInvoiceDate(DateConversion.convertTimestampToString(visaOrderRow.getCreatedAt()));
		dashBoardSearchCommonVirtualObject.setInvoiceNo(visaOrderRow.getInvoiceNo());
		dashBoardSearchCommonVirtualObject.setOrderRef("-");
		dashBoardSearchCommonVirtualObject.setSupplierPrice(visaOrderRow.getSupplierPrice()!=null?visaOrderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
		dashBoardSearchCommonVirtualObject.setTicketType("-");
		dashBoardSearchCommonVirtualObject.setTravelDate(DateConversion.convertDateToStringToDate(visaOrderRow.getTravelDate()));
		dashBoardSearchCommonVirtualObject.setCreatedAt(visaOrderRow.getCreatedAt());
		dashBoardSearchCommonVirtualObject.setPnr("-");
		dashBoardSearchCommonVirtualObject.setBookingDate(DateConversion.convertDateToStringDate(visaOrderRow.getBookingDate()));
		dashBoardSearchCommonVirtualObject.setDepartureDate(DateConversion.convertDateToStringToDate(visaOrderRow.getTravelDate()));
		dashBoardSearchCommonVirtualObject.setAirline("-");
		dashBoardSearchCommonVirtualObject.setStatus(visaOrderRow.getStatusAction());
		dashBoardSearchCommonVirtualObject.setRoute("-");
		dashBoardSearchCommonVirtualObject.setBasePrice(visaOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setCurCode(visaOrderRow.getBookingCurrency());
		dashBoardSearchCommonVirtualObject.setTotal(visaOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
		BigDecimal gstorServiceTax=new BigDecimal(0);
		if(visaOrderRow.getVisaOrderRowServiceTax()!=null) 
			gstorServiceTax=visaOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP);
		if (visaOrderRow.getVisaOrderRowGstTax()!=null)  
			gstorServiceTax=visaOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP);
		BigDecimal finalPrice=visaOrderRow.getTotalAmount().add(gstorServiceTax).setScale(2, BigDecimal.ROUND_UP);
		dashBoardSearchCommonVirtualObject.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));
		if(visaOrderRow.getCreatedBy()!=null)
			dashBoardSearchCommonVirtualObject.setCreatedBy(visaOrderRow.getCreatedBy().replace("+", " "));	
		else
			dashBoardSearchCommonVirtualObject.setCreatedBy(visaOrderRow.getCreatedBy());	
		dashBoardSearchCommonVirtualObject.setOrderId(visaOrderRow.getOrderId());
		dashBoardSearchCommonVirtualObject.setServiceTax(gstorServiceTax.setScale(2, BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setCompanyId(visaOrderRow.getCompanyId());
		dashBoardSearchCommonVirtualObject.setOrderRequested(visaOrderRow.isOrderRequested());
		dashBoardSearchCommonVirtualObject.setOrderUpdated(visaOrderRow.isOrderUpdated());
		dashBoardSearchCommonVirtualObject.setCreditNoteIssued(visaOrderRow.isCreditNoteIssued());
		dashBoardSearchCommonVirtualObject.setFirstName(visaOrderRow.getOrderCustomer().getFirstName());
		dashBoardSearchCommonVirtualObject.setLastName(visaOrderRow.getOrderCustomer().getLastName());
		dashBoardSearchCommonVirtualObject.setTitle(visaOrderRow.getOrderCustomer().getTitle());
		dashBoardSearchCommonVirtualObject.setOrigin("-");
		dashBoardSearchCommonVirtualObject.setDestination("-");
		dashBoardSearchCommonVirtualObject.setMarkUp(visaOrderRow.getMarkUp()!=null?visaOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
		BigDecimal netPaybleAmount=(visaOrderRow.getTotalAmount().subtract(visaOrderRow.getMarkUp()).setScale(2, BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setNetAmnt(netPaybleAmount.setScale(2,BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setPaymentStatus(visaOrderRow.getPaymentStatus());
		User user=new User();
		if(visaOrderRow.getUserId()!=null && !visaOrderRow.getUserId().trim().equalsIgnoreCase("")){
			user=flightOrderDao.getSalesPersonName(visaOrderRow.getUserId());
			if(user!=null && user.getUsername()!=null) 
				dashBoardSearchCommonVirtualObject.setSalesPersonName(user.getUsername());
			else 
				dashBoardSearchCommonVirtualObject.setSalesPersonName("NA");
		}else
			dashBoardSearchCommonVirtualObject.setSalesPersonName("NA");

		FlightAndHotelBookApiResponse flightAndHotelBookApiResponse = new FlightAndHotelBookApiResponse();   // getApiStatusMessage(flightOrderRow.getId());
		if(flightAndHotelBookApiResponse!=null)
			dashBoardSearchCommonVirtualObject.setApiResponseMessage(flightAndHotelBookApiResponse.getApiStatusMessage());
		dashBoardSearchCommonVirtualObject.setApiProvider(visaOrderRow.getSupplierName()!=null && !visaOrderRow.getSupplierName().equals("")?visaOrderRow.getSupplierName():"NA");

		return dashBoardSearchCommonVirtualObject;
	}



	public DashBoardSearchCommonVirtualObject generateInsuranceRequiredData(InsuranceOrderRow insuranceOrderRow,Company sessionCompany) {
		DashBoardSearchCommonVirtualObject dashBoardSearchCommonVirtualObject=new DashBoardSearchCommonVirtualObject();
		FlightOrderDao  flightOrderDao=new FlightOrderDao();
		dashBoardSearchCommonVirtualObject.setServicetype("Insurance");
		dashBoardSearchCommonVirtualObject.setId(insuranceOrderRow.getId());
		dashBoardSearchCommonVirtualObject.setUserId(insuranceOrderRow.getUserId());
		dashBoardSearchCommonVirtualObject.setCheckInDate("NA");
		dashBoardSearchCommonVirtualObject.setCheckOutDate("NA");
		dashBoardSearchCommonVirtualObject.setConfirmationNo(insuranceOrderRow.getConfirmationNumber());
		dashBoardSearchCommonVirtualObject.setDescription(insuranceOrderRow.getRemarks());
		dashBoardSearchCommonVirtualObject.setFilterCompanyType("-");
		dashBoardSearchCommonVirtualObject.setHotelName("-");
		dashBoardSearchCommonVirtualObject.setStatusAction(insuranceOrderRow.getStatusAction());
		dashBoardSearchCommonVirtualObject.setInvoiceDate(DateConversion.convertTimestampToString(insuranceOrderRow.getCreatedAt()));
		dashBoardSearchCommonVirtualObject.setInvoiceNo(insuranceOrderRow.getInvoiceNo());
		dashBoardSearchCommonVirtualObject.setOrderRef("-");
		dashBoardSearchCommonVirtualObject.setSupplierPrice(insuranceOrderRow.getSupplierPrice()!=null?insuranceOrderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
		dashBoardSearchCommonVirtualObject.setTicketType("-");
		dashBoardSearchCommonVirtualObject.setTravelDate(DateConversion.convertDateToStringToDate(insuranceOrderRow.getTravelDate()));
		dashBoardSearchCommonVirtualObject.setCreatedAt(insuranceOrderRow.getCreatedAt());
		dashBoardSearchCommonVirtualObject.setPnr("-");
		dashBoardSearchCommonVirtualObject.setBookingDate(DateConversion.convertDateToStringDate(insuranceOrderRow.getBookingDate()));
		dashBoardSearchCommonVirtualObject.setDepartureDate(DateConversion.convertDateToStringToDate(insuranceOrderRow.getTravelDate()));
		dashBoardSearchCommonVirtualObject.setAirline("-");
		dashBoardSearchCommonVirtualObject.setStatus(insuranceOrderRow.getStatusAction());
		dashBoardSearchCommonVirtualObject.setRoute("-");
		dashBoardSearchCommonVirtualObject.setBasePrice(insuranceOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setCurCode(insuranceOrderRow.getBookingCurrency());
		dashBoardSearchCommonVirtualObject.setTotal(insuranceOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
		BigDecimal gstorServiceTax=new BigDecimal(0);
		if(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null) 
			gstorServiceTax=insuranceOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP);
		if (insuranceOrderRow.getInsuranceOrderRowGstTax()!=null)  
			gstorServiceTax=insuranceOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP);
		BigDecimal finalPrice=insuranceOrderRow.getTotalAmount().add(gstorServiceTax).setScale(2, BigDecimal.ROUND_UP);
		dashBoardSearchCommonVirtualObject.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));
		if(insuranceOrderRow.getCreatedBy()!=null)
			dashBoardSearchCommonVirtualObject.setCreatedBy(insuranceOrderRow.getCreatedBy().replace("+", " "));	
		else
			dashBoardSearchCommonVirtualObject.setCreatedBy(insuranceOrderRow.getCreatedBy());	
		dashBoardSearchCommonVirtualObject.setOrderId(insuranceOrderRow.getOrderId());
		dashBoardSearchCommonVirtualObject.setServiceTax(gstorServiceTax.setScale(2, BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setCompanyId(insuranceOrderRow.getCompanyId());
		dashBoardSearchCommonVirtualObject.setOrderRequested(insuranceOrderRow.isOrderRequested());
		dashBoardSearchCommonVirtualObject.setOrderUpdated(insuranceOrderRow.isOrderUpdated());
		dashBoardSearchCommonVirtualObject.setCreditNoteIssued(insuranceOrderRow.isCreditNoteIssued());
		dashBoardSearchCommonVirtualObject.setFirstName(insuranceOrderRow.getOrderCustomer().getFirstName());
		dashBoardSearchCommonVirtualObject.setLastName(insuranceOrderRow.getOrderCustomer().getLastName());
		dashBoardSearchCommonVirtualObject.setTitle(insuranceOrderRow.getOrderCustomer().getTitle());
		dashBoardSearchCommonVirtualObject.setOrigin("-");
		dashBoardSearchCommonVirtualObject.setMarkUp(insuranceOrderRow.getMarkUpamount()!=null?insuranceOrderRow.getMarkUpamount().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
		BigDecimal netPaybleAmount=(insuranceOrderRow.getTotalAmount().subtract(insuranceOrderRow.getMarkUpamount()).setScale(2, BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setNetAmnt(netPaybleAmount.setScale(2,BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setPaymentStatus(insuranceOrderRow.getPaymentStatus());
		User user=new User();
		if(insuranceOrderRow.getUserId()!=null && !insuranceOrderRow.getUserId().trim().equalsIgnoreCase("")){
			user=flightOrderDao.getSalesPersonName(insuranceOrderRow.getUserId());
			if(user!=null && user.getUsername()!=null) 
				dashBoardSearchCommonVirtualObject.setSalesPersonName(user.getUsername());
			else 
				dashBoardSearchCommonVirtualObject.setSalesPersonName("NA");
		}else
			dashBoardSearchCommonVirtualObject.setSalesPersonName("NA");

		FlightAndHotelBookApiResponse flightAndHotelBookApiResponse = new FlightAndHotelBookApiResponse();   // getApiStatusMessage(flightOrderRow.getId());
		if(flightAndHotelBookApiResponse!=null)
			dashBoardSearchCommonVirtualObject.setApiResponseMessage(flightAndHotelBookApiResponse.getApiStatusMessage());
		dashBoardSearchCommonVirtualObject.setApiProvider(insuranceOrderRow.getSupplierName()!=null && !insuranceOrderRow.getSupplierName().equals("")?insuranceOrderRow.getSupplierName():"NA");

		return dashBoardSearchCommonVirtualObject;
	}



	public DashBoardSearchCommonVirtualObject generateMiscellaneousRequiredData(MiscellaneousOrderRow miscellaneousOrderRow, Company sessionCompany) {
		DashBoardSearchCommonVirtualObject dashBoardSearchCommonVirtualObject=new DashBoardSearchCommonVirtualObject();
		FlightOrderDao  flightOrderDao=new FlightOrderDao();
		dashBoardSearchCommonVirtualObject.setServicetype("Miscellaneous");
		dashBoardSearchCommonVirtualObject.setId(miscellaneousOrderRow.getId());
		dashBoardSearchCommonVirtualObject.setUserId(Integer.toString(miscellaneousOrderRow.getUserId()));
		dashBoardSearchCommonVirtualObject.setCheckInDate("NA");
		dashBoardSearchCommonVirtualObject.setCheckOutDate("NA");
		dashBoardSearchCommonVirtualObject.setConfirmationNo(miscellaneousOrderRow.getConfirmationNumber());
		dashBoardSearchCommonVirtualObject.setDescription(miscellaneousOrderRow.getRemarks());
		dashBoardSearchCommonVirtualObject.setFilterCompanyType("-");
		dashBoardSearchCommonVirtualObject.setHotelName("-");
		dashBoardSearchCommonVirtualObject.setStatusAction(miscellaneousOrderRow.getStatusAction());
		dashBoardSearchCommonVirtualObject.setInvoiceDate(DateConversion.convertTimestampToString(miscellaneousOrderRow.getCreatedAt()));
		dashBoardSearchCommonVirtualObject.setInvoiceNo(miscellaneousOrderRow.getInvoiceNo());
		dashBoardSearchCommonVirtualObject.setOrderRef("-");
		dashBoardSearchCommonVirtualObject.setSupplierPrice(miscellaneousOrderRow.getSupplierPrice()!=null?miscellaneousOrderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
		dashBoardSearchCommonVirtualObject.setTicketType("-");
		dashBoardSearchCommonVirtualObject.setTravelDate(DateConversion.convertDateToStringToDate(miscellaneousOrderRow.getCreatedAt()));
		dashBoardSearchCommonVirtualObject.setCreatedAt(miscellaneousOrderRow.getCreatedAt());
		dashBoardSearchCommonVirtualObject.setPnr("-");
		dashBoardSearchCommonVirtualObject.setBookingDate(DateConversion.convertDateToStringDate(miscellaneousOrderRow.getBookingDate()));
		dashBoardSearchCommonVirtualObject.setDepartureDate(DateConversion.convertDateToStringToDate(miscellaneousOrderRow.getCreatedAt()));
		dashBoardSearchCommonVirtualObject.setAirline("-");
		dashBoardSearchCommonVirtualObject.setStatus(miscellaneousOrderRow.getStatusAction());
		dashBoardSearchCommonVirtualObject.setRoute("-");
		dashBoardSearchCommonVirtualObject.setBasePrice(miscellaneousOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setCurCode(miscellaneousOrderRow.getBookingCurrency());
		dashBoardSearchCommonVirtualObject.setTotal(miscellaneousOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
		BigDecimal gstorServiceTax=new BigDecimal(0);
		if (miscellaneousOrderRow.getMiscellaneousOrderRowGstTax()!=null)  
			gstorServiceTax=miscellaneousOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP);
		BigDecimal finalPrice=miscellaneousOrderRow.getTotalAmount().add(gstorServiceTax).setScale(2, BigDecimal.ROUND_UP);
		dashBoardSearchCommonVirtualObject.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));
		if(miscellaneousOrderRow.getCreatedBy()!=null)
			dashBoardSearchCommonVirtualObject.setCreatedBy(miscellaneousOrderRow.getCreatedBy().replace("+", " "));	
		else
			dashBoardSearchCommonVirtualObject.setCreatedBy(miscellaneousOrderRow.getCreatedBy());	
		dashBoardSearchCommonVirtualObject.setOrderId(miscellaneousOrderRow.getOrderId());
		dashBoardSearchCommonVirtualObject.setServiceTax(gstorServiceTax.setScale(2, BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setCompanyId(Integer.toString(miscellaneousOrderRow.getCompanyId()));
		dashBoardSearchCommonVirtualObject.setOrderRequested(miscellaneousOrderRow.isOrderRequested());
		dashBoardSearchCommonVirtualObject.setOrderUpdated(miscellaneousOrderRow.isOrderUpdated());
		dashBoardSearchCommonVirtualObject.setCreditNoteIssued(miscellaneousOrderRow.isCreditNoteIssued());
		dashBoardSearchCommonVirtualObject.setFirstName(miscellaneousOrderRow.getOrderCustomer().getFirstName());
		dashBoardSearchCommonVirtualObject.setLastName(miscellaneousOrderRow.getOrderCustomer().getLastName());
		dashBoardSearchCommonVirtualObject.setTitle(miscellaneousOrderRow.getOrderCustomer().getTitle());
		dashBoardSearchCommonVirtualObject.setOrigin("-");
		dashBoardSearchCommonVirtualObject.setDestination("-");
		dashBoardSearchCommonVirtualObject.setMarkUp(miscellaneousOrderRow.getMarkUpamount()!=null?miscellaneousOrderRow.getMarkUpamount().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
		BigDecimal netPaybleAmount=(miscellaneousOrderRow.getTotalAmount().subtract(miscellaneousOrderRow.getMarkUpamount()).setScale(2, BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setNetAmnt(netPaybleAmount.setScale(2,BigDecimal.ROUND_UP));
		dashBoardSearchCommonVirtualObject.setPaymentStatus(miscellaneousOrderRow.getPaymentStatus());
		User user=new User();
		if(Integer.toString(miscellaneousOrderRow.getUserId())!=null && !Integer.toString(miscellaneousOrderRow.getUserId()).trim().equalsIgnoreCase("")){
			user=flightOrderDao.getSalesPersonName(Integer.toString(miscellaneousOrderRow.getUserId()));
			if(user!=null && user.getUsername()!=null) 
				dashBoardSearchCommonVirtualObject.setSalesPersonName(user.getUsername());
			else 
				dashBoardSearchCommonVirtualObject.setSalesPersonName("NA");
		}else
			dashBoardSearchCommonVirtualObject.setSalesPersonName("NA");
		FlightAndHotelBookApiResponse flightAndHotelBookApiResponse = new FlightAndHotelBookApiResponse();   // getApiStatusMessage(flightOrderRow.getId());
		if(flightAndHotelBookApiResponse!=null)
			dashBoardSearchCommonVirtualObject.setApiResponseMessage(flightAndHotelBookApiResponse.getApiStatusMessage());
		dashBoardSearchCommonVirtualObject.setApiProvider(miscellaneousOrderRow.getSupplierName()!=null && !miscellaneousOrderRow.getSupplierName().equals("")?miscellaneousOrderRow.getSupplierName():"NA");
		return dashBoardSearchCommonVirtualObject;
	}


	public List<String> getCompanyIdListByParentCompany(Company loginCompany){
		List<String> companyIdList= null;
		int reportType=0;
		if(loginCompany!=null && loginCompany.getCompanyRole()!=null){
			if(loginCompany.getCompanyRole().isSuperUser() || loginCompany.getCompanyRole().isDistributor()){ 
			} 
			else
				reportType=1;
			companyIdList=new CommonDsrDao().getCompanyIdList(loginCompany, reportType);

		}
		return companyIdList;

	}


}
