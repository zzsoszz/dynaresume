package org.akrogen.dynaresume.remoting.http;

import java.util.Collection;
import java.util.Iterator;

import org.akrogen.dynaresume.service.IAgenceService;


public class Scalpa {

	
	
	

	
	private IAgenceService agenceService;
	
	
	public void start() {

		System.out.println(agenceService);
		assert agenceService !=null;
		Collection subject=agenceService.findAllAgence();
		for (Iterator iterator = subject.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			System.err.println(object);
		}
	}
	
	public void setAgenceService(IAgenceService agenceService) {
		this.agenceService = agenceService;
	}

	
}
