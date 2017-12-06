package com.common.dsr.helper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.lintas.admin.DAO.RmConfigDao;
import com.lintas.admin.hotel.model.HotelOrderGuest;
import com.lintas.admin.model.RmConfigModel;
import com.lintas.admin.model.RmConfigTripDetailsModel;
import com.lintas.config.HibernateUtil;

public class DsrRmConfigHelperDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(DsrRmConfigHelperDao.class);
	public  RmConfigTripDetailsModel getRmConfigTripDetails(String orderId, int Companyid){
		Session session=null;
		List<String> extraRmConfigFieldList=new ArrayList<>();
		Map<String,String> dynamicRmConfigMap=new LinkedHashMap<>();
		StringBuilder extraRmConfigDetails=new StringBuilder();
		RmConfigTripDetailsModel rmConfigTripDetailsModel= null;
		RmConfigDao rmConfigDao=new RmConfigDao();
		RmConfigModel comapanyRmConfigData =rmConfigDao.getConfigDetailsByCompanyId(Companyid);
		String manualStringFields[] =comapanyRmConfigData!=null && (comapanyRmConfigData.getDynamicFieldsData() != null && !comapanyRmConfigData.getDynamicFieldsData().equals(""))? comapanyRmConfigData.getDynamicFieldsData().split(",") : null;
		if(manualStringFields!=null && manualStringFields.length>0){
			String fieldsName[]=null;
			for (String oneField : manualStringFields) {
				if(!oneField.trim().equalsIgnoreCase(""))
					fieldsName  = oneField.split(":");
				extraRmConfigFieldList.add(fieldsName[0]);

			}
		}
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(RmConfigTripDetailsModel.class);
			criteria.add(Restrictions.eq("orderId",orderId));
			rmConfigTripDetailsModel = (RmConfigTripDetailsModel) criteria.uniqueResult();
			if(rmConfigTripDetailsModel!=null){
				if(rmConfigTripDetailsModel.getApproverName()==null ||rmConfigTripDetailsModel.getApproverName().equals("")) 
					rmConfigTripDetailsModel.setApproverName("-");
				if(rmConfigTripDetailsModel.getBillNonBill()==null  || rmConfigTripDetailsModel.getBillNonBill().equals("")) 
					rmConfigTripDetailsModel.setBillNonBill("-");
				if(rmConfigTripDetailsModel.getBussinessUnit()==null  || rmConfigTripDetailsModel.getBussinessUnit().equals("")) 
					rmConfigTripDetailsModel.setBussinessUnit("-");
				if(rmConfigTripDetailsModel.getCostCenter()==null  || rmConfigTripDetailsModel.getCostCenter().equals("")) 
					rmConfigTripDetailsModel.setCostCenter("-");
				if(rmConfigTripDetailsModel.getDepartment()==null  || rmConfigTripDetailsModel.getDepartment().equals("")) 
					rmConfigTripDetailsModel.setDepartment("-");
				if(rmConfigTripDetailsModel.getEmpCode()==null  || rmConfigTripDetailsModel.getEmpCode().equals("")) 
					rmConfigTripDetailsModel.setEmpCode("-");
				if(rmConfigTripDetailsModel.getLocation()==null  || rmConfigTripDetailsModel.getLocation().equals("")) 
					rmConfigTripDetailsModel.setLocation("-");
				if(rmConfigTripDetailsModel.getProjectCode()==null  || rmConfigTripDetailsModel.getProjectCode().equals("")) 
					rmConfigTripDetailsModel.setProjectCode("-");
				if(rmConfigTripDetailsModel.getReasonForTravel()==null  || rmConfigTripDetailsModel.getReasonForTravel().equals("")) 
					rmConfigTripDetailsModel.setReasonForTravel("-");
				if(rmConfigTripDetailsModel.getTrNumber()==null  || rmConfigTripDetailsModel.getTrNumber().equals("")) 
					rmConfigTripDetailsModel.setTrNumber("-");
				String manualField1=rmConfigTripDetailsModel.getManualField1()!=null && !rmConfigTripDetailsModel.getManualField1().equals("")?rmConfigTripDetailsModel.getManualField1():"-";
				String manualField2=rmConfigTripDetailsModel.getManualField2()!=null &&  !rmConfigTripDetailsModel.getManualField2().equals("")?rmConfigTripDetailsModel.getManualField2():"-";
				String manualField3=rmConfigTripDetailsModel.getManualField3()!=null  && !rmConfigTripDetailsModel.getManualField3().equals("")?rmConfigTripDetailsModel.getManualField3():"-";
				String manualField4=rmConfigTripDetailsModel.getManualField4()!=null  && !rmConfigTripDetailsModel.getManualField4().equals("") ?rmConfigTripDetailsModel.getManualField4():"-";
				String manualField5=rmConfigTripDetailsModel.getManualField5()!=null && !rmConfigTripDetailsModel.getManualField5().equals("")?rmConfigTripDetailsModel.getManualField5():"-";
				if(extraRmConfigFieldList!=null && extraRmConfigFieldList.size()>0){
					if(extraRmConfigFieldList.size()==1){
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), manualField1);
						extraRmConfigDetails.append(extraRmConfigFieldList.get(0)+":"+manualField1);
					}
					if(extraRmConfigFieldList.size()==2){ 
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), manualField1);
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(1), manualField2);
						extraRmConfigDetails.append(extraRmConfigFieldList.get(0)+":"+manualField1+"/"+extraRmConfigFieldList.get(1)+":"+manualField2);
					}
					if(extraRmConfigFieldList.size()==3){ 
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), manualField1);
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(1), manualField2);
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(2), manualField3);
						extraRmConfigDetails.append(extraRmConfigFieldList.get(0)+":"+manualField1+"/"+extraRmConfigFieldList.get(1)+":"+manualField2+"/"
								+extraRmConfigFieldList.get(2)+":"+manualField3);
					}
					if(extraRmConfigFieldList.size()==4){
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), manualField1);
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(1), manualField2);
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(2), manualField3);
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(3), manualField4);
						extraRmConfigDetails.append(extraRmConfigFieldList.get(0)+":"+manualField1+"/"+extraRmConfigFieldList.get(1)+":"+ manualField2+"/"
								+extraRmConfigFieldList.get(2)+":"+manualField3+"/"+extraRmConfigFieldList.get(3)+":"+ manualField4);
					}
					if(extraRmConfigFieldList.size()==5){
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), manualField1);
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(1), manualField2);
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(2), manualField3);
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(3), manualField4);
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(4), manualField5);
						extraRmConfigDetails.append(extraRmConfigFieldList.get(0)+":"+manualField1+"/"+extraRmConfigFieldList.get(1)+":"+manualField2+"/"
								+extraRmConfigFieldList.get(2)+":"+manualField3+"/"+extraRmConfigFieldList.get(3)+":"+manualField4+"/"
								+extraRmConfigFieldList.get(4)+":"+manualField5);
					}
					rmConfigTripDetailsModel.setRmconfigFieldsMap(dynamicRmConfigMap);
					rmConfigTripDetailsModel.setExtraRmConfigDetails(extraRmConfigDetails.toString());
				}
			}
			else{
				rmConfigTripDetailsModel= new RmConfigTripDetailsModel();
				rmConfigTripDetailsModel.setApproverName("-");
				rmConfigTripDetailsModel.setBillNonBill("-");
				rmConfigTripDetailsModel.setBussinessUnit("-");
				rmConfigTripDetailsModel.setCostCenter("-");
				rmConfigTripDetailsModel.setDepartment("-");
				rmConfigTripDetailsModel.setEmpCode("-");
				rmConfigTripDetailsModel.setLocation("-");
				rmConfigTripDetailsModel.setProjectCode("-");
				rmConfigTripDetailsModel.setReasonForTravel("-");
				rmConfigTripDetailsModel.setTrNumber("-");
				rmConfigTripDetailsModel.setManualField1("-");
				rmConfigTripDetailsModel.setManualField2("-");
				rmConfigTripDetailsModel.setManualField3("-");
				rmConfigTripDetailsModel.setManualField4("-");
				rmConfigTripDetailsModel.setManualField5("-");
				rmConfigTripDetailsModel.setExtraRmConfigDetails("-");
				if(extraRmConfigFieldList!=null && extraRmConfigFieldList.size()>0){
					if(extraRmConfigFieldList.size()==1){ 
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), "-");
					}
					if(extraRmConfigFieldList.size()==2){ 
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), "-");
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(1), "-");
					}
					if(extraRmConfigFieldList.size()==3){ 
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), "-");
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(1), "-");
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(2),"-");
					}
					if(extraRmConfigFieldList.size()==4){ 
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), "-");
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(1), "-");
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(2),"-");
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(3), "-");
					}
					if(extraRmConfigFieldList.size()==5){ 
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), "-");
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(1), "-");
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(2),"-");
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(3), "-");
						dynamicRmConfigMap.put(extraRmConfigFieldList.get(4), "-");
					}
					rmConfigTripDetailsModel.setRmconfigFieldsMap(dynamicRmConfigMap);
				}
			}

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}  
		return rmConfigTripDetailsModel;
	}
	
	
	public RmConfigTripDetailsModel  getFlightPaxRmConfigTripDetails(RmConfigTripDetailsModel rmConfigTripDetailsModel,String rmDynamicStruc){
		return getPerPassangerRmConfigTripDetails(rmConfigTripDetailsModel, rmDynamicStruc);
	}
	public RmConfigTripDetailsModel  getHotelPaxRmConfigTripDetails(RmConfigTripDetailsModel rmConfigTripDetailsModel,String rmDynamicStruc){
		return getPerPassangerRmConfigTripDetails(rmConfigTripDetailsModel, rmDynamicStruc);
	}
	public RmConfigTripDetailsModel  getCarPaxRmConfigTripDetails(RmConfigTripDetailsModel rmConfigTripDetailsModel,String rmDynamicStruc){
		return getPerPassangerRmConfigTripDetails(rmConfigTripDetailsModel, rmDynamicStruc);
	}
	public RmConfigTripDetailsModel  getBusPaxRmConfigTripDetails(RmConfigTripDetailsModel rmConfigTripDetailsModel,String rmDynamicStruc){
		return getPerPassangerRmConfigTripDetails(rmConfigTripDetailsModel, rmDynamicStruc);
	}
	public RmConfigTripDetailsModel  getTrainPaxRmConfigTripDetails(RmConfigTripDetailsModel rmConfigTripDetailsModel,String rmDynamicStruc){
		return getPerPassangerRmConfigTripDetails(rmConfigTripDetailsModel, rmDynamicStruc);
	}
	public RmConfigTripDetailsModel  getVisaPaxRmConfigTripDetails(RmConfigTripDetailsModel rmConfigTripDetailsModel,String rmDynamicStruc){
		return getPerPassangerRmConfigTripDetails(rmConfigTripDetailsModel, rmDynamicStruc);
	}
	
	public RmConfigTripDetailsModel  getInsurancePaxRmConfigTripDetails(RmConfigTripDetailsModel rmConfigTripDetailsModel,String rmDynamicStruc){
		return getPerPassangerRmConfigTripDetails(rmConfigTripDetailsModel, rmDynamicStruc);
	}
	public RmConfigTripDetailsModel  getMiscellaneousPaxRmConfigTripDetails(RmConfigTripDetailsModel rmConfigTripDetailsModel,String rmDynamicStruc){
		return getPerPassangerRmConfigTripDetails(rmConfigTripDetailsModel, rmDynamicStruc);
	}
	
	 
	public RmConfigTripDetailsModel  getPerPassangerRmConfigTripDetails(RmConfigTripDetailsModel rmConfigTripDetailsModel,String rmDynamicStruc){
		List<String> extraRmConfigFieldList=new ArrayList<>();
		Map<String,String> dynamicRmConfigMap=new LinkedHashMap<>();
		StringBuilder extraRmConfigDetails=new StringBuilder();
		String manualStringFields[] = rmDynamicStruc!= null && !rmDynamicStruc.equals("")? rmDynamicStruc.split(",") : null;
		if(manualStringFields!=null && manualStringFields.length>0){
			String fieldsName[]=null;
			for (String oneField : manualStringFields) {
				if(!oneField.trim().equalsIgnoreCase(""))
					fieldsName  = oneField.split(":");
				extraRmConfigFieldList.add(fieldsName[0]);

			}
		}
		if(rmConfigTripDetailsModel!=null){
			if(rmConfigTripDetailsModel.getApproverName()==null ||rmConfigTripDetailsModel.getApproverName().equals("")) 
				rmConfigTripDetailsModel.setApproverName("-");
			if(rmConfigTripDetailsModel.getBillNonBill()==null  || rmConfigTripDetailsModel.getBillNonBill().equals("")) 
				rmConfigTripDetailsModel.setBillNonBill("-");
			if(rmConfigTripDetailsModel.getBussinessUnit()==null  || rmConfigTripDetailsModel.getBussinessUnit().equals("")) 
				rmConfigTripDetailsModel.setBussinessUnit("-");
			if(rmConfigTripDetailsModel.getCostCenter()==null  || rmConfigTripDetailsModel.getCostCenter().equals("")) 
				rmConfigTripDetailsModel.setCostCenter("-");
			if(rmConfigTripDetailsModel.getDepartment()==null  || rmConfigTripDetailsModel.getDepartment().equals("")) 
				rmConfigTripDetailsModel.setDepartment("-");
			if(rmConfigTripDetailsModel.getEmpCode()==null  || rmConfigTripDetailsModel.getEmpCode().equals("")) 
				rmConfigTripDetailsModel.setEmpCode("-");
			if(rmConfigTripDetailsModel.getLocation()==null  || rmConfigTripDetailsModel.getLocation().equals("")) 
				rmConfigTripDetailsModel.setLocation("-");
			if(rmConfigTripDetailsModel.getProjectCode()==null  || rmConfigTripDetailsModel.getProjectCode().equals("")) 
				rmConfigTripDetailsModel.setProjectCode("-");
			if(rmConfigTripDetailsModel.getReasonForTravel()==null  || rmConfigTripDetailsModel.getReasonForTravel().equals("")) 
				rmConfigTripDetailsModel.setReasonForTravel("-");
			if(rmConfigTripDetailsModel.getTrNumber()==null  || rmConfigTripDetailsModel.getTrNumber().equals("")) 
				rmConfigTripDetailsModel.setTrNumber("-");
			String manualField1=rmConfigTripDetailsModel.getManualField1()!=null && !rmConfigTripDetailsModel.getManualField1().equals("")?rmConfigTripDetailsModel.getManualField1():"-";
			String manualField2=rmConfigTripDetailsModel.getManualField2()!=null &&  !rmConfigTripDetailsModel.getManualField2().equals("")?rmConfigTripDetailsModel.getManualField2():"-";
			String manualField3=rmConfigTripDetailsModel.getManualField3()!=null  && !rmConfigTripDetailsModel.getManualField3().equals("")?rmConfigTripDetailsModel.getManualField3():"-";
			String manualField4=rmConfigTripDetailsModel.getManualField4()!=null  && !rmConfigTripDetailsModel.getManualField4().equals("") ?rmConfigTripDetailsModel.getManualField4():"-";
			String manualField5=rmConfigTripDetailsModel.getManualField5()!=null && !rmConfigTripDetailsModel.getManualField5().equals("")?rmConfigTripDetailsModel.getManualField5():"-";
			if(extraRmConfigFieldList!=null && extraRmConfigFieldList.size()>0){
				if(extraRmConfigFieldList.size()==1){
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), manualField1);
					extraRmConfigDetails.append(extraRmConfigFieldList.get(0)+":"+manualField1);
				}
				if(extraRmConfigFieldList.size()==2){ 
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), manualField1);
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(1), manualField2);
					extraRmConfigDetails.append(extraRmConfigFieldList.get(0)+":"+manualField1+"/"+extraRmConfigFieldList.get(1)+":"+manualField2);
				}
				if(extraRmConfigFieldList.size()==3){ 
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), manualField1);
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(1), manualField2);
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(2), manualField3);
					extraRmConfigDetails.append(extraRmConfigFieldList.get(0)+":"+manualField1+"/"+extraRmConfigFieldList.get(1)+":"+manualField2+"/"
							+extraRmConfigFieldList.get(2)+":"+manualField3);
				}
				if(extraRmConfigFieldList.size()==4){
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), manualField1);
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(1), manualField2);
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(2), manualField3);
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(3), manualField4);
					extraRmConfigDetails.append(extraRmConfigFieldList.get(0)+":"+manualField1+"/"+extraRmConfigFieldList.get(1)+":"+ manualField2+"/"
							+extraRmConfigFieldList.get(2)+":"+manualField3+"/"+extraRmConfigFieldList.get(3)+":"+ manualField4);
				}
				if(extraRmConfigFieldList.size()==5){
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), manualField1);
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(1), manualField2);
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(2), manualField3);
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(3), manualField4);
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(4), manualField5);
					extraRmConfigDetails.append(extraRmConfigFieldList.get(0)+":"+manualField1+"/"+extraRmConfigFieldList.get(1)+":"+manualField2+"/"
							+extraRmConfigFieldList.get(2)+":"+manualField3+"/"+extraRmConfigFieldList.get(3)+":"+manualField4+"/"
							+extraRmConfigFieldList.get(4)+":"+manualField5);
				}
				rmConfigTripDetailsModel.setRmconfigFieldsMap(dynamicRmConfigMap);
				rmConfigTripDetailsModel.setExtraRmConfigDetails(extraRmConfigDetails.toString());
			}
		}
		else{
			rmConfigTripDetailsModel= new RmConfigTripDetailsModel();
			rmConfigTripDetailsModel.setApproverName("-");
			rmConfigTripDetailsModel.setBillNonBill("-");
			rmConfigTripDetailsModel.setBussinessUnit("-");
			rmConfigTripDetailsModel.setCostCenter("-");
			rmConfigTripDetailsModel.setDepartment("-");
			rmConfigTripDetailsModel.setEmpCode("-");
			rmConfigTripDetailsModel.setLocation("-");
			rmConfigTripDetailsModel.setProjectCode("-");
			rmConfigTripDetailsModel.setReasonForTravel("-");
			rmConfigTripDetailsModel.setTrNumber("-");
			rmConfigTripDetailsModel.setManualField1("-");
			rmConfigTripDetailsModel.setManualField2("-");
			rmConfigTripDetailsModel.setManualField3("-");
			rmConfigTripDetailsModel.setManualField4("-");
			rmConfigTripDetailsModel.setManualField5("-");
			rmConfigTripDetailsModel.setExtraRmConfigDetails("-");
			if(extraRmConfigFieldList!=null && extraRmConfigFieldList.size()>0){
				if(extraRmConfigFieldList.size()==1){ 
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), "-");
				}
				if(extraRmConfigFieldList.size()==2){ 
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), "-");
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(1), "-");
				}
				if(extraRmConfigFieldList.size()==3){ 
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), "-");
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(1), "-");
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(2),"-");
				}
				if(extraRmConfigFieldList.size()==4){ 
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), "-");
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(1), "-");
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(2),"-");
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(3), "-");
				}
				if(extraRmConfigFieldList.size()==5){ 
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(0), "-");
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(1), "-");
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(2),"-");
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(3), "-");
					dynamicRmConfigMap.put(extraRmConfigFieldList.get(4), "-");
				}
				rmConfigTripDetailsModel.setRmconfigFieldsMap(dynamicRmConfigMap);
			}
		}
		return rmConfigTripDetailsModel;
	}
 
	public  RmConfigTripDetailsModel getLeadPersonRmDetails(List<HotelOrderGuest>  guestList) {
		RmConfigTripDetailsModel rmConfigTripDetailsModel=null;
		if(guestList!=null && guestList.size()>0) 
			for(HotelOrderGuest guest : guestList) 
				if (guest.getLeader()){ 
					rmConfigTripDetailsModel=guest.getRmConfigTripDetailsModel();
					break;
				}
		return rmConfigTripDetailsModel;
	}


}
