/* Sidebar Menu*/
$(document).ready(function () {
    $('.nav > li > a').click(function(){
        if ($(this).attr('class') != 'active'){
            $('.nav li ul').slideUp();
            $(this).next().slideToggle();
            $('.nav li a').removeClass('active');
            $(this).addClass('active');
        }
    });
});
//Notice 公告页面 js
$("#notice-btn").click(function () {
    var text=$(".notice-textarea").val();
    console.log(text);
    $.ajax({
        url: "/Admin/reviseNotice",
        data: {
            "text":text,
        },
        type:"Get",
        success: function (r) {
            console.log(r);
            if(r){
                alert("公告更新成功");
                window.location.href="/Admin/Notice";
            }else {
                alert("更新失败，情联系管理员");
            }
        }
    })
})