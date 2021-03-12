package internet_store.application.web_ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"internet_store.application"})
public class SpringWebConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/icons/**")
                .addResourceLocations("classpath:/static/images/icons/");
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resourceViewResolver = new SpringResourceTemplateResolver();
        resourceViewResolver.setPrefix("classpath:/templates/");
        resourceViewResolver.setSuffix(".html");
        resourceViewResolver.setTemplateMode(TemplateMode.HTML);
        resourceViewResolver.setCharacterEncoding("UTF-8");
        resourceViewResolver.setCheckExistence(false);
        return resourceViewResolver;
    }

}