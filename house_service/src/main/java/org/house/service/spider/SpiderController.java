package org.house.service.spider;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.house.db.entity.EarthBasicData;
import org.house.db.entity.PreSellLicenseData;
import org.house.db.entity.ProjectBasicData;
import org.house.db.repository.EarthBasicDataRepository;
import org.house.db.repository.PreSellLicenseDataRepository;
import org.house.db.repository.ProjectBasicDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spider")
public class SpiderController {
	@Autowired
	private ProjectBasicDataRepository projectBasicDataRepository;
	@Autowired
	private PreSellLicenseDataRepository preSellLicenseDataRepository;
	@Autowired
	private EarthBasicDataRepository earthBasicDataRepository;

	@RequestMapping(value = "/updateProjectBasicData", produces = "application/json")
	public ProjectBasicData updateProjectBasicData(final String projectId) throws UnsupportedEncodingException {
		final ProjectBasicData projectBasicData = WebSpider.getProjectBasicData(projectId);

		final ProjectBasicData projectBasicDataInDb = this.projectBasicDataRepository.findByProjectId(projectBasicData.getProjectId());
		if (projectBasicDataInDb == null) {
			this.projectBasicDataRepository.save(projectBasicData);
		} else if (!projectBasicDataInDb.equals(projectBasicData)) {
			projectBasicDataInDb.fromObj(projectBasicData);
			this.projectBasicDataRepository.save(projectBasicDataInDb);
		} else {
		}

		return projectBasicData;
	}

	@RequestMapping(value = "/updateProjectBasicDataByPage", produces = "application/json")
	public List<ProjectBasicData> updateProjectBasicDataByPage(final int page) throws UnsupportedEncodingException {
		final List<ProjectBasicData> projectBasicDatas = WebSpider.getProjectBasicData(page);

		for (final ProjectBasicData projectBasicData : projectBasicDatas) {
			final ProjectBasicData projectBasicDataInDb = this.projectBasicDataRepository.findByProjectId(projectBasicData.getProjectId());
			if (projectBasicDataInDb == null) {
				this.projectBasicDataRepository.save(projectBasicData);
			} else if (!projectBasicDataInDb.equals(projectBasicData)) {
				projectBasicDataInDb.fromObj(projectBasicData);
				this.projectBasicDataRepository.save(projectBasicDataInDb);
			} else {
			}
		}

		return projectBasicDatas;
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
	public EarthBasicData updateEarthBasicData(final String countryName, final String countryId, final String queryCountryId, final int flag)
			throws UnsupportedEncodingException {
		final EarthBasicData earthBasicData = WebSpider.getEarthBasicData(countryName, countryId, queryCountryId, flag);

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