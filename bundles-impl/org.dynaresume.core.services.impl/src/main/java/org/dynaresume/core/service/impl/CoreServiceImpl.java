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

package org.dynaresume.core.service.impl;

import java.util.List;

import org.dynaresume.core.domain.Agency;
import org.dynaresume.core.domain.Collaboration;
import org.dynaresume.core.domain.Country;
import org.dynaresume.core.domain.Group;
import org.dynaresume.core.domain.NaturalPerson;
import org.dynaresume.core.repository.AgencyRepository;
import org.dynaresume.core.repository.CollaborationRepository;
import org.dynaresume.core.repository.CountryRepository;
import org.dynaresume.core.repository.GroupRepository;
import org.dynaresume.core.repository.NaturalPersonRepository;
import org.dynaresume.core.service.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * Description : Services which manage Agency, NaturalPerson, Collaboration
 * 
 * 
 */
@Transactional(readOnly = true)
@Service("coreService")
public class CoreServiceImpl implements CoreService {
	
	@Autowired
	private AgencyRepository agencyRepository;
	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private NaturalPersonRepository naturalPersonRepository;

	@Autowired
	private CollaborationRepository collaborationRepository;

	public List<Agency> getAllAgencies() {

		
		
		List<Agency> returnList = agencyRepository.findAll();

		return returnList;
	}

	public List<Group> getAllGroups() {
//   
	//	List<Group> returnList = groupRepository.findByQuery("from  Group a  join a.subsidiaries", null, null);
		List<Group> returnList =groupRepository.findAll();

		return returnList;
	}

	@Transactional(readOnly = false)
	public Group saveGroup(Group newGroup) {

		Group returnGroup = groupRepository.save(newGroup);

		return returnGroup;

	}

	public List<Agency> getAgenciesForGroup(Group group) {

		List<Agency> res = agencyRepository.findByQuery("select a from Agency a where a.group=:group", "group", group);

		return res;
	}

	public List<Country> getAllCountries() {

		List<Country> returnList = countryRepository.findAll();

		return returnList;
	}

	@Transactional(readOnly = false)
	public void deleteGroup(Group aGroup) {
		groupRepository.delete(aGroup);
	}

	public Collaboration getCollaborationByID(Long id) {

		return collaborationRepository.findById(id);
	}

	@Transactional(readOnly = false)
	public NaturalPerson saveNaturalPerson(NaturalPerson naturalPerson) {
		return naturalPersonRepository.save(naturalPerson);
	}

	@Transactional(readOnly = false)
	public void deleteNaturalPerson(NaturalPerson naturalPerson) {
		naturalPersonRepository.delete(naturalPerson);
	}

	public List<Collaboration> getCollaborationsForAgency(Agency agency) {
		List<Collaboration> res = collaborationRepository.findByQuery(
				"select c from  Collaboration c where c.agency=:agency ", "agency", agency);
		return res;
	}

	@Transactional(readOnly = false)
	public void deleteAgency(Agency agency) {
		agencyRepository.delete(agency);

	}

	@Transactional(readOnly = false)
	public void deleteCollaboration(Collaboration collaboration) {
		collaborationRepository.delete(collaboration);

	}

	@Transactional(readOnly = false)
	public Agency saveAgency(Agency agency) {
		return agencyRepository.save(agency);
	}

	@Transactional(readOnly = false)
	public Collaboration saveCollaboration(Collaboration collaboration) {

		return collaborationRepository.save(collaboration);
	}
}
