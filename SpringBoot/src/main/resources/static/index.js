/**
 * JS源码文件
 */
function aaa() {
	alert("hello");
}

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
	xmlhttp.open("GET", "/redis/setValue", true);
	xmlhttp.send();
	
	xmlhttp.onreadystatechange = function() {// 这个方法可以放在send()方法之后。
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
//			document.getElementById("123").innerHTML = xmlhttp.responseText;
			document.getElementById("123").value = xmlhttp.responseText;
		}
	}
	
}


var loginApp = angular.module('loginApp', []); // ng-app指令指明的应
loginApp.controller("loginAppController", function($scope, $rootScope, $location, $http){ // 参数必须小写，字母s不能大写
	$scope.name = "kunning";

	$scope.username= "";
	$scope.email= "";
	$scope.password= "";

	console.log("当前页面的URL：" + $location.absUrl());

	$scope.submit = function ($scope) {
		debugger;
		$http({
			method: 'GET',
			url: '/login'
		}).then(function successCallback(response) {
			alert("成功");
			// 请求成功执行代码
		}, function errorCallback(response) {
			// 请求失败执行代码
		});
	}

});



