package com.izobretatel777.chat.controller;

import com.izobretatel777.chat.dto.JwtRequest;
import com.izobretatel777.chat.dto.JwtResponse;
import com.izobretatel777.chat.security.JwtUtil;
import com.izobretatel777.chat.service.JwtUserDetailsService;
import com.izobretatel777.chat.service.KeyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class JwtAuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final KeyService keyService;

    @PostMapping
    @Operation(summary = "Login (get JWT token)")
    public ResponseEntity<?> authenticate(@RequestBody JwtRequest jwtRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        var userDetails = jwtUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        var token = jwtUtil.generateToken(userDetails);
        var decryptionKey = keyService.getKeyByLogin(userDetails.getUsername());
        return ResponseEntity.ok(JwtResponse.builder().token(token).decryptionKey(decryptionKey.getValue()).build());
    }
}