package org.house.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.house.bean.EarthBasicData;
import org.house.bean.PreSellLicenceData;
import org.house.bean.ProjectBasicData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EarthDataController {
	@RequestMapping(value = "/getProjectBasicData", produces = "application/json")
	Object getProjectBasicData(@RequestParam("page") final int page) throws ClientProtocolException, IOException {
		final List<ProjectBasicData> projectBasicDatas = WebSpider.getProjectBasicData(page);

		return projectBasicDatas;
	}

	@RequestMapping(value = "/getPreSellLicenceData", produces = "application/json")
	Object getPreSellLicenceData(@RequestParam("projectId") final String projectId,
			@RequestParam("licenceId") final String licenceId) throws ClientProtocolException, IOException {
		final PreSellLicenceData preSellLicenceData = WebSpider.getPreSellLicenceData(projectId, licenceId);

		return preSellLicenceData;
	}

	@RequestMapping(value = "/getEarthBasicData", produces = "application/json")
	Object getEarthBasicData(@RequestParam("projectId") final String projectId,
			@RequestParam("licenceId") final String licenceId) throws ClientProtocolException, IOException {
		final EarthBasicData preSellLicenceData = WebSpider.getEarthBasicData(projectId, licenceId);

		return preSellLicenceData;
	}
}