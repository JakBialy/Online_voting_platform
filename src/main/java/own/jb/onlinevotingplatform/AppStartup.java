package own.jb.onlinevotingplatform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import own.jb.onlinevotingplatform.Entities.User;
import own.jb.onlinevotingplatform.Service.UserService;

@Component
public class AppStartup implements ApplicationRunner {

    private final UserService userService;

    @Autowired
    public AppStartup(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args)  {
        if (userService.getNumUsers() == 0L) {
            userService.saveUser(testUser1());
        }
    }

    private User testUser1() {
        User user = new User();
        user.setUsername("bialyj1@gmail.com");
        user.setFirstName("Jakub");
        user.setLastName("B");
        user.setDocumentId("AAAPPPPDDDD");
        user.setEmail("bialyj1@gmail.com");
        user.setPassword("123");
        return user;
    }
}
