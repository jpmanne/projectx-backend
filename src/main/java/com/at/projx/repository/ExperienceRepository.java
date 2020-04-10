/**
* @author  Jaya Prakash Manne
*/
package com.at.projx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.projx.dao.model.ExperienceDetails;

@Repository
public interface ExperienceRepository extends JpaRepository<ExperienceDetails, Long> {
	@Query("SELECT a FROM ExperienceDetails a WHERE a.status = :status and a.userDetails.userDetailsId = :userDetailsId")
	List<ExperienceDetails> getExperiences(@Param("status") String status, @Param("userDetailsId") Long userDetailsId);
}
