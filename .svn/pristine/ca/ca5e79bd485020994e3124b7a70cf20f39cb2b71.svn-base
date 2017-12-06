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
@Table(name="hotel_temp_searchroomdetail")
public class HotelSearchRoomDetailTemp implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/*	  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	  `search_key` bigint(20) DEFAULT NULL,
	  `api_provider` bigint(20) DEFAULT NULL,
	  `roomstay` mediumblob,  
	  PRIMARY KEY (`id`)*/
	
	
	@Override
	public String toString() {
		return "HotelSearchRoomDetail [id=" + id + ", search_key=" + search_key + ", api_provider=" + api_provider
				+ ", hotelsearch_cmd=" + Arrays.toString(hotelsearch_cmd) + ", roomstay=" + Arrays.toString(roomstay)
				+ "]";
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long search_key;
	private Integer api_provider;
	@Lob
	private byte[] hotelsearch_cmd;
	@Lob
	private byte[] roomstay;
	
	public byte[] getHotelsearch_cmd() {
		return hotelsearch_cmd;
	}
	public void setHotelsearch_cmd(byte[] hotelsearch_cmd) {
		this.hotelsearch_cmd = hotelsearch_cmd;
	}
	
	public Long getSearch_key() {
		return search_key;
	}
	public void setSearch_key(Long search_key) {
		this.search_key = search_key;
	}
	public Integer getApi_provider() {
		return api_provider;
	}
	public void setApi_provider(Integer api_provider) {
		this.api_provider = api_provider;
	}
	public byte[] getRoomstay() {
		return roomstay;
	}
	public void setRoomstay(byte[] roomstay) {
		this.roomstay = roomstay;
	}
	public HotelSearchRoomDetailTemp(Long search_key, Integer api_provider, byte[] roomstay, byte[] hotelsearch_cmd) {
		super();
		this.search_key = search_key;
		this.api_provider = api_provider;
		this.roomstay = roomstay;
		this.hotelsearch_cmd = hotelsearch_cmd;
	}
	public HotelSearchRoomDetailTemp(Long search_key) {
		super();
		this.search_key = search_key;
		this.api_provider = Integer.valueOf(-1);
		this.roomstay = null;
		this.hotelsearch_cmd = null;
	}
	public HotelSearchRoomDetailTemp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
