package com.digitalbooking.backend.Repository.custom;

import lombok.Data;

import java.util.List;

@Data
public class CustomPage<T> {
    private Long totalElements;
    private List<T> content;
}
