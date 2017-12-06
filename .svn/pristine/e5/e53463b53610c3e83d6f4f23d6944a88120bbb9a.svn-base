/**
 * 
 */
package com.admin.lookbook.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.FlightLoookBook.Filter.LookBookFilterPage;
import com.FlightLoookBook.Filter.LookBookReportData;
import com.FlightLoookBook.Filter.LookBookReportFilter;
import com.admin.flight.fin.sheet.model.HotelandFlightDisReportProperty;
import com.admin.lookbook.model.BusBook;
import com.admin.lookbook.model.BusLook;
import com.admin.lookbook.model.BusLookBook;
import com.admin.lookbook.model.FlightBook;
import com.admin.lookbook.model.FlightLook;
import com.admin.lookbook.model.FlightLookBook;
import com.admin.lookbook.model.HotelBook;
import com.admin.lookbook.model.HotelLook;
import com.admin.lookbook.model.HotelLookBook;
import com.admin.lookbook.model.LookBookCustomerIPHistory;
import com.admin.lookbook.model.LookBookCustomerIPStatus;
import com.customerIp.filter.CustomerIpFilterPage;
import com.customerIp.filter.CustomerIpReportData;
import com.customerIp.filter.CustomerIpReportFilter;
import com.isl.admin.page.Page;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.CompanyRole;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

/**
 * @author : created by Manish Samrat and modified by basha
 * @createdAt : 17/07/2017
 * @version
 * @updateaBy   : Manish Samrat (25/07/2017), 
 */
