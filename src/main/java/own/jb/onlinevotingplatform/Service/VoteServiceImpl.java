package own.jb.onlinevotingplatform.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import own.jb.onlinevotingplatform.Entities.Vote;
import own.jb.onlinevotingplatform.Repository.VoteRepository;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public List<Vote> findAll() {
        return voteRepository.findAll();
    }

    @Override
    public Vote findOne(Long id) {
        return voteRepository.findById(id).get();
    }

    @Override
    public void saveVote(Vote vote) {
        voteRepository.save(vote);
    }

    @Override
    public void deleteVote(Vote vote) {
        voteRepository.delete(vote);
    }
}
