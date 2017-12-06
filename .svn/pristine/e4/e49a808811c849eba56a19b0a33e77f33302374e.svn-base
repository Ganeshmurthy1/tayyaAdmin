package com.lintas.admin.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "flight_hotel_book_api_response")
public class FlightAndHotelBookApiResponse extends Timestampable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Column(name="order_row_id")
	private Long orderRowId; 
	
	@Column(name="apiprovider")
	private String apiProvider; 
	
	@Column(name="apistatuscode")
	private String apiStatusCode;
	
	@Column(name="apistatusmessage",columnDefinition = "text")
	private String apiStatusMessage;
	
	@Column(name="api_traceid")
	private String apiTraceId;
	
	@Column(name="product_type")
	private String productType;

	public String getApiProvider() {
		return apiProvider;
	}

	public String getApiStatusCode() {
		return apiStatusCode;
	}

	public String getApiStatusMessage() {
		return apiStatusMessage;
	}

	public String getApiTraceId() {
		return apiTraceId;
	}

	
	public void setApiProvider(String apiProvider) {
		this.apiProvider = apiProvider;
	}

	public void setApiStatusCode(String apiStatusCode) {
		this.apiStatusCode = apiStatusCode;
	}

	public void setApiStatusMessage(String apiStatusMessage) {
		this.apiStatusMessage = apiStatusMessage;
	}

	public void setApiTraceId(String apiTraceId) {
		this.apiTraceId = apiTraceId;
	}

	public Long getOrderRowId() {
		return orderRowId;
	}

	public void setOrderRowId(Long orderRowId) {
		this.orderRowId = orderRowId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

}
