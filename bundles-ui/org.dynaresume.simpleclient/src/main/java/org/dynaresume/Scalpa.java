package org.dynaresume;

import org.dynaresume.common.service.AgenceService;
import org.springframework.beans.factory.annotation.Autowired;

public class Scalpa {

	@Autowired
	private AgenceService agenceService;
	public void start(){
		System.err.println("coucou");
		System.out.println(agenceService);
	}
}
