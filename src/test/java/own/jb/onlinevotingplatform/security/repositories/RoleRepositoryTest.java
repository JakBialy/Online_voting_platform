package own.jb.onlinevotingplatform.security.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import own.jb.onlinevotingplatform.security.repositories.entities.Role;
import own.jb.onlinevotingplatform.security.repositories.repositories.RoleRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private RoleRepository roleRepository;


    /**
     * {@link RoleRepository#findByName(String)}
     */
    @Test
    public void testFindByName(){
        Role user = new Role();
        user.setName("USER");

        Role admin = new Role();
        admin.setName("ADMIN");

        entityManager.persist(user);
        entityManager.persist(admin);

        Role result = roleRepository.findByName("USER");
        assertEquals("USER", result.getName());
        assertEquals(1, result.getId());
    }

}