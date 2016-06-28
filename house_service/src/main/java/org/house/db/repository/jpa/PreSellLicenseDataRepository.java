package org.house.db.repository.jpa;

import org.house.db.entity.jpa.PreSellLicenseData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreSellLicenseDataRepository extends JpaRepository<PreSellLicenseData, Integer> {
	PreSellLicenseData findByPreSellLicenseId(String preSellLicenseId);
}
