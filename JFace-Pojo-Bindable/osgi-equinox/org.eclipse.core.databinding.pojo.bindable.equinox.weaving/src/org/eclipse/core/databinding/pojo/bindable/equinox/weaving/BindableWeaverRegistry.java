package org.eclipse.core.databinding.pojo.bindable.equinox.weaving;

import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.osgi.baseadaptor.BaseData;
import org.eclipse.osgi.baseadaptor.bundlefile.BundleEntry;
import org.eclipse.osgi.baseadaptor.hooks.ClassLoadingHook;
import org.eclipse.osgi.baseadaptor.loader.BaseClassLoader;
import org.eclipse.osgi.baseadaptor.loader.ClasspathEntry;
import org.eclipse.osgi.baseadaptor.loader.ClasspathManager;
import org.eclipse.osgi.framework.adaptor.BundleProtectionDomain;
import org.eclipse.osgi.framework.adaptor.ClassLoaderDelegate;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class BindableWeaverRegistry implements ClassLoadingHook,
		ServiceTrackerCustomizer {
	private static BindableWeaverRegistry instance = new BindableWeaverRegistry();
	private List<ServiceReference> weaverServices = new ArrayList<ServiceReference>();
	private BundleContext ctx;
	private ServiceTracker serviceTracker;

	private BindableWeaverRegistry() {
	}

	public static BindableWeaverRegistry getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	public boolean addClassPathEntry(ArrayList cpEntries, String cp,
			ClasspathManager hostmanager, BaseData sourcedata,
			ProtectionDomain sourcedomain) {
		return false;
	}

	public BaseClassLoader createClassLoader(ClassLoader parent,
			ClassLoaderDelegate delegate, BundleProtectionDomain domain,
			BaseData data, String[] bundleclasspath) {
		return null;
	}

	public String findLibrary(BaseData data, String libName) {
		return null;
	}

	public ClassLoader getBundleClassLoaderParent() {
		return null;
	}

	public void initializedClassLoader(BaseClassLoader baseClassLoader,
			BaseData data) {
	}

	public byte[] processClass(String name, byte[] classbytes,
			ClasspathEntry classpathEntry, BundleEntry entry,
			ClasspathManager manager) {

		String bundleId = manager.getBaseData().getSymbolicName();
		if (this.weaverServices.isEmpty()) {

			// try {
			// serviceTracker.waitForService(5000);
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

			return null;
		}
		for (Iterator<ServiceReference> iterator = this.weaverServices
				.iterator(); iterator.hasNext();) {
			ServiceReference reference = iterator.next();
			BindableWeaver weaver = (BindableWeaver) ctx.getService(reference);
			if (weaver != null) {
				byte[] transformedBytes = weaver.transform(name, classbytes);
				if (transformedBytes != null) {
					return transformedBytes;
				}
			}
		}
		return null;
	}

	public void start(BundleContext context) {
		this.ctx = context;
		serviceTracker = new ServiceTracker(context, BindableWeaver.class
				.getName(), this);
		serviceTracker.open();
	}

	public void stop(BundleContext context) {
		// Close the service tracker
		serviceTracker.close();
		serviceTracker = null;
		weaverServices = new ArrayList<ServiceReference>();
	}

	public Object addingService(ServiceReference reference) {
		System.out.println("Registering Service " + reference);
		this.weaverServices.add(reference);
		return reference;
	}

	public void modifiedService(ServiceReference reference, Object service) {
		// Rogue provider -- we don't support modifying provider services
		removedService(reference, service);
	}

	public void removedService(ServiceReference reference, Object service) {
		this.weaverServices.remove(reference);
	}
}
