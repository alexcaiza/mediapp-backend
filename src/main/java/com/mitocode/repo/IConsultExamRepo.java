/**
 * 
 */
package com.mitocode.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.ConsultExam;

/**
 * @author Alex
 *
 */
public interface IConsultExamRepo extends IGenericRepo<ConsultExam, Integer> {
	
	//@Transactional
	@Modifying
	@Query(value = "INSERT INTO consult_exam(id_consult, id_exam) VALUES (:idConsult, :idExam)", nativeQuery = true)
	Integer saveExam(@Param("idConsult") Integer idConsult, @Param("idExam") Integer idExam);
	
}
