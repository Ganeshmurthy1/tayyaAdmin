package com.lintas.admin.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "deals")
public class CrudOperationDeals implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue
	Long id;
	
	@Column(name="price")
	private String price;
	@Column(name="currency")
	private String currency;
	@Column(name="City")
	private String City;
	@Column(name="image_caption")
	private String imageCaption;
	@Column(name="image_name")
	private String imageName;
	@Column(name="image_url")
	private String imageUrl;
	@Column(name="image_type")
	private String imageType;
	@Column(name="image_size")
	private String imageSize;
	@Column(name="deals_type")
	private String dealsType;
	@Column(name="catagory")
	private String catagory;
	@Column(name="description")
	private String description;
	@Column(name="title")
	private String title;
	@Column(name="pageName")
	private String PageName;
	@Column(name="is_status")
	private boolean isStatus;
	@Column(name="is_locked")
	private boolean isLocked;

	@Column(name="start_date")
	private String Startdate;
	@Column(name="end_date")
	private String Enddate;
	
	@Column(name="city1")
	private String City1;
	
	public boolean isLocked() {
		return isLocked;
	}
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	public boolean isStatus() {
		return isStatus;
	}
	public void setStatus(boolean isStatus) {
		this.isStatus = isStatus;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getImageCaption() {
		return imageCaption;
	}
	public void setImageCaption(String imageCaption) {
		this.imageCaption = imageCaption;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public String getImageSize() {
		return imageSize;
	}
	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}
	public String getDealsType() {
		return dealsType;
	}
	public void setDealsType(String dealsType) {
		this.dealsType = dealsType;
	}
	public String getCatagory() {
		return catagory;
	}
	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPageName() {
		return PageName;
	}
	public void setPageName(String pageName) {
		PageName = pageName;
	}
	public String getStartdate() {
		return Startdate;
	}
	public void setStartdate(String startdate) {
		Startdate = startdate;
	}
	public String getEnddate() {
		return Enddate;
	}
	public void setEnddate(String enddate) {
		Enddate = enddate;
	}
	public String getCity1() {
		return City1;
	}
	public void setCity1(String city1) {
		City1 = city1;
	}

}



	
