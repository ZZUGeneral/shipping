package com.cit.its.shipping.simulator.message;

import lombok.Data;

@Data
public class VibrationMessage extends AbstractMessage {

    /**
     * 水平方向振动频率，单位hz
     */
    private Float val_h;
    /**
     * 垂直方向振动频率，单位hz
     */
    private Float val_v;


    @Override
    public void randomValue() {
        val_h = random.nextInt(0, 50) + 0F;
        val_v = random.nextInt(0, 50) + 0F;
    }
}
