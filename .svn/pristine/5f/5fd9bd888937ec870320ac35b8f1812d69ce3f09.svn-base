package com.admin.enums.utility;
public enum IndianUnionTerritories {

	DEFAULT (0, "Default",""),
	Chandigarh (1, "Chandigarh","04"),
	Delhi(2, "Delhi","07"),
	Daman_and_Diu(3, "Daman and Diu","25"),
	Dadra_and_Nagar_Haveli(4, "Dadra and Nagar Haveli","26"),
	Lakshadweep(5, "Lakshadweep","31"),
	Puducherry(6, "Puducherry","34"),
	Andaman_and_Nicobar_Islands(7, "Andaman and Nicobar Islands","35");
	private final Integer code;
	private final String stateName;
	private final String stateCode;

	IndianUnionTerritories(int code,String sname,String scode){
		this.code = code;
		this.stateCode = scode;
		this.stateName = sname;
	}
	public static boolean isUnionter(String stateName){
		boolean isUT = false;
		if(stateName.equalsIgnoreCase(""))
			return false;
		for (IndianUnionTerritories s : IndianUnionTerritories.values())
		{
			if(s.getStateName().equalsIgnoreCase(stateName))
			{
				isUT = true;
			}
		}
		return isUT;
	}
	public static IndianUnionTerritories getStateEnum(Integer statusCode){
		IndianUnionTerritories StateEnum = DEFAULT;
		if(statusCode == null)
			return DEFAULT;
		for (IndianUnionTerritories s : IndianUnionTerritories.values())
		{
			if(s.getCode() == statusCode)
			{
				StateEnum = s;
			}
		}
		return StateEnum;
	}
	public static String getStateName(String  stateName){
		String statename ="";
		for (IndianUnionTerritories s : IndianUnionTerritories.values())
		{
			if(s.getStateName().equalsIgnoreCase(stateName))
			{
				statename = stateName;
			}
		}
		return statename;
	}
	public Integer getCode() {
		return code;
	}
	public String getStateName() {
		return stateName;
	}
	public String getStateCode() {
		return stateCode;
	}
	 
}
