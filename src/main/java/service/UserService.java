package service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import model.User;
import model.UserLogin;
import repository.UserRepository;

public class UserService {

	@Autowired
	private UserRepository repository;

	public User CadastrarUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(user.getSenha());
		user.setSenha(senhaEncoder);

		return repository.save(user);
	}

	public Optional<UserLogin> Logar(Optional<UserLogin> userL) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<User> user = repository.findByUser(userL.get().getUser());

		if (user.isPresent()) {
			if (encoder.matches(user.get().getSenha(), user.get().getSenha())) {

				String auth = userL.get().getUser() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				userL.get().setToken(authHeader);
				userL.get().setVendedor(user.get().isVendedor());
				userL.get().setNome(user.get().getNome());

				return userL;

			}
		}
		return null;
	}
}
