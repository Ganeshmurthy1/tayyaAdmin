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
@Table(name="hotel_temp_bookings")
public class HotelBookingTemp implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public HotelBookingTemp() {
		super();
		// TODO Auto-generated constructor stub
	}
public HotelBookingTemp(String orderId, byte[] hotelbook_cmd, byte[] roomstay, byte[] prebook_res, byte[] book_res) {
		super();
		this.orderId = orderId;
		this.hotelbook_cmd = hotelbook_cmd;
		this.roomstay = roomstay;
		this.prebook_res = prebook_res;
		this.book_res = book_res;
	}
@Override
	public String toString() {
		return "HotelBooking [id=" + id + ", orderId=" + orderId + ", hotelbook_cmd=" + Arrays.toString(hotelbook_cmd)
				+ ", roomstay=" + Arrays.toString(roomstay) + ", prebook_res=" + Arrays.toString(prebook_res)
				+ ", book_res=" + Arrays.toString(book_res) + "]";
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public byte[] getHotelbook_cmd() {
		return hotelbook_cmd;
	}
	public void setHotelbook_cmd(byte[] hotelbook_cmd) {
		this.hotelbook_cmd = hotelbook_cmd;
	}
	public byte[] getRoomstay() {
		return roomstay;
	}
	public void setRoomstay(byte[] roomstay) {
		this.roomstay = roomstay;
	}
	public byte[] getPrebook_res() {
		return prebook_res;
	}
	public void setPrebook_res(byte[] prebook_res) {
		this.prebook_res = prebook_res;
	}
	public byte[] getBook_res() {
		return book_res;
	}
	public void setBook_res(byte[] book_res) {
		this.book_res = book_res;
	}
	/*CREATE TABLE `hotelbookings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(100) DEFAULT NULL,
  `hotelbook_cmd` mediumblob,
  `roomstay` mediumblob,
  `prebook_res` mediumblob,
  `book_res` mediumblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=latin1;*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="order_id")
	private String orderId;	
	@Lob
	private byte[] hotelbook_cmd;
	@Lob
	private byte[] roomstay;
	@Lob
	private byte[] prebook_res;
	@Lob
	private byte[] book_res;
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

