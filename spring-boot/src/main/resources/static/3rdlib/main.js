/**
 * 功能描述：requirejs的入口配置文件，相关配置依赖在这里配置
 */
require.config({
    // baseUrl: '3rdlib/', // 设置根路径

    urlArgs: "bust=_" + new Date().toLocaleString().replace(/[^0-9]/g, ""), // 每次新加载js，为了避免缓存

    paths: {
        // 注意点：文件名不能带.js后缀，否则加载失败！！！
        "jquery": "./jquery-3.5.1.min",
        "angular": "./angular.min",
        "angular-route": "./angular-route.min",
        'domReady': 'https://cdnjs.cloudflare.com/ajax/libs/domready/1.0.8/ready.min.js',
        // "layer": "./layer",
        // "bootstrap": "./bootstrap.min",

        "bmsloader": "../angular/bmsloader",
    },

    shim: {
        "angular": {
            deps: ["jquery"],
            exports: "angular"
        },
        "angular-route": {
            deps: ["angular"],   //依赖什么模块
            exports: "angular-route"
        }
    }
})

/**
 * 功能描述：可以通过如下方式来检验下是否加载成功
 */
// requirejs(["jquery", "bmsloader", "angular"], function ($, bmsloader, angular) {
//     console.log($);
//     console.log(bmsloader);
//     console.log(angular);
//     console.log("【main.js 加载成功】");
// });

/**
 * 立即执行函数
 */
// !(function () {
//     console.log("【立即执行函数】")
//     debugger;
//     'use strict';
//     //当然了这个scripts的数据完全可以从服务器上动态获取回来加载
//
//     //依赖脚本加载
//     require(["bmsloader"], function () {
//         //渲染
//         debugger;
//         // angular.bootstrap(document, ['app']);
//     });
// }());
