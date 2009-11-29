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
import java.util.List;

import org.dynaresume.domain.Agency;
import org.dynaresume.dto.criteria.TableReferenceCriteria;
import org.dynaresume.repository.AgencyRepository;
import org.dynaresume.service.AgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * Description : Services which manage Agence, TableReference 
 * @version 1.0.0 
 * @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
 *
 */
@Transactional(readOnly=true)
@Service("agenceService")
public class AgenceServiceImpl  implements AgenceService {

	//public static final String TREF_TYPE_SITUATION_FAMILIALE = "SITUATION_FAMILIALE";
	
	@Autowired
	private AgencyRepository agencyRepository;
//	@Resource
//	private ITableReferenceDAO tableReferenceDAO;
//	@Resource
//	private IModeleRTFDAO modeleRTFDAO;
//	
	public List<Agency> findAllAgence() {
		
		return agencyRepository.findAll();
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
