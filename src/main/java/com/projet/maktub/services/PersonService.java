package com.projet.maktub.services;

import java.util.List;
import java.util.Optional;

import com.projet.maktub.dto.PersonDto;
import com.projet.maktub.model.Links;
import com.projet.maktub.model.Person;




public interface PersonService {
	
	
	
	String signin(String email, String password);
	String signup(Person user);
	
	boolean testPassword(String email, String oldPass);
	boolean updatePassword(String email, String oldPass, String newPass);

	PersonDto save(PersonDto dto);
	
	boolean addFriendToUser(Person person , Person friend);

	boolean removeFriendFromUser(Person user , Person friend);
	
	List<Person> getAlluserFriends(String mail);
	
	List<Person> getAlluserFollowing(String mail);
	
	boolean addfollowing(Person person,  Person friend);
	
	boolean removeFollowingFromUser(Person person , Person friend);
	
	boolean saveUser(Person utilisateur);

	PersonDto findById(Integer id);
	
	boolean updateUser(String email,String username,String tel, String nom,String prenom , String adresse,  String fonction);

	List<PersonDto> findAll();
	
	Optional<Person> findByEmail(String email);


    void delete(Integer id);


}
