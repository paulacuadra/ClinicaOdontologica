package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.service.impl.OdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private OdontologoService odontologoService;
    private static Logger logger = LoggerFactory.getLogger(OdontologoController.class);


    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(odontologoService.agregarOdontologo(odontologo));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Odontologo>  buscarOdontologoPorId(@PathVariable Integer id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologo = odontologoService.buscarUnOdontologo(id);
        if(odontologo.isPresent()){
            logger.info("Odontologo encontrado: " + odontologo);

            Odontologo odontologoARetornar = odontologo.get();
            return ResponseEntity.ok(odontologoARetornar);
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping
    public ResponseEntity<String> modificarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoOptional = odontologoService.buscarUnOdontologo(odontologo.getId());
        if(odontologoOptional.isPresent()){
            odontologoService.modificarOdontologo(odontologo);
            return ResponseEntity.ok("{\"message\": \"odontologo modificado\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id) throws ResourceNotFoundException {
        try {
            odontologoService.eliminarOdontologo(id);
            logger.info("Odontologo eliminado");
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodosOdontologos());
    }
    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<List<Odontologo>> buscarPorApellido(@PathVariable String apellido){
        List<Odontologo> listaOdontologos = odontologoService.buscarPorApellido(apellido);
        if(!listaOdontologos.isEmpty()){
            return ResponseEntity.ok(listaOdontologos);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Odontologo>> buscarTodos(@PathVariable String nombre){
        return ResponseEntity.ok(odontologoService.buscarPorNombre(nombre));
    }
}
