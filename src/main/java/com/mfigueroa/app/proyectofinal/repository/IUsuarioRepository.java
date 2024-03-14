package com.mfigueroa.app.proyectofinal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mfigueroa.app.proyectofinal.entity.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
	Optional<Usuario> findByEmail(String email);
	@Query("SELECT u FROM Usuario u  where email= :email and password= :password")
	Optional<Usuario> validateCredentials(String email, String password);
}
