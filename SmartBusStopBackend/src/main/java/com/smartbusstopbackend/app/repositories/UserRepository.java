package com.smartbusstopbackend.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.smartbusstopbackend.app.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	public User findByDNI(String dNI);

}
