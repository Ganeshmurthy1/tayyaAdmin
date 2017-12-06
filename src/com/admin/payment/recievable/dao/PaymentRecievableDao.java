package com.admin.payment.recievable.dao;

import java.util.List;

import com.admin.payment.recievable.PaymentRecievable;
import com.isl.admin.page.CompanyFilterPage;

public interface PaymentRecievableDao {
public PaymentRecievable save(PaymentRecievable paymentRecievable);
public List<PaymentRecievable> list();
public   Boolean update(PaymentRecievable paymentRecievable);
public   PaymentRecievable updatePaymentFile(PaymentRecievable paymentRecievable);
public  PaymentRecievable  getDetails(Long id);
public CompanyFilterPage paymentReceivableList(CompanyFilterPage companyFilterPage);
public void updateAirKnockOff(Long orderRowId,boolean isKnockOff);
public void updateHotelKnockOff(Long orderRowId,boolean isKnockOff);
public void updateBusKnockOff(Long orderRowId,boolean isKnockOff);
public void updateCarKnockOff(Long orderRowId,boolean isKnockOff);
public void updateTrainKnockOff(Long orderRowId,boolean isKnockOff);
public void updateInsuranceKnockOff(Long orderRowId,boolean isKnockOff);
public void updateVisaKnockOff(Long orderRowId,boolean isKnockOff);
public void updateMiscellaneousKnockOff(Long orderRowId,boolean isKnockOff);
public PaymentRecievable  updatePaymentRefNo(PaymentRecievable paymentRecievable);
}
