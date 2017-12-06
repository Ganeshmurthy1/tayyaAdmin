package com.admin.knockoff.dao;

import java.util.List;

import com.admin.payment.recievable.PaymentKnockOffRow;
import com.admin.payment.recievable.PaymentKnockOffRowTx;

public interface PaymentKnockDao {
	public PaymentKnockOffRow  save (PaymentKnockOffRow paymentKnockOffRow);
	public PaymentKnockOffRowTx  save(PaymentKnockOffRowTx paymentKnockOffRowTx);
	public List<PaymentKnockOffRow> fetchPaymentKnockOffRowList(Integer companyId);
	public  PaymentKnockOffRow  fetchPaymentKnockOffRow(String  orderId,Integer companyId);
	public void updateOrderRows(String invoiveNo,boolean isKnockOff);
	public List<PaymentKnockOffRowTx> fetchPaymentKnockOffRowTxs(String BRV);
	 
}
