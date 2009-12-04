package org.dynaresume.common.repository;

import org.dynaresume.common.domain.Address;
import org.generic.repository.GenericRepository;

public interface AddressRepository  extends GenericRepository<Address, Long> {
	//we inherit the basic CRUD operations from the GenericRepository
}
