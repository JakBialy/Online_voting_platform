package own.jb.onlinevotingplatform.Service;

import own.jb.onlinevotingplatform.Entities.User;
import own.jb.onlinevotingplatform.Entities.Vote;

import java.time.LocalDateTime;
import java.util.List;


public interface VoteService {

    List<Vote> findAll();

    Vote findOne (Long id);

    void saveVote (Vote vote);

    void deleteVote (Vote vote);

    void saveVotingUser (String name, Long voteOptionId);

    void calculateVoteResultWithTiming (Vote vote);

    List<Vote> findAllCurrentVoting ();

}
