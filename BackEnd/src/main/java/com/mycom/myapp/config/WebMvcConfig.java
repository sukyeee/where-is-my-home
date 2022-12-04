package com.mycom.myapp.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mycom.myapp.common.LoginInterceptor;


// 즉슨, xml 을 대체하는 녀셕이다.
// 러프하게 해당 클래스가 servlet-context.xml 의 설정 내용을 대체한다.
// interceptor 외에 다른 많은 설정이 가능하다.
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	// interceptor 에 대한 정책
	// interceptor 객체 DI

	@Autowired
	private LoginInterceptor loginInterceptor;


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
		    .addInterceptor(loginInterceptor)
		    .addPathPatterns("/**")
		    .excludePathPatterns("/", "/index.html", "/favicon.ico")
		    .excludePathPatterns("/login/**", "/register/**", "/css/**", "/js/**", "/img/**")
		    .excludePathPatterns("/public/**")
		    .excludePathPatterns("/houses/**", "/reviews/**")
		    .excludePathPatterns("/houses/news");
	}
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5500")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "OPTIONS")
                .allowedHeaders("headers")
                .allowCredentials(true)
                .maxAge(3000);
    }
}

/// *
// interface 는 추상 메서드만으로 구성
// 바디를 가지는 default method 를 가질 수 없었다.
// interface 에 가령 20개의 추상 메서드가 있으면, 그걸 실제로 implements 하는 클래스는 딱 2개만 구현하고 싶어도
// 나머지 18개를 빈 body 로 모두 구현해야했다.
// 해당 문제가 불편해지니까, api 에서 자주 사용되는 interface 에 대해서는 클래스로 (...Adaptor 이런 형태로) 제공
// 이러한 ...Adaptor 클래스는 interface의 20개의 메서드를 모두 구현 한다.
// A interface 를 implements 하는 것이 아니고, extends AAdaptor 해서 2개만 오버라이딩 하면 되도록
// AAdaptor 는 다음과 같이 구성되어 있을 것이다. => AAdaptor implements A
// */