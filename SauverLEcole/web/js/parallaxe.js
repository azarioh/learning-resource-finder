		
		var SCROLL_1 =0.5;
		var SCROLL_2 =3;
		var SCROLL_3 =2;
		var PENTE = 0.36;
		var scroll1;
		var scroll2;
		var scroll3;		
		var bodyWidth;
		var bodyHeight;
		
		var docElem = document.documentElement;

		function getDimensions(){
			bodyHeight = docElem.clientHeight;
			bodyWidth = docElem.clientWidth;
			if(bodyHeight == null)
			{
				bodyHeight = $(window).height();			
			}
			if(bodyWidth == null)
			{
				bodyWidth = $(window).width();				
			}
			if(bodyHeight == null)
			{
				bodyHeight = $(document).height();			
			}
			if(bodyWidth == null)
			{
				bodyWidth = $(document).width();				
			}
		};

		/*
		$(window).load(function() 
		{
			calculDimensions() 
			alert("window : load : "+bodyWidth+" : "+bodyHeight);		
		});
		$(document).load(function() 
		{
			calculDimensions() 
			alert("document : load : "+bodyWidth+" : "+bodyHeight);
		});
		$(window).ready(function() 
		{		
			calculDimensions() 
			alert("window : ready : "+bodyWidth+" : "+bodyHeight);
		});
		$(document).ready(function() 
		{	
			calculDimensions() 	
			alert("document : ready : "+bodyWidth+" : "+bodyHeight);
		});
		
		
		*/
		
		
		
		
		
		
		
		$(window).ready(function() 
		{
			$('#parallax-bg1').css('z-index',100);
			$('#parallax-people').css('z-index',110);				
			$('#parallax-bg2').css('z-index',120);		
			$('#parallax-bg3').css('z-index',130);		
		
			calculDimensions() 
			parallaxScroll();	
			$("object.parralaxObject").width(bodyWidth);
			$("#parallaxBG").css("display","block");			
			parallaxScroll();	
			window.addEventListener('resize', function(event)
			{
				calculDimensions();				
				$("object.parralaxObject").width(bodyWidth);
				parallaxScroll();
			});
		});
		
		function calculDimensions() 
		{
			/*bodyWidth = $(window).width();
			bodyHeight = $("body").height();
			*/
			
			bodyHeight = docElem.clientHeight;
			bodyWidth = docElem.clientWidth;
			
			scroll1 = SCROLL_1*bodyWidth/100;
			scroll2 = SCROLL_2*bodyWidth/100;
			scroll3 = SCROLL_3*bodyWidth/100;				
		}

		$(window).bind('scroll',function(e)
		{
			parallaxScroll();
		});

		function parallaxScroll()
		{
			var scrolled = $(window).scrollTop();
			bodyHeight = $("body").height();
			$('#parallax-bg1').css('bottom',(scrolled/bodyHeight)*scroll1-scroll1+'px');
			$('#parallax-bg2').css('bottom',(scrolled/bodyHeight)*scroll2-scroll2+'px');
			$('#parallax-bg3').css('bottom',(scrolled/bodyHeight)*scroll3-scroll3-2+'px');
			
			
			$('#parallax-people').css('bottom',(scrolled/bodyHeight)*(scroll1+(bodyWidth/20))-scroll1+'px');
			$('#parallax-people').css('left',(scrolled/bodyHeight)*(scroll1+(bodyWidth/10))+'px');
		}