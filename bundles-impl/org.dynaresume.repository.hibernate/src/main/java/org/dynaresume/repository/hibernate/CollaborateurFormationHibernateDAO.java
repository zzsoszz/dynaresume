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
import org.dynaresume.domain.CollaborateurFormation;
import org.dynaresume.dto.criteria.BaseCriteria;
import org.dynaresume.dto.criteria.CollaborateurFormationCriteria;
import org.dynaresume.repository.ICollaborateurFormationDAO;
import org.springframework.stereotype.Repository;

/**
 * 
 * Description : Competence Hibernate DAO.
 * @version 1.0.0 
 * @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
 *
 */
@Repository("collaborateurFormationDAO")
public class CollaborateurFormationHibernateDAO extends AbstractDynaResumeDAO implements ICollaborateurFormationDAO {

	protected String getHQLQuery(BaseCriteria c) {
		CollaborateurFormationCriteria criteria = (CollaborateurFormationCriteria)c;		
		// Prepare HQL Query
		StringBuffer query = new StringBuffer("");
		query.append("from CollaborateurFormation as f");
		if (criteria != null) {
			Integer idCollaborateur = criteria.getIdCollaborateur();
			// WHERE
			query.append(" where 1=1 ");	
			if (idCollaborateur != null)
				query.append(" and f.idCollaborateur = :idCollaborateur ");
									
		}
		return query.toString();
	}

	protected String getHQLAlias(String field) {
		return "f";
	}
	
	public List findAll() {
		// TODO Auto-generated method stub
		return super.findAll(CollaborateurFormation.class);
	}
}