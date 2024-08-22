package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dto.UserDTO;
import dto.UserLoginDTO;
import model.User;
import repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setNome(user.getNome());
        dto.setUsuario(user.getUsuario());
        return dto;
    }

    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setNome(dto.getNome());
        user.setUsuario(dto.getUsuario());
        return user;
    }

    public Optional<UserLoginDTO> logar(Optional<UserLoginDTO> userL) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<User> usuario = repository.findByUsuario(userL.get().getUsuario());

        if (usuario.isPresent()) {
            if (encoder.matches(userL.get().getSenha(), usuario.get().getSenha())) {
                return Optional.of(userL.get());
            }
        }
        return Optional.empty();
    }

    public User cadastrarUser(UserDTO usuarioDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User usuario = toEntity(usuarioDTO);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }

    public User atualizarUser(UserDTO usuarioDTO) {
        User usuario = toEntity(usuarioDTO);
        return repository.save(usuario);
    }
}
