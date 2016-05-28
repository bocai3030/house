package org.house.db.repository;

import java.util.List;

import org.house.db.entity.ProjectBasicData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectBasicDataRepository extends JpaRepository<ProjectBasicData, Integer> {
	ProjectBasicData findByProjectId(String projectId);

	List<ProjectBasicData> findByDivision(String division);
}
