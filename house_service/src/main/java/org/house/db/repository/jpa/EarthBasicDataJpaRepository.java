package org.house.db.repository.jpa;

import java.util.Date;
import java.util.List;

import org.house.db.entity.jpa.EarthBasicDataJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EarthBasicDataJpaRepository extends JpaRepository<EarthBasicDataJpa, Integer> {
	EarthBasicDataJpa findByEarthLicenseId(String earthLicenseId);

	List<EarthBasicDataJpa> findByBorrowFromBetween(Date from, Date to);
}
