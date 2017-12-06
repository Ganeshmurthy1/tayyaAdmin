package com.lintas.admin.flight.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "flight_temp_book_transaction_keys")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FlightBookingKeysTemp implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name = "id")
	@GeneratedValue
	private int id;
	@Column(name="transaction_key")
	private String transaction_key;
	@Column(name="search_key")
	private String search_key ;

	@Column(name="price_key")
	private String price_key ;

	@Column(name="active")
	private boolean active ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "FlightBookingKeys [id=" + id + ", transaction_key="
				+ transaction_key + ", search_key=" + search_key
				+ ", price_key=" + price_key + ", active=" + active + "]";
	}
	public String getTransaction_key() {
		return transaction_key;
	}
	public void setTransaction_key(String transaction_key) {
		this.transaction_key = transaction_key;
	}
	public String getSearch_key() {
		return search_key;
	}
	public void setSearch_key(String search_key) {
		this.search_key = search_key;
	}
	public String getPrice_key() {
		return price_key;
	}
	public void setPrice_key(String price_key) {
		this.price_key = price_key;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}


}
