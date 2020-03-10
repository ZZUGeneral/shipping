package com.cit.its.shipping.simulator.message;

import lombok.Data;

@Data
public class WeatherVisibilityMessage extends AbstractMessage {

    private Float val;



    @Override
    public void randomValue() {

        val = 1000F + random.nextInt(-5, 5);
    }
}
