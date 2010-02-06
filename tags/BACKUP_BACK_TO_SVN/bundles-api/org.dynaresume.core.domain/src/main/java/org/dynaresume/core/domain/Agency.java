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

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "T_AGENCY",schema="core")

public class Agency extends LegalEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6480261025176940528L;
	
	

	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="manager_id")
	private NaturalPerson manager;
	@OneToMany
	private Set<LegalEntity> clients;
	@OneToMany(mappedBy="agency")
	private Set<Collaboration> collaborators;
	@ManyToOne
	@JoinColumn(name="GROUP_ID", nullable=false)
	private Group group;
	public NaturalPerson getManager() {
		return manager;
	}
	public void setManager(NaturalPerson manager) {
		Object oldValue = this.manager;
		this.manager = manager;
		firePropertyChange("manager", oldValue, manager);
	}
	public Set<LegalEntity> getClients() {
		return clients;
	}
	public void setClients(Set<LegalEntity> clients) {
		Object oldValue = this.clients;
		this.clients = clients;
		firePropertyChange("clients", oldValue, clients);
	}
	public Set<Collaboration> getCollaborators() {
		return collaborators;
	}
	public void setCollaborators(Set<Collaboration> collaborators) {
		Object oldValue = this.collaborators;
		this.collaborators = collaborators;
		firePropertyChange("collaborators", oldValue, collaborators);
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		Object oldValue = this.group;
		this.group = group;
		firePropertyChange("group", oldValue, group);
	}
	
	
}
