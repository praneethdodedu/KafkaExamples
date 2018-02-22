package com.aggregator.news.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.aggregator.news.client.model.News;
import com.aggregator.news.client.model.NewsApiResponse;
import com.aggregator.news.producer.NewsProducer;

@Component
public class NewsAggregator {

	@Value("${news.api.url}")
	public String NEWS_API_URL;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private NewsProducer newsProducer;

	public void sendNewsToTopic() {
		try {
			for (int pageNumber = 1; pageNumber <= 1000; pageNumber++) {
				System.out.println(NEWS_API_URL + pageNumber);
				List<News> newsList = getAllNews(pageNumber);
				newsList.forEach(news -> {
					newsProducer.send(news);
				});
			}
		} catch (Exception exception) {
			System.out.println("exception::::::::::::  " + exception);
		}
	}

	public List<News> getAllNews(int pageNumber) throws Exception {
		NewsApiResponse newsApiResponse = restTemplate.getForObject(NEWS_API_URL + pageNumber, NewsApiResponse.class);
		List<News> newsList = new LinkedList<News>();
		newsApiResponse.getArticles().forEach(article -> {
			try {
				News news = new News(article.getSource().getId(), article.getSource().getName(), article.getAuthor(),
						article.getTitle(), article.getDescription(), article.getUrl(), article.getUrlToImage(),
						parseDate(article.getPublishedAt()));
				newsList.add(news);
			} catch (Exception exception) {
				System.out.println(exception);
			}
		});
		return newsList;
	}

	public Date parseDate(String dateInString) throws Exception {
		try {
			if (dateInString != null) {
				dateInString = dateInString.replaceAll("T", "-").replaceAll("Z", "");
				return new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse(dateInString);
			}
			return null;
		} catch (ParseException exception) {
			throw new Exception("Error while parsing date", exception);
		}
	}
}