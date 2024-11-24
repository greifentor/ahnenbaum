package de.ollie.ahnenbaum.persistence;

import de.ollie.ahnenbaum.core.model.Place;
import de.ollie.ahnenbaum.core.service.port.persistence.PlacePersistencePort;
import de.ollie.ahnenbaum.persistence.entity.PlaceDBO;
import de.ollie.ahnenbaum.persistence.factory.PlaceDBOFactory;
import de.ollie.ahnenbaum.persistence.mapper.PlaceDBOMapper;
import de.ollie.ahnenbaum.persistence.repository.PlaceDBORepository;
import jakarta.inject.Named;

@Named
public class PlacePersistenceJPAAdapter
	extends NameProviderPersistenceJPAAdapter<Place, PlaceDBO>
	implements PlacePersistencePort {

	public PlacePersistenceJPAAdapter(PlaceDBOFactory factory, PlaceDBOMapper mapper, PlaceDBORepository repository) {
		super(factory, mapper, repository);
	}
}
