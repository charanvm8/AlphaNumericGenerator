package com.finra.challenge.model;

import java.util.List;

public class ResponseModel {

    public List<String> response;
    public int totalCount;
    public int pageNumber;
    public int totalPages;
    public int totalResults;

    public ResponseModel(List<String> alphaNumericValues, int totalCount, int pageNumber,int totalPages,int totalResults) {
        this.response = alphaNumericValues;
        this.totalCount = totalCount;
        this.pageNumber = pageNumber;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }
}
