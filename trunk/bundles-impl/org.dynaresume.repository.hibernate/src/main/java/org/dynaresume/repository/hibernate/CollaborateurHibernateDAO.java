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
import org.dynaresume.domain.Collaborateur;
import org.dynaresume.dto.criteria.BaseCriteria;
import org.dynaresume.dto.criteria.CollaborateurCriteria;
import org.dynaresume.repository.ICollaborateurDAO;
import org.springframework.stereotype.Repository;

/**
 * 
 * Description : Collaborateur Hibernate DAO. 
 * @version 1.0.0 
 * @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
 *
 */
@Repository("collaborateurDAO")
public class CollaborateurHibernateDAO extends AbstractDynaResumeDAO implements ICollaborateurDAO {


	protected String getHQLQuery(BaseCriteria c) {
		CollaborateurCriteria criteria = (CollaborateurCriteria)c;		
		// Prepare HQL Query
		StringBuffer query = new StringBuffer("");
		query.append("from Collaborateur as c");
		if (criteria != null) {
			String nom = criteria.getNom();
			String prenom = criteria.getPrenom();
			String nomLike = criteria.getNomLike();
			String prenomLike = criteria.getPrenomLike();
			Integer idAgence = criteria.getIdAgence();
			String nomCompetenceLike = criteria.getNomCompetenceLike();
			Integer idNot = criteria.getIdNot();
			// WHERE
			query.append(" where 1=1 ");
			if (nom != null)
				query.append(" and c.nom = :nom");
			if (prenom != null)
				query.append(" and c.prenom = :prenom");
			if (nomLike != null)
				query.append(" and c.nom like :nomLike");
			if (prenomLike != null)
				query.append(" and c.prenom like :prenomLike");
			if (idAgence != null)
				query.append(" and c.agence.id = :idAgence");
			if (nomCompetenceLike != null) {
				query.append(" and c.id in ");
				query.append(" (select cpt.idCollaborateur");
				query.append(" from CollaborateurCompetence cpt ");
				query.append(" where cpt.competence.nom like :nomCompetenceLike) ");
			}
			if (idNot != null) {
				query.append(" and c.id != :idNot");
			}
		}
		return query.toString();
	}
	

	
	
	protected String getHQLAlias(String field) {
		return "c";
	}
	public List findAll() {
		// TODO Auto-generated method stub
		return super.findAll(Collaborateur.class);
	}
}
