package com.lintas.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.stringtemplate.v4.compiler.CodeGenerator.list_return;

import com.admin.corporate.employee.CompanyBandEntity;
import com.admin.corporate.employee.CompanyBusinessUnitEntity;
import com.admin.corporate.employee.CompanyCostCenterEntity;
import com.admin.corporate.employee.CompanyDepartmentEntity;
import com.admin.corporate.employee.CompanyDesignationEntity;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.lintas.admin.model.UserRole;
import com.lintas.admin.model.UserWallet;
import com.opensymphony.xwork2.ActionContext;

public class CompanyEmployeeBulkUploadUtil {
	public static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CompanyEmployeeBulkUploadUtil.class);
	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static HashMap<String, EmployeeData>getFileEmployeeDataFromExcel(File file) {		
		HashMap<String, EmployeeData> errorEmailMap = new HashMap<>();
		InputStream inputStream = null;
		String uploadStatus = "";
		Pattern pattern;
		Matcher matcher;

		UserDAO DAO = new UserDAO();

		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			log.info("File not found in the specified path.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {		
			List<CompanyCostCenterEntity> userCostCenterList = new ArrayList<>();
			List<CompanyBusinessUnitEntity> userBusinessUnitList = new ArrayList<>();
			List<CompanyDepartmentEntity> userDepartmentList = new ArrayList<>();
			List<CompanyBandEntity> userBandList = new ArrayList<>();

			if(inputStream != null){
				org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(inputStream);
				org.apache.poi.ss.usermodel.Sheet mySheet = workbook.getSheetAt(0);			
				int totalRows = mySheet.getPhysicalNumberOfRows() - 1;		
				String headers = null;
				boolean isFirstRow = true;	
				Company company = (Company) ActionContext.getContext().getSession().get("Company");	
				User user = DAO.getUserByEmail(company.getEmail());
				HashMap<String, String> emailMapSorted = new HashMap<>();
				HashMap<String, String> costCenterMap = new HashMap<>();
				HashMap<String, String> businessUnitMapSorted = new HashMap<>();
				HashMap<String, String> departmentMapSorted = new HashMap<>();
				HashMap<String, String> designationMapSorted = new HashMap<>();
				HashMap<String, String> bandMapSorted = new HashMap<>();
				int costCenterIndex = -1;
				int businessUnitIndex = -1;
				int departmentIndex = -1;
				int designationIndex = -1;
				int bandIndex = -1;
				int emailIndex = -1;
				for(Row myRow : mySheet){

					if (isFirstRow) {
						headers = addHeaderRowCells(myRow);					
						String[] headerVal = headers.split(",");					
						for (int i=0;i<headerVal.length;i++) {
							if(headerVal[i].equalsIgnoreCase("Email")){
								emailIndex = i;
							}
							if(headerVal[i].equalsIgnoreCase("CostCenter")){
								costCenterIndex = i;
							}
							if(headerVal[i].equalsIgnoreCase("Business Unit")){
								businessUnitIndex = i;
							}
							if(headerVal[i].equalsIgnoreCase("Department")){
								departmentIndex = i;
							}
							if(headerVal[i].equalsIgnoreCase("Designation")){
								designationIndex = i;
							}
							if(headerVal[i].equalsIgnoreCase("Band")){
								bandIndex = i;
							}
						}
					} else {				
						int noofcol = myRow.getPhysicalNumberOfCells();					
						for (Cell cell : myRow) {
							cell.setCellType(Cell.CELL_TYPE_STRING);

						}

					}
					if(isFirstRow) {
						isFirstRow = false;
					}
				}
				emailMapSorted = getCellvaluesMap(mySheet, emailIndex);
				List<String> emailList = getuserObjectDefinedList(emailMapSorted);
				HashMap<String, String> emailMap = new HashMap<>();
				emailMap = getEmailCellvaluesMap(mySheet, emailIndex);
				pattern = Pattern.compile(EMAIL_PATTERN);
				emailList = new ArrayList<>();
				for (Entry<String, String> email : emailMap.entrySet()) {				
					matcher = pattern.matcher(email.getValue());
					boolean isvalid =  matcher.matches();
					if(isvalid){					
						emailList.add(email.getValue());
					}else{
						EmployeeData employeeData  = new EmployeeData();
						employeeData.setEmailId(email.getValue());
						employeeData.setEmployeeId(email.getKey());
						employeeData.setErrorMessage("Invalid Email Address");
						errorEmailMap.put(email.getKey(), employeeData);
					}
				}	

				List<String> existedEmailList =  DAO.getUserEmailListByCompanyId(company.getCompanyid());
				if(emailList.size() > 0 && existedEmailList.size() > 0){
					List<String> existedEmployeeData = getExistedEmployeeData(emailList, existedEmailList);					
					if(existedEmployeeData.size() > 0){		
						for (String email : existedEmployeeData) {
							String employeeId = getemployeeid(emailMap,email);
							EmployeeData employeeData  = new EmployeeData();
							employeeData.setEmailId(email);
							employeeData.setEmployeeId(employeeId);
							employeeData.setErrorMessage("Already Existed Email Address");
							errorEmailMap.put(employeeId, employeeData);

						}						

					}

					if(errorEmailMap.size() > 0){
						for (Row row : mySheet) {
							int totalRows1 = mySheet.getPhysicalNumberOfRows() - 1;								
							if(totalRows1 > 0){
								if(row.getRowNum() != 0){								
									for (Entry<String, EmployeeData> existedemail : errorEmailMap.entrySet()) {	
										row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
										if(row.getCell(0).getStringCellValue().equalsIgnoreCase(existedemail.getKey())){                                   
											int rowno =  row.getRowNum();
											int lastIndex = mySheet.getLastRowNum();                                    
											if(lastIndex != 0 && rowno > 1)
												mySheet.shiftRows(rowno + 1, lastIndex, -1);
											if(lastIndex != 0 && rowno == 1)
												mySheet.shiftRows(rowno , lastIndex, -1); 
										}
									}
								}
							}
						}
					}
					int totalRowsafter = mySheet.getPhysicalNumberOfRows() - 1;	

					if(totalRowsafter > 0){

						costCenterMap = getCellvaluesMap(mySheet, costCenterIndex);
						businessUnitMapSorted = getCellvaluesMap(mySheet, businessUnitIndex);
						departmentMapSorted = getCellvaluesMap(mySheet, departmentIndex);
						designationMapSorted = getCellvaluesMap(mySheet, designationIndex);
						bandMapSorted = getCellvaluesMap(mySheet, bandIndex);


						List<String> costCenterList = getuserObjectDefinedList(costCenterMap);
						List<String> businessUnitList = getuserObjectDefinedList(businessUnitMapSorted);
						List<String> departmentList = getuserObjectDefinedList(departmentMapSorted);
						List<String> designationList = getuserObjectDefinedList(designationMapSorted);
						List<String> bandList = getuserObjectDefinedList(bandMapSorted);

						List<CompanyBusinessUnitEntity> companyBusinessUnitList = createCompanyBusinessUnitEntity(businessUnitList, company.getCompanyid(), user.getId());
						List<CompanyCostCenterEntity> companyCostCenterList = createCompanyCostCenterEntity(costCenterList, company.getCompanyid(), user.getId());
						List<CompanyDesignationEntity> companyDesignationList = createCompanyDesignationEntity(designationList, company.getCompanyid(), user.getId());
						List<CompanyBandEntity> companyBandList = createCompanyBandEntityEntity(bandList, company.getCompanyid(), user.getId());

						//	HashMap<String, List<CompanyCostCenterEntity>> businessUnitMap = getBusinessUnitMap(mySheet, businessUnitIndex, costCenterIndex, companyCostCenterList, businessUnitList,company.getCompanyid(), user.getId());
						HashMap<String, List<CompanyDesignationEntity>> departmentMap = getDepartmentMap(mySheet, departmentIndex, designationIndex, companyDesignationList, departmentList,company.getCompanyid(), user.getId());


						// Check BusinessUnit for paricular company exist
						List<CompanyBusinessUnitEntity> existedBusinessUnitList = DAO.isBusinessUnitExistByCompanyId(company.getCompanyid());

						// Check BusinessUnit for paricular company exist
						List<CompanyCostCenterEntity> existedCostCenterList = DAO.isCostCenterExistByCompanyId(company.getCompanyid());
						// Check Department for paricular company exist
						List<CompanyDepartmentEntity> existedDepartmentList = DAO.isDepartmentExistByCompanyId(company.getCompanyid());			
						// Check same band Name Exists
						List<CompanyBandEntity> existedBandList = DAO.isBandExistByCompanyId(company.getCompanyid());

						boolean isBusinessUnitExists = false;
						boolean isCostCentertExists = false;
						boolean isDepartmentExists = false;
						boolean isBandExists = false;
						boolean isCompanyRmDataInserted = false;

						if(existedBusinessUnitList!=null && existedBusinessUnitList.size() > 0 ){
							// If exists check BusinessUnit current data and old data are same
							List<String> existbusinessUnitList = new ArrayList<>();
							for (CompanyBusinessUnitEntity companyBusinessUnitEntity : existedBusinessUnitList) {
								existbusinessUnitList.add(companyBusinessUnitEntity.getBusinessUnitName());
							}
							List<String> existedBusinessUnitData = getRMEmployeeData(businessUnitList, existbusinessUnitList);		
							if(existedBusinessUnitData.size() > 0){
								List<CompanyBusinessUnitEntity> newcompanyBusinessUnitList = createCompanyBusinessUnitEntity(existedBusinessUnitData, company.getCompanyid(), user.getId());
								userBusinessUnitList =  DAO.insertCompanyBusinessUnit(newcompanyBusinessUnitList);

							}else{
								userBusinessUnitList = existedBusinessUnitList;
							}
							isBusinessUnitExists = true;
							isCompanyRmDataInserted = true;			
						}
						if(existedCostCenterList !=null && existedCostCenterList.size() > 0){
							List<String> existCostCenterList = new ArrayList<>();
							for (CompanyCostCenterEntity companyCostCenterEntity : existedCostCenterList) {
								existCostCenterList.add(companyCostCenterEntity.getCostCenterName());
							}
							List<String> existedcostcenterData = getRMEmployeeData(costCenterList, existCostCenterList);		
							if(existedcostcenterData.size() > 0){		
								List<CompanyCostCenterEntity> newcompanyCostCenterList = createCompanyCostCenterEntity(existedcostcenterData, company.getCompanyid(), user.getId());
								// Insert Cost Center Data
								userCostCenterList =  DAO.insertCostCenterList(newcompanyCostCenterList);	

							}else{
								userCostCenterList = existedCostCenterList;
							}
							isCostCentertExists = true;
							isCompanyRmDataInserted = true;
						}
						if(existedDepartmentList != null && existedDepartmentList.size() > 0){				
							HashMap<String, List<CompanyDesignationEntity>> existedDepartmentMap = getExistedDepartmentMap(existedDepartmentList);
							if(existedDepartmentMap!=null && existedDepartmentMap.size() > 0){
								// If exists check Department current data and old data are same
								boolean isSameDepartmentExist = CollectionUtils.isEqualCollection(departmentMap.keySet(), existedDepartmentMap.keySet());	
								if(!isSameDepartmentExist){
									Collection<String> departmentcollection = CollectionUtils.subtract(departmentMap.keySet(), existedDepartmentMap.keySet());
									if(departmentcollection.size() > 0){
										HashMap<String, List<CompanyDesignationEntity>> newDepartmentMap = new HashMap<>();
										for (String department : departmentcollection) {
											if(departmentMap.containsKey(department)){
												List<CompanyDesignationEntity> companyDesignationEntityList = departmentMap.get(department);
												newDepartmentMap.put(department, companyDesignationEntityList);
											}
										}

										// Generate Department List
										List<CompanyDepartmentEntity> companyDepartmentEntityList = getDepartmentEntityList(newDepartmentMap, company.getCompanyid(), user.getId());

										// Insert Department Data
										userDepartmentList  = DAO.insertDepartmentEntity(companyDepartmentEntityList);

										// Get Updated  companyDesignationList
										List<CompanyDesignationEntity> companyDesignationListToBeUpdate = getCompanyDesignationEntityToBeUpdate(userDepartmentList, newDepartmentMap);

										// Insert Designation Data
										boolean isDesignationInserted =  DAO.insertDesignationList(companyDesignationListToBeUpdate);

										// Update Department with Designation Data
										userDepartmentList = setDesignationEntityIntoDepartmentEntity(userDepartmentList, companyDesignationListToBeUpdate);
									}
									else{
										userDepartmentList = existedDepartmentList;
									}
								}

							}

							isDepartmentExists = true;
							isCompanyRmDataInserted = true;
						}

						if(existedBandList!=null && existedBandList.size() > 0){
							List<String> existbandList = new ArrayList<>();
							for (CompanyBandEntity companyBandEntity : existedBandList) {
								existbandList.add(companyBandEntity.getBandName());
							}
							List<String> existedbandData = getRMEmployeeData(bandList, existbandList);		
							if(existedbandData.size() > 0){		
								List<CompanyBandEntity> newcompanyBandList = createCompanyBandEntityEntity(existedbandData, company.getCompanyid(), user.getId());
								// Insert Band  Data
								userBandList = DAO.insertCompanyBandEntity(newcompanyBandList);

							}else{
								userBandList = existedBandList;
							}
							isBandExists = true;
							isCompanyRmDataInserted = true;

						}

						if(!isBusinessUnitExists && !isDepartmentExists && !isBandExists & !isCostCentertExists){

							// No data avaiable for company insert it

							// Generate Business Unit List
							//List<CompanyBusinessUnitEntity> companyBusinessUnitEntityList = getCompanyBusinessUnitEntityList(businessUnitMap, company.getCompanyid(), user.getId());				

							// Insert Business Unit Data
							userBusinessUnitList =  DAO.insertCompanyBusinessUnit(companyBusinessUnitList);

							// Get Updated CompanyCostCenterList
							//List<CompanyCostCenterEntity> companyCostCenterListToBeUpdate = getcompanyCostCenterListToBeUpdate(userBusinessUnitList, businessUnitMap);				

							// Insert Cost Center Data
							userCostCenterList =  DAO.insertCostCenterList(companyCostCenterList);		

							// Generate Department List
							List<CompanyDepartmentEntity> companyDepartmentEntityList = getDepartmentEntityList(departmentMap, company.getCompanyid(), user.getId());

							// Insert Department Data
							userDepartmentList  = DAO.insertDepartmentEntity(companyDepartmentEntityList);

							// Get Updated  companyDesignationList
							List<CompanyDesignationEntity> companyDesignationListToBeUpdate = getCompanyDesignationEntityToBeUpdate(userDepartmentList, departmentMap);

							// Insert Designation Data
							boolean isDesignationInserted =  DAO.insertDesignationList(companyDesignationListToBeUpdate);

							// Update Department with Designation Data
							userDepartmentList = setDesignationEntityIntoDepartmentEntity(userDepartmentList, companyDesignationListToBeUpdate);

							// Insert Band  Data
							userBandList = DAO.insertCompanyBandEntity(companyBandList);				

							isCompanyRmDataInserted = true;

						}


						if(isCompanyRmDataInserted){

							for(Row myRow : mySheet){
								if(myRow.getRowNum() != 0){
									User newUser = new User();

									myRow.getCell(3).setCellType(Cell.CELL_TYPE_STRING);	


									myRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);			

									newUser.setUsername(myRow.getCell(0).getStringCellValue());
									newUser.setFirstname(myRow.getCell(1).getStringCellValue());
									newUser.setLastname(myRow.getCell(2).getStringCellValue());
									newUser.setEmail(myRow.getCell(3).getStringCellValue());
									newUser.setPhone(myRow.getCell(4).getStringCellValue());
									newUser.setAddress(myRow.getCell(5).getStringCellValue());
									newUser.setCity(myRow.getCell(6).getStringCellValue());
									newUser.setCountryname(myRow.getCell(7).getStringCellValue());
									System.out.println(myRow.getCell(8).getStringCellValue());


									// Set Department 
									if(userDepartmentList.size() > 0){
										for (CompanyDepartmentEntity companyDepartmentEntity : userDepartmentList) {
											if(companyDepartmentEntity.getDepartmentName().equalsIgnoreCase(myRow.getCell(9).getStringCellValue())){
												newUser.setDepartmentId(companyDepartmentEntity.getId());
												break;
											}


										}
									}
									// Set Band
									if(userBandList.size() > 0){
										for (CompanyBandEntity companyBandEntity : userBandList) {
											if(companyBandEntity.getBandName().equalsIgnoreCase(myRow.getCell(10).getStringCellValue())){
												newUser.setBandId(companyBandEntity.getId());
												break;
											}

										}
									}
									// Set Cost Center
									if(userBusinessUnitList.size() > 0){
										for (CompanyCostCenterEntity companyCostCenterEntity : userCostCenterList) {									
											if(companyCostCenterEntity.getCostCenterName().equalsIgnoreCase(myRow.getCell(11).getStringCellValue())){
												newUser.setCostCenterId(companyCostCenterEntity.getId());
												break;
											}

										}

									}
									// Set Business Unit
									if(userBusinessUnitList.size() > 0){
										for (CompanyBusinessUnitEntity companyBusinessUnitEntity : userBusinessUnitList) {
											if(myRow.getCell(12).getStringCellValue().equalsIgnoreCase(companyBusinessUnitEntity.getBusinessUnitName())){
												newUser.setBusinessUnitId(companyBusinessUnitEntity.getId());
												break;
											}
										}

									}

									newUser.setCreateddate(new Date());
									newUser.setModifieddate(new Date());
									newUser.setCompanyid(company.getCompanyid());
									newUser.setLocked(false);
									newUser.setStatus(false);
									newUser.setCurrencyCode(company.getCurrencyCode());
									newUser.setCompany_userid(company.getCompany_userid());
									newUser.setLanguage("English");
									newUser.setMobile(newUser.getPhone());
									newUser.setCompany_userid(company.getCompany_userid());
									newUser.setCreatedbyCompanyUserId(company.getCompanyid());
									newUser.setModifiedbyCompanyUserId(company.getCompanyid());
									newUser.setMailStatus(5);
									newUser.setStatus(true);

									UserRole user_role =new UserRole(); 
									user_role.setCorporateemployee(true);							
									newUser.setUserrole_id(user_role);

									UserWallet userWallet = new UserWallet();
									userWallet.setWalletBalanceRange(new BigDecimal("0.00"));
									userWallet.setWalletType("Prepaid");
									userWallet.setWalletbalance(new BigDecimal("0.00"));
									userWallet.setDepositBalance(new BigDecimal("0.00"));
									userWallet.setCurrencyCode(company.getCurrencyCode());
									userWallet.setCreatedAt(new Timestamp(new Date().getTime()));
									userWallet.setUpdatedAt(new Timestamp(new Date().getTime()));
									userWallet.setTransactionType("Credit");							
									newUser.setAgentWallet(userWallet);

									User userObj = DAO.RegisterUser(newUser, user);

								}

								uploadStatus = "Success";



							}

						}
					}

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		log.info("ExcelToXMLUtil getXMLFileDataFromExcel end" );
		return errorEmailMap;
	}
	public static HashMap<String, String> getCellvaluesMap(Sheet sheet,int cellIndex){
		HashMap<String, String> userDefinedMap = new HashMap<>();
		try{
			for(Row myRow : sheet){			
				if(myRow.getRowNum() != 0){
					Cell myCell = myRow.getCell(cellIndex - 1);	
					myCell.setCellType(Cell.CELL_TYPE_STRING);
					if(!myCell.getStringCellValue().equalsIgnoreCase(""))
						userDefinedMap.put(myCell.getStringCellValue(), myCell.getStringCellValue());	
				}
			}

		}catch (Exception e) {
			log.info("getCellvaluesMap " +e);
		}
		return userDefinedMap;
	}

	public static HashMap<String, String> getEmailCellvaluesMap(Sheet sheet,int cellIndex){
		HashMap<String, String> userDefinedMap = new HashMap<>();
		try{
			for(Row myRow : sheet){			
				if(myRow.getRowNum() != 0){
					Cell myCell = myRow.getCell(cellIndex - 1);	
					Cell idCell = myRow.getCell(1 - 1);	
					myCell.setCellType(Cell.CELL_TYPE_STRING);
					idCell.setCellType(Cell.CELL_TYPE_STRING);
					if(!myCell.getStringCellValue().equalsIgnoreCase(""))
						userDefinedMap.put(idCell.getStringCellValue(), myCell.getStringCellValue());	
					if(!myCell.getStringCellValue().equalsIgnoreCase(""))
						userDefinedMap.put(idCell.getStringCellValue(), myCell.getStringCellValue());	
				}
			}

		}catch (Exception e) {
			log.info("getCellvaluesMap " +e);
		}
		return userDefinedMap;
	}

	public static HashMap<String, List<CompanyCostCenterEntity>> getBusinessUnitMap(Sheet sheet,int businessUnitcellIndex,int costCenterIndex,List<CompanyCostCenterEntity> companyCostCenterEntityList,List<String> businessUnitList,long companyId,long companyUserId){
		HashMap<String, List<CompanyCostCenterEntity>> businessUnitMap= new HashMap<>();

		try{
			for (String BU : businessUnitList) {
				String businessUnit = "";
				HashMap<String, CompanyCostCenterEntity> costCenterListMap = new HashMap<>();				
				for (CompanyCostCenterEntity companyCostCenterEntity : companyCostCenterEntityList) {				
					for(Row myRow : sheet){	
						if(myRow.getRowNum() != 0){
							Cell businessUnitCell = myRow.getCell(businessUnitcellIndex - 1);	
							businessUnit = businessUnitCell.getStringCellValue();
							Cell costCenterCell = myRow.getCell(costCenterIndex - 1);
							String costCenter = costCenterCell.getStringCellValue();
							if(companyCostCenterEntity.getCostCenterName().equalsIgnoreCase(costCenter)){
								if(businessUnit.equalsIgnoreCase(BU)){									
									costCenterListMap.put(businessUnit, companyCostCenterEntity);
								}

							}
						}
					}
				}
				List<CompanyCostCenterEntity> costCenterList = new ArrayList<>();
				for (Map.Entry<String, CompanyCostCenterEntity> entry : costCenterListMap.entrySet()) {
					costCenterList.add(entry.getValue());
				}
				businessUnitMap.put(BU, costCenterList);	

			}

		}catch (Exception e) {
			log.info("getBusinessUnitMap " +e);
		}
		return businessUnitMap;
	}
	public static HashMap<String, List<CompanyDesignationEntity>> getDepartmentMap(Sheet sheet,int departmentcellIndex,int designationIndex,List<CompanyDesignationEntity> companyDesignationEntityList,List<String> departmentList,long companyId,long companyUserId){
		HashMap<String, List<CompanyDesignationEntity>> departmentMap= new HashMap<>();

		try{
			for (String departmentValue : departmentList) {					
				String currentDepartment = "";	
				currentDepartment = departmentValue;
				HashMap<String, CompanyDesignationEntity> designationListMap = new HashMap<>();			
				for (CompanyDesignationEntity companyDesignationEntity : companyDesignationEntityList) {	

					for(Row myRow : sheet){	
						if(myRow.getRowNum() != 0){
							Cell departmentCell = myRow.getCell(departmentcellIndex - 1);	
							departmentCell.setCellType(Cell.CELL_TYPE_STRING);
							String department = departmentCell.getStringCellValue();
							Cell designationCell = myRow.getCell(designationIndex - 1);
							designationCell.setCellType(Cell.CELL_TYPE_STRING);
							String designation = designationCell.getStringCellValue();
							if(department.equalsIgnoreCase(departmentValue)){	
								if(companyDesignationEntity.getDesignationName().equalsIgnoreCase(designation)){															
									designationListMap.put(designation, companyDesignationEntity);
								}

							}
						}
					}					
				}
				List<CompanyDesignationEntity> designationList = new ArrayList<>();
				if(designationListMap.size() > 0){
					for (Entry<String, CompanyDesignationEntity> entry : designationListMap.entrySet()) {
						designationList.add(entry.getValue());
					}
					departmentMap.put(currentDepartment, designationList);	
				}

			}

		}catch (Exception e) {
			log.info("getDepartmentMap " +e);
		}
		return departmentMap;
	}
	public static String addHeaderRowCells(Row row) {		
		String header = "";
		try{				
			for (Cell cell : row) {		
				header += "," + cell.getStringCellValue();
			}			
		}catch(Exception e){
			e.printStackTrace();
		}		
		return header;
	}

	public static List<String> getuserObjectDefinedList(HashMap<String, String> userDefinedMap){
		List<String> userDefinedList = new ArrayList<>();
		if(userDefinedMap.size() > 0){
			for (String costCenter : userDefinedMap.keySet()) {				
				userDefinedList.add(costCenter);
			}
		}
		return userDefinedList;
	}
	public static List<CompanyBusinessUnitEntity> createCompanyBusinessUnitEntity(List<String> businessUnitList,long companyId,long companyUserId){
		List<CompanyBusinessUnitEntity> companyBusinessUnitEntityList = new ArrayList<>();
		for (String businessUnit : businessUnitList) {
			CompanyBusinessUnitEntity companyBusinessUnitEntity = new CompanyBusinessUnitEntity();
			companyBusinessUnitEntity.setCompanyId(companyId);
			companyBusinessUnitEntity.setCompanyUserId(companyUserId);
			companyBusinessUnitEntity.setBusinessUnitCode("N/A");
			companyBusinessUnitEntity.setBusinessUnitName(businessUnit);
			companyBusinessUnitEntityList.add(companyBusinessUnitEntity);
		}
		return companyBusinessUnitEntityList;
	}
	public static List<CompanyCostCenterEntity> createCompanyCostCenterEntity(List<String> costCenterList,long companyId,long companyUserId){
		List<CompanyCostCenterEntity> companyCostCenterEntityList = new ArrayList<>();
		for (String costCenter : costCenterList) {
			CompanyCostCenterEntity companyCostCenterEntity = new CompanyCostCenterEntity();
			companyCostCenterEntity.setCompanyId(companyId);
			companyCostCenterEntity.setCompanyUserId(companyUserId);
			companyCostCenterEntity.setCostCenterCode("N/A");
			companyCostCenterEntity.setCostCenterName(costCenter);
			companyCostCenterEntityList.add(companyCostCenterEntity);
		}
		return companyCostCenterEntityList;
	}
	public static List<CompanyDesignationEntity> createCompanyDesignationEntity(List<String> designationList,long companyId,long companyUserId){
		List<CompanyDesignationEntity> companyDesignationEntityList = new ArrayList<>();
		for (String  designation : designationList) {
			CompanyDesignationEntity companyDesignationEntity = new CompanyDesignationEntity();
			companyDesignationEntity.setCompanyId(companyId);
			companyDesignationEntity.setCompanyUserId(companyUserId);
			companyDesignationEntity.setDesignationCode("N/A");
			companyDesignationEntity.setDesignationName(designation);
			companyDesignationEntityList.add(companyDesignationEntity);
		}
		return companyDesignationEntityList;
	}
	public static List<CompanyBandEntity> createCompanyBandEntityEntity(List<String> bandList,long companyId,long companyUserId){
		List<CompanyBandEntity> companyBandEntityList = new ArrayList<>();
		for (String  band : bandList) {
			CompanyBandEntity companyBandEntity = new CompanyBandEntity();
			companyBandEntity.setCompanyId(companyId);
			companyBandEntity.setCompanyUserId(companyUserId);
			companyBandEntity.setBandCode("N/A");
			companyBandEntity.setBandName(band);
			companyBandEntityList.add(companyBandEntity);
		}
		return companyBandEntityList;
	}

	/*public static  HashMap<String, List<CompanyCostCenterEntity>> getExistedBusinessUnitMap(List<CompanyBusinessUnitEntity> existedBusinessUnitList){
		HashMap<String, List<CompanyCostCenterEntity>> businessUnitMap = new HashMap<>();
		for (CompanyBusinessUnitEntity companyBusinessUnitEntity : existedBusinessUnitList) {
			businessUnitMap.put(companyBusinessUnitEntity.getBusinessUnitName(), companyBusinessUnitEntity.getCompanyCostCenterEntityList());			
		}
		return businessUnitMap;
	}*/

	public static List<CompanyBusinessUnitEntity> getCompanyBusinessUnitEntityList(HashMap<String, List<CompanyCostCenterEntity>> businessUnitMap,long companyId,long companyUserId){
		List<CompanyBusinessUnitEntity> companyBusinessUnitEntityList = new ArrayList<>();
		for (Entry<String, List<CompanyCostCenterEntity>> businessobj : businessUnitMap.entrySet()) {
			CompanyBusinessUnitEntity companyBusinessUnitEntity = new CompanyBusinessUnitEntity();
			companyBusinessUnitEntity.setBusinessUnitName(businessobj.getKey());
			companyBusinessUnitEntity.setBusinessUnitCode("N/A");				
			companyBusinessUnitEntity.setCompanyId(companyId);
			companyBusinessUnitEntity.setCompanyUserId(companyUserId);			
			companyBusinessUnitEntityList.add(companyBusinessUnitEntity);
		}
		return companyBusinessUnitEntityList;
	}

	/*public static List<CompanyCostCenterEntity> getcompanyCostCenterListToBeUpdate(List<CompanyBusinessUnitEntity> businessUnitListUpdated,HashMap<String, List<CompanyCostCenterEntity>> businessUnitMap){
		List<CompanyCostCenterEntity> companyCostCenterListToBeUpdate = new ArrayList<>();
		if(businessUnitListUpdated != null && businessUnitListUpdated.size() > 0){
			for (CompanyBusinessUnitEntity companyBusinessUnitEntity : businessUnitListUpdated) {
				for (Entry<String, List<CompanyCostCenterEntity>> businessobj : businessUnitMap.entrySet()) {
					if(companyBusinessUnitEntity.getBusinessUnitName().equalsIgnoreCase(businessobj.getKey())){	
						for (CompanyCostCenterEntity companyCostCenterEntity : companyBusinessUnitEntity.getCompanyCostCenterEntityList()) {
							companyCostCenterEntity.setCompanyBusinessUnitEntity(companyBusinessUnitEntity);
							companyCostCenterListToBeUpdate.add(companyCostCenterEntity);
						}
					}
				}
			}


		if(businessUnitListUpdated != null && businessUnitListUpdated.size() > 0){
			List<CompanyBusinessUnitEntity> companyBusinessUnitEntityList = new ArrayList<>();
			for (CompanyBusinessUnitEntity companyBusinessUnitEntity : businessUnitListUpdated) {					
				companyBusinessUnitEntityList.add(companyBusinessUnitEntity);
				List<CompanyCostCenterEntity>  companyCostCenterEntityList = businessUnitMap.get(companyBusinessUnitEntity.getBusinessUnitName());
				for (CompanyCostCenterEntity companyCostCenterEntity : companyCostCenterEntityList) {
					companyCostCenterEntity.setCompanyBusinessUnitEntity(companyBusinessUnitEntityList);
					companyCostCenterListToBeUpdate.add(companyCostCenterEntity);
				}
				for (Entry<String, List<CompanyCostCenterEntity>> businessobj : businessUnitMap.entrySet()) {
					if(companyBusinessUnitEntity.getBusinessUnitName().equalsIgnoreCase(businessobj.getKey())){
						if(businessUnitMap.get(businessobj.getKey()))
						for (CompanyCostCenterEntity companyCostCenterEntity : businessobj.getValue()) {
							companyCostCenterEntity.setCompanyBusinessUnitEntity(companyBusinessUnitEntity);
							companyCostCenterListToBeUpdate.add(companyCostCenterEntity);
						}
					}
				}
			}
		}
		return companyCostCenterListToBeUpdate;
	}*/

	public static List<CompanyDepartmentEntity> getDepartmentEntityList(HashMap<String, List<CompanyDesignationEntity>> departmentMap,long companyId,long companyUserId){
		List<CompanyDepartmentEntity> companyDepartmentEntityList = new ArrayList<>();
		for (Entry<String, List<CompanyDesignationEntity>> departmentobj : departmentMap.entrySet()) {
			CompanyDepartmentEntity companyDepartmentEntity = new CompanyDepartmentEntity();
			companyDepartmentEntity.setDepartmentName(departmentobj.getKey());
			companyDepartmentEntity.setDepartmentCode("N/A");			
			companyDepartmentEntity.setCompanyId(companyId);
			companyDepartmentEntity.setCompanyUserId(companyUserId);		
			companyDepartmentEntityList.add(companyDepartmentEntity);
		}
		return companyDepartmentEntityList;
	}

	public static List<CompanyDesignationEntity> getCompanyDesignationEntityToBeUpdate(List<CompanyDepartmentEntity> companyDepartmentEntityListUpdated,HashMap<String, List<CompanyDesignationEntity>> departmentMap){
		List<CompanyDesignationEntity> companyDesignationEntityToBeUpdate = new ArrayList<>();
		if(companyDepartmentEntityListUpdated != null && companyDepartmentEntityListUpdated.size() > 0){
			for (Entry<String, List<CompanyDesignationEntity>> departmentobj : departmentMap.entrySet()) {
				for (CompanyDepartmentEntity companyDepartmentEntity : companyDepartmentEntityListUpdated) {
					if(companyDepartmentEntity.getDepartmentName().equalsIgnoreCase(departmentobj.getKey())){							
						for (CompanyDesignationEntity companyDesignationEntity : departmentobj.getValue()) {
							companyDesignationEntity.setCompanyDepartmentEntity(companyDepartmentEntity);
							companyDesignationEntityToBeUpdate.add(companyDesignationEntity);
						}
					}
				}
			}
		}
		return companyDesignationEntityToBeUpdate;
	}

	public static List<CompanyDepartmentEntity> setDesignationEntityIntoDepartmentEntity(List<CompanyDepartmentEntity> companyDepartmentEntityListUpdated,List<CompanyDesignationEntity> companyDesignationEntityToBeUpdate){
		List<CompanyDepartmentEntity> companyDepartmentEntityList = new ArrayList<>();		
		List<CompanyDesignationEntity> companyDesignationEntityList = new ArrayList<>();
		for (CompanyDepartmentEntity companyDepartmentEntity : companyDepartmentEntityListUpdated) {
			companyDesignationEntityList = new ArrayList<>();
			for (CompanyDesignationEntity companyDesignationEntity : companyDesignationEntityToBeUpdate) {
				if(companyDesignationEntity.getCompanyDepartmentEntity().getId() == companyDepartmentEntity.getId()){
					companyDesignationEntityList.add(companyDesignationEntity);
				}
			}
			companyDepartmentEntity.setCompanyDesignationEntityList(companyDesignationEntityList);
			companyDepartmentEntityList.add(companyDepartmentEntity);
		}
		return companyDepartmentEntityList;
	}

	public static  HashMap<String, List<CompanyDesignationEntity>> getExistedDepartmentMap(List<CompanyDepartmentEntity> existedDepartmentList){
		HashMap<String, List<CompanyDesignationEntity>> departmentMap = new HashMap<>();
		for (CompanyDepartmentEntity companyDepartmentEntity : existedDepartmentList) {
			departmentMap.put(companyDepartmentEntity.getDepartmentName(), companyDepartmentEntity.getCompanyDesignationEntityList());			
		}
		return departmentMap;
	}
	public static List<String> getExistedEmployeeData(List<String> newlist,List<String> existedlist){
		List<String> employeeData = new ArrayList<>();
		Set<String> emailset = new HashSet<>() ;		
		for(int i = 0; i < newlist.size(); i++)
			emailset.add(newlist.get(i));
		for(int i = 0; i < existedlist.size(); i++) {
			if(emailset.contains(existedlist.get(i))) {
				employeeData.add(existedlist.get(i));
			}

		}	
		return employeeData;
	}
	public static List<String> getRMEmployeeData(List<String> newlist,List<String> existedlist){
		List<String> employeeData = new ArrayList<>();
		Set<String> emailset = new HashSet<>() ;		
		for(int i = 0; i < existedlist.size(); i++)
			emailset.add(existedlist.get(i));
		for(int i = 0; i < newlist.size(); i++) {
			if(!emailset.contains(newlist.get(i))) {
				employeeData.add(newlist.get(i));
			}

		}	
		return employeeData;
	}
	public static String getemployeeid(HashMap<String, String> employeeList,String email){
		String employeeId = "";
		for (Entry<String, String> data : employeeList.entrySet()) {
			if(data.getValue().equalsIgnoreCase(email)){
				employeeId = data.getKey();
			}
		}
		return employeeId;
	}
	/*public static List<String> getBusinessUnitData(List<String> newlist,List<String> existedlist){
		List<String> employeeData = new ArrayList<>();
		Set<String> emailset = new HashSet<>() ;		
		for(int i = 0; i < newlist.size(); i++)
			emailset.add(newlist.get(i));
		for(int i = 0; i < existedlist.size(); i++) {
			if(emailset.contains(existedlist.get(i))) {
				employeeData.add(existedlist.get(i));
			}
		}	
		return employeeData;
	}*/
	/*public static List<String> getBusinessUnitData(List<String> newlist,List<String> existedlist){
		List<String> employeeData = new ArrayList<>();
		Set<String> emailset = new HashSet<>() ;		
		for(int i = 0; i < newlist.size(); i++)
			emailset.add(newlist.get(i));
		for(int i = 0; i < existedlist.size(); i++) {
			if(emailset.contains(existedlist.get(i))) {
				employeeData.add(existedlist.get(i));
			}
		}	
		return employeeData;
	}

	public static List<CompanyCostCenterEntity> getCostCenterData(List<CompanyCostCenterEntity> newlist,List<CompanyCostCenterEntity> existedlist){
		List<CompanyCostCenterEntity> costCenterData = new ArrayList<>();
		Set<CompanyCostCenterEntity> costCenterset = new HashSet<>() ;		
		for(int i = 0; i < newlist.size(); i++)
			costCenterset.add(newlist.get(i));
		for(int i = 0; i < existedlist.size(); i++) {
			if(!costCenterset.contains(existedlist.get(i))) {
				costCenterData.add(existedlist.get(i));
			}
		}	
		return costCenterData;
	}
	public static List<CompanyDepartmentEntity> getDepartmentData(List<CompanyDepartmentEntity> newlist,List<CompanyDepartmentEntity> existedlist){
		List<CompanyDepartmentEntity> DepartmentData = new ArrayList<>();
		Set<CompanyDepartmentEntity> Departmentset = new HashSet<>() ;		
		for(int i = 0; i < newlist.size(); i++)
			Departmentset.add(newlist.get(i));
		for(int i = 0; i < existedlist.size(); i++) {
			if(!Departmentset.contains(existedlist.get(i))) {
				DepartmentData.add(existedlist.get(i));
			}
		}	
		return DepartmentData;
	}
	public static List<CompanyBandEntity> getBandData(List<CompanyBandEntity> newlist,List<CompanyBandEntity> existedlist){
		List<CompanyBandEntity> bandData = new ArrayList<>();
		Set<CompanyBandEntity> bandDataset = new HashSet<>() ;		
		for(int i = 0; i < newlist.size(); i++)
			bandDataset.add(newlist.get(i));
		for(int i = 0; i < existedlist.size(); i++) {
			if(!bandDataset.contains(existedlist.get(i))) {
				bandData.add(existedlist.get(i));
			}
		}	
		return bandData;
	}*/

}
