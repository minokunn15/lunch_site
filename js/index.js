$(function() {
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
});