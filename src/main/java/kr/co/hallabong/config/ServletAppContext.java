package kr.co.hallabong.config;

import javax.annotation.Resource;

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
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.hallabong.bean.CustBean;
import kr.co.hallabong.mapper.CustMapper;
import kr.co.hallabong.service.CustService;



@PropertySource("/WEB-INF/properties/db.properties")
@ComponentScan("kr.co.hallabong.controller") //스캔할 패키지
@ComponentScan("kr.co.hallabong.bean") //스캔할 패키지
@ComponentScan("kr.co.hallabong.config") //스캔할 패키지
@ComponentScan("kr.co.hallabong.dao") //스캔할 패키지
@ComponentScan("kr.co.hallabong.service") //스캔할 패키지
@EnableWebMvc //Controller로 등록되어 있는 클래스 셋팅
@Configuration
public class ServletAppContext implements WebMvcConfigurer {
	
	@Value("${db.classname}")
	private String db_classname;
	
	@Value("${db.url}")
	private String db_url;
	
	@Value("${db.username}")
	private String db_username;
	
	@Value("${db.password}")
	private String db_password;
	
	/*
	 * @Autowired private TopMenuService topMenuService;
	 */
	
	@Resource(name="loginCustBean")
	private CustBean loginCustBean;
	
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
	
	
	
	 //데이터베이스 접속 정보 관리
	   @Bean
	   public BasicDataSource dataSource() {
	      BasicDataSource source = new BasicDataSource();
	      source.setDriverClassName(db_classname);
	      source.setUrl(db_url);
	      source.setUsername(db_username);
	      source.setPassword(db_password);
	      
	      return source;
	   }
	   
	   
	   //쿼리문과 접속 관리하는 객체(sqlSessionFactory 접속, 쿼리를 관리하는 객체 )
	   @Bean
	   public SqlSessionFactory factory(BasicDataSource source) throws Exception{
	      
	      SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	      factoryBean.setDataSource(source);
	      
	      SqlSessionFactory factory = factoryBean.getObject();
	      return factory;
	   }   
	   
/*	   // 쿼리문 실행을 위한 객체(쿼리문을 관리하는 Mapper를 정의)
	   @Bean
	      public MapperFactoryBean<CustMapper> getCustmapper(SqlSessionFactory factory) throws Exception{
	         MapperFactoryBean<CustMapper> factoryBean = new MapperFactoryBean<CustMapper>(CustMapper.class);
	         factoryBean.setSqlSessionFactory(factory);
	         return factoryBean;
	      

		      @Bean
		      public MapperFactoryBean<TopMenuMapper> getTopMenuMapper(SqlSessionFactory factory) throws Exception{
		         MapperFactoryBean<TopMenuMapper> factoryBean = new MapperFactoryBean<TopMenuMapper>(TopMenuMapper.class);
		         factoryBean.setSqlSessionFactory(factory);
		         return factoryBean;
			
		}
		      
*/		      
		      @Bean
		      public MapperFactoryBean<CustMapper> getUsermapper(SqlSessionFactory factory) throws Exception{
		         MapperFactoryBean<CustMapper> factoryBean = new MapperFactoryBean<CustMapper>(CustMapper.class);
		         factoryBean.setSqlSessionFactory(factory);
		         return factoryBean;
			
		}
		      
/*
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				
				WebMvcConfigurer.super.addInterceptors(registry);
				//메뉴 & 로그인 세션정보
				TopMenuInterceptor topMenuInterceptor=new TopMenuInterceptor(topMenuService, loginUserBean);
				InterceptorRegistration reg1=registry.addInterceptor(topMenuInterceptor);
				//작동시기
				reg1.addPathPatterns("/**"); //모든 요청에 대하여 작동
				
				// 로그인처리
				CheckLoginInterceptor checkLoginInterceptor=new CheckLoginInterceptor(loginUserBean);
				InterceptorRegistration reg2=registry.addInterceptor(checkLoginInterceptor);
				
				reg2.addPathPatterns("/user/modify", "/user/logout","/board/*");
				reg2.excludePathPatterns("/board/main");
				
				
			}
			
*/			
			//properties와 충돌되어 오류가 발생 되므로 분리하여 등록
			@Bean
			public static PropertySourcesPlaceholderConfigurer PropertySourcesPlaceholderConfigurer() {
				return new PropertySourcesPlaceholderConfigurer();
			}
		      
			//메세지 등록
			@Bean
			public ReloadableResourceBundleMessageSource messageSource() {
				ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
				res.setBasename("/WEB-INF/properties/error_message");
				
				return res;
						
						
			}
			
			//정적파일 인식
			@Bean
			public StandardServletMultipartResolver multipartResolver() {
				return new StandardServletMultipartResolver();
			}
			
		      
		
	}
		
		