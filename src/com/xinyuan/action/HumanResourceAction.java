package com.xinyuan.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.jdbc.Work;

import com.Global.HibernateInitializer;
import com.opensymphony.xwork2.Action;
import com.xinyuan.Util.GsonHelper;
import com.xinyuan.Util.ParametersHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.HumanResourceDAOIMP;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.model.Approval.Approvals;
import com.xinyuan.model.Setting.APPSettings;

public class HumanResourceAction extends SuperAction {
	private static final long serialVersionUID = 1L;

	@Override
	protected SuperDAO getDao() {
		return new HumanResourceDAOIMP();
	}

	public String trace() {
		
		final String traceUser = ParametersHelper.getParameter(requestMessage, "User");
		
		final Map<String, List<Object>> orderResults = new HashMap<String, List<Object>>();
		
		HibernateInitializer.getSessionFactory().getCurrentSession().doWork(new Work() {
					@Override
					public void execute(Connection connection) throws SQLException {
						// TODO Auto-generated method stub
						String connectionURL = connection.getMetaData().getURL();
						String dbName = connectionURL.substring(connectionURL.lastIndexOf("/") + 1);
						
						String callProcedureString = "{call getAllTraceTables('" + dbName + "')}";
						ResultSet tableResult = connection.prepareStatement(callProcedureString).executeQuery();
						
						while (tableResult.next()) {
							String tableName = tableResult.getString("ATableName");
							
							int dayCount = 2 ; //default
							HumanResourceDAOIMP daoimp = (HumanResourceDAOIMP)getDao();
							APPSettings appSettings = (APPSettings)daoimp.getObject(APPSettings.class, "type", "ADMIN_APNS_TraceFilesDate");
							String jsonString = appSettings.getSettings();
							Map<String, Object> map = GsonHelper.translateJsonStringToMap(jsonString);
							if (map != null) {
							    Map<String, String> paraMap = (Map<String, String>)map.get("PARAMETERS");
                                if (paraMap != null) {
                                    String dayCountString = paraMap.get("KEYS.save.Day.count");
                                    if (dayCountString != null) {
                                        dayCount = Integer.valueOf(dayCountString);
                                    }
                                }
                            }
							
							
							Calendar cal = Calendar.getInstance();
							cal.add(Calendar.DAY_OF_MONTH, -dayCount);
							Date date = cal.getTime();
							
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String dateString = format.format(date);
							String callProcedureStr = "{call getTraceResults('" + dbName + "', '" + tableName + "', '" + dateString + "', '" + traceUser + "', " + 0 + " )}";
							ResultSet values = connection.prepareStatement(callProcedureStr).executeQuery();
							
							List<Object> outterResults = new ArrayList<Object>();
							while (values.next()) {
								String orderNO = values.getString("orderNO");
								
								outterResults.add(orderNO);
							}
							
							orderResults.put(tableName, outterResults);
							
						}
					}
				});
		
		Approvals pendingApproval = (Approvals)(new SuperDAOIMP()).getObject(Approvals.class, traceUser);
		String unReadApprovals = pendingApproval.getUnReadApprovals();

		List<Object> results = new ArrayList<Object>();
		results.add(orderResults);
		results.add(unReadApprovals);
		
		responseMessage.results = results;
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		return Action.NONE;
	}
	
	
	
}
