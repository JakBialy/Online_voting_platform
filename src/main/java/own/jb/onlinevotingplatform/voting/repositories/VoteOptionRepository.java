package own.jb.onlinevotingplatform.voting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import own.jb.onlinevotingplatform.voting.entites.VoteOption;

@Repository
public interface VoteOptionRepository extends JpaRepository<VoteOption,Long> {
}
