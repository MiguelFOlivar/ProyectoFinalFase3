package com.mfigueroa.app.proyectofinal.service;

import java.util.List;

import com.mfigueroa.app.proyectofinal.dto.PeliculaDTO;

public interface IGeneroService {
	List<PeliculaDTO> obtenerPorGenero(String genero);
}
