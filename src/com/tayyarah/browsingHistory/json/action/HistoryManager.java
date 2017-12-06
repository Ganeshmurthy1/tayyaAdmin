package com.tayyarah.browsingHistory.json.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.dispatcher.SessionMap;

import com.tayyarah.browsingHistory.Dao.BrowsingHistoryDao;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistory;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetail;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryDetailInfo;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class HistoryManager {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HistoryManager.class);

	
	public HistoryInfo inSertHistoryAndDetail(SessionMap<String, Object> sessionmap, Integer pageId, Integer actionId, Integer statusCode, Integer detailId, String inventoryId, String description)
	{
		HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());  		
		historyInfo.changeNature(BrowsingOptionPageEnum.getPageEnum(pageId), BrowsingOptionActionEnum.getActionEnum(actionId), ActionStatusEnum.getStatusEnum(statusCode)); 
		historyInfo.setDetails(BrowsingHistoryDetailTypeEnum.getBrowsingHistoryDetailTypeEnum(detailId), inventoryId, description);
//		inSertHistory(historyInfo);
	//	logger.info("historyinfo inserted-------" + historyInfo.toString());
		historyInfo.setDetails(new ArrayList<HistoryDetailInfo>());
		//logger.info("historyinfo inserted-------" + historyInfo.toString());
		return historyInfo;	
	}
	
	public HistoryInfo inSertHistory(SessionMap<String, Object> sessionmap, Integer pageId, Integer actionId, Integer statusCode)
	{
		HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());  		
		historyInfo.changeNature(BrowsingOptionPageEnum.getPageEnum(pageId), BrowsingOptionActionEnum.getActionEnum(actionId), ActionStatusEnum.getStatusEnum(statusCode)); 
//		inSertHistory(historyInfo);
	//	logger.info("historyinfo inserted-------" + historyInfo.toString());
		historyInfo.setDetails(new ArrayList<HistoryDetailInfo>());
	//	logger.info("historyinfo inserted-------" + historyInfo.toString());
		return historyInfo;	
	}
	
	public HistoryInfo inSertHistory(HistoryInfo historyInfo)
	{
		boolean result  = false;
		//logger.info("page navigation historyInfo" + historyInfo.toString());
		try {
			BrowsingHistoryDao browsingHistoryDao = new BrowsingHistoryDao();
			
			BrowsingHistory browsingHistory = new BrowsingHistory();
			browsingHistory.setUserId(historyInfo.getUserId());
			browsingHistory.setPassword(historyInfo.getPassword());
			browsingHistory.setCompanyId(historyInfo.getCompanyId());
			browsingHistory.setCityName(historyInfo.getCityName());
			browsingHistory.setCountry(historyInfo.getCountry());
			browsingHistory.setLatitude(historyInfo.getLatitude());
			browsingHistory.setLongitude(historyInfo.getLongitude());
			browsingHistory.setPostal(historyInfo.getPostal());
			browsingHistory.setIp_address(historyInfo.getIp_address());
			browsingHistory.setCreatedAt(new Timestamp(new Date().getTime()));			
			//logger.info(browsingHistory.toString());			
			browsingHistory.setActionid(historyInfo.getAction().getId());
			browsingHistory.setPageid(historyInfo.getPage().getId());
			browsingHistory.setStatusid(historyInfo.getStatus().getCode());
			
			if(historyInfo.getDetails() != null)
			{
				List<BrowsingHistoryDetail> details = new ArrayList<BrowsingHistoryDetail>();
				for (HistoryDetailInfo historyDetailInfo : historyInfo.getDetails()) {
					BrowsingHistoryDetail browsingHistoryDetail = new BrowsingHistoryDetail();
					browsingHistoryDetail.setCreatedAt(new Timestamp(new Date().getTime()));
					browsingHistoryDetail.setInventoryId(historyDetailInfo.getInventoryId());
					browsingHistoryDetail.setType(historyDetailInfo.getDetailTypeEnum().getId());
					browsingHistoryDetail.setUpdatedAt(new Timestamp(new Date().getTime()));
					browsingHistoryDetail.setDescription(historyDetailInfo.getDescription());
					details.add(browsingHistoryDetail);
				}
				browsingHistory.setDetails(details);
			}
			else
			{
				browsingHistory.setDetails(new ArrayList<BrowsingHistoryDetail>());				
			}		
			
			browsingHistory = browsingHistoryDao.insert(browsingHistory);			
			
		//	logger.info("browsingHistory-------" + browsingHistory.toString());
			result  = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e);
			e.printStackTrace();
			result  = false;
		}
		
		return historyInfo;
	}

}
