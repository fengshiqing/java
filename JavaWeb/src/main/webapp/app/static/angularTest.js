var myAngularApp = angular.module('myAngularApp', []); // ng-app指令指明的应
myAngularApp.controller("myAngularAppController", function($scope, $rootScope, $location, $http){ // 参数必须小写，字母s不能大写
    $scope.name = "kunning";

    $scope.firstName= "John";
    $scope.lastName= "Doe";

    console.log("当前页面的URL：" + $location.absUrl());

    // https://www.runoob.com/angularjs/angularjs-http.html
    // 默认是异步的
    $http.get("/JavaWeb/SpringMVC/dealAjaxGetReq").then(function (response) { // ../../SpringMVC/dealAjaxGetReq
        alert(response.data);// response.data就是后台响应的数据
    });

    alert("123");
});



