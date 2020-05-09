var datatable;
$(function () {

    initDataTable();
    $('#id').hide();
    selectTrigger
    sensor();
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
    if (triggerName != null && triggerName != "") {
        $.ajax({
            url: context + '/trigger/dropTrigger',
            type: "POST",
            dataType: 'json',
            data: {"triggerName": triggerName},
            success: function (data) {
                alert("删除成攻");
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
    $('createTriggerName').blur(function () {
        var triggerName = $("createTriggerName").val();
        $.ajax({
            "url": context + '/trigger/selectTrigger',
            "dataType": "json",
            "type": "POST",
            "data": {"triggerName": triggerName},
            success: function success(data) {
                if (data == 1) alert("触发器已存在，请重新命名。");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
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
                data.data = $('#data').val();
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
                    if (data == "0") return '<span class="badge badge-success">未分类</span>';
                    if (data == "1") return '<span class="badge badge-danger">信息</span>';
                    if (data == "2") return '<span class="badge badge-success">一般</span>';
                    if (data == "3") return '<span class="badge badge-success">警告</span>';
                    if (data == "4") return '<span class="badge badge-success">严重</span>';
                    if (data == "5") return '<span class="badge badge-success">灾难</span>';
                },
                "className": "text-center"
            },
            {"data": "equip"},
            {"data": "data"},
            {"data": "leValue"},
            {"data": "geValue"},
            {"data": "createTime"},

        ],
        language: {
            "url": context + "/plugins/dataTable/datatables_language.json"
        },
    });
}

function addTrigger() {
    $.ajax({
        "url": context + '/trigger/createTrigger',
        contentType: "application/json",
        "dataType": "json",
        "type": "POST",
        "data": function (data) {
            data = {
                triggerName: $('#createTriggerName').val(),
                grade: $('#createGrade').val(),
                equip: $('#createEquip').val(),
                data: $('#createData').val(),
                leValue: $('#leValue').val(),
                geValue: $('#geValue').val(),
                triggerDesc: $('#createDesc').val(),
            };
            return JSON.stringify(data);
        }(),
        success: function success(data) {
            alert("添加成功");
            $('#id').hide();
            datatable.ajax.reload();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
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
                var options = "<option>--请选择传感器--</option>";
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
                        console.log("没有进行选择");
                    } else {
                        // console.log(selectedIndex);
                        var trigger_data = data[selectedIndex - 1].trigger_data;
                        // console.log(trigger_data);
                        for (var i = 0; i < trigger_data.length; i++) {
                            create_data_options += "<option value='" + trigger_data[i] + "'>" + trigger_data[i] + "</option>";
                        }
                        //  console.log(create_data_options);
                        $("#createData").html(create_data_options);
                    }
                });
                $("#equip").change(function () {
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
                });

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
        client.subscribe('/realtime/sensor', function (data) {
            setTimeout(function () {
                devicesState()
                datatable.ajax.reload(false);
            }, 1500);
        });
    });
}