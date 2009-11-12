/*****************************************************************************
 * Copyright (c) 2009
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo Zerr <angelo.zerr@gmail.com>
 *     Pascal Leclercq <pascal.leclercq@gmail.com>
 *******************************************************************************/
package org.dynaresume.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.dynaresume.domain.BaseBean;
import org.dynaresume.domain.Collaborateur;

@Entity
@Table(name = "T_AGENCE")
public class Agence extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3303011457766085882L;
	@Id
	@Column(name = "AGC_ID_N")
	private Integer id;
	@Column(name="AGC_RAISONSOCIALE_C", length=50 )
	private String raisonSociale;
	@Column(name="AGC_ADRESSE1_C", length=50)
	private String adresse1;
	@Column(name="AGC_ADRESSE2_C",length=50)
	private String adresse2;
	@Column(name="AGC_CODEPOSTAL_C",length=6)
	private String codePostal;
	@Column(name="AGC_VILLE_C",length=100)
	private String ville;
	@Column(name="AGC_TELEPHONE_C",length=10)
	private String telephone;
	@Column(name="AGC_FAX_C",length=10)
	private String fax;
	@OneToMany(mappedBy="agence")
	private Set<Collaborateur> collaborateurs = new HashSet<Collaborateur>();

	
	public String getAdresse1() {
		return adresse1;
	}

	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}
	
	public String getAdresse2() {
		return adresse2;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getRaisonSociale() {
		return raisonSociale;
	}

	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Set<Collaborateur> getCollaborateurs() {
		return collaborateurs;
	}

	public void setCollaborateurs(Set<Collaborateur> collaborateurs) {
		this.collaborateurs = collaborateurs;
	}

	
	
}
