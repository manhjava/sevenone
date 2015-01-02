/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sevenone.sevenfb.webapp.social;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;

//@Entity
@Table(name = "app_user")
public class User {

	private long id;
	
	private String username;

	private String password;

	private String firstName;

	private String lastName;
	
	/**
     * Default constructor - creates a new instance with no values set.
     */
    public User() {
    }

	public User(String username, String password, String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Column(nullable = false, length = 50, unique = true)
    @Field
	public String getUsername() {
		return username;
	}
	@Column(nullable = false)
    @XmlTransient
	public String getPassword() {
		return password;
	}

	@Column(name = "firstName", nullable = false, length = 50)
    @Field
	public String getFirstName() {
		return firstName;
	}

	@Column(name = "lastName", nullable = false, length = 50)
    @Field
	public String getLastName() {
		return lastName;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DocumentId
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
