package com.tayyarah.hotel.model;

 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.lintas.admin.common.model.Timestampable;

@Entity
@Table(name = "hotel_details")
	public class HotelDetails extends Timestampable{
		@Override
	public String toString() {
		return "HotelDetails [name=" + name + ", hotelCode=" + hotelCode + ", hotelChain=" + hotelChain + ", rating="
				+ rating + ", city=" + city + ", country=" + country + ", latitude=" + latitude + ", longitude="
				+ longitude + ", hotelType=" + hotelType + ", activeStatus=" + activeStatus + ", rooms=";
	}

		/**
	 * @author HARSHA
	 */
	private static final long serialVersionUID = 1L;
		@Column(name = "name")
		private String name;
		@Column(name = "hotel_code")
		private String hotelCode;
		@Column(name = "hotel_chain")
		private String hotelChain;
		@Column(name = "rating")
		private String rating;
		@Column(name = "city")
		private String city;
		@Column(name = "country")
		private String country;
		@Column(name = "latitude")
		private String latitude;
		@Column(name = "longitude")
		private String longitude;
		@Column(name = "hotel_type") // hotel, Villa, Apartment
		private String hotelType;

		@Column(name = "is_active", columnDefinition = "bit DEFAULT b'0' NOT NULL")
		private boolean activeStatus;
		
		@Column(name = "company_user_id")
		private String  companyUserId;
		@OneToMany(targetEntity = HotelRoomDetails.class,   mappedBy = "hotelDetails" , cascade = CascadeType.ALL)
		private List<HotelRoomDetails> rooms; // list of rooms
 	 
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getHotelCode() {
			return hotelCode;
		}

		public void setHotelCode(String hotelCode) {
			this.hotelCode = hotelCode;
		}

		public String getHotelChain() {
			return hotelChain;
		}

		public void setHotelChain(String hotelChain) {
			this.hotelChain = hotelChain;
		}

		public String getRating() {
			return rating;
		}

		public void setRating(String rating) {
			this.rating = rating;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
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

		public String getHotelType() {
			return hotelType;
		}

		public void setHotelType(String hotelType) {
			this.hotelType = hotelType;
		}

 	public List<HotelRoomDetails> getRooms() {
			return rooms;
		}

		public void setRooms(List<HotelRoomDetails> rooms) {
			this.rooms = rooms;
		} 

		public boolean isActiveStatus() {
			return activeStatus;
		}

		public void setActiveStatus(boolean activeStatus) {
			this.activeStatus = activeStatus;
		}

		public String getCompanyUserId() {
			return companyUserId;
		}

		public void setCompanyUserId(String companyUserId) {
			this.companyUserId = companyUserId;
		}

		/*@Override
		public String toString() {
			return "HotelDetailPojo [name=" + name + ", hotelCode=" + hotelCode + ", hotelChain=" + hotelChain
					+ ", rating=" + rating + ", city=" + city + ", country=" + country + ", latitude=" + latitude
					+ ", longitude=" + longitude + ", hotelType=" + hotelType + ", activeStatus=" + activeStatus
					+ ", rooms=" + rooms + "]";
		}*/

}
