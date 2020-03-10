var tempChart = echarts.init(document.getElementById("temperature"));
var humiChart = echarts.init(document.getElementById("humidity"));
var airChart = echarts.init(document.getElementById("airPressure"));
var windSpeedChart = echarts.init(document.getElementById("windSpeed"));
var windDireChart = echarts.init(document.getElementById("windDirection"));
var rainfallChart = echarts.init(document.getElementById("rainfall"));
var visibilityChart = echarts.init(document.getElementById("visibility"));

var tData = [];
var hData = [];
var aData = [];
var wsData = [];
var wdData = [];
var rData = [];
var vData = [];

weatherOption = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            animation: false
        }
    },
    xAxis: {
        type: 'time',
        splitLine: {
            show: false
        }
    },
    yAxis: {
        type: 'value',
        boundaryGap: [0, '100%'],
        splitLine: {
            show: false
        }
    },
    series: [{
        type: 'line',
        showSymbol: false,
        hoverAnimation: false,
        animation: false
    }]
};
tempChart.setOption(weatherOption);
tempChart.setOption({
    title: {
        text: '温度'
    }
});
humiChart.setOption(weatherOption);
humiChart.setOption({
    title: {
        text: '湿度'
    }
});
airChart.setOption(weatherOption);
airChart.setOption({
    title: {
        text: '气压'
    }
});
windSpeedChart.setOption(weatherOption);
windSpeedChart.setOption({
    title: {
        text: '风速'
    }
});
windDireChart.setOption(weatherOption);
windDireChart.setOption({
    title: {
        text: '风向'
    }
});
rainfallChart.setOption(weatherOption);
rainfallChart.setOption({
    title: {
        text: '雨量'
    }
});
visibilityChart.setOption(weatherOption);
visibilityChart.setOption({
    title: {
        text: '能见度'
    }
});

function bindWeatherData(topic, data) {
    var value = $.parseJSON(data);
    var time = new Date(1000 * value.time)
    if (_.endsWith(topic, 'general')) {
        tData.push([time, value.temperature]);
        hData.push([time, value.humidity]);
        aData.push([time, value.airPressure]);
        wsData.push([time, value.windSpeed]);
        wdData.push([time, value.windDirection]);
        if (tData.length > 30) {
            tData.shift();
            hData.shift();
            aData.shift();
            wsData.shift();
            wdData.shift();
        };
        tempChart.setOption({
            series: [{
                data: tData
            }]
        });
        humiChart.setOption({
            series: [{
                data: hData
            }]
        });
        airChart.setOption({
            series: [{
                data: aData
            }]
        });
        windSpeedChart.setOption({
            series: [{
                data: wsData
            }]
        });
        windDireChart.setOption({
            series: [{
                data: wdData
            }]
        });
    } else if (_.endsWith(topic, 'rainfall')) {
        rData.push([time, value.val]);
        if (rData.length > 30) {
            rData.shift();
        }
        rainfallChart.setOption({
            series: [{
                data: rData
            }]
        });
    } else if (_.endsWith(topic, 'visibility')) {
        vData.push([time, value.val]);
        if (vData.length > 30) {
            vData.shift();
        }
        visibilityChart.setOption({
            series: [{
                data: vData
            }]
        });
    }
}