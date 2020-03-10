package com.cit.its.shipping.simulator.message;

import lombok.Data;

@Data
public class WaterLevelMessage extends AbstractMessage {

    /**
     * 水位值，单位米，保留两位小数
     */
    private Float val;


    @Override
    public void randomValue() {
//        val = random.nextInt(100, 501) / 100F;
        val = 300F + random.nextInt(1,30);
    }
}
