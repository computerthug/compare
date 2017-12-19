package com.malfunction.filecmprapp.model;


import com.malfunction.filecmprapp.model.SQR.SQR;
import com.malfunction.filecmprapp.model.SQR.SqrParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoadFilesTests {


    @Test
    public void create_new_object(){
        LoadFile loadFile = new LoadFile();
    }


    @Test
    public void verify_can_read_Line(){
        LoadFile loadFile = new LoadFile();

        assertTrue(loadFile.readFile());

    }

    @Test
    public void verify_create_SQR_from_String(){
        LoadFile loadFile = new LoadFile();
        String strSQR = "test";

        SQR sqr = loadFile.create_Sqr(strSQR);

        assertEquals(strSQR,sqr.getReportCode());
    }


    @Test
    public void verify_create_SqrParam_from_String(){
        LoadFile loadFile = new LoadFile();
        String strSeq = "0";
        String strLabel = "test";

        SqrParam sqrParam = loadFile.create_SqrParam(strSeq,strLabel);

        assertEquals(strSeq,sqrParam.getSequence().toString());
        assertEquals(strLabel,sqrParam.getLabel());


    }

    @Test
    public void verify_retrieveSqr_creates_SQR_when_empty(){

        LoadFile loadFile = new LoadFile();
        SQR tmpSQR = loadFile.create_Sqr("SQR1");

        SQR findSQR = loadFile.retrieveSqr(tmpSQR);

        assertEquals(tmpSQR.getReportCode(),findSQR.getReportCode());



    }

    @Test
    public void verify_retreiveSqr_finds_exising_SQR(){

        LoadFile loadFile = new LoadFile();
        SQR tmpSQR = loadFile.create_Sqr("SQR1");
        List<SQR> sqrList = new ArrayList<>();
        sqrList.add(tmpSQR);

        loadFile.setLoadedSQRs(sqrList);

        SQR findSQR = loadFile.retrieveSqr(tmpSQR);

        assertEquals(tmpSQR.getReportCode(),findSQR.getReportCode());

    }

    @Test
    public void  verify_addParam_adds_new_param(){

        LoadFile loadFile = new LoadFile();
        SQR tmpSQR = loadFile.create_Sqr("SQR1");
        List<SQR> sqrList = new ArrayList<>();
        sqrList.add(tmpSQR);

        SqrParam newParam = loadFile.create_SqrParam("3","Entity");

        loadFile.setLoadedSQRs(sqrList);

        SQR findSQR = loadFile.retrieveSqr(tmpSQR);

        loadFile.addNewParam(newParam, findSQR);
        loadFile.addNewParam(newParam, findSQR);
        loadFile.addNewParam(newParam, findSQR);

        SQR verifySqr = loadFile.retrieveSqr(findSQR);

        //System.out.println("Size of Params:" + verifySqr.getParams().size());



        assertEquals((int)verifySqr.getParams().get(2).getSequence(),(int)newParam.getSequence());



    }


    @Test
    public void verify_create_listSqr_from_file(){

        LoadFile loadFile = new LoadFile();

        loadFile.createSqrListFromFile();

        int loadedSqrCount = loadFile.getLoadedSQRs().size();

        assertEquals(2321,loadedSqrCount);

;
    }


}
