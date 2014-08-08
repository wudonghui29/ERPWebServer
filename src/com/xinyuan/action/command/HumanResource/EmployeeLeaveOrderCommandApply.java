package com.xinyuan.action.command.HumanResource;

import j2se.modules.Introspector.ObjectIntrospector;

import java.util.List;
import java.util.Map;

import com.Global.SessionManager;
import com.xinyuan.Util.AppModelsHelper;
import com.xinyuan.Util.ApprovalsDAOHelper;
import com.xinyuan.Util.GsonHelper;
import com.xinyuan.action.command.category.HumanResourceCommandApply;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.model.BaseOrder;
import com.xinyuan.model.HumanResource.EmployeeLeaveOrder;
import com.xinyuan.model.Setting.APPSettings;
import com.xinyuan.model.User.User;

public class EmployeeLeaveOrderCommandApply extends HumanResourceCommandApply {

    @Override
    protected void handleApprovals(SuperDAO dao, String appKey, String forwardUser, BaseOrder persistence) throws Exception {
        
        String finalLevel = this.getFinalKeyBySettings((SuperDAOIMP)dao, (EmployeeLeaveOrder)persistence);
        
        // Check and Handle The Final Approval Stuff
        if (AppModelsHelper.isFinalApproval(appKey, persistence)) {
            handleFinalApprovalProcess(dao, persistence);
            
            // set the app...
            String finalAppKey = appKey;
            String nextAppKey = finalLevel;
            String signinedUser = ((User)SessionManager.get(ConfigConstants.SIGNIN_USER)).getUsername();
            do {
                ObjectIntrospector.setProperty(persistence, nextAppKey, signinedUser);
            } while ((nextAppKey = AppModelsHelper.getNextApprovalKey(nextAppKey)) != null && !nextAppKey.equals(finalAppKey));
        } 
            
        // Handle The Pending Approvals
        ApprovalsDAOHelper.handlePendingApprovals(dao, appKey, forwardUser, persistence);
    }











    private String getFinalKeyBySettings(SuperDAOIMP dao, EmployeeLeaveOrder objects) {
        
        float day = objects.getDay();
        
        APPSettings appSettings = (APPSettings)((SuperDAOIMP)dao).getObject(APPSettings.class, "type", ConfigConstants.APPSettings_TYPE_ADMIN_APPROVALS);
        Map<String, Object> map = GsonHelper.translateJsonStringToMap(appSettings.getSettings());
        Map<String, Map<String, Map<String, Object>>> departmentMap = (Map<String, Map<String, Map<String, Object>>>)map.get("HumanResource");
        if (departmentMap != null) {
            Map<String, Map<String, Object>> orderMap = departmentMap.get("EmployeeLeaveOrder");
            if (orderMap != null) {
                List<String> applevels = AppModelsHelper.getApprovalLevels(EmployeeLeaveOrder.class);
                for(int i = 0; i < applevels.size(); i++) {
                    String levelstr = applevels.get(i);
                    Map<String, Object> appValueMap = orderMap.get(levelstr);
                    String aPP_LEAVE_DAY_String = (String)appValueMap.get("APP_LEAVE_DAY");
                    if (aPP_LEAVE_DAY_String != null) {
                        int APP_LEAVE_DAY = Integer.parseInt(aPP_LEAVE_DAY_String);
                        if (APP_LEAVE_DAY >= day) {
                            return levelstr;
                        }
                    }
                }
            }
        }
        
        return null;
        
    }
}
