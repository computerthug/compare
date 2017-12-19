package com.malfunction.filecmprapp.model.SQR;

import com.malfunction.filecmprapp.model.ISystem;
//import com.malfunction.filecmprapp.model.Trigger.SQR;
import com.malfunction.filecmprapp.model.SQR.SQR;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public abstract class abstractSystemSQRs implements ISystem {
    protected List<SQR> sysSQRs;

    protected ApplicationContext applicationContext;

    public List<SQR> getSysSQRs() {
        return sysSQRs;
    }

    public void setSysSQRs(List<SQR> sysSQRs) {
        this.sysSQRs = sysSQRs;
    }

    @Override
    public List<SQR> getItems() {
        return sysSQRs;
    }

    @Override
    public abstract String getSrcSysName();

    @Override
    public String getItemType() {
        return "SQR";
    }

    @Override
    public void loadItems() {
        sysSQRs = new ArrayList<>();
        List<SqrParam> tmpParams = new ArrayList<>();

        tmpParams.add(new SqrParam(new Integer(0),"Product"));
        tmpParams.add(new SqrParam(new Integer(1),"Report Code"));
        tmpParams.add(new SqrParam(new Integer(2),"Entity"));


        sysSQRs.add(new SQR("SQR1",tmpParams));
        sysSQRs.add(new SQR("SQR2",tmpParams));
        sysSQRs.add(new SQR("SQR3",tmpParams));


    }


}
