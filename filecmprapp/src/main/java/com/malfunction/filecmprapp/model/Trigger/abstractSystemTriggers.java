package com.malfunction.filecmprapp.model.Trigger;

import com.malfunction.filecmprapp.model.ISystem;

import java.util.ArrayList;
import java.util.List;

public abstract class abstractSystemTriggers implements ISystem {
    protected List<Trigger> sysTriggers;

    public List<Trigger> getSysTriggers() {
        return sysTriggers;
    }

    public void setSysTriggers(List<Trigger> sysTriggers) {
        this.sysTriggers = sysTriggers;
    }

    @Override
    public List<Trigger> getItems() {
        return sysTriggers;
    }

    @Override
    public abstract String getSrcSysName();

    @Override
    public String getItemType() {
        return "TRG";
    }

    @Override
    public void loadItems() {
        sysTriggers = new ArrayList<>();

        sysTriggers.add(new Trigger("TRG1","EVENT"));
        sysTriggers.add(new Trigger("TRG2","EVENT"));
        sysTriggers.add(new Trigger("TRG3","EVENT"));


    }
}
