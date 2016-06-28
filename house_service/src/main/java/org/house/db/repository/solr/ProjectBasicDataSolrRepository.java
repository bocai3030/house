package org.house.db.repository.solr;

import java.util.List;

import org.house.db.entity.solr.ProjectBasicDataSolr;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface ProjectBasicDataSolrRepository extends SolrCrudRepository<ProjectBasicDataSolr, Integer> {
	@Query(value = "project_id:?0 AND entity_name:project_basic_data")
	ProjectBasicDataSolr findByProjectId(String projectId);

	@Query(value = "project_name:*?0* AND entity_name:project_basic_data")
	List<ProjectBasicDataSolr> findByProjectNameContaining(String projectName);

	@Query(value = "pre_sell_license_id:?0 AND entity_name:project_basic_data")
	ProjectBasicDataSolr findByPreSellLicenseId(String preSellLicenseId);

	@Query(value = "project_address:*?0* AND entity_name:project_basic_data")
	List<ProjectBasicDataSolr> findByProjectAddressContaining(String projectAddress);

	@Query(value = "developer:*?0* AND entity_name:project_basic_data")
	List<ProjectBasicDataSolr> findByDeveloperContaining(String developer);

	@Query(value = "division:?0 AND entity_name:project_basic_data")
	List<ProjectBasicDataSolr> findByDivision(String division);

}
