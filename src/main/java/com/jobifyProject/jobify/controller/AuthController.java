package com.jobifyProject.jobify.controller;

import com.jobifyProject.jobify.converter.UserConverter;
import com.jobifyProject.jobify.dto.UserDto;
import com.jobifyProject.jobify.dto.request.LoginRequest;
import com.jobifyProject.jobify.dto.response.JwtResponse;
import com.jobifyProject.jobify.model.EnumRole;
import com.jobifyProject.jobify.model.Role;
import com.jobifyProject.jobify.model.User;
import com.jobifyProject.jobify.model.UserDetailsImpl;
import com.jobifyProject.jobify.security.jwt.JWTService;
import com.jobifyProject.jobify.service.RoleService;
import com.jobifyProject.jobify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {

        if (userService.existsByUsername(userDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (userService.existsByEmail(userDto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        User user = userConverter.dtoToModel(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<String> strRoles = userDto.getRoles();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleService.findByName(EnumRole.ROLE_USER);
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleService.findByName(EnumRole.ROLE_ADMIN);
                        roles.add(adminRole);
                    }
                    case "company" -> {
                        Role companyRole = roleService.findByName(EnumRole.ROLE_COMPANY);
                        roles.add(companyRole);
                    }
                    default -> {
                        Role userRole = roleService.findByName(EnumRole.ROLE_USER);
                        roles.add(userRole);
                    }
                }
            });
        }

        user.setRoles(roles);
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateJwToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles
        ));
    }
}
