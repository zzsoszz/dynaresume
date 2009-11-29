package org.dynaresume.repository;

import org.dynaresume.domain.Address;
import org.generic.repository.GenericRepository;

public interface AddressRepository  extends GenericRepository<Address, Long> {
	//we inherit the basic CRUD operations from the GenericRepository
}
