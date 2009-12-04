package org.dynaresume.service;


import org.osgi.framework.ServiceReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;

public class AgenceServiceTest extends AbstractConfigurableBundleCreatorTests  {

	
	public void testOsgiPlatformStarts() {
	      assertNotNull(bundleContext);
	   }
	
	
	@Override
	protected String getManifestLocation() {
		// TODO Auto-generated method stub
		return "file:META-INF/MANIFEST.MF";
	}
	/**
	 * The location of the packaged OSGi bundles to be installed
	 * for this test. Values are Spring resource paths. The bundles
	 * we want to use are part of the same multi-project maven
	 * build as this project is. Hence we use the localMavenArtifact
	 * helper method to find the bundles produced by the package
	 * phase of the maven build (these tests will run after the
	 * packaging phase, in the integration-test phase). 
	 * 
	 * JUnit, commons-logging, spring-core and the spring OSGi
	 * test bundle are automatically included so do not need
	 * to be specified here.
	 */
//	protected String[] getTestBundlesNames() {
//		return new String[] {
//			"org.springframework.osgi.samples, simple-service-bundle," +getSpringDMVersion()
//		};
//	}
	
	
	/**
	 * The simple service should have been exported as an
	 * OSGi service, which we can verify using the OSGi
	 * service APIs.
	 *
	 * In a Spring bundle, using osgi:reference is a much
	 * easier way to get a reference to a published service.
	 * 
	 */
	public void testSimpleServiceExported() {
		waitOnContextCreation("org.remoting.http.client");
        ServiceReference ref = bundleContext.getServiceReference(IAgenceService.class.getName());
        assertNotNull("Service Reference is null", ref);
        try {
        	IAgenceService simpleService = (IAgenceService) bundleContext.getService(ref);
            assertNotNull("Cannot find the service", simpleService);
          //  assertEquals("simple service at your service", simpleService.stringValue());
        } finally {
            bundleContext.ungetService(ref);
        }
	}
	
	
	
	@Override
	protected String[] getConfigLocations() {
		// TODO Auto-generated method stub
		return new String[]{"file:META-INF/spring/module-context.xml","file:META-INF/spring/osgi-context.xml"};
	}
	
//	@Autowired
//	IAgenceService agenceService;
//	
//	public void testFindAllAgence() {
//		assertNotNull(agenceService);
//	}
//	public void testServiceReferenceExists() {
//		   ServiceReference serviceReference = 
//		      bundleContext.getServiceReference(ApplicationContext.class.getName());
//		   assertNotNull(serviceReference);
////		   assertEquals("Pig Latin", 
////		         serviceReference.getProperty("translator.language"));
//		}
//	
//	public void testFindAllSituationFamiliale() {
//		assertNotNull(agenceService);
//	}
//
//
//	public void testFindAllModeleRTF() {
//		assertNotNull(agenceService);
//	}

}
