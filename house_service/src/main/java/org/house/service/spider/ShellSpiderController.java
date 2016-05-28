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

@RestController
@RequestMapping("/spider")
public class ShellSpiderController {
	@Autowired
	private SpiderController spiderController;

	@RequestMapping(value = "/updatePageData")
	public void updateProjectBasicData(final int page, final HttpServletResponse response) throws ClientProtocolException, IOException {
		final List<ProjectBasicData> projectBasicDatas = this.spiderController.updateProjectBasicData(page);
		Utils.writlnAndFlushResponse(response, "updated page " + page);
		for (final ProjectBasicData projectBasicData : projectBasicDatas) {
			Utils.writlnAndFlushResponse(response,
					"updating projectId:" + projectBasicData.getProjectId() + ", projectName:" + projectBasicData.getProjectName());

			final PreSellLicenseData preSellLicenseData = this.spiderController.updatePreSellLicenseData(projectBasicData.getProjectId(),
					projectBasicData.getPreSellLicenseId());
			Utils.writlnAndFlushResponse(response, "updated preSellLicenseData, id:" + preSellLicenseData.getPreSellLicenseId());

			final String countryName = projectBasicData.getCountryName();
			final String countryId = projectBasicData.getCountryId();
			final String[] countryIdAr = countryId.split(",");
			for (int i = 0; i < countryIdAr.length; i++) {
				final EarthBasicData earthBasicData = this.spiderController.updateEarthBasicData(countryName, countryId, countryIdAr[i], i);
				Utils.writlnAndFlushResponse(response, "updated earthBasicData, id:" + earthBasicData.getEarthLicenseId());
			}
			Utils.writlnAndFlushResponse(response, "");
		}

	}
}