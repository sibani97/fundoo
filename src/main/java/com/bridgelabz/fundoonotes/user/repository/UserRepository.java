package com.bridgelabz.fundoonotes.user.repository;



import java.util.Optional;

//import java.util.Optional;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.notes.model.Notes;
import com.bridgelabz.fundoonotes.user.model.User;

@Repository
 public interface UserRepository extends JpaRepository<User,Long> {
	
public Optional<User> findByEmailId(String email);
public Optional<User> findByUserId(Long userId);

}
