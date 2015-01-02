package com.sevenone.sevenfb.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;

// TODO: Auto-generated Javadoc
/**
 * The Class Notification.
 */
@Entity
@Table(name = "notification")
public class Notification {
	
	/** The id. */
	private long id;
	
	/** The noti to. */
	private long notiTo;
	
	/** The who did. */
	private long whoDid;
	
	/** The noti to type. */
	private int notiToType;//Team or Player
	
	/** The noti type. */
	private String notiType;
	
	/** The noti content. */
	private String notiContent;
	
	/** The noti status. */
	private String notiStatus;
	
	private String uuid;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@DocumentId
	public long getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Gets the noti to.
	 *
	 * @return the noti to
	 */
	@Column(name = "notiTo")
	@Field
	public long getNotiTo() {
		return notiTo;
	}
	
	/**
	 * Sets the noti to.
	 *
	 * @param notiTo the new noti to
	 */
	public void setNotiTo(long notiTo) {
		this.notiTo = notiTo;
	}
	
	/**
	 * Gets the noti type.
	 *
	 * @return the noti type
	 */
	@Column(name = "notiType", length = 50)
	@Field
	public String getNotiType() {
		return notiType;
	}
	
	/**
	 * Sets the noti type.
	 *
	 * @param notiType the new noti type
	 */
	public void setNotiType(String notiType) {
		this.notiType = notiType;
	}
	
	/**
	 * Gets the noti content.
	 *
	 * @return the noti content
	 */
	@Column(name = "notiContent")
	@Field
	public String getNotiContent() {
		return notiContent;
	}
	
	/**
	 * Sets the noti content.
	 *
	 * @param notiContent the new noti content
	 */
	public void setNotiContent(String notiContent) {
		this.notiContent = notiContent;
	}
	
	/**
	 * Gets the noti status.
	 *
	 * @return the noti status
	 */
	@Column(name = "notiStatus", length = 50)
	@Field
	public String getNotiStatus() {
		return notiStatus;
	}
	
	/**
	 * Sets the noti status.
	 *
	 * @param notiStatus the new noti status
	 */
	public void setNotiStatus(String notiStatus) {
		this.notiStatus = notiStatus;
	}
	
	/**
	 * Gets the who did.
	 *
	 * @return the who did
	 */
	@Column(name = "whoDid")
	@Field
	public long getWhoDid() {
		return whoDid;
	}
	
	/**
	 * Sets the who did.
	 *
	 * @param whoDid the new who did
	 */
	public void setWhoDid(long whoDid) {
		this.whoDid = whoDid;
	}
	
	/**
	 * Gets the noti to type.
	 *
	 * @return the noti to type
	 */
	@Column(name = "notiToType")
	@Field
	public int getNotiToType() {
		return notiToType;
	}
	
	/**
	 * Sets the noti to type.
	 *
	 * @param notiToType the new noti to type
	 */
	public void setNotiToType(int notiToType) {
		this.notiToType = notiToType;
	}
	
	@Column(name = "uuid", length = 50)
	@Field
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
