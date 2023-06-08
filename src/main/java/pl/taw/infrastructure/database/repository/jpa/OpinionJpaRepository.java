package pl.taw.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.taw.infrastructure.database.entity.OpinionEntity;

public interface OpinionJpaRepository extends JpaRepository<OpinionEntity, Integer> {


}
