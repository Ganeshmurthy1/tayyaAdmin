package com.common.dsr.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;

public class MergeOrderRowsUtility {
	public List<FlightOrderRow> groupFlightRecords(List<FlightOrderRow> toList) {

		Map<String, FlightOrderRow> uniqueMap = new HashMap<String, FlightOrderRow>();
		List<FlightOrderRow> listnew=new ArrayList<>();
		for(FlightOrderRow to : toList) {
			if (uniqueMap.containsKey(to.getOrderId())) {
				if (uniqueMap.get(to.getOrderId()).equals(to.getOrderId())) {
					uniqueMap.put(to.getOrderId(), to);
				}
			} else {
				uniqueMap.put(to.getOrderId(), to);
			}
		}
		for(String keys:uniqueMap.keySet()){
			FlightOrderRow value = uniqueMap.get(keys);  
			listnew.add(value);
		}
		return listnew;
	}

	public List<HotelOrderRow> groupRecordsForHotel(List<HotelOrderRow> toList) {

		Map<String, HotelOrderRow> uniqueMap = new HashMap<String, HotelOrderRow>();
		List<HotelOrderRow> listnew=new ArrayList<>();
		for(HotelOrderRow to : toList) {
			if (uniqueMap.containsKey(to.getOrderReference())) {
				if (uniqueMap.get(to.getOrderReference()).equals(to.getOrderReference())) {
					uniqueMap.put(to.getOrderReference(), to);
				}
			} else {
				uniqueMap.put(to.getOrderReference(), to);
			}
		}
		for(String keys:uniqueMap.keySet()){
			HotelOrderRow value = uniqueMap.get(keys);  
			listnew.add(value);
		}
		return listnew;
	}

	public List<CarOrderRow> groupRecordsForCar(List<CarOrderRow> toList) {

		Map<String, CarOrderRow> uniqueMap = new HashMap<String, CarOrderRow>();
		List<CarOrderRow> listnew=new ArrayList<>();
		for(CarOrderRow to : toList) {
			if (uniqueMap.containsKey(to.getOrderId())) {
				if (uniqueMap.get(to.getOrderId()).equals(to.getOrderId())) {
					uniqueMap.put(to.getOrderId(), to);
				}
			} else {
				uniqueMap.put(to.getOrderId(), to);
			}
		}
		for(String keys:uniqueMap.keySet()){
			CarOrderRow value = uniqueMap.get(keys);  
			listnew.add(value);
		}
		return listnew;
	}

	public List<BusOrderRow> groupRecordsForBus(List<BusOrderRow> toList) {

		Map<String, BusOrderRow> uniqueMap = new HashMap<String, BusOrderRow>();
		List<BusOrderRow> listnew=new ArrayList<>();
		for(BusOrderRow to : toList) {
			if (uniqueMap.containsKey(to.getOrderId())) {
				if (uniqueMap.get(to.getOrderId()).equals(to.getOrderId())) {
					uniqueMap.put(to.getOrderId(), to);
				}
			} else {
				uniqueMap.put(to.getOrderId(), to);
			}
		}
		for(String keys:uniqueMap.keySet()){
			BusOrderRow value = uniqueMap.get(keys);  
			listnew.add(value);
		}
		return listnew;
	}

	public List<TrainOrderRow> groupRecordsForTrain(List<TrainOrderRow> toList) {

		Map<String, TrainOrderRow> uniqueMap = new HashMap<String, TrainOrderRow>();
		List<TrainOrderRow> listnew=new ArrayList<>();
		for(TrainOrderRow to : toList) {
			if (uniqueMap.containsKey(to.getOrderId())) {
				if (uniqueMap.get(to.getOrderId()).equals(to.getOrderId())) {
					uniqueMap.put(to.getOrderId(), to);
				}
			} else {
				uniqueMap.put(to.getOrderId(), to);
			}
		}
		for(String keys:uniqueMap.keySet()){
			TrainOrderRow value = uniqueMap.get(keys);  
			listnew.add(value);
		}
		return listnew;
	}

	public List<VisaOrderRow> groupRecordsForVisa(List<VisaOrderRow> toList) {

		Map<String, VisaOrderRow> uniqueMap = new HashMap<String, VisaOrderRow>();
		List<VisaOrderRow> listnew=new ArrayList<>();
		for(VisaOrderRow to : toList) {
			if (uniqueMap.containsKey(to.getOrderId())) {
				if (uniqueMap.get(to.getOrderId()).equals(to.getOrderId())) {
					uniqueMap.put(to.getOrderId(), to);
				}
			} else {
				uniqueMap.put(to.getOrderId(), to);
			}
		}
		for(String keys:uniqueMap.keySet()){
			VisaOrderRow value = uniqueMap.get(keys);  
			listnew.add(value);
		}
		return listnew;
	}

	public List<InsuranceOrderRow> groupRecordsForInsurance(List<InsuranceOrderRow> toList) {

		Map<String, InsuranceOrderRow> uniqueMap = new HashMap<String, InsuranceOrderRow>();
		List<InsuranceOrderRow> listnew=new ArrayList<>();
		for(InsuranceOrderRow to : toList) {
			if (uniqueMap.containsKey(to.getOrderId())) {
				if (uniqueMap.get(to.getOrderId()).equals(to.getOrderId())) {
					uniqueMap.put(to.getOrderId(), to);
				}
			} else {
				uniqueMap.put(to.getOrderId(), to);
			}
		}
		for(String keys:uniqueMap.keySet()){
			InsuranceOrderRow value = uniqueMap.get(keys);  
			listnew.add(value);
		}
		return listnew;
	}

	public List<MiscellaneousOrderRow> groupRecordsForMisc(List<MiscellaneousOrderRow> toList) {
		Map<String, MiscellaneousOrderRow> uniqueMap = new HashMap<String, MiscellaneousOrderRow>();
		List<MiscellaneousOrderRow> listnew=new ArrayList<>();
		for(MiscellaneousOrderRow to : toList) {
			if (uniqueMap.containsKey(to.getOrderId())) {
				if (uniqueMap.get(to.getOrderId()).equals(to.getOrderId())) {
					uniqueMap.put(to.getOrderId(), to);
				}
			} else {
				uniqueMap.put(to.getOrderId(), to);
			}
		}
		for(String keys:uniqueMap.keySet()){
			MiscellaneousOrderRow value = uniqueMap.get(keys);  
			listnew.add(value);
		}
		return listnew;
	}
	 
}
