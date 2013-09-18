package com.xinyuan.action;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

import com.xinyuan.dao.BaseDAO;
import com.xinyuan.dao.SecurityDAOIMP;

public class SecurityAction extends BaseAction {

	@Override
	protected BaseDAO getDao() {
		return new SecurityDAOIMP();
	}

	public void inform() {		// TODO: Hard code now
		try {
			String deviceToken = "9ab941ea30f5cc4db41fc0a5dbbeae2dfe6a9d0f8c3bca1b97cc5c043aff6be0";

			PayLoad payLoad = new PayLoad();
			payLoad.addAlert("我的push测试");
			payLoad.addBadge(4);
			payLoad.addSound("default");

			PushNotificationManager pushManager = PushNotificationManager.getInstance();
			pushManager.addDevice("iPhone", deviceToken);

			// Connect to APNs
			String host = "gateway.sandbox.push.apple.com";
			int port = 2195;
			String certificatePath = "/Users/Isaacs/Downloads/work/xinyuan_develop/apnsDevelop.p12";
			String certificatePassword = "12345";
			pushManager.initializeConnection(host, port, certificatePath, certificatePassword, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);

			// Send Push
			Device client = pushManager.getDevice("iPhone");
			pushManager.sendNotification(client, payLoad);
			pushManager.stopConnection();

			pushManager.removeDevice("iPhone");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
