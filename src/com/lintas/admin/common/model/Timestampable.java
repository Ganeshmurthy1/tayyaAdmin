package com.lintas.admin.common.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class Timestampable implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue
	Long id;

	@Column(name = "created_at")
	Timestamp createdAt;

	@Column(name = "updated_at",
			insertable = false,
			columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL")
	Timestamp updatedAt;

	@Version
	@Column(name = "version",columnDefinition = "integer DEFAULT 0", nullable = false)
	int version;

	
	
	/************
	 *
	 * Getters and Setters
	 *
	 */

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

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

	public int getVersion()
	{
		return version;
	}

	public void setVersion(int version)
	{
		this.version = version;
	}

}
