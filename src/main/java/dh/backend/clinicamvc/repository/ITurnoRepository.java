package dh.backend.clinicamvc.repository;


import dh.backend.clinicamvc.Dto.response.TurnoResponseDto;
import dh.backend.clinicamvc.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ITurnoRepository extends JpaRepository<Turno, Integer> {

        //Buscar turnos entre dos fechas
        @Query("Select t from Turno t where t.fecha BETWEEN :startDate and :endDate")
        List<Turno> buscarTurnoEntreFechas(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);

    //Buscar por odontologo

        @Query("SELECT t FROM Turno t WHERE t.odontologo.id = :odontologoId")
        List<Turno> findByOdontologoId(@Param("odontologoId") Integer odontologoId);


}
