package co.com.poli.sidemuc.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "escenarios")
@AllArgsConstructor
@NoArgsConstructor
public class Escenario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nombre_escenario")
    private String nombreEscenario;

    @Column(name = "barrio")
    private String barrio;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "fecha_mantenimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaMantenimiento;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;


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
