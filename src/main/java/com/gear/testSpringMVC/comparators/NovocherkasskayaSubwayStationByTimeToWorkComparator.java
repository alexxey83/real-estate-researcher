package com.gear.testSpringMVC.comparators;

import java.util.Comparator;

import com.gear.testSpringMVC.Constants;
import com.gear.testSpringMVC.Entities.Advertisement;
import com.gear.testSpringMVC.Entities.SubwayStations;

public final class NovocherkasskayaSubwayStationByTimeToWorkComparator implements Comparator<Advertisement> {

	@Override
	public int compare(Advertisement a1, Advertisement a2) {
		int difference = calculateTime(a1) - calculateTime(a2);
		if (difference != 0) {
			return difference;
		}
		
		if (a1.getSubwayStation().isPrefer && !a2.getSubwayStation().isPrefer) {
			return -1;
		}
		
		if (!a1.getSubwayStation().isPrefer && a2.getSubwayStation().isPrefer) {
			return 1;
		}
		
		double costPerArea1 = calculateCostPerArea(a1);
		double costPerArea2 = calculateCostPerArea(a2);
		if (costPerArea1 == costPerArea2) {
			return 0;
		} else if (costPerArea1 < costPerArea2) {
			return -1;
		} else {
			return  1;
		}
	}

	private int calculateTime(Advertisement a) {
		SubwayStations ss = a.getSubwayStation();
		int subwayTimeToWork = ss.TimeToWork;
		int totalTimeToWork = subwayTimeToWork + a.getTimeToSubwayStation();
		
		return totalTimeToWork;
	}
	
	private double calculateCostPerArea(Advertisement a) {
		int totalCost = a.getPrice();
		
		if (a.getSubwayStation().TimeToWork != 0) {
			totalCost += Constants.SubwayFare;
		}
		
		if (!a.isOnFoot()) {
			totalCost += Constants.GroundTransportFare;
		}
		
		return totalCost / a.getArea();
	}
}
