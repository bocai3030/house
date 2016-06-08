package org.house.db.repository;

import java.util.List;

import org.house.db.entity.ProjectTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectTagRepository extends JpaRepository<ProjectTag, Integer> {
	ProjectTag findByProjectId(String projectId);

	List<ProjectTag> findByFocusStatusLike(String focusStatusLike);

	@Query("SELECT DISTINCT focusStatus from ProjectTag")
	List<String> findDistinctFocusStatus();
}
