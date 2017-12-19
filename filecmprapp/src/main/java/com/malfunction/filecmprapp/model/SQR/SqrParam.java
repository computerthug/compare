package com.malfunction.filecmprapp.model.SQR;

import com.malfunction.filecmprapp.model.SQR.Exception.SqrParamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SqrParam {


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SqrParam)) return false;

        SqrParam sqrParam = (SqrParam) o;

        if (!sequence.equals(sqrParam.sequence)) return false;
        return label.equals(sqrParam.label);
    }

    @Override
    public int hashCode() {
        int result = sequence.hashCode();
        result = 31 * result + label.hashCode();
        return result;
    }

    public SqrParam(Integer i, String s){
        this.sequence = i;
        this.label = s;

    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    //Will check is a Source Param pair exist Target param list
    public static boolean checkParamExist(SqrParam p, List<SqrParam> targetList,Logger logger) throws SqrParamException {

        //return false
        if(targetList.contains(p)){
            String mess = String.format("SQR Param Values Confirmed - Sequence[%d] : Label[%s] ",p.sequence,p.label);
            logger.info(mess);
        }
        else {
            String mess = String.format("SQR Param Not Found in Target System - Sequence[%d] : Label[%s] ",p.sequence,p.label);
            //logger.error(mess);
            throw new SqrParamException(mess);
        }
        return false;
    }





    private Integer sequence;
    private String label;
}
