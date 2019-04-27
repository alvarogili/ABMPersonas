package ar.com.trimix.personasback.services;

import ar.com.trimix.personasback.entities.Persona;
import ar.com.trimix.personasback.entities.TipoDocumento;
import ar.com.trimix.personasback.repositories.PersonaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Author: agili
 * Date: 27/04/19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test_context.xml")
public class PersonaServicesTest {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaServices personaServices;

    private Persona alvaro;

    @Before
    public void setUp() throws Exception {
        personaRepository.deleteAll();

        alvaro = new Persona(
                "Gili",
                new Date(1982, 07, 07),
                "Alvaro",
                29494566,
                TipoDocumento.DNI);
        alvaro = personaServices.agregarPersona(alvaro);

        Persona pepe = new Persona(
                "Argento",
                new Date(1975, 03, 27),
                "Pepe",
                15321012,
                TipoDocumento.PASAPORTE);
        personaServices.agregarPersona(pepe);
    }

    @Test
    public void obtener2Personas() {


        assertEquals(2L, personaServices.obtenerPersonas(0,100,null, null).getTotalElements());
    }

    @Test
    public void obtener1PersonaPorPagina() {
        Page<Persona> personaList = personaServices.obtenerPersonas(0,1,null, null);
        List<Persona> personas = personaList.getContent();
        assertEquals(1L,personas.size());
        assertEquals("Alvaro",personas.get(0).getPerNombre());
    }

    @Test
    public void editarPersona() {
        String FRANCO = "Franco";
        alvaro.setPerNombre(FRANCO);
        alvaro = personaServices.editarPersona(alvaro);
        assertEquals(FRANCO, alvaro.getPerNombre());
    }

    @Test
    public void eliminarPersona() {

        personaServices.eliminarPersona(alvaro.getPerId());
        assertEquals(1L, personaServices.obtenerPersonas(0,100,null, null).getTotalElements());
    }
}