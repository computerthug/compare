package com.malfunction.filecmprapp.model.SQR;

import com.malfunction.filecmprapp.model.ISystem;
import com.malfunction.filecmprapp.model.ISystemCompare;
import com.malfunction.filecmprapp.model.SQR.Exception.SqrParamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SQRSysCompare implements ISystemCompare {

    private ISystem source;
    private ISystem target;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String desc;

    public SQRSysCompare(ISystem s, ISystem t) {
        this.source = s;
        this.target = t;
    }

    //---------------------------------------------------------------------------------

    @Override
    public boolean executeSysCompare() {



        return false;
    }








    //Check Source ISystem SQR's agains Target ISystem
    public boolean compareSrcSqrTargetSqr(){

        boolean bool = true;
        List<SQR> srcSqrs = this.source.getItems();

        for (SQR tmpSQR:srcSqrs){
            if(verifySqrInTarget(tmpSQR)){
                compareSqrParamSysCounts(tmpSQR);
                verifySqrParamInTarget(tmpSQR);
            }
            else
                bool &= false;
        }
        return bool;
    }






    //Verify counts of a SQR parameters in each ISystem
    public boolean compareSqrParamSysCounts(SQR sqr){



        boolean bool = true;
        SQR tarSqR = (SQR)this.target.getItems().get(this.target.getItems().indexOf(sqr));


        int srcCnt = sqr.getParams().size();
        logger.info("Report Code ["+sqr.getReportCode() + "]: Parameter count in Source:" + srcCnt);
        int tarCnt = tarSqR.getParams().size();
        logger.info("Report Code ["+tarSqR.getReportCode() + "]: Parameter count in Target:" + tarCnt);


        //int targetSqrParamCnt =



        return (srcCnt==tarCnt);
    }




    //Verify counts of SQR's in each ISystem
    public boolean compareSqrSysCounts(){

        boolean bool = true;
        int srcSQRcnt = this.source.getItems().size();
        logger.info("Source System SQR count:" + srcSQRcnt);
        int tarSQRcnt = this.target.getItems().size();
        logger.info("Target System SQR count:" +tarSQRcnt );

        if(srcSQRcnt==tarSQRcnt){
            logger.info("Source Count matches Target");
        }
        else {
            logger.info("Source Does NOT match Target");
            bool= false;
        }
        return bool;
    }


    //Verify if SQR exist in target system
    public boolean verifySqrInTarget(SQR sqr){

        if(target.getItems().contains(sqr)){
            logger.info(sqr.getReportCode() + " :SQR Confirmed in Target");
            return true;
        }
        logger.error(sqr.getReportCode() + " :SQR NOT in Target");

        return false;
    }

    //Verify if SQR Params exist in target system
    public boolean verifySqrParamInTarget(SQR sqr){

        final SQR tarSqr= (SQR)this.target.getItems().get(target.getItems().indexOf(sqr));
        List<SqrParam> srcSqrParams = sqr.getParams();
        List<SqrParam> tarSqrParams = tarSqr.getParams();
        boolean allOk = true;

        for(int i=0;i<srcSqrParams.size();i++){
            SqrParam tmpParam = srcSqrParams.get(i);
            if(tarSqrParams.contains(tmpParam)) {
                logger.info("Report Code ["+sqr.getReportCode() + "]: Seq[" +tmpParam.getSequence()+"] Label["+tmpParam.getLabel() + "] - Confirmed");
                allOk &= true;
            }else{
                logger.info("Report Code ["+sqr.getReportCode() + "]: Seq[" +tmpParam.getSequence()+"] Label["+tmpParam.getLabel() + "] - NOT Found in Target");
                allOk &= false;
            }

        }
        return allOk;

    }






/*
    public void compareSQRReportCode(ISystem s, ISystem t){

        List<SQR> source = s.getItems();
        List<SQR> target = t.getItems();

        for(int i=0; i<source.size();i++){
            SQR srcSQR = source.get(i);
            if(target.contains(srcSQR)){
                logger.info(srcSQR.getReportCode()+ ": Verified in Target System :" + t.getSrcSysName());
            }
            else
            {
                logger.error(srcSQR.getReportCode()+ ": Not Found In Target System :" + t.getSrcSysName());
            }
        }




    }
    */

    @Override
    public String getDescription() {
        return this.desc;
    }

    @Override
    public void setDescription(String des) {
        this.desc = des;
    }
}
