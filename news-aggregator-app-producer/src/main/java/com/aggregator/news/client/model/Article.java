package com.aggregator.news.client.model;

import lombok.Data;

public @Data class Article {

	private Source source;
	private String author;
	private String title;
	private String description;
	private String url;
	private String urlToImage;
	private String publishedAt;

}
