//var toFrontZIndex = 2;
//function resourceIncrease(){
//	$(".resource-content-small > .panel-heading").not(".clickAvailable").click(function() 	{
//      	if ($(this).parent().hasClass("resource-content-big")) { // If is small
//      		$(this).parent().switchClass("resource-content-big", "resource-content", 0);  // Big will be small
//      		$(this).parent().css("z-index", "1");
//      	} else {
//            $(this).parent().switchClass("resource-content", "resource-content-big", 0); // Small will be big
//            $(this).parent().css("z-index", toFrontZIndex++);  // to front (we make it more and more to front in case user opens multiple boxes that overlap
//      	}
//	});
//	$(".resource-content > .panel-heading").addClass("clickAvailable");
//}
//
//$(document).ready(function() {
//	resourceIncrease();
//});