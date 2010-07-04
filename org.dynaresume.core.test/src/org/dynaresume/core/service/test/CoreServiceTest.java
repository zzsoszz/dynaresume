package org.dynaresume.core.service.test;

import java.util.Calendar;
import java.util.List;

import org.dynaresume.core.domain.Address;
import org.dynaresume.core.domain.Agency;
import org.dynaresume.core.domain.Collaboration;
import org.dynaresume.core.domain.Country;
import org.dynaresume.core.domain.Group;
import org.dynaresume.core.domain.NaturalPerson;
import org.dynaresume.core.service.CoreService;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

public class CoreServiceTest extends junit.framework.TestCase {

	private CoreService coreService;
	private ServiceTracker tracker;

	@Override
	protected void setUp() throws Exception {
		Bundle hostBundle = FrameworkUtil.getBundle(CoreService.class);
		tracker = new ServiceTracker(hostBundle.getBundleContext(), CoreService.class.getName(), null);
		tracker.open();
		try {
			tracker.waitForService(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		coreService = (CoreService) tracker.getService();
	}

	@Override
	protected void tearDown() throws Exception {
		tracker.close();
	};

	public void testGetAllCountries() throws InterruptedException {

		try {
			System.out.println(coreService.getAllCountries());
			List<Country> countries = coreService.getAllCountries();
			System.out.println(countries);
			assertNotNull(countries);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public void testCreateAndDeleteGroup() throws Exception {

		System.out.println(coreService.getAllGroups().size());
		int initial = coreService.getAllGroups().size();
		Group aGroup = new Group();

		// aGroup.setCode("1224");
		aGroup.setName("DEMO");
		// aGroup.setEmail("demo@demo.com");
		aGroup = coreService.saveGroup(aGroup);
		System.out.println(coreService.getAllGroups().size());
		int plusone = coreService.getAllGroups().size();
		assertEquals(plusone, initial + 1);
		coreService.deleteGroup(aGroup);
		System.out.println(coreService.getAllGroups().size());
		int finaal = coreService.getAllGroups().size();
		assertEquals(finaal, initial);
	}

	public void testGetAgenciesForGroup() throws Exception {

		Group aGroup = new Group();
		aGroup.setId(Long.valueOf(1L));
		Object o = coreService.getAgenciesForGroup(aGroup);
		System.out.println(o);
	}

	public void testGetCollaborationsForAgency() {

		Agency agency = new Agency();
		agency.setId(Long.valueOf(1L));
		Object o = coreService.getCollaborationsForAgency(agency);
		System.out.println(o);
	}

	public void testCreateNaturalPerson() {

		NaturalPerson naturalPerson = new NaturalPerson();
		naturalPerson.setFirstName("Teta");
		naturalPerson.setLastName("Toto");
		naturalPerson.setDrivingLicense(true);
		naturalPerson.setBirthPlace("Moon");
		naturalPerson.setEmail("teta.toto@proxiad.com");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1900);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		naturalPerson.setBirthDate(cal.getTime());
		// agency.setId(Long.valueOf(1L));
		NaturalPerson o = coreService.saveNaturalPerson(naturalPerson);
		System.out.println(o);
		System.out.println(o.getId());
		coreService.deleteNaturalPerson(o);
		System.out.println("end");

	}

	public void testSaveAndDeleteAgency() {
		Group proxiad = coreService.getAllGroups().get(0);
		Agency agency = new Agency();
		agency.setGroup(proxiad);
		agency.setEmail("moon.contact@proxiad.com");
		agency.setName("Proxiad Moon");
		Address address = new Address();
		address.setCity("Moon");
		Country country = new Country();
		country.setIso3("FRA");
		address.setCountry(country);
		address.setZipCode("00000");
		agency.setAddress(address);
		agency = coreService.saveAgency(agency);
		System.out.println(agency.getId());
		coreService.deleteAgency(agency);

	}

	public void testSaveAndDeleteCollaboration() {
		Collaboration collaboration = new Collaboration();

		NaturalPerson naturalPerson = new NaturalPerson();
		naturalPerson.setFirstName("Teta");
		naturalPerson.setLastName("Toto");
		naturalPerson.setDrivingLicense(true);
		naturalPerson.setBirthPlace("Moon");
		naturalPerson.setEmail("teta.toto@proxiad.com");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1900);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		naturalPerson.setBirthDate(cal.getTime());

		naturalPerson = coreService.saveNaturalPerson(naturalPerson);
		collaboration.setEmployee(naturalPerson);

		Group aGroup = new Group();
		aGroup.setId(Long.valueOf(1L));
		List<Agency> agencies = coreService.getAgenciesForGroup(aGroup);
		collaboration.setAgency(agencies.get(0));
		collaboration = coreService.saveCollaboration(collaboration);
		coreService.deleteCollaboration(collaboration);

	}

}
