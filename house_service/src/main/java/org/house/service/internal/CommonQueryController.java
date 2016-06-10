package org.house.service.internal;

import java.util.List;

import javax.validation.Valid;

import org.house.bean.response.ProjectHasTag;
import org.house.db.entity.ProjectBasicData;
import org.house.db.entity.ProjectTag;
import org.house.db.repository.ProjectBasicDataRepository;
import org.house.db.repository.ProjectTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

@CrossOrigin
@RestController
@RequestMapping("/api/internal/commonQuery")
public class CommonQueryController {
	@Autowired
	private ProjectBasicDataRepository projectBasicDataRepository;
	@Autowired
	private ProjectTagRepository projectTagRepository;

	@RequestMapping(value = "/getProjectTagByProjectId", produces = "application/json")
	public Object getProjectTagByProjectId(@RequestParam(required = true) final String projectId) {
		return this.projectTagRepository.findByProjectId(projectId);
	}

	@RequestMapping(value = "/updateFocusStatusByProjectId", method = RequestMethod.POST, produces = "application/json")
	public Object updateFocusStatusByProjectId(@RequestParam(required = true) final String projectId,
			@RequestParam(required = true) final String focusStatus) {
		ProjectTag projectTag = this.projectTagRepository.findByProjectId(projectId);
		if (projectTag == null) {
			projectTag = new ProjectTag();
			projectTag.setProjectId(projectId);
			projectTag.setFocusStatus(focusStatus);
			projectTag.setRemark("");
		} else if (!projectTag.getFocusStatus().equals(focusStatus)) {
			projectTag.setFocusStatus(focusStatus);
		}
		this.projectTagRepository.save(projectTag);
		return ImmutableMap.<String, String>of("status", "ok");
	}

	@RequestMapping(value = "/getProjectHasTag", produces = "application/json")
	public Object getProjectHasTag(final String projectId) {
		final ProjectTag projectTag = this.projectTagRepository.findByProjectId(projectId);
		if (projectTag == null) {
			return null;
		}

		final ProjectBasicData projectBasicData = this.projectBasicDataRepository.findByProjectId(projectId);
		if (projectBasicData == null) {
			return null;
		}

		final ProjectHasTag projectHasTag = new ProjectHasTag();
		projectHasTag.setProjectId(projectBasicData.getProjectId());
		projectHasTag.setProjectName(projectBasicData.getProjectName());
		projectHasTag.setProjectAddress(projectBasicData.getProjectAddress());
		projectHasTag.setProjectTag(projectTag);
		return projectHasTag;
	}

	@RequestMapping(value = "/getProjectHasTags", produces = "application/json")
	public Object getProjectHasTags(final String focusStatus) {
		List<ProjectTag> projectTags = null;
		if (Strings.isNullOrEmpty(focusStatus)) {
			projectTags = this.projectTagRepository.findAll();
		} else {
			projectTags = this.projectTagRepository.findByFocusStatusLike("%" + focusStatus + "%");
		}

		final List<ProjectHasTag> re = Lists.newArrayListWithExpectedSize(projectTags.size());
		for (final ProjectTag projectTag : projectTags) {
			final ProjectBasicData projectBasicData = this.projectBasicDataRepository.findByProjectId(projectTag.getProjectId());
			if (projectBasicData == null) {
				continue;
			}

			final ProjectHasTag projectHasTag = new ProjectHasTag();
			projectHasTag.setProjectId(projectBasicData.getProjectId());
			projectHasTag.setProjectName(projectBasicData.getProjectName());
			projectHasTag.setProjectAddress(projectBasicData.getProjectAddress());
			projectHasTag.setProjectTag(projectTag);

			re.add(projectHasTag);
		}

		return re;
	}

	@RequestMapping(value = "/updateProjectTagByProjectId", method = RequestMethod.POST, produces = "application/json")
	public Object updateProjectTagByProjectId(@RequestBody @Valid final ProjectTag newProjectTag) {
		final String projectId = newProjectTag.getProjectId();
		final String focusStatus = newProjectTag.getFocusStatus();
		final String remark = newProjectTag.getRemark();

		ProjectTag projectTag = this.projectTagRepository.findByProjectId(projectId);
		if (projectTag == null) {
			if (focusStatus != null || remark != null) {
				projectTag = new ProjectTag();
				projectTag.setProjectId(projectId);
				projectTag.setFocusStatus(focusStatus == null ? "" : focusStatus);
				projectTag.setRemark(remark == null ? "" : remark);
				this.projectTagRepository.save(projectTag);
				return ImmutableMap.<String, String>of("status", "saved");
			} else {
				return ImmutableMap.<String, String>of("status", "not changed");
			}
		}

		if (focusStatus == null && remark == null) {
			return ImmutableMap.<String, String>of("status", "not changed");
		}

		boolean needSave = false;
		if (focusStatus != null && !projectTag.getFocusStatus().equals(focusStatus)) {
			projectTag.setFocusStatus(focusStatus);
			needSave = true;
		}
		if (remark != null && !projectTag.getRemark().equals(remark)) {
			projectTag.setRemark(remark);
			needSave = true;
		}
		if (needSave) {
			this.projectTagRepository.save(projectTag);
			return ImmutableMap.<String, String>of("status", "saved");
		} else {
			return ImmutableMap.<String, String>of("status", "not changed");
		}
	}

}