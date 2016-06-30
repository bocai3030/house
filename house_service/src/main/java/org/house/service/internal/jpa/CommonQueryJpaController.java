package org.house.service.internal.jpa;

import java.util.List;

import javax.validation.Valid;

import org.house.bean.response.ProjectHasTagJpa;
import org.house.db.entity.jpa.ProjectBasicDataJpa;
import org.house.db.entity.jpa.ProjectTagJpa;
import org.house.db.repository.jpa.ProjectBasicDataJpaRepository;
import org.house.db.repository.jpa.ProjectTagJpaRepository;
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
@RequestMapping("/api/internal/jpa/commonQuery")
public class CommonQueryJpaController {
	@Autowired
	private ProjectBasicDataJpaRepository projectBasicDataJpaRepository;
	@Autowired
	private ProjectTagJpaRepository projectTagJpaRepository;

	@RequestMapping(value = "/getProjectTagByProjectId", produces = "application/json")
	public Object getProjectTagByProjectId(@RequestParam(required = true) final String projectId) {
		return this.projectTagJpaRepository.findByProjectId(projectId);
	}

	@RequestMapping(value = "/updateFocusStatusByProjectId", method = RequestMethod.POST, produces = "application/json")
	public Object updateFocusStatusByProjectId(@RequestParam(required = true) final String projectId,
			@RequestParam(required = true) final String focusStatus) {
		ProjectTagJpa projectTagJpa = this.projectTagJpaRepository.findByProjectId(projectId);
		if (projectTagJpa == null) {
			projectTagJpa = new ProjectTagJpa();
			projectTagJpa.setProjectId(projectId);
			projectTagJpa.setFocusStatus(focusStatus);
			projectTagJpa.setRemark("");
		} else if (!projectTagJpa.getFocusStatus().equals(focusStatus)) {
			projectTagJpa.setFocusStatus(focusStatus);
		}
		this.projectTagJpaRepository.save(projectTagJpa);
		return ImmutableMap.<String, String>of("status", "ok");
	}

	@RequestMapping(value = "/getProjectHasTag", produces = "application/json")
	public Object getProjectHasTag(final String projectId) {
		final ProjectTagJpa projectTagJpa = this.projectTagJpaRepository.findByProjectId(projectId);
		if (projectTagJpa == null) {
			return null;
		}

		final ProjectBasicDataJpa projectBasicDataJpa = this.projectBasicDataJpaRepository.findByProjectId(projectId);
		if (projectBasicDataJpa == null) {
			return null;
		}

		final ProjectHasTagJpa projectHasTagJpa = new ProjectHasTagJpa();
		projectHasTagJpa.setProjectId(projectBasicDataJpa.getProjectId());
		projectHasTagJpa.setProjectName(projectBasicDataJpa.getProjectName());
		projectHasTagJpa.setProjectAddress(projectBasicDataJpa.getProjectAddress());
		projectHasTagJpa.setProjectTag(projectTagJpa);
		return projectHasTagJpa;
	}

	@RequestMapping(value = "/getProjectHasTags", produces = "application/json")
	public Object getProjectHasTags(final String focusStatus) {
		List<ProjectTagJpa> projectTagJpas = null;
		if (Strings.isNullOrEmpty(focusStatus)) {
			projectTagJpas = this.projectTagJpaRepository.findAll();
		} else {
			projectTagJpas = this.projectTagJpaRepository.findByFocusStatusLike("%" + focusStatus + "%");
		}

		final List<ProjectHasTagJpa> re = Lists.newArrayListWithExpectedSize(projectTagJpas.size());
		for (final ProjectTagJpa projectTagJpa : projectTagJpas) {
			final ProjectBasicDataJpa projectBasicDataJpa = this.projectBasicDataJpaRepository.findByProjectId(projectTagJpa.getProjectId());
			if (projectBasicDataJpa == null) {
				continue;
			}

			final ProjectHasTagJpa projectHasTagJpa = new ProjectHasTagJpa();
			projectHasTagJpa.setProjectId(projectBasicDataJpa.getProjectId());
			projectHasTagJpa.setProjectName(projectBasicDataJpa.getProjectName());
			projectHasTagJpa.setProjectAddress(projectBasicDataJpa.getProjectAddress());
			projectHasTagJpa.setProjectTag(projectTagJpa);

			re.add(projectHasTagJpa);
		}

		return re;
	}

	@RequestMapping(value = "/updateProjectTagByProjectId", method = RequestMethod.POST, produces = "application/json")
	public Object updateProjectTagByProjectId(@RequestBody @Valid final ProjectTagJpa newProjectTagJpa) {
		final String projectId = newProjectTagJpa.getProjectId();
		final String focusStatus = newProjectTagJpa.getFocusStatus();
		final String remark = newProjectTagJpa.getRemark();

		ProjectTagJpa projectTagJpa = this.projectTagJpaRepository.findByProjectId(projectId);
		if (projectTagJpa == null) {
			if (focusStatus != null || remark != null) {
				projectTagJpa = new ProjectTagJpa();
				projectTagJpa.setProjectId(projectId);
				projectTagJpa.setFocusStatus(focusStatus == null ? "" : focusStatus);
				projectTagJpa.setRemark(remark == null ? "" : remark);
				this.projectTagJpaRepository.save(projectTagJpa);
				return ImmutableMap.<String, String>of("status", "saved");
			} else {
				return ImmutableMap.<String, String>of("status", "not changed");
			}
		}

		if (focusStatus == null && remark == null) {
			return ImmutableMap.<String, String>of("status", "not changed");
		}

		boolean needSave = false;
		if (focusStatus != null && !projectTagJpa.getFocusStatus().equals(focusStatus)) {
			projectTagJpa.setFocusStatus(focusStatus);
			needSave = true;
		}
		if (remark != null && !projectTagJpa.getRemark().equals(remark)) {
			projectTagJpa.setRemark(remark);
			needSave = true;
		}
		if (needSave) {
			this.projectTagJpaRepository.save(projectTagJpa);
			return ImmutableMap.<String, String>of("status", "saved");
		} else {
			return ImmutableMap.<String, String>of("status", "not changed");
		}
	}

}