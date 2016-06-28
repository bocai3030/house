package org.house.service.internal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.house.db.entity.jpa.EarthBasicData;
import org.house.db.entity.jpa.PreSellLicenseDataJpa;
import org.house.db.entity.jpa.ProjectBasicDataJpa;
import org.house.db.repository.jpa.EarthBasicDataRepository;
import org.house.db.repository.jpa.PreSellLicenseDataJpaRepository;
import org.house.db.repository.jpa.ProjectBasicDataJpaRepository;
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
@RequestMapping("/api/internal/simpleQuery")
public class SimpleQueryController {
	@Autowired
	private ProjectBasicDataJpaRepository projectBasicDataJpaRepository;
	@Autowired
	private PreSellLicenseDataJpaRepository preSellLicenseDataJpaRepository;
	@Autowired
	private EarthBasicDataRepository earthBasicDataRepository;

	private Object getFullProjectData(final List<ProjectBasicDataJpa> projectBasicDataJpas) {
		final List<Map<String, Object>> reList = Lists.newArrayList();

		for (final ProjectBasicDataJpa projectBasicDataJpa : projectBasicDataJpas) {
			if (projectBasicDataJpa == null) {
				continue;
			}
			final PreSellLicenseDataJpa preSellLicenseDataJpa = this.preSellLicenseDataJpaRepository
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
			re.put("preSellLicenseData", preSellLicenseDataJpa);
			re.put("earthBasicDatas", earthBasicDatas);

			reList.add(re);
		}

		return reList;
	}

	@RequestMapping(value = "/getProjectDataByProjectId", produces = "application/json")
	public Object getProjectDataByProjectId(@RequestParam(required = true) final String projectId) {
		final List<ProjectBasicDataJpa> projectBasicDataJpas = Lists.newArrayList();
		projectBasicDataJpas.add(this.projectBasicDataJpaRepository.findByProjectId(projectId));
		return this.getFullProjectData(projectBasicDataJpas);
	}

	@RequestMapping(value = "/getProjectDataByProjectNameLike", produces = "application/json")
	public Object getProjectDataByProjectNameLike(@RequestParam(required = true) final String projectNameLike) {
		final List<ProjectBasicDataJpa> projectBasicDataJpas = Lists.newArrayList();
		projectBasicDataJpas.addAll(this.projectBasicDataJpaRepository.findByProjectNameLike("%" + projectNameLike + "%"));
		return this.getFullProjectData(projectBasicDataJpas);
	}

	@RequestMapping(value = "/getProjectDataByPreSellLicenseId", produces = "application/json")
	public Object getProjectDataByPreSellLicenseId(@RequestParam(required = true) final String preSellLicenseId) {
		final List<ProjectBasicDataJpa> projectBasicDataJpas = Lists.newArrayList();
		projectBasicDataJpas.add(this.projectBasicDataJpaRepository.findByPreSellLicenseId(preSellLicenseId));
		return this.getFullProjectData(projectBasicDataJpas);
	}

	@RequestMapping(value = "/getProjectDataByProjectAddressLike", produces = "application/json")
	public Object getProjectDataByProjectAddressLike(@RequestParam(required = true) final String projectAddressLike) {
		final List<ProjectBasicDataJpa> projectBasicDataJpas = Lists.newArrayList();
		projectBasicDataJpas.addAll(this.projectBasicDataJpaRepository.findByProjectAddressLike("%" + projectAddressLike + "%"));
		return this.getFullProjectData(projectBasicDataJpas);
	}

	@RequestMapping(value = "/getProjectDataByDeveloperLike", produces = "application/json")
	public Object getProjectDataByDeveloperLike(@RequestParam(required = true) final String developerLike) {
		final List<ProjectBasicDataJpa> projectBasicDataJpas = Lists.newArrayList();
		projectBasicDataJpas.addAll(this.projectBasicDataJpaRepository.findByDeveloperLike("%" + developerLike + "%"));
		return this.getFullProjectData(projectBasicDataJpas);
	}

	@RequestMapping(value = "/getProjectDataByDivision", produces = "application/json")
	public Object getProjectDataByDivision(@RequestParam(required = true, defaultValue = "番禺区") final String division) {
		final List<ProjectBasicDataJpa> projectBasicDataJpas = Lists.newArrayList();
		projectBasicDataJpas.addAll(this.projectBasicDataJpaRepository.findByDivision(division));
		return this.getFullProjectData(projectBasicDataJpas);
	}

	@RequestMapping(value = "/getProjectDataByEarthBorrowFromBetween", produces = "application/json")
	public Object getProjectDataByEarthBorrowFromBetween(@DateTimeFormat(pattern = "yyyy-MM-dd") Date borrowFrom,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date borrowTo) {
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

		final List<EarthBasicData> earthBasicDatas = this.earthBasicDataRepository.findByBorrowFromBetween(borrowFrom, borrowTo);
		final Set<String> set = Sets.newHashSet();
		for (final EarthBasicData earthBasicData : earthBasicDatas) {
			if (!set.contains(earthBasicData.getProjectId())) {
				final ProjectBasicDataJpa projectBasicDataJpa = this.projectBasicDataJpaRepository.findByProjectId(earthBasicData.getProjectId());
				if (projectBasicDataJpa != null) {
					projectBasicDataJpas.add(projectBasicDataJpa);
				}
				set.add(earthBasicData.getProjectId());
			}
		}

		return this.getFullProjectData(projectBasicDataJpas);
	}

}