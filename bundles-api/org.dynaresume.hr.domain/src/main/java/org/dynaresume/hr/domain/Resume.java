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

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dynaresume.basebean.BaseBean;
import org.dynaresume.core.domain.NaturalPerson;

@Entity
@Table(name = "T_RESUME", schema = "hr")
public class Resume extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7407392831377640438L;

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String title;

	@Column
	private byte[] picture;

	@Column(name = "owner_id", unique = true)
	private Long ownerId;

	@Transient
	private NaturalPerson owner;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="resume_fk")
	private Set<Experience> experiences;

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="resume_fk")
	private Set<Diploma> diplomas;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="resume_fk")
	private Set<Competence> competences;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		Object oldValue = this.title;
		this.title = title;
		firePropertyChange("title", oldValue, title);
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		Object oldValue = this.picture;
		this.picture = picture;
		firePropertyChange("picture", oldValue, picture);
	}

	public NaturalPerson getOwner() {
		return owner;
	}

	public void setOwner(NaturalPerson owner) {
		Object oldValue = this.owner;
		this.owner = owner;
		firePropertyChange("owner", oldValue, owner);
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		Object oldValue = this.ownerId;
		this.ownerId = ownerId;
		firePropertyChange("ownerId", oldValue, ownerId);
	}

	public Set<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(Set<Experience> experiences) {
		Object oldValue = this.experiences;
		this.experiences = experiences;
		firePropertyChange("experiences", oldValue, experiences);
	}

	public Set<Diploma> getDiplomas() {
		return diplomas;
	}

	public void setDiplomas(Set<Diploma> diplomas) {
		Object oldValue = this.diplomas;
		this.diplomas = diplomas;
		firePropertyChange("diplomas", oldValue, diplomas);
	}

	public Set<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(Set<Competence> competences) {
		Object oldValue = this.competences;
		this.competences = competences;
		firePropertyChange("competences", oldValue, competences);
	}

}
