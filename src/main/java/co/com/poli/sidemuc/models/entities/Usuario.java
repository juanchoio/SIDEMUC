package co.com.poli.sidemuc.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20)
    private String username;

    @Column(length = 60)//porque lo encriptamos en un hash y queda mas extenso
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Monitor monitor;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Role> roles;


}
