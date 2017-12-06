package com.admin.common.config.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.lintas.admin.common.model.Timestampable;
import com.lintas.utility.DateConversion;

@Entity
@Table(name = "hotel_booking_cutoff_config")
public class HotelBookingCutoffConfig extends Timestampable implements Serializable {
	
	public String getTranCutoffDate() {
		if(getCutoffDate()!=null){
			 
			tranCutoffDate=DateConversion.convertDateToStringToDate(getCutoffDate());
		}
		 
		return tranCutoffDate;
	}
	public void setTranCutoffDate(String tranCutoffDate) {
		this.tranCutoffDate = tranCutoffDate;
	}
	/**
	 * 
	 */
	@Transient
	private String  tranCutoffDate;
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "cut_off_type")
	private String cutofftype;
	
	@Column(name = "cut_off_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date cutoffDate;
	
	@Column(name = "cut_off_week")
	private String cutoffweek;
	
	public String getCutoffweek() {
		return cutoffweek;
	}
	public void setCutoffweek(String cutoffweek) {
		this.cutoffweek = cutoffweek;
	}
	
	public Date getCutoffDate() {
		return cutoffDate;
	}
	public void setCutoffDate(Date cutoffDate) {
		this.cutoffDate = cutoffDate;
	}
	public String getCutofftype() {
		return cutofftype;
	}
	public void setCutofftype(String cutofftype) {
		this.cutofftype = cutofftype;
	} 

}