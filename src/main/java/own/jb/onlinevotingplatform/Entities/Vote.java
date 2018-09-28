package own.jb.onlinevotingplatform.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vote")
public @Data
class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty
    String name;

    LocalDateTime voteStart;

    LocalDateTime voteEnd;

    LocalDateTime voteResults;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference
    Company company;

    @OneToMany(mappedBy = "vote")
    List<VoteOption> voteOptions = new ArrayList<>();
}