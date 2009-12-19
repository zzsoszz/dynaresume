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
package org.dynaresume.common.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.dynaresume.basebean.BaseBean;

@MappedSuperclass
public abstract class Person extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 298482050929739147L;
	@Column(length=100,nullable=false)
	private String email;

	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Address address;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		Object oldValue = this.email;
		this.email = email;
		firePropertyChange("email", oldValue, email);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		Object oldValue = this.address;
		this.address = address;
		firePropertyChange("address", oldValue, address);
	}

}
