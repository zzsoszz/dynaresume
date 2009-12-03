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
import org.dynaresume.common.domain.Country;
import org.dynaresume.common.domain.Group;
import org.dynaresume.common.repository.AgencyRepository;
import org.dynaresume.common.repository.CountryRepository;
import org.dynaresume.common.repository.GroupRepository;
import org.dynaresume.common.service.AgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * Description : Services which manage Agence, TableReference
 * 
 * @version 1.0.0
 * @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
 * 
 */
@Transactional(readOnly=true)
@Service("agenceService")
public class AgenceServiceImpl implements AgenceService {


	@Autowired
	private AgencyRepository agencyRepository;
	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private CountryRepository countryRepository;


	public List<Agency> getAllAgencies() {

		return agencyRepository.findAll();
	}


	public List<Group> getAllGroups() {
		return  groupRepository.findAll();
	}

	@Transactional(readOnly = false)
	public Group createGroup(Group newGroup) {
		return groupRepository.save(newGroup);

	}

	public List<Agency> getAgenciesForGroup(Group group) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Country> getAllCountries() {
		return countryRepository.findAll();
	}
}
