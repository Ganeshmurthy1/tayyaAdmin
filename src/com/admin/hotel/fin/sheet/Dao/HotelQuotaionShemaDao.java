package com.admin.hotel.fin.sheet.Dao;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.admin.hotel.fin.sheet.model.HotelQuotationPerTravelRequestSchema;
import com.admin.hotel.fin.sheet.model.HotelQuotationSchema;
import com.lintas.config.HibernateUtil;
public class HotelQuotaionShemaDao {
	public  HotelQuotationSchema saveHotelQuotationSchema(HotelQuotationSchema hotelQuotationSchema){
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(hotelQuotationSchema);
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			e.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return hotelQuotationSchema;
	}
	public  static HotelQuotationPerTravelRequestSchema saveHotelQuotationPerTravelRequestSchema(HotelQuotationPerTravelRequestSchema hotelQuotationPerTravelRequestSchema){
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(hotelQuotationPerTravelRequestSchema);
			transaction.commit();
		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			e.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return hotelQuotationPerTravelRequestSchema;
	}
	public   List<HotelQuotationSchema> getHotelQuotationSchemaList(int companyId){
		Session session= null;
		List<HotelQuotationSchema> hotelQuotationSchemalist=new LinkedList<>(); 
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelQuotationSchema.class);
			criteria.add(Restrictions.eq("companyId", companyId));
			HotelQuotationSchema newObj= (HotelQuotationSchema) criteria.uniqueResult(); 
			if(newObj!=null){
				String date=newObj.getSchemaData(); 
				StringTokenizer tok=new StringTokenizer(date, "[<#\\#>]");
				List<String> addList=  new LinkedList<>();
				while(tok.hasMoreTokens()){
					String token=tok.nextToken();
					addList.add(token.trim());
				}
				Iterator<String> itr=addList.iterator();
				String[] partsTest=null;
				while (itr.hasNext()) {
					String s=itr.next();
					partsTest=s.split("\\(\\|\\)");
					hotelQuotationSchemalist.add(new HotelQuotationSchema(partsTest[0], partsTest[1],Integer.parseInt(partsTest[3]), partsTest[4], partsTest[5],partsTest[2]));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return hotelQuotationSchemalist;
	}


	public  HotelQuotationSchema  getHotelQuotationSchema(int companyId){
		Session session= null;
		HotelQuotationSchema newObj=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelQuotationSchema.class);
			criteria.add(Restrictions.eq("companyId", companyId));
			newObj= (HotelQuotationSchema) criteria.uniqueResult(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return newObj;
	}









	/*public static void main(String[] args) {
		String date="<Hotel Name:text:1:fixed><Hotel Category:text:2:fixed><Hotel Address:longText:3:fixed><Hotel City:text:4:fixed><Hotel Country:select:5:fixed><Project Address:text:6:fixed><Distance from Work:text:7:fixed><Room Type:text:8:fixed><CheckInDate:text:9:fixed><CheckInTime:text:10:fixed><CheckOutDate:text:11:fixed><CheckOutTime:text:12:fixed><Room Count:int:13:fixed><Adult Count:int:14:fixed><Child Count:int:15:fixed><RoomRate/Night:text:16:fixed><Payment Option(s):multiSelect:17:fixed><Taxes:select:18:fixed><Breakfast:select:19:fixed><Internet:select:20:fixed><Cancellation Policy:longText:21:fixed><Prefer Property:select:22:fixed>";
		StringTokenizer tok=new StringTokenizer(date, "[<\\>]");
		List<String > list=  new LinkedList<>();
		List<HotelQuotationSchema> hotelQuotationSchemalist=new LinkedList<>();
		while(tok.hasMoreTokens()){
			String token=tok.nextToken();
			list.add(token.trim());
		}

		Iterator<String> itr=list.iterator();
		String[] partsTest=null;
		while (itr.hasNext()) {
			String s=itr.next();
			logger.info(s);
			partsTest=s.split(":");
			logger.info("partsTest length:-"+partsTest.length);
			logger.info(partsTest[0]);
			hotelQuotationSchemalist.add(new HotelQuotationSchema(partsTest[0], partsTest[1],Integer.parseInt( partsTest[2]), partsTest[3]) );

		}
		logger.info("hotelQuotationSchemalist.size()------"+hotelQuotationSchemalist.size());
	}
	 */
/*	 
public static void main(String[] args) {
	HotelQuotationSchema hotelQuotationSchema=new HotelQuotationSchema();
	HotelQuotationPerTravelRequestSchema saveHotelQuotationPerTravelRequestSchema(HotelQuotationPerTravelRequestSchema hotelQuotationPerTravelRequestSchema){
	hotelQuotationSchema.setCompanyId(1);
	hotelQuotationSchema.setCreatedAt(new Timestamp(new Date().getTime()));
	hotelQuotationSchema.setSchemaData("<Hotel Name:text:1:fixed><Hotel Category:text:2:fixed><Hotel Address:longText:3:fixed><Hotel City:text:4:fixed><Hotel Country:select:5:fixed><Project Address:text:6:fixed><Distance from Work:text:7:fixed><Room Type:text:8:fixed><CheckInDate:text:9:fixed><CheckInTime:text:10:fixed><CheckOutDate:text:11:fixed><CheckOutTime:text:12:fixed><Room Count:int:13:fixed><Adult Count:int:14:fixed><Child Count:int:15:fixed><RoomRate/Night:text:16:fixed><Payment Option(s):multiSelect:17:fixed><Taxes:select:18:fixed><Breakfast:select:19:fixed><Internet:select:20:fixed><Cancellation Policy:longText:21:fixed><Prefer Property:select:22:fixed> <Hotel Name:text:1:fixed><Hotel Category:text:2:fixed><Hotel Address:longText:3:fixed><Hotel City:text:4:fixed><Hotel Country:select:5:fixed><Project Address:text:6:fixed><Distance from Work:text:7:fixed><Room Type:text:8:fixed><CheckInDate:text:9:fixed><CheckInTime:text:10:fixed><CheckOutDate:text:11:fixed><CheckOutTime:text:12:fixed><Room Count:int:13:fixed><Adult Count:int:14:fixed><Child Count:int:15:fixed><RoomRate/Night:text:16:fixed><Payment Option(s):multiSelect:17:fixed><Taxes:select:18:fixed><Breakfast:select:19:fixed><Internet:select:20:fixed><Cancellation Policy:longText:21:fixed><Prefer Property:select:22:fixed>");
	new HotelQuotaionShemaDao().saveHotelQuotationSchema(hotelQuotationSchema);

}  */
}
