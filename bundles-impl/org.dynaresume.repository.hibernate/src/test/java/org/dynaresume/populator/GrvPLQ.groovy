package org.dynaresume.populator;

import org.dynaresume.domain.*;

class CommonPopulator {
	
	public static void main(String[] args) {
		
		Country france=new Country()
		france.iso3="FRA"
		france.label="France"
		Country tunisia=new Country()
		tunisia.iso3="TUN"
		tunisia.label="Tunisia"
		CommonPopulator populator = new CommonPopulator()
		//agency1
		Agency agency1 = populator.createAgency(code: "1",name:"Proxiad Nord",zipCode:"59000",fax:"0320121250",city:"Lille",telephone:"0320121250",country:france); 
		//agency2		
		Agency agency2 = populator.createAgency(code: "2",name:"Proxiad Idf",zipCode:"75000",fax:"0320121250",city:"Paris",telephone:"0320121250",country:france);
		Agency agency3 = populator.createAgency(code: "3",name:"Proxiad Tunis",zipCode:"2016",fax:"0320121250",city:"Paris",telephone:"0320121250",country:tunisia);
		NaturalPerson pascal = populator.createNaturalPerson()
		
		println pascal
		//println pascal.birthDate
//		println agency1.address.city
	}
	private static createAgency(def code, def name, def zipCode, def fax, def city, def telephone,Country country){
		Agency agency = new Agency(code: code,name:name)
		Address address = new Address(zipCode:zipCode,fax:fax,city:city,telephone:telephone,country:country)
		agency.address = address;
	}
	
	private static createNaturalPerson(){
		
		NaturalPerson naturalPerson = new NaturalPerson()
		
		naturalPerson.firstName = "Pascal"
		naturalPerson.lastName = "Leclercq"
		naturalPerson.birthDate = "16/06/1973"
		
	}
}
