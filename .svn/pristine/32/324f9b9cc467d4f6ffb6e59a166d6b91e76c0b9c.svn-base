package com.admin.api.provider.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="travel_type")
public class ApiProviderTravelType  implements Serializable{
 /**@author info raham
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue
		@Column(name="id")
		private int travelTypeId;
		
		@Column(name="hotel", columnDefinition = "BOOLEAN DEFAULT false")
		private boolean isHotel;

		@Column(name="flight" ,columnDefinition = "BOOLEAN DEFAULT false")
		private boolean isFlight;
		@Column(name="bus", columnDefinition = "BOOLEAN DEFAULT false")
		private boolean isBus;

		@Column(name="car" ,columnDefinition = "BOOLEAN DEFAULT false")
		private boolean isCar;

		public boolean isBus() {
			return isBus;
		}

		public void setBus(boolean isBus) {
			this.isBus = isBus;
		}

		public boolean isCar() {
			return isCar;
		}

		public void setCar(boolean isCar) {
			this.isCar = isCar;
		}

		public int getTravelTypeId() {
			return travelTypeId;
		}

		public ApiProviderTravelType(boolean isHotel, boolean isFlight) {
			super();
			this.isHotel = isHotel;
			this.isFlight = isFlight;
		}
		public ApiProviderTravelType(){
			
		}
		public void setTravelTypeId(int travelTypeId) {
			this.travelTypeId = travelTypeId;
		}

		public boolean isHotel() {
			return isHotel;
		}

		public void setHotel(boolean isHotel) {
			this.isHotel = isHotel;
		}

		public boolean isFlight() {
			return isFlight;
		}

		public void setFlight(boolean isFlight) {
			this.isFlight = isFlight;
		}
 
}
