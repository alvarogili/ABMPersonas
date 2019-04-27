package ar.com.trimix.personasback.repositories;

import ar.com.trimix.personasback.entities.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;


/**
 * Repositorio para el CRUD de Personas en la base de datos
 * Author: agili
 * Date: 26/04/19
 */
public interface PersonaRepository extends CrudRepository<Persona, Long>, QueryByExampleExecutor<Persona> {
}
