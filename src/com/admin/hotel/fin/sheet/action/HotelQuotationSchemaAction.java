package com.admin.hotel.fin.sheet.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.hotel.fin.sheet.Dao.HotelQuotaionShemaDao;
import com.admin.hotel.fin.sheet.model.HotelQuotationSchema;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class HotelQuotationSchemaAction extends ActionSupport implements ModelDriven<List<HotelQuotationSchema>>,SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<HotelQuotationSchema> hotelQuotationSchemaList=new ArrayList<>();
	SessionMap<String, Object> sessionMap=null;
	HotelQuotaionShemaDao hotelQuotaionShemaDao=null;
	public String createHotelQuotationSchema(){
		StringBuilder appendSchema = new StringBuilder();
		hotelQuotaionShemaDao=new HotelQuotaionShemaDao();
		User user=(User) sessionMap.get("User");
		if(hotelQuotationSchemaList!=null && hotelQuotationSchemaList.size()>0){
			for(HotelQuotationSchema hotelQuotationSchema:hotelQuotationSchemaList){
				appendSchema.append("<"+hotelQuotationSchema.getFieldName()+":"+hotelQuotationSchema.getDataType()+":"
						+hotelQuotationSchema.getPositionNumber()+":"+hotelQuotationSchema.getFixedPosition()+">");
			}
			//logger.info("appendSchema--------"+appendSchema.toString());
		}
		HotelQuotationSchema hotelQuotaionShema=new HotelQuotationSchema();
		hotelQuotaionShema.setCompanyId(user.getCompanyid());
		hotelQuotaionShema.setSchemaData(appendSchema.toString());
		hotelQuotaionShema.setCreatedAt(new Timestamp(new Date().getTime()));
		hotelQuotaionShema=hotelQuotaionShemaDao.saveHotelQuotationSchema(hotelQuotaionShema);
		if(hotelQuotaionShema!=null){
			addActionMessage("Successfully Hotel Schema created.");
			return SUCCESS;
		}
		else{
			addActionMessage("We found some error.Please wait some time,thank you.");
			return ERROR;
		}
	}


	@Override
	public List<HotelQuotationSchema> getModel() {
		// TODO Auto-generated method stub
		return hotelQuotationSchemaList;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}
	public List<HotelQuotationSchema> getHotelQuotationSchemaList() {
		return hotelQuotationSchemaList;
	}
	public void setHotelQuotationSchemaList(List<HotelQuotationSchema> hotelQuotationSchemaList) {
		this.hotelQuotationSchemaList = hotelQuotationSchemaList;
	}
}
