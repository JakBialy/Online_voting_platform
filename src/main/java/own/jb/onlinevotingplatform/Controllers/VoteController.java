package own.jb.onlinevotingplatform.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import own.jb.onlinevotingplatform.Service.VoteOptionService;
import own.jb.onlinevotingplatform.Service.VoteService;

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
        return "Vote";
    }

    @GetMapping("/add-vote/{id}")
    public String countVoteForOption(@PathVariable Long id) {
        voteOptionService.countVoteForOption(id);
        return "redirect:/home";
    }

}

//http://localhost:8080/vote/add-vote/2
