package com.isl.admin.filter;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;

public class BugReportFilter extends Filter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int companyId;
	private int userId;
	private String companyUserId;
	private String fromDate;
	private String toDate;
	private String companyName;
	private String userName;
	private String showtype;
	private String assignTo;
	private String assignedBy;
	public BugReportFilter() {
		super();
		this.companyId = -1;
		this.userId = -1;
		this.companyName = null;
		this.showtype = null;
		this.fromDate = "";
		this.toDate = "";
		this.userName = null;
		this.companyUserId=null;
	}
	public BugReportFilter(CompanyConfig loginCompanyConfig, Company loginCompany, User loginUser, int reportType) {
		super(loginCompanyConfig, loginCompany, loginUser, reportType);
		// TODO Auto-generated constructor stub
	}
	public BugReportFilter(CompanyConfig loginCompanyConfig, Company loginCompany, User loginUser) {
		super(loginCompanyConfig, loginCompany, loginUser);
		// TODO Auto-generated constructor stub
	}

	public String getFromDate() {
		return fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getShowtype() {
		return showtype;
	}
	public void setShowtype(String showtype) {
		this.showtype = showtype;
	}

	

	public String getCompanyUserId() {
		return companyUserId;
	}

	public void setCompanyUserId(String companyUserId) {
		this.companyUserId = companyUserId;
	}
	public String getAssignTo() {
		return assignTo;
	}
	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}
	public String getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}
	

	/*@Override
	public String toString() {
		return "FlightReportFilter [bookingStatusQueue=" + bookingStatusQueue + ", paymentStatusQueue="
				+ paymentStatusQueue + ", invoiceNo=" + invoiceNo + ", dateFilterBooking=" + dateFilterBooking
				+ ", dateFilterArrival=" + dateFilterArrival + ", dateFilterDeparture=" + dateFilterDeparture
				+ ", amountRangeBooking=" + amountRangeBooking + ", companyId=" + companyId + ", userId=" + userId
				+ ", companyUserId=" + companyUserId + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", companyName=" + companyName + ", userName=" + userName + ", paymentStatus=" + paymentStatus
				+ ", bookingStatus=" + bookingStatus + ", origin=" + origin + ", destination=" + destination
				+ ", airlineName=" + airlineName + ", showtype=" + showtype + ", pnr=" + pnr + ", paxName=" + paxName
				+ ", orderId=" + orderId + ", email=" + email + ", confirmationNo=" + confirmationNo + ", customerId="
				+ customerId + ", travelTypeList=" + travelTypeList + "]";
	}*/

}
