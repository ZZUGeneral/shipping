package com.cit.its.shipping.simulator.message;

import lombok.Data;

@Data
public class TiltMessage extends AbstractMessage {

    private Float val_x;
    private Float val_y;


    @Override
    protected void randomValue() {
        val_x = random.nextInt(0, 30) - 15F;
        val_y = random.nextInt(0, 30) - 15F;
    }
}
