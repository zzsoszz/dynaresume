package org.dynaresume.common.service.test;

import java.util.Properties;

import javax.sql.DataSource;

import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;

public class DemoBundleTest extends AbstractConfigurableBundleCreatorTests
// 
{

	
//	@Resource
//	private AgencyRepository agencyRepository;

	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		
		
	}
	
//	@Resource
//	private DataSource dynaresumeDataSource;
//	
	
	public void testOsgiPlatformStarts() throws Exception {
		System.out.println(bundleContext
				.getProperty(Constants.FRAMEWORK_VENDOR));
		System.out.println(bundleContext
				.getProperty(Constants.FRAMEWORK_VERSION));
		System.out.println(bundleContext
				.getProperty(Constants.FRAMEWORK_EXECUTIONENVIRONMENT));
	}

	public void testOsgiPlatformStarts2() throws Exception {
		assertTrue(true);
		assertNotNull(bundleContext);
		
		ServiceReference[] refs= bundleContext.getAllServiceReferences(DataSource.class.getName(), null);
		for (int i = 0; i < refs.length; i++) {
			System.out.println(refs[i]);
			
		}
		ServiceTracker tracker = new ServiceTracker(bundleContext, refs[0], null);
		tracker.open();
		
		System.out.println(tracker.getService());
		//System.out.println(dynaresumeDataSource);
		// assertNotNull(agenceService);
		System.err.println("**************** " + bundleContext);
	}

//	@Override
//	protected String getManifestLocation() {
//
//		return "file:./META-INF/MANIFEST.MF";
//	}

	@Override
		protected String getRootPath() {
			// TODO Auto-generated method stub
			return super.getRootPath();
		}
	
	protected Properties getDefaultSettings() {
		
		Properties props=super.getDefaultSettings();
		System.out.println("************************** "+props);
		
		return props;
	};
	protected String[] getTestBundlesNames() {
		return new String[] {
				  				
				"org.springframework, org.springframework.beans, 2.5.6.SEC01",
				"org.springframework, org.springframework.context, 2.5.6.SEC01",
				"org.springframework, org.springframework.core, 2.5.6.SEC01",
//				"org.springframework, org.springframework.transaction, 2.5.6.SEC01",
//				"org.springframework, org.springframework.aop, 2.5.6.SEC01",
//				
				  "org.apache.xbean, com.springsource.org.apache.xbean.spring, 3.6.0",
//				  "org.springframework, org.springframework.jdbc,  2.5.6.SEC01",
				  
				  "org.apache.commons, com.springsource.org.apache.commons.pool, 1.3.0",
				  "org.apache.commons, com.springsource.org.apache.commons.dbcp, 1.2.2.osgi",
				  "com.h2database, com.springsource.org.h2, 1.0.71",
				  "org.dynaresume, org.dynaresume.infrastructure.db.h2, 0.0.1-SNAPSHOT",
				  "org.dynaresume, org.dynaresume.infrastructure.db, 0.0.1-SNAPSHOT",
				 
					"org.apache.commons, com.springsource.org.apache.commons.logging, 1.1.1",
					"javax.annotation, com.springsource.javax.annotation, 1.0.0",
					
					
//				
//				"org.objectweb.asm, com.springsource.org.objectweb.asm, 1.5.3",
//				"org.objectweb.asm, com.springsource.org.objectweb.asm.attrs, 1.5.3",
//				"javax.xml.stream, com.springsource.javax.xml.stream, 1.0.1",
//				"org.dom4j, com.springsource.org.dom4j, 1.6.1",
				
//				"net.sourceforge.cglib, com.springsource.net.sf.cglib, 2.1.3",
//				"org.jboss.javassist, com.springsource.javassist, 3.3.0.ga",
//				"javax.persistence, com.springsource.javax.persistence, 1.99.0",
//"org.springframework, org.springframework.orm, 2.5.6.SEC01",
//				"org.dynaresume, org.dynaresume.infrastructure.util, 0.0.1-SNAPSHOT",
//				"org.dynaresume, org.dynaresume.common.domain, 0.0.1-SNAPSHOT",
//				"org.apache.commons, com.springsource.org.apache.commons.collections, 3.2.1",
//				"org.antlr, com.springsource.antlr, 2.7.6",
//				"org.hibernate, com.springsource.org.hibernate, 3.2.6.ga",
//				"org.dynaresume, org.dynaresume.common.services, 0.0.1-SNAPSHOT",
//				"org.dynaresume, org.dynaresume.infrastructure.repository.generic, 0.0.1-SNAPSHOT",
//				"org.dynaresume, org.dynaresume.common.repository, 0.0.1-SNAPSHOT",
//				"org.dynaresume, org.dynaresume.common.repository.hibernate, 0.0.1-SNAPSHOT",
//				
//				"org.hibernate, com.springsource.org.hibernate.annotations.common, 3.3.0.ga",
				  
		
				  
//				"org.dynaresume, org.dynaresume.common.services.impl, 0.0.1-SNAPSHOT",
				  

		};
	};

//	protected String[] getConfigLocations() {
//		return new String[] { "file:./META-INF/spring/module-context.xml" };
//		//,		"file:./META-INF/spring/osgi-context.xml"
//	}
}
