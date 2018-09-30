package own.jb.onlinevotingplatform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import own.jb.onlinevotingplatform.Entities.Company;
import own.jb.onlinevotingplatform.Entities.User;
import own.jb.onlinevotingplatform.Service.CompanyService;
import own.jb.onlinevotingplatform.Service.UserService;

import java.util.Arrays;

@Component
public class AppStartup implements ApplicationRunner {

    private final UserService userService;
    private final CompanyService companyService;

    @Autowired
    public AppStartup(UserService userService, CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }

    @Override
    public void run(ApplicationArguments args)  {
        companyService.saveCompany(testCompany1());
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

    private Company testCompany1(){
        Company company = new Company();
        company.setEmployersIds(Arrays.asList("ABC123", "ABC1234"));
        company.setCompanyName("TEST_1");
        return company;
    }

    private Company testCompany2(){
        Company company = new Company();
        company.setEmployersIds(Arrays.asList("ABC321", "ABC4321"));
        company.setCompanyName("TEST_2");
        return company;
    }
}
