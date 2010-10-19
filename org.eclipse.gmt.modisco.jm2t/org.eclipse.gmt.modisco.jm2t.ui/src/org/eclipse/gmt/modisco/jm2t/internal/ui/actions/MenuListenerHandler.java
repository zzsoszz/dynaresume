/*******************************************************************************
 * Copyright (c) 2010 Angelo ZERR.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:      
 *     Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.gmt.modisco.jm2t.internal.ui.actions;

import java.net.URI;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.gmt.modisco.jm2t.core.JM2TCore;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.internal.ui.Messages;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

/**
 * Menu listener used to launch the generation of selected object with
 * teh generator confifiguration {@link IGeneratorConfiguration}.
 * 
 */
public class MenuListenerHandler implements SelectionListener {

	private final IGeneratorConfiguration generatorConfiguration;
	private final Object[] sources;

	MenuListenerHandler(final IGeneratorConfiguration generatorConfiguration,
			final Object[] sourceParameter) {
		this.generatorConfiguration = generatorConfiguration;
		this.sources = sourceParameter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse
	 * .swt.events.SelectionEvent)
	 */
	public void widgetDefaultSelected(final SelectionEvent e) {
		// we will not use this method
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt
	 * .events.SelectionEvent)
	 */
	public void widgetSelected(final SelectionEvent event) {
		for (final Object source : this.sources) {
			Job job = new Job(Messages.MenuListenerHandler_0) {

				@Override
				protected IStatus run(final IProgressMonitor monitor) {
					JM2TCore.getGeneratorManager().generate(source,
							generatorConfiguration);
					return Status.OK_STATUS;
				}

			};
			job.schedule();
		}
	}

}
