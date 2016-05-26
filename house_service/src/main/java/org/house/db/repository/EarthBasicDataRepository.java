package org.house.db.repository;

import org.house.db.entity.EarthBasicData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EarthBasicDataRepository extends JpaRepository<EarthBasicData, Integer> {
	EarthBasicData findByEarthLicenceId(String earthLicenceId);
}
