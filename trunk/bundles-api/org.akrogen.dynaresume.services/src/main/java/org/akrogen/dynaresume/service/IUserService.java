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

package org.akrogen.dynaresume.service;

import java.util.Collection;

import org.akrogen.commons.exception.CommonsErrorException;
import org.akrogen.dynaresume.domain.Collaborateur;
import org.akrogen.dynaresume.domain.User;
import org.akrogen.dynaresume.dto.criteria.CollaborateurCriteria;



public interface IUserService {

	public User findUserByLogin(String login);
	
/// COLLABORATEUR
	
	public Integer createCollaborateur(Collaborateur collaborateur) throws CommonsErrorException;
	public void updateCollaborateur(Collaborateur collaborateur) throws CommonsErrorException;
	
	public Collaborateur findColloborateurByPrimaryKey(Integer id);
	
	public Collection findCollaborateurByCriteria(CollaborateurCriteria criteria);
//	public void findCollaborateurByCriteria(CollaborateurCriteria criteria, PaginatedListImpl paginatedList);
//	public void findAllCollaborateur(PaginatedListImpl paginatedCollaborateurList);

//	public void findCollaborateurByCriteria(CollaborateurCriteria criteria, Collection paginatedList);
	public void findAllCollaborateur(Collection paginatedCollaborateurList);
	public Collection findAllCollaborateur();

	//public void findAllCollaborateurCompetencesByCriteria(CollaborateurCompetenceCriteria criteria, PaginatedListImpl paginatedCollaborateurList);
//	public void findAllCollaborateurCompetencesByCriteria(CollaborateurCompetenceCriteria criteria, Collection paginatedCollaborateurList);
	
	public Collection findAllCollaborateurFormationByCollaborateurId(Integer idCollaborateur);
}
