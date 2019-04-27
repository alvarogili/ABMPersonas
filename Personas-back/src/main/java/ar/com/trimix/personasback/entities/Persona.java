package ar.com.trimix.personasback.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import javax.persistence.*;

/**
 * Entidad persona
 * Author: agili
 * Date: 26/04/19
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long perId;

    @Basic(optional = false)
    private String perApellido;

    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyy")
    private Date perFechaNacimiento;

    @Basic(optional = false)
    private String perNombre;

    @Basic(optional = false)
    private Long perNumeroDocumento;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento perTipoDocumento;

    /**
     * Constructor por defecto
     */
    public Persona(){

    }

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

    @JsonProperty("perId")
    public Long getPerId() {
        return perId;
    }

    public void setPerId(Long perId) {
        this.perId = perId;
    }

    @JsonProperty("perApellido")
    public String getPerApellido() {
        return perApellido;
    }

    @JsonProperty("perApellido")
    public void setPerApellido(String perApellido) {
        this.perApellido = perApellido;
    }

    @JsonProperty("perFechaNacimiento")
    public Date getPerFechaNacimiento() {
        return perFechaNacimiento;
    }

    public void setPerFechaNacimiento(Date perFechaNacimiento) {
        this.perFechaNacimiento = perFechaNacimiento;
    }

    @JsonProperty("perNombre")
    public String getPerNombre() {
        return perNombre;
    }

    public void setPerNombre(String perNombre) {
        this.perNombre = perNombre;
    }

    @JsonProperty("perNumeroDocumento")
    public Long getPerNumeroDocumento() {
        return perNumeroDocumento;
    }

    public void setPerNumeroDocumento(Long perNumeroDocumento) {
        this.perNumeroDocumento = perNumeroDocumento;
    }

    @JsonProperty("perTipoDocumento")
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
