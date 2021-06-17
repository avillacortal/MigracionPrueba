package com.telefonica.b2b.fidelity.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(entityManagerFactoryRef = "rtdmEntityManagerFactory", transactionManagerRef = "rtdmTransactionManager", basePackages = {
		"com.telefonica.b2b.fidelity.repository" })

public class RTDMConfig {

	@Value(value = "${rtdm.datasource.maximumPoolSize}")
	private Integer poolSize;
	@Value(value = "${rtdm.datasource.jdbcUrl}")
	private String jdbcUrl;
	@Value(value = "${rtdm.datasource.username}")
	private String userName;
	@Value(value = "${rtdm.datasource.password}")
	private String password;
	@Value(value = "${rtdm.datasource.driverClassName}")
	private String driverClassName;
	@Value(value = "${rtdm.datasource.connectionTimeout}")
	private Long connectionTimeOut;
	@Value(value = "${rtdm.datasource.minimumIdle}")
	private Integer minimunIdle;

	@Primary
	@Bean(name = "rtdmDataSource")
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
	@Primary
	@Bean(name = "rtdmEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean rtdmEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("rtdmDataSource") HikariDataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.telefonica.b2b.fidelity.entity").persistenceUnit("rtdm").build();
	}
	@Primary
	@Bean(name = "rtdmTransactionManager")
	public PlatformTransactionManager rtdmTransactionManager(
			@Qualifier("rtdmEntityManagerFactory") EntityManagerFactory rtdmEntityManagerFactory) {
		return new JpaTransactionManager(rtdmEntityManagerFactory);
	}

}
