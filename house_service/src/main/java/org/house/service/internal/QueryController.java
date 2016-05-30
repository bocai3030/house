package org.house.service.internal;

import java.util.Date;
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

	private Object getFullProjectData(final List<ProjectBasicData> projectBasicDatas) {
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

	@RequestMapping(value = "/getProjectDataByProjectId", produces = "application/json")
	public Object getProjectDataByProjectId(final String projectId) {
		final List<ProjectBasicData> projectBasicDatas = Lists.newArrayList();
		projectBasicDatas.add(this.projectBasicDataRepository.findByProjectId(projectId));
		return this.getFullProjectData(projectBasicDatas);
	}

	@RequestMapping(value = "/getProjectDataByProjectNameLike", produces = "application/json")
	public Object getProjectDataByProjectNameLike(final String projectNameLike) {
		final List<ProjectBasicData> projectBasicDatas = Lists.newArrayList();
		projectBasicDatas.addAll(this.projectBasicDataRepository.findByProjectNameLike("%" + projectNameLike + "%"));
		return this.getFullProjectData(projectBasicDatas);
	}

	@RequestMapping(value = "/getProjectDataByPreSellLicenseId", produces = "application/json")
	public Object getProjectDataByPreSellLicenseId(final String preSellLicenseId) {
		final List<ProjectBasicData> projectBasicDatas = Lists.newArrayList();
		projectBasicDatas.add(this.projectBasicDataRepository.findByPreSellLicenseId(preSellLicenseId));
		return this.getFullProjectData(projectBasicDatas);
	}

	@RequestMapping(value = "/getProjectDataByProjectAddressLike", produces = "application/json")
	public Object getProjectDataByProjectAddressLike(final String projectAddressLike) {
		final List<ProjectBasicData> projectBasicDatas = Lists.newArrayList();
		projectBasicDatas.addAll(this.projectBasicDataRepository.findByProjectAddressLike("%" + projectAddressLike + "%"));
		return this.getFullProjectData(projectBasicDatas);
	}

	@RequestMapping(value = "/getProjectDataByDeveloperLike", produces = "application/json")
	public Object getProjectDataByDeveloperLike(final String developerLike) {
		final List<ProjectBasicData> projectBasicDatas = Lists.newArrayList();
		projectBasicDatas.addAll(this.projectBasicDataRepository.findByDeveloperLike("%" + developerLike + "%"));
		return this.getFullProjectData(projectBasicDatas);
	}

	@RequestMapping(value = "/getProjectDataByDivision", produces = "application/json")
	public Object getProjectDataByDivision(final String division) {
		final List<ProjectBasicData> projectBasicDatas = Lists.newArrayList();
		projectBasicDatas.addAll(this.projectBasicDataRepository.findByDivision(division));
		return this.getFullProjectData(projectBasicDatas);
	}

	@RequestMapping(value = "/getProjectDataByEarthBorrowFromBetween", produces = "application/json")
	public Object getProjectDataByEarthBorrowFromBetween(final Date borrowFrom, final Date borrowTo) {
		final List<ProjectBasicData> projectBasicDatas = Lists.newArrayList();
		// TODO
		return this.getFullProjectData(projectBasicDatas);
	}

}