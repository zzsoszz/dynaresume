package org.dynaresume.core.repository.jpa.internal;

import org.dynaresume.core.domain.NaturalPerson;
import org.dynaresume.core.repository.NaturalPersonRepository;
import org.generic.repository.jpa.GenericJpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "naturalPersonRepository")
public class NaturalPersonRepositoryJpa extends GenericJpaRepository<NaturalPerson, Long> implements
		NaturalPersonRepository {

}