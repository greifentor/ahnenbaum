package de.ollie.ahnenbaum.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.ahnenbaum.core.exception.ParameterIsNullException;
import de.ollie.ahnenbaum.core.exception.ServiceException;
import de.ollie.ahnenbaum.core.model.NameProvider;
import de.ollie.ahnenbaum.core.service.UUIDService;
import de.ollie.ahnenbaum.core.service.port.persistence.NameProviderPersistencePort;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

abstract class NameProviderServiceImplTest<M extends NameProvider> {

	private static final String NAME = "name";
	private static final UUID UID = UUID.randomUUID();

	abstract M createModel();

	abstract NameProviderPersistencePort<M> persistencePort();

	abstract NameProviderServiceImpl<M> unitUnderTest();

	@Mock
	private UUIDService uuidService;

	@Nested
	class TestsOfMethod_create_String {

		@Test
		void throwsAnException_passingANullValueAsName() {
			assertThrows(ParameterIsNullException.class, () -> unitUnderTest().create(null));
		}

		@Test
		void throwsAnException_passingAnEmptyStringAsName() {
			assertThrows(ServiceException.class, () -> unitUnderTest().create(""));
		}

		@Test
		void returnsTheReturnedValueOfThePersistencePort() {
			// Prepare
			M model = createModel();
			when(persistencePort().create(NAME)).thenReturn(model);
			// Run
			M returned = unitUnderTest().create(NAME);
			// Check
			assertSame(model, returned);
		}
	}

	@Nested
	class TestsOfMethod_deleteById_UUID {

		@Test
		void throwsAnException_passingANullValueAsId() {
			assertThrows(ParameterIsNullException.class, () -> unitUnderTest().deleteById(null));
		}

		@Test
		void callsThePersistencePortMethodDeleteByIdCorrectly() {
			// Run
			unitUnderTest().deleteById(UID);
			// Check
			verify(persistencePort(), times(1)).deleteById(UID);
		}
	}

	@Nested
	class TestsOfMethod_findAll {

		@Test
		void returnsTheReturnedValueOfThePersistencePort() {
			// Prepare
			M model = createModel();
			List<M> expected = List.of(model);
			when(persistencePort().findAll()).thenReturn(expected);
			// Run
			List<M> returned = unitUnderTest().findAll();
			// Check
			assertSame(expected, returned);
		}
	}

	@Nested
	class TestsOfMethod_findById_UUID {

		@Test
		void throwsAnException_passingANullValueAsId() {
			assertThrows(ParameterIsNullException.class, () -> unitUnderTest().findById(null));
		}

		@Test
		void returnsTheReturnedValueOfThePersistencePort() {
			// Prepare
			M model = createModel();
			Optional<M> expected = Optional.of(model);
			when(persistencePort().findById(UID)).thenReturn(expected);
			// Run
			Optional<M> returned = unitUnderTest().findById(UID);
			// Check
			assertSame(expected, returned);
		}
	}

	@Nested
	class TestsOfMethod_update_Gender {

		@Test
		void throwsAnException_passingANullValue() {
			assertThrows(ParameterIsNullException.class, () -> unitUnderTest().update(null));
		}

		@Test
		void returnsTheReturnOfThePersistenceMethodCall() {
			// Prepare
			M model = createModel();
			M expected = createModel();
			when(persistencePort().update(model)).thenReturn(expected);
			// Run & Check
			assertSame(expected, unitUnderTest().update(model));
		}
	}
}
