/**
* @author  Jaya Prakash Manne
*/
package com.at.projx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.projx.dao.model.ProfileDetails;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileDetails, Long> {
	@Query("SELECT a FROM ProfileDetails a WHERE a.userDetails.userDetailsId = :userDetailsId")
	List<ProfileDetails> getProfile(@Param("userDetailsId") Long userDetailsId);
}
