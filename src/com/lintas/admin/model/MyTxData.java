package com.lintas.admin.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class MyTxData {
 private String currency;
 private BigDecimal amount;
 private BigDecimal openingBal;
 private BigDecimal closinBal;
 private String agency;
 private Timestamp allotedAt;
 
 public String getCurrency() {
	return currency;
}
public void setCurrency(String currency) {
	this.currency = currency;
}
public BigDecimal getAmount() {
	return amount;
}
public void setAmount(BigDecimal amount) {
	this.amount = amount;
}
public BigDecimal getOpeningBal() {
	return openingBal;
}
public void setOpeningBal(BigDecimal openingBal) {
	this.openingBal = openingBal;
}
public BigDecimal getClosinBal() {
	return closinBal;
}
public void setClosinBal(BigDecimal closinBal) {
	this.closinBal = closinBal;
}
public String getAgency() {
	return agency;
}
public void setAgency(String agency) {
	this.agency = agency;
}
public Timestamp getAllotedAt() {
	return allotedAt;
}
public void setAllotedAt(Timestamp allotedAt) {
	this.allotedAt = allotedAt;
}

}
