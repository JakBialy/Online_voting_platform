package own.jb.onlinevotingplatform.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
    @Column(unique = true)
    String username;

    @NotEmpty
    String firstName;

    @NotEmpty
    String lastName;

    @Email
    @Column(unique = true)
    String email;

    @NotEmpty
    @Column(unique = true)
    String documentId;

    @NotEmpty
    String password;

    String aboutMe;

    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference
    Company company;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
