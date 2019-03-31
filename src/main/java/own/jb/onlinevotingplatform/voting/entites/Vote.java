package own.jb.onlinevotingplatform.voting.entites;

import lombok.*;
import own.jb.onlinevotingplatform.company.entities.Company;
import own.jb.onlinevotingplatform.user.entites.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "vote_start")
    private LocalDateTime voteStart;

    @Column(name = "vote_end")
    private LocalDateTime voteEnd;

    /**
     * Time of vote results is saying when results should be displayed for users
     */
    @Column(name = "vote_results")
    private LocalDateTime voteResults;

    @Column(name = "is_finished")
    private boolean isFinished;

    @Column(name = "is_one_winner")
    private boolean isOneWinner;

    @ManyToOne
    @JoinColumn(name = "companies_id")
    private Company company;

    // TODO change to eager fetch type just for having proper working ASYNC method
    @OneToMany(mappedBy = "vote", fetch = FetchType.EAGER)
    private List<VoteOption> voteOptions = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="votes_users",
            joinColumns=@JoinColumn(name="vote_id"),
            inverseJoinColumns=@JoinColumn(name="user_ID")
    )
    private List<User> votingUsers;

}
