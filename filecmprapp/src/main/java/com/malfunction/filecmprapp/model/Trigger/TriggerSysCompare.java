package com.malfunction.filecmprapp.model.Trigger;

import com.malfunction.filecmprapp.model.ISystem;
import com.malfunction.filecmprapp.model.ISystemCompare;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TriggerSysCompare implements ISystemCompare {


    private ISystem source;
    private ISystem target;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String desc;


    public TriggerSysCompare(ISystem s, ISystem t){
        this.source = s;
        this.target = t;
    }



    @Override
    public boolean executeSysCompare() {

            logger.info("Executing compare for: " + this.getDescription());
        int s_count = source.getItems().size();
            logger.info("Source system count: " + Integer.toString(s_count));
        int d_count = target.getItems().size();
            logger.info("Target system count: " + Integer.toString(d_count));


        if(s_count==d_count) {
            this.compareTriggerCode(source, target);
            return true;
        }
        else
            return false;
    }


    public void compareTriggerCode(ISystem s, ISystem t){

        List<Trigger> source = s.getItems();
        List<Trigger> target = t.getItems();

        for(int i=0; i<source.size();i++){
            Trigger srcTrg = source.get(i);
            if(target.contains(srcTrg)){
                logger.info(srcTrg.getCode()+ ": Verified in Target System :" + t.getSrcSysName());
            }
            else
            {
                logger.error(srcTrg.getCode()+ ": Not Found In Target System :" + t.getSrcSysName());
            }
        }




    }

    @Override
    public String getDescription() {
        return this.desc;
    }

    @Override
    public void setDescription(String des) {
        this.desc = des;
    }
}
