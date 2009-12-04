package org.dynaresume.common.repository;

import org.dynaresume.common.domain.Collaboration;
import org.generic.repository.GenericRepository;

public interface CollaborationRepository  extends GenericRepository<Collaboration, Long> {
	//we inherit the basic CRUD operations from the GenericRepository
}

