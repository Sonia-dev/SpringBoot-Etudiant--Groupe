package com.sonia.jpa.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@Entity
public class Groupe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long id;
	private String nom;
	
	
	
	
	@OneToMany(cascade = CascadeType.ALL ,targetEntity = Etudiant.class,mappedBy = "groupes" ,fetch = FetchType.LAZY)
	List<Etudiant>etudiants=new ArrayList<Etudiant>();








	public Groupe(Long id, String nom, List<Etudiant> etudiants) {
		super();
		this.id = id;
		this.nom = nom;
		this.etudiants = etudiants;
	}



	public Groupe() {
		super();
	}


	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}


	
	public List<Etudiant> getEtudiants() {
		return etudiants;
	}



	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}
	
}
