package com.finra.challenge.model;

public class InputModel {
    public String number;
    public int pageSize;

    InputModel(String number,int pageSize){
        this.number = number;
        this.pageSize = pageSize;
    }
}
