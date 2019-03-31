package own.jb.onlinevotingplatform.user.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import own.jb.onlinevotingplatform.user.entites.User;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() {
        User userOne = User
                .builder()
                .username("Kuba")
                .firstName("Kuba")
                .lastName("Jakuba")
                .email("123@123.123")
                .documentId("ABC_123")
                .password("papapa")
                .build();

        User userTwo = User
                .builder()
                .username("Jakub")
                .firstName("Jakuba")
                .lastName("Kuba")
                .email("321@321.321")
                .documentId("DEMO")
                .password("alalala")
                .build();
        entityManager.persist(userOne);
        entityManager.persist(userTwo);
    }

    /**
     * {@link UserRepository#findByUsername(String)}
     */
    @Test
    public void testFindByUsername(){
        User result = userRepository.findByUsername("Kuba");
        assertEquals("Kuba", result.getUsername());
        assertEquals("ABC_123", result.getDocumentId());
    }

    /**
     * {@link UserRepository#findByEmail(String)}
     */
    @Test
    public void testFindByEmail(){
        User result = userRepository.findByEmail("321@321.321");
        assertEquals("Jakub", result.getUsername());
        assertEquals("DEMO", result.getDocumentId());
    }


}