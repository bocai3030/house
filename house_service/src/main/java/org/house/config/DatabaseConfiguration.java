package org.house.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfiguration {
	private final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfiguration.class);

	@Value("${spring.datasource.dataSourceClassName}")
	private String dataSourceClassName;
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.autoReconnect}")
	private String autoReconnect;

	@Value("${spring.datasource.cachePrepStmts}")
	private String cachePrepStmts;
	@Value("${spring.datasource.prepStmtCacheSize}")
	private String prepStmtCacheSize;
	@Value("${spring.datasource.prepStmtCacheSqlLimit}")
	private String prepStmtCacheSqlLimit;
	@Value("${spring.datasource.useServerPrepStmts}")
	private String useServerPrepStmts;

	@Bean(destroyMethod = "shutdown")
	public DataSource dataSource() {
		this.LOGGER.debug("Configuring Datasource");
		if (this.url == null) {
			this.LOGGER.error(
					"Your database connection pool configuration is incorrect! The application cannot start. Please check your Spring profile.");
			throw new ApplicationContextException("Database connection pool is not configured correctly");
		}

		final HikariConfig config = new HikariConfig();
		config.setDataSourceClassName(this.dataSourceClassName);
		config.addDataSourceProperty("url", this.url);
		config.addDataSourceProperty("user", this.username);
		config.addDataSourceProperty("password", this.password);
		config.addDataSourceProperty("autoReconnect", this.autoReconnect);

		if ("com.mysql.jdbc.jdbc2.optional.MysqlDataSource".equals(this.dataSourceClassName)) {
			config.addDataSourceProperty("cachePrepStmts", this.cachePrepStmts);
			config.addDataSourceProperty("prepStmtCacheSize", this.prepStmtCacheSize);
			config.addDataSourceProperty("prepStmtCacheSqlLimit", this.prepStmtCacheSqlLimit);
			config.addDataSourceProperty("useServerPrepStmts", this.useServerPrepStmts);
		}
		return new HikariDataSource(config);
	}
}
