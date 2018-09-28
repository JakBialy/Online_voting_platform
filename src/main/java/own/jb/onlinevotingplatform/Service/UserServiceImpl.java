package own.jb.onlinevotingplatform.Service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import own.jb.onlinevotingplatform.Entities.Role;
import own.jb.onlinevotingplatform.Entities.User;
import own.jb.onlinevotingplatform.Repository.UserRepository;
import java.util.*;

@Service
@javax.transaction.Transactional
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_USER_ROLE_NAME = "USER";

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService,
                           BCryptPasswordEncoder passwordEncoder) {
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

        userRepository.save(user);
    }

    @Override
    public void editUser(String firstName, String lastName, String email, String documentId, String password, String aboutMe) {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUsername(email);
        user.setDocumentId(documentId);
        user.setPassword(passwordEncoder.encode(password));
        user.setAboutMe(aboutMe);

        userRepository.save(user);
    }

}
