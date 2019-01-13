package own.jb.onlinevotingplatform.Service;

import own.jb.onlinevotingplatform.Entities.VoteOption;

import java.util.List;

public interface VoteOptionService {

    List<VoteOption> findAll();

    VoteOption findById(Long id);

    void saveVoteOption(VoteOption voteOption);

    void deleteVoteOption(VoteOption voteOption);

    void countVoteForOption(Long id);
}
