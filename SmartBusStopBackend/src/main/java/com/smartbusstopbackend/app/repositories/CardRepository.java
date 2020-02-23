package com.smartbusstopbackend.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.smartbusstopbackend.app.models.Card;
import com.smartbusstopbackend.app.models.User;

@Repository
public interface CardRepository extends CrudRepository<Card, Long>{
	
	@Query("select c from Card c join fetch c.user")
	public List<Card> findAllWithUser();
	
	@Query("select c from Card c join fetch c.user where c.id = ?1")
	public Optional<Card> findByIdWithUser(Long id);
}
