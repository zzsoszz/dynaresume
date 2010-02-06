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

package org.dynaresume.common.service.impl;

import java.util.List;

import org.dynaresume.common.domain.Agency;
import org.dynaresume.common.domain.Country;
import org.dynaresume.common.domain.Group;
import org.dynaresume.common.repository.AgencyRepository;
import org.dynaresume.common.repository.CountryRepository;
import org.dynaresume.common.repository.GroupRepository;
import org.dynaresume.common.service.AgenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(AgenceServiceImpl.class);


	@Autowired
	private AgencyRepository agencyRepository;
	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private CountryRepository countryRepository;


	public List<Agency> getAllAgencies() {
		logger.debug("getAllAgencies() - start"); //$NON-NLS-1$

		List<Agency> returnList = agencyRepository.findAll();
		logger.debug("getAllAgencies() - end"); //$NON-NLS-1$
		return returnList;
	}


	public List<Group> getAllGroups() {
		logger.debug("getAllGroups() - start"); //$NON-NLS-1$

		List<Group> returnList = groupRepository.findAll();
		logger.debug("getAllGroups() - end"); //$NON-NLS-1$
		return  returnList;
	}

	@Transactional(readOnly = false)
	public Group createGroup(Group newGroup) {
		logger.debug("createGroup(Group) - start"); //$NON-NLS-1$

		Group returnGroup = groupRepository.save(newGroup);
		logger.debug("createGroup(Group) - end"); //$NON-NLS-1$
		return returnGroup;

	}

	public List<Agency> getAgenciesForGroup(Group group) {
		logger.debug("getAgenciesForGroup(Group) - start"); //$NON-NLS-1$

		// TODO Auto-generated method stub

		logger.debug("getAgenciesForGroup(Group) - end"); //$NON-NLS-1$
		return null;
	}

	public List<Country> getAllCountries() {
		logger.debug("getAllCountries() - start"); //$NON-NLS-1$

		List<Country> returnList = countryRepository.findAll();
		logger.debug("getAllCountries() - end"); //$NON-NLS-1$
		return returnList;
	}
}
