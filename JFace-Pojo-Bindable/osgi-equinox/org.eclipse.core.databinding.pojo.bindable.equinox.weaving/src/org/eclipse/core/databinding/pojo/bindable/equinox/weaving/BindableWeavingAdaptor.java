package org.eclipse.core.databinding.pojo.bindable.equinox.weaving;

import java.io.IOException;
import java.net.URLConnection;
import java.util.Properties;

import org.eclipse.osgi.baseadaptor.BaseAdaptor;
import org.eclipse.osgi.baseadaptor.hooks.AdaptorHook;
import org.eclipse.osgi.framework.log.FrameworkLog;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

public class BindableWeavingAdaptor implements AdaptorHook {

	public void frameworkStart(BundleContext context) throws BundleException {
		BindableWeaverRegistry.getInstance().start(context);
	}

	public void frameworkStopping(BundleContext context) {
		BindableWeaverRegistry.getInstance().stop(context);

	}

	public void addProperties(Properties properties) {
	}

	public FrameworkLog createFrameworkLog() {
		return null;
	}

	public void frameworkStop(BundleContext context) throws BundleException {
	}

	public void handleRuntimeError(Throwable error) {
	}

	public void initialize(BaseAdaptor adaptor) {
	}

	public URLConnection mapLocationToURLConnection(String location)
			throws IOException {
		return null;
	}

	public boolean matchDNChain(String pattern, String[] dnChain) {
		return false;
	}

}
