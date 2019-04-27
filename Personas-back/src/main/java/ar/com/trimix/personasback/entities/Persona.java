package ar.com.trimix.Personasback.entities;

import java.util.Date;
import javax.persistence.*;

/**
 * Author: agili
 * Date: 26/04/19
 */
@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private long perId;

    @Basic(optional = false)
    private String perApellido;

    @Basic(optional = false)
    private Date perFechaNacimiento;

    @Basic(optional = false)
    private String perNombre;

    @Basic(optional = false)
    private long perNumeroDocumento;

    @Basic(optional = false)
    private TipoDocumento tipoDocumento;

    /**
     * Constructor de persona
     * @param perApellido Apellido
     * @param perFechaNacimiento Fecha de nacimiento
     * @param perNombre Nombre
     * @param perNumeroDocumento Número de documento
     * @param tipoDocumento Tipo de documento definidos en {@}
     */
    public Persona(String perApellido, Date perFechaNacimiento, String perNombre, long perNumeroDocumento, TipoDocumento tipoDocumento) {
        this.perApellido = perApellido;
        this.perFechaNacimiento = perFechaNacimiento;
        this.perNombre = perNombre;
        this.perNumeroDocumento = perNumeroDocumento;
        this.tipoDocumento = tipoDocumento;
    }

    public long getPerId() {
        return perId;
    }

    public void setPerId(long perId) {
        this.perId = perId;
    }
}
