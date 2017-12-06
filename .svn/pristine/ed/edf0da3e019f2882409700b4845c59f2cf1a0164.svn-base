package com.lintas.admin.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;

import com.lintas.admin.DAO.CompanyDAO;

public class ReportsAndOrderFilter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<String> getDatelist(String fromdate,String todate){
		List<String> datelist=new ArrayList<String>();
		List<Date> dates = new ArrayList<Date>();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		try {
			startDate = (Date)formatter.parse(fromdate);
			Date  endDate = (Date)formatter.parse(todate);
			long interval = 24*1000 * 60 * 60; // 1 hour in millis
			long endTime =endDate.getTime() ; // create your endtime here, possibly using Calendar or Date
			long curTime = startDate.getTime();
			while (curTime <= endTime) {
				dates.add(new Date(curTime));
				curTime += interval;
			}
			for(int i=0;i<dates.size();i++){
				Date lDate =(Date)dates.get(i);
				String ds = formatter.format(lDate);    
				//logger.info("Date is ..." + ds);
				datelist.add(ds);
			}
			//datelist.add(reportData.getUser());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return datelist;

	}

	/* this method for fetch company and under users*/
	public List<User> getCompanyUserList(SessionMap<String, Object> sessionMap ){
		User user=(User)sessionMap.get("User");
		List<User> li=new CompanyDAO().getCompanyUserList(user);
		return li;
	}

	public List<User> getRoleUser(User user){
	 
		//logger.info(user.getId()+"........"+user.getUsername());
		List<User> li=new CompanyDAO().getRoleUser(user);
		return li;
	}

	 public Map<List<User>,List<User>> getSuperuserCompanyList(SessionMap<String, Object> sessionMap)
		{
			User user=(User)sessionMap.get("User");
			List<User> li=new CompanyDAO().getSuperuserCompanyList(user);
			List<User> companyList=new ArrayList<User>();
			List<User> userList=new ArrayList<User>();
			Map<List<User>,List<User>> map=new  LinkedHashMap<List<User>, List<User>>();

			//logger.info("size..."+li.size());
			for(User u:li){
				UserRole role=u.getUserrole_id();
			 if(!role.isSuperuser()&&role.isUsermode()) {
					companyList.add(u);
					
				 } 
				if(role.isSuperuser()){
					companyList.add(u);
				}
				if(!role.isSuperuser() && !role.isUsermode()){
					userList.add(u);
				}
			}
			map.put(companyList, userList);
		
			return map;

		}



}
