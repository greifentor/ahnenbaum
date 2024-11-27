package de.ollie.ahnenbaum.persistence;

import de.ollie.ahnenbaum.core.model.Gender;
import de.ollie.ahnenbaum.core.service.port.persistence.GenderPersistencePort;
import de.ollie.ahnenbaum.persistence.entity.GenderDBO;
import de.ollie.ahnenbaum.persistence.factory.DBOFactory;
import de.ollie.ahnenbaum.persistence.mapper.GenderDBOMapper;
import de.ollie.ahnenbaum.persistence.repository.GenderDBORepository;
import jakarta.inject.Named;

@Named
class GenderPersistenceJPAAdapter
	extends NameProviderPersistenceJPAAdapter<Gender, GenderDBO>
	implements GenderPersistencePort {

	public GenderPersistenceJPAAdapter(
		DBOFactory<GenderDBO> factory,
		GenderDBOMapper mapper,
		GenderDBORepository repository
	) {
		super(factory, mapper, repository);
	}
}
