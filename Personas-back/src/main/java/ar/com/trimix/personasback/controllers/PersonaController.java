package ar.com.trimix.personasback.controllers;

import ar.com.trimix.personasback.entities.Persona;
import ar.com.trimix.personasback.entities.Personas;
import ar.com.trimix.personasback.services.PersonaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Author: agili
 * Date: 26/04/19
 */
@Controller
@RequestMapping(value = "/api", produces = {APPLICATION_JSON_VALUE})
public class PersonaController {

    @Autowired
    private PersonaServices personaServices;

    @RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Personas> obtenerPersonas(
            @RequestParam(value = "numPag", required = false, defaultValue="0") Integer numPag,
            @RequestParam(value = "tamPag", required = false, defaultValue="20") Integer tamPag,
            @RequestParam(value = "perNombre", required = false) String perNombre,
            @RequestParam(value = "perTipoDocumento", required = false) String perTipoDocumento){

        Iterable<Persona> personasInBD = personaServices.obtenerPersonas(numPag, tamPag, perNombre, perTipoDocumento);

        List<Persona> listaPersonas = new ArrayList<>();
        personasInBD.forEach(p -> listaPersonas.add(p));

        return new ResponseEntity<Personas>(new Personas(listaPersonas), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> agregarPersona(@RequestBody Persona persona){
        Persona personaGuardada = personaServices.agregarPersona(persona);
        return new ResponseEntity<Persona>(persona, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> editarPersona(@PathVariable Long id, @RequestBody Persona persona){
        Persona personaGuardada = personaServices.editarPersona(id, persona);
        return new ResponseEntity<Persona>(persona, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity borrarPersona(@PathVariable Long id){
        personaServices.eliminarPersona(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
