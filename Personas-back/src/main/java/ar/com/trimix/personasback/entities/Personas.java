package ar.com.trimix.personasback.entities;

import java.util.List;

/**
 * Lista de ersonas para retornar en API REST
 * Author: agili
 * Date: 26/04/19
 */
public class Personas {

    private List<Persona> personas;

    public Personas(List<Persona> personas) {
        this.personas = personas;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
}
