package com.lintas.admin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the email_types database table.
 * 
 */

@Entity
@Table(name="email_types")
@JsonIgnoreProperties(ignoreUnknown = true)
@Proxy(lazy = false)
public class EmailType implements Serializable {
	private static final long serialVersionUID = 1L;


	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Column(name="email_type")
	private int emailType;

	@Column(name="email_type_description")
	private String emailTypeDescription;

	/*//bi-directional many-to-one association to Email
	@OneToMany(targetEntity = EmailType.class, mappedBy="emailType",fetch=FetchType.EAGER,cascade = CascadeType.ALL )
	@JsonManagedReference
	private List<Email> emails;
*/
	public EmailType() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmailType() {
		return this.emailType;
	}

	public void setEmailType(int emailType) {
		this.emailType = emailType;
	}

	public String getEmailTypeDescription() {
		return this.emailTypeDescription;
	}

	public void setEmailTypeDescription(String emailTypeDescription) {
		this.emailTypeDescription = emailTypeDescription;
	}

	/*public List<Email> getEmails() {
		return this.emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}*/

	/*public Email addEmail(Email email) {
		getEmails().add(email);
		email.setEmailType(this);

		return email;
	}

	public Email removeEmail(Email email) {
		getEmails().remove(email);
		email.setEmailType(null);

		return email;
	}*/

}