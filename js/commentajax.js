/*記事をお気に入りを登録したときにajaxで記事IDをservletに引き渡す*/
$(function() {
    /*ポップアップを作成*/
    $('#dialogdemo').dialog({
        autoOpen: false,
        closeOnEscape: false,
        modal: true,
        button: {
            "OK": function(){
            $(this).dialog('close');
            }
        }
    });
    /*HTMLタグをエスケープする関数*/
    function escapeHTML2(html) {
    	  return jQuery('<div>').text(html).html();
    }
    /*お気に入りボタンクリック時*/
    $('#commentbutton').click(function() {
    	console.log("success");
        /*パラメーターを取得*/
        var url = location.href;
        params = url.split("?")[1].split("&");
        newsIdParam = params[0].split("=")[1];
        var ratingParam = $(".evalcount").val();
        var commentParam = escapeHTML2($(".textcomment").val());
        /*パラメーターを渡すjavascriptオブジェクトを作成*/
        var obj = {newsId : null,rating : null,comment : null};
        obj.newsId = newsIdParam;
        obj.rating = ratingParam;
        obj.comment = commentParam;
        var reqJson = window.JSON.stringify(obj);
        /*ajax通信*/
        $.ajax({
            type : "POST",
            url : '../Controller/CommentComplete',
            data : {requestParams : reqJson},
            dataType : 'json',
        }).done(function(data) {
            /*成功した場合*/
            $("#dialogdemo").find("p").text(data.message);
            $('#dialogdemo').dialog('open');
            /*要素の中に今投稿したコメントを表示させる(insertできた場合)*/
            if (data.message == "コメント投稿完了しました") {
                var stars = "";
                for (var i = 0;i < ratingParam;i++) {
                	stars += "<i class='fa fa-star' aria-hidden='true'></i>";
                }
                var nowComment = "<li>"
                	           + stars
                	           + "<p class='other_comment'>"+commentParam+"</p>"
                	           + "<p class='other'>"+"by" +$(".nickname").val()+"</p>"
                	           + "</li>";
                $(".otherlist").append(nowComment);
            }
            console.log("ajsx");
        }).fail(function(XMLHttpRequest, textStatus, errorThrown) {
            /*失敗した場合*/
            console.log(textStatus);
        })
    })
})