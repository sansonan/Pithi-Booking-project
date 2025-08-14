package com.sonan.pithi_booking.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageDTO<T> {
    private List<T> list;
    private PaginationDTO pagination;

    public PageDTO(Page<T> page) {
        this.list = page.getContent();
        this.pagination = PaginationDTO.builder()
                .empty(page.isEmpty())
                .first(page.isFirst())
                .last(page.isLast())
                .pageNumber(page.getNumber() + 1) // Optional: convert to 1-based
                .pageSize(page.getSize())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .numberOfElements(page.getNumberOfElements())
                .build();
    }

    public List<T> getList() {
        return list;
    }

    public PaginationDTO getPagination() {
        return pagination;
    }
}


