package com.isl.admin.commission.remarks.util;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class test {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(test.class);
	

	
	public static void main(String[] args) throws JsonMappingException, IOException {
		// TODO Auto-generated method stub

		//[base+yq]?[{sector:(<gulf>,>all<)}]
		
		//[base]?[{sector:(<gulf>,>all<)}?{country:(<in>,>all<)}]
		
		//[base]?[!sector]
		
		PLBCommission plbCommission = new PLBCommission("22-02-2016", "28-02-2016", new BigDecimal(4));
		//Economy applicable
		Applicable economy = new Applicable(Constants.TYPE_CLASS, "Economy");
		//Applicable allinclude = new Applicable(Constants.TYPE_ALL, Constants.TYPE_ALL);
		//Applicable nilexclude = new Applicable(Constants.TYPE_NIL, Constants.TYPE_NIL);
		//economy.addExcludes(nilexclude);
		//economy.addIncludes(allinclude);
		
		
		Applicable bussiness = new Applicable(Constants.TYPE_CLASS, "Business");
		
		//bussiness.addExcludes(nilexclude);
		
		
		Applicable sectorApplicable = new Applicable(Constants.TYPE_SECTOR, "GULF");
		
		
		Applicable countryApplicable = new Applicable(Constants.TYPE_COUNTRY, "Abu dabi");	
		Applicable countryApplicable2 = new Applicable(Constants.TYPE_COUNTRY, "saudi");	
		Applicable countryApplicable3 = new Applicable(Constants.TYPE_COUNTRY, "oman");	
		//countryApplicable.addExcludes(nilexclude);
		//countryApplicable.addIncludes(allinclude);		
		
		Applicable sodoapplicable = new Applicable(Constants.TYPE_SOTO, "soto");		
		Route routeapplicable = new Route(Constants.TYPE_SOTO, "RIT", "GUI", Route.WAY_TWOWAY);
		Applicable codeshare = new Applicable(Constants.TYPE_CODE_SHARE, "codeshare");
		Applicable airline1 = new Applicable(Constants.TYPE_AIRLINE, "AIR");
		Applicable airline2 = new Applicable(Constants.TYPE_AIRLINE, "AIF");
		codeshare.addIncludes(airline1);
		codeshare.addIncludes(airline2);
		sodoapplicable.addIncludes(codeshare);		
		sodoapplicable.addExcludes(routeapplicable);
		
		
		//sodoapplicable.addIncludes(allinclude);		
		//sodoapplicable.addExcludes(routeapplicable);
		
		
		
		
		sectorApplicable.addIncludes(sodoapplicable);	
		sectorApplicable.addExcludes(countryApplicable);
		sectorApplicable.addExcludes(countryApplicable2);
		sectorApplicable.addExcludes(countryApplicable3);
		
		bussiness.addIncludes(sectorApplicable);		
		//bussiness.addExcludes(nilexclude);
		
		
		Fare fareapplicable = new Fare(Constants.TYPE_FARE_CORPORATE_MSG);		
		Applicable infantapplicable = new Applicable(Constants.TYPE_PASSENGER, Constants.TYPE_PASSENGER_INFANT_MSG);
		fareapplicable.addExcludes(infantapplicable);
		economy.addIncludes(fareapplicable);
		Applicable tourcode = new Applicable(Constants.TYPE_TOUR, "DFDFf56656");
		tourcode.addExcludes(fareapplicable);
		
		economy.addIncludes(tourcode);
		
		
		plbCommission.addIncludes(economy);
		plbCommission.addIncludes(bussiness);		
		//plbCommission.addExcludes(nilexclude);		
		plbCommission.addAppliedOn("BASE");
		plbCommission.addAppliedOn("YQ");
		logger.info("PLB commission--"+plbCommission.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		
		String plbcommissionJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(plbCommission);
		//logger.info("PLB commission--"+plbcommissionJson);


		//Object to JSON in String
		
	}	

	
	
}
