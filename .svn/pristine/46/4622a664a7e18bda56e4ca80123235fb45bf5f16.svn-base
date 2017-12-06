package com.admin.knockoff.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.knockoff.common.filter.page.KnockOffFilter;
import com.admin.knockoff.common.filter.page.KnockOffPage;
import com.admin.knockoff.dao.KnockOffDao;
import com.admin.knockoff.dao.KnockOffDaoImpl;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class KnockOffListAction extends ActionSupport implements SessionAware,ModelDriven<KnockOffPage>{
	/**
	 * @author info raham
	 * created date : 31st Aug 2015
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(KnockOffListAction.class);
	KnockOffPage knockOffPage = new  KnockOffPage();
	SessionMap<String , Object> sessionMap;
	KnockOffDao knockOffDao=new KnockOffDaoImpl();
	public String showKnockOffListXXX(){
		String pageRedirectType="";
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");
		knockOffPage = getKnockOffPage();
		KnockOffFilter knockOffFilter = knockOffPage.getKnockOffFilter();
		knockOffFilter.setLoginCompany(companySessionObj);
		knockOffFilter.setLoginUser(userSessionObj);
		knockOffFilter.setTravelType(knockOffPage.getKnockOffFilter().getTravelType());
		knockOffPage.setKnockOffFilter(knockOffFilter); 
	 switch (knockOffPage.getKnockOffFilter().getTravelType()) {
		case "Flight":
			pageRedirectType="flight";
			break;

		case "Hotel":
			pageRedirectType="hotel";
			break;

		case "Car":
			pageRedirectType="car";
			break;

		case "Bus":
			pageRedirectType="bus";
			break;

		case "Train":
			pageRedirectType= "train";
			break;

		case "Visa":
			pageRedirectType="visa";
			break;

		case "Insurance":
			pageRedirectType="insurance";
			break;
		default:
			pageRedirectType="flight";
			break;
		} 
		knockOffPage =knockOffDao.commonKnockOffBookings(knockOffPage);
		return pageRedirectType;
	}

	public String showKnockOffList(){
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");
		knockOffPage = getKnockOffPage();
		KnockOffFilter knockOffFilter = knockOffPage.getKnockOffFilter();
		knockOffFilter.setLoginCompany(companySessionObj);
		knockOffFilter.setLoginUser(userSessionObj);
		knockOffFilter.setTravelType(knockOffPage.getKnockOffFilter().getTravelType());
		knockOffPage.setKnockOffFilter(knockOffFilter); 
		knockOffPage =knockOffDao.commonKnockOffBookings(knockOffPage);
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public KnockOffPage getModel() {
		// TODO Auto-generated method stub
		return knockOffPage;
	}

	public KnockOffPage getKnockOffPage() {
		return knockOffPage;
	}

	public void setKnockOffPage(KnockOffPage knockOffPage) {
		this.knockOffPage = knockOffPage;
	}

}
