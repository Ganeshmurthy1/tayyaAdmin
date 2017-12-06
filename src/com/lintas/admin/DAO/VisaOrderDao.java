/**
 * 
 */
package com.lintas.admin.DAO;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightReportPage;
import com.isl.admin.page.Page;
import com.lintas.action.CreditNote.modal.VisaCreditNote;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.common.model.VisaOrderRowCancellation;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.model.User;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

/**
 * @author MANISH
 *
 */
public class VisaOrderDao {

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(VisaOrderDao.class);
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
	List<VisaOrderRow> visaOrderRowList=new ArrayList<VisaOrderRow>();

	public  FlightReportPage getVisaReports(FlightReportPage flightReportPage,String showType){
		List<ReportData>  reportData_list=new ArrayList<ReportData>();
		int availablePages = 0;
		int availableItems = 0;
		String companyId=null;
		Session session = null;
		FlightOrderDao flightOrderDao = new FlightOrderDao();
		try{
			//2017-03-15
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(VisaOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Conjunction  conjunctionFlightOrderCustomer= Restrictions.conjunction();
			// To get total row count.
			if(flightReportPage!=null && flightReportPage.isFilterEnabled())
			{FlightReportFilter flightReportFilter = flightReportPage.getFlightReportFilter();
			List<String> companyIdList  = new ArrayList<String>();
			companyIdList = flightOrderDao.getCompanyIdList(flightReportFilter.getLoginCompany(), flightReportFilter.getReportType(), flightReportFilter.getCompanyName());
			logger.info("companyIdList--------------"+companyIdList.size());
			if(companyIdList == null || companyIdList.size() <= 0)
			{
				flightReportPage.setAvailableItems(0);					
				flightReportPage.setItems(new ArrayList<ReportData>());
				return flightReportPage;
			}
			reportConjunction.add(Restrictions.in("companyId",companyIdList));	

			if(flightReportFilter.getPaxName()!= null && !flightReportFilter.getPaxName().equalsIgnoreCase(""))
			{ 
				reportConjunction.add(Restrictions.like("empNmae", flightReportFilter.getPaxName(), MatchMode.ANYWHERE));
				logger.info("##########PaxName added----"+flightReportFilter.getPaxName());
			}
			if(flightReportFilter.getConfirmationNo() != null && !flightReportFilter.getConfirmationNo().equalsIgnoreCase("") )
			{
				reportConjunction.add(Restrictions.like("confirmationNumber", flightReportFilter.getConfirmationNo(), MatchMode.ANYWHERE));
				logger.info("##########origin added----"+flightReportFilter.getConfirmationNo());
			}	
			if(flightReportFilter.getOrderId()!= null && !flightReportFilter.getOrderId().equalsIgnoreCase(""))
			{
				reportConjunction.add(Restrictions.like("orderId", flightReportFilter.getOrderId(), MatchMode.ANYWHERE));
				logger.info("##########origin added----"+flightReportFilter.getOrderId());
			}	
			
			/////////////////////////////////////////////////////////////////////////////////////////////	
			if(flightReportFilter.getInvoiceNo() != null  && !flightReportFilter.getInvoiceNo().equalsIgnoreCase(""))
			{
				reportConjunction.add(Restrictions.like("invoiceNo", flightReportFilter.getInvoiceNo(), MatchMode.ANYWHERE));
				logger.info("##########origin added----"+flightReportFilter.getInvoiceNo());
			}	
			if(flightReportFilter.getConfirmationNo() != null && !flightReportFilter.getConfirmationNo().equalsIgnoreCase(""))
			{
				reportConjunction.add(Restrictions.like("confirmationNumber", flightReportFilter.getConfirmationNo(), MatchMode.ANYWHERE));
				logger.info("##########origin added----"+flightReportFilter.getConfirmationNo());
			}	
			if(flightReportFilter.getFirstName() != null && !flightReportFilter.getFirstName().equalsIgnoreCase(""))
			{
				conjunctionFlightOrderCustomer.add(Restrictions.like("firstName", flightReportFilter.getFirstName(), MatchMode.ANYWHERE));
				logger.info("##########origin added----"+flightReportFilter.getFirstName());
			}	
			if(flightReportFilter.getLastName() != null && !flightReportFilter.getLastName().equalsIgnoreCase(""))
			{
				conjunctionFlightOrderCustomer.add(Restrictions.like("lastName", flightReportFilter.getLastName(), MatchMode.ANYWHERE));
				logger.info("##########origin added----"+flightReportFilter.getLastName());
			}	
			if(flightReportFilter.getSupplierName() != null && !flightReportFilter.getSupplierName().equalsIgnoreCase(""))
			{
				reportConjunction.add(Restrictions.like("supplierName", flightReportFilter.getSupplierName(), MatchMode.ANYWHERE));
				logger.info("##########origin added----"+flightReportFilter.getSupplierName());
			}	
			if(flightReportFilter.getCustomerMobileNo() != null && !flightReportFilter.getCustomerMobileNo().equalsIgnoreCase(""))
			{
				conjunctionFlightOrderCustomer.add(Restrictions.like("mobile", flightReportFilter.getCustomerMobileNo(), MatchMode.ANYWHERE));
				logger.info("##########origin added----"+flightReportFilter.getCustomerMobileNo());
			}	
			if(flightReportFilter.getLocation() != null && !flightReportFilter.getLocation().equalsIgnoreCase("ALL"))
			{
				conjunctionFlightOrderCustomer.add(Restrictions.like("countryId", flightReportFilter.getLocation(), MatchMode.ANYWHERE));
				logger.info("##########origin added----"+flightReportFilter.getLocation());
			}	
			
			if(flightReportFilter.getDateFilterInvoice().getDtStart() != null && flightReportFilter.getDateFilterInvoice().getDtEnd() != null && !flightReportFilter.getDateFilterInvoice().getDtStart().equals("") && !flightReportFilter.getDateFilterInvoice().getDtEnd().equals("") )
			{
				DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
				try{
					Date date = originalFormat.parse(flightReportFilter.getDateFilterInvoice().getDtStart());
					reportConjunction.add(Restrictions.ge("bookingDate", date));
				}catch(Exception ex)
				{
					logger.info("##########date min format exception---"+ex.getMessage());

				}
				try{
					Date date = originalFormat.parse(flightReportFilter.getDateFilterInvoice().getDtEnd());
					reportConjunction.add(Restrictions.le("bookingDate", date));
				}catch(Exception ex)
				{
					logger.info("##########date max format exception---"+ex.getMessage());

				}
			}
			

			
		///////////////////////////////////////////////////////////////////////////////////////////////	
			if(flightReportFilter.getEmail() != null && !flightReportFilter.getEmail().equals(""))
			{		
				conjunctionFlightOrderCustomer.add(Restrictions.eq("email", flightReportFilter.getEmail()));
			}
 
			if(flightReportFilter.getBookingStatus() != null && !flightReportFilter.getBookingStatus().equalsIgnoreCase("ALL"))
			{
				Disjunction statusActionDisjunction = Restrictions.disjunction();
				statusActionDisjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus().toLowerCase()));
				statusActionDisjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus().toUpperCase()));
				statusActionDisjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus()));
				reportConjunction.add(statusActionDisjunction);
				//reportConjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus()));
				logger.info("##########booking status added----"+flightReportFilter.getBookingStatus());
			}
			if(flightReportFilter.getPaymentStatus() != null && !flightReportFilter.getPaymentStatus().equalsIgnoreCase("ALL"))
			{

				Disjunction statusActionDisjunction = Restrictions.disjunction();
				statusActionDisjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus().toLowerCase()));
				statusActionDisjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus().toUpperCase()));
				statusActionDisjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus()));
				reportConjunction.add(statusActionDisjunction); 
				//reportConjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus()));
				logger.info("########## Payment Status added----"+flightReportFilter.getPaymentStatus());
			}
			
			if(flightReportFilter.getUserId()>0)
				reportConjunction.add(Restrictions.like("userId",  String.valueOf(flightReportFilter.getUserId())));

			if(flightReportFilter.getCompanyId()>0)
				reportConjunction.add(Restrictions.like("companyId", String.valueOf(flightReportFilter.getCompanyId())));



			if(flightReportFilter.getDateFilterBooking().getDtStart() != null && flightReportFilter.getDateFilterBooking().getDtEnd() != null && !flightReportFilter.getDateFilterBooking().getDtStart().equals("") && !flightReportFilter.getDateFilterBooking().getDtEnd().equals("") )
			{
				DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
				try{
					Date date = originalFormat.parse(flightReportFilter.getDateFilterBooking().getDtStart());
					reportConjunction.add(Restrictions.ge("bookingDate", date));
				}catch(Exception ex)
				{
					logger.info("##########date min format exception---"+ex.getMessage());

				}
				try{
					Date date = originalFormat.parse(flightReportFilter.getDateFilterBooking().getDtEnd());
					reportConjunction.add(Restrictions.le("bookingDate", date));
				}catch(Exception ex)
				{
					logger.info("##########date max format exception---"+ex.getMessage());

				}
			}
			if(flightReportFilter.getDateFilterArrival().getDtStart()!= null && flightReportFilter.getDateFilterArrival().getDtEnd() != null && !flightReportFilter.getDateFilterArrival().getDtEnd().equals("") && !flightReportFilter.getDateFilterArrival().getDtStart().equals(""))
			{

				//2016-06-28
				//SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
				DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");


				try{
					Date date = originalFormat.parse(flightReportFilter.getDateFilterArrival().getDtStart());
					String formattedDate = targetFormat.format(date); 
					date = targetFormat.parse(formattedDate);

					date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));

					reportConjunction.add(Restrictions.ge("travelDate", date));
					logger.info("##########getDateFilterCheckIn date min added to conjuction---"+date);

				}catch(Exception ex)
				{
					logger.info("##########getDateFilterCheckIn date min format exception---"+ex.getMessage());

				}
				try{
					Date date = originalFormat.parse(flightReportFilter.getDateFilterArrival().getDtEnd());
					String formattedDate = targetFormat.format(date); 
					date = targetFormat.parse(formattedDate);

					date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));

					reportConjunction.add(Restrictions.lt("travelDate", date));
					logger.info("##########getDateFilterCheckIn date max added to conjuction---"+date);

				}catch(Exception ex)
				{
					logger.info("##########getDateFilterCheckIn date max format exception---"+ex.getMessage());

				}
			}




			try{
				if(showType!=null && showType.equalsIgnoreCase("visaconfirm")){
					///reportConjunction.add(Restrictions.in("companyId",companyIdList));
					reportConjunction.add(Restrictions.eq("statusAction","Confirmed"));
				}
				else if(showType!=null && showType.equalsIgnoreCase("visapaymentfailed")){
					//reportConjunction.add(Restrictions.in("companyId",companyIdList));
					reportConjunction.add(Restrictions.eq("paymentStatus","Failed"));
				}	
				else if(showType!=null && showType.equalsIgnoreCase("visapayment")){
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
					logger.info("##########today added to conjuction---"+today);
				}
				else if(showType!=null && showType.equalsIgnoreCase("week")){							

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
					logger.info("##########month added to conjuction---"+firstdayofthemonth  +  today);
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
					reportConjunction.add(Restrictions.ne("paymentStatus", "Success"));
				}


			}catch(Exception ex)
			{
				logger.info("##########today format exception---"+ex.getMessage());

			} 
			

			criteria.add(reportConjunction);
			criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);	
			criteria.addOrder(Order.desc("id"));
			//criteria.createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);	

			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);

			List<VisaOrderRow> list =null;
			if(rowCount>0)
			{
				if(flightReportPage.getMaxItems()==Page.ALL_ITEMS){
					logger.info(":::: retriving all items for excel export------");

					criteria = session.createCriteria(VisaOrderRow.class);
					criteria.add(reportConjunction);
					criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);	
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

					//int itemIndex = flightReportPage.getCurrentPageIndex() * flightReportPage.getMaxItems();

					logger.info("setFirstResult-------"+itemIndex);

					if(itemIndex <= rowCount)
					{
						logger.info("setFirstResult-------"+itemIndex);
						logger.info("MaxResults-------"+flightReportPage.getMaxItems());

						criteria = session.createCriteria(VisaOrderRow.class);
						criteria.add(reportConjunction);
						criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);	
						//criteria.createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);	
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(flightReportPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
						logger.info("Reports size------"+list.size());	

					}

				}

				//logger.info("---------logged in company id-----------------------------"+companySessionObj.getCompanyid());
				if(list!=null && list.size()>0)
				{
					for (VisaOrderRow visaOrderRow :list)
					{
						ReportData reportData=new ReportData();
						reportData=buildReportData(visaOrderRow, reportData);
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
		//logger.info("--------reportData_list size-------"+reportData_list.size());
		return flightReportPage;
	}

	public VisaOrderRow updateVisaOrderRowDetail(VisaOrderRow visaOrderRow){
		VisaOrderRow newVisaOrderRow = null;
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			newVisaOrderRow=(VisaOrderRow) session.get(VisaOrderRow.class,visaOrderRow.getId());
			newVisaOrderRow.setInvoiceNo(visaOrderRow.getInvoiceNo());
			newVisaOrderRow.setOrderId(visaOrderRow.getOrderId());
			newVisaOrderRow.getOrderCustomer().setOrderId(visaOrderRow.getOrderId());//added by basha
			session.update(newVisaOrderRow);
			tx.commit();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  newVisaOrderRow;
	}
	public VisaOrderRow updateVisaOrderRowDetailPaymentStatus(VisaOrderRow visaOrderRow){
		VisaOrderRow newVisaOrderRow = null;
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			newVisaOrderRow=(VisaOrderRow) session.get(VisaOrderRow.class,visaOrderRow.getId());
			newVisaOrderRow.setPaymentStatus(visaOrderRow.getPaymentStatus());
			session.update(newVisaOrderRow);
			tx.commit();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  newVisaOrderRow;
	}

	public FlightReportPage getVisaOrders(FlightReportPage commonReportPage) {
		List<ReportData>  reportData_list=new ArrayList<ReportData>();
		int availablePages = 0;
		int availableItems = 0;
		String companyId=null;
		Session session = null;
		FlightOrderDao flightOrderDao = new FlightOrderDao();
		try{
			//2017-03-15
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(VisaOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Conjunction  conjunctionFlightOrderCustomer= Restrictions.conjunction();
			// To get total row count.
			if(commonReportPage!=null && commonReportPage.isFilterEnabled())
			{

				FlightReportFilter flightReportFilter = commonReportPage.getFlightReportFilter();
				List<String> companyIdList  = new ArrayList<String>();
				companyIdList = flightOrderDao.getCompanyIdList(flightReportFilter.getLoginCompany(), flightReportFilter.getReportType(), flightReportFilter.getCompanyName());
				logger.info("companyIdList--------------"+companyIdList.size());
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					commonReportPage.setAvailableItems(0);					
					commonReportPage.setItems(new ArrayList<ReportData>());
					return commonReportPage;
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));	


				if(flightReportFilter.getPaxName()!= null && !flightReportFilter.getPaxName().equalsIgnoreCase(""))
				{ 
					reportConjunction.add(Restrictions.like("empNmae", flightReportFilter.getPaxName(), MatchMode.ANYWHERE));
					logger.info("##########PaxName added----"+flightReportFilter.getPaxName());
				}
				if(flightReportFilter.getConfirmationNo() != null && !flightReportFilter.getConfirmationNo().equalsIgnoreCase(""))
				{
					reportConjunction.add(Restrictions.like("confirmationNumber", flightReportFilter.getConfirmationNo(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getConfirmationNo());
				}	
				if(flightReportFilter.getOrderId()!= null && !flightReportFilter.getOrderId().equalsIgnoreCase(""))
				{
					reportConjunction.add(Restrictions.like("orderId", flightReportFilter.getOrderId(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getOrderId());
				}	
				
				/////////////////////////////////////////////////////////////////////////////////////////////	
				if(flightReportFilter.getInvoiceNo() != null  && !flightReportFilter.getInvoiceNo().equalsIgnoreCase(""))
				{
					reportConjunction.add(Restrictions.like("invoiceNo", flightReportFilter.getInvoiceNo(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getInvoiceNo());
				}	
				if(flightReportFilter.getConfirmationNo() != null && !flightReportFilter.getConfirmationNo().equalsIgnoreCase(""))
				{
					reportConjunction.add(Restrictions.like("confirmationNumber", flightReportFilter.getConfirmationNo(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getConfirmationNo());
				}	
				if(flightReportFilter.getFirstName() != null && !flightReportFilter.getFirstName().equalsIgnoreCase(""))
				{
					conjunctionFlightOrderCustomer.add(Restrictions.like("firstName", flightReportFilter.getFirstName(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getFirstName());
				}	
				if(flightReportFilter.getLastName() != null && !flightReportFilter.getLastName().equalsIgnoreCase(""))
				{
					conjunctionFlightOrderCustomer.add(Restrictions.like("lastName", flightReportFilter.getLastName(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getLastName());
				}	
				if(flightReportFilter.getSupplierName() != null && !flightReportFilter.getSupplierName().equalsIgnoreCase(""))
				{
					reportConjunction.add(Restrictions.like("supplierName", flightReportFilter.getSupplierName(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getSupplierName());
				}	
				if(flightReportFilter.getCustomerMobileNo() != null && !flightReportFilter.getCustomerMobileNo().equalsIgnoreCase(""))
				{
					conjunctionFlightOrderCustomer.add(Restrictions.like("mobile", flightReportFilter.getCustomerMobileNo(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getCustomerMobileNo());
				}	
				if(flightReportFilter.getLocation() != null && !flightReportFilter.getLocation().equalsIgnoreCase("All"))
				{
					conjunctionFlightOrderCustomer.add(Restrictions.like("countryId", flightReportFilter.getLocation(), MatchMode.ANYWHERE));
					logger.info("##########origin added----"+flightReportFilter.getLocation());
				}	
				
				if(flightReportFilter.getDateFilterInvoice().getDtStart() != null && flightReportFilter.getDateFilterInvoice().getDtEnd() != null && !flightReportFilter.getDateFilterInvoice().getDtStart().equals("") && !flightReportFilter.getDateFilterInvoice().getDtEnd().equals("") )
				{
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterInvoice().getDtStart());
						reportConjunction.add(Restrictions.ge("bookingDate", date));
					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterInvoice().getDtEnd());
						reportConjunction.add(Restrictions.le("bookingDate", date));
					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());

					}
				}
				

				
			///////////////////////////////////////////////////////////////////////////////////////////////	
				
				if(flightReportFilter.getEmail() != null && !flightReportFilter.getEmail().equals(""))
				{		
					conjunctionFlightOrderCustomer.add(Restrictions.eq("email", flightReportFilter.getEmail()));
				}
				if(flightReportFilter.getUserId()>0)
					reportConjunction.add(Restrictions.like("userId",  String.valueOf(flightReportFilter.getUserId())));

				if(flightReportFilter.getCompanyId()>0)
					reportConjunction.add(Restrictions.like("companyId", String.valueOf(flightReportFilter.getCompanyId())));
			
				if(flightReportFilter.getBookingStatus() != null && !flightReportFilter.getBookingStatus().equalsIgnoreCase("ALL"))
				{
					Disjunction statusActionDisjunction = Restrictions.disjunction();
					statusActionDisjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus().toLowerCase()));
					statusActionDisjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus().toUpperCase()));
					statusActionDisjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus()));
					reportConjunction.add(statusActionDisjunction);
					//reportConjunction.add(Restrictions.eq("statusAction", flightReportFilter.getBookingStatus()));
					logger.info("##########booking status added----"+flightReportFilter.getBookingStatus());
				}
				if(flightReportFilter.getPaymentStatus() != null && !flightReportFilter.getPaymentStatus().equalsIgnoreCase("ALL"))
				{

					Disjunction statusActionDisjunction = Restrictions.disjunction();
					statusActionDisjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus().toLowerCase()));
					statusActionDisjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus().toUpperCase()));
					statusActionDisjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus()));
					reportConjunction.add(statusActionDisjunction); 
					//reportConjunction.add(Restrictions.eq("paymentStatus", flightReportFilter.getPaymentStatus()));
					logger.info("########## Payment Status added----"+flightReportFilter.getPaymentStatus());
				}


				if(flightReportFilter.getDateFilterBooking().getDtStart() != null && flightReportFilter.getDateFilterBooking().getDtEnd() != null && !flightReportFilter.getDateFilterBooking().getDtStart().equals("") && !flightReportFilter.getDateFilterBooking().getDtEnd().equals("") )
				{
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterBooking().getDtStart());
						reportConjunction.add(Restrictions.ge("bookingDate", date));
					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterBooking().getDtEnd());
						reportConjunction.add(Restrictions.le("bookingDate", date));
					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());

					}
				}
				if(flightReportFilter.getDateFilterArrival().getDtStart()!= null && flightReportFilter.getDateFilterArrival().getDtEnd() != null && !flightReportFilter.getDateFilterArrival().getDtEnd().equals("") && !flightReportFilter.getDateFilterArrival().getDtStart().equals(""))
				{

					//2016-06-28
					//SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");


					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterArrival().getDtStart());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);

						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));

						reportConjunction.add(Restrictions.ge("travelDate", date));
						logger.info("##########getDateFilterCheckIn date min added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########getDateFilterCheckIn date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(flightReportFilter.getDateFilterArrival().getDtEnd());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);

						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));

						reportConjunction.add(Restrictions.lt("travelDate", date));
						logger.info("##########getDateFilterCheckIn date max added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########getDateFilterCheckIn date max format exception---"+ex.getMessage());

					}
				}
			 
				criteria.add(reportConjunction);
				criteria.addOrder(Order.desc("id"));
				criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);
				//criteria.createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);	

			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);

			List<VisaOrderRow> list =null;
			if(rowCount>0)
			{
				if(commonReportPage.getMaxItems()==Page.ALL_ITEMS){
					logger.info(":::: retriving all items for excel export------");

					criteria = session.createCriteria(VisaOrderRow.class);
					criteria.add(reportConjunction);
					criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);
					//criteria.createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					logger.info(":::: retriving all items for excel export-----list-"+list);
					logger.info("rowCountForExcel list.size ------"+((list != null)?0:list.size()));	
					commonReportPage.setAvailableItems(list.size());
					commonReportPage.setAvailablePages(1);

				}
				else{
					if(commonReportPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % commonReportPage.getMaxItems() == 0)?(availableItems / commonReportPage.getMaxItems()):((availableItems / commonReportPage.getMaxItems()) + 1);
						commonReportPage.setAvailableItems(availableItems);
						commonReportPage.setAvailablePages(availablePages);
					} 
					//Retrive report with pagination .......

					int pageIndexDb = (commonReportPage.getCurrentPageIndex() > 1)?commonReportPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * commonReportPage.getMaxItems();

					//int itemIndex = flightReportPage.getCurrentPageIndex() * flightReportPage.getMaxItems();

					logger.info("setFirstResult-------"+itemIndex);

					if(itemIndex <= rowCount)
					{
						logger.info("setFirstResult-------"+itemIndex);
						logger.info("MaxResults-------"+commonReportPage.getMaxItems());

						criteria = session.createCriteria(VisaOrderRow.class);
						criteria.add(reportConjunction);
						criteria.createCriteria("orderCustomer").add(conjunctionFlightOrderCustomer);
						//criteria.createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);	
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(commonReportPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
						logger.info("Reports size------"+list.size());	

					}

				}

				//logger.info("---------logged in company id-----------------------------"+companySessionObj.getCompanyid());
				if(list!=null && list.size()>0)
				{
					for (VisaOrderRow visaOrderRow :list)
					{
						ReportData reportData=new ReportData();
						reportData=buildReportData(visaOrderRow, reportData);
						reportData_list.add(reportData);
					}					
					commonReportPage.setItems(reportData_list);

				}
				else
				{
					//current page is having empty items..
					commonReportPage.setAvailableItems(0);					
					commonReportPage.setItems(new ArrayList<ReportData>());
				}

			}
			else
			{
				commonReportPage.setAvailableItems(0);
				commonReportPage.setAvailablePages(0);
				commonReportPage.setItems(new ArrayList<ReportData>());
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
		return commonReportPage;
	}
	public List<VisaCreditNote> loadCreditNoteListByRowId(Long id) {
		String sql="from VisaCreditNote hcn where hcn.rowId=:id";
		List<VisaCreditNote> list=new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("id",id.intValue());
			List<VisaCreditNote> creditList=query.list();
			if(creditList!=null &&creditList.size()>0){
				for(VisaCreditNote note:creditList){
					note.setConvertDate(DateConversion.convertTimestampToStringWithoutAM(note.getOrderedAt()));
					note.setManagementFees(note.getManagementFees().setScale(2, BigDecimal.ROUND_UP));
					note.setConvenienceFees(note.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP));
					note.setCancellationFees(note.getCancellationFees().setScale(2, BigDecimal.ROUND_UP));
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
	public VisaOrderRowCancellation getVisaOrderRowCancellation(String orderReference) {
		// TODO Auto-generated method stub
		VisaOrderRowCancellation cancellation=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(VisaOrderRowCancellation.class);
			criteria.add(Restrictions.eq("orderId", orderReference));
			cancellation=(VisaOrderRowCancellation) criteria.uniqueResult();
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
	public  VisaCreditNote visaCreditNoteData(Long id, int companyId) {

		VisaCreditNote creditNote=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(VisaCreditNote.class);
			criteria.add(Restrictions.eq("rowId", id.intValue()));
			criteria.add(Restrictions.eq("companyId",String.valueOf(companyId)));
			creditNote=(VisaCreditNote) criteria.uniqueResult();
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
	public ReportData getReportDetailsByRowId(Long id) {
		// TODO Auto-generated method stub
		ReportData reportData=new ReportData();

		try{
			String ordersSql= "from VisaOrderRow row where row.id=:id";
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(ordersSql);
			query.setParameter("id", id);
			VisaOrderRow visaOrderRow = (VisaOrderRow) query.uniqueResult();
			if(visaOrderRow!=null){
				buildReportData(visaOrderRow, reportData);
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
	private ReportData buildReportData(VisaOrderRow visaOrderRow,ReportData reportData) {//reportData.setPnr(visaOrderRow.getPnr());
		//reportData.setPnr(visaOrderRow.getPnr());
		reportData.setFirstName(visaOrderRow.getOrderCustomer().getFirstName());
		reportData.setLastName(visaOrderRow.getOrderCustomer().getLastName());
		reportData.setTitle(visaOrderRow.getOrderCustomer().getTitle());
		reportData.setMarkUp(visaOrderRow.getMarkUp().setScale(2,BigDecimal.ROUND_UP));
		reportData.setVfsTaxes(visaOrderRow.getVfsCharges().setScale(2,BigDecimal.ROUND_UP));
		reportData.setCourierCharges(visaOrderRow.getCourierCharges().setScale(2,BigDecimal.ROUND_UP));
		reportData.setInvoiceAmount(visaOrderRow.getTotalAmount().add(calculateTotalserviceTax(visaOrderRow)).setScale(2, BigDecimal.ROUND_UP));
		reportData.setBookingPrice(visaOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP).add(visaOrderRow.getMarkUp()!=null?visaOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00")).add(visaOrderRow.getTaxes()!=null?visaOrderRow.getTaxes().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00")));
		
		reportData.setCreatedAt(visaOrderRow.getCreatedAt());
		reportData.setSupplierName(visaOrderRow.getSupplierName());

		if(visaOrderRow.getProcessingFees()!=null)
			reportData.setConvenienceFees(visaOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
		else
			reportData.setConvenienceFees(new BigDecimal(0));
		//		reportData.setVehicleCompanyName(visaOrderRow.getVisaCompanyName());
    	reportData.setFinalPrice(visaOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
		//reportData.setFinalPrice(visaOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP).add(calculateTotalserviceTax(visaOrderRow)).add(visaOrderRow.getMarkUp()!=null?visaOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00").add(visaOrderRow.getTaxes())).setScale(2, BigDecimal.ROUND_UP));
		reportData.setBookingPrice(visaOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP).add(visaOrderRow.getMarkUp()!=null?visaOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00").add(visaOrderRow.getTaxes())).setScale(2, BigDecimal.ROUND_UP));
		 
		BigDecimal gstorServiceTax=new BigDecimal(0);
		if(visaOrderRow.getVisaOrderRowServiceTax()!=null) 
			gstorServiceTax=visaOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP);
		 if (visaOrderRow.getVisaOrderRowGstTax()!=null)  
				gstorServiceTax=visaOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP);
		BigDecimal finalPrice=visaOrderRow.getTotalAmount().add(gstorServiceTax).setScale(2, BigDecimal.ROUND_UP);
		reportData.setTotal(visaOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
		//reportData.setFinalPrice(finalPrice.setScale(2, BigDecimal.ROUND_UP));
		
		
		
		
		reportData.setEmail(visaOrderRow.getOrderCustomer().getEmail());
		reportData.setStatus(visaOrderRow.getStatusAction());
		reportData.setPaymentStatus(visaOrderRow.getPaymentStatus());
		reportData.setOrderId(visaOrderRow.getOrderId());
		reportData.setCurCode(visaOrderRow.getBookingCurrency());
		reportData.setBookingDate(visaOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringDateddMMyyyy(visaOrderRow.getBookingDate()):"-");
		reportData.setMarkUp(visaOrderRow.getMarkUp()!=null?visaOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP):new BigDecimal("0.00"));
		BigDecimal netPaybleAmount=(visaOrderRow.getTotalAmount().subtract(visaOrderRow.getMarkUp()).setScale(2, BigDecimal.ROUND_UP));
		reportData.setNetAmnt(netPaybleAmount.setScale(2,BigDecimal.ROUND_UP));
		reportData.setId(visaOrderRow.getId());
		reportData.setCompanyId(visaOrderRow.getCompanyId());
		reportData.setBasePrice(visaOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
		reportData.setConfirmationNumber(visaOrderRow.getConfirmationNumber());
		reportData.setConvenienceFees(visaOrderRow.getConvenienceFee().setScale(2, BigDecimal.ROUND_UP));
		reportData.setInvoiceNo(visaOrderRow.getInvoiceNo());
		reportData.setManagementFee(visaOrderRow.getManagementFee().setScale(2, BigDecimal.ROUND_UP));
		reportData.setOtherTaxes(visaOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP));
		reportData.setProcessingFee(visaOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
		reportData.setServiceTax(visaOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP));
		reportData.setTax(visaOrderRow.getTaxes().setScale(2, BigDecimal.ROUND_UP));
		reportData.setTravelDate1(visaOrderRow.getTravelDate());
		reportData.setTransactionKey(visaOrderRow.getTransactionKey());
		reportData.setOrderCustomerId(visaOrderRow.getOrderCustomer().getId());
		reportData.setSupplierPrice(visaOrderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP));
		reportData.setUpdatedBy(visaOrderRow.getUpdatedBy());

		if(visaOrderRow.getCreatedBy()!=null){
			reportData.setCreatedBy(visaOrderRow.getCreatedBy().replace("+", " "));	
		}
		else{
			reportData.setCreatedBy(visaOrderRow.getCreatedBy());	
		}

		reportData.setAgencyUsername(visaOrderRow.getCreatedBy());
		reportData.setDescription(visaOrderRow.getRemarks());


		reportData.setDescription(visaOrderRow.getRemarks());
		reportData.setUserId(visaOrderRow.getUserId());
		reportData.setStatus(visaOrderRow.getStatusAction());
		reportData.setPaymentStatus(visaOrderRow.getPaymentStatus());
		reportData.setOrderRequested(visaOrderRow.isOrderRequested());
		reportData.setBookingMode(visaOrderRow.getBookingMode());
		reportData.setCreditNoteIssued(visaOrderRow.isCreditNoteIssued());
		reportData.setOrderUpdated(visaOrderRow.isOrderUpdated());
		User user= new UserDAO().getSalesPersonName(visaOrderRow.getUserId());

		if(user!=null) 
			reportData.setSalesPersonName(user.getUsername());
		else 
			reportData.setSalesPersonName("-");
		return reportData;
	}
	public static BigDecimal  calculateTotalserviceTax(VisaOrderRow  visaOrderRow){
		BigDecimal convenienceFeePerRoom = new BigDecimal("0");
		BigDecimal managementFeePerRoom = new BigDecimal("0");
		if(visaOrderRow.getVisaOrderRowServiceTax() !=null && visaOrderRow.getVisaOrderRowServiceTax().getManagementFee()!=null)
			managementFeePerRoom= visaOrderRow.getVisaOrderRowServiceTax().getManagementFee();
		if(visaOrderRow.getVisaOrderRowServiceTax() !=null && visaOrderRow.getVisaOrderRowServiceTax().getConvenienceFee()!=null)
			convenienceFeePerRoom= visaOrderRow.getVisaOrderRowServiceTax().getConvenienceFee();
		if(visaOrderRow.getVisaOrderRowGstTax() !=null && visaOrderRow.getVisaOrderRowGstTax().getManagementFee()!=null)
			managementFeePerRoom= visaOrderRow.getVisaOrderRowGstTax().getManagementFee();
		if(visaOrderRow.getVisaOrderRowGstTax() !=null && visaOrderRow.getVisaOrderRowGstTax().getConvenienceFee()!=null)
			convenienceFeePerRoom= visaOrderRow.getVisaOrderRowGstTax().getConvenienceFee();
		
		BigDecimal  serviceTax= visaOrderRow.getServiceTax()!=null?visaOrderRow.getServiceTax().setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);
		BigDecimal  gstTax=visaOrderRow.getTotalGstTax()!=null?visaOrderRow.getTotalGstTax().setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);
		
		BigDecimal  totalAmount=serviceTax.add(managementFeePerRoom).add(convenienceFeePerRoom).add(gstTax);
		return totalAmount.setScale(2,BigDecimal.ROUND_UP);
	}
	public VisaOrderRow getVisaOrderRowDetail(String orderId) {
		/*this method for get  FlightOrderRow  using order id */
		VisaOrderRow visaOrderRow = null;
		Session session=null;
			try{
				session = HibernateUtil.getSessionFactory().openSession();
				Criteria criteria = session.createCriteria(VisaOrderRow.class);
				criteria.add(Restrictions.eq("orderId", orderId));
				visaOrderRow = (VisaOrderRow) criteria.uniqueResult();
			}catch (HibernateException e) {
				logger.error("--------------HibernateException-----------------"+e.getMessage());
			}finally {
				if(session!=null && session.isOpen())
					session.close(); 
			}
			return visaOrderRow;
		}
	public VisaOrderRow getVisaOrderRowDetail(Long id) {
		/*this method for get  FlightOrderRow  using order id */
		VisaOrderRow visaOrderRow = null;
			try{
				session = HibernateUtil.getSessionFactory().openSession();
				String sql = "from VisaOrderRow fotd where fotd.id=:id";
				Query query = session.createQuery(sql);
				query.setParameter("id", id);
				visaOrderRow = (VisaOrderRow) query.uniqueResult();
			}catch (HibernateException e) {
				logger.error("--------------HibernateException-----------------"+e.getMessage());
			}finally {
				if(session!=null && session.isOpen())
					session.close(); 
			}
			return visaOrderRow;
		}
	
	public List<VisaOrderRow> getVisaBookingForIds(List<String> companyUserId ) {
		List<VisaOrderRow> visaOrderRows=null;
		BigDecimal finalPrice=new BigDecimal("0");
 		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr=session.createCriteria(VisaOrderRow.class);
			cr.add(Restrictions.in("companyId", companyUserId));
			visaOrderRows=cr.list();
			 
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
 		return visaOrderRows;
	}
}
