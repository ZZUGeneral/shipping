var myChart1 = echarts.init(document.getElementById("w1"));
var myChart2 = echarts.init(document.getElementById("w2"));
var myChart3 = echarts.init(document.getElementById("w3"));
var myChart4 = echarts.init(document.getElementById("w4"));


var data1 = [];
var data2 = [];
var data3 = [];
var data4 = [];

var options = {
    connectTimeout: 4000, // 超时时间
    clientId: 'client_front_index_websocket',
}
var client = mqtt.connect("ws://192.168.102.50:8083/mqtt", options)
client.on('reconnect', function(error) {
    console.log('正在重连:', error)
})

client.on('error', function(error) {
    console.log('连接失败:', error)
})

client.on('connect', function (e) {
    console.log('成功连接服务器')
    // 订阅一个主题
    client.subscribe('get/waterLevel/+', {qos: 1}, function (error) {
        if (!error) {
            console.log('订阅成功')
        }
    })
});

// 监听接收消息事件
client.on('message', function(topic, message) {
    console.log('收到来自', topic, '的消息', message.toString())
    var parseJSON = $.parseJSON(message);
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
})

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
        animation: false,
        data: data1
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