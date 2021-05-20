package co.com.poli.sidemuc.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "habilidades")
@AllArgsConstructor
@NoArgsConstructor
public class Habilidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "nombre_habilidad")
    private String nombreHabilidad;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    /*@JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Deportista deportista;*/

    //@JsonBackReference
    @JsonIgnoreProperties(value = {"habilidades", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToMany(mappedBy = "habilidades")
    private List<Deportista> deportistas;

    @PrePersist
    public void prePersist(){
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
