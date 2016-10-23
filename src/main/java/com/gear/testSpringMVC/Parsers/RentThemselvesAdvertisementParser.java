package com.gear.testSpringMVC.Parsers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import com.gear.testSpringMVC.Entities.Advertisement;
import com.gear.testSpringMVC.Entities.SubwayStations;

public class RentThemselvesAdvertisementParser {
	
	private static final Pattern pricePattern = Pattern.compile("([0-9]+ [0-9]+)");
	private static final Pattern headerPattern = Pattern.compile("[\\s\\S]*,\\s([0-9]+(\\.[0-9])?)\\sм2,\\sст\\.\\sм\\.\\s([\\s\\S]*)", Pattern.UNICODE_CHARACTER_CLASS);
	private static final Pattern timeToSubwayStationPattern = Pattern.compile("([0-9]+)[\\s\\S]+(пешком|на транспорте)", Pattern.UNICODE_CHARACTER_CLASS);
	private static final Pattern datePattern = Pattern.compile("([0-2][0-9]|3[0-1])\\.(0[0-9]|1[0-2])\\.[0-9]{1,4}", Pattern.UNICODE_CHARACTER_CLASS);
	private static final String subwayIconUrl = "/images/icons/6.jpg";

	public Advertisement parse(Element el) {
		Advertisement advertisment = new Advertisement();
		Element priceEl = el.getElementsByClass("price_object").get(0);
		parsePrice(priceEl.text(), advertisment);
		Element headerElement = el.getElementsByTag("h3").get(0);
		parseHeader(headerElement, advertisment);
		Elements subwayIconElements = el.getElementsByAttributeValueContaining("src", subwayIconUrl);
		if (subwayIconElements.size() > 0) {
			parseTimeToSubwayStation(subwayIconElements.get(0).parent().text(), advertisment);
		}
		Element dateElement = el.getElementsByClass("objectdate").get(0);
		parseDate(dateElement.text(), advertisment);
		if (!advertisment.isOnFoot()) {
			advertisment.setTimeToSubwayStation(2 * (advertisment.getTimeToSubwayStation() == null ? 0 : advertisment.getTimeToSubwayStation()));
		}
		
		Element totalTimeToWorkElement = new Element(Tag.valueOf("p"), "");
		int timeToSubwayStation = advertisment.getTimeToSubwayStation() == null ? 0 : advertisment.getTimeToSubwayStation();
		totalTimeToWorkElement.text("Время до работы: " + (timeToSubwayStation + advertisment.getSubwayStation().TimeToWork));
		el.getElementsByClass("info_object").get(0).appendChild(totalTimeToWorkElement);
		advertisment.setHtml(el.outerHtml());
		
		return advertisment;
	}
	
	private void parsePrice(String priceText, Advertisement advertisment) {
		Matcher matcher = pricePattern.matcher(priceText);
		if (matcher.find()) {
			String priceValue = matcher.group();
			advertisment.setPrice(Integer.valueOf(priceValue. replace(" ", "")));
		}
	}
	
	private void parseHeader(Element headerElement, Advertisement advertisment) {
		Element linkElement = headerElement.getElementsByTag("a").get(0);
		advertisment.setUrl(linkElement.absUrl("href"));
		Matcher matcher = headerPattern.matcher(headerElement.text());
		if (matcher.find()) {
			String areaValue = matcher.group(1);
			advertisment.setArea(Float.valueOf(areaValue));
			advertisment.setSubwayStation(SubwayStations.valueByName(matcher.group(3)));
		}
	}
	
	private void parseTimeToSubwayStation(String timeToSubwayStation, Advertisement advertisment) {
		Matcher matcher = timeToSubwayStationPattern.matcher(timeToSubwayStation);
		if (matcher.find()) {
			String timeToSubwayStationValue = matcher.group(1);
			advertisment.setTimeToSubwayStation(Integer.valueOf(timeToSubwayStationValue.replace(" ", "")));
			advertisment.setOnFoot("пешком".equals(matcher.group(2)));
		}
	}
	
	private void parseDate(String date, Advertisement advertisment) {
		if ("Вчера".equals(date)) {
			LocalDate ld = LocalDate.now(ZoneId.of("Europe/Samara"));
			ld.minusDays(1);
			advertisment.setDate(ld.toString());
			return;
		}
		
		Matcher matcher = datePattern.matcher(date);
		if (matcher.find()) {
			advertisment.setDate(date);
		}
	}
}
