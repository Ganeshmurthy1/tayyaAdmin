package com.isl.admin.commission.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.email.notification.EmailNotification;
import com.email.notification.EmailNotificationDaoImp;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isl.admin.commission.dao.AirlineCommissionBlockDaoImp;
import com.isl.admin.commission.dao.AirlineCommissionSheetDaoImp;
import com.isl.admin.commission.model.AirlineCommision;
import com.isl.admin.commission.model.AirlineCommissionBlock;
import com.isl.admin.commission.model.AirlineCommissionCompanyBlock;
import com.isl.admin.commission.model.AirlineCommissionSheet;
import com.isl.admin.commission.model.AirlineLiteral;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.model.CompanyConfig;

public class CommissionService {

	public static final Logger logger = Logger.getLogger(CommissionService.class);	
	AirlineCommissionSheetDaoImp airlineCommissionSheetDao = new AirlineCommissionSheetDaoImp();
	AirlineCommissionBlockDaoImp airlineCommissionBlockDao = new AirlineCommissionBlockDaoImp();
	CompanyConfigDao CmpconfgDao=new CompanyConfigDao();
	EmailNotificationDaoImp emailNotificationDaoImp = new EmailNotificationDaoImp();


	public List<AirlineCommissionSheet> getAirLineCommissionSheetCompany(int companyId, int configId) throws Exception	
	{
		List<AirlineCommissionSheet> airlineCommissionSheetRowsResponse = new ArrayList<AirlineCommissionSheet>();
		CompanyConfig companyConfig = (CompanyConfig) CmpconfgDao.getCompanyConfigDetails(configId);
		logger.info("companyConfig------object-------------"+companyConfig.getConfig_id());
		if(companyConfig != null && companyConfig.isSheetMode())
		{
			AirlineCommissionCompanyBlock airlineCommissionCompanyBlock = airlineCommissionBlockDao.getAirlineCommissionCompanyBlock(companyConfig.getAppliedBlockId());

			logger.info("airlineCommissionCompanyBlock-----------"+airlineCommissionCompanyBlock);

			if(airlineCommissionCompanyBlock != null)
			{
				logger.info("airlineCommissionCompanyBlock  getAppliedSheetId-----------"+airlineCommissionCompanyBlock.getAppliedSheetId());

				List<AirlineCommissionSheet> airlineCommissionSheetRows = airlineCommissionSheetDao.getAirlineCommissionSheetRows(airlineCommissionCompanyBlock.getAppliedSheetId());

				Map<Integer, Map<String, AirlineCommissionBlock>> airlineCommissionBlockMapCompany = new HashMap<Integer, Map<String, AirlineCommissionBlock>>();					

				CompanyConfig companyConfigLoop = companyConfig;
				//Collect company and all his parent company blocks 
				//dont collect super user dummy block....
				while(companyConfigLoop != null && companyConfigLoop.getCompany_id() != 1 && companyConfigLoop.getParent_config_id() != 0)
				{
					try{
						List<AirlineCommissionBlock> airlineCommissionBlockRows = airlineCommissionBlockDao.getAirlineCommissionBlockRow(companyConfigLoop.getAppliedBlockId());
						Map<String, AirlineCommissionBlock> airlineCommissionBlockMap = new HashMap<String, AirlineCommissionBlock>();
						for (AirlineCommissionBlock airlineCommissionBlockRow : airlineCommissionBlockRows) {
							airlineCommissionBlockMap.put(airlineCommissionBlockRow.getIataCode(), airlineCommissionBlockRow);
						}
						logger.info(companyConfigLoop.getConfig_id()+" config id------parent cofig"+companyConfigLoop.getParent_config_id());

						airlineCommissionBlockMapCompany.put(Integer.valueOf(companyConfigLoop.getCompany_id()), airlineCommissionBlockMap);
						companyConfigLoop = (CompanyConfig) CmpconfgDao.getCompanyConfigDetails(companyConfigLoop.getParent_config_id());
						//logger.info("companyConfigLoop------object-------------"+companyConfigLoop.getConfig_id());
					}
					catch(Exception ex)
					{
						companyConfigLoop = null;
					}						
				}
				logger.info("airlineCommissionBlockMapCompany  before sorting-----------"+airlineCommissionBlockMapCompany.size());

				airlineCommissionBlockMapCompany = new TreeMap<Integer, Map<String, AirlineCommissionBlock>>(airlineCommissionBlockMapCompany);

				logger.info("airlineCommissionBlockMapCompany  after sorting-----------"+airlineCommissionBlockMapCompany.size());




				for (AirlineCommissionSheet airlineCommissionSheetRow : airlineCommissionSheetRows) {
					if(airlineCommissionSheetRow != null)
					{	
						logger.info("AirlineCommissionSheetRow------object-------------"+airlineCommissionSheetRow.getId()+"----------------"+airlineCommissionSheetRow.getIataCode());

						BigDecimal iataCommissionRetained = new BigDecimal(0);
						BigDecimal plbCommissionRetained = new BigDecimal(0);
						for (Integer parentCompanyId : airlineCommissionBlockMapCompany.keySet()) {			
							Map<String, AirlineCommissionBlock> airlineCommissionBlockRowMap = airlineCommissionBlockMapCompany.get(parentCompanyId);
							AirlineCommissionBlock airlineCommissionBlockRow = airlineCommissionBlockRowMap.get(airlineCommissionSheetRow.getIataCode());
							if(airlineCommissionBlockRow != null)
							{
								AirlineCommision airlineCommisionItem = new AirlineCommision();			
								airlineCommisionItem.setAirlineCommissionBlockRow(airlineCommissionBlockRow);
								logger.info(parentCompanyId+" Company id----iata retain--"+airlineCommissionBlockRow.getIataCommissionRetain());
								logger.info(parentCompanyId+" Company id----plb retain--"+airlineCommissionBlockRow.getPlbCommissionRetain());
								//iataCommissionRetained = iataCommissionRetained.add(airlineCommissionBlockRow.getIataCommissionRetain());
								//plbCommissionRetained = plbCommissionRetained.add(airlineCommissionBlockRow.getPlbCommissionRetain());


								BigDecimal iataRetainCompanyPercentage = airlineCommissionBlockRow.getIataCommissionRetain()!=null?airlineCommissionBlockRow.getIataCommissionRetain():new BigDecimal(0);
								BigDecimal plbRetainCompanyPercentage = airlineCommissionBlockRow.getPlbCommissionRetain()!=null?airlineCommissionBlockRow.getPlbCommissionRetain():new BigDecimal(0);
								try
								{
									iataRetainCompanyPercentage = iataRetainCompanyPercentage.divide(new BigDecimal(100));
									plbRetainCompanyPercentage = plbRetainCompanyPercentage.divide(new BigDecimal(100));								
								}
								catch(ArithmeticException ex)
								{
									iataRetainCompanyPercentage = iataRetainCompanyPercentage.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
									plbRetainCompanyPercentage = plbRetainCompanyPercentage.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);	
								}
								BigDecimal iataRetainCompanyPercentageLeft = new BigDecimal(1).subtract(iataCommissionRetained);
								BigDecimal plbRetainCompanyPercentageLeft = new BigDecimal(1).subtract(plbCommissionRetained);

								iataCommissionRetained = iataCommissionRetained.add(iataRetainCompanyPercentage.multiply(iataRetainCompanyPercentageLeft));
								plbCommissionRetained = plbCommissionRetained.add(plbRetainCompanyPercentage.multiply(plbRetainCompanyPercentageLeft));				

							}
						}	

						logger.info(" total----iata retain--"+iataCommissionRetained);
						logger.info(" total----plb retain--"+plbCommissionRetained);

						airlineCommissionSheetRow = modifyDealSheet(airlineCommissionSheetRow, iataCommissionRetained, plbCommissionRetained, true);
						//logger.info("################  parentCompanyId---airlineCommissionSheetRow ater modify "+airlineCommissionSheetRow.getPlbRemark());					

						airlineCommissionSheetRowsResponse.add(airlineCommissionSheetRow);

					}	
				}
			}
		}

