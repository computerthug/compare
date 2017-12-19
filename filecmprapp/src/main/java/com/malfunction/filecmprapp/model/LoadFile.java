package com.malfunction.filecmprapp.model;

import com.malfunction.filecmprapp.model.SQR.SQR;
import com.malfunction.filecmprapp.model.SQR.SqrParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LoadFile {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Resource resource;

    protected ApplicationContext applicationContext;



    protected List<SQR> loadedSQRs;



    public LoadFile(){
        applicationContext = new ClassPathXmlApplicationContext();
        resource = applicationContext.getResource("classpath:test_csv_file.csv");
        logger.info("The Resource is " + resource.toString());
        loadedSQRs = new ArrayList<>();

    }

    public boolean readFile(){
        boolean bool = true;

        try{
            InputStream is = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();

        }catch(IOException e){
            bool=false;
            e.printStackTrace();
        }

        return bool;
    }


    public SQR create_Sqr(String strSQR) {
        SQR newSqr= new SQR(strSQR,new ArrayList<>());



        return newSqr;

    }

    public SqrParam create_SqrParam(String strSeq, String strLabel) {


        SqrParam newSqrParam = new SqrParam(Integer.parseInt(strSeq),strLabel);



        return newSqrParam;

    }

    public SQR retrieveSqr(SQR tmpSQR) {

        SQR rtrvSQR;
        int intSqr = loadedSQRs.indexOf(tmpSQR);
        if(intSqr!=-1){
            logger.debug(tmpSQR.getReportCode() +": found at location: " + intSqr);
            rtrvSQR = loadedSQRs.get(intSqr);
        }
        else{
            rtrvSQR = create_Sqr(tmpSQR.getReportCode());
            loadedSQRs.add(rtrvSQR);
            logger.debug(tmpSQR.getReportCode() +": Not found at location, Creating new Sqr with value:" + tmpSQR.getReportCode());

        }
        return rtrvSQR;

    }

    public List<SQR> getLoadedSQRs() {
        return loadedSQRs;
    }

    public void setLoadedSQRs(List<SQR> loadedSQRs) {
        this.loadedSQRs = loadedSQRs;
    }

    public void addNewParam(SqrParam newParam, SQR findSQR) {

        SQR tmpSQR = retrieveSqr(findSQR);
        tmpSQR.getParams().add(newParam);

    }


    public void createSqrListFromFile() {

        try{
            InputStream is = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = br.readLine()) != null) {
                String[] parsedSqr = line.split(",");
                if(parsedSqr.length==3)
                {
                    SQR newSqr = create_Sqr(parsedSqr[0]);
                    SqrParam newParam = create_SqrParam(parsedSqr[1],parsedSqr[2]);

                    SQR tmpSqr = retrieveSqr(newSqr);
                    addNewParam(newParam,tmpSqr);

                }
                else
                    logger.error(line + ": Line not processed");
            }
            br.close();

        }catch(IOException e){
            //bool=false;
            e.printStackTrace();
        }

    }
}
