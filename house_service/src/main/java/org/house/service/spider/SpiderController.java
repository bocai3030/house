package org.house.service.spider;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.house.db.entity.jpa.EarthBasicData;
import org.house.db.entity.jpa.PreSellLicenseData;
import org.house.db.entity.jpa.ProjectBasicDataJpa;
import org.house.db.repository.jpa.EarthBasicDataRepository;
import org.house.db.repository.jpa.PreSellLicenseDataRepository;
import org.house.db.repository.jpa.ProjectBasicDataJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spider")
public class SpiderController {
	@Autowired
	private ProjectBasicDataJpaRepository projectBasicDataJpaRepository;
	@Autowired
	private PreSellLicenseDataRepository preSellLicenseDataRepository;
	@Autowired
	private EarthBasicDataRepository earthBasicDataRepository;

	@RequestMapping(value = "/updateProjectBasicData", produces = "application/json")
	public ProjectBasicDataJpa updateProjectBasicData(final String projectId) throws UnsupportedEncodingException {
		final ProjectBasicDataJpa projectBasicDataJpa = WebSpider.getProjectBasicData(projectId);

		final ProjectBasicDataJpa projectBasicDataJpaInDb = this.projectBasicDataJpaRepository.findByProjectId(projectBasicDataJpa.getProjectId());
		if (projectBasicDataJpaInDb == null) {
			this.projectBasicDataJpaRepository.save(projectBasicDataJpa);
		} else if (!projectBasicDataJpaInDb.equals(projectBasicDataJpa)) {
			projectBasicDataJpaInDb.fromObj(projectBasicDataJpa);
			this.projectBasicDataJpaRepository.save(projectBasicDataJpaInDb);
		} else {
		}

		return projectBasicDataJpa;
	}

	@RequestMapping(value = "/updateProjectBasicDataByPage", produces = "application/json")
	public List<ProjectBasicDataJpa> updateProjectBasicDataByPage(final int page) throws UnsupportedEncodingException {
		final List<ProjectBasicDataJpa> projectBasicDataJpas = WebSpider.getProjectBasicData(page);

		for (final ProjectBasicDataJpa projectBasicDataJpa : projectBasicDataJpas) {
			final ProjectBasicDataJpa projectBasicDataJpaInDb = this.projectBasicDataJpaRepository
					.findByProjectId(projectBasicDataJpa.getProjectId());
			if (projectBasicDataJpaInDb == null) {
				this.projectBasicDataJpaRepository.save(projectBasicDataJpa);
			} else if (!projectBasicDataJpaInDb.equals(projectBasicDataJpa)) {
				projectBasicDataJpaInDb.fromObj(projectBasicDataJpa);
				this.projectBasicDataJpaRepository.save(projectBasicDataJpaInDb);
			} else {
			}
		}

		return projectBasicDataJpas;
	}

	@RequestMapping(value = "/updatePreSellLicenseData", produces = "application/json")
	public PreSellLicenseData updatePreSellLicenseData(final String projectId, final String preSellLicenseId) {
		final PreSellLicenseData preSellLicenseData = WebSpider.getPreSellLicenseData(projectId, preSellLicenseId);

		final PreSellLicenseData preSellLicenseDataInDb = this.preSellLicenseDataRepository.findByPreSellLicenseId(preSellLicenseId);
		if (preSellLicenseDataInDb == null) {
			this.preSellLicenseDataRepository.save(preSellLicenseData);
		} else if (!preSellLicenseDataInDb.equals(preSellLicenseData)) {
			preSellLicenseDataInDb.fromObj(preSellLicenseData);
			this.preSellLicenseDataRepository.save(preSellLicenseDataInDb);
		} else {
		}

		return preSellLicenseData;
	}

	@RequestMapping(value = "/updateEarthBasicData", produces = "application/json")
	public EarthBasicData updateEarthBasicData(@RequestParam(value = "projectId", defaultValue = "") final String projectId, final String countryName,
			final String countryId, final String queryCountryId, final int flag) throws UnsupportedEncodingException {
		final EarthBasicData earthBasicData = WebSpider.getEarthBasicData(countryName, countryId, queryCountryId, flag);
		earthBasicData.setProjectId(projectId);

		final EarthBasicData earthBasicDataInDb = this.earthBasicDataRepository.findByEarthLicenseId(queryCountryId);
		if (earthBasicDataInDb == null) {
			this.earthBasicDataRepository.save(earthBasicData);
		} else if (!earthBasicDataInDb.equals(earthBasicData)) {
			earthBasicDataInDb.fromObj(earthBasicData);
			this.earthBasicDataRepository.save(earthBasicDataInDb);
		} else {
		}

		return earthBasicData;
	}
}