/**
* @author  Jaya Prakash Manne
*/
package com.at.projx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.projx.dao.model.EducationDetails;

@Repository
public interface EducationRepository extends JpaRepository<EducationDetails, Long> {
	@Query("SELECT a FROM EducationDetails a WHERE a.status = :status and a.userDetails.userDetailsId = :userDetailsId")
	List<EducationDetails> getEducations(@Param("status") String status, @Param("userDetailsId") Long userDetailsId);
}
