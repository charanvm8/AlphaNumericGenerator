package com.finra.challenge.model;

import java.util.List;

public class ResponseModel {

    public List<String> response;
    public int totalCount;
    public int pageNumber;

    public ResponseModel(List<String> alphaNumericValues, int totalCount, int pageNumber) {
        this.response = alphaNumericValues;
        this.totalCount = totalCount;
        this.pageNumber = pageNumber;
    }
}
