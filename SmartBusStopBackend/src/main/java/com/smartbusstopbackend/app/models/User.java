package com.smartbusstopbackend.app.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Gabriel Huertas
 *
 */
@Data
@Entity
@Table(name="USERS")
@NoArgsConstructor
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "USERS_ID_SEQ")
	private Long id;
	
	@Column(unique = true)
	private String DNI;
	private String name;
	private String lastName;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate birthDate;
	
}
