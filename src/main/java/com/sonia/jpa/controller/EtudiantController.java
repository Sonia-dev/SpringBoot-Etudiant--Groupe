package com.sonia.jpa.controller;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.sonia.jpa.entities.Etudiant;
import com.sonia.jpa.entities.Groupe;
import com.sonia.jpa.repository.EtudiantRepository;
import com.sonia.jpa.repository.GroupeRepository;



@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/v1/")
public class EtudiantController {
	@Autowired
	private EtudiantRepository etudiantRep;
	
	@Autowired
	private GroupeRepository grouperep;

	
	

	@PostMapping("/add/{groupes}")
	
	public Etudiant add(@RequestBody Etudiant etudiant,@PathVariable  Long groupes) {
		
		
		Groupe groupe=grouperep.findById(groupes).get();
		
		etudiant.setGroupes(groupe);
		
		
		return etudiantRep.save(etudiant);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/etudiant/{id}")
	public ResponseEntity<Etudiant>getEtudiantById(@PathVariable Long id){
		
	
		Etudiant etudiant = etudiantRep.findById(id).get();
	
	
		return ResponseEntity.ok(etudiant);
				}
	
	
	@GetMapping("/etudiants")
	public List<Etudiant> alletudiant(){
			
			return etudiantRep.findAll();
		}
	
	
	@PutMapping("/etudiants/{id}/{groupes}")
	public ResponseEntity<Etudiant>updateEtudiant(@PathVariable Long id ,@RequestBody Etudiant detailEtudiant,@PathVariable Long groupes)
	{
		Etudiant etudiant=etudiantRep.findById(id).get();
		
		etudiant.setNom(detailEtudiant.getNom());
		etudiant.setMatricule(detailEtudiant.getMatricule());
		etudiant.setPrenom(detailEtudiant.getPrenom());
		etudiant.setDate_naissance(detailEtudiant.getDate_naissance());
		
		
		
		Groupe groupe =grouperep.findById(groupes).get();
		etudiant.setGroupes(groupe);
		
		Etudiant updatedEtudiant=etudiantRep.save(etudiant);
		return ResponseEntity.ok(updatedEtudiant);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@DeleteMapping("/etudiant/{id}")
	public String delete(@PathVariable Long id) {
		etudiantRep.deleteById(id);
	return "Suppression avec succés";
    }
	
	
	
	
}
