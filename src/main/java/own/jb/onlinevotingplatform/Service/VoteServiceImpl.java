package own.jb.onlinevotingplatform.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import own.jb.onlinevotingplatform.Entities.User;
import own.jb.onlinevotingplatform.Entities.Vote;
import own.jb.onlinevotingplatform.Entities.VoteOption;
import own.jb.onlinevotingplatform.Repository.UserRepository;
import own.jb.onlinevotingplatform.Repository.VoteOptionRepository;
import own.jb.onlinevotingplatform.Repository.VoteRepository;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final VoteOptionRepository voteOptionRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository, UserRepository userRepository, VoteOptionRepository voteOptionRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.voteOptionRepository = voteOptionRepository;
    }

    @Override
    public List<Vote> findAll() {
        return voteRepository.findAll();
    }

    @Override
    public Vote findOne(Long id) {
        return voteRepository.getOne(id);
    }

    @Override
    public void saveVote(Vote vote) {
        voteRepository.save(vote);
    }

    @Override
    public void deleteVote(Vote vote) {
        voteRepository.delete(vote);
    }

    @Override
    public void saveVotingUser(String name, Long voteOptionId) {
        User user = userRepository.findByEmail(name);
        VoteOption voteOption = voteOptionRepository.getOne(voteOptionId);
        Vote vote = voteOption.getVote();
        List<User> usersWhoVoted = vote.getVotingUsers();
        usersWhoVoted.add(user);
        vote.setVotingUsers(usersWhoVoted);

        voteRepository.save(vote);
    }

}
