package com.lintas.admin.hotel.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lintas.admin.common.model.Timestampable;

@Entity @Access(AccessType.FIELD)
@Table(name = "hotel_order_hotel_data")
public class HotelOrderHotelData extends Timestampable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "hotelName")
	private String name;

	@Column(name = "profile_image_url")
	private String profileImageURL;

	@Column(name = "hotelType")
	private String type;

	@Column(name = "hotelStars")
	private Integer stars;

	@Column(name = "rank")
	private Integer rank;

	@Column(name = "trip_advisor_location_id")
	private String tripAdvisorLocationId;

	//TODO this column is not required
	@Column(name = "hotelElementID")
	private String  hotelElementID;

	@Column(name = "regionID")
	private String regionID;

	@Column(name = "regionName")
	private String regionName;

	@Column(name = "regionCityID")
	private Integer regionCityID;

	@Column(name = "address1")
	private String address1;

	@Column(name = "address2")
	private String address2;

	@Column(name = "address3")
	private String address3;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "fax")
	private String fax;

	@Column(name = "email")
	private String email;

	@Column(name = "url")
	private String url;

	@Column(name = "zip")
	private String zip;

	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;

	@Column(name = "chain")
	private String chain;

	@Column(name = "hotelLocation", columnDefinition="LONGTEXT")
	private String hotelLocation;

	@Column(name = "hotelInfo", columnDefinition="LONGTEXT")
	private String hotelInfo;

	@Column(name = "hotelCategory")
	private String hotelCategory;

	@Column(name = "hotelStyle")
	private String hotelStyle;

	@Column(name = "hotelTheme")
	private String hotelTheme;

	@Column(name = "property_amenities", columnDefinition="LONGTEXT")
	private String propertyAmenities;

	@Column(name = "room_amenities", columnDefinition="LONGTEXT")
	private String roomAmenities;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileImageURL()
	{
		return profileImageURL;
	}

	public void setProfileImageURL(String profileImageURL)
	{
		this.profileImageURL = profileImageURL;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String  getHotelElementID() {
		return hotelElementID;
	}

	public void setHotelElementID(String  hotelElementID) {
		this.hotelElementID = hotelElementID;
	}

	public String getRegionID() {
		return regionID;
	}

	public void setRegionID(String regionID) {
		this.regionID = regionID;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getRegionCityID() {
		return regionCityID;
	}

	public void setRegionCityID(Integer regionCityID) {
		this.regionCityID = regionCityID;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getZip() {
		return zip;
	}

	public String getHotelLocation() {
		return hotelLocation;
	}

	public void setHotelLocation(String hotelLocation) {
		this.hotelLocation = hotelLocation;
	}

	public String getHotelInfo() {
		return hotelInfo;
	}

	public void setHotelInfo(String hotelInfo) {
		this.hotelInfo = hotelInfo;
	}

	public String getHotelCategory() {
		return hotelCategory;
	}

	public void setHotelCategory(String hotelCategory) {
		this.hotelCategory = hotelCategory;
	}

	public String getHotelStyle() {
		return hotelStyle;
	}

	public void setHotelStyle(String hotelStyle) {
		this.hotelStyle = hotelStyle;
	}

	public String getHotelTheme() {
		return hotelTheme;
	}

	public void setHotelTheme(String hotelTheme) {
		this.hotelTheme = hotelTheme;
	}
	public String getPropertyAmenities()
	{
		return propertyAmenities;
	}

	public void setPropertyAmenities(String propertyAmenities)
	{
		this.propertyAmenities = propertyAmenities;
	}

	public String getRoomAmenities()
	{
		return roomAmenities;
	}

	public void setRoomAmenities(String roomAmenities)
	{
		this.roomAmenities = roomAmenities;
	}

	public String getTripAdvisorLocationId()
	{
		return tripAdvisorLocationId;
	}

	public void setTripAdvisorLocationId(String tripAdvisorLocationId)
	{
		this.tripAdvisorLocationId = tripAdvisorLocationId;
	}

	@Override
	public String toString() {
		return getId() + ": " + getName();
	}

	public List<String> getListOfPropertyAmenities(){
		try{
			return Arrays.asList(getPropertyAmenities().split(";"));
		} catch(Exception e){
			return null;
		}
	}

	public String getChain() {
		return chain;
	}

	public void setChain(String chain) {
		this.chain = chain;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}
