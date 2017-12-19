package com.malfunction.filecmprapp.model;

import java.util.List;


public interface ISystem {

    public <T> List<T> getItems();
    public String getSrcSysName();
    public String getItemType();
    public void loadItems();

    //public boolean isValidSqr(String code);
    //public Map<Integer,String> getSqrParams(String code);


}
