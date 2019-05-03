package by.petrik.spring.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//import com.mysql.cj.xdevapi.SessionFactory;

//@EnableAutoConfiguration
@Configuration
//@ComponentScan(basePackages = "by.patrik.springmvc.*")
@EnableJpaRepositories(basePackages={"by.patrik.srpingmvc.DAO.Repository"})
//@EntityScan("by.patrik.springmvc.DAO.Model")
//@PropertySource("classpath:app.properties")
@EnableTransactionManagement
public class DataConfig{
	
	
	/*
	//Плейсхолдеры для подключения бд
	//-------------------
	@Value("${jdbc.driver}")
	private String driverName;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String userName;
	
	@Value("${jdbc.password}")
	private String password;
	//-------------------
	
	//Плейсхолдеры hibernate
	//-------------------
	@Value("${hibernate.dialect}")
	private String hibernateDialect;
	
	@Value("${hibernate.show_sql}")
	private String showsql;
	
	@Value("${hibernate.format_sql}")
	private String formatsql;
	
	@Value("${hibernate.creation_policy}")
	private String creationPolicy;
	//-------------------
	 */
	/*
    private static final String PROP_DATABASE_DRIVER = "jdbc.driver";
    private static final String PROP_DATABASE_URL = "jdbc.url";
    private static final String PROP_DATABASE_USERNAME = "jdbc.username";
    private static final String PROP_DATABASE_PASSWORD = "jdbc.password";
    private static final String PROP_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROP_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROP_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROP_HIBERNATE_CREATION_POLICY = "hibernate.creation_policy";
    
    //private static final String PROP_ENTITYMANAGER_PACKAGES_TO_SCAN = "db.entitymanager.packages.to.scan";
    //private static final String PROP_HIBERNATE_HBM2DDL_AUTO = "db.hibernate.hbm2ddl.auto";

    //@Resource
    //private Environment env;
	
	//Отвечает за подключение к бд
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/testdb?useLegacyDatetimeCode=false&serverTimezone=UTC");
		dataSource.setUsername("root");		
		dataSource.setPassword("12345");
		return dataSource;		
	}
	
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("by.patrik.springmvc.DAO.Model");
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;	
	}
	
	@Bean
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");	
		properties.setProperty("hibernate.show_sql", "true");	
		properties.setProperty("hibernate.format_sql", "true");	
		properties.setProperty("hibernate.creation_policy", "create");	
		properties.setProperty("hibernate.hbm2ddl.auto", "create");
		
	    //properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
	    //properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		
        //properties.put(PROP_HIBERNATE_DIALECT, env.getRequiredProperty(PROP_HIBERNATE_DIALECT));
        //properties.put(PROP_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL));
        //properties.put(PROP_HIBERNATE_FORMAT_SQL, env.getRequiredProperty(PROP_HIBERNATE_FORMAT_SQL));
        //properties.put(PROP_HIBERNATE_CREATION_POLICY, env.getRequiredProperty(PROP_HIBERNATE_CREATION_POLICY));
		return properties;		
	}
	
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;		
	}
	
	
	
		
	//Отвечает за плейсхолдеры необходим что бы спринг их воспринимал(их обработка)
	//@Bean
	//public PropertyPlaceholderConfigurer placeholderConfig() {
	//	return new PropertyPlaceholderConfigurer();		
	//}
	 
	 */
	


    @Autowired
    private Environment environment;

    /*
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
    	//DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        return dataSource;
    }
    */
    
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/testdb?useLegacyDatetimeCode=false&serverTimezone=UTC");
		dataSource.setUsername("root");		
		dataSource.setPassword("12345");
		return dataSource;		
	}

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("by.patrik.springmvc.DAO.Model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }
	
}
