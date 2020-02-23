package com.smartbusstopbackend.app.restcontrollers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartbusstopbackend.app.exceptions.CardNotFoundException;
import com.smartbusstopbackend.app.models.Card;
import com.smartbusstopbackend.app.models.User;
import com.smartbusstopbackend.app.services.CardService;
import com.smartbusstopbackend.app.services.UserService;

@Controller
@RequestMapping("/cards")
public class CardRestController {

	private CardService cardService;

	@Autowired
	public CardRestController(CardService cardService) {
		this.cardService = cardService;
	}

	@ResponseBody
	@GetMapping("")
	public List<Card> getAllCards() {
		return cardService.findAll();
	}
	
	@ResponseBody
	@GetMapping("/{cardId}")
	public Card getCardById(@PathVariable Long cardId) {
		try {
			return cardService.findById(cardId);
		} catch (CardNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PutMapping("/cards/{cardId}")
	public ResponseEntity<?> updateCard(@RequestBody Card card, @PathVariable Long id){
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			Card cardAux = cardService.findById(id);
			
			try {	
				cardService.save(card);
			} catch (DataAccessException e) {

				response.put("mensaje", "Error al hacer registro en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			response.put("mensaje", "Tarjeta actualizada con éxito");
			response.put("card", card);
			
		} catch (CardNotFoundException e) {
			response.put("error", e.getMessage());
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
}
