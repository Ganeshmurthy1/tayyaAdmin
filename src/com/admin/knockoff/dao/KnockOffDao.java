package com.admin.knockoff.dao;

import com.admin.knockoff.common.filter.page.KnockOffPage;
import com.admin.knockoff.common.filter.page.KnockOffUtility;

public interface KnockOffDao {
	public KnockOffPage flightKnockOffBookings(KnockOffPage knockOffPage);
	public KnockOffPage hotelKnockOffBookings(KnockOffPage knockOffPage);
	public KnockOffPage carKnockOffBookings(KnockOffPage knockOffPage);
	public KnockOffPage busKnockOffBookings(KnockOffPage knockOffPage);
	public KnockOffPage trainKnockOffBookings(KnockOffPage knockOffPage);
	public KnockOffPage visaKnockOffBookings(KnockOffPage knockOffPage);	
	public KnockOffPage insuranceKnockOffBookings(KnockOffPage knockOffPage);
	public KnockOffPage miscellaneousKnockOffBookings(KnockOffPage knockOffPage);
	public KnockOffPage  commonKnockOffBookings(KnockOffPage knockOffPage);
	public boolean  flightKnockOffUpdate(KnockOffUtility knockOffUtility);
	public boolean  hotelKnockOffUpdate(KnockOffUtility knockOffUtility);
	public boolean  carKnockOffUpdate(KnockOffUtility knockOffUtility);
	public boolean  busKnockOffUpdate(KnockOffUtility knockOffUtility);
	public boolean  trainKnockOffUpdate(KnockOffUtility knockOffUtility);
	public boolean  visaKnockOffUpdate(KnockOffUtility knockOffUtility);
	public boolean  insuranceKnockOffUpdate(KnockOffUtility knockOffUtility);
	public boolean  miscellaneousKnockOffUpdate(KnockOffUtility knockOffUtility);
	 
}
