package com.investmentsportal.portal.services;

import com.investmentsportal.portal.entities.Profile;
import com.investmentsportal.portal.entities.dtos.requests.users.UserRequest;
import com.investmentsportal.portal.exceptions.UserNotFoundException;
import com.investmentsportal.portal.mappers.UserMapper;
import com.investmentsportal.portal.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@AllArgsConstructor
@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public ResponseEntity<?> createUser(UserRequest request) {
        // Check if email is already in the database
        var user = usersRepository.findByEmail(request.getEmail()).orElse(null);
        if( user == null){
            var new_user = userMapper.toEntity(request);
            new_user.setPassword(passwordEncoder.encode(request.getPassword()));
            usersRepository.save(new_user);
            return ResponseEntity.ok(userMapper.toDto(new_user));
        } else {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Email not valid")
            );
        }
    }


    public ResponseEntity<?> findUser(Long id) {
        var user = usersRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    public ResponseEntity<?> updatePassword(Long id, UserRequest request ) {
        var user = usersRepository.findById(id).orElseThrow(UserNotFoundException::new);
        var user_updated = userMapper.update( request , user);
        request.setId(user.getId());
        return ResponseEntity.ok(userMapper.toDto(user_updated));
    };

    public ResponseEntity<?> delete(Long id) {
        var user = usersRepository.findById(id).orElseThrow(UserNotFoundException::new);
        usersRepository.deleteById(id);
        return ResponseEntity.ok(userMapper.toDto(user));
    };

    public ResponseEntity<?> getProfileByUserId( Long id) {
        var user = usersRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if (user.getProfile() != null) {
            return ResponseEntity.ok(user.getProfile());
        }
        return ResponseEntity.badRequest().body(
                 Map.of("error", "Email not valid") );
    };
}



