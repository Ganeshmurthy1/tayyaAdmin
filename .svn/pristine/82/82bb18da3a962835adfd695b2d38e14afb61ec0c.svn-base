package com.isl.admin.commission.dao;
import java.util.List;

import org.hibernate.HibernateException;

import com.isl.admin.commission.model.AirlineCommissionSheet;
import com.isl.admin.commission.model.CommissionActionStatus;
import com.isl.admin.commission.model.AirlineCommissionMasterSheet;

public interface AirlineCommissionSheetDao {
	public AirlineCommissionMasterSheet getSuperUserAirlineCommissionSheet(Long sheetId);
	 public AirlineCommissionMasterSheet saveSuperUserSheetDetails(AirlineCommissionMasterSheet sheet); 
	 public List<AirlineCommissionMasterSheet> getSuperUserSheetList();
	public List<AirlineCommissionSheet> getAirlineCommissionSheet(Long sheetId) throws HibernateException;
	public CommissionActionStatus duplicateMasterSheetItems(Long sheetIdBackUp, int superUserCompanyId, AirlineCommissionMasterSheet superUserDealSheet)
					throws HibernateException;
	public AirlineCommissionSheet getAirlineCommissionSheetCommons(
			Long sheetId) throws HibernateException;
	public AirlineCommissionSheet getAirlineCommissionSheetRemark(
			Long sheetId, String iataCode, boolean isPlbRemark) throws HibernateException;
	public AirlineCommissionSheet getAirlineCommissionSheetComplete(
			Long sheetId, String iataCode) throws HibernateException;
	public List<AirlineCommissionSheet> updateAirlineCommissionSheet(List<AirlineCommissionSheet> airlineCommissionSheetRows) throws HibernateException;
	public List<AirlineCommissionSheet> updateAirlineCommissionSheetDates(String dtValidFromStr, String dtValidTillStr,Long sheetId) throws HibernateException;	
	public AirlineCommissionMasterSheet getDealSheetDetails(Long sheetId);
	public AirlineCommissionMasterSheet updateDealSheet(AirlineCommissionMasterSheet commissionSheet);
	public List<AirlineCommissionSheet> getAirlineCommissionSheetRows(
			Long sheetId) throws HibernateException;
	public List<AirlineCommissionMasterSheet> UpdateDealSheetStatus();
}