		return airlineCommissionSheetRowsResponse;
	}




	public AirlineCommissionSheet getAirLineCommissionSheetCompany(String iataCode, int companyId, int configId) throws Exception	
	{
		AirlineCommissionSheet airlineCommissionSheetRow = new AirlineCommissionSheet();
		CompanyConfig companyConfig = (CompanyConfig) CmpconfgDao.getCompanyConfigDetails(configId);
		if(companyConfig != null && companyConfig.isSheetMode())
		{
			AirlineCommissionCompanyBlock airlineCommissionCompanyBlock = airlineCommissionBlockDao.getAirlineCommissionCompanyBlock(companyConfig.getAppliedBlockId());
			if(airlineCommissionCompanyBlock != null)
			{
				airlineCommissionSheetRow = (AirlineCommissionSheet) airlineCommissionSheetDao.getAirlineCommissionSheetComplete(airlineCommissionCompanyBlock.getAppliedSheetId(), iataCode);
				if(airlineCommissionSheetRow != null)
				{				
					Map<Integer, AirlineCommissionBlock> airlineCommissionBlockMap = new HashMap<Integer, AirlineCommissionBlock>();					
					CompanyConfig companyConfigLoop = companyConfig;
					while(companyConfigLoop != null && companyConfigLoop.getCompany_id() != 1 && companyConfigLoop.getParent_config_id() != 0)						
					{
						try{
							AirlineCommissionBlock airlineCommissionBlockRow = airlineCommissionBlockDao.getAirlineCommissionBlockRow(iataCode, companyConfigLoop.getAppliedBlockId());
							airlineCommissionBlockMap.put(Integer.valueOf(companyConfigLoop.getCompany_id()), airlineCommissionBlockRow);
							companyConfigLoop = (CompanyConfig) CmpconfgDao.getCompanyConfigDetails(companyConfigLoop.getParent_config_id());
						}
						catch(Exception ex)
						{
							companyConfigLoop = null;
						}						
					}	

					Map<Integer, AirlineCommissionBlock> airlineCommissionBlockTreeMap = new TreeMap<Integer, AirlineCommissionBlock>(airlineCommissionBlockMap);
					BigDecimal iataCommissionRetained = new BigDecimal(0);
					BigDecimal plbCommissionRetained = new BigDecimal(0);

					for (Integer parentCompanyId : airlineCommissionBlockTreeMap.keySet()) {			
						AirlineCommissionBlock airlineCommissionBlockRow = airlineCommissionBlockTreeMap.get(parentCompanyId);
						AirlineCommision airlineCommisionItem = new AirlineCommision();			
						airlineCommisionItem.setAirlineCommissionBlockRow(airlineCommissionBlockRow);	

						BigDecimal iataRetainCompanyPercentage = airlineCommissionBlockRow.getIataCommissionRetain()!=null?airlineCommissionBlockRow.getIataCommissionRetain():new BigDecimal(0);
						BigDecimal plbRetainCompanyPercentage = airlineCommissionBlockRow.getPlbCommissionRetain()!=null?airlineCommissionBlockRow.getPlbCommissionRetain():new BigDecimal(0);
						try
						{
							iataRetainCompanyPercentage = iataRetainCompanyPercentage.divide(new BigDecimal(100));
							plbRetainCompanyPercentage = plbRetainCompanyPercentage.divide(new BigDecimal(100));								
						}
						catch(ArithmeticException ex)
						{
							iataRetainCompanyPercentage = iataRetainCompanyPercentage.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
							plbRetainCompanyPercentage = plbRetainCompanyPercentage.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);	
						}
						BigDecimal iataRetainCompanyPercentageLeft = new BigDecimal(1).subtract(iataCommissionRetained);
						BigDecimal plbRetainCompanyPercentageLeft = new BigDecimal(1).subtract(plbCommissionRetained);

						iataCommissionRetained = iataCommissionRetained.add(iataRetainCompanyPercentage.multiply(iataRetainCompanyPercentageLeft));
						plbCommissionRetained = plbCommissionRetained.add(plbRetainCompanyPercentage.multiply(plbRetainCompanyPercentageLeft));				


					}			
					airlineCommissionSheetRow = modifyDealSheet(airlineCommissionSheetRow, iataCommissionRetained, plbCommissionRetained, true);
					//logger.info("################  parentCompanyId---airlineCommissionSheetRow ater modify "+airlineCommissionSheetRow.getPlbRemark());					
				}	
			}
		}

		return airlineCommissionSheetRow;
	}


	public Map<Integer, AirlineCommision> getAirLineCommission(String iataCode, int companyId, int configId, HashMap<Integer, AirlineLiteral> literals)
	{		

		Map<Integer, AirlineCommision> agentCommissionMap = new TreeMap<Integer, AirlineCommision>();			


		try{
			CompanyConfig companyConfig = (CompanyConfig) CmpconfgDao.getCompanyConfigDetails(configId);
			if(companyConfig != null && companyConfig.isSheetMode())
			{
				AirlineCommissionCompanyBlock airlineCommissionCompanyBlock = airlineCommissionBlockDao.getAirlineCommissionCompanyBlock(companyConfig.getAppliedBlockId());

				if(airlineCommissionCompanyBlock != null && airlineCommissionCompanyBlock.isActive())
				{
					logger.info("################ airlineCommissionCompanyBlock---id "+airlineCommissionCompanyBlock.getId());
					logger.info("################ airlineCommissionCompanyBlock---sheet id "+airlineCommissionCompanyBlock.getAppliedSheetId());
					logger.info("################ airlineCommissionCompanyBlock---iataCode "+iataCode);

					AirlineCommissionSheet airlineCommissionSheetRow = (AirlineCommissionSheet) airlineCommissionSheetDao.getAirlineCommissionSheetComplete(airlineCommissionCompanyBlock.getAppliedSheetId(), iataCode);
					if(airlineCommissionSheetRow != null)
					{	
						logger.info("################ airlineCommissionCompanyBlock---id "+airlineCommissionCompanyBlock.getId());						

						boolean isIataAvailable = false;
						boolean isPlbAvailable = false;

						BigDecimal totalIataCommission = airlineCommissionSheetRow.getIataCommission();
						if(!airlineCommissionSheetRow.getIsIataFixed())
						{
							totalIataCommission = getDynamicCommission(literals, airlineCommissionSheetRow.getIataRemark(), false);
						}			
						BigDecimal totalPlbCommission = airlineCommissionSheetRow.getPlbCommission();
						if(!airlineCommissionSheetRow.getIsPlbFixed())
						{
							totalPlbCommission = getDynamicCommission(literals, airlineCommissionSheetRow.getPlbRemark(), true);
						}			

						logger.info("################ totalIataCommission---"+totalIataCommission);
						logger.info("################ totalPlbCommission---"+totalPlbCommission);

						if(totalIataCommission.compareTo(new BigDecimal(0))>0)
							isIataAvailable = true;

						if(totalPlbCommission.compareTo(new BigDecimal(0))>0)
							isPlbAvailable = true;


						Map<Integer, AirlineCommissionBlock> airlineCommissionBlockMap = new HashMap<Integer, AirlineCommissionBlock>();					
						CompanyConfig companyConfigLoop = companyConfig;

						if(companyConfigLoop.getCompany_id() == 1 && companyConfigLoop.getParent_config_id() == 0)
						{
							AirlineCommision airlineCommisionItem = new AirlineCommision(isIataAvailable, isPlbAvailable);	
							airlineCommisionItem.setIataCommission(isIataAvailable?totalIataCommission:new BigDecimal(0));
							airlineCommisionItem.setPlbCommission(isPlbAvailable?totalPlbCommission:new BigDecimal(0));	

							agentCommissionMap.put(companyId, airlineCommisionItem);
						}
						else
						{
							while(companyConfigLoop != null && companyConfigLoop.getCompany_id() != 1 && companyConfigLoop.getParent_config_id() != 0)	
							{
								try{
									AirlineCommissionBlock airlineCommissionBlockRow = airlineCommissionBlockDao.getAirlineCommissionBlockRow(iataCode, companyConfigLoop.getAppliedBlockId());
									logger.info(companyConfigLoop.getConfig_id()+" config id------parent cofig"+companyConfigLoop.getParent_config_id());

									airlineCommissionBlockMap.put(Integer.valueOf(companyConfigLoop.getCreatedbyComapnyId()), airlineCommissionBlockRow);
									companyConfigLoop = (CompanyConfig) CmpconfgDao.getCompanyConfigDetails(companyConfigLoop.getParent_config_id());
								}
								catch(Exception ex)
								{
									companyConfigLoop = null;
								}						
							}							
							logger.info("################  airlineCommissionBlockMap before sorting---"+airlineCommissionBlockMap.size());
							Map<Integer, AirlineCommissionBlock> airlineCommissionBlockTreeMap = new TreeMap<Integer, AirlineCommissionBlock>(airlineCommissionBlockMap);
							logger.info("################  airlineCommissionBlockMap after sorting---"+airlineCommissionBlockTreeMap.size());
							logger.info("################ main super user deal sheet airlineCommissionSheetRow---"+airlineCommissionSheetRow.toString());

							BigDecimal iataCommissionRetained = new BigDecimal(0);
							BigDecimal plbCommissionRetained = new BigDecimal(0);

							BigDecimal iataCommissionCompanyTotal = totalIataCommission;
							BigDecimal plbCommissionCompanyTotal = totalPlbCommission;

							for (Integer parentCompanyId : airlineCommissionBlockTreeMap.keySet()) {			
								AirlineCommissionBlock airlineCommissionBlockRow = airlineCommissionBlockTreeMap.get(parentCompanyId);

								BigDecimal iataRetainCompanyPercentage = isIataAvailable?airlineCommissionBlockRow.getIataCommissionRetain():new BigDecimal(0);
								BigDecimal plbRetainCompanyPercentage = isPlbAvailable?airlineCommissionBlockRow.getPlbCommissionRetain():new BigDecimal(0);

								BigDecimal iataRetainCompanyAmt = new BigDecimal(0);
								BigDecimal plbRetainCompanyAmt = new BigDecimal(0);

								try
								{
									iataRetainCompanyAmt = iataCommissionCompanyTotal.multiply(iataRetainCompanyPercentage).divide(new BigDecimal(100));
									plbRetainCompanyAmt = plbCommissionCompanyTotal.multiply(plbRetainCompanyPercentage).divide(new BigDecimal(100));								
								}
								catch(ArithmeticException ex)
								{
									iataRetainCompanyAmt = iataCommissionCompanyTotal.multiply(iataRetainCompanyPercentage).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
									plbRetainCompanyAmt = plbCommissionCompanyTotal.multiply(plbRetainCompanyPercentage).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);	
								}

								iataCommissionRetained = iataCommissionRetained.add(iataRetainCompanyAmt);
								plbCommissionRetained = plbCommissionRetained.add(plbRetainCompanyAmt);




								AirlineCommision airlineCommisionItem = new AirlineCommision(isIataAvailable, isPlbAvailable);			
								airlineCommisionItem.setAirlineCommissionBlockRow(airlineCommissionBlockRow);							


								logger.info("################  company---"+parentCompanyId);

								logger.info("################  iataCommissionRetained by parent---"+iataCommissionRetained);
								logger.info("################  plbCommissionRetained by parent---"+plbCommissionRetained);

								airlineCommisionItem.setIataCommission(isIataAvailable?iataRetainCompanyAmt:new BigDecimal(0));
								airlineCommisionItem.setPlbCommission(isPlbAvailable?plbRetainCompanyAmt:new BigDecimal(0));					

								airlineCommisionItem.setIataCommissionTotal(isIataAvailable?iataCommissionCompanyTotal:new BigDecimal(0));
								airlineCommisionItem.setPlbCommissionTotal(isPlbAvailable?plbCommissionCompanyTotal:new BigDecimal(0));
								airlineCommisionItem.setCommissionTotal(iataCommissionCompanyTotal.add(plbCommissionCompanyTotal));


								iataCommissionCompanyTotal = subtractAndGet(iataCommissionCompanyTotal, iataCommissionRetained);
								plbCommissionCompanyTotal = subtractAndGet(plbCommissionCompanyTotal, plbCommissionRetained);



								agentCommissionMap.put(parentCompanyId, airlineCommisionItem);




							}

							AirlineCommision airlineCommisionItem = new AirlineCommision(isIataAvailable, isPlbAvailable);		


							AirlineCommissionSheet airlineCommissionSheetRowTemp = new AirlineCommissionSheet();
							BeanUtils.copyProperties(airlineCommissionSheetRow, airlineCommissionSheetRowTemp);
							//airlineCommissionSheetRowTemp = modifyDealSheet(airlineCommissionSheetRowTemp, iataCommissionRetained, plbCommissionRetained);
							logger.info("################  parentCompanyId---airlineCommissionSheetRow ater modify "+airlineCommissionSheetRowTemp.getPlbRemark());
							//airlineCommisionItem.setAirlineCommissionSheetRow(airlineCommissionSheetRowTemp);

							airlineCommisionItem.setIataCommission(isIataAvailable?subtractAndGet(totalIataCommission, iataCommissionRetained):new BigDecimal(0));
							airlineCommisionItem.setPlbCommission(isPlbAvailable?subtractAndGet(totalPlbCommission, plbCommissionRetained):new BigDecimal(0));	
							airlineCommisionItem.setIataCommissionTotal(isIataAvailable?subtractAndGet(totalIataCommission, iataCommissionRetained):new BigDecimal(0));
							airlineCommisionItem.setPlbCommissionTotal(isPlbAvailable?subtractAndGet(totalPlbCommission, plbCommissionRetained):new BigDecimal(0));
							airlineCommisionItem.setCommissionTotal(airlineCommisionItem.getIataCommissionTotal().add(airlineCommisionItem.getPlbCommissionTotal()));

							agentCommissionMap.put(companyId, airlineCommisionItem);

						}
					}
				}
				else
				{
					logger.info("################ Empty company commission block or inactive block- ");

				}

			}

		}
		catch(Exception e)
		{
			logger.info("################  Airline Commission map retrival.. Exception-- "+e.getMessage());
			logger.error(e);
		}
		return agentCommissionMap;
	}




	public BigDecimal subtractAndGet(BigDecimal amount, BigDecimal amountToSubtracted)
	{		
		try{			
			if(amount.compareTo(amountToSubtracted)<=0)
			{
				amount = new BigDecimal("0");									
			}
			else
			{
				amount = amount.subtract(amountToSubtracted);
			}
		}
		catch(Exception e)
		{
			logger.info("################ Amount subtract error..  "+e.getMessage());
			logger.error(e);
		}	
		amount= amount.setScale(2, BigDecimal.ROUND_UP);
		return amount;
	}


	private AirlineCommissionSheet modifyDealSheet(AirlineCommissionSheet airlineCommissionSheetRow , BigDecimal iataRetained, BigDecimal plbRetained, boolean isPercentageMode)
	{		

		if(!iataRetained.equals(0))
		{
			if(airlineCommissionSheetRow.getIataCommission()!=null)
			{
				BigDecimal amount = new BigDecimal(0);
				try{
					amount = airlineCommissionSheetRow.getIataCommission();
					if(!isPercentageMode)
					{
						if(amount.compareTo(iataRetained)<=0)
						{
							amount = new BigDecimal("0");									
						}
						else
						{
							//amount = amount.subtract(iataRetained);
							amount = subtractAndGet(amount, iataRetained);
						}	
					}
					else
					{
						BigDecimal retainedAmount = amount.multiply(iataRetained);
						//amount = amount.subtract(retainedAmount);
						amount = subtractAndGet(amount, retainedAmount);
					}

				}
				catch(Exception e)
				{
					logger.info("################ Amount subtract error..  "+e.getMessage());
					logger.error(e);
				}	
				amount= amount.setScale(2, BigDecimal.ROUND_UP);
				airlineCommissionSheetRow.setIataCommission(amount);
			}
			//	airlineCommissionSheetRow.setIataCommission(airlineCommissionSheetRow.getIataCommission().subtract(iataRetained));
			airlineCommissionSheetRow.setIataRemark(convertRemark(airlineCommissionSheetRow.getIataRemark(), iataRetained, isPercentageMode));
		}
		if(!plbRetained.equals(0))
		{
			if(airlineCommissionSheetRow.getPlbCommission()!=null)
			{
				BigDecimal amount = new BigDecimal(0);
				try{
					amount = airlineCommissionSheetRow.getPlbCommission();
					if(!isPercentageMode)
					{
						if(amount.compareTo(plbRetained)<=0)
						{
							amount = new BigDecimal("0");									
						}
						else
						{
							//amount = amount.subtract(plbRetained);
							amount = subtractAndGet(amount, plbRetained);
						}
					}
					else
					{
						BigDecimal retainedAmount = amount.multiply(plbRetained);
						//amount = amount.subtract(retainedAmount);
						amount = subtractAndGet(amount, retainedAmount);
					}


				}
				catch(Exception e)
				{
					logger.info("################ Amount subtract error..  "+e.getMessage());
					logger.error(e);
				}	
				amount= amount.setScale(2, BigDecimal.ROUND_UP);
				airlineCommissionSheetRow.setPlbCommission(amount);
			}
			//	airlineCommissionSheetRow.setPlbCommission(airlineCommissionSheetRow.getPlbCommission().subtract(plbRetained));
			airlineCommissionSheetRow.setPlbRemark(convertRemark(airlineCommissionSheetRow.getPlbRemark(), plbRetained, isPercentageMode));
		}
		return airlineCommissionSheetRow;		
	}




	private String convertRemark(String remark, BigDecimal commissionRetained, boolean isPercentageMode)
	{		
		ObjectMapper mapper = new ObjectMapper();	
		try {
			logger.info("################  remark---"+remark);

			if(remark != null && remark.contains("customize"))
			{
				Object remarkTreeView = mapper.readValue(remark, Object.class);
				if(remarkTreeView !=null && remarkTreeView instanceof ArrayList<?>)
				{
					ArrayList<Object> remarksArray = (ArrayList<Object>) remarkTreeView;

					if(remarksArray != null && remarksArray.size()>0)
					{
						Object remarkObj = remarksArray.get(0);				
						//Object to JSON in String
						String jsonInString = mapper.writeValueAsString(remarkObj);
						logger.info("################  jsonInString---"+jsonInString);
						HashMap remarkMap = mapper.readValue(jsonInString, HashMap.class);
						logger.info("################  remarkMap---"+remarkMap);
						remarkMap = traverseAndChange(remarkMap, commissionRetained, isPercentageMode);						
						logger.info("################  converted remarkMap---"+remarkMap);
						remarksArray.set(0, remarkMap);
					}
					remark = mapper.writeValueAsString(remarksArray);
					return remark;
				}
				else if(remarkTreeView !=null)
				{					
					logger.info("################  malformed remark has been stored db---class--"+remarkTreeView.getClass());
					return null;
				}
			}
			else
			{
				return null;
			}

		} catch (JsonParseException e) {
			e.printStackTrace();
			return null;

		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;

		} catch (IOException e) {
			e.printStackTrace();
			return null;

		}

		return null;
	}






	private BigDecimal getDynamicCommission(HashMap<Integer, AirlineLiteral> literals, String remark, boolean isPlb)
	{
		BigDecimal dynamicCommission = new BigDecimal(0);
		ObjectMapper mapper = new ObjectMapper();	
		try {
			//logger.info("################  remark---"+remark);

			if(remark != null && remark.contains("customize"))
			{
				Object remarkTreeView = mapper.readValue(remark, Object.class);
				if(remarkTreeView !=null && remarkTreeView instanceof ArrayList<?>)
				{
					ArrayList<Object> remarksArray = (ArrayList<Object>) remarkTreeView;
					if(remarksArray != null && remarksArray.size()>0)
					{
						Object remarkObj = remarksArray.get(0);				
						//Object to JSON in String
						String jsonInString = mapper.writeValueAsString(remarkObj);
						//logger.info("################  jsonInString---"+jsonInString);
						HashMap remarkMap = mapper.readValue(jsonInString, HashMap.class);
						//logger.info("################  remarkMap---"+remarkMap);
						dynamicCommission = getCommissionFromRemarkMap(remarkMap, literals, isPlb);
						/*if(remarkObj != null && remarkObj instanceof RemarkNode)
						{
							RemarkNode remarkNode = (com.isl.admin.commission.model.RemarkNode) remarkObj;
							logger.info("################  remarkObj---"+remarkNode.toString());
							//traverseRemark(remarkNode, literals);
						}
						else
						{
							logger.info("################  remarkObj- cane not be a RemarkNode--");
						}*/

					}

				}
				else if(remarkTreeView !=null)
				{
					logger.info("################  malformed remark has been stored db---class--"+remarkTreeView.getClass());
				}

			}

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally{
			return dynamicCommission;
		}

	}

	/*	{\"ix\":0,\"pr\":[],\"ndt\":0,\"nm\":\"customize\",\"tp\":\"100\",
	 * \"nds\":[{\"ix\":1,\"ndt\":1,\"nm\":\"20\",\"tp\":\"18\",
	 * 				\"nds\":[{\"ix\":-1,\"ndt\":2,\"nm\":\"inc\",\"tp\":\"inc\",
	 * 				\"nds\":[{\"ix\":2,\"ndt\":1,\"nm\":\"A\",\"tp\":\"2\",
	 * 				\"nds\":[{\"ix\":-1,\"ndt\":2,\"nm\":\"inc\",\"tp\":\"inc\",
	 * 				\"nds\":[{\"ix\":3,\"ndt\":1,\"nm\":\"xfd\",\"tp\":\"13\",
	 * 				\"nds\":[{\"ix\":-1,\"ndt\":2,\"nm\":\"inc\",\"tp\":\"inc\",
	 * 				\"nds\":[{\"ix\":4,\"ndt\":1,\"nm\":\"dfdsf\",\"tp\":\"4\",
	 * 				\"nds\":[{\"ix\":-1,\"ndt\":2,\"nm\":\"inc\",\"tp\":\"inc\",
	 * 				\"nds\":[],\"isc\":true,\"$$hashKey\":\"object:22245\"},
	 * 			{\"ix\":-1,\"ndt\":3,\"nm\":\"exc\",\"tp\":\"exc\",
	 * 					\"nds\":[],\"isc\":true,\"$$hashKey\":\"object:22246\"}]
	 * 			,\"isc\":true,\"$$hashKey\":\"object:22200\"}],
	 * 			\"isc\":true,\"$$hashKey\":\"object:22064\"}
	 * 			,{\"ix\":-1,\"ndt\":3,\"nm\":\"exc\",\"tp\":\"exc\",
	 * 				\"nds\":[{\"ix\":5,\"ndt\":1,\"nm\":\"Economy\",\"tp\":\"2\",
	 * 				\"nds\":[{\"ix\":-1,\"ndt\":2,\"nm\":\"inc\",\"tp\":\"inc\",
	 * 				\"nds\":[],\"isc\":true,\"$$hashKey\":\"object:22426\"},
	 * 				{\"ix\":-1,\"ndt\":3,\"nm\":\"exc\",\"tp\":\"exc\",
	 * 				\"nds\":[],\"isc\":true,\"$$hashKey\":\"object:22427\"}],	
	 * 			\"isc\":true,\"$$hashKey\":\"object:22381\"}],\"isc\":true,\"$$hashKey\":\"object:22065\"}],
	 * 			\"isc\":true,\"$$hashKey\":\"object:22019\"}],\"isc\":true,\"$$hashKey\":\"object:21883\"}
	 * 			,{\"ix\":-1,\"ndt\":3,\"nm\":\"exc\",\"tp\":\"exc\",
	 * 			\"nds\":[],\"isc\":true,\"$$hashKey\":\"object:21884\"}],
	 * 			\"isc\":true,\"$$hashKey\":\"object:21838\"}],\"isc\":true,\"$$hashKey\":\"object:20788\"},
	 * 			{\"ix\":-1,\"ndt\":3,\"nm\":\"exc\",\"tp\":\"exc\",\"nds\":[],\"isc\":true,\"$$hashKey\":\"object:20789\"}],
	 * 			\"isc\":true,\"$$hashKey\":\"object:20510\"},{\"ix\":6,\"ndt\":1,\"nm\":\"10\",\"tp\":\"18\",
	 * 					\"nds\":[{\"ix\":-1,\"ndt\":2,\"nm\":\"inc\",\"tp\":\"inc\",
	 * 					\"nds\":[{\"ix\":7,\"ndt\":1,\"nm\":\"dsfdg\",\"tp\":\"9\",
	 * 					\"nds\":[{\"ix\":-1,\"ndt\":2,\"nm\":\"inc\",\"tp\":\"inc\",
	 * 					\"nds\":[{\"ix\":8,\"ndt\":1,\"nm\":\"fdgd\",\"tp\":\"17\",
	 * 					\"nds\":[{\"ix\":-1,\"ndt\":2,\"nm\":\"inc\",\"tp\":\"inc\",
	 * 					\"nds\":[{\"ix\":10,\"ndt\":1,\"nm\":\"fdgfdg\",\"tp\":\"4\",
	 * 					\"nds\":[{\"ix\":-1,\"ndt\":2,\"nm\":\"inc\",\"tp\":\"inc\",
	 * 					\"nds\":[],\"isc\":true,\"$$hashKey\":\"object:23485\"},
	 * 				{\"ix\":-1,\"ndt\":3,\"nm\":\"exc\",\"tp\":\"exc\",
	 * 				\"nds\":[],\"isc\":true,\"$$hashKey\":\"object:23486\"}],
	 * 				\"isc\":true,\"$$hashKey\":\"object:23440\"}],\"isc\":true,\"$$hashKey\":\"object:23259\"},
	 * 				{\"ix\":-1,\"ndt\":3,\"nm\":\"exc\",\"tp\":\"exc\",\"nds\":[],\"isc\":true,\"$$hashKey\":\"object:23260\"}],
	 * 			\"isc\":true,\"$$hashKey\":\"object:23214\"}],\"isc\":true,\"$$hashKey\":\"object:23078\"},
	 * 			{\"ix\":-1,\"ndt\":3,\"nm\":\"exc\",\"tp\":\"exc\",\"nds\":[],\"isc\":true,\"$$hashKey\":\"object:23079\"}],
	 * 			\"isc\":true,\"$$hashKey\":\"object:23033\"}],\"isc\":true,\"$$hashKey\":\"object:22589\"},
	 * 			{\"ix\":-1,\"ndt\":3,\"nm\":\"exc\",\"tp\":\"exc\",\"nds\":[],\"isc\":true,\"$$hashKey\":\"object:22590\"}],
	 * 			\"isc\":true,\"$$hashKey\":\"object:22561\"}],\"isc\":true,\"$$hashKey\":\"object:19951\"}*/

	public BigDecimal getCommissionFromRemarkMap(HashMap remarkMap, HashMap<Integer, AirlineLiteral> literals, boolean isPlb)
	{
		/*{\"ix\":-1,\"ndt\":3,\"nm\":\"exc\",\"tp\":\"exc\",\"nds\":[],\"isc\":true,\"$$hashKey\":\"object:23079\"}
		 */		
		String remarkType = "IATA remark";
		if(isPlb)
			remarkType = "PLB remark";
		BigDecimal commission = new BigDecimal(0);
		if(remarkMap != null && remarkMap.get("nds") != null)
		{
			ArrayList<HashMap> amountRemarkMapList = (ArrayList<HashMap>) remarkMap.get("nds");
			boolean isAmountFound = false;
			if(amountRemarkMapList != null && amountRemarkMapList.size()>0)
			{
				logger.info(remarkType+"################  Dynamic amount map list found-");
				for (HashMap amountMap : amountRemarkMapList) {
					BigDecimal amount = getCommissionFromAmountMap(amountMap, literals, isPlb);
					if(amount != null)
					{
						//logger.info(remarkType+"################  Amount fount to be applied-");
						//logger.info(remarkType+"################  Amount fount to be applied- break here");
						commission = amount;
						isAmountFound = true;
						break;

					}

				}
			}

			if(!isAmountFound)
			{
				logger.info("################  No Dynamic Amount fount to be applied-");
			}

		}		
		return commission;
	}
	public BigDecimal getCommissionFromAmountMap(HashMap amountMap, HashMap<Integer, AirlineLiteral> literals, boolean isPlb)
	{
		/*{\"ix\":-1,\"ndt\":3,\"nm\":\"exc\",\"tp\":\"exc\",\"nds\":[],\"isc\":true,\"$$hashKey\":\"object:23079\"}
		 */	

		String remarkType = "IATA remark";
		if(isPlb)
			remarkType = "PLB remark";
		BigDecimal commission = null;
		if(amountMap != null && amountMap.get("nds") != null)
		{
			try{
				String amountstr = (String) amountMap.get("nm");
				BigDecimal amount = new BigDecimal(amountstr); 		
				logger.info(remarkType+"################  Amount applicable checkk");
				logger.info(remarkType+"################  Amount tree will traverse here......");
				boolean isApplicable = true;
				logger.info(remarkType+"################  no of literlas....."+literals.size());
				for (Integer key : literals.keySet()) {
					AirlineLiteral airlineLiteral = literals.get(key);
					//boolean isLiteralApplicable = traverseAndCheck(null, amountMap, airlineLiteral);
					int statusCount = traverseAndCheck(0, null, amountMap, airlineLiteral, isPlb, 0);
					logger.info(remarkType+"################  airlineLiteral .."+airlineLiteral.getType() +"---"+airlineLiteral.getName());
					logger.info(remarkType+"################  airlineLiteral .."+airlineLiteral.getType() +"---"+airlineLiteral.getName() +" is applicable="+statusCount);
					if(statusCount >= 0)
					{
						isApplicable = (isApplicable && true);
					}
					else
					{
						isApplicable = (isApplicable && false);
						break;
					}
				}
				logger.info(remarkType+"################ "+amountstr+" % Amount to be checked for applicable..."+isApplicable);
				if(isApplicable)
					commission = amount;
			}
			catch(Exception e)
			{
				logger.info(remarkType+"################  Amount Check .. Error..."+e.getMessage());
				logger.error(e);
				return null;
			}

			/*ArrayList<HashMap> amountRemarkMapList = (ArrayList<HashMap>) remarkMap.get("nodes");
			if(amountRemarkMapList != null)
			{
				for (HashMap amountMap : amountRemarkMapList) {

				}
			}*/

		}		
		return commission;
	}
	public int traverseAndCheck(int traverselevel, HashMap parentNode, HashMap node, AirlineLiteral airlineLiteral, boolean isPlb, int availStatus)
	{
		/*{\"ix\":-1,\"ndt\":3,\"nm\":\"exc\",\"tp\":\"exc\",\"nds\":[],\"isc\":true,\"$$hashKey\":\"object:23079\"}
		 */		
		traverselevel ++;

		String remarkType = "IATA remark";
		if(isPlb)
			remarkType = "PLB remark";

		//int status = 0;
		if(node != null && node.get("nds") != null)
		{
			ArrayList<HashMap> nodeList = (ArrayList<HashMap>) node.get("nds");			
			if(nodeList != null)
			{
				for (HashMap child : nodeList) {					
					Object nodeTypeStr = (Object) child.get("ndt");	
					logger.info(remarkType+" "+traverselevel+" traverseAndCheck ################ traverse..nodeTypeStr."+nodeTypeStr);
					if(nodeTypeStr!=null)
					{
						if(nodeTypeStr.equals(1))
						{ 						
							int type = child.get("tp")!=null?Integer.parseInt((String) child.get("tp")):-1;
							String name = (String) child.get("nm");
							logger.info(remarkType+" "+traverselevel+" traverseAndCheck ################ tree child node type "+type);
							logger.info(remarkType+" "+traverselevel+" traverseAndCheck ################ literal type "+airlineLiteral.getType());

							if(type == (airlineLiteral.getType()))
							{
								logger.info(remarkType+" "+traverselevel+" traverseAndCheck ################ tree child value  "+name);
								logger.info(remarkType+" "+traverselevel+" traverseAndCheck ################ literla value  "+airlineLiteral.getName());
								if(name.equals(airlineLiteral.getName()) || name.equalsIgnoreCase("all"))
								{
									logger.info(remarkType+" "+traverselevel+" traverseAndCheck ################ value matching....--- ");
									if(parentNode != null)
									{
										Object parentNodeTypeStr = (Object) node.get("ndt");	
										logger.info(remarkType+" "+traverselevel+" traverseAndCheck ################ traverse..parent type is ."+parentNodeTypeStr);
										if(parentNodeTypeStr!=null)
										{
											if(parentNodeTypeStr.equals(2))
											{ 	
												availStatus = 1;
												logger.info(remarkType+" "+traverselevel+" traverseAndCheck ################ match found in includes."+parentNodeTypeStr);
												/*logger.info("################ match found in excludes."+nodeTypeStr);
												ArrayList<HashMap> childNodeList = (ArrayList<HashMap>) child.get("nodes");			
												if(childNodeList != null)
												{
													status = status + traverseAndCheck(node, child, airlineLiteral);
												}*/

											}
											else if(parentNodeTypeStr.equals(3))
											{
												availStatus = -1;								
												logger.info(remarkType+" "+traverselevel+" traverseAndCheck ################ match found in excludes."+parentNodeTypeStr);

											}
										}
									}


								}
								else
								{
									availStatus = 0;									
									logger.info(remarkType+" "+traverselevel+" traverseAndCheck ################ no match found for literal."+nodeTypeStr);

								}
							}
							else
							{
								logger.info(remarkType+" "+traverselevel+" traverseAndCheck ################ diff liternal found traverse more  ");
								availStatus = traverseAndCheck(traverselevel, node, child, airlineLiteral, isPlb, availStatus);
								logger.info(remarkType+" "+traverselevel+" traverseAndCheck ################ child found  availStatus"+availStatus);
							}

						}
						else if(nodeTypeStr.equals(2))
						{
							availStatus =  traverseAndCheck(traverselevel, node, child, airlineLiteral, isPlb, availStatus);
							logger.info(remarkType+" "+traverselevel+" traverseAndCheck ################ child inc found.  availStatus"+availStatus);

						}
						else if(nodeTypeStr.equals(3))
						{
							availStatus =  traverseAndCheck(traverselevel, node, child, airlineLiteral, isPlb, availStatus);
							logger.info(remarkType+" "+traverselevel+" traverseAndCheck ################ child exc found.  availStatus"+availStatus);


						}
					}

				}


			}
		}		
		return availStatus;
	}


	public HashMap traverseAndChange(HashMap node, BigDecimal amtRetained, boolean isPercentageMode)
	{		
		if(node != null && node.get("nds") != null)
		{
			ArrayList<HashMap> nodeList = (ArrayList<HashMap>) node.get("nds");	
			ArrayList<HashMap> nodeListNew = null;	
			if(nodeList != null)
			{
				nodeListNew = new ArrayList<HashMap>();	


				//for (HashMap child : nodeList) {
				for(int childIndex=0; childIndex< nodeList.size(); childIndex ++)
				{
					HashMap child = nodeList.get(childIndex);
					//HashMap childNew = nodeListNew.get(childIndex);
					Object nodeTypeStr = (Object) child.get("ndt");	
					logger.info("################ traverse..nodeTypeStr."+nodeTypeStr);
					if(nodeTypeStr.equals(1))
					{ 						
						Object type = (Object) child.get("tp");
						String name = (String) child.get("nm");
						logger.info("################ child node found.  -type "+type);
						String checkType = (String) type;
						if(checkType.equals("18"))
						{
							logger.info("################ Amount type found  ");
							logger.info("################ Amount is..  "+name);
							BigDecimal amount = new BigDecimal(0);
							try{
								amount = new BigDecimal(name);


								if(!isPercentageMode)
								{
									if(amount.compareTo(amtRetained)<=0)
									{
										amount = new BigDecimal("0");									
									}
									else
									{
										amount = subtractAndGet(amount, amtRetained);
										//amount = amount.subtract(amtRetained);
									}	
								}
								else
								{
									BigDecimal retainedAmount = amount.multiply(amtRetained);
									//amount = amount.subtract(retainedAmount);
									amount = subtractAndGet(amount, retainedAmount);
								}


							}
							catch(Exception e)
							{
								logger.info("################ Amount subtract error..  "+e.getMessage());
								logger.error(e);
							}	
							amount= amount.setScale(2, BigDecimal.ROUND_UP);
							logger.info("################ New Amount is..  "+name);
							child.put("nm", String.valueOf(amount));

						}
						child = traverseAndChange(child, amtRetained, isPercentageMode);
					}
					else if(nodeTypeStr.equals(2))
					{
						logger.info("################ child inc found.  ");
						child = traverseAndChange(child, amtRetained, isPercentageMode);
					}
					else if(nodeTypeStr.equals(3))
					{
						logger.info("################ child exc found.  ");
						child = traverseAndChange(child, amtRetained, isPercentageMode);
					}				
					nodeListNew.add(child);					
				}

			}

			node.put("nds", nodeListNew);
		}		
		return node;
	}

	public boolean traverseAndNotifyChildren(CompanyConfig superCompanyConfig, Long blockId, int type)
	{			
		List<CompanyConfig> companyConfigList = CmpconfgDao.getCompanyConfigList(blockId);
		for (CompanyConfig companyConfig : companyConfigList) {			
			List<AirlineCommissionCompanyBlock> airlineCommissionCompanyBlockList = airlineCommissionBlockDao.getChildrenCompanyCommissionBlocks(false, companyConfig.getCompany_id());
			for (AirlineCommissionCompanyBlock airlineCommissionCompanyBlock : airlineCommissionCompanyBlockList) {

				//emailNotificationDaoImp.insertEmailNotification(companyConfig.getCompany_id(), superCompanyConfig.getCompany_id(), companyConfig.getCompany_id(), -1, EmailNotification.ACTION_PARENT_TO_CHILD, type , String.valueOf(blockId));
				//logger.info("Commission change modification..email notification entry..type-"+type+"---block id-"+blockId+"--by--"+superCompanyConfig.getCompanyName()+" to="+companyConfig.getCompanyName());

				traverseAndNotifyChildren(superCompanyConfig, airlineCommissionCompanyBlock.getId(), type);
			}

			emailNotificationDaoImp.insertEmailNotification(companyConfig.getCompany_id(), superCompanyConfig.getCompany_id(), companyConfig.getCompany_id(), -1, EmailNotification.ACTION_PARENT_TO_CHILD, type , String.valueOf(blockId));
			logger.info("Commission change modification..email notification entry..type-"+type+"---block id-"+blockId+"--by--"+superCompanyConfig.getCompanyName()+" to="+companyConfig.getCompanyName());

		}
		return true;
	}
	public boolean sheetChangeNotifyChildren(CompanyConfig superCompanyConfig, Long sheetId, int type)
	{	
		logger.info("Commission change modification....type-"+type+"---sheet/block id-"+sheetId+"--by--"+superCompanyConfig.getCompanyName());
		List<AirlineCommissionCompanyBlock> airlineCommissionCompanyBlockList = airlineCommissionBlockDao.getChildrenCompanyCommissionBlocks(false, superCompanyConfig.getCompany_id());
		for (AirlineCommissionCompanyBlock airlineCommissionCompanyBlock : airlineCommissionCompanyBlockList) {
			if(airlineCommissionCompanyBlock.getAppliedSheetId().equals(sheetId))
			{
				traverseAndNotifyChildren(superCompanyConfig, sheetId, type);
				break;
			}
		}
		return true;
	}
	public HashMap<Long, Boolean> traverseAndChangeSheet(CompanyConfig superCompanyConfig, Long blockId, Long appliedSheetIdNew, HashMap<Long, Boolean> resultMap)
	{
		if(resultMap == null)
			resultMap = new HashMap<Long, Boolean>();		
		List<CompanyConfig> companyConfigList = CmpconfgDao.getCompanyConfigList(blockId);
		for (CompanyConfig companyConfig : companyConfigList) 
		{
			List<AirlineCommissionCompanyBlock> airlineCommissionCompanyBlockList = airlineCommissionBlockDao.getChildrenCompanyCommissionBlocks(false, companyConfig.getCompany_id());
			for (AirlineCommissionCompanyBlock airlineCommissionCompanyBlock : airlineCommissionCompanyBlockList)
			{
				airlineCommissionCompanyBlock.setAppliedSheetId(appliedSheetIdNew);
				boolean isapplied = false;
				try
				{
					airlineCommissionCompanyBlock = airlineCommissionBlockDao.updateAirlineCommissionCompanyBlock(airlineCommissionCompanyBlock);
					if(airlineCommissionCompanyBlock != null)
					{
						isapplied = true;						
					}
				}
				catch(Exception e)
				{
					isapplied = true;
					logger.error("################ Company block sheet change error."+e.getMessage());
				}
				if(isapplied)
					emailNotificationDaoImp.insertEmailNotification(companyConfig.getCompany_id(), superCompanyConfig.getCompany_id(), companyConfig.getCompany_id(), -1, EmailNotification.ACTION_PARENT_TO_CHILD, EmailNotification.EMAIL_TYPE_COMMISSION_BLOCK_MODIFICATION, String.valueOf(airlineCommissionCompanyBlock.getId()));
				resultMap = traverseAndChangeSheet(superCompanyConfig, airlineCommissionCompanyBlock.getId(), appliedSheetIdNew, resultMap);
				resultMap.put(airlineCommissionCompanyBlock.getId(), isapplied);				
			}

			emailNotificationDaoImp.insertEmailNotification(companyConfig.getCompany_id(), superCompanyConfig.getCompany_id(), companyConfig.getCompany_id(), -1, EmailNotification.ACTION_PARENT_TO_CHILD, EmailNotification.EMAIL_TYPE_COMMISSION_BLOCK_MODIFICATION, String.valueOf(blockId));
		}		
		return resultMap;		
	}


	
}
