package org.dynaresume.common.repository.internal;

import java.util.Iterator;
import java.util.List;

import org.dynaresume.common.domain.Agency;
import org.dynaresume.common.repository.AgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class Scalpa {
	
	@Autowired
	private AgencyRepository agencyRepository;
	public void start(){
		System.out.println("coucou");
		
		System.out.println(agencyRepository);
		try{
		List<Agency> o=agencyRepository.findAll();
		System.out.println(o);
		for (Iterator<Agency> iterator = o.iterator(); iterator.hasNext();) {
			Agency agence =  iterator.next();
			System.out.println(agence);
		}
		} catch (Throwable e){
			e.printStackTrace();
		}
		
	}
}
