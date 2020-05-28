package com.cit.its.shipping.simulator.message;

import lombok.Data;

@Data
public class TiltMessage extends AbstractMessage {

    private Float val_1x;
    private Float val_1y;
    private Float val_2x;
    private Float val_2y;

    @Override
    protected void randomValue() {
        val_1x = random.nextInt(0, 30) - 15F;
        val_1y = random.nextInt(0, 30) - 15F;
        val_2x = random.nextInt(0, 30) - 15F;
        val_2y = random.nextInt(0, 30) - 15F;
    }
}
