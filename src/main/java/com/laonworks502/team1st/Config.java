package com.laonworks502.team1st;

import javax.sql.DataSource;

import com.laonworks502.team1st.interceptor.LoginInterceptor;
import com.laonworks502.team1st.interceptor.NavbarInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan
public class Config implements WebMvcConfigurer {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("static/mapper/**/*.xml")
        );

        /* Alias 설정 */
        factoryBean.setTypeAliasesPackage("com.laonworks502.team1st.model");
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    // 인터셉터 설정
    @Bean
    public NavbarInterceptor navbarInterceptor() {
        return  new NavbarInterceptor();
    }

    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Navbar 게시판 목록 세션
        registry.addInterceptor(navbarInterceptor()).addPathPatterns("/**");

        // 로그인 세션
        registry.addInterceptor(loginInterceptor())
                //.addPathPatterns("/**")
                .excludePathPatterns("/**");
    }
}
