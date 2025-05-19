package com.dimension.security.repository;

import com.dimension.security.model.Rol;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IRolRepository extends CrudRepository<Rol, Long> {

	public Optional<Rol> findByAuthority(String authority);
	
}
