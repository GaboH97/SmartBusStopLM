package com.smartbusstopbackend.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartbusstopbackend.app.exceptions.UserNotFoundException;
import com.smartbusstopbackend.app.models.User;
import com.smartbusstopbackend.app.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User findById(Long id) throws UserNotFoundException {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
	}

	@Override
	@Transactional
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public User findByDNI(String DNI) {
		return userRepository.findByDNI(DNI);
	}

	@Override
	@Transactional
	public void DeleteById(Long id) {
		userRepository.deleteById(id);
	}

}
