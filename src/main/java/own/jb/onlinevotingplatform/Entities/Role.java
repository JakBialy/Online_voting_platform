package own.jb.onlinevotingplatform.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public @Getter @Setter
class Role {

    /**
     * Role class is used to define role of users in system
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;

    @Column(name = "role")
    private String name;
}
