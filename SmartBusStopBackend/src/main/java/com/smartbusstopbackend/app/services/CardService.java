package com.smartbusstopbackend.app.services;

import java.util.List;

import com.smartbusstopbackend.app.exceptions.CardNotFoundException;
import com.smartbusstopbackend.app.models.Card;

public interface CardService {
	
	public List<Card> findAll();
	public Card findById(Long id) throws CardNotFoundException;
	public void save(Card card);
	public void DeleteById(Long id);
	public List<Card> findAllWithUser();
	
}
