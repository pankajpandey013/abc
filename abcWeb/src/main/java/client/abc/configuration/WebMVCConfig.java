package client.abc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;



@Configuration
@EnableWebMvc
public class WebMVCConfig implements WebMvcConfigurer {
	
	@Bean
    public ClassLoaderTemplateResolver templateResolver() {

		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        templateResolver.setPrefix("templates/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");

        return templateResolver;
    }

   @Bean
   public SpringTemplateEngine templateEngine() {
       SpringTemplateEngine templateEngine = new SpringTemplateEngine();
       templateEngine.setTemplateResolver(templateResolver());
       //templateEngine.setTemplateEngineMessageSource(messageSource());
       return templateEngine;
   }

   @Bean
   public ViewResolver viewResolver() {
	   ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();   
       viewResolver.setTemplateEngine(templateEngine());
       viewResolver.setCharacterEncoding("UTF-8");
       return viewResolver;
    }

}