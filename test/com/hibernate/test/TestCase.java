package com.hibernate.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.xinyuan.model.HumanResource.EmployeeSMBill;
import com.xinyuan.model.HumanResource.EmployeeSMOrder;

public class TestCase {

	private static Session session;
	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void beforeClass() {
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		session = sessionFactory.getCurrentSession();
	}

	@AfterClass
	public static void afterClass() {
		sessionFactory.close();
	}
	
	@Test
	public void testBill() {
		EmployeeSMOrder employeeSMOrder = new EmployeeSMOrder();
		employeeSMOrder.setCreateDate(new Date());
		
		EmployeeSMBill bill1 = new EmployeeSMBill();
		bill1.setOrder(employeeSMOrder);
		bill1.setEndDate(new Date());
		
		EmployeeSMBill bill2 = new EmployeeSMBill();
		bill2.setOrder(employeeSMOrder);
		bill2.setEndDate(new Date());
		
		Set<EmployeeSMBill> set = new HashSet<EmployeeSMBill>();
		set.add(bill1);
		set.add(bill2);
		employeeSMOrder.setBills(set);
		
		session.beginTransaction();
//		session.save(bill1);
//		session.save(bill2);
		session.save(employeeSMOrder);
//		EmployeeSMOrder order = (EmployeeSMOrder) session.get(EmployeeSMOrder.class, 1);
//		EmployeeSMBill bill = (EmployeeSMBill)session.get(EmployeeSMBill.class, 1);
//		EmployeeSMOrder order = bill.getOrder();
//		session.delete(session.get(EmployeeSMOrder.class, 1));
		session.getTransaction().commit();
		
		System.out.println("Done!");
	}

	@Test
	public void test() {
//		 new SchemaExport(new AnnotationConfiguration().configure()).create(true, true);		// it will drop all tables and create
		 
		try {
			
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
			  
			System.out.println("Done");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
