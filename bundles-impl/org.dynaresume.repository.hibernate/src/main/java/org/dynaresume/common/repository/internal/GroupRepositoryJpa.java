package org.dynaresume.common.repository.internal;

import org.dynaresume.common.domain.Group;
import org.dynaresume.common.repository.GroupRepository;
import org.generic.repository.jpa.GenericJpaRepository;
import org.springframework.stereotype.Repository;
@Repository(value="groupRepository")
public class GroupRepositoryJpa extends GenericJpaRepository<Group, Long> implements GroupRepository {

}
