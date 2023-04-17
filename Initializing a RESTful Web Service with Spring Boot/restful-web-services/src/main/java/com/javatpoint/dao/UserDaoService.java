package com.javatpoint.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.javatpoint.model.User;

@Component
@Service
public class UserDaoService {

	public static int usersCount = 5;
	private static List<User> users = new ArrayList<>();
	static {
		// adding users to the list
		users.add(new User(1, "Kehsva", new java.util.Date()));
		users.add(new User(2, "Robert", new java.util.Date()));
		users.add(new User(3, "Adam", new java.util.Date()));
		users.add(new User(4, "Andrew", new java.util.Date()));
		users.add(new User(5, "Jack", new java.util.Date()));
	}

	// method that retrieve all users from the list
	public List<User> findAll() {
		return users;
	}

	// method that add the user in the list
	public User save(User user) {
		if (user.getId() == 0) {
			// increments the user id
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}

	// method that find a particular user from the list
	public User findOne(int id) {
		for (User user : users) {
			if (user.getId() == id)
				return user;
		}
		return null;
	}

	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user; // returns the deleted resource back
			}
		}
		return null;
	}

}
