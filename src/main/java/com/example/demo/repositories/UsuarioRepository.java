package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.UsuarioModel;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
	Optional<UsuarioModel> findByEmail(String email);
	
}