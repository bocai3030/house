package org.house.db.repository;

import java.util.Date;
import java.util.List;

import org.house.db.entity.EarthBasicData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EarthBasicDataRepository extends JpaRepository<EarthBasicData, Integer> {
	EarthBasicData findByEarthLicenseId(String earthLicenseId);

	List<EarthBasicData> findByBorrowFromBetween(Date from, Date to);
}
