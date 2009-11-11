package org.akrogen.dynaresume.repository.hibernate;

import java.util.Collection;
import java.util.Iterator;

import org.akrogen.dynaresume.domain.Agence;
import org.akrogen.dynaresume.repository.IAgenceDAO;
import org.springframework.beans.factory.annotation.Autowired;


public class Scalpa {
	
	@Autowired
	private IAgenceDAO agenceDAO;
	public void start(){
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
