package org.dynaresume.remoting.http;

import java.util.Collection;
import java.util.Iterator;

import org.dynaresume.service.AgenceService;


public class Scalpa {

	
	
	

	
	private AgenceService agenceService;
	
	
	public void start() {

		System.out.println(agenceService);
		assert agenceService !=null;
		Collection subject=agenceService.findAllAgence();
		for (Iterator iterator = subject.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			System.err.println(object);
		}
	}
	
	public void setAgenceService(AgenceService agenceService) {
		this.agenceService = agenceService;
	}

	
}
