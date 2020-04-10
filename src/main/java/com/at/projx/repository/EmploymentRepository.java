/**
* @author  Jaya Prakash Manne
*/
package com.at.projx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.projx.dao.model.EmploymentDetails;

@Repository
public interface EmploymentRepository extends JpaRepository<EmploymentDetails, Long> {
	@Query("SELECT a FROM EmploymentDetails a WHERE a.employeeId = :employeeId and a.userDetails.organizationDetails.organizationDetailsId = :organizationDetailsId")
	List<EmploymentDetails> findByEmployeeId(@Param("employeeId") String employeeId, @Param("organizationDetailsId") Long organizationDetailsId);
	
	@Query("SELECT a FROM EmploymentDetails a WHERE a.userDetails.userDetailsId = :userDetailsId")
	List<EmploymentDetails> findByUserDetailsId(@Param("userDetailsId") Long userDetailsId);
	
}
