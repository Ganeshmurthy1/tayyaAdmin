
package com.tayyarah.notification.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.admin.common.util.CommonUtilSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.tayyarah.notification.Notification;
import com.tayyarah.notification.NotificationDetail;
import com.tayyarah.notification.NotificationFactory;
import com.tayyarah.notification.NotificationInventoryTypeEnum;
import com.tayyarah.notification.dao.NotificationDao;




public class NotificationAction extends ActionSupport implements SessionAware{
	/**
	 * @author Ramesh
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(NotificationAction.class);	
	SessionMap<String, Object> sessionMap;	
	private List<Notification> notifications;
	private Notification  notification;
	private long  notificationId;
	private NotificationDetail notificationDetail;
	SessionMap<String, Object> sessionmap ;
	NotificationDao notificationDao= new NotificationDao();
	public String notificationJson;
	private long notificationid;
	private HashMap<String, Object> notificationInfo;
	
	
		
	public String insertNotification()
	{
		logger.info("notificationJson" +notificationJson);
		try {
			User sessionUser=(User)sessionmap.get("User");
			Notification notification = NotificationFactory.createNotification(notificationJson);
			if(notification != null)
			{
				notification.setCompanyId(sessionUser.getCompanyid());
				notification.setUserId(CommonUtilSession.checkEmulatedUser(sessionmap)?CommonUtilSession.getEmulatedUserIdInt(sessionmap):sessionUser.getId());
				logger.info("###### before insertion-----"+notification.toString());	
				new NotificationDao().insert(notification);	
				return SUCCESS;
			}
			else
			{				
				return SUCCESS;
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return SUCCESS;
		}

	}	
	
	public String notificationsSeeAll(){
		
		return SUCCESS;
	}
	
	
	
	
	public String getPendingNotifications()
	{
		ObjectMapper mapper = new ObjectMapper();		
		if(sessionMap != null && ((Company)sessionMap.get("Company") != null) && ((User)sessionMap.get("User")) != null)	
		{
			Company company = (Company)sessionMap.get("Company");
			User user = (User)sessionMap.get("User");
			notificationInfo = NotificationFactory.createPendingNotificationList(company.getCompanyid(),user.getId());
			nullifyDefaults();			
			return SUCCESS;
		}
		else
		{			
			notificationInfo = NotificationFactory.getPendingNotifications(1,1);
			nullifyDefaults();
			return SUCCESS;
		}
	}
	public String updateNotifications()
	{
		logger.info("notificationid" +notificationid);
		try {
			List<Notification> notifications = new NotificationDao().getNotificationsList(notificationid);
			boolean result = new NotificationDao().updateNotifications(notifications);			
			return SUCCESS;
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return SUCCESS;
		}
	}
	public String updateAllNotifications()
	{
		
		List<Notification> notifications = new ArrayList<Notification>();
		try {
			JSONParser parser = new JSONParser();
			JSONArray json = (JSONArray) parser.parse(notificationJson);			
			for (int i = 0; i < json.size(); i++) {
				 JSONObject jsonobject = (JSONObject) json.get(i);
				 String id = (String) jsonobject.get("id");
				 notifications.add(new NotificationDao().getNotifications(Long.parseLong(id)));
			}		
			boolean result = new NotificationDao().updateNotifications(notifications);		
			return SUCCESS;
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return SUCCESS;
		}
		
	}

	public String updateNotificationsTemp()
	{
		
		try {
			ObjectMapper mappercom = new ObjectMapper();
			JSONObject notificationJsonObj =  mappercom.readValue(notificationJson, JSONObject.class);
			Integer companyId = Integer.valueOf((String) notificationJsonObj.get("companyId"));
			Integer userId = Integer.valueOf((String) notificationJsonObj.get("userId"));
			JSONArray notificationsArray = (JSONArray) notificationJsonObj.get("notifications");
			List<Notification> notifications = new ArrayList<Notification>();
			ObjectMapper mapper = new ObjectMapper();			
			if (notificationsArray != null) { 
				int len = ((CharSequence) notificationsArray).length();
				for (int i=0;i<len;i++){ 					   
					Notification obj = mapper.readValue(notificationsArray.get(i).toString(), Notification.class);				   
					notifications.add(obj);
				} 
				new NotificationDao().updateNotifications(notifications);
				return SUCCESS;
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return SUCCESS;
		}
		return SUCCESS;
	}

	public String showReleatedNotificationPage(){

		logger.info("notificationJson" +notificationJson);
		try {
			ObjectMapper mappercom = new ObjectMapper();
			JSONObject notificationJsonObj =  mappercom.readValue(notificationJson, JSONObject.class);
			JSONArray notificationsArray = (JSONArray) notificationJsonObj.get("notifications");
			List<Notification> notifications = new ArrayList<Notification>();
			ObjectMapper mapper = new ObjectMapper();			
			if (notificationsArray != null) { 
				int len = ((CharSequence) notificationsArray).length();
				for (int i=0;i<len;i++){ 					   
					Notification obj = mapper.readValue(notificationsArray.get(i).toString(), Notification.class);				   
					notifications.add(obj);					
				} 
			}

			if(notifications.size() > 0){
				for (int i = 0; i < notifications.size(); i++) {
					Notification notification = notifications.get(i);
					for (int j = 0; j < notification.getDetails().size(); j++) {
						NotificationDetail notificationDetail = notification.getDetails().get(j);
						if(notificationDetail.getType() == NotificationInventoryTypeEnum.FLIGHT_ORDER.getId()){

						}
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return SUCCESS;
		}
		return SUCCESS;
	}

	public void insertNotificationOneandAll(User sessionUser,String invertory_id,String description,String comments,Integer type,boolean hastoupdateparent,boolean notifyview,boolean flag,boolean isadmin,boolean isemail,boolean isexpired){
         
		try{
			
			NotificationDao notificationDao =  new NotificationDao();
			List<User> userlist  = new ArrayList<User>();
			if(hastoupdateparent){
				List<Integer> useridlist = notificationDao.getParentUserIdLevel2(sessionUser.getId());
				userlist = notificationDao.getParentCompanyList(useridlist);		
			}else{				
				userlist.add(sessionUser);
			}					
			if(userlist != null && userlist.size() > 0){
				for (int i = 0; i < userlist.size(); i++) {
					User user = userlist.get(i);
					String timeString = "00:00";
					List<NotificationDetail> details = new ArrayList<NotificationDetail>();									
					NotificationDetail notificationDetail = new NotificationDetail();
					notificationDetail.setType(type);
					notificationDetail.setDescription(description);
					notificationDetail.setInventoryId(invertory_id);
					notificationDetail.setComments(comments);
					details.add(notificationDetail);

					Notification notification = new Notification();
					notification.setType(type);
					notification.setCompanyId(user.getCompanyid());
					notification.setDescription(description);
					notification.setUserId(user.getId());
					notification.setDetails(details);
					notification.setCreatedby(sessionUser.getId());
					notification.setCurrentNotificationView(notifyview);
					notification.setCustomFlag(flag);
					notification.setIs_admin(isadmin);
					notification.setIs_email(isemail);
					notification.setIsExpired(isexpired);
					notification.setTimeInterval(DateConversion.StringTimeToDateTime(timeString));
					notification.setFromDate(DateConversion.StringToDateDBFormat(DateConversion.convertDateToStringDateDefault(new Date())));
					notification.setToDate(DateConversion.StringToDateDBFormat(DateConversion.convertDateToStringDateDefault(new Date())));
					notificationDao.insert(notification);
				}
			}
		}catch(Exception e){
            logger.info("Exception " +e);
		}

	}

	//added by basha for custom notifications
	
	
	public String editNotificationPage() throws Exception{
		if(notificationId>0){
			Notification notificationNew=notificationDao.getNotifications(notificationId);
			if(notificationNew!=null){
				notificationNew.setTransFromDate(DateConversion.convertTimestampToString(notificationNew.getFromDate()));
				notificationNew.setTransToDate(DateConversion.convertTimestampToString(notificationNew.getToDate()));
				setNotification(notificationNew);
			}
			 
		}
		 
		return SUCCESS;
	}
	
	public String viewNotificationPage() throws Exception{
		if(notificationId>0){
			Notification notificationNew=notificationDao.getNotifications(notificationId);
			if(notificationNew!=null){
				notificationNew.setTransFromDate(DateConversion.convertTimestampToString(notificationNew.getFromDate()));
				notificationNew.setTransToDate(DateConversion.convertTimestampToString(notificationNew.getToDate()));
				setNotification(notificationNew);
			}
			 
		}
		 
		return SUCCESS;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	public String getNotificationJson() {
		return notificationJson;
	}
	public void setNotificationJson(String notificationJson) {
		this.notificationJson = notificationJson;
	}
	public HashMap<String, Object> getNotificationMap() {
		return notificationInfo;
	}
	public void setNotificationMap(HashMap<String, Object> notificationMap) {
		this.notificationInfo = notificationMap;
	}
	public long getNotificationid() {
		return notificationid;
	}
	public void setNotificationid(long notificationid) {
		this.notificationid = notificationid;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		
		sessionMap = (SessionMap<String, Object>) arg0;
	}
	public long getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
	}

	public Notification getNotification() {
		return notification;
	}
	public void setNotification(Notification notification) {
		this.notification = notification;
	}
	public NotificationDetail getNotificationDetail() {
		return notificationDetail;
	}
	public void setNotificationDetail(NotificationDetail notificationDetail) {
		this.notificationDetail = notificationDetail;
	}
	private void nullifyDefaults()
	{
		sessionMap = null;
		notifications = null;
		notificationJson = null;
	}
}
