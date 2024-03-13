package com.mfigueroa.app.proyectofinal.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mfigueroa.app.proyectofinal.dto.PeliculaDTO;
import com.mfigueroa.app.proyectofinal.entity.Genero;
import com.mfigueroa.app.proyectofinal.mappers.PeliculaMapper;
import com.mfigueroa.app.proyectofinal.repository.IGeneroRepository;
import com.mfigueroa.app.proyectofinal.service.IGeneroService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class GeneroServiceImp implements IGeneroService{
	
	private IGeneroRepository generoRepository;
	private PeliculaMapper peliculaMapper;
	
	@Override
	public List<PeliculaDTO> obtenerPorGenero(String genero) {
		Genero generoBuscado = generoRepository.findByNombreGenero(genero);
		List<PeliculaDTO> peliculas = generoBuscado.getPeliculas().stream()
				.map(
						p->{
							PeliculaDTO dto = peliculaMapper.peliculaToPeliculaDTO(p);
							return dto;
						}).collect(Collectors.toList());
		return peliculas;
	}

}
