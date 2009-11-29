package org.dynaresume.service.impl;

import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Resource;

import org.dynaresume.domain.Agency;
import org.dynaresume.repository.AgencyRepository;
import org.dynaresume.service.AgenceService;


public class Scalpa {

	
	
	

	@Resource
	private AgenceService agenceService;
	
	
	public void start() {

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
