package org.house.db.repository;

import org.house.db.entity.PreSellLicenseData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreSellLicenseDataRepository extends JpaRepository<PreSellLicenseData, Integer> {
	PreSellLicenseData findByPreSellLicenseId(String preSellLicenseId);
}
