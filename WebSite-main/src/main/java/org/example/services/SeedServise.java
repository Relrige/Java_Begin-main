package org.example.services;

import lombok.AllArgsConstructor;
import org.example.entities.RoleEntity;
import org.example.entities.UserEntity;
import org.example.entities.UserRoleEntity;
import org.example.repositories.RoleRepository;
import org.example.repositories.UserRepository;
import org.example.repositories.UserRoleRepository;
import org.example.constants.Roles;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class SeedServise {
    private  final RoleRepository roleRepository;
    private  final UserRepository userRepository;
    private  final UserRoleRepository userRoleRepository;
    private  final PasswordEncoder passwordEncoder;

    public void seedRoleData(){
        if (roleRepository.count()==0){
            var admin = RoleEntity
                    .builder()
                    .name(Roles.Admin)
                    .build();

            roleRepository.save(admin);
            var user = RoleEntity
                    .builder()
                    .name(Roles.User)
                    .build();
            roleRepository.save(user);
        }
    }

    public  void seedUserData(){
        if (userRepository.count()==0){
            var user= UserEntity
                    .builder()
                    .email("vasiliy@emailcom")
                    .firstName("Sergiy")
                    .lastName("Semenov")
                    .phone("380961475654")
                    .password(passwordEncoder.encode("123456"))
                    .build();
            userRepository.save(user);
            var role = roleRepository.findByName(Roles.Admin);
            var ur = UserRoleEntity
                    .builder()
                    .role(role)
                    .user(user)
                    .build();
            userRoleRepository.save(ur);
        }

    }



}
