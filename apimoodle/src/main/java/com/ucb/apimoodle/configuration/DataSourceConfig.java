package com.ucb.apimoodle.configuration;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

//BUSCA AS INFORMAÇÕES PARA CONEXÃO COM O BANCO DE DADOS DO application.properties E AS COLOCA EM VARIÁVEIS PARA USO POSTERIOR
@Configuration
public class DataSourceConfig {
	@Value("${spring.datasource.first.url}")
	private String firstUrl;
	
	@Value("${spring.datasource.first.username}")
	private String firstUsername;
	
	@Value("${spring.datasource.first.password}")
	private String firstPassword;
	
	@Value("${spring.datasource.first.driver-class-name}")
	private String firstDriverClassName;
	
	@Value("${spring.datasource.second.url}")
	private String secondUrl;
	
	@Value("${spring.datasource.second.username}")
	private String secondusername;
	
	@Value("${spring.datasource.second.password}")
	private String secondPassword;
	
	@Value("${spring.datasource.second.driver-class-name}")
	private String secondDriverClassName;
	
	@Bean(value = "firstDataSource")
	@Primary
	public DataSource firstDataSource() {
		return createDataSource(firstUrl, firstUsername, firstPassword, firstDriverClassName);
	}
	
	@Bean(value = "secondDataSource")
	public DataSource secondDataSource() {
		return createDataSource(secondUrl, secondusername, secondPassword, secondDriverClassName);
	}
	
	private DataSource createDataSource(String url, String username, String password, String driverClassName) {
		DataSource dataSource = new DataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setInitialSize(5);
		dataSource.setMaxActive(10);
		dataSource.setMaxIdle(5);
		dataSource.setMinIdle(2);
		return dataSource;
	}
}
