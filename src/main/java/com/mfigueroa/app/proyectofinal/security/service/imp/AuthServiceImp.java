package com.mfigueroa.app.proyectofinal.security.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mfigueroa.app.proyectofinal.entity.Rol;
import com.mfigueroa.app.proyectofinal.entity.Usuario;
import com.mfigueroa.app.proyectofinal.repository.IUsuarioRepository;
import com.mfigueroa.app.proyectofinal.security.dto.AuthResponse;
import com.mfigueroa.app.proyectofinal.security.dto.AuthenticationRequest;
import com.mfigueroa.app.proyectofinal.security.dto.RegisterRequest;
import com.mfigueroa.app.proyectofinal.security.service.AuthService;
import com.mfigueroa.app.proyectofinal.security.service.JWTService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService{
	private final PasswordEncoder passwordEncoder;
	private final IUsuarioRepository usuarioRepository;
	private final JWTService jwtService;
	private final AuthenticationManager authenticationManager;
	
	@Override
	public AuthResponse register(RegisterRequest request) {
		
		List<Rol> roles = new ArrayList<>();
		Rol rol = new Rol();
		rol.setNombreRol("ROLE_USER");
		roles.add(rol);
		var user = Usuario.builder()
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.roles(roles)
				.build();
		usuarioRepository.save(user);
		var jwttoken = jwtService.generateToken(user);
		return AuthResponse.builder().token(jwttoken).build();
	}

	@Override
	public AuthResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(),
						request.getPassword()));
		
		var user = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthResponse.builder().token(jwtToken).build();
	}
	
}
