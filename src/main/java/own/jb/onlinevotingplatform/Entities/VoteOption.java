package own.jb.onlinevotingplatform.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Table(name = "vote_options")
public @Getter @Setter
class VoteOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long votesNumber = 0L;

    @ManyToOne
    @JoinColumn(name = "vote_id")
    @JsonBackReference
    private Vote vote;
}