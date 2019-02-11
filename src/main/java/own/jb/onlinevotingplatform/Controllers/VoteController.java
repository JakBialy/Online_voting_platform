package own.jb.onlinevotingplatform.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import own.jb.onlinevotingplatform.Entities.VoteOption;
import own.jb.onlinevotingplatform.Service.VoteOptionService;
import own.jb.onlinevotingplatform.Service.VoteService;

import java.security.Principal;

@Controller
@RequestMapping(value = "/vote")
public class VoteController {

    private final VoteService voteService;
    private final VoteOptionService voteOptionService;

    @Autowired
    public VoteController(VoteService voteService, VoteOptionService voteOptionService) {
        this.voteService = voteService;
        this.voteOptionService = voteOptionService;
    }

    @GetMapping("/{id}")
    public String vote(@PathVariable Long id, Model model) {
        model.addAttribute("vote", voteService.findOne(id));
        model.addAttribute("voteOption", new VoteOption());
        return "Vote";
    }

    @PostMapping("/add-vote")
    public String countVoteForOption(Principal principal, @RequestParam Long id) {
        voteOptionService.countVoteForOption(id);
        voteService.saveVotingUser(principal.getName(), id);
        return "redirect:/home";
    }

}
