package de.ollie.ahnenbaum.persistence.factory;

public interface DBOFactory<D> {
	D create(String name);
}
