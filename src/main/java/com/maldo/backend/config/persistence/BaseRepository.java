package com.maldo.backend.config.persistence;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * The Interface Repo.
 *
 * @param <T>  the generic type
 * @param <ID> the generic type
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {	}
