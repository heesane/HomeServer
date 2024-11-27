package hhs.server.domain.repository.jpa;

import hhs.server.domain.persistence.Badge;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge,Long> {
  List<Badge> findAll();

  boolean existsByName(String name);
}
