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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.dynaresume.basebean.BaseBean;
@Entity
@Table(name = "T_COUNTRY",schema="common")
public class Country extends BaseBean{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1896189210454791831L;
	@Id
	private String iso3;
	public String getIso3() {
		return iso3;
	}
	public void setIso3(String iso3) {
		Object oldValue = this.iso3;
		this.iso3 = iso3;
		firePropertyChange("iso3", oldValue, iso3);
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		Object oldValue = this.label;
		this.label = label;
		firePropertyChange("label", oldValue, label);
	}
	@Column
	private String label;
}
