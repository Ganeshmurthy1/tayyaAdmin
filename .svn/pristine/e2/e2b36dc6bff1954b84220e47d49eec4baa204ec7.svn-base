
package com.lintas.admin.flight.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;





@Entity
@Table(name="flight_temp_search_details_keys")
public class FlightSearchDetailsTemp implements Serializable

{
	private static final long serialVersionUID = 1L;
	@Id	
	@Column(name = "id")
	@GeneratedValue
	private int id;


	@Column(name = "uapisearchflightkeymap" , columnDefinition = "LONGBLOB")
	private byte[] uapiSearchFlightKeyMap;

	public byte[] getUapiSearchFlightKeyMap() {
		return uapiSearchFlightKeyMap;
	}
	public void setUapiSearchFlightKeyMap(byte[] uapiSearchFlightKeyMap) {
		this.uapiSearchFlightKeyMap = uapiSearchFlightKeyMap;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSearchkey() {
		return searchkey;
	}
	public void setSearchkey(String searchkey) {
		this.searchkey = searchkey;
	}
	public String getTransactionkey() {
		return transactionkey;
	}
	public void setTransactionkey(String transactionkey) {
		this.transactionkey = transactionkey;
	}
	/*public UAPISearchFlightKeyMap getUapiSearchFlightKeyMap() {
		return uapiSearchFlightKeyMap;
	}
	public void setUapiSearchFlightKeyMap(
			UAPISearchFlightKeyMap uapiSearchFlightKeyMap) {
		this.uapiSearchFlightKeyMap = uapiSearchFlightKeyMap;
	}*/
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	@Column(name = "search_key")
	private String searchkey;
	@Column(name = "transaction_key")
	private String transactionkey;


	/*   @Column(name = "uapisearchflightkeymap")
    private UAPISearchFlightKeyMap uapiSearchFlightKeyMap;*/
	@Column(name = "date_time")
	private Date datetime;

}