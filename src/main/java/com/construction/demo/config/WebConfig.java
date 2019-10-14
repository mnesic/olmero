/**
 * 
 */
package com.construction.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Milivoje Nesic
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**").allowedMethods("*").allowedOrigins("http://stage.marketplace.cerebroplatform.com",
//				"http://marketplace.cerebroplatform.com", "https://stage.marketplace.cerebroplatform.com",
//				"https://marketplace.cerebroplatform.com");
//	}

}
