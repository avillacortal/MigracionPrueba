package com.telefonica.b2b.fidelity.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * @Author:
 * @Datecreation: 01 jun. 2020 04:00
 * @FileName: RTDMConfig.java
 * @AuthorCompany: Telefonica
 * @version: 0.1
 * @Description: Configuración de conexión con Base de Datos (Base RTDM,
 *               utilizada para cruzar catálogos de planes móviles).
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "daasEntityManagerFactory", transactionManagerRef = "daasTransactionManager", basePackages = {
		"com.telefonica.b2b.fidelity.daas.repository" })

public class DAASConfig {

	@Value(value = "${daas.datasource.maximumPoolSize}")
	private Integer poolSize;
	@Value(value = "${daas.datasource.url}")
	private String jdbcUrl;
	@Value(value = "${daas.datasource.username}")
	private String userName;
	@Value(value = "${daas.datasource.password}")
	private String password;
	@Value(value = "${daas.datasource.driver-class-name}")
	private String driverClassName;
	@Value(value = "${daas.datasource.connectionTimeout}")
	private Long connectionTimeOut;
	@Value(value = "${daas.datasource.minimumIdle}")
	private Integer minimunIdle;

	@Bean(name = "daasDataSource")
	public HikariDataSource dataSource() {
		final HikariDataSource hds = new HikariDataSource();
		hds.setMaximumPoolSize(poolSize);
		hds.setJdbcUrl(jdbcUrl);
		hds.setUsername(userName);
		hds.setPassword(password);
		hds.setDriverClassName(driverClassName);
		hds.setConnectionTimeout(connectionTimeOut);
		hds.setMinimumIdle(minimunIdle);
		return hds;
	}

	@Bean(name = "daasEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean rtdmEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("daasDataSource") HikariDataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.telefonica.b2b.fidelity.entity").persistenceUnit("daas").build();
	}

	@Bean(name = "daasTransactionManager")
	public PlatformTransactionManager rtdmTransactionManager(
			@Qualifier("daasEntityManagerFactory") EntityManagerFactory daasEntityManagerFactory) {
		return new JpaTransactionManager(daasEntityManagerFactory);
	}

}
