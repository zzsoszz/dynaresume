package org.dynaresume.hr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.dynaresume.basebean.BaseBean;
@Entity
@Table(name = "T_COMPETENCE",schema="HR")
public class CompetenceCategory  extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2603000363466573076L;

	@Id
	private long id;
	@Column
	private String label;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		
		this.id = id;
		
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		Object oldValue = this.label;
		this.label = label;
		firePropertyChange("label", oldValue, label);
	}
	
	
}
