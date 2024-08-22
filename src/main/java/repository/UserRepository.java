package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public List<User> findAllByNomeContainingIgnoreCase (String nome);
	public Optional<User> findByUserAndSenha(String usuario, String senha);
	public Optional<User> findByUser(String usuario);
}
