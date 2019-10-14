/**
 * 
 */
package com.construction.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuring application related utility stuff.
 * 
 * @author Milivoje Nesic
 *
 */
@Configuration
public class AppConfig {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
