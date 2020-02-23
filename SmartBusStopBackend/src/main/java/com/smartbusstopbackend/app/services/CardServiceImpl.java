package com.smartbusstopbackend.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartbusstopbackend.app.exceptions.CardNotFoundException;
import com.smartbusstopbackend.app.exceptions.UserNotFoundException;
import com.smartbusstopbackend.app.models.Card;
import com.smartbusstopbackend.app.models.User;
import com.smartbusstopbackend.app.repositories.CardRepository;
import com.smartbusstopbackend.app.repositories.UserRepository;

@Service
public class CardServiceImpl implements CardService {

	private CardRepository cardRepository;
	
	@Autowired
	public CardServiceImpl(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Card> findAll() {
		return (List<Card>) cardRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Card> findAllWithUser() {
		return (List<Card>) cardRepository.findAllWithUser();
	}
	

	@Override
	public Card findById(Long id) throws CardNotFoundException {
		return cardRepository.findByIdWithUser(id).orElseThrow(() -> new CardNotFoundException());
	}

	@Override
	@Transactional
	public void save(Card card) {
		cardRepository.save(card);
	}

	@Override
	@Transactional
	public void DeleteById(Long id) {
		cardRepository.deleteById(id);
	}

}
