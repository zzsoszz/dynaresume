/*****************************************************************************
 * Copyright (c) 2009
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo Zerr <angelo.zerr@gmail.com>
 *     Jawher Moussa <jawher.moussa@gmail.com>
 *     Nicolas Inchauspe <nicolas.inchauspe@gmail.com>
 *     Pascal Leclercq <pascal.leclercq@gmail.com>
 *******************************************************************************/
package org.dynaresume.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.dynaresume.basebean.BaseBean;
@Entity
@Table(name = "T_ADDRESS",schema="core")
 
public class Address extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9217187221005720810L;
	@Id
	 @GeneratedValue 
	private Long id;
	@Column
	private String zipCode;
	@Column
	private String city;
	@Column
	private String fax;
	@Column
	private String telephone;
	
	@ManyToOne
	private Country country;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		Object oldValue = this.id;
		this.id = id;
		firePropertyChange("id", oldValue, id);
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		Object oldValue = this.zipCode;
		this.zipCode = zipCode;
		firePropertyChange("zipCode", oldValue, zipCode);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		Object oldValue = this.city;
		this.city = city;
		firePropertyChange("city", oldValue, city);
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		Object oldValue = this.fax;
		this.fax = fax;
		firePropertyChange("fax", oldValue, fax);
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		Object oldValue = this.telephone;
		this.telephone = telephone;
		firePropertyChange("telephone", oldValue, telephone);
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		Object oldValue = this.country;
		this.country = country;
		firePropertyChange("country", oldValue, country);
	}


	
}
