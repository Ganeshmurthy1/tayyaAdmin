package com.tayyarah.bus.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bus_seatavailable_temp")
public class BusSeatAvailableTemp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "created_at")
	Timestamp createdAt;	
	@Column(name = "search_key", unique = true, nullable = false)
	private String searchKey;	
	@Column(name = "bus_seatdata" , columnDefinition = "LONGBLOB")
	private byte[] busSeatData;
	
	public Long getId() {
		return id;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public String getSearchKey() {
		return searchKey;
	}	
	public byte[] getBusSeatData() {
		return busSeatData;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}	
	public void setBusSeatData(byte[] busSeatData) {
		this.busSeatData = busSeatData;
	}

}
