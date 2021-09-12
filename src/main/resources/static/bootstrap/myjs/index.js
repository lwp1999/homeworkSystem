$(function () {
    $(".nav_content").hide();
    $(".menu_content").hide();
    $(".nav_title").click(function () {
        //没用上
        if($(this).children().eq(1).children().eq(0).attr("src")=="../img/up.png"){
            $(this).siblings().hide(100);
            $(this).children().eq(1).children().eq(0).attr("src","../img/down.png");
        }
        else {
            $(".nav_title").siblings().hide(350);

            for(var i = 0; i < $("aside").children().length; i++){
                $("aside").children().eq(i).children().eq(0).children().eq(1).children().eq(0).attr("src","../img/down.png");
            }

            $(this).siblings().show(100);
            $(this).children().eq(1).children().eq(0).attr("src","../img/up.png");
            $(".nav_title").css("background","#21A6BB");
            $(this).css("background","#1385FF");
        }
    });

    $(".nav_content").click(function () {
        var n1 = $(this).parent().index();
        var n2 = $(this).index() - 1;

        $(".menu_content").hide();
        $("header").children().eq(n1).children().eq(n2).children().attr('src', $("header").children().eq(n1).children().eq(n2).children().attr('src'));
        $("header").children().eq(n1).children().eq(n2).show();

        console.log('aiya' + $("header").children().eq(n1).children().eq(n2).children().attr('src'));
        console.log('我在这');

        for(var i =0 ;i < $(".nav_content").length; i++){
            $(".nav_content").eq(i).css("background","#21A6BB");
        }
        $(this).css("background","#1385FF");


    })


})