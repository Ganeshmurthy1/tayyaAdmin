package com.isl.admin.page;

import java.io.Serializable;
import java.util.List;

import com.admin.api.provider.model.ApiProvider;
import com.admin.hotel.fin.sheet.model.TripRequest;
import com.isl.admin.filter.ApiProviderFilter;

public class ApiProviderPage extends Page implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ApiProvider> apiProviderList;
	private List<TripRequest> tripRequestList;
	private ApiProviderFilter   apiProviderFilter;
	
	private int selectedRowIndex;

	public ApiProviderPage() {
		super();
		 this.setApiProviderFilter(new ApiProviderFilter());
		// TODO Auto-generated constructor stub
	}

	public ApiProviderPage(boolean isPagination, boolean isFilterEnabled, int maxItems, int currentPageIndex,
			int availablePages, int availableItems, ApiProviderFilter companyFilter) {
		super(isPagination, isFilterEnabled, maxItems, currentPageIndex, availablePages, availableItems);
		 this.setApiProviderFilter(new ApiProviderFilter());;
		// TODO Auto-generated constructor stub
	}
	public ApiProviderPage(boolean isPagination, boolean isFilterEnabled, int maxItems,  ApiProviderFilter companyFilter) {
		super(isPagination, isFilterEnabled, maxItems);
		 this.setApiProviderFilter(new ApiProviderFilter());
		// TODO Auto-generated constructor stub
	}

	public int getSelectedRowIndex() {
		return selectedRowIndex;
	}
	public void setSelectedRowIndex(int selectedRowIndex) {
		this.selectedRowIndex = selectedRowIndex;
	}
	public ApiProviderFilter getApiProviderFilter() {
		return apiProviderFilter;
	}
	public void setApiProviderFilter(ApiProviderFilter apiProviderFilter) {
		this.apiProviderFilter = apiProviderFilter;
	}
	
	
 
	public List<ApiProvider> getApiProviderList() {
		return apiProviderList;
	}
	public void setApiProviderList(List<ApiProvider> apiProviderList) {
		this.apiProviderList = apiProviderList;
	}

	public List<TripRequest> getTripRequestList() {
		return tripRequestList;
	}

	public void setTripRequestList(List<TripRequest> tripRequestList) {
		this.tripRequestList = tripRequestList;
	} 
	
	 
}
