package org.house.db.repository.solr;

import java.util.List;

import org.house.db.entity.solr.ProjectTagSolr;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface ProjectTagSolrRepository extends SolrCrudRepository<ProjectTagSolr, Integer> {
	@Query(value = "project_id:?0 AND entity_name:project_tag")
	ProjectTagSolr findByProjectId(String projectId);

	@Query(value = "focus_status:*?0* AND entity_name:project_tag")
	List<ProjectTagSolr> findByFocusStatusLike(String focusStatusLike);

	@Facet(fields = "focus_status")
	// @Query("SELECT DISTINCT focusStatus from ProjectTagSolr")
	@Query(value = "entity_name:project_tag")
	FacetPage<ProjectTagSolr> findDistinctFocusStatus(Pageable pageable);
}
