package own.jb.onlinevotingplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import own.jb.onlinevotingplatform.Entities.User;
import own.jb.onlinevotingplatform.Entities.Vote;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {

    List<Vote> findByVotingUsersAndVoteEndBefore(User user, LocalDateTime localDateTime);
}
