package dh.backend.clinicamvc.service.impl;


import dh.backend.clinicamvc.Dto.response.OdontologoResponseDto;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.repository.IOdontologoRepository;
import dh.backend.clinicamvc.service.IOdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private static Logger logger = LoggerFactory.getLogger(OdontologoService.class);
    private IOdontologoRepository odontologoRepository;

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo agregarOdontologo(Odontologo odontologo){
        logger.info("Odontólogo guardado: "+ odontologo);
        return odontologoRepository.save(odontologo);
    }

    //registrar odontologoDto
    public OdontologoResponseDto registrar(Odontologo odontologo) {
        Odontologo savedOdontologo = odontologoRepository.save(odontologo);
        return new OdontologoResponseDto(savedOdontologo.getId(), savedOdontologo.getNombre(), savedOdontologo.getApellido(), savedOdontologo.getNroMatricula());
    }
    public Optional<Odontologo> buscarUnOdontologo(Integer id){
        return odontologoRepository.findById(id);
    }
    public List<Odontologo> buscarTodosOdontologos(){
        return odontologoRepository.findAll();
    }

    @Override
    public void modificarOdontologo(Odontologo odontologo) {
        odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminarOdontologo(Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoOptional = buscarUnOdontologo(id);
        if(odontologoOptional.isPresent()){
            logger.info("Odontólogo guardado: "+ odontologoOptional);
            odontologoRepository.deleteById(id);}
        else {throw new ResourceNotFoundException("{\"message\": \"odontologo no encontrado\"}");}
    }
    @Override
    public List<Odontologo> buscarPorApellido(String apellido) {
        return odontologoRepository.buscarPorApellido(apellido);
    }

    @Override
    public List<Odontologo> buscarPorNombre(String nombre) {
        return odontologoRepository.findByNombreLike(nombre);
    }
}
