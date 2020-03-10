package com.cit.its.shipping.front.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 传感器在线数VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DevicesStateVo {

    /**
     * 传感器类型
     */
    private Integer type;

    /**
     * 在线数量
     */
    private Integer online;

    /**
     * 总数
     */
    private Integer total;

}
