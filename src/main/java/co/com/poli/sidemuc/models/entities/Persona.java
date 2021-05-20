package co.com.poli.sidemuc.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "numero_documento", updatable = false, unique = true, nullable = false)
    private Long numeroDocumento;

    @NotEmpty(message = "no puede estar vacío")
    @Column(name = "nombre_persona", nullable = false)
    private String nombrePersona;

    @NotEmpty(message = "no puede estar vacío")
    @Column(name = "apellido_persona")
    private String apellidoPersona;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "edad")
    private Long edad;

    @NotEmpty(message = "no puede estar vacío")
    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private Long telefono;

    @NotEmpty(message = "no puede estar vacío")
    @Column(name = "sexo")
    private String sexo;

    @NotEmpty(message = "no puede estar vacío")
    @Email(message = "no es una dirección de correo con formato válido")
    @Column(name = "correo")
    private String correo;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deporte_id")
    private Deporte deporte;

    private String foto;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
