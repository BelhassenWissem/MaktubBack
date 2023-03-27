package com.projet.maktub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.maktub.model.Links;
import com.projet.maktub.model.Person;

public interface LinksRepository extends JpaRepository<Links, Integer> {

	Optional<Links> findByName(String name);
}
