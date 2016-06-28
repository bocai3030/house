package org.house.service.spider;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.house.db.entity.jpa.EarthBasicData;
import org.house.db.entity.jpa.PreSellLicenseData;
import org.house.db.entity.jpa.ProjectBasicDataJpa;
import org.house.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;

@RestController
@RequestMapping("/spider")
public class ShellSpiderController {
	@Autowired
	private SpiderController spiderController;

	@RequestMapping(value = "/updatePageData", produces = "text/html; charset=utf-8")
	public void updatePageData(final int page, final HttpServletResponse response) throws ClientProtocolException, IOException {
		response.setContentType("text/html; charset=utf-8");
		final List<ProjectBasicDataJpa> projectBasicDataJpas = this.spiderController.updateProjectBasicDataByPage(page);
		Utils.writlnAndFlushResponse(response, "updated page " + page);
		for (final ProjectBasicDataJpa projectBasicDataJpa : projectBasicDataJpas) {
			Utils.writlnAndFlushResponse(response,
					"updating projectId:" + projectBasicDataJpa.getProjectId() + ", projectName:" + projectBasicDataJpa.getProjectName());

			final PreSellLicenseData preSellLicenseData = this.spiderController.updatePreSellLicenseData(projectBasicDataJpa.getProjectId(),
					projectBasicDataJpa.getPreSellLicenseId());
			Utils.writlnAndFlushResponse(response, "updated preSellLicenseData, id:" + preSellLicenseData.getPreSellLicenseId());

			final String countryName = projectBasicDataJpa.getCountryName();
			final String countryId = projectBasicDataJpa.getCountryId();
			if (!Strings.isNullOrEmpty(countryId)) {
				final String[] countryIdAr = countryId.split(",");
				for (int i = 0; i < countryIdAr.length; i++) {
					final EarthBasicData earthBasicData = this.spiderController.updateEarthBasicData(projectBasicDataJpa.getProjectId(), countryName,
							countryId, countryIdAr[i], i);
					Utils.writlnAndFlushResponse(response, "updated earthBasicData, id:" + earthBasicData.getEarthLicenseId());
				}
			} else {
				Utils.writlnAndFlushResponse(response, "no earthBasicData found for projectId:" + projectBasicDataJpa.getProjectId());
			}
			Utils.writlnAndFlushResponse(response, "");
		}
	}

	@RequestMapping(value = "/updateProjectData", produces = "text/html; charset=utf-8")
	public void updateProjectData(final String projectId, final HttpServletResponse response) throws ClientProtocolException, IOException {
		response.setContentType("text/html; charset=utf-8");

		Utils.writlnAndFlushResponse(response, "updating project_basic_data " + projectId);
		final ProjectBasicDataJpa projectBasicDataJpa = this.spiderController.updateProjectBasicData(projectId);

		Utils.writlnAndFlushResponse(response,
				"updating projectId:" + projectBasicDataJpa.getProjectId() + ", projectName:" + projectBasicDataJpa.getProjectName());

		final PreSellLicenseData preSellLicenseData = this.spiderController.updatePreSellLicenseData(projectBasicDataJpa.getProjectId(),
				projectBasicDataJpa.getPreSellLicenseId());
		Utils.writlnAndFlushResponse(response, "updated preSellLicenseData, id:" + preSellLicenseData.getPreSellLicenseId());

		final String countryName = projectBasicDataJpa.getCountryName();
		final String countryId = projectBasicDataJpa.getCountryId();
		if (!Strings.isNullOrEmpty(countryId)) {
			final String[] countryIdAr = countryId.split(",");
			for (int i = 0; i < countryIdAr.length; i++) {
				final EarthBasicData earthBasicData = this.spiderController.updateEarthBasicData(projectId, countryName, countryId, countryIdAr[i],
						i);
				Utils.writlnAndFlushResponse(response, "updated earthBasicData, id:" + earthBasicData.getEarthLicenseId());
			}
		} else {
			Utils.writlnAndFlushResponse(response, "no earthBasicData found for projectId:" + projectId);
		}
		Utils.writlnAndFlushResponse(response, "");
	}
}