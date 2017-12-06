package com.admin.ageing.report.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.admin.ageing.report.dto.AgeingReportVO;
import com.admin.ageing.report.dto.OrderRowDTO;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.common.dsr.CommonDsrDao;
import com.common.dsr.CommonDsrPage;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.config.HibernateUtil;

public class AgeingReportDaoImpl implements AgeingReportDao {
	CommonDsrDao commonDsrDao =new CommonDsrDao();
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AgeingReportDaoImpl.class);
	@Override
	public List<OrderRowDTO> loadFlightUnKnockOffReports(CommonDsrPage commonDsrPage) {
		// TODO Auto-generated method stub
		Session session=null;
		List<OrderRowDTO> list=null;
		List<FlightOrderRow> listnew=null;
		try
		{
			List<String> ids=new LinkedList<>();
			ids.add("7");
			ids.add("2");
			ids.add("8");
			session=HibernateUtil.getSessionFactory().openSession();
			Conjunction conjunction=Restrictions.conjunction();
			ProjectionList projectionList=Projections.projectionList();
			projectionList.add(Projections.property("finalPrice"),"finalPrice");
			projectionList.add(Projections.property("createdAt"),"createdAt");
			projectionList.add(Projections.property("gstOnFlights"),"gstOnFlights"); 
			projectionList.add(Projections.property("companyId"),"companyId"); 
			projectionList.add(Projections.property("flightOrderRowServiceTax"),"flightOrderRowServiceTax"); 
			projectionList.add(Projections.property("flightOrderRowGstTax"),"flightOrderRowGstTax");
			Criteria criteria=session.createCriteria(FlightOrderRow.class);
			conjunction.add(Restrictions.in("companyId",ids));
			conjunction.add(Restrictions.eq("knockOff",false));
			criteria.add(conjunction);
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(Transformers.aliasToBean(OrderRowDTO.class));
			list=criteria.list();
		}
		catch(HibernateException he){
			logger.error("--------------HibernateException-----------------"+he.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;
	}



	/*@Override
	public List<FlightOrderRow> loadFlightUnKnockOffReports(CommonDsrPage commonDsrPage) {
		// TODO Auto-generated method stub
		Session session=null;
		List<FlightOrderRow> list=null;
		try
		{
			session=HibernateUtil.getSessionFactory().openSession();
			Conjunction conjunction=Restrictions.conjunction();
			Criteria criteria=session.createCriteria(FlightOrderRow.class);
			if(commonDsrPage!=null) 
			{
			CommonDsrFilters commonDsrFilters = commonDsrPage.getCommonDsrFilters();
			List<String>  companyIdList =null;
			if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") || commonDsrFilters.getTravelType().equalsIgnoreCase("F")){
				Company selectedCompany=new Company();
				if(commonDsrFilters.getCompanyUserId()!=null && commonDsrFilters.getCompanyUserId().equals("")) 
					companyIdList =commonDsrDao.getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
				else{
					selectedCompany.setCompany_userid(commonDsrFilters.getCompanyUserId());
					companyIdList =commonDsrDao.getCompanyIdList(selectedCompany,1);
				}

			}
			else if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") || commonDsrFilters.getTravelType().equalsIgnoreCase("F") && (commonDsrFilters.getCompanyUserId()!=null && !commonDsrFilters.getCompanyUserId().equals(""))){
				companyIdList = commonDsrDao.getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
			}
			conjunction.add(Restrictions.in("companyId",companyIdList));
			conjunction.add(Restrictions.eq("knockOff",false));
			criteria.add(conjunction);
			list=criteria.list();
			}

		}
		catch(HibernateException he){
			logger.info("HibernateException------------------"+he.getMessage());
		}
		catch (Exception e){
			logger.info("Exception------------------"+e.getMessage());
		}

		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;
	}*/
	@Override
	public List<HotelOrderRow> loadHotelUnKnockOffReports(CommonDsrPage commonDsrPage) {
		// TODO Auto-generated method stub
		Session session=null;
		List<HotelOrderRow> list=null;
		try
		{
			session=HibernateUtil.getSessionFactory().openSession();
			Conjunction conjunction=Restrictions.conjunction();
			Criteria criteria=session.createCriteria(HotelOrderRow.class);
			conjunction.add(Restrictions.eq("companyId","15"));
			conjunction.add(Restrictions.eq("knockOff",false));
			criteria.add(conjunction);
			list=criteria.list();
		}
		catch(HibernateException he){
			logger.info("HibernateException------------------"+he.getMessage());
		}
		catch (Exception e){
			logger.info("Exception------------------"+e.getMessage());
		}

		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;
	}

	@Override
	public List<CarOrderRow> loadCarUnKnockOffReports(CommonDsrPage commonDsrPage) {
		// TODO Auto-generated method stub
		Session session=null;
		List<CarOrderRow> list=null;
		try
		{
			session=HibernateUtil.getSessionFactory().openSession();
			Conjunction conjunction=Restrictions.conjunction();
			Criteria criteria=session.createCriteria(CarOrderRow.class);
			conjunction.add(Restrictions.eq("companyId","15"));
			conjunction.add(Restrictions.eq("knockOff",false));
			criteria.add(conjunction);
			list=criteria.list();
		}
		catch(HibernateException he){
			logger.info("HibernateException------------------"+he.getMessage());
		}
		catch (Exception e){
			logger.info("Exception------------------"+e.getMessage());
		}

		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;
	}

	@Override
	public List<BusOrderRow> loadBusUnKnockOffReports(CommonDsrPage commonDsrPage) {
		// TODO Auto-generated method stub
		Session session=null;
		List<BusOrderRow> list=null;
		try
		{
			session=HibernateUtil.getSessionFactory().openSession();
			Conjunction conjunction=Restrictions.conjunction();
			Criteria criteria=session.createCriteria(BusOrderRow.class);
			conjunction.add(Restrictions.eq("companyId","15"));
			conjunction.add(Restrictions.eq("knockOff",false));
			criteria.add(conjunction);
			list=criteria.list();
		}
		catch(HibernateException he){
			logger.info("HibernateException------------------"+he.getMessage());
		}
		catch (Exception e){
			logger.info("Exception------------------"+e.getMessage());
		}

		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;
	}

	@Override
	public List<TrainOrderRow> loadTrainUnKnockOffReports(CommonDsrPage commonDsrPage) {
		// TODO Auto-generated method stub
		Session session=null;
		List<TrainOrderRow> list=null;
		try
		{
			session=HibernateUtil.getSessionFactory().openSession();
			Conjunction conjunction=Restrictions.conjunction();
			Criteria criteria=session.createCriteria(TrainOrderRow.class);
			conjunction.add(Restrictions.eq("companyId","15"));
			conjunction.add(Restrictions.eq("knockOff",false));
			criteria.add(conjunction);
			list=criteria.list();
		}
		catch(HibernateException he){
			logger.info("HibernateException------------------"+he.getMessage());
		}
		catch (Exception e){
			logger.info("Exception------------------"+e.getMessage());
		}

		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;
	}

	@Override
	public List<VisaOrderRow> loadVisaUnKnockOffReports(CommonDsrPage commonDsrPage) {
		// TODO Auto-generated method stub
		Session session=null;
		List<VisaOrderRow> list=null;
		try
		{
			session=HibernateUtil.getSessionFactory().openSession();
			Conjunction conjunction=Restrictions.conjunction();
			Criteria criteria=session.createCriteria(VisaOrderRow.class);
			conjunction.add(Restrictions.eq("companyId","15"));
			conjunction.add(Restrictions.eq("knockOff",false));
			criteria.add(conjunction);
			list=criteria.list();
		}
		catch(HibernateException he){
			logger.info("HibernateException------------------"+he.getMessage());
		}
		catch (Exception e){
			logger.info("Exception------------------"+e.getMessage());
		}

		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;
	}

	@Override
	public List<InsuranceOrderRow> loadInsuranceUnKnockOffReports(CommonDsrPage commonDsrPage) {
		// TODO Auto-generated method stub
		Session session=null;
		List<InsuranceOrderRow> list=null;
		try
		{
			session=HibernateUtil.getSessionFactory().openSession();
			Conjunction conjunction=Restrictions.conjunction();
			Criteria criteria=session.createCriteria(InsuranceOrderRow.class);
			conjunction.add(Restrictions.eq("companyId","15"));
			conjunction.add(Restrictions.eq("knockOff",false));
			criteria.add(conjunction);
			list=criteria.list();
		}
		catch(HibernateException he){
			logger.info("HibernateException------------------"+he.getMessage());
		}
		catch (Exception e){
			logger.info("Exception------------------"+e.getMessage());
		}

		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;
	}

	@Override
	public List<MiscellaneousOrderRow> loadMiscellaneousUnKnockOffReports(CommonDsrPage commonDsrPage) {
		// TODO Auto-generated method stub
		Session session=null;
		List<MiscellaneousOrderRow> list=null;
		try
		{
			session=HibernateUtil.getSessionFactory().openSession();
			Conjunction conjunction=Restrictions.conjunction();
			Criteria criteria=session.createCriteria(MiscellaneousOrderRow.class);
			conjunction.add(Restrictions.eq("companyId",15));
			conjunction.add(Restrictions.eq("knockOff",false));
			criteria.add(conjunction);
			list=criteria.list();
		}
		catch(HibernateException he){
			logger.info("HibernateException------------------"+he.getMessage());
		}
		catch (Exception e){
			logger.info("Exception------------------"+e.getMessage());
		}

		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;
	}
	public AgeingReportVO getAgeingReportVO(CommonDsrPage commonDsrPage){
		List<OrderRowDTO> flightOrderRowDTOList=loadFlightUnKnockOffReports(null);
		List<AgeingReportVO> manupulateList=new LinkedList<>();
		List<AgeingReportVO> companyTypeList=new LinkedList<>();
		Set<String> monthHeadings=new LinkedHashSet<>();
		Map<String,List<AgeingReportVO>> companyTypeMap=new LinkedHashMap<>();
		Collections.sort(flightOrderRowDTOList, new Comparator<OrderRowDTO>() {
			@Override
			public int compare(OrderRowDTO o1, OrderRowDTO o2) {
				 return  o1.getCreatedAt() .compareTo(o2.getCreatedAt() );
			}
	    });
		if(flightOrderRowDTOList!=null && flightOrderRowDTOList.size()>0){
			for(OrderRowDTO flightOrderRowDTO:flightOrderRowDTOList){
				AgeingReportVO ageingReportVO=new AgeingReportVO();
				BigDecimal managementFee=new BigDecimal(0);
				BigDecimal convFee=new BigDecimal(0);
				BigDecimal gstOrServiceTax=new BigDecimal(0);
				Date date=flightOrderRowDTO.getCreatedAt();
				String companyName = null;
				String companyType = null;
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM yy");
				String format=simpleDateFormat.format(date);
				Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(flightOrderRowDTO.getCompanyId()));
				if(company!=null && company.getCompanyRole()!=null){
					if(company.getCompanyRole().isCorporate()){
						companyType="B2E";
					}
					else if(!company.getCompanyRole().isCorporate()){
						companyType="B2B";
					}
					companyName=company.getCompanyname();
					ageingReportVO.setCompanyType(companyType);
					ageingReportVO.setCompanyName(companyName);
				} 
				if(flightOrderRowDTO.getFlightOrderRowServiceTax()!=null){
					if(flightOrderRowDTO.getFlightOrderRowServiceTax().getManagementFee()!=null) 
						managementFee=flightOrderRowDTO.getFlightOrderRowServiceTax().getManagementFee();
					if(flightOrderRowDTO.getFlightOrderRowGstTax().getConvenienceFee()!=null) 
						convFee=flightOrderRowDTO.getFlightOrderRowGstTax().getConvenienceFee();
					gstOrServiceTax=flightOrderRowDTO.getGstOnFlights();
				}

				else if(flightOrderRowDTO.getFlightOrderRowGstTax()!=null){
					if(flightOrderRowDTO.getFlightOrderRowGstTax().getManagementFee()!=null) 
						managementFee=flightOrderRowDTO.getFlightOrderRowGstTax().getManagementFee();
					if(flightOrderRowDTO.getFlightOrderRowGstTax().getConvenienceFee()!=null) 
						convFee=flightOrderRowDTO.getFlightOrderRowGstTax().getConvenienceFee();

					gstOrServiceTax=flightOrderRowDTO.getGstOnFlights();
				}

				BigDecimal totalInvoiceAmount=flightOrderRowDTO.getFinalPrice().add(managementFee).add(convFee).add(gstOrServiceTax);
				ageingReportVO.setTotalInvoiceAmount(totalInvoiceAmount.setScale(2, BigDecimal.ROUND_UP));
				ageingReportVO.setMMMyyFormat(format);
				manupulateList.add(ageingReportVO) ;
				monthHeadings.add(ageingReportVO.getMMMyyFormat());
				/*companyNames.add(ageingReportVO.getCompanyName());*/
			}
		}
		 
		Collections.sort(manupulateList, new Comparator<AgeingReportVO>() {

			@Override
			public int compare(AgeingReportVO o1, AgeingReportVO o2) {
				 return  o2.getCompanyType() .compareTo(o1.getCompanyType() );
			}
			 
	    });
		
		
		AgeingReportVO ageingReportVO=new AgeingReportVO();
		if(manupulateList!=null && manupulateList.size()>0){
			for(AgeingReportVO  report:manupulateList){
				System.out.println(report.getCompanyType()+"----getCompanyName--"+report.getCompanyName()+"---getMMMyyFormat--"+report.getMMMyyFormat()+"-----total invoice amount----"+report.getTotalInvoiceAmount());
			}
		}
		ageingReportVO.setMonthHeadings(monthHeadings);
		return ageingReportVO;

	}
 
 
	 /*public static void main(String[] args) {
		AgeingReportDaoImpl ageingReportDaoImpl=new AgeingReportDaoImpl();
	 ageingReportDaoImpl.getAgeingReportVO(null);
	} */
}
