package org.dynaresume.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "T_LEGAL_ENTITY",schema="common")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class LegalEntity extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8975049737136593445L;
	@Id
	private long id;
	
	@Column
	private String name;
	
	@Column
	private String code;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		Object oldValue = this.id;
		this.id = id;
		firePropertyChange("id", oldValue, id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		Object oldValue = this.name;
		this.name = name;
		firePropertyChange("name", oldValue, name);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		Object oldValue = this.code;
		this.code = code;
		firePropertyChange("code", oldValue, code);
	}

	
}
