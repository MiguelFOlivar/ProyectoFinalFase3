package com.mfigueroa.app.proyectofinal.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mfigueroa.app.proyectofinal.dto.PeliculaDTO;
import com.mfigueroa.app.proyectofinal.dto.ResumenPeliculaDTO;
import com.mfigueroa.app.proyectofinal.entity.Genero;
import com.mfigueroa.app.proyectofinal.entity.ImagenPelicula;
import com.mfigueroa.app.proyectofinal.entity.Pelicula;
import com.mfigueroa.app.proyectofinal.repository.IGeneroRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PeliculaMapper {
	private IGeneroRepository generoRepository;
	
	public Pelicula peliculaDtoToPelicula(PeliculaDTO peliculaDTO) {
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo(peliculaDTO.getTitulo());
		pelicula.setUrlWeb(peliculaDTO.getUrlWeb());
		ImagenPelicula imagenPelicula = new ImagenPelicula();
		imagenPelicula.setNombreArchivo(peliculaDTO.getNombreImagen());
		imagenPelicula.setImagen(peliculaDTO.getImagenPelicula());
		pelicula.setImagenPelicula(imagenPelicula);
		List<Genero> generos = new ArrayList<>();
		for(String g:  peliculaDTO.getGeneros()) {
			generos.add(generoRepository.findByNombreGenero(g));
		}
		pelicula.setGeneros(generos);
		return pelicula;
	}
	public ResumenPeliculaDTO peliculaToResumenPeliculaDTO(Pelicula pelicula) {
		ResumenPeliculaDTO resumenPeliculaDTO = new ResumenPeliculaDTO();
		
		resumenPeliculaDTO.setNombreImagen(pelicula.getImagenPelicula().getNombreArchivo());
		String generosString = pelicula.getGeneros().stream().map(
				g->g.getNombreGenero()).collect(Collectors.joining(" - "));
		resumenPeliculaDTO.setStringGeneros(generosString);
		resumenPeliculaDTO.setTitulo(pelicula.getTitulo());
		resumenPeliculaDTO.setUrlWeb(pelicula.getUrlWeb());
		return resumenPeliculaDTO;
	}
}
