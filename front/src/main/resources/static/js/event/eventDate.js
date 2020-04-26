$(function () {
    $(".form_datetime").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        autoclose: true,
        todayBtn: true,
        minuteStep: 1,
        language: 'zh-CN'
    });
    initDataTable();
    connect();
});