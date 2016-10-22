/*$(function() {
	var settings = {
		items: 10,
		displayedPages: 3,
		cssStyle: "light-theme",
		// prevText: "<<",
		// nextText: ">>",
		onPageClick: function(currentPageNumber) {
			showPage(currentPageNumber);
		}
	};
	$(".pagination").pagination(settings);
	function showPage(currentPageNumber) {
		var page="#page_" + currentPageNumber;
		$(".selection").hide();
		$(page).show();
	}
});*/


/*paginationの実装*/

/*現在のページ数を格納する変数*/
var page = 1;
/*1ページに表示する件数を格納する変数*/
var page_cnt = 10;

$(function(){
    $.ajax({
        type : "POST",
        url : 
        dataType : "json";
        success : function(json) {
            
    }
    })
})