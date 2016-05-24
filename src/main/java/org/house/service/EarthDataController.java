package org.house.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.house.bean.EarthBasicData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EarthDataController {
	@RequestMapping(value = "/getPreSellEarthBasicData", produces = "application/json")
	Object getPreSellEarthBasicData(@RequestParam("page") final int page) throws ClientProtocolException, IOException {
		final List<EarthBasicData> earthBasicDatas = WebSpider.getPreSellEarthBasicData(page);

		return earthBasicDatas;
	}
}