package own.jb.onlinevotingplatform.Service;

import own.jb.onlinevotingplatform.Entities.User;

import java.util.List;

public interface UserService {
    long getNumUsers();

    List<User> findAll();

    User findByUserName(String name);

    void saveUser(User user);

    void editUser(String firstName, String lastName, String email, String documentId, String password, String aboutMe);
}
