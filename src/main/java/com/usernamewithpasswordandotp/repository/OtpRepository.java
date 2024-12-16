package com.usernamewithpasswordandotp.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.usernamewithpasswordandotp.model.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
	@Query("select o from Otp o where o.email = ?1 and o.createdAt <= ?2 and o.expirationTime >= ?2 and o.status = false order by o.createdAt desc")
	List<Otp> findByOtpByEmail(@Param("email") String email, @Param("date") LocalDateTime date);

}
