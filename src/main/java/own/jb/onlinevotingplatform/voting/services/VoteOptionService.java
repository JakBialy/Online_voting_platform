package own.jb.onlinevotingplatform.voting.services;

import own.jb.onlinevotingplatform.voting.entites.VoteOption;

import java.util.List;

public interface VoteOptionService {

    List<VoteOption> findAll();

    VoteOption findById(Long id);

    void saveVoteOption(VoteOption voteOption);

    void deleteVoteOption(VoteOption voteOption);

    void countVoteForOption(Long id);
}
