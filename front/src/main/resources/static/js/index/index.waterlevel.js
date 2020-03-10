var myChart1 = echarts.init(document.getElementById("water_level_1"));
var myChart2 = echarts.init(document.getElementById("water_level_2"));
var myChart3 = echarts.init(document.getElementById("water_level_3"));
var myChart4 = echarts.init(document.getElementById("water_level_4"));

var data1 = [];
var data2 = [];
var data3 = [];
var data4 = [];

commonOption = {
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
myChart1.setOption(commonOption);
myChart1.setOption({
    title: {
        text: '上游'
    }
});
myChart2.setOption(commonOption);
myChart2.setOption({
    title: {
        text: '下游'
    }
});
myChart3.setOption(commonOption);
myChart3.setOption({
    title: {
        text: '闸门上游'
    }
});
myChart4.setOption(commonOption);
myChart4.setOption({
    title: {
        text: '闸门下游'
    }
});

function bindWaterLevelData(topic, data) {
    var parseJSON = $.parseJSON(data);
    var time = new Date(1000 * parseJSON.time)
    var value = parseJSON.val;
    var item = [time, value];
    var t;
    var chart;
    if (_.endsWith(topic, 'w1')) {
        t = data1;
        chart = myChart1;
    } else if (_.endsWith(topic, 'w2')) {
        t = data2;
        chart = myChart2;
    } else if (_.endsWith(topic, 'w3')) {
        t = data3;
        chart = myChart3;
    } else if (_.endsWith(topic, 'w4')) {
        t = data4;
        chart = myChart4;
    }
    if (t.length >= 100) {
        t.shift();
    }
    t.push(item);
    chart.setOption({
        series: [{
            data: t
        }]
    });
}