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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.dynaresume.domain.BaseBean;

/**
 * Table references : 
 * => Situation (Célibataire, Marié)
 * 
 * @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
 */
@Entity
@Table(name = "T_TABLEREFERENCE")
public class TableReference extends BaseBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9085153218442286685L;
	@Id
	@Column(name = "TRF_ID_N")
	private Integer id;
	@Column(name = "TRF_TYPE_C",length=50,nullable=false)
	private String type;
	
	@Column(name = "TRF_VALEUR_C",length=50,nullable=false)
	private String valeur;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValeur() {
		return valeur;
	}
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	

}
