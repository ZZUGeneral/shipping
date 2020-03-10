var waterChart;
function initWaterLevelChart() {
    waterChart = echarts.init(document.getElementById("water_chart"));
    waterOption = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                animation: false
            }
        },
        legend: {
            data:['上游','下游','闸门上游','闸门下游']
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
    waterChart.setOption(waterOption);
}


function waterLevelHistory() {
    var begin = $('#beginDateTime').val();
    var end = $('#endDateTime').val();
    console.log(begin);
    console.log(end);
    $.post(
        context + "/history/waterLevel",
        {
            "beginDateStr": begin,
            "endDateStr": end
        },
        function (data) {
            var obj = data.data;
            console.log(obj);
            var waterLevelMap = obj.waterLevel;
            var sta = obj.sta;
            $('#wl_max').text(sta.max);
            $('#wl_min').text(sta.min);
            $('#wl_avg').text(_.floor(sta.avg, 4));
            $('#wl_vpop').text(_.floor(sta.varPop, 4));
            $('#wl_spop').text(_.floor(sta.stdDevPop, 4));
            $('#wl_vsamp').text(_.floor(sta.varSamp, 4));
            $('#wl_ssamp').text(_.floor(sta.stdDevSamp, 4));
            var w1Data = waterLevelMap['get/waterLevel/w1'];
            var w2Data = waterLevelMap['get/waterLevel/w2'];
            var w3Data = waterLevelMap['get/waterLevel/w3'];
            var w4Data = waterLevelMap['get/waterLevel/w4'];
            var data1 = [];
            var data2 = [];
            var data3 = [];
            var data4 = [];
            $.each(w1Data, function (i, e) {
                var item = [new Date(1000 * e.time), e.value];
                data1.push(item);
            });
            $.each(w2Data, function (i, e) {
                var item = [new Date(1000 * e.time), e.value];
                data2.push(item);
            });
            $.each(w3Data, function (i, e) {
                var item = [new Date(1000 * e.time), e.value];
                data3.push(item);
            });
            $.each(w4Data, function (i, e) {
                var item = [new Date(1000 * e.time), e.value];
                data4.push(item);
            });
            waterChart.setOption({
                series: [
                    {
                        name:'上游',
                        type:'line',
                        showSymbol: false,
                        data: data1
                    },
                    {
                        name:'下游',
                        type:'line',
                        showSymbol: false,
                        data: data2
                    },
                    {
                        name:'闸门上游',
                        type:'line',
                        showSymbol: false,
                        data: data3
                    },
                    {
                        name:'闸门下游',
                        type:'line',
                        showSymbol: false,
                        data: data4
                    }
                ]
            });
        }
    );
}

function waterLevelReset() {
    $('#water_level_form')[0].reset();
}