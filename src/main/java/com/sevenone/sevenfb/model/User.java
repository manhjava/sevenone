package com.sevenone.sevenfb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.UserProfile;

import com.sevenone.sevenfb.Constants;

/**
 * This class represents the basic "user" object in AppFuse that allows for
 * authentication and user management. It implements Acegi Security's
 * UserDetails interface.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 */
@Entity
@Table(name = "app_user")
@Indexed
@XmlRootElement
public class User extends BaseObject implements Serializable, UserDetails {
	private static final long serialVersionUID = 3832626162173359411L;

	private Long id;
	private String username; // required
	private String password; // required
	private String confirmPassword;
	//private String passwordHint;
	private String firstName; // required
	private String lastName; // required
	private String email; // required; unique
	private String phoneNumber;
	//private Address address = new Address();
	private Integer version;
	private Set<Role> roles = new HashSet<Role>();
	private boolean enabled;
	private boolean accountExpired;
	private boolean accountLocked;
	private boolean credentialsExpired;
	private String height;
	private String weight;
	private String dob;
	private String position;
	private Team team;
	//private Long teamid;
	//private List<Team> teams;
	private String fbprofileUrl;
	private String fbimageUrl;
	private String fbdisplayName;
	private String teamRole;

	/**
	 * Default constructor - creates a new instance with no values set.
	 */
	public User() {
	}

	/**
	 * Create a new instance and set the username.
	 * 
	 * @param username
	 *            login name for user.
	 */
	public User(final String username) {
		this.username = username;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@DocumentId
	public Long getId() {
		return id;
	}

	@Column(name = "username", nullable = false, length = 50, unique = true)
	@Field
	public String getUsername() {
		return username;
	}

	@Column(nullable = false)
	@XmlTransient
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@Transient
	@XmlTransient
	@JsonIgnore
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/*@Column(name = "password_hint")
	@XmlTransient
	public String getPasswordHint() {
		return passwordHint;
	}*/

	@Column(name = "first_name", nullable = false, length = 50)
	@Field
	public String getFirstName() {
		return firstName;
	}

	@Column(name = "last_name", nullable = false, length = 50)
	@Field
	public String getLastName() {
		return lastName;
	}

	@Column(nullable = false, unique = true)
	@Field
	public String getEmail() {
		return email;
	}

	@Column(name = "phone_number")
	@Field(analyze = Analyze.NO)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Returns the full name.
	 * 
	 * @return firstName + ' ' + lastName
	 */
	@Transient
	public String getFullName() {
		return firstName + ' ' + lastName;
	}

//	@Embedded
//	@IndexedEmbedded
//	public Address getAddress() {
//		return address;
//	}

	@ManyToMany(fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * Convert user roles to LabelValue objects for convenience.
	 * 
	 * @return a list of LabelValue objects with role information
	 */
	@Transient
	public List<LabelValue> getRoleList() {
		List<LabelValue> userRoles = new ArrayList<LabelValue>();

		if (this.roles != null) {
			for (Role role : roles) {
				// convert the user's roles to LabelValue Objects
				userRoles.add(new LabelValue(role.getName(), role.getName()));
			}
		}

		return userRoles;
	}

	/**
	 * Adds a role for the user
	 * 
	 * @param role
	 *            the fully instantiated role
	 */
	public void addRole(Role role) {
		getRoles().add(role);
	}

	/**
	 * @return GrantedAuthority[] an array of roles.
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Transient
	public Set<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new LinkedHashSet<GrantedAuthority>();
		authorities.addAll(roles);
		return authorities;
	}

	@Version
	public Integer getVersion() {
		return version;
	}

	@Column(name = "account_enabled")
	public boolean isEnabled() {
		return enabled;
	}

	@Column(name = "account_expired", nullable = false)
	public boolean isAccountExpired() {
		return accountExpired;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 * @return true if account is still active
	 */
	@Transient
	public boolean isAccountNonExpired() {
		return !isAccountExpired();
	}

	@Column(name = "account_locked", nullable = false)
	public boolean isAccountLocked() {
		return accountLocked;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 * @return false if account is locked
	 */
	@Transient
	public boolean isAccountNonLocked() {
		return !isAccountLocked();
	}

	@Column(name = "credentials_expired", nullable = false)
	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 * @return true if credentials haven't expired
	 */
	@Transient
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}
	

	@Column(name = "position", nullable = true)
	@Field
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "height", nullable = true)
	@Field
	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	@Column(name = "weight", nullable = true)
	@Field
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@Column(name = "dob", nullable = true)
	@Field
	public String getDob() {
		return dob;
	}
	
	
	@Column(name = "fbprofileUrl", nullable = true, updatable = false, insertable = false)
	@Field
	public String getFbprofileUrl() {
		if(fbprofileUrl == null) {
			return Constants.AppUrl + "/all-players-ViewPlayerDetail?username="+this.username;
		}
		return fbprofileUrl;
	}

	public void setFbprofileUrl(String fbprofileUrl) {
		this.fbprofileUrl = fbprofileUrl;
	}
	@Column(name = "fbimageUrl", nullable = true)
	@Field
	public String getFbimageUrl() {
		if(fbimageUrl == null) {
			return "https://dl.dropboxusercontent.com/u/66639390/7bongda/img/-Yty9nvdyik.png";
		}
		return fbimageUrl;
	}

	public void setFbimageUrl(String fbimageUrl) {
		this.fbimageUrl = fbimageUrl;
	}
	@Column(name = "fbdisplayName", nullable = true)
	@Field
	public String getFbdisplayName() {
		if(fbdisplayName == null) {
			return firstName + ' ' + lastName;
		}
		return fbdisplayName;
	}

	public void setFbdisplayName(String fbdisplayName) {
		this.fbdisplayName = fbdisplayName;
	}

	public static void main(String[] arg){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1001);
		cal.set(Calendar.MONTH, 1001);
		cal.set(Calendar.YEAR, 1001);
		Date dob2 = cal.getTime();
		System.out.println(dob2);
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/*public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}*/

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

//	public void setAddress(Address address) {
//		this.address = address;
//	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}
	


	/*@ManyToOne
	@JoinTable(name = "team", joinColumns = { 
			@JoinColumn(name = "userid", nullable = true, updatable = true) }, 
			inverseJoinColumns = { @JoinColumn(name = "teamid", 
					nullable = true, updatable = true) })*/
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="teamid", updatable = true, insertable = true)
	public Team getTeam() {//TESTED
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String getTeamRole() {
		return teamRole;
	}

	public void setTeamRole(String teamRole) {
		this.teamRole = teamRole;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof User)) {
			return false;
		}

		final User user = (User) o;

		return !(username != null ? !username.equals(user.getUsername()) : user.getUsername() != null);

	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return (username != null ? username.hashCode() : 0);
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append("username", this.username).append("enabled", this.enabled)
				.append("accountExpired", this.accountExpired).append("credentialsExpired", this.credentialsExpired)
				.append("accountLocked", this.accountLocked);

		if (roles != null) {
			sb.append("Granted Authorities: ");

			int i = 0;
			for (Role role : roles) {
				if (i > 0) {
					sb.append(", ");
				}
				sb.append(role.toString());
				i++;
			}
		} else {
			sb.append("No Granted Authorities");
		}
		
		
		return sb.toString();
	}

	public static User fromProviderUser(UserProfile fetchUserProfile) {
		User form = new User();
		form.setFirstName(fetchUserProfile.getFirstName());
		form.setLastName(fetchUserProfile.getLastName());
		form.setUsername(fetchUserProfile.getUsername());
		form.setEmail(fetchUserProfile.getEmail());
		return form;
	}
}
