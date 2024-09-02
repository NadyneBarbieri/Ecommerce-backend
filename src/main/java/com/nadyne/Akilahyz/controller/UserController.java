package com.nadyne.Akilahyz.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadyne.Akilahyz.dto.UserDTO;
import com.nadyne.Akilahyz.model.UserLoginModel;
import com.nadyne.Akilahyz.model.UserModel;
import com.nadyne.Akilahyz.repository.UserRepository;
import com.nadyne.Akilahyz.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserModel> users = userRepository.findAll();
        List<UserDTO> userDTOs = users.stream()
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPhoto(), user.getType()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok(new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPhoto(), user.getType())))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getByEmail(@PathVariable String email) {
        return userRepository.findByEmail(email)
                .map(user -> ResponseEntity.ok(new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPhoto(), user.getType())))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping("/logar")
    public ResponseEntity<UserLoginModel> login(@RequestBody Optional<UserLoginModel> usuarioLogin) {
        return userService.autenticarUsuario(usuarioLogin)
                .map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UserDTO> registrar(@Valid @RequestBody UserModel user) {
        return userService.cadastrarUsuario(user)
                .map(userModel -> new UserDTO(userModel.getId(), userModel.getName(), userModel.getEmail(), userModel.getPhoto(), userModel.getType()))
                .map(userDTO -> ResponseEntity.status(HttpStatus.CREATED).body(userDTO))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
    
    @PutMapping("/atualizar")
    public ResponseEntity<UserDTO> atualizar(@Valid @RequestBody UserModel user) {
        return userService.atualizarUsuario(user)
                .map(userModel -> new UserDTO(userModel.getId(), userModel.getName(), userModel.getEmail(), userModel.getPhoto(), userModel.getType()))
                .map(userDTO -> ResponseEntity.status(HttpStatus.OK).body(userDTO))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}