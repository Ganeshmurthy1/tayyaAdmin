package com.lintas.utility;

import java.util.Comparator;

import com.lintas.admin.model.Airlinelist;
import com.lintas.admin.model.Airport;

public class AirlineComparator  implements Comparator<Object>{
	 
	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		   Airlinelist A1 = (Airlinelist) o1;
		   Airlinelist A2 = (Airlinelist) o2;
		  return  A1.getAirlinename().compareTo(A2.getAirlinename());
	}

	
	public class AirportComparator  implements Comparator<Object>{
		
		@Override
		public int compare(Object o1, Object o2) {
			 Airport A1 = (Airport) o1;
			 Airport A2 = (Airport) o2;
			  return  A1.getAirport_name().compareTo(A2.getAirport_name());
		}
		
		 
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
