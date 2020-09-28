package com.zf.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author zhengfan
 * @create 2020-09-28 22:29
 *
 *  配置数据源 JDBCTemplate
 * 开启事务 3步
 *  1 @EnableTransactionManagement
 *  2 给方法上直接使用  @Transactional 表示方法事务管理
 * No qualifying bean of type 'org.springframework.transaction.PlatformTransactionManager
 * 3 需要配置事务管理器 控制事务
 *
 *  事务原理
 *  @EnableTransactionManagement
 *  给容器中 导入组件
 *  @Import(TransactionManagementConfigurationSelector.class
 *   导入了两个组件
 *   AutoProxyRegistrar
 *     给容器注册了InfrastructureAdvisorAutoProxyCreator这个组件  这个组件也是一个后置处理器
 *     利用后置处理器机制在对象创建之后 包装对象返回一个代理对象  代理对象执行方法利用拦截器链进行调用
 *   ProxyTransactionManagementConfiguration
 *     给容器中注册了事务增强器
 *       1事务增前器要是事务注解 AnnotationTransactionAttributeSource 解析事务注解
 *       2事务拦截器
 *        transactionInterceptor 【implements MethodInterceptor】 是一个保存了事务属性信息和事务管理器的拦截器
 *        他是一个MethodInterceptor
 *        在目标方法执行的时候执行拦截器链
 *        作用:
 *           1 先获取事务相关的属性
 *           2 在获取事务PlatformTransactionManager 如果事先没有添加指定任何事务管理器
 *           最终会从容器中按照类型获取事务管理器
 *           3 执行目标方法如果异常获取到事务管理器利用事务管理器回滚这次操作
 *             如果执行目标方法正常通过事务管理器进行提交事务
 */
@EnableTransactionManagement
@Configuration
@ComponentScan("com.zf")
@PropertySource({"classpath:/db.properties"})
public class TxConfig {

    @Value("${jdbc.driverClass}")
    private String driverClass;
    @Value("${jdbc.user}")
    private String user;
    @Value("${jdbc.password}")
    private  String password;
    @Value("${jdbc.url}")
    private  String url;

    @Bean
    public DataSource dataSource() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return  dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return  jdbcTemplate;
    }

    //给容器中注册 事务管理器
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        return  transactionManager;
    }


}
