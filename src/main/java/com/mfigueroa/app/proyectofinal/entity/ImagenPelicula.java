package com.mfigueroa.app.proyectofinal.entity;

import lombok.Data;

@Data
public class ImagenPelicula {
	private Integer id;
	private String nombreArchivo;
	private byte[] imagen;
}
