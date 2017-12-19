package com.malfunction.filecmprapp.model.Schedule;

import com.malfunction.filecmprapp.model.SQR.SQR;
import com.malfunction.filecmprapp.model.SQR.SqrParam;
import com.malfunction.filecmprapp.model.Trigger.Trigger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class Schedule {

    private String schedule_name;
    private Trigger trigger;
    private SQR sqr;
    private List<SqrParam> arguments;

    public String getSchedule_name() {
        return schedule_name;
    }

    public void setSchedule_name(String schedule_name) {
        this.schedule_name = schedule_name;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public SQR getSqr() {
        return sqr;
    }

    public void setSqr(SQR sqr) {
        this.sqr = sqr;
    }

    public List<SqrParam> getArguments() {
        return arguments;
    }

    public void setArguments(List<SqrParam> arguments) {
        this.arguments = arguments;
    }


    //Check SQR Parms against Schedule arguments
    public void validateSQRargmuments(){
        throw new NotImplementedException();
    }
}
