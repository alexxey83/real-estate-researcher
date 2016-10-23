package com.gear.testSpringMVC.comparators;

import java.util.Comparator;

import com.gear.testSpringMVC.Constants;
import com.gear.testSpringMVC.Entities.Advertisement;
import com.gear.testSpringMVC.Entities.SubwayStations;

public final class NovocherkasskayaSubwayStationComparator implements Comparator<Advertisement> {

	@Override
	public int compare(Advertisement a1, Advertisement a2) {
		if ((!a1.isOnFoot() && a1.getTimeToSubwayStation() > 10) ^ (!a2.isOnFoot() && a2.getTimeToSubwayStation() > 10)) {
			return !a1.isOnFoot() && a1.getTimeToSubwayStation() > 10 ? 1 : -1;
		}
		
		int additionalCost1 = calculateAdditionalCost(a1);
		int additionalCost2 = calculateAdditionalCost(a2);

		double costPerArea1 = (a1.getPrice() + additionalCost1) / a1.getArea();
		double costPerArea2 = (a2.getPrice() + additionalCost2) / a2.getArea();
		
		if (costPerArea1 == costPerArea2) {
			return 0;
		} else if (costPerArea1 < costPerArea2) {
			return -1;
		} else {
			return  1;
		}
	}

	private int calculateAdditionalCost(Advertisement a) {
		SubwayStations ss = a.getSubwayStation();
		int subwayTimeToWork = ss.TimeToWork;
		int additionalCost = subwayTimeToWork == 0 ? 0 : Constants.SubwayFare;
		
		if (!a.isOnFoot()) {
			additionalCost += Constants.GroundTransportFare;
		}
		
		int totalTimeToWork = subwayTimeToWork + a.getTimeToSubwayStation();
		if (totalTimeToWork > 10 && totalTimeToWork < 20) {
			additionalCost += (ss.isPrefer ? 1000 : 2000);
		} else if (totalTimeToWork < 30) {
			additionalCost += (ss.isPrefer ? 2000 : 4000);
		} else if (totalTimeToWork < 40) {
			additionalCost += (ss.isPrefer ? 4000 : 8000);
		} else if (totalTimeToWork < 50) {
			additionalCost += (ss.isPrefer ? 8000 : 16000);
		} else if (totalTimeToWork < 60) {
			additionalCost += (ss.isPrefer ? 16000 : 32000);
		} else {
			additionalCost += (ss.isPrefer ? 30000 : 50000);
		}
		
		return additionalCost;
	}
}
