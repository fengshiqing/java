

function query() {

    var username = document.getElementsByName("username");//对象是单选选项

        if(username) {//是否选中
            document.getElementById("query").action = projectName+"/query.do?currentPage=1&stsex="+ sex;

        }else{

            document.getElementById("query").action = projectName+"/query.do?currentPage=1";

        }


    document.getElementById("query").submit();//提交到后台

}