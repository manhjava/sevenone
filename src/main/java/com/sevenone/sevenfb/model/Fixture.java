package com.sevenone.sevenfb.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name = "fixture")
public class Fixture implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private Long id;
	
	private long homeid;
	private long awayid;
	
	/** The home. */
	private Team home;
	
	/** The away. */
	private Team away;
	
	/** The time. */
	private Date time;
	
	/** The stadium. */
	private String stadium;
	
	/** The result. */
	private String result;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DocumentId
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the home.
	 *
	 * @return the home
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="homeid", updatable = false, insertable = false)
	public Team getHome() {
		return home;
	}
	
	/**
	 * Sets the home.
	 *
	 * @param home the new home
	 */
	public void setHome(Team home) {
		this.home = home;
	}
	
	/**
	 * Gets the away.
	 *
	 * @return the away
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="awayid", updatable = false, insertable = false)
	public Team getAway() {
		return away;
	}
	
	/**
	 * Sets the away.
	 *
	 * @param away the new away
	 */
	public void setAway(Team away) {
		this.away = away;
	}
	
	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	@Column(name = "time")
	public Date getTime() {
		return time;
	}
	
	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(Date time) {
		this.time = time;
	}
	
	/**
	 * Gets the stadium.
	 *
	 * @return the stadium
	 */
	@Column(name = "stadium")
	public String getStadium() {
		return stadium;
	}
	
	/**
	 * Sets the stadium.
	 *
	 * @param stadium the new stadium
	 */
	public void setStadium(String stadium) {
		this.stadium = stadium;
	}
	
	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	@Column(name = "result")
	public String getResult() {
		return result;
	}
	
	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	public void setResult(String result) {
		this.result = result;
	}
	
	@Column(name = "homeid")
	public long getHomeid() {
		return homeid;
	}

	public void setHomeid(long homeid) {
		this.homeid = homeid;
	}
	@Column(name = "awayid")
	public long getAwayid() {
		return awayid;
	}

	public void setAwayid(long awayid) {
		this.awayid = awayid;
	}
	
	
}
