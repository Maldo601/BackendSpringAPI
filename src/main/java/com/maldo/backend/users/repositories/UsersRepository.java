package com.maldo.backend.users.repositories;


import com.maldo.backend.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>, QuerydslPredicateExecutor<Users>
{
	Optional<Users> findByEmail(String email);
	Optional<Users> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	Boolean existsByPwd(String pwd);
}
