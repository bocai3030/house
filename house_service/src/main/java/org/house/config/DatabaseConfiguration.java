package org.house.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfiguration implements EnvironmentAware {

	private final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfiguration.class);

	private RelaxedPropertyResolver propertyResolver;

	private Environment env;

	@Override
	public void setEnvironment(final Environment env) {
		this.env = env;
		this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
	}

	@Bean(destroyMethod = "shutdown")
	public DataSource dataSource() {
		this.LOGGER.debug("Configuring Datasource");
		if (this.propertyResolver.getProperty("url") == null
				&& this.propertyResolver.getProperty("databaseName") == null) {
			this.LOGGER.error(
					"Your database connection pool configuration is incorrect! The application"
							+ "cannot start. Please check your Spring profile, current profiles are: {}",
					Arrays.toString(this.env.getActiveProfiles()));

			throw new ApplicationContextException("Database connection pool is not configured correctly");
		}
		final HikariConfig config = new HikariConfig();
		config.setDataSourceClassName(this.propertyResolver.getProperty("dataSourceClassName"));
		if (this.propertyResolver.getProperty("url") == null || "".equals(this.propertyResolver.getProperty("url"))) {
			config.addDataSourceProperty("databaseName", this.propertyResolver.getProperty("databaseName"));
			config.addDataSourceProperty("serverName", this.propertyResolver.getProperty("serverName"));
		} else {
			config.addDataSourceProperty("url", this.propertyResolver.getProperty("url"));
		}
		config.addDataSourceProperty("user", this.propertyResolver.getProperty("username"));
		config.addDataSourceProperty("password", this.propertyResolver.getProperty("password"));
		config.addDataSourceProperty("autoReconnect", this.propertyResolver.getProperty("autoReconnect", "true"));

		if ("com.mysql.jdbc.jdbc2.optional.MysqlDataSource"
				.equals(this.propertyResolver.getProperty("dataSourceClassName"))) {
			config.addDataSourceProperty("cachePrepStmts", this.propertyResolver.getProperty("cachePrepStmts", "true"));
			config.addDataSourceProperty("prepStmtCacheSize",
					this.propertyResolver.getProperty("prepStmtCacheSize", "250"));
			config.addDataSourceProperty("prepStmtCacheSqlLimit",
					this.propertyResolver.getProperty("prepStmtCacheSqlLimit", "2048"));
			config.addDataSourceProperty("useServerPrepStmts",
					this.propertyResolver.getProperty("useServerPrepStmts", "true"));
		}
		return new HikariDataSource(config);
	}
}
