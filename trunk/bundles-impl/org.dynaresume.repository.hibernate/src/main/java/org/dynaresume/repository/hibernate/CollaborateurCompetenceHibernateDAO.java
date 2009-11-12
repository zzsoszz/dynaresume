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

package org.dynaresume.repository.hibernate;

import java.util.List;

import org.dynaresume.repository.hibernate.AbstractDynaResumeDAO;
import org.dynaresume.domain.CollaborateurCompetence;
import org.dynaresume.dto.criteria.BaseCriteria;
import org.dynaresume.dto.criteria.CollaborateurCompetenceCriteria;
import org.dynaresume.repository.ICollaborateurCompetenceDAO;
import org.springframework.stereotype.Repository;

@Repository("collaborateurCompetenceDAO")
public class CollaborateurCompetenceHibernateDAO<T> extends AbstractDynaResumeDAO<T> implements ICollaborateurCompetenceDAO<T> {

	protected String getHQLQuery(BaseCriteria c) {
		CollaborateurCompetenceCriteria criteria = (CollaborateurCompetenceCriteria)c;		
		// Prepare HQL Query
		StringBuffer query = new StringBuffer("");
		query.append("from CollaborateurCompetence as c");
		if (criteria != null) {
			Integer idCollaborateur = criteria.getIdCollaborateur();
			// WHERE
			query.append(" where 1=1 ");	
			if (idCollaborateur != null)
				query.append(" and c.idCollaborateur =:idCollaborateur ");
									
		}
		return query.toString();
	}


	protected String getHQLAlias(String field) {
		return "c";
	}


	public List findAll() {
		// TODO Auto-generated method stub
		return super.findAll(CollaborateurCompetence.class);
	}
}
