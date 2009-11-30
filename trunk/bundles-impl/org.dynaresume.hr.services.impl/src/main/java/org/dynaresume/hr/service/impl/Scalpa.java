package org.dynaresume.hr.service.impl;

import java.util.Collection;
import java.util.Iterator;

import org.dynaresume.hr.domain.Resume;
import org.dynaresume.hr.service.HRService;
import org.springframework.beans.factory.annotation.Autowired;


public class Scalpa {

	
	
	
	@Autowired
	private HRService hrService;

	

	
	public void start(){
		System.out.println("coucou");
		
		System.out.println(hrService);
		try{
		Collection<Resume> o=hrService.findAll();
		System.out.println(o);
		for (Iterator<Resume> iterator = o.iterator(); iterator.hasNext();) {
			Resume resume =iterator.next();
			System.out.println(resume);
		}
		} catch (Throwable e){
			e.printStackTrace();
		}
		
	}
}
