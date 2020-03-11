$(document).ready(function () { // 可以简写：$(function(){

    // ajax 调用后台接口
    $("#invokeAjax").click(function () {
        var list = {
            name: "fengshiqing",
            email: "938481169@qq.com"
        };

        $.ajax({
            type: "POST", // 请求方式:post、get
            contentType: "application/json;charset=UTF-8", // 请求的媒体类型
            url: "/JavaWeb/SpringMVC/dealAjaxReq", // 请求地址
            data: JSON.stringify(list), // json对象，必须转成json字符串，后台才能正常解析
            //请求成功
            success: function (result) {
                console.log(result);
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        })

    });

});


