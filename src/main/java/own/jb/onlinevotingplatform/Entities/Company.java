package own.jb.onlinevotingplatform.Entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
public @Data
class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty
    @Column(unique = true)
    private String name;

    @ElementCollection
    @CollectionTable(name="employers_ids_companies", joinColumns=@JoinColumn(name="company_id"))
    @Column(name="employers_ids")
    private List<String> employersIds = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<User> user = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List <Vote> votes = new ArrayList<>();

}