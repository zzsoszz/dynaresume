package org.dynaresume.hr.repository;

import org.dynaresume.hr.domain.Resume;
import org.generic.repository.GenericRepository;

public interface ResumeRepository extends GenericRepository<Resume, Long> {
	//we inherit the basic CRUD operations from the GenericRepository
}

