package own.jb.onlinevotingplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import own.jb.onlinevotingplatform.Entities.VoteOption;

@Repository
public interface VoteOptionRepository extends JpaRepository<VoteOption,Long> {
}
