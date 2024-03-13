package com.mfigueroa.app.proyectofinal.peliculas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mfigueroa.app.proyectofinal.dto.PeliculaDTO;
import com.mfigueroa.app.proyectofinal.entity.Genero;
import com.mfigueroa.app.proyectofinal.entity.ImagenPelicula;
import com.mfigueroa.app.proyectofinal.entity.Pelicula;
import com.mfigueroa.app.proyectofinal.model.MapperFiles;
import com.mfigueroa.app.proyectofinal.repository.IGeneroRepository;
import com.mfigueroa.app.proyectofinal.repository.IPeliculaRepository;
import com.mfigueroa.app.proyectofinal.service.IPeliculaService;

@SpringBootTest
public class ConsultaPorTituloTest {
	@Autowired
	private IGeneroRepository generoRepository;
	@Autowired
	private MapperFiles mapperFiles;
	@Autowired
	private IPeliculaRepository peliculaRepository;
	@Autowired
	private IPeliculaService peliculaService;
	@Test
	public void validarConsulta() throws IOException {
		Genero genero = new Genero();
		genero.setNombreGenero("Animación");
		Genero genero1 = generoRepository.save(genero);
		
		genero = new Genero();
		genero.setNombreGenero("Fantasía");
		Genero genero2 = generoRepository.save(genero);
		
		genero = new Genero();
		genero.setNombreGenero("Super Héroes");
		Genero genero3 = generoRepository.save(genero);
		
		//Pelicula 1
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo("Super Mario: La Pelicula");
		pelicula.setUrlWeb("https://www.universalpictures.com.mx/micro/super-mario-bros");
		
		List<Genero> generos = new ArrayList<>();
		generos.add(genero1);
		generos.add(genero2);
		pelicula.setGeneros(generos);
		
		ImagenPelicula imagenPelicula = new ImagenPelicula();
		imagenPelicula.setNombreArchivo("super.jpg");
		imagenPelicula.setImagen(mapperFiles.inputStreamToByteArray(getClass().
				getResourceAsStream("../resources/super.jpg")));
		pelicula.setImagenPelicula(imagenPelicula);
		Pelicula peliculaGuardada = peliculaRepository.save(pelicula);
		
		//pelicula 2
		pelicula = new Pelicula();
		pelicula.setTitulo("Superman: El hombre del mañana");
		pelicula.setUrlWeb("https://doblaje.fandom.com/es/wiki/Superman:_Hombre_del_ma%C3%B1ana");
		
		generos = new ArrayList<>();
		generos.add(genero1);
		generos.add(genero3);
		pelicula.setGeneros(generos);
		
		imagenPelicula = new ImagenPelicula();
		imagenPelicula.setNombreArchivo("superman.jpg");
		imagenPelicula.setImagen(mapperFiles.inputStreamToByteArray(getClass().
				getResourceAsStream("../resources/superman.jpg")));
		pelicula.setImagenPelicula(imagenPelicula);
		peliculaGuardada = peliculaRepository.save(pelicula);
		
		//pelicula 3
		pelicula = new Pelicula();
		pelicula.setTitulo("Aquaman: El reino perdido");
		pelicula.setUrlWeb("https://www.filmaffinity.com/es/film415770.html");
		
		generos = new ArrayList<>();
		generos.add(genero3);
		generos.add(genero2);
		pelicula.setGeneros(generos);
		
		imagenPelicula = new ImagenPelicula();
		imagenPelicula.setNombreArchivo("aquaman.jpg");
		imagenPelicula.setImagen(mapperFiles.inputStreamToByteArray(getClass().
				getResourceAsStream("../resources/aquaman.jpg")));
		pelicula.setImagenPelicula(imagenPelicula);
		peliculaGuardada = peliculaRepository.save(pelicula);
		
		List<PeliculaDTO> peliculas = peliculaService.buscarPorTitulo("Super");
		for(PeliculaDTO p: peliculas) {
			System.out.println(p.getTitulo());
		}
		
		Assertions.assertTrue(peliculas.size() == 2);
		
		//peliculaRepository.deleteAll();
		//generoRepository.deleteAll();
		
		//Assertions.assertTrue(peliculaRepository.findAll().isEmpty() && generoRepository.findAll().isEmpty());
	}
}
