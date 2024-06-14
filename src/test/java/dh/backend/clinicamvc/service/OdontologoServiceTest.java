package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.service.impl.OdontologoService;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class OdontologoServiceTest {
    private static Logger LOGGER = LoggerFactory.getLogger(OdontologoServiceTest.class);
    @Autowired
    private OdontologoService odontologoService;
    private Odontologo odontologo;

    @BeforeEach
    void setUp() {
        odontologo = new Odontologo();
        odontologo.setNombre("Pedro");
        odontologo.setApellido("Perez");
        odontologo.setNroMatricula("123456");
    }

    @Test
    @DisplayName("Testear que un odontologo fue guardado")
    void testOdontologoGuardado(){
        Odontologo odontologoDesdeLaBD = odontologoService.agregarOdontologo(odontologo);
        LOGGER.info("odontologo: "+ odontologo);
        assertNotNull(odontologoDesdeLaBD);
        assertNotNull(odontologoDesdeLaBD.getId());
    }
    @Test
    @DisplayName("Testear busqueda odontologo por id")
    void testOdontologoPorId(){

        Odontologo odontologoGuardado = odontologoService.agregarOdontologo(odontologo);
        Integer id = odontologoGuardado.getId();

        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarUnOdontologo(id);
        Odontologo odontologo1 = odontologoEncontrado.get();

        assertEquals(id, odontologo1.getId());
        assertNotNull(odontologoGuardado.getId());

    }

    @Test
    @DisplayName("Testear busqueda todos los odontologos")
    void testBusquedaTodos() {

        List<Odontologo> odontologos = odontologoService.buscarTodosOdontologos();

        assertTrue(odontologos.size()!=0);

    }

}
