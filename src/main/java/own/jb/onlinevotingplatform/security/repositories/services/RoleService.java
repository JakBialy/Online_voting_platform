package own.jb.onlinevotingplatform.security.repositories.services;


import own.jb.onlinevotingplatform.security.repositories.entities.Role;

public interface RoleService {

    Role getOrCreate(String roleName);
}
