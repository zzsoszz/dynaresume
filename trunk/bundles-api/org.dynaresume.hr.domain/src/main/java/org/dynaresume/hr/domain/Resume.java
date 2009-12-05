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
package org.dynaresume.hr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dynaresume.basebean.BaseBean;
import org.dynaresume.common.domain.NaturalPerson;

@Entity
@Table(name = "T_RESUME",schema="hr")
public class Resume extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7407392831377640438L;


	@Id
	private long id;

	
	@Column
	private String title;
	
	@Column
	private byte[] picture;

	@Column(name="owner_id",unique=true)
	private Long ownerId;

	@Transient
	private NaturalPerson owner;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		Object oldValue = this.title;
		this.title = title;
		firePropertyChange("title", oldValue, title);
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		Object oldValue = this.picture;
		this.picture = picture;
		firePropertyChange("picture", oldValue, picture);
	}

	public NaturalPerson getOwner() {
		return owner;
	}

	public void setOwner(NaturalPerson owner) {
		Object oldValue = this.owner;
		this.owner = owner;
		firePropertyChange("owner", oldValue, owner);
	}

	public long getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(Long ownerId) {
		Object oldValue = this.ownerId;
		this.ownerId = ownerId;
		firePropertyChange("ownerId", oldValue, ownerId);
	}
	
	
	
}
