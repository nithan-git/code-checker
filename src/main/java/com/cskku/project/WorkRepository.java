package com.cskku.project;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends CrudRepository<Work,Long>{
	
	@Query("SELECT w FROM Work w WHERE w.student = :student AND w.laboratory = :laboratory")
	Work findByStudentAndLaboratory(@Param("student") Student student,@Param("laboratory") Laboratory laboratory);
}
