package dh.backend.clinicamvc.service;


import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo agregarOdontologo(Odontologo odontologo) throws BadRequestException;

    Optional<Odontologo> buscarUnOdontologo(Integer id) throws ResourceNotFoundException;
    List<Odontologo> buscarTodosOdontologos();

    void modificarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Integer id) throws ResourceNotFoundException;

    // Metodos con HQL
    List<Odontologo> buscarPorApellido(String apellido);
    List<Odontologo> buscarPorNombre(String nombre);
}
