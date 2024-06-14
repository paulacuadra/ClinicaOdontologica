package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.Dto.request.TurnoRequestDto;
import dh.backend.clinicamvc.Dto.response.TurnoResponseDto;
import dh.backend.clinicamvc.entity.Turno;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    TurnoResponseDto registrar(TurnoRequestDto turnoRequestDto) throws BadRequestException;

    TurnoResponseDto buscarPorId(Integer id);

    List<TurnoResponseDto> buscarTodos();
    void actualizarTurno(Integer id, TurnoRequestDto turnoRequestDto);
    void eliminarTurno(Integer id) throws ResourceNotFoundException;
    List<Turno> buscarPorOdontologo(Integer id);
}
