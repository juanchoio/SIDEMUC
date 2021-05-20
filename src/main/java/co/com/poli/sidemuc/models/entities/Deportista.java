package co.com.poli.sidemuc.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "deportistas")
public class Deportista extends Persona implements Serializable {


    /*@JsonManagedReference
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "deportista")
    private List<Habilidad> habilidades;*/

    //@JsonManagedReference
    @JsonIgnoreProperties(value = {"deportistas", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "deportistas_habilidades", joinColumns = @JoinColumn(name = "deportista_id"), inverseJoinColumns = @JoinColumn(name = "habilidad_id"), uniqueConstraints = {
            @UniqueConstraint(columnNames = {"deportista_id", "habilidad_id"})
    })
    private List<Habilidad> habilidades;



    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
