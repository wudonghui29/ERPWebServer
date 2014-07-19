package com.xinyuan.action.command.HumanResource;

import java.util.Set;

import com.Global.SessionManager;
import com.xinyuan.Util.ApporvalsHelper;
import com.xinyuan.action.command.category.HumanResourceCommandDelete;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.ApprovalDAOIMP;
import com.xinyuan.dao.impl.UserDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.MessagesException;
import com.xinyuan.message.MessagesKeys;
import com.xinyuan.model.Approval.Approvals;
import com.xinyuan.model.HumanResource.Employee;
import com.xinyuan.model.User.User;

public class EmployeeCommandDelete extends HumanResourceCommandDelete {

    @Override
    protected void handlePersistence(SuperDAO dao, Object model, Set<String> modelkeys, Object persistence) throws Exception {

        super.handlePersistence(dao, model, modelkeys, persistence);

        // Check have approvals left , then Delete the user and Approvals
        String employeeNO = ((Employee) persistence).getEmployeeNO();
        

        UserDAOIMP userDAOIMP = new UserDAOIMP();
        ApprovalDAOIMP approvalDAOIMP = new ApprovalDAOIMP();
        User user = userDAOIMP.getUser(employeeNO);
        
        String signinedUser = ((User)SessionManager.get(ConfigConstants.SIGNIN_USER)).getUsername();
        if (user != null) {
            if (user.getUsername().equals(signinedUser)) {
                throw new MessagesException(MessagesKeys.DEFAULT + MessagesKeys.CONNECTOR + MessagesKeys.KEYS_PRE  + "cannot.delete.yourself");
            }
        }
        
        Approvals approvals = approvalDAOIMP.getApprovals(employeeNO);

        if (ApporvalsHelper.isUserHavePendingApprovals(approvals)) {
            throw new MessagesException(MessagesKeys.DEFAULT + MessagesKeys.CONNECTOR + MessagesKeys.HR.EMPLOYEE_HAVING_APPROVALS);
        } else {
            if (user != null)
                userDAOIMP.deleteObject(user);
            if (approvals != null)
                approvalDAOIMP.deleteObject(approvals);
        }

    }

}
