package kr.co.hallabong.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.hallabong.mapper.CatMapper;

@Configuration
@EnableWebMvc //Controller로 등록되어 있는 클래스 셋팅
@ComponentScan("kr.co.hallabong.controller") //스캔할 패키지
@ComponentScan("kr.co.hallabong.dao") //스캔할 패키지
@ComponentScan("kr.co.hallabong.service") //스캔할 패키지
@ComponentScan("kr.co.hallabong.rowMapper")
@PropertySource("/WEB-INF/properties/db.properties")
public class ServletAppContext implements WebMvcConfigurer {
	@Value("${db.classname}")
	private String db_classname;
	
	@Value("${db.url}")
	private String db_url;
	
	@Value("${db.username}")
	private String db_username;
	
	@Value("${db.password}")
	private String db_password;
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// view 경로와 확장자 셋팅
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 이미지,영상, 소리 등 정적파일 경로 매핑
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName(db_classname);
		source.setUrl(db_url);
		source.setUsername(db_username);
		source.setPassword(db_password);
		
		return source;
	}
	
	@Bean
	public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(source);
		SqlSessionFactory factory = factoryBean.getObject();
		return factory;
	}
	
	/*
	 컴파일시 올바른 입력도 유효성에 걸림
	이유
	enctype="multipart/form-data"에서 정보를 받으면 일반적인 방법이 아니므로 contentBean의 
	@NotBlank
	private String content_subject;
	
	@NotBlank
	private String content_text;에서 받지못함
	그러므로 Bean을 정의 
	 */

	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	
//	@Bean
//	public MapperFactoryBean<CategoryMapper> getCategoryMapper(SqlSessionFactory factory) throws Exception{
//		MapperFactoryBean<CategoryMapper> factoryBean = new MapperFactoryBean<CategoryMapper>(CategoryMapper.class);
//		factoryBean.setSqlSessionFactory(factory);
//		return factoryBean;
//	}
//	
//	@Bean
//	public MapperFactoryBean<UserMapper> getUserMapper(SqlSessionFactory factory) throws Exception{
//		MapperFactoryBean<UserMapper> factoryBean = new MapperFactoryBean<UserMapper>(UserMapper.class);
//		factoryBean.setSqlSessionFactory(factory);
//		return factoryBean;
//	}
}

