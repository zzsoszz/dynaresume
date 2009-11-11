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

package org.akrogen.dynaresume.repository.hibernate;

import java.util.List;

import org.akrogen.dynaresume.domain.Competence;
import org.akrogen.dynaresume.dto.criteria.BaseCriteria;
import org.akrogen.dynaresume.dto.criteria.CompetenceCriteria;
import org.akrogen.dynaresume.repository.ICompetenceDAO;
import org.springframework.stereotype.Repository;

/**
 * 
 * Description : Competence Hibernate DAO.
 * @version 1.0.0 
 * @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
 *
 */
@Repository("competenceDAO")
public class CompetenceHibernateDAO extends AbstractDynaResumeDAO implements ICompetenceDAO {

	protected String getHQLQuery(BaseCriteria c) {
		CompetenceCriteria criteria = (CompetenceCriteria)c;		
		// Prepare HQL Query
		StringBuffer query = new StringBuffer("");
		query.append("from Competence as c");
		if (criteria != null) {
			String nomLike = criteria.getNomLike();
			// WHERE
			query.append(" where 1=1 ");	
			if (nomLike != null)
				query.append(" and c.nom like :nomLike ");
									
		}
		return query.toString();
	}

	protected String getHQLAlias(String field) {
		return "c";
	}
	public List<Competence> findAll() {
		// TODO Auto-generated method stub
		return super.findAll(Competence.class);
	}
}
