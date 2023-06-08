package pl.taw.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.taw.infrastructure.database.entity.DoctorEntity;

import java.util.Optional;

@Repository
public interface DoctorJpaRepository extends JpaRepository<DoctorEntity, Integer> {

    // Zapewnia podstawowe operacje z encjami w bazie danych: dodawanie, usuwanie, aktualizacja i pobieranie
    // Dziedziczy wiele gotowych metod takich jak: findById, findAll, save, delete

    @Override
    Optional<DoctorEntity> findById(Integer doctorId);

}
