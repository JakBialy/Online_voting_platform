package own.jb.onlinevotingplatform.security.repositories.services.implementations;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import own.jb.onlinevotingplatform.security.repositories.entities.Role;
import own.jb.onlinevotingplatform.security.repositories.repositories.RoleRepository;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceImplTest {

    @Mock
    RoleRepository roleRepository;

    private RoleServiceImpl roleService;

    @Before
    public void setUp(){
        roleService = new RoleServiceImpl(roleRepository);
    }

    /**
     * {@link RoleServiceImpl#getOrCreate(String)}
     * When role is already saved in database
     */
    @Test
    public void testGetOrCreateWhenThereIsRoleInDb() {
        Role role = new Role();
        role.setName("USER");

        when(roleRepository.findByName("USER")).thenReturn(role);

        Role result = roleService.getOrCreate("USER");

        assertEquals("USER", result.getName());
        verify(roleRepository, never()).save(any());
    }
    /**
     * {@link RoleServiceImpl#getOrCreate(String)}
     * When role is not saved in database
     */
    @Test
    public void testGetOrCreateWhenThereIsNoRoleInDb() {
        Role role = new Role();
        role.setName("USER");
        role.setId(1);

        when(roleRepository.findByName("USER")).thenReturn(null);
        when(roleRepository.save(any())).thenReturn(role);

        Role result = roleService.getOrCreate("USER");

        assertEquals("USER", result.getName());
        verify(roleRepository, times(1)).save(any());
    }
}