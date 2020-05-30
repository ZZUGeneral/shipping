var tiltChart1 = echarts.init(document.getElementById("tilt_1"));
var tiltChart2 = echarts.init(document.getElementById("tilt_2"));
var tiltChart3 = echarts.init(document.getElementById("tilt_3"));
var tiltChart4 = echarts.init(document.getElementById("tilt_4"));

var dataDoor1_1_X = [];
var dataDoor1_1_Y = [];
var dataDoor1_2_X = [];
var dataDoor1_2_Y = [];
var dataDoor2_1_X = [];
var dataDoor2_1_Y = [];
var dataDoor2_2_X = [];
var dataDoor2_2_Y = [];
var dataDoor3_1_X = [];
var dataDoor3_1_Y = [];
var dataDoor3_2_X = [];
var dataDoor3_2_Y = [];
var dataDoor4_1_X = [];
var dataDoor4_1_Y = [];
var dataDoor4_2_X = [];
var dataDoor4_2_Y = [];

tiltOption = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            animation: false
        }
    },
    legend: {
        data: ['1号传感器X轴', '1号传感器Y轴', '2号传感器X轴', '2号传感器Y轴']
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

tiltChart1.setOption(tiltOption);
tiltChart1.setOption({
    title: {
        text: '一号闸门'
    }
});
tiltChart2.setOption(tiltOption);
tiltChart2.setOption({
    title: {
        text: '二号闸门'
    }
});
tiltChart3.setOption(tiltOption);
tiltChart3.setOption({
    title: {
        text: '三号闸门'
    }
});
tiltChart4.setOption(tiltOption);
tiltChart4.setOption({
    title: {
        text: '四号闸门'
    }
});

function bindTiltData(topic, data) {
    var parseJSON = $.parseJSON(data);
    //console.log(topic);
    //console.log(parseJSON);
    var time = new Date(1000 * parseJSON.time)
    var x1 = parseJSON.val_1x;
    var y1 = parseJSON.val_1y;
    var x2 = parseJSON.val_2x;
    var y2 = parseJSON.val_2y;
    var item1X = [time, x1];
    var item1Y = [time, y1];
    var item2X = [time, x2];
    var item2Y = [time, y2];
    var dataX_1, dataY_1, dataX_2, dataY_2;
    var chart;
    switch (topic.substr(topic.length - 2, 2)) {
        case 'd1':
            dataX_1 = dataDoor1_1_X;
            dataY_1 = dataDoor1_1_Y;
            dataX_2 = dataDoor1_2_X;
            dataY_2 = dataDoor1_2_Y;
            chart = tiltChart1;
            if (dataX_1.length > 30) {
                dataX_1.shift();
                dataY_1.shift();
                dataX_2.shift();
                dataY_2.shift();
            }
            dataX_1.push(item1X);
            dataY_1.push(item1Y);
            dataX_2.push(item2X);
            dataY_2.push(item2Y);
            break;
        /*        case 'd1_2':
                    dataX_1 = dataDoor1_1_X;
                    dataY_1 = dataDoor1_1_Y;
                    dataX_2 = dataDoor1_2_X;
                    dataY_2 = dataDoor1_2_Y;
                    chart = tiltChart1;
                    if (dataX_2.length > 30) {
                        dataX_2.shift();
                        dataY_2.shift();
                    }
                    dataX_2.push(itemX);
                    dataY_2.push(itemY);
                    break;*/
        case 'd2':
            dataX_1 = dataDoor2_1_X;
            dataY_1 = dataDoor2_1_Y;
            dataX_2 = dataDoor2_2_X;
            dataY_2 = dataDoor2_2_Y;
            chart = tiltChart2;
            if (dataX_1.length > 30) {
                dataX_1.shift();
                dataY_1.shift();
                dataX_2.shift();
                dataY_2.shift();
            }
            dataX_1.push(item1X);
            dataY_1.push(item1Y);
            dataX_2.push(item2X);
            dataY_2.push(item2Y);
            break;
            /*        case 'd2_2':
                        dataX_1 = dataDoor2_1_X;
                        dataY_1 = dataDoor2_1_Y;
                        dataX_2 = dataDoor2_2_X;
                        dataY_2 = dataDoor2_2_Y;
                        chart = tiltChart2;
                        if (dataX_2.length > 30) {
                            dataX_2.shift();
                            dataY_2.shift();
                        }
                        dataX_2.push(itemX);
                        dataY_2.push(itemY);*/
            break;
        case 'd3':
            dataX_1 = dataDoor3_1_X;
            dataY_1 = dataDoor3_1_Y;
            dataX_2 = dataDoor3_2_X;
            dataY_2 = dataDoor3_2_Y;
            chart = tiltChart3;
            if (dataX_1.length > 30) {
                dataX_1.shift();
                dataY_1.shift();
                dataX_2.shift();
                dataY_2.shift();
            }
            dataX_1.push(item1X);
            dataY_1.push(item1Y);
            dataX_2.push(item2X);
            dataY_2.push(item2Y);
            break;
        /*        case 'd3_2':
                    dataX_1 = dataDoor3_1_X;
                    dataY_1 = dataDoor3_1_Y;
                    dataX_2 = dataDoor3_2_X;
                    dataY_2 = dataDoor3_2_Y;
                    chart = tiltChart3;
                    if (dataX_2.length > 30) {
                        dataX_2.shift();
                        dataY_2.shift();
                    }
                    dataX_2.push(itemX);
                    dataY_2.push(itemY);
                    break;*/
        case 'd4':
            dataX_1 = dataDoor4_1_X;
            dataY_1 = dataDoor4_1_Y;
            dataX_2 = dataDoor4_2_X;
            dataY_2 = dataDoor4_2_Y;
            chart = tiltChart4;
            if (dataX_1.length > 30) {
                dataX_1.shift();
                dataY_1.shift();
                dataX_2.shift();
                dataY_2.shift();
            }
            dataX_1.push(item1X);
            dataY_1.push(item1Y);
            dataX_2.push(item2X);
            dataY_2.push(item2Y);
            break;
        /*
                case 'd4_2':
                    dataX_1 = dataDoor4_1_X;
                    dataY_1 = dataDoor4_1_Y;
                    dataX_2 = dataDoor4_2_X;
                    dataY_2 = dataDoor4_2_Y;
                    chart = tiltChart4;
                    if (dataX_2.length > 30) {
                        dataX_2.shift();
                        dataY_2.shift();
                    }
                    dataX_2.push(itemX);
                    dataY_2.push(itemY);
                    break;
        */
    };
    chart.setOption({
        series: [
            {
                name: '1号传感器X轴',
                symbol: "none",
                type: 'line',
                hoverAnimation: false,
                animation: false,
                data: dataX_1
            },
            {
                name: '1号传感器Y轴',
                symbol: "none",
                type: 'line',
                hoverAnimation: false,
                animation: false,
                data: dataY_1
            },
            {
                name: '2号传感器X轴',
                symbol: "none",
                type: 'line',
                hoverAnimation: false,
                animation: false,
                data: dataX_2
            },
            {
                name: '2号传感器Y轴',
                symbol: "none",
                type: 'line',
                hoverAnimation: false,
                animation: false,
                data: dataY_2
            }
        ]
    });
}