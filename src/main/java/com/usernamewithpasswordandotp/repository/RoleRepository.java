package com.usernamewithpasswordandotp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usernamewithpasswordandotp.model.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
