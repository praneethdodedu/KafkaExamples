package com.aggregator.news.constants;

public enum NewsCategory {

	TECHNOLOGY("technology:tech:it industry:science:smartphone:computer:ceo:android:ios:qualcomm:Software"), 
	HEALTHCARE("health:hospital:medication:meditation:pharmacy:blood sugar"), 
	ENTERTAINMENT("entertainment:films:music:movies"), 
	BUSINESS("business:stocks:share market"), 
	SPORTS("sports:games:commonwealth:worldcup:basketball:cricket:football:training"),
	POLITICS("election:trump:israel:political:politics:democratic"),
	INDIA("indian:india:tegulu:tamil:kannada:hindi:new delhi:espn"),
	WORLD("america:usa:north korea:south korea:russia:israel:syria");

	private String category;

	NewsCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

}
