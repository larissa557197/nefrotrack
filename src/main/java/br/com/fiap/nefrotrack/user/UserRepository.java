package br.com.fiap.nefrotrack.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByGithubId(String githubId);
}
