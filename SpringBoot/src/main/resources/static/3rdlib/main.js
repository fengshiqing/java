
require.config({
    paths : {
        'jquery': 'jquery-3.4.1.min.js',
        'bootstrap': 'bootstrap-3.3.7-dist/js/bootstrap.min',
        'angular': 'angular.min.js',
        'ui-router': 'node_modules/angular-ui-router/release/angular-ui-router.min',
        'laydate': 'static/laydate/laydate',
        'layer': 'layer.js'
    }
})

require(["jquery","a"], function($){
    $(function(){
        alert("load finished");
    })
})


//注册一个controller
require(["signinApp"], function(signinApp) {
    signinApp.controller("signinAppController",function($scope){
        $scope.text = "hello world!";
    });
});
