package repositories;

import domain.User;

public interface UserRepository {
	
	User getUserByUsername(String username);
	boolean isAuthorized (String username, String pass);
	void add(User newUser);

}
