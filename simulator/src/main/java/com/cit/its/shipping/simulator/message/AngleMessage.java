package com.cit.its.shipping.simulator.message;

import lombok.Data;

@Data
public class AngleMessage extends AbstractMessage {

    /**
     * 角度值，单位度，范围 0~70
     */
    private Float val;


    @Override
    public void randomValue() {
//        val = random.nextInt(100, 501) / 100F;
        val = random.nextInt(0, 7000) / 100F;
    }
}
