package org.house.db.repository.solr;

import java.util.List;

import org.house.db.entity.solr.ProjectBasicDataSolr;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface ProjectBasicDataSolrRepository extends SolrCrudRepository<ProjectBasicDataSolr, Integer> {
	ProjectBasicDataSolr findByProjectId(String projectId);

	List<ProjectBasicDataSolr> findByProjectNameContaining(String projectName);

	ProjectBasicDataSolr findByPreSellLicenseId(String preSellLicenseId);

	List<ProjectBasicDataSolr> findByProjectAddressContaining(String projectAddress);

	List<ProjectBasicDataSolr> findByDeveloperContaining(String developer);

	List<ProjectBasicDataSolr> findByDivision(String division);

}
