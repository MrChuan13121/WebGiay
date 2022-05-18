package com.ntc.webgiay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        Path brandUploadDir = Paths.get("./src/main/resources/static/img/brand/");
        String brandUploadPath = brandUploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/src/main/resources/static/img/brand/**").addResourceLocations("file:/"+ brandUploadPath + "/");
    }
}
