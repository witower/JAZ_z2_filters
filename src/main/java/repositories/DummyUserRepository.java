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

	@Override
	public String toString() {
		String result = "";
		for (User user: db) {
			result += user.toString() + "<br>";
		}
		return result;
	}

	public List<User> getDb() {
		return db;
	}

	public void togglePremium(String username) {
		int useridx = db.indexOf(getUserByUsername(username));
		User user = db.get(useridx);
		if (user.isPremium()) user.setPremium(false);
		else user.setPremium(true);
		db.set(useridx, user);
	}
	
	
	
	

}
