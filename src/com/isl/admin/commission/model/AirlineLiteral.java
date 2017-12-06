package com.isl.admin.commission.model;

import java.io.Serializable;
import java.util.ArrayList;

public class AirlineLiteral implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int type;
	String name;
	boolean isApplicable;
	ArrayList<AirlineLiteral> items;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isApplicable() {
		return isApplicable;
	}
	public void setApplicable(boolean isApplicable) {
		this.isApplicable = isApplicable;
	}
	public ArrayList<AirlineLiteral> getItems() {
		return items;
	}
	public void setItems(ArrayList<AirlineLiteral> items) {
		this.items = items;
	}
	public AirlineLiteral(int type, String name, boolean isApplicable,
			ArrayList<AirlineLiteral> items) {
		super();
		this.type = type;
		this.name = name;
		this.isApplicable = isApplicable;
		this.items = items;
	}

	public AirlineLiteral(int type, String name, boolean isApplicable
			) {
		super();
		this.type = type;
		this.name = name;
		this.isApplicable = isApplicable;
		this.items = new ArrayList<AirlineLiteral>();
	}
	
	@Override
	public String toString() {
		return "AirlineLiteral [type=" + type + ", name=" + name
				+ ", isApplicable=" + isApplicable + ", items=" + items + "]";
	}
		
}
