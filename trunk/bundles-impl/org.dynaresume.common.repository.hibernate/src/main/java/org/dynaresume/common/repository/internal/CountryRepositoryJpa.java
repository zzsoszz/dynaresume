package org.dynaresume.common.repository.internal;

import org.dynaresume.common.domain.Country;
import org.dynaresume.common.repository.CountryRepository;
import org.generic.repository.jpa.GenericJpaRepository;
import org.springframework.stereotype.Repository;
@Repository(value="countryRepository")
public class CountryRepositoryJpa extends GenericJpaRepository<Country, String> implements CountryRepository {

}
