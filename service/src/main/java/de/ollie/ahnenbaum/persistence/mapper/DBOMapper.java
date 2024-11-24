package de.ollie.ahnenbaum.persistence.mapper;

public interface DBOMapper<M, D> {
	D toDBO(M model);

	M toModel(D dbo);
}
