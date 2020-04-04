/**
* @author  Jaya Prakash Manne
*/
package com.at.projx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.projx.dao.model.OrganizationDetails;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationDetails, Long> {
	
	@Query("select a from OrganizationDetails a where a.organizationName = :organizationName")
	List<OrganizationDetails> getOrganizationDetailsByOrganizationName(@Param("organizationName") String organizationName);
	
}
