package org.dynaresume.common.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "T_AGENCY",schema="common")
public class Agency extends LegalEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6480261025176940528L;
	
	

	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="manager_id")
	private NaturalPerson manager;
	@OneToMany
	@JoinColumn(name="agency_id")
	private Set<LegalEntity> clients;
	@OneToMany(mappedBy="agency")
	private Set<Collaboration> collaborators;
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
	
	
}