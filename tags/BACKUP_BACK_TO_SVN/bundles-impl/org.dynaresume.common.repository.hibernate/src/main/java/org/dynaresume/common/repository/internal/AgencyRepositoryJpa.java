/*****************************************************************************
 * Copyright (c) 2009
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo Zerr <angelo.zerr@gmail.com>
 *     Jawher Moussa <jawher.moussa@gmail.com>
 *     Nicolas Inchauspe <nicolas.inchauspe@gmail.com>
 *     Pascal Leclercq <pascal.leclercq@gmail.com>
 *******************************************************************************/
package org.dynaresume.common.repository.internal;


import org.dynaresume.common.domain.Agency;
import org.dynaresume.common.repository.AgencyRepository;
import org.generic.repository.jpa.GenericJpaRepository;
import org.springframework.stereotype.Repository;
@Repository(value="agencyRepository")
public class AgencyRepositoryJpa extends GenericJpaRepository<Agency, Long> implements AgencyRepository {

}
