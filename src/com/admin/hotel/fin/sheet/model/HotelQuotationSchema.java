package com.admin.hotel.fin.sheet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lintas.admin.common.model.Timestampable;
@Entity
@Table(name="hotel_quotation_schema")
public class HotelQuotationSchema extends Timestampable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="company_id")
	private int companyId;

	@Column(name="schema_data",columnDefinition="text")
	private String schemaData;
	@Transient
	private String fieldName;
	@Transient
	private String dataType;
	@Transient
	private int positionNumber;
	@Transient
	private String fixedPosition;
	@Transient
	private String displayName;
	@Transient
	private String dataValue;

	public String getSchemaData() {
		return schemaData;
	}
	public void setSchemaData(String schemaData) {
		this.schemaData = schemaData;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public int getPositionNumber() {
		return positionNumber;
	}
	public void setPositionNumber(int positionNumber) {
		this.positionNumber = positionNumber;
	}

	public String getFixedPosition() {
		return fixedPosition;
	}
	public void setFixedPosition(String fixedPosition) {
		this.fixedPosition = fixedPosition;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public HotelQuotationSchema(){
		super();

	}
	public HotelQuotationSchema(String fieldName, String dataType, int positionNumber, String fixedPosition,String displayName,String dataValue) {
		super();
		this.fieldName = fieldName;
		this.dataType = dataType;
		this.positionNumber = positionNumber;
		this.fixedPosition = fixedPosition;
		this.dataValue = dataValue;
		this.displayName = displayName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getDataValue() {
		return dataValue;
	}
	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

}
