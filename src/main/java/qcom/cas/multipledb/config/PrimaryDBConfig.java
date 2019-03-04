package qcom.cas.multipledb.config;

import java.util.HashMap;
import java.util.Properties;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

@Configuration
@ComponentScan
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "qcom.cas.multipledb.repository1", entityManagerFactoryRef = "primaryEntityManager",
transactionManagerRef = "transactionManager")
@EnableTransactionManagement
@EnableConfigurationProperties(User1DataSourceProperties.class)
public class PrimaryDBConfig {
	
	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;
	
	@Autowired
	private User1DataSourceProperties user1DataSourceProperties;
	
	
	/*@Bean("primaryTransactionManager")
    @Primary
    PlatformTransactionManager transactionManager(DataSource primaryDs) {
        return new JpaTransactionManager(entityManager().getObject());
    }*/
	
	
	//@Primary
	/*@Bean(name = "primaryDs")
	@ConfigurationProperties(prefix = "spring.primary.datasource")
	public DataSource customerDataSource() {
	  return DataSourceBuilder.create().build();
	}*/
	
	//@Primary
	@Bean(name = "primaryDs")
	//@ConfigurationProperties(prefix = "spring.primary.datasource")
	public DataSource customerDataSource() {
		MysqlXADataSource  ds = new MysqlXADataSource();
		ds.setURL(user1DataSourceProperties.getUrl());
		ds.setUser(user1DataSourceProperties.getUsername());
		ds.setPassword(user1DataSourceProperties.getPassword());
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(ds);
		xaDataSource.setUniqueResourceName("xads1");
	  return xaDataSource;
	}
	
	@Bean("primaryEntityManager")
	@DependsOn("transactionManager")
    //@Primary
    public LocalContainerEntityManagerFactoryBean entityManager() {
		
		//atmokios
		HashMap<String, Object> txPproperties = new HashMap<String, Object>();
		txPproperties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		txPproperties.put("javax.persistence.transactionType", "JTA");
		
        /*HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");*/
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(customerDataSource());
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan("qcom.cas.multipledb.entity1");
        entityManager.setPersistenceUnitName("primaryPersistenceUnit");
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        entityManager.setJpaProperties(properties);
        
        //atmokios
        entityManager.setJpaPropertyMap(txPproperties);
        
        return entityManager;
    }

}
