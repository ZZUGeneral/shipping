package com.cit.its.shipping.simulator.message;

import lombok.Data;

@Data
public class WeatherRainfallMessage extends AbstractMessage {

    private Float val;



    @Override
    public void randomValue() {

        val = 10F + random.nextInt(-5, 5);
    }
}
