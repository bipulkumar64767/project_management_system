package com.pms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pms.entity.pmsEntity;

public interface PmsRepository extends JpaRepository<pmsEntity, Integer>{
	
	@Query(value = "SELECT COUNT(*) FROM pms_entity where project_id =:projectId ", nativeQuery = true)
	int checkProjectIdIsPresent(String projectId );
	
	@Query(value = "SELECT * FROM pms_entity WHERE LOWER(project_name) LIKE %:projectId% ", nativeQuery = true)
	List<pmsEntity> searchAllProjectByName(String projectId );


}
