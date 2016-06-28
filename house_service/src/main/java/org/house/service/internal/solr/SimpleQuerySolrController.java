package org.house.service.internal.solr;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.house.db.entity.jpa.EarthBasicDataJpa;
import org.house.db.entity.solr.PreSellLicenseDataSolr;
import org.house.db.entity.solr.ProjectBasicDataSolr;
import org.house.db.repository.jpa.EarthBasicDataJpaRepository;
import org.house.db.repository.solr.PreSellLicenseDataSolrRepository;
import org.house.db.repository.solr.ProjectBasicDataSolrRepository;
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
@RequestMapping("/api/internal/solr/simpleQuery")
public class SimpleQuerySolrController {
	@Autowired
	private ProjectBasicDataSolrRepository projectBasicDataSolrRepository;
	@Autowired
	private PreSellLicenseDataSolrRepository preSellLicenseDataSolrRepository;
	@Autowired
	private EarthBasicDataJpaRepository earthBasicDataJpaRepository;

	private Object getFullProjectData(final List<ProjectBasicDataSolr> projectBasicDataSolrs) {
		final List<Map<String, Object>> reList = Lists.newArrayList();

		for (final ProjectBasicDataSolr projectBasicDataSolr : projectBasicDataSolrs) {
			if (projectBasicDataSolr == null) {
				continue;
			}
			final PreSellLicenseDataSolr preSellLicenseDataSolr = this.preSellLicenseDataSolrRepository
					.findByPreSellLicenseId(projectBasicDataSolr.getPreSellLicenseId());

			final List<EarthBasicDataJpa> earthBasicDataJpas = Lists.newArrayList();
			final String[] earthLicenseIds = projectBasicDataSolr.getCountryId().split(",");
			for (int i = 0; i < earthLicenseIds.length; i++) {
				if (!Strings.isNullOrEmpty(earthLicenseIds[i])) {
					final EarthBasicDataJpa earthBasicDataJpa = this.earthBasicDataJpaRepository.findByEarthLicenseId(earthLicenseIds[i]);
					earthBasicDataJpas.add(earthBasicDataJpa);
				}
			}

			final Map<String, Object> re = Maps.newHashMap();
			re.put("projectBasicData", projectBasicDataSolr);
			re.put("preSellLicenseData", preSellLicenseDataSolr);
			re.put("earthBasicDatas", earthBasicDataJpas);

			reList.add(re);
		}

		return reList;
	}

	@RequestMapping(value = "/getProjectDataByProjectId", produces = "application/json")
	public Object getProjectDataByProjectId(@RequestParam(required = true) final String projectId) {
		final List<ProjectBasicDataSolr> projectBasicDataSolrs = Lists.newArrayList();
		projectBasicDataSolrs.add(this.projectBasicDataSolrRepository.findByProjectId(projectId));
		return this.getFullProjectData(projectBasicDataSolrs);
	}

	@RequestMapping(value = "/getProjectDataByProjectNameLike", produces = "application/json")
	public Object getProjectDataByProjectNameLike(@RequestParam(required = true) final String projectNameLike) {
		final List<ProjectBasicDataSolr> projectBasicDataSolrs = Lists.newArrayList();
		projectBasicDataSolrs.addAll(this.projectBasicDataSolrRepository.findByProjectNameContaining(projectNameLike));
		return this.getFullProjectData(projectBasicDataSolrs);
	}

	@RequestMapping(value = "/getProjectDataByPreSellLicenseId", produces = "application/json")
	public Object getProjectDataByPreSellLicenseId(@RequestParam(required = true) final String preSellLicenseId) {
		final List<ProjectBasicDataSolr> projectBasicDataSolrs = Lists.newArrayList();
		projectBasicDataSolrs.add(this.projectBasicDataSolrRepository.findByPreSellLicenseId(preSellLicenseId));
		return this.getFullProjectData(projectBasicDataSolrs);
	}

	@RequestMapping(value = "/getProjectDataByProjectAddressLike", produces = "application/json")
	public Object getProjectDataByProjectAddressLike(@RequestParam(required = true) final String projectAddressLike) {
		final List<ProjectBasicDataSolr> projectBasicDataSolrs = Lists.newArrayList();
		projectBasicDataSolrs.addAll(this.projectBasicDataSolrRepository.findByProjectAddressContaining(projectAddressLike));
		return this.getFullProjectData(projectBasicDataSolrs);
	}

	@RequestMapping(value = "/getProjectDataByDeveloperLike", produces = "application/json")
	public Object getProjectDataByDeveloperLike(@RequestParam(required = true) final String developerLike) {
		final List<ProjectBasicDataSolr> projectBasicDataSolrs = Lists.newArrayList();
		projectBasicDataSolrs.addAll(this.projectBasicDataSolrRepository.findByDeveloperContaining(developerLike));
		return this.getFullProjectData(projectBasicDataSolrs);
	}

	@RequestMapping(value = "/getProjectDataByDivision", produces = "application/json")
	public Object getProjectDataByDivision(@RequestParam(required = true, defaultValue = "番禺区") final String division) {
		final List<ProjectBasicDataSolr> projectBasicDataSolrs = Lists.newArrayList();
		projectBasicDataSolrs.addAll(this.projectBasicDataSolrRepository.findByDivision(division));
		return this.getFullProjectData(projectBasicDataSolrs);
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

		final List<ProjectBasicDataSolr> projectBasicDataSolrs = Lists.newArrayList();

		final List<EarthBasicDataJpa> earthBasicDataJpas = this.earthBasicDataJpaRepository.findByBorrowFromBetween(borrowFrom, borrowTo);
		final Set<String> set = Sets.newHashSet();
		for (final EarthBasicDataJpa earthBasicDataJpa : earthBasicDataJpas) {
			if (!set.contains(earthBasicDataJpa.getProjectId())) {
				final ProjectBasicDataSolr projectBasicDataSolr = this.projectBasicDataSolrRepository
						.findByProjectId(earthBasicDataJpa.getProjectId());
				if (projectBasicDataSolr != null) {
					projectBasicDataSolrs.add(projectBasicDataSolr);
				}
				set.add(earthBasicDataJpa.getProjectId());
			}
		}

		return this.getFullProjectData(projectBasicDataSolrs);
	}

}