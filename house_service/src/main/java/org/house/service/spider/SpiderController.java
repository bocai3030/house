package org.house.service.spider;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.house.db.entity.EarthBasicData;
import org.house.db.entity.PreSellLicenseData;
import org.house.db.entity.ProjectBasicData;
import org.house.db.repository.EarthBasicDataRepository;
import org.house.db.repository.ProjectBasicDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@RestController
@RequestMapping("/spider")
public class SpiderController {
	@Autowired
	private ProjectBasicDataRepository projectBasicDataRepository;
	@Autowired
	private EarthBasicDataRepository earthBasicDataRepository;

	@RequestMapping(value = "/updateProjectBasicData", produces = "application/json")
	Object updateProjectBasicData(@RequestParam("page") final int page) throws ClientProtocolException, IOException {
		final List<ProjectBasicData> projectBasicDatas = WebSpider.getProjectBasicData(page);

		final Map<String, List<String>> re = Maps.newHashMap();
		final List<String> updatedProjectBasicDatas = Lists.newArrayList();
		final List<String> newProjectBasicDatas = Lists.newArrayList();
		final List<String> sameProjectBasicDatas = Lists.newArrayList();
		for (final ProjectBasicData projectBasicData : projectBasicDatas) {
			final ProjectBasicData projectBasicDataInDb = this.projectBasicDataRepository
					.findByProjectId(projectBasicData.getProjectId());
			if (projectBasicDataInDb == null) {
				this.projectBasicDataRepository.save(projectBasicData);
				newProjectBasicDatas.add(projectBasicData.getProjectId());
			} else if (!projectBasicDataInDb.equals(projectBasicData)) {
				projectBasicDataInDb.fromObj(projectBasicData);
				this.projectBasicDataRepository.save(projectBasicDataInDb);
				updatedProjectBasicDatas.add(projectBasicData.getProjectId());
			} else {
				sameProjectBasicDatas.add(projectBasicData.getProjectId());
			}
		}

		re.put("new", newProjectBasicDatas);
		re.put("updated", updatedProjectBasicDatas);
		re.put("same", sameProjectBasicDatas);

		return re;
	}

	@RequestMapping(value = "/getPreSellLicenseData", produces = "application/json")
	Object getPreSellLicenseData(@RequestParam("projectId") final String projectId,
			@RequestParam("licenseId") final String licenseId) throws ClientProtocolException, IOException {
		final PreSellLicenseData preSellLicenseData = WebSpider.getPreSellLicenseData(projectId, licenseId);

		return preSellLicenseData;
	}

	@RequestMapping(value = "/updateEarthBasicData", produces = "application/json")
	Object updateEarthBasicData(@RequestParam("countryName") final String countryName,
			@RequestParam("countryId") final String countryId,
			@RequestParam("queryCountryId") final String queryCountryId) throws ClientProtocolException, IOException {
		final EarthBasicData earthBasicData = WebSpider.getEarthBasicData(countryName, countryId, queryCountryId);

		final Map<String, List<String>> re = Maps.newHashMap();
		final List<String> updatedEarthBasicDatas = Lists.newArrayList();
		final List<String> newEarthBasicDatas = Lists.newArrayList();
		final List<String> sameEarthBasicDatas = Lists.newArrayList();
		final EarthBasicData earthBasicDataInDb = this.earthBasicDataRepository.findByEarthLicenceId(queryCountryId);
		if (earthBasicDataInDb == null) {
			this.earthBasicDataRepository.save(earthBasicData);
			newEarthBasicDatas.add(earthBasicData.getEarthLicenseId());
		} else if (!earthBasicDataInDb.equals(earthBasicData)) {
			earthBasicDataInDb.fromObj(earthBasicData);
			this.earthBasicDataRepository.save(earthBasicDataInDb);
			updatedEarthBasicDatas.add(earthBasicData.getEarthLicenseId());
		} else {
			sameEarthBasicDatas.add(earthBasicData.getEarthLicenseId());
		}

		re.put("new", newEarthBasicDatas);
		re.put("updated", updatedEarthBasicDatas);
		re.put("same", sameEarthBasicDatas);

		return re;
	}
}