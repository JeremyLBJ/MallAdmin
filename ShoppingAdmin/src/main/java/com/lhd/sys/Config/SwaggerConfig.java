package com.lhd.sys.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration  //表示当前类为配置类 
@EnableSwagger2  //开启swagger
public class SwaggerConfig {
	

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2) 
				.apiInfo(apiInfo())
				.select()
				//withClassAnnotation //扫描类上的注解  参数是一个	注解的对象 比如  Controller.class 扫描类上带有Controller 生成接口
				//withMethodAnnotation   扫描方法的注解
				//basePackage  扫描包下面的
				.apis(RequestHandlerSelectors.basePackage("com.lhd.sys.API"))
				.build();
	}
	
	//配置swagger信息
	public ApiInfo apiInfo () {
		Contact contact = new Contact("LHD", "https://www.cnblogs.com/lhd1998/", "1447076355@qq.com") ; 
			
		return new ApiInfo(
				"Api文档"
			  , "商城Api文档"
			  , "v1.0"
			  , "www.baidu.com"
			  , contact
			  , "Apache 2.0"
			  , "www.baidu.com"
			 );
	
	}

}
