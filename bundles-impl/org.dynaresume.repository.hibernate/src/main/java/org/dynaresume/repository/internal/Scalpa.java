package org.dynaresume.repository.internal;

import java.util.Collection;
import java.util.Iterator;

import org.dynaresume.domain.Agency;
import org.dynaresume.repository.AgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class Scalpa {
	
	@Autowired
	private AgencyRepository agencyRepository;
	public void start(){
		System.out.println("coucou");
		
		System.out.println(agencyRepository);
		try{
		Collection<Agency> o=agencyRepository.findAll();
		System.out.println(o);
		for (Iterator iterator = o.iterator(); iterator.hasNext();) {
			Agency agence = (Agency) iterator.next();
			System.out.println(agence);
		}
		} catch (Throwable e){
			e.printStackTrace();
		}
		
	}
}
