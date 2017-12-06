package com.lintas.utility;

import com.lintas.admin.model.RmConfigTripDetailsModel;

public class GetNewRmConfigDetail {

	public RmConfigTripDetailsModel getRmConfigDetail(RmConfigTripDetailsModel configTripDetailsModel,String ordertype){
		RmConfigTripDetailsModel rmConfigTripDetailsModel = new RmConfigTripDetailsModel();
		rmConfigTripDetailsModel.setApproverName(configTripDetailsModel.getApproverName());
		rmConfigTripDetailsModel.setBillNonBill(configTripDetailsModel.getBillNonBill());
		rmConfigTripDetailsModel.setBussinessUnit(configTripDetailsModel.getBussinessUnit());
		rmConfigTripDetailsModel.setCostCenter(configTripDetailsModel.getCostCenter());
		rmConfigTripDetailsModel.setDepartment(configTripDetailsModel.getDepartment());
		rmConfigTripDetailsModel.setEmpCode(configTripDetailsModel.getEmpCode());
		rmConfigTripDetailsModel.setLocation(configTripDetailsModel.getLocation());
		rmConfigTripDetailsModel.setManualField1(configTripDetailsModel.getManualField1());
		rmConfigTripDetailsModel.setManualField2(configTripDetailsModel.getManualField2());
		rmConfigTripDetailsModel.setManualField3(configTripDetailsModel.getManualField3());
		rmConfigTripDetailsModel.setManualField4(configTripDetailsModel.getManualField4());
		rmConfigTripDetailsModel.setManualField5(configTripDetailsModel.getManualField5());
		rmConfigTripDetailsModel.setOrderId(configTripDetailsModel.getOrderId());
		rmConfigTripDetailsModel.setOrdertype(ordertype);
		rmConfigTripDetailsModel.setTrNumber(configTripDetailsModel.getTrNumber());		
		rmConfigTripDetailsModel.setReasonForTravel(configTripDetailsModel.getReasonForTravel());
		rmConfigTripDetailsModel.setProjectCode(configTripDetailsModel.getProjectCode());		
		return rmConfigTripDetailsModel;
	}
	
}
