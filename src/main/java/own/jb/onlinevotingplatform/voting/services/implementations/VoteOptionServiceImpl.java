package own.jb.onlinevotingplatform.voting.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import own.jb.onlinevotingplatform.voting.repositories.VoteOptionRepository;
import own.jb.onlinevotingplatform.voting.entites.VoteOption;
import own.jb.onlinevotingplatform.voting.services.VoteOptionService;

import java.util.List;

@Service
public class VoteOptionServiceImpl implements VoteOptionService {

    private final VoteOptionRepository voteOptionRepository;

    @Autowired
    public VoteOptionServiceImpl(VoteOptionRepository voteOptionRepository) {
        this.voteOptionRepository = voteOptionRepository;
    }

    @Override
    public List<VoteOption> findAll() {
        return voteOptionRepository.findAll();
    }

    @Override
    public VoteOption findById(Long id) {
        return voteOptionRepository.getOne(id);
    }

    @Override
    public void saveVoteOption(VoteOption voteOption) {
        voteOptionRepository.save(voteOption);
    }

    @Override
    public void deleteVoteOption(VoteOption voteOption) {
        voteOptionRepository.delete(voteOption);
    }

    @Override
    public void countVoteForOption(Long id){
        VoteOption voteOption = voteOptionRepository.getOne(id);
        voteOption.setVotesNumber(voteOption.getVotesNumber() + 1);
        voteOptionRepository.save(voteOption);
    }
}
