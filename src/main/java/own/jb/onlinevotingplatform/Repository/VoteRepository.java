package own.jb.onlinevotingplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import own.jb.onlinevotingplatform.Entities.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {
}
