package controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.UserDTO;
import dto.UserLoginDTO;
import model.User;
import repository.UserRepository;
import service.UserService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService usuarioService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        List<User> users = repository.findAll();
        List<UserDTO> userDTOs = users.stream()
                                      .map(usuarioService::toDTO)
                                      .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOs);
    }

    @PostMapping("/logar")
    public ResponseEntity<UserLoginDTO> autentication(@RequestBody Optional<UserLoginDTO> user) {
        return usuarioService.logar(user).map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable long id) {
        return repository.findById(id)
                .map(usuarioService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UserDTO> post(@RequestBody UserDTO usuarioDTO) {
        User newUser = usuarioService.cadastrarUser(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.toDTO(newUser));
    }

    @PutMapping
    public ResponseEntity<UserDTO> put(@RequestBody UserDTO usuarioDTO) {
        User updatedUser = usuarioService.atualizarUser(usuarioDTO);
        return ResponseEntity.ok(usuarioService.toDTO(updatedUser));
    }
}
