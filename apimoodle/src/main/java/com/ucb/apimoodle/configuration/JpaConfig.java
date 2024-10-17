package com.ucb.apimoodle.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import jakarta.annotation.PreDestroy;

@Configuration
public class JpaConfig {

	private LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setPackagesToScan("com.portal.apimoodle.entities");

		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);

		return entityManagerFactoryBean;
	}

	@PreDestroy
	public void onDestroy() {
		if (entityManagerFactoryBean != null) {
			entityManagerFactoryBean.destroy();
		}
	}
}