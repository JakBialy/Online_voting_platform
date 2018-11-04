//package own.jb.onlinevotingplatform.Controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import own.jb.onlinevotingplatform.Service.VoteService;
//
//@Controller
//@RequestMapping(value = "/vote")
//public class VoteController {
//
//    private final
//    VoteService voteService;
//
//    @Autowired
//    public VoteController(VoteService voteService) {
//        this.voteService = voteService;
//    }
//
//    @GetMapping("vote/{id}")
//    public String vote(@PathVariable Long id, Model model) {
//        model.addAttribute("vote", voteService.findOne(id));
//        return "Vote";
//    }
//
//}
