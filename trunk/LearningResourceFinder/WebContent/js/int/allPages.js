/** Java Script file imported on all pages from the template.jsp */

/* start google Analytics  */
	(function(i, s, o, g, r, a, m) {
		i['GoogleAnalyticsObject'] = r;
		i[r] = i[r] || function() {
			(i[r].q = i[r].q || []).push(arguments)
		}, i[r].l = 1 * new Date();
		a = s.createElement(o), m = s.getElementsByTagName(o)[0];
		a.async = 1;
		a.src = g;
		m.parentNode.insertBefore(a, m)
	})(window, document, 'script', '//www.google-analytics.com/analytics.js',
			'ga');

	ga('create', 'UA-44711788-1', 'toujoursplus.be');
	ga('send', 'pageview');
/* End google analytics */

/* Script Connection With Facebook */
		function mPopupLogin(provid) {			
			var w = 780;
			var h = 410;
			var left = (screen.width/2)-(w/2);
		    var   top = (screen.height/2)-(h/2);
		  /*var urlLogin = "<%= UrlUtil.getAbsoluteUrl("/loginsocial?provider=") %>" + provid;*/
		    var urlLogin = "http://ToujoursPlus.be/loginsocial?provider=" + provid;
		    var signin= window.open(urlLogin, "Login", "nom_popup,menubar=no, status=no, scrollbars=no, menubar=no, width="+w+", height="+h+",left=" + left + ",top=" + top);
			return false; 			
		};
				
		$(document).ready(function(event) {		
			$(".connectionGoogle").click(function() {
				mPopupLogin("googleplus");
			});
			$(".connectionFacebook").click(function() {
				mPopupLogin("facebook");
			});	
		});
/* end script Connection With Facebook */


$(document).ready(function(event) {		
	$(".addToolTip").tooltip();  /* prepares all elements in the page that need a tooltip to get a bootstrap tooltip */
});