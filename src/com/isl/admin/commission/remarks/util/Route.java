package com.isl.admin.commission.remarks.util;

import java.io.Serializable;



public class Route  extends Applicable  implements Serializable
{
	public static final String WAY_ONWARD = "=>";
	public static final String WAY_RETURN = "<=";
	public static final String WAY_TWOWAY = "<=>";
	
	public Route(int type, String name1, String name2, String wayType) {
		super(type, name1);
		this.name2 = name2;
		this.wayType = wayType;
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		StringBuffer includesstr =  new StringBuffer("");
		if(in.size()>0)
		{
			includesstr = new StringBuffer("for {");
			for (int i = 0; i < in.size(); i++) {
				
				if(i==(in.size()-1))
					includesstr.append("("+in.get(i).toString()+")");
				else
					includesstr.append("("+in.get(i).toString()+"), ");
			}			
			includesstr.append("}");
		}
		StringBuffer excludesstr =  new StringBuffer("");
		if(ex.size()>0)
		{
			excludesstr = new StringBuffer("not for {");
			for (int i = 0; i < ex.size(); i++) {
				
				if(i==(ex.size()-1))
					excludesstr.append("("+ex.get(i).toString()+")");
				else
					excludesstr.append("("+ex.get(i).toString()+"), ");
			}			
			excludesstr.append("}");
		}
		
		if(type==Constants.TYPE_ALL)
		{
			return "{All}";
		}
		else if(type==Constants.TYPE_NIL)
		{
			return "{NIL}";
		}
		else
			return "Route " + name +  " "+ wayType+ " "+ name2
				+ " " + includesstr + " " + excludesstr ;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getWayType() {
		return wayType;
	}
	public void setWayType(String wayType) {
		this.wayType = wayType;
	}
	String name2;
	String wayType;
}
