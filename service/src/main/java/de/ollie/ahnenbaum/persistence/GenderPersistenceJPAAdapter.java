package de.ollie.ahnenbaum.persistence;

import de.ollie.ahnenbaum.core.model.Gender;
import de.ollie.ahnenbaum.core.service.port.persistence.GenderPersistencePort;
import de.ollie.ahnenbaum.persistence.entity.GenderDBO;
import de.ollie.ahnenbaum.persistence.factory.GenderDBOFactory;
import de.ollie.ahnenbaum.persistence.mapper.GenderDBOMapper;
import de.ollie.ahnenbaum.persistence.repository.GenderDBORepository;
import jakarta.inject.Named;

@Named
public class GenderPersistenceJPAAdapter
	extends NameProviderPersistenceJPAAdapter<Gender, GenderDBO>
	implements GenderPersistencePort {

	public GenderPersistenceJPAAdapter(GenderDBOFactory factory, GenderDBOMapper mapper, GenderDBORepository repository) {
		super(factory, mapper, repository);
	}
}
