package com.example.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

@Configuration
@MapperScan(basePackages = "com.example.mapper.db02", sqlSessionTemplateRef = "sqlSessionTemplate02")
public class MyBatisConfig02 {

	@Bean(name = "dataSource02")
	public DataSource test2DataSource(DBConfig01 test2Config) throws SQLException {
		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
		mysqlXaDataSource.setUrl(test2Config.getUrl());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXaDataSource.setPassword(test2Config.getPassword());
		mysqlXaDataSource.setUser(test2Config.getUsername());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlXaDataSource);
		xaDataSource.setUniqueResourceName("dataSource02");

		xaDataSource.setMinPoolSize(test2Config.getMinPoolSize());
		xaDataSource.setMaxPoolSize(test2Config.getMaxPoolSize());
		xaDataSource.setMaxLifetime(test2Config.getMaxLifetime());
		xaDataSource.setBorrowConnectionTimeout(test2Config.getBorrowConnectionTimeout());
		xaDataSource.setLoginTimeout(test2Config.getLoginTimeout());
		xaDataSource.setMaintenanceInterval(test2Config.getMaintenanceInterval());
		xaDataSource.setMaxIdleTime(test2Config.getMaxIdleTime());
		xaDataSource.setTestQuery(test2Config.getTestQuery());
		return xaDataSource;
	}

	// @Bean(name = "test2SqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("dataSource02") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper2/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "sqlSessionTemplate02")
	public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("dataSource02") DataSource dataSource)
			throws Exception {

		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper2/*.xml"));
		return new SqlSessionTemplate(bean.getObject());
	}
}
