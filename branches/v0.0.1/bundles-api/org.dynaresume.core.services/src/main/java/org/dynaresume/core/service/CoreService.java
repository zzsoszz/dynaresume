/*****************************************************************************
 * Copyright (c) 2009
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo Zerr <angelo.zerr@gmail.com>
 *     Jawher Moussa <jawher.moussa@gmail.com>
 *     Nicolas Inchauspe <nicolas.inchauspe@gmail.com>
 *     Pascal Leclercq <pascal.leclercq@gmail.com>
 *******************************************************************************/
package org.dynaresume.core.service;

import java.util.List;

import org.dynaresume.core.domain.Agency;
import org.dynaresume.core.domain.Collaboration;
import org.dynaresume.core.domain.Country;
import org.dynaresume.core.domain.Group;
import org.dynaresume.core.domain.NaturalPerson;

public interface CoreService {

	Group saveGroup(Group newGroup);

	void deleteGroup(Group aGroup);

	List<Group> getAllGroups();

	List<Country> getAllCountries();

	Collaboration getCollaborationByID(Long id);

	List<Agency> getAgenciesForGroup(Group group);

	List<Collaboration> getCollaborationsForAgency(Agency agency);

	NaturalPerson saveNaturalPerson(NaturalPerson naturalPerson);

	void deleteNaturalPerson(NaturalPerson naturalPerson);

	Agency saveAgency(Agency agency);

	void deleteAgency(Agency agency);

	Collaboration saveCollaboration(Collaboration collaboration);

	void deleteCollaboration(Collaboration collaboration);

}
