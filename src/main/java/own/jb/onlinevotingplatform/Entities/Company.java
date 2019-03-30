package own.jb.onlinevotingplatform.Entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "companies")
public
class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name = "company_name", unique = true)
    private String companyName;

    /**
     * element collection as this is a list of strings linked to specific company account
     */
    @ElementCollection
    @CollectionTable(name="employers_ids_companies", joinColumns=@JoinColumn(name="company_id"))
    @Column(name="employers_ids")
    private List<String> employersIds = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List <Vote> votes = new ArrayList<>();

}
