package own.jb.onlinevotingplatform.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "vote_options")
public class VoteOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "votes_number")
    private Long votesNumber = 0L;

    @Column(name = "percentage")
    private double percentage;

    @Column(name = "is_winner")
    boolean isWinner;

    @ManyToOne
    @JoinColumn(name = "vote_id")
    @JsonBackReference
    private Vote vote;
}