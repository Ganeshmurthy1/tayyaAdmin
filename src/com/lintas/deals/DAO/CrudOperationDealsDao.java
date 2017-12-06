package com.lintas.deals.DAO;

import java.util.List;

import com.lintas.admin.model.CrudOperationDeals;
import com.lintas.utility.InvoiceFilter;
public interface CrudOperationDealsDao {
	public CrudOperationDeals saveOrUpdateDeals(CrudOperationDeals deals);
	public List<CrudOperationDeals> listOfDeals();
	public CrudOperationDeals DealsById(CrudOperationDeals dealId);
	public boolean deleteDeal(Long dealId);
	public CrudOperationDeals UpdateDeals(CrudOperationDeals userId);
	public CrudOperationDeals GetCmsDetails (Long id);
	public CrudOperationDeals updateCmsActiveOrInactive(CrudOperationDeals cms);
	public List<CrudOperationDeals> filterDelasByDealType(InvoiceFilter filterParams);
	public CrudOperationDeals updatDealImagepath(CrudOperationDeals deals);
	
}
