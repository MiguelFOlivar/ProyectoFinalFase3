package com.mfigueroa.app.proyectofinal.security.service;

import com.mfigueroa.app.proyectofinal.security.dto.AuthResponse;
import com.mfigueroa.app.proyectofinal.security.dto.AuthenticationRequest;
import com.mfigueroa.app.proyectofinal.security.dto.RegisterRequest;

public interface AuthService {
	AuthResponse register(RegisterRequest request);
	AuthResponse authenticate(AuthenticationRequest request);
}
