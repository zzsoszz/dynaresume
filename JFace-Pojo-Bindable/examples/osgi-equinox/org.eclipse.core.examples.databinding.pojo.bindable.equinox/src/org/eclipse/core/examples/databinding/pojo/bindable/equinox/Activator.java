package org.eclipse.core.examples.databinding.pojo.bindable.equinox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;

import org.eclipse.core.examples.databinding.pojo.bindable.model.PojoPerson;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		
		PojoPerson person = new PojoPerson();

		// Instrumentation was done with successfull, add Listener
		PropertyChangeListener listener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				System.out.println("---------- Property User changed --------");
				System.out.println("  PropertyName=" + event.getPropertyName());
				System.out.println("  OldValue=" + event.getOldValue());
				System.out.println("  NewValue=" + event.getNewValue());
				System.out
						.println("------------------------------------------");
			}
		};
		
		try {
			Method m = person.getClass().getMethod("addPropertyChangeListener",
					String.class, PropertyChangeListener.class);
			m.invoke(person, "name", listener);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		person.setName("AAAAAAAAAAAAAAAAAAA");
		person.setName("bhbh");
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
	}

}
