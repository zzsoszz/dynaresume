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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.dynaresume.basebean.BaseBean;

@Entity
@Table(name = "T_EXPERIENCE", schema = "hr")
public class Experience extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8846754655040768127L;
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private Date startDate;
	@Column
	private Date endDate;
	@Column
	private String context;
	@Column
	private String mission;

	public Long getId() {
		return id;
	}

	public void setId(long id) {

		this.id = id;

	}

	

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		Object oldValue = this.startDate;
		this.startDate = startDate;
		firePropertyChange("startDate", oldValue, startDate);
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		Object oldValue = this.endDate;
		this.endDate = endDate;
		firePropertyChange("endDate", oldValue, endDate);
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		Object oldValue = this.context;
		this.context = context;
		firePropertyChange("context", oldValue, context);
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		Object oldValue = this.mission;
		this.mission = mission;
		firePropertyChange("mission", oldValue, mission);
	}
}
