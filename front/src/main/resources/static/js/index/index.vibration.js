var vibrationChart1 = echarts.init(document.getElementById("vibration_1"));
var vibrationChart2 = echarts.init(document.getElementById("vibration_2"));
var vibrationChart3 = echarts.init(document.getElementById("vibration_3"));
var vibrationChart4 = echarts.init(document.getElementById("vibration_4"));

var vibration_1_h = [];
var vibration_1_v = [];
var vibration_2_h = [];
var vibration_2_v = [];
var vibration_3_h = [];
var vibration_3_v = [];
var vibration_4_h = [];
var vibration_4_v = [];

vibrationOption = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            animation: false
        }
    },
    legend: {
        data:['水平振动','垂直振动']
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
    }
};

vibrationChart1.setOption(vibrationOption);
vibrationChart1.setOption({
    title: {
        text: '一号闸门'
    }
});
vibrationChart2.setOption(vibrationOption);
vibrationChart2.setOption({
    title: {
        text: '二号闸门'
    }
});
vibrationChart3.setOption(vibrationOption);
vibrationChart3.setOption({
    title: {
        text: '三号闸门'
    }
});
vibrationChart4.setOption(vibrationOption);
vibrationChart4.setOption({
    title: {
        text: '四号闸门'
    }
});

function bindVibrationData(topic, data) {
    var parseJSON = $.parseJSON(data);
    var time = new Date(1000 * parseJSON.time)
    var h = parseJSON.val_h;
    var v = parseJSON.val_v;
    var itemH = [time, h];
    var itemV = [time, v];
    var dataH, dataV;
    var chart;
    switch (topic.substr(topic.length - 2, 2)) {
        case 'd1':
            dataH = vibration_1_h;
            dataV = vibration_1_v;
            chart = vibrationChart1;
            break;
        case 'd2':
            dataH = vibration_2_h;
            dataV = vibration_2_v;
            chart = vibrationChart2;
            break;
        case 'd3':
            dataH = vibration_3_h;
            dataV = vibration_3_v;
            chart = vibrationChart3;
            break;
        case 'd4':
            dataH = vibration_4_h;
            dataV = vibration_4_v;
            chart = vibrationChart4;
            break;
    };
    if (dataH.length > 30) {
        dataH.shift();
        dataV.shift();
    }
    dataH.push(itemH);
    dataV.push(itemV);

    chart.setOption({
        series: [
            {
                name:  '水平振动',
                symbol: "none",
                type: 'line',
                hoverAnimation: false,
                animation: false,
                data: dataH
            },
            {
                name: '垂直振动',
                symbol: "none",
                type: 'line',
                hoverAnimation: false,
                animation: false,
                data: dataV
            }
        ]
    });
}