package com.isl.admin.commission.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;


import com.isl.admin.commission.model.AirlineCommissionBlock;
import com.isl.admin.commission.model.AirlineCommissionCompanyBlock;

public interface AirlineCommissionBlockDao {
	public List<AirlineCommissionBlock> getAirlineCommissionBlock(Integer parentCompanyId,Integer childCompanyId) throws HibernateException;
	public Map<Integer, AirlineCommissionBlock> getAirlineCommissionBlockMap(
			 Integer childCompanyId, String iataCode)
					throws HibernateException;
	public List<AirlineCommissionBlock> insertAirlineCommissionBlock(List<AirlineCommissionBlock> airlineCommissionBlockRows) throws HibernateException;
	public List<AirlineCommissionBlock> createAirlineCommissionBlock(
			 Integer parentCompanyId, Integer childCompanyId, Long sheetId)
					throws HibernateException;
	public List<AirlineCommissionBlock> updateAirlineCommissionBlock(List<AirlineCommissionBlock> airlineCommissionBlockRows) throws HibernateException;	
	public AirlineCommissionCompanyBlock createCompanyDealSheetBlock(AirlineCommissionCompanyBlock commissionBlockSheet) throws HibernateException;	
	public AirlineCommissionCompanyBlock createCompanyCommissionBlock(AirlineCommissionCompanyBlock airlineCommissionCompanyBlock)
			throws HibernateException, Exception;
	public List<AirlineCommissionCompanyBlock> getChildrenCompanyCommissionBlocks(boolean isMaster, int companyId)
			throws HibernateException;
	public AirlineCommissionCompanyBlock  getAirlineCommissionCompanyBlock(Long blockId)throws HibernateException;
	public AirlineCommissionBlock  getAirlineCommissionBlockRow(String iataCode ,Long blockId)throws HibernateException;
	public List<AirlineCommissionBlock> getAirlineCommissionBlockRow (Long blockId) throws HibernateException;
	public AirlineCommissionCompanyBlock updateAirlineCommissionCompanyBlock(AirlineCommissionCompanyBlock airlineCommissionCompanyBlock);
	
}
