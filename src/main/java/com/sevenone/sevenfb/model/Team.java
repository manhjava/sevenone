/*
* Copyright (c) sevenfb Ltd All rights reserved.
*
* This software is the confidential and proprietary information of sevenfb
* ("Confidential Information"). You shall not disclose such Confidential
* Information and shall use it only in accordance with the terms of the
* license agreement you entered into with sevenfb.
*/
package com.sevenone.sevenfb.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name = "team")
public class Team implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1237065486037661997L;
	private Long id;
	private String name;
	private Date joinedDate;
	private String color;
	private int type;//san 7 or san 11
	
	/** The stadium id. */
	private String stadium;
	
	/** The region. */
	private String region;
	
	private String description;
	
	//private List<User> players;
	private Set<User> players = new HashSet<User>();
	private List<User> captain;
	
	/**
	 * @return 
	 */
	@Column(name = "name")
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "joinedDate")
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	@Column(name = "color")
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "stype")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String toString() {
		return "Team info [name=" + name + ", type=" + type + ", region=" + region
				+ ", description=" + description
				+ ", description=" + description + "]";
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DocumentId
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "stadium")
	public String getStadium() {
		return stadium;
	}
	public void setStadium(String stadium) {
		this.stadium = stadium;
	}
	
	@Column(name = "region")
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	/*@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "teamuser", joinColumns = {
			@JoinColumn(name = "teamid", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "userid", 
					nullable = false, updatable = false) })*/
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="teamid", insertable = true, updatable = true)
	public Set<User> getPlayers() {
		if(players == null || players.size() == 0) 
			return new HashSet<>();
		return players;
	}
	public void setPlayers(Set<User> players) {
		this.players = players;
	}
	
	/*@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "teamuser", joinColumns = {
			@JoinColumn(name = "teamid", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "userid", 
					nullable = false, updatable = false) })
	@Where(clause = "role = 'C'")*/
	/*@OneToOne(cascade = CascadeType.ALL) //running well
	@JoinColumn(name="captainid", insertable = false, updatable=false)*/
	@Transient
	public List<User> getCaptain() {
		return captain;
	}
	public void setCaptain(List<User> captain) {
		this.captain = captain;
	}
	
	public void addPlayer(User player) {
		this.players.add(player);
	}
	
}
