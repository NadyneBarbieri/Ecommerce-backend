package com.nadyne.Akilahyz.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nadyne.Akilahyz.model.UserLoginModel;
import com.nadyne.Akilahyz.model.UserModel;
import com.nadyne.Akilahyz.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public Optional<UserModel> cadastrarUsuario(UserModel user) {

		if (userRepository.findByEmail(user.getEmail()).isPresent())
			return Optional.empty();

		user.setPassword(criptografarSenha(user.getPassword()));

		return Optional.of(userRepository.save(user));

	}

	public Optional<UserModel> atualizarUsuario(UserModel user) {

		if (userRepository.findById(user.getId()).isPresent()) {

			Optional<UserModel> buscaUsuario = userRepository.findByEmail(user.getEmail());

			if ((buscaUsuario.isPresent()) && (buscaUsuario.get().getId() != user.getId()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

			user.setPassword(criptografarSenha(user.getPassword()));

			return Optional.ofNullable(userRepository.save(user));

		}

		return Optional.empty();

	}

	public Optional<UserLoginModel> autenticarUsuario(Optional<UserLoginModel> usuarioLogin) {

		Optional<UserModel> usuario = userRepository.findByEmail(usuarioLogin.get().getEmail());

		if (usuario.isPresent()) {

			if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getPassword())) {

				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getName());
				usuarioLogin.get().setFoto(usuario.get().getPhoto());
				usuarioLogin.get()
						.setToken(gerarBasicToken(usuarioLogin.get().getEmail(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getPassword());
				usuarioLogin.get().setTipo(usuario.get().getType());

				return usuarioLogin;
			}

		}

		return Optional.empty();
	}

	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(senha);
	}

	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.matches(senhaDigitada, senhaBanco);

	}

	private String gerarBasicToken(String email, String senha) {
	    String token = email + ":" + senha;
	    String tokenBase64 = Base64.getEncoder().encodeToString(token.getBytes(StandardCharsets.US_ASCII));
	    return "Basic " + tokenBase64;
	}

	public void deleteById(Long id) {
		Optional<UserModel> usuario = userRepository.findById(id);

		if (usuario.isPresent()) {
			userRepository.deleteById(id);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
		}
	}
	
}
