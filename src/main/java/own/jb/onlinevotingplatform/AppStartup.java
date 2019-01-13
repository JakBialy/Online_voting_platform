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
import java.util.Arrays;

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
        saveTestCompany1();
        companyService.saveCompany(testCompany2());
        if (userService.getNumUsers() == 0L) {
            userService.saveUser(testUser1());
        }
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
        Company company = new Company();
        company.setEmployersIds(Arrays.asList("ABC123", "ABC1234"));
        company.setCompanyName("TEST_1");
        companyService.saveCompany(company);

        Vote vote = new Vote();
        vote.setCompany(company);
        vote.setName("Company's drinking party");
        vote.setVoteStart(LocalDateTime.now().plusMinutes(1));
        vote.setVoteEnd(LocalDateTime.now().plusMinutes(5));
        vote.setVoteResults(LocalDateTime.now().plusMinutes(6));
        voteService.saveVote(vote);

        VoteOption voteOption = new VoteOption();
        voteOption.setName("Thursday");
        voteOption.setVote(vote);
        voteOptionService.saveVoteOption(voteOption);

        VoteOption voteOption1 = new VoteOption();
        voteOption1.setName("Friday");
        voteOption1.setVote(vote);
        voteOptionService.saveVoteOption(voteOption1);
    }

    private Company testCompany2(){
        Company company = new Company();
        company.setEmployersIds(Arrays.asList("ABC321", "ABC4321"));
        company.setCompanyName("TEST_2");
        return company;
    }

}
