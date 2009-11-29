package org.dynaresume.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "T_NATURAL_PERSON",schema="common")
public class NaturalPerson extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1233890503792715089L;
	@Id
	private long id;
	
	@Enumerated(EnumType.STRING)
	@Column(length=10)
	private MaritalStatus maritalStatus;
	
	@Column(length=100,nullable=false)
	private String firstName;
	@Column(length=100,nullable=false)
	private String lastName;
	@Column
	private String birthDate;

	@Column
	private String birthPlace;

	@Column
	private String mobilePhone;

	@Column
	private boolean drivingLicense;
	
	@ManyToOne
	private Country nationality; 
	
	@OneToMany(mappedBy="employee")
	 private Set<Collaboration> employers;

}
