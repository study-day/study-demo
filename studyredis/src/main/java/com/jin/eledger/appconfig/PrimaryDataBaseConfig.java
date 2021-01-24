package com.jin.eledger.appconfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

//import lombok.Data;

/**
 * @Description: 主数据源配置类
 */
@Configuration
@MapperScan(basePackages = PrimaryDataBaseConfig.PACKAGE, sqlSessionFactoryRef = "primarySqlSessionFactory")
public class PrimaryDataBaseConfig {

    @Value("${jin.primary.datasource.jndi-name}")
    private String jndiName;

    @Value("${jin.primary.datasource.type}")
    private String type;
    /**
     * dao层的包路径
     */
    static final String PACKAGE = "com.jin.eledger.dao";

    /**
     * mapper文件的相对路径
     */
    private static final String MAPPER_LOCATION = "classpath:mapper/*.xml";
    @Value("${jin.primary.datasource.druid.filters}")
    private String filters;
    @Value("${jin.primary.datasource.druid.url}")
    private String url;
    @Value("${jin.primary.datasource.druid.username}")
    private String username;
    @Value("${jin.primary.datasource.druid.password}")
    private String password;
    @Value("${jin.primary.datasource.druid.driverClassName}")
    private String driverClassName;
    @Value("${jin.primary.datasource.druid.initialSize}")
    private int initialSize;
    @Value("${jin.primary.datasource.druid.minIdle}")
    private int minIdle;
    @Value("${jin.primary.datasource.druid.maxActive}")
    private int maxActive;
    @Value("${jin.primary.datasource.druid.maxWait}")
    private long maxWait;
    @Value("${jin.primary.datasource.druid.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;
    @Value("${jin.primary.datasource.druid.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;
    @Value("${jin.primary.datasource.druid.validationQuery}")
    private String validationQuery;
    @Value("${jin.primary.datasource.druid.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${jin.primary.datasource.druid.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${jin.primary.datasource.druid.testOnReturn}")
    private boolean testOnReturn;
    @Value("${jin.primary.datasource.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${jin.primary.datasource.druid.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource() throws SQLException {
        if("jndi".equalsIgnoreCase(type)) {
            JndiDataSourceLookup lookup = new JndiDataSourceLookup();
            //lookup.setResourceRef(true);
            DataSource jndiDatasource = lookup.getDataSource(jndiName);
            return jndiDatasource;

        }
        DruidDataSource druid = new DruidDataSource();
        if("jdbc".equalsIgnoreCase(type)){
            druid.setUsername(username);
            druid.setPassword(password);
            druid.setUrl(url);
        }
        // 监控统计拦截的filters
        druid.setFilters(filters);
        // 配置基本属性
        druid.setDriverClassName(driverClassName);
        //初始化时建立物理连接的个数
        druid.setInitialSize(initialSize);
        //最大连接池数量
        druid.setMaxActive(maxActive);
        //最小连接池数量
        druid.setMinIdle(minIdle);
        //获取连接时最大等待时间，单位毫秒。
        druid.setMaxWait(maxWait);
        //间隔多久进行一次检测，检测需要关闭的空闲连接
        druid.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        //一个连接在池中最小生存的时间
        druid.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        //用来检测连接是否有效的sql
        druid.setValidationQuery(validationQuery);
        //建议配置为true，不影响性能，并且保证安全性。
        druid.setTestWhileIdle(testWhileIdle);
        //申请连接时执行validationQuery检测连接是否有效
        druid.setTestOnBorrow(testOnBorrow);
        druid.setTestOnReturn(testOnReturn);
        //是否缓存preparedStatement，也就是PSCache，oracle设为true，mysql设为false。分库分表较多推荐设置为false
        druid.setPoolPreparedStatements(poolPreparedStatements);
        // 打开PSCache时，指定每个连接上PSCache的大小
        druid.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        return druid;
    }

    // 创建该数据源的事务管理
   // @Primary
    @Bean(name = "primaryTransactionManager")
    public DataSourceTransactionManager primaryTransactionManager() throws SQLException {
        return new DataSourceTransactionManager(primaryDataSource());
    }
    // 创建Mybatis的连接会话工厂实例
    @Bean(name = "primarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource primaryDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(primaryDataSource);  // 设置数据源bean
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(PrimaryDataBaseConfig.MAPPER_LOCATION));  // 设置mapper文件路径
        return sessionFactory.getObject();
    }
}
