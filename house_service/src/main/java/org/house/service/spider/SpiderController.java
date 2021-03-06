package org.house.service.spider;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.house.db.entity.jpa.EarthBasicDataJpa;
import org.house.db.entity.jpa.PreSellLicenseDataJpa;
import org.house.db.entity.jpa.ProjectBasicDataJpa;
import org.house.db.repository.jpa.EarthBasicDataJpaRepository;
import org.house.db.repository.jpa.PreSellLicenseDataJpaRepository;
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
	private PreSellLicenseDataJpaRepository preSellLicenseDataJpaRepository;
	@Autowired
	private EarthBasicDataJpaRepository earthBasicDataJpaRepository;

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
	public PreSellLicenseDataJpa updatePreSellLicenseData(final String projectId, final String preSellLicenseId) {
		final PreSellLicenseDataJpa preSellLicenseDataJpa = WebSpider.getPreSellLicenseData(projectId, preSellLicenseId);

		final PreSellLicenseDataJpa preSellLicenseDataJpaInDb = this.preSellLicenseDataJpaRepository.findByPreSellLicenseId(preSellLicenseId);
		if (preSellLicenseDataJpaInDb == null) {
			this.preSellLicenseDataJpaRepository.save(preSellLicenseDataJpa);
		} else if (!preSellLicenseDataJpaInDb.equals(preSellLicenseDataJpa)) {
			preSellLicenseDataJpaInDb.fromObj(preSellLicenseDataJpa);
			this.preSellLicenseDataJpaRepository.save(preSellLicenseDataJpaInDb);
		} else {
		}

		return preSellLicenseDataJpa;
	}

	@RequestMapping(value = "/updateEarthBasicData", produces = "application/json")
	public EarthBasicDataJpa updateEarthBasicData(@RequestParam(value = "projectId", defaultValue = "") final String projectId,
			final String countryName, final String countryId, final String queryCountryId, final int flag) throws UnsupportedEncodingException {
		final EarthBasicDataJpa earthBasicDataJpa = WebSpider.getEarthBasicData(countryName, countryId, queryCountryId, flag);
		earthBasicDataJpa.setProjectId(projectId);

		final EarthBasicDataJpa earthBasicDataJpaInDb = this.earthBasicDataJpaRepository.findByEarthLicenseId(queryCountryId);
		if (earthBasicDataJpaInDb == null) {
			this.earthBasicDataJpaRepository.save(earthBasicDataJpa);
		} else if (!earthBasicDataJpaInDb.equals(earthBasicDataJpa)) {
			earthBasicDataJpaInDb.fromObj(earthBasicDataJpa);
			this.earthBasicDataJpaRepository.save(earthBasicDataJpaInDb);
		} else {
		}

		return earthBasicDataJpa;
	}
}