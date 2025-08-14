package com.sonan.pithi_booking.service.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public final class PageUtil {

    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_PAGE_NUM = 1;

    public static final String PAGE_SIZE = "_limit";
    public static final String PAGE_NUM = "_page";

    private PageUtil() {
        // Prevent instantiation
    }

    public static Pageable getPageable(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageNum < DEFAULT_PAGE_NUM) {
            pageNum = DEFAULT_PAGE_NUM;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return PageRequest.of(pageNum - 1, pageSize);
    }
}


