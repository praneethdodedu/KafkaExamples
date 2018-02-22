package com.aggregator.news.model;

import lombok.Data;

public @Data class News {

	private String newsId;
	private String newsName;
	private String author;
	private String title;
	private String description;
	private String url;
	private String imageUrl;
	private String publishedDate;

	public News(String newsId, String newsName, String author, String title, String description, String url,
			String imageUrl, String publishedDate) {
		super();
		this.newsId = newsId;
		this.newsName = newsName;
		this.author = author;
		this.title = title;
		this.description = description;
		this.url = url;
		this.imageUrl = imageUrl;
		this.publishedDate = publishedDate;
	}

	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", newsName=" + newsName + ", author=" + author + ", title=" + title
				+ ", description=" + description + ", url=" + url + ", imageUrl=" + imageUrl + ", publishedDate="
				+ publishedDate + "]";
	}

	public News() {
	}

}
