package com.lintas.admin.DAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyEntity;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.SalesLeadGeneration;
import com.lintas.admin.model.User;
import com.lintas.admin.model.UserDesignationRole;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

public class CompanyDAO{
	/**
	 * 
	 */

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CompanyDAO.class);
	/*Insert the company details in the database*/
	public Company registerCompany(Company company, Company creator) throws Exception {
		Session sess = null;
		Transaction tx=null;
		try{
			if(creator != null){
				company.setCompany_userid(company.getCompany_userid());
				company.setParent_company_userid(creator.getCompany_userid());
				company.setSuper_company_userid(creator.getSuper_company_userid());
			} else {
				company.setCompany_userid(company.getCompany_userid());
				company.setParent_company_userid(company.getCompany_userid());
				company.setSuper_company_userid(company.getCompany_userid());
			}

			sess = HibernateUtil.getSessionFactory().openSession();
			tx = sess.beginTransaction();
			//companyid=(Integer)sess.save(company);
			sess.save(company);
			company.setCompType("Transient variable");
			company.setCreateDate("CreateDate");
			company.setModifyDate("modifieddate");
			/*
			 * company.setPostPaidAmount(new BigDecimal("0.00"));
			 * company.setWalletType("wallet");
			 */
			updateCompanyUserId(company,creator,sess);
			tx.commit();
		} catch (HibernateException e) {
			logger.error("---------HibernateException---------"+e.getMessage());
			if(tx!=null){
				tx.rollback();
			}
		} catch (Exception e) {
			if(tx!=null){
				tx.rollback();
			}
			logger.error("---------Exception---------"+e.getMessage());
		} finally {
			if(sess != null && sess.isOpen())
				sess.close();
		}
		return company;
	}
	//update company image path
	public Company updatCompanyImagepath(Company obj){
		Session session = null;
		Transaction transaction = null;
		System.out.println("-imagepath------"+obj.getImagepath());
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(obj);
			transaction.commit();
		}catch(HibernateException e){
			if(transaction!=null){
				transaction.rollback();
			}
			logger.info("---------HibernateException-------------"+e.getMessage());
		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.info("---------Exception-------------"+e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close();
		}
		return obj;

	}

	/*Check company name exists in DB */
	public boolean CompanyExists(String email) throws Exception {
		Session session = null;
		boolean exists = false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from Company com where com.Email=:email";
			Query query = session.createQuery(sql);
			query.setParameter("email", email);
			Company company = (Company) query.uniqueResult();
			if (company!=null) {
				exists = true;
			}
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}

		return exists;
	}


	/*Check Email and password are correct for login*/
	public Company CompanyLogin(String email, String password, String companyId) {
		// String sql = "select * from company com where (com.email='" +
		// username + "' and com.password='" + password + "') and
		// com.company_userid='"+companyId+"'";
		Session session = null;
		Company company=null;
		String sql="from Company as cmp  where cmp.Email = :email and cmp.Password = :password and cmp.company_userid = :companyuserid";
		try{
			//using named parameters to prevent the sql injection
			session = HibernateUtil.getSessionFactory().openSession();
			Query safeHQLQuery = session.createQuery(sql);
			safeHQLQuery.setParameter("email", email);
			safeHQLQuery.setParameter("password", password);
			safeHQLQuery.setParameter("companyuserid",companyId);
			company = (Company)safeHQLQuery.uniqueResult();
		} catch (HibernateException e) {
			logger.error("---------Exception---------"+e.getMessage());
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return company;
	}

	// getting company list  by passing company_user_id
	public List<Company> getCompanyListByCompanyUserId(String companyUserId){
		Session session = null;
		Company companyObj=null;
		Query query=null;
		User userObj=null;
		String sql="";
		List<Company> companyList=new ArrayList<Company>();
		try{

			if(companyUserId!=null){
				companyObj=getCompanyIdByCompanyUserId(companyUserId,session);
				userObj=getUserDetailsByUserid(companyObj.getCompany_userid(), companyObj.getEmail());
			}
			session = HibernateUtil.getSessionFactory().openSession();
			if(companyObj!=null && userObj!=null){
				if(userObj.getUserrole_id().isSuperuser()){
					sql="from Company  as c where c.super_company_userid = :superCompanyUserId";
					query = session.createQuery(sql);
					query.setParameter("superCompanyUserId", companyObj.getCompany_userid());
				} else if (userObj.getUserrole_id().isUsermode() && companyObj.getCompanyRole().isDistributor()) {
					sql="from Company as c  where c.parent_company_userid = :parentCompanyUserId or  c.company_userid =:companyUserId";
					// sql="from Company as c where
					// c.parent_company_userid='"+companyObj.getCompany_userid()+"'
					// or company_userid='"+companyObj.getCompany_userid()+"'";
					query = session.createQuery(sql);
					query.setParameter("parentCompanyUserId", companyObj.getCompany_userid());
					query.setParameter("companyUserId", companyObj.getCompany_userid());
				} else if (userObj.getUserrole_id().isUsermode() && companyObj.getCompanyRole().isAgent()) {
					sql="from Company c where  c.company_userid =:companyUserId";
					query = session.createQuery(sql);
					query.setParameter("companyUserId", companyObj.getCompany_userid());
				}
			}

			List<Company> list = query.list();
			if (list!=null && list.size()> 0) {
				for(Company company:list){
					if(!company.getCompany_userid().equals(companyUserId)){
						if(company.getCompanyRole().isAgent()||company.getCompanyRole().isDistributor()){
							companyList.add(company);	
						}
					}
				}
			}
		} catch (HibernateException e) {
			logger.error("---------HibernateException---------"+e.getMessage());
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return companyList;
	}

	/*method for fetch company ids  by passing company user id*/
	public List<Integer> getAllComapnyIdsByCompanyUserIdTest(String companyUserId){
		Session session = null;
		Query query=null;
		Company companyObj= getCompanyIdByCompanyUserId(companyUserId,session);
		User userObj=getUserDetailsByUserid(companyObj.getCompany_userid(), companyObj.getEmail());
		List<Integer>  companiesList =new ArrayList<>();
		String sql=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(companyObj!=null && userObj!=null){
				if(userObj.getUserrole_id().isSuperuser()){
					sql="from Company  as c where c.super_company_userid = :superCompanyUserId";
					query = session.createQuery(sql);
					query.setParameter("superCompanyUserId", companyObj.getCompany_userid());
				} else if (userObj.getUserrole_id().isUsermode() && companyObj.getCompanyRole().isDistributor()) {
					// sql="select * from company where
					// parent_company_userid='"+companyObj.getCompany_userid()+"'
					// or company_userid='"+companyObj.getCompany_userid()+"'";
					sql="from Company as c  where c.parent_company_userid = :parentCompanyUserId or  c.company_userid =:companyUserId";
					query = session.createQuery(sql);
					query.setParameter("parentCompanyUserId", companyObj.getCompany_userid());
					query.setParameter("companyUserId", companyObj.getCompany_userid());
				} else if (userObj.getUserrole_id().isUsermode() && companyObj.getCompanyRole().isAgent()) {
					sql="from Company c where  c.company_userid =:companyUserId";
					query = session.createQuery(sql);
					query.setParameter("companyUserId", companyObj.getCompany_userid());
				}
			}
			List<Company> list = query.list();
			for(Company id:list){
				companiesList.add(id.getCompanyid());
			}
		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		} catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return companiesList;
	}

	/*method for fetch company ids  by passing company user id*/
	public List<Company> getAllComapnyIdsByCompanyUserId(String companyUserId){
		Session session = null;
		Query query=null;
		Company companyObj= getCompanyIdByCompanyUserId(companyUserId,session);
		User userObj=getUserDetailsByUserid(companyObj.getCompany_userid(), companyObj.getEmail());
		String sql=null;
		List<Company> companiesList=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(companyObj!=null && userObj!=null){
				if(!companyObj.getCompanyRole().isDistributor() && !companyObj.getCompanyRole().isAgent()){
					sql="from Company  as c where c.super_company_userid = :superCompanyUserId";
					query = session.createQuery(sql);
					query.setParameter("superCompanyUserId", companyObj.getCompany_userid());
				} else if (companyObj.getCompanyRole().isDistributor()) {
					// sql="select * from company where
					// parent_company_userid='"+companyObj.getCompany_userid()+"'
					// or company_userid='"+companyObj.getCompany_userid()+"'";
					sql="from Company as c  where c.parent_company_userid = :parentCompanyUserId or  c.company_userid =:companyUserId";
					query = session.createQuery(sql);
					query.setParameter("parentCompanyUserId", companyObj.getCompany_userid());
					query.setParameter("companyUserId", companyObj.getCompany_userid());
				} else if (companyObj.getCompanyRole().isAgent()) {
					sql="from Company c where  c.company_userid =:companyUserId";
					query = session.createQuery(sql);
					query.setParameter("companyUserId", companyObj.getCompany_userid());
				}
			}
			companiesList = query.list();

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		} catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return companiesList;
	}

	/*get all agents by passing company ids*/
	public List<User> getAllAgentsByUserId(String companyUserId){
		Session session = null;
		Map<Integer,Company> companyNameMap=new HashMap<>();
		List<Company>  companyList =getAllComapnyIdsByCompanyUserId(companyUserId);
		List<Integer>  newCompanyIds=new ArrayList<>();
		if(companyList!=null && companyList.size()>0){
			for(Company c:companyList){
				companyNameMap.put(c.getCompanyid(), c);
				newCompanyIds.add(c.getCompanyid());
			}
		}
		List<User> newUsersList= new ArrayList<User>();
		try{

			if(newCompanyIds!=null && newCompanyIds.size()>0){
				String sql="from User u where u.Companyid in(:companyids)";
				logger.info("---------all agents list query--------------"+sql);
				session = HibernateUtil.getSessionFactory().openSession();
				Query companyQuery = session.createQuery(sql);
				companyQuery.setParameterList("companyids", newCompanyIds);
				List<User> list = companyQuery.list();
				if (list!=null && list.size() > 0) {
					for(User user:list){
						Company companyObj=companyNameMap.get(user.getCompanyid());
						if(!user.getUserrole_id().isSuperuser() &&! user.getUserrole_id().isUsermode()){
							if(!user.getUsername().equalsIgnoreCase("DirectUser")){
								if(user.getCompanyid()==companyObj.getCompanyid()){
									user.setCompanyName(companyObj.getCompanyname());
									if(companyObj.getCompanyRole().isAgent()){
										user.setCompanyType("Agency");
									} else if (companyObj.getCompanyRole().isDistributor()) {
										user.setCompanyType("Wholesaler");
									} else {
										user.setCompanyType("Super Agency");
									}
									newUsersList.add(user);	
								}
							}
						}
					}
				} 
			}
		} catch (HibernateException e) {
			logger.error("--------HibernateException-------"+ e.getMessage());
		} catch (Exception e) {
			logger.error("--------Exception-------"+ e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close();
		}
		return newUsersList;
	}
	public Integer getUserIdByCompanyId(int companyId){
		Session session = null;

		int userId=0;
		try{
			if(companyId>0){
				String sql = "from User as u where u.Companyid=:companyid";
				logger.info("----- userid- query---"+sql);
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameter("companyid", companyId);
				List<User> list = query.list();
				if (list!=null && list.size() > 0) {
					for (User user:list){
						if(user.getUserrole_id().isUsermode() || user.getUserrole_id().isSuperuser()){
							userId=user.getId();
						}
					}
				}
			}
		} catch (HibernateException e) {
			logger.error("---------HibernateException---------"+e.getMessage());
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return userId;
	}
	/*filtering company wallet list passing company user id*/
	public List<User> getCompanyWalletListByCompanyUserId(String companyUserId){
		Session session = null;
		List<Company> companyList=	getCompanyListByCompanyUserId(companyUserId);
		List<Integer> companyIds= new ArrayList<>();
		if(companyList!=null && companyList.size()>0){
			for (int i = 0; i < companyList.size(); i++) {
				Company companyObj= (Company)companyList.get(i);
				companyIds.add(companyObj.getCompanyid());
				/*
				 * if(i == companyList.size()-1)
				 * companyListBuffer.append("'"+companyObj.getCompanyid()+"'");
				 * else
				 * companyListBuffer.append("'"+companyObj.getCompanyid()+"',");
				 */
			} 
		}

		String sql="";
		List<User> userList=new ArrayList<User>();
		try{
			if(companyUserId!=null){
				sql = "from User u where u.Companyid in (:companyids)";
				logger.info("-----Filter Agent list- query---"+sql);
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameterList("companyids",companyIds);
				List<User> list = query.list();
				if (list!=null && list.size() > 0) {
					for (User user:list){
						if(user.getUserrole_id().isUsermode()){
							userList.add(user);	
						}
					}
				}

			}
		} catch (HibernateException e) {
			logger.error("---------HibernateException---------"+e.getMessage());
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return userList;
	}

	/*filtering company wallet list passing   user id*/
	public List<User> getCompanyWalletListByUserId(String userId){
		List<User> filterUserList=getUserListByCreatedUserId(userId);
		List<User> newCompanyWalletList=new ArrayList<User>();
		try{
			if(filterUserList!=null && filterUserList.size()>0){
				for (User user:filterUserList){
					if(user.getUserrole_id().isUsermode()){
						newCompanyWalletList.add(user);	
					}
				}
			}
		}

		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());
		} 
		return newCompanyWalletList;
	}

	public List<User> getWalletListByCompanyUserId(String companyUserId){
		Session session = null;
		List<User> walletList=new ArrayList<User>();
		int userId=getUserIdByCompanyId(getCompanyIdByCompanyUserId(companyUserId,session).getCompanyid());
		try{
			if(userId>0){
				String sql  = "from User as u where u.createdbyCompanyUserId = :createdbyCompanyUserId";
				logger.info("-----Filter wallet list- query---"+sql);
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameter("createdbyCompanyUserId", userId);
				List<User> list = query.list();
				if(session != null && session.isOpen()){
					session.close();
				}
				if (list!=null && list.size() > 0) {
					for (User user:list){
						if(user.getId()!=userId){
							walletList.add(user);
						}
					}
				}
			}
		} catch (HibernateException e) {
			logger.error("---------HibernateException---------"+e.getMessage());
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close();
		}
		return walletList;
	}

	// khalid for json creation getting companylist and users list under
	// superuser
	public List<Company> getCompanyistByUserId(String userId){
		List<Company> companyList= new ArrayList<Company>();
		List<User> userList=getUserListByCreatedUserId(userId);
		List<User> filterUserList= new ArrayList<User>();
		List<Integer>  companyids= new ArrayList<>();
		if(userList!=null && userList.size()>0){
			for(User usernew:userList){
				if(usernew.getUserrole_id().isUsermode()){
					filterUserList.add(usernew);
				}
			}
			Session session = null;
			logger.info("-----userList--------------"+userList.size());
			StringBuilder companyListBuffer=new StringBuilder();
			if(filterUserList!=null && filterUserList.size()>0){
				for (int i = 0; i < filterUserList.size(); i++) {
					User userObj= (User)filterUserList.get(i);
					companyids.add(userObj.getCompanyid());
					/*
					 * if(i == filterUserList.size()-1)
					 * companyListBuffer.append("'"+userObj.getCompanyid()+"'");
					 * else
					 * companyListBuffer.append("'"+userObj.getCompanyid()+"',")
					 * ;
					 */
				}
			}

			try{

				if(companyids!=null && companyids.size()>0){
					String sql = "from Company c where  c.companyid in (:companyids)"; 
					logger.info("-----company list query by pass user--------------"+sql);
					session = HibernateUtil.getSessionFactory().openSession();
					Query query = session.createQuery(sql);
					query.setParameterList("companyids", companyids);
					companyList = query.list();
				}
			}catch(HibernateException e){
				logger.error("---------HibernateException---------"+e.getMessage());
			} catch (Exception e) {
				logger.error("---------Exception---------"+e.getMessage());
			} finally {
				if(session != null && session.isOpen())
					session.close();
			}		

		}

		return companyList;
	}

	/*loading the wallet users   company names*/
	public List<User> getAllAgentAgentsCompanyTypeByCompanyId(List<User> userList){
		List<Company> allCompanyNames= null;
		List<User> allWalletUsers= new ArrayList<>();
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(userList!=null && userList.size()>0){
				for(User user:userList){
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
								} else if (companyName.getCompanyRole().isDistributor()) {
									user.setCompanyType("Wholesaler");
								} else {
									user.setCompanyType("Super Agency");
								}
								allWalletUsers.add(user);
							}
						}
					}
				}
			}
		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return allWalletUsers;
	}

	/*fetch agents list by passing user id */
	public List<User> getAgentListByUserId(String userId){
		List<User> userList= new ArrayList<User>();
		List<User> filterUserList=getUserListByCreatedUserId(userId);
		logger.info("-----filterUserList--------------"+filterUserList.size());
		List<User> usersCompanyList=null;
		if(filterUserList!=null && filterUserList.size()>0){
			usersCompanyList=getAllAgentAgentsCompanyTypeByCompanyId(filterUserList);
		}
		try{
			if(usersCompanyList!=null && usersCompanyList.size()>0){
				for(User userObj:usersCompanyList){
					System.out.println("getCompanyName----------"+userObj.getCompanyName());
					if(!userObj.getUserrole_id().isUsermode() && !userObj.getUserrole_id().isSuperuser()){
						userList.add(userObj);
					}
				}
			}
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		} 
		return userList;
	}


	/*fetch agents list by passing user id */
	public List<User> getWalletListByUserId(String userId){
		List<User> walletList= new ArrayList<User>();
		List<User> filterUserList=getUserListByCreatedUserId(userId);
		logger.info("-----filterUserList--------------"+filterUserList.size());
		try{
			if(filterUserList!=null && filterUserList.size()>0){
				for(User userObj:filterUserList){
					if(userObj.getId()!=Integer.parseInt(userId))
						walletList.add(userObj);
				}
			}
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		} 
		return walletList;
	}









	/*fetch users list by passing user id*/
	public List<User> getUserListByCreatedUserId(String userId){
		Session session = null;
		String sql="";
		List<User> userList=null;

		try{
			Integer userIdInt = Integer.valueOf(userId);
			if(userId!=null){
				sql = "from User com where com.createdbyCompanyUserId =:createdbyCompanyUserId";
				logger.info("---------get created by users Query-------"+sql);
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameter("createdbyCompanyUserId", userIdInt);
				userList = query.list();
			}
		} catch (HibernateException e) {
			logger.error("---------HibernateException---------"+e.getMessage());
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close();
		}
		return userList;
	}

	// khalid for json creation getting companylist and users list under
	// superuser
	public List<Company> getCompanyListByComapnyUserId(String companyUserId, String comEmail){
		Company companyObj=getCompanyDetailsByUserid(companyUserId, comEmail);
		User userObj=getUserDetailsByUserid(companyUserId, comEmail);
		String sql="";
		List<Company> companyLi=null;
		Query query=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(!companyObj.getCompanyRole().isDistributor() &&  !companyObj.getCompanyRole().isAgent()){
				sql = "from Company com where com.super_company_userid=:supercompanyuserid";
				query = session.createQuery(sql);
				query.setParameter("supercompanyuserid", companyObj.getSuper_company_userid());
			}
			if(companyObj.getCompanyRole().isDistributor() || companyObj.getCompanyRole().isAgent()){
				sql = "from Company com where com.company_userid = :companyuserid or com.parent_company_userid =:parentcompanyuserid";
				query = session.createQuery(sql);
				query.setParameter("companyuserid", companyObj.getCompany_userid());
				query.setParameter("parentcompanyuserid", companyObj.getCompany_userid());
			}


			companyLi = query.list();
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close();
		}
		return companyLi;
	}


	public Company getCompanyDetailsByUserid(String companyUserId, String comEmail){
		Session session = null;
		Company company=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();	
			// String sql = "select * from company com where
			// com.email='"+comEmail+"' and
			// com.company_userid='"+companyUserId+"'";
			String sql = "from Company com where com.Email=:email and com.company_userid=:companyUserId";
			Query query = session.createQuery(sql);
			query.setParameter("email",comEmail);
			query.setParameter("companyUserId",companyUserId);
			company = (Company) query.uniqueResult();
		} catch (HibernateException e) {
			logger.error("---------HibernateException---------"+e.getMessage());
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return company;
	}

	public User getUserDetailsByUserid(String companyUserId, String comEmail){
		Session session = null;

		User user=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();	

			String sql = "from User com where com.Email=:email and com.company_userid=:companyUserId";
			Query query = session.createQuery(sql);
			query.setParameter("email",comEmail);
			query.setParameter("companyUserId",companyUserId);
			user = (User) query.uniqueResult();
		} catch (HibernateException e) {
			logger.error("---------HibernateException---------"+e.getMessage());
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return user;
	}

	public List<User> getUserListByCompanyId(String companyUserId){
		Session session = null;
		Company companyObj=getCompanyIdByCompanyUserId(companyUserId,session);
		String sql="";
		List<User> userList=new ArrayList<User>();

		try{
			if(companyObj!=null){
				sql = "from User com where com.Companyid = :companyid";
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameter("companyid", companyObj.getCompanyid());
				List<User> list = query.list();
				if (list!=null && list.size() > 0) {
					for (User userObj:list){
						if(!userObj.getUserrole_id().isUsermode() && !userObj.getUserrole_id().isSuperuser()){
							/*if(userObj.getUserrole_id().isAdmin()){*/
							userList.add(userObj);
							/*}*/
						}
					}
				} 
			}
		} catch (HibernateException e) {
			logger.error("---------HibernateException---------"+e.getMessage());
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close();
		}
		return userList;
	}


	/*	fetch company details by company_user_id */
	public Company getCompanyIdByCompanyUserId(String companyUserId,Session session){
		String sql="";
		Company company=null;
		try{
			if(companyUserId!=null){
				sql = "from Company com where  com.company_userid =:companyUserId";
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameter("companyUserId",companyUserId);
				company = (Company) query.uniqueResult();
			}
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close();
		}
		return company;
	}



	//getting companylist and users list under superuser
	public List<User> getSuperuserCompanyList(User u){
		Session session = null;
		String sql="";
		List<User> userLi=null;
		try{
			if(u!=null){
				sql = "from User com where com.company_userid =:companyuserid order by com.id  desc";
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameter("companyuserid", u.getCompany_userid());
				userLi = query.list();
			}
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return userLi;
	}


	//get userlist under company  
	public List<User> getCompanyUserList(User u){
		Session session = null;
		String sql="";
		List<User> userLi=null;
		try{
			if(u!=null){
				sql = "from User com where com.Companyid =:companyid";
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameter("companyid",u.getCompanyid());
				userLi = query.list();
			}
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return userLi;
	}

	//get perticular User  who ever login    
	public List<User> getRoleUser(User u){
		Session session = null;

		String sql="";
		List<User> userLi=null;
		try{
			if(u!=null){
				sql = "from User com where com.id =:id";
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameter("id", u.getId());
				userLi = query.list();
			}
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return userLi;

	}

	/*Check Email and password are correct*/
	public Company CompanyLogin(String companyId) {
		Session session = null;
		Company company=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();	
			// String sql = "select * from company com where
			// com.company_userid='"+companyId+"'";
			String sql = "from Company com where com.company_userid = :companyuserid";
			Query query = session.createQuery(sql);
			query.setParameter("companyuserid", companyId);
			company = (Company) query.uniqueResult();
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return company;
	}

	/*get the CompanyProfile base on company id*/
	public Company getCompanyProfile(int companyId) {
		Session session = null;
		Company company=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();	
			String sql = "from Company com where  com.companyid=:companyid";
			Query query = session.createQuery(sql);
			query.setParameter("companyid", companyId);
			company = (Company) query.uniqueResult();
			if (company != null) {
				if(company.getCreateddate() != null)
					company.setCreateDate(DateConversion.convertDateToStringToDateWithTIME(company.getCreateddate()));
				if(company.getModifieddate() != null)
					company.setModifyDate(DateConversion.convertDateToStringToDateWithTIME(company.getModifieddate()));
				if(company.getAgreementExpiryDate() != null)
					company.setAgreementTranExpiryDate(
							DateConversion.convertDateToStringToDateWithTIME(company.getAgreementExpiryDate()));

				if(company.getCompanyRole().isAgent()){
					company.setCompType("Agency");
				}
				if(company.getCompanyRole().isDistributor()){
					company.setCompType("Wholesaler");
				}
			}
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return company;
	}
	/*get the CompanyProfile from user by email and company id*/
	public User getCompanyProfileFromUser(int companyId, String email) {
		Session session = null;
		User newUserObj=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();	
			String sql = "from User u where u.Companyid=:companyid  and  u.Email=:email";
			Query query = session.createQuery(sql);
			query.setParameter("companyid", companyId);
			query.setParameter("email", email);
			newUserObj = (User) query.uniqueResult();

		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return newUserObj;
	}
	// load the all company details using company id
	public List<User> getCompanyListFromUser(Company c){
		Session session = null;

		String sql="";
		List<User> userLi=null;
		try{
			if(c!=null){
				sql = "from User com where com.Companyid =:companyId";
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameter("companyId",c.getCompanyid());
				userLi = query.list();
			}
		} catch (HibernateException e) {
			logger.error("---------HibernateException---------"+e.getMessage());
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		} 
		return userLi;
	}

	/*
	 * bellow methods for upade superuser company profile in user table and
	 * company table
	 */

	public Company superUserCompanyProfileUpdate(Company  updateCompany){
		Session session = null;
		Transaction transaction = null;
		boolean update=false;
		try{
			if(updateCompany!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();	
				session.update(updateCompany);
				transaction.commit();
				update=true;
			}
		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return updateCompany;

	}

	public boolean  updateCompanyProfileInUser(User u){
		boolean updated = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(u);
			transaction.commit();
			updated = true;
		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return updated;

	}

	public Company getNewCompanyId(int companyId) {
		String sql = "from Company com where com.companyid=:companyId";
		logger.info("-------------sql......."+sql);
		Company companyNewObj= null;
		Session session = null;

		try{
			session = HibernateUtil.getSessionFactory().openSession();	
			Query query = session.createQuery(sql);
			query.setParameter("companyId", companyId);
			companyNewObj= (Company)query.uniqueResult();

		} catch (HibernateException e) {
			logger.error("---------HibernateException---------"+e.getMessage());
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return companyNewObj;
	}

	public boolean updateCompanyUserId(Company companyNew, Company companyLoggedIn, Session session) {
		boolean isUpdate=false;
		try{
			String company_userid=null;
			logger.info("------updateCompanyUserIdupdateCompanyUserId------"+companyNew.getCompanyid());
			if(companyNew!=null){
				company_userid=companyNew.getCompanyname().substring(0,2).toUpperCase()+companyNew.getCompanyid(); 
			}
			if (companyNew != null) {
				if(companyLoggedIn!=null){
					companyNew.setCompany_userid(company_userid);
					companyNew.setParent_company_userid(companyLoggedIn.getCompany_userid());
					companyNew.setSuper_company_userid(companyLoggedIn.getSuper_company_userid());
				} else {
					companyNew.setCompany_userid(company_userid);
					companyNew.setParent_company_userid(company_userid);
					companyNew.setSuper_company_userid(company_userid);
				}
				session.saveOrUpdate(companyNew);
			}
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
			return isUpdate;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isUpdate;
	}
	/*Get the company profile passing email */
	public Company GetCompanyProfile(Company sessionObj) {
		Session session = null;
		Company company= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();	
			String sql = "from Company com where com.companyid=:companyId";
			Query query = session.createQuery(sql);
			query.setParameter("companyId", sessionObj.getCompanyid());
			company = (Company)query.uniqueResult();
			if (company != null) {
				company.setCreateDate(DateConversion.convertDateToStringToDateWithTIME(company.getCreateddate()));
				company.setModifyDate(DateConversion.convertDateToStringToDateWithTIME(company.getModifieddate()));
			}
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return company;
	}
	/*Update company profile*/
	public boolean UpdateCompanyProfile(Company companyOld, Company companyNew) {
		Session session = null;
		Transaction transaction = null;
		boolean updateFlag=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			Company company =  (Company) session.get(Company.class,companyOld.getCompanyid());
			if (company != null) {
				company.setSecurityquestion(companyNew.getSecurityquestion());
				company.setSecurityanswer(companyNew.getSecurityanswer());
				company.setAddress(companyNew.getAddress());
				company.setPhone(companyNew.getPhone());
				company.setUsername(companyNew.getUsername());
				company.setCompanydescription(companyNew.getCompanydescription());
				company.setModifieddate(new Date());
				session.update(company);
				transaction.commit();
				updateFlag=true;
			}

		}catch(Exception e){
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("---------Exception---------"+e.getMessage());
			return updateFlag;
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return updateFlag;

	}
	/*Delete the company profile*/
	public boolean DeleteCompanyProfile(Company oldcompanyobj) {
		Session session = null;
		Transaction transaction = null;
		boolean updateFlag=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();	
			Company companyupdate = (Company) session.get(Company.class,oldcompanyobj.getCompanyid());
			if (companyupdate != null) {
				session.delete(companyupdate);
				transaction.commit();
				updateFlag=true;
			}
		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("---------Exception---------"+e.getMessage());
			return updateFlag;
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return updateFlag;
	}

	/*passing company_userid and mail  retrieve companyid List details*/
	public Company getCompanyId(String email, String companyid) {
		Session session = null;
		Company company=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from Company com where com.Email=:email and com.company_userid = :companyUserId"; 
			Query query = session.createQuery(sql);
			query.setParameter("email", email);
			query.setParameter("companyUserId", companyid);
			company = (Company) query.uniqueResult();
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return company;
	}

	/* method for active or deactive the company and under users      */
	public User updateSuperUserCompanyActiveOrInactive(User u) {
		Session session = null;
		Transaction transaction = null;
		User status= new User();
		try{
			if(u!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();
				logger.info(" update status id--------- "+u.getId());
				logger.info(" update is status--------- "+u.isStatus());
				User config = (User) session.get(User.class,u.getId());
				config.setStatus(u.isStatus());
				status.setStatus(config.isStatus());
				session.update(config);
				transaction.commit();
			}
		} catch (Exception e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return status;


	}

	/* method for retrieve the  users Id list  passing Company Id*/
	public List<User> getUserIdList(Company companyObj) {
		Session session = null;
		List<User> userLi= new ArrayList<User>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			// String sql="select * from user where
			// companyid='"+companyObj.getCompanyid()+"'";
			String sql="from User u where u.Companyid=:companyId";
			Query query = session.createQuery(sql);
			query.setParameter("companyId", companyObj.getCompanyid());
			List<User> list = query.list();
			for(User user:list){
				if(user!=null && user.getUserrole_id().isUsermode()){
					userLi.add(user);
				}
			}
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return userLi;
	}


	/* method for active or deactive the company  bsed on company id    */
	public Company updateCompanyActiveOrInactive(User u) {
		Session session = null;
		Transaction transaction = null;
		Company status= new Company();
		try{
			if(u!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				Company config =  (Company) session.get(Company.class,u.getCompanyid());
				config.setStatus(u.isStatus());
				status.setStatus(config.isStatus());
				status.setCompanyid(u.getCompanyid());
				session.update(config);
				transaction.commit();

			}
		}catch(Exception e){
			if(transaction!=null)
				transaction.rollback();
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return status;
	}

	/* method for active or deactive the company  bsed on company id    */
	public Company updateCompanyLockOrUnlock(User u) {
		Session session = null;
		Transaction transaction = null;
		Company status= new Company();
		try{
			if(u!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				Company config =  (Company) session.get(Company.class,u.getCompanyid());
				config.setLocked(u.isLocked());
				status.setLocked(config.isLocked());
				status.setCompanyid(u.getCompanyid());
				session.update(config);
				transaction.commit();

			}
		}catch(Exception e){
			if(transaction!=null)
				transaction.rollback();
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return status;
	}


	/* method for active or deactive the SuperUser User      */
	public User updateSuperUser_UserActiveOrInactive(User u) {
		Session session = null;
		Transaction transaction = null;
		User config =null;
		try{
			if(u!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				config =  (User) session.get(User.class,u.getId());
				config.setStatus(u.isStatus());
				session.update(config);
				transaction.commit();
			}
		} catch(Exception e){
			if(transaction!=null)
				transaction.rollback();
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return config;


	}

	/* method for active or deactive the Company  User      */
	public User updateCompanyUserActiveOrInactive(User u) {
		Session session = null;
		Transaction transaction = null;
		User status= new User();
		try{
			if(u!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				User config = (User) session.get(User.class,u.getId());
				config.setStatus(u.isStatus());
				status.setStatus(config.isStatus());
				session.update(config);
				transaction.commit();
			}
		} catch (Exception e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return status;
	}


	/* method for lock or unlock the super user company lock */
	public User updateSuperUserCompanyLockOrUnlock(User u) {
		Session session = null;
		Transaction transaction = null;
		User status= new User();
		try{
			if(u!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				User config =  (User) session.get(User.class,u.getId());
				config.setLocked(u.isLocked());
				status.setLocked(config.isLocked());
				session.update(config);
				transaction.commit();

			}
		} catch (Exception e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return status;
	}
	/* method for lock or unlock the super user user lock */
	public User updateSuperUser_UserLockOrUnlock(User u) {
		Session session = null;
		Transaction transaction = null;
		User status= new User();
		try{
			if(u!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				User config =  (User) session.get(User.class,u.getId());
				config.setLocked(u.isLocked());
				status.setLocked(config.isLocked());
				session.update(config);
				transaction.commit();

			}
		}catch(Exception e){
			if(transaction!=null)
				transaction.rollback();
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return status;

	}

	/* method for lock or unlock the Company  User      */
	public User updateCompanyUserLockOrUnlock(User u) {
		Session session = null;
		Transaction transaction = null;
		User status= new User();
		try{
			if(u!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				User config =  (User) session.get(User.class,u.getId());
				config.setLocked(u.isLocked());
				status.setLocked(config.isLocked());
				session.update(config);
				transaction.commit();

			}
		}catch(Exception e){
			if(transaction!=null)
				transaction.rollback();
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return status;
	}

	public boolean ifAnyCompanyExist() {
		Session session = null;
		boolean isExist=false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Long companyCount = (Long) session.createCriteria(Company.class).setProjection(Projections.rowCount()).uniqueResult();
			if (companyCount!=null && companyCount > 0) {
				isExist = true;
			}
		} catch (Exception e) {
			logger.error("---------Exception---------"+e.getMessage());
			return isExist;
		} finally {
			if(session != null && session.isOpen())
				session.close();
		}
		return isExist;
	}


	/*method for fetch companies list  by passing company user id*/
	public List<Company> getAllDistributors(User userSessionObj,Company companySessionObj){
		Session session = null;
		List<Company> companiesList =new ArrayList<Company>();
		String sql="from Company com where com.super_company_userid=:superCompanyUserid";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query  companyQuery = session.createQuery(sql);
			companyQuery.setParameter("superCompanyUserid", companySessionObj.getCompany_userid());
			List<Company> list=companyQuery.list();
			if(list!=null && list.size()>0){
				for(Company company:list){
					if (company.getCompanyRole().isDistributor()) {
						companiesList.add(company);
					}
				} 
			}
		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		} catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return companiesList;

	}

	public List<Company> getAllCorporates(User userSessionObj,Company companySessionObj){
		Session session = null;
		List<Company> companiesList =new ArrayList<Company>();
		String sql="from Company com where com.super_company_userid=:superCompanyUserid";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query  companyQuery = session.createQuery(sql);
			companyQuery.setParameter("superCompanyUserid", companySessionObj.getCompany_userid());
			List<Company> list=companyQuery.list();
			if(list!=null && list.size()>0){
				for(Company company:list){
					if (company.getCompanyRole().isCorporate()) {
						companiesList.add(company);
					}
				} 
			}
		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		} catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return companiesList;

	}

	/*method for fetch companies list  by passing company user id*/
	public List<Company> getAllCompaniesByCompanyUserId(User userSessionObj,Company companySessionObj){
		Session session = null;
		List<Company> companiesList = null;
		String sql=null;
		Query  query=null;
		session = HibernateUtil.getSessionFactory().openSession();
		try{
			if(!companySessionObj.getCompanyRole().isDistributor() && !companySessionObj.getCompanyRole().isAgent()){
				sql="from Company com where com.super_company_userid=:supercompanyuserid order by com.companyid desc";
				query = session.createQuery(sql);
				query.setParameter("supercompanyuserid", companySessionObj.getCompany_userid());

			} else if (companySessionObj.getCompanyRole().isDistributor()) {
				sql="from Company com where com.parent_company_userid=:parentcompanyuserid or company_userid=:companyuserid order by com.companyid desc";
				query = session.createQuery(sql);
				query.setParameter("parentcompanyuserid", companySessionObj.getCompany_userid());
				query.setParameter("companyuserid", companySessionObj.getCompany_userid());

			} else if (companySessionObj.getCompanyRole().isAgent()) {
				sql="from Company com where com.company_userid=:company_userid order by com.companyid desc";
				query = session.createQuery(sql);
				query.setParameter("company_userid", companySessionObj.getCompany_userid());

			}

			companiesList = query.list();

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		} catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return companiesList;

	}


	/*-------------------------get all company Approval companies ----------------------*/
	public List<Company> getApprovalCompaniesList(Company company){
		Session session = null;

		List<Company> approvalCompanyLi=null;
		String sql = "from Company com  where com.parent_company_userid=:parentcompanyuserid order by com.companyid desc";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("parentcompanyuserid", company.getCompany_userid());
			approvalCompanyLi = query.list();

		}catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return approvalCompanyLi;
	}

	/*-------------------------get all company Approval companies ----------------------*/
	public List<Company> getAllApprovalCompaniesList(User userSessionObj,Company companySessionObj){
		Session session = null;
		List<Company> approvalCompanyLi=null;
		String sql=null;
		Query query=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(!companySessionObj.getCompanyRole().isDistributor() && !companySessionObj.getCompanyRole().isAgent()){
				sql="from Company com where com.super_company_userid=:supercompanyuserid";
				query = session.createQuery(sql);
				query.setParameter("supercompanyuserid", companySessionObj.getCompany_userid());
			} else if (companySessionObj.getCompanyRole().isDistributor()) {
				sql="from Company com where com.parent_company_userid=:parentcompanyuserid or  com.company_userid=:companyuserid";
				query = session.createQuery(sql);
				query.setParameter("parentcompanyuserid", companySessionObj.getCompany_userid());
				query.setParameter("companyuserid", companySessionObj.getCompany_userid());
			} else if (companySessionObj.getCompanyRole().isAgent()) {
				sql="from Company com where com.company_userid=:companyuserid";
				query = session.createQuery(sql);
				query.setParameter("companyuserid", companySessionObj.getCompany_userid());
			}
			/*
			 * else{ if(userSessionObj.getUserrole_id().isAdmin()){
			 * if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid(
			 * )){ if(companySessionObj.getCompanyRole().isDistributor()){
			 * sql="select * from company where parent_company_userid='"
			 * +companySessionObj.getCompany_userid()+"' or company_userid='"
			 * +companySessionObj.getCompany_userid()+"'"; } else
			 * if(companySessionObj.getCompanyRole().isAgent()){
			 * sql="select * from company where company_userid='"
			 * +companySessionObj.getCompany_userid()+"'"; } else{
			 * sql="select * from company where super_company_userid='"
			 * +companySessionObj.getCompany_userid()+"'"; } } }
			 * 
			 * logger.
			 * info("------Direct Admin query for particular company- for- fetching company list---------"
			 * +sql); }
			 */
			//System.out.println("sql "+sql);
			approvalCompanyLi = query.list();
		}catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return approvalCompanyLi;

	}

	/*----------------------- method for approval the company  based on company id---------------*/
	public Company updateCompanyApproval(Company c) {
		Session session = null;
		Transaction transaction = null;
		Company status= null;
		try{
			if(c!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				Company  config =  (Company) session.get(Company.class,c.getCompanyid());
				config.setModifieddate(new Date());
				config.setStatus(c.isStatus());
				session.update(config);
				status=config;
				transaction.commit();
			}
		}catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return  status;
	}

	/* this method for get userIDs by passing  company id*/
	public List<User> getUserIdsByCompanyIds(List<Company> companiesList){
		Session session = null;
		List<User> userIds =new ArrayList<User>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(companiesList!=null && companiesList.size()>0){
				logger.info("------companiesList---size--------"+companiesList.size());
				for(Company companyObj:companiesList){
					String useridsSql="from User u where u.Companyid=:companyid";
					Query userQuery = session.createQuery(useridsSql);
					userQuery.setParameter("companyid", companyObj.getCompanyid());
					userIds.addAll(userQuery.list());
				}
			}
		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		} catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return userIds;
	} 

	public UserDesignationRole InsertUserDesignationRole(UserDesignationRole userDesignationRoleObj){
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(userDesignationRoleObj);
			transaction.commit();
		}catch(HibernateException e){
			if(transaction!=null){
				transaction.rollback();
			}
			logger.info(""+e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close();
		}
		return userDesignationRoleObj;
	}

	public User updateUserImagePath(User userObj){
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(userObj);
			transaction.commit();
		}catch(HibernateException e){
			if(transaction!=null){
				transaction.rollback();
			}
			logger.info(""+e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close();
		}
		return userObj;
	}

	public Email insertEmail(String userid, int status, int emailType) {
		Session session = null;
		Transaction transaction = null;
		Email email = new Email();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			email.setOrderId(userid);
			email.setMailStatus(status);
			email.setType(emailType);
			Timestamp updated_at = new Timestamp(new Date().getTime());		
			email.setUpdatedAt(updated_at);
			email.setCreatedAt(updated_at);
			session.save(email);
			transaction.commit();
		}catch(HibernateException e){
			if(transaction!=null){
				transaction.rollback();
			}
			logger.info(""+e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close();
		}
		return email;
	}

	public Email insertEmail(Email email) {
		Session session = null;
		Transaction transaction = null;

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Timestamp updated_at = new Timestamp(new Date().getTime());		
			email.setUpdatedAt(updated_at);
			email.setCreatedAt(updated_at);
			session.save(email);
			transaction.commit();
		}catch(HibernateException e){
			if(transaction!=null){
				transaction.rollback();
			}
			logger.info(""+e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close();
		}
		return email;
	}

	public boolean checkCompanyRole(int companyId){
		Session session = null;
		boolean isMasterCompany=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(Company.class);
			criteria.add(Restrictions.eq("companyid", companyId));
			Company companyObj=(Company) criteria.uniqueResult();
			System.out.println("companyObj----"+companyObj);
			if(!companyObj.getCompanyRole().isAgent() && !companyObj.getCompanyRole().isDistributor()) 
				isMasterCompany=true;
		}catch(HibernateException e){
			logger.info(""+e.getMessage());
		} finally {
			if(session != null && session.isOpen())
				session.close();
		}
		return isMasterCompany;
	}

	//Added by Ramesh for Invoicing purpose


	@SuppressWarnings("finally")	
	public Company getParentCompany(Company company) throws HibernateException {
		Session sess = null;		
		Criteria crit = null;
		Company parentcompany = null;
		logger.info("Email verification call");
		try{
			sess = HibernateUtil.getSessionFactory().openSession();			
			crit = sess.createCriteria(Company.class);			
			crit.add(Restrictions.eq("company_userid", company.getParent_company_userid()));			
			parentcompany = (Company) crit.uniqueResult();				
			sess.close();	

		}catch (HibernateException e) {			
			throw e; 
		}finally {
			if (sess != null && sess.isOpen()) {
				sess.close(); 
			}
			return parentcompany;
		}
	}

	public Company getParentCompanyByParentCompanyUserid(String parentCompanyUserid) throws HibernateException {
		Session sess = null;		
		Criteria crit = null;
		Company parentcompany = null;
		logger.info("Email verification call");
		try{
			Conjunction con = Restrictions.conjunction();
			sess = HibernateUtil.getSessionFactory().openSession();			
			crit = sess.createCriteria(Company.class);			
			con.add(Restrictions.eq("parent_company_userid", parentCompanyUserid));
			crit.createCriteria("companyRole").add(Restrictions.eq("isSuperUser", true));
			crit.add(con);
			parentcompany = (Company) crit.uniqueResult();				
		}catch (HibernateException e) {			
			throw e; 
		}finally {
			sess.close(); 
		}
		return parentcompany;
	}


	@SuppressWarnings("finally")	
	public Company getCompanyByCompanyUserId(String companyUserId) throws HibernateException {
		Session sess = null;		
		Criteria crit = null;
		Company parentcompany = null;
		logger.info("Email verification call");
		try{
			sess = HibernateUtil.getSessionFactory().openSession();			
			crit = sess.createCriteria(Company.class);			
			crit.add(Restrictions.eq("company_userid", companyUserId));			
			parentcompany = (Company) crit.uniqueResult();				
			sess.close();	

		}catch (HibernateException e) {			
			throw e; 
		}finally {
			if (sess != null && sess.isOpen()) {
				sess.close(); 
			}
			return parentcompany;
		}
	}

	@SuppressWarnings("finally")

	public User getCompanyWalletUser(Company company) throws HibernateException {
		Session sess = null;		
		Criteria crit = null;
		User user = null;		
		logger.info("### get wallet user for company call");
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			// select * from company where companyid in (select companyid from
			// user where id = 1);
			String sql = "select * from user where companyid = " + company.getCompanyid() + " and email ='"
					+ company.getEmail() + "';";
			logger.info("########################## DB get Wallet user of company select query--"+sql);
			SQLQuery query = sess.createSQLQuery(sql);
			query.addEntity(User.class);
			user = (User) query.uniqueResult();			

		}catch (HibernateException e) {			
			throw e; 
		}finally {
			if (sess != null && sess.isOpen()) {
				sess.close(); 
			}
			return user;
		}
	}



	public User getUserByUserId(String userId) throws HibernateException {
		Session sess = null;
		User userDb = null;
		try{
			Integer userid = Integer.valueOf(userId);
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit1 = sess.createCriteria(User.class);			
			crit1.add(Restrictions.eq("id", userid));
			userDb = (User) crit1.uniqueResult();	
			logger.info("Email getUserByUserId retrival----userDb--"+userDb);
		} catch (HibernateException e) {			
			logger.error("HibernateException", e);
		} catch (Exception e) {			
			logger.error("Exception", e);
		} finally {
			if(sess != null && sess.isOpen())
				sess.close();
		}
		return userDb;
	}

	@SuppressWarnings("finally")	
	public Company getParentCompany(User user) throws HibernateException {
		Session sess = null;		
		Criteria crit = null;
		Company company = null;
		Company parentcompany = null;
		logger.info("### get parent company call");
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			// select * from company where companyid in (select companyid from
			// user where id = 1);
			String sql = "select * from company where companyid in (select companyid from user where id = "
					+ user.getId() + ")";
			logger.info("########################## DB get Company select query--"+sql);
			SQLQuery query = sess.createSQLQuery(sql);
			query.addEntity(Company.class);
			company = (Company) query.uniqueResult();
			if(!user.getUserrole_id().isSuperuser() && !user.getUserrole_id().isUsermode())// employee
			{
				parentcompany =  company;
			} else {
				crit = sess.createCriteria(Company.class);			
				crit.add(Restrictions.eq("company_userid", company.getParent_company_userid()));			
				parentcompany = (Company) crit.uniqueResult();				
			}

		}catch (HibernateException e) {			
			throw e; 
		}finally {
			if (sess != null && sess.isOpen()) {
				sess.close(); 
			}
			return parentcompany;
		}
	}

	@SuppressWarnings("finally")	
	public Company getImmediateChildCompanyBooked(Company company, Integer orderUserId) throws HibernateException {
		Session sess = null;		
		Criteria crit = null;
		Company parentCompany = null;
		Company bookchildCompany = null;		
		logger.info("### DB get immediate book company call");
		try{
			sess = HibernateUtil.getSessionFactory().openSession();

			// select * from company where companyid in (select companyid from
			// user where id = 1);
			String sql = "select * from company where companyid in (select companyid from user where id = "
					+ orderUserId + ")";
			logger.info("########################## DB get immediate book company query--"+sql);
			SQLQuery query = sess.createSQLQuery(sql);
			query.addEntity(Company.class);
			bookchildCompany = (Company) query.uniqueResult();

			if (bookchildCompany != null) {
				if ((company.getCompanyid() != bookchildCompany.getCompanyid())) {
					logger.info(
							"########################## DB get immediate book company call--Booker is not a super distributor");
					crit = sess.createCriteria(Company.class);			
					crit.add(Restrictions.eq("company_userid", bookchildCompany.getParent_company_userid()));			
					parentCompany = (Company) crit.uniqueResult();		
					if (parentCompany != null && (parentCompany.getCompanyid() == company.getCompanyid())) {
						logger.info(
								"########################## DB get immediate book company call--Distributor level book--parent is caller ");
						return bookchildCompany;
					} else if (parentCompany != null && (parentCompany.getCompanyid() != company.getCompanyid())) {
						logger.info(
								"########################## DB get immediate book company call--Agency level book--parent is Distributor ");
						return parentCompany;
					}
				} else {
					logger.info("########################## DB get immediate book company call--Booker is a caller ");
					return company;
				}
			}
			sess.close();	

		}catch (HibernateException e) {			
			throw e; 
		}finally {
			if (sess != null && sess.isOpen()) {
				sess.close(); 
			}

		}
		return bookchildCompany;
	}


	public List<String> getChildrenCompanyIds(int companyId, int userId){
		Session session = null;
		String sql="";
		List<String> companyIdList= new ArrayList<String>();
		Session sess = null;
		try{			
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = sess.createCriteria(User.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			reportConjunction.add(Restrictions.eq("createdbyCompanyUserId",userId));
			reportConjunction.add(Restrictions.ne("Companyid",companyId));
			criteria.add(reportConjunction);		
			criteria.createCriteria("userrole_id").add(Restrictions.eq("isUsermode",true));					
			List<User> userList = (ArrayList<User>) criteria.list();	
			logger.info("########################## DB getChildrenCompanyIds -- userList "+userList);

			if (userList != null && userList.size() > 0) {
				logger.info("########################## DB getChildrenCompanyIds -- userList size "+userList.size());

				for (User user : userList) {
					companyIdList.add(String.valueOf(user.getCompanyid()));
				}
			}
		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(sess!=null && sess.isOpen())
				sess.close(); 
		}

		return companyIdList;
	}
	public List<User> getSalesPersons(User user) {
		// TODO Auto-generated method stub
		//User userNew=getUserDetailsByCompanyId(user);
		List<User> salesPersonsList =null;
		Session session=null;
		Disjunction disjunction=Restrictions.disjunction();
		try{

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(User.class);
			disjunction.add(Restrictions.eq("createdbyCompanyUserId", user.getId()));
			disjunction.add(Restrictions.eq("id", user.getId()));
			criteria.add(disjunction);
			salesPersonsList=criteria.list();
		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return salesPersonsList;
	}
	public  User getUserDetailsByCompanyId(User user) {
		// TODO Auto-generated method stub
		Company  company=null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Company.class);
			criteria.add(Restrictions.eq("companyid", user.getCompanyid()));
			company=(Company) criteria.uniqueResult();
		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}


		return 	getCompanyDetailsByCompanyId(company);
	}

	public  User getCompanyDetailsByCompanyId(Company company) {
		// TODO Auto-generated method stub
		User  user=null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("Email", company.getEmail()));
			user=(User) criteria.uniqueResult();
		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return user;
	}
	public List<Company> getDirectCompanyListUnderParent(Company sessionObj) {
		// TODO Auto-generated method stub
		Session session=null;
		List<Company> newComapnyList=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Company.class);
			Conjunction conj=Restrictions.conjunction();
			conj.add(Restrictions.eq("parent_company_userid",sessionObj.getParent_company_userid()));
			conj.add(Restrictions.ne("company_userid",sessionObj.getParent_company_userid()));
			criteria.add(conj);
			newComapnyList=criteria.list();
		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return newComapnyList;
	}

	public boolean updateSalesPersonChildUserId(Company newCompany,User newUser){
		Session session = null;
		Transaction transaction = null;
		boolean updateFlag=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			SalesLeadGeneration salesLeadGeneration = (SalesLeadGeneration) session.get(SalesLeadGeneration.class,
					newCompany.getSalesLeadGeneration().getId());
			System.out.println("newUser.getId()-------"+newUser.getId());
			salesLeadGeneration.setChildUserId(newUser.getId());
			session.update(salesLeadGeneration);
			transaction.commit();
			updateFlag=true;

		}catch(Exception e){
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("---------Exception---------"+e.getMessage());
			return updateFlag;
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return updateFlag;

	}
	@SuppressWarnings("unchecked")
	public StringBuffer GetAllUserListUnderCompany(User userSessionObj, Company companySessionObj) {
		Session session = null;
		StringBuffer userIdBuffer=new StringBuffer();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			List<User> userIds =new ArrayList<User>();
			List<Company> companiesList = null;
			companiesList = getAllCompanyListUnderCompany(session,companySessionObj);

			if(companiesList!=null && companiesList.size()>0){				
				userIds = 	getUserListUnderCompany(session,companiesList);
				if(userIds.size()>0){
					for (int i = 0; i < userIds.size(); i++) {
						User userId= (User)userIds.get(i);
						if(i == userIds.size()-1)
							userIdBuffer.append("'"+userId.getId()+"'");
						else
							userIdBuffer.append("'"+userId.getId()+"',");
					}
				}
			}
		}catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return userIdBuffer;
	}	

	private List<User> getUserListUnderCompany(Session session, List<Company> companiesList) {
		List<Company> newCompaniesList= new ArrayList<Company>();
		List<User> userIds =new ArrayList<User>();
		for (Company companyObj:companiesList){
			newCompaniesList.add(companyObj);
		}
		for(Company companyObj:newCompaniesList){
			String useridsSql="from User u where u.Companyid=:companyid";
			Query userQuery = session.createQuery(useridsSql);
			userQuery.setParameter("companyid",companyObj.getCompanyid());
			List userList =userQuery.list();
			userIds.addAll(userList);
		}
		return userIds;

	}
	private List<Company> getAllCompanyListUnderCompany(Session session, Company companySessionObj) {
		List<Company> companiesList = null;
		if (companySessionObj.getCompanyRole().isCorporate()
				|| companySessionObj.getCompanyRole().isDistributor()) {
			String getallcompanyundersuperusersql = "from Company c where c.parent_company_userid=:parent_company_userid or c.company_userid=:company_userid";
			Query companyQuery = session.createQuery(getallcompanyundersuperusersql);
			companyQuery = session.createQuery(getallcompanyundersuperusersql);
			companyQuery.setParameter("parent_company_userid", companySessionObj.getCompany_userid());
			companyQuery.setParameter("company_userid", companySessionObj.getCompany_userid());
			companiesList = companyQuery.list();
		} else if (companySessionObj.getCompanyRole().isAgent()) {
			String	getallcompanyundersuperusersql = "from Company c where c.company_userid=:company_userid";
			Query companyQuery = session.createQuery(getallcompanyundersuperusersql);
			companyQuery.setParameter("company_userid", companySessionObj.getCompany_userid());
			companiesList = companyQuery.list();
		} else {
			String getallcompanyundersuperusersql ="from Company com where com.super_company_userid=:super_company_userid";
			Query companyQuery = session.createQuery(getallcompanyundersuperusersql);
			companyQuery = session.createQuery(getallcompanyundersuperusersql);
			companyQuery.setParameter("super_company_userid",companySessionObj.getCompany_userid());
			companiesList = companyQuery.list();
		}
		return companiesList;

	}
	@SuppressWarnings("unchecked")
	public  List<User> GetAllUserListUnderCompanyAsList(User userSessionObj, Company companySessionObj) {
		Session session = null;
		List<User> userIds =new ArrayList<User>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			List<Company> companiesList = null;
			companiesList = getAllCompanyListUnderCompany(session,companySessionObj);

			if(companiesList!=null && companiesList.size()>0){				
				userIds = 	getUserListUnderCompany(session,companiesList);
			}
		}catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return userIds;
	}
	
	public List<Company>  getCorporateCompanyList(){
		List<Company> list=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(Company.class);
			Conjunction conjunction=Restrictions.conjunction();
			conjunction.add(Restrictions.eq("super_company_userid","TA1"));
			criteria.add(conjunction);
			criteria.createCriteria("companyRole").add(Restrictions.eq("isCorporate", true));
			list=criteria.list();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return list;

	}
	public  List<User> getPaymentUserList() {
		// TODO Auto-generated method stub
		List<User> list=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(User.class);
			criteria.add(Restrictions.eq("isStatus", true));
			criteria.add(Restrictions.ne("Email", "DirectUser@intellicommsolutions.com"));
			/*
			 * ProjectionList projectionList=Projections.projectionList();
			 * projectionList.add(Projections.property("userName"));
			 * criteria.setProjection(projectionList);
			 */
			list=criteria.list();

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return list;

	}
	public CompanyEntity registerCompanyEntity(CompanyEntity companyEntity) { 
		Session sess = null;
		Transaction tx=null;
		try{ 
			sess = HibernateUtil.getSessionFactory().openSession();
			tx = sess.beginTransaction(); 
			sess.save(companyEntity); 
			tx.commit();
		}
		catch(HibernateException e){
			logger.error("---------HibernateException---------"+e.getMessage());
			if(tx!=null){
				tx.rollback();
			}
		}
		catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
			logger.error("---------Exception---------"+e.getMessage());
		}
		finally
		{
			if(sess != null && sess.isOpen())
				sess.close();
		}
		return companyEntity; 
	}	

	/* bellow methods for  upade company Entity  */

	public boolean updateCompanyEntity(CompanyEntity companyEntity){
		Session session = null;
		Transaction transaction = null;
		boolean update=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();	
			CompanyEntity companyEntityNew=(CompanyEntity) session.get(CompanyEntity.class, companyEntity.getCompanyEntityId());
			companyEntityNew.setAddress1(companyEntity.getAddress1());
			companyEntityNew.setAddress2(companyEntity.getAddress2());
			companyEntityNew.setCity(companyEntity.getCity());
			companyEntityNew.setCompanyEntityGstIn(companyEntity.getCompanyEntityGstIn());
			companyEntityNew.setCompanyEntityName(companyEntity.getCompanyEntityName());
			companyEntityNew.setCompanyname(companyEntity.getCompanyname());
			companyEntityNew.setCountryname(companyEntity.getCountryname());
			companyEntityNew.setEmail(companyEntity.getEmail());
			companyEntityNew.setPhoneNo(companyEntity.getPhoneNo());
			companyEntityNew.setState(companyEntity.getState());
			companyEntityNew.setStateCode(companyEntity.getStateCode());
			session.update(companyEntityNew);
			transaction.commit();
			update=true;

		}
		catch(Exception e){
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return update;

	}

	public CompanyEntity companyEntityProfile(int id){
		Session session = null;
		CompanyEntity companyEntity=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(CompanyEntity.class);
			criteria.add(Restrictions.eq("companyEntityId", id));
			companyEntity=(CompanyEntity) criteria.uniqueResult();


		}
		catch(Exception e){

			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return companyEntity;

	}


	public boolean deleteCompanyEntity(int id){
		Session session = null;
		Transaction transaction = null;
		boolean isDelete=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String sql = "delete from CompanyEntity ce where ce.companyEntityId=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			if (result> 0) {
				isDelete = true;
			}
			transaction.commit();

		}catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
			return isDelete;

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return isDelete;

	}
	
	public List<Company>  getDistributorUnderSuperuser(){
		List<Company> list=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(Company.class);
			Conjunction conjunction=Restrictions.conjunction();
			conjunction.add(Restrictions.eq("super_company_userid","TA1"));
			criteria.add(conjunction);
			criteria.createCriteria("companyRole").add(Restrictions.eq("isDistributor", true));
			list=criteria.list();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return list;

	}
	public List<Company>  getCorpotatesUnderSuperuser(){
		List<Company> list=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(Company.class);
			Conjunction conjunction=Restrictions.conjunction();
			conjunction.add(Restrictions.eq("super_company_userid","TA1"));
			criteria.add(conjunction);
			criteria.createCriteria("companyRole").add(Restrictions.eq("isCorporate", true));
			list=criteria.list();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return list;

	}
	public List<Company>  getAgencyUnderSuperuser(){
		List<Company> list=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(Company.class);
			Conjunction conjunction=Restrictions.conjunction();
			conjunction.add(Restrictions.eq("super_company_userid","TA1"));
			criteria.add(conjunction);
			criteria.createCriteria("companyRole").add(Restrictions.eq("isAgent", true));
			list=criteria.list();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return list;

	}
	public Company  getCompanyListUsingCompanyUserId(String companyUserid){
		 Company company=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(Company.class);
			criteria.add(Restrictions.eq("companyid",Integer.parseInt(companyUserid)));
			company=(Company) criteria.uniqueResult();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return company;

	}
	 
}
