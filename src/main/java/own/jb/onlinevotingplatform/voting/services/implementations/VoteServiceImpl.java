package own.jb.onlinevotingplatform.voting.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import own.jb.onlinevotingplatform.user.entites.User;
import own.jb.onlinevotingplatform.user.repositories.UserRepository;
import own.jb.onlinevotingplatform.voting.repositories.VoteOptionRepository;
import own.jb.onlinevotingplatform.voting.repositories.VoteRepository;
import own.jb.onlinevotingplatform.voting.entites.Vote;
import own.jb.onlinevotingplatform.voting.entites.VoteOption;
import own.jb.onlinevotingplatform.voting.services.VoteService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

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

    /**
     * Asynchronous method which is calculating vote results, method is waiting during
     * time when vote take place, after it it is running and checking which option is a
     * winning option
     * @param vote vote which is currently processed
     */
    @Override
    @Async
    public void calculateVoteResultWithTiming(Vote vote) {
        long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), vote.getVoteEnd());

        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // called again for same vote for gets update about voting which happened in meantime
        // TODO check difference between findById and getOne and why getOne causes lazyException and findById with .get not
        Vote updatedVote = voteRepository.findById(vote.getId()).get();
        List<VoteOption> voteOptionList = updatedVote.getVoteOptions();

        long totalNumberOfVotes = voteOptionList
                .stream()
                .mapToLong(VoteOption::getVotesNumber).sum();

        long maxVotes = voteOptionList.stream()
                .max(Comparator.comparing(VoteOption::getVotesNumber))
                .orElseThrow(NoSuchElementException::new).getVotesNumber();

        int winnersCounter = 0;

        for (VoteOption voteOption: voteOptionList) {
            double calculatePercentage = voteOption.getVotesNumber() / (double)totalNumberOfVotes;
            calculatePercentage = Math.round(calculatePercentage);
            voteOption.setPercentage(calculatePercentage);

            if (voteOption.getVotesNumber() == maxVotes){
                voteOption.setWinner(true);
                winnersCounter++;
            }
        }

        updatedVote.setOneWinner(winnersCounter == 1);
        updatedVote.setFinished(true);
        updatedVote.setVoteOptions(voteOptionList);
        voteRepository.save(updatedVote);
    }

    public List<Vote> findAllCurrentVoting (){
        return voteRepository.findAllByVoteEndIsAfter(LocalDateTime.now());
    }

}
