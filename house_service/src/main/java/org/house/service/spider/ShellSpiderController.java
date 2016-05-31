package org.house.service.spider;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.house.db.entity.EarthBasicData;
import org.house.db.entity.PreSellLicenseData;
import org.house.db.entity.ProjectBasicData;
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
		final List<ProjectBasicData> projectBasicDatas = this.spiderController.updateProjectBasicDataByPage(page);
		Utils.writlnAndFlushResponse(response, "updated page " + page);
		for (final ProjectBasicData projectBasicData : projectBasicDatas) {
			Utils.writlnAndFlushResponse(response,
					"updating projectId:" + projectBasicData.getProjectId() + ", projectName:" + projectBasicData.getProjectName());

			final PreSellLicenseData preSellLicenseData = this.spiderController.updatePreSellLicenseData(projectBasicData.getProjectId(),
					projectBasicData.getPreSellLicenseId());
			Utils.writlnAndFlushResponse(response, "updated preSellLicenseData, id:" + preSellLicenseData.getPreSellLicenseId());

			final String countryName = projectBasicData.getCountryName();
			final String countryId = projectBasicData.getCountryId();
			if (!Strings.isNullOrEmpty(countryId)) {
				final String[] countryIdAr = countryId.split(",");
				for (int i = 0; i < countryIdAr.length; i++) {
					final EarthBasicData earthBasicData = this.spiderController.updateEarthBasicData(projectBasicData.getProjectId(), countryName,
							countryId, countryIdAr[i], i);
					Utils.writlnAndFlushResponse(response, "updated earthBasicData, id:" + earthBasicData.getEarthLicenseId());
				}
			} else {
				Utils.writlnAndFlushResponse(response, "no earthBasicData found for projectId:" + projectBasicData.getProjectId());
			}
			Utils.writlnAndFlushResponse(response, "");
		}
	}

	@RequestMapping(value = "/updateProjectData", produces = "text/html; charset=utf-8")
	public void updateProjectData(final String projectId, final HttpServletResponse response) throws ClientProtocolException, IOException {
		response.setContentType("text/html; charset=utf-8");

		Utils.writlnAndFlushResponse(response, "updating project_basic_data " + projectId);
		final ProjectBasicData projectBasicData = this.spiderController.updateProjectBasicData(projectId);

		Utils.writlnAndFlushResponse(response,
				"updating projectId:" + projectBasicData.getProjectId() + ", projectName:" + projectBasicData.getProjectName());

		final PreSellLicenseData preSellLicenseData = this.spiderController.updatePreSellLicenseData(projectBasicData.getProjectId(),
				projectBasicData.getPreSellLicenseId());
		Utils.writlnAndFlushResponse(response, "updated preSellLicenseData, id:" + preSellLicenseData.getPreSellLicenseId());

		final String countryName = projectBasicData.getCountryName();
		final String countryId = projectBasicData.getCountryId();
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