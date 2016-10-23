package com.gear.testSpringMVC.Servces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.gear.testSpringMVC.Entities.Advertisement;
import com.gear.testSpringMVC.Parsers.RentThemselvesAdvertisementParser;
import com.gear.testSpringMVC.comparators.NovocherkasskayaSubwayStationByTimeToWorkComparator;
import com.gear.testSpringMVC.comparators.NovocherkasskayaSubwayStationComparator;

@Service
public class RentThemselvesService {

	private static final String hostName = "http://snimaem-sami.ru";
	private static final String urlPattern = hostName + "/objects/?p=%d&fieldsfilter[price][0]=20000&fieldsfilter[price][1]=40000";
	
	private static final RentThemselvesAdvertisementParser advertisementParser = new RentThemselvesAdvertisementParser();
	
	public String getResourceData() throws IOException {
		TreeSet<Advertisement> advertisements = new TreeSet<Advertisement>(new NovocherkasskayaSubwayStationComparator());
		boolean carryOn = true;
		int i = 0;
		while (carryOn) {
			Document doc = Jsoup.connect(String.format(urlPattern, i)).get();
			ArrayList<Advertisement> advertisementList = proccessDocument(doc);
			advertisements.addAll(advertisementList);
			Advertisement lastAdvertisement = advertisementList.get(advertisementList.size() - 1);
			carryOn = lastAdvertisement.getDate() == null;
			System.out.println("Last date: " + lastAdvertisement.getDate());
			i++;
		}
		
		return toHTML(advertisements);
	}
	
	private ArrayList<Advertisement> proccessDocument(Document doc) {
		Element middleElement = doc.body().getElementById("middle");
		Attributes advertisementsElementAttr = new Attributes();
		advertisementsElementAttr.put("id", "middlet");
		// Element advertisementsElement = new Element(Tag.valueOf("div"),	"",	advertisementsElementAttr);
		Elements advertisementElements = middleElement.getElementsByClass("inlist_object");
		ArrayList<Advertisement> advertisements = new ArrayList<Advertisement>();
		for (Element nextElement : advertisementElements) {
			processAdvertisement(nextElement);
			Advertisement advertisement = advertisementParser.parse(nextElement);
			advertisements.add(advertisement);
		}
		
		return advertisements;
	}
	
	private void processAdvertisement(Element advertisementElement) {
		for (Element nextElement : advertisementElement.getElementsByTag("img")) {
			String imgUrl = nextElement.attr("src");
			nextElement.attr("src", hostName + imgUrl);
		}
		
		for (Element nextElement : advertisementElement.getElementsByTag("a")) {
			String imgUrl = nextElement.attr("href");
			nextElement.attr("href", hostName + imgUrl);
		}
	}
	
	private String toHTML(Collection<Advertisement> advertisements) {
		StringBuilder sb = new StringBuilder();
		for (Advertisement nextAdvertisement : advertisements) {
			sb.append(nextAdvertisement.getHtml());
			sb.append(StringUtils.LF);
		}
		
		return sb.toString();
	}
}
