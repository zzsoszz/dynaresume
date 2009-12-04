package org.dynaresume.common.group;
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
	private org.dynaresume.common.domain.Group group = new org.dynaresume.common.domain.Group();

	public GroupCompositeController(GroupComposite groupComposite, org.dynaresume.common.domain.Group newGroup) {
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
		IObservableValue codeObserveWidget = SWTObservables.observeText(m_groupComposite.getCodeText(), SWT.Modify);
		IObservableValue codeObserveValue = BeansObservables.observeValue(group, "code");
		IObservableValue emailObserveWidget = SWTObservables.observeText(m_groupComposite.getEmailText(), SWT.Modify);
		
		IObservableValue emailObserveValue = BeansObservables.observeValue(group, "email");
		IObservableValue nameObserveWidget = SWTObservables.observeText(m_groupComposite.getNameText(), SWT.Modify);
		IObservableValue nameObserveValue = BeansObservables.observeValue(group, "name");
		//
		DataBindingContext bindingContext = new DataBindingContext();
		//
		bindingContext.bindValue(codeObserveWidget, codeObserveValue, null, null);
		//PLQ hand coded
		UpdateValueStrategy emailUpdateValueStrategy = new UpdateValueStrategy();
		emailUpdateValueStrategy.setAfterConvertValidator(new IValidator() {
			
			public IStatus validate(Object value) {
				if(value!=null)
				{
					String value2=(String)value;
					if(value2.length()>5)
						return ValidationStatus
						.error("this is not a valid email");
				}
				return Status.OK_STATUS;
			}
		});
		
		//end PLQ hand coded
		bindingContext.bindValue(emailObserveWidget, emailObserveValue, emailUpdateValueStrategy, null);
		bindingContext.bindValue(nameObserveWidget, nameObserveValue, null, null);
		//
		return bindingContext;
	}

	public org.dynaresume.common.domain.Group getGroup() {
		return group;
	}

	public void setGroup(org.dynaresume.common.domain.Group newGroup) {
		setGroup(newGroup, true);
	}

	public void setGroup(org.dynaresume.common.domain.Group newGroup, boolean update) {
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