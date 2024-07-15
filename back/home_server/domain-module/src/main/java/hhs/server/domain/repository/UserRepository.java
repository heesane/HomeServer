package hhs.server.domain.repository;

import hhs.server.domain.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsByName(String name);

    Optional<User> findByName(String name);
}

