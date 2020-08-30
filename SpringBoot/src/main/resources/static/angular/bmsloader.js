/**
 * 功能描述：后台管理系统的初始化页面。
 * 此js用来手动启动angularjs应用。
 */
var bmsApp = angular.module("bmsApp", []); // 传一个参数就是获取，传空数组就是创建
// angular在执行控制器函数时，会根据参数的名字去自定的注入对象，因此参数名必须注意不能出错，注意大小写
// bmsApp.controller("bmsController", function ($scope, $http, $routeParams) { // 这种写法，在压缩代码时，会改变参数的名字
bmsApp.controller("bmsController", ["$scope", function ($scope) { // 这种写法，在压缩代码时，会改变参数的名字
    $scope.changeTab = function (tabPageNum) {
        debugger;
        $scope.tabPageNum = tabPageNum;
    }
    console.log("【应用日志：王五】")
    $scope.userInfo = {
        name: "王五",
        age: 21
    }
}])
// .component('bmsApp', {
//     templateUrl: "http://localhost:8080/angular/app.html"
// })
// .config(["$routeProvider", function ($routeProvider) {
//     $routeProvider.when("/home", {
//         templateUrl: "http://localhost:8080/angular/home.html",
//         resolve: {
//             $routeChangeSuccess: function ($rootScope) {
//                 $rootScope.appName = '这里是appName';
//             }
//         }
//     }).when("/angular/index", {
//         templateUrl: "http://localhost:8080/angular/index.html",
//         controller: "IndexCtrl"
//     })
// }]);
console.log("【加载 bmsloader.js 完成】");
