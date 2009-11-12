package org.dynaresume;

import org.dynaresume.service.IAgenceService;
import org.springframework.beans.factory.annotation.Autowired;

public class Scalpa {

	@Autowired
	private IAgenceService agenceService;
	public void start(){
		System.err.println("coucou");
		System.out.println(agenceService);
	}
}
