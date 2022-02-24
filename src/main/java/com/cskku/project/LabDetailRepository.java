package com.cskku.project;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabDetailRepository extends CrudRepository<LabDetail,Long>{
	
}
