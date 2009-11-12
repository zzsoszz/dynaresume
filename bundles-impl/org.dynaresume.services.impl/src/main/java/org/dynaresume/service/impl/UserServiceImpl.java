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

package org.dynaresume.service.impl;

import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Resource;

import org.commons.exception.CommonsErrorException;
import org.dynaresume.domain.Collaborateur;
import org.dynaresume.domain.User;
import org.dynaresume.dto.criteria.CollaborateurCompetenceCriteria;
import org.dynaresume.dto.criteria.CollaborateurCriteria;
import org.dynaresume.dto.criteria.CollaborateurFormationCriteria;
import org.dynaresume.dto.criteria.UserCriteria;
import org.dynaresume.repository.ICollaborateurCompetenceDAO;
import org.dynaresume.repository.ICollaborateurDAO;
import org.dynaresume.repository.ICollaborateurFormationDAO;
import org.dynaresume.repository.IUserDAO;
import org.dynaresume.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * Description : User services for collaborateur, user and rubriques
 * associated to Collaborateur.
 * @version 1.0.0 
 * @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
 *
 */
@Service("userService")
@Transactional(readOnly=true)
public class UserServiceImpl implements IUserService {

	@Resource
	public IUserDAO userDAO;
	@Resource
	public ICollaborateurDAO collaborateurDAO;
	@Resource
	public ICollaborateurCompetenceDAO collaborateurCompetenceDAO;
	@Resource
	public ICollaborateurFormationDAO collaborateurFormationDAO;
	/// COLLABORATEUR
	@Transactional(readOnly=false)
	public Integer createCollaborateur(Collaborateur collaborateur) throws CommonsErrorException {
		Collaborateur collaborateurBean = collaborateur;
		collaborateurDAO.create(collaborateurBean);
		return collaborateurBean.getId();
	}
	@Transactional(readOnly=false)
	public void updateCollaborateur(Collaborateur collaborateur) throws CommonsErrorException {
		System.out.println(collaborateur);
		Collaborateur collaborateurBean = collaborateur;
		collaborateurDAO.update(collaborateurBean);
		
		//PLQ : fake rollback
		//throw new RuntimeException("fake rollback");
	}
	
	public Collaborateur findColloborateurByPrimaryKey(Integer id) {
		Collaborateur collaborateurBean = (Collaborateur)collaborateurDAO.findByPrimaryKey(Collaborateur.class, id);
		return collaborateurBean;
	}
	
	public Collection findCollaborateurByCriteria(CollaborateurCriteria criteria) {
		Collection collaborateurBeanList =  collaborateurDAO.findByCriteria(criteria);
		return collaborateurBeanList;
	}

	public void findCollaborateurByCriteria(CollaborateurCriteria criteria, Collection paginatedList) {
//		IPage page = collaborateurDAO.findPageByCriteria(criteria, paginatedList.getSort(), paginatedList.getPageNumber() - 1, paginatedList.getObjectsPerPage() );
//		UserAssembleur.parseCollaborateurBeanToDTO(page);
//		paginatedList.update(page);
	}
	
	public void findAllCollaborateur(Collection paginatedList) {
//		IPage page = collaborateurDAO.findAll(paginatedList.getSort(), paginatedList.getPageNumber() - 1, paginatedList.getObjectsPerPage() );
//		UserAssembleur.parseCollaborateurBeanToDTO(page);
//		paginatedList.update(page);
	}
	
	public void findAllCollaborateurCompetencesByCriteria(CollaborateurCompetenceCriteria criteria, Collection paginatedList) {
//		IPage page = collaborateurCompetenceDAO.findPageByCriteria(criteria, paginatedList.getSort(), paginatedList.getPageNumber() - 1, paginatedList.getObjectsPerPage() );
//		RubriqueAssembleur.parseCollaborateurCompetenceBeanToDTO(page);
//		paginatedList.update(page);
	}
	
	public User findUserByLogin(String login) {
		UserCriteria criteria = new UserCriteria();
		criteria.setLogin(login);
		User userBean =  (User)userDAO.findFirstByCriteria(criteria);
		return userBean;
	}
	
	// ------------ Collaborateur Formation
	
	public Collection findAllCollaborateurFormationByCollaborateurId(Integer idCollaborateur) {
		CollaborateurFormationCriteria criteria = new CollaborateurFormationCriteria();
		criteria.setIdCollaborateur(idCollaborateur);
		Collection collaborateurFormationBeanList =  collaborateurFormationDAO.findByCriteria(criteria);
		return collaborateurFormationBeanList;
		
	}

	
	public Collection findAllCollaborateur() {
	System.out.println("titi");
		Collection collaborateurs= collaborateurDAO.findAll();
		
		
		for (Iterator iterator = collaborateurs.iterator(); iterator.hasNext();) {
			Collaborateur object = (Collaborateur) iterator.next();
			System.out.println(object.getEmail());
			System.out.println(object.getAgence().getRaisonSociale());
		}
		return collaborateurs;
	}
	

}
