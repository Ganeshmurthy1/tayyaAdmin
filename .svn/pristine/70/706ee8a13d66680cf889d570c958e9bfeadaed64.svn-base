/*package com.lintas.admin.DAO;

 
 
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lintas.admin.model.Agent;
import com.lintas.admin.model.CompanyMarkup;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;

*//**
@author info name:raham
	created date:26-07-2015
 *//*
public class AgentDAO{
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	Insert the agent details into the database
	public void insertAgentDetails(Agent agent) throws Exception
	{
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		session.save(agent);
		transaction.commit();
		session.close();

	}

	Check agent exists or not  in DB 
	public boolean isAgentExists(Agent agent) throws Exception
	{
		boolean exists = false;
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String sql = "select * from agent a where a.agentname='"+agent.getAgentname()+"'and  a.email='"+agent.getEmail()+"'"; 
		SQLQuery query = session.createSQLQuery(sql);
		List<Agent> list = query.list();
		if (list.size() > 0) {
			exists = true;
		}
		transaction.commit();
		session.close();
		return exists;
	}

	 delete agent from DB if existed...
	public boolean deleteAgentDetails(Agent agent) throws Exception
	{
		logger.info("agentId...."+agent.getAgentid());
		boolean isDelete = false;
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String sql = "DELETE FROM agent WHERE agentid ='"+agent.getAgentid()+"'";
		SQLQuery query = session.createSQLQuery(sql);
		int result=query.executeUpdate();
		if (result> 0) {
			isDelete = true;
		}
		transaction.commit();
		session.close();
		return isDelete;
	}

	Get the Agent profile values
	  public Agent GetAgentProfile(String email)
	  {
	   Agent agent= null;
	   session = HibernateUtil.getSessionFactory().openSession();  
	   String sql = "select * from agent com where com.email='" + email + "'";
	   SQLQuery query = session.createSQLQuery(sql);
	   query.addEntity(Agent.class);
	   List list = query.list();
	   for (Iterator iterator = list.iterator(); iterator.hasNext();){
	    agent = (Agent)iterator.next(); 
	    agent.setAgentid(agent.getAgentid());
	    agent.setAgentname(agent.getAgentname());
	    agent.setAddress(agent.getAddress());
	    agent.setCity(agent.getCity());
	    agent.setCountryName(agent.getCountryName());
	    agent.setDescription(agent.getDescription());
	    agent.setEmail(agent.getEmail());
	    agent.setFax(agent.getFax());
	    agent.setFirstname(agent.getFirstname());
	    agent.setImagepath(agent.getImagepath());
	    agent.setLastname(agent.getLastname());
	    agent.setMobile(agent.getMobile());
	    agent.setSecurityAnswer(agent.getSecurityAnswer());
	    agent.setSecurityQuestion(agent.getSecurityQuestion());
	  
	    
	   }  
	   session.close();
	   return agent;
	  }
	
		Check Email and password are correct
		public  Agent agentLogin(String email, String password)
		{
			logger.info("mail,,,,,,,,,,,,"+email);
			Agent agent=null;
			 
			session = HibernateUtil.getSessionFactory().openSession();		
			String sql = "select * from agent a where a.email='" + email + "' and  a.password='" + password + "'";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Agent.class);
			List<Agent> list = query.list();
			if (list.size() > 0) {
				for (Iterator iterator = list.iterator(); iterator.hasNext();){
				agent = (Agent)iterator.next(); 
				agent.setAgentid(agent.getAgentid());
				agent.setAgentname(agent.getAgentname());
				agent.setCity(agent.getCity());
				agent.setConfPassword(agent.getConfPassword());
				agent.setCountryName(agent.getCountryName());
				agent.setDescription(agent.getDescription());
				agent.setEmail(agent.getEmail());
				agent.setFax(agent.getFax());
				agent.setFirstname(agent.getFirstname());
				agent.setImagepath(agent.getImagepath());
				agent.setLastname(agent.getLastname());
				agent.setMobile(agent.getMobile());
				agent.setPassword(agent.getPassword());
				agent.setSecurityAnswer(agent.getSecurityAnswer());
				agent.setSecurityQuestion(agent.getSecurityQuestion());
				session.close();
				}
			}
			return agent;
			
			 
		}
	  
		sending  user password to  mail, if forgot passwordby raham 
		public String getAgentPassword(String email)
		{
			Agent user = null;
			 String password="";
			 session = HibernateUtil.getSessionFactory().openSession();
			
			String sql = "select *  from agent com where com.email='" +email+ "'";
			SQLQuery query = session.createSQLQuery(sql);
			 query .addEntity(Agent.class);
			List  list = query.list();
			for (Iterator   iterator = list.iterator(); iterator.hasNext();){
				user = (Agent) iterator.next(); 
				password=user.getPassword();
			 }		
			session.close();
			return password;
		}
	  
		Update User details
		public boolean updateAgentProfile(Agent oldconfigobj,Agent newobj)
		{
			boolean updated = false;
			try
			{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			Agent config =  (Agent) session.get(Agent.class,oldconfigobj.getAgentid());
			config.setAddress(newobj.getAddress());
			config.setAgentname(newobj.getAgentname());
			config.setCity(newobj.getCity());
			config.setCountryName(newobj.getCountryName());
			config.setDescription(newobj.getDescription());
			config.setEmail(newobj.getEmail());
			config.setFax(newobj.getFax());
			config.setFirstname(newobj.getFirstname());
			config.setImagepath(newobj.getImagepath());
			config.setLastname(newobj.getLastname());
			config.setMobile(newobj.getMobile());
			config.setModifiedDate(new Date());
			config.setPassword(newobj.getPassword());
			config.setSecurityAnswer(newobj.getSecurityAnswer());
			config.setSecurityQuestion(newobj.getSecurityQuestion());
			session.update(config);
			transaction.commit();
			session.close();
			updated = true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return updated;
			
		}
		get Agent List details
		 
		public List<Agent> getAgentList()
		{
			List<Agent> configLi=null;
			 try
			{
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();
				String sql="select * from agent";
				SQLQuery query = session.createSQLQuery(sql);
				query.addEntity(Agent.class);
				configLi = query.list();
				transaction.commit();
				session.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return configLi;
		}
		
		
		
		
		
		get Agent List details
		public List<Agent> getAgentList()
		{
			List<Agent> agentLi=null;
			 try
			{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			agentLi = session.createQuery("FROM Agent").list(); 
			transaction.commit();
			session.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return agentLi;
			 
		 }
		 
}
*/