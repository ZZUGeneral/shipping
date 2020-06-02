var datatable;

function clientReset() {
    $('#client_form')[0].reset();
    datatable.ajax.reload();
}

function clientSearch() {
    datatable.ajax.reload();
}

function initDataTable() {
    datatable = $('#event_table').DataTable({
        "serverSide": true,
        "ordering": false,
        "searching": false,
        "lengthChange": false,
        "ajax": {
            "url": context + '/event/pageData',
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
                data.beginTime = $('#beginTime').val();
                data.endTime = $('#endTime').val();
                data.grade = $('#grade').val();
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
            {"data": "eventId"},
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
                "data": "createTime",
                render:
                    function (data, type, full, meta) {
                        if (data == null) return '无';
                        return moment(data).format("YYYY-MM-DD HH:mm:ss")
                    }
            }, {
                "data": "eventDesc"
            },
            {
                "data": "dealNo",
                render: function (data, type, full, meta) {
                    if (data != 0) {
                        return '<span>' + data + '</span>';
                    } else {
                        return '<span onclick="deal_event()">分配</span>'
                    }
                }
            }
        ],
        language: {
            "url": context + "/plugins/dataTable/datatables_language.json"
        },
    });
}

function deal_event(node) {
    var eventID = prompt("请输入分配处理的事件ID：", "");
    var dealNo = prompt("请输入分配处理的事件的人员编号：", "");
    $.ajax({
        url: context + '/event/dealEvent',
        type: "POST",
        dataType: 'json',
        data: {"event_no": eventID, "deal_no": dealNo},
        success: function (data) {
            alert("分配成攻");
            location.reload();
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