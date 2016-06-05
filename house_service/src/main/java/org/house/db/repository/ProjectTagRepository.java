package org.house.db.repository;

import org.house.db.entity.ProjectTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTagRepository extends JpaRepository<ProjectTag, Integer> {
	ProjectTag findByProjectId(String projectId);
}
