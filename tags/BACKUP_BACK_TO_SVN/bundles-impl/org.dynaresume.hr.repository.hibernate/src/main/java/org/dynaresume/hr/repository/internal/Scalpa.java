package org.dynaresume.hr.repository.internal;

import java.util.Collection;
import java.util.Iterator;

import org.dynaresume.hr.domain.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.dynaresume.hr.repository.ResumeRepository;

public class Scalpa {
	
	@Autowired
	private ResumeRepository resumeRepository;
	public void start(){
		System.out.println("coucou");
		
		System.out.println(resumeRepository);
		try{
		Collection<Resume> o=resumeRepository.findAll();
		System.out.println(o);
		for (Iterator<Resume> iterator = o.iterator(); iterator.hasNext();) {
			Resume agence = (Resume) iterator.next();
			System.out.println(agence);
		}
		} catch (Throwable e){
			e.printStackTrace();
		}
		
	}
}
