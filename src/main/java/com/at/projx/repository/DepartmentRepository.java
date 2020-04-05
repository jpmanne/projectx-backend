/**
* @author  Jaya Prakash Manne
*/
package com.at.projx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.projx.dao.model.DepartmentDetails;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentDetails, Long> {
	@Query("SELECT a FROM DepartmentDetails a WHERE a.department = :department and a.organizationDetails.organizationDetailsId = :organizationDetailsId")
	List<DepartmentDetails> findByDepartment(@Param("department") String department, @Param("organizationDetailsId") Long organizationDetailsId);
	
	@Query("SELECT a FROM DepartmentDetails a WHERE a.organizationDetails.organizationDetailsId = :organizationDetailsId")
	List<DepartmentDetails> getDepartments(@Param("organizationDetailsId") Long organizationDetailsId);
	
	@Query("SELECT a FROM DepartmentDetails a WHERE a.organizationDetails.organizationDetailsId = :organizationDetailsId and a.status = :status")
	List<DepartmentDetails> getDepartments(@Param("organizationDetailsId") Long organizationDetailsId, @Param("status") String status);
}
