package own.jb.onlinevotingplatform.user.services.implementations;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import own.jb.onlinevotingplatform.company.entities.Company;
import own.jb.onlinevotingplatform.company.repositories.CompanyRepository;
import own.jb.onlinevotingplatform.security.repositories.entities.Role;
import own.jb.onlinevotingplatform.security.repositories.services.RoleService;
import own.jb.onlinevotingplatform.user.entites.User;
import own.jb.onlinevotingplatform.user.repositories.UserRepository;
import own.jb.onlinevotingplatform.voting.entites.Vote;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private static final String DEFAULT_USER_ROLE_NAME = "USER";

    @Mock
    UserRepository userRepository;

    @Mock
    RoleService roleService;

    @Mock
    CompanyRepository companyRepository;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    private UserServiceImpl userService;

    private List<Vote> votes = new ArrayList<>();


    @Before
    public void setUp(){
        userService = new UserServiceImpl(userRepository, roleService, companyRepository, passwordEncoder);

        Vote voteOne = Vote
                .builder()
                .voteEnd(LocalDateTime.now().minusMinutes(30))
                .voteResults(LocalDateTime.now().minusDays(1L))
                .name("VOTE_TEST_1")
                .build();

        Vote voteTwo = Vote
                .builder()
                .voteEnd(LocalDateTime.now().plusMinutes(30))
                .voteResults(LocalDateTime.now().plusDays(1L))
                .name("VOTE_TEST_2")
                .build();
        votes.add(voteOne);
        votes.add(voteTwo);
    }

    /**
     * This test is also testing private method UserServiceImpl#userCompanySetter(User)
     * {@link UserServiceImpl#saveUser(User)}
     */
    @Test
    public void testSaveUser() {
        User userOne = User
                .builder()
                .username("Kuba")
                .firstName("Kuba")
                .lastName("Jakuba")
                .email("123@123.123")
                .documentId("ABC_123")
                .password("papapa")
                .build();

        Role role =  Role
                .builder()
                .id(1)
                .name(DEFAULT_USER_ROLE_NAME)
                .build();

        Company company = Company
                .builder()
                .companyName("TEST_COMPANY")
                .id(1L)
                .employersIds(Collections.singletonList("ABC_123"))
                .build();

        when(companyRepository.findAll()).thenReturn(Collections.singletonList(company));
        when(passwordEncoder.encode(any())).thenReturn("ENCODED");
        when(roleService.getOrCreate(any())).thenReturn(role);

        userService.saveUser(userOne);

        verify(passwordEncoder, times(1)).encode(any());
        verify(roleService, times(1)).getOrCreate(any());
        verify(companyRepository, times(1)).findAll();
    }

    /**
     * {@link UserServiceImpl#editUser(String, String, String, String, String, String)}
     */
    @Test
    public void testEditUser() {
        User userOne = User
                .builder()
                .username("Kuba")
                .firstName("Kuba")
                .lastName("Jakuba")
                .email("123@123.123")
                .documentId("ABC_123")
                .password("papapa")
                .build();

        springSecurityMocking();

        Company company = Company
                .builder()
                .companyName("TEST_COMPANY")
                .id(1L)
                .employersIds(Collections.singletonList("ABC_321"))
                .build();

        when(companyRepository.findAll()).thenReturn(Collections.singletonList(company));

        when(userRepository.findByUsername(any())).thenReturn(userOne);
        userService.editUser("Kuba", "Bialy", "123@123.123", "ABC_321", "parapapa", "olll!");

        verify(companyRepository, times(1)).findAll();
        verify(userRepository, times(1)).findByUsername(any());
        verify(passwordEncoder, times(1)).encode(any());
    }

    private void springSecurityMocking() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    /**
     * {@link UserServiceImpl#findAllReadyForDisplayVotesForUser(User)}
     */
    @Test
    public void findAllReadyForDisplayVotesForUser() {
        User user = new User();
        inputDataForFindAllReadyForDisplay(user);
        List<Vote> results = userService.findAllReadyForDisplayVotesForUser(user);

        assertEquals(1, results.size());
        assertEquals("VOTE_TEST_1", results.get(0).getName());
    }

    /**
     * {@link UserServiceImpl#findAllCurrentVotesForUser(User)}
     */
    @Test
    public void findAllCurrentVotesForUser() {
        User user = new User();
        inputDataForFindAllReadyForDisplay(user);
        List<Vote> results = userService.findAllCurrentVotesForUser(user);
        assertEquals(1, results.size());
        assertEquals("VOTE_TEST_2", results.get(0).getName());
    }

    private void inputDataForFindAllReadyForDisplay(User user) {
        user.setUsername("Kuba");
        user.setFirstName("Kuba");
        user.setLastName("Jakuba");
        user.setEmail("123@123.123");
        user.setDocumentId("ABC_123");
        user.setPassword("papapa");

        Company company = Company
                .builder()
                .companyName("TEST_COMPANY")
                .id(1L)
                .employersIds(Collections.singletonList("ABC_321"))
                .votes(votes)
                .build();

        user.setCompany(company);
    }
}