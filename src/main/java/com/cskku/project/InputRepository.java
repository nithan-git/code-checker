package com.cskku.project;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputRepository extends CrudRepository<Input,Long>{
	
}
