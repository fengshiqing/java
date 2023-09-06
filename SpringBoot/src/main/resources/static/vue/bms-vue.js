/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

var vm = new Vue({
    el: '#app',
    data: {
        email:"",
        telephone:"",
        password:"",
        message: 'Hello Vue.js!',
        newTodoText: '',
        visitCount: 0,
        hideCompletedTodos: false,
        todos: [],
        error: null
    },
    methods:{
        signin: function () {
            console.log(this.$data.email);
            console.log(this.email);
            window.location.href = "/hello/helloAdmin";
        }
    }
});
