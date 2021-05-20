package co.com.poli.sidemuc.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "implementos")
@AllArgsConstructor
@NoArgsConstructor
public class Implemento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nombre_implemento")
    private String nombreImplemento;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    /*estamos excluyendo los atributos deporte y escenario cuando retorna
    * los monitores que tienen asignado algun implemento, es decir,
    * para que el Json no sea generado con demasiada informacion*/
    @JsonIgnoreProperties(value = {"implementos", "deporte", "escenario", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToMany(mappedBy = "implementos")
    private List<Monitor> monitores;

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
