package com.tayyarah.notification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.model.User;
import com.tayyarah.notification.dao.NotificationDao;


public class NotificationFactory {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(NotificationFactory.class);	
	
	public static HashMap<String, Object> getPendingNotifications(Integer companyId, Integer userId)
	{
		ObjectMapper mapper = new ObjectMapper();	
		HashMap<String, Object> notificationMap = new HashMap<String, Object>();
		notificationMap.put("companyId", companyId);
		notificationMap.put("userId", userId);		
		try{
			List<Notification> notifications = new NotificationDao().getNotifications(NotificationStatusEnum.STATUS_PENDING, companyId, userId);

			if(notifications != null)
			{
				//logger.info("###### user notifications count----"+notifications.size());	
				List<HashMap<String, Object>> notificationFeeds = new ArrayList<HashMap<String, Object>>();			
				for (Notification notification : notifications) {
					//HashMap<String, Object> notificationFeed = new HashMap<String, Object>(); 
					//notificationFeed.set
					//logger.info("###### before transform-----"+notification.toString());				
					mapper.getSerializationConfig().withSerializationInclusion(JsonInclude.Include.NON_NULL);					
					ObjectWriter viewWriter = mapper.writerWithView(Views.Public.class);
					String notificationInString = viewWriter.writeValueAsString(notification);				
					//String notificationInString = mapper.writeValueAsString(notification);
					//notification = mapper.readValue(notificationInString, Notification.class);				
					JSONObject notificationJsonObj =  mapper.readValue(notificationInString, JSONObject.class);				
					//logger.info("###### after transform-----"+notificationJsonObj.toString());			
					notificationFeeds.add(notificationJsonObj);
				}
				notificationMap.put("notifications", notificationFeeds);
				notificationMap.put("count", notificationFeeds.size());	
			}
			else
			{
				notificationMap.put("notifications", new ArrayList<Notification>());
				notificationMap.put("count", 0);	
			}
		}
		catch(IOException ex)
		{
			notificationMap.put("notifications", new ArrayList<Notification>());
			notificationMap.put("count", 0);	
			logger.info("###### Exception,,"+ex.toString());
			ex.printStackTrace();
		}		
		/*try {
			notificationMap.put("notifications", mapper.writeValueAsString(notifications));
			//notificationJson = mapper.writeValueAsString(notificationMap);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return notificationMap;
	}

	public static Notification createNotification(String notificationJson)
	{
		ObjectMapper mapper = new ObjectMapper();	
		Notification notification = null;
		try{			
			notification = mapper.readValue(notificationJson, Notification.class);			
		}
		catch(IOException ex)
		{			
			logger.info("###### Exception,,"+ex.toString());
			ex.printStackTrace();
		}	
		return notification;
	}
	public static List<Notification> createNotificationList(String notificationJson)
	{
		logger.info("###### notification---"+notificationJson.toString());
		List<Notification> notifications = new ArrayList<Notification>();
		try{	
			ObjectMapper mapper = new ObjectMapper();				
			HashMap<String, String> notificationInfoObj =  mapper.readValue(notificationJson, new TypeReference<Map<String, String>>(){});
			if(notificationInfoObj != null)
			{
				logger.info("###### notification---"+notificationInfoObj.toString());
				//	List<java.util.LinkedHashMap> notificationHashMapList =  (List<java.util.LinkedHashMap>) notificationInfoObj.get("notifications");	
				/*	for (java.util.LinkedHashMap linkedHashMap : notificationHashMapList) {
					Notification notification = new Notification();
					//Integer userId = Integer.valueOf();					
					notification.setId(new Long((Integer)linkedHashMap.get("id")));
					logger.info("###### notification---"+notification.toString());
					notifications.add(notification);
				}*/
				//notifications = mapper.convertValue(listOfObjects, new TypeReference<List<POJO>>() { });[conversion of Linkedhashmap][1]				
				/*Iterator itr= notificationsJsonArray.iterator();
				while(itr.hasNext()){
					JSONObject notificationJsonObj = (JSONObject)itr.next();				
					String notificationInString = mapper.writeValueAsString(notificationJsonObj);
					Notification notification = mapper.readValue(notificationInString, Notification.class);	
					if(notification != null && notification.getId() != null)
						notifications.add(notification);			
				}*/
			}
		}
		catch(Exception ex)
		{			
			logger.info("###### Exception,,"+ex.toString());
			ex.printStackTrace();
		}	
		return notifications;
	}

	@SuppressWarnings("unchecked")
	public static HashMap<String, Object> createPendingNotificationList(Integer companyId, Integer userId)
	{
		ObjectMapper mapper = new ObjectMapper();	
		HashMap<String, Object> notificationMap = new HashMap<String, Object>();
		notificationMap.put("companyId", companyId);
		notificationMap.put("userId", userId);		
		try{
			List<Notification> notifications = new NotificationDao().getNotifications(NotificationStatusEnum.STATUS_PENDING, companyId, userId);

			if(notifications != null && notifications.size()>0)
			{
				//logger.info("###### user notifications count----"+notifications.size());	
				List<HashMap<String, Object>> notificationFeeds = new ArrayList<HashMap<String, Object>>();			
				for (Notification notification : notifications) {
					JSONObject notificationJsonObj = new JSONObject();
					notificationJsonObj.put("notificationId", notification.getId());
					notificationJsonObj.put("statusId", notification.getStatusId());
					notificationJsonObj.put("createdAt", notification.getCreatedAt().getTime());
					notificationJsonObj.put("updatedAt", notification.getUpdatedAt().getTime());	
					notificationJsonObj.put("userId", notification.getUserId());
					notificationJsonObj.put("userName", getUserName(notification.getUserId()));
					if(notification.getCreatedby()!=null && notification.getCreatedby()>0)
						notificationJsonObj.put("createdby", getUserName(notification.getCreatedby()));
					notificationJsonObj.put("companyId", notification.getCompanyId());
					/*notificationJsonObj.put("imageid", getImageid(notification.getDetails().get(notification.getDetails().size() - 1).getType()));
					notificationJsonObj.put("actionlink", getActionLink(notification.getDetails().get(notification.getDetails().size() - 1).getType(),notification.getDetails().get(notification.getDetails().size() - 1).getInventoryId()));
					*/
					notificationJsonObj.put("imageid", getImageid(notification.getType()));
					notificationJsonObj.put("actionlink", getActionLink(notification.getType(),notification.getDetails().get(0).getInventoryId()));
				
					
					JSONArray notificationDetailJsonArr = new JSONArray();
					for (int i = 0; i < notification.getDetails().size(); i++) {
						NotificationDetail notificationDetail = notification.getDetails().get(i);
						JSONObject notificationDetailJsonObj = new JSONObject();
						notificationDetailJsonObj.put("createdAt", notificationDetail.getCreatedAt().getTime());
						notificationDetailJsonObj.put("updatedAt", notificationDetail.getUpdatedAt().getTime());
						notificationDetailJsonObj.put("inventoryId", notificationDetail.getInventoryId());
						notificationDetailJsonObj.put("type", notificationDetail.getType());
						notificationDetailJsonObj.put("description", notificationDetail.getDescription());
						notificationDetailJsonArr.add(notificationDetailJsonObj);					
					}				
					notificationJsonObj.put("details", notificationDetailJsonArr);
					notificationFeeds.add(notificationJsonObj);
				}
				notificationMap.put("notifications", notificationFeeds);
				notificationMap.put("count", notificationFeeds.size());	
			}
			else
			{
				notificationMap.put("notifications", new ArrayList<Notification>());
				notificationMap.put("count", 0);	
			}
		}
		catch(Exception ex)
		{
			notificationMap.put("notifications", new ArrayList<Notification>());
			notificationMap.put("count", 0);	
			logger.info("###### Exception,,"+ex.toString());
			ex.printStackTrace();
		}		
		return notificationMap;
	}

	private static String getImageid(int notifytype){		 
		return NotificationInventoryTypeEnum.getBrowsingHistoryDetailTypeEnum(notifytype).getImagename();		
	}
	private static String getActionLink(int notifytype,String inventoryId){	

		String actionlink = "";
		if(notifytype == NotificationInventoryTypeEnum.FLIGHT_ORDER.getId()){		
			FlightOrderRow flightOrderRow =  new FlightOrderDao().getFlightOrderRowDetail(inventoryId);
			if(flightOrderRow!=null)
			actionlink = NotificationInventoryTypeEnum.getBrowsingHistoryDetailTypeEnum(notifytype).getActionname()+"?id="+flightOrderRow.getId()+"&orderId="+inventoryId;
		}
		else if(notifytype == NotificationInventoryTypeEnum.HOTEL_ORDER.getId()){		
			HotelOrderRow hotelOrderRow =  new HotelOrderDao().getHotelOrderRowById(inventoryId);
			if(hotelOrderRow!=null)
			actionlink = NotificationInventoryTypeEnum.getBrowsingHistoryDetailTypeEnum(notifytype).getActionname()+"?selectedRowIndex="+hotelOrderRow.getId();
		}
		else if(notifytype == NotificationInventoryTypeEnum.USER_WALLET.getId()){			
			actionlink = NotificationInventoryTypeEnum.getBrowsingHistoryDetailTypeEnum(notifytype).getActionname()+"?transactionid="+inventoryId;
		}
		else if(notifytype == NotificationInventoryTypeEnum.COMPANY.getId()){			
			actionlink = NotificationInventoryTypeEnum.getBrowsingHistoryDetailTypeEnum(notifytype).getActionname()+"?companyid="+inventoryId;
		}
		else if(notifytype == NotificationInventoryTypeEnum.COMPANY_CONFIG.getId()){			
			actionlink = NotificationInventoryTypeEnum.getBrowsingHistoryDetailTypeEnum(notifytype).getActionname()+"?config_id="+inventoryId;
		}
		else if(notifytype == NotificationInventoryTypeEnum.B2CUSER.getId()){			
			actionlink = NotificationInventoryTypeEnum.getBrowsingHistoryDetailTypeEnum(notifytype).getActionname()+"?id="+inventoryId;
		}
		else if(notifytype == NotificationInventoryTypeEnum.USER.getId()){			
			actionlink = NotificationInventoryTypeEnum.getBrowsingHistoryDetailTypeEnum(notifytype).getActionname()+"?id="+inventoryId;
		}
		else if(notifytype == NotificationInventoryTypeEnum.CMS.getId()){			
			actionlink = NotificationInventoryTypeEnum.getBrowsingHistoryDetailTypeEnum(notifytype).getActionname()+"?id="+inventoryId;
		}
		else if(notifytype == NotificationInventoryTypeEnum.COMPANY_APPROVAL.getId()){			
			actionlink = NotificationInventoryTypeEnum.getBrowsingHistoryDetailTypeEnum(notifytype).getActionname()+"?companyid="+inventoryId;
		}
		else if(notifytype == NotificationInventoryTypeEnum.FLIGHT_MARKUP.getId()){			
			actionlink = NotificationInventoryTypeEnum.getBrowsingHistoryDetailTypeEnum(notifytype).getActionname()+"?markupId="+inventoryId;
		}
		else if(notifytype == NotificationInventoryTypeEnum.HOTEL_MARKUP.getId()){			
			actionlink = NotificationInventoryTypeEnum.getBrowsingHistoryDetailTypeEnum(notifytype).getActionname()+"?id="+inventoryId;
		}
		else if(notifytype == NotificationInventoryTypeEnum.HOTEL_QUOATATION.getId()){			
			actionlink = NotificationInventoryTypeEnum.getBrowsingHistoryDetailTypeEnum(notifytype).getActionname()+"?hotelQuotationRequestId="+inventoryId;
		}
		else if(notifytype == NotificationInventoryTypeEnum.HOTEL_BOOKREQUEST.getId()){			
			actionlink = NotificationInventoryTypeEnum.getBrowsingHistoryDetailTypeEnum(notifytype).getActionname()+"?id="+inventoryId;
		}
		else{
			actionlink = "";
		}

		return actionlink;
	}
	private static String getUserName(int userId){	
		String username = "";	
		if(userId!=0){
			List<User> user = new UserDAO().getUserDetailsByUserId(userId);
			username = user.get(user.size() - 1).getUsername();	
		}
		return username;
	}
}
