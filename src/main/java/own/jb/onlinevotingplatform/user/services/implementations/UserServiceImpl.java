package own.jb.onlinevotingplatform.user.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import own.jb.onlinevotingplatform.company.entities.Company;
import own.jb.onlinevotingplatform.security.repositories.entities.Role;
import own.jb.onlinevotingplatform.security.repositories.services.RoleService;
import own.jb.onlinevotingplatform.user.entites.User;
import own.jb.onlinevotingplatform.user.repositories.UserRepository;
import own.jb.onlinevotingplatform.user.services.UserService;
import own.jb.onlinevotingplatform.voting.entites.Vote;
import own.jb.onlinevotingplatform.company.repositories.CompanyRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@javax.transaction.Transactional
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_USER_ROLE_NAME = "USER";

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final CompanyRepository companyRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService,
                           CompanyRepository companyRepository, BCryptPasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public long getNumUsers() {
        return userRepository.count();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getUsername());
        user.setEnabled(true);

        Role role = roleService.getOrCreate(DEFAULT_USER_ROLE_NAME);
        Set<Role> roles = new HashSet<>(Collections.singletonList(role));
        user.setRoles(roles);

        userCompanySetter(user);
        userRepository.save(user);
    }

    private void userCompanySetter(User user) {
        for (Company company: companyRepository.findAll()) {
                if(company.getEmployersIds().contains(user.getDocumentId())){
                    user.setCompany(company);
                }
        }
    }

    @Override
    public void editUser(String firstName, String lastName, String email, String documentId, String password, String aboutMe) {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUsername(email);
        if (!user.getDocumentId().equals(documentId)){
            user.setDocumentId(documentId);
            userCompanySetter(user);
        }
        user.setPassword(passwordEncoder.encode(password));
        user.setAboutMe(aboutMe);

        userRepository.save(user);
    }

    @Override
    public List<Vote> findAllReadyForDisplayVotesForUser(User user) {
        List<Vote> voteList = user.getCompany().getVotes();
        voteList = voteList.stream().filter(element -> element.getVoteResults().isBefore(LocalDateTime.now())).collect(Collectors.toList());
        return voteList;
    }

    @Override
    public List<Vote> findAllCurrentVotesForUser(User user) {
        List<Vote> voteList = user.getCompany().getVotes();
        voteList = voteList.stream().filter(element -> element.getVoteEnd().isAfter(LocalDateTime.now())).collect(Collectors.toList());
        return voteList;
    }

}
