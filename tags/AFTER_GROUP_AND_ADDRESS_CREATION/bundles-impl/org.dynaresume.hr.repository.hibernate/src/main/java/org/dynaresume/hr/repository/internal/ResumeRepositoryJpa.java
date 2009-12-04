package org.dynaresume.hr.repository.internal;

import org.dynaresume.hr.repository.ResumeRepository;
import org.dynaresume.hr.domain.Resume;
import org.generic.repository.jpa.GenericJpaRepository;
import org.springframework.stereotype.Repository;
@Repository(value="resumeRepository")
public class ResumeRepositoryJpa extends GenericJpaRepository<Resume, Long> implements ResumeRepository {

}

