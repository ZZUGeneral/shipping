var datatable;
$(function () {

    initDataTable();
    $('#id').hide();
    $('#button').click(function () {
        $('#id').show();
        $('#id').animate({
            // height:'300px',
            // width:'260px',
            top: '200px',
            opacity: '1.0',
            left: '480px',
            top: '200px'
        }, 10)
    });
    $('#header-right_create').click(function () {
        $('#id').hide();

    });
    sensor();
    connect();
})

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
                    if (data == "1") return '<span class="badge badge-danger">一级</span>';
                    if (data == "2") return '<span class="badge badge-success">二级</span>';
                    if (data == "3") return '<span class="badge badge-success">三级</span>';

                },
                "className": "text-center"
            },
            {"data": "equip"},
            {"data": "data"},
            {"data": "relation"},
            {"data": "value"},
            {"data": "desc"},

        ],
        language: {
            "url": context + "/plugins/dataTable/datatables_language.json"
        },
    });
}

function addTrigger() {

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
            if (data.statusCode == 100) {
                var options = "<option>--请选择传感器--</option>";
                for (var i = 0; i < data.data.length; i++) {
                    options += "<option value='" + data.data[i].sensor + "'>" + data.data[i].name + "</option>";
                }
                $("#create_equip").html(options);
                var create_data_options = "<option>--请选择传感器数据项--</option>";
                //活动名称下拉选改变事件：
                $(".create_equip").change(function () {
                    var selectedIndex = $(this).get(0).selectedIndex;
                    if (selectedIndex == 0) {
                        console.log("没有进行选择");
                        $(".create_data").html(create_data_options);
                    } else {
                        var trigger_data = data.data[selectedIndex].trigger_data;
                        for (var i = 0; i < trigger_data.length; i++) {
                            create_data_options += "<option value='" + trigger[i] + "'>" + trigger_data[i] + "</option>";
                        }
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