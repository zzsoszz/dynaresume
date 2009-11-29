package org.dynaresume.repository.internal;

import org.dynaresume.domain.Agency;
import org.dynaresume.repository.AgencyRepository;
import org.generic.repository.jpa.GenericJpaRepository;
import org.springframework.stereotype.Repository;
@Repository(value="agencyRepository")
public class AgencyRepositoryJpa extends GenericJpaRepository<Agency, Long> implements AgencyRepository {

}
