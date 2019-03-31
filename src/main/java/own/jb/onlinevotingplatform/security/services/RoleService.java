package own.jb.onlinevotingplatform.security.services;


import own.jb.onlinevotingplatform.security.entities.Role;

public interface RoleService {

    Role getOrCreate(String roleName);
}
