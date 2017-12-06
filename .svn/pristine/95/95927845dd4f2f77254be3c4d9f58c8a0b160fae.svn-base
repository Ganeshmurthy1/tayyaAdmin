package com.lintas.admin.DAO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.admin.corporate.employee.CompanyBandEntity;
import com.admin.corporate.employee.CompanyBusinessUnitEntity;
import com.admin.corporate.employee.CompanyCostCenterEntity;
import com.admin.corporate.employee.CompanyDepartmentEntity;
import com.admin.corporate.employee.CompanyDesignationEntity;
import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.filter.dao.CompanyFilterDao;
import com.isl.admin.page.CompanyFilterPage;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.MyTransactions;
import com.lintas.admin.model.SalesLeadGeneration;
import com.lintas.admin.model.User;
import com.lintas.admin.model.UserDesignation;
import com.lintas.admin.model.UserRole;
import com.lintas.admin.model.UserWallet;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.config.HibernateUtil;
import com.lintas.config.RandomConfigurationNumber;
import com.lintas.utility.DateConversion;

public class UserDAO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(UserDAO.class);
	/*Insert the user details in the database*/
	public User RegisterUser(User user, User creator) throws Exception
	{
		//int id = 0;
		//User userObj=null;   
		Session sess = null;
		Transaction transaction = null;

		try{			
			if(user.getUserrole_id().isSuperuser()){
				user.setCreatedbyCompanyUserId(user.getId());
				user.setModifiedbyCompanyUserId(user.getId());
			}
			else{
				user.setCreatedbyCompanyUserId(creator.getId());
				user.setModifiedbyCompanyUserId(creator.getId());
			}			
			sess = HibernateUtil.getSessionFactory().openSession();
			transaction = sess.beginTransaction();
			sess.save(user);		
			transaction.commit();

		}catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(sess != null && sess.isOpen())
			{
				sess.close();
			}
		}
		return user;
	}
	/*Check User name exists in DB */
	public boolean UserIdExists(String email) throws Exception
	{
		boolean exists = false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from User com where com.Email=:email";
			Query query = session.createQuery(sql);
			query.setParameter("email",email);
			User user = (User)query.uniqueResult();
			if (user!=null) {
				exists = true;
			}

		} 
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
			return  exists;
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return exists;
	}


	public User GetUserProfile (int id)
	{
		User userNew = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from User com where com.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			userNew = (User) query.uniqueResult();
			StringBuilder role= new StringBuilder();
			if(userNew!=null){
				if(userNew.getUserrole_id()!=null){
					if(userNew.getUserrole_id().isAdmin()) 
						role.append("Admin");
					if(userNew.getUserrole_id().isOrder()) 
						role.append(",Operations");
					if(userNew.getUserrole_id().isReports()) 
						role.append(",Accounts");
					if(userNew.getUserrole_id().isCms()) 
						role.append(",CMS");
					if(userNew.getUserrole_id().isCorporateemployee()) 
						role.append(",Corporate Employee");
					if(userNew.getUserrole_id().isDemoUser()) 
						role.append(",DemoUser");
					if(userNew.getUserrole_id().isSuperuser()) 
						role.append(",SuperUser");
					if(userNew.getUserrole_id().isTechHead())
						role.append(",TechHead");
					if(userNew.getUserrole_id().isTechSupport())
						role.append(",TechSupport");
					if(userNew.getUserrole_id().isTravelDesk())
						role.append(",Travel Desk");
				}
				else 
					role.append("NA");
				userNew.setRole(role.toString());
				if(userNew.getCreateddate()!=null) 
					userNew.setCreateDate(DateConversion.convertDateToStringToDateWithTIME(userNew.getCreateddate()));
				else
					userNew.setCreateDate("NA");
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return userNew;
	}

	/*fetching  superuser user Profile using userId from user table	*/
	public User getSuperUser_UserProfile(User u)
	{
		User user = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from User com where com.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id",u.getId());
			user = (User) query.uniqueResult();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return user;
	}

	/*Updation for  superuser user Profile in user table	*/
	public User updateUserProfile(User u, UserRole role ){
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();	
			if(u!=null){
				session.update(u);
				transaction.commit();
			}
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return u;

	}
	/*Updation for  superuser user Profile in user table	*/
	public boolean  updateUserProfileInUser(User u, UserRole role ){
		boolean updated = false;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();	

			if(u!=null){
				session.update(u);
				updated=true;
				transaction.commit();
			}
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());
			return updated;
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updated;

	}
	/*Update User roles*/
	public void updateUserRole(UserRole  role)
	{
		//logger.info("---roleId-----"+role.getRoleid());
		logger.info("---isOrder-----"+role.isOrder());
		logger.info("---isReports-----"+role.isReports());
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();	
			if(role!=null){
				session.update(role);
				transaction.commit();
			}
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}

	}
	/*Update User Wallet*/
	public UserWallet updateUserWallet(UserWallet  wallet)
	{
		logger.info("---getWalletId-----"+wallet.getWalletId());
		logger.info("---getCurrencyCode-----"+wallet.getCurrencyCode());
		logger.info("---getWalletType-----"+wallet.getWalletType());
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();	
			if(wallet!=null){
				session.update(wallet);
				transaction.commit();
			}

		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return wallet;

	}

	public UserWallet updateUserWalletByUserId(int walletId,BigDecimal newBalance)
	{
		UserWallet walletObj=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();	
			walletObj=(UserWallet) session.get(UserWallet.class, walletId);
			walletObj.setWalletbalance(newBalance);
			session.update(walletObj);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return walletObj;

	}



	/*Update User details*/
	public  User updateUserProfile(User newobj)
	{

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			session.update(newobj);
			transaction.commit();

		}catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return newobj;

	}

	/*checking user logins*/
	public User userLogin(String email, String password,String companyId)
	{

		//String sql = "select * from company com where (com.email='" + username + "' and com.password='" + password + "') and com.company_userid='"+companyId+"'";
		Session session = null;
		User user = null;
		String sql="from User as u where u.Email = :email and u.Password = :password and u.company_userid = :companyuserid";
		try{
			//using named parameters to prevent the sql injection
			session = HibernateUtil.getSessionFactory().openSession();	
			Query safeHQLQuery = session.createQuery(sql);
			safeHQLQuery.setParameter("email", email);
			safeHQLQuery.setParameter("password", password);
			safeHQLQuery.setParameter("companyuserid",companyId);
			user = (User) safeHQLQuery.uniqueResult();
			if(user!=null){
				user.setCreateDate(DateConversion.convertDateToStringToDateWithTIME(user.getCreateddate()));

			}
		}
		catch(HibernateException e){
			logger.error("---------Exception---------"+e.getMessage());
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return user;
	}

	/* lock the company in user and user table if he enter wrong credentials */
	public User getUserPasswordForLock(String email)
	{
		Session session=null;
		User user=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession(); 
			Criteria criteria=session.createCriteria(User.class);
			criteria.add(Restrictions.eq("Email", email));
			user = (User) criteria.uniqueResult();

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
			e.printStackTrace();

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return user;
	}

	/*Update user lock using user Id */
	public boolean setUserLock1(int id)
	{ 
		boolean lock=false;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			User setLock =  (User) session.get(User.class,id);
			setLock.setLocked(true);
			setLock.setLockedDate(new Date());
			lock=setLock.isLocked();
			session.update(setLock);
			transaction.commit();
		}
		catch (HibernateException e) {

			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return lock;

	}

	/*Update user lock using user Id */
	public User setUserLock(int id)
	{ 
		//boolean lock=false;
		User userLock=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			userLock =  (User) session.get(User.class,id);
			userLock.setLocked(true);
			userLock.setLockedDate(new Date());
			//lock=setLock.isLocked();
			session.update(userLock);
			transaction.commit();
		}
		catch (HibernateException e) {

			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return userLock;

	}

	public Company setCompanyLock(int companyid) {

		Company companyLock=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			companyLock =  (Company) session.get(Company.class,companyid);
			companyLock.setLocked(true);
			session.update(companyLock);
			transaction.commit();
		}
		catch (HibernateException e) {

			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return companyLock;

	}


	/*Update user attemps using user Id */
	public User setUserAttemps(User u)
	{ 
		User noofAttempts= new User();
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			User attempts =  (User) session.get(User.class,u.getId());
			attempts.setAttemt(u.getAttemt());
			noofAttempts.setAttemt(attempts.getAttemt());
			session.update(attempts);
			transaction.commit();

		}
		catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return noofAttempts;
	}
	/*sending  user password to  mail, if forgot passwordby raham */
	public int getUserPassword(String email)
	{
		int  userId = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from User u where  u.Email=:email";
			Query query = session.createQuery(sql);
			query.setParameter("email", email);
			User user = (User) query.uniqueResult();
			if(user!=null)
				userId = user.getId();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return userId;
	}

	/*	this method for fetching all the users based on the company id from the user table */
	public List<User> getUsersByCompanyIds(List<Company> companyIds){
		List<User> usersList=null/*new ArrayList<User>()*/;
		try{
			for(Company companyObj:companyIds){
				session = HibernateUtil.getSessionFactory().openSession();
				String sql = "from User u where u.Companyid=:companyid";
				Query query = session.createQuery(sql);
				query.setParameter("companyid",companyObj.getCompanyid());
				usersList = query.list();

			}
		}
		catch(Exception e)
		{
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return usersList;
	}
	public List<User> getUsersByCompanyId(int companyId){
		List<User> usersList=null/*new ArrayList<User>()*/;
		try{

			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from User u where u.Companyid=:companyid";
			Query query = session.createQuery(sql);
			query.setParameter("companyid",companyId);
			usersList = query.list();


		}
		catch(Exception e)
		{
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return usersList;
	}




	/*method for fetch company ids  by passing company user id*/
	public List<Integer> getAllComapnyIdsByCompanyUserId(User userSessionObj,Company companySessionObj){

		String sql=null;
		Query query=null;
		List<Integer> companyIds=new ArrayList<>();
		session = HibernateUtil.getSessionFactory().openSession();
		try{
			if(!companySessionObj.getCompanyRole().isDistributor() && !companySessionObj.getCompanyRole().isAgent() && !companySessionObj.getCompanyRole().isCorporate()){
				sql="from Company com where com.super_company_userid=:supercompanyuserid";
				query = session.createQuery(sql);
				query.setParameter("supercompanyuserid", companySessionObj.getCompany_userid());
			}
			else if(companySessionObj.getCompanyRole().isDistributor()){
				sql="from Company com where com.parent_company_userid=:parentcompanyuserid or com.company_userid=:companyuserid";
				query = session.createQuery(sql);
				query.setParameter("parentcompanyuserid", companySessionObj.getCompany_userid());
				query.setParameter("companyuserid",companySessionObj.getCompany_userid());
			}
			else if(companySessionObj.getCompanyRole().isAgent()){
				sql="from Company com  where com.company_userid=:companyuserid";
				query = session.createQuery(sql);
				query.setParameter("companyuserid",companySessionObj.getCompany_userid());
			}
			else if(companySessionObj.getCompanyRole().isCorporate()){
				sql="from Company com  where com.company_userid=:companyuserid";
				query = session.createQuery(sql);
				query.setParameter("companyuserid",companySessionObj.getCompany_userid());
			}
			/*else{
			if(userSessionObj.getUserrole_id().isAdmin()){
				if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
					if(companySessionObj.getCompanyRole().isDistributor()){
						sql="select * from company where parent_company_userid='"+companySessionObj.getCompany_userid()+"' or company_userid='"+companySessionObj.getCompany_userid()+"'";
					}
					else if(companySessionObj.getCompanyRole().isAgent()){
						sql="select * from company where company_userid='"+companySessionObj.getCompany_userid()+"'";
					}
					else{
						sql="select * from company where super_company_userid='"+companySessionObj.getCompany_userid()+"'";
					}
				}
			}

			logger.info("------Direct Admin query for particular company-----------"+sql);
		}
			 */
			List<Company> companiesList = query.list();
			if(companiesList!=null && companiesList.size()>0){
				for (int i = 0; i < companiesList.size(); i++) {
					Company companyNew= (Company)companiesList.get(i);
					companyIds.add(companyNew.getCompanyid());
				}
			}

		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}	
		finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return companyIds;

	}



	/*loading the wallet users   company names*/
	public List<User> getAllAgentsCompanyTypeByCompanyId(User sessionObj,Company companySessionObj){
		List<Company> allCompanyNames= null;
		List<User> allWalletUsers= new ArrayList<>();
		List<User> walletList=getAllAgentsByCompanyId(sessionObj, companySessionObj);
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(walletList!=null){
				for(User user:walletList){
					String sql = "from Company com where  com.companyid=:companyid";
					logger.info("-----getAllAgents List query------"+sql);
					Query query = session.createQuery(sql);
					query.setParameter("companyid",user.getCompanyid());
					allCompanyNames = query.list();
					if(allCompanyNames!=null){
						for(Company companyName:allCompanyNames){
							if(user.getCompanyid()==companyName.getCompanyid()){
								user.setCompanyName(companyName.getCompanyname());
								if(companyName.getCompanyRole().isAgent()){
									user.setCompanyType("Agency");
								}
								else if(companyName.getCompanyRole().isDistributor()){
									user.setCompanyType("Wholesaler");
								}
								else if(companyName.getCompanyRole().isCorporate()){
									user.setCompanyType("Corporate");
								}
								else{
									user.setCompanyType("Super Agency");
								}
								allWalletUsers.add(user);
							}

						}
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
		return allWalletUsers;
	}



	/*get all agents by passing company ids*/
	public List<User> getAllAgentsByCompanyId(User userSessionObj,Company companySessionObj){
		List<Integer> companyIds =getAllComapnyIdsByCompanyUserId(userSessionObj,companySessionObj);
		List<User> usersList=null;
		try{
			if(companyIds!=null && companyIds.size()>0){
				String sql="from User u where u.Companyid in (:companyIds)";
				logger.info("---------all agents list query--------------"+sql);
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameterList("companyIds", companyIds);
				usersList = query.list();
			}
		}
		catch(Exception e){
			logger.error("--------Exception-------"+ e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return usersList;
	}
	/*get all agents by passing company ids*/
	public List<CompanyConfig> getCompanyConfigNamesByCompanyId(List<Integer> companyIds){
		List<CompanyConfig> configList=null;
		try{
			if(companyIds!=null && companyIds.size()>0){
				String sql="from CompanyConfig u where u.company_id in (:companyIds)";
				logger.info("---------all agents list query--------------"+sql);
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameterList("companyIds", companyIds);
				configList = query.list();
			}
		}
		catch(Exception e){
			logger.error("--------Exception-------"+ e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return configList;
	}


	/*get all agents by passing company ids*/
	public List<Company> getAllAgencyByCompanyId(User userSessionObj,Company companySessionObj){

		List<Company> companiesList =new ArrayList<Company>();
		String sql=null;
		Query  query=null;

		session = HibernateUtil.getSessionFactory().openSession();
		try{
			if(!companySessionObj.getCompanyRole().isDistributor() && !companySessionObj.getCompanyRole().isAgent()){
				sql="from Company com where com.super_company_userid=:supercompanyuserid";
				query = session.createQuery(sql);
				query.setParameter("supercompanyuserid", companySessionObj.getCompany_userid());
			}
			else if(companySessionObj.getCompanyRole().isDistributor()){
				sql="from Company com where com.parent_company_userid=:parentcompanyuserid or com.company_userid=:companyuserid";
				query = session.createQuery(sql);
				query.setParameter("parentcompanyuserid", companySessionObj.getCompany_userid());
				query.setParameter("companyuserid", companySessionObj.getCompany_userid());

			}
			else if(companySessionObj.getCompanyRole().isAgent()){
				sql="from Company com where com.company_userid=:companyuserid";
				query = session.createQuery(sql);
				query.setParameter("companyuserid",companySessionObj.getCompany_userid());
			}
			/*	else{
			if(userSessionObj.getUserrole_id().isAdmin()){
				if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
					if(companySessionObj.getCompanyRole().isDistributor()){
						sql="select * from company where parent_company_userid='"+companySessionObj.getCompany_userid()+"' or company_userid='"+companySessionObj.getCompany_userid()+"'";
					}
					else if(companySessionObj.getCompanyRole().isAgent()){
						sql="select * from company where company_userid='"+companySessionObj.getCompany_userid()+"'";
					}
					else{
						sql="select * from company where super_company_userid='"+companySessionObj.getCompany_userid()+"'";
					}
				}
			}

			logger.info("------Direct Admin query for particular company- for- fetching company list---------"+sql);
		}		
			 */
			List<Company> companies=query.list();
			if(companies!=null && companies.size()>0)
			{
				for (Company company:companies){
					if(company.getCompanyRole().isAgent())
					{				  
						companiesList.add(company);
					}
				}
			}
		}
		catch(Exception e){
			logger.error("--------Exception-------"+ e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen())
				session.close();	
		}
		return companiesList;

	}


	/*loading the wallet users   company names*/
	public List<User> getWalletUsersCompanyNameByComapanyId(User sessionObj){
		List<Company> allCompanyNames= null;
		List<User> allWalletUsers= new ArrayList<>();
		List<User> walletList=getWalletUsersByCompanyUserId(sessionObj);
		logger.info("walletList---------------"+walletList.size());
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(walletList!=null){
				for(User user:walletList){
					String sql = "from Company com where  com.companyid=:companyid";
					logger.info("-----walletUsersList query------"+sql);
					Query query = session.createQuery(sql);
					query.setParameter("companyid", user.getCompanyid());
					allCompanyNames = query.list();
					logger.info(" Wallet user CompanyNames-- size()---------"+allCompanyNames.size());
					if(allCompanyNames!=null){					
						for(Company companyName:allCompanyNames){
							if(user.getCompanyid()==companyName.getCompanyid()){
								user.setCompanyName(companyName.getCompanyname());
								if(companyName.getCompanyRole().isAgent()){
									user.setCompanyType("Agency");
								}
								else if(companyName.getCompanyRole().isDistributor()){
									user.setCompanyType("Wholesaler");
								}
								else if(companyName.getCompanyRole().isCorporate()){
									user.setCompanyType("Corporate");
								}
								else{
									user.setCompanyType("Super Agency");
								}
								allWalletUsers.add(user);
							}

						}
					}
				}
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen()){
				session.close(); 

			}
		}
		return allWalletUsers;
	}
	/*loading the wallet users based on company userid*/
	public List<User> getWalletUsersByCompanyUserId(User sessionObj){
		List<User> walletList= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from User u where u.createdbyCompanyUserId=:createdbyCompanyUserId order by u.id  desc";
			logger.info("-----walletUsersList query------"+sql);
			Query query = session.createQuery(sql);
			query.setParameter("createdbyCompanyUserId", sessionObj.getId());
			walletList = query.list();

		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return walletList;
	}

	/*loading the wallet users based on company userid*/
	public List<User> getWalletUsersByCompanytype(User sessionObj,String type){
		List<User> walletList= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from User u where u.createdbyCompanyUserId=:createdbyCompanyUserId  order by u.id  desc";
			logger.info("-----walletUsersList query------"+sql);
			Query query = session.createQuery(sql);
			query.setParameter("createdbyCompanyUserId", sessionObj.getId());
			walletList = query.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return walletList;
	}


	/*	this method for get parentwalletAmouny through his userid*/
	public UserWallet getParentUserWalletAmount(int parentWalletId){
		UserWallet userWallet = null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(UserWallet.class);
			criteria.add(Restrictions.eq("walletId", parentWalletId));
			/*	String sql = "from UserWallet uw where uw.walletId=:walletid";
			logger.info("-------------userwallet query------------"+sql);
			Query query = session.createQuery(sql);
			query.setParameter("walletid",parentWalletId);*/
			userWallet = (UserWallet) criteria.uniqueResult();

		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}

		return userWallet;

	}
	public UserWallet updateParentWallet(UserWallet parentWalletObj)
	{
		try{
			if(parentWalletObj!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();
				session.update(parentWalletObj);
				logger.info("--------------parent wallet balance---------------------"+parentWalletObj.getWalletbalance());
				transaction.commit();
			}
		}
		catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return parentWalletObj;
	}  

	public UserWallet updateUserWalletByWalletId(User user,BigDecimal newDepositBalance)
	{
		Session session=null;
		Transaction  transaction=null;
		UserWallet userWallet=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			userWallet=(UserWallet) session.get(UserWallet.class, user.getAgentWallet().getWalletId());
			userWallet.setTransactionType(user.getAgentWallet().getTransactionType());
			userWallet.setDepositBalance(newDepositBalance);
			session.update(userWallet);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return userWallet;
	}  






	public UserWallet userOpeningBalane(int userWalletId)
	{
		UserWallet userOpeningBalane=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from UserWallet uw where uw.walletId=:walletid";
			Query query = session.createQuery(sql);
			query.setParameter("walletid", userWalletId);
			userOpeningBalane = (UserWallet) query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
			e.printStackTrace();
		}
		catch ( Exception e) {
			logger.error("-------------- Exception-----------------"+e.getMessage());
			e.printStackTrace();
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return userOpeningBalane;
	}  

	public WalletAmountTranferHistory updateParentWallet(UserWallet wallet,User sessionObj,BigDecimal openingBalance,User newObj,String remarks)
	{ 
		WalletAmountTranferHistory  tranferHistory=new WalletAmountTranferHistory();
		WalletAmountTranferHistory transferHistoryupdated = new WalletAmountTranferHistory();


		try{
			if(wallet!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				session.update(wallet);
				tranferHistory.setCurrency(wallet.getCurrencyCode());
				tranferHistory.setAmount(newObj.getAgentWallet().getWalletbalance());
				tranferHistory.setWalletId(wallet.getWalletId());
				tranferHistory.setOpeningBalance(new BigDecimal("0.00"));
				tranferHistory.setClosingBalance(new BigDecimal("0.00"));
				tranferHistory.setUserId(sessionObj.getId());
				tranferHistory.setParentUserId(sessionObj.getId());
				tranferHistory.setActionId(RandomConfigurationNumber.generateRandomTxID());
				tranferHistory.setTransactionType(wallet.getTransactionType());
				tranferHistory.setCreatedAt(new Timestamp(new Date().getTime()));
				tranferHistory.setParentOpeningBalance(openingBalance);
				tranferHistory.setRemarks(remarks);
				tranferHistory.setParentClosingBalance(wallet.getWalletbalance());
				tranferHistory.setAction("Taken");
				tranferHistory.setCompanyId(sessionObj.getCompanyid());
				transferHistoryupdated = insertWalletAmountTransferHistory(tranferHistory, session); 
				transaction.commit();
			}	
		}
		catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return transferHistoryupdated;
	}  

	public BigDecimal getWalletbalanceById(int walletID){
		BigDecimal walletBalance = new BigDecimal(0);
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from UserWallet uw where uw.walletId=:walletid";
			Query query = session.createQuery(sql);
			query.setParameter("walletid", walletID);
			UserWallet userWallet = (UserWallet) query.uniqueResult();
			if(userWallet!=null)
			{
				walletBalance =userWallet.getWalletbalance();
			}
		}
		catch(Exception e)
		{
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return walletBalance;
	}




	public WalletAmountTranferHistory updateAgentWallet(UserWallet wallet,User sessionObj, User userNew,BigDecimal userOpeningBalance,UserWallet openingBalanceObj,UserWallet closingBalanceObj,String remarks)
	{

		WalletAmountTranferHistory  tranferHistory=new WalletAmountTranferHistory();
		WalletAmountTranferHistory transferId= null;
		try{
			if(wallet!=null){

				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				session.update(wallet);
				tranferHistory.setAction("taken");
				tranferHistory.setActionId(RandomConfigurationNumber.generateRandomTxID());
				tranferHistory.setAmount(userNew.getAgentWallet().getWalletbalance());
				tranferHistory.setWalletId(wallet.getWalletId());
				tranferHistory.setOpeningBalance(userOpeningBalance); 
				tranferHistory.setClosingBalance(wallet.getWalletbalance());
				tranferHistory.setCreatedAt(new Timestamp(new Date().getTime()));
				tranferHistory.setCurrency(wallet.getCurrencyCode());
				tranferHistory.setTransactionType(wallet.getTransactionType());
				tranferHistory.setUserId(userNew.getId());
				tranferHistory.setParentOpeningBalance(openingBalanceObj.getWalletbalance());
				tranferHistory.setParentClosingBalance(closingBalanceObj.getWalletbalance());
				tranferHistory.setParentUserId(sessionObj.getId());
				tranferHistory.setRemarks(remarks);
				tranferHistory.setCompanyId(sessionObj.getCompanyid());
				WalletAmountTranferHistory history=insertWalletAmountTransferHistory(tranferHistory, session); 
				if(history!=null && history.getId()>=0){
					transferId=history	;
				} 
				transaction.commit();
			}
		}
		catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());


		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return transferId;
	}  


	public synchronized int insertMyTransactions(MyTransactions myTx,Session session)
	{
		int id = 0;
		try{
			if(myTx!=null){
				transaction = session.beginTransaction();
				id=(Integer) session.save(myTx);
			}
		}catch(Exception e){
			logger.error("--------------Exception-----------------"+e.getMessage());
			transaction.rollback();
		}
		finally{
			session.close();
		}
		return id;

	} 
	public synchronized WalletAmountTranferHistory insertWalletAmountTransferHistory(WalletAmountTranferHistory myTxH,Session session)
	{
		//try{
		if(myTxH!=null){
			//transaction = session.beginTransaction();
			session.save(myTxH);
			myTxH.setConvertDate("transiant Date.....");
			myTxH.setAgencyName("sample");
			myTxH.setParentcompany_userid("some");
			myTxH.setCompany_userid("some");
		}
		return myTxH;
	} 

	public synchronized WalletAmountTranferHistory insertWalletAmountTransferHistory(WalletAmountTranferHistory walletAmountTranferHistory)
	{
		Session session =null;
		Transaction transaction=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();	
			session.save(walletAmountTranferHistory);
			transaction.commit();

		}catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}

			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return walletAmountTranferHistory;
	} 




	/*get user id for update the approval status in user table*/
	public int getUserId(int companyId,String email)
	{
		int userId = 0;
		try{
			if(companyId>0){
				String sql = "from User com where com.Companyid=:companyid and com.Email=:email";
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameter("companyid", companyId);
				query.setParameter("email", email);
				User user = (User) query.uniqueResult();
				if (user!=null) {
					userId=user.getId();
				}
			}
		}catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return userId;
	}

	/*------------------passing  user id for update the  company approval status in user table------------------------*/
	public boolean updateUserApproval(User userObj)
	{
		boolean isStatus=false;

		try{
			if(userObj!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				User config =  (User) session.get(User.class,userObj.getId());
				config.setModifieddate(new Date());
				config.setStatus(userObj.isStatus());
				session.merge(config);
				transaction.commit();
				isStatus=true;
			}
		}catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			isStatus=false;
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return isStatus;
	}

	/*---------------this method for update the Createdby_UserId---in user table--------------------*/
	public boolean updateUserCreatedby_UserId(User newObj,User sessionObj)
	{
		boolean isUpdated=false;
		try{
			if(newObj!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();
				User userObj =  (User) session.get(User.class,newObj.getId());
				if(newObj.getUserrole_id().isSuperuser()){
					userObj.setCreatedbyCompanyUserId(newObj.getId());
					userObj.setModifiedbyCompanyUserId(newObj.getId());
				}
				else{
					userObj.setCreatedbyCompanyUserId(sessionObj.getId());
					userObj.setModifiedbyCompanyUserId(sessionObj.getId());
				}
				session.update(userObj);
				transaction.commit();
				isUpdated=true;
			}
		}catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}

			logger.error("-------HibernateException-------"+  e.getMessage());
			return isUpdated;
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 

		}
		return isUpdated;
	}


	/*---------------get User Details by passing the user Id----------------------*/

	public List<User> getUserDetailsByUserId(int userId){
		List<User> userDetails=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from User com where com.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", userId);
			userDetails = query.list();
		}catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());

		}
		catch (Exception e) {
			logger.error("------Exception-------"+  e.getMessage());

		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}

		return userDetails;
	}
	public User getUserByUserId(int userId){
		User userDetails = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from User com where com.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", userId);
			userDetails = (User) query.uniqueResult();
		}catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());

		}
		catch (Exception e) {
			logger.error("------Exception-------"+  e.getMessage());

		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}

		return userDetails;
	}
	public User getUserByUserName(String username){
		User userDetails = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from User com where com.Firstname=:Firstname";
			Query query = session.createQuery(sql);
			query.setParameter("Firstname", username);
			userDetails = (User) query.uniqueResult();
		}catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());

		}
		catch (Exception e) {
			logger.error("------Exception-------"+  e.getMessage());

		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}

		return userDetails;
	}

	/* -----------------------------------update  updateCompanyPassword In User table--------------------------------*/
	public boolean updateCompanyPasswordInUser(Company  company){
		boolean isCompanyPwdUpdated= updateCompanyPasswordInCompany(company); 
		User  userListObj=null;
		if(isCompanyPwdUpdated){
			userListObj= getUserByCompanyidandEmail(company);
		}
		boolean isUpdate=false;
		User userObj=null;
		Session session=null;
		Transaction transaction=null;
		try{
			if(isCompanyPwdUpdated){
				if(userListObj!=null){
					session = HibernateUtil.getSessionFactory().openSession();
					transaction = session.beginTransaction();
					userObj =  (User) session.get(User.class,userListObj.getId());
					userObj.setPassword(company.getPassword());
					session.update(userObj);
					transaction.commit(); 
					if(userObj.getId()==userListObj.getId()){
						isUpdate=true;
					}
				}
			}
		}
		catch (Exception e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("------Exception-------"+  e.getMessage());
			return isUpdate;
		}
		finally {
			if(session!=null &&session.isOpen()){
				session.close(); 
			}
		}

		return isUpdate;

	}

	/* -----------------------------------update  updateUser Password In User table--------------------------------*/
	public User updateUserPassword(User  user){
		//		boolean isUpdate=false;
		User userObj = null;
		try{
			if(user!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();
				userObj =  (User) session.get(User.class,user.getId());
				userObj.setPassword(user.getPassword());
				session.update(userObj);
				transaction.commit(); 
			}
		}
		catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
			//			return isUpdate;
		}
		catch (Exception e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("------Exception-------"+  e.getMessage());
			return userObj;
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 

		}
		return userObj;
	}

	/*getUserList User details*/
	public  User  getUserByCompanyidandEmail(Company company)
	{
		String sql="";
		if(company!=null){
			sql = "from User u where u.Companyid=:companyid and u.Email=:email";
		}
		User  newUserObj=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("companyid", company.getCompanyid());
			query.setParameter("email", company.getEmail());
			newUserObj = (User) query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return newUserObj;

	}

	/*getUserList User details*/
	public List<User> getUserListByCompanyid(User user)
	{
		String sql="";
		if(user!=null){
			sql = "from User com where com.Companyid=:companyId";
		}
		List<User> userLi=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("companyId", user.getCompanyid());
			userLi = query.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return userLi;

	}

	/* -----------------------------------update  updateCompanyPassword In  Company table--------------------------------*/

	public boolean updateCompanyPasswordInCompany(Company user){
		Session session=null;
		Transaction transaction=null;
		boolean isUpdate=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			logger.info("----user.getCompanyid()-----------"+user.getCompanyid());
			Company companyObj =  (Company) session.get(Company.class,user.getCompanyid());

			companyObj.setPassword(user.getPassword());
			session.update(companyObj);
			isUpdate=true;
			transaction.commit();
		}catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
			return isUpdate;
		}
		catch (Exception e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("------Exception-------"+  e.getMessage());
			return isUpdate;
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 

		}

		return isUpdate;

	}

	public synchronized UserWallet getUpdatedUserWallet(User sessionObj){
		UserWallet userWallet = null;
		User user=null;
		String sql="";
		if(sessionObj!=null){
			Company company= new CompanyDAO().getCompanyProfile(sessionObj.getCompanyid());
			if(company!=null && company.getCompanyRole()!=null &&  company.getCompanyRole().isCorporate())
				user=new UserDAO().getUserPasswordForLock(company.getEmail());
		}
		try
		{
			sql = "from UserWallet uw where uw.walletId=:walletid"; 
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			if(user!=null) 
				query.setParameter("walletid",user.getAgentWallet().getWalletId());	
			else 
				query.setParameter("walletid",sessionObj.getAgentWallet().getWalletId());	

			userWallet = (UserWallet) query.uniqueResult();	
		}catch(HibernateException e){
			logger.info("########################### HibernateException--"+e.getMessage());
			logger.error(e);

		}finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return userWallet;

	}

	/*	method for Save UserDesignation*/

	public UserDesignation SaveandUpdateUserDesignation(UserDesignation userdesignation)
	{
		Session  session=null;
		Transaction tx = null;
		try
		{		     
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(userdesignation);
			tx.commit();
		}catch(HibernateException e){
			logger.error("--------------HibernateException-------------"+e.getMessage());
			tx.rollback();
		}finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return userdesignation;
	}

	public List<UserDesignation> getDesignationList ()
	{
		Session  session=null;
		List<UserDesignation> list=null;
		try
		{		     
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(UserDesignation.class);
			list= criteria.list();
		}catch(HibernateException e){
			logger.error("--------------HibernateException-------------"+e.getMessage());

		}finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return list;
	}



	public List<UserDesignation> SaveandUpdateUserDesignationExcel(UserDesignation userdesignation)
	{
		List<UserDesignation> updatedList= new ArrayList<>();
		Session  session=null;
		Transaction tx = null;
		try
		{		     
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(userdesignation);
			tx.commit();
			updatedList.add(userdesignation);


		}catch(HibernateException e){
			logger.error("--------------HibernateException-------------"+e.getMessage());
			tx.rollback();
		}finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return updatedList;
	}

	public  List<Boolean>  isUserDesignationExists(String designation)
	{
		List<Boolean> list =new ArrayList<>();
		Session  session=null;

		try
		{		     
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(UserDesignation.class);
			UserDesignation userDesignation=(UserDesignation) criteria.add(Restrictions.eq("designation",designation)).uniqueResult();
			if(userDesignation!=null && userDesignation.getDesignation().equalsIgnoreCase(designation)) 
				list.add(true);

			else
				list.add(false);

		}catch(HibernateException e){
			logger.error("--------------HibernateException-------------"+e.getMessage());

		}finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return list;
	}
	public List<UserDesignation> GetUserDesignationList(Company companyobj)
	{
		List<UserDesignation> designationlist = new ArrayList<UserDesignation>();
		try
		{
			String sql = "from UserDesignation ud  where ud.companyid =:companyId";
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("companyId", companyobj.getCompanyid());
			designationlist =  query.list();	
		}catch(HibernateException e){
			logger.error("--------------HibernateException-------------"+e.getMessage());

		}finally{
			if(session!=null && session.isOpen())
				session.close();
		}

		return designationlist;
	}
	public Company checkUserExistInCompany(User user)
	{
		Company company = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(Company.class);
			cr.add(Restrictions.eq("Email",user.getEmail()));
			company = (Company) cr.uniqueResult();
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return company;
	}
	public User checkCompanyExistInUser(User user) 
	{
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(User.class);
			cr.add(Restrictions.eq("Email",user.getEmail()));
			user = (User) cr.uniqueResult();
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return user;	
	}

	public List<User> getAllAgenciesByCompanyId(Company companySessionObj){
		List<Company> agencyCompanyIdList = null;
		List<User> agencyList = null;
		List<Integer> agencyCompanyIds=new ArrayList<>();

		if(companySessionObj!=null){
			agencyCompanyIdList=getAllAgencyCompanyIds(companySessionObj);
		}
		if(agencyCompanyIdList!=null && agencyCompanyIdList.size()>0){
			for (int i = 0; i < agencyCompanyIdList.size(); i++) {
				Company companyId= (Company)agencyCompanyIdList.get(i);
				agencyCompanyIds.add(companyId.getCompanyid());

			}
		}
		try{
			String query="from User u where u.Companyid in (:agencyCompanyIds)";
			System.out.println("Agencies  user query-----------"+query);
			session = HibernateUtil.getSessionFactory().openSession();
			Query sqlQuery = session.createQuery(query);
			sqlQuery.setParameterList("agencyCompanyIds",agencyCompanyIds);
			agencyList = sqlQuery.list();	
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return agencyList;
	}

	public List<Company> getAllAgencyCompanyIds(Company companySessionObj){
		// TODO Auto-generated method stub
		List<Company> agencyList = null;
		String sql="from Company com where com.parent_company_userid=:parentcompanyuserid";
		logger.info("Agencies query-----------"+sql);
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("parentcompanyuserid", companySessionObj.getCompany_userid());
			agencyList = query.list();	
		}catch(HibernateException e){
			logger.error("--------------HibernateException-------------"+e.getMessage());

		}finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return agencyList;
	}
	public boolean validateUserByIdPassword(int userId, String password) {
		boolean flag=false;

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from User com where com.id=:id and  com.Password=:password";
			Query query = session.createQuery(sql);
			query.setParameter("id", userId);
			query.setParameter("password", password);
			User userDetails = (User) query.uniqueResult();
			if(userDetails!=null)
				flag = true;

		}catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());

		}
		catch (Exception e) {
			logger.error("------Exception-------"+  e.getMessage());

		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return flag;
	}


	public List<User> getallUserListByCompanyUserId(Company companyObj)
	{ 
		CompanyFilter companyFilter=new CompanyFilter();
		companyFilter.setLoginCompany(companyObj);
		CompanyFilterPage companyFilterPage=new CompanyFilterPage();
		companyFilterPage.setCompanyFilter(companyFilter);
		List<Integer> companyIdList=new CompanyFilterDao().getCompanyIdList(companyFilterPage);
		if(companyIdList!=null && companyIdList.size()>0){
		}
		else{
			companyIdList=new ArrayList<>();
			companyIdList.add(new Integer(0));
		}
		List<User> userListNew= new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(User.class);
			criteria.add(Restrictions.in("Companyid", companyIdList));
			/*String sql ="from User com where com.Companyid in(:ids)";
			Query query = session.createQuery(sql);
			query.setParameterList("ids", companyIdList);
			userList = query.list();
			 */
			List<User> userList=criteria.list(); 
			if(userList==null){
				userListNew=userList ;
			}


		}catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());

		}
		catch (Exception e) {
			logger.error("------Exception-------"+  e.getMessage());

		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return userListNew;


	}
	public List<User>  getAllTechHeadandSupportList() {
		// TODO Auto-generated method stub
		Session session=null;
		List<User> userList=null;
		Disjunction disjunction=Restrictions.disjunction();
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(User.class);
			disjunction.add(Restrictions.eq("isTechSupport", true));
			disjunction.add(Restrictions.eq("isTechHead", true));
			criteria.createCriteria("userrole_id").add(disjunction);
			userList=criteria.list();
		}catch(HibernateException he){
			logger.info("HibernateException.............."+he.getMessage());
		}
		return userList;
	}

	/*public List<User>  getAllUserHavingUserMode(List<UserRole> userRoleList,User) {
		// TODO Auto-generated method stub
		Session session=null;
		List<UserRole> userList=null;
		List<User> users=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(User.class);
			list=criteria.list();
		     userList=filterUserBasedUserMode();
			for (User user2 : users) {

			}


			criteria.add(Restrictions.eq("Companyid", user.getCompanyid()));
			userList=criteria.list();
			disjunction.add(Restrictions.eq("isTechSupport", true));
			disjunction.add(Restrictions.eq("isTechHead", true));
			criteria.createCriteria("userrole_id").add(disjunction);
			userList=criteria.list();
		}catch(HibernateException he){
			logger.info("HibernateException.............."+he.getMessage());
		}
		finally{
			session.close();
		}
		return userList;
	}*/



	public List<UserRole>  filterUserBasedUserModexxxx() {
		// TODO Auto-generated method stub
		Session session=null;
		List<UserRole> userRoleList=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(UserRole.class);
			criteria.add(Restrictions.eq("isUsermode", true));
			userRoleList=criteria.list();
			/*disjunction.add(Restrictions.eq("isTechSupport", true));
		disjunction.add(Restrictions.eq("isTechHead", true));
		criteria.createCriteria("userrole_id").add(disjunction);
		userList=criteria.list();*/
		}catch(HibernateException he){
			logger.info("HibernateException.............."+he.getMessage());
		}
		finally {
			session.close();
		}
		return userRoleList;
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

	public User getUserByEmail(String email){
		Session session = null;
		User user = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("Email", email));
			user = (User) criteria.uniqueResult();
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return user;
	}

	public List<CompanyBusinessUnitEntity> insertCompanyBusinessUnit(List<CompanyBusinessUnitEntity> businessUnitList) {
		Session session = null;
		Transaction transaction = null;
		List<CompanyBusinessUnitEntity> insertedBusinessUnitList = new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			for (CompanyBusinessUnitEntity companyBusinessUnitEntity : businessUnitList) {
				session.save(companyBusinessUnitEntity);
				insertedBusinessUnitList.add(companyBusinessUnitEntity);
			}
			transaction.commit();

		}catch (Exception e) {
			logger.error("--------------insertCompanyBusinessUnit Exception-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return insertedBusinessUnitList;
	}
	public List<CompanyBusinessUnitEntity> updateCompanyBusinessUnit(List<CompanyBusinessUnitEntity> businessUnitList) {
		Session session = null;
		Transaction transaction = null;
		List<CompanyBusinessUnitEntity> updatedBusinessUnitList = new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			for (CompanyBusinessUnitEntity companyBusinessUnitEntity : businessUnitList) {
				session.update(companyBusinessUnitEntity);
				updatedBusinessUnitList.add(companyBusinessUnitEntity);
			}
			transaction.commit();

		}catch (Exception e) {
			logger.error("--------------insertCompanyBusinessUnit Exception-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updatedBusinessUnitList;
	}

	public List<CompanyCostCenterEntity> insertCostCenterList(List<CompanyCostCenterEntity> companyCostCenterList){
		Session session = null;
		Transaction transaction = null;
		boolean isInserted = false;
		List<CompanyCostCenterEntity> costCenterList = new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			for (CompanyCostCenterEntity companyCostCenterEntity : companyCostCenterList) {
				session.save(companyCostCenterEntity);
				costCenterList.add(companyCostCenterEntity);
			}
			transaction.commit();
			isInserted = true;
		}catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return costCenterList;
	}

	public List<CompanyCostCenterEntity> isCostCenterExist(long companyId){
		Session session = null;
		List<CompanyCostCenterEntity> existedCostCenterList = new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(CompanyCostCenterEntity.class);
			criteria.add(Restrictions.eq("companyId", companyId));
			existedCostCenterList = criteria.list();

		}catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return existedCostCenterList;
	}

	public List<CompanyBusinessUnitEntity> isBusinessUnitExistByCompanyId(long companyId){
		Session session = null;
		List<CompanyBusinessUnitEntity> existedBusinessUnitList = new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(CompanyBusinessUnitEntity.class);
			criteria.add(Restrictions.eq("companyId", companyId));
			existedBusinessUnitList = criteria.list();

		}catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return existedBusinessUnitList;
	}
	public List<CompanyCostCenterEntity> isCostCenterExistByCompanyId(long companyId){
		Session session = null;
		List<CompanyCostCenterEntity> existedCostCenterList = new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(CompanyCostCenterEntity.class);
			criteria.add(Restrictions.eq("companyId", companyId));
			existedCostCenterList = criteria.list();

		}catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return existedCostCenterList;
	}

	public List<CompanyDepartmentEntity> isDepartmentExistByCompanyId(long companyId){
		Session session = null;
		List<CompanyDepartmentEntity> existedDepartmentList = new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(CompanyDepartmentEntity.class);
			criteria.add(Restrictions.eq("companyId", companyId));
			existedDepartmentList = criteria.list();

		}catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return existedDepartmentList;
	}
	public List<CompanyBandEntity> isBandExistByCompanyId(long companyId){
		Session session = null;
		List<CompanyBandEntity> existedBandList = new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(CompanyBandEntity.class);
			criteria.add(Restrictions.eq("companyId", companyId));
			existedBandList = criteria.list();

		}catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return existedBandList;
	}


	public List<CompanyDepartmentEntity> insertDepartmentEntity(List<CompanyDepartmentEntity> departmentList) {
		Session session = null;
		Transaction transaction = null;
		List<CompanyDepartmentEntity> insertedDepartmentList = new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			for (CompanyDepartmentEntity companyDepartmentEntity : departmentList) {
				session.save(companyDepartmentEntity);
				insertedDepartmentList.add(companyDepartmentEntity);
			}
			transaction.commit();

		}catch (Exception e) {
			logger.error("--------------insertDepartmentEntity Exception-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return insertedDepartmentList;
	}

	public boolean insertDesignationList(List<CompanyDesignationEntity> companyDesignationList){
		Session session = null;
		Transaction transaction = null;
		boolean isInserted = false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			for (CompanyDesignationEntity companyDesignationEntity : companyDesignationList) {
				session.save(companyDesignationEntity);
			}
			transaction.commit();
			isInserted = true;
		}catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return isInserted;
	}

	public List<CompanyBandEntity> insertCompanyBandEntity(List<CompanyBandEntity> bandList) {
		Session session = null;
		Transaction transaction = null;
		List<CompanyBandEntity> insertedBandList = new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			for (CompanyBandEntity companyBandEntity : bandList) {
				session.save(companyBandEntity);
				insertedBandList.add(companyBandEntity);
			}
			transaction.commit();

		}catch (Exception e) {
			logger.error("--------------insertCompanyBandEntity Exception-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return insertedBandList;
	}
	public List<String> getUserEmailListByCompanyId(int companyid){
		List<String> emailList = new ArrayList<>();
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("Companyid", companyid));
			List<User> userlist = criteria.list();
			if(userlist != null && userlist.size()>0){
				for (User user : userlist) {
					emailList.add(user.getEmail());
				}
			}

		}catch (Exception e) {
			logger.error("--------------getUserEmailListByCompanyId Exception-----------------"+e.getMessage());
		}
		return emailList;
	}

}
