var angleChart1 = echarts.init(document.getElementById("angle_1"));
var angleChart2 = echarts.init(document.getElementById("angle_2"));
var angleChart3 = echarts.init(document.getElementById("angle_3"));
var angleChart4 = echarts.init(document.getElementById("angle_4"));

var angleData1 = [];
var angleData2 = [];
var angleData3 = [];
var angleData4 = [];

angleOption = {
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
angleChart1.setOption(angleOption);
angleChart1.setOption({
    title: {
        text: '一号闸门'
    }
});
angleChart2.setOption(angleOption);
angleChart2.setOption({
    title: {
        text: '二号闸门'
    }
});
angleChart3.setOption(angleOption);
angleChart3.setOption({
    title: {
        text: '三号闸门'
    }
});
angleChart4.setOption(angleOption);
angleChart4.setOption({
    title: {
        text: '四号闸门'
    }
});

function bindAngleData(topic, data) {
    var parseJSON = $.parseJSON(data);
    var time = new Date(1000 * parseJSON.time)
    var value = parseJSON.val;
    var item = [time, value];
    var t;
    var chart;
    if (_.endsWith(topic, 'd1')) {
        t = angleData1;
        chart = angleChart1;
    } else if (_.endsWith(topic, 'd2')) {
        t = angleData2;
        chart = angleChart2;
    } else if (_.endsWith(topic, 'd3')) {
        t = angleData3;
        chart = angleChart3;
    } else if (_.endsWith(topic, 'd4')) {
        t = angleData4;
        chart = angleChart4;
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