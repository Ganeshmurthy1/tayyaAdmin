package com.admin.hotel.fin.sheet.action;

import com.admin.common.quotation.dao.BusTravelRequestDao;
import com.admin.common.quotation.model.BusTravelRequestQuotation;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.model.InvoiceData;
import com.opensymphony.xwork2.ActionSupport;

public class BusOfflineBookingAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long orderId;
	private InvoiceData busOfflineVoucherData=null;
	BusOrderRow busOrderRow =new BusOrderRow();
	BusTravelRequestDao busTravelRequestDao=new BusTravelRequestDao();
	BusTravelRequestQuotation busQuotation=new BusTravelRequestQuotation();
	
	public String getBusOfflineVoucher(){
		BusOrderRow busOrderRow=busTravelRequestDao.getBusOrderRowDetailsById(orderId);
		if(busOrderRow!=null)
			setBusOrderRow(busOrderRow);

		return SUCCESS;
		
	}

	public InvoiceData getBusOfflineVoucherData() {
		return busOfflineVoucherData;
	}

	public void setBusOfflineVoucherData(InvoiceData busOfflineVoucherData) {
		this.busOfflineVoucherData = busOfflineVoucherData;
	}


	public BusOrderRow getBusOrderRow() {
		return busOrderRow;
	}

	public void setBusOrderRow(BusOrderRow busOrderRow) {
		this.busOrderRow = busOrderRow;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
}
