package com.foam.gene.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        //视图解析器
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/Tools").setViewName("Tools");
        registry.addViewController("/Resources").setViewName("Resources");
        registry.addViewController("/common").setViewName("common");
        registry.addViewController("/ReferencePanel").setViewName("ReferencePanel");
        registry.addViewController("/Download").setViewName("Download");

        //Tools下的解析
        registry.addViewController("/Tools/PCA").setViewName("/Tools/PCA");
        registry.addViewController("/Tools/Characterizing").setViewName("/Tools/Characterizing");
        registry.addViewController("/Tools/Genetic-breeding").setViewName("/Tools/Genetic-breeding");
        registry.addViewController("/Tools/SNP-Imputation").setViewName("/Tools/SNP-Imputation");

        //help下的解析
        registry.addViewController("/Help/Contact").setViewName("/Tools/SNP-Imputation");
        registry.addViewController("/Help/FAQs").setViewName("/Help/FAQs");
        registry.addViewController("/Help/Tutorial").setViewName("/Help/Tutorial");
        registry.addViewController("/Help/Updates").setViewName("/Help/Updates");

//        registry.addViewController("/login").setViewName("index");
//        registry.addViewController("/test").setViewName("test");
//        registry.addViewController("/main.html").setViewName("data-tables");
    }
}
