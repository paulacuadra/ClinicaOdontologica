package dh.backend.clinicamvc.repository;

import dh.backend.clinicamvc.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {

    //Buscar Paciente por DNI

    @Query("SELECT p FROM Paciente p WHERE p.dni = :dni")
    List<Paciente> findByDni(@Param("dni") String dni);

    @Query("SELECT p FROM Paciente p WHERE LOWER(p.domicilio.provincia) LIKE LOWER(CONCAT('%', :provincia, '%'))")
    List<Paciente> findbyProvinciaLike(@Param("provincia") String provincia);
}
