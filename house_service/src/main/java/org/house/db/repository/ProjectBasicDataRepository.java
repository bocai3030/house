package org.house.db.repository;

import java.util.List;

import org.house.db.entity.ProjectBasicData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectBasicDataRepository extends JpaRepository<ProjectBasicData, Integer> {
	ProjectBasicData findByProjectId(String projectId);

	List<ProjectBasicData> findByProjectNameLike(String projectName);

	ProjectBasicData findByPreSellLicenseId(String preSellLicenseId);

	List<ProjectBasicData> findByProjectAddressLike(String projectAddress);

	List<ProjectBasicData> findByDeveloperLike(String developer);

	List<ProjectBasicData> findByDivision(String division);

}
