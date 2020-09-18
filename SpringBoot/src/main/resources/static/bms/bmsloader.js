/**
 * 功能描述：后台管理系统的初始化页面。
 * 此js用来手动启动angularjs应用。
 */
var bmsApp = angular.module("bmsApp", ["ngRoute"]); // 传一个参数就是获取，传空数组就是创建
// angular在执行控制器函数时，会根据参数的名字去自定的注入对象，因此参数名必须注意不能出错，注意大小写
// bmsApp.controller("bmsController", function ($scope, $http, $routeParams) { // 这种写法，在压缩代码时，会改变参数的名字
bmsApp.controller("bmsController", ["$scope", "$http", function app($scope, $http) { // 这种写法，在压缩代码时，会改变参数的名字

    // 初始化数据
    const Model = $scope.Model = {
        tabPageNum: 1,
        Constant: {},
        userInfo: {
            name: "王五",
            age: 21
        },
        Tips: {},
        tableDate: [
            {id: 1, name: "111", age: 12},
            {id: 1, name: "111", age: 12},
            {id: 1, name: "111", age: 12},
            {id: 1, name: "111", age: 12},
            {id: 1, name: "111", age: 12},
            {id: 1, name: "111", age: 12},
        ],
    };

    $scope.changeTab = function (tabPageNum) {
        debugger;
        Model.tabPageNum = tabPageNum;
    }
    console.log("【应用日志：王五】")

    // angular基本不用操作DOM，如果必要，可以使用angular自带的jqlite，使用方法如下：
    // angular.element("body"); // 获取body元素标签


    /*
    $scope.users=[{'username':"zhangsan","email":"zs@11.com"},
      {'username':"zhangsan2","email":"zs@22.com"},
      {'username':"zhangsan3","email":"zs@33.com"}];
    */
    $scope.queryDate = function () {
        debugger;
        // 看这个：https://blog.csdn.net/qq_31490071/article/details/80704224
        const data = {username: $scope.username, email: $scope.email};
        $http({
            url: "/angular/hello",
            method: "POST", // GET/DELETE/HEAD/JSONP/POST/PUT
            headers: {'Content-Type': 'application/x-www-form-urlencoded', 'Authentication':'abc'}, // 请求头
            // params: {'username':'john','age':27,'phone':'1233232323'}, // 可以是对象，最终会被转换为字符串，格式：?param1=value1&param2=value2
            data: data //包含了将被当做消息体发送给服务器的数据，通常在POST请求时使用
        }).then(
            function successCallback(response) {
                debugger;
                // 其中响应参数 response 必须是一个json对象，或者是一个能被转化为json的字符串，包含5个参数：
                // 1.data:响应体，就是后台响应后返回的数据。格式为字符串或对象
                // 2.status:http返回的状态码,如200，表示服务器响应成功;
                // 3.headers(函数)：头信息的getter函数，可以接受一个参数，用来获取对应名字的值
                // 4.config(对象)：生成原始请求的完整设置对象
                // 5.statusText:相应的http状态文本，如"ok"

                console.log("【查询数据 成功】");
                console.log(response);
            },
            function errorCallback(response) {
                debugger;
                console.log("【查询数据 失败】");
                console.log(response);
            }
        );
    }


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
    }).when("/angular/index", {
        templateUrl: "http://localhost:8080/angular/index.html",
        controller: "IndexCtrl"
    }).otherwise({
        redirectTo: ""
    });
}]);
console.log("【加载 bmsloader.js 完成】");
