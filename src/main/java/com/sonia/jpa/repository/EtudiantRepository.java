package com.sonia.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonia.jpa.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

}
