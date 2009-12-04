package org.dynaresume.common.service.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.dynaresume.common.domain.Agency;
import org.dynaresume.common.domain.Group;
import org.dynaresume.common.repository.AgencyRepository;
import org.dynaresume.common.service.AgenceService;


public class Scalpa {

	
	
	

	@Resource
	private AgenceService agenceService;
	
	
	public void start(){
		System.out.println("coucou");
		
		
		
			System.err.println(agenceService.getAllGroups().size());
		Group newGroup =new Group();
		newGroup.setCode("12");
		newGroup.setEmail("scalpa12");
		
		newGroup.setName("dfsd");
		
		Group other=agenceService.createGroup(newGroup);
		
		System.out.println(agenceService.getAllGroups().size());
		System.out.println(other);
		
	}
	

}
