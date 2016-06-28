package org.house.db.repository.solr;

import org.house.db.entity.solr.PreSellLicenseDataSolr;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface PreSellLicenseDataSolrRepository extends SolrCrudRepository<PreSellLicenseDataSolr, Integer> {
	PreSellLicenseDataSolr findByPreSellLicenseId(String preSellLicenseId);
}
