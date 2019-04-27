package ar.com.trimix.personasback.Controllers;

import ar.com.trimix.personasback.entities.Persona;
import ar.com.trimix.personasback.entities.Personas;
import ar.com.trimix.personasback.services.PersonaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Author: agili
 * Date: 26/04/19
 */
@Controller
@RequestMapping(value = "/personas", produces = {APPLICATION_JSON_VALUE})
public class PersonaController {

    @Autowired
    private PersonaServices personaServices;

    @RequestMapping(value = "/", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Personas> obtenerPersonas(){

        Iterable<Persona> personasInBD = personaServices.obtenerPersonas();

        List<Persona> listaPersonas = new ArrayList<>();
        personasInBD.forEach(p -> listaPersonas.add(p));

        return new ResponseEntity<Personas>(new Personas(listaPersonas), HttpStatus.OK);
    }


    public
}
