package com.tayyarah.browsingHistory.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lintas.admin.common.model.Timestampable;
@Entity
@Table(name = "browsingHistory_detail")
public class BrowsingHistoryDetail extends Timestampable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "inventory_id" ,columnDefinition="LONGTEXT")
	private String inventoryId;	

	@Column(name = "type")
	private Integer type;

	@Column(name = "description" , columnDefinition="LONGTEXT")
	public String description;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
 @ManyToOne(cascade = CascadeType.ALL)	
	@JoinColumn(name = "history_id", referencedColumnName = "id")	
	private BrowsingHistory history;

	public BrowsingHistory getHistory() {
		return history;
	}
	public void setHistory(BrowsingHistory history) {
		this.history = history;
	} 
	
}
