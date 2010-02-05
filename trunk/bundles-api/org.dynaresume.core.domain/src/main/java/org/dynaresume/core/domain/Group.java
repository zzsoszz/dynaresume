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

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.dynaresume.basebean.BaseBean;

@Entity
@Table(name = "T_GROUP", schema = "core")
public class Group extends BaseBean {
	@Id
	 @GeneratedValue 
	private Long id;
	
	@Column
	private String name;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7884537382593661072L;
	@OneToMany(mappedBy="group",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Agency> subsidiaries;

	public Set<Agency> getSubsidiaries() {
		return subsidiaries;
	}

	public void setSubsidiaries(Set<Agency> subsidiaries) {
		Object oldValue = this.subsidiaries;
		this.subsidiaries = subsidiaries;
		firePropertyChange("subsidiaries", oldValue, subsidiaries);
	}

	@Override
	public Serializable getId() {
		
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		Object oldValue = this.name;
		this.name = name;
		firePropertyChange("name", oldValue, name);
	}

	public void setId(Long id) {
		
		this.id = id;
		
	}
	
	
}
