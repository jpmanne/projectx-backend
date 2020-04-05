/**
* @author  Jaya Prakash Manne
* @version 1.0
* @since   01-Nov-2019 
*/
package com.at.projx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.projx.dao.model.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {
	
	@Query("SELECT a FROM UserDetails a WHERE a.email = :email AND a.password = :password")
	List<UserDetails> getUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);
	
	@Query("SELECT a FROM UserDetails a WHERE a.organizationDetails.organizationDetailsId = :organizationDetailsId and a.status = :status and a.userDetailsId != :userDetailsId")
	List<UserDetails> getUsers(@Param("organizationDetailsId") Long organizationDetailsId, @Param("status") String status, @Param("userDetailsId") Long userDetailsId);
	
	//List<UserDetails> getUsersByDateOfBirth(Date dateOfBirth);
	
	@Query("SELECT a FROM UserDetails a WHERE a.status = :status")
	List<UserDetails> getAllUsers(String status);
	
	@Query("SELECT a FROM UserDetails a WHERE a.userDetailsId = :userDetailsId")
	List<UserDetails> getUsers(Long userDetailsId);
	
	//https://javadeveloperzone.com/spring/spring-jpa-query-in-clause-example/
	@Query("SELECT a FROM UserDetails a WHERE a.userDetailsId IN (:userDetailsIds)")
	List<UserDetails> findByUserDetailsIds(@Param("userDetailsIds")List<Long> userDetailsIds);
	
	@Query("SELECT a FROM UserDetails a WHERE a.email = :email")
	List<UserDetails> getUserByEmail(@Param("email") String email);
	
	@Query("select u from UserDetails u where u.userDetailsId = ?1")
	UserDetails findByUserDetailsId(Long userDetailsId);
	
}
