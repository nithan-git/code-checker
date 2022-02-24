package com.cskku.project;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRoomRepository extends CrudRepository<ClassRoom,Long>{

	
//	@Query("SELECT c FROM ClassRoom c WHERE c.teacher.teacher_id = :teacher_id")
//	public Iterable<ClassRoom> findClassRoomByTeacherID(@Param("teacher_id") String teacher_id);
	
	
}
