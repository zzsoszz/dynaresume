package org.dynaresume.common.repository.internal;

import java.util.Iterator;
import java.util.List;

import org.dynaresume.common.domain.Agency;
import org.dynaresume.common.domain.Group;
import org.dynaresume.common.repository.AgencyRepository;
import org.dynaresume.common.repository.GroupRepository;


public class Scalpa {
	
	
	private AgencyRepository agencyRepository;
	private GroupRepository groupRepository;
	
	public void setAgencyRepository(AgencyRepository agencyRepository) {
		this.agencyRepository = agencyRepository;
	}
	
	public void setGroupRepository(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}
	public void start(){
		System.out.println("coucou");
		
		System.out.println(groupRepository);
		try{
		List<Group> o=groupRepository.findAll();
		System.out.println(o.size());

		Group newGroup =new Group();
		newGroup.setCode("12");
		newGroup.setEmail("scalpa12");
		
		newGroup.setName("dfsd");
		
		groupRepository.save(newGroup);
		o=groupRepository.findAll();
		System.out.println(o.size());
		} catch (Throwable e){
			e.printStackTrace();
		}
		
	}
	
	
	public void start2(){
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
