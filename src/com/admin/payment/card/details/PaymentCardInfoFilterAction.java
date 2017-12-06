package com.admin.payment.card.details;

import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.isl.admin.filter.CommonConfigFilter;
import com.isl.admin.page.CommonConfigPage;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PaymentCardInfoFilterAction extends ActionSupport implements ModelDriven<CommonConfigPage>,SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PaymentCardInfoFilterAction.class);
	CommonConfigPage commonConfigPage=new CommonConfigPage();
	PaymentCardDetailsDao paymentCardDetailsDao=new PaymentCardDetailsDao();
	private List<PaymentCardDetailsConfig> paymentCardDetailsList=null;
	SessionMap<String, Object> sessionMap=null;
	public  String  fetchPaymentCardInfoList(){
		//User user=(User)sessionMap.get("User");
		List<PaymentCardDetailsConfig> paymentCardDetailsConfigs=paymentCardDetailsDao.getAllPaymentCardDetailsList();
		if(paymentCardDetailsConfigs!=null && paymentCardDetailsConfigs.size()>0)
			setPaymentCardDetailsList(paymentCardDetailsConfigs);

		CommonConfigFilter commonConfigFilter = commonConfigPage.getCommonConfigFilter();
		commonConfigPage.setCommonConfigFilter(commonConfigFilter);
		CommonConfigPage newApiProviderPage=paymentCardDetailsDao.getAllPaymentCardDetailsList(commonConfigPage);
		if(newApiProviderPage!=null && newApiProviderPage.getCommonConfigList()!=null){
			commonConfigPage=newApiProviderPage;

		}
		else{
			commonConfigPage=newApiProviderPage;
		}
		//HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.MASTER_CONFIGURATIONS_COMMON_CONFIG_LIST.getId(), BrowsingOptionActionEnum.ACTION_FILTER_SUBMIT.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId(), String.valueOf(user.getCompanyid()),"company config list filter submit  click ");

		/*HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.ALL_COMPANY.getId(), actionId, ActionStatusEnum.SUCCESS.getCode(), detailType, String.valueOf(user.getCompanyid()),actionMessage);*/
		return SUCCESS;
	}

	@Override
	public CommonConfigPage getModel() {
		// TODO Auto-generated method stub
		return commonConfigPage;
	}

	public CommonConfigPage getCommonConfigPage() {
		return commonConfigPage;
	}

	public void setCommonConfigPage(CommonConfigPage commonConfigPage) {
		this.commonConfigPage = commonConfigPage;
	}


	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}
	 
	public List<PaymentCardDetailsConfig> getPaymentCardDetailsList() {
		return paymentCardDetailsList;
	}

	public void setPaymentCardDetailsList(List<PaymentCardDetailsConfig> paymentCardDetailsList) {
		this.paymentCardDetailsList = paymentCardDetailsList;
	}

}
