package org.house.db.repository.jpa;

import java.util.List;

import org.house.db.entity.jpa.ProjectBasicDataJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectBasicDataJpaRepository extends JpaRepository<ProjectBasicDataJpa, Integer> {
	ProjectBasicDataJpa findByProjectId(String projectId);

	List<ProjectBasicDataJpa> findByProjectNameLike(String projectName);

	ProjectBasicDataJpa findByPreSellLicenseId(String preSellLicenseId);

	List<ProjectBasicDataJpa> findByProjectAddressLike(String projectAddress);

	List<ProjectBasicDataJpa> findByDeveloperLike(String developer);

	List<ProjectBasicDataJpa> findByDivision(String division);

}
