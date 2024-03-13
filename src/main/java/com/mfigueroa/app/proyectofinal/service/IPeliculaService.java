package com.mfigueroa.app.proyectofinal.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mfigueroa.app.proyectofinal.dto.PeliculaDTO;
import com.mfigueroa.app.proyectofinal.dto.ResumenPeliculaDTO;

public interface IPeliculaService {
	ResumenPeliculaDTO registrarPelicula(String movie, MultipartFile archivoImagen);
	List<PeliculaDTO> buscarPorTitulo(String titulo);
}
