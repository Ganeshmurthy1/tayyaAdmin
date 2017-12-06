package com.lintas.admin.model;

public class CompanyConfigTax {
	private CompanyConfig gstTaxObj;
	private CompanyConfig serviceTaxObj;
	private CompanyConfig commonDSRreportObj;
	public CompanyConfig getGstTaxObj() {
		return gstTaxObj;
	}
	public void setGstTaxObj(CompanyConfig gstTaxObj) {
		this.gstTaxObj = gstTaxObj;
	}
	public CompanyConfig getServiceTaxObj() {
		return serviceTaxObj;
	}
	public void setServiceTaxObj(CompanyConfig serviceTaxObj) {
		this.serviceTaxObj = serviceTaxObj;
	}
	public CompanyConfig getCommonDSRreportObj() {
		return commonDSRreportObj;
	}
	public void setCommonDSRreportObj(CompanyConfig commonDSRreportObj) {
		this.commonDSRreportObj = commonDSRreportObj;
	}
}
