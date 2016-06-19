package org.house.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfiguration {
	private final Logger LOGGER = LoggerFactory.getLogger(TomcatConfiguration.class);

	@Value("${spring.customConnector.port}")
	private Integer port;
	@Value("${spring.customConnector.protocol}")
	private String protocol;
	@Value("${spring.customConnector.connectionTimeout}")
	private Integer connectionTimeout;
	@Value("${spring.customConnector.maxKeepAliveRequests}")
	private Integer maxKeepAliveRequests;
	@Value("${spring.customConnector.keepAliveTimeout}")
	private Integer keepAliveTimeout;

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		this.LOGGER.debug("Configuring Tomcat");
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected void customizeConnector(Connector connector) {
				super.customizeConnector(connector);
				@SuppressWarnings("rawtypes")
				AbstractHttp11Protocol abstractHttp11Protocol = (AbstractHttp11Protocol) connector.getProtocolHandler();
				abstractHttp11Protocol.setConnectionTimeout(connectionTimeout);
				abstractHttp11Protocol.setMaxKeepAliveRequests(maxKeepAliveRequests);
				abstractHttp11Protocol.setKeepAliveTimeout(keepAliveTimeout);
			}
		};
		factory.setPort(port);

		String protocolClassName = null;
		switch (protocol) {
		case "Http11Protocol":
			protocolClassName = "org.apache.coyote.http11.Http11Protocol";
			break;
		case "Http11AprProtocol":
			protocolClassName = "org.apache.coyote.http11.Http11AprProtocol";
			break;
		case "org.apache.coyote.http11.Http11Protocol":
		case "org.apache.coyote.http11.Http11AprProtocol":
		case "org.apache.coyote.http11.Http11NioProtocol":
			protocolClassName = protocol;
			break;
		// case "Http11NioProtocol":
		default:
			protocolClassName = "org.apache.coyote.http11.Http11NioProtocol";
			break;
		}
		factory.setProtocol(protocolClassName);
		return factory;
	}
}