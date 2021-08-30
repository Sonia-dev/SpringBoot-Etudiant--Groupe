package com.sonia.jpa.controller;

import java.awt.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/groupe")
public class GroupeController {
	@Autowired
	private GroupeRepository groupRep;
	@Autowired
	private EtudiantRepository etudiantRep;
	

	//add_departement
		@PostMapping("/new")
	    public ResponseEntity<Groupe> addgroupe(@RequestBody Groupe dep) {
			
			
	        return new ResponseEntity<Groupe>(groupRep.save(dep), HttpStatus.OK);
	    }
		//list

		@GetMapping("/groupes")
		public Iterable<Groupe> allgr(){
			Iterable<Groupe> list =  groupRep.findAll();	
			for(Groupe gr : list) {
				for(Etudiant et : gr.getEtudiants()) {
					et.setGroupes(null);
				}
				
			}
			return list;
			
		}
		

		@GetMapping("/groupe/{id}")
		public Groupe recherche(@PathVariable Long id) {
			Groupe e = groupRep.findById(id).get();
			for(Etudiant etudiant : e.getEtudiants()) {
				etudiant.setGroupes(null);
			}
			return e;
		}
		@PutMapping("/groupe/update/{id}")
		public ResponseEntity<Groupe>update(@RequestBody Groupe groupe,@PathVariable Long id ) {
			
			Groupe gr=groupRep.findById(id).get();//get el groupe eli hachti bih
		
			gr.setNom(groupe.getNom());//tseti el nom
		
			
			Groupe updatedgr=groupRep.save(gr);//tseyvi el groupe jdid
			return ResponseEntity.ok(updatedgr);
		}
		


		@DeleteMapping("/groupe/{id}")
		public String delete(@PathVariable Long id) {
			groupRep.deleteById(id);
		return "Suppression avec succés";
	    }
		
		
		
		
}
