package org.house.db.repository.solr;

import org.house.db.entity.solr.PreSellLicenseDataSolr;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface PreSellLicenseDataSolrRepository extends SolrCrudRepository<PreSellLicenseDataSolr, Integer> {
	@Query(value = "pre_sell_license_id:?0 AND entity_name:pre_sell_license_data")
	PreSellLicenseDataSolr findByPreSellLicenseId(String preSellLicenseId);
}
