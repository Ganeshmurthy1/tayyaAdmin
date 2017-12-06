package com.lintas.admin.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.config.HibernateUtil;

/**
 * @author info raham
 * created date : 31st Aug 2015
 */
public class OrderCustomerDao{
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(OrderCustomerDao.class);

	/* load all FlightOrder data from db */
	public  List<ReportData> getFlightOrderCustomerRowDetails(){
		List<ReportData>  reportData_list=new ArrayList<ReportData>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightOrderRow";
			Query query = session.createQuery(sql);
			List<FlightOrderRow> list = query.list();
			for (FlightOrderRow  customerPriceBreakup:list){
				ReportData reportData=new ReportData();
				//reportData.setAgencyUsername(customerPriceBreakup.getAgencyUserName());
				reportData.setAirline(customerPriceBreakup.getAirline());
				reportData.setPnr(customerPriceBreakup.getPnr());
				reportData.setCurCode(customerPriceBreakup.getBookingCurrency());
				reportData.setBookingDate(customerPriceBreakup.getBookingDate() );
				reportData.setDepartureDate(customerPriceBreakup.getDepartureDate());
				reportData.setArrivalDate(customerPriceBreakup.getArrivalDate());
				reportData.setId(customerPriceBreakup.getId());
				reportData.setCompanyId(customerPriceBreakup.getCompanyId());
				reportData.setPrice(customerPriceBreakup.getPrice());
				reportData.setPassengers(customerPriceBreakup.getPassengerCount());
				reportData.setProcessingFee(customerPriceBreakup.getProcessingFees());
				reportData.setFinalPrice(customerPriceBreakup.getFinalPrice());
				reportData.setEmail(customerPriceBreakup.getFlightCustomer().getEmail());
				reportData.setStatus(customerPriceBreakup.getStatusAction());
				reportData.setPaymentStatus(customerPriceBreakup.getPaymentStatus());
				/*logger.info("........orderid........."+customerPriceBreakup.getOrderId());*/
				reportData.setOrderId(customerPriceBreakup.getOrderId());
				reportData.setRoute(customerPriceBreakup.getOrigin()+"-"+customerPriceBreakup.getDestination());
				reportData.setConfigId(customerPriceBreakup.getConfigId());
				reportData.setCreatedBy(customerPriceBreakup.getCreatedBy());
				reportData.setApiComments(customerPriceBreakup.getApi_commit());
				reportData.setUserId(customerPriceBreakup.getUserId());
				//getUserNames(reportData, session);
				reportData_list.add(reportData);
			}


		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return reportData_list;
	}



	/*this method for get all the  OrderCustomer Data  */
	public  List<OrderCustomer> getOrderCustomersList() {
		List<OrderCustomer> list=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from OrderCustomer";
			Query query = session.createQuery(sql);
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
	/*this method for get all the  OrderCustomer Data  */
	public boolean updateOrderCustomerDetails(OrderCustomer orderCustomer ) {
		boolean isUpdated=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();	
			OrderCustomer customerUpdate = (OrderCustomer)session.get(OrderCustomer.class,orderCustomer.getId());
			customerUpdate.setFirstName(orderCustomer.getFirstName());
			customerUpdate.setLastName(orderCustomer.getLastName());
			customerUpdate.setEmail(orderCustomer.getEmail());
			customerUpdate.setCountryId(orderCustomer.getCountryId());
			customerUpdate.setMobile(orderCustomer.getMobile());
			customerUpdate.setGender(orderCustomer.getGender());
			session.update(customerUpdate);
			transaction.commit();
			isUpdated=true;
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
			return isUpdated;
		}finally {
			session.close(); 
		}

		return isUpdated;
	}
}
