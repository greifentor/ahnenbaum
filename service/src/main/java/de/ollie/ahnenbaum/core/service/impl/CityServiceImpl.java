package de.ollie.ahnenbaum.core.service.impl;

import static de.ollie.ahnenbaum.util.Check.ensure;

import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.model.City;
import de.ollie.ahnenbaum.core.service.CityService;
import jakarta.inject.Named;
import java.util.List;
import java.util.UUID;

@Named
class CityServiceImpl implements CityService {

	@Override
	public void changeName(String name) {
		ensure(name != null, new ServiceException("", null, ""));
		// TODO Auto-generated method stub

	}

	@Override
	public City create(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public City findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
