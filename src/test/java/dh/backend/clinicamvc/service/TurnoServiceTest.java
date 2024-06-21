package dh.backend.clinicamvc.service;


import dh.backend.clinicamvc.Dto.request.TurnoRequestDto;
import dh.backend.clinicamvc.Dto.response.OdontologoResponseDto;
import dh.backend.clinicamvc.Dto.response.PacienteResponseDto;
import dh.backend.clinicamvc.Dto.response.TurnoResponseDto;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.entity.Turno;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.service.impl.OdontologoService;
import dh.backend.clinicamvc.service.impl.PacienteService;
import dh.backend.clinicamvc.service.impl.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class TurnoServiceTest {


    @Autowired
    private TurnoService turnoService;


    @Autowired
    private PacienteService pacienteService;


    @Autowired
    private OdontologoService odontologoService;

    private TurnoRequestDto turnoDTO;
    private Paciente paciente;
    private Odontologo odontologo;
    private Turno turno;
    private static Logger logger = LoggerFactory.getLogger(TurnoServiceTest.class);


    @BeforeEach
    public void setUp() throws BadRequestException{
        turno = new Turno();
        turno.setId(1);

        // Registra un nuevo paciente
        paciente = new Paciente();
        paciente.setNombre("Roberto");
        paciente.setApellido("Sanchéz");
        paciente.setDni("16589698");
        paciente.setFechaIngreso(LocalDate.of(2023, 4, 20));
    //    paciente = pacienteService.registrar(nuevoPaciente); // Asegúrate de que este método retorna un PacienteResponseDto


        // Registra un nuevo odontólogo
        odontologo = new Odontologo();
        odontologo.setNombre("Carlos");
        odontologo.setApellido("Perez");
        odontologo.setNroMatricula("12563");
      //  odontologo = odontologoService.registrar(nuevoOdontologo); // Asegúrate de que este método retorna un OdontologoResponseDto


        // Crea el DTO del turno usando los IDs del paciente y odontólogo recién registrados

            turno.setFecha(LocalDate.parse("2023-04-20"));
            turno.setHora(LocalTime.parse("10:30"));// Asegúrate de que el formato de la fecha es correcto
            turno.setPaciente(paciente);
            turno.setOdontologo(odontologo);

    }

    @Test
    public void testCrearTurno() throws Exception {
        PacienteResponseDto pacienteDesdeLaBD = pacienteService.registrar(paciente);
        OdontologoResponseDto odontologoDesdeBD = odontologoService.registrar(odontologo);
        turnoDTO = new TurnoRequestDto(paciente.getId(), odontologo.getId(),"2024-10-26", "10:30");

        TurnoResponseDto turnoDesdeLaBD = turnoService.registrar(turnoDTO);
        assertNotNull(turnoDesdeLaBD);
//         turnoService.registrar(turno);
//        List<TurnoResponseDto> buscarTurno = turnoService.buscarTodos();
//        assertFalse(buscarTurno.isEmpty());
    }
    @Test
    @DisplayName("Testear busqueda turno por id")
    public void testBuscarTurno() throws BadRequestException, ResourceNotFoundException {
        PacienteResponseDto pacienteDesdeLaBD = pacienteService.registrar(paciente);
        OdontologoResponseDto odontologoDesdeBD = odontologoService.registrar(odontologo);
        turnoDTO = new TurnoRequestDto(paciente.getId(), odontologo.getId(),"2024-10-26", "10:30");

        TurnoResponseDto crearTurno =  turnoService.registrar(turnoDTO);
        Integer id = crearTurno.getId();
        TurnoResponseDto turnoEncontrado = turnoService.buscarPorId(id);
        assertEquals(id, turnoEncontrado.getId());
    }

    @Test
    @DisplayName("Testear busqueda todos los turnos")
    void testBusquedaTodos() {
        List<TurnoResponseDto> turnos = turnoService.buscarTodos();

        logger.info("lista "+turnos.size());
        assertFalse(turnos.isEmpty());
    }
}

