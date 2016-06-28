package org.house.db.repository.jpa;

import java.util.List;

import org.house.db.entity.jpa.ProjectTagJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectTagJpaRepository extends JpaRepository<ProjectTagJpa, Integer> {
	ProjectTagJpa findByProjectId(String projectId);

	List<ProjectTagJpa> findByFocusStatusLike(String focusStatusLike);

	@Query("SELECT DISTINCT focusStatus from ProjectTag")
	List<String> findDistinctFocusStatus();
}
