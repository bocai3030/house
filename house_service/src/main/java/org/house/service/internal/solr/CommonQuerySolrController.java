package org.house.service.internal.solr;

import java.util.List;

import org.house.bean.response.ProjectHasTagSolr;
import org.house.db.entity.solr.ProjectBasicDataSolr;
import org.house.db.entity.solr.ProjectTagSolr;
import org.house.db.repository.solr.ProjectBasicDataSolrRepository;
import org.house.db.repository.solr.ProjectTagSolrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

@CrossOrigin
@RestController
@RequestMapping("/api/internal/solr/commonQuery")
public class CommonQuerySolrController {
	@Autowired
	private ProjectBasicDataSolrRepository projectBasicDataSolrRepository;
	@Autowired
	private ProjectTagSolrRepository projectTagSolrRepository;

	@RequestMapping(value = "/getProjectTagByProjectId", produces = "application/json")
	public Object getProjectTagByProjectId(@RequestParam(required = true) final String projectId) {
		return this.projectTagSolrRepository.findByProjectId(projectId);
	}

	@RequestMapping(value = "/getProjectHasTag", produces = "application/json")
	public Object getProjectHasTag(final String projectId) {
		final ProjectTagSolr projectTagSolr = this.projectTagSolrRepository.findByProjectId(projectId);
		if (projectTagSolr == null) {
			return null;
		}

		final ProjectBasicDataSolr projectBasicDataSolr = this.projectBasicDataSolrRepository.findByProjectId(projectId);
		if (projectBasicDataSolr == null) {
			return null;
		}

		final ProjectHasTagSolr projectHasTagSolr = new ProjectHasTagSolr();
		projectHasTagSolr.setProjectId(projectBasicDataSolr.getProjectId());
		projectHasTagSolr.setProjectName(projectBasicDataSolr.getProjectName());
		projectHasTagSolr.setProjectAddress(projectBasicDataSolr.getProjectAddress());
		projectHasTagSolr.setProjectTag(projectTagSolr);
		return projectHasTagSolr;
	}

	@RequestMapping(value = "/getProjectHasTags", produces = "application/json")
	public Object getProjectHasTags(final String focusStatus) {
		Iterable<ProjectTagSolr> projectTagSolrs = null;
		if (Strings.isNullOrEmpty(focusStatus)) {
			projectTagSolrs = this.projectTagSolrRepository.findAll();
		} else {
			projectTagSolrs = this.projectTagSolrRepository.findByFocusStatusLike(focusStatus);
		}

		final List<ProjectHasTagSolr> re = Lists.newArrayList();
		for (final ProjectTagSolr projectTagSolr : projectTagSolrs) {
			final ProjectBasicDataSolr projectBasicDataSolr = this.projectBasicDataSolrRepository.findByProjectId(projectTagSolr.getProjectId());
			if (projectBasicDataSolr == null) {
				continue;
			}

			final ProjectHasTagSolr projectHasTagSolr = new ProjectHasTagSolr();
			projectHasTagSolr.setProjectId(projectBasicDataSolr.getProjectId());
			projectHasTagSolr.setProjectName(projectBasicDataSolr.getProjectName());
			projectHasTagSolr.setProjectAddress(projectBasicDataSolr.getProjectAddress());
			projectHasTagSolr.setProjectTag(projectTagSolr);

			re.add(projectHasTagSolr);
		}

		return re;
	}

}