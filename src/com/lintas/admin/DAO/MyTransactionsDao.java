package com.lintas.admin.DAO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transaction;

import org.displaytag.util.CollectionUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.admin.dashboardsearch.dao.DashBoardSearchDao;
import com.admin.enums.utility.CommonBookingStatusEnum;
import com.admin.knockoff.dao.PaymentKnockDao;
import com.admin.knockoff.dao.PaymentKnockDaoImpl;
import com.admin.knockoff.vo.KnockOffVO;
import com.admin.miscellaneous.dao.MiscellaneousCreditNoteDao;
import com.admin.miscellaneous.dao.MiscellaneousOrderDao;
import com.admin.miscellaneous.model.MiscellaneousCreditNote;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.admin.payment.recievable.PaymentKnockOffRow;
import com.admin.payment.recievable.PaymentKnockOffRowTx;
import com.common.dsr.CommonDsrDao;
import com.common.dsr.CommonDsrTravelReportData;
import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.page.CompanyFilterPage;
import com.isl.admin.page.Page;
import com.lintas.action.CreditNote.Dao.BusCreditNoteDao;
import com.lintas.action.CreditNote.Dao.CarCreditNoteDao;
import com.lintas.action.CreditNote.Dao.CreditNoteDao;
import com.lintas.action.CreditNote.Dao.HotelCreditNoteDao;
import com.lintas.action.CreditNote.Dao.InsuranceCreditNoteDao;
import com.lintas.action.CreditNote.Dao.TrainCreditNoteDao;
import com.lintas.action.CreditNote.Dao.VisaCreditNoteDao;
import com.lintas.action.CreditNote.modal.BusCreditNote;
import com.lintas.action.CreditNote.modal.CarCreditNote;
import com.lintas.action.CreditNote.modal.InsuranceCreditNote;
import com.lintas.action.CreditNote.modal.TrainCreditNote;
import com.lintas.action.CreditNote.modal.VisaCreditNote;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.common.model.CreditNote;
import com.lintas.admin.common.model.HotelCreditNote;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.CommonUtil;
import com.lintas.utility.DateConversion;
 

public class MyTransactionsDao {
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(MyTransactionsDao.class);
	/* load all super transactions based on company id*/

