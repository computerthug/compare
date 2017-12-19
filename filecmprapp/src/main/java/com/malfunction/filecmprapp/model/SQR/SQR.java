package com.malfunction.filecmprapp.model.SQR;

import java.util.List;

public class SQR {

    private String reportCode;
    private List<SqrParam> params;

    public SQR(String sqrCode,List<SqrParam> sqrParams){
        this.reportCode = sqrCode;
        this.params = sqrParams;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public List<SqrParam> getParams() {
        return params;
    }

    public void setParams(List<SqrParam> params) {
        this.params = params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SQR)) return false;

        SQR sqr = (SQR) o;

        return reportCode.equals(sqr.reportCode);
    }

    @Override
    public int hashCode() {
        return reportCode.hashCode();
    }
}
