package org.house.service.internal.solr;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.house.db.entity.solr.EarthBasicDataSolr;
import org.house.db.entity.solr.PreSellLicenseDataSolr;
import org.house.db.entity.solr.ProjectBasicDataSolr;
import org.house.db.entity.solr.ProjectTagSolr;
import org.house.db.repository.solr.EarthBasicDataSolrRepository;
import org.house.db.repository.solr.PreSellLicenseDataSolrRepository;
import org.house.db.repository.solr.ProjectBasicDataSolrRepository;
import org.house.db.repository.solr.ProjectTagSolrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.data.solr.core.query.result.FacetEntry;
import org.springframework.data.solr.core.query.result.FacetPage;
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
@RequestMapping("/api/internal/solr/complicateQuery")
public class ComplicateQuerySolrController {
	@Autowired
	private ProjectBasicDataSolrRepository projectBasicDataSolrRepository;
	@Autowired
	private PreSellLicenseDataSolrRepository preSellLicenseDataSolrRepository;
	@Autowired
	private EarthBasicDataSolrRepository earthBasicDataSolrRepository;
	@Autowired
	private ProjectTagSolrRepository projectTagSolrRepository;

	private Object getFullProjectData(final List<ProjectBasicDataSolr> projectBasicDataSolrs) {
		final List<Map<String, Object>> reList = Lists.newArrayList();

		for (final ProjectBasicDataSolr projectBasicDataSolr : projectBasicDataSolrs) {
			if (projectBasicDataSolr == null) {
				continue;
			}
			final PreSellLicenseDataSolr preSellLicenseDataSolr = this.preSellLicenseDataSolrRepository
					.findByPreSellLicenseId(projectBasicDataSolr.getPreSellLicenseId());

			final List<EarthBasicDataSolr> earthBasicDataSolrs = Lists.newArrayList();
			final String[] earthLicenseIds = projectBasicDataSolr.getCountryId().split(",");
			for (int i = 0; i < earthLicenseIds.length; i++) {
				if (!Strings.isNullOrEmpty(earthLicenseIds[i])) {
					final EarthBasicDataSolr earthBasicDataSolr = this.earthBasicDataSolrRepository.findByEarthLicenseId(earthLicenseIds[i]);
					earthBasicDataSolrs.add(earthBasicDataSolr);
				}
			}

			final Map<String, Object> re = Maps.newHashMap();
			re.put("projectBasicData", projectBasicDataSolr);
			re.put("preSellLicenseData", preSellLicenseDataSolr);
			re.put("earthBasicDatas", earthBasicDataSolrs);

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

		final List<ProjectBasicDataSolr> projectBasicDataSolrs = Lists.newArrayList();

		// 1st: by borrowFrom and division
		final List<EarthBasicDataSolr> earthBasicDataSolrs = this.earthBasicDataSolrRepository.findByBorrowFromBetween(borrowFrom, borrowTo);
		final Set<String> set = Sets.newHashSet();
		for (final EarthBasicDataSolr earthBasicDataSolr : earthBasicDataSolrs) {
			if (!set.contains(earthBasicDataSolr.getProjectId())) {
				final ProjectBasicDataSolr projectBasicDataSolr = this.projectBasicDataSolrRepository
						.findByProjectId(earthBasicDataSolr.getProjectId());
				if (projectBasicDataSolr != null && projectBasicDataSolr.getDivision().equals(division)) {
					projectBasicDataSolrs.add(projectBasicDataSolr);
				}
				set.add(earthBasicDataSolr.getProjectId());
			}
		}
		// 2nd: by focusStatus,<br>
		// =null:non about focusStatus,<br>
		// ='':focusStatus not marked,<br>
		// ='other str':focusStatus equals it
		if (focusStatus != null) {
			final List<ProjectBasicDataSolr> projectBasicDataSolrsTmp = Lists.newArrayList(projectBasicDataSolrs);
			projectBasicDataSolrs.clear();
			for (final ProjectBasicDataSolr projectBasicDataSolr : projectBasicDataSolrsTmp) {
				final ProjectTagSolr projectTagSolr = this.projectTagSolrRepository.findByProjectId(projectBasicDataSolr.getProjectId());
				if (projectTagSolr == null && focusStatus.length() == 0) {
					projectBasicDataSolrs.add(projectBasicDataSolr);
				} else if (projectTagSolr != null && focusStatus.length() >= 0 && focusStatus.equals(projectTagSolr.getFocusStatus())) {
					projectBasicDataSolrs.add(projectBasicDataSolr);
				}
			}
		}
		// 3rd: filter by onlyResidential
		if (onlyResidential) {
			final List<ProjectBasicDataSolr> projectBasicDataSolrsTmp = Lists.newArrayList(projectBasicDataSolrs);
			projectBasicDataSolrs.clear();
			for (final ProjectBasicDataSolr projectBasicDataSolr : projectBasicDataSolrsTmp) {
				final PreSellLicenseDataSolr preSellLicenseDataSolr = this.preSellLicenseDataSolrRepository
						.findByPreSellLicenseId(projectBasicDataSolr.getPreSellLicenseId());
				if (preSellLicenseDataSolr == null /* loose restriction */ || preSellLicenseDataSolr.getDistributeOfResidentialCount() > 0) {
					projectBasicDataSolrs.add(projectBasicDataSolr);
				}
			}
		}
		return this.getFullProjectData(projectBasicDataSolrs);
	}

	@RequestMapping(value = "/getAllFocusStatuses", produces = "application/json")
	public List<String> getAllFocusStatuses() {
		FacetPage<ProjectTagSolr> facetPage = this.projectTagSolrRepository.findDistinctFocusStatus(new SolrPageRequest(0, 200));
		List<String> allFocusStatuses = Lists.newArrayList();
		for (Page<? extends FacetEntry> pageEntry : facetPage.getAllFacets()) {
			if (pageEntry.getNumberOfElements() == 0) {
				continue;
			}
			for (FacetEntry facetEntry : pageEntry.getContent()) {
				allFocusStatuses.add(facetEntry.getValue());
			}
		}
		return allFocusStatuses;
	}
}