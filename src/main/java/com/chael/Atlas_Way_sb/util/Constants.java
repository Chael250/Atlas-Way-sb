package com.chael.Atlas_Way_sb.util;

public interface Constants {
    public String DEFAULT_PAGE_NUMBER = "0";
    public String DEFAULT_PAGE_SIZE = "10";
    public int MAX_PAGE_SIZE = 100;

    public static void validatePageNumberAndPageSize(int pageNumber, int pageSize){
        if(pageNumber < 0){
            throw new IllegalArgumentException("Page number cannot be less than zero");
        }
        if(pageSize > Constants.MAX_PAGE_SIZE){
            throw new IllegalArgumentException("Page size cannot be greater than " + Constants.MAX_PAGE_SIZE);
        }
    }
}
