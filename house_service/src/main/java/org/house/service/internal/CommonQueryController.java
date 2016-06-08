package org.house.service.internal;

import org.house.db.entity.ProjectTag;
import org.house.db.repository.ProjectTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;

@CrossOrigin
@RestController
@RequestMapping("/api/internal/commonQuery")
public class CommonQueryController {
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
}