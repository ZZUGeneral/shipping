var client = null;

$(function () {
    connect();
});


function connect() {
    var socket = new SockJS("/ws");
    client = Stomp.over(socket);
    // 关闭控制台输出
    client.debug = null;
    client.connect({}, function (frame) {
        console.log("stomp connected ! ");
        client.subscribe('/realtime/get/waterLevel/w1', function (data) {
            bindWaterLevelData('/realtime/get/waterLevel/w1', data.body);
        });
        client.subscribe('/realtime/get/waterLevel/w2', function (data) {
            bindWaterLevelData('/realtime/get/waterLevel/w2', data.body);
        });
        client.subscribe('/realtime/get/waterLevel/w3', function (data) {
            bindWaterLevelData('/realtime/get/waterLevel/w3', data.body);
        });
        client.subscribe('/realtime/get/waterLevel/w4', function (data) {
            bindWaterLevelData('/realtime/get/waterLevel/w4', data.body);
        });
        client.subscribe('/realtime/get/tilt/d1', function (data) {
            bindTiltData('/realtime/get/tilt/d1', data.body);
        });
        /* client.subscribe('/realtime/get/tilt/d1_2', function (data) {
             bindTiltData('/realtime/get/tilt/d1_2', data.body);
         });*/
        client.subscribe('/realtime/get/tilt/d2', function (data) {
            bindTiltData('/realtime/get/tilt/d2', data.body);
        });
        /* client.subscribe('/realtime/get/tilt/d2_2', function (data) {
             bindTiltData('/realtime/get/tilt/d2_2', data.body);
         });*/
        client.subscribe('/realtime/get/tilt/d3', function (data) {
            bindTiltData('/realtime/get/tilt/d3', data.body);
        });
        /*client.subscribe('/realtime/get/tilt/d3_2', function (data) {
            bindTiltData('/realtime/get/tilt/d3_2', data.body);
        });*/
        client.subscribe('/realtime/get/tilt/d4', function (data) {
            bindTiltData('/realtime/get/tilt/d4', data.body);
        });
        /* client.subscribe('/realtime/get/tilt/d4_2', function (data) {
             bindTiltData('/realtime/get/tilt/d4_2', data.body);
         });*/
        client.subscribe('/realtime/get/angle/d1', function (data) {
            bindAngleData('/realtime/get/angle/d1', data.body);
        });
        client.subscribe('/realtime/get/angle/d2', function (data) {
            bindAngleData('/realtime/get/angle/d2', data.body);
        });
        client.subscribe('/realtime/get/angle/d3', function (data) {
            bindAngleData('/realtime/get/angle/d3', data.body);
        });
        client.subscribe('/realtime/get/angle/d4', function (data) {
            bindAngleData('/realtime/get/angle/d4', data.body);
        });
        client.subscribe('/realtime/get/vibration/d1', function (data) {
            bindVibrationData('/realtime/get/vibration/d1', data.body);
        });
        client.subscribe('/realtime/get/vibration/d2', function (data) {
            bindVibrationData('/realtime/get/vibration/d2', data.body);
        });
        client.subscribe('/realtime/get/vibration/d3', function (data) {
            bindVibrationData('/realtime/get/vibration/d3', data.body);
        });
        client.subscribe('/realtime/get/vibration/d4', function (data) {
            bindVibrationData('/realtime/get/vibration/d4', data.body);
        });
        client.subscribe('/realtime/get/weather/general', function (data) {
            bindWeatherData('/realtime/get/weather/general', data.body);
        });
        client.subscribe('/realtime/get/weather/rainfall', function (data) {
            bindWeatherData('/realtime/get/weather/rainfall', data.body);
        });
        client.subscribe('/realtime/get/weather/visibility', function (data) {
            bindWeatherData('/realtime/get/weather/visibility', data.body);
        });

    });
}

function disconnect() {
    if (client != null) {
        client.disconnect();
    }
}

window.onunload = function (ev) {
    disconnect();
}
