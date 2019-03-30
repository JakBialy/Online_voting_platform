package own.jb.onlinevotingplatform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import own.jb.onlinevotingplatform.Entities.Company;
import own.jb.onlinevotingplatform.Entities.User;
import own.jb.onlinevotingplatform.Entities.Vote;
import own.jb.onlinevotingplatform.Entities.VoteOption;
import own.jb.onlinevotingplatform.Service.CompanyService;
import own.jb.onlinevotingplatform.Service.UserService;
import own.jb.onlinevotingplatform.Service.VoteOptionService;
import own.jb.onlinevotingplatform.Service.VoteService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
public class AppStartup implements ApplicationRunner {

    private final UserService userService;
    private final CompanyService companyService;
    private final VoteService voteService;
    private final VoteOptionService voteOptionService;

    @Autowired
    public AppStartup(UserService userService, CompanyService companyService,
    VoteService voteService, VoteOptionService voteOptionService) {
        this.userService = userService;
        this.companyService = companyService;
        this.voteService = voteService;
        this.voteOptionService = voteOptionService;
    }

    @Override
    public void run(ApplicationArguments args)  {
        saveTestData();
    }

    private void saveTestData(){
        saveTestCompany1();
        companyService.saveCompany(testCompany2());
        userService.saveUser(testUser1());
    }

    private User testUser1() {
        User user = new User();
        user.setUsername("bialyj1@gmail.com");
        user.setFirstName("Jakub");
        user.setLastName("B");
        user.setDocumentId("ABC123");
        user.setEmail("bialyj1@gmail.com");
        user.setPassword("123");
        user.setCompany(companyService.findById(1L));
        return user;
    }

    private void saveTestCompany1(){
        Company company = Company
                .builder()
                .employersIds(Arrays.asList("ABC123", "ABC1234", "DEMO"))
                .companyName("TEST_1")
                .build();

        companyService.saveCompany(company);

        Vote vote = Vote
                .builder()
                .company(company)
                .name("Company's drinking party")
                .voteStart(LocalDateTime.now().plusMinutes(1))
                .voteEnd(LocalDateTime.now().plusMinutes(2))
                .voteResults(LocalDateTime.now().plusMinutes(2))
                .build();
        voteService.saveVote(vote);
        voteService.calculateVoteResultWithTiming(vote);

        VoteOption voteOption = VoteOption
                .builder()
                .name("Thursday")
                .vote(vote)
                .build();
        voteOptionService.saveVoteOption(voteOption);

        VoteOption voteOption2 = VoteOption
                .builder()
                .name("Friday")
                .vote(vote)
                .build();
        voteOptionService.saveVoteOption(voteOption2);


        Vote voteTestTwo = new Vote();
        voteTestTwo.setCompany(company);
        voteTestTwo.setName("Some another event");
        voteTestTwo.setVoteStart(LocalDateTime.now());
        voteTestTwo.setVoteEnd(LocalDateTime.now().plusMinutes(20));
        voteTestTwo.setVoteResults(LocalDateTime.now().plusMinutes(30));
        voteService.saveVote(voteTestTwo);
        voteService.calculateVoteResultWithTiming(voteTestTwo);

        VoteOption voteOptionOne = new VoteOption();
        voteOptionOne.setName("10.05.2019");
        voteOptionOne.setVote(voteTestTwo);
        voteOptionService.saveVoteOption(voteOptionOne);

        VoteOption VoteOptionTwo = new VoteOption();
        VoteOptionTwo.setName("10.06.2019");
        VoteOptionTwo.setVote(voteTestTwo);
        voteOptionService.saveVoteOption(VoteOptionTwo);
        pastEvent(company);
    }

    private void pastEvent(Company company) {
        Vote pastVote = Vote
                .builder()
                .company(company)
                .name("Some event in the past")
                .voteStart(LocalDateTime.now().minusDays(15))
                .voteEnd(LocalDateTime.now().minusDays(10))
                .voteResults(LocalDateTime.now().minusDays(9))
                .build();

        voteService.saveVote(pastVote);

        // past event, to show and check ability of showing past events
        VoteOption pastVoteOption = VoteOption
                .builder()
                .name("Saturday")
                .votesNumber(10L)
                .vote(pastVote)
                .build();

        voteOptionService.saveVoteOption(pastVoteOption);

        VoteOption pastVoteOptionTwo = VoteOption
                .builder()
                .name("Friday")
                .votesNumber(15L)
                .vote(pastVote)
                .build();
        voteOptionService.saveVoteOption(pastVoteOptionTwo);

    }

    private Company testCompany2(){
        return Company
                .builder()
                .employersIds(Arrays.asList("ABC321", "ABC4321"))
                .companyName("TEST_2")
                .build();
    }

}
