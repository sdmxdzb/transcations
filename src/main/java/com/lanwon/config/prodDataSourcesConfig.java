/**
 * 蓝网科技股份有限公司
 */
package com.lanwon.config;

import java.sql.SQLException;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author dzb	
 * @date 2017年8月23日 
 * 
 */
@Configuration
@MapperScan(basePackages="com.lanwon.mapper.prod",sqlSessionFactoryRef="prodSqlSessionFactory")
public class prodDataSourcesConfig {

    @Value("${spring.datasource.prod.driver-class-name}")
    private String dbDriver;

    @Value("${spring.datasource.prod.url}")
    private String dbUrl;

    @Value("${spring.datasource.prod.username}")
    private String userName;

    @Value("${spring.datasource.prod.password}")
    private String password;

    @Resource
    private GlobalDbConfig globalDbConfig;

    private static final String MAPPER_PATH = "classpath*:/mapper/prod/*.xml";

    private static final String ENTITY_PACKAGE = "com.lanwon.entity.prod";

    @Bean(name = "prodDataSource")
    public DataSource prodDataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setFilters(globalDbConfig.getDbFilters());
        dataSource.setMaxActive(globalDbConfig.getDbMaxActive());
        dataSource.setMaxWait(globalDbConfig.getDbMaxWait());
        dataSource.setMinIdle(globalDbConfig.getDbMinIdle());
        dataSource.setTimeBetweenEvictionRunsMillis(globalDbConfig
                .getDbTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(globalDbConfig
                .getDbMinEvictableIdleTimeMillis());
        dataSource.setTestWhileIdle(globalDbConfig.isDbtestWhileIdle());
        dataSource.setValidationQuery(globalDbConfig.getDbValidationQuery());
        dataSource.setTestOnBorrow(globalDbConfig.isDbTestOnBorrow());
        dataSource.setTestOnReturn(globalDbConfig.isDbTestOnReturn());
        dataSource.setPoolPreparedStatements(globalDbConfig
                .isDbPoolPreparedStatements());
        dataSource.setMaxOpenPreparedStatements(globalDbConfig
                .getDbMaxOpenPreparedStatements());
        dataSource.init();
        return dataSource;
    }

    @Bean(name = "prodTransactionManager")
    public DataSourceTransactionManager prodTransactionManager()
            throws SQLException {
        return new DataSourceTransactionManager(prodDataSource());
    }

    @Bean(name = "prodSqlSessionFactory")
    public SqlSessionFactory prodSqlSessionFactory(
            @Qualifier("prodDataSource") DataSource pdataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(pdataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources(MAPPER_PATH));
        sessionFactory.setTypeAliasesPackage(ENTITY_PACKAGE);
        return sessionFactory.getObject();
    }

}
