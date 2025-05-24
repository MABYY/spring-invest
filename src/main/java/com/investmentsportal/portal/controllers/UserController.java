package com.investmentsportal.portal.controllers;
import com.investmentsportal.portal.entities.Profile;
import com.investmentsportal.portal.entities.Users;
import com.investmentsportal.portal.entities.dtos.requests.users.UserRequest;
import com.investmentsportal.portal.mappers.UserMapper;
import com.investmentsportal.portal.repositories.UsersRepository;
import com.investmentsportal.portal.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Tag(name = "User")
public class UserController {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser( @Valid @RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/all")
    public List<Users> getUsers(){
        return usersRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        return userService.findUser(id);
    };

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePassword( @PathVariable Long id,
                                         @Valid @RequestBody UserRequest request) {
        return userService.updatePassword(id,request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser( @PathVariable Long id) {
        return userService.delete(id);
    };


    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getProfileByUserId(@PathVariable Long id) {
        return userService.getProfileByUserId(id);
    };




}
