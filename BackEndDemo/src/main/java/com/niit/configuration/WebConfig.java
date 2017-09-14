package com.niit.configuration;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages="com.niit")
//it is as like dispatcher file
public class WebConfig extends  WebMvcConfigurerAdapter {
	public void AddResourceHandler(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
		
	}
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10971520); // 10MB
		
		return multipartResolver;
	}

}
