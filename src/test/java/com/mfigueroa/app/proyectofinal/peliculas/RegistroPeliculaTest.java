package com.mfigueroa.app.proyectofinal.peliculas;

import java.io.IOException;
import java.util.ArrayList;
import  java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mfigueroa.app.proyectofinal.entity.Genero;
import com.mfigueroa.app.proyectofinal.entity.ImagenPelicula;
import com.mfigueroa.app.proyectofinal.entity.Pelicula;
import com.mfigueroa.app.proyectofinal.model.MapperFiles;
import com.mfigueroa.app.proyectofinal.repository.IGeneroRepository;
import com.mfigueroa.app.proyectofinal.repository.IPeliculaRepository;

@SpringBootTest
public class RegistroPeliculaTest {
	@Autowired
	IGeneroRepository generoRepository;
	@Autowired
	private MapperFiles mapperFiles;
	@Autowired
	IPeliculaRepository peliculaRepository;
	
	@Test
	public void validarRegistroPelicula() throws IOException {
		Genero genero1 = new Genero();
		genero1.setNombreGenero("genero1");
		generoRepository.save(genero1);
		
		Genero genero2 = new Genero();
		genero2.setNombreGenero("genero2");
		generoRepository.save(genero2);
		
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo("Wish");
		pelicula.setUrlWeb("https://www.disney.es/peliculas/wish-el-poder-de-los-deseos");
		
		List<Genero> generos = new ArrayList<>();
		generos.add(genero1);
		generos.add(genero2);
		pelicula.setGeneros(generos);
		
		ImagenPelicula imagenPelicula = new ImagenPelicula();
		imagenPelicula.setNombreArchivo("wish.jpg");
		imagenPelicula.setImagen(mapperFiles.inputStreamToByteArray(getClass().
				getResourceAsStream("../resources/wish.jpg")));
		pelicula.setImagenPelicula(imagenPelicula);
		Pelicula peliculaGuardada = peliculaRepository.save(pelicula);
		
		List<Pelicula> peliculas = peliculaRepository.findAll();
		Assertions.assertTrue(!peliculas.isEmpty() && peliculas.get(0).getId().equals(peliculaGuardada.getId()));
		
		peliculaRepository.deleteAll();
		generoRepository.deleteAll();
		Assertions.assertTrue(peliculaRepository.findAll().isEmpty() && generoRepository.findAll().isEmpty());
	}
}
