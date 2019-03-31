package own.jb.onlinevotingplatform.voting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import own.jb.onlinevotingplatform.user.entites.User;
import own.jb.onlinevotingplatform.voting.entites.Vote;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {

    List<Vote> findByVotingUsersAndVoteEndBefore(User user, LocalDateTime localDateTime);

    List<Vote> findAllByVoteEndIsAfter(LocalDateTime localDateTime);
}
