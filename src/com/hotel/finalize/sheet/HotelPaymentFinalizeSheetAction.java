package com.hotel.finalize.sheet;

import java.util.List;

import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.model.Country;
import com.opensymphony.xwork2.ActionSupport;

public class HotelPaymentFinalizeSheetAction extends ActionSupport{

	/**
	 * 
	 */
	 private static final long serialVersionUID = 1L;
	 private List<Country> countryList=null;
	
	public String goHotelPaymentFinalizeSheet(){
		List<Country> list=new CountryDao().getCountryList();
		if(list!=null && list.size()>0){
			countryList=new CountryDao().getCountryList();
		}
		
	 return SUCCESS;
	}
	public List<Country> getCountryList() {
		return countryList;
	}
	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}
	
	
	
	
	
	
	
	
	
	

}
