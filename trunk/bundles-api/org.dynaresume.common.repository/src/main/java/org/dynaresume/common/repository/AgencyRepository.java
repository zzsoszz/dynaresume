package org.dynaresume.common.repository;

import org.dynaresume.common.domain.Agency;
import org.generic.repository.GenericRepository;

public interface AgencyRepository extends GenericRepository<Agency, Long> {
	//we inherit the basic CRUD operations from the GenericRepository
}
