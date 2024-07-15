package hhs.server.domain.repository;

import hhs.server.domain.domain.RoadMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadMapRepository extends JpaRepository<RoadMap, Long> {

}
