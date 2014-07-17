package com.xinyuan.Quartz;

import j2se.modules.Helper.DLog;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.Global.HibernateInitializer;
import com.xinyuan.Util.AppModelsHelper;
import com.xinyuan.Util.GsonHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.model.Setting.APPSettings;

public class EraseJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		DLog.log(new Date() + " Begin EraseJob ---------------------->>>>>>>>>>>> ");
		
		Session session = HibernateInitializer.getSessionFactory().getCurrentSession();	
		Transaction	transaction = session.beginTransaction();
		
		try {
			
			Map<String, Map<String, List<String>>> categoriesModelsMap = AppModelsHelper.getCategoriesModelsConstructs();
			SuperDAO superDao = new SuperDAOIMP();
			APPSettings settingPO = superDao.readUnique(APPSettings.class, APPSettings.COLUMN_TYPE, ConfigConstants.APPSettings_TYPE_ADMIN_ORDERSEXPIRATIONS);
			
			String settings = settingPO.getSettings();
			DLog.log(settings);
			
			Map<String, Map<String, Number>> ordersexpirationsMap = GsonHelper.getGson().fromJson(settings, Map.class);
			
			for (Map.Entry<String, Map<String, Number>> entry : ordersexpirationsMap.entrySet()) {
				String category = entry.getKey();
				Map<String, Number> ordersMap = entry.getValue();
				for (Map.Entry<String, Number> orderEntry : ordersMap.entrySet()) {
					String orderType = orderEntry.getKey();
					Number number = orderEntry.getValue();
					int month_count = number.intValue();
					
					if (month_count == 0) continue;
					
					DLog.log("TO BE CONTINUE . DO THE QUERY AND DELETE JOB -> " + month_count);
					
				}
			}
			
			transaction.commit();
			DLog.log(new Date() + " Done EraseJob ---------------------->>>>>>>>>>>> ");
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
				
		 
		
	}

}
