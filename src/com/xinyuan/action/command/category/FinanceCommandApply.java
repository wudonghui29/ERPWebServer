package com.xinyuan.action.command.category;

import com.xinyuan.Util.AppModelsHelper;
import com.xinyuan.action.command.CommandApply;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.model.Finance.FinanceSalary;
import com.xinyuan.model.Finance.FinanceSalaryCHOrder;

public class FinanceCommandApply extends CommandApply {

    @Override
    protected void handleFinalApprovalProcess(SuperDAO dao, Object persistence) throws Exception {
        super.handleFinalApprovalProcess(dao, persistence);
        
        SuperDAOIMP daoImp = (SuperDAOIMP)dao;
        
        if (persistence instanceof FinanceSalaryCHOrder) {
            FinanceSalaryCHOrder financeSalaryCHOrder = (FinanceSalaryCHOrder)persistence;
            FinanceSalary financeSalary = (FinanceSalary) daoImp.getObject(FinanceSalary.class, "employeeNO", financeSalaryCHOrder.getEmployeeNO());
            
            // modify the information
            AppModelsHelper.copyNewValueToPersistence(financeSalaryCHOrder, financeSalary);
            dao.modify(financeSalary);
        }
    }

    
}
