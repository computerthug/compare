package com.malfunction.filecmprapp.model.Trigger;

import java.util.ArrayList;
import java.util.List;

public class EcsTriggers extends abstractSystemTriggers {


    @Override
    public String getSrcSysName() {
        return "ECS";
    }

    @Override
    public void loadItems() {

        super.sysTriggers = new ArrayList<>();

        sysTriggers.add(new Trigger("TRG1","EVENT"));
        sysTriggers.add(new Trigger("TRG2","EVENT"));
        sysTriggers.add(new Trigger("TRG3","EcENT"));


    }
}
