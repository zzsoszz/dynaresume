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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "T_LEGAL_ENTITY",schema="core")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class LegalEntity extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8975049737136593445L;
	@Id
	 @GeneratedValue 
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		Object oldValue = this.id;
		this.id = id;
		firePropertyChange("id", oldValue, id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		Object oldValue = this.name;
		this.name = name;
		firePropertyChange("name", oldValue, name);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		Object oldValue = this.code;
		this.code = code;
		firePropertyChange("code", oldValue, code);
	}

	
}
