var PENTE = 0.60;

var percentVariationScroll1 = 2;
var percentVariationScroll2 =4;
var percentVariationScroll3 =8;

var bodyWidth;
var bodyHeight;
var screenHeight;
var screenWidth;

/*
		$(window).load(function() 
		{
			calculDimensions();
			parallaxScroll();	
		});
		$(document).load(function() 
		{
			calculDimensions() ;
			parallaxScroll();
		});
		$(window).ready(function() 
		{		
			calculDimensions() ;
			parallaxScroll();
		});
		$(document).ready(function() 
		{	
			calculDimensions() 	;
			parallaxScroll();
		});


 */

$(window).ready(function() 
{	
	calculPositionParallaxe();
	resizeParallaxe();	
	$("#parallaxBG").css("display","block");			
	window.addEventListener('resize', function(event)
	{
		calculPositionParallaxe();
		resizeParallaxe();
	});
});

function resizeParallaxe()
{
	$("object.parralaxObject").width(bodyWidth);
}




$(window).bind('scroll',function(e)
{
	calculPositionParallaxe();
});


function calculPositionParallaxe()
{
	var scrolled = $(window).scrollTop();
	calculDimensions();

	var percentOfScrool = scrolled/(bodyHeight-screenHeight);
	var negativePercentOfScrool = percentOfScrool-1;
	var hauteur1 = percentVariationScroll1 * screenHeight/100 * negativePercentOfScrool;
	var hauteur2 = percentVariationScroll2 * screenHeight/100 * negativePercentOfScrool;
	var hauteur3 = percentVariationScroll3 * screenHeight/100 * negativePercentOfScrool;
	
	
	
	$('#parallax-bg1').css('bottom',hauteur1+'px');
	$('#parallax-bg2').css('bottom',hauteur2+'px');
	$('#parallax-bg3').css('bottom',hauteur3+'px');	
	
	
	$('#parallax-people').css('bottom',hauteur1+percentOfScrool*PENTE*40+'px');
	$('#parallax-people').css('left',percentOfScrool*(1/PENTE)*40+'px');
}
function calculDimensions() 
{	
	bodyHeight = $(document).height();		
	bodyWidth = $(document).width();
	screenHeight = $(window).height();
	screenWidth = $(window).width();
};



