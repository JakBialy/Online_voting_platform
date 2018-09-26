package own.jb.onlinevotingplatform.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import own.jb.onlinevotingplatform.Service.UserService;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public String user (Model model, Principal principal){
        model.addAttribute("user", userService.findByUserName(principal.getName()));
        return "User";
    }

    @PostMapping("/user")
    public String userSettings(Principal principal,@RequestParam String firstName, String lastName, String documentId, String email, String password, String aboutMe) {
        if (!principal.getName().equals(email)){
            userService.editUser(firstName,lastName, email,  documentId, password, aboutMe);
            return "redirect:/login";
        }
        userService.editUser(firstName,lastName, email,  documentId, password, aboutMe);
        return "redirect:/user";
    }

}