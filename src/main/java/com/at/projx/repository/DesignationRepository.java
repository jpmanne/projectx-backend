/**
* @author  Jaya Prakash Manne
*/
package com.at.projx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.projx.dao.model.DesignationDetails;

@Repository
public interface DesignationRepository extends JpaRepository<DesignationDetails, Long> {
	@Query("SELECT a FROM DesignationDetails a WHERE a.designation = :designation and a.organizationDetails.organizationDetailsId = :organizationDetailsId")
	List<DesignationDetails> findByDesignation(@Param("designation") String designation, @Param("organizationDetailsId") Long organizationDetailsId);
	
	@Query("SELECT a FROM DesignationDetails a WHERE a.organizationDetails.organizationDetailsId = :organizationDetailsId")
	List<DesignationDetails> getDesignations(@Param("organizationDetailsId") Long organizationDetailsId);
	
	@Query("SELECT a FROM DesignationDetails a WHERE a.organizationDetails.organizationDetailsId = :organizationDetailsId and a.status = :status")
	List<DesignationDetails> getDesignations(@Param("organizationDetailsId") Long organizationDetailsId, @Param("status") String status);
}
