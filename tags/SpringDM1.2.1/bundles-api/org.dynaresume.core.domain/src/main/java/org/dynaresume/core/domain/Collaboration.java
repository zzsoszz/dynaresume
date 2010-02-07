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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.dynaresume.basebean.BaseBean;

@Entity
@Table(name = "T_COLLABORATION", schema = "core")

public class Collaboration extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	// @Id
	// private long projectId;

	@Column
	private Date startDate;
	@Column
	private Date endDate;

	@OneToOne(fetch=FetchType.EAGER)
    @PrimaryKeyJoinColumn
	private NaturalPerson employee;
	@ManyToOne(fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn(referencedColumnName = "ID")
	private Agency agency;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		Object oldValue = this.id;
		this.id = id;
		firePropertyChange("id", oldValue, id);
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

	public NaturalPerson getEmployee() {
		return employee;
	}

	public void setEmployee(NaturalPerson employee) {
		Object oldValue = this.employee;
		this.employee = employee;
		firePropertyChange("employee", oldValue, employee);
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		Object oldValue = this.agency;
		this.agency = agency;
		firePropertyChange("agency", oldValue, agency);
	}

}
