package com.smartbusstopbackend.app.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Gabriel Huertas
 *
 */
@Data
@Entity
@Table(name = "CARDS")
@NoArgsConstructor
public class Card implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "CARD_ID_SEQ")
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL )
	private User user;
	
	@Enumerated(EnumType.STRING)
	private CardStatus cardStatus;
	
	private Integer balance;
}
