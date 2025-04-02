package com.bezkoder.spring.mssql.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Paginator {
    private int page;
    private int size;
    private long total;
    private List<?> content;
}
