package pl.taw.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.taw.infrastructure.database.entity.ReservationEntity;

@Repository
public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, Integer> {


}
