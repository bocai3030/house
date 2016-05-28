package org.house.service.spider;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.house.db.entity.EarthBasicData;
import org.house.db.entity.PreSellLicenseData;
import org.house.db.entity.ProjectBasicData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spider")
public class ShellSpiderController {
	@Autowired
	private SpiderController spiderController;

	@RequestMapping(value = "/updatePageData", produces = "application/json")
	public void updateProjectBasicData(final int page) throws ClientProtocolException, IOException {
		final List<ProjectBasicData> projectBasicDatas = this.spiderController.updateProjectBasicData(page);
		System.out.println("updated page " + page);
		for (final ProjectBasicData projectBasicData : projectBasicDatas) {
			System.out.println("updating projectId:" + projectBasicData.getProjectId());

			final PreSellLicenseData preSellLicenseData = this.spiderController.updatePreSellLicenseData(projectBasicData.getProjectId(),
					projectBasicData.getPreSellLicenseId());
			System.out.println("updated preSellLicenseData, id:" + preSellLicenseData.getPreSellLicenseId());

			final String countryName = projectBasicData.getCountryName();
			final String countryId = projectBasicData.getCountryId();
			final String[] countryIdAr = countryId.split(",");
			for (int i = 0; i < countryIdAr.length; i++) {
				final EarthBasicData earthBasicData = this.spiderController.updateEarthBasicData(countryName, countryId, countryIdAr[i], i);
				System.out.println("updated earthBasicData, id:" + earthBasicData.getEarthLicenseId());
			}
			System.out.println();
		}

	}
}