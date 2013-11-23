package com.hibernate.test;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.xinyuan.Util.JsonHelper;
import com.xinyuan.model.Warehouse.WHLendOutBill;
import com.xinyuan.model.Warehouse.WHLendOutOrder;

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
	public void test() {
//		 new SchemaExport(new AnnotationConfiguration().configure()).create(true, true);		// it will drop all tables and create
		 
		try {
			
			String textString = "{\"annotation\":\"annotation\",\"bills\":[{\"comment\":\"comment\"}]}";
			WHLendOutOrder whLendOutOrder = JsonHelper.getGson().fromJson(textString, WHLendOutOrder.class);
			
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

//	public static void main(String[] args) {
//		beforeClass();
//		new TestCase().test();
//		afterClass();
//	}

}
