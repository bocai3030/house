package org.house.service.internal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.house.db.entity.jpa.EarthBasicData;
import org.house.db.entity.jpa.PreSellLicenseData;
import org.house.db.entity.jpa.ProjectBasicDataJpa;
import org.house.db.entity.jpa.ProjectTag;
import org.house.db.repository.jpa.EarthBasicDataRepository;
import org.house.db.repository.jpa.PreSellLicenseDataRepository;
import org.house.db.repository.jpa.ProjectBasicDataJpaRepository;
import org.house.db.repository.jpa.ProjectTagRepository;
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
	private ProjectBasicDataJpaRepository projectBasicDataJpaRepository;
	@Autowired
	private PreSellLicenseDataRepository preSellLicenseDataRepository;
	@Autowired
	private EarthBasicDataRepository earthBasicDataRepository;
	@Autowired
	private ProjectTagRepository projectTagRepository;

	private Object getFullProjectData(final List<ProjectBasicDataJpa> projectBasicDataJpas) {
		final List<Map<String, Object>> reList = Lists.newArrayList();

		for (final ProjectBasicDataJpa projectBasicDataJpa : projectBasicDataJpas) {
			if (projectBasicDataJpa == null) {
				continue;
			}
			final PreSellLicenseData preSellLicenseData = this.preSellLicenseDataRepository
					.findByPreSellLicenseId(projectBasicDataJpa.getPreSellLicenseId());

			final List<EarthBasicData> earthBasicDatas = Lists.newArrayList();
			final String[] earthLicenseIds = projectBasicDataJpa.getCountryId().split(",");
			for (int i = 0; i < earthLicenseIds.length; i++) {
				if (!Strings.isNullOrEmpty(earthLicenseIds[i])) {
					final EarthBasicData earthBasicData = this.earthBasicDataRepository.findByEarthLicenseId(earthLicenseIds[i]);
					earthBasicDatas.add(earthBasicData);
				}
			}

			final Map<String, Object> re = Maps.newHashMap();
			re.put("projectBasicData", projectBasicDataJpa);
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

		final List<ProjectBasicDataJpa> projectBasicDataJpas = Lists.newArrayList();

		// 1st: by borrowFrom and division
		final List<EarthBasicData> earthBasicDatas = this.earthBasicDataRepository.findByBorrowFromBetween(borrowFrom, borrowTo);
		final Set<String> set = Sets.newHashSet();
		for (final EarthBasicData earthBasicData : earthBasicDatas) {
			if (!set.contains(earthBasicData.getProjectId())) {
				final ProjectBasicDataJpa projectBasicDataJpa = this.projectBasicDataJpaRepository.findByProjectId(earthBasicData.getProjectId());
				if (projectBasicDataJpa != null && projectBasicDataJpa.getDivision().equals(division)) {
					projectBasicDataJpas.add(projectBasicDataJpa);
				}
				set.add(earthBasicData.getProjectId());
			}
		}

		// 2nd: by focusStatus,<br>
		// =null:non about focusStatus,<br>
		// ='':focusStatus not marked,<br>
		// ='other str':focusStatus equals it
		if (focusStatus != null) {
			final List<ProjectBasicDataJpa> projectBasicDataJpasTmp = Lists.newArrayList(projectBasicDataJpas);
			projectBasicDataJpas.clear();
			for (final ProjectBasicDataJpa projectBasicDataJpa : projectBasicDataJpasTmp) {
				final ProjectTag projectTag = this.projectTagRepository.findByProjectId(projectBasicDataJpa.getProjectId());
				if (projectTag == null && focusStatus.length() == 0) {
					projectBasicDataJpas.add(projectBasicDataJpa);
				} else if (projectTag != null && focusStatus.length() >= 0 && focusStatus.equals(projectTag.getFocusStatus())) {
					projectBasicDataJpas.add(projectBasicDataJpa);
				}
			}
		}

		// 3rd: filter by onlyResidential
		if (onlyResidential) {
			final List<ProjectBasicDataJpa> projectBasicDataJpasTmp = Lists.newArrayList(projectBasicDataJpas);
			projectBasicDataJpas.clear();
			for (final ProjectBasicDataJpa projectBasicDataJpa : projectBasicDataJpasTmp) {
				final PreSellLicenseData preSellLicenseData = this.preSellLicenseDataRepository
						.findByPreSellLicenseId(projectBasicDataJpa.getPreSellLicenseId());
				if (preSellLicenseData == null /* loose restriction */ || preSellLicenseData.getDistributeOfResidentialCount() > 0) {
					projectBasicDataJpas.add(projectBasicDataJpa);
				}
			}
		}

		return this.getFullProjectData(projectBasicDataJpas);
	}

	@RequestMapping(value = "/getAllFocusStatuses", produces = "application/json")
	public Object getAllFocusStatuses() {
		return this.projectTagRepository.findDistinctFocusStatus();
	}
}