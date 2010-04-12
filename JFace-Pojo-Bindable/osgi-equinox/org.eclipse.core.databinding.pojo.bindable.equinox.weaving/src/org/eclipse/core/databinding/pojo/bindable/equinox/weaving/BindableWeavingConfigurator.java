
package org.eclipse.core.databinding.pojo.bindable.equinox.weaving;

import org.eclipse.osgi.baseadaptor.HookConfigurator;
import org.eclipse.osgi.baseadaptor.HookRegistry;
import org.eclipse.osgi.framework.debug.Debug;

public class BindableWeavingConfigurator implements HookConfigurator {
    public BindableWeavingConfigurator() {
        super();
    }

    public void addHooks(HookRegistry hookRegistry) {
        if (Debug.DEBUG && Debug.DEBUG_GENERAL){
            //Debug.println("EclipseLink: Adding WeaverRegistry Class Loading Hook"); //$NON-NLS-1$
        }
        hookRegistry.addClassLoadingHook(BindableWeaverRegistry.getInstance());
        hookRegistry.addAdaptorHook(new BindableWeavingAdaptor());
    }
}
