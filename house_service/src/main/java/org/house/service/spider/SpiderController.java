package org.house.service.spider;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.house.db.entity.EarthBasicData;
import org.house.db.entity.PreSellLicenceData;
import org.house.db.entity.ProjectBasicData;
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

	@RequestMapping(value = "/getPreSellLicenceData", produces = "application/json")
	Object getPreSellLicenceData(@RequestParam("projectId") final String projectId,
			@RequestParam("licenceId") final String licenceId) throws ClientProtocolException, IOException {
		final PreSellLicenceData preSellLicenceData = WebSpider.getPreSellLicenceData(projectId, licenceId);

		return preSellLicenceData;
	}

	@RequestMapping(value = "/getEarthBasicData", produces = "application/json")
	Object getEarthBasicData(@RequestParam("projectId") final String projectId,
			@RequestParam("licenceId") final String licenceId) throws ClientProtocolException, IOException {
		final EarthBasicData preSellLicenceData = WebSpider.getEarthBasicData(projectId, licenceId);

		return preSellLicenceData;
	}
}