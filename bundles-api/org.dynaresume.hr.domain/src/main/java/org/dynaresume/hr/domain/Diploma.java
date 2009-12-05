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

import org.dynaresume.basebean.BaseBean;
@Entity
@Table(name = "T_DIPLOMA",schema="hr")
public class Diploma extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2452995207655152758L;
	@Id
	private long id;

	
	
	@Column
	private String institute;

	@Column
	private String label;

	@Column
	private String period;
	
	public long getId() {
		return id;
	}
	
	public String getInstitute() {
		return institute;
	}

	public String getLabel() {
		return label;
	}

	public String getPeriod() {
		return period;
	}

	public void setId(long id) {
		
		this.id = id;
		
	}

	public void setInstitute(String institute) {
		Object oldValue = this.institute;
		this.institute = institute;
		firePropertyChange("institute", oldValue, institute);
	}

	public void setLabel(String label) {
		Object oldValue = this.label;
		this.label = label;
		firePropertyChange("label", oldValue, label);
	}

	public void setPeriod(String period) {
		Object oldValue = this.period;
		this.period = period;
		firePropertyChange("period", oldValue, period);
	}
	
	
	
}
