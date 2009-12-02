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
		
		
		
			System.err.println(agenceService.findAllGroups().size());
		Group newGroup =new Group();
		newGroup.setCode("12");
		newGroup.setEmail("scalpa12");
		
		newGroup.setName("dfsd");
		
		Group other=agenceService.createGroup(newGroup);
		
		System.out.println(agenceService.findAllGroups().size());
		System.out.println(other);
		
	}
	
	public void start3() {

		System.out.println(agenceService);
		assert agenceService !=null;
		Collection<Agency> subject=agenceService.findAllAgence();
		for (Iterator<Agency> iterator = subject.iterator(); iterator.hasNext();) {
			Agency agence =iterator.next();
			System.err.println(agence);
		}
	}
	

	@Resource
	private AgencyRepository agencyRepository;
	public void start2(){
		System.out.println("coucou");
		
		System.out.println(agencyRepository);
		try{
		Collection<Agency> o=agencyRepository.findAll();
		System.out.println(o);
		for (Iterator<Agency> iterator = o.iterator(); iterator.hasNext();) {
			Agency agence =iterator.next();
			System.out.println(agence);
		}
		} catch (Throwable e){
			e.printStackTrace();
		}
		
	}
}
