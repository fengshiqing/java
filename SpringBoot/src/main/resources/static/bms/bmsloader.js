/**
 * 功能描述：后台管理系统的初始化页面。
 * 此js用来手动启动angularjs应用。
 */
var bmsApp = angular.module("bmsApp", ["ngRoute"]); // 传一个参数就是获取，传空数组就是创建
// angular在执行控制器函数时，会根据参数的名字去自定的注入对象，因此参数名必须注意不能出错，注意大小写
// bmsApp.controller("bmsController", function ($scope, $http, $routeParams) { // 这种写法，在压缩代码时，会改变参数的名字
bmsApp.controller("bmsController", ["$scope", function ($scope) { // 这种写法，在压缩代码时，会改变参数的名字

    // 初始化数据
    var Model = $scope.Model = {
        tabPageNum: 1,
        Constant:{},
        userInfo: {
            name: "王五",
            age: 21
        },
        Tips: {},
    };

    $scope.changeTab = function (tabPageNum) {
        debugger;
        $scope.Model.tabPageNum = tabPageNum;
    }
    console.log("【应用日志：王五】")

    // angular基本不用操作DOM，如果必要，可以使用angular自带的jqlite，使用方法如下：
    // angular.element("body"); // 获取body元素标签
}])
// .component('bmsApp', {
//     templateUrl: "http://localhost:8080/angular/app.html"
// })
// 路由配置
.config(["$routeProvider", function ($routeProvider) {
    $routeProvider.when("/home", {
        templateUrl: "http://localhost:8080/angular/home.html",
        controller: "",
        resolve: {
            $routeChangeSuccess: function ($rootScope) {
                $rootScope.appName = '这里是appName';
            }
        }
    })
    .when("/angular/index", {
        templateUrl: "http://localhost:8080/angular/index.html",
        controller: "IndexCtrl"
    })
    .otherwise({
        redirectTo: ""
    });
}]);
console.log("【加载 bmsloader.js 完成】");
