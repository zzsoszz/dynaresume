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

package org.akrogen.dynaresume.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.akrogen.dynaresume.dto.criteria.CompetenceCriteria;
import org.akrogen.dynaresume.repository.ICompetenceDAO;
import org.akrogen.dynaresume.service.ICompetenceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("competenceService")
@Transactional(readOnly=true)
public class CompetenceServiceImpl implements ICompetenceService {
	@Resource
	private ICompetenceDAO competenceDAO;


	public Collection findCompetencesByNomLike(String nom) {
		CompetenceCriteria criteria = new CompetenceCriteria();
		criteria.setNomLike(nom);
		Collection compentenceBeanList = competenceDAO.findByCriteria(criteria);
		return compentenceBeanList;
	}

}
