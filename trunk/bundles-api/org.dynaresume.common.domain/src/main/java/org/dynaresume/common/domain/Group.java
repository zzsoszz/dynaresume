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

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "T_GROUP",schema="common")
public class Group extends LegalEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7884537382593661072L;
	@OneToMany
	@JoinColumn(name="group_id")
	private Set<Agency> subsidiaries;
	public Set<Agency> getSubsidiaries() {
		return subsidiaries;
	}
	public void setSubsidiaries(Set<Agency> subsidiaries) {
		Object oldValue = this.subsidiaries;
		this.subsidiaries = subsidiaries;
		firePropertyChange("subsidiaries", oldValue, subsidiaries);
	}
}
