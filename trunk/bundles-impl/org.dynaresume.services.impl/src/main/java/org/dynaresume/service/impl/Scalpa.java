package org.dynaresume.service.impl;

import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Resource;

import org.dynaresume.domain.Agence;
import org.dynaresume.repository.IAgenceDAO;
import org.dynaresume.service.IAgenceService;


public class Scalpa {

	
	
	

	@Resource
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
	

	@Resource
	private IAgenceDAO<Agence> agenceDAO;
	public void start2(){
		System.out.println("coucou");
		
		System.out.println(agenceDAO);
		try{
		Collection<Agence> o=agenceDAO.findAll();
		System.out.println(o);
		for (Iterator iterator = o.iterator(); iterator.hasNext();) {
			Agence agence = (Agence) iterator.next();
			System.out.println(agence.getRaisonSociale());
		}
		} catch (Throwable e){
			e.printStackTrace();
		}
		
	}
}
