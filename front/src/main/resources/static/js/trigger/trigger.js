var datatable;
$(function () {

    initDataTable();
    $('#id').hide();
    sensor();
    selectTrigger();
    connect();
})

function triggerShow() {

    $('#id').show();
    $('#id').animate({
        // height:'300px',
        // width:'260px',
        top: '200px',
        opacity: '1.0',
        left: '480px',
        top: '200px'
    }, 10)
}

function dropTrigger() {
    var triggerName = prompt("请输入删除的触发器名称：", "");
    if (!triggerName.match(triggerRe) || triggerName == null) {
        alert("请输入正确格式的告警名称");
        $('#createTriggerName').style.color = "red";
        return;
    } else {
        $.ajax({
            url: context + '/trigger/dropTrigger',
            type: "POST",
            dataType: 'json',
            data: {"triggerName": triggerName},
            success: function (data) {
                alert("删除成攻");
                //   datatable.ajax.reload();
                location.reload();
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    }
}

function selectTrigger() {
    $('#createTriggerName').blur(function () {
        var triggerName = $("#createTriggerName").val();
        $.ajax({
            "url": context + '/trigger/selectTrigger',
            "dataType": "json",
            "type": "POST",
            "data": {"triggerName": triggerName},
            success: function success(data) {
                if (data.code == 1) alert("触发器已存在，请重新命名!");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("请求错误,请重试！");
            }
        });

    });
}

function clientReset() {
    $('#client_form')[0].reset();
    datatable.ajax.reload();
}

function clientSearch() {
    datatable.ajax.reload();
}

function initDataTable() {
    datatable = $('#trigger_table').DataTable({
        "serverSide": true,
        "ordering": false,
        "searching": false,
        "lengthChange": false,
        "ajax": {
            "url": context + '/trigger/pageData',
            "dataType": "json",
            "type": "POST",
            "data": function (data) {
                for (var key in data) {
                    if (key.indexOf("columns") == 0 || key.indexOf("order") == 0 || key.indexOf("search") == 0) {
                        delete data[key];
                    }
                }
                data.size = data.length;
                data.page = data.start / data.size + 1;
                data.trigger_name = $('#triggerName').val();
                data.grade = $('#grade').val();
                data.equip = $('#equip').val();
                //console.log(data);
                delete data['start'];
                delete data['length'];
            },
            "dataFilter": function (json) {
                var jsonObj = $.parseJSON(json);
                var tableData = {};
                tableData.recordsTotal = jsonObj.data.total;
                tableData.recordsFiltered = jsonObj.data.total;
                tableData.data = jsonObj.data.data;
                return JSON.stringify(tableData);
            }
        },
        "columns": [
            {"data": "triggerId"},
            {"data": "triggerName"},
            {
                "data": "grade",
                "render": function (data, type, full, meta) {
                    if (data == "0") return '<span class="badge badge-light">未分类</span>';
                    if (data == "1") return '<span class="badge badge-success">信息</span>';
                    if (data == "2") return '<span class="badge badge-info">一般</span>';
                    if (data == "3") return '<span class="badge badge-warning">警告</span>';
                    if (data == "4") return '<span class="badge badge-danger">严重</span>';
                    if (data == "5") return '<span class="badge badge-dark">灾难</span>';
                },
                "className": "text-center"
            },
            {
                "data": "equip",
                render:
                    function (data, type, full, meta) {
                        //console.log(data);
                        if (data == "msg_angle") return "角度传感器";
                        if (data == "msg_tilt") return "倾斜传感器";
                        if (data == "msg_vibration") return "垂直传感器";
                        if (data == "msg_water_level") return "水位传感器";
                        else return "天气传感器";
                    }
            },
            {
                "data":
                    "data"
            }
            ,
            {
                "data":
                    "leValue"
            }
            ,
            {
                "data":
                    "geValue"
            }
            ,
            {
                "data": "createTime",
                render:

                    function (data, type, full, meta) {
                        //console.log(data);
                        if (data == null) return '无';
                        return moment(data).format("YYYY-MM-DD HH:mm:ss")
                    }
            }
            ,

        ],
        language: {
            "url":
                context + "/plugins/dataTable/datatables_language.json"
        }
        ,
    });
}

function addTrigger() {
    var triggerRe = /^[a-zA-Z0-9_]{1,}$/;
    var triggerName = $('#createTriggerName').val();
    if (!triggerName.match(triggerRe) || triggerName == null) {
        alert("请输入正确格式的告警名称");
        $('#createTriggerName').style.color = "red";
        return;
    }
    var grade = $('#createGrade').val();
    var equip = $('#createEquip').val();
    if (!equip.match(triggerRe)) {
        alert("请选择传感器");
        $('#createEquip').style.color = "red";
        return;
    }
    var trigger_data = $('#createData').val();
    if (!trigger_data.match(triggerRe)) {
        alert("请选择数据项");
        $('#createData').style.color = "red";
        return;
    }
    var leValue = $('#leValue').val();
    var geValue = $('#geValue').val();
    if (leValue > geValue) {
        alert("请输入正确的数据范围！！！");
        $('#leValue').style.color = "red";
        $('#geValue').style.color = "red";
        return;
    }
    var triggerDesc = $('#createDesc').val();
    if (triggerDesc == null) {
        alert("请输入告警描述！！！");
        $('#createDesc').style.color = "red";
        return;
    }
    $.ajax({
        "url": context + '/trigger/createTrigger',
        contentType: "application/json",
        "dataType": "json",
        "type": "POST",
        "data": function (data) {
            data = {
                triggerName: triggerName,
                grade: grade,
                equip: equip,
                data: trigger_data,
                leValue: leValue,
                geValue: geValue,
                triggerDesc: triggerDesc,
            };
            return JSON.stringify(data);
        }(),
        success: function success(data) {
            if (data == 1) alert("添加成功");
            else alert("添加失败");
            location.reload();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("添加失败");
        }
    });

}

function cancelTrigger() {
    $('#id').hide();
}

function sensor() {
    $.ajax({
        type: "get",
        url: "/js/trigger/trigger_data.json",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            console.log(data);
            console.log(data.length);
            if (data.length > 0) {
                var options = "<option value=''>--请选择传感器--</option>";
                for (var i = 0; i < data.length; i++) {
                    options += "<option value='" + data[i].sensor + "'>" + data[i].name + "</option>";
                }
                $("#createEquip").html(options);
                $("#equip").html(options);
                //活动名称下拉选改变事件：
                $("#createEquip").change(function () {
                    var create_data_options = "<option>--请选择传感器数据项--</option>";
                    var selectedIndex = $(this).get(0).selectedIndex;
                    if (selectedIndex == 0) {
                        alter("请选择传感器");
                    } else {
                        // console.log(selectedIndex);
                        var trigger_data = data[selectedIndex - 1].trigger_data;
                        // console.log(trigger_data);
                        for (var i = 0; i < trigger_data.length; i++) {
                            if (i == 0)
                                create_data_options += "<option value='" + trigger_data[i] + "'  selected>" + trigger_data[i] + "</option>";
                            else create_data_options += "<option value='" + trigger_data[i] + "'>" + trigger_data[i] + "</option>";
                        }
                        //  console.log(create_data_options);
                        $("#createData").html(create_data_options);
                    }
                });
                /*$("#equip").change(function () {
                    var create_data_options = "<option>--请选择传感器数据项--</option>";
                    var selectedIndex = $(this).get(0).selectedIndex;
                    if (selectedIndex == 0) {
                        console.log("没有进行选择");
                    } else {
                        // console.log(selectedIndex);
                        var trigger_data = data[selectedIndex - 1].trigger_data;
                        // console.log(trigger_data);
                        for (var i = 0; i < trigger_data.length; i++) {
                            create_data_options += "<option value='" + trigger_data[i] + "'>" + trigger_data[i] + "</option>";
                        }
                        //  console.log(create_data_options);
                        $("#data").html(create_data_options);
                    }
                });*/

            } else {
                console.log("获取数据失败");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function connect() {
    var socket = new SockJS("/ws");
    client = Stomp.over(socket);
    // 关闭控制台输出
    client.debug = null;
    client.connect({}, function (frame) {
        console.log("stomp connected ! ");
    });
}