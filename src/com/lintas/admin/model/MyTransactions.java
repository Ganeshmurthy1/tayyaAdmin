package com.lintas.admin.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="my_transactions")
public class MyTransactions implements Serializable {

	/**@author info raham
	 * created date:02-10-2015
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "user_id")
	private int userId;
	private String currency;
	@Column(name = "alloted_at")
	Timestamp allotedAt;
	private BigDecimal amount;
	@Column(name = "wallet_id")
	private int walletId;
	
	@Column(name = "parent_id")
	private int parentId;
	
	
	@Column(name = "opening_balance")
	private BigDecimal openingBalance;
	@Column(name = "closing_balance")
	private BigDecimal closingBalance;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Timestamp getAllotedAt() {
		return allotedAt;
	}
	public void setAllotedAt(Timestamp allotedAt) {
		this.allotedAt = allotedAt;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public BigDecimal getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(BigDecimal openingBalance) {
		this.openingBalance = openingBalance;
	}
	public BigDecimal getClosingBalance() {
		return closingBalance;
	}
	public void setClosingBalance(BigDecimal closingBalance) {
		this.closingBalance = closingBalance;
	}
	/**
	 * @return the walletId
	 */
	public int getWalletId() {
		return walletId;
	}
	/**
	 * @param walletId the walletId to set
	 */
	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

}
