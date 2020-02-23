package com.smartbusstopbackend.app.services;

import java.util.List;

import com.smartbusstopbackend.app.exceptions.UserNotFoundException;
import com.smartbusstopbackend.app.models.User;

public interface UserService {
	
	public List<User> findAll();
	public User findById(Long id) throws UserNotFoundException;
	public void save(User user);
	public User findByDNI(String DNI);
	public void DeleteById(Long id);
	

}
