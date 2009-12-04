package org.dynaresume.common.repository.internal;

import org.dynaresume.common.domain.Agency;
import org.dynaresume.common.repository.AgencyRepository;
import org.generic.repository.jpa.GenericJpaRepository;
import org.springframework.stereotype.Repository;
@Repository(value="agencyRepository")
public class AgencyRepositoryJpa extends GenericJpaRepository<Agency, Long> implements AgencyRepository {

}
