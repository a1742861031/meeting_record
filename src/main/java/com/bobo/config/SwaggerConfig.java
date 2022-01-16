package com.bobo.config;

import org.assertj.core.util.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @Description swagger配置类
 * @Date 2022/1/12 13:13
 * @Created by bobo
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name("Authorization").description("token").modelRef(new ModelRef("string"))
                .parameterType("header").build();
        ArrayList<Parameter> list = Lists.newArrayList();
        list.add(parameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bobo.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("会议系统1.0")
                        .description("基于SpringBoot的会议系统")
                        .version("1.0")
                        .contact(new Contact(
                                "yanbo",
                                "localhost:8080",
                                "2460417845@qq.com"))
                        .license("The Apache License")
                        .build()
                ).globalOperationParameters(list);
    }

}
