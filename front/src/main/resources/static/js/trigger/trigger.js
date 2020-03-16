var datatable;
$(function () {
    initDataTable();
    devicesState();
    connect();
})


function devicesState() {
    $.get(context + "/devices/state", function (data) {
        $('h1').html('0/0');
        if (data.code == 0) {
            $.each(data.data, function (t, e) {
                switch (e.type) {
                    case 0 :
                        $("#h_all").html(e.online + "/" + e.total);
                        break;
                    case 1 :
                        $("#h_water").html(e.online + "/" + e.total);
                        break;
                    case 2 :
                        $("#h_tilt").html(e.online + "/" + e.total);
                        break;
                    case 3 :
                        $("#h_angle").html(e.online + "/" + e.total);
                        break;
                    case 4 :
                        $("#h_vibration").html(e.online + "/" + e.total);
                        break;
                    case 5 :
                        $("#h_weather").html(e.online + "/" + e.total);
                        break;
                }
            })
        }
    })
}


function clientReset() {
    $('#client_form')[0].reset();
    datatable.ajax.reload();
}

function clientSearch() {
    datatable.ajax.reload();
}

function initDataTable() {
    datatable = $('#devices_table').DataTable({
        "serverSide": true,
        "ordering": false,
        "searching": false,
        "lengthChange": false,
        "ajax": {
            "url": context + '/devices/pageData',
            "dataType": "json",
            "type": "POST",
            "data": function (data) {
                for(var key in data){
                    if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){
                        delete data[key];
                    }
                }
                data.size = data.length;
                data.page = data.start / data.size + 1;
                data.clientId = $('#clientId').val();
                data.name = $('#name').val();
                data.typeValue = $('#type').val();
                data.state = $('#state').val();
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
            {"data":"clientId"},
            {"data":"name"},
            {
                "data":"state",
                "render": function (data, type, full, meta) {
                    if (data == "0") return '<span class="badge badge-danger">离线</span>';
                    if (data == "1") return '<span class="badge badge-success">在线</span>';

                },
                "className": "text-center"
            },
            {
                "data":"onlineAt",
                "render": function (data, type, full, meta) {
                    if (data == null) return '无';
                    return moment(data).format("YYYY-MM-DD HH:mm:ss")
                }
            },
            {
                "data":"offlineAt",
                "render": function (data, type, full, meta) {
                    if (data == null) return '无';
                    return moment(data).format("YYYY-MM-DD HH:mm:ss")
                }
            }
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