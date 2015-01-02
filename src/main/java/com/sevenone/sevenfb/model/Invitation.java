package com.sevenone.sevenfb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;

@Entity
@Table(name = "invitation")
public class Invitation {
	
	private long id;
	private String status;
	private long teamid;
	private long userid;
	private long invitor;
	private User invitorUser;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@DocumentId
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "status", length = 50)
	@Field
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "teamid")
	@Field
	public long getTeamid() {
		return teamid;
	}
	public void setTeamid(long teamid) {
		this.teamid = teamid;
	}
	
	@Column(name = "userid")
	@Field
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	
	@Column(name = "invitor")
	@Field
	public long getInvitor() {
		return invitor;
	}
	public void setInvitor(long invitor) {
		this.invitor = invitor;
	}
	
	@ManyToOne
    @JoinColumn(name="invitor", insertable=false, updatable = false)//invitor is id of user
	public User getInvitorUser() {
		return invitorUser;
	}
	public void setInvitorUser(User invitorUser) {
		this.invitorUser = invitorUser;
	}
	
	
	
	
	
}
