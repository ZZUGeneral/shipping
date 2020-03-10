package com.cit.its.shipping.simulator.message;

import lombok.Data;

@Data
public class WeatherGeneralMessage extends AbstractMessage {

    private Float temperature;
    private Float humidity;
    private Float airPressure;
    private Float windSpeed;
    private Float windDirection;




    @Override
    public void randomValue() {

        temperature = 20F + random.nextInt(-5, 5);
        humidity = 30F + random.nextInt(-10, 10);
        airPressure = 1000F + random.nextInt(-50, 50);
        windSpeed = 3F + random.nextInt(-1, 1);
        windDirection = 180F + random.nextInt(-90, 90);
    }
}
