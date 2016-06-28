package org.house.db.repository.solr;

import java.util.Date;
import java.util.List;

import org.house.db.entity.solr.EarthBasicDataSolr;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface EarthBasicDataSolrRepository extends SolrCrudRepository<EarthBasicDataSolr, Integer> {
	@Query(value = "earth_license_id:?0 AND entity_name:earth_basic_data")
	EarthBasicDataSolr findByEarthLicenseId(String earthLicenseId);

	@Query(value = "borrow_from:?0 TO ?1 AND entity_name:earth_basic_data")
	List<EarthBasicDataSolr> findByBorrowFromBetween(Date from, Date to);
}
