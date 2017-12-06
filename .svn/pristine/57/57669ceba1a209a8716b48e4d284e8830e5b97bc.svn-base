package com.tayyarah.admin.orderrow.rm.structure;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hotel_order_row_rm_config_struct")
public class HotelOrderRowRmConfigStruct implements Serializable{

	/**
	 * @author raham
	 * 13-Oct-2017
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name="rm_dynamic_data", columnDefinition = "TEXT")
	private String rmDynamicData;
	public Integer getId() {
		return id;
	}
	public String getRmDynamicData() {
		return rmDynamicData;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setRmDynamicData(String rmDynamicData) {
		this.rmDynamicData = rmDynamicData;
	}
 
}
