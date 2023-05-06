package kr.co.hallabong.config;

import javax.annotation.Resource;
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
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.hallabong.bean.AdminBean;
import kr.co.hallabong.bean.CustBean;
import kr.co.hallabong.interceptor.AdminInterceptor;
import kr.co.hallabong.mapper.AdminMapper;
import kr.co.hallabong.mapper.CartMapper;
import kr.co.hallabong.mapper.CatMapper;
import kr.co.hallabong.mapper.CustMapper;
import kr.co.hallabong.mapper.DlvyMapper;
import kr.co.hallabong.mapper.NotiMapper;
import kr.co.hallabong.mapper.ODPDMapper;
import kr.co.hallabong.mapper.OrdMapper;
import kr.co.hallabong.mapper.ProdCatMapper;
import kr.co.hallabong.mapper.ProdMapper;
import kr.co.hallabong.mapper.QAMapper;
import kr.co.hallabong.interceptor.CheckLoginInterceptor;
import kr.co.hallabong.interceptor.TopMenuInterceptor;

@ComponentScan("kr.co.hallabong.beans") // 스캔할 패키지
@ComponentScan("kr.co.hallabong.controller") // 스캔할 패키지
@ComponentScan("kr.co.hallabong.config") // 스캔할 패키지
@ComponentScan("kr.co.hallabong.dao") // 스캔할 패키지
@ComponentScan("kr.co.hallabong.service") // 스캔할 패키지
@PropertySource("/WEB-INF/properties/db.properties")
@Configuration
@EnableWebMvc // Controller로 등록되어 있는 클래스 셋팅
public class ServletAppContext implements WebMvcConfigurer {
	@Value("${db.classname}")
	private String db_classname;

	@Value("${db.url}")
	private String db_url;

	@Value("${db.username}")
	private String db_username;

	@Value("${db.password}")
	private String db_password;

	@Autowired
	private AdminBean adminBean;

	@Resource(name = "loginCustBean")
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
	public SqlSessionFactory factory(BasicDataSource source) throws Exception {

		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(source);

		SqlSessionFactory factory = factoryBean.getObject();
		return factory;
	}

	// properties와 충돌되어 오류가 발생 되므로 분리하여 등록
	@Bean
	public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	/*
	 * 컴파일시 올바른 입력도 유효성에 걸림 이유 enctype="multipart/form-data"에서 정보를 받으면 일반적인 방법이 아니므로
	 * contentBean의
	 * 
	 * @NotBlank private String content_subject;
	 * 
	 * @NotBlank private String content_text;에서 받지못함 그러므로 Bean을 정의
	 */

	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);

		AdminInterceptor admin = new AdminInterceptor(adminBean);
		InterceptorRegistration reg = registry.addInterceptor(admin);
		reg.addPathPatterns("/admin/**");
		reg.excludePathPatterns("/admin", "/admin/login_proc");

		// 메뉴 , 로그인 세션정보
		TopMenuInterceptor topMenuInterceptor = new TopMenuInterceptor(loginCustBean);
		InterceptorRegistration reg1 = registry.addInterceptor(topMenuInterceptor);
		reg1.addPathPatterns("/**"); // 모든 요청에 대하여 작동

		// 로그인 처리
		CheckLoginInterceptor checkLoginInterceptor = new CheckLoginInterceptor(loginCustBean);
		InterceptorRegistration reg2 = registry.addInterceptor(checkLoginInterceptor);
		reg2.addPathPatterns("/user/modify", "/user/logout", "/board/*");
		reg2.excludePathPatterns("/board/main");
	}

	@Bean
	public MapperFactoryBean<ProdMapper> getProdMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<ProdMapper> factoryBean = new MapperFactoryBean<>(ProdMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<CatMapper> getCatMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<CatMapper> factoryBean = new MapperFactoryBean<>(CatMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<NotiMapper> getNotiMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<NotiMapper> factoryBean = new MapperFactoryBean<>(NotiMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<QAMapper> getQAMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<QAMapper> factoryBean = new MapperFactoryBean<>(QAMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<AdminMapper> getAdminMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<AdminMapper> factoryBean = new MapperFactoryBean<>(AdminMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<DlvyMapper> getDlvyMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<DlvyMapper> factoryBean = new MapperFactoryBean<>(DlvyMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<OrdMapper> getOrdMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<OrdMapper> factoryBean = new MapperFactoryBean<>(OrdMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<ODPDMapper> getODPDMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<ODPDMapper> factoryBean = new MapperFactoryBean<>(ODPDMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<CustMapper> getCustMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<CustMapper> factoryBean = new MapperFactoryBean<>(CustMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}
	
	@Bean
    public MapperFactoryBean<CartMapper> getcartMapper(SqlSessionFactory factory) throws Exception{
  	  MapperFactoryBean<CartMapper> factoryBean = new MapperFactoryBean<>(CartMapper.class);
  	  factoryBean.setSqlSessionFactory(factory);
  	  return factoryBean;
    }
	
	@Bean
    public MapperFactoryBean<ProdCatMapper> getProdCatMapper(SqlSessionFactory factory) throws Exception{
  	  MapperFactoryBean<ProdCatMapper> factoryBean = new MapperFactoryBean<>(ProdCatMapper.class);
  	  factoryBean.setSqlSessionFactory(factory);
  	  return factoryBean;
    }

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
		res.setDefaultEncoding("UTF-8");
		res.setBasenames("/WEB-INF/properties/error_message");
		return res;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer PropertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
