package com.admin.hotel.fin.sheet.model;

public class HotelOffineVoucherUtility {
	public HotelOffineVoucherUtility(int totalGuest, String roomType, String inclussions) {
		super();
		this.totalGuest = totalGuest;
		this.roomType = roomType;
		this.inclussions = inclussions;
	}
	private int totalGuest;
	private String roomType;
	private String inclussions;
	public int getTotalGuest() {
		return totalGuest;
	}
	public void setTotalGuest(int totalGuest) {
		this.totalGuest = totalGuest;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getInclussions() {
		return inclussions;
	}
	public void setInclussions(String inclussions) {
		this.inclussions = inclussions;
	}

}
