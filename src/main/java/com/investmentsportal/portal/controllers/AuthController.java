package com.investmentsportal.portal.controllers;

import com.investmentsportal.portal.entities.dtos.JwtResponseDTO;
import com.investmentsportal.portal.entities.dtos.UserDto;
import com.investmentsportal.portal.entities.dtos.requests.users.UserRequest;
import com.investmentsportal.portal.exceptions.UserNotFoundException;
import com.investmentsportal.portal.mappers.UserMapper;
import com.investmentsportal.portal.repositories.UsersRepository;
import com.investmentsportal.portal.services.JwtService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@AllArgsConstructor
@Tag(name = "Auth")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody UserRequest request
    ){

        // Authenticate user

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                ));

        // Generate jwt
        var user = usersRepository.findByEmail(request.getEmail()).orElseThrow(
                ()-> new UsernameNotFoundException("Invalid credentials")
        );
        var token = jwtService.generateToken(user);
        return ResponseEntity.ok( new JwtResponseDTO(token));
    };

    @PostMapping("/validate")
    public Boolean validate( @RequestHeader("Authorization") String token ){
//        System.out.println("validate called" );
        return jwtService.validateToken(token.substring(7));
    };


    @GetMapping("/me")
    public ResponseEntity<?> me(){
        // Get security auth object
        var authentication = SecurityContextHolder.getContext().getAuthentication();

//        var email = (String) authentication.getPrincipal();
//        var user = usersRepository.findByEmail(email).orElse(null);
//        System.out.println("email " + email);

//        System.out.println("userID " + authentication.getPrincipal());
        var userID = (Long) authentication.getPrincipal();
        var user = usersRepository.findById(userID).orElse(null);


        if (user == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userMapper.toDto(user));
        }
    };

}

//        var user = usersRepository.findByEmail(request.getEmail()).orElseThrow(UserNotValidException::new);
//        if ( !passwordEncoder.matches(request.getPassword(), user.getPassword())){
//            throw new AccessDeniedException("Forbidden login");
//        } else {
//            return ResponseEntity.ok().build();
//        }