package com.psi;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				    .setMatchingStrategy(MatchingStrategies.STRICT)
				    .setFieldMatchingEnabled(true)
				    .setSkipNullEnabled(true);
		return modelMapper;
	}
	
	// 將 HTML Form(POST) + _method=PUT     -> @PutMapping
	// 將 HTML Form(POST) + _method=DELETE  -> @DeleteMapping
	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter();
		return filter;
	}
}
