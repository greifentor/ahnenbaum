package de.ollie.ahnenbaum.persistence;

import de.ollie.ahnenbaum.core.model.Place;
import de.ollie.ahnenbaum.core.service.port.persistence.PlacePersistencePort;
import de.ollie.ahnenbaum.persistence.entity.PlaceDBO;
import de.ollie.ahnenbaum.persistence.factory.DBOFactory;
import de.ollie.ahnenbaum.persistence.mapper.PlaceDBOMapper;
import de.ollie.ahnenbaum.persistence.repository.PlaceDBORepository;
import jakarta.inject.Named;

@Named
class PlacePersistenceJPAAdapter
	extends NameProviderPersistenceJPAAdapter<Place, PlaceDBO>
	implements PlacePersistencePort {

	public PlacePersistenceJPAAdapter(
		DBOFactory<PlaceDBO> factory,
		PlaceDBOMapper mapper,
		PlaceDBORepository repository
	) {
		super(factory, mapper, repository);
	}
}
