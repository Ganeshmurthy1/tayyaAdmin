package com.lintas.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;



public class UserAction extends ActionSupport implements SessionAware,ModelDriven<User>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user = new User();
	SessionMap<String, Object> sessionmap ;
	UserDAO dao = new UserDAO();
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserDAO getDao() {
		return dao;
	}

	public void setDao(UserDAO dao) {
		this.dao = dao;
	}
	
	/*public String DeleteUserProfile()
	{
		 
		boolean deleted = dao.DeleteUserProfile(user);
		if(deleted)
		{
			addActionMessage("Successfully Deleted.");
			return SUCCESS;
		}
		else
		{
			addActionError("Oops! Try Again.");
			return ERROR;
		}
		
	}*/
	

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		sessionmap = (SessionMap<String, Object>) arg0;
		
	}

}
