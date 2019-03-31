package own.jb.onlinevotingplatform.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import own.jb.onlinevotingplatform.user.entites.User;
import own.jb.onlinevotingplatform.user.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/home")
    public String home(Model model, Principal principal){
        User user = userService.findByUserName(principal.getName());
        model.addAttribute("user", user);
        return "Home";
    }

    @GetMapping("/current-voting")
    public String currentVoting(Model model, Principal principal){
        User user = userService.findByUserName(principal.getName());
        model.addAttribute("currentVotes", userService.findAllCurrentVotesForUser(user));
        return "CurrentVoting";
    }

    @GetMapping("/finished-voting")
    public String finishedVoting(Model model, Principal principal){
        User user = userService.findByUserName(principal.getName());
        model.addAttribute("finishedVotes", userService.findAllReadyForDisplayVotesForUser(user));
        return "FinishedVoting";
    }

    @GetMapping("/user-profile")
    public String userProfile (Model model, Principal principal){
        model.addAttribute("user", userService.findByUserName(principal.getName()));
        return "User";
    }

    @PostMapping("/user-profile")
    public String user(Principal principal, @Valid @RequestParam String firstName, String lastName, String documentId, String email, String password, String aboutMe) {
        // TODO maybe change it to model atribbute User?
        // TODO then add binding result
        if (!principal.getName().equals(email)){
            userService.editUser(firstName,lastName, email,  documentId, password, aboutMe);
            return "redirect:/login";
        }
        userService.editUser(firstName,lastName, email,  documentId, password, aboutMe);
        return "redirect:/user-profile";
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