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
    /*お気に入りボタンクリック時*/
    $('.favoritebutton').click(function() {
        /*URLパラメーターを取得*/
        var url = location.href;
        params = url.split("?")[1].split("&");
        newsIdParam = params[0].split("=")[1];
        /*javascriptオブジェクトをjsonに変換する*/
        var obj = {newsId : null};
        obj.newsId = newsIdParam;
        var reqJson = window.JSON.stringify(obj);
        /*ajax通信*/
        $.ajax({
            type : "GET",
            url : '../Controller/Favorite',
            data : {requestNewsId : reqJson},
            dataType : 'json',
        }).done(function(data) {
            /*成功した場合*/
            $("#dialogdemo").find("p").text(data.message)
            $('#dialogdemo').dialog('open');
        }).fail(function(XMLHttpRequest, textStatus, errorThrown) {
            /*失敗した場合*/
            console.log(textStatus);
        })
    })
})