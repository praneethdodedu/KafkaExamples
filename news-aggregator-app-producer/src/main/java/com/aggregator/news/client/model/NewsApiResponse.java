package com.aggregator.news.client.model;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Configuration
public @Data class NewsApiResponse {

	private String status;
	private Long totalResults;
	private List<Article> articles;
	
	@Bean
	public RestTemplate geRestTemplate() {
		return new RestTemplate();
	}

}
