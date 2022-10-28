package com.maldo.backend.config.persistence;

import java.io.Serializable;
import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * The Class RepositoryImpl.
 *
 * @param <T>  the generic type
 * @param <ID> the generic type
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

	/** The Constant ERROR_CONCURRENCY_UPDATE. */
	private static final String ERROR_CONCURRENCY_UPDATE = "error.concurrency.update";

	/** The entity manager. */
	private final EntityManager entityManager;

	/**
	 * Instantiates a new repository impl.
	 *
	 * @param entityInformation the entity information
	 * @param entityManager     the entity manager
	 */
	public BaseRepositoryImpl(final JpaEntityInformation<T, ?> entityInformation, final EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}
}

