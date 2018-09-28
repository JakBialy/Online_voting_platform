package own.jb.onlinevotingplatform.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import own.jb.onlinevotingplatform.Entities.User;
import own.jb.onlinevotingplatform.Service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String user (Model model, Principal principal){
        model.addAttribute("user", userService.findByUserName(principal.getName()));
        return "User";
    }


    @PostMapping("/user")
    public String user(Principal principal, @Valid @RequestParam String firstName, String lastName, String documentId, String email, String password, String aboutMe) {
        // TODO maybe change it to model atribbute User?
        // TODO then add bindingresult
        if (!principal.getName().equals(email)){
            userService.editUser(firstName,lastName, email,  documentId, password, aboutMe);
            return "redirect:/login";
        }
        userService.editUser(firstName,lastName, email,  documentId, password, aboutMe);
        return "redirect:/user";
    }

    @GetMapping("/register")
    public String register (Model model){
        model.addAttribute("user", new User());
        return "Register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User user, BindingResult result){
        if (result.hasErrors()){
            return "Register";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }

}