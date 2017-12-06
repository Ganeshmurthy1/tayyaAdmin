package com.admin.dashboard.analysis.json.action;

public class DistributorAnalysisAction {

	/**
	 * author saumya
	 */
	private static final long serialVersionUID = 1L;
	/*SessionMap<String, Object> sessionMap;
	DistributorAndAgencyService distributorAndAgencyService=new DistributorAndAgencyService();
	DistributorAnalysisDao distributorAnalysisDao = new DistributorAnalysisDaoImpl();
	CompanyDAO companyDAO=new CompanyDAO();
	List<DistributorAnalysisDataVO> distributors=new ArrayList<>();
	Company company=null;
	 */
	/*public String distributorBookingCount() {
		Company sessionCompany = (Company) sessionMap.get("Company");
		List<String> companyUserId = new ArrayList<>();
		List<DistributorAnalysisDataVO> distributorAnalysisDataVOs=new ArrayList<>();
		
		if (sessionCompany == null) {
			ErrorMsg error = new ErrorMsg();
			error.setMessage("Session is expired.");
			// hotelData.setError(error);
			return SUCCESS;
		}
		List<Company> companies=companyDAO.getDistributorCompanyList();
		
		if(companies !=null && companies.size() >0){
			for (Company company : companies) {
				companyUserId.add(String.valueOf(company.getCompanyid()));
			}
		}
		if(companyUserId !=null && companyUserId.size()>0)
		distributorAnalysisDataVOs=distributorAndAgencyService.sortAndGetData(companyUserId);
		// companyUserId=distributorAnalysisDao.getAllCompanyUnderDistributor(sessionCompany);
		
		 int i=0;
		 if(distributorAnalysisDataVOs !=null){
			 
		
		 for (DistributorAnalysisDataVO distributorAnalysisDataVO : distributorAnalysisDataVOs) {
			 i++;
			 if(i<=5){
				 distributors.add(distributorAnalysisDataVO);
			 }
			
		}
	 }
		return SUCCESS;
	}

	

	public List<DistributorAnalysisDataVO> getDistributors() {
		return distributors;
	}



	public void setDistributors(List<DistributorAnalysisDataVO> distributors) {
		this.distributors = distributors;
	}



	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap = (SessionMap<String, Object>) map;
	}*/
	 
	 
}
