package repositories;

import java.util.ArrayList;
import java.util.List;
import domain.User;

public class DummyUserRepository implements UserRepository {
	
	private static List<User> db = new ArrayList<User>();

	@Override
	public User getUserByUsername(String username) {
		for (User user: db) {
			if (user.getUsername().equalsIgnoreCase(username))
				return user;
		}
		return null;
	}

	@Override
	public void add(User newUser) {
		db.add(newUser);
	}

	@Override
	public boolean isAuthorized(String username, String pass) {
		User user = getUserByUsername(username);
		if ((user!=null) && (user.getPassword().equals(pass))) return true;
		else return false;
	}

}
