/**
 * 
 */
package com.admin.api.analytics.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.analytics.dao.TrainAnalysisDao;
import com.admin.dashboard.Vo.DashBoardJsonVo;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author MANISH
 *
 */
public class TrainAnalysisAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;

	public static final Logger logger = Logger.getLogger(TrainAnalysisDao.class);
	Map<String, Long> jsonobj = new HashMap<String, Long>();
	CompanyDAO companyDAO = new CompanyDAO();
	SessionMap<String, Object> sessionMap;
	public String type; // D,W,M
	public String bstatus; // HK,HC,HF
	public String pstatus; // PF,PS
	TrainAnalysisDao analysisDao=new TrainAnalysisDao();

	public String getOnlyTrainOrderJson() {
		User userSessionObj = (User) sessionMap.get("User");
		Company companySessionObj = (Company) sessionMap.get("Company");
		try {

			if (userSessionObj != null && companySessionObj != null) {
				List<User> userList = getUserIdListForAllCompanyList(userSessionObj, companySessionObj);
				List<String> userIdList= new ArrayList<>(); 
				if(userList!=null && userList.size()>0)
				{
					for(User user : userList)
					{
						userIdList.add(String.valueOf(user.getId()));
					}
				}

				
				DashBoardJsonVo countD=new DashBoardJsonVo();
				DashBoardJsonVo countW=new DashBoardJsonVo();
				DashBoardJsonVo countM=new DashBoardJsonVo();
				
				try {
					if(type!=null && !type.equalsIgnoreCase(""))
					{
						if(bstatus!=null && bstatus.equalsIgnoreCase("ALL")){
							DashBoardJsonVo countHK = analysisDao.getTrainOrdersCount(userSessionObj, companySessionObj, userIdList, type, pstatus, "HK");
							DashBoardJsonVo countHC = analysisDao.getTrainOrdersCount(userSessionObj, companySessionObj, userIdList, type, pstatus, "HC");
							DashBoardJsonVo countHF = analysisDao.getTrainOrdersCount(userSessionObj, companySessionObj, userIdList, type, pstatus, "HF");
							jsonobj.put("countHK", countHK.getCount());
							jsonobj.put("countHC", countHC.getCount());
							jsonobj.put("countHF", countHF.getCount());
							return SUCCESS;
						}
						if(type.equalsIgnoreCase("D"))
						{
							countD = analysisDao.getTrainOrdersCount(userSessionObj, companySessionObj, userIdList, type,  pstatus,bstatus);		
						}
						else if(type.equalsIgnoreCase("W"))
						{
							countW = analysisDao.getTrainOrdersCount(userSessionObj, companySessionObj, userIdList, type,  pstatus,bstatus);
						}
						else if(type.equalsIgnoreCase("M"))
						{
							countM = analysisDao.getTrainOrdersCount(userSessionObj, companySessionObj, userIdList, type,  pstatus,bstatus);	
						}

					}
					else{
						countD = analysisDao.getTrainOrdersCount(userSessionObj, companySessionObj, userIdList, "D",  pstatus,bstatus);	
						countM = analysisDao.getTrainOrdersCount(userSessionObj, companySessionObj, userIdList, "M",  pstatus,bstatus);	
						countW = analysisDao.getTrainOrdersCount(userSessionObj, companySessionObj, userIdList, "W",  pstatus,bstatus);	
					}
					jsonobj.put("countD", countD.getCount());
					jsonobj.put("bookingAmountD", countD.getTotalBookingAmount().longValue());//added by basha
					jsonobj.put("countM", countM.getCount());
					jsonobj.put("bookingAmountM", countM.getTotalBookingAmount().longValue());//added by basha
					jsonobj.put("countW", countW.getCount());
					jsonobj.put("bookingAmountW", countW.getTotalBookingAmount().longValue());//added by basha
				} catch (Exception e) {
					logger.error(e);
					jsonobj.put("error-exception", new Long(888888888));
				}

			} else {
				jsonobj.put("error-not-login", new Long(0));
			}
		} catch (Exception e) {
			logger.error(e);
			jsonobj.put("error-exception", new Long(0));
		}
		return SUCCESS;

	}


	private List<User> getUserIdListForAllCompanyList(User userSessionObj, Company companySessionObj) {
		List<User> userIdBuffer = new ArrayList<>();
		if (userSessionObj != null && companySessionObj != null) {
			if (userSessionObj.getUserrole_id().isSuperuser()) {
				userIdBuffer = companyDAO.GetAllUserListUnderCompanyAsList(userSessionObj, companySessionObj);
			} else if (userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()) {
				if (!companySessionObj.getCompanyRole().isCorporate()) {
					userIdBuffer = companyDAO.GetAllUserListUnderCompanyAsList(userSessionObj, companySessionObj);
				}
			}
		}
		return userIdBuffer;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;

	}

	public Map<String, Long> getJsonobj() {
		return jsonobj;
	}

	public void setJsonobj(Map<String, Long> jsonobj) {
		this.jsonobj = jsonobj;
	}
}
