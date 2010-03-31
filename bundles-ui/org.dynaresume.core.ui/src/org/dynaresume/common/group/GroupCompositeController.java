/*****************************************************************************
 * Copyright (c) 2009
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo Zerr <angelo.zerr@gmail.com>
 *     Jawher Moussa <jawher.moussa@gmail.com>
 *     Nicolas Inchauspe <nicolas.inchauspe@gmail.com>
 *     Pascal Leclercq <pascal.leclercq@gmail.com>
 *******************************************************************************/
package org.dynaresume.common.group;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;

public class GroupCompositeController {
	private GroupComposite m_groupComposite;
	private DataBindingContext m_bindingContext;
	private org.dynaresume.core.domain.Group group = new org.dynaresume.core.domain.Group();

	public GroupCompositeController(GroupComposite groupComposite, org.dynaresume.core.domain.Group newGroup) {
		m_groupComposite = groupComposite;
		setGroup(newGroup);
	}

	public GroupCompositeController(GroupComposite groupComposite) {
		m_groupComposite = groupComposite;
		if (group != null) {
			m_bindingContext = initDataBindings();
		}
	}

	private DataBindingContext initDataBindings() {
//		IObservableValue codeObserveWidget = SWTObservables.observeText(m_groupComposite.getCodeText(), SWT.Modify);
//		IObservableValue codeObserveValue = BeansObservables.observeValue(group, "code");
//		IObservableValue emailObserveWidget = SWTObservables.observeText(m_groupComposite.getEmailText(), SWT.Modify);
//
//		IObservableValue emailObserveValue = BeansObservables.observeValue(group, "email");
		IObservableValue nameObserveWidget = SWTObservables.observeText(m_groupComposite.getNameText(), SWT.Modify);
		IObservableValue nameObserveValue = BeansObservables.observeValue(group, "name");
		//
		DataBindingContext bindingContext = new DataBindingContext();
		//
//		bindingContext.bindValue(codeObserveWidget, codeObserveValue, null, null);
		// PLQ hand coded
		UpdateValueStrategy fakeUpdateValueStrategy = new UpdateValueStrategy();
		fakeUpdateValueStrategy.setAfterConvertValidator(new IValidator() {

			public IStatus validate(Object value) {
				if (value != null) {
					String input = (String) value;

					Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
					Matcher m = p.matcher(input.toUpperCase());
					boolean result= m.matches();
					
					if (!result)
						// if(value2.length()>5)
						return ValidationStatus.error("please enter a valid name");
				}
				return Status.OK_STATUS;
			}
		});

		// end PLQ hand coded
	//	bindingContext.bindValue(emailObserveWidget, emailObserveValue, emailUpdateValueStrategy, null);
		bindingContext.bindValue(nameObserveWidget, nameObserveValue, fakeUpdateValueStrategy, null);
		//
		return bindingContext;
	}

	public org.dynaresume.core.domain.Group getGroup() {
		return group;
	}

	public void setGroup(org.dynaresume.core.domain.Group newGroup) {
		setGroup(newGroup, true);
	}

	public void setGroup(org.dynaresume.core.domain.Group newGroup, boolean update) {
		group = newGroup;
		if (update) {
			if (m_bindingContext != null) {
				m_bindingContext.dispose();
				m_bindingContext = null;
			}
			if (group != null) {
				m_bindingContext = initDataBindings();
			}
		}
	}

	public DataBindingContext getBindingContext() {
		return m_bindingContext;
	}
}