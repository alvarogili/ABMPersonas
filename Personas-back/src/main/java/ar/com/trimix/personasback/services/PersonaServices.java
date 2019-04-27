package ar.com.trimix.personasback.services;

import ar.com.trimix.personasback.entities.Persona;
import ar.com.trimix.personasback.entities.TipoDocumento;
import ar.com.trimix.personasback.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Clase que agrupa las acciones a realizar sobre una persona
 * Author: agili
 * Date: 26/04/19
 */
@Component
public class PersonaServices {

    public static final String ACTUALIZACION_FALLIDA = "La persona con nombre %s no existe en la base de datos. Actualización fallida.";
    public static final String ELIMINACION_FALLIDA = "La persona con ID %s no existe en la base de datos. Eliminación fallida.";

    @Autowired
    private PersonaRepository personaRepository;

    public Persona agregarPersona(Persona persona){
        Persona personaGuardada = personaRepository.save(persona);
        return personaGuardada;
    }

    public Page<Persona> obtenerPersonas(Integer numPag,
                                         Integer tamPag,
                                         String perNombre,
                                         String perTipoDocumento){
        Persona persona = new Persona();
        if(isValid(perNombre))
            persona.setPerNombre(perNombre);
        if(isValid(perTipoDocumento))
            persona.setPerTipoDocumento(TipoDocumento.valueOf(perTipoDocumento.toUpperCase()));

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("perNombre", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withIgnoreNullValues();

        Page<Persona> pagePersona = personaRepository.findAll(Example.of(persona, matcher), PageRequest.of(numPag, tamPag));
        return pagePersona;
    }

    private boolean isValid(String dato) {
        return dato != null && !dato.isEmpty();
    }

    public Persona editarPersona(Long id, Persona persona){
        Optional<Persona> findResult = personaRepository.findById(id);
        Persona personaEnBD = findResult.orElseThrow(() -> new IllegalArgumentException(String.format(ACTUALIZACION_FALLIDA, persona.getPerNombre() + " " + persona.getPerApellido())));
        persona.setPerId(personaEnBD.getPerId());
        personaEnBD.actualizarDatos(persona);
        return personaRepository.save(personaEnBD);
    }

    public void eliminarPersona(Long id){
        Optional<Persona> findResult = personaRepository.findById(id);
        Persona personaEnBD = findResult.orElseThrow(() -> new IllegalArgumentException(String.format(ELIMINACION_FALLIDA, id)));
        personaRepository.delete(personaEnBD);
    }
}