	public CompanyFilterPage getMySelfTransferHistory(CompanyFilterPage companyFilterPage)
	{
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(WalletAmountTranferHistory.class);
			Conjunction conjunction = Restrictions.conjunction();
			CompanyFilter companyFilter =null;
			if(companyFilterPage!=null && companyFilterPage.isFilterEnabled())
			{
				companyFilter = companyFilterPage.getCompanyFilter();

				if(companyFilter.getCompanyId()>0 )
				{
					Company  Company =new CompanyDAO().getCompanyProfile(companyFilter.getCompanyId());
					User user=null;
					if(Company!=null){
						user=new UserDAO().getUserPasswordForLock(Company.getEmail());
					}

					conjunction.add(Restrictions.eq("userId", user.getId()));
					logger.info("##########transactionType  added----"+companyFilter.getCompanyRoleType());
				}
				else{
					conjunction.add(Restrictions.eq("parentUserId", companyFilter.getLoginUser().getId()));
				}
				if(companyFilter.getCompanyRoleType() != null && !companyFilter.getCompanyRoleType().equals("") &&   !companyFilter.getCompanyRoleType().equals("ALL"))
				{
					conjunction.add(Restrictions.eq("transactionType", companyFilter.getCompanyRoleType()));
					logger.info("##########transactionType  added----"+companyFilter.getCompanyRoleType());
				}
				if(companyFilter.getCompanyId()>0 )
				{
					Company  Company =new CompanyDAO().getCompanyProfile(companyFilter.getCompanyId());
					User user=null;
					if(Company!=null){
						user=new UserDAO().getUserPasswordForLock(Company.getEmail());
					}

					conjunction.add(Restrictions.eq("userId", user.getId()));
					logger.info("##########transactionType  added----"+companyFilter.getCompanyRoleType());
				}



				if(companyFilter.getDateFilterCreated() != null && companyFilter.getDateFilterCreated().getDtEnd() != null && companyFilter.getDateFilterCreated().getDtStart() != null )
				{
					//2016-06-28
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(companyFilter.getDateFilterCreated().getDtStart());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						conjunction.add(Restrictions.ge("createdAt", date));
						logger.info("##########date min added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(companyFilter.getDateFilterCreated().getDtEnd());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						conjunction.add(Restrictions.lt("createdAt", date));
						logger.info("##########date max added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());
					}
				}

				criteria.add(conjunction);
				criteria.addOrder(Order.desc("id"));
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			List<WalletAmountTranferHistory> walletTxList =  null;
			if(rowCount>0)
			{

				if(companyFilterPage.getMaxItems()==Page.ALL_ITEMS){
					logger.info(":::: retriving all items for excel export------");
					criteria = session.createCriteria(WalletAmountTranferHistory.class);
					criteria.add(conjunction);
					criteria.addOrder(Order.desc("id"));
					walletTxList = criteria.list();
					logger.info(":::: retriving all items for excel export-----list-"+walletTxList);
					companyFilterPage.setAvailableItems(walletTxList.size());
					companyFilterPage.setAvailablePages(1);

				}
				else{
					if(companyFilterPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % companyFilterPage.getMaxItems() == 0)?(availableItems / companyFilterPage.getMaxItems()):((availableItems / companyFilterPage.getMaxItems()) + 1);
						companyFilterPage.setAvailableItems(availableItems);
						companyFilterPage.setAvailablePages(availablePages);
					} 
					int pageIndexDb = (companyFilterPage.getCurrentPageIndex() > 1)?companyFilterPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * companyFilterPage.getMaxItems();
					logger.info("setFirstResult-------"+itemIndex);
					if(itemIndex <= rowCount)
					{ 

						criteria = session.createCriteria(WalletAmountTranferHistory.class);
						criteria.add(conjunction);
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(companyFilterPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						walletTxList = criteria.list();
						logger.info("walletTxList size------"+walletTxList.size());					
					}
				}
				List<WalletAmountTranferHistory> newWalletTxList= new ArrayList<>();;
				if (walletTxList!=null && walletTxList.size()>0) {
					for (WalletAmountTranferHistory tranferHistory  : walletTxList){
						tranferHistory.setConvertDate(DateConversion.convertTimestampToStringwithoutseconds(tranferHistory.getCreatedAt()));
						tranferHistory.setAgencyName(getAgencyName(tranferHistory.getUserId(),session).getUsername());
						tranferHistory.setCompany_userid(getAgencyName(tranferHistory.getUserId(),session).getCompany_userid());
						if(tranferHistory.getParentUserId()>0) {
							tranferHistory.setParentcompany_userid(getAgencyName(tranferHistory.getParentUserId(),session).getUsername());
						}else{
							logger.info("tranferHistory.getCompany_userid()--"+tranferHistory.getCompany_userid()); 
							tranferHistory.setParentcompany_userid("-"); 
						}
						tranferHistory.setTransactionType(tranferHistory.getTransactionType());
						tranferHistory.setOpeningBalance(tranferHistory.getOpeningBalance().setScale(2, BigDecimal.ROUND_UP));
						tranferHistory.setClosingBalance(tranferHistory.getClosingBalance().setScale(2, BigDecimal.ROUND_UP));
						tranferHistory.setAmount(tranferHistory.getAmount().setScale(2, BigDecimal.ROUND_UP));
						tranferHistory.setParentOpeningBalance(tranferHistory.getParentOpeningBalance().setScale(2, BigDecimal.ROUND_UP));
						tranferHistory.setParentClosingBalance(tranferHistory.getParentClosingBalance().setScale(2, BigDecimal.ROUND_UP));
						newWalletTxList.add(tranferHistory);
					}
					companyFilterPage.setWalletTxList(newWalletTxList);
				}

				List<WalletAmountTranferHistory> newFilterWalletTxList=new ArrayList<>();
				if(companyFilter.getUserName() != null && !companyFilter.getUserName().equals(""))
				{
					if(companyFilterPage.getWalletTxList()!=null && companyFilterPage.getWalletTxList().size()>0){
						for(WalletAmountTranferHistory tranferHistory:companyFilterPage.getWalletTxList()){
							if(tranferHistory.getAgencyName().equals(companyFilter.getUserName())){
								newFilterWalletTxList.add(tranferHistory);
							}
						} 
						companyFilterPage.setWalletTxList(newFilterWalletTxList); 
						if(companyFilterPage.isPagination())
						{
							availableItems =companyFilterPage.getWalletTxList().size();
							availablePages = (availableItems % companyFilterPage.getMaxItems() == 0)?(availableItems / companyFilterPage.getMaxItems()):((availableItems / companyFilterPage.getMaxItems()) + 1);
							companyFilterPage.setAvailableItems(availableItems);
							companyFilterPage.setAvailablePages(availablePages);
						} 

					}

				} 



				logger.info("(----companyFilterPage.getWalletTxList()-- size-----)"+companyFilterPage.getWalletTxList().size());

			}	
			else
			{
				companyFilterPage.setAvailableItems(0);
				companyFilterPage.setAvailablePages(0);
				companyFilterPage.setWalletTxList(new ArrayList<WalletAmountTranferHistory>());
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
		return companyFilterPage;
	}



	public  CompanyFilterPage  getTransactionsHistory(CompanyFilterPage companyFilterPage){

		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(WalletAmountTranferHistory.class);
			Conjunction conjunction = Restrictions.conjunction();
			Disjunction disjunction=Restrictions.disjunction();
			CompanyFilter companyFilter =null;
			if(companyFilterPage!=null && companyFilterPage.isFilterEnabled())
			{
				companyFilter = companyFilterPage.getCompanyFilter();
				/*if(companyFilter.getUserName() != null && !companyFilter.getUserName().equals("") )
				{
					if(companyFilter.getUserId()!=0){
						conjunction.add(Restrictions.eq("userId", companyFilter.getUserId()));
						User user=new UserDAO().GetUserProfile(companyFilter.getUserId());
						if(user!=null){
							conjunction.add(Restrictions.eq("parentUserId",0));
						}

					}
					logger.info("User Id Based on company Name and user name:::::"+companyFilter.getUserName()+(companyFilter.getUserId()));

				}

				else{
					disjunction.add(Restrictions.eq("userId", companyFilter.getLoginUser().getId()));
					//disjunction.add(Restrictions.eq("parentUserId",companyFilter.getLoginUser().getId()));	
				}*/

				if(companyFilter.getCompanyId()>0 )
				{
					Company  Company =new CompanyDAO().getCompanyProfile(companyFilter.getCompanyId());
					User user=null;
					if(Company!=null){
						user=new UserDAO().getUserPasswordForLock(Company.getEmail());
					}

					conjunction.add(Restrictions.eq("userId", user.getId()));
					logger.info("##########transactionType  added----"+companyFilter.getCompanyRoleType());
				}
				else{
					disjunction.add(Restrictions.eq("userId", companyFilter.getLoginUser().getId()));
				}

				if(companyFilter.getCompanyRoleType() != null && !companyFilter.getCompanyRoleType().equals("") &&   !companyFilter.getCompanyRoleType().equals("ALL"))
				{
					conjunction.add(Restrictions.eq("transactionType", companyFilter.getCompanyRoleType()));
					logger.info("##########transactionType  added----"+companyFilter.getCompanyRoleType());
				}
				if(companyFilter.getOrderId() != null && !companyFilter.getOrderId().equals("") )
				{
					conjunction.add(Restrictions.eq("actionId", companyFilter.getOrderId()));
					logger.info("##########getOrderId  added----"+companyFilter.getOrderId());
				}

				if(companyFilter.getDateFilterCreated() != null && companyFilter.getDateFilterCreated().getDtEnd() != null && companyFilter.getDateFilterCreated().getDtStart() != null )
				{
					//2016-06-28
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(companyFilter.getDateFilterCreated().getDtStart());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						conjunction.add(Restrictions.ge("createdAt", date));
						logger.info("##########date min added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(companyFilter.getDateFilterCreated().getDtEnd());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						conjunction.add(Restrictions.lt("createdAt", date));
						logger.info("##########date max added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());
					}
				}
				/*disjunction.add(Restrictions.ne("action", "Payment received"));
				disjunction.add(Restrictions.ne("action", "Payment given"));*/
				criteria.add(disjunction);
				criteria.add(conjunction);
				criteria.addOrder(Order.desc("id"));
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			List<WalletAmountTranferHistory> walletTxList =  null;
			if(rowCount>0)
			{
				if(companyFilterPage.getMaxItems()==Page.ALL_ITEMS){
					logger.info(":::: retriving all items for excel export------");

					criteria = session.createCriteria(WalletAmountTranferHistory.class);
					criteria.add(disjunction);
					criteria.add(conjunction);
					criteria.addOrder(Order.desc("id"));
					walletTxList = criteria.list();
					logger.info(":::: retriving all items for excel export-----list-"+walletTxList);
					companyFilterPage.setAvailableItems(walletTxList.size());
					companyFilterPage.setAvailablePages(1);

				}
				else{
					if(companyFilterPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % companyFilterPage.getMaxItems() == 0)?(availableItems / companyFilterPage.getMaxItems()):((availableItems / companyFilterPage.getMaxItems()) + 1);
						companyFilterPage.setAvailableItems(availableItems);
						companyFilterPage.setAvailablePages(availablePages);
					} 
					int pageIndexDb = (companyFilterPage.getCurrentPageIndex() > 1)?companyFilterPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * companyFilterPage.getMaxItems();
					logger.info("setFirstResult-------"+itemIndex);

					if(itemIndex <= rowCount)
					{ 
						criteria = session.createCriteria(WalletAmountTranferHistory.class);
						criteria.add(disjunction);
						criteria.add(conjunction);
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(companyFilterPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						walletTxList = criteria.list();
						logger.info("walletTxList size------"+walletTxList.size());					
					}
				}
				List<WalletAmountTranferHistory> newWalletTxList= new ArrayList<>();
				BigDecimal outStandingBalance=new BigDecimal(0);
				if (walletTxList!=null && walletTxList.size()>0) {
					for (WalletAmountTranferHistory tranferHistory  : walletTxList){
						tranferHistory.setConvertDate(DateConversion.convertTimestampToStringwithoutseconds(tranferHistory.getCreatedAt()));
						tranferHistory.setAgencyName(getAgencyName(tranferHistory.getUserId(),session).getUsername());
						tranferHistory.setCompany_userid(getAgencyName(tranferHistory.getUserId(),session).getUsername());
						if(tranferHistory.getInvoiceNo()== null) 
							tranferHistory.setInvoiceNo("-");

						//tranferHistory.setParentcompany_userid(getAgencyName(tranferHistory.getParentUserId(),session).getCompany_userid());
						//tranferHistory.setCompanyName(companyFilter.getCompanyName());
						BigDecimal amount=tranferHistory.getAmount().setScale(2, BigDecimal.ROUND_UP);
						/*if(tranferHistory.getAction().endsWith("deducted")) 
							amount=tranferHistory.getAmount().negate().setScale(2, BigDecimal.ROUND_UP);*/

						if(tranferHistory.getAction().endsWith("collected")) 
							amount=tranferHistory.getAmount().negate().setScale(2, BigDecimal.ROUND_UP);



						tranferHistory.setAmount(amount);
						outStandingBalance=outStandingBalance.add(tranferHistory.getAmount());
						tranferHistory.setOutStandingBalance(outStandingBalance);
						tranferHistory.setOpeningBalance(tranferHistory.getOpeningBalance().setScale(2, BigDecimal.ROUND_UP)); 
						tranferHistory.setClosingBalance(tranferHistory.getClosingBalance().setScale(2, BigDecimal.ROUND_UP));
						tranferHistory.setParentOpeningBalance(tranferHistory.getParentOpeningBalance().setScale(2, BigDecimal.ROUND_UP));
						tranferHistory.setParentClosingBalance(tranferHistory.getParentClosingBalance().setScale(2, BigDecimal.ROUND_UP)); 
						BigDecimal open=null;
						BigDecimal close=null;
						if(tranferHistory.getAction()!=null && tranferHistory.getAction().equalsIgnoreCase("Taken") ){
							tranferHistory.setAction(tranferHistory.getTransactionType());
						}


						if(companyFilter.getUserName() != null && !companyFilter.getUserName().equals("") )
						{ 
							open=tranferHistory.getOpeningBalance().setScale(2, BigDecimal.ROUND_UP);
							close=tranferHistory.getClosingBalance().setScale(2, BigDecimal.ROUND_UP);
							tranferHistory.setOpeningBalance(open.setScale(2, BigDecimal.ROUND_UP)); 
							tranferHistory.setClosingBalance(close.setScale(2, BigDecimal.ROUND_UP));

						}
						else if(tranferHistory.getUserId()!=companyFilter.getLoginUser().getId())
						{
							open=tranferHistory.getParentOpeningBalance().setScale(2, BigDecimal.ROUND_UP);
							close=tranferHistory.getParentClosingBalance().setScale(2, BigDecimal.ROUND_UP);
							tranferHistory.setOpeningBalance(open.setScale(2, BigDecimal.ROUND_UP)); 
							tranferHistory.setClosingBalance(close.setScale(2, BigDecimal.ROUND_UP));

						}  
						newWalletTxList.add(tranferHistory); 
					}
					companyFilterPage.setWalletTxList(newWalletTxList);

					logger.info("(----companyFilterPage.getWalletTxList()-- size-----)"+companyFilterPage.getWalletTxList().size());
				}	


			}
		}
		catch (HibernateException e) {
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
		return companyFilterPage;

	}
	public  CompanyFilterPage  getLedgerReport(CompanyFilterPage companyFilterPage){

		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(WalletAmountTranferHistory.class);
			Conjunction conjunction = Restrictions.conjunction();
			Disjunction disjunction=Restrictions.disjunction();

			CompanyFilter companyFilter =null;
			if(companyFilterPage!=null && companyFilterPage.isFilterEnabled())
			{
				companyFilter = companyFilterPage.getCompanyFilter();
				if(companyFilter.getCompanyId()>0)
					conjunction.add(Restrictions.eq("companyId", companyFilter.getCompanyId()));
				else if(companyFilter.getUserName() != null && !companyFilter.getUserName().equals("") )
				{
					if(companyFilter.getUserId()!=0){
						conjunction.add(Restrictions.eq("userId", companyFilter.getUserId()));
						User user=new UserDAO().GetUserProfile(companyFilter.getUserId());
						if(user!=null){
							conjunction.add(Restrictions.eq("parentUserId",0));
						}

					}
				}

				else 
					conjunction.add(Restrictions.eq("userId", companyFilter.getLoginUser().getId()));

				if(companyFilter.getOrderId() != null && !companyFilter.getOrderId().equals("") )
					conjunction.add(Restrictions.eq("actionId", companyFilter.getOrderId()));


				if(companyFilter.getDateFilterCreated() != null && companyFilter.getDateFilterCreated().getDtEnd() != null && companyFilter.getDateFilterCreated().getDtStart() != null )
				{
					//2016-06-28
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(companyFilter.getDateFilterCreated().getDtStart());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						conjunction.add(Restrictions.ge("createdAt", date));

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(companyFilter.getDateFilterCreated().getDtEnd());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						conjunction.add(Restrictions.lt("createdAt", date));

					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());
					}
				}

				disjunction.add(Restrictions.eq("action", "Train Booking payment"));
				disjunction.add(Restrictions.eq("action", "Visa Booking payment"));
				disjunction.add(Restrictions.eq("action", "Bus Booking payment"));
				disjunction.add(Restrictions.eq("action", "Flight Booking payment"));
				disjunction.add(Restrictions.eq("action", "Hotel Booking payment"));
				disjunction.add(Restrictions.eq("action", "Car Booking payment"));
				disjunction.add(Restrictions.eq("action", "Insurance Booking payment"));
				disjunction.add(Restrictions.eq("action", "Miscellaneous Booking payment"));
				disjunction.add(Restrictions.eq("action", "Payment received"));
				disjunction.add(Restrictions.eq("action", "Payment given"));
				disjunction.add(Restrictions.eq("action", "Deposited"));
				disjunction.add(Restrictions.eq("action", "Taken"));
				disjunction.add(Restrictions.eq("action", "taken")); 
				criteria.add(conjunction).add(disjunction);
				/*criteria.addOrder(Order.desc("id"));*/
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			List<WalletAmountTranferHistory> walletTxList =  null;
			if(rowCount>0)
			{
				if(companyFilterPage.getMaxItems()==Page.ALL_ITEMS){
					criteria = session.createCriteria(WalletAmountTranferHistory.class);
					disjunction.add(Restrictions.eq("action", "Train Booking payment"));
					disjunction.add(Restrictions.eq("action", "Visa Booking payment"));
					disjunction.add(Restrictions.eq("action", "Bus Booking payment"));
					disjunction.add(Restrictions.eq("action", "Flight Booking payment"));
					disjunction.add(Restrictions.eq("action", "Hotel Booking payment"));
					disjunction.add(Restrictions.eq("action", "Car Booking payment"));
					disjunction.add(Restrictions.eq("action", "Insurance Booking payment"));
					disjunction.add(Restrictions.eq("action", "Miscellaneous Booking payment"));
					disjunction.add(Restrictions.eq("action", "Payment received"));
					disjunction.add(Restrictions.eq("action", "Payment given"));
					disjunction.add(Restrictions.eq("action", "Deposited"));
					disjunction.add(Restrictions.eq("action", "Taken"));
					disjunction.add(Restrictions.eq("action", "taken")); 
					criteria.add(conjunction).add(disjunction);
					walletTxList = criteria.list();
					companyFilterPage.setAvailableItems(walletTxList.size());
					companyFilterPage.setAvailablePages(1);

				}
				else{
					if(companyFilterPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % companyFilterPage.getMaxItems() == 0)?(availableItems / companyFilterPage.getMaxItems()):((availableItems / companyFilterPage.getMaxItems()) + 1);
						companyFilterPage.setAvailableItems(availableItems);
						companyFilterPage.setAvailablePages(availablePages);
					} 
					int pageIndexDb = (companyFilterPage.getCurrentPageIndex() > 1)?companyFilterPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * companyFilterPage.getMaxItems();
					if(itemIndex <= rowCount)
					{ 
						criteria = session.createCriteria(WalletAmountTranferHistory.class);
						disjunction.add(Restrictions.eq("action", "Train Booking payment"));
						disjunction.add(Restrictions.eq("action", "Visa Booking payment"));
						disjunction.add(Restrictions.eq("action", "Bus Booking payment"));
						disjunction.add(Restrictions.eq("action", "Flight Booking payment"));
						disjunction.add(Restrictions.eq("action", "Hotel Booking payment"));
						disjunction.add(Restrictions.eq("action", "Car Booking payment"));
						disjunction.add(Restrictions.eq("action", "Insurance Booking payment"));
						disjunction.add(Restrictions.eq("action", "Miscellaneous Booking payment"));
						disjunction.add(Restrictions.eq("action", "Payment received"));
						disjunction.add(Restrictions.eq("action", "Payment given"));
						disjunction.add(Restrictions.eq("action", "Deposited"));
						disjunction.add(Restrictions.eq("action", "Taken"));
						disjunction.add(Restrictions.eq("action", "taken"));
						criteria.add(conjunction).add(disjunction);
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(companyFilterPage.getMaxItems());
						/*	criteria.addOrder(Order.desc("id"));*/
						walletTxList = criteria.list();
					}
				}
				List<WalletAmountTranferHistory> newWalletTxList= new ArrayList<>();
				if (walletTxList!=null && walletTxList.size()>0) {
					for (WalletAmountTranferHistory tranferHistory  : walletTxList){
						if(!tranferHistory.isFailed()){
						
						//tranferHistory.setConvertDate(DateConversion.convertTimestampToStringwithoutseconds(tranferHistory.getCreatedAt()));
						tranferHistory.setAgencyName(getAgencyName(tranferHistory.getUserId(),session).getUsername());
						//tranferHistory.setCompany_userid(getAgencyName(tranferHistory.getUserId(),session).getCompany_userid());
						//tranferHistory.setParentcompany_userid(getAgencyName(tranferHistory.getParentUserId(),session).getCompany_userid());
						BigDecimal open=null;
						BigDecimal close=null;
						String invoiceNo=tranferHistory.getInvoiceNo();
						if(tranferHistory.getAction()!=null && tranferHistory.getAction().equalsIgnoreCase("Deposited") || tranferHistory.getAction().equalsIgnoreCase("taken")) 
							invoiceNo=tranferHistory.getActionId();
						tranferHistory.setInvoiceNo(invoiceNo);
						tranferHistory.setAmount(tranferHistory.getAmount().setScale(2, BigDecimal.ROUND_UP));
						tranferHistory.setOpeningBalance(tranferHistory.getOpeningBalance().setScale(2, BigDecimal.ROUND_UP)); 
						tranferHistory.setClosingBalance(tranferHistory.getClosingBalance().setScale(2, BigDecimal.ROUND_UP));
						tranferHistory.setParentOpeningBalance(tranferHistory.getParentOpeningBalance().setScale(2, BigDecimal.ROUND_UP));
						tranferHistory.setParentClosingBalance(tranferHistory.getParentClosingBalance().setScale(2, BigDecimal.ROUND_UP)); 
						String voucherType="Invoice";
						if(tranferHistory.getAction()!=null && (tranferHistory.getAction().equalsIgnoreCase("Taken") || tranferHistory.getAction().equalsIgnoreCase("Deposited") ) ){
							tranferHistory.setRemarks(tranferHistory.getTransactionType());
							voucherType="Credit limit";
						} 
						tranferHistory.setVoucherType(voucherType);
						if((companyFilter.getUserName() != null && !companyFilter.getUserName().equals("")) || companyFilter.getCompanyId()>0)
						{ 
							open=tranferHistory.getOpeningBalance().setScale(2, BigDecimal.ROUND_UP);
							close=tranferHistory.getClosingBalance().setScale(2, BigDecimal.ROUND_UP);
							tranferHistory.setOpeningBalance(open.setScale(2, BigDecimal.ROUND_UP)); 
							tranferHistory.setClosingBalance(close.setScale(2, BigDecimal.ROUND_UP));
						}
						else if(tranferHistory.getUserId()!=companyFilter.getLoginUser().getId())
						{
							open=tranferHistory.getParentOpeningBalance().setScale(2, BigDecimal.ROUND_UP);
							close=tranferHistory.getParentClosingBalance().setScale(2, BigDecimal.ROUND_UP);
							tranferHistory.setOpeningBalance(open.setScale(2, BigDecimal.ROUND_UP)); 
							tranferHistory.setClosingBalance(close.setScale(2, BigDecimal.ROUND_UP));
						}  
						newWalletTxList.add(tranferHistory); 
						WalletAmountTranferHistory tranferHistoryNew=buildCancellationData(tranferHistory,companyFilter);
						if(tranferHistoryNew!=null)
							newWalletTxList.add(tranferHistoryNew); 
					}
					}
					newWalletTxList=buildMergingListData(newWalletTxList);
					companyFilterPage.setOutstandingReportList(newWalletTxList);
					logger.info("(----companyFilterPage.getWalletTxList()-- size-----)"+companyFilterPage.getWalletTxList().size());
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
		return companyFilterPage;
	}

	public  CompanyFilterPage  getKnockOffReports(CompanyFilterPage companyFilterPage){
		PaymentKnockDao paymentKnockDao=new PaymentKnockDaoImpl();
		List<KnockOffVO> allKnockOffVOReports=new ArrayList<>();
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		BigDecimal totBrvAmount = new BigDecimal(0);
		WalletAmountTranferHistory walletAmountTranferHistory=null;
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		List<PaymentKnockOffRow> paymentKnockOffRowList=null;
		if((companyFilter.getOrderId() != null && !companyFilter.getOrderId().equals("")) &&  companyFilter.getCompanyId()>0){
			walletAmountTranferHistory=getWalletHistoryByBRVwithCompanyId(companyFilter.getCompanyId(), companyFilter.getOrderId());
			paymentKnockOffRowList=paymentKnockDao.fetchPaymentKnockOffRowList(companyFilter.getCompanyId());
		}
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(WalletAmountTranferHistory.class);
			Conjunction conjunction = Restrictions.conjunction();
			Disjunction disjunction=Restrictions.disjunction();
			if(walletAmountTranferHistory!=null && walletAmountTranferHistory.getActionId()!=null){
				walletAmountTranferHistory.setBRVorCRVAmount(walletAmountTranferHistory.getAmount());
				if(paymentKnockOffRowList!=null && paymentKnockOffRowList.size()>0){
					for(PaymentKnockOffRow paymentKnockOffRow:paymentKnockOffRowList){
						if(paymentKnockOffRow.getPaymentKnockOffRowTxs()!=null && paymentKnockOffRow.getPaymentKnockOffRowTxs().size()>0){
							for(PaymentKnockOffRowTx paymentKnockOffRowTx:paymentKnockOffRow.getPaymentKnockOffRowTxs()){
								if(paymentKnockOffRowTx.getBRVorCRV().equalsIgnoreCase(walletAmountTranferHistory.getActionId())){
									totBrvAmount=totBrvAmount.add(paymentKnockOffRowTx.getAmount());
								}
								
							}
						}
					}
				}	
				if(totBrvAmount.compareTo(new BigDecimal(0))>0)
					totBrvAmount=walletAmountTranferHistory.getAmount().subtract(totBrvAmount);
				else 
					totBrvAmount=walletAmountTranferHistory.getAmount();
				walletAmountTranferHistory.setBRVorCRVSpentAmount(walletAmountTranferHistory.getBRVorCRVAmount().subtract(totBrvAmount));
				walletAmountTranferHistory.setRestAmount(totBrvAmount);
				walletAmountTranferHistory.setAmount(totBrvAmount);
				if(companyFilterPage!=null && companyFilterPage.isFilterEnabled())
				{
					conjunction.add(Restrictions.eq("companyId", companyFilter.getCompanyId()));
					if(companyFilter.getDateFilterCreated() != null && companyFilter.getDateFilterCreated().getDtEnd() != null && companyFilter.getDateFilterCreated().getDtStart() != null )
					{
						//2016-06-28
						SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
						DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
						try{
							Date date = originalFormat.parse(companyFilter.getDateFilterCreated().getDtStart());
							String formattedDate = targetFormat.format(date); 
							date = targetFormat.parse(formattedDate);
							date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
							conjunction.add(Restrictions.ge("createdAt", date));

						}catch(Exception ex)
						{
							logger.info("##########date min format exception---"+ex.getMessage());

						}
						try{
							Date date = originalFormat.parse(companyFilter.getDateFilterCreated().getDtEnd());
							String formattedDate = targetFormat.format(date); 
							date = targetFormat.parse(formattedDate);
							date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
							conjunction.add(Restrictions.lt("createdAt", date));

						}catch(Exception ex)
						{
							logger.info("##########date max format exception---"+ex.getMessage());
						}
					}
					disjunction.add(Restrictions.eq("action", "Train Booking payment"));
					disjunction.add(Restrictions.eq("action", "Visa Booking payment"));
					disjunction.add(Restrictions.eq("action", "Bus Booking payment"));
					disjunction.add(Restrictions.eq("action", "Flight Booking payment"));
					disjunction.add(Restrictions.eq("action", "Hotel Booking payment"));
					disjunction.add(Restrictions.eq("action", "Car Booking payment"));
					disjunction.add(Restrictions.eq("action", "Insurance Booking payment"));
					disjunction.add(Restrictions.eq("action", "Miscellaneous Booking payment"));
					/*disjunction.add(Restrictions.eq("action", "Payment received"));
					disjunction.add(Restrictions.eq("action", "Payment given"));*/
					criteria.add(conjunction).add(disjunction);
					/*criteria.addOrder(Order.desc("id"));*/
				}
				Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
				List<WalletAmountTranferHistory> walletTxList =  null;
				if(rowCount>0)
				{
					if(companyFilterPage.getMaxItems()==Page.ALL_ITEMS){
						criteria = session.createCriteria(WalletAmountTranferHistory.class);
						disjunction.add(Restrictions.eq("action", "Train Booking payment"));
						disjunction.add(Restrictions.eq("action", "Visa Booking payment"));
						disjunction.add(Restrictions.eq("action", "Bus Booking payment"));
						disjunction.add(Restrictions.eq("action", "Flight Booking payment"));
						disjunction.add(Restrictions.eq("action", "Hotel Booking payment"));
						disjunction.add(Restrictions.eq("action", "Car Booking payment"));
						disjunction.add(Restrictions.eq("action", "Insurance Booking payment"));
						disjunction.add(Restrictions.eq("action", "Miscellaneous Booking payment"));
						/*disjunction.add(Restrictions.eq("action", "Payment received"));
						disjunction.add(Restrictions.eq("action", "Payment given"));*/
						criteria.add(conjunction).add(disjunction);
						walletTxList = criteria.list();
						companyFilterPage.setAvailableItems(walletTxList.size());
						companyFilterPage.setAvailablePages(1);

					}
					else{
						if(companyFilterPage.isPagination())
						{
							availableItems = rowCount.intValue();
							availablePages = (availableItems % companyFilterPage.getMaxItems() == 0)?(availableItems / companyFilterPage.getMaxItems()):((availableItems / companyFilterPage.getMaxItems()) + 1);
							companyFilterPage.setAvailableItems(availableItems);
							companyFilterPage.setAvailablePages(availablePages);
						} 
						int pageIndexDb = (companyFilterPage.getCurrentPageIndex() > 1)?companyFilterPage.getCurrentPageIndex() -1 : 0;
						int itemIndex = pageIndexDb * companyFilterPage.getMaxItems();
						if(itemIndex <= rowCount)
						{ 
							criteria = session.createCriteria(WalletAmountTranferHistory.class);
							disjunction.add(Restrictions.eq("action", "Train Booking payment"));
							disjunction.add(Restrictions.eq("action", "Visa Booking payment"));
							disjunction.add(Restrictions.eq("action", "Bus Booking payment"));
							disjunction.add(Restrictions.eq("action", "Flight Booking payment"));
							disjunction.add(Restrictions.eq("action", "Hotel Booking payment"));
							disjunction.add(Restrictions.eq("action", "Car Booking payment"));
							disjunction.add(Restrictions.eq("action", "Insurance Booking payment"));
							disjunction.add(Restrictions.eq("action", "Miscellaneous Booking payment"));
							/*	disjunction.add(Restrictions.eq("action", "Payment received"));
							disjunction.add(Restrictions.eq("action", "Payment given"));*/
							criteria.add(conjunction).add(disjunction);
							criteria.setFirstResult(itemIndex);
							criteria.setMaxResults(companyFilterPage.getMaxItems());
							/*	criteria.addOrder(Order.desc("id"));*/
							walletTxList = criteria.list();
						}
					}
					if (walletTxList!=null && walletTxList.size()>0) {
						for (WalletAmountTranferHistory tranferHistory  : walletTxList){
							if(!tranferHistory.isFailed()){
							KnockOffVO knockOffVO=new KnockOffVO();
							knockOffVO.setAmount(new BigDecimal(0));
							knockOffVO.setBillDate(DateConversion.convertTimestampToString(tranferHistory.getCreatedAt()));
							knockOffVO.setBillNo(tranferHistory.getInvoiceNo());
							knockOffVO.setBillAmount(tranferHistory.getAmount());
							knockOffVO.setBookingRef(tranferHistory.getActionId());
							knockOffVO.setId(tranferHistory.getId());
							String bookingDate=null;
							String itineraryType="-";
							String GDSorLCC=null;

							switch(CommonUtil.findStringFromString(tranferHistory.getInvoiceNo())){
							case "TYA":
								FlightOrderRow flightOrderRow =new FlightOrderDao().getFlightOrderRowDataById(CommonUtil.findDigitsFromString(tranferHistory.getInvoiceNo()));
								if(flightOrderRow!=null){
									bookingDate=flightOrderRow.getBookingDate()!=null?DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getBookingDate()):"-";
									boolean isIsInternational=CommonDsrDao.isDomesticOrInternational(flightOrderRow.getDestination(),flightOrderRow.getOrigin());
									if(isIsInternational)
										itineraryType="International Flight";
									else
										itineraryType="Domestic Flight";
									GDSorLCC="Air";
								}
								break;
							case "TYH":
								HotelOrderRow hotelOrderRow =new HotelOrderDao().getHotelOrderRowInfo(CommonUtil.findDigitsFromString(tranferHistory.getInvoiceNo()));
								if(hotelOrderRow!=null){
									bookingDate=hotelOrderRow.getBookingDate()!=null?DateConversion.getBluestarDateddMMyyyy(hotelOrderRow.getBookingDate()):"-";
									String country=hotelOrderRow.getHotelOrderHotelData().getCountry()!=null?hotelOrderRow.getHotelOrderHotelData().getCountry():"India";
									itineraryType=country.equalsIgnoreCase("India") ?"Domestic Hotel":"International Hotel";
									GDSorLCC="Hotel";
								}
								break;
							case "TYC":
								CarOrderRow carOrderRow =new CarOrderDao().getCarOrderRowDetail(CommonUtil.findDigitsFromString(tranferHistory.getInvoiceNo()));
								if(carOrderRow!=null){
									bookingDate=carOrderRow.getCarBookingDate(); 	
									GDSorLCC="Car";
								}
								break;

							case "TYB":
								BusOrderRow busOrderRow =new BusOrderDao().getBusOrderRowDetail(CommonUtil.findDigitsFromString(tranferHistory.getInvoiceNo()));
								if(busOrderRow!=null){
									bookingDate=busOrderRow.getBusBookingDate()	;
									GDSorLCC="Bus";
								}
								break;
							case "TYT":
								TrainOrderRow trainOrderRow  =new TrainOrderDao().getTrainOrderRowDetail(CommonUtil.findDigitsFromString(tranferHistory.getInvoiceNo()));
								if(trainOrderRow!=null){
									bookingDate=trainOrderRow.getTrainBookingDate();
									GDSorLCC="Train";
								}
								break;
							case "TYV":
								VisaOrderRow visaOrderRow  =new VisaOrderDao().getVisaOrderRowDetail(CommonUtil.findDigitsFromString(tranferHistory.getInvoiceNo()));
								if(visaOrderRow!=null){
									bookingDate=visaOrderRow.getVisaBookingDate(); 	 
									GDSorLCC="Visa";
								}
								break;
							case "TYI":
								InsuranceOrderRow insuranceOrderRow  =new InsuranceOrderDao().getInsuranceOrderRowDetail(CommonUtil.findDigitsFromString(tranferHistory.getInvoiceNo()));
								if(insuranceOrderRow!=null){
									bookingDate=insuranceOrderRow.getInsuranceBookingDate(); 
									GDSorLCC="Insurance";
								}
								break;


							case "TYM":
								MiscellaneousOrderRow miscellaneousOrderRow  =new MiscellaneousOrderDao().getMiscellaneousOrderRowById(CommonUtil.findDigitsFromString(tranferHistory.getInvoiceNo()));
								if(miscellaneousOrderRow!=null){
									bookingDate=miscellaneousOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringDate(miscellaneousOrderRow.getBookingDate()):"-";
									GDSorLCC="Miscellaneous";
								}
								break;
							default:
								bookingDate="-";
								itineraryType="-";
								GDSorLCC="-";
								break;
							}
							knockOffVO.setUserId(tranferHistory.getUserId());
							knockOffVO.setBookingDate(bookingDate);
							knockOffVO.setGDSorLCC(GDSorLCC);
							knockOffVO.setBookingType(itineraryType);
							knockOffVO.setCompanyId(tranferHistory.getCompanyId());
							knockOffVO.setInvoiceType("Invoice");
							BigDecimal totAmount=new BigDecimal(0);
							PaymentKnockOffRow paymentKnockOffRow=  paymentKnockDao.fetchPaymentKnockOffRow(tranferHistory.getInvoiceNo(), tranferHistory.getCompanyId());
							Set<String> BRVorCRVList= null;
							if(paymentKnockOffRow!=null){
								BRVorCRVList  =new HashSet<>();
								if(paymentKnockOffRow.getPaymentKnockOffRowTxs()!=null && paymentKnockOffRow.getPaymentKnockOffRowTxs().size()>0){
									for(PaymentKnockOffRowTx paymentKnockOffRowTx:paymentKnockOffRow.getPaymentKnockOffRowTxs()){
										totAmount=totAmount.add(paymentKnockOffRowTx.getAmount());
										BRVorCRVList.add(paymentKnockOffRowTx.getBRVorCRV());
									}
								}
								 
							}
							if(totAmount.compareTo(new BigDecimal(0))>0){
								knockOffVO.setKnockedOffAmount(totAmount);
								knockOffVO.setBalanceAmount(tranferHistory.getAmount().subtract(totAmount)); 
							}
							else{
								knockOffVO.setBalanceAmount(tranferHistory.getAmount());
								knockOffVO.setKnockedOffAmount(new BigDecimal(0));
							} 
							knockOffVO.setBRVorCRVList(BRVorCRVList);
							allKnockOffVOReports.add(knockOffVO);
							KnockOffVO knockOffVONew=buildKnockOffCancellationData(knockOffVO,paymentKnockDao);
							if(knockOffVONew!=null){
								knockOffVONew.setInvoiceType("CreditNote");
							allKnockOffVOReports.add(knockOffVONew);
							}
						}
					}
						companyFilterPage.setWalletAmountTranferHistory(walletAmountTranferHistory);
						companyFilterPage.setAllKnockOffVOReports(allKnockOffVOReports);
						logger.info("(----companyFilterPage.getWalletTxList()-- size-----)"+companyFilterPage.getWalletTxList().size());
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


		return companyFilterPage;
	}


	public WalletAmountTranferHistory GetWalletHistoryById(String actionId,User user){		
		WalletAmountTranferHistory walletAmountTranferHistory = new WalletAmountTranferHistory();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(WalletAmountTranferHistory.class);
			criteria.add(Restrictions.eq("actionId", actionId));
			WalletAmountTranferHistory walletAmountTranferdetail = (WalletAmountTranferHistory) criteria.uniqueResult();
			walletAmountTranferHistory.setTransactionType(walletAmountTranferdetail.getTransactionType());
			walletAmountTranferHistory.setActionId(walletAmountTranferdetail.getActionId());
			walletAmountTranferHistory.setConvertDate(DateConversion.convertTimestampToStringwithoutseconds(walletAmountTranferdetail.getCreatedAt()));
			walletAmountTranferHistory.setAgencyName(getAgencyName(walletAmountTranferdetail.getUserId(),session).getUsername());
			walletAmountTranferHistory.setCompany_userid(getAgencyName(walletAmountTranferdetail.getUserId(),session).getCompany_userid());
			//tranferHistory.setParentcompany_userid(getAgencyName(tranferHistory.getParentUserId(),session).getCompany_userid());
			walletAmountTranferHistory.setAmount(walletAmountTranferdetail.getAmount().setScale(2, BigDecimal.ROUND_UP));
			walletAmountTranferHistory.setOpeningBalance(walletAmountTranferdetail.getOpeningBalance().setScale(2, BigDecimal.ROUND_UP)); 
			walletAmountTranferHistory.setClosingBalance(walletAmountTranferdetail.getClosingBalance().setScale(2, BigDecimal.ROUND_UP));
			walletAmountTranferHistory.setParentOpeningBalance(walletAmountTranferdetail.getParentOpeningBalance().setScale(2, BigDecimal.ROUND_UP));
			walletAmountTranferHistory.setParentClosingBalance(walletAmountTranferdetail.getParentClosingBalance().setScale(2, BigDecimal.ROUND_UP));
			walletAmountTranferHistory.setCurrency(walletAmountTranferdetail.getCurrency());
			walletAmountTranferHistory.setRemarks(walletAmountTranferdetail.getRemarks());
			if(walletAmountTranferHistory.getUserId() != user.getId())
			{
				walletAmountTranferHistory.setOpeningBalance(walletAmountTranferdetail.getParentOpeningBalance().setScale(2, BigDecimal.ROUND_UP)); 
				walletAmountTranferHistory.setClosingBalance(walletAmountTranferdetail.getParentClosingBalance().setScale(2, BigDecimal.ROUND_UP));
			}

		}catch(HibernateException e){
			logger.error("------------GetWalletHistoryById HibernateException---------------"+e.getMessage());
		}		
		return walletAmountTranferHistory;
	}

	public List<WalletAmountTranferHistory> getMySelfTransferHistory1(User sessionObj)
	{
		List<WalletAmountTranferHistory> mySelfHistoryList=new ArrayList<WalletAmountTranferHistory>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from WalletAmountTranferHistory wt where  wt.parentUserId=:parent_userid order by wt.id desc";
			Query query = session.createQuery(sql);
			query.setParameter("parent_userid", sessionObj.getId());
			List<WalletAmountTranferHistory> list=query.list();
			if (list!=null && list.size()>0) {
				for (WalletAmountTranferHistory tranferHistory  : list){
					tranferHistory.setCreatedAt(tranferHistory.getCreatedAt());
					tranferHistory.setAgencyName(getAgencyName(tranferHistory.getUserId(),session).getUsername());
					tranferHistory.setCompany_userid(getAgencyName(tranferHistory.getUserId(),session).getCompany_userid());
					tranferHistory.setParentcompany_userid(getAgencyName(tranferHistory.getParentUserId(),session).getCompany_userid());
					tranferHistory.setTransactionType(tranferHistory.getTransactionType());
					tranferHistory.setOpeningBalance(tranferHistory.getOpeningBalance().setScale(2, BigDecimal.ROUND_UP));
					tranferHistory.setClosingBalance(tranferHistory.getClosingBalance().setScale(2, BigDecimal.ROUND_UP));
					tranferHistory.setAmount(tranferHistory.getAmount().setScale(2, BigDecimal.ROUND_UP));
					tranferHistory.setParentOpeningBalance(tranferHistory.getParentOpeningBalance().setScale(2, BigDecimal.ROUND_UP));
					tranferHistory.setParentClosingBalance(tranferHistory.getParentClosingBalance().setScale(2, BigDecimal.ROUND_UP));
					mySelfHistoryList.add(tranferHistory);
				}
			}
		}
		catch(HibernateException e){
			logger.error("------------HibernateException---------------"+e.getMessage());
		}catch (Exception e) {
			logger.error("------------Exception---------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return mySelfHistoryList; 

	}

	private User getAgencyName(int userId,Session session) {
		// TODO Auto-generated method stub
		String sql = "from User u where u.id=:id";
		Query query = session.createQuery(sql);
		query.setParameter("id", userId);
		User user=(User) query.uniqueResult();
		return user; 
	}
	private Map<Object,String>  getRefundAmount(String orderId ,int userId) {
		Map<Object,String>  map=new LinkedHashMap<>();
		FlightOrderRow flightOrderRow=null;
		HotelOrderRow hotelOrderRow=null;
		BusOrderRow busOrderRow=null;
		CarOrderRow carOrderRow=null;
		TrainOrderRow trainOrderRow=null;
		VisaOrderRow visaOrderRow=null;
		InsuranceOrderRow insuranceOrderRow=null;
		MiscellaneousOrderRow  miscellaneousOrderRow=null;
		switch(CommonUtil.findStringFromString(orderId)){
		case "TYA":
			flightOrderRow=new FlightOrderDao().getFlightOrderRowDetail(orderId);
			if(flightOrderRow!=null){
				CreditNote flightCreditNote=new  CreditNoteDao().getCreditNoteDetailsByUserId(userId, flightOrderRow.getId());
				if(flightCreditNote!=null) 
					map.put(flightCreditNote, "TYA");
			 
			}
			break;
		case "TYB":
			busOrderRow=new BusOrderDao().getBusOrderRowDetail(orderId);
			if(busOrderRow!=null){
				BusCreditNote busCreditNote=new  BusCreditNoteDao().getCreditNoteDetailsByUserId(userId, busOrderRow.getId());
				if(busCreditNote!=null) 
				map.put(busCreditNote, "TYB");
			}
			break;
		case "TYC":
			carOrderRow=new CarOrderDao().getCarOrderRowDetail(orderId);
			if(carOrderRow!=null){
				CarCreditNote carCreditNote=new  CarCreditNoteDao().getCreditNoteDetailsByUserId(userId, carOrderRow.getId());
				if(carCreditNote!=null) 
				map.put(carCreditNote, "TYC");
			}
			break;
		case "TYH":
			hotelOrderRow=new HotelOrderDao().getHotelOrderRowDetail(orderId);
			if(hotelOrderRow!=null){
				HotelCreditNote hotelCreditNote=new  HotelCreditNoteDao().getCreditNoteDetailsByUserId(userId, hotelOrderRow.getId());
				if(hotelCreditNote!=null)
				map.put(hotelCreditNote, "TYH");
			}
			break;
		case "TYI":
			insuranceOrderRow=new InsuranceOrderDao().getInsuranceOrderRowDetail(orderId);
			if(insuranceOrderRow!=null){
				InsuranceCreditNote insuranceCreditNote=new  InsuranceCreditNoteDao().getCreditNoteDetailsByUserId(userId, insuranceOrderRow.getId());
				if(insuranceCreditNote!=null)
				map.put(insuranceCreditNote, "TYI");
			}
			break;
		case "TYT":
			trainOrderRow=new TrainOrderDao().getTrainOrderRowDetail(orderId);
			if(trainOrderRow!=null){
				TrainCreditNote trainCreditNote=new TrainCreditNoteDao().getCreditNoteDetailsByUserId(userId, trainOrderRow.getId());
				if(trainCreditNote!=null)
				map.put(trainCreditNote, "TYT");
			}
			break;
		case "TYV":
			visaOrderRow=new VisaOrderDao().getVisaOrderRowDetail(orderId);
			if(visaOrderRow!=null){
				VisaCreditNote  visaCreditNote=new VisaCreditNoteDao().getCreditNoteDetailsByUserId(userId, visaOrderRow.getId());
				if(visaCreditNote!=null)
				map.put(visaCreditNote, "TYV");
			}
			break;
		case "TYM":
			miscellaneousOrderRow=new MiscellaneousOrderDao().getMiscellaneousOrderRowDetail(orderId);
			if(miscellaneousOrderRow!=null){
				MiscellaneousCreditNote  miscellaneousCreditNote=new MiscellaneousCreditNoteDao().getCreditNoteDetailsByUserId(userId, miscellaneousOrderRow.getId());
				if(miscellaneousCreditNote!=null)
				map.put(miscellaneousCreditNote, "TYM");
			}
			break;
		default:
			break;
		}

		return map;
	}

	private KnockOffVO buildKnockOffCancellationData(KnockOffVO knockOffVO, PaymentKnockDao paymentKnockDao) {
		// TODO Auto-generated method stub
		BigDecimal refundAmount=null;
		String  creditNoteNo ="-";
		Timestamp issuedAt=null;
		KnockOffVO nullifyknockOffVO=null;
		Map<Object,String> refundMap  =getRefundAmount(knockOffVO.getBookingRef(),knockOffVO.getUserId());
		if(!refundMap.isEmpty()){
			for(Map.Entry<Object,String> entry:refundMap.entrySet()){
				switch(entry.getValue()){
				case "TYA":
					CreditNote flightCreditNote=(CreditNote)entry.getKey();
					if(flightCreditNote!=null){
						refundAmount=flightCreditNote.getRefundedAmount();
						creditNoteNo=flightCreditNote.getCNINumber();
						issuedAt=flightCreditNote.getIssuedAt();
					}
					break;
				case "TYB":
					BusCreditNote busCreditNote=(BusCreditNote)entry.getKey();
					if(busCreditNote!=null){
						refundAmount=busCreditNote.getRefundedAmount();
						creditNoteNo=busCreditNote.getCNINumber();
						issuedAt=busCreditNote.getIssuedAt();
					}
					break;
				case "TYC":
					CarCreditNote carCreditNote=(CarCreditNote)entry.getKey();
					if(carCreditNote!=null){
						refundAmount=carCreditNote.getRefundedAmount();
						creditNoteNo=carCreditNote.getCNINumber();
						issuedAt=carCreditNote.getIssuedAt();
					}
					break;
				case "TYH":
					HotelCreditNote hotelCreditNote=(HotelCreditNote)entry.getKey();
					if(hotelCreditNote!=null){
						refundAmount=hotelCreditNote.getRefundedAmount();
						creditNoteNo=hotelCreditNote.getCNINumber();
						issuedAt=hotelCreditNote.getIssuedAt();
					}
					break;
				case "TYI":
					InsuranceCreditNote insuranceCreditNote=(InsuranceCreditNote)entry.getKey();
					if(insuranceCreditNote!=null){
						refundAmount=insuranceCreditNote.getRefundedAmount();
						creditNoteNo=insuranceCreditNote.getCNINumber();
						issuedAt=insuranceCreditNote.getIssuedAt();
					}
					break;
				case "TYT":
					TrainCreditNote trainCreditNote=(TrainCreditNote)entry.getKey();
					if(trainCreditNote!=null){
						refundAmount=trainCreditNote.getRefundedAmount();
						creditNoteNo=trainCreditNote.getCNINumber();
						issuedAt=trainCreditNote.getIssuedAt();
					}
					break;
				case "TYV":
					VisaCreditNote visaCreditNote=(VisaCreditNote)entry.getKey();
					if(visaCreditNote!=null){
						refundAmount=visaCreditNote.getRefundedAmount();
						creditNoteNo=visaCreditNote.getCNINumber();
						issuedAt=visaCreditNote.getIssuedAt();
					}
					break;
				case "TYM":
					MiscellaneousCreditNote miscellaneousCreditNote=(MiscellaneousCreditNote)entry.getKey();
					if(miscellaneousCreditNote!=null){
						refundAmount=miscellaneousCreditNote.getRefundedAmount();
						creditNoteNo=miscellaneousCreditNote.getCNINumber();
						issuedAt=miscellaneousCreditNote.getIssuedAt();
					}
					break;
				default:
					break;
				}
				if(refundAmount==null)
					return nullifyknockOffVO;
				else if(refundAmount.compareTo(new BigDecimal("0.00"))<=0)
					return nullifyknockOffVO;
				else{
					BigDecimal totAmount=new BigDecimal(0);
					nullifyknockOffVO=new KnockOffVO();
					PaymentKnockOffRow paymentKnockOffRow=  paymentKnockDao.fetchPaymentKnockOffRow(creditNoteNo, knockOffVO.getCompanyId());
					Set<String> BRVorCRVList= null;
					if(paymentKnockOffRow!=null){
						BRVorCRVList  =new HashSet<>();
						if(paymentKnockOffRow.getPaymentKnockOffRowTxs()!=null && paymentKnockOffRow.getPaymentKnockOffRowTxs().size()>0){
							for(PaymentKnockOffRowTx paymentKnockOffRowTx:paymentKnockOffRow.getPaymentKnockOffRowTxs()){
								totAmount=totAmount.add(paymentKnockOffRowTx.getAmount());
								BRVorCRVList.add(paymentKnockOffRowTx.getBRVorCRV());
							}
						}
						 
					}
					if(totAmount.compareTo(new BigDecimal(0))>0){
						nullifyknockOffVO.setKnockedOffAmount(totAmount);
						nullifyknockOffVO.setBalanceAmount(refundAmount.subtract(totAmount)); 
					}
					else{
						nullifyknockOffVO.setBalanceAmount(refundAmount);
						nullifyknockOffVO.setKnockedOffAmount(new BigDecimal(0));
					} 
					nullifyknockOffVO.setBRVorCRVList(BRVorCRVList);
					nullifyknockOffVO.setBookingDate(knockOffVO.getBookingDate());
					nullifyknockOffVO.setBookingRef(knockOffVO.getBookingRef());
					nullifyknockOffVO.setBillDate(DateConversion.convertTimestampToString(issuedAt));
					nullifyknockOffVO.setBillNo(creditNoteNo);
					nullifyknockOffVO.setBillAmount(refundAmount.setScale(2, BigDecimal.ROUND_UP));
					nullifyknockOffVO.setBookingType(knockOffVO.getBookingType());
					nullifyknockOffVO.setBRVorCRV(knockOffVO.getBRVorCRV());
					nullifyknockOffVO.setGDSorLCC(knockOffVO.getGDSorLCC());
					nullifyknockOffVO.setAmount(knockOffVO.getAmount());
					return nullifyknockOffVO;

				}
			}
		}
		return nullifyknockOffVO;
	}
	 
	
	private WalletAmountTranferHistory buildCancellationData(WalletAmountTranferHistory tranferHistory, CompanyFilter companyFilter) {
		// TODO Auto-generated method stub
		BigDecimal refundAmount=null;
		String  action ="";
		String  creditNoteNo ="-";
		BigDecimal open=null;
		BigDecimal close=null;
		Timestamp createdAt=null;
		WalletAmountTranferHistory nullifyHistory=null;
		Map<Object,String> refundMap  =getRefundAmount(tranferHistory.getActionId(),tranferHistory.getUserId());
		if(!refundMap.isEmpty()){
			for(Map.Entry<Object,String> entry:refundMap.entrySet()){
				switch(entry.getValue()){
				case "TYA":
					CreditNote flightCreditNote=(CreditNote)entry.getKey();
					if(flightCreditNote!=null){
						refundAmount=flightCreditNote.getRefundedAmount();
						action="Flight Booking Payment Refunded";
						creditNoteNo=flightCreditNote.getCNINumber();
						createdAt=flightCreditNote.getIssuedAt();
					}
					break;
				case "TYB":
					BusCreditNote busCreditNote=(BusCreditNote)entry.getKey();
					if(busCreditNote!=null){
						refundAmount=busCreditNote.getRefundedAmount();
						action="Bus Booking Payment Refunded";
						creditNoteNo=busCreditNote.getCNINumber();
						createdAt=busCreditNote.getIssuedAt();
					}
					break;
				case "TYC":
					CarCreditNote carCreditNote=(CarCreditNote)entry.getKey();
					if(carCreditNote!=null){
						refundAmount=carCreditNote.getRefundedAmount();
						action="Car Booking Payment Refunded";
						creditNoteNo=carCreditNote.getCNINumber();
						createdAt=carCreditNote.getIssuedAt();
					}
					break;
				case "TYH":
					HotelCreditNote hotelCreditNote=(HotelCreditNote)entry.getKey();
					if(hotelCreditNote!=null){
						refundAmount=hotelCreditNote.getRefundedAmount();
						action="Hotel Booking Payment Refunded";
						creditNoteNo=hotelCreditNote.getCNINumber();
						createdAt=hotelCreditNote.getIssuedAt();
					}
					break;
				case "TYI":
					InsuranceCreditNote insuranceCreditNote=(InsuranceCreditNote)entry.getKey();
					if(insuranceCreditNote!=null){
						refundAmount=insuranceCreditNote.getRefundedAmount();
						action="Insurance Booking Payment Refunded";
						creditNoteNo=insuranceCreditNote.getCNINumber();
						createdAt=insuranceCreditNote.getIssuedAt();
					}
					break;
				case "TYT":
					TrainCreditNote trainCreditNote=(TrainCreditNote)entry.getKey();
					if(trainCreditNote!=null){
						refundAmount=trainCreditNote.getRefundedAmount();
						action="Train Booking Payment Refunded";
						creditNoteNo=trainCreditNote.getCNINumber();
						createdAt=trainCreditNote.getIssuedAt();
					}
					break;
				case "TYV":
					VisaCreditNote visaCreditNote=(VisaCreditNote)entry.getKey();
					if(visaCreditNote!=null){
						refundAmount=visaCreditNote.getRefundedAmount();
						creditNoteNo=visaCreditNote.getCNINumber();
						action="Visa Booking Payment Refunded";
						createdAt=visaCreditNote.getIssuedAt();
					}
					break;
				case "TYM":
					MiscellaneousCreditNote miscellaneousCreditNote=(MiscellaneousCreditNote)entry.getKey();
					if(miscellaneousCreditNote!=null){
						refundAmount=miscellaneousCreditNote.getRefundedAmount();
						creditNoteNo=miscellaneousCreditNote.getCNINumber();
						action="Miscellaneous Booking Payment Refunded";
						createdAt=miscellaneousCreditNote.getIssuedAt();
					}
					break;
				default:
					break;
				}
				if(refundAmount==null)
					return nullifyHistory;
				else if(refundAmount.compareTo(new BigDecimal("0.00"))<=0)
					return nullifyHistory;
				else{
					nullifyHistory=new WalletAmountTranferHistory();
					nullifyHistory.setCreatedAt(createdAt);
					nullifyHistory.setActionId(tranferHistory.getActionId());
					nullifyHistory.setAgencyName(tranferHistory.getAgencyName());
					nullifyHistory.setCompany_userid(tranferHistory.getCompany_userid());
					nullifyHistory.setRemarks(action);
					nullifyHistory.setAction(action);
					nullifyHistory.setTransactionType(tranferHistory.getTransactionType());
					nullifyHistory.setInvoiceNo(creditNoteNo);
					nullifyHistory.setActionId(tranferHistory.getActionId());
					nullifyHistory.setVoucherType("Credit note");
					nullifyHistory.setAmount(refundAmount.negate().setScale(2, BigDecimal.ROUND_UP));
					nullifyHistory.setOpeningBalance(tranferHistory.getClosingBalance().setScale(2, BigDecimal.ROUND_UP)); 
					nullifyHistory.setClosingBalance(tranferHistory.getClosingBalance().add(refundAmount).setScale(2, BigDecimal.ROUND_UP));
					nullifyHistory.setParentOpeningBalance(tranferHistory.getParentClosingBalance().setScale(2, BigDecimal.ROUND_UP));
					nullifyHistory.setParentClosingBalance(tranferHistory.getParentClosingBalance().add(refundAmount).setScale(2, BigDecimal.ROUND_UP)); 
					if((companyFilter.getUserName() != null && !companyFilter.getUserName().equals("")) || companyFilter.getCompanyId()>0 )
					{ 
						open=tranferHistory.getOpeningBalance().setScale(2, BigDecimal.ROUND_UP);
						close=tranferHistory.getClosingBalance().setScale(2, BigDecimal.ROUND_UP);
						nullifyHistory.setOpeningBalance(close.setScale(2, BigDecimal.ROUND_UP)); 
						nullifyHistory.setClosingBalance(close.add(refundAmount).setScale(2, BigDecimal.ROUND_UP));

					}
					else if(tranferHistory.getUserId()!=companyFilter.getLoginUser().getId())
					{
						open=tranferHistory.getParentOpeningBalance().setScale(2, BigDecimal.ROUND_UP);
						close=tranferHistory.getParentClosingBalance().setScale(2, BigDecimal.ROUND_UP);
						nullifyHistory.setOpeningBalance(close.setScale(2, BigDecimal.ROUND_UP)); 
						nullifyHistory.setClosingBalance(close.add(refundAmount).setScale(2, BigDecimal.ROUND_UP));
					}  
					return nullifyHistory;

				}
			}
		}
		return nullifyHistory;
	}


	private List<WalletAmountTranferHistory> buildMergingListData(List<WalletAmountTranferHistory> list ) {
		// TODO Auto-generated method stub
		List<WalletAmountTranferHistory> mergedList=new LinkedList<>();
		if(list!=null && list.size()>0){
			BigDecimal outStandingBalance=new BigDecimal(0);
			for(WalletAmountTranferHistory newHistory:list){
				WalletAmountTranferHistory newtTranferHistoryBuild=new WalletAmountTranferHistory();
				newtTranferHistoryBuild.setCreatedAt(newHistory.getCreatedAt());
				newtTranferHistoryBuild.setAgencyName(newHistory.getAgencyName());
				newtTranferHistoryBuild.setInvoiceNo(newHistory.getInvoiceNo());
				newtTranferHistoryBuild.setOpeningBalance(newHistory.getOpeningBalance());
				newtTranferHistoryBuild.setClosingBalance(newHistory.getClosingBalance());
				newtTranferHistoryBuild.setActionId(newHistory.getActionId());
				newtTranferHistoryBuild.setRemarks(newHistory.getRemarks());
				BigDecimal payment=newHistory.getAmount(); 
				String voucherType=newHistory.getVoucherType();

				if(newHistory.getAction()!=null && newHistory.getAction().equalsIgnoreCase("Payment received")){
					payment=newHistory.getAmount().negate();
					voucherType="Payment";
					outStandingBalance=outStandingBalance.add(payment.setScale(2, BigDecimal.ROUND_UP));
				}
				else if(newHistory.getAction()!=null && newHistory.getAction().equalsIgnoreCase("Payment given")){ 
					outStandingBalance=outStandingBalance.add(payment.setScale(2, BigDecimal.ROUND_UP));
					voucherType="Payment";
				}
				else
					outStandingBalance=outStandingBalance.add(payment.setScale(2, BigDecimal.ROUND_UP));

				if(newHistory.getAction()!=null && (newHistory.getAction().equalsIgnoreCase("Deposited") || newHistory.getAction().equalsIgnoreCase("taken"))){ 
					outStandingBalance=outStandingBalance.subtract(newHistory.getAmount().setScale(2, BigDecimal.ROUND_UP));
				}


				newtTranferHistoryBuild.setAction(newHistory.getAction());
				newtTranferHistoryBuild.setAmount(payment);
				newtTranferHistoryBuild.setVoucherType(voucherType);
				newtTranferHistoryBuild.setOutStandingBalance(outStandingBalance);
				newtTranferHistoryBuild.setGstOrServiceTax(getGstOrServiceTax(newHistory.getInvoiceNo()));
				mergedList.add(newtTranferHistoryBuild);
			}
		}
		return mergedList;
	}

	private  BigDecimal getGstOrServiceTax(String invoiceNo) {
		BigDecimal gstOrServiceTax= null;
		FlightOrderRow  flightOrderRow=null;
		HotelOrderRow  hotelOrderRow=null;
		CarOrderRow  carOrderRow=null;
		BusOrderRow  busOrderRow=null;
		TrainOrderRow  trainOrderRow=null;
		VisaOrderRow visaOrderRow=null;
		InsuranceOrderRow  insuranceOrderRow=null;
		MiscellaneousOrderRow   miscellaneousOrderRow=null;
		switch(CommonUtil.findStringFromString(invoiceNo)){
		case "TYA":
			flightOrderRow =new FlightOrderDao().getFlightOrderRowDataById(CommonUtil.findDigitsFromString(invoiceNo));
			if(flightOrderRow!=null){
				if(flightOrderRow.getFlightOrderRowServiceTax()!=null) 
					gstOrServiceTax=flightOrderRow.getServiceTax();
				else if(flightOrderRow.getFlightOrderRowGstTax()!=null) 
					gstOrServiceTax=flightOrderRow.getGstOnFlights();
				else
					gstOrServiceTax=new BigDecimal("0.00");

			}
			break;
		case "TYB":
			busOrderRow =new BusOrderDao().getBusOrderRowDetail(CommonUtil.findDigitsFromString(invoiceNo));
			if(busOrderRow!=null){
				if(busOrderRow.getBusOrderRowServiceTax()!=null) 
					gstOrServiceTax=busOrderRow.getServiceTax();
				else if(busOrderRow.getBusOrderRowGstTax()!=null) 
					gstOrServiceTax=busOrderRow.getTotalGstTax();
				else
					gstOrServiceTax=new BigDecimal("0.00");
			}
			break;
		case "TYC":
			carOrderRow =new CarOrderDao().getCarOrderRowDetail(CommonUtil.findDigitsFromString(invoiceNo));
			if(carOrderRow!=null){
				if(carOrderRow.getCarOrderRowServiceTax()!=null) 
					gstOrServiceTax=carOrderRow.getServiceTax();
				else if(carOrderRow.getCarOrderRowGstTax()!=null) 
					gstOrServiceTax=carOrderRow.getTotalGstTax();
				else
					gstOrServiceTax=new BigDecimal("0.00");
			}
			break;
		case "TYH":
			hotelOrderRow =new HotelOrderDao().getHotelOrderRowInfo(CommonUtil.findDigitsFromString(invoiceNo));
			if(hotelOrderRow!=null){
				if(hotelOrderRow.getHotelOrderRowServiceTax()!=null) 
					gstOrServiceTax=hotelOrderRow.getServiceTax();
				else if(hotelOrderRow.getHotelOrderRowGstTax()!=null) 
					gstOrServiceTax=hotelOrderRow.getTotGst();
				else
					gstOrServiceTax=new BigDecimal("0.00");
					
			}
			break;
		case "TYI":
			insuranceOrderRow  =new InsuranceOrderDao().getInsuranceOrderRowDetail(CommonUtil.findDigitsFromString(invoiceNo));
			if(insuranceOrderRow!=null){
				if(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null) 
					gstOrServiceTax=insuranceOrderRow.getServiceTax();
				else if(insuranceOrderRow.getInsuranceOrderRowGstTax()!=null) 
					gstOrServiceTax=insuranceOrderRow.getTotalGstTax();
				else
					gstOrServiceTax=new BigDecimal("0.00");
			}
			break;
		case "TYT":
			trainOrderRow  =new TrainOrderDao().getTrainOrderRowDetail(CommonUtil.findDigitsFromString(invoiceNo));
			if(trainOrderRow!=null){
				if(trainOrderRow.getTrainOrderRowServiceTax()!=null) 
					gstOrServiceTax=trainOrderRow.getServiceTax();
				else if(trainOrderRow.getTrainOrderRowGstTax()!=null) 
					gstOrServiceTax=trainOrderRow.getTotalGstTax();
				else
					gstOrServiceTax=new BigDecimal("0.00");
			}
			break;
		case "TYV":
			visaOrderRow  =new VisaOrderDao().getVisaOrderRowDetail(CommonUtil.findDigitsFromString(invoiceNo));
			if(visaOrderRow!=null){
				if(visaOrderRow.getVisaOrderRowServiceTax()!=null) 
					gstOrServiceTax=visaOrderRow.getServiceTax();
				else if(visaOrderRow.getVisaOrderRowGstTax()!=null) 
					gstOrServiceTax=visaOrderRow.getTotalGstTax();
				else
					gstOrServiceTax=new BigDecimal("0.00");
			}
			break;
		case "TYM":
			miscellaneousOrderRow  =new MiscellaneousOrderDao().getMiscellaneousOrderRowById(CommonUtil.findDigitsFromString(invoiceNo));
			if(miscellaneousOrderRow!=null){
				if(miscellaneousOrderRow.getMiscellaneousOrderRowGstTax()!=null) 
					gstOrServiceTax=miscellaneousOrderRow.getTotalGstTax();
				else
					gstOrServiceTax=new BigDecimal("0.00");
			}
			break;
		default:
			gstOrServiceTax=new BigDecimal("0.00");
			break;
		}
		return gstOrServiceTax.setScale(2, BigDecimal.ROUND_UP);

	}


	public WalletAmountTranferHistory  getWalletHistoryByBRVwithCompanyId(int companyId,String BRV){
		WalletAmountTranferHistory walletAmountTranferHistory=new WalletAmountTranferHistory ();
		Session session=null;
		try{
			Conjunction conjunctin=Restrictions.conjunction();
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(WalletAmountTranferHistory.class);
			conjunctin.add(Restrictions.eq("actionId", BRV));
			conjunctin.add(Restrictions.eq("companyId", companyId));
			walletAmountTranferHistory=(WalletAmountTranferHistory) criteria.add(conjunctin).uniqueResult();

		}
		catch(HibernateException e){
			logger.error("------------HibernateException---------------"+e.getMessage());
		}catch (Exception e) {
			logger.error("------------Exception---------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return walletAmountTranferHistory;
	}
	public List<WalletAmountTranferHistory>  getWalletHistoryBRVListwithCompanyId(Company company){
		 List<WalletAmountTranferHistory> brvHistoryList =null;
		 List<Integer> newIds =new LinkedList<>();
		 List<String> ids =new  DashBoardSearchDao().getCompanyIdListByParentCompany(company);
		 if(ids!=null){
			 for(String id:ids){
				 newIds.add(Integer.parseInt(id));
			 }
		 }
		Session session=null;
		try{
			Conjunction conjunctin=Restrictions.conjunction();
			Disjunction disjunctin=Restrictions.disjunction();
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(WalletAmountTranferHistory.class);
			disjunctin.add(Restrictions.eq("action", "Payment received"));
			disjunctin.add(Restrictions.eq("action", "Payment given")); 
			conjunctin.add(Restrictions.in("companyId",newIds));
			brvHistoryList=criteria.add(conjunctin).add(disjunctin).list();
		}
		catch(HibernateException e){
			logger.error("------------HibernateException---------------"+e.getMessage());
		}catch (Exception e) {
			logger.error("------------Exception---------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return brvHistoryList;
	}

	public void updateWalletHistoryWhenFailed(String OrderId) {
		// TODO Auto-generated method stub
		String hqlUpdate = "update WalletAmountTranferHistory c set c.failed = :failed where c.actionId= :actionId";
		 Session session=null;
		 org.hibernate.Transaction tx=null;
		try
		{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.createQuery(hqlUpdate).setBoolean("failed",true).setString("actionId",OrderId).executeUpdate(); 
			session.close();
			tx.commit();

		}
		catch(HibernateException he){
			if(tx!=null)
				tx.rollback();
			logger.info("HibernateException------------------"+he.getMessage());
		}
		catch (Exception e){
			if(tx!=null)
				tx.rollback();
			logger.info("Exception------------------"+e.getMessage());
		}

		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		 
	}
}
