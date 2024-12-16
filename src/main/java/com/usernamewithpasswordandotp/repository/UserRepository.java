package com.usernamewithpasswordandotp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usernamewithpasswordandotp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	User findByMobileNumber(String username);
	
	User findByEmail(String email);
	
}
