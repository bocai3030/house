package org.house.service.internal;

import java.util.List;
import java.util.Map;

import org.house.db.entity.EarthBasicData;
import org.house.db.entity.PreSellLicenseData;
import org.house.db.entity.ProjectBasicData;
import org.house.db.repository.EarthBasicDataRepository;
import org.house.db.repository.PreSellLicenseDataRepository;
import org.house.db.repository.ProjectBasicDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@RestController
@RequestMapping("/internal")
public class QueryController {
	@Autowired
	private ProjectBasicDataRepository projectBasicDataRepository;
	@Autowired
	private PreSellLicenseDataRepository preSellLicenseDataRepository;
	@Autowired
	private EarthBasicDataRepository earthBasicDataRepository;

	private Object getData(final String projectId, final String division) {
		final List<ProjectBasicData> projectBasicDatas = Lists.newArrayList();
		if (projectId != null) {
			projectBasicDatas.add(this.projectBasicDataRepository.findByProjectId(projectId));
		} else {
			projectBasicDatas.addAll(this.projectBasicDataRepository.findByDivision(division));
		}

		final List<Map<String, Object>> reList = Lists.newArrayList();

		for (final ProjectBasicData projectBasicData : projectBasicDatas) {
			final PreSellLicenseData preSellLicenseData = this.preSellLicenseDataRepository
					.findByPreSellLicenseId(projectBasicData.getPreSellLicenseId());

			final List<EarthBasicData> earthBasicDatas = Lists.newArrayList();
			final String[] earthLicenseIds = projectBasicData.getCountryId().split(",");
			for (int i = 0; i < earthLicenseIds.length; i++) {
				if (!Strings.isNullOrEmpty(earthLicenseIds[i])) {
					final EarthBasicData earthBasicData = this.earthBasicDataRepository.findByEarthLicenseId(earthLicenseIds[i]);
					earthBasicDatas.add(earthBasicData);
				}
			}

			final Map<String, Object> re = Maps.newHashMap();
			re.put("projectBasicData", projectBasicData);
			re.put("preSellLicenseData", preSellLicenseData);
			re.put("earthBasicDatas", earthBasicDatas);

			reList.add(re);
		}

		return reList;
	}

	@RequestMapping(value = "/getWholeProjectData", produces = "application/json")
	public Object getWholeProjectData(final String projectId) {
		return this.getData(projectId, null);
	}

	@RequestMapping(value = "/getProjectData", produces = "application/json")
	public Object getProjectData(final String division) {
		return this.getData(null, division);
	}
}