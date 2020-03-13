/**
* @author  Jaya Prakash Manne
* @version 1.0
* @since   01-Nov-2019 
*/
package com.at.projx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.at.projx.dao.model.ExceptionDetails;

@Repository
public interface ExceptionRepository extends JpaRepository<ExceptionDetails, Long> {

}
