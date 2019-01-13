package own.jb.onlinevotingplatform.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import own.jb.onlinevotingplatform.Entities.VoteOption;
import own.jb.onlinevotingplatform.Repository.VoteOptionRepository;

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
        return voteOptionRepository.findById(id).get();
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
        VoteOption voteOption = voteOptionRepository.findById(id).get();
        voteOption.setVotesNumber(voteOption.getVotesNumber() + 1);
        voteOptionRepository.save(voteOption);
    }
}
