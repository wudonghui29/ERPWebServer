package com.xinyuan.Util;

import j2se.modules.Introspector.IntrospectHelper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.struts2.ServletActionContext;

import com.xinyuan.dao.BusinessDAO;
import com.xinyuan.dao.HumanResourceDAO;
import com.xinyuan.dao.impl.BusinessDAOIMP;
import com.xinyuan.dao.impl.HumanResourceDAOIMP;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.model.Setting.APPSettings;

public class QueueHelper {

    public static void flushClients() {

        try {

            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.results = getRefreshDataList();
            responseMessage.status = ConfigConstants.STATUS_POSITIVE;
            String json = GsonHelper.getGson().toJson(responseMessage);
            byte[] data = json.getBytes("UTF-8");

            ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();

            outputStream.write(data);
            outputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Object> getRefreshDataList() {

        HumanResourceDAO humanResourceDAO = new HumanResourceDAOIMP();
        List<Object> hrList = humanResourceDAO.getUsersNOPairs();

        BusinessDAO businessDAO = new BusinessDAOIMP();
        List<Object> bsList = businessDAO.getClientsNOPairs();

        SuperDAOIMP dao = new SuperDAOIMP();

        String appSettingTableString = IntrospectHelper.getShortClassName(APPSettings.class);
        List<Object> settingList = dao.getObjects("select settings.settings from " + appSettingTableString + " settings where settings.type = '" + ConfigConstants.APPSettings_TYPE_ADMIN_APPROVALS + "'");

        List<Object> results = new ArrayList<Object>();
        results.add(hrList);
        results.add(bsList);
        results.add(settingList);

        return results;
    }

}
