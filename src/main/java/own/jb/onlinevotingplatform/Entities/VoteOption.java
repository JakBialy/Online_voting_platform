package own.jb.onlinevotingplatform.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "vote_options")
public @Data
class VoteOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    Long votesNumber = 0L;

    @ManyToOne
    @JoinColumn(name = "vote_id")
    @JsonBackReference
    Vote vote;
}