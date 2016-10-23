package com.gear.testSpringMVC.Entities;

public class Advertisement {
	private Float area;
	private Integer timeToSubwayStation;
	private Integer price;
	private String url;
	private String html;
	private String date;
	private SubwayStations subwayStation;
	private boolean onFoot = false;
	
	public Float getArea() {
		return area;
	}
	
	public void setArea(Float area) {
		this.area = area;
	}
	
	public Integer getTimeToSubwayStation() {
		return timeToSubwayStation;
	}
	
	public void setTimeToSubwayStation(Integer timeToSubwayStation) {
		this.timeToSubwayStation = timeToSubwayStation;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getHtml() {
		return html;
	}
	
	public void setHtml(String html) {
		this.html = html;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public SubwayStations getSubwayStation() {
		return subwayStation;
	}

	public void setSubwayStation(SubwayStations subwayStation) {
		this.subwayStation = subwayStation;
	}

	public boolean isOnFoot() {
		return onFoot;
	}

	public void setOnFoot(boolean onFoot) {
		this.onFoot = onFoot;
	}
}
