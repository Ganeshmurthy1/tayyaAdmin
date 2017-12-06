package com.isl.admin.commission.remarks.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;



public class PLBCommission implements Serializable{
	public PLBCommission(String datestart, String dateend, BigDecimal amount) {
		super();
		this.datestart = datestart;
		this.dateend = dateend;
		this.amt = amount;
		this.appliedon = new ArrayList<String>();
		this.in = new ArrayList<Applicable>();
		this.ex = new ArrayList<Applicable>();
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
		StringBuffer appliedonstr =  new StringBuffer("");
		if(appliedon.size()>0)
		{
			appliedonstr = new StringBuffer(" of {");
			
			for (int i = 0; i < appliedon.size(); i++) {
				
				if(i==(appliedon.size()-1))
					appliedonstr.append(appliedon.get(i));
				else
					appliedonstr.append(appliedon.get(i)+",");
			}	
			
			appliedonstr.append("}");
		}		
		
		return "PLBCommission \n datestart=" + datestart + ", \n dateend="
				+ dateend + ", \n amount=" + amt + " % "
				+ appliedonstr + " \n " + includesstr + " \n"
				+ excludesstr + "";
	}
	public String getDatestart() {
		return datestart;
	}
	public void setDatestart(String datestart) {
		this.datestart = datestart;
	}
	public String getDateend() {
		return dateend;
	}
	public void setDateend(String dateend) {
		this.dateend = dateend;
	}
	public BigDecimal getAmount() {
		return amt;
	}
	public void setAmount(BigDecimal amount) {
		this.amt = amount;
	}
	public ArrayList<String> getAppliedon() {
		return appliedon;
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
	public void addAppliedOn(String appliedOn) {
		appliedon.add(appliedOn);
	}
	
	String datestart;
	String dateend;
	BigDecimal amt;
	final ArrayList<String> appliedon;
	final ArrayList<Applicable> in;
	final ArrayList<Applicable> ex;		
}
