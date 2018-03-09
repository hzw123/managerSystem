package com.managerSystem.oasys.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtil {

    private final static int SIZE=10;

    private final static Sort SORT=new Sort(Sort.Direction.ASC,"id");

    public static PageRequest getPageRequest(Pageable pageable, Sort sort){

        int size=pageable.getPageSize()!=0?pageable.getPageSize():SIZE;

        int page=pageable.getPageNumber()!=0 ?pageable.getPageNumber():1;

        sort=sort==null?SORT:sort;

        return PageRequest.of(page,size,sort);
    }

    public static PageRequest getPageRequest(Pageable pageable){

        return getPageRequest(pageable,null);

    }

}
