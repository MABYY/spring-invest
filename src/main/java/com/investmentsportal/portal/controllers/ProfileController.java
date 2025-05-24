package com.investmentsportal.portal.controllers;


import com.investmentsportal.portal.entities.Profile;
import com.investmentsportal.portal.entities.dtos.ProfileDto;
import com.investmentsportal.portal.repositories.ProfileRepository;
import com.investmentsportal.portal.services.ProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/profile")
@Tag(name = "Profile")
public class ProfileController {

   private final ProfileService profileService;
    private ProfileRepository profileRepository;

    // Get all profiles
    @GetMapping
    public ResponseEntity<?> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    // Get profile by ID
    @GetMapping("/{userId}")
    public ResponseEntity<?> getProfileByUserId(@PathVariable Long userId) {
        return profileService.getProfileByUserId(userId);
    }

    // Create a new profile
    @PostMapping("/{userId}")
    public ResponseEntity<?> createProfile(
            @PathVariable Long userId,
            @Valid @RequestBody ProfileDto request) {

        return profileService.createProfile(request, userId);
    }


    // Update profile
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateProfile(
            @PathVariable Long userId,
            @Valid @RequestBody ProfileDto request
    ) {
        return profileService.updateProfile(request,userId);
    };

    // Delete profile
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
//        if (profileRepository.existsById(id)) {
//            profileRepository.deleteById(id);
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
}
