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

package org.dynaresume.common.service;

import java.util.List;

import org.dynaresume.common.domain.Agency;
import org.dynaresume.common.domain.Group;

//import org.commons.dao.Sort;


public interface AgenceService {

	// ------------ Agence
	public List<Agency> findAllAgence();
	
	
	public Group  createGroup(Group newGroup);
	/**
	 * @return
	 */
//	public Collection findAllAgence(Sort sort);
	
	//public void findAllAgence(PaginatedListImpl paginatedAgenceList);
//	public void findAllAgence(Collection paginatedAgenceList);
	// ------------ Table Reference
//	public Collection findAllSituationFamiliale();
//	
//	// ------------ Modele RTF
//	public Collection findAllModeleRTF();
	
}
