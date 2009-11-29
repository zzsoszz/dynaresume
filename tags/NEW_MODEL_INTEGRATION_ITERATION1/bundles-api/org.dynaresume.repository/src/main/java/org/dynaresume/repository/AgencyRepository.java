package org.dynaresume.repository;

import org.dynaresume.domain.Agency;
import org.generic.repository.GenericRepository;

public interface AgencyRepository extends GenericRepository<Agency, Long> {
	//we inherit the basic CRUD operations from the GenericRepository
}
