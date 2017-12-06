package com.isl.admin.commission.remarks.util;

import java.io.Serializable;
import java.util.ArrayList;


public class Applicable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Applicable(int type, String name) {
		super();
		this.type = type;
		this.name = name;
		this.in = new ArrayList<Applicable>();
		this.ex = new ArrayList<Applicable>();
	}
	public static final int ALL = 1;
	public static final int NIL = -1;
	public static final int PARTIAL = 0;


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
			//return "";
		}
		else if(type==Constants.TYPE_NIL)
		{
			return "{NIL}";
			//return "";
		}
	/*	else if(type.equalsIgnoreCase(Constants.TYPE_AIRLINE))
			return type + "=" + name
					+ " " + includesstr + " " + excludesstr ;*/
		else
			return type + "=" + name
				+ " " + includesstr + " " + excludesstr ;
	}
	public boolean isApplicable(ArrayList<Applicable> applicableChecks) {		

		boolean isApplicable = false;

		/*for (Applicable applicable : includes) {
			includesstr.append("("+applicable.toString()+")");
		}

		StringBuffer excludesstr = new StringBuffer("{");
		for (Applicable applicable : excludes) {
			excludesstr.append("("+applicable.toString()+")");
		}*/

		return isApplicable;

	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Applicable> getIn() {
		return in;
	}

	public ArrayList<Applicable> getEx() {
		return ex;
	}

	public void addExcludes(Applicable exclude) {
		ex.add(exclude);
	}
	public void addIncludes(Applicable include) {
		in.add(include);
	}

	int type;
	String name;
	final ArrayList<Applicable> in;
	final ArrayList<Applicable> ex;		


}
