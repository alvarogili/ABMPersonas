package ar.com.trimix.personasback.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

/**
 * Entidad persona
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
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyy")
    private Date perFechaNacimiento;

    @Basic(optional = false)
    private String perNombre;

    @Basic(optional = false)
    private long perNumeroDocumento;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento perTipoDocumento;

    /**
     * Constructor de persona
     * @param perApellido Apellido
     * @param perFechaNacimiento Fecha de nacimiento
     * @param perNombre Nombre
     * @param perNumeroDocumento Número de documento
     * @param perTipoDocumento Tipo de documento definidos en {@link TipoDocumento}
     */
    public Persona(String perApellido, Date perFechaNacimiento, String perNombre, long perNumeroDocumento, TipoDocumento perTipoDocumento) {
        this.perApellido = perApellido;
        this.perFechaNacimiento = perFechaNacimiento;
        this.perNombre = perNombre;
        this.perNumeroDocumento = perNumeroDocumento;
        this.perTipoDocumento = perTipoDocumento;
    }

    public long getPerId() {
        return perId;
    }

    public String getPerApellido() {
        return perApellido;
    }

    public void setPerApellido(String perApellido) {
        this.perApellido = perApellido;
    }

    public Date getPerFechaNacimiento() {
        return perFechaNacimiento;
    }

    public void setPerFechaNacimiento(Date perFechaNacimiento) {
        this.perFechaNacimiento = perFechaNacimiento;
    }

    public String getPerNombre() {
        return perNombre;
    }

    public void setPerNombre(String perNombre) {
        this.perNombre = perNombre;
    }

    public long getPerNumeroDocumento() {
        return perNumeroDocumento;
    }

    public void setPerNumeroDocumento(long perNumeroDocumento) {
        this.perNumeroDocumento = perNumeroDocumento;
    }

    public TipoDocumento getPerTipoDocumento() {
        return perTipoDocumento;
    }

    public void setPerTipoDocumento(TipoDocumento perTipoDocumento) {
        this.perTipoDocumento = perTipoDocumento;
    }

    public void actualizarDatos(Persona persona){
        perNombre = persona.getPerNombre();
        perApellido = persona.getPerApellido();
        perFechaNacimiento = persona.getPerFechaNacimiento();
        perNumeroDocumento = persona.getPerNumeroDocumento();
        perTipoDocumento = persona.getPerTipoDocumento();
    }
}
