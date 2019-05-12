/**
 * JS源码文件
 */
function aaa() {
	alert("hello");
}

var app = new Vue({
	el: '#app',
	data: {
		message: "Hello Vue!"
	}
});

var app2 = new Vue({
	el: '#app-2',
	data: {
		message: '页面加载于 ' + new Date().toLocaleString()
	}
});

var app3 = new Vue({
	el: '#app-3',
	data: {
		seen: true
	}
});

var app4 = new Vue({
	el: '#app-4',
	data: {
		todos: [
			{ text: '学习 JavaScript' },
			{ text: '学习 Vue' },
			{ text: '整个牛项目' }
		]
	}
});

var app5 = new Vue({
	el: '#app-5',
	data: {
		message: 'Hello Vue.js!'
	},
	methods: {
		reverseMessage: function () {
			this.message = this.message.split('').reverse().join('')
		}
	}
});

var app6 = new Vue({
	el: '#app-6',
	data: {
		message: 'Hello Vue!'
	}
});



function ajax() {
	var xmlhttp;
	if (window.XMLHttpRequest) {
	// 所有现代浏览器（IE7+、Firefox、Chrome、Safari 以及 Opera）均内建 XMLHttpRequest 对象（IE5、IE6使用 ActiveXObject）。
	// XMLHttpRequest 用于在后台与服务器交换数据。这意味着可以在不重新加载整个网页的情况下，对网页的某部分进行更新。
		xmlhttp = new XMLHttpRequest();// IE7+、Firefox、Chrome、Opera、Safari 浏览器执行代码
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");// IE6, IE5 浏览器执行代码
	}

	// 如需将请求发送到服务器，我们使用 XMLHttpRequest 对象的 open() 和 send() 方法：
	xmlhttp.open("GET", "/JavaWeb/SpringMVC/ajax", true);// 注意路径，要加上“/JavaWeb”
	xmlhttp.send();

	xmlhttp.onreadystatechange = function() {// 这个方法可以放在send()方法之后。
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
//			document.getElementById("123").innerHTML = xmlhttp.responseText;
			document.getElementById("123").value = xmlhttp.responseText;
		}
	}

}
