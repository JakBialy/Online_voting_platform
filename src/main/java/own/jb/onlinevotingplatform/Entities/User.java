package own.jb.onlinevotingplatform.Entities;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "users")
public @Data
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty
    @Column(nullable = false, unique = true)
    String username;

    @NotEmpty
    String firstName;

    @NotEmpty
    String lastName;

    @NotEmpty
    @Column(nullable = false, unique = true)
    String email;

    @NotEmpty
    String documentId;

    @NotEmpty
    String password;

    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
