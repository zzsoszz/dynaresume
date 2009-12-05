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
	private long id;
	@Column
	private Date start;
	@Column
	private Date end;
	@Column
	private String context;
	@Column
	private String mission;

	public long getId() {
		return id;
	}

	public void setId(long id) {

		this.id = id;

	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		Object oldValue = this.start;
		this.start = start;
		firePropertyChange("start", oldValue, start);
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		Object oldValue = this.end;
		this.end = end;
		firePropertyChange("end", oldValue, end);
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
