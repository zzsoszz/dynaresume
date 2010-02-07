package org.dynaresume.core.repository.jpa.internal;

import org.dynaresume.core.domain.Collaboration;
import org.dynaresume.core.repository.CollaborationRepository;
import org.generic.repository.jpa.GenericJpaRepository;
import org.springframework.stereotype.Repository;
@Repository(value="collaborationRepository")
public class CollaborationRepositoryJpa extends GenericJpaRepository<Collaboration, Long> implements CollaborationRepository {

	

}
