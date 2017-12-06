package com.isl.admin.page;

import java.util.List;

import com.admin.payment.card.details.PaymentCardDetailsConfig;
import com.isl.admin.filter.CommonConfigFilter;
import com.lintas.admin.model.CommonConfig;
public class CommonConfigPage extends Page{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CommonConfig> commonConfigList;
	private CommonConfigFilter  commonConfigFilter;
	private List<PaymentCardDetailsConfig>  paymentCardDetailsConfigList;
	private int selectedRowIndex;
	public CommonConfigPage() {
		super();
		 this.setCommonConfigFilter(new CommonConfigFilter()); 
		// TODO Auto-generated constructor stub
	}

	public CommonConfigPage(boolean isPagination, boolean isFilterEnabled, int maxItems, int currentPageIndex,
			int availablePages, int availableItems, CommonConfigFilter companyFilter) {
		super(isPagination, isFilterEnabled, maxItems, currentPageIndex, availablePages, availableItems);
		this.setCommonConfigFilter(new CommonConfigFilter()); 
		// TODO Auto-generated constructor stub
	}
	public CommonConfigPage(boolean isPagination, boolean isFilterEnabled, int maxItems,  CommonConfigFilter companyFilter) {
		super(isPagination, isFilterEnabled, maxItems);
		 this.setCommonConfigFilter(new CommonConfigFilter());
		// TODO Auto-generated constructor stub
	}

	public int getSelectedRowIndex() {
		return selectedRowIndex;
	}
	public void setSelectedRowIndex(int selectedRowIndex) {
		this.selectedRowIndex = selectedRowIndex;
	}
  
	public CommonConfigFilter getCommonConfigFilter() {
		return commonConfigFilter;
	}

	public void setCommonConfigFilter(CommonConfigFilter commonConfigFilter) {
		this.commonConfigFilter = commonConfigFilter;
	}

	public List<CommonConfig> getCommonConfigList() {
		return commonConfigList;
	}

	public void setCommonConfigList(List<CommonConfig> commonConfigList) {
		this.commonConfigList = commonConfigList;
	}

	public List<PaymentCardDetailsConfig> getPaymentCardDetailsConfigList() {
		return paymentCardDetailsConfigList;
	}

	public void setPaymentCardDetailsConfigList(List<PaymentCardDetailsConfig> paymentCardDetailsConfigList) {
		this.paymentCardDetailsConfigList = paymentCardDetailsConfigList;
	} 
	
}
