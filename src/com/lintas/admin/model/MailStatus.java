package com.lintas.admin.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mailstatus")
public class MailStatus implements Serializable{

	/**
	 * @author info
	 * name:raham
	 * date:24-07-2015
	 * time:7:15PM
	 * 
	 */
	private static final long serialVersionUID = 1L;
  
	 public int getsNo() {
		return sNo;
	}
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getfromMail() {
		return fromMail;
	}
	public void setfromMail(String from) {
		this.fromMail = from;
	}
	public String getToMail() {
		return toMail;
	}
	public void setToMail(String to) {
		this.toMail = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getAttachmentlist() {
		return attachmentlist;
	}
	public void setAttachmentlist(String attachmentlist) {
		this.attachmentlist = attachmentlist;
	}
	public String getLinkAttached() {
		return linkAttached;
	}
	public void setLinkAttached(String linkAttached) {
		this.linkAttached = linkAttached;
	}
	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	public Date getMailSendDate() {
		return mailSendDate;
	}
	public void setMailSendDate(Date mailSendDate) {
		this.mailSendDate = mailSendDate;
	}
	public String getMailStatus() {
		return mailStatus;
	}
	public void setMailStatus(String mailStatus) {
		this.mailStatus = mailStatus;
	}
	@Id
	 @GeneratedValue
	 private int sNo;
	 @Column(name="userId")
	 private int userId;
	 @Column(name="fromMail")
	 private String fromMail;/*="popi@intellicommsolutions.com"*/
	 @Column(name="toMail")
	 private String toMail;
	 @Column(name="mailSubject")
	 private String subject;
	 @Column(name="body")
	 private String body;
	 @Column(name="attachmentlist")
	 private String  attachmentlist;
	 @Column(name="linkAttached")
	 private String linkAttached;
	 @Column(name="emailType")
	 private String  emailType;
	 @Column(name="mailSendDate")
	 private Date mailSendDate;
	 @Column(name="mailStatus")
	 private String mailStatus;	
	 
	 
	 
	 
	 
}
