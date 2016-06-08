package org.house.service.internal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.house.db.entity.EarthBasicData;
import org.house.db.entity.PreSellLicenseData;
import org.house.db.entity.ProjectBasicData;
import org.house.db.entity.ProjectTag;
import org.house.db.repository.EarthBasicDataRepository;
import org.house.db.repository.PreSellLicenseDataRepository;
import org.house.db.repository.ProjectBasicDataRepository;
import org.house.db.repository.ProjectTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@CrossOrigin
@RestController
@RequestMapping("/api/internal/complicateQuery")
public class ComplicateQueryController {
	@Autowired
	private ProjectBasicDataRepository projectBasicDataRepository;
	@Autowired
	private PreSellLicenseDataRepository preSellLicenseDataRepository;
	@Autowired
	private EarthBasicDataRepository earthBasicDataRepository;
	@Autowired
	private ProjectTagRepository projectTagRepository;

	private Object getFullProjectData(final List<ProjectBasicData> projectBasicDatas) {
		final List<Map<String, Object>> reList = Lists.newArrayList();

		for (final ProjectBasicData projectBasicData : projectBasicDatas) {
			if (projectBasicData == null) {
				continue;
			}
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

	@RequestMapping(value = "/getProjectData", produces = "application/json")
	public Object getProjectData(@RequestParam(required = true, defaultValue = "番禺区") final String division,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date borrowFrom, @DateTimeFormat(pattern = "yyyy-MM-dd") Date borrowTo, final String focusStatus,
			@RequestParam(defaultValue = "false") final Boolean onlyResidential) {
		// TODO find time to learn spring jpa multitable query

		try {
			if (borrowFrom == null) {
				borrowFrom = new SimpleDateFormat("yyyy-MM-dd").parse("1980-01-01");
			}
			if (borrowTo == null) {
				borrowTo = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01");
			}
		} catch (final Exception e) {
		}

		final List<ProjectBasicData> projectBasicDatas = Lists.newArrayList();

		// 1st: by borrowFrom and division
		final List<EarthBasicData> earthBasicDatas = this.earthBasicDataRepository.findByBorrowFromBetween(borrowFrom, borrowTo);
		final Set<String> set = Sets.newHashSet();
		for (final EarthBasicData earthBasicData : earthBasicDatas) {
			if (!set.contains(earthBasicData.getProjectId())) {
				final ProjectBasicData projectBasicData = this.projectBasicDataRepository.findByProjectId(earthBasicData.getProjectId());
				if (projectBasicData != null && projectBasicData.getDivision().equals(division)) {
					projectBasicDatas.add(projectBasicData);
				}
				set.add(earthBasicData.getProjectId());
			}
		}

		// 2nd: by focusStatus,<br>
		// =null:non about focusStatus,<br>
		// ='':focusStatus not marked,<br>
		// ='other str':focusStatus equals it
		if (focusStatus != null) {
			final List<ProjectBasicData> projectBasicDatasTmp = Lists.newArrayList(projectBasicDatas);
			projectBasicDatas.clear();
			for (final ProjectBasicData projectBasicData : projectBasicDatasTmp) {
				final ProjectTag projectTag = this.projectTagRepository.findByProjectId(projectBasicData.getProjectId());
				if (projectTag == null && focusStatus.length() == 0) {
					projectBasicDatas.add(projectBasicData);
				} else if (projectTag != null && focusStatus.length() >= 0 && focusStatus.equals(projectTag.getFocusStatus())) {
					projectBasicDatas.add(projectBasicData);
				}
			}
		}

		// 3rd: filter by onlyResidential
		if (onlyResidential) {
			final List<ProjectBasicData> projectBasicDatasTmp = Lists.newArrayList(projectBasicDatas);
			projectBasicDatas.clear();
			for (final ProjectBasicData projectBasicData : projectBasicDatasTmp) {
				final PreSellLicenseData preSellLicenseData = this.preSellLicenseDataRepository
						.findByPreSellLicenseId(projectBasicData.getPreSellLicenseId());
				if (preSellLicenseData == null /* loose restriction */ || preSellLicenseData.getDistributeOfResidentialCount() > 0) {
					projectBasicDatas.add(projectBasicData);
				}
			}
		}

		return this.getFullProjectData(projectBasicDatas);
	}

	@RequestMapping(value = "/getAllFocusStatuses", produces = "application/json")
	public Object getAllFocusStatuses() {
		return this.projectTagRepository.findDistinctFocusStatus();
	}
}