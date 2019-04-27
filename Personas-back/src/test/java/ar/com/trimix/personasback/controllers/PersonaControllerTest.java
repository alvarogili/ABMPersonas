package ar.com.trimix.personasback.controllers;

import ar.com.trimix.personasback.entities.Persona;
import ar.com.trimix.personasback.entities.Personas;
import ar.com.trimix.personasback.entities.TipoDocumento;
import ar.com.trimix.personasback.repositories.PersonaRepository;
import ar.com.trimix.personasback.services.PersonaServices;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Author: agili
 * Date: 27/04/19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test_context.xml")
public class PersonaControllerTest {

    @Autowired
    private PersonaController personaController;

    @Autowired
    private PersonaRepository personaRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Persona alvaro;

    @Before
    public void setUp(){
        personaRepository.deleteAll();

        alvaro = new Persona(
                "Gili",
                new Date(1982, 07, 07),
                "Alvaro",
                29494566,
                TipoDocumento.DNI);
        alvaro = personaRepository.save(alvaro);
    }

    @Test
    public void obtenerPersonas() {

        ResponseEntity<Personas> personas = personaController.
                obtenerPersonas(0,10, null, null);
        assertEquals(HttpStatus.OK, personas.getStatusCode());
        assertEquals(alvaro.getPerNombre(), personas.getBody().getPersonas().get(0).getPerNombre());
    }

    @Test
    public void obtenerPersonasInexistentes() {

        ResponseEntity<Personas> personas = personaController.
                obtenerPersonas(0,10, "pepe", null);
        assertEquals(HttpStatus.OK, personas.getStatusCode());
        assertEquals(0, personas.getBody().getPersonas().size());
    }

    @Test
    public void agregarPersona() {
        Persona pepe = new Persona(
                "Argento",
                new Date(1975, 03, 27),
                "Pepe",
                15321012,
                TipoDocumento.PASAPORTE);

        ResponseEntity<Persona> personaResponseEntity = personaController.agregarPersona(pepe);
        assertEquals(HttpStatus.OK, personaResponseEntity.getStatusCode());
        assertNotNull(personaResponseEntity.getBody().getPerId());
    }

    @Test
    public void editarPersona() {

        Persona alvaroEdited = new Persona(
                "Gili",
                new Date(1982, 07, 07),
                "AlvaroEdited",
                29494566,
                TipoDocumento.CEDULA);

        ResponseEntity<Persona> personaResponseEntity = personaController.editarPersona(alvaro.getPerId(), alvaroEdited);
        assertEquals(HttpStatus.OK, personaResponseEntity.getStatusCode());
        assertEquals("AlvaroEdited", personaResponseEntity.getBody().getPerNombre());
        assertEquals(TipoDocumento.CEDULA, personaResponseEntity.getBody().getPerTipoDocumento());
    }

    @Test
    public void editarPersonaInexistente() {

        Persona alvaroEdited = new Persona(
                "Gili",
                new Date(1982, 07, 07),
                "AlvaroEdited",
                29494566,
                TipoDocumento.CEDULA);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(String.format(PersonaServices.ACTUALIZACION_FALLIDA, "AlvaroEdited Gili"));

        ResponseEntity<Persona> personaResponseEntity = personaController.editarPersona(-1L, alvaroEdited);
    }

    @Test
    public void borrarPersona() {

        ResponseEntity responseEntity = personaController.borrarPersona(alvaro.getPerId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void borrarPersonaInexistente() {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(String.format(PersonaServices.ELIMINACION_FALLIDA, "-1"));

        ResponseEntity responseEntity = personaController.borrarPersona(-1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }
}