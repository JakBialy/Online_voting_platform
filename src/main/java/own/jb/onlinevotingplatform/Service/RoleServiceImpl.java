package own.jb.onlinevotingplatform.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import own.jb.onlinevotingplatform.Entities.Role;
import own.jb.onlinevotingplatform.Repository.RoleRepository;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    /**
     * Method is a constructor of rolerepository
     *
     * @param roleRepository role repository
     */
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Method is getting string of role name
     * then is getting object based by role name
     * if role is null then is setting name for roll and saved in into database
     * otherwise is getting role
     * @param name
     * @return role
     */
    @Override
    public Role getOrCreate(String name) {

        Role role = roleRepository.findByName(name);

        if (role == null) {
            role = new Role();
            role.setName(name);
            role = roleRepository.save(role);
        }

        return role;
    }
}
