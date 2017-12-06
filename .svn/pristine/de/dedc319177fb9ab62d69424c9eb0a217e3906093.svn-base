package com.tayyarah.browsingHistory.model;

import java.io.Serializable;

public class HistoryDetailInfo implements Serializable{
	@Override
	public String toString() {
		return "HistoryDetailInfo [detailTypeEnum=" + detailTypeEnum + ", inventoryId=" + inventoryId + ", description="
				+ description + "]";
	}
	public HistoryDetailInfo(BrowsingHistoryDetailTypeEnum detailTypeEnum, String inventoryId, String description) {
		super();
		this.detailTypeEnum = detailTypeEnum;
		this.inventoryId = inventoryId;
		this.description = description;
	}
	public BrowsingHistoryDetailTypeEnum getDetailTypeEnum() {
		return detailTypeEnum;
	}
	public void setDetailTypeEnum(BrowsingHistoryDetailTypeEnum detailTypeEnum) {
		this.detailTypeEnum = detailTypeEnum;
	}
	public String getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private BrowsingHistoryDetailTypeEnum detailTypeEnum;	
	private String inventoryId;		
	public String description;
}
