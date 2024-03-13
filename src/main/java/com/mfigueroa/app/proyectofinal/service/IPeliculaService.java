package com.mfigueroa.app.proyectofinal.service;

import org.springframework.web.multipart.MultipartFile;

import com.mfigueroa.app.proyectofinal.dto.ResumenPeliculaDTO;

public interface IPeliculaService {
	ResumenPeliculaDTO registrarPelicula(String movie, MultipartFile archivoImagen);
}
