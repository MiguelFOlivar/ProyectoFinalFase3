package com.mfigueroa.app.proyectofinal.imp;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfigueroa.app.proyectofinal.dto.PeliculaDTO;
import com.mfigueroa.app.proyectofinal.dto.ResumenPeliculaDTO;
import com.mfigueroa.app.proyectofinal.entity.Pelicula;
import com.mfigueroa.app.proyectofinal.mappers.PeliculaMapper;
import com.mfigueroa.app.proyectofinal.repository.IPeliculaRepository;
import com.mfigueroa.app.proyectofinal.service.IPeliculaService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class PeliculaServiceImp implements IPeliculaService {

	private PeliculaMapper peliculaMapper;
	private IPeliculaRepository peliculaRepository;

	@Override
	public ResumenPeliculaDTO registrarPelicula(String movie, MultipartFile archivoImagen) {
		ResumenPeliculaDTO resumenPeliculaDTO = new ResumenPeliculaDTO();
		PeliculaDTO peliculaDTO = new PeliculaDTO();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			peliculaDTO = objectMapper.readValue(movie, PeliculaDTO.class);
			peliculaDTO.setImagenPelicula(archivoImagen.getBytes());
			Pelicula pelicula = peliculaMapper.peliculaDtoToPelicula(peliculaDTO);
			Pelicula peliculaRegistrada = peliculaRepository.save(pelicula);
			resumenPeliculaDTO = peliculaMapper.peliculaToResumenPeliculaDTO(peliculaRegistrada);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				return resumenPeliculaDTO;
	}

	@Override
	public List<PeliculaDTO> buscarPorTitulo(String titulo) {
		List<Pelicula>peliculas = peliculaRepository.findByTituloContainingIgnoreCase(titulo);
		List<PeliculaDTO>peliculasDto = peliculas.stream()
				.map(p-> {
					PeliculaDTO peliculaDTO = peliculaMapper.peliculaToPeliculaDTO(p);
					return peliculaDTO;
				}).collect(Collectors.toList());
		
		return peliculasDto;
	}

}
