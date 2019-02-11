package own.jb.onlinevotingplatform.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public @Getter @Setter
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String username;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty
    @Column(unique = true)
    private String documentId;

    @NotEmpty
    private String password;

    private String aboutMe;

    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "companies_id")
    private Company company;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    @ManyToMany
    @JoinTable(name="votes_users",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="vote_id"))
    private List<Vote> userVotes;
}
