package com.isl.admin.commission.action;

import java.math.BigDecimal;

import com.isl.admin.commission.dao.AirlineCommissionBlockDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class AirlineCommissionBlockPlbandIataUpdate extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AirlineCommissionBlockPlbandIataUpdate.class);
	
	BigDecimal plbAmount = null;
	public BigDecimal getPlbAmount() {
		return plbAmount;
	}


	public void setPlbAmount(BigDecimal plbAmount) {
		this.plbAmount = plbAmount;
	}


	public BigDecimal getIataAmount() {
		return iataAmount;
	}


	public void setIataAmount(BigDecimal iataAmount) {
		this.iataAmount = iataAmount;
	}


	public Long getBlockId() {
		return blockId;
	}


	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}


	BigDecimal iataAmount = null;
	Long blockId = null;
	
	
	public String updatePLBandIATACommissions(){	
		try {			
			logger.info("plbAmount-----"+plbAmount);
			logger.info("iataAmount-----"+iataAmount);
			logger.info("blockId-----"+blockId);		
			if(new AirlineCommissionBlockDaoImp().setDefaultCommissions(blockId, iataAmount, plbAmount))
				return SUCCESS;
			else
				return ERROR;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
		
		//http://localhost:8080/TravelAdmin/childcommission?blockId=2&companyId=1&configId=7&iseditable=true
	}




	
	
	
	

}
