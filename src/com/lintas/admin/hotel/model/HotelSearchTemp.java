package com.lintas.admin.hotel.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
@Entity
@Table(name="hotel_temp_searches")
public class HotelSearchTemp implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "HotelSearch [search_key=" + search_key + ", hotelsearch_cmd=" + Arrays.toString(hotelsearch_cmd)
				+ ", hotelres_map=" + Arrays.toString(hotelres_map) + "]";
	}
	/*`search_key` bigint(20) NOT NULL AUTO_INCREMENT,
	  `hotelsearch_cmd` mediumblob,
	  `hotelres_map` mediumblob,  
	  PRIMARY KEY (`search_key`)*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long search_key;
	@Lob
	private byte[] hotelsearch_cmd;
	@Lob
	private byte[] hotelres_map;
	
	public byte[] getHotelsearch_cmd() {
		return hotelsearch_cmd;
	}
	public void setHotelsearch_cmd(byte[] hotelsearch_cmd) {
		this.hotelsearch_cmd = hotelsearch_cmd;
	}
	public byte[] getHotelres_map() {
		return hotelres_map;
	}
	public void setHotelres_map(byte[] hotelres_map) {
		this.hotelres_map = hotelres_map;
	}
	public HotelSearchTemp(Long search_key, byte[] hotelsearch_cmd, byte[] hotelres_map) {
		super();
		this.search_key = search_key;
		this.hotelsearch_cmd = hotelsearch_cmd;
		this.hotelres_map = hotelres_map;
	}
	public HotelSearchTemp() {
		super();		
		this.hotelsearch_cmd = null;
		this.hotelres_map = null;
	}
	public Long getSearch_key() {
		return search_key;
	}
	public void setSearch_key(Long search_key) {
		this.search_key = search_key;
	}
	@Column(name = "created_at")
	 Timestamp createdAt;
	 
	 @Column(name = "updated_at",
	   insertable = false,
	   columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL")
	 Timestamp updatedAt;

	 public Timestamp getCreatedAt() {
	  return createdAt;
	 }
	 public void setCreatedAt(Timestamp createdAt) {
	  this.createdAt = createdAt;
	 }
	 public Timestamp getUpdatedAt() {
	  return updatedAt;
	 }
	 public void setUpdatedAt(Timestamp updatedAt) {
	  this.updatedAt = updatedAt;
	 }
	
}
