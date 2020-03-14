// 立即执行函数
// https://www.cnblogs.com/wangdan0915/p/7852608.html
// https://www.cnblogs.com/L-xmin/p/11178599.html
$(document).ready(function () { // 可以简写：$(function(){


    /**
     * 功能描述：手写ajax，Asynchronous JavaScript and XML（异步的 JavaScript 和 XML）
     *
     * 参考：https://blog.csdn.net/weixin_37580235/article/details/81459282
     */
    $("#testAjax").click(function () {
        debugger;
        let xmlhttp;
        if (window.XMLHttpRequest) {
            // 所有现代浏览器（IE7+、Firefox、Chrome、Safari 以及 Opera）均内建 XMLHttpRequest 对象（IE5、IE6使用 ActiveXObject）。
            // XMLHttpRequest 用于在后台与服务器交换数据。这意味着可以在不重新加载整个网页的情况下，对网页的某部分进行更新。
            xmlhttp = new XMLHttpRequest();// IE7+、Firefox、Chrome、Opera、Safari 浏览器执行代码
        } else {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");// IE6, IE5 浏览器执行代码
        }

        // 如需将请求发送到服务器，我们使用 XMLHttpRequest 对象的 open() 和 send() 方法：
        xmlhttp.open("GET", "/JavaWeb/SpringMVC/testAjax", true);// 注意路径，要加上“/JavaWeb”
        xmlhttp.send();

        // XMLHttpRequest 对象的三个常用的属性：1.onreadystatechange 属性，2.readyState 属性，3.responseText 属性
        xmlhttp.onreadystatechange = function() {// 这个方法可以放在send()方法之后。
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
//			document.getElementById("123").innerHTML = xmlhttp.responseText;
                document.getElementById("123").value = xmlhttp.responseText;
            }
        }
    });


    /**
     * 功能描述：使用 Jquery 的 ajax 调用后台接口
     */
    $("#invokeAjax").click(function () {
        // JSON.parse();//用于将一个 JSON 字符串转换为 JavaScript 对象。
        // JSON.stringify();// 用于将 JavaScript 值转换为 JSON 字符串。
        const list = {
            name: "fengshiqing",
            email: "938481169@qq.com"
        };

        $.ajax({
            type: "POST", // 请求方式:post、get
            url: "/JavaWeb/SpringMVC/dealAjaxReq", // 请求地址
            contentType: "application/json;charset=UTF-8", // 请求的媒体类型，也可以这么写"application/json"。这句不加出现415错误:Unsupported Media Type
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


