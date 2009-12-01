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

package org.dynaresume.common.service.impl;

import java.util.List;

import org.dynaresume.common.domain.Agency;
import org.dynaresume.common.domain.Group;
import org.dynaresume.common.repository.AgencyRepository;
import org.dynaresume.common.repository.GroupRepository;
import org.dynaresume.common.service.AgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * Description : Services which manage Agence, TableReference 
 * @version 1.0.0 
 * @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
 *
 */
@Transactional
@Service("agenceService")
public class AgenceServiceImpl  implements AgenceService {

	//public static final String TREF_TYPE_SITUATION_FAMILIALE = "SITUATION_FAMILIALE";
	
	@Autowired
	private AgencyRepository agencyRepository;
	@Autowired
	private GroupRepository groupRepository;
//	@Resource
//	private ITableReferenceDAO tableReferenceDAO;
//	@Resource
//	private IModeleRTFDAO modeleRTFDAO;
//	
	@Transactional
	public List<Agency> findAllAgence() {
		
		return agencyRepository.findAll();
	}
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
public Group  createGroup(Group newGroup) {
		
		
			Group newGroup2= groupRepository.save(newGroup);
//			List<Group> aGroup= groupRepository.findAll();
//			newGroup2.setEmail("pascal@free.fr");
//			Group newGroup3= groupRepository.save(newGroup2);
//			System.out.println(aGroup.size());
			return newGroup2;
		
		
	}
	
//	public Collection findAllAgence(Sort sort) {
//		Collection agenceBeanList = agenceDAO.findAll(sort);
//		//return AgenceAssembleur.parseAgenceBeanToDTO(agenceBeanList);
//		return agenceBeanList;
//	}
	
//	public void findAllAgence(Collection paginatedList) {
//		//FIXME :
//		IPage page = agenceDAO.findAll(paginatedList.getSort(), paginatedList.getPageNumber() - 1, paginatedList.getObjectsPerPage() );
//		AgenceAssembleur.parseAgenceBeanToDTO(page);
//		paginatedList.update(page);
//	}

//	///////////// TABLE REFERENCE
//	
//	public Collection findAllSituationFamiliale() {
//		TableReferenceCriteria criteria = new TableReferenceCriteria();
//		criteria.setType(TREF_TYPE_SITUATION_FAMILIALE);
//		Collection tableReferenceBeanList = tableReferenceDAO.findByCriteria(criteria);
//		return tableReferenceBeanList; 
//	}
//	
//	public Collection findAllModeleRTF() {
//		Collection modeleRTFBeanList = modeleRTFDAO.findAll();
//		return modeleRTFBeanList; 
//	}
	
	


}
