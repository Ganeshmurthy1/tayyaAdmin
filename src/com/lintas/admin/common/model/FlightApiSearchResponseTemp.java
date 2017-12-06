package com.lintas.admin.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "api_search_response_temp")
public class FlightApiSearchResponseTemp implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	@Column(name = "producttype")
	private String productType;

	@Column(name = "date_time")
	private Date dateTime;

	@Column(name = "search_key")
	private String searchKey;

	@Column(name = "searchresponse", columnDefinition = "LONGBLOB")
	private byte[] searchResponse;

	@Column(name = "searchrequest", columnDefinition = "LONGBLOB")
	private byte[] searchRequest;

	@Column(name = "api_provider")
	private String apiprovider;

	public byte[] getSearchRequest() {
		return searchRequest;
	}

	public void setSearchRequest(byte[] searchRequest) {
		this.searchRequest = searchRequest;
	}

	public String getApiprovider() {
		return apiprovider;
	}

	public void setApiprovider(String apiprovider) {
		this.apiprovider = apiprovider;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public byte[] getSearchResponse() {
		return searchResponse;
	}

	public void setSearchResponse(byte[] searchResponse) {
		this.searchResponse = searchResponse;
	}

}
