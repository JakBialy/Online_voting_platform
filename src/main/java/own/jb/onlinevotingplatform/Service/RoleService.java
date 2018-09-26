package own.jb.onlinevotingplatform.Service;


import own.jb.onlinevotingplatform.Entities.Role;

public interface RoleService {

    Role getOrCreate(String roleName);
}
