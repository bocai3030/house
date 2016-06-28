package org.house.db.repository.jpa;

import org.house.db.entity.jpa.PreSellLicenseDataJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreSellLicenseDataJpaRepository extends JpaRepository<PreSellLicenseDataJpa, Integer> {
	PreSellLicenseDataJpa findByPreSellLicenseId(String preSellLicenseId);
}
