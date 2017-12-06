/**
@Author VimalSusaiRaj
20-Jul-2015 2015
Userregistration.java
 */
/**
 * 
 */
package com.lintas.admin.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.admin.corporate.employee.CompanyBandEntity;
import com.admin.corporate.employee.CompanyBusinessUnitEntity;
import com.admin.corporate.employee.CompanyCostCenterEntity;
import com.admin.corporate.employee.CompanyDepartmentEntity;
import com.admin.corporate.employee.CompanyDesignationEntity;
import com.admin.designationband.EmployeeDesignationsModel;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.UserDAO;

@Entity
@Table(name="user")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	@Transient
	private BigDecimal walletRange;
	@Transient
	private BigDecimal corporateCreditBalance;
	@Transient
	private BigDecimal corporateDepositBalance;
	@Transient
	@Column(name="company_naame")
	private String companyName;
	@Transient
	@Column(name="userRole")
	private String role; 
	@Transient
	@Column(name="company_type")
	private String companyType; 

	@Column(name="bandName")
	private String bandName;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "desgId", referencedColumnName = "desgId")
	private EmployeeDesignationsModel employeeDesignationsModels;

	/*@ManyToOne  
	 @JoinColumn(name = "companyid")  
	 private Company company; */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_role_id", referencedColumnName = "roleid")
	private UserRole userrole_id;	


	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "walletId" )
	private UserWallet agentWallet;
	@Transient
	@Column(name="create_date")
	private String createDate;
	/*@Transient
	@Column(name="modify_date")
	private String modifyDate; */
	@Column(name="company_userid")
	private String company_userid;
	@Column(name="username")
	private String Username;
	@Column(name="firstname")
	private String Firstname;
	@Column(name="lastname")
	private String Lastname;
	@Column(name="password")
	private String Password;
	@Column(name="address")
	private String Address;
	@Column(name="city")
	private String City;
	@Column(name="countryname")
	private String Countryname;
	@Column(name="imagepath")
	private String Imagepath;
	@Column(name="securityquestion")
	private String Securityquestion;
	@Column(name="securityanswer")
	private String Securityanswer;	
	@Column(name="email")
	private String Email;
	@Column(name="phone")
	private String Phone;
	@Column(name="mobile")
	private String Mobile;
	@Column(name="description")
	private String Description;
	/*@Column(name="status")
	private String status;*/
	@Column(name="language")
	private String Language;
	@Column(name="createdby_company_user_id")
	private int createdbyCompanyUserId;
	@Column(name="modifiedby_company_user_id")
	private int modifiedbyCompanyUserId;
	@Column(name="createddate")
	private Date Createddate;
	/*@Column(name="locked")
	private String Locked;*/
	@Column(name="lockedDate")
	private Date LockedDate;
	@Column(name="attempts")
	private int attemt;
	@Column(name="companyid")
	private int Companyid;
	@Column(name="is_status")
	private boolean isStatus;
	@Column(name="is_locked")
	private boolean isLocked;
	@Column(name="currency_code")
	private String currencyCode; 
	@Column(name="modifieddate")
	private Date Modifieddate;	
	@Column(name="passportno")
	private String passportno; 
	@Column(name="passport_expirydate")
	private String passport_expirydate; 
	@Column(name="passport_issuedate")
	private String passport_issuedate; 
	@Column(name="passport_issueplace")
	private String passport_issueplace; 
	@Column(name="dateofbirth")
	private String dateofbirth; 
	@Column(name="mail_status")
	private int mailStatus;
	@Column(name="email_code")
	private String  emailCode; 
	private transient String headers;
	@Column(name="passport_size_image")
	private String  passportSizeImage;
	@Column(name="passport_scan_copy")
	private String  passportScanCopy;
	@Column(name="designation")
	private String  designation;
	@Column(name="business_unit_id")
	private Long  businessUnitId;
	@Column(name="cost_center_id")
	private Long  costCenterId;
	@Column(name="department_id")
	private Long  DepartmentId;
	@Column(name="band_id")
	private Long  BandId;
	@Column(name="country_code")
	private String countryCode;
	
	public long getBusinessUnitId() {
		if(businessUnitId!=null)
		    return businessUnitId;
		else
			return 0L;
	}

	public void setBusinessUnitId(long businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	public long getCostCenterId() {
		if(costCenterId!=null)
		    return costCenterId;
		else
			return 0L;
	}

	public void setCostCenterId(long costCenterId) {
		this.costCenterId = costCenterId;
	}

	public long getDepartmentId() {
		if(DepartmentId!=null)
		    return DepartmentId;
		else
			return 0L;
	}

	public void setDepartmentId(long departmentId) {
		DepartmentId = departmentId;
	}

	public long getBandId() {
		if(BandId!=null)
		    return BandId;
		else
			return 0L;
	}

	public void setBandId(long bandId) {
		BandId = bandId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompanyid() {
		return Companyid;
	}

	public void setCompanyid(int companyid) {
		Companyid = companyid;
	}


	public Date getModifieddate() {
		return Modifieddate;
	}

	public void setModifieddate(Date modifieddate) {
		Modifieddate = modifieddate;
	}


	/*public int getCompanyid() {
		return Companyid;
	}
	public void setCompanyid(int companyid) {
		Companyid = companyid;
	}*/
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getFirstname() {
		return Firstname;
	}
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	/*public String getFax() {
		return Fax;
	}
	public void setFax(String fax) {
		Fax = fax;
	}*/
	/*public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}*/
	public String getLanguage() {
		return Language;
	}
	public void setLanguage(String language) {
		Language = language;
	}

	public Date getCreateddate() {
		return Createddate;
	}
	public void setCreateddate(Date createddate) {
		Createddate = createddate;
	}
	/*public String getLocked() {
		return Locked;
	}
	public void setLocked(String locked) {
		Locked = locked;
	}*/
	public Date getLockedDate() {
		return LockedDate;
	}
	public void setLockedDate(Date lockedDate) {
		LockedDate = lockedDate;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getCountryname() {
		return Countryname;
	}
	public void setCountryname(String countryname) {
		Countryname = countryname;
	}
	public String getImagepath() {
		return Imagepath;
	}
	public void setImagepath(String imagepath) {
		Imagepath = imagepath;
	}
	public String getSecurityquestion() {
		return Securityquestion;
	}
	public void setSecurityquestion(String securityquestion) {
		Securityquestion = securityquestion;
	}
	public String getSecurityanswer() {
		return Securityanswer;
	}
	public void setSecurityanswer(String securityanswer) {
		Securityanswer = securityanswer;
	}


	/**
	 * @return the userrole_id
	 */
	public UserRole getUserrole_id() {
		return userrole_id;
	}

	/**
	 * @param userrole_id the userrole_id to set
	 */
	public void setUserrole_id(UserRole userrole_id) {
		this.userrole_id = userrole_id;
	}


	/**
	 * @return the company_userid
	 */
	public String getCompany_userid() {
		return company_userid;
	}

	/**
	 * @param company_userid the company_userid to set
	 */
	public void setCompany_userid(String company_userid) {
		this.company_userid = company_userid;
	}

	/**
	 * @return the attemt
	 */
	public int getAttemt() {
		return attemt;
	}

	/**
	 * @param attemt the attemt to set
	 */
	public void setAttemt(int attemt) {
		this.attemt = attemt;
	}

	/**
	 * @return the agentWallet
	 */
	public UserWallet getAgentWallet() {
		return agentWallet;
	}

	/**
	 * @param agentWallet the agentWallet to set
	 */
	public void setAgentWallet(UserWallet agentWallet) {
		this.agentWallet = agentWallet;
	}

	/**
	 * @return the isStatus
	 */
	public boolean isStatus() {
		return isStatus;
	}

	/**
	 * @param isStatus the isStatus to set
	 */
	public void setStatus(boolean isStatus) {
		this.isStatus = isStatus;
	}

	/**
	 * @return the isLocked
	 */
	public boolean isLocked() {
		return isLocked;
	}

	/**
	 * @param isLocked the isLocked to set
	 */
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public int getCreatedbyCompanyUserId() {
		return createdbyCompanyUserId;
	}

	public void setCreatedbyCompanyUserId(int createdbyCompanyUserId) {
		this.createdbyCompanyUserId = createdbyCompanyUserId;
	}

	public int getModifiedbyCompanyUserId() {
		return modifiedbyCompanyUserId;
	}

	public void setModifiedbyCompanyUserId(int modifiedbyCompanyUserId) {
		this.modifiedbyCompanyUserId = modifiedbyCompanyUserId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getPassportno() {
		return passportno;
	}

	public void setPassportno(String passportno) {
		this.passportno = passportno;
	}

	public String getPassport_expirydate() {
		return passport_expirydate;
	}

	public void setPassport_expirydate(String passport_expirydate) {
		this.passport_expirydate = passport_expirydate;
	}

	public String getPassport_issuedate() {
		return passport_issuedate;
	}

	public void setPassport_issuedate(String passport_issuedate) {
		this.passport_issuedate = passport_issuedate;
	}

	public String getPassport_issueplace() {
		return passport_issueplace;
	}

	public void setPassport_issueplace(String passport_issueplace) {
		this.passport_issueplace = passport_issueplace;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public String getHeaders() {
		return headers;
	}

	public void setHeaders(String headers) {
		this.headers = headers;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}


	public String getEmailCode() {
		return emailCode;
	}

	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}


	public int getMailStatus() {
		return mailStatus;
	}

	public void setMailStatus(int mailStatus) {
		this.mailStatus = mailStatus;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassportSizeImage() {
		return passportSizeImage;
	}

	public void setPassportSizeImage(String passportSizeImage) {
		this.passportSizeImage = passportSizeImage;
	}

	public String getPassportScanCopy() {
		return passportScanCopy;
	}

	public void setPassportScanCopy(String passportScanCopy) {
		this.passportScanCopy = passportScanCopy;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public EmployeeDesignationsModel getEmployeeDesignationsModels() {
		return employeeDesignationsModels;
	}

	public void setEmployeeDesignationsModels(EmployeeDesignationsModel employeeDesignationsModels) {
		this.employeeDesignationsModels = employeeDesignationsModels;
	}

	public BigDecimal getWalletRange() {
		BigDecimal walletRangebalance= agentWallet.getWalletBalanceRange();
		String walletrange=walletRangebalance.toString();
		if(walletRangebalance!=null && walletrange!=null &&  walletrange.startsWith("-")){
			walletRange=new BigDecimal(walletrange.substring(1)).setScale(2, BigDecimal.ROUND_UP);
			return walletRange;
		}
		else {
			walletRange=new BigDecimal(walletrange).setScale(2, BigDecimal.ROUND_UP);
			return walletRange;	
		}
	}

	/*public BigDecimal getCorporateWalletBalanceForEmployee() {
		Company company= new CompanyDAO().getCompanyProfile(getCompanyid());
		String walletrange=walletRangebalance.toString();
		if(walletRangebalance!=null && walletrange!=null &&  walletrange.startsWith("-")){
			walletRange=new BigDecimal(walletrange.substring(1)).setScale(2, BigDecimal.ROUND_UP);
			return walletRange;
		}
		else {
			walletRange=new BigDecimal(walletrange).setScale(2, BigDecimal.ROUND_UP);
			return walletRange;	
		}
	}
	*/
	
	
	public void setWalletRange(BigDecimal walletRange) {
		this.walletRange = walletRange;
	}
	
	public BigDecimal getCorporateCreditBalance() {
		return corporateCreditBalance;
	}

	public BigDecimal getCorporateDepositBalance() {
		return corporateDepositBalance;
	}

	public void setCorporateCreditBalance(BigDecimal corporateCreditBalance) {
		this.corporateCreditBalance = corporateCreditBalance;
	}

	public void setCorporateDepositBalance(BigDecimal corporateDepositBalance) {
		this.corporateDepositBalance = corporateDepositBalance;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
 
}
