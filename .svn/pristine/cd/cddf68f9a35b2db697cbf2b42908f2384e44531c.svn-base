package com.lintas.admin.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "user_wallet")
public class UserWallet implements Serializable {

	/**
	 * @author info raham
	 * created date:01-10-2015
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	private String referenceNumber;

	@Transient
	private String txType;

	@Id
	@GeneratedValue
	@Column(name = "wallet_id")
	private int walletId;

	@Column(name = "wallet_balance_range", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal walletBalanceRange;

	@Column(name = "wallet_type")
	private String walletType;

	@Column(name = "wallet_balance", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal walletbalance;
	@Column(name = "currency_code")
	private String currencyCode;
	/*@Column(name = "user_id")
	  private int userId; */
	@Column(name = "created_at")
	Timestamp createdAt;

	@Column(name = "updated_at",
			insertable = false,
			columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL")
	Timestamp updatedAt;
	@Column(name = "transaction_type")
	private String transactionType;

	@Column(name="deposit_balance", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal depositBalance;
	public BigDecimal getWalletbalance() {
		return walletbalance;
	}

	public void setWalletbalance(BigDecimal walletbalance) {
		this.walletbalance = walletbalance;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/*public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}*/

	public Timestamp getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt)
	{
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt()
	{
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt)
	{
		this.updatedAt = updatedAt;
	}

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public BigDecimal getWalletBalanceRange() {
		return walletBalanceRange;
	}

	public void setWalletBalanceRange(BigDecimal walletBalanceRange) {
		this.walletBalanceRange = walletBalanceRange;
	}

	public String getWalletType() {
		return walletType;
	}

	public void setWalletType(String walletType) {
		this.walletType = walletType;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getDepositBalance() {
		return depositBalance;
	}

	public void setDepositBalance(BigDecimal depositBalance) {
		this.depositBalance = depositBalance;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getTxType() {
		return txType;
	}

	public void setTxType(String txType) {
		this.txType = txType;
	}

}



