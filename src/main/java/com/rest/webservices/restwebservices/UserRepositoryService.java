package com.rest.webservices.restwebservices;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryService {
	
	private static List<User> users= new ArrayList<User>();
	
	private static int countOfUsers=3;
	
	static {
		users.add(new User(1,"AAA",new Date()));
		users.add(new User(2,"BBB",new Date()));
		users.add(new User(3,"CCC",new Date()));
	}
	
	public List<User> getAllUsers(){
		return users;
	}
	
	public User findUser(Integer id) {
		for(User user:users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}
	
	public User saveUser(User user) {
		if(user.getId()==null) {
			user.setId(++countOfUsers);
			users.add(user);
		}
		return user;
	}
	
	public User deleteUser(Integer id) {
		Iterator<User> iterator=users.iterator();
		while(iterator.hasNext()) {
			User user=iterator.next();
			if(user.getId()==id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}

}
