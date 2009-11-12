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

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class BaseBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4621270018596086154L;
	@Column(name="UTILISATEUR_CREATION",length=50,updatable=false)
	private String utilisateurCreation;
	@Column(name="DATE_CREATION",length=23,updatable=false)
	private Date dateCreation;
	@Column(name="UTILISATEUR_MODIFICATION",length=50)
	private String utilisateurModification;
	@Column(name="DATE_MODIFICATION",length=23)
	private Date dateModification;
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Date getDateModification() {
		return dateModification;
	}
	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}
	public String getUtilisateurCreation() {
		return utilisateurCreation;
	}
	public void setUtilisateurCreation(String utilisateurCreation) {
		this.utilisateurCreation = utilisateurCreation;
	}
	public String getUtilisateurModification() {
		return utilisateurModification;
	}
	public void setUtilisateurModification(String utilisateurModification) {
		this.utilisateurModification = utilisateurModification;
	}
	
	
}
