package com.hibernate.test;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.Global.HibernateInitializer;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.model.Finance.FinanceSalary;

public class TestCase {

	private static Session session;
	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void beforeClass() {
		HibernateInitializer.initialize();
		sessionFactory = HibernateInitializer.getSessionFactory();
		session = sessionFactory.getCurrentSession();
	}

	@AfterClass
	public static void afterClass() {
		sessionFactory.close();
	}
	
	@Test
	public void testBill() {
		session.beginTransaction();
		
		
		SuperDAOIMP daoimp = new SuperDAOIMP();
		FinanceSalary salary = (FinanceSalary)daoimp.getObject(FinanceSalary.class, new Integer(1));
		salary.setBaseSalary(220000);
		
		FinanceSalary salary2 = (FinanceSalary)daoimp.getObject(FinanceSalary.class, "employeeNO", "AE0001");
		salary2.setBaseSalary(110000);
		
		daoimp.updateObject(salary);
		daoimp.updateObject(salary2);
		
		session.getTransaction().commit(); 
		System.out.println("Done!  ");
	}
	
	@Test
	public void testDate() throws ParseException {
//		WHScrapOrder eeOrder = new WHScrapOrder();
//		Date date = new Date();
//		eeOrder.setScrapTime(date);
//	
//		session.beginTransaction();
//		session.save(eeOrder);
//		session.getTransaction().commit();
//		
//		System.out.println("Done!");
	}

	@Test
	public void test() {
//		 new SchemaExport(new AnnotationConfiguration().configure()).create(true, true);		// it will drop all tables and create
//		try {
//			String textString = "{\"annotation\":\"annotation\",\"bills\":[{\"comment\":\"comment\"}]}";
//			WHLendOutOrder whLendOutOrder = JsonHelper.getGson().fromJson(textString, WHLendOutOrder.class);
			
//			WHLendOutOrder whLendOutOrder = new WHLendOutOrder();
//			whLendOutOrder.setAnnotation("Hello , Hello ");
//			whLendOutOrder.setLevelApp_1("HAHAHAHAH");
//			
//			Set<WHLendOutBill> set = new HashSet<WHLendOutBill>();
//			WHLendOutBill whLendOutBill = new WHLendOutBill();
//			whLendOutBill.setComment("IIIIIIII , IIIIIIII");
//			WHLendOutBill whLendOutBill2 = new WHLendOutBill();
//			whLendOutBill2.setComment("IIIIIIII2 , IIIIIIII2");
//			set.add(whLendOutBill);
//			set.add(whLendOutBill2);
//			
//			whLendOutOrder.setBills(set);
//			
//			
//			session.beginTransaction();
//			session.persist(whLendOutOrder);
//			session.getTransaction().commit();
//			session.beginTransaction();
//			WHLendOutOrder obj = (WHLendOutOrder) session.get(WHLendOutOrder.class, 2);``
//			
//			Set<WHLendOutBill> set = obj.getBills();
//			int size = set.size();
//			System.out.println("size : " + size);
//			session.getTransaction().commit();
//			Iterator<WHLendOutBill> it = set.iterator();  
//			while (it.hasNext()) {  
//			  WHLendOutBill str = it.next();  
//			  System.out.println(str.getComment()); 
//			  System.out.println(str.getId());
//			}  
//			System.out.println("Done");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
