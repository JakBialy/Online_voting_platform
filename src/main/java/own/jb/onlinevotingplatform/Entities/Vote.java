package own.jb.onlinevotingplatform.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "votes")
public @Getter @Setter
class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    private LocalDateTime voteStart;

    private LocalDateTime voteEnd;

    private LocalDateTime voteResults;

    @ManyToOne
    @JoinColumn(name = "companies_id")
    private Company company;

    @OneToMany(mappedBy = "vote")
    private List<VoteOption> voteOptions = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="votes_users",
            joinColumns=@JoinColumn(name="vote_id"),
            inverseJoinColumns=@JoinColumn(name="user_ID")
    )
    private List<User> votingUsers;
}
