package own.jb.onlinevotingplatform.voting.services;

import own.jb.onlinevotingplatform.voting.entites.Vote;

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
