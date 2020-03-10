package com.cit.its.shipping.front.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageVo<T> {

    private Integer page;
    private Integer pageSize;
    private Long total;
    private List<T> data;

}
