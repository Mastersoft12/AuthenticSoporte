package com.bancolombia.AuthenticSoporte.bean;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component("dataSourceO")
@Configuration
@ConfigurationProperties("spring.datasource")
public class DataSourceOracle implements IDatasource {

	@NotNull
	private String username;
	@NotNull
	private String password;
	@NotNull
	private String url;	
	@NotNull
	private String driverClassName;	
	

	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public void setUrl(String url) {
		this.url = url;
	}	
	
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}


	@Override
	public DataSource obtenerDatasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(url);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

}
