package com.malfunction.filecmprapp.model;

import com.malfunction.filecmprapp.model.SQR.*;
import com.malfunction.filecmprapp.model.SQR.Exception.SqrParamException;
import com.malfunction.filecmprapp.model.Trigger.EcsTriggers;
import com.malfunction.filecmprapp.model.Trigger.HypTriggers;
import com.malfunction.filecmprapp.model.Trigger.TriggerSysCompare;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SQRComapreTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());



    @Test(expected = SqrParamException.class)
    public void verify_SQR_param_Exception() throws SqrParamException {
        SqrParam tmpParam = new SqrParam(0,"Test Param");
        List<SqrParam> tmpList = new ArrayList<>();
        boolean tmpBool;

            tmpBool = SqrParam.checkParamExist(tmpParam,tmpList,logger);

            //logger.error("SQR Parm exception thrown");

    }

    @Test
    public void verify_test_param_list(){
        List<SqrParam> tmp= this.getTestParamList();
        assertEquals(3,tmp.size());

    }



    private List<SqrParam> getTestParamList(){
        List<SqrParam> tmp = new ArrayList<>();
        final int numberOfParams = 3;

        for(int i=0;i<numberOfParams;i++){
            SqrParam tmpParam = new SqrParam(i,"Test Param: " + i);
            tmp.add(tmpParam);
        }
        return tmp;
    }

    private List<SQR> getTestSQRList(){
        List<SQR> tmp = new ArrayList<>();
        final int numberOfParams = 3;

        for(int i=0;i<numberOfParams;i++){
            SQR tmpParam = new SQR("Test SQR: " + i, new ArrayList<>());
            tmp.add(tmpParam);
        }
        return tmp;
    }


    @Test
    public void verify_compareSqrParamSysCounts_matches(){


        EcsSQRs src = new EcsSQRs();
        HypSQRs targ = new HypSQRs();


        src.loadItems();
        targ.loadItems();

        List<SQR> tm =  src.getSysSQRs();
        //tm.add(new SQR("extrat sqr",new ArrayList<>()));

        SQRSysCompare sqrSysCompare = new SQRSysCompare(src,targ);



        boolean b =  sqrSysCompare.compareSqrParamSysCounts(tm.get(1));

        assertTrue(b);

    }


    @Test
    public void verify_compareSqrSysCount_matches (){

        ISystem src = new EcsSQRs();
        ISystem targ = new HypSQRs();

        src.loadItems();
        targ.loadItems();

        SQRSysCompare sqrSysCompare = new SQRSysCompare(src,targ);

        boolean b =  sqrSysCompare.compareSqrSysCounts();

        assertTrue(b);
    }
    @Test
    public void verify_compareSqrSysCount_NOT_matches (){

        EcsSQRs src = new EcsSQRs();
        HypSQRs targ = new HypSQRs();


        src.loadItems();
        targ.loadItems();

         List<SQR> tm =  src.getSysSQRs();
         tm.add(new SQR("extrat sqr",new ArrayList<>()));

        SQRSysCompare sqrSysCompare = new SQRSysCompare(src,targ);

        boolean b =  sqrSysCompare.compareSqrSysCounts();

        assertFalse(b);
    }




    @Test
    public void verify_compareSrcSqrTargetSqr_Finds_SQR(){

        EcsSQRs src = new EcsSQRs();
        HypSQRs targ = new HypSQRs();


        src.loadItems();
        targ.loadItems();

        List<SQR> tm =  src.getSysSQRs();
        //tm.add(new SQR("extrat sqr",new ArrayList<>()));

        SQRSysCompare sqrSysCompare = new SQRSysCompare(src,targ);

        boolean b =  sqrSysCompare.compareSrcSqrTargetSqr();

        assertTrue(b);

    }



    @Test
    public void verify_compareSrcSqrTargetSqr_NOT_Find_SQR(){

        EcsSQRs src = new EcsSQRs();
        HypSQRs targ = new HypSQRs();


        src.loadItems();
        targ.loadItems();

        List<SQR> tm =  src.getSysSQRs();
        tm.add(new SQR("SQR not to be Found",new ArrayList<>()));
        src.setSysSQRs(tm);

        SQRSysCompare sqrSysCompare = new SQRSysCompare(src,targ);

        boolean b =  sqrSysCompare.compareSrcSqrTargetSqr();

        assertFalse(b);

    }










    @Test
    public void check_verifySqrInTargget_finds_SQR (){

        ISystem src = new EcsSQRs();
        ISystem targ = new HypSQRs();

        src.loadItems();
        targ.loadItems();

        SQRSysCompare sqrSysCompare = new SQRSysCompare(src,targ);

        boolean b =  sqrSysCompare.verifySqrInTarget((SQR)src.getItems().get(1));

        assertTrue(b);
    }

    @Test
    public void check_verifySqr_NOT_InTargget_finds_SQR (){

        EcsSQRs src = new EcsSQRs();
        HypSQRs targ = new HypSQRs();

        src.loadItems();
        targ.loadItems();

    /*
        List<SQR> tmp = src.getItems();
        tmp.add(new SQR("Bad SQR", new ArrayList<>()));
        src.setSysSQRs(tmp);
    */

        SQRSysCompare sqrSysCompare = new SQRSysCompare(src,targ);

        boolean b =  sqrSysCompare.verifySqrInTarget(new SQR("BAD SQR",new ArrayList<>()));

        assertFalse(b);
    }

    @Test
    public void check_verifySqrParamInTarget_finds_Params(){

        ISystem src = new EcsSQRs();
        ISystem targ = new HypSQRs();

        src.loadItems();
        targ.loadItems();

        SQRSysCompare sqrSysCompare = new SQRSysCompare(src,targ);

        boolean b =  sqrSysCompare.verifySqrParamInTarget((SQR)src.getItems().get(1));

        assertTrue(b);
    }

    @Test
    public void check_verifySqrParam_NOT_InTarget_finds_Params(){

        ISystem src = new EcsSQRs();
        ISystem targ = new HypSQRs();

        src.loadItems();
        targ.loadItems();

        SQRSysCompare sqrSysCompare = new SQRSysCompare(src,targ);


        List<SqrParam> tmpParam = this.getTestParamList();
        SQR tmp = new SQR("SQR1",tmpParam);

        boolean b =  sqrSysCompare.verifySqrParamInTarget(tmp);

        assertFalse(b);
    }

}


