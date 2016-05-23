import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestClient {
	@Test
	public void testt() throws ClientProtocolException, IOException, InterruptedException {
		final CloseableHttpClient httpClient = HttpClientBuilder.create()
				// .setConnectionManager(new BasicHttpClientConnectionManager())
				// {
				// @Override
				// public synchronized void shutdown() {
				// System.out.println("called shutdown");
				// }
				//
				// @Override
				// protected void finalize() throws Throwable {
				// System.out.println("called finalize");
				// try {
				// this.shutdown();
				// } finally { // Make sure we call overridden method even if shutdown barfs
				// super.finalize();
				// }
				// }
				// }
				.build();
		final HttpGet httpGet = new HttpGet("http://192.168.252.128:8080/i1");
		final CloseableHttpResponse response = httpClient.execute(httpGet);
		System.out.println(EntityUtils.toString(response.getEntity()));

		Thread.sleep(10000);
		// 这里调用httpclient的close似乎是发的rst包！究竟怎么样才能够找出一个正常忘记关连接的方法？！考虑一下httpclient在多线程中的使用？
		// httpClient.close();
	}

	@Test
	public void test2() throws IOException {
		String url = "http://192.168.252.128:8080/i1";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());
	}

}
