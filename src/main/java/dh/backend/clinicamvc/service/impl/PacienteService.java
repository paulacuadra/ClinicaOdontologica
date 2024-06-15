package dh.backend.clinicamvc.service.impl;

import dh.backend.clinicamvc.Dto.response.PacienteResponseDto;
import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.repository.IPacienteRepository;
import dh.backend.clinicamvc.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private static Logger logger = LoggerFactory.getLogger(OdontologoService.class);

    private IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente registrarPaciente(Paciente paciente){
        logger.info("Paciente registrado: "+ paciente);
        return pacienteRepository.save(paciente);
    }
    //registrar paciente dto
    public PacienteResponseDto registrar(Paciente paciente) {
        Paciente savedPaciente = pacienteRepository.save(paciente);
        return new PacienteResponseDto(savedPaciente.getId(), savedPaciente.getNombre(), savedPaciente.getApellido(), savedPaciente.getDni());
    }

    public Optional<Paciente> buscarPorId(Integer id){
        return pacienteRepository.findById(id);
    }

    public List<Paciente> buscarTodos(){
        return pacienteRepository.findAll();
    }

    @Override
    public void actualizarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Integer id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteOptional = buscarPorId(id);
        if(pacienteOptional.isPresent()) {
            pacienteRepository.deleteById(id);
            logger.info("Paciente registrado: " + pacienteOptional);
        }
        else {
            throw new ResourceNotFoundException("{\"message\": \"paciente no encontrado\"}");
        }
    }

    public List<Paciente> buscarPorDni(String dni) {return pacienteRepository.findByDni(dni);}
    public List<Paciente> buscarPorProvincia(String provincia){return pacienteRepository.findbyProvinciaLike(provincia);};
}