public class LookBookDao {
	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LookBookDao.class);

	private InputStream fileInputStream;
	 private String fileName;
	public List<FlightBook> fetchFlightBookDetailById(int configId, int companyId) {
		Session session = null;
		Criteria criteria = null;
		List<FlightBook> list = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(FlightBook.class);
			criteria.add(Restrictions.eq("configId", configId));
			criteria.add(Restrictions.eq("companyId", companyId));
			list = criteria.list();

		} catch (HibernateException e) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return list;
	}

	public List<HotelBook> fetchHotelBookDetailById(int configId, int companyId) {
		Session session = null;
		Criteria criteria = null;
		List<HotelBook> list = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(HotelBook.class);
			criteria.add(Restrictions.eq("configId", configId));
			criteria.add(Restrictions.eq("companyId", companyId));
			list = criteria.list();

		} catch (HibernateException e) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return list;
	}

	public List<BusBook> fetchBusBookDetailById(int configId, int companyId) {
		Session session = null;
		Criteria criteria = null;
		List<BusBook> list = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(BusBook.class);
			criteria.add(Restrictions.eq("configId", configId));
			criteria.add(Restrictions.eq("companyId", companyId));
			list = criteria.list();

		} catch (HibernateException e) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return list;
	}

	public LookBookFilterPage getCompanyFlightLookBookReports(LookBookFilterPage lookBookFilterPage,
			Company companySessionObj) {
		List<LookBookReportData> reportData_list = new ArrayList<LookBookReportData>();
		int availablePages = 0;
		int availableItems = 0;
		String companyId = null;
		Session session = null;
		try {
			// 2016-06-28
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(FlightLookBook.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction disjunctionFlightOrderCustomer = Restrictions.disjunction();
			// To get total row count.
			if (lookBookFilterPage != null && lookBookFilterPage.isFilterEnabled()) {
				LookBookReportFilter lookBookReportFilter = lookBookFilterPage.getLookBookReportFilter();
				companyId = String.valueOf(lookBookReportFilter.getLoginCompany().getCompanyid());
				/*
				 * Add multiple condition separated by AND clause within
				 * brackets.
				 */
				List<String> companyIdList = new ArrayList<String>();
				FlightOrderDao flightOrderDao = new FlightOrderDao();
				companyIdList = flightOrderDao.getCompanyIdList(lookBookReportFilter.getLoginCompany(),
						lookBookReportFilter.getReportType(), lookBookReportFilter.getCompanyName());
				if (companyIdList == null || companyIdList.size() <= 0) {
					lookBookFilterPage.setAvailableItems(0);
					lookBookFilterPage.setItems(new ArrayList<LookBookReportData>());
					return lookBookFilterPage;
				}

				List<Integer> companyIdListNew = new LinkedList<>();
				if (companyIdList != null && companyIdList.size() > 0) {
					for (String id : companyIdList) {
						companyIdListNew.add(Integer.parseInt(id));
					}
				}
				reportConjunction.add(Restrictions.in("companyId", companyIdListNew));

				if (lookBookReportFilter.getCompanyId() > 0)
					reportConjunction.add(Restrictions.like("companyId", lookBookReportFilter.getCompanyId()));

				if(lookBookReportFilter.getCompanyType()!=null && !lookBookReportFilter.getCompanyType().equalsIgnoreCase("All")){
					try{
						List<Integer> configIdList  =new CompanyConfigDao().getCompanyConfigIdListByConfigType(lookBookReportFilter.getCompanyType());
						reportConjunction.add(Restrictions.in("configId",configIdList));
					}catch (Exception e) {
					}
				}

				criteria.add(reportConjunction);

			}
			Long rowCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------" + rowCount);
			List<FlightLookBook> list = null;
			if (rowCount > 0) {
				if (lookBookFilterPage.getMaxItems() == Page.ALL_ITEMS) {
					criteria = session.createCriteria(FlightLookBook.class);
					criteria.add(reportConjunction);
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					lookBookFilterPage.setAvailableItems(list.size());
					lookBookFilterPage.setAvailablePages(1);
				} else {
					if (lookBookFilterPage.isPagination()) {
						availableItems = rowCount.intValue();
						availablePages = (availableItems % lookBookFilterPage.getMaxItems() == 0)
								? (availableItems / lookBookFilterPage.getMaxItems())
								: ((availableItems / lookBookFilterPage.getMaxItems()) + 1);
						lookBookFilterPage.setAvailableItems(availableItems);
						lookBookFilterPage.setAvailablePages(availablePages);
					}
					int pageIndexDb = (lookBookFilterPage.getCurrentPageIndex() > 1)
							? lookBookFilterPage.getCurrentPageIndex() - 1 : 0;
					int itemIndex = pageIndexDb * lookBookFilterPage.getMaxItems();
					if (itemIndex <= rowCount) {
						criteria = session.createCriteria(FlightLookBook.class);
						criteria.add(reportConjunction);
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(lookBookFilterPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
					}
				}
				if (list != null && list.size() > 0) {
					for (FlightLookBook flightLookBook : list) {
						LookBookReportData reportData = new LookBookReportData();
						reportData.setCompanyName(flightLookBook.getCompanyName());
						reportData.setAppkey(flightLookBook.getAppkey());
						reportData.setTotalSearchCount(flightLookBook.getTotalSearchCount());
						reportData.setTotalBookedCount(flightLookBook.getTotalBookedCount());
						reportData.setConfigId(flightLookBook.getConfigId());
						reportData.setCompanyId(flightLookBook.getCompanyId());
						reportData.setId(flightLookBook.getId());

						reportData_list.add(reportData);

					}

					lookBookFilterPage.setItems(reportData_list);
				} else {
					lookBookFilterPage.setAvailableItems(0);
					lookBookFilterPage.setItems(new ArrayList<LookBookReportData>());
				}

			} else {
				lookBookFilterPage.setAvailableItems(0);
				lookBookFilterPage.setAvailablePages(0);
				lookBookFilterPage.setItems(new ArrayList<LookBookReportData>());
			}

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------" + e.getMessage());
		} catch (Exception e) {
			logger.error("--------------Exception-----------------" + e.getMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		// logger.info("--------reportData_list
		// size-------"+reportData_list.size());
		return lookBookFilterPage;
	}

	public LookBookFilterPage getCompanyHotelLookBookReports(LookBookFilterPage lookBookFilterPage,
			Company companySessionObj) {
		List<LookBookReportData> reportData_list = new ArrayList<LookBookReportData>();
		int availablePages = 0;
		int availableItems = 0;
		String companyId = null;
		Session session = null;
		try {
			// 2016-06-28
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(HotelLookBook.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction disjunctionFlightOrderCustomer = Restrictions.disjunction();
			// To get total row count.
			if (lookBookFilterPage != null && lookBookFilterPage.isFilterEnabled()) {
				LookBookReportFilter lookBookReportFilter = lookBookFilterPage.getLookBookReportFilter();
				companyId = String.valueOf(lookBookReportFilter.getLoginCompany().getCompanyid());
				/*
				 * Add multiple condition separated by AND clause within
				 * brackets.
				 */
				List<String> companyIdList = new ArrayList<String>();
				FlightOrderDao flightOrderDao = new FlightOrderDao();
				companyIdList = flightOrderDao.getCompanyIdList(lookBookReportFilter.getLoginCompany(),
						lookBookReportFilter.getReportType(), lookBookReportFilter.getCompanyName());
				if (companyIdList == null || companyIdList.size() <= 0) {
					lookBookFilterPage.setAvailableItems(0);
					lookBookFilterPage.setItems(new ArrayList<LookBookReportData>());
					return lookBookFilterPage;
				}

				List<Integer> companyIdListNew = new LinkedList<>();
				if (companyIdList != null && companyIdList.size() > 0) {
					for (String id : companyIdList) {
						companyIdListNew.add(Integer.parseInt(id));
					}
				}
				reportConjunction.add(Restrictions.in("companyId", companyIdListNew));

				if (lookBookReportFilter.getCompanyId() > 0)
					reportConjunction.add(Restrictions.like("companyId", lookBookReportFilter.getCompanyId()));
				if(lookBookReportFilter.getCompanyType()!=null && !lookBookReportFilter.getCompanyType().equalsIgnoreCase("All")){
					try{
						List<Integer> configIdList  =new CompanyConfigDao().getCompanyConfigIdListByConfigType(lookBookReportFilter.getCompanyType());
						reportConjunction.add(Restrictions.in("configId",configIdList));
					}catch (Exception e) {
					}
				}
				criteria.add(reportConjunction);

			}
			Long rowCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------" + rowCount);
			List<HotelLookBook> list = null;
			if (rowCount > 0) {
				if (lookBookFilterPage.getMaxItems() == Page.ALL_ITEMS) {
					criteria = session.createCriteria(HotelLookBook.class);
					criteria.add(reportConjunction);
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					lookBookFilterPage.setAvailableItems(list.size());
					lookBookFilterPage.setAvailablePages(1);
				} else {
					if (lookBookFilterPage.isPagination()) {
						availableItems = rowCount.intValue();
						availablePages = (availableItems % lookBookFilterPage.getMaxItems() == 0)
								? (availableItems / lookBookFilterPage.getMaxItems())
								: ((availableItems / lookBookFilterPage.getMaxItems()) + 1);
						lookBookFilterPage.setAvailableItems(availableItems);
						lookBookFilterPage.setAvailablePages(availablePages);
					}
					int pageIndexDb = (lookBookFilterPage.getCurrentPageIndex() > 1)
							? lookBookFilterPage.getCurrentPageIndex() - 1 : 0;
					int itemIndex = pageIndexDb * lookBookFilterPage.getMaxItems();
					if (itemIndex <= rowCount) {
						criteria = session.createCriteria(HotelLookBook.class);
						criteria.add(reportConjunction);
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(lookBookFilterPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
					}
				}
				if (list != null && list.size() > 0) {
					for (HotelLookBook hotelLookBook : list) {
						LookBookReportData reportData = new LookBookReportData();
						reportData.setCompanyName(hotelLookBook.getCompanyName());
						reportData.setAppkey(hotelLookBook.getAppkey());
						reportData.setTotalSearchCount(hotelLookBook.getTotalSearchCount());
						reportData.setTotalBookedCount(hotelLookBook.getTotalBookedCount());
						reportData.setConfigId(hotelLookBook.getConfigId());
						reportData.setCompanyId(hotelLookBook.getCompanyId());
						reportData.setId(hotelLookBook.getId());

						reportData_list.add(reportData);
					}
					lookBookFilterPage.setItems(reportData_list);
				} else {
					lookBookFilterPage.setAvailableItems(0);
					lookBookFilterPage.setItems(new ArrayList<LookBookReportData>());
				}

			} else {
				lookBookFilterPage.setAvailableItems(0);
				lookBookFilterPage.setAvailablePages(0);
				lookBookFilterPage.setItems(new ArrayList<LookBookReportData>());
			}

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------" + e.getMessage());
		} catch (Exception e) {
			logger.error("--------------Exception-----------------" + e.getMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		// logger.info("--------reportData_list
		// size-------"+reportData_list.size());
		return lookBookFilterPage;
	}

	public LookBookFilterPage getCompanyBusLookBookReports(LookBookFilterPage lookBookFilterPage,
			Company companySessionObj) {
		List<LookBookReportData> reportData_list = new ArrayList<LookBookReportData>();
		int availablePages = 0;
		int availableItems = 0;
		String companyId = null;
		Session session = null;
		try {
			// 2016-06-28
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(BusLookBook.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction disjunctionFlightOrderCustomer = Restrictions.disjunction();
			// To get total row count.
			if (lookBookFilterPage != null && lookBookFilterPage.isFilterEnabled()) {
				LookBookReportFilter lookBookReportFilter = lookBookFilterPage.getLookBookReportFilter();
				companyId = String.valueOf(lookBookReportFilter.getLoginCompany().getCompanyid());
				/*
				 * Add multiple condition separated by AND clause within
				 * brackets.
				 */
				List<String> companyIdList = new ArrayList<String>();
				FlightOrderDao flightOrderDao = new FlightOrderDao();
				companyIdList = flightOrderDao.getCompanyIdList(lookBookReportFilter.getLoginCompany(),
						lookBookReportFilter.getReportType(), lookBookReportFilter.getCompanyName());
				if (companyIdList == null || companyIdList.size() <= 0) {
					lookBookFilterPage.setAvailableItems(0);
					lookBookFilterPage.setItems(new ArrayList<LookBookReportData>());
					return lookBookFilterPage;
				}

				List<Integer> companyIdListNew = new LinkedList<>();
				if (companyIdList != null && companyIdList.size() > 0) {
					for (String id : companyIdList) {
						companyIdListNew.add(Integer.parseInt(id));
					}
				}
				reportConjunction.add(Restrictions.in("companyId", companyIdListNew));

				if (lookBookReportFilter.getCompanyId() > 0)
					reportConjunction.add(Restrictions.like("companyId", lookBookReportFilter.getCompanyId()));
				if(lookBookReportFilter.getCompanyType()!=null && !lookBookReportFilter.getCompanyType().equalsIgnoreCase("All")){
					try{
						List<Integer> configIdList  =new CompanyConfigDao().getCompanyConfigIdListByConfigType(lookBookReportFilter.getCompanyType());
						reportConjunction.add(Restrictions.in("configId",configIdList));
					}catch (Exception e) {
					}
				}
				criteria.add(reportConjunction);

			}
			Long rowCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------" + rowCount);
			List<BusLookBook> list = new ArrayList<BusLookBook>();
			if (rowCount > 0) {
				if (lookBookFilterPage.getMaxItems() == Page.ALL_ITEMS) {
					criteria = session.createCriteria(BusLookBook.class);
					criteria.add(reportConjunction);
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					lookBookFilterPage.setAvailableItems(list.size());
					lookBookFilterPage.setAvailablePages(1);
				} else {
					if (lookBookFilterPage.isPagination()) {
						availableItems = rowCount.intValue();
						availablePages = (availableItems % lookBookFilterPage.getMaxItems() == 0)
								? (availableItems / lookBookFilterPage.getMaxItems())
								: ((availableItems / lookBookFilterPage.getMaxItems()) + 1);
						lookBookFilterPage.setAvailableItems(availableItems);
						lookBookFilterPage.setAvailablePages(availablePages);
					}
					int pageIndexDb = (lookBookFilterPage.getCurrentPageIndex() > 1)
							? lookBookFilterPage.getCurrentPageIndex() - 1 : 0;
					int itemIndex = pageIndexDb * lookBookFilterPage.getMaxItems();
					if (itemIndex <= rowCount) {
						criteria = session.createCriteria(BusLookBook.class);
						criteria.add(reportConjunction);
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(lookBookFilterPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
					}
				}
				if (list != null && list.size() > 0) {
					for (BusLookBook busLookBook : list) {
						LookBookReportData reportData = new LookBookReportData();
						reportData.setCompanyName(busLookBook.getCompanyName());
						reportData.setAppkey(busLookBook.getAppkey());
						reportData.setTotalSearchCount(busLookBook.getTotalSearchCount());
						reportData.setTotalBookedCount(busLookBook.getTotalBookedCount());
						reportData.setConfigId(busLookBook.getConfigId());
						reportData.setCompanyId(busLookBook.getCompanyId());
						reportData.setId(busLookBook.getId());

						reportData_list.add(reportData);
					}
					lookBookFilterPage.setItems(reportData_list);
				} else {
					lookBookFilterPage.setAvailableItems(0);
					lookBookFilterPage.setItems(new ArrayList<LookBookReportData>());
				}

			} else {
				lookBookFilterPage.setAvailableItems(0);
				lookBookFilterPage.setAvailablePages(0);
				lookBookFilterPage.setItems(new ArrayList<LookBookReportData>());
			}

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------" + e.getMessage());
		} catch (Exception e) {
			logger.error("--------------Exception-----------------" + e.getMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		// logger.info("--------reportData_list
		// size-------"+reportData_list.size());
		return lookBookFilterPage;
	}

	public List<FlightLook> fetchFlightLookDetailById(int configId, int companyId) {
		Session session = null;
		Criteria criteria = null;
		List<FlightLook> list = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(FlightLook.class);
			criteria.add(Restrictions.eq("configId", configId));
			criteria.add(Restrictions.eq("companyId", companyId));
			list = criteria.list();

		} catch (HibernateException e) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return list;
	}

	public List<HotelLook> fetchHotelLookDetailById(int configId, int companyId) {
		Session session = null;
		Criteria criteria = null;
		List<HotelLook> list = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(HotelLook.class);
			criteria.add(Restrictions.eq("configId", configId));
			criteria.add(Restrictions.eq("companyId", companyId));
			list = criteria.list();

		} catch (HibernateException e) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return list;
	}

	public List<BusLook> fetchBusLookDetailById(int configId, int companyId) {
		Session session = null;
		Criteria criteria = null;
		List<BusLook> list = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(BusLook.class);
			criteria.add(Restrictions.eq("configId", configId));
			criteria.add(Restrictions.eq("companyId", companyId));
			list = criteria.list();

		} catch (HibernateException e) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return list;
	}

	public List<Long> fetchCompanyIdListFromCompanyRolexxxxxxxx(String roleType) {
		Session session = null;
		Criteria criteria = null;
		List<Long> list = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(CompanyRole.class);

			if (roleType.equalsIgnoreCase("B2B")) {
				criteria.add(Restrictions.eq("isAgent", true));
				criteria.add(Restrictions.eq("isDistributor", true));
			}
			if (roleType.equalsIgnoreCase("B2E"))
				criteria.add(Restrictions.eq("isCorporate", true));

			if (roleType.equalsIgnoreCase("B2C"))
				criteria.add(Restrictions.eq("isSuperUser", true));

			criteria.setProjection(Projections.property("proName"));

			list = criteria.list();

		} catch (HibernateException e) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return list;
	}

	public CompanyConfig fetchCompanyIdListFromCompanyConfig(int configId) {
		Session session = null;
		Criteria criteria = null;
		CompanyConfig list = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(CompanyConfig.class);
			criteria.add(Restrictions.eq("config_id", configId));
			list = (CompanyConfig) criteria.uniqueResult();

		} catch (HibernateException e) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return list;
	}

	public String verifyRoleType(CompanyConfig companyConfig) {
		String type = null;
		if (companyConfig.getCompanyConfigType().isWhitelable())
			type = "WL";
		if (companyConfig.getCompanyConfigType().isB2B())
			type = "B2B";
		if (companyConfig.getCompanyConfigType().isB2C())
			type = "B2C";
		if (companyConfig.getCompanyConfigType().isB2E())
			type = "B2E";

		return type;
	}

	public CustomerIpFilterPage fetchAllIpListFromIpStatus(CustomerIpFilterPage lookBookFilterPage,
			Company companySessionObj) {
		List<CustomerIpReportData> reportData_list = new ArrayList<CustomerIpReportData>();
		int availablePages = 0;
		int availableItems = 0;
		String companyId = null;
		Session session = null;
		try {
			// 2017-07-24
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(LookBookCustomerIPStatus.class);
			Conjunction reportConjunction = Restrictions.conjunction();
//			Disjunction disjunctionFlightOrderCustomer = Restrictions.disjunction();
			// To get total row count.
			if (lookBookFilterPage != null && lookBookFilterPage.isFilterEnabled()) {
				CustomerIpReportFilter lookBookReportFilter = lookBookFilterPage.getCustomerIpReportFilter();
				companyId = String.valueOf(lookBookReportFilter.getLoginCompany().getCompanyid());
				
				List<String> companyIdList = new ArrayList<String>();
				FlightOrderDao flightOrderDao = new FlightOrderDao();
				companyIdList = flightOrderDao.getCompanyIdList(lookBookReportFilter.getLoginCompany(),
						lookBookReportFilter.getReportType(), lookBookReportFilter.getCompanyName());
				if (companyIdList == null || companyIdList.size() <= 0) {
					lookBookFilterPage.setAvailableItems(0);
					lookBookFilterPage.setItems(new ArrayList<CustomerIpReportData>());
					return lookBookFilterPage;
				}

				List<Integer> companyIdListNew = new LinkedList<>();
				if (companyIdList != null && companyIdList.size() > 0) {
					for (String id : companyIdList) {
						companyIdListNew.add(Integer.parseInt(id));
					}
				}
				reportConjunction.add(Restrictions.in("companyId", companyIdListNew));

				if (lookBookReportFilter.getCompanyId() > 0)
					reportConjunction.add(Restrictions.like("companyId", lookBookReportFilter.getCompanyId()));
				if (lookBookReportFilter.getIp()!=null && !lookBookReportFilter.getIp().equals("") )
					reportConjunction.add(Restrictions.like("ip", lookBookReportFilter.getIp()));
				
				if(lookBookReportFilter.getCompanyType()!=null && !lookBookReportFilter.getCompanyType().equalsIgnoreCase("All")){
					try{
						List<Integer> configIdList  =new CompanyConfigDao().getCompanyConfigIdListByConfigType(lookBookReportFilter.getCompanyType());
						reportConjunction.add(Restrictions.in("configId",configIdList));
					}catch (Exception e) {
					}
				}
				
				criteria.add(reportConjunction);

			}
			
			
			Long rowCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------" + rowCount);
			List<LookBookCustomerIPStatus> list = null;
			if (rowCount > 0) {
				if (lookBookFilterPage.getMaxItems() == Page.ALL_ITEMS) {
					criteria = session.createCriteria(LookBookCustomerIPStatus.class);
					criteria.add(reportConjunction);
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					lookBookFilterPage.setAvailableItems(list.size());
					lookBookFilterPage.setAvailablePages(1);
				} else {
					if (lookBookFilterPage.isPagination()) {
						availableItems = rowCount.intValue();
						availablePages = (availableItems % lookBookFilterPage.getMaxItems() == 0)
								? (availableItems / lookBookFilterPage.getMaxItems())
								: ((availableItems / lookBookFilterPage.getMaxItems()) + 1);
						lookBookFilterPage.setAvailableItems(availableItems);
						lookBookFilterPage.setAvailablePages(availablePages);
					}
					int pageIndexDb = (lookBookFilterPage.getCurrentPageIndex() > 1)
							? lookBookFilterPage.getCurrentPageIndex() - 1 : 0;
					int itemIndex = pageIndexDb * lookBookFilterPage.getMaxItems();
					if (itemIndex <= rowCount) {
						criteria = session.createCriteria(LookBookCustomerIPStatus.class);
						criteria.add(reportConjunction);
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(lookBookFilterPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
					}
				}
				if (list != null && list.size() > 0) {
					for (LookBookCustomerIPStatus ipStatus : list) {
						CustomerIpReportData reportData = new CustomerIpReportData();
						reportData.setIp(ipStatus.getIp());
						reportData.setLastDate(ipStatus.getLastDate());
						reportData.setTotalSearchCount(ipStatus.getTotalSearchCount());
						reportData.setTotalBookedCount(ipStatus.getTotalBookedCount());
						reportData.setStartDate(ipStatus.getStartDate());
						reportData.setBlockStatus(ipStatus.isBlockStatus());
						reportData.setB2cFlag(ipStatus.isB2cFlag());
						reportData.setCompanyId(ipStatus.getCompanyId());
						reportData.setConfigId(ipStatus.getConfigId());
						
						reportData.setCompanyName(ipStatus.getCompanyName()!=null?ipStatus.getCompanyName():"-");
						reportData.setConfigName(ipStatus.getConfigName()!=null?ipStatus.getConfigName():"-");
						reportData.setConfigType(ipStatus.getConfigType()!=null?ipStatus.getConfigType():"-");
						
						reportData.setId(ipStatus.getId());
						reportData_list.add(reportData);
					}
					lookBookFilterPage.setItems(reportData_list);
					
				} else {
					lookBookFilterPage.setAvailableItems(0);
					lookBookFilterPage.setItems(new ArrayList<CustomerIpReportData>());
				}

			} else {
				lookBookFilterPage.setAvailableItems(0);
				lookBookFilterPage.setAvailablePages(0);
				lookBookFilterPage.setItems(new ArrayList<CustomerIpReportData>());
			}
		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------" + e.getMessage());
		} catch (Exception e) {
			logger.error("--------------Exception-----------------" + e.getMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return lookBookFilterPage;
	}
	
	public LookBookCustomerIPStatus fetchIpDetailsById(long id) {
		Session session = null;
		Criteria criteria = null;
		LookBookCustomerIPStatus ipStatus = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(LookBookCustomerIPStatus.class);
			criteria.add(Restrictions.eq("id", id));
			ipStatus = (LookBookCustomerIPStatus) criteria.uniqueResult();

		} catch (HibernateException e) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return ipStatus;
	}
	public List<LookBookCustomerIPStatus> fetchIpDetailsForEcellDownload(String ip, String companyType,int configId) {
		Session session = null;
		Criteria criteria = null;
		List<LookBookCustomerIPStatus> list = null;
		List<LookBookCustomerIPStatus> updatedList = new ArrayList<LookBookCustomerIPStatus>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(LookBookCustomerIPStatus.class);
			Conjunction conjunction=Restrictions.conjunction();
			if(ip!=null && !ip.equals(""))
			conjunction.add(Restrictions.eq("ip", ip));
			criteria.add(conjunction);
			
			list = criteria.list();
			for(LookBookCustomerIPStatus ipStatus:list){
				if(companyType!=null && !companyType.equals("")){
					CompanyConfig companyConfig = fetchCompanyIdListFromCompanyConfig(ipStatus.getConfigId());
					if (companyConfig != null) {
						if (companyType.equalsIgnoreCase(verifyRoleType(companyConfig))) {
							updatedList.add(ipStatus);
						}
					}
				}
			}
			if(updatedList.size()>0){
				list=null;
				list=updatedList;
			}
		} catch (HibernateException e) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return list;
	}
	public CustomerIpFilterPage fetchAllIpListFromIpHistory(CustomerIpFilterPage lookBookFilterPage,
			Company companySessionObj) {
		List<CustomerIpReportData> reportData_list = new ArrayList<CustomerIpReportData>();
		int availablePages = 0;
		int availableItems = 0;
		String companyId = null;
		Session session = null;
		String CompanyType = null;
		try {
			// 2017-07-24
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(LookBookCustomerIPHistory.class);
			Conjunction reportConjunction = Restrictions.conjunction();
//			Disjunction disjunctionFlightOrderCustomer = Restrictions.disjunction();
			// To get total row count.
			if (lookBookFilterPage != null && lookBookFilterPage.isFilterEnabled()) {
				CustomerIpReportFilter lookBookReportFilter = lookBookFilterPage.getCustomerIpReportFilter();
				companyId = String.valueOf(lookBookReportFilter.getLoginCompany().getCompanyid());
				
				List<String> companyIdList = new ArrayList<String>();
				FlightOrderDao flightOrderDao = new FlightOrderDao();
				companyIdList = flightOrderDao.getCompanyIdList(lookBookReportFilter.getLoginCompany(),
						lookBookReportFilter.getReportType(), lookBookReportFilter.getCompanyName());
				if (companyIdList == null || companyIdList.size() <= 0) {
					lookBookFilterPage.setAvailableItems(0);
					lookBookFilterPage.setItems(new ArrayList<CustomerIpReportData>());
					return lookBookFilterPage;
				}

				List<Integer> companyIdListNew = new LinkedList<>();
				if (companyIdList != null && companyIdList.size() > 0) {
					for (String id : companyIdList) {
						companyIdListNew.add(Integer.parseInt(id));
					}
				}
				reportConjunction.add(Restrictions.in("companyId", companyIdListNew));

				if (lookBookReportFilter.getCompanyId() > 0)
					reportConjunction.add(Restrictions.like("companyId", lookBookReportFilter.getCompanyId()));
				if (lookBookReportFilter.getIp()!=null && !lookBookReportFilter.getIp().equals("") )
					reportConjunction.add(Restrictions.like("ip", lookBookReportFilter.getIp()));
				
				if(lookBookReportFilter.getCompanyType()!=null && !lookBookReportFilter.getCompanyType().equalsIgnoreCase("All")){
					try{
						List<Integer> configIdList  =new CompanyConfigDao().getCompanyConfigIdListByConfigType(lookBookReportFilter.getCompanyType());
						reportConjunction.add(Restrictions.in("configId",configIdList));
					}catch (Exception e) {
					}
				}
				
				criteria.add(reportConjunction);

			}
			
			
			Long rowCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------" + rowCount);
			List<LookBookCustomerIPHistory> list = null;
			if (rowCount > 0) {
				if (lookBookFilterPage.getMaxItems() == Page.ALL_ITEMS) {
					criteria = session.createCriteria(LookBookCustomerIPHistory.class);
					criteria.add(reportConjunction);
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					lookBookFilterPage.setAvailableItems(list.size());
					lookBookFilterPage.setAvailablePages(1);
				} else {
					if (lookBookFilterPage.isPagination()) {
						availableItems = rowCount.intValue();
						availablePages = (availableItems % lookBookFilterPage.getMaxItems() == 0)
								? (availableItems / lookBookFilterPage.getMaxItems())
								: ((availableItems / lookBookFilterPage.getMaxItems()) + 1);
						lookBookFilterPage.setAvailableItems(availableItems);
						lookBookFilterPage.setAvailablePages(availablePages);
					}
					int pageIndexDb = (lookBookFilterPage.getCurrentPageIndex() > 1)
							? lookBookFilterPage.getCurrentPageIndex() - 1 : 0;
					int itemIndex = pageIndexDb * lookBookFilterPage.getMaxItems();
					if (itemIndex <= rowCount) {
						criteria = session.createCriteria(LookBookCustomerIPHistory.class);
						criteria.add(reportConjunction);
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(lookBookFilterPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
					}
				}
				if (list != null && list.size() > 0) {
					for (LookBookCustomerIPHistory ipStatus : list) {
						CustomerIpReportData reportData = new CustomerIpReportData();
						reportData.setIp(ipStatus.getIp());
						reportData.setLastDate(ipStatus.getLastDate());
						reportData.setTotalSearchCount(ipStatus.getTotalSearchCount());
						reportData.setTotalBookedCount(ipStatus.getTotalBookedCount());
						reportData.setStartDate(ipStatus.getStartDate());
						reportData.setB2cFlag(ipStatus.isB2cFlag());
						reportData.setCompanyId(ipStatus.getCompanyId());
						reportData.setConfigId(ipStatus.getConfigId());
						reportData.setCompanyName(ipStatus.getCompanyName()!=null?ipStatus.getCompanyName():"-");
						reportData.setConfigName(ipStatus.getConfigName()!=null?ipStatus.getConfigName():"-");
						reportData.setConfigType(ipStatus.getConfigType()!=null?ipStatus.getConfigType():"-");
						reportData.setId(ipStatus.getId());
						reportData_list.add(reportData);
					}
					lookBookFilterPage.setItems(reportData_list);
					
				} else {
					lookBookFilterPage.setAvailableItems(0);
					lookBookFilterPage.setItems(new ArrayList<CustomerIpReportData>());
				}

			} else {
				lookBookFilterPage.setAvailableItems(0);
				lookBookFilterPage.setAvailablePages(0);
				lookBookFilterPage.setItems(new ArrayList<CustomerIpReportData>());
			}
		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------" + e.getMessage());
		} catch (Exception e) {
			logger.error("--------------Exception-----------------" + e.getMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return lookBookFilterPage;
	}
	public String resetIpStatusById(long id){
		Session session = null;
		Transaction transaction=null;
		LookBookCustomerIPStatus customerIPStatusDelete=null;
		try{
				session = HibernateUtil.getSessionFactory().openSession();
				customerIPStatusDelete=(LookBookCustomerIPStatus) session.get(LookBookCustomerIPStatus.class, id);
				transaction=session.beginTransaction();
				session.delete(customerIPStatusDelete);
				transaction.commit();
		}
		catch (Exception e) {
			transaction.rollback();
		}
		finally {
			if(session != null && session.isOpen())
			{				
				session.close(); 
			}
		}
		return "";
	}
	public String resetIpHistoryById(long id){
		Session session = null;
		Transaction transaction=null;
		LookBookCustomerIPHistory customerIPHistoryDelete=null;
		try{
				session = HibernateUtil.getSessionFactory().openSession();
				customerIPHistoryDelete=(LookBookCustomerIPHistory) session.get(LookBookCustomerIPHistory.class, id);
				transaction=session.beginTransaction();
				session.delete(customerIPHistoryDelete);
				transaction.commit();
		}
		catch (Exception e) {
			transaction.rollback();
		}
		finally {
			if(session != null && session.isOpen())
			{				
				session.close(); 
			}
		}
		return "";
	}
	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
