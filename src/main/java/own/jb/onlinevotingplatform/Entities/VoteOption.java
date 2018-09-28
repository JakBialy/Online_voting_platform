package own.jb.onlinevotingplatform.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "vote_options")
public class VoteOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    Long votesNumber;

    @ManyToOne
    @JoinColumn(name = "vote_id")
    @JsonBackReference
    Vote vote;
}
