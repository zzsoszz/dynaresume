package org.dynaresume.common.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.dynaresume.basebean.BaseBean;

@MappedSuperclass
public abstract class Person extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 298482050929739147L;
	@Column(length=100,nullable=false)
	private String email;

	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Address address;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		Object oldValue = this.email;
		this.email = email;
		firePropertyChange("email", oldValue, email);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		Object oldValue = this.address;
		this.address = address;
		firePropertyChange("address", oldValue, address);
	}

}
