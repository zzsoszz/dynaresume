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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.dynaresume.basebean.BaseBean;
@Entity
@Table(name = "T_COLLABORATION",schema="common")
public class Collaboration extends BaseBean {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	  private long id;
//	  @Id
//	  private long projectId;
	
	  @Column
	private Date start;
	  @Column
	private Date end;
	
	

	  
	  @ManyToOne
	  @PrimaryKeyJoinColumn(referencedColumnName="ID")
	  private NaturalPerson employee;
	  @ManyToOne
	  @PrimaryKeyJoinColumn( referencedColumnName="ID")
	  private Agency agency;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		Object oldValue = this.id;
		this.id = id;
		firePropertyChange("id", oldValue, id);
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
