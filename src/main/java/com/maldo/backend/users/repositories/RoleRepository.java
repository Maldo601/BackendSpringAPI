package com.maldo.backend.users.repositories;

import com.maldo.backend.users.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Long>, QuerydslPredicateExecutor<Roles>
{
	Optional<Roles> findByName(String name);
}
