package com.dimension.security.controller;

import com.dimension.security.dto.JWTAuthResponseDTO;
import com.dimension.security.dto.LoginDTO;
import com.dimension.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // (A)
    // Retorna el token JWT
    // http://localhost:8080/api/auth/login
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO) {

        // authenticationManager.authenticate:
        // Autentica el objeto Authentication pasado y retorna un objeto Authentication
        // completo (incluido las autorizaciones otorgadas), en caso no autentique, arroja una excepcion
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        // SecurityContextHolder:
        // Almacena los detalles sobre los usuarios que ya estan autenticados
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Se obtiene el token
        String token = jwtTokenProvider.generarToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponseDTO(token));
    }
}

  /* (A)
     POSTMAN:
        Authorization:
            Type: No Auth

		Body:
			--> raw	    --> JSON
			{
				"username" : "luis",
				"password" : "luis123"
			}
   */
