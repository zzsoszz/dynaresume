package org.dynaresume.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.dynaresume.basebean.BaseBean;
@Entity
@Table(name = "T_ADDRESS",schema="common")
public class Address extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9217187221005720810L;
	@Id
	private long id;
	@Column
	private String zipCode;
	@Column
	private String city;
	@Column
	private String fax;
	@Column
	private String telephone;
	
	@ManyToOne
	private Country country;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		Object oldValue = this.id;
		this.id = id;
		firePropertyChange("id", oldValue, id);
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		Object oldValue = this.zipCode;
		this.zipCode = zipCode;
		firePropertyChange("zipCode", oldValue, zipCode);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		Object oldValue = this.city;
		this.city = city;
		firePropertyChange("city", oldValue, city);
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		Object oldValue = this.fax;
		this.fax = fax;
		firePropertyChange("fax", oldValue, fax);
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		Object oldValue = this.telephone;
		this.telephone = telephone;
		firePropertyChange("telephone", oldValue, telephone);
	}


	
}
