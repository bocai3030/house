package org.house.db.repository.solr;

import java.util.List;

import org.house.db.entity.solr.ProjectBasicDataSolr;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface ProjectBasicDataSolrRepository extends SolrCrudRepository<ProjectBasicDataSolr, Integer> {
	ProjectBasicDataSolr findByProjectId(String projectId);

	@Query("project_name: *?0*")
	List<ProjectBasicDataSolr> findByProjectNameLike(String projectName);

	ProjectBasicDataSolr findByPreSellLicenseId(String preSellLicenseId);

	@Query("project_address: *?0*")
	List<ProjectBasicDataSolr> findByProjectAddressLike(String projectAddress);

	@Query("developer: *?0*")
	List<ProjectBasicDataSolr> findByDeveloperLike(String developer);

	List<ProjectBasicDataSolr> findByDivision(String division);

}
