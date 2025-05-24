package com.investmentsportal.portal.services;

import com.investmentsportal.portal.entities.Profile;
import com.investmentsportal.portal.entities.dtos.ProfileDto;
import com.investmentsportal.portal.entities.dtos.requests.profile.ProfileCreateRequest;
import com.investmentsportal.portal.exceptions.UserNotFoundException;
import com.investmentsportal.portal.mappers.ProfileMapper;
import com.investmentsportal.portal.mappers.UserMapper;
import com.investmentsportal.portal.repositories.ProfileRepository;
import com.investmentsportal.portal.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProfileService {

    private final UsersRepository usersRepository;
    private final ProfileRepository profileRepository;

    private final ProfileMapper profileMapper;
    private final UserMapper userMapper;


    public ResponseEntity<?> createProfile(ProfileDto request, Long userId) {
        var user = usersRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if( user.getProfile() == null) {
            var profile = profileMapper.toEntity(request);
            profile.setUsers(user);
            profileRepository.save(profile);
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.badRequest().body(
                Map.of("error", "Profile already exists") );
    }


    public ResponseEntity<?> getAllProfiles() {
        List<ProfileDto> profiles = profileRepository.findAll().stream().map(profileMapper::toDto).toList();
        return ResponseEntity.ok(profiles);
    }

    public ResponseEntity<?> getProfileByUserId( Long userId) {
        var user = usersRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return ResponseEntity.ok(profileMapper.toDto(user.getProfile()));
    };

    public ResponseEntity<?> updateProfile(ProfileDto request, Long userId) {
        var user = usersRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (user.getProfile() != null) {
            var updatedProfile = user.getProfile();
            updatedProfile.setFirst_name(request.getFirst_name());
            updatedProfile.setLast_name(request.getLast_name());
            updatedProfile.setCompany(request.getCompany());
            profileRepository.save(updatedProfile);
            return ResponseEntity.ok(profileMapper.toDto(updatedProfile));
        }
        return ResponseEntity.badRequest().body(
                Map.of("error", "Profile not found") );


    };


}
