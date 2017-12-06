/*package com.lintas.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.AddressException;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.AgentDAO;
import com.lintas.admin.DAO.MailStatusDao;
import com.lintas.admin.model.Agent;
import com.lintas.admin.model.MailStatus;
import com.lintas.admin.model.User;
import com.lintas.config.MailConfig;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

public class AgentRegistrationAction extends ActionSupport implements ModelDriven<Agent>,SessionAware{

	*//**
				@author info name:raham
 				created date:26-07-2015
	 *//*
	private static final long serialVersionUID = 1L;
	private Agent agent = new Agent(); 
	private  AgentDAO agentDao  = new  AgentDAO(); 
	SessionMap<String, Object> sessionmap ;
	MailStatus ms=new MailStatus();
	MailStatusDao mailDao=new MailStatusDao();
	private  MailConfig email=new MailConfig();
	private String mailStatus="";
	Map<String,String> jsonobjRes  =  new HashMap<String, String>();


	public String agentReg(){	

		try {
			if(!agentDao.isAgentExists(agent)){
				agent.setCreatedDate(new Date());
				agent.setMainUserId(233);
				agentDao.insertAgentDetails(agent);
				logger.info("agent register mail....."+agent.getEmail()); 
				ms =email.sendRegMail(agent.getEmail());//if register success mail will go by raham
				mailStatus=ms.getMailStatus();
				logger.info("Mail Status......"+mailStatus);
				if(mailStatus!=null || mailStatus.length()>0){
					mailDao.insertMailStatus(getMailRegStausData(agent, "Agent-Registartion",mailStatus));
					 addActionMessage("Successfully Registered...!");
				}
				else{
					addActionMessage(getText("usererror.registerfail"));
				}
				
	    		return SUCCESS;
			 }
			else{
				addActionMessage("Agent Already Existed...!");
				return ERROR;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			addActionError("Registartion Failed....Try Again!");
			return ERROR;
		}

	}

	public String EditAgentProfile()
	{
		Agent agent=null;
		if((String)sessionmap.get("agentEmail")!=null){
			agent = agentDao.GetAgentProfile((String)sessionmap.get("agentEmail"));
			if(sessionmap!=null){
				sessionmap.put("Agent", agent);
				return SUCCESS;
			}
		}

		else{
			return ERROR;
		}
		return SUCCESS;




	}

	public String deleteAgent(){
		logger.info(agent.getAgentid());
		 try {
			 
				boolean isDelete=agentDao.deleteAgentDetails(agent);				
				if(isDelete){
					addActionMessage("Successfully Deleted...!");
					return SUCCESS;
				}
				else{
					addActionMessage("Agent Not Existed or Please Check Credentials!");
					return ERROR;
				}
			}
			
		 catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			addActionError("Deleteion Failed....Try Again!");
			return ERROR;
		}
		 

	}

	public String agentLogout()
	{
		if(sessionmap!=null){
			sessionmap.invalidate();
		}
		return SUCCESS;
	}
	public String getAgentList(){
		 List<Agent>  agent_li= agentDao.getAgentList();
		 if(agent_li.size()>0){
			sessionmap.put("agentList", agent_li);
			logger.info("agent List----"+agent_li.size());
			
		}
		return SUCCESS;
		
	  }
	
	
	
	
	public String updateAgentProfile()
    {
		
    	 boolean isUpdate = agentDao.updateAgentProfile((Agent)sessionmap.get("Agent"),agent) ;  	
    	if(isUpdate)
    	{
    		addActionMessage("Successfully Updated Agent Details..");
    	 return SUCCESS;
    	}
    	else
    	{
    		addActionError("oops!Try Again.");
    		return ERROR;
    	}
    }
	
 @Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionmap=(SessionMap<String, Object>) map;
	}

	@Override
	public Agent getModel() {
		// TODO Auto-generated method stub
		return agent;
	}


	private MailStatus getMailRegStausData(Agent agent,String mailType,String status){
		ms.setUserId(agent.getAgentid());
		ms.getfromMail();
		ms.setToMail(agent.getEmail());
		ms.getSubject();
		ms.getBody();
		ms.getLinkAttached();
		ms.setEmailType(mailType);
		ms.setMailSendDate(agent.getCreatedDate());
		ms.setMailStatus(status);
		return ms;
	}


}
*/