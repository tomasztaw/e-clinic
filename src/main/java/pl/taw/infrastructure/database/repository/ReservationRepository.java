package pl.taw.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.taw.controller.dao.ReservationDAO;
import pl.taw.controller.dto.ReservationDTO;
import pl.taw.controller.exception.NotFoundException;
import pl.taw.infrastructure.database.entity.ReservationEntity;
import pl.taw.infrastructure.database.repository.jpa.ReservationJpaRepository;
import pl.taw.infrastructure.database.repository.mapper.ReservationEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class ReservationRepository implements ReservationDAO {

    private final ReservationJpaRepository reservationJpaRepository;
    private final ReservationEntityMapper reservationEntityMapper;

    @Override
    public ReservationEntity findById(Integer id) {
        return reservationJpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Could not found ReservationEntity with id: [%s]".formatted(id)
                ));
    }

    @Override
    public List<ReservationDTO> findReservationByDoctorId(Integer doctorId) {
        return reservationJpaRepository.findAll().stream()
                .filter(reservation -> reservation.getDoctorId().equals(doctorId))
                .map(reservationEntityMapper::mapFromEntity)
                .toList();
    }
}
