var context = [[${#httpServletRequest.getContextPath()}]];

$(document).ready(function () {
    $("#login").click(function (event) {
        var name = $("#username").val();
        var pwd = $("#password").val();
        if (name == "") {
            alert("用户名不能为空！");
        } else if (pwd == "") {
            alert("密码不能为空！");
        } else if (name != "" && pwd != "" && pwd.length >= 6) {
            $.ajax({
                "url": context + '/login',
                contentType: "application/json",
                "dataType": "json",
                "type": "POST",
                "data": function (data) {
                    data = {
                        username: name,
                        password: pwd
                    };
                    return JSON.stringify(data);
                }(),
                success: function success(data) {
                    alert("登录成功");
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("登录失败");
                }
            });

        }
    });
});