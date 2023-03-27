package com.projet.maktub.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.projet.maktub.model.OrderClient;
import com.projet.maktub.model.Person;
import com.projet.maktub.model.Product;
import com.projet.maktub.repository.OrderClientRepository;
import com.projet.maktub.repository.PersonRepository;
import com.projet.maktub.repository.ProductRepository;
import com.projet.maktub.services.OrderClientService;
import com.projet.maktub.services.PersonService;
import com.projet.maktub.services.ProductService;


@CrossOrigin(origins = "*" )
@RestController
@RequestMapping("productsdone")
                                                  
public class OrderClientController {
	
	
	@Autowired
	OrderClientService orderClientService;
	

	@Autowired
	OrderClientRepository orderClientRepository;
	
	
	@Autowired 
	PersonService utilisateurService;
	@Autowired
	ProductService produitService;
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	ProductRepository  productRepository;


	
	@PostMapping(path="/addProductDoneToUser")
	public ResponseEntity<Boolean> addProductDoneToUser(@RequestBody ObjectNode json) throws JsonProcessingException{
		
		
		List list ;

		
			
		StreamSupport.stream(json.get("list").spliterator(), false)
		 .forEach(element -> {
			 
		     int idpro = element.get("idpro").asInt();
		     int qte = element.get("qte").asInt();
		 	

			 
		   /*  Product productToAdd = this.productRepository.findById(idpro).get();*/
				System.out.println(idpro);
				
				Person persono = new Person();

				
				try {
					
					persono = new ObjectMapper().treeToValue(json.get("person"), Person.class);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 	 
				
				boolean test = this.orderClientService.addProductDoneToUser(idpro,persono,"444" , qte);
				
			
		 });
			
		
	
		return new ResponseEntity<Boolean>(HttpStatus.ACCEPTED);

	}
	
	
	
	@PostMapping(path="/status")
	public ResponseEntity<Person> updateProductDone(@RequestBody ObjectNode json){
		Person user = new Person();
		List list;
	
			try {
			
		    user = new ObjectMapper().treeToValue(json.get("person"), Person.class);
			int idpro = json.get("idpro").asInt();
			String code = json.get("code").asText();
			int qte = json.get("qte").asInt();
			
			boolean test = this.orderClientService.addProductDoneToUser(idpro,user,code,qte);
			

			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ResponseEntity<Person>(user,HttpStatus.OK);
	}
	
	
	@GetMapping
	public ResponseEntity<List<OrderClient>> getAllUserDoneProducts
					(@RequestParam(value="email", required=true) String email){
		List<OrderClient> listeDone = this.orderClientService.getAlluserDoneProducts(email);
		
		if(listeDone!=null) {
			return new ResponseEntity<List<OrderClient>>(listeDone,HttpStatus.OK);
		}
		else 
			return new ResponseEntity<List<OrderClient>>(listeDone,HttpStatus.NOT_FOUND);
	}
	
	
	
	@PostMapping(path="/deleteDoneProductFromUser")
	public ResponseEntity<Boolean> deleteProductFromUser(@RequestBody ObjectNode jso){
		Person user = new Person();
		Product productToRemove = new Product();
		
			int idpro = jso.get("idpro").asInt();
			String code = jso.get("code").asText();
			int qte = jso.get("qte").asInt();
			String email = jso.get("email").asText();
			
			user = this.personRepository.findByMail(email).get();
			productToRemove = this.productRepository.findById(idpro).get();
			
			boolean done = this.orderClientService.removeProductDoneFromUser(productToRemove, user , code , qte);
		return new ResponseEntity<Boolean>(done,HttpStatus.OK);
	

	}

	@GetMapping("/all")
	public ResponseEntity<List<OrderClient>> getAll() {
		try {
			List<OrderClient> tutorials = new ArrayList<OrderClient>();

				orderClientRepository.findAll().forEach(tutorials::add);
		
			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

	
	
	
	
	
	
	
	

}
