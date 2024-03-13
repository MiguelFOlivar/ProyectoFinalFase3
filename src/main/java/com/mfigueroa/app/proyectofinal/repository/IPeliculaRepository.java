package com.mfigueroa.app.proyectofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfigueroa.app.proyectofinal.entity.Pelicula;

@Repository
public interface IPeliculaRepository extends JpaRepository<Pelicula, Integer>{
	List<Pelicula> findByTituloContainingIgnoreCase(String titulo);
}
