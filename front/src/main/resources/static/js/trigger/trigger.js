var datatable;
$(function () {
    initDataTable();
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