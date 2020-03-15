$(function () {

    $("#from").click(function(){
        debugger;
        $("form").submit(function(e){
            alert("Submitted");
        });
    });

});