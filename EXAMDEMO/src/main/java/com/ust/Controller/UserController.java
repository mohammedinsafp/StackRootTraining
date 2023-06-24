package com.ust.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ust.Repository.UserRepository;
import com.ust.model.User;

@RestController
public class UserController {
		@Autowired
		private UserRepository userRepository;
		@PostMapping("/user")
		public ResponseEntity<User> createUser(@RequestBody User user){
			User createUser=userRepository.save(user);
			return ResponseEntity.ok().body(createUser);
		}
		@PostMapping("/users")
		public ResponseEntity<List<User>> createUser(@RequestBody List<User> user){
			List<User> createUser=userRepository.saveAll(user);
			return ResponseEntity.ok().body(createUser);
		}
		@GetMapping("/users")
		public ResponseEntity<List<User>> getAllUsers(){
			List<User> getAllUser=userRepository.findAll();
			return ResponseEntity.ok().body(getAllUser);
		}
		@GetMapping("/user/{id}")
		public ResponseEntity<User> getUserById(@PathVariable int id){
			Optional<User> getUser=userRepository.findById(id);
			if(getUser.isPresent()) {
			return ResponseEntity.ok().body(getUser.get());
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
		@PutMapping("/user/{id}")
		public ResponseEntity<User> updateUserById(@PathVariable int id,@RequestBody User user){
			Optional<User> updateUser=userRepository.findById(id);
			if(updateUser.isPresent()) {
				User updatedUser=updateUser.get();
				updatedUser.setName(user.getName());
				updatedUser.setAddress(user.getAddress());
				userRepository.save(updatedUser);
				return ResponseEntity.ok().body(updateUser.get());
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
		@DeleteMapping("/user/{id}")
		public ResponseEntity<User> deleteUserById(@PathVariable int id){
			Optional<User> deleteUser=userRepository.findById(id);
			if(deleteUser.isPresent()) {
				User toDeleteUser=deleteUser.get();
				userRepository.delete(toDeleteUser);
				return ResponseEntity.ok().body(toDeleteUser);
			}
			else {
			return ResponseEntity.noContent().build();
			}
		}
		@DeleteMapping("/users")
		public ResponseEntity<List<User>> deleteAllUser(){
				userRepository.deleteAll();
				return ResponseEntity.ok().body(userRepository.findAll());
		}
}
