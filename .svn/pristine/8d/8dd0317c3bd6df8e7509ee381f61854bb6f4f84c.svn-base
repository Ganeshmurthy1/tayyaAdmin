package com.admin.hotel.fin.sheet.model;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="hotel_Quotation_history")
public class HotelQuotationHistory implements Serializable{

	/**
	 * @author raham
	 * Date:12/28/
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue
	private Long id;
	@Column(name = "created_at", insertable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL")
	private Timestamp createdAt;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hotel_quotation_id", referencedColumnName = "id")
	private HotelTravelRequestQuotation  hotelTravelRequestQuotation;
	@Column(columnDefinition = "LONGTEXT")
	private String message;
	@Column(columnDefinition = "LONGTEXT")
	private String  comments;
	@Column(name="quotation_schema", columnDefinition = "LONGTEXT")
	private String hotelQuotationSchema;
	@Column(name="cc_mails", columnDefinition = "LONGTEXT")
	private String ccMails;
	@Column(name="to_mails", columnDefinition = "LONGTEXT")
	private String toMails;
	@Column(name="email_status_id")
	private int emailStatusId;
	
	
	public String getHotelQuotationSchema() {
		return hotelQuotationSchema;
	}
	public void setHotelQuotationSchema(String hotelQuotationSchema) {
		this.hotelQuotationSchema = hotelQuotationSchema;
	}
	public String getCcMails() {
		return ccMails;
	}
	public void setCcMails(String ccMails) {
		this.ccMails = ccMails;
	}
	public String getToMails() {
		return toMails;
	}
	public void setToMails(String toMails) {
		this.toMails = toMails;
	}
	public int getEmailStatusId() {
		return emailStatusId;
	}
	public void setEmailStatusId(int emailStatusId) {
		this.emailStatusId = emailStatusId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public HotelTravelRequestQuotation getHotelTravelRequestQuotation() {
		return hotelTravelRequestQuotation;
	}
	public void setHotelTravelRequestQuotation(HotelTravelRequestQuotation hotelTravelRequestQuotation) {
		this.hotelTravelRequestQuotation = hotelTravelRequestQuotation;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public static int INIT_STATUS_ID=0;
	public static int SUCCESS_STATUS_ID=1;
	public static int FAILED_STATUS_ID=-1;
	
	
	

}
