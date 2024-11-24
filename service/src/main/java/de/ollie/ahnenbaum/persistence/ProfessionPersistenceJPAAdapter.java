package de.ollie.ahnenbaum.persistence;

import de.ollie.ahnenbaum.core.model.Profession;
import de.ollie.ahnenbaum.core.service.port.persistence.ProfessionPersistencePort;
import de.ollie.ahnenbaum.persistence.entity.ProfessionDBO;
import de.ollie.ahnenbaum.persistence.factory.ProfessionDBOFactory;
import de.ollie.ahnenbaum.persistence.mapper.ProfessionDBOMapper;
import de.ollie.ahnenbaum.persistence.repository.ProfessionDBORepository;
import jakarta.inject.Named;

@Named
public class ProfessionPersistenceJPAAdapter
	extends NameProviderPersistenceJPAAdapter<Profession, ProfessionDBO>
	implements ProfessionPersistencePort {

	public ProfessionPersistenceJPAAdapter(
		ProfessionDBOFactory factory,
		ProfessionDBOMapper mapper,
		ProfessionDBORepository repository
	) {
		super(factory, mapper, repository);
	}
}
