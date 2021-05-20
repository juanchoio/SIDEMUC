package co.com.poli.sidemuc.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "monitores")
public class Monitor extends Persona implements Serializable {

    @JsonIgnoreProperties(value = {"monitores", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "monitores_implementos", joinColumns = @JoinColumn(name = "monitor_id"), inverseJoinColumns = @JoinColumn(name = "implemento_id"), uniqueConstraints = {
            @UniqueConstraint(columnNames = {"monitor_id", "implemento_id"})
    })
    private List<Implemento> implementos;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escenario_id")
    private Escenario escenario;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
